package org.bukkit.entity;

import org.bukkit.block.Block;
import org.jetbrains.annotations.Nullable;

public interface Raider extends Monster {
  @Nullable
  Block getPatrolTarget();
  
  void setPatrolTarget(@Nullable Block paramBlock);
  
  boolean isPatrolLeader();
  
  void setPatrolLeader(boolean paramBoolean);
  
  boolean isCanJoinRaid();
  
  void setCanJoinRaid(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Raider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */