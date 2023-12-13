package emu.lunarcore.command.commands;

import java.util.ArrayList;
import java.util.List;

import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import emu.lunarcore.data.GameData;
import emu.lunarcore.data.excel.ItemExcel;
import emu.lunarcore.game.avatar.GameAvatar;
import emu.lunarcore.game.enums.ItemMainType;
import emu.lunarcore.game.enums.ItemRarity;
import emu.lunarcore.game.enums.ItemSubType;
import emu.lunarcore.game.inventory.GameItem;
import emu.lunarcore.game.player.Player;

@Command(
        label = "giveall", 
        aliases = {"ga"}, 
        permission = "player.give", 
        requireTarget = true, 
        desc = "/giveall {materials (材料) | avatars (角色) | lightcones (光锥) | relics (遗器)} lv(等级). 给予玩家某种类型的所有物品."
)
public class GiveAllCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        Player target = args.getTarget();
        String type = args.get(0).toLowerCase();

        switch (type) {
            default -> args.sendMessage("错误：无效类型");
            case "m", "materials", "mats" -> {
                List<GameItem> items = new ArrayList<>();

                // Character/Relic/Lightcone upgrade materials
                for (ItemExcel excel : GameData.getItemExcelMap().values()) {
                    int purpose = excel.getPurposeType();
                    if ((purpose >= 1 && purpose <= 7) || purpose == 10) {
                        items.add(new GameItem(excel, excel.getPileLimit()));
                    }
                }

                // Credits
                items.add(new GameItem(2, Integer.MAX_VALUE));

                // Add to target's inventory
                target.getInventory().addItems(items, true);

                // Send message
                args.sendMessage("Giving " + target.getName() + " " + items.size() + " items");
            }
            case "lc", "lightcones" -> {
                // Make sure we dont go over the inventory limit
                var tab = args.getTarget().getInventory().getTabByItemType(ItemMainType.Equipment);
                if (tab.getSize() >= tab.getMaxCapacity()) {
                    args.sendMessage(target.getName() + " has too many of this item type");
                    return;
                }
                
                // Get lightcones
                List<GameItem> items = GameData.getItemExcelMap().values()
                        .stream()
                        .filter(ItemExcel::isEquipment)
                        .map(excel -> {
                            var item = new GameItem(excel, 1);
                            args.setProperties(item);
                            return item;
                        })
                        .toList();

                // Add to target's inventory
                target.getInventory().addItems(items, true);

                // Send message
                args.sendMessage("Giving " + target.getName() + " " + items.size() + " light cones");
            }
            case "r", "relics" -> {
                // Make sure we dont go over the inventory limit
                var tab = args.getTarget().getInventory().getTabByItemType(ItemMainType.Relic);
                if (tab.getSize() >= tab.getMaxCapacity()) {
                    args.sendMessage(target.getName() + " has too many of this item type");
                    return;
                }
                
                // Get relics
                List<GameItem> items = GameData.getItemExcelMap().values()
                        .stream()
                        .filter(excel -> excel.isRelic() && excel.getRarity() == ItemRarity.SuperRare)
                        .map(excel -> {
                            var item = new GameItem(excel, 1);
                            args.setProperties(item);
                            return item;
                        })
                        .toList();
                
                // Add to target's inventory
                target.getInventory().addItems(items, true);

                // Send message
                args.sendMessage("Giving " + target.getName() + " " + items.size() + " relics");
            }
            case "a", "characters", "avatars" -> {
                // Eidolon items
                List<GameItem> items = new ArrayList<>();
                
                // All avatars and their eidolons
                for (var excel : GameData.getAvatarExcelMap().values()) {
                    // Get avatar id
                    GameAvatar avatar = target.getAvatarById(excel.getAvatarID());
                    
                    // Add avatar
                    if (avatar == null) {
                        // Add avatar
                        avatar = new GameAvatar(excel);
                        args.setProperties(avatar); // Set avatar properties

                        target.getAvatars().addAvatar(avatar);
                    }
                    
                    // Get eidolon excel
                    ItemExcel itemExcel = GameData.getItemExcelMap().get(excel.getRankUpItemId());
                    if (itemExcel == null || itemExcel.getItemSubType() != ItemSubType.Eidolon) {
                        continue;
                    }
                    
                    // Calculate how many eidolons we need
                    int rankCount = avatar.getRank();
                    GameItem rankItem = target.getInventory().getMaterialByItemId(itemExcel.getId());
                    if (rankItem != null) {
                        rankCount += rankItem.getCount();
                    }
                    
                    // Add eidolons
                    int amount = 6 - rankCount;
                    if (amount > 0) {
                        items.add(new GameItem(itemExcel, amount));
                    }
                }
                
                // Add to target's inventory
                if (items.size() > 0) {
                    target.getInventory().addItems(items, true);
                }

                // Send message
                args.sendMessage("Giving " + target.getName() + " all avatars");
            }
            case "ic", "icons" -> {
                // Get UnlockedHeads
                for (var iconhead : GameData.getPlayerIconExcelMap().values()) {
                    // This function will handle any duplicate head icons
                    target.addHeadIcon(iconhead.getId());
                }

                // Send message
                args.sendMessage("Added all icons to " + target.getName());
            }
            case "consumables", "food" -> {
                // Get consumables
                List<GameItem> items = GameData.getItemExcelMap().values()
                        .stream()
                        .filter(excel -> excel.getItemSubType() == ItemSubType.Food)
                        .map(excel -> new GameItem(excel, 1000))
                        .toList();
                
                // Add to target's inventory
                target.getInventory().addItems(items, true);

                // Send message
                args.sendMessage("Added all consumables to " + target.getName());
            }
        }
    }

}
