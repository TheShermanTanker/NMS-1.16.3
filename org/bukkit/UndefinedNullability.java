package org.bukkit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Deprecated
public @interface UndefinedNullability {
  String value() default "";
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\UndefinedNullability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */