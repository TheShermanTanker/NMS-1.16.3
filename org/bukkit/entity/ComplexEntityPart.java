package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;

public interface ComplexEntityPart extends Entity {
  @NotNull
  ComplexLivingEntity getParent();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\ComplexEntityPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */