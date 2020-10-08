package net.minecraft.server.v1_16_R2;

import javax.annotation.Nullable;

public interface ISaddleable {
  boolean canSaddle();
  
  void saddle(@Nullable SoundCategory paramSoundCategory);
  
  boolean hasSaddle();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ISaddleable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */