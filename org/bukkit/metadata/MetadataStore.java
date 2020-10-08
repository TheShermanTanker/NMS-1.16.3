package org.bukkit.metadata;

import java.util.List;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface MetadataStore<T> {
  void setMetadata(@NotNull T paramT, @NotNull String paramString, @NotNull MetadataValue paramMetadataValue);
  
  @NotNull
  List<MetadataValue> getMetadata(@NotNull T paramT, @NotNull String paramString);
  
  boolean hasMetadata(@NotNull T paramT, @NotNull String paramString);
  
  void removeMetadata(@NotNull T paramT, @NotNull String paramString, @NotNull Plugin paramPlugin);
  
  void invalidateAll(@NotNull Plugin paramPlugin);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\metadata\MetadataStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */