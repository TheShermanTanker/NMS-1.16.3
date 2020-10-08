package org.bukkit.entity.minecart;

import com.destroystokyo.paper.loottable.LootableEntityInventory;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.InventoryHolder;

public interface HopperMinecart extends Minecart, InventoryHolder, LootableEntityInventory {
  boolean isEnabled();
  
  void setEnabled(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\minecart\HopperMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */