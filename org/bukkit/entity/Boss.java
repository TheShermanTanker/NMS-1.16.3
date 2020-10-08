package org.bukkit.entity;

import org.bukkit.boss.BossBar;
import org.jetbrains.annotations.Nullable;

public interface Boss extends Entity {
  @Nullable
  BossBar getBossBar();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Boss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */