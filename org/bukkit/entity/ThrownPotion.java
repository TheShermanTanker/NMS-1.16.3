package org.bukkit.entity;

import java.util.Collection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

public interface ThrownPotion extends Projectile {
  @NotNull
  Collection<PotionEffect> getEffects();
  
  @NotNull
  ItemStack getItem();
  
  void setItem(@NotNull ItemStack paramItemStack);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\ThrownPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */