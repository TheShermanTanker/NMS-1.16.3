/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandKick
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 21 */     var0.register(
/* 22 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("kick")
/* 23 */         .requires(var0 -> var0.hasPermission(3)))
/* 24 */         .then((
/* 25 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/* 26 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), new ChatMessage("multiplayer.disconnect.kicked"))))
/* 27 */           .then(
/* 28 */             CommandDispatcher.<T>a("reason", ArgumentChat.a())
/* 29 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ArgumentChat.a(var0, "reason"))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, IChatBaseComponent var2) {
/* 36 */     for (EntityPlayer var4 : var1) {
/* 37 */       var4.playerConnection.disconnect(var2);
/* 38 */       var0.sendMessage(new ChatMessage("commands.kick.success", new Object[] { var4.getScoreboardDisplayName(), var2 }), true);
/*    */     } 
/*    */     
/* 41 */     return var1.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandKick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */