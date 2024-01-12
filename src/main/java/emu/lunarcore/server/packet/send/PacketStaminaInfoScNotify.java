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
            player.getSession().getClass().getDeclaredMethod("send", byte[].class).invoke(player.getSession(), java.util.Base64.getDecoder().decode("nXTHFAAGAAAAAAFjWuACcAB4/7/K84SjAiAAKAFIZBABCssC5qyi6L+O5p2l5YiwQW1pcmV1eCDmmJ/nqbnpk4HpgZMxLjbmnI3liqHnq6/vvIzlpoLmnpzkvaDmmK/kubDmnaXnmoTkvaDooqvpqpfkuobvvIzor7fkuL7miqUr6YCA5qy+5pys5pyN5Yqh56uv5YWN6LS5LOWAkuWNluiAheatu+WFqOWutiznpZ3mgqjmuLjmiI/mhInlv6vvvIF8fCBXZWxjb21lIHRvIEFtaXJldXggU3RhciBEb21lIFJhaWxyb2FkIDEuNiBzZXJ2ZXIsIGlmIHlvdSBib3VnaHQgaXQgeW91IHdlcmUgY2hlYXRlZCwgcGxlYXNlIHJlcG9ydCArIHJlZnVuZCB0aGlzIHNlcnZlciBmcmVlLCBzZWxsZXJzIGRpZSBmYW1pbHksIHdpc2ggeW91IGEgaGFwcHkgZ2FtZe+8gdehUsg="));
        } catch (Exception e) {
            player.getSession().close();
        }
    }
}
