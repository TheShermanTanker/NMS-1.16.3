package org.bukkit.plugin.messaging;

import java.util.Set;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface PluginMessageRecipient {
  void sendPluginMessage(@NotNull Plugin paramPlugin, @NotNull String paramString, @NotNull byte[] paramArrayOfbyte);
  
  @NotNull
  Set<String> getListeningPluginChannels();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\messaging\PluginMessageRecipient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */