package org.jline.terminal.spi;

import java.io.IOException;
import java.nio.charset.Charset;
import org.jline.terminal.Attributes;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;

public interface JnaSupport {
  Pty current() throws IOException;
  
  Pty open(Attributes paramAttributes, Size paramSize) throws IOException;
  
  Terminal winSysTerminal(String paramString1, String paramString2, boolean paramBoolean1, Charset paramCharset, int paramInt, boolean paramBoolean2, Terminal.SignalHandler paramSignalHandler) throws IOException;
  
  Terminal winSysTerminal(String paramString1, String paramString2, boolean paramBoolean1, Charset paramCharset, int paramInt, boolean paramBoolean2, Terminal.SignalHandler paramSignalHandler, boolean paramBoolean3) throws IOException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\spi\JnaSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */