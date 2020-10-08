package org.bukkit.block;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Skull extends TileState {
  boolean hasOwner();
  
  @Deprecated
  @Nullable
  String getOwner();
  
  @Deprecated
  @Contract("null -> false")
  boolean setOwner(@Nullable String paramString);
  
  @Nullable
  OfflinePlayer getOwningPlayer();
  
  void setOwningPlayer(@NotNull OfflinePlayer paramOfflinePlayer);
  
  void setPlayerProfile(@NotNull PlayerProfile paramPlayerProfile);
  
  @Nullable
  PlayerProfile getPlayerProfile();
  
  @Deprecated
  @NotNull
  BlockFace getRotation();
  
  @Deprecated
  void setRotation(@NotNull BlockFace paramBlockFace);
  
  @Deprecated
  @NotNull
  SkullType getSkullType();
  
  @Deprecated
  @Contract("_ -> fail")
  void setSkullType(SkullType paramSkullType);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Skull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */