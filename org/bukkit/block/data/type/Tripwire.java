package org.bukkit.block.data.type;

import org.bukkit.block.data.Attachable;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.block.data.Powerable;

public interface Tripwire extends Attachable, MultipleFacing, Powerable {
  boolean isDisarmed();
  
  void setDisarmed(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Tripwire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */