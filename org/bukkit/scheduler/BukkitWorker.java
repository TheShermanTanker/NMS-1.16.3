package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface BukkitWorker {
  int getTaskId();
  
  @NotNull
  Plugin getOwner();
  
  @NotNull
  Thread getThread();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\scheduler\BukkitWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */