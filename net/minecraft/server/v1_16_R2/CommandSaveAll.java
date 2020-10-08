/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandSaveAll
/*    */ {
/* 14 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.save.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 17 */     var0.register(
/* 18 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("save-all")
/* 19 */         .requires(var0 -> var0.hasPermission(4)))
/* 20 */         .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), false)))
/* 21 */         .then(
/* 22 */           CommandDispatcher.a("flush")
/* 23 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), true))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, boolean var1) throws CommandSyntaxException {
/* 29 */     var0.sendMessage(new ChatMessage("commands.save.saving"), false);
/*    */     
/* 31 */     MinecraftServer var2 = var0.getServer();
/* 32 */     var2.getPlayerList().savePlayers();
/*    */     
/* 34 */     boolean var3 = var2.saveChunks(true, var1, true);
/*    */     
/* 36 */     if (!var3) {
/* 37 */       throw a.create();
/*    */     }
/*    */     
/* 40 */     var0.sendMessage(new ChatMessage("commands.save.success"), true);
/*    */     
/* 42 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSaveAll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */