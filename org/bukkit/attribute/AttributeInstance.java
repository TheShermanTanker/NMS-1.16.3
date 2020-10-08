package org.bukkit.attribute;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;

public interface AttributeInstance {
  @NotNull
  Attribute getAttribute();
  
  double getBaseValue();
  
  void setBaseValue(double paramDouble);
  
  @NotNull
  Collection<AttributeModifier> getModifiers();
  
  void addModifier(@NotNull AttributeModifier paramAttributeModifier);
  
  void removeModifier(@NotNull AttributeModifier paramAttributeModifier);
  
  double getValue();
  
  double getDefaultValue();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\attribute\AttributeInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */