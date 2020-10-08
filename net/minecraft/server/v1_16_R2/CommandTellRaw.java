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
/*    */ 
/*    */ public class CommandTellRaw
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 19 */     var0.register(
/* 20 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("tellraw")
/* 21 */         .requires(var0 -> var0.hasPermission(2)))
/* 22 */         .then(
/* 23 */           CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/* 24 */           .then(
/* 25 */             CommandDispatcher.<T>a("message", ArgumentChatComponent.a())
/* 26 */             .executes(var0 -> {
/*    */                 int var1 = 0;
/*    */                 for (EntityPlayer var3 : ArgumentEntity.f(var0, "targets")) {
/*    */                   var3.sendMessage(ChatComponentUtils.filterForDisplay((CommandListenerWrapper)var0.getSource(), ArgumentChatComponent.a(var0, "message"), var3, 0), SystemUtils.b);
/*    */                   var1++;
/*    */                 } 
/*    */                 return var1;
/*    */               }))));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandTellRaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */