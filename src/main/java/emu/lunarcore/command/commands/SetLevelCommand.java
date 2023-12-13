package emu.lunarcore.command.commands;

import emu.lunarcore.util.Utils;
import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;

@Command(label = "setlevel", aliases = {"level"}, permission = "player.setlevel", requireTarget = true, desc = "/setlevel [等级] 设置目标玩家开拓等级.")
public class SetLevelCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        int targetLevel = Utils.parseSafeInt(args.get(0));

        args.getTarget().setLevel(targetLevel);

        args.sendMessage("将开拓等级设置为 " + args.getTarget().getLevel());
    }

}
