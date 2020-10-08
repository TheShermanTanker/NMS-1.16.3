package com.destroystokyo.paper.brigadier;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

public interface BukkitBrigadierCommandSource {
  @Nullable
  Entity getBukkitEntity();
  
  @Nullable
  World getBukkitWorld();
  
  @Nullable
  Location getBukkitLocation();
  
  CommandSender getBukkitSender();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\brigadier\BukkitBrigadierCommandSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */