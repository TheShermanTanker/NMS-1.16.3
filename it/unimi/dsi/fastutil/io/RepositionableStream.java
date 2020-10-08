package it.unimi.dsi.fastutil.io;

import java.io.IOException;

public interface RepositionableStream {
  void position(long paramLong) throws IOException;
  
  long position() throws IOException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\i\\unimi\dsi\fastutil\io\RepositionableStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */