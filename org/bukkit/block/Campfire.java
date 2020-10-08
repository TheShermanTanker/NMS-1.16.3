package org.bukkit.block;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface Campfire extends TileState {
  int getSize();
  
  @Nullable
  ItemStack getItem(int paramInt);
  
  void setItem(int paramInt, @Nullable ItemStack paramItemStack);
  
  int getCookTime(int paramInt);
  
  void setCookTime(int paramInt1, int paramInt2);
  
  int getCookTimeTotal(int paramInt);
  
  void setCookTimeTotal(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Campfire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */