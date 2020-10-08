package org.bukkit.block;

import com.destroystokyo.paper.loottable.LootableBlockInventory;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public interface Chest extends Container, LootableBlockInventory, Lidded {
  @NotNull
  Inventory getBlockInventory();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Chest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */