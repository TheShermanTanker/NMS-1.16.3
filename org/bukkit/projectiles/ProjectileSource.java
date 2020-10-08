package org.bukkit.projectiles;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ProjectileSource {
  @NotNull
  <T extends org.bukkit.entity.Projectile> T launchProjectile(@NotNull Class<? extends T> paramClass);
  
  @NotNull
  <T extends org.bukkit.entity.Projectile> T launchProjectile(@NotNull Class<? extends T> paramClass, @Nullable Vector paramVector);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\projectiles\ProjectileSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */