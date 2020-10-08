package net.minecraft.server.v1_16_R2;

public interface IFluidContainer {
  boolean canPlace(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, IBlockData paramIBlockData, FluidType paramFluidType);
  
  boolean place(GeneratorAccess paramGeneratorAccess, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Fluid paramFluid);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IFluidContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */