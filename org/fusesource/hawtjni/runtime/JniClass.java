package org.fusesource.hawtjni.runtime;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JniClass {
  ClassFlag[] flags() default {};
  
  String conditional() default "";
  
  String name() default "";
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\hawtjni\runtime\JniClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */