package net.minecraft.server.v1_16_R2;

public interface ISourceBlock extends IPosition {
  double getX();
  
  double getY();
  
  double getZ();
  
  BlockPosition getBlockPosition();
  
  IBlockData getBlockData();
  
  <T extends TileEntity> T getTileEntity();
  
  WorldServer getWorld();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ISourceBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */