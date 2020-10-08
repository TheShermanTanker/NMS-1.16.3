package io.netty.handler.codec.serialization;

public interface ClassResolver {
  Class<?> resolve(String paramString) throws ClassNotFoundException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\serialization\ClassResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */