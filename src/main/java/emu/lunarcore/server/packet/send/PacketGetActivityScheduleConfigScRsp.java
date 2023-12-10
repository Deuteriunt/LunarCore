package emu.lunarcore.server.packet.send;

import emu.lunarcore.data.GameData;
import emu.lunarcore.proto.ActivityScheduleInfoOuterClass.ActivityScheduleInfo;
import emu.lunarcore.proto.GetActivityScheduleConfigScRspOuterClass.GetActivityScheduleConfigScRsp;
import emu.lunarcore.server.packet.BasePacket;
import emu.lunarcore.server.packet.CacheablePacket;
import emu.lunarcore.server.packet.CmdId;

@CacheablePacket
public class PacketGetActivityScheduleConfigScRsp extends BasePacket {

    public PacketGetActivityScheduleConfigScRsp() {
        super(CmdId.GetActivityScheduleConfigScRsp);
        
        var data = GetActivityScheduleConfigScRsp.newInstance();
        
        for (var activity : GameData.getActivityPanelExcelMap().values()) {
            if (activity.getType() != 31)
            if (activity.getType() != 11)
            if (activity.getType() != 29)
            if (activity.getType() != 32)
            if (activity.getType() != 30)
            if (activity.getType() != 17)
            if (activity.getType() != 12)
            if (activity.getType() != 29)
            if (activity.getType() != 27)
            if (activity.getType() != 22)
            if (activity.getType() != 41)
            continue;
            
            var info = ActivityScheduleInfo.newInstance()
                    .setActivityId(activity.getPanelID())
                    .setModuleId(activity.getActivityModuleID())
                    .setBeginTime(0)
                    .setEndTime(Integer.MAX_VALUE);
            
            data.addActivityScheduleList(info);
        }
        
        this.setData(data);
    }
}
