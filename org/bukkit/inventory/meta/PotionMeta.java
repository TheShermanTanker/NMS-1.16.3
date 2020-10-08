package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.Color;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PotionMeta extends ItemMeta {
  void setBasePotionData(@NotNull PotionData paramPotionData);
  
  @NotNull
  PotionData getBasePotionData();
  
  boolean hasCustomEffects();
  
  @NotNull
  List<PotionEffect> getCustomEffects();
  
  boolean addCustomEffect(@NotNull PotionEffect paramPotionEffect, boolean paramBoolean);
  
  boolean removeCustomEffect(@NotNull PotionEffectType paramPotionEffectType);
  
  boolean hasCustomEffect(@NotNull PotionEffectType paramPotionEffectType);
  
  @Deprecated
  boolean setMainEffect(@NotNull PotionEffectType paramPotionEffectType);
  
  boolean clearCustomEffects();
  
  boolean hasColor();
  
  @Nullable
  Color getColor();
  
  void setColor(@Nullable Color paramColor);
  
  PotionMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\PotionMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */