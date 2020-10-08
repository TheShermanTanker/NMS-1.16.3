package org.bukkit.inventory.meta;

import java.util.Map;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

public interface EnchantmentStorageMeta extends ItemMeta {
  boolean hasStoredEnchants();
  
  boolean hasStoredEnchant(@NotNull Enchantment paramEnchantment);
  
  int getStoredEnchantLevel(@NotNull Enchantment paramEnchantment);
  
  @NotNull
  Map<Enchantment, Integer> getStoredEnchants();
  
  boolean addStoredEnchant(@NotNull Enchantment paramEnchantment, int paramInt, boolean paramBoolean);
  
  boolean removeStoredEnchant(@NotNull Enchantment paramEnchantment) throws IllegalArgumentException;
  
  boolean hasConflictingStoredEnchant(@NotNull Enchantment paramEnchantment);
  
  @NotNull
  EnchantmentStorageMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\EnchantmentStorageMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */