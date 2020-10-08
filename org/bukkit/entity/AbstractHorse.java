package org.bukkit.entity;

import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface AbstractHorse extends Vehicle, InventoryHolder, Tameable {
  @Deprecated
  @NotNull
  Horse.Variant getVariant();
  
  @Deprecated
  @Contract("_ -> fail")
  void setVariant(Horse.Variant paramVariant);
  
  int getDomestication();
  
  void setDomestication(int paramInt);
  
  int getMaxDomestication();
  
  void setMaxDomestication(int paramInt);
  
  double getJumpStrength();
  
  void setJumpStrength(double paramDouble);
  
  @NotNull
  AbstractHorseInventory getInventory();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\AbstractHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */