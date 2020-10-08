package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BannerMeta extends ItemMeta {
  @Deprecated
  @Nullable
  DyeColor getBaseColor();
  
  @Deprecated
  void setBaseColor(@Nullable DyeColor paramDyeColor);
  
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\BannerMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */