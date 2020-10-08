package org.bukkit.block;

import java.util.Collection;
import org.bukkit.Nameable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Beacon extends TileState, Lockable, Nameable {
  @NotNull
  Collection<LivingEntity> getEntitiesInRange();
  
  int getTier();
  
  @Nullable
  PotionEffect getPrimaryEffect();
  
  void setPrimaryEffect(@Nullable PotionEffectType paramPotionEffectType);
  
  @Nullable
  PotionEffect getSecondaryEffect();
  
  void setSecondaryEffect(@Nullable PotionEffectType paramPotionEffectType);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Beacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */