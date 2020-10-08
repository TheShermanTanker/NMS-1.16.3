package org.apache.logging.log4j.core.appender.rewrite;

import org.apache.logging.log4j.core.LogEvent;

public interface RewritePolicy {
  LogEvent rewrite(LogEvent paramLogEvent);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\core\appender\rewrite\RewritePolicy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */