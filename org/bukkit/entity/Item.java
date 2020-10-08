package org.bukkit.entity;

import java.util.UUID;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Item extends Entity {
  @NotNull
  ItemStack getItemStack();
  
  void setItemStack(@NotNull ItemStack paramItemStack);
  
  int getPickupDelay();
  
  void setPickupDelay(int paramInt);
  
  boolean canMobPickup();
  
  void setCanMobPickup(boolean paramBoolean);
  
  @Nullable
  UUID getOwner();
  
  void setOwner(@Nullable UUID paramUUID);
  
  @Nullable
  UUID getThrower();
  
  void setThrower(@Nullable UUID paramUUID);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */