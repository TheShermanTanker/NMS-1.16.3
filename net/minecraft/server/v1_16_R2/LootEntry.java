package net.minecraft.server.v1_16_R2;

import java.util.function.Consumer;

public interface LootEntry {
  int a(float paramFloat);
  
  void a(Consumer<ItemStack> paramConsumer, LootTableInfo paramLootTableInfo);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */