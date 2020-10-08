package net.minecraft.server.v1_16_R2;

import java.io.Closeable;
import java.io.InputStream;

public interface IResource extends Closeable {
  InputStream b();
  
  String d();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IResource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */