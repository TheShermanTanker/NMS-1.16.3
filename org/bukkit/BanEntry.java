package org.bukkit;

import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BanEntry {
  @NotNull
  String getTarget();
  
  @NotNull
  Date getCreated();
  
  void setCreated(@NotNull Date paramDate);
  
  @NotNull
  String getSource();
  
  void setSource(@NotNull String paramString);
  
  @Nullable
  Date getExpiration();
  
  void setExpiration(@Nullable Date paramDate);
  
  @Nullable
  String getReason();
  
  void setReason(@Nullable String paramString);
  
  void save();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\BanEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */