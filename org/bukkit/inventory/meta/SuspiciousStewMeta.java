package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public interface SuspiciousStewMeta extends ItemMeta {
  boolean hasCustomEffects();
  
  @NotNull
  List<PotionEffect> getCustomEffects();
  
  boolean addCustomEffect(@NotNull PotionEffect paramPotionEffect, boolean paramBoolean);
  
  boolean removeCustomEffect(@NotNull PotionEffectType paramPotionEffectType);
  
  boolean hasCustomEffect(@NotNull PotionEffectType paramPotionEffectType);
  
  boolean clearCustomEffects();
  
  SuspiciousStewMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\SuspiciousStewMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */