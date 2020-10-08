package org.bukkit.inventory.meta;

import org.bukkit.Color;
import org.bukkit.UndefinedNullability;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MapMeta extends ItemMeta {
  @Deprecated
  boolean hasMapId();
  
  @Deprecated
  int getMapId();
  
  @Deprecated
  void setMapId(int paramInt);
  
  boolean hasMapView();
  
  @Nullable
  MapView getMapView();
  
  void setMapView(@UndefinedNullability("implementation defined") MapView paramMapView);
  
  boolean isScaling();
  
  void setScaling(boolean paramBoolean);
  
  boolean hasLocationName();
  
  @Nullable
  String getLocationName();
  
  void setLocationName(@Nullable String paramString);
  
  boolean hasColor();
  
  @Nullable
  Color getColor();
  
  void setColor(@Nullable Color paramColor);
  
  @NotNull
  MapMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\MapMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */