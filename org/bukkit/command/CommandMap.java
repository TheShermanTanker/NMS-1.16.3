package org.bukkit.command;

import java.util.List;
import java.util.Map;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandMap {
  void registerAll(@NotNull String paramString, @NotNull List<Command> paramList);
  
  boolean register(@NotNull String paramString1, @NotNull String paramString2, @NotNull Command paramCommand);
  
  boolean register(@NotNull String paramString, @NotNull Command paramCommand);
  
  boolean dispatch(@NotNull CommandSender paramCommandSender, @NotNull String paramString) throws CommandException;
  
  void clearCommands();
  
  @Nullable
  Command getCommand(@NotNull String paramString);
  
  @Nullable
  List<String> tabComplete(@NotNull CommandSender paramCommandSender, @NotNull String paramString) throws IllegalArgumentException;
  
  @Nullable
  List<String> tabComplete(@NotNull CommandSender paramCommandSender, @NotNull String paramString, @Nullable Location paramLocation) throws IllegalArgumentException;
  
  @NotNull
  Map<String, Command> getKnownCommands();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\CommandMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */