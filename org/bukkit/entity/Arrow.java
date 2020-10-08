package org.bukkit.entity;

import java.util.List;
import org.bukkit.Color;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Arrow extends AbstractArrow {
  void setBasePotionData(@NotNull PotionData paramPotionData);
  
  @NotNull
  PotionData getBasePotionData();
  
  @NotNull
  Color getColor();
  
  void setColor(@NotNull Color paramColor);
  
  boolean hasCustomEffects();
  
  @NotNull
  List<PotionEffect> getCustomEffects();
  
  boolean addCustomEffect(@NotNull PotionEffect paramPotionEffect, boolean paramBoolean);
  
  boolean removeCustomEffect(@NotNull PotionEffectType paramPotionEffectType);
  
  boolean hasCustomEffect(@Nullable PotionEffectType paramPotionEffectType);
  
  void clearCustomEffects();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Arrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */