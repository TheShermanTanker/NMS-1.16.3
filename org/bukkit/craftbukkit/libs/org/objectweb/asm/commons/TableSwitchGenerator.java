package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;

import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;

public interface TableSwitchGenerator {
  void generateCase(int paramInt, Label paramLabel);
  
  void generateDefault();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\TableSwitchGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */