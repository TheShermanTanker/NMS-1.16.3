package org.bukkit.block.data.type;

import org.bukkit.block.data.AnaloguePowerable;

public interface DaylightDetector extends AnaloguePowerable {
  boolean isInverted();
  
  void setInverted(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\DaylightDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */