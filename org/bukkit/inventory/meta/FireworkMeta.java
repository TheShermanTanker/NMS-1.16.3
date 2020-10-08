package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.FireworkEffect;
import org.jetbrains.annotations.NotNull;

public interface FireworkMeta extends ItemMeta {
  void addEffect(@NotNull FireworkEffect paramFireworkEffect) throws IllegalArgumentException;
  
  void addEffects(@NotNull FireworkEffect... paramVarArgs) throws IllegalArgumentException;
  
  void addEffects(@NotNull Iterable<FireworkEffect> paramIterable) throws IllegalArgumentException;
  
  @NotNull
  List<FireworkEffect> getEffects();
  
  int getEffectsSize();
  
  void removeEffect(int paramInt) throws IndexOutOfBoundsException;
  
  void clearEffects();
  
  boolean hasEffects();
  
  int getPower();
  
  void setPower(int paramInt) throws IllegalArgumentException;
  
  @NotNull
  FireworkMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\FireworkMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */