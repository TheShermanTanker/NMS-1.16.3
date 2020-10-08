/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandPublish
/*    */ {
/*    */   private static final DynamicCommandExceptionType b;
/* 18 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.publish.failed")); static {
/* 19 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.publish.alreadyPublished", new Object[] { var0 }));
/*    */   }
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 22 */     var0.register(
/* 23 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("publish")
/* 24 */         .requires(var0 -> var0.hasPermission(4)))
/* 25 */         .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), HttpUtilities.a())))
/* 26 */         .then(
/* 27 */           CommandDispatcher.<T>a("port", (ArgumentType<T>)IntegerArgumentType.integer(0, 65535))
/* 28 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), IntegerArgumentType.getInteger(var0, "port")))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, int var1) throws CommandSyntaxException {
/* 34 */     if (var0.getServer().n()) {
/* 35 */       throw b.create(Integer.valueOf(var0.getServer().getPort()));
/*    */     }
/* 37 */     if (!var0.getServer().a(var0.getServer().getGamemode(), false, var1)) {
/* 38 */       throw a.create();
/*    */     }
/* 40 */     var0.sendMessage(new ChatMessage("commands.publish.success", new Object[] { Integer.valueOf(var1) }), true);
/* 41 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandPublish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */