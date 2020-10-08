package org.bukkit.inventory.meta;

import org.jetbrains.annotations.NotNull;

public interface Damageable {
  boolean hasDamage();
  
  int getDamage();
  
  void setDamage(int paramInt);
  
  @NotNull
  Damageable clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\Damageable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */