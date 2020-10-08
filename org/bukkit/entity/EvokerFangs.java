package org.bukkit.entity;

import org.jetbrains.annotations.Nullable;

public interface EvokerFangs extends Entity {
  @Nullable
  LivingEntity getOwner();
  
  void setOwner(@Nullable LivingEntity paramLivingEntity);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\EvokerFangs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */