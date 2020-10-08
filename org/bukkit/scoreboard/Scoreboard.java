package org.bukkit.scoreboard;

import java.util.Set;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Scoreboard {
  @Deprecated
  @NotNull
  Objective registerNewObjective(@NotNull String paramString1, @NotNull String paramString2) throws IllegalArgumentException;
  
  @NotNull
  Objective registerNewObjective(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3) throws IllegalArgumentException;
  
  @NotNull
  Objective registerNewObjective(@NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3, @NotNull RenderType paramRenderType) throws IllegalArgumentException;
  
  @Nullable
  Objective getObjective(@NotNull String paramString) throws IllegalArgumentException;
  
  @NotNull
  Set<Objective> getObjectivesByCriteria(@NotNull String paramString) throws IllegalArgumentException;
  
  @NotNull
  Set<Objective> getObjectives();
  
  @Nullable
  Objective getObjective(@NotNull DisplaySlot paramDisplaySlot) throws IllegalArgumentException;
  
  @Deprecated
  @NotNull
  Set<Score> getScores(@NotNull OfflinePlayer paramOfflinePlayer) throws IllegalArgumentException;
  
  @NotNull
  Set<Score> getScores(@NotNull String paramString) throws IllegalArgumentException;
  
  @Deprecated
  void resetScores(@NotNull OfflinePlayer paramOfflinePlayer) throws IllegalArgumentException;
  
  void resetScores(@NotNull String paramString) throws IllegalArgumentException;
  
  @Deprecated
  @Nullable
  Team getPlayerTeam(@NotNull OfflinePlayer paramOfflinePlayer) throws IllegalArgumentException;
  
  @Nullable
  Team getEntryTeam(@NotNull String paramString) throws IllegalArgumentException;
  
  @Nullable
  Team getTeam(@NotNull String paramString) throws IllegalArgumentException;
  
  @NotNull
  Set<Team> getTeams();
  
  @NotNull
  Team registerNewTeam(@NotNull String paramString) throws IllegalArgumentException;
  
  @Deprecated
  @NotNull
  Set<OfflinePlayer> getPlayers();
  
  @NotNull
  Set<String> getEntries();
  
  void clearSlot(@NotNull DisplaySlot paramDisplaySlot) throws IllegalArgumentException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\scoreboard\Scoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */