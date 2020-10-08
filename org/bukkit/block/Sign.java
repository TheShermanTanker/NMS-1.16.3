package org.bukkit.block;

import org.bukkit.material.Colorable;
import org.jetbrains.annotations.NotNull;

public interface Sign extends TileState, Colorable {
  @NotNull
  String[] getLines();
  
  @NotNull
  String getLine(int paramInt) throws IndexOutOfBoundsException;
  
  void setLine(int paramInt, @NotNull String paramString) throws IndexOutOfBoundsException;
  
  boolean isEditable();
  
  void setEditable(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Sign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */