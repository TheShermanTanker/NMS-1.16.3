package org.bukkit.block;

import com.destroystokyo.paper.loottable.LootableBlockInventory;
import org.bukkit.DyeColor;
import org.jetbrains.annotations.NotNull;

public interface ShulkerBox extends Container, LootableBlockInventory, Lidded {
  @NotNull
  DyeColor getColor();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\ShulkerBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */