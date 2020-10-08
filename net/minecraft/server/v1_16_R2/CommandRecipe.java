/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
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
/*     */ public class CommandRecipe
/*     */ {
/*  24 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.recipe.give.failed"));
/*  25 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.recipe.take.failed"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  28 */     var0.register(
/*  29 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("recipe")
/*  30 */         .requires(var0 -> var0.hasPermission(2)))
/*  31 */         .then(
/*  32 */           CommandDispatcher.a("give")
/*  33 */           .then((
/*  34 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/*  35 */             .then(
/*  36 */               CommandDispatcher.<T>a("recipe", ArgumentMinecraftKeyRegistered.a())
/*  37 */               .suggests(CompletionProviders.b)
/*  38 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Collections.singleton(ArgumentMinecraftKeyRegistered.b(var0, "recipe"))))))
/*     */             
/*  40 */             .then(
/*  41 */               CommandDispatcher.a("*")
/*  42 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ((CommandListenerWrapper)var0.getSource()).getServer().getCraftingManager().b()))))))
/*     */ 
/*     */ 
/*     */         
/*  46 */         .then(
/*  47 */           CommandDispatcher.a("take")
/*  48 */           .then((
/*  49 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/*  50 */             .then(
/*  51 */               CommandDispatcher.<T>a("recipe", ArgumentMinecraftKeyRegistered.a())
/*  52 */               .suggests(CompletionProviders.b)
/*  53 */               .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Collections.singleton(ArgumentMinecraftKeyRegistered.b(var0, "recipe"))))))
/*     */             
/*  55 */             .then(
/*  56 */               CommandDispatcher.a("*")
/*  57 */               .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ((CommandListenerWrapper)var0.getSource()).getServer().getCraftingManager().b()))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, Collection<IRecipe<?>> var2) throws CommandSyntaxException {
/*  65 */     int var3 = 0;
/*     */     
/*  67 */     for (EntityPlayer var5 : var1) {
/*  68 */       var3 += var5.discoverRecipes(var2);
/*     */     }
/*     */     
/*  71 */     if (var3 == 0) {
/*  72 */       throw a.create();
/*     */     }
/*     */     
/*  75 */     if (var1.size() == 1) {
/*  76 */       var0.sendMessage(new ChatMessage("commands.recipe.give.success.single", new Object[] { Integer.valueOf(var2.size()), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/*  78 */       var0.sendMessage(new ChatMessage("commands.recipe.give.success.multiple", new Object[] { Integer.valueOf(var2.size()), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/*  81 */     return var3;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, Collection<EntityPlayer> var1, Collection<IRecipe<?>> var2) throws CommandSyntaxException {
/*  85 */     int var3 = 0;
/*     */     
/*  87 */     for (EntityPlayer var5 : var1) {
/*  88 */       var3 += var5.undiscoverRecipes(var2);
/*     */     }
/*     */     
/*  91 */     if (var3 == 0) {
/*  92 */       throw b.create();
/*     */     }
/*     */     
/*  95 */     if (var1.size() == 1) {
/*  96 */       var0.sendMessage(new ChatMessage("commands.recipe.take.success.single", new Object[] { Integer.valueOf(var2.size()), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/*  98 */       var0.sendMessage(new ChatMessage("commands.recipe.take.success.multiple", new Object[] { Integer.valueOf(var2.size()), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 101 */     return var3;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */