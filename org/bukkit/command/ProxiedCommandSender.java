package org.bukkit.command;

import org.jetbrains.annotations.NotNull;

public interface ProxiedCommandSender extends CommandSender {
  @NotNull
  CommandSender getCaller();
  
  @NotNull
  CommandSender getCallee();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\ProxiedCommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */