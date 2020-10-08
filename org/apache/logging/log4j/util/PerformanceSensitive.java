package org.apache.logging.log4j.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface PerformanceSensitive {
  String[] value() default {""};
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4\\util\PerformanceSensitive.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */