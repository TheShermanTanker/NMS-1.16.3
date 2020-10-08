package org.apache.logging.log4j.core.async;

import org.apache.logging.log4j.Level;

public interface AsyncQueueFullPolicy {
  EventRoute getRoute(long paramLong, Level paramLevel);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\core\async\AsyncQueueFullPolicy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */