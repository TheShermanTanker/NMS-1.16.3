package net.minecraft.server.v1_16_R2;

import javax.annotation.Nullable;

@FunctionalInterface
public interface ITileEntityContainer {
  @Nullable
  Container createMenu(int paramInt, PlayerInventory paramPlayerInventory, EntityHuman paramEntityHuman);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ITileEntityContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */