package org.bukkit.entity.minecart;

import org.bukkit.entity.Minecart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandMinecart extends Minecart {
  @NotNull
  String getCommand();
  
  void setCommand(@Nullable String paramString);
  
  void setName(@Nullable String paramString);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\minecart\CommandMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */