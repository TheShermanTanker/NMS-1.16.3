/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CompletableFuture;
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
/*     */ public class CommandTrigger
/*     */ {
/*  30 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.trigger.failed.unprimed"));
/*  31 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.trigger.failed.invalid"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  34 */     var0.register(
/*  35 */         (LiteralArgumentBuilder)CommandDispatcher.a("trigger")
/*  36 */         .then((
/*  37 */           (RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/*  38 */           .suggests((var0, var1) -> a((CommandListenerWrapper)var0.getSource(), var1))
/*  39 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(((CommandListenerWrapper)var0.getSource()).h(), ArgumentScoreboardObjective.a(var0, "objective")))))
/*  40 */           .then(
/*  41 */             CommandDispatcher.a("add")
/*  42 */             .then(
/*  43 */               CommandDispatcher.<T>a("value", (ArgumentType<T>)IntegerArgumentType.integer())
/*  44 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(((CommandListenerWrapper)var0.getSource()).h(), ArgumentScoreboardObjective.a(var0, "objective")), IntegerArgumentType.getInteger(var0, "value"))))))
/*     */ 
/*     */           
/*  47 */           .then(
/*  48 */             CommandDispatcher.a("set")
/*  49 */             .then(
/*  50 */               CommandDispatcher.<T>a("value", (ArgumentType<T>)IntegerArgumentType.integer())
/*  51 */               .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), a(((CommandListenerWrapper)var0.getSource()).h(), ArgumentScoreboardObjective.a(var0, "objective")), IntegerArgumentType.getInteger(var0, "value")))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CompletableFuture<Suggestions> a(CommandListenerWrapper var0, SuggestionsBuilder var1) {
/*  59 */     Entity var2 = var0.getEntity();
/*  60 */     List<String> var3 = Lists.newArrayList();
/*     */     
/*  62 */     if (var2 != null) {
/*  63 */       Scoreboard var4 = var0.getServer().getScoreboard();
/*  64 */       String var5 = var2.getName();
/*     */       
/*  66 */       for (ScoreboardObjective var7 : var4.getObjectives()) {
/*  67 */         if (var7.getCriteria() == IScoreboardCriteria.TRIGGER && var4.b(var5, var7)) {
/*  68 */           ScoreboardScore var8 = var4.getPlayerScoreForObjective(var5, var7);
/*  69 */           if (!var8.g()) {
/*  70 */             var3.add(var7.getName());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     return ICompletionProvider.b(var3, var1);
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardScore var1, int var2) {
/*  80 */     var1.addScore(var2);
/*  81 */     var0.sendMessage(new ChatMessage("commands.trigger.add.success", new Object[] { var1.getObjective().e(), Integer.valueOf(var2) }), true);
/*  82 */     return var1.getScore();
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, ScoreboardScore var1, int var2) {
/*  86 */     var1.setScore(var2);
/*  87 */     var0.sendMessage(new ChatMessage("commands.trigger.set.success", new Object[] { var1.getObjective().e(), Integer.valueOf(var2) }), true);
/*  88 */     return var2;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardScore var1) {
/*  92 */     var1.addScore(1);
/*  93 */     var0.sendMessage(new ChatMessage("commands.trigger.simple.success", new Object[] { var1.getObjective().e() }), true);
/*  94 */     return var1.getScore();
/*     */   }
/*     */   
/*     */   private static ScoreboardScore a(EntityPlayer var0, ScoreboardObjective var1) throws CommandSyntaxException {
/*  98 */     if (var1.getCriteria() != IScoreboardCriteria.TRIGGER) {
/*  99 */       throw b.create();
/*     */     }
/* 101 */     Scoreboard var2 = var0.getScoreboard();
/* 102 */     String var3 = var0.getName();
/* 103 */     if (!var2.b(var3, var1)) {
/* 104 */       throw a.create();
/*     */     }
/* 106 */     ScoreboardScore var4 = var2.getPlayerScoreForObjective(var3, var1);
/* 107 */     if (var4.g()) {
/* 108 */       throw a.create();
/*     */     }
/* 110 */     var4.a(true);
/* 111 */     return var4;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */