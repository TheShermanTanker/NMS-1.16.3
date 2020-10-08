package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.Waterlogged;

public interface Campfire extends Directional, Lightable, Waterlogged {
  boolean isSignalFire();
  
  void setSignalFire(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Campfire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */