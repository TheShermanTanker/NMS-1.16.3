/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.tree.CommandNode;
/*    */ import com.mojang.brigadier.tree.LiteralCommandNode;
/*    */ import java.util.Collection;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandTell
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 26 */     LiteralCommandNode<CommandListenerWrapper> var1 = var0.register(
/* 27 */         (LiteralArgumentBuilder)CommandDispatcher.a("msg")
/* 28 */         .then(
/* 29 */           CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/* 30 */           .then(
/* 31 */             CommandDispatcher.<T>a("message", ArgumentChat.a())
/* 32 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ArgumentChat.a(var0, "message"))))));
/*    */ 
/*    */ 
/*    */     
/* 36 */     var0.register((LiteralArgumentBuilder)CommandDispatcher.a("tell").redirect((CommandNode)var1));
/* 37 */     var0.register((LiteralArgumentBuilder)CommandDispatcher.a("w").redirect((CommandNode)var1));
/*    */   }
/*    */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, IChatBaseComponent var2) {
/*    */     Consumer<IChatBaseComponent> var4;
/* 41 */     UUID var3 = (var0.getEntity() == null) ? SystemUtils.b : var0.getEntity().getUniqueID();
/*    */ 
/*    */     
/* 44 */     Entity var5 = var0.getEntity();
/* 45 */     if (var5 instanceof EntityPlayer) {
/* 46 */       EntityPlayer var6 = (EntityPlayer)var5;
/* 47 */       var4 = (var2 -> var0.sendMessage((new ChatMessage("commands.message.display.outgoing", new Object[] { var2, var1 })).a(new EnumChatFormat[] { EnumChatFormat.GRAY, EnumChatFormat.ITALIC }), var0.getUniqueID()));
/*    */     } else {
/* 49 */       var4 = (var2 -> var0.sendMessage((new ChatMessage("commands.message.display.outgoing", new Object[] { var2, var1 })).a(new EnumChatFormat[] { EnumChatFormat.GRAY, EnumChatFormat.ITALIC }), false));
/*    */     } 
/*    */     
/* 52 */     for (EntityPlayer var7 : var1) {
/* 53 */       var4.accept(var7.getScoreboardDisplayName());
/* 54 */       var7.sendMessage((new ChatMessage("commands.message.display.incoming", new Object[] { var0.getScoreboardDisplayName(), var2 })).a(new EnumChatFormat[] { EnumChatFormat.GRAY, EnumChatFormat.ITALIC }), var3);
/*    */     } 
/*    */     
/* 57 */     return var1.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandTell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */