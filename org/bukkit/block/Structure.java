package org.bukkit.block;

import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.block.structure.UsageMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BlockVector;
import org.jetbrains.annotations.NotNull;

public interface Structure extends TileState {
  @NotNull
  String getStructureName();
  
  void setStructureName(@NotNull String paramString);
  
  @NotNull
  String getAuthor();
  
  void setAuthor(@NotNull String paramString);
  
  void setAuthor(@NotNull LivingEntity paramLivingEntity);
  
  @NotNull
  BlockVector getRelativePosition();
  
  void setRelativePosition(@NotNull BlockVector paramBlockVector);
  
  @NotNull
  BlockVector getStructureSize();
  
  void setStructureSize(@NotNull BlockVector paramBlockVector);
  
  void setMirror(@NotNull Mirror paramMirror);
  
  @NotNull
  Mirror getMirror();
  
  void setRotation(@NotNull StructureRotation paramStructureRotation);
  
  @NotNull
  StructureRotation getRotation();
  
  void setUsageMode(@NotNull UsageMode paramUsageMode);
  
  @NotNull
  UsageMode getUsageMode();
  
  void setIgnoreEntities(boolean paramBoolean);
  
  boolean isIgnoreEntities();
  
  void setShowAir(boolean paramBoolean);
  
  boolean isShowAir();
  
  void setBoundingBoxVisible(boolean paramBoolean);
  
  boolean isBoundingBoxVisible();
  
  void setIntegrity(float paramFloat);
  
  float getIntegrity();
  
  void setSeed(long paramLong);
  
  long getSeed();
  
  void setMetadata(@NotNull String paramString);
  
  @NotNull
  String getMetadata();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Structure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */