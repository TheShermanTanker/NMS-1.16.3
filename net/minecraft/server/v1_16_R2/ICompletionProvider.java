/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface ICompletionProvider
/*     */ {
/*     */   public static class a
/*     */   {
/*  27 */     public static final a a = new a("^", "^", "^");
/*     */     
/*  29 */     public static final a b = new a("~", "~", "~");
/*     */     
/*     */     public final String c;
/*     */     
/*     */     public final String d;
/*     */     
/*     */     public final String e;
/*     */     
/*     */     public a(String var0, String var1, String var2) {
/*  38 */       this.c = var0;
/*  39 */       this.d = var1;
/*  40 */       this.e = var2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   default Collection<String> r() {
/*  47 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default Collection<a> s() {
/*  59 */     return Collections.singleton(a.b);
/*     */   }
/*     */   
/*     */   default Collection<a> t() {
/*  63 */     return Collections.singleton(a.b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static <T> void a(Iterable<T> var0, String var1, Function<T, MinecraftKey> var2, Consumer<T> var3) {
/*  73 */     boolean var4 = (var1.indexOf(':') > -1);
/*  74 */     for (T var6 : var0) {
/*  75 */       MinecraftKey var7 = var2.apply(var6);
/*  76 */       if (var4) {
/*  77 */         String var8 = var7.toString();
/*  78 */         if (a(var1, var8))
/*  79 */           var3.accept(var6); 
/*     */         continue;
/*     */       } 
/*  82 */       if (a(var1, var7.getNamespace()) || (var7.getNamespace().equals("minecraft") && a(var1, var7.getKey()))) {
/*  83 */         var3.accept(var6);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static <T> void a(Iterable<T> var0, String var1, String var2, Function<T, MinecraftKey> var3, Consumer<T> var4) {
/*  90 */     if (var1.isEmpty()) {
/*  91 */       var0.forEach(var4);
/*     */     } else {
/*  93 */       String var5 = Strings.commonPrefix(var1, var2);
/*  94 */       if (!var5.isEmpty()) {
/*  95 */         String var6 = var1.substring(var5.length());
/*  96 */         a(var0, var6, var3, var4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static CompletableFuture<Suggestions> a(Iterable<MinecraftKey> var0, SuggestionsBuilder var1, String var2) {
/* 102 */     String var3 = var1.getRemaining().toLowerCase(Locale.ROOT);
/* 103 */     a(var0, var3, var2, var0 -> var0, var2 -> var0.suggest(var1 + var2));
/* 104 */     return var1.buildFuture();
/*     */   }
/*     */   
/*     */   static CompletableFuture<Suggestions> a(Iterable<MinecraftKey> var0, SuggestionsBuilder var1) {
/* 108 */     String var2 = var1.getRemaining().toLowerCase(Locale.ROOT);
/* 109 */     a(var0, var2, var0 -> var0, var1 -> var0.suggest(var1.toString()));
/* 110 */     return var1.buildFuture();
/*     */   }
/*     */   
/*     */   static <T> CompletableFuture<Suggestions> a(Iterable<T> var0, SuggestionsBuilder var1, Function<T, MinecraftKey> var2, Function<T, Message> var3) {
/* 114 */     String var4 = var1.getRemaining().toLowerCase(Locale.ROOT);
/* 115 */     a(var0, var4, var2, var3 -> var0.suggest(((MinecraftKey)var1.apply(var3)).toString(), var2.apply(var3)));
/* 116 */     return var1.buildFuture();
/*     */   }
/*     */   
/*     */   static CompletableFuture<Suggestions> a(Stream<MinecraftKey> var0, SuggestionsBuilder var1) {
/* 120 */     return a(var0::iterator, var1);
/*     */   }
/*     */   
/*     */   static <T> CompletableFuture<Suggestions> a(Stream<T> var0, SuggestionsBuilder var1, Function<T, MinecraftKey> var2, Function<T, Message> var3) {
/* 124 */     return a(var0::iterator, var1, var2, var3);
/*     */   }
/*     */   
/*     */   static CompletableFuture<Suggestions> a(String var0, Collection<a> var1, SuggestionsBuilder var2, Predicate<String> var3) {
/* 128 */     List<String> var4 = Lists.newArrayList();
/*     */     
/* 130 */     if (Strings.isNullOrEmpty(var0)) {
/* 131 */       for (a var6 : var1) {
/* 132 */         String var7 = var6.c + " " + var6.d + " " + var6.e;
/* 133 */         if (var3.test(var7)) {
/* 134 */           var4.add(var6.c);
/* 135 */           var4.add(var6.c + " " + var6.d);
/* 136 */           var4.add(var7);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 140 */       String[] var5 = var0.split(" ");
/*     */       
/* 142 */       if (var5.length == 1) {
/* 143 */         for (a var7 : var1) {
/* 144 */           String var8 = var5[0] + " " + var7.d + " " + var7.e;
/* 145 */           if (var3.test(var8)) {
/* 146 */             var4.add(var5[0] + " " + var7.d);
/* 147 */             var4.add(var8);
/*     */           } 
/*     */         } 
/* 150 */       } else if (var5.length == 2) {
/* 151 */         for (a var7 : var1) {
/* 152 */           String var8 = var5[0] + " " + var5[1] + " " + var7.e;
/* 153 */           if (var3.test(var8)) {
/* 154 */             var4.add(var8);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 159 */     return b(var4, var2);
/*     */   }
/*     */   
/*     */   static CompletableFuture<Suggestions> b(String var0, Collection<a> var1, SuggestionsBuilder var2, Predicate<String> var3) {
/* 163 */     List<String> var4 = Lists.newArrayList();
/*     */     
/* 165 */     if (Strings.isNullOrEmpty(var0)) {
/* 166 */       for (a var6 : var1) {
/* 167 */         String var7 = var6.c + " " + var6.e;
/* 168 */         if (var3.test(var7)) {
/* 169 */           var4.add(var6.c);
/* 170 */           var4.add(var7);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 174 */       String[] var5 = var0.split(" ");
/* 175 */       if (var5.length == 1) {
/* 176 */         for (a var7 : var1) {
/* 177 */           String var8 = var5[0] + " " + var7.e;
/* 178 */           if (var3.test(var8)) {
/* 179 */             var4.add(var8);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 184 */     return b(var4, var2);
/*     */   }
/*     */   
/*     */   static CompletableFuture<Suggestions> b(Iterable<String> var0, SuggestionsBuilder var1) {
/* 188 */     String var2 = var1.getRemaining().toLowerCase(Locale.ROOT);
/* 189 */     for (String var4 : var0) {
/* 190 */       if (a(var2, var4.toLowerCase(Locale.ROOT))) {
/* 191 */         var1.suggest(var4);
/*     */       }
/*     */     } 
/* 194 */     return var1.buildFuture();
/*     */   }
/*     */   
/*     */   static CompletableFuture<Suggestions> b(Stream<String> var0, SuggestionsBuilder var1) {
/* 198 */     String var2 = var1.getRemaining().toLowerCase(Locale.ROOT);
/* 199 */     var0.filter(var1 -> a(var0, var1.toLowerCase(Locale.ROOT))).forEach(var1::suggest);
/* 200 */     return var1.buildFuture();
/*     */   }
/*     */   
/*     */   static CompletableFuture<Suggestions> a(String[] var0, SuggestionsBuilder var1) {
/* 204 */     String var2 = var1.getRemaining().toLowerCase(Locale.ROOT);
/* 205 */     for (String var6 : var0) {
/* 206 */       if (a(var2, var6.toLowerCase(Locale.ROOT))) {
/* 207 */         var1.suggest(var6);
/*     */       }
/*     */     } 
/* 210 */     return var1.buildFuture();
/*     */   }
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
/*     */   static boolean a(String var0, String var1) {
/* 225 */     int var2 = 0;
/* 226 */     while (!var1.startsWith(var0, var2)) {
/* 227 */       var2 = var1.indexOf('_', var2);
/* 228 */       if (var2 < 0) {
/* 229 */         return false;
/*     */       }
/*     */       
/* 232 */       var2++;
/*     */     } 
/*     */     
/* 235 */     return true;
/*     */   }
/*     */   
/*     */   Collection<String> l();
/*     */   
/*     */   Collection<String> m();
/*     */   
/*     */   Collection<MinecraftKey> n();
/*     */   
/*     */   Stream<MinecraftKey> o();
/*     */   
/*     */   CompletableFuture<Suggestions> a(CommandContext<ICompletionProvider> paramCommandContext, SuggestionsBuilder paramSuggestionsBuilder);
/*     */   
/*     */   Set<ResourceKey<World>> p();
/*     */   
/*     */   IRegistryCustom q();
/*     */   
/*     */   boolean hasPermission(int paramInt);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ICompletionProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */