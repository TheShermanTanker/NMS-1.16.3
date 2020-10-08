package org.bukkit.command;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface BlockCommandSender extends CommandSender {
  @NotNull
  Block getBlock();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\BlockCommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */