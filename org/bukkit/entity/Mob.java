package org.bukkit.entity;

import com.destroystokyo.paper.entity.Pathfinder;
import org.bukkit.loot.Lootable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Mob extends LivingEntity, Lootable {
  @NotNull
  Pathfinder getPathfinder();
  
  boolean isInDaylight();
  
  void setTarget(@Nullable LivingEntity paramLivingEntity);
  
  @Nullable
  LivingEntity getTarget();
  
  void setAware(boolean paramBoolean);
  
  boolean isAware();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Mob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */