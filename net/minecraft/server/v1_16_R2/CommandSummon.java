/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandSummon
/*    */ {
/* 31 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.summon.failed"));
/* 32 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.summon.failed.uuid"));
/* 33 */   private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.summon.invalidPosition"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 36 */     var0.register(
/* 37 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("summon")
/* 38 */         .requires(var0 -> var0.hasPermission(2)))
/* 39 */         .then((
/* 40 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("entity", ArgumentEntitySummon.a())
/* 41 */           .suggests(CompletionProviders.e)
/* 42 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntitySummon.a(var0, "entity"), ((CommandListenerWrapper)var0.getSource()).getPosition(), new NBTTagCompound(), true)))
/* 43 */           .then((
/* 44 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("pos", ArgumentVec3.a())
/* 45 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntitySummon.a(var0, "entity"), ArgumentVec3.a(var0, "pos"), new NBTTagCompound(), true)))
/* 46 */             .then(
/* 47 */               CommandDispatcher.<T>a("nbt", ArgumentNBTTag.a())
/* 48 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntitySummon.a(var0, "entity"), ArgumentVec3.a(var0, "pos"), ArgumentNBTTag.a(var0, "nbt"), false))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, MinecraftKey var1, Vec3D var2, NBTTagCompound var3, boolean var4) throws CommandSyntaxException {
/* 56 */     BlockPosition var5 = new BlockPosition(var2);
/* 57 */     if (!World.l(var5)) {
/* 58 */       throw c.create();
/*    */     }
/*    */     
/* 61 */     NBTTagCompound var6 = var3.clone();
/* 62 */     var6.setString("id", var1.toString());
/*    */     
/* 64 */     WorldServer var7 = var0.getWorld();
/* 65 */     Entity var8 = EntityTypes.a(var6, var7, var1 -> {
/*    */           var1.setPositionRotation(var0.x, var0.y, var0.z, var1.yaw, var1.pitch);
/*    */           return var1;
/*    */         });
/* 69 */     if (var8 == null) {
/* 70 */       throw a.create();
/*    */     }
/*    */     
/* 73 */     if (var4 && var8 instanceof EntityInsentient) {
/* 74 */       ((EntityInsentient)var8).prepare(var0.getWorld(), var0.getWorld().getDamageScaler(var8.getChunkCoordinates()), EnumMobSpawn.COMMAND, null, null);
/*    */     }
/*    */     
/* 77 */     if (!var7.addAllEntitiesSafely(var8)) {
/* 78 */       throw b.create();
/*    */     }
/*    */     
/* 81 */     var0.sendMessage(new ChatMessage("commands.summon.success", new Object[] { var8.getScoreboardDisplayName() }), true);
/* 82 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSummon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */