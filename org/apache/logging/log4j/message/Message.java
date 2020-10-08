package org.apache.logging.log4j.message;

import java.io.Serializable;

public interface Message extends Serializable {
  String getFormattedMessage();
  
  String getFormat();
  
  Object[] getParameters();
  
  Throwable getThrowable();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\message\Message.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */