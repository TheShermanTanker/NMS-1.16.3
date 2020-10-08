/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.brigadier.ImmutableStringReader;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.BiFunction;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentParserItemStack
/*     */ {
/*     */   public static final DynamicCommandExceptionType b;
/*     */   private static final BiFunction<SuggestionsBuilder, Tags<Item>, CompletableFuture<Suggestions>> c;
/*  26 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.item.tag.disallowed")); static {
/*  27 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.item.id.invalid", new Object[] { var0 }));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  32 */     c = ((var0, var1) -> var0.buildFuture());
/*     */   }
/*     */   private final StringReader d;
/*     */   private final boolean e;
/*  36 */   private final Map<IBlockState<?>, Comparable<?>> f = Maps.newHashMap();
/*     */   private Item g;
/*     */   @Nullable
/*     */   private NBTTagCompound h;
/*  40 */   private MinecraftKey i = new MinecraftKey("");
/*     */   private int j;
/*  42 */   private BiFunction<SuggestionsBuilder, Tags<Item>, CompletableFuture<Suggestions>> k = c;
/*     */   
/*     */   public ArgumentParserItemStack(StringReader var0, boolean var1) {
/*  45 */     this.d = var0;
/*  46 */     this.e = var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item b() {
/*  54 */     return this.g;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound c() {
/*  59 */     return this.h;
/*     */   }
/*     */   
/*     */   public MinecraftKey d() {
/*  63 */     return this.i;
/*     */   }
/*     */   
/*     */   public void e() throws CommandSyntaxException {
/*  67 */     int var0 = this.d.getCursor();
/*  68 */     MinecraftKey var1 = MinecraftKey.a(this.d);
/*  69 */     this.g = (Item)IRegistry.ITEM.getOptional(var1).orElseThrow(() -> {
/*     */           this.d.setCursor(var0);
/*     */           return b.createWithContext((ImmutableStringReader)this.d, var1.toString());
/*     */         });
/*     */   }
/*     */   
/*     */   public void f() throws CommandSyntaxException {
/*  76 */     if (!this.e) {
/*  77 */       throw a.create();
/*     */     }
/*     */     
/*  80 */     this.k = this::c;
/*  81 */     this.d.expect('#');
/*  82 */     this.j = this.d.getCursor();
/*  83 */     this.i = MinecraftKey.a(this.d);
/*     */   }
/*     */   
/*     */   public void g() throws CommandSyntaxException {
/*  87 */     this.h = (new MojangsonParser(this.d)).f();
/*     */   }
/*     */   
/*     */   public ArgumentParserItemStack h() throws CommandSyntaxException {
/*  91 */     this.k = this::d;
/*  92 */     if (this.d.canRead() && this.d.peek() == '#') {
/*  93 */       f();
/*     */     } else {
/*  95 */       e();
/*  96 */       this.k = this::b;
/*     */     } 
/*  98 */     if (this.d.canRead() && this.d.peek() == '{') {
/*  99 */       this.k = c;
/* 100 */       g();
/*     */     } 
/* 102 */     return this;
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> b(SuggestionsBuilder var0, Tags<Item> var1) {
/* 106 */     if (var0.getRemaining().isEmpty()) {
/* 107 */       var0.suggest(String.valueOf('{'));
/*     */     }
/* 109 */     return var0.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> c(SuggestionsBuilder var0, Tags<Item> var1) {
/* 113 */     return ICompletionProvider.a(var1.b(), var0.createOffset(this.j));
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> d(SuggestionsBuilder var0, Tags<Item> var1) {
/* 117 */     if (this.e) {
/* 118 */       ICompletionProvider.a(var1.b(), var0, String.valueOf('#'));
/*     */     }
/* 120 */     return ICompletionProvider.a(IRegistry.ITEM.keySet(), var0);
/*     */   }
/*     */   
/*     */   public CompletableFuture<Suggestions> a(SuggestionsBuilder var0, Tags<Item> var1) {
/* 124 */     return this.k.apply(var0.createOffset(this.d.getCursor()), var1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentParserItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */