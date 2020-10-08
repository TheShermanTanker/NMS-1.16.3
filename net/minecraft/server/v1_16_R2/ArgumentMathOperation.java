/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentMathOperation
/*     */   implements ArgumentType<ArgumentMathOperation.a>
/*     */ {
/*  21 */   private static final Collection<String> a = Arrays.asList(new String[] { "=", ">", "<" });
/*  22 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("arguments.operation.invalid"));
/*  23 */   private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("arguments.operation.div0"));
/*     */   
/*     */   public static ArgumentMathOperation a() {
/*  26 */     return new ArgumentMathOperation();
/*     */   }
/*     */   
/*     */   public static a a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/*  30 */     return (a)var0.getArgument(var1, a.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public a parse(StringReader var0) throws CommandSyntaxException {
/*  35 */     if (var0.canRead()) {
/*  36 */       int var1 = var0.getCursor();
/*  37 */       while (var0.canRead() && var0.peek() != ' ') {
/*  38 */         var0.skip();
/*     */       }
/*  40 */       return a(var0.getString().substring(var1, var0.getCursor()));
/*     */     } 
/*     */     
/*  43 */     throw b.create();
/*     */   }
/*     */ 
/*     */   
/*     */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/*  48 */     return ICompletionProvider.a(new String[] { "=", "+=", "-=", "*=", "/=", "%=", "<", ">", "><" }, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<String> getExamples() {
/*  53 */     return a;
/*     */   }
/*     */   
/*     */   private static a a(String var0) throws CommandSyntaxException {
/*  57 */     if (var0.equals("><")) {
/*  58 */       return (var0, var1) -> {
/*     */           int var2 = var0.getScore();
/*     */           
/*     */           var0.setScore(var1.getScore());
/*     */           var1.setScore(var2);
/*     */         };
/*     */     }
/*  65 */     return b(var0);
/*     */   }
/*     */   
/*     */   private static b b(String var0) throws CommandSyntaxException {
/*  69 */     switch (var0) {
/*     */       case "=":
/*  71 */         return (var0, var1) -> var1;
/*     */       case "+=":
/*  73 */         return (var0, var1) -> var0 + var1;
/*     */       case "-=":
/*  75 */         return (var0, var1) -> var0 - var1;
/*     */       case "*=":
/*  77 */         return (var0, var1) -> var0 * var1;
/*     */       case "/=":
/*  79 */         return (var0, var1) -> {
/*     */             if (var1 == 0) {
/*     */               throw c.create();
/*     */             }
/*     */             return MathHelper.a(var0, var1);
/*     */           };
/*     */       case "%=":
/*  86 */         return (var0, var1) -> {
/*     */             if (var1 == 0) {
/*     */               throw c.create();
/*     */             }
/*     */             return MathHelper.b(var0, var1);
/*     */           };
/*     */       case "<":
/*  93 */         return Math::min;
/*     */       case ">":
/*  95 */         return Math::max;
/*     */     } 
/*  97 */     throw b.create();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @FunctionalInterface
/*     */   static interface b
/*     */     extends a
/*     */   {
/*     */     int apply(int param1Int1, int param1Int2) throws CommandSyntaxException;
/*     */ 
/*     */ 
/*     */     
/*     */     default void apply(ScoreboardScore var0, ScoreboardScore var1) throws CommandSyntaxException {
/* 111 */       var0.setScore(apply(var0.getScore(), var1.getScore()));
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface a {
/*     */     void apply(ScoreboardScore param1ScoreboardScore1, ScoreboardScore param1ScoreboardScore2) throws CommandSyntaxException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentMathOperation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */