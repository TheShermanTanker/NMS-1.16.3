package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MetadataValue {
  @Nullable
  Object value();
  
  int asInt();
  
  float asFloat();
  
  double asDouble();
  
  long asLong();
  
  short asShort();
  
  byte asByte();
  
  boolean asBoolean();
  
  @NotNull
  String asString();
  
  @Nullable
  Plugin getOwningPlugin();
  
  void invalidate();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\metadata\MetadataValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */