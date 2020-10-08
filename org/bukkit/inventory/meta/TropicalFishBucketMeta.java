package org.bukkit.inventory.meta;

import org.bukkit.DyeColor;
import org.bukkit.entity.TropicalFish;
import org.jetbrains.annotations.NotNull;

public interface TropicalFishBucketMeta extends ItemMeta {
  @NotNull
  DyeColor getPatternColor();
  
  void setPatternColor(@NotNull DyeColor paramDyeColor);
  
  @NotNull
  DyeColor getBodyColor();
  
  void setBodyColor(@NotNull DyeColor paramDyeColor);
  
  @NotNull
  TropicalFish.Pattern getPattern();
  
  void setPattern(@NotNull TropicalFish.Pattern paramPattern);
  
  boolean hasVariant();
  
  @NotNull
  TropicalFishBucketMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\TropicalFishBucketMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */