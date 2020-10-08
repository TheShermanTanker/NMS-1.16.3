package com.destroystokyo.paper.inventory.meta;

import org.bukkit.inventory.meta.ItemMeta;

public interface ArmorStandMeta extends ItemMeta {
  boolean isInvisible();
  
  boolean hasNoBasePlate();
  
  boolean shouldShowArms();
  
  boolean isSmall();
  
  boolean isMarker();
  
  void setInvisible(boolean paramBoolean);
  
  void setNoBasePlate(boolean paramBoolean);
  
  void setShowArms(boolean paramBoolean);
  
  void setSmall(boolean paramBoolean);
  
  void setMarker(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\inventory\meta\ArmorStandMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */