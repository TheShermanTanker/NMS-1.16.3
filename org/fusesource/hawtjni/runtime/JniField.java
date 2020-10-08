package org.fusesource.hawtjni.runtime;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JniField {
  String cast() default "";
  
  String accessor() default "";
  
  String getter() default "";
  
  String setter() default "";
  
  String conditional() default "";
  
  FieldFlag[] flags() default {};
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\hawtjni\runtime\JniField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */