package net.minecraft.server.v1_16_R2;

import javax.annotation.Nullable;

public interface ILightAccess {
  @Nullable
  IBlockAccess c(int paramInt1, int paramInt2);
  
  default void a(EnumSkyBlock var0, SectionPosition var1) {}
  
  IBlockAccess getWorld();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ILightAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */