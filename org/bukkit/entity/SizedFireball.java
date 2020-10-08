package org.bukkit.entity;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface SizedFireball extends Fireball {
  @NotNull
  ItemStack getDisplayItem();
  
  void setDisplayItem(@NotNull ItemStack paramItemStack);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\SizedFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */