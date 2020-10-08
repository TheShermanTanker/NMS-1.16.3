package org.bukkit.entity;

import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.Nullable;

public interface Projectile extends Entity {
  @Nullable
  ProjectileSource getShooter();
  
  void setShooter(@Nullable ProjectileSource paramProjectileSource);
  
  boolean doesBounce();
  
  void setBounce(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Projectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */