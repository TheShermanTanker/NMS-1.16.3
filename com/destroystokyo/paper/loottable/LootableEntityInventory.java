package com.destroystokyo.paper.loottable;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface LootableEntityInventory extends LootableInventory {
  @NotNull
  Entity getEntity();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\loottable\LootableEntityInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */