package it.unimi.dsi.fastutil.bytes;

public interface ByteHash {
  public static interface Strategy {
    int hashCode(byte param1Byte);
    
    boolean equals(byte param1Byte1, byte param1Byte2);
  }
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\i\\unimi\dsi\fastutil\bytes\ByteHash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */