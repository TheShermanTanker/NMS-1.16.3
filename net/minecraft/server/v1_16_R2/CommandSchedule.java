/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import com.mojang.datafixers.util.Pair;
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
/*     */ public class CommandSchedule
/*     */ {
/*     */   private static final DynamicCommandExceptionType b;
/*     */   private static final SuggestionProvider<CommandListenerWrapper> c;
/*  32 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.schedule.same_tick")); static {
/*  33 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.schedule.cleared.failure", new Object[] { var0 }));
/*     */     
/*  35 */     c = ((var0, var1) -> ICompletionProvider.b(((CommandListenerWrapper)var0.getSource()).getServer().getSaveData().H().u().a(), var1));
/*     */   }
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  38 */     var0.register(
/*  39 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("schedule")
/*  40 */         .requires(var0 -> var0.hasPermission(2)))
/*  41 */         .then(
/*  42 */           CommandDispatcher.a("function")
/*  43 */           .then(
/*  44 */             CommandDispatcher.<T>a("function", ArgumentTag.a())
/*  45 */             .suggests(CommandFunction.a)
/*  46 */             .then((
/*  47 */               (RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("time", ArgumentTime.a())
/*  48 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentTag.b(var0, "function"), IntegerArgumentType.getInteger(var0, "time"), true)))
/*  49 */               .then(
/*  50 */                 CommandDispatcher.a("append")
/*  51 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentTag.b(var0, "function"), IntegerArgumentType.getInteger(var0, "time"), false))))
/*     */               
/*  53 */               .then(
/*  54 */                 CommandDispatcher.a("replace")
/*  55 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentTag.b(var0, "function"), IntegerArgumentType.getInteger(var0, "time"), true)))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  60 */         .then(
/*  61 */           CommandDispatcher.a("clear")
/*  62 */           .then(
/*  63 */             CommandDispatcher.<T>a("function", (ArgumentType<T>)StringArgumentType.greedyString())
/*  64 */             .suggests(c)
/*  65 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "function"))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Pair<MinecraftKey, Either<CustomFunction, Tag<CustomFunction>>> var1, int var2, boolean var3) throws CommandSyntaxException {
/*  72 */     if (var2 == 0) {
/*  73 */       throw a.create();
/*     */     }
/*     */     
/*  76 */     long var4 = var0.getWorld().getTime() + var2;
/*     */     
/*  78 */     MinecraftKey var6 = (MinecraftKey)var1.getFirst();
/*  79 */     CustomFunctionCallbackTimerQueue<MinecraftServer> var7 = var0.getServer().getSaveData().H().u();
/*  80 */     ((Either)var1.getSecond())
/*  81 */       .ifLeft(var7 -> {
/*     */           String var8 = var0.toString();
/*     */           
/*     */           if (var1) {
/*     */             var2.a(var8);
/*     */           }
/*     */           var2.a(var8, var3, new CustomFunctionCallback(var0));
/*     */           var5.sendMessage(new ChatMessage("commands.schedule.created.function", new Object[] { var0, Integer.valueOf(var6), Long.valueOf(var3) }), true);
/*  89 */         }).ifRight(var7 -> {
/*     */           String var8 = "#" + var0.toString();
/*     */           
/*     */           if (var1) {
/*     */             var2.a(var8);
/*     */           }
/*     */           var2.a(var8, var3, new CustomFunctionCallbackTag(var0));
/*     */           var5.sendMessage(new ChatMessage("commands.schedule.created.tag", new Object[] { var0, Integer.valueOf(var6), Long.valueOf(var3) }), true);
/*     */         });
/*  98 */     return (int)Math.floorMod(var4, 2147483647L);
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, String var1) throws CommandSyntaxException {
/* 102 */     int var2 = var0.getServer().getSaveData().H().u().a(var1);
/* 103 */     if (var2 == 0) {
/* 104 */       throw b.create(var1);
/*     */     }
/* 106 */     var0.sendMessage(new ChatMessage("commands.schedule.cleared.success", new Object[] { Integer.valueOf(var2), var1 }), true);
/* 107 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSchedule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */