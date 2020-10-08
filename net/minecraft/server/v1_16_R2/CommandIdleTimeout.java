/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandIdleTimeout
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 15 */     var0.register(
/* 16 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("setidletimeout")
/* 17 */         .requires(var0 -> var0.hasPermission(3)))
/* 18 */         .then(
/* 19 */           CommandDispatcher.<T>a("minutes", (ArgumentType<T>)IntegerArgumentType.integer(0))
/* 20 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), IntegerArgumentType.getInteger(var0, "minutes")))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, int var1) {
/* 26 */     var0.getServer().setIdleTimeout(var1);
/* 27 */     var0.sendMessage(new ChatMessage("commands.setidletimeout.success", new Object[] { Integer.valueOf(var1) }), true);
/* 28 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandIdleTimeout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */