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
/*    */ public class CommandGamemodeDefault
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 16 */     LiteralArgumentBuilder<CommandListenerWrapper> var1 = (LiteralArgumentBuilder<CommandListenerWrapper>)CommandDispatcher.a("defaultgamemode").requires(var0 -> var0.hasPermission(2));
/*    */     
/* 18 */     for (EnumGamemode var5 : EnumGamemode.values()) {
/* 19 */       if (var5 != EnumGamemode.NOT_SET) {
/* 20 */         var1.then(CommandDispatcher.a(var5.b()).executes(var1 -> a((CommandListenerWrapper)var1.getSource(), var0)));
/*    */       }
/*    */     } 
/*    */     
/* 24 */     var0.register(var1);
/*    */   }
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, EnumGamemode var1) {
/* 28 */     int var2 = 0;
/* 29 */     MinecraftServer var3 = var0.getServer();
/* 30 */     var3.a(var1);
/*    */     
/* 32 */     if (var3.getForceGamemode()) {
/* 33 */       for (EntityPlayer var5 : var3.getPlayerList().getPlayers()) {
/* 34 */         if (var5.playerInteractManager.getGameMode() != var1) {
/* 35 */           var5.a(var1);
/* 36 */           var2++;
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 41 */     var0.sendMessage(new ChatMessage("commands.defaultgamemode.success", new Object[] { var1.c() }), true);
/*    */     
/* 43 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandGamemodeDefault.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */