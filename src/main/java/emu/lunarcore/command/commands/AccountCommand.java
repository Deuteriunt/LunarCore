package emu.lunarcore.command.commands;

import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import emu.lunarcore.game.account.AccountHelper;
import emu.lunarcore.util.Utils;

@Command(label = "account", permission = "admin.account", desc = "/account {create | delete} [username] (reserved player uid). Creates or deletes an account.")
public class AccountCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        if (args.size() < 2) {
            args.sendMessage("无效参数数量");
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
                    args.sendMessage("已创建帐户");
                } else {
                    args.sendMessage("账户已存在");
                }
            }
            case "delete" -> {
                if (AccountHelper.deleteAccount(username)) {
                    args.sendMessage("帐户已删除");
                } else {
                    args.sendMessage("账户不存在");
                }
            }
        }
    }

}
