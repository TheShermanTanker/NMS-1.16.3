package org.bukkit.entity;

import java.util.List;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AreaEffectCloud extends Entity {
  int getDuration();
  
  void setDuration(int paramInt);
  
  int getWaitTime();
  
  void setWaitTime(int paramInt);
  
  int getReapplicationDelay();
  
  void setReapplicationDelay(int paramInt);
  
  int getDurationOnUse();
  
  void setDurationOnUse(int paramInt);
  
  float getRadius();
  
  void setRadius(float paramFloat);
  
  float getRadiusOnUse();
  
  void setRadiusOnUse(float paramFloat);
  
  float getRadiusPerTick();
  
  void setRadiusPerTick(float paramFloat);
  
  @NotNull
  Particle getParticle();
  
  void setParticle(@NotNull Particle paramParticle);
  
  <T> void setParticle(@NotNull Particle paramParticle, @Nullable T paramT);
  
  void setBasePotionData(@NotNull PotionData paramPotionData);
  
  @NotNull
  PotionData getBasePotionData();
  
  boolean hasCustomEffects();
  
  @NotNull
  List<PotionEffect> getCustomEffects();
  
  boolean addCustomEffect(@NotNull PotionEffect paramPotionEffect, boolean paramBoolean);
  
  boolean removeCustomEffect(@NotNull PotionEffectType paramPotionEffectType);
  
  boolean hasCustomEffect(@Nullable PotionEffectType paramPotionEffectType);
  
  void clearCustomEffects();
  
  @NotNull
  Color getColor();
  
  void setColor(@NotNull Color paramColor);
  
  @Nullable
  ProjectileSource getSource();
  
  void setSource(@Nullable ProjectileSource paramProjectileSource);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\AreaEffectCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */