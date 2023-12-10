package emu.lunarcore.game.drops;

import java.util.ArrayList;
import java.util.List;

import emu.lunarcore.GameConstants;
import emu.lunarcore.data.GameData;
import emu.lunarcore.data.common.ItemParam;
import emu.lunarcore.data.excel.ItemExcel;
import emu.lunarcore.game.battle.Battle;
import emu.lunarcore.game.inventory.GameItem;
import emu.lunarcore.game.scene.entity.EntityMonster;
import emu.lunarcore.server.game.BaseGameService;
import emu.lunarcore.server.game.GameServer;
import emu.lunarcore.util.Utils;

public class DropService extends BaseGameService {

    public DropService(GameServer server) {
        super(server);
    }

    // TODO this isnt the right way drops are calculated on the official server... but its good enough for now
    public void calculateDrops(Battle battle) {
        // Setup drop map
        var dropMap = new DropMap();
        
        // Calculate drops from monsters
        for (EntityMonster monster : battle.getNpcMonsters()) {
            var dropExcel = GameData.getMonsterDropExcel(monster.getExcel().getId(), monster.getWorldLevel());
            if (dropExcel == null || dropExcel.getDisplayItemList() == null) {
                continue;
            }
            
            for (ItemParam param : dropExcel.getDisplayItemList()) {
                int id = param.getId();
                int count = Utils.randomRange(0, 3);
                
                if (id == 2) {
                    count = dropExcel.getAvatarExpReward();
                }
                
                dropMap.addTo(id, count);
            }
        }
        
        // Mapping info
        if (battle.getMappingInfoId() > 0) {
            var mapInfoExcel = GameData.getMappingInfoExcel(battle.getMappingInfoId(), battle.getWorldLevel());
            if (mapInfoExcel != null) {
                int rolls = Math.max(battle.getCocoonWave(), 1);
                for (var dropParam : mapInfoExcel.getDropList()) {
                    for (int i = 0; i < rolls; i++) {
                        dropParam.roll(dropMap);
                    }
                }
            }
        }
        
        // Sanity check
        if (dropMap.size() == 0) {
            return;
        }
        
        // Create drops
        for (var entry : dropMap.entries()) {
            if (entry.getIntValue() <= 0) {
                continue;
            }
            
            // Create item and add it to player
            ItemExcel excel = GameData.getItemExcelMap().get(entry.getIntKey());
            if (excel == null) continue;
            
            // Add item
            if (excel.isEquippable()) {
                for (int i = 0; i < entry.getIntValue(); i++) {
                    battle.getDrops().add(new GameItem(excel, 1));
                }
            } else {
                battle.getDrops().add(new GameItem(excel, entry.getIntValue()));
            }
        }
        
        // Add to inventory
        battle.getPlayer().getInventory().addItems(battle.getDrops());
    }
    
    // TODO filler
    public List<GameItem> calculateDropsFromProp(int propId) {
        List<GameItem> drops = new ArrayList<>();
        
        drops.add(new GameItem(GameConstants.MATERIAL_HCOIN_ID, 5));
        drops.add(new GameItem(GameConstants.TRAILBLAZER_EXP_ID, 5));
        drops.add(new GameItem(GameConstants.MATERIAL_COIN_ID, Utils.randomRange(20, 100)));
        
        return drops;
    }
}
