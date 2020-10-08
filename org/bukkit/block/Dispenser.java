package org.bukkit.block;

import com.destroystokyo.paper.loottable.LootableBlockInventory;
import org.bukkit.Nameable;
import org.bukkit.projectiles.BlockProjectileSource;
import org.jetbrains.annotations.Nullable;

public interface Dispenser extends Container, Nameable, LootableBlockInventory {
  @Nullable
  BlockProjectileSource getBlockProjectileSource();
  
  boolean dispense();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Dispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */