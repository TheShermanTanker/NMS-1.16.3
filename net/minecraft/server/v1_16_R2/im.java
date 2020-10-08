/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ public interface im
/*     */   extends Supplier<JsonElement>
/*     */ {
/*     */   void a(BlockStateList<?, ?> paramBlockStateList);
/*     */   
/*     */   public enum b
/*     */   {
/*  21 */     a("AND"),
/*  22 */     b("OR");
/*     */     
/*     */     private final String c;
/*     */ 
/*     */     
/*     */     b(String var2) {
/*  28 */       this.c = var2;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class a implements im {
/*     */     private final im.b a;
/*     */     private final List<im> b;
/*     */     
/*     */     private a(im.b var0, List<im> var1) {
/*  37 */       this.a = var0;
/*  38 */       this.b = var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(BlockStateList<?, ?> var0) {
/*  43 */       this.b.forEach(var1 -> var1.a(var0));
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement get() {
/*  48 */       JsonArray var0 = new JsonArray();
/*  49 */       this.b.stream().map(Supplier::get).forEach(var0::add);
/*     */       
/*  51 */       JsonObject var1 = new JsonObject();
/*  52 */       var1.add(im.b.a(this.a), (JsonElement)var0);
/*  53 */       return (JsonElement)var1;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class c implements im {
/*  58 */     private final Map<IBlockState<?>, String> a = Maps.newHashMap();
/*     */     
/*     */     private static <T extends Comparable<T>> String a(IBlockState<T> var0, Stream<T> var1) {
/*  61 */       return var1.<CharSequence>map(var0::a).collect(Collectors.joining("|"));
/*     */     }
/*     */     
/*     */     private static <T extends Comparable<T>> String c(IBlockState<T> var0, T var1, T[] var2) {
/*  65 */       return a(var0, Stream.concat(Stream.of(var1), Stream.of(var2)));
/*     */     }
/*     */     
/*     */     private <T extends Comparable<T>> void a(IBlockState<T> var0, String var1) {
/*  69 */       String var2 = this.a.put(var0, var1);
/*  70 */       if (var2 != null) {
/*  71 */         throw new IllegalStateException("Tried to replace " + var0 + " value from " + var2 + " to " + var1);
/*     */       }
/*     */     }
/*     */     
/*     */     public final <T extends Comparable<T>> c a(IBlockState<T> var0, T var1) {
/*  76 */       a(var0, var0.a(var1));
/*  77 */       return this;
/*     */     }
/*     */     
/*     */     @SafeVarargs
/*     */     public final <T extends Comparable<T>> c a(IBlockState<T> var0, T var1, T... var2) {
/*  82 */       a(var0, c(var0, var1, var2));
/*  83 */       return this;
/*     */     }
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
/*     */     public JsonElement get() {
/* 100 */       JsonObject var0 = new JsonObject();
/* 101 */       this.a.forEach((var1, var2) -> var0.addProperty(var1.getName(), var2));
/* 102 */       return (JsonElement)var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(BlockStateList<?, ?> var0) {
/* 107 */       List<IBlockState<?>> var1 = (List<IBlockState<?>>)this.a.keySet().stream().filter(var1 -> (var0.a(var1.getName()) != var1)).collect(Collectors.toList());
/* 108 */       if (!var1.isEmpty()) {
/* 109 */         throw new IllegalStateException("Properties " + var1 + " are missing from " + var0);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   static c a() {
/* 115 */     return new c();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static im b(im... var0) {
/* 123 */     return new a(b.b, Arrays.asList(var0));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\im.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */