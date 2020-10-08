package net.minecraft.server.v1_16_R2;

import java.util.Random;

public interface IBlockFragilePlantElement {
  boolean a(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, IBlockData paramIBlockData, boolean paramBoolean);
  
  boolean a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition, IBlockData paramIBlockData);
  
  void a(WorldServer paramWorldServer, Random paramRandom, BlockPosition paramBlockPosition, IBlockData paramIBlockData);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IBlockFragilePlantElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */