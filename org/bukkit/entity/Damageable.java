package org.bukkit.entity;

import org.jetbrains.annotations.Nullable;

public interface Damageable extends Entity {
  void damage(double paramDouble);
  
  void damage(double paramDouble, @Nullable Entity paramEntity);
  
  double getHealth();
  
  void setHealth(double paramDouble);
  
  double getAbsorptionAmount();
  
  void setAbsorptionAmount(double paramDouble);
  
  @Deprecated
  double getMaxHealth();
  
  @Deprecated
  void setMaxHealth(double paramDouble);
  
  @Deprecated
  void resetMaxHealth();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Damageable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */