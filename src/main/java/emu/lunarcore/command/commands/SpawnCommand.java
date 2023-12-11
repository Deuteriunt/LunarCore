package emu.lunarcore.command.commands;

import emu.lunarcore.LunarCore;
import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import emu.lunarcore.data.GameData;
import emu.lunarcore.data.config.GroupInfo;
import emu.lunarcore.data.config.MonsterInfo;
import emu.lunarcore.data.config.PropInfo;
import emu.lunarcore.data.excel.NpcMonsterExcel;
import emu.lunarcore.data.excel.PropExcel;
import emu.lunarcore.game.enums.PropState;
import emu.lunarcore.game.player.Player;
import emu.lunarcore.game.scene.entity.EntityMonster;
import emu.lunarcore.game.scene.entity.EntityProp;
import emu.lunarcore.util.Position;
import emu.lunarcore.util.Utils;

@Command(label = "spawn", permission = "player.spawn", requireTarget = true, desc = "/spawn [生物id] [场景id] x[数量] lv[等级] r[半径] 生成实体.")
public class SpawnCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        Player target = args.getTarget();
        
        if (target.getScene() == null) {
            args.sendMessage("错误：目标不在场景中");
            return;
        }
        
        // Get id
        int id = Utils.parseSafeInt(args.get(0));
        int stage = Math.max(Utils.parseSafeInt(args.get(1)), 1);
        int amount = Math.max(args.getAmount(), 1);
        int radius = Math.max(args.getRank(), 5) * 1000;
        
        // Enforce scene max entity limit
        if (target.getScene().getEntities().size() + amount >= LunarCore.getConfig().getServerOptions().getSceneMaxEntites()) {
            args.sendMessage("错误：场景中的实体数量已达到上限");
            return;
        }
        
        // Spawn monster
        NpcMonsterExcel monsterExcel = GameData.getNpcMonsterExcelMap().get(id);
        if (monsterExcel != null) {
            // Get first monster config from floor info that isnt a boss monster
            GroupInfo groupInfo = null;
            MonsterInfo monsterInfo = null;
            
            for (var group : target.getScene().getFloorInfo().getGroups().values()) {
                if (group.getMonsterList().size() == 0) continue;
                
                for (var m : group.getMonsterList()) {
                    if (m.getFarmElementID() == 0) {
                        groupInfo = group;
                        monsterInfo = m;
                        break;
                    }
                }
                
                if (monsterInfo != null) break;
            }
            
            if (monsterInfo == null || groupInfo == null) {
                args.sendMessage("错误：此场景中没有找到现有的怪物");
                return;
            }
            
            // Spawn monster
            for (int i = 0; i < amount; i++) {
                Position pos = target.getPos().clone().add(Utils.randomRange(-radius, radius), 0, Utils.randomRange(-radius, radius));
                EntityMonster monster = new EntityMonster(target.getScene(), monsterExcel, groupInfo, monsterInfo);
                monster.getPos().set(pos);
                monster.setEventId(monsterInfo.getEventID());
                monster.setOverrideStageId(stage);
                
                if (args.getLevel() > 0) {
                    monster.setOverrideLevel(Math.min(args.getLevel(), 100));
                }
                
                target.getScene().addEntity(monster, true);
            }
            
            // Send message when done
            args.sendMessage("Spawning " + amount + " monsters");
            return;
        }
        
        PropExcel propExcel = GameData.getPropExcelMap().get(id);
        if (propExcel != null) {
            // Get first prop config from floor info
            GroupInfo groupInfo = null;
            PropInfo propInfo = null;
            
            for (var group : target.getScene().getFloorInfo().getGroups().values()) {
                if (group.getPropList().size() == 0) continue;
                
                for (var p : group.getPropList()) {
                    if (p.getFarmElementID() == 0 && p.getAnchorID() == 0 && p.getCocoonID() == 0) {
                        groupInfo = group;
                        propInfo = p;
                        break;
                    }
                }
                
                if (propInfo != null) break;
            }
            
            if (propInfo == null || groupInfo == null) {
                args.sendMessage("错误：此场景中未找到现有的道具");
                return;
            }
            
            // Spawn props
            for (int i = 0; i < amount; i++) {
                Position pos = target.getPos().clone().add(Utils.randomRange(-radius, radius), 0, Utils.randomRange(-radius, radius));
                EntityProp prop = new EntityProp(target.getScene(), propExcel, groupInfo, propInfo);
                prop.getPos().set(pos);
                prop.getRot().set(0, 0, 0);
                prop.setState(PropState.Open);
                
                target.getScene().addEntity(prop, true);
            }
            
            // Send message when done
            args.sendMessage("Spawning " + amount + " props");
            return;
        }

        args.sendMessage("错误：无效 ID");
    }

}
