package org.bukkit.entity;

public interface Breedable extends Ageable {
  void setAgeLock(boolean paramBoolean);
  
  boolean getAgeLock();
  
  boolean canBreed();
  
  void setBreed(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Breedable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */