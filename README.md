![LunarCore](https://socialify.git.ci/Melledy/LunarCore/image?description=1&descriptionEditable=A%20game%20server%20reimplementation%20for%20version%201.5.0%20of%20a%20certain%20turn-based%20anime%20game%20for%20educational%20purposes.%20&font=Inter&forks=1&issues=1&language=1&name=1&owner=1&pulls=1&stargazers=1&theme=Light)
<div align="center"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/Melledy/LunarCore?logo=java&style=for-the-badge"> <img alt="GitHub" src="https://img.shields.io/github/license/Melledy/LunarCore?style=for-the-badge"> <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/Melledy/LunarCore?style=for-the-badge"> <img alt="GitHub Workflow Status" src="https://img.shields.io/github/actions/workflow/status/Melledy/LunarCore/build.yml?branch=development&logo=github&style=for-the-badge"></div>

<div align="center"><a href="https://discord.gg/cfPKJ6N5hw"><img alt="Discord - Grasscutter" src="https://img.shields.io/discord/1163718404067303444?label=Discord&logo=discord&style=for-the-badge"></a></div>

[EN](README.md) | [简中](docs/README_zh-CN.md) | [繁中](docs/README_zh-TW.md) | [JP](docs/README_ja-JP.md) | [RU](docs/README_ru-RU.md) | [FR](docs/README_fr-FR.md) | [KR](docs/README_ko-KR.md)

**Attention:** For any extra support, questions, or discussions, check out our [Discord](https://discord.gg/cfPKJ6N5hw).

### Notable features
- Basic game features: Logging in, team setup, inventory, basic scene/entity management
- Monster battles working
- Natural world monster/prop/NPC spawns
- Character techniques
- Crafting/Consumables working
- Npc shops handled
- Gacha system
- Mail system
- Friend system (Assists are not working yet)
- Forgotten hall
- Simulated universe (Runs can be finished, but many features are missing)

# Running the server and client

### Prerequisites
* [Java 17 JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

### Recommended
* [MongoDB 4.0+](https://www.mongodb.com/try/download/community)

### Compiling the server
1. Open your system terminal, and compile the server with `./gradlew jar`
2. Create a folder named `resources` in your server directory
3. Download the `Config`, `TextMap`, and `ExcelBin` folders from [https://github.com/Dimbreath/StarRailData](https://github.com/Dimbreath/StarRailData) and place them into your resources folder.
4. Download the `Config` folder from [https://gitlab.com/Melledy/LunarCore-Configs](https://gitlab.com/Melledy/LunarCore-Configs) and place them into your resources folder. REPLACE any files that your system asks about. These are for world spawns and are quite important for the server.
5. Run the server with `java -jar LunarCore.jar` from your system terminal. Lunar Core comes with a built-in internal MongoDB server for its database, so no Mongodb installation is required. However, it is highly recommended to install Mongodb anyway.
6. If you have `autoCreateAccount` set to true in the config, then you can skip creating an account. Otherwise, use the `/account` command in the server console to create one.

### Connecting with the client (Fiddler)
1. **Login with the client to an official server and Hoyoverse account at least once to download game data.**
2. Install and have [Fiddler Classic](https://www.telerik.com/fiddler) running.
3. Set fiddler to decrypt https traffic. (Tools -> Options -> HTTPS -> Decrypt HTTPS traffic) Make sure `ignore server certificate errors` is checked as well.
4. Copy and paste the following code into the Fiddlerscript tab of Fiddler Classic:

```
import System;
import System.Windows.Forms;
import Fiddler;
import System.Text.RegularExpressions;

class Handlers
{
    static function OnBeforeRequest(oS: Session) {
        if (oS.host.EndsWith(".starrails.com") || oS.host.EndsWith(".hoyoverse.com") || oS.host.EndsWith(".mihoyo.com") || oS.host.EndsWith(".bhsr.com")) {
            oS.host = "localhost"; // This can also be replaced with another IP address.
        }
    }
};
```

5. Login with your account name, the password can be set to anything.

### Server commands
Server commands can be run in the server console or in-game. There is a dummy user named "Server" in every player's friends list that you can message to use in-game commands.

```
/account {create | delete} [username] (reserved player uid). Creates or deletes an account.
/avatar lv(level) p(ascension) r(eidolon) s(skill levels). Sets the current avatar's properties.
/clear {relics | lightcones | materials | items}. Removes filtered items from the player inventory.
/gender {male | female}. Sets the player gender.
/give [item id] x[amount] lv[number]. Gives the targetted player an item.
/giveall {materials | avatars | lightcones | relics}. Gives the targeted player items.
/heal. Heals your avatars.
/help. Displays a list of available commands.
/kick @[player id]. Kicks a player from the server.
/mail [content]. Sends the targeted player a system mail.
/permission {add | remove | clear} [permission]. Gives/removes a permission from the targeted player.
/refill. Refill your skill points in open world.
/reload. Reloads the server config.
/scene [scene id] [floor id]. Teleports the player to the specified scene.
/spawn [monster/prop id] x[amount] s[stage id]. Spawns a monster or prop near the targeted player.
/unstuck @[player id]. Unstucks an offline player if theyre in a scene that doesnt load.
/worldlevel [world level]. Sets the targeted player's equilibrium level.
```
