package net.minecraft.server.v1_16_R2;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

public interface CommandDataAccessor {
  void a(NBTTagCompound paramNBTTagCompound) throws CommandSyntaxException;
  
  NBTTagCompound a() throws CommandSyntaxException;
  
  IChatBaseComponent b();
  
  IChatBaseComponent a(NBTBase paramNBTBase);
  
  IChatBaseComponent a(ArgumentNBTKey.h paramh, double paramDouble, int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandDataAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */