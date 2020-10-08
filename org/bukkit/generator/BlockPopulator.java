package org.bukkit.generator;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public abstract class BlockPopulator {
  public abstract void populate(@NotNull World paramWorld, @NotNull Random paramRandom, @NotNull Chunk paramChunk);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\generator\BlockPopulator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */