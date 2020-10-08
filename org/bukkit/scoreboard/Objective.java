package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Objective {
  @NotNull
  String getName() throws IllegalStateException;
  
  @NotNull
  String getDisplayName() throws IllegalStateException;
  
  void setDisplayName(@NotNull String paramString) throws IllegalStateException, IllegalArgumentException;
  
  @NotNull
  String getCriteria() throws IllegalStateException;
  
  boolean isModifiable() throws IllegalStateException;
  
  @Nullable
  Scoreboard getScoreboard();
  
  void unregister() throws IllegalStateException;
  
  void setDisplaySlot(@Nullable DisplaySlot paramDisplaySlot) throws IllegalStateException;
  
  @Nullable
  DisplaySlot getDisplaySlot() throws IllegalStateException;
  
  void setRenderType(@NotNull RenderType paramRenderType) throws IllegalStateException;
  
  @NotNull
  RenderType getRenderType() throws IllegalStateException;
  
  @Deprecated
  @NotNull
  Score getScore(@NotNull OfflinePlayer paramOfflinePlayer) throws IllegalArgumentException, IllegalStateException;
  
  @NotNull
  Score getScore(@NotNull String paramString) throws IllegalArgumentException, IllegalStateException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\scoreboard\Objective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */