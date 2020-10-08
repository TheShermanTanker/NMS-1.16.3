package org.bukkit.entity;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface Fireball extends Projectile, Explosive {
  void setDirection(@NotNull Vector paramVector);
  
  @NotNull
  Vector getDirection();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Fireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */