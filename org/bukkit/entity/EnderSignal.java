package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EnderSignal extends Entity {
  @NotNull
  Location getTargetLocation();
  
  void setTargetLocation(@NotNull Location paramLocation);
  
  boolean getDropItem();
  
  void setDropItem(boolean paramBoolean);
  
  @NotNull
  ItemStack getItem();
  
  void setItem(@Nullable ItemStack paramItemStack);
  
  int getDespawnTimer();
  
  void setDespawnTimer(int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\EnderSignal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */