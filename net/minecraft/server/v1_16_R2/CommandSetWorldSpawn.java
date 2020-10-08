/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandSetWorldSpawn
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 18 */     var0.register(
/* 19 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("setworldspawn")
/* 20 */         .requires(var0 -> var0.hasPermission(2)))
/* 21 */         .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), new BlockPosition(((CommandListenerWrapper)var0.getSource()).getPosition()), 0.0F)))
/* 22 */         .then((
/* 23 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("pos", ArgumentPosition.a())
/* 24 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.b(var0, "pos"), 0.0F)))
/* 25 */           .then(
/* 26 */             CommandDispatcher.<T>a("angle", ArgumentAngle.a())
/* 27 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.b(var0, "pos"), ArgumentAngle.a(var0, "angle"))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, BlockPosition var1, float var2) {
/* 34 */     var0.getWorld().a(var1, var2);
/* 35 */     var0.sendMessage(new ChatMessage("commands.setworldspawn.success", new Object[] { Integer.valueOf(var1.getX()), Integer.valueOf(var1.getY()), Integer.valueOf(var1.getZ()), Float.valueOf(var2) }), true);
/* 36 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSetWorldSpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */