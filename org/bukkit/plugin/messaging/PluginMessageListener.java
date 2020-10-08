package org.bukkit.plugin.messaging;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface PluginMessageListener {
  void onPluginMessageReceived(@NotNull String paramString, @NotNull Player paramPlayer, @NotNull byte[] paramArrayOfbyte);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\messaging\PluginMessageListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */