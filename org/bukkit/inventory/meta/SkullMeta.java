package org.bukkit.inventory.meta;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SkullMeta extends ItemMeta {
  @Deprecated
  @Nullable
  String getOwner();
  
  boolean hasOwner();
  
  @Deprecated
  boolean setOwner(@Nullable String paramString);
  
  void setPlayerProfile(@Nullable PlayerProfile paramPlayerProfile);
  
  @Nullable
  PlayerProfile getPlayerProfile();
  
  @Nullable
  OfflinePlayer getOwningPlayer();
  
  boolean setOwningPlayer(@Nullable OfflinePlayer paramOfflinePlayer);
  
  @NotNull
  SkullMeta clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\SkullMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */