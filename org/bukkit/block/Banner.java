package org.bukkit.block;

import java.util.List;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.jetbrains.annotations.NotNull;

public interface Banner extends TileState {
  @NotNull
  DyeColor getBaseColor();
  
  void setBaseColor(@NotNull DyeColor paramDyeColor);
  
  @NotNull
  List<Pattern> getPatterns();
  
  void setPatterns(@NotNull List<Pattern> paramList);
  
  void addPattern(@NotNull Pattern paramPattern);
  
  @NotNull
  Pattern getPattern(int paramInt);
  
  @NotNull
  Pattern removePattern(int paramInt);
  
  void setPattern(int paramInt, @NotNull Pattern paramPattern);
  
  int numberOfPatterns();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Banner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */