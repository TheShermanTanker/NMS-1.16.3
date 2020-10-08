package org.bukkit.persistence;

import java.util.Set;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PersistentDataContainer {
  <T, Z> void set(@NotNull NamespacedKey paramNamespacedKey, @NotNull PersistentDataType<T, Z> paramPersistentDataType, @NotNull Z paramZ);
  
  <T, Z> boolean has(@NotNull NamespacedKey paramNamespacedKey, @NotNull PersistentDataType<T, Z> paramPersistentDataType);
  
  @Nullable
  <T, Z> Z get(@NotNull NamespacedKey paramNamespacedKey, @NotNull PersistentDataType<T, Z> paramPersistentDataType);
  
  @NotNull
  <T, Z> Z getOrDefault(@NotNull NamespacedKey paramNamespacedKey, @NotNull PersistentDataType<T, Z> paramPersistentDataType, @NotNull Z paramZ);
  
  @NotNull
  Set<NamespacedKey> getKeys();
  
  void remove(@NotNull NamespacedKey paramNamespacedKey);
  
  boolean isEmpty();
  
  @NotNull
  PersistentDataAdapterContext getAdapterContext();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\persistence\PersistentDataContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */