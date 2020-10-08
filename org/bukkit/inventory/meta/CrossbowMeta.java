package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CrossbowMeta extends ItemMeta {
  boolean hasChargedProjectiles();
  
  @NotNull
  List<ItemStack> getChargedProjectiles();
  
  void setChargedProjectiles(@Nullable List<ItemStack> paramList);
  
  void addChargedProjectile(@NotNull ItemStack paramItemStack);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\CrossbowMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */