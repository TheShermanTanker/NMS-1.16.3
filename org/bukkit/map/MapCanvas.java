package org.bukkit.map;

import java.awt.Image;
import org.jetbrains.annotations.NotNull;

public interface MapCanvas {
  @NotNull
  MapView getMapView();
  
  @NotNull
  MapCursorCollection getCursors();
  
  void setCursors(@NotNull MapCursorCollection paramMapCursorCollection);
  
  void setPixel(int paramInt1, int paramInt2, byte paramByte);
  
  byte getPixel(int paramInt1, int paramInt2);
  
  byte getBasePixel(int paramInt1, int paramInt2);
  
  void drawImage(int paramInt1, int paramInt2, @NotNull Image paramImage);
  
  void drawText(int paramInt1, int paramInt2, @NotNull MapFont paramMapFont, @NotNull String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\map\MapCanvas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */