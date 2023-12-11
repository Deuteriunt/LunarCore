package emu.lunarcore.command.commands;

import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import emu.lunarcore.game.player.lineup.PlayerLineup;

@Command(label = "heal", permission = "player.heal", requireTarget = true, desc = "/heal. 恢复角色所有生命")
public class HealCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        PlayerLineup lineup = args.getTarget().getCurrentLineup();
        lineup.forEachAvatar(avatar -> {
            avatar.setCurrentHp(lineup, 10000);
            avatar.save();
        });
        lineup.refreshLineup();

        args.sendMessage("已治疗所有角色 " + args.getTarget().getName());
    }

}
