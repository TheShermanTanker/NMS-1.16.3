package org.bukkit.entity;

import org.jetbrains.annotations.Nullable;

public interface Vex extends Monster {
  boolean isCharging();
  
  void setCharging(boolean paramBoolean);
  
  @Nullable
  Mob getSummoner();
  
  void setSummoner(@Nullable Mob paramMob);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Vex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */