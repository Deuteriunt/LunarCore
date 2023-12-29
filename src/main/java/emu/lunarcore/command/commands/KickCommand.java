package emu.lunarcore.command.commands;

import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;

@Command(
    label = "kick",
    desc = "/kick @[玩家uid]. 将指定玩家踢出服务器.",
    requireTarget = true,
    permission = "player.kick"
)
public final class KickCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        // Kick player
        args.getTarget().getSession().close();

        // Send message
        args.sendMessage("已成功踢出玩家");
    }
}
