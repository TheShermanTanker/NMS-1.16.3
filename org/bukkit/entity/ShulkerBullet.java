package org.bukkit.entity;

import org.jetbrains.annotations.Nullable;

public interface ShulkerBullet extends Projectile {
  @Nullable
  Entity getTarget();
  
  void setTarget(@Nullable Entity paramEntity);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\ShulkerBullet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */