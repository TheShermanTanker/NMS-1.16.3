package com.google.gson;

import java.lang.reflect.Type;

public interface JsonSerializer<T> {
  JsonElement serialize(T paramT, Type paramType, JsonSerializationContext paramJsonSerializationContext);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\google\gson\JsonSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */