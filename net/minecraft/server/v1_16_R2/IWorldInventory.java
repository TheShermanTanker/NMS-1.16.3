package net.minecraft.server.v1_16_R2;

import javax.annotation.Nullable;

public interface IWorldInventory extends IInventory {
  int[] getSlotsForFace(EnumDirection paramEnumDirection);
  
  boolean canPlaceItemThroughFace(int paramInt, ItemStack paramItemStack, @Nullable EnumDirection paramEnumDirection);
  
  boolean canTakeItemThroughFace(int paramInt, ItemStack paramItemStack, EnumDirection paramEnumDirection);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IWorldInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */