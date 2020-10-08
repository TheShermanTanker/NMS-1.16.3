/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
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
/*    */ public class CommandKill
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 19 */     var0.register(
/* 20 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("kill")
/* 21 */         .requires(var0 -> var0.hasPermission(2)))
/* 22 */         .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), (Collection<? extends Entity>)ImmutableList.of(((CommandListenerWrapper)var0.getSource()).g()))))
/* 23 */         .then(
/* 24 */           CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities())
/* 25 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.b(var0, "targets")))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<? extends Entity> var1) {
/* 31 */     for (Entity var3 : var1) {
/* 32 */       var3.killEntity();
/*    */     }
/*    */     
/* 35 */     if (var1.size() == 1) {
/* 36 */       var0.sendMessage(new ChatMessage("commands.kill.success.single", new Object[] { ((Entity)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*    */     } else {
/* 38 */       var0.sendMessage(new ChatMessage("commands.kill.success.multiple", new Object[] { Integer.valueOf(var1.size()) }), true);
/*    */     } 
/*    */     
/* 41 */     return var1.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandKill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */