package org.jline.reader;

public interface Buffer {
  int cursor();
  
  int atChar(int paramInt);
  
  int length();
  
  int currChar();
  
  int prevChar();
  
  int nextChar();
  
  boolean cursor(int paramInt);
  
  int move(int paramInt);
  
  boolean up();
  
  boolean down();
  
  boolean moveXY(int paramInt1, int paramInt2);
  
  boolean clear();
  
  boolean currChar(int paramInt);
  
  void write(int paramInt);
  
  void write(int paramInt, boolean paramBoolean);
  
  void write(CharSequence paramCharSequence);
  
  void write(CharSequence paramCharSequence, boolean paramBoolean);
  
  boolean backspace();
  
  int backspace(int paramInt);
  
  boolean delete();
  
  int delete(int paramInt);
  
  String substring(int paramInt);
  
  String substring(int paramInt1, int paramInt2);
  
  String upToCursor();
  
  String toString();
  
  Buffer copy();
  
  void copyFrom(Buffer paramBuffer);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\Buffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */