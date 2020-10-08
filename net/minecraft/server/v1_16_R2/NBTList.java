package net.minecraft.server.v1_16_R2;

import java.util.AbstractList;

public abstract class NBTList<T extends NBTBase> extends AbstractList<T> implements NBTBase {
  public abstract T set(int paramInt, T paramT);
  
  public abstract void add(int paramInt, T paramT);
  
  public abstract T remove(int paramInt);
  
  public abstract boolean a(int paramInt, NBTBase paramNBTBase);
  
  public abstract boolean b(int paramInt, NBTBase paramNBTBase);
  
  public abstract byte d_();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */