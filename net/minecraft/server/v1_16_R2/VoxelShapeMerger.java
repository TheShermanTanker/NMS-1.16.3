package net.minecraft.server.v1_16_R2;

import it.unimi.dsi.fastutil.doubles.DoubleList;

interface VoxelShapeMerger {
  DoubleList a();
  
  boolean a(a parama);
  
  public static interface a {
    boolean merge(int param1Int1, int param1Int2, int param1Int3);
  }
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeMerger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */