package org.bukkit.entity;

public interface Ageable extends Creature {
  int getAge();
  
  void setAge(int paramInt);
  
  @Deprecated
  void setAgeLock(boolean paramBoolean);
  
  @Deprecated
  boolean getAgeLock();
  
  void setBaby();
  
  void setAdult();
  
  boolean isAdult();
  
  @Deprecated
  boolean canBreed();
  
  @Deprecated
  void setBreed(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Ageable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */