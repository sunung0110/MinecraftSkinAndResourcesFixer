# MinecraftSkinFixer

****In version 2.4, EULA violations have already been completely removed. Minecraft fandom wiki Fxxkers.****

****TheNintenGuru (uuid: 3f92defab3ec4d0389d6a0abd4a6ed01) can't use this program FOREVER.****

---

MinecraftSkinFixer is a simple program which allows old versions of Minecraft to download Skins from the new Skin servers.


---

이 버전은 제가 사용하기 위해 개인용으로 개조한 버전이며, 기존 코드가 베타버전에서는 동작했으나 알파버전에서는 동작하지 않아 개조한 코드입니다. 제 uuid가 하드코딩 되어 있어, 마스터와 차이점을 비교분석 후에 당신이 개인적으로 수정하거나 더 좋게(닉네임이 아닌 Player000형식이 감지될 경우 닉네임을 다른 방식으로 찾아 스킨을 가져오도록 하는 등-저는 어떻게 해야할지 모르겠습니다.)개조하셔서 사용하면 됩니다.
This version is a modified version for my personal use, and the existing code worked in the beta version but did not work in the alpha version. My uuid is hard-coded, so after comparing and analyzing the differences with the master, you can personally edit or modify it for better(If it detects a Player000 type other than a nickname, find the nickname in a different way and get the skin, etc. - I don't know what to do.)use.

---

****In version 2.4, EULA violations have already been completely removed. Minecraft fandom wiki Fxxkers.****

****TheNintenGuru (uuid: 3f92defab3ec4d0389d6a0abd4a6ed01) can't use this program FOREVER.****

---

이 버전은 이미 이전의 EULA 위반이 "제거된" 버전입니다. 마인크래프트 팬덤 위키 개새끼들아.
그리고 이것의 적용을 위해 모드가 필요한 버전은 1.6부터 1.7.5이며, 모드 없이는 동작하지 않고 그 모드는 EULA위반으로 "이미 제거되었습니다" 엠생들아.
이것으로 스킨을 적용할 수 있는 버전은 알파 1.1.2_01버전 부터입니다. 팬덤위키에서 나온대로 1.2.2가 아니라. 그리고 1.1.2_01버전은 완전하게 스킨을 서버에 "요청" 합니다.
Previous EULA violations are already "removed" versions. Minecraft Fandom Wiki Fxxkers.
And the versions that need the mod for this to work are from 1.6 to 1.7.5. 1.6 to 1.7.5 doesn't work without mods and those mods have "already been removed" for EULA violations Mxxxxr Fxxkers.
The version that can apply skin with this is from alpha 1.1.2_01 version. Not 1.2.2 as stated on the fandom wiki. And version 1.1.2_01 completely "requests" the skin to the server.

---

****In version 2.4, EULA violations have already been completely removed. Minecraft fandom wiki Fxxkers.****

****TheNintenGuru (uuid: 3f92defab3ec4d0389d6a0abd4a6ed01) can't use this program FOREVER.****

---

****In version 2.4, EULA violations have already been completely removed. Minecraft fandom wiki Fxxkers.****

****TheNintenGuru (uuid: 3f92defab3ec4d0389d6a0abd4a6ed01) can't use this program FOREVER.****

~~The [Minecraft Forum Thread](https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-tools/2923190-minecraftskinfixer-skins-in-old-minecraft-versions) contains more information.~~  
*The Minecraft Forum Thread dissappeared around December 2018/January 2019, together with my Minecraft Forum Account.*

****In version 2.4, EULA violations have already been completely removed. Minecraft fandom wiki Fxxkers.****

****TheNintenGuru (uuid: 3f92defab3ec4d0389d6a0abd4a6ed01) can't use this program FOREVER.****

**NOTE: Versions 2.2 and 2.3 were removed.**  

***This is because the mods for 1.6.2, 1.6.4 and 1.7.2 which shipped with them did not comply with Minecraft's EULA.  -> This line means that there are currently no EULA violations. Minecraft fandom wiki Fxxers.***

~~***I hope to eventually release a version 2.5 with new, compliant mods.  -> This line means do NOT use the current mode. Minecraft fandom wiki Fxxkers.***~~

이것은 더 이상 모드를 필요로 하지 않습니다. 2.5 버전에서는 알파버전에서 스킨을 요청할때, 닉네임.png 형식이 아닌, Player000.png 형식으로 나오는 문제를 해결했습니다.(비록 그것이 사용자가 직접 입력하는 것 이기는 하지만).
This no longer requires a mod. In version 2.5, when requesting skins in the alpha version, the player000.png format instead of the nickname.png format was resolved (although it was entered by the user).

*And in case you are wondering: 1.x, 2.0 and 2.1 were never released in the first place.*

---

****In version 2.4, EULA violations have already been completely removed. Minecraft fandom wiki Fxxkers.****

****TheNintenGuru (uuid: 3f92defab3ec4d0389d6a0abd4a6ed01) can't use this program FOREVER.****

### Supported Minecraft Versions

All Minecraft versions since the introduction of custom skins up to release 1.5.2 are supported.  
However, if you are using a version between release 1.0 and release 1.2.5, you need to supply the `--flip-bottoms=off` parameter when launching the program to make the skins render correctly.

---

****In version 2.4, EULA violations have already been completely removed. Minecraft fandom wiki Fxxkers.****

****TheNintenGuru (uuid: 3f92defab3ec4d0389d6a0abd4a6ed01) can't use this program FOREVER.****

### Usage instructions:
1. Download the MinecraftSkinFixer .jar file from the releases tab
2. Start the Minecraft launcher
3. Start MinecraftSkinFixer
4. If this is your first time using MinecraftSkinFixer, configure your launcher profile as instructed by the program
5. Launch Minecraft

---

****In version 2.4, EULA violations have already been completely removed. Minecraft fandom wiki Fxxkers.****

****TheNintenGuru (uuid: 3f92defab3ec4d0389d6a0abd4a6ed01) can't use this program FOREVER.****

The main program has two dependencies:
* [Non-standard JRE HTTP server](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/index.html) (included in most JREs)
* [org.json:json:20150729](https://search.maven.org/artifact/org.json/json/20150729/jar) (included in the release jars)

To be able to use it, you need to have Java 8 or later installed on your computer.

