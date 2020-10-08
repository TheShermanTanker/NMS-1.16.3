/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.FloatArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandPlaySound
/*     */ {
/*  32 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.playsound.failed"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  35 */     RequiredArgumentBuilder<CommandListenerWrapper, MinecraftKey> var1 = CommandDispatcher.<T>a("sound", ArgumentMinecraftKeyRegistered.a()).suggests(CompletionProviders.c);
/*     */     
/*  37 */     for (SoundCategory var5 : SoundCategory.values()) {
/*  38 */       var1.then((ArgumentBuilder)a(var5));
/*     */     }
/*     */     
/*  41 */     var0.register(
/*  42 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("playsound")
/*  43 */         .requires(var0 -> var0.hasPermission(2)))
/*  44 */         .then((ArgumentBuilder)var1));
/*     */   }
/*     */ 
/*     */   
/*     */   private static LiteralArgumentBuilder<CommandListenerWrapper> a(SoundCategory var0) {
/*  49 */     return (LiteralArgumentBuilder<CommandListenerWrapper>)CommandDispatcher.a(var0.a())
/*  50 */       .then((
/*  51 */         (RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/*  52 */         .executes(var1 -> a((CommandListenerWrapper)var1.getSource(), ArgumentEntity.f(var1, "targets"), ArgumentMinecraftKeyRegistered.e(var1, "sound"), var0, ((CommandListenerWrapper)var1.getSource()).getPosition(), 1.0F, 1.0F, 0.0F)))
/*  53 */         .then((
/*  54 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("pos", ArgumentVec3.a())
/*  55 */           .executes(var1 -> a((CommandListenerWrapper)var1.getSource(), ArgumentEntity.f(var1, "targets"), ArgumentMinecraftKeyRegistered.e(var1, "sound"), var0, ArgumentVec3.a(var1, "pos"), 1.0F, 1.0F, 0.0F)))
/*  56 */           .then((
/*  57 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("volume", (ArgumentType<T>)FloatArgumentType.floatArg(0.0F))
/*  58 */             .executes(var1 -> a((CommandListenerWrapper)var1.getSource(), ArgumentEntity.f(var1, "targets"), ArgumentMinecraftKeyRegistered.e(var1, "sound"), var0, ArgumentVec3.a(var1, "pos"), ((Float)var1.getArgument("volume", Float.class)).floatValue(), 1.0F, 0.0F)))
/*  59 */             .then((
/*  60 */               (RequiredArgumentBuilder)CommandDispatcher.<T>a("pitch", (ArgumentType<T>)FloatArgumentType.floatArg(0.0F, 2.0F))
/*  61 */               .executes(var1 -> a((CommandListenerWrapper)var1.getSource(), ArgumentEntity.f(var1, "targets"), ArgumentMinecraftKeyRegistered.e(var1, "sound"), var0, ArgumentVec3.a(var1, "pos"), ((Float)var1.getArgument("volume", Float.class)).floatValue(), ((Float)var1.getArgument("pitch", Float.class)).floatValue(), 0.0F)))
/*  62 */               .then(
/*  63 */                 CommandDispatcher.<T>a("minVolume", (ArgumentType<T>)FloatArgumentType.floatArg(0.0F, 1.0F))
/*  64 */                 .executes(var1 -> a((CommandListenerWrapper)var1.getSource(), ArgumentEntity.f(var1, "targets"), ArgumentMinecraftKeyRegistered.e(var1, "sound"), var0, ArgumentVec3.a(var1, "pos"), ((Float)var1.getArgument("volume", Float.class)).floatValue(), ((Float)var1.getArgument("pitch", Float.class)).floatValue(), ((Float)var1.getArgument("minVolume", Float.class)).floatValue())))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, MinecraftKey var2, SoundCategory var3, Vec3D var4, float var5, float var6, float var7) throws CommandSyntaxException {
/*  73 */     double var8 = Math.pow((var5 > 1.0F) ? (var5 * 16.0F) : 16.0D, 2.0D);
/*  74 */     int var10 = 0;
/*     */     
/*  76 */     for (EntityPlayer var12 : var1) {
/*  77 */       double var13 = var4.x - var12.locX();
/*  78 */       double var15 = var4.y - var12.locY();
/*  79 */       double var17 = var4.z - var12.locZ();
/*  80 */       double var19 = var13 * var13 + var15 * var15 + var17 * var17;
/*  81 */       Vec3D var21 = var4;
/*  82 */       float var22 = var5;
/*     */       
/*  84 */       if (var19 > var8) {
/*  85 */         if (var7 <= 0.0F) {
/*     */           continue;
/*     */         }
/*     */         
/*  89 */         double var23 = MathHelper.sqrt(var19);
/*  90 */         var21 = new Vec3D(var12.locX() + var13 / var23 * 2.0D, var12.locY() + var15 / var23 * 2.0D, var12.locZ() + var17 / var23 * 2.0D);
/*  91 */         var22 = var7;
/*     */       } 
/*     */       
/*  94 */       var12.playerConnection.sendPacket(new PacketPlayOutCustomSoundEffect(var2, var3, var21, var22, var6));
/*  95 */       var10++;
/*     */     } 
/*     */     
/*  98 */     if (var10 == 0) {
/*  99 */       throw a.create();
/*     */     }
/*     */     
/* 102 */     if (var1.size() == 1) {
/* 103 */       var0.sendMessage(new ChatMessage("commands.playsound.success.single", new Object[] { var2, ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 105 */       var0.sendMessage(new ChatMessage("commands.playsound.success.multiple", new Object[] { var2, Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 108 */     return var10;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandPlaySound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */