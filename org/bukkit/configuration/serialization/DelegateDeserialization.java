package org.bukkit.configuration.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DelegateDeserialization {
  @NotNull
  Class<? extends ConfigurationSerializable> value();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\serialization\DelegateDeserialization.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */