/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
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
/*    */ 
/*    */ 
/*    */ public class CommandSpawnpoint
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 26 */     var0.register(
/* 27 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("spawnpoint")
/* 28 */         .requires(var0 -> var0.hasPermission(2)))
/* 29 */         .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), Collections.singleton(((CommandListenerWrapper)var0.getSource()).h()), new BlockPosition(((CommandListenerWrapper)var0.getSource()).getPosition()), 0.0F)))
/* 30 */         .then((
/* 31 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/* 32 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), new BlockPosition(((CommandListenerWrapper)var0.getSource()).getPosition()), 0.0F)))
/* 33 */           .then((
/* 34 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("pos", ArgumentPosition.a())
/* 35 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ArgumentPosition.b(var0, "pos"), 0.0F)))
/* 36 */             .then(
/* 37 */               CommandDispatcher.<T>a("angle", ArgumentAngle.a())
/* 38 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ArgumentPosition.b(var0, "pos"), ArgumentAngle.a(var0, "angle")))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, BlockPosition var2, float var3) {
/* 46 */     ResourceKey<World> var4 = var0.getWorld().getDimensionKey();
/* 47 */     for (EntityPlayer var6 : var1) {
/* 48 */       var6.setRespawnPosition(var4, var2, var3, true, false);
/*    */     }
/*    */     
/* 51 */     String var5 = var4.a().toString();
/* 52 */     if (var1.size() == 1) {
/* 53 */       var0.sendMessage(new ChatMessage("commands.spawnpoint.success.single", new Object[] { Integer.valueOf(var2.getX()), Integer.valueOf(var2.getY()), Integer.valueOf(var2.getZ()), Float.valueOf(var3), var5, ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*    */     } else {
/* 55 */       var0.sendMessage(new ChatMessage("commands.spawnpoint.success.multiple", new Object[] { Integer.valueOf(var2.getX()), Integer.valueOf(var2.getY()), Integer.valueOf(var2.getZ()), Float.valueOf(var3), var5, Integer.valueOf(var1.size()) }), true);
/*    */     } 
/*    */     
/* 58 */     return var1.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSpawnpoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */