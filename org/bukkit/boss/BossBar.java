package org.bukkit.boss;

import java.util.List;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BossBar {
  @NotNull
  String getTitle();
  
  void setTitle(@Nullable String paramString);
  
  @NotNull
  BarColor getColor();
  
  void setColor(@NotNull BarColor paramBarColor);
  
  @NotNull
  BarStyle getStyle();
  
  void setStyle(@NotNull BarStyle paramBarStyle);
  
  void removeFlag(@NotNull BarFlag paramBarFlag);
  
  void addFlag(@NotNull BarFlag paramBarFlag);
  
  boolean hasFlag(@NotNull BarFlag paramBarFlag);
  
  void setProgress(double paramDouble);
  
  double getProgress();
  
  void addPlayer(@NotNull Player paramPlayer);
  
  void removePlayer(@NotNull Player paramPlayer);
  
  void removeAll();
  
  @NotNull
  List<Player> getPlayers();
  
  void setVisible(boolean paramBoolean);
  
  boolean isVisible();
  
  @Deprecated
  void show();
  
  @Deprecated
  void hide();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\boss\BossBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */