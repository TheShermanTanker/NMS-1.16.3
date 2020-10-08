package org.bukkit.scoreboard;

import org.jetbrains.annotations.NotNull;

public interface ScoreboardManager {
  @NotNull
  Scoreboard getMainScoreboard();
  
  @NotNull
  Scoreboard getNewScoreboard();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\scoreboard\ScoreboardManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */