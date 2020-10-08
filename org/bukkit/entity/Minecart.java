package org.bukkit.entity;

import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Minecart extends Vehicle {
  void setDamage(double paramDouble);
  
  double getDamage();
  
  double getMaxSpeed();
  
  void setMaxSpeed(double paramDouble);
  
  boolean isSlowWhenEmpty();
  
  void setSlowWhenEmpty(boolean paramBoolean);
  
  @NotNull
  Vector getFlyingVelocityMod();
  
  void setFlyingVelocityMod(@NotNull Vector paramVector);
  
  @NotNull
  Vector getDerailedVelocityMod();
  
  void setDerailedVelocityMod(@NotNull Vector paramVector);
  
  void setDisplayBlock(@Nullable MaterialData paramMaterialData);
  
  @NotNull
  MaterialData getDisplayBlock();
  
  void setDisplayBlockData(@Nullable BlockData paramBlockData);
  
  @NotNull
  BlockData getDisplayBlockData();
  
  void setDisplayBlockOffset(int paramInt);
  
  int getDisplayBlockOffset();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Minecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */