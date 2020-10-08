package org.bukkit;

import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface BlockChangeDelegate {
  boolean setBlockData(int paramInt1, int paramInt2, int paramInt3, @NotNull BlockData paramBlockData);
  
  @NotNull
  BlockData getBlockData(int paramInt1, int paramInt2, int paramInt3);
  
  int getHeight();
  
  boolean isEmpty(int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\BlockChangeDelegate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */