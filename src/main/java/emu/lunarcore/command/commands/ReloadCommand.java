package emu.lunarcore.command.commands;

import emu.lunarcore.LunarCore;
import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;

@Command(label = "reload", permission = "admin.reload", desc = "/reload 重新加载服务端配置.")
public class ReloadCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        LunarCore.loadConfig();
        args.sendMessage("已重新加载服务端配置");
    }

}
