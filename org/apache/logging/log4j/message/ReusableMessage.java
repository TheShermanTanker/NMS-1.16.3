package org.apache.logging.log4j.message;

import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.StringBuilderFormattable;

@PerformanceSensitive({"allocation"})
public interface ReusableMessage extends Message, StringBuilderFormattable {
  Object[] swapParameters(Object[] paramArrayOfObject);
  
  short getParameterCount();
  
  Message memento();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\message\ReusableMessage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */