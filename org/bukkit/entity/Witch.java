package org.bukkit.entity;

import com.destroystokyo.paper.entity.RangedEntity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface Witch extends Raider, RangedEntity {
  boolean isDrinkingPotion();
  
  int getPotionUseTimeLeft();
  
  @Nullable
  ItemStack getDrinkingPotion();
  
  void setDrinkingPotion(@Nullable ItemStack paramItemStack);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Witch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */