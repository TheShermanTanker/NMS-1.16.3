package net.minecraft.server.v1_16_R2;

public interface ICrafting {
  void a(Container paramContainer, NonNullList<ItemStack> paramNonNullList);
  
  void a(Container paramContainer, int paramInt, ItemStack paramItemStack);
  
  void setContainerData(Container paramContainer, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ICrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */