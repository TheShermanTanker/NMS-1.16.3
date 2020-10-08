package org.bukkit.entity;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public interface Zombie extends Monster, Ageable {
  @Deprecated
  boolean isBaby();
  
  @Deprecated
  void setBaby(boolean paramBoolean);
  
  @Deprecated
  boolean isVillager();
  
  @Deprecated
  @Contract("_ -> fail")
  void setVillager(boolean paramBoolean);
  
  @Deprecated
  @Contract("_ -> fail")
  void setVillagerProfession(Villager.Profession paramProfession);
  
  @Deprecated
  @Nullable
  @Contract("-> null")
  Villager.Profession getVillagerProfession();
  
  boolean isConverting();
  
  int getConversionTime();
  
  void setConversionTime(int paramInt);
  
  boolean isDrowning();
  
  @Deprecated
  void startDrowning(int paramInt);
  
  void stopDrowning();
  
  void setArmsRaised(boolean paramBoolean);
  
  boolean isArmsRaised();
  
  boolean shouldBurnInDay();
  
  void setShouldBurnInDay(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Zombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */