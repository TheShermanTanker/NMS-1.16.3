package net.minecraft.server.v1_16_R2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public interface IResourcePack extends AutoCloseable {
  InputStream a(EnumResourcePackType paramEnumResourcePackType, MinecraftKey paramMinecraftKey) throws IOException;
  
  Collection<MinecraftKey> a(EnumResourcePackType paramEnumResourcePackType, String paramString1, String paramString2, int paramInt, Predicate<String> paramPredicate);
  
  boolean b(EnumResourcePackType paramEnumResourcePackType, MinecraftKey paramMinecraftKey);
  
  Set<String> a(EnumResourcePackType paramEnumResourcePackType);
  
  @Nullable
  <T> T a(ResourcePackMetaParser<T> paramResourcePackMetaParser) throws IOException;
  
  String a();
  
  void close();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IResourcePack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */