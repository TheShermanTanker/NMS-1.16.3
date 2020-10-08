package org.bukkit.attribute;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Attributable {
  @Nullable
  AttributeInstance getAttribute(@NotNull Attribute paramAttribute);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\attribute\Attributable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */