package emu.lunarcore.game.player;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import emu.lunarcore.GameConstants;
import emu.lunarcore.LunarCore;
import emu.lunarcore.data.GameData;
import emu.lunarcore.game.avatar.GameAvatar;
import emu.lunarcore.game.enums.PersonalizeShowType;
import emu.lunarcore.server.packet.BasePacket;
import emu.lunarcore.server.packet.send.PacketPlayerSyncScNotify;
import emu.lunarcore.server.packet.send.PacketUnlockChatBubbleScNotify;
import emu.lunarcore.server.packet.send.PacketUnlockPhoneThemeScNotify;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import lombok.Getter;

@Getter
@Entity(value = "unlocks", useDiscriminator = false)
public class PlayerUnlockData {
    private transient Player owner;
    
    @Id private int ownerUid;
    
    private IntSet headIcons;
    private IntSet chatBubbles;
    private IntSet phoneThemes;
    
    @Deprecated // Morphia only
    public PlayerUnlockData() {}

    public PlayerUnlockData(Player player) {
        this.owner = player;
        this.ownerUid = player.getUid();
        
        // Add default head icons
        for (int iconId : GameConstants.DEFAULT_HEAD_ICONS) {
            this.addHeadIcon(iconId);
        }
        
        // Add head icons from avatars we already have
        for (GameAvatar avatar : owner.getAvatars()) {
            this.addHeadIcon(avatar.getHeadIconId());
        }
        
        // Add default chat bubble(s)
        for (var excel : GameData.getChatBubbleExcelMap().values()) {
            if (excel.getShowType() == PersonalizeShowType.Always) {
                this.addChatBubble(excel.getId());
            }
        }
        
        // Add default phone theme(s)
        for (var excel : GameData.getPhoneThemeExcelMap().values()) {
            if (excel.getShowType() == PersonalizeShowType.Always) {
                this.addPhoneTheme(excel.getId());
            }
        }
        
        this.save();
    }
    
    protected void setOwner(Player player) {
        this.owner = player;
    }
    
    public IntSet getHeadIcons() {
        if (this.headIcons == null) {
            this.headIcons = new IntOpenHashSet();
        }
        return this.headIcons;
    }
    
    public IntSet getChatBubbles() {
        if (this.chatBubbles == null) {
            this.chatBubbles = new IntOpenHashSet();
        }
        return this.chatBubbles;
    }
    
    public IntSet getPhoneThemes() {
        if (this.phoneThemes == null) {
            this.phoneThemes = new IntOpenHashSet();
        }
        return this.phoneThemes;
    }
    
    public void addHeadIcon(int headIconId) {
        boolean success = this.getHeadIcons().add(headIconId);
        
        if (success && this.getOwner().isLoggedIn()) {
            this.sendPacket(new PacketPlayerSyncScNotify(getOwner().toBoardData()));
            this.save();
        }
    }
    
    public void addChatBubble(int chatBubbleId) {
        boolean success = this.getChatBubbles().add(chatBubbleId);
        
        if (success && this.getOwner().isLoggedIn()) {
            this.sendPacket(new PacketUnlockChatBubbleScNotify(chatBubbleId));
            this.save();
        }
    }
    
    public void addPhoneTheme(int phoneThemeId) {
        boolean success = this.getPhoneThemes().add(phoneThemeId);
        
        if (success && this.getOwner().isLoggedIn()) {
            this.sendPacket(new PacketUnlockPhoneThemeScNotify(phoneThemeId));
            this.save();
        }
    }
    
    private void sendPacket(BasePacket packet) {
        this.getOwner().sendPacket(packet);
    }

    public void save() {
        LunarCore.getGameDatabase().save(this);
    }
}
