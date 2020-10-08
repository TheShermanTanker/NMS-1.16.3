package org.bukkit.advancement;

import java.util.Collection;
import org.bukkit.Keyed;
import org.jetbrains.annotations.NotNull;

public interface Advancement extends Keyed {
  @NotNull
  Collection<String> getCriteria();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\advancement\Advancement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */