package org.bukkit.entity;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ZombieVillager extends Zombie {
  void setVillagerProfession(@Nullable Villager.Profession paramProfession);
  
  @Nullable
  Villager.Profession getVillagerProfession();
  
  @NotNull
  Villager.Type getVillagerType();
  
  void setVillagerType(@NotNull Villager.Type paramType);
  
  boolean isConverting();
  
  int getConversionTime();
  
  void setConversionTime(int paramInt);
  
  @Nullable
  OfflinePlayer getConversionPlayer();
  
  void setConversionPlayer(@Nullable OfflinePlayer paramOfflinePlayer);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\ZombieVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */