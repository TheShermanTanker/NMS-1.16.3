package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface BukkitTask {
  int getTaskId();
  
  @NotNull
  Plugin getOwner();
  
  boolean isSync();
  
  boolean isCancelled();
  
  void cancel();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\scheduler\BukkitTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */