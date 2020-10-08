package org.bukkit.entity;

public interface Creeper extends Monster {
  boolean isPowered();
  
  void setPowered(boolean paramBoolean);
  
  void setMaxFuseTicks(int paramInt);
  
  int getMaxFuseTicks();
  
  void setExplosionRadius(int paramInt);
  
  int getExplosionRadius();
  
  void explode();
  
  void ignite();
  
  void setIgnited(boolean paramBoolean);
  
  boolean isIgnited();
  
  int getFuseTicks();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Creeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */