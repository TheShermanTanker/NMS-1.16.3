package org.bukkit.loot;

import java.util.Collection;
import java.util.Random;
import org.bukkit.Keyed;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface LootTable extends Keyed {
  @NotNull
  Collection<ItemStack> populateLoot(@NotNull Random paramRandom, @NotNull LootContext paramLootContext);
  
  void fillInventory(@NotNull Inventory paramInventory, @NotNull Random paramRandom, @NotNull LootContext paramLootContext);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\loot\LootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */