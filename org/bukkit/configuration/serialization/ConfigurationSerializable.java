package org.bukkit.configuration.serialization;

import java.util.Map;
import org.jetbrains.annotations.NotNull;

public interface ConfigurationSerializable {
  @NotNull
  Map<String, Object> serialize();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\serialization\ConfigurationSerializable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */