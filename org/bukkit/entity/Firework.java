package org.bukkit.entity;

import java.util.UUID;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Firework extends Projectile {
  @NotNull
  FireworkMeta getFireworkMeta();
  
  void setFireworkMeta(@NotNull FireworkMeta paramFireworkMeta);
  
  void detonate();
  
  boolean isShotAtAngle();
  
  void setShotAtAngle(boolean paramBoolean);
  
  @Nullable
  UUID getSpawningEntity();
  
  @Nullable
  LivingEntity getBoostedEntity();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Firework.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */