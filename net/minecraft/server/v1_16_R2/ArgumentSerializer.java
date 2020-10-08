package net.minecraft.server.v1_16_R2;

import com.google.gson.JsonObject;

public interface ArgumentSerializer<T extends com.mojang.brigadier.arguments.ArgumentType<?>> {
  void a(T paramT, PacketDataSerializer paramPacketDataSerializer);
  
  T b(PacketDataSerializer paramPacketDataSerializer);
  
  void a(T paramT, JsonObject paramJsonObject);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */