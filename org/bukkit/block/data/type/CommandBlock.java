package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

public interface CommandBlock extends Directional {
  boolean isConditional();
  
  void setConditional(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\CommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */