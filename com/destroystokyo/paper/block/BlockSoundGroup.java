package com.destroystokyo.paper.block;

import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

public interface BlockSoundGroup {
  @NotNull
  Sound getBreakSound();
  
  @NotNull
  Sound getStepSound();
  
  @NotNull
  Sound getPlaceSound();
  
  @NotNull
  Sound getHitSound();
  
  @NotNull
  Sound getFallSound();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\block\BlockSoundGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */