package net.minecraft.server.v1_16_R2;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public interface LootSerializer<T> {
  void a(JsonObject paramJsonObject, T paramT, JsonSerializationContext paramJsonSerializationContext);
  
  T a(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */