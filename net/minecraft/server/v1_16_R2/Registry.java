package net.minecraft.server.v1_16_R2;

import javax.annotation.Nullable;

public interface Registry<T> extends Iterable<T> {
  int a(T paramT);
  
  @Nullable
  T fromId(int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Registry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */