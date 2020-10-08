package org.bukkit.entity;

public interface Hoglin extends Animals {
  boolean isImmuneToZombification();
  
  void setImmuneToZombification(boolean paramBoolean);
  
  boolean isAbleToBeHunted();
  
  void setIsAbleToBeHunted(boolean paramBoolean);
  
  int getConversionTime();
  
  void setConversionTime(int paramInt);
  
  boolean isConverting();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Hoglin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */