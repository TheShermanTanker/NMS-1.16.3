package org.bukkit.inventory.meta;

import org.bukkit.FireworkEffect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FireworkEffectMeta extends ItemMeta {
  void setEffect(@Nullable FireworkEffect paramFireworkEffect);
  
  boolean hasEffect();
  
  @Nullable
  FireworkEffect getEffect();
  
  @NotNull
  FireworkEffectMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\FireworkEffectMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */