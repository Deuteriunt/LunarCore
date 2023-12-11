package emu.lunarcore.command.commands;

import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import emu.lunarcore.game.account.AccountHelper;
import emu.lunarcore.util.Utils;

@Command(label = "account", permission = "admin.account", desc = "/account {create | delete} [用户名] (玩家uid). Creates（创建）或者 deletes(删除) 一个账号.")
public class AccountCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        if (args.size() < 2) {
            args.sendMessage("参数数量无效");
            return;
        }
        
        String command = args.get(0).toLowerCase();
        String username = args.get(1);

        switch (command) {
            case "create" -> {
                // Reserved player uid
                int reservedUid = 0;
                
                if (args.size() >= 3) {
                    reservedUid = Utils.parseSafeInt(args.get(2));
                }
    
                if (AccountHelper.createAccount(username, null, reservedUid) != null) {
                    args.sendMessage("帐号已创建");
                } else {
                    args.sendMessage("账号已存在");
                }
            }
            case "delete" -> {
                if (AccountHelper.deleteAccount(username)) {
                    args.sendMessage("账号已删除");
                } else {
                    args.sendMessage("帐号不存在");
                }
            }
        }
    }

}
