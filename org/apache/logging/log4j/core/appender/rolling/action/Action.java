package org.apache.logging.log4j.core.appender.rolling.action;

import java.io.IOException;

public interface Action extends Runnable {
  boolean execute() throws IOException;
  
  void close();
  
  boolean isComplete();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\core\appender\rolling\action\Action.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */