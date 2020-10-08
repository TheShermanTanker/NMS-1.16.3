package org.bukkit.entity;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.Merchant;
import org.jetbrains.annotations.NotNull;

public interface AbstractVillager extends Breedable, NPC, InventoryHolder, Merchant {
  @NotNull
  Inventory getInventory();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\AbstractVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */