package org.bukkit.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Lockable {
  boolean isLocked();
  
  @NotNull
  String getLock();
  
  void setLock(@Nullable String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Lockable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */