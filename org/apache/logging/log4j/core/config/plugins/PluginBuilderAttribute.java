package org.apache.logging.log4j.core.config.plugins;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.logging.log4j.core.config.plugins.visitors.PluginBuilderAttributeVisitor;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@PluginVisitorStrategy(PluginBuilderAttributeVisitor.class)
public @interface PluginBuilderAttribute {
  String value() default "";
  
  boolean sensitive() default false;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\core\config\plugins\PluginBuilderAttribute.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */