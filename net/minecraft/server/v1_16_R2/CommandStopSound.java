/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ import javax.annotation.Nullable;
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
/*    */ 
/*    */ 
/*    */ public class CommandStopSound
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 29 */     RequiredArgumentBuilder<CommandListenerWrapper, EntitySelector> var1 = (RequiredArgumentBuilder<CommandListenerWrapper, EntitySelector>)((RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.d()).executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), null, null))).then(
/* 30 */         CommandDispatcher.a("*")
/* 31 */         .then(
/* 32 */           CommandDispatcher.<T>a("sound", ArgumentMinecraftKeyRegistered.a())
/* 33 */           .suggests(CompletionProviders.c)
/* 34 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), null, ArgumentMinecraftKeyRegistered.e(var0, "sound")))));
/*    */ 
/*    */ 
/*    */     
/* 38 */     for (SoundCategory var5 : SoundCategory.values()) {
/* 39 */       var1.then((
/* 40 */           (LiteralArgumentBuilder)CommandDispatcher.a(var5.a())
/* 41 */           .executes(var1 -> a((CommandListenerWrapper)var1.getSource(), ArgumentEntity.f(var1, "targets"), var0, null)))
/* 42 */           .then(
/* 43 */             CommandDispatcher.<T>a("sound", ArgumentMinecraftKeyRegistered.a())
/* 44 */             .suggests(CompletionProviders.c)
/* 45 */             .executes(var1 -> a((CommandListenerWrapper)var1.getSource(), ArgumentEntity.f(var1, "targets"), var0, ArgumentMinecraftKeyRegistered.e(var1, "sound")))));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 50 */     var0.register(
/* 51 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("stopsound")
/* 52 */         .requires(var0 -> var0.hasPermission(2)))
/* 53 */         .then((ArgumentBuilder)var1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, @Nullable SoundCategory var2, @Nullable MinecraftKey var3) {
/* 60 */     PacketPlayOutStopSound var4 = new PacketPlayOutStopSound(var3, var2);
/* 61 */     for (EntityPlayer var6 : var1) {
/* 62 */       var6.playerConnection.sendPacket(var4);
/*    */     }
/*    */     
/* 65 */     if (var2 != null) {
/* 66 */       if (var3 != null) {
/* 67 */         var0.sendMessage(new ChatMessage("commands.stopsound.success.source.sound", new Object[] { var3, var2.a() }), true);
/*    */       } else {
/* 69 */         var0.sendMessage(new ChatMessage("commands.stopsound.success.source.any", new Object[] { var2.a() }), true);
/*    */       }
/*    */     
/* 72 */     } else if (var3 != null) {
/* 73 */       var0.sendMessage(new ChatMessage("commands.stopsound.success.sourceless.sound", new Object[] { var3 }), true);
/*    */     } else {
/* 75 */       var0.sendMessage(new ChatMessage("commands.stopsound.success.sourceless.any"), true);
/*    */     } 
/*    */ 
/*    */     
/* 79 */     return var1.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandStopSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */