package org.bukkit.inventory.meta;

import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LeatherArmorMeta extends ItemMeta {
  @NotNull
  Color getColor();
  
  void setColor(@Nullable Color paramColor);
  
  @NotNull
  LeatherArmorMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\LeatherArmorMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */