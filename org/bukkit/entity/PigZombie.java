package org.bukkit.entity;

public interface PigZombie extends Zombie {
  int getAnger();
  
  void setAnger(int paramInt);
  
  void setAngry(boolean paramBoolean);
  
  boolean isAngry();
  
  boolean isConverting();
  
  int getConversionTime();
  
  void setConversionTime(int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\PigZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */