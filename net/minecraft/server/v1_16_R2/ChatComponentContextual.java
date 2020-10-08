package net.minecraft.server.v1_16_R2;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import javax.annotation.Nullable;

public interface ChatComponentContextual {
  IChatMutableComponent a(@Nullable CommandListenerWrapper paramCommandListenerWrapper, @Nullable Entity paramEntity, int paramInt) throws CommandSyntaxException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatComponentContextual.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */