package emu.lunarcore;

import java.util.List;
import java.util.Set;

import emu.lunarcore.data.common.ItemParam;
import lombok.Getter;

@Getter
public class Config {

    public DatabaseInfo accountDatabase = new DatabaseInfo();
    public DatabaseInfo gameDatabase = new DatabaseInfo();
    public InternalMongoInfo internalMongoServer = new InternalMongoInfo();
    public boolean useSameDatabase = true;

    public KeystoreInfo keystore = new KeystoreInfo();

    public HttpServerConfig httpServer = new HttpServerConfig(443);
    public GameServerConfig gameServer = new GameServerConfig(23301);
    
    public ServerOptions serverOptions = new ServerOptions();
    public LogOptions logOptions = new LogOptions();
    public DownloadData downloadData = new DownloadData();

    public String resourceDir = "./resources";
    public String dataDir = "./data";

    @Getter
    public static class DatabaseInfo {
        public String uri = "mongodb://localhost:27017";
        public String collection = "lunarcore";
        public boolean useInternal = true;
    }

    @Getter
    public static class InternalMongoInfo {
        public String address = "localhost";
        public int port = 27017;
        public String filePath = "database.mv";
    }

    @Getter
    public static class KeystoreInfo {
        public String path = "./keystore.p12";
        public String password = "lunar";
    }

    @Getter
    private static class ServerConfig {
        public String bindAddress = "0.0.0.0";
        public String publicAddress = "127.0.0.1";
        public int port;

        public ServerConfig(int port) {
            this.port = port;
        }
    }
    
    @Getter
    public static class HttpServerConfig extends ServerConfig {
        public boolean useSSL = true;
        public long regionListRefresh = 60_000; // Time in milliseconds to wait before refreshing region list cache again

        public HttpServerConfig(int port) {
            super(port);
        }
        
        public String getDisplayAddress() {
            return (useSSL ? "https" : "http") + "://" + publicAddress + ":" + port;
        }
    }

    @Getter
    public static class GameServerConfig extends ServerConfig {
        public String id = "lunar_rail_test";
        public String name = "Amireux";
        public String description = "Amireux HSR Test Server";
        public int kcpInterval = 40;

        public GameServerConfig(int port) {
            super(port);
        }
    }
    
    @Getter
    public static class ServerOptions {
        public boolean autoCreateAccount = true;
        public int sceneMaxEntites = 500;
        public boolean spendStamina = true;
        public boolean unlockAllChallenges = true;
        public int staminaRecoveryRate = 5 * 60;
        public int staminaReserveRecoveryRate = 18 * 60;
        public String language = "EN";
        public Set<String> defaultPermissions = Set.of("*");
        
        public ServerProfile serverFriendInfo = new ServerProfile();
        public WelcomeMail welcomeMail = new WelcomeMail();
        
        public int getStaminaRecoveryRate() {
            return staminaRecoveryRate > 0 ? staminaRecoveryRate : 1;
        }
        
        public int getStaminaReserveRecoveryRate() {
            return staminaReserveRecoveryRate > 0 ? staminaReserveRecoveryRate : 1;
        }
    }
    
    @Getter
    public static class ServerProfile {
        public String name = "Amireux";
        public String signature = "输入 /help 以获取命令列表，\\n" + //
                "欢迎来到Amireux 星穹铁道1.5服务端，如果你是买来的你被骗了，请举报+退款，本服务端免费，倒卖者死全家.\\n" + //
                "祝您游戏愉快";
        public int level = 114514;
        public int headIcon = 201302;
        public int chatBubbleId = 0;
        public int displayAvatarId = 1302;
        public int displayAvatarLevel = 114514;
    }
    
    @Getter
    public static class WelcomeMail {
        public String title;
        public String sender;
        public String content;
        public List<ItemParam> attachments;
        
        public WelcomeMail() {
            this.title = "欢迎来到Amireux星穹铁道1.5服务器.";
            this.sender = "Amireux";
            this.content = "欢迎来到Amireux 星穹铁道1.5服务端\n如果你是买来的你被骗了，请举报+退款\n本服务端免费，倒卖者死全家.祝您游戏愉快！";
            this.attachments = List.of(
                new ItemParam(2, 1000000),
                new ItemParam(101, 100),
                new ItemParam(102, 100),
                new ItemParam(1001, 1),
                new ItemParam(1002, 1),
                new ItemParam(1217, 1),
                new ItemParam(1215, 1),
                new ItemParam(1204, 1)
            );
        }
    }
    
    @Getter
    public static class LogOptions {
        public boolean commands = true;
        public boolean connections = true;
        public boolean packets = false;
        public boolean filterLoopingPackets = false;
    }
    
    @Getter
    public static class DownloadData {
        public String assetBundleUrl = null;
        public String exResourceUrl = null;
        public String luaUrl = null;
        public String ifixUrl = null;
    }

}
