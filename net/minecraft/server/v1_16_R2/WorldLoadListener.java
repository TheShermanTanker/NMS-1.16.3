package net.minecraft.server.v1_16_R2;

import javax.annotation.Nullable;

public interface WorldLoadListener {
  void a(ChunkCoordIntPair paramChunkCoordIntPair);
  
  void a(ChunkCoordIntPair paramChunkCoordIntPair, @Nullable ChunkStatus paramChunkStatus);
  
  void b();
  
  void setChunkRadius(int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldLoadListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */