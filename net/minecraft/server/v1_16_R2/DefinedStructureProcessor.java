package net.minecraft.server.v1_16_R2;

import javax.annotation.Nullable;

public abstract class DefinedStructureProcessor {
  @Nullable
  public abstract DefinedStructure.BlockInfo a(IWorldReader paramIWorldReader, BlockPosition paramBlockPosition1, BlockPosition paramBlockPosition2, DefinedStructure.BlockInfo paramBlockInfo1, DefinedStructure.BlockInfo paramBlockInfo2, DefinedStructureInfo paramDefinedStructureInfo);
  
  protected abstract DefinedStructureStructureProcessorType<?> a();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */