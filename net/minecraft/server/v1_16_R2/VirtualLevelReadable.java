package net.minecraft.server.v1_16_R2;

import java.util.function.Predicate;

public interface VirtualLevelReadable {
  boolean a(BlockPosition paramBlockPosition, Predicate<IBlockData> paramPredicate);
  
  BlockPosition getHighestBlockYAt(HeightMap.Type paramType, BlockPosition paramBlockPosition);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VirtualLevelReadable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */