/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandTag
/*     */ {
/*  25 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.tag.add.failed"));
/*  26 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.tag.remove.failed"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  29 */     var0.register(
/*  30 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("tag")
/*  31 */         .requires(var0 -> var0.hasPermission(2)))
/*  32 */         .then((
/*  33 */           (RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities())
/*  34 */           .then(
/*  35 */             CommandDispatcher.a("add")
/*  36 */             .then(
/*  37 */               CommandDispatcher.<T>a("name", (ArgumentType<T>)StringArgumentType.word())
/*  38 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.b(var0, "targets"), StringArgumentType.getString(var0, "name"))))))
/*     */ 
/*     */           
/*  41 */           .then(
/*  42 */             CommandDispatcher.a("remove")
/*  43 */             .then(
/*  44 */               CommandDispatcher.<T>a("name", (ArgumentType<T>)StringArgumentType.word())
/*  45 */               .suggests((var0, var1) -> ICompletionProvider.b(a(ArgumentEntity.b(var0, "targets")), var1))
/*  46 */               .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentEntity.b(var0, "targets"), StringArgumentType.getString(var0, "name"))))))
/*     */ 
/*     */           
/*  49 */           .then(
/*  50 */             CommandDispatcher.a("list")
/*  51 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.b(var0, "targets"))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Collection<String> a(Collection<? extends Entity> var0) {
/*  58 */     Set<String> var1 = Sets.newHashSet();
/*  59 */     for (Entity var3 : var0) {
/*  60 */       var1.addAll(var3.getScoreboardTags());
/*     */     }
/*  62 */     return var1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<? extends Entity> var1, String var2) throws CommandSyntaxException {
/*  66 */     int var3 = 0;
/*     */     
/*  68 */     for (Entity var5 : var1) {
/*  69 */       if (var5.addScoreboardTag(var2)) {
/*  70 */         var3++;
/*     */       }
/*     */     } 
/*     */     
/*  74 */     if (var3 == 0) {
/*  75 */       throw a.create();
/*     */     }
/*     */     
/*  78 */     if (var1.size() == 1) {
/*  79 */       var0.sendMessage(new ChatMessage("commands.tag.add.success.single", new Object[] { var2, ((Entity)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/*  81 */       var0.sendMessage(new ChatMessage("commands.tag.add.success.multiple", new Object[] { var2, Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/*  84 */     return var3;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, Collection<? extends Entity> var1, String var2) throws CommandSyntaxException {
/*  88 */     int var3 = 0;
/*     */     
/*  90 */     for (Entity var5 : var1) {
/*  91 */       if (var5.removeScoreboardTag(var2)) {
/*  92 */         var3++;
/*     */       }
/*     */     } 
/*     */     
/*  96 */     if (var3 == 0) {
/*  97 */       throw b.create();
/*     */     }
/*     */     
/* 100 */     if (var1.size() == 1) {
/* 101 */       var0.sendMessage(new ChatMessage("commands.tag.remove.success.single", new Object[] { var2, ((Entity)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 103 */       var0.sendMessage(new ChatMessage("commands.tag.remove.success.multiple", new Object[] { var2, Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 106 */     return var3;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<? extends Entity> var1) {
/* 110 */     Set<String> var2 = Sets.newHashSet();
/*     */     
/* 112 */     for (Entity var4 : var1) {
/* 113 */       var2.addAll(var4.getScoreboardTags());
/*     */     }
/*     */     
/* 116 */     if (var1.size() == 1) {
/* 117 */       Entity var3 = var1.iterator().next();
/*     */       
/* 119 */       if (var2.isEmpty()) {
/* 120 */         var0.sendMessage(new ChatMessage("commands.tag.list.single.empty", new Object[] { var3.getScoreboardDisplayName() }), false);
/*     */       } else {
/* 122 */         var0.sendMessage(new ChatMessage("commands.tag.list.single.success", new Object[] { var3.getScoreboardDisplayName(), Integer.valueOf(var2.size()), ChatComponentUtils.a(var2) }), false);
/*     */       }
/*     */     
/* 125 */     } else if (var2.isEmpty()) {
/* 126 */       var0.sendMessage(new ChatMessage("commands.tag.list.multiple.empty", new Object[] { Integer.valueOf(var1.size()) }), false);
/*     */     } else {
/* 128 */       var0.sendMessage(new ChatMessage("commands.tag.list.multiple.success", new Object[] { Integer.valueOf(var1.size()), Integer.valueOf(var2.size()), ChatComponentUtils.a(var2) }), false);
/*     */     } 
/*     */ 
/*     */     
/* 132 */     return var2.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */