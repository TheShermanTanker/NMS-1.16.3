/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandSeed
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0, boolean var1) {
/* 18 */     var0.register(
/* 19 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("seed")
/* 20 */         .requires(var1 -> (!var0 || var1.hasPermission(2))))
/* 21 */         .executes(var0 -> {
/*    */             long var1 = ((CommandListenerWrapper)var0.getSource()).getWorld().getSeed();
/*    */             IChatBaseComponent var3 = ChatComponentUtils.a((new ChatComponentText(String.valueOf(var1))).format(()));
/*    */             ((CommandListenerWrapper)var0.getSource()).sendMessage(new ChatMessage("commands.seed.success", new Object[] { var3 }), false);
/*    */             return (int)var1;
/*    */           }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */