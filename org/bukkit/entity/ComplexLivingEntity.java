package org.bukkit.entity;

import java.util.Set;
import org.jetbrains.annotations.NotNull;

public interface ComplexLivingEntity extends LivingEntity {
  @NotNull
  Set<ComplexEntityPart> getParts();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\ComplexLivingEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */