package org.bukkit.inventory.meta.tags;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public interface CustomItemTagContainer {
  <T, Z> void setCustomTag(@NotNull NamespacedKey paramNamespacedKey, @NotNull ItemTagType<T, Z> paramItemTagType, @NotNull Z paramZ);
  
  <T, Z> boolean hasCustomTag(@NotNull NamespacedKey paramNamespacedKey, @NotNull ItemTagType<T, Z> paramItemTagType);
  
  @Nullable
  <T, Z> Z getCustomTag(@NotNull NamespacedKey paramNamespacedKey, @NotNull ItemTagType<T, Z> paramItemTagType);
  
  void removeCustomTag(@NotNull NamespacedKey paramNamespacedKey);
  
  boolean isEmpty();
  
  @NotNull
  ItemTagAdapterContext getAdapterContext();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\tags\CustomItemTagContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */