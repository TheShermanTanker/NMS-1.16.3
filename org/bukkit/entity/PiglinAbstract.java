package org.bukkit.entity;

public interface PiglinAbstract extends Monster, Ageable {
  boolean isImmuneToZombification();
  
  void setImmuneToZombification(boolean paramBoolean);
  
  int getConversionTime();
  
  void setConversionTime(int paramInt);
  
  boolean isConverting();
  
  @Deprecated
  boolean isBaby();
  
  @Deprecated
  void setBaby(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\PiglinAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */