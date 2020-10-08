package org.bukkit.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
  EventPriority priority() default EventPriority.NORMAL;
  
  boolean ignoreCancelled() default false;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\EventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */