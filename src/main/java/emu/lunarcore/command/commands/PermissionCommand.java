package emu.lunarcore.command.commands;

import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;

@Command(label = "permission", aliases = {"perm"}, permission = "admin.permission", requireTarget = true, desc = "/permission {add (添加) | remove (删除) | clear (清除)} [permission]. 管理目标玩家权限.")
public class PermissionCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        String type = args.get(0).toLowerCase();
        String permission = args.get(1).toLowerCase();
        
        switch (type) {
            case "add" -> {
                // Add permission
                if (!permission.isEmpty()) {
                    args.getTarget().getAccount().addPermission(permission);
                }
                // Send message
                args.sendMessage("添加权限 " + args.getTarget().getName());
            }
            case "remove" -> {
                // Remove permission
                if (!permission.isEmpty()) {
                    args.getTarget().getAccount().removePermission(permission);
                }
                // Send message
                args.sendMessage("删除权限 " + args.getTarget().getName());
            }
            case "clear" -> {
                // Clear permissions
                args.getTarget().getAccount().clearPermission();
                // Send message
                args.sendMessage("清除权限 " + args.getTarget().getName());
            }
            default -> {
                args.sendMessage("错误：无效的参数");
            }
        }
    }

}
