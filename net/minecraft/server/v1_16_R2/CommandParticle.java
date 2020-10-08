/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.FloatArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandParticle
/*    */ {
/* 30 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.particle.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 33 */     var0.register(
/* 34 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("particle")
/* 35 */         .requires(var0 -> var0.hasPermission(2)))
/* 36 */         .then((
/* 37 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("name", ArgumentParticle.a())
/* 38 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentParticle.a(var0, "name"), ((CommandListenerWrapper)var0.getSource()).getPosition(), Vec3D.ORIGIN, 0.0F, 0, false, ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().getPlayers())))
/* 39 */           .then((
/* 40 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("pos", ArgumentVec3.a())
/* 41 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentParticle.a(var0, "name"), ArgumentVec3.a(var0, "pos"), Vec3D.ORIGIN, 0.0F, 0, false, ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().getPlayers())))
/* 42 */             .then(
/* 43 */               CommandDispatcher.<T>a("delta", ArgumentVec3.a(false))
/* 44 */               .then(
/* 45 */                 CommandDispatcher.<T>a("speed", (ArgumentType<T>)FloatArgumentType.floatArg(0.0F))
/* 46 */                 .then((
/* 47 */                   (RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("count", (ArgumentType<T>)IntegerArgumentType.integer(0))
/* 48 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentParticle.a(var0, "name"), ArgumentVec3.a(var0, "pos"), ArgumentVec3.a(var0, "delta"), FloatArgumentType.getFloat(var0, "speed"), IntegerArgumentType.getInteger(var0, "count"), false, ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().getPlayers())))
/* 49 */                   .then((
/* 50 */                     (LiteralArgumentBuilder)CommandDispatcher.a("force")
/* 51 */                     .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentParticle.a(var0, "name"), ArgumentVec3.a(var0, "pos"), ArgumentVec3.a(var0, "delta"), FloatArgumentType.getFloat(var0, "speed"), IntegerArgumentType.getInteger(var0, "count"), true, ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().getPlayers())))
/* 52 */                     .then(
/* 53 */                       CommandDispatcher.<T>a("viewers", ArgumentEntity.d())
/* 54 */                       .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentParticle.a(var0, "name"), ArgumentVec3.a(var0, "pos"), ArgumentVec3.a(var0, "delta"), FloatArgumentType.getFloat(var0, "speed"), IntegerArgumentType.getInteger(var0, "count"), true, ArgumentEntity.f(var0, "viewers"))))))
/*    */ 
/*    */                   
/* 57 */                   .then((
/* 58 */                     (LiteralArgumentBuilder)CommandDispatcher.a("normal")
/* 59 */                     .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentParticle.a(var0, "name"), ArgumentVec3.a(var0, "pos"), ArgumentVec3.a(var0, "delta"), FloatArgumentType.getFloat(var0, "speed"), IntegerArgumentType.getInteger(var0, "count"), false, ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().getPlayers())))
/* 60 */                     .then(
/* 61 */                       CommandDispatcher.<T>a("viewers", ArgumentEntity.d())
/* 62 */                       .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentParticle.a(var0, "name"), ArgumentVec3.a(var0, "pos"), ArgumentVec3.a(var0, "delta"), FloatArgumentType.getFloat(var0, "speed"), IntegerArgumentType.getInteger(var0, "count"), false, ArgumentEntity.f(var0, "viewers")))))))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, ParticleParam var1, Vec3D var2, Vec3D var3, float var4, int var5, boolean var6, Collection<EntityPlayer> var7) throws CommandSyntaxException {
/* 74 */     int var8 = 0;
/*    */     
/* 76 */     for (EntityPlayer var10 : var7) {
/* 77 */       if (var0.getWorld().a(var10, var1, var6, var2.x, var2.y, var2.z, var5, var3.x, var3.y, var3.z, var4)) {
/* 78 */         var8++;
/*    */       }
/*    */     } 
/*    */     
/* 82 */     if (var8 == 0) {
/* 83 */       throw a.create();
/*    */     }
/*    */     
/* 86 */     var0.sendMessage(new ChatMessage("commands.particle.success", new Object[] { IRegistry.PARTICLE_TYPE.getKey(var1.getParticle()).toString() }), true);
/*    */     
/* 88 */     return var8;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */