package org.bukkit.configuration;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Configuration extends ConfigurationSection {
  void addDefault(@NotNull String paramString, @Nullable Object paramObject);
  
  void addDefaults(@NotNull Map<String, Object> paramMap);
  
  void addDefaults(@NotNull Configuration paramConfiguration);
  
  void setDefaults(@NotNull Configuration paramConfiguration);
  
  @Nullable
  Configuration getDefaults();
  
  @NotNull
  ConfigurationOptions options();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\Configuration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */