package org.jline.reader;

public interface CompletingParsedLine extends ParsedLine {
  CharSequence escape(CharSequence paramCharSequence, boolean paramBoolean);
  
  int rawWordCursor();
  
  int rawWordLength();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\CompletingParsedLine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */