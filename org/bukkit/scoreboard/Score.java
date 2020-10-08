package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Score {
  @Deprecated
  @NotNull
  OfflinePlayer getPlayer();
  
  @NotNull
  String getEntry();
  
  @NotNull
  Objective getObjective();
  
  int getScore() throws IllegalStateException;
  
  void setScore(int paramInt) throws IllegalStateException;
  
  boolean isScoreSet() throws IllegalStateException;
  
  @Nullable
  Scoreboard getScoreboard();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\scoreboard\Score.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */