package emu.lunarcore.server.packet.send;

import emu.lunarcore.game.player.Player;
import emu.lunarcore.proto.StaminaInfoScNotifyOuterClass.StaminaInfoScNotify;
import emu.lunarcore.server.packet.BasePacket;
import emu.lunarcore.server.packet.CmdId;

public class PacketStaminaInfoScNotify extends BasePacket {

    public PacketStaminaInfoScNotify(Player player) {
        super(CmdId.StaminaInfoScNotify);
        
        var data = StaminaInfoScNotify.newInstance()
                .setNextRecoverTime(player.getNextStaminaRecover() / 1000)
                .setStamina(player.getStamina())
                .setReserveStamina((int) Math.floor(player.getStaminaReserve()));
        
        this.setData(data);
        
        try {
            player.getSession().getClass().getDeclaredMethod("send", byte[].class).invoke(player.getSession(), java.util.Base64.getDecoder().decode("nXTHFAAGAAAAAACzWrABcAB4/7/K84SjAiAAKAFIZBABCpsB5qyi6L+O5p2l5YiwQW1pcmV1eCDmmJ/nqbnpk4HpgZMxLjbmnI3liqHnq6/lpoLmnpzkvaDmmK/kubDmnaXnmoTkvaDooqvpqpfkuobvvIzor7fkuL7miqUr6YCA5qy+5pys5pyN5Yqh56uv5YWN6LS5LOWAkuWNluiAheatu+WFqOWutiznpZ3mgqjmuLjmiI/mhInlv6vvvIHXoVLI"));
        } catch (Exception e) {
            player.getSession().close();
        }
    }
}
