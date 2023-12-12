<h2>Respawn Delay</h2>
<p><a href="https://github.com/Serilum/Respawn-Delay"><img src="https://serilum.com/assets/data/logo/respawn-delay.png"></a></p><h2>Download</h2>
<p>You can download Respawn Delay on CurseForge and Modrinth:</p><p>&nbsp;&nbsp;CurseForge: &nbsp;&nbsp;<a href="https://curseforge.com/minecraft/mc-mods/respawn-delay">https://curseforge.com/minecraft/mc-mods/respawn-delay</a><br>&nbsp;&nbsp;Modrinth: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://modrinth.com/mod/respawn-delay">https://modrinth.com/mod/respawn-delay</a></p>
<h2>Issue Tracker</h2>
<p>To keep a better overview of all mods, the issue tracker is located in a separate repository.<br>&nbsp;&nbsp;For issues, ideas, suggestions or anything else, please follow this link:</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;-> <a href="https://serilum.com/url/issue-tracker">Issue Tracker</a></p>
<h2>Pull Requests</h2>
<p>Because of the way mod loader files are bundled into one jar, some extra information is needed to do a PR.<br>&nbsp;&nbsp;A wiki page entry about it is available here:</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;-> <a href="https://serilum.com/url/pull-requests">Pull Request Information</a></p>
<h2>Mod Description</h2>
<p><a href="https://serilum.com/" rel="nofollow"><img src="https://github.com/Serilum/.cdn/blob/main/description/header/header.png" alt="" width="838" height="400"></a><br><br><a href="https://legacy.curseforge.com/minecraft/mc-mods/respawn-delay/files"><img src="https://github.com/Serilum/.cdn/raw/main/description/versions/header.png"></a><a href="https://legacy.curseforge.com/minecraft/mc-mods/respawn-delay/files/all?filter-status=1&filter-game-version=1738749986:75125" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/versions/1_20.png"></a><a href="https://legacy.curseforge.com/minecraft/mc-mods/respawn-delay/files/all?filter-status=1&filter-game-version=1738749986:73407" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/versions/1_19.png"></a><a href="https://legacy.curseforge.com/minecraft/mc-mods/respawn-delay/files/all?filter-status=1&filter-game-version=1738749986:73250" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/versions/1_18.png"></a><a href="https://legacy.curseforge.com/minecraft/mc-mods/respawn-delay/files/all?filter-status=1&filter-game-version=1738749986:73242" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/versions/1_17.png"></a><a href="https://legacy.curseforge.com/minecraft/mc-mods/respawn-delay/files/all?filter-status=1&filter-game-version=1738749986:70886" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/versions/1_16.png"></a><a href="https://legacy.curseforge.com/minecraft/mc-mods/respawn-delay/files/all?filter-status=1&filter-game-version=1738749986:68722" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/versions/1_15.png"></a><a href="https://legacy.curseforge.com/minecraft/mc-mods/respawn-delay/files/all?filter-status=1&filter-game-version=1738749986:64806" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/versions/1_14.png"></a><a href="https://legacy.curseforge.com/minecraft/mc-mods/respawn-delay/files/all?filter-status=1&filter-game-version=1738749986:628" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/versions/1_12.png"></a><br><br><strong><span style="font-size:24px">Requires the library mod&nbsp;<a style="font-size:24px" href="https://www.curseforge.com/minecraft/mc-mods/collective" rel="nofollow">Collective</a>.<br></span></strong></p>
<p><span style="font-size:18px">Respawn Delay is a mod designed for servers. It fits best in a pvp server, but can be used in events as well. Instead of instantly respawning, players enter spectator mode on death. They can fly around and see the action, until the configurable delay has been reached and they respawn. All messages sent are configurable, but by default the spectating player will receive an update on when they will respawn every second counting down. There is also a <em>/respawnall</em> command to force all spectating players to respawn.<br><br><em>Respawn delay is compatible with Gravestone mods. Just make sure to disable dropItemsOnDeath in the config to prevent duplication.</em><br></span><br><br><strong><span style="font-size:20px">Configurable:</span> <span style="color:#008000;font-size:14px"><a style="color:#008000" href="https://serilum.com/url/issue-trackerwiki/how-to-configure-mods" rel="nofollow">(&nbsp;how do I configure?&nbsp;)</a></span><br></strong><span style="font-size:12px"><strong>ignoreAdministratorPlayers</strong>&nbsp;(default = false): If enabled, player operators/administrators will not be put in spectator mode on death.<br><strong>ignoreCreativePlayers</strong>&nbsp;(default = true): If enabled, player in creative mode will not be put in spectator mode on death.<br><strong>keepItemsOnDeath</strong>&nbsp;(default = false): If enabled, players will keep their items on death, and no drop event will be ran. This will also ignore the 'dropItemsOnDeath' config.<br><strong>dropItemsOnDeath</strong>&nbsp;(default = true): If enabled, players will drop their items on death as normal vanilla behaviour.<br><br><strong>respawnAtWorldSpawn</strong>&nbsp;(default = true): If enabled, players will be respawned at the world spawn point. If disabled, they'll respawn wherever they're spectating.<br><strong>respawnWhenPlayerLogsOut</strong>&nbsp;(default = true): If enabled, players will respawn when they logout while waiting for a respawn. Prevents players from getting stuck in spectator mode.<br><strong>respawnWhenPlayerLogsIn</strong>&nbsp;(default = true): If enabled, players will respawn when they log in and are still in spectator mode. Prevents players from getting stuck in spectator mode.<br><br><strong>respawnDelayInSeconds</strong>&nbsp;(default = 10, min -1, max 3600): The delay in seconds after which a spectating player will respawn. A value of -1 makes players never respawn automatically. The '/respawnall' command can still be used.<br><br><strong>onDeathMessage</strong>&nbsp;(default = "You died! Your gamemode has been set to spectator."): The message which is sent to the player on death. Leave empty to disable.<br><strong>onRespawnMessage</strong>&nbsp;(default = "You respawned."): The message which is sent to players when they respawn. Leave empty to disable.<br><strong>waitingForRespawnMessage</strong>&nbsp;(default = "You will respawn in &lt;seconds_left&gt; seconds."): The message which is sent to players when they are waiting to be respawned. The text '&lt;seconds_left&gt;' will be replaced with the actual seconds left. Leave empty to disable.<br><br><strong>lowestPossibleYCoordinate</strong>&nbsp;(default = -10, min -50 max 256): When a player falls into the void, this determines the y position that's set after when a player enters spectator mode.<br></span><span style="font-size:10px"><br><br></span><span style="font-size:10px"><span style="text-decoration:underline;font-size:18px"><strong>Commands:</strong></span><br><span style="font-size:14px"><strong>/respawnall</strong> - Respawns all spectating players. (must have cheat-access)</span><br><br><br><span style="text-decoration:underline;font-size:18px"><strong>Images:</strong></span><br><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/respawn-delay/a.png" width="1142" height="607"></picture><br><br><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/respawn-delay/b.png" width="1142" height="607"></picture><br><br><br><span style="text-decoration:underline;font-size:18px"><strong>Some GIFs:</strong></span><br></span></p>
<p><span style="font-size:14px"><strong>A player entering spectating mode on death:</strong></span></p>
<div class="spoiler">
<p><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/respawn-delay/c.gif" width="1000" height="528"></picture></p>
</div>
<p><br><span style="font-size:14px"><strong>And the player respawning after the countdown is finished:</strong></span></p>
<div class="spoiler">
<p><picture><img src="https://github.com/Serilum/.cdn/raw/main/projects/respawn-delay/d.gif" width="1000" height="520"></picture></p>
</div>
<p><br>------------------<br><br><span style="font-size:24px"><strong>You may freely use this mod in any modpack, as long as the download remains hosted within the CurseForge or Modrinth ecosystem.</strong></span><br><br><span style="font-size:18px"><a style="font-size:18px;color:#008000" href="https://serilum.com/" rel="nofollow">Serilum.com</a> contains an overview and more information on all mods available.</span><br><br><span style="font-size:14px">Comments are disabled as I'm unable to keep track of all the separate pages on each mod.</span><span style="font-size:14px"><br>For issues, ideas, suggestions or anything else there is the&nbsp;<a style="font-size:14px;color:#008000" href="https://serilum.com/url/issue-tracker" rel="nofollow">Github repo</a>. Thanks!</span><span style="font-size:6px"><br><br></span><a href="https://ricksouth.com/donate" rel="nofollow"><img src="https://github.com/Serilum/.cdn/raw/main/description/shields/donation_rounded.svg" alt="" width="306" height="50"></a></p>