/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class in
/*     */   implements il
/*     */ {
/*     */   private final Block a;
/*  17 */   private final List<b> b = Lists.newArrayList();
/*     */   
/*     */   private in(Block var0) {
/*  20 */     this.a = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block a() {
/*  25 */     return this.a;
/*     */   }
/*     */   
/*     */   public static in a(Block var0) {
/*  29 */     return new in(var0);
/*     */   }
/*     */   
/*     */   public in a(List<ir> var0) {
/*  33 */     this.b.add(new b(var0));
/*  34 */     return this;
/*     */   }
/*     */   
/*     */   public in a(ir var0) {
/*  38 */     return a((List<ir>)ImmutableList.of(var0));
/*     */   }
/*     */   
/*     */   public in a(im var0, List<ir> var1) {
/*  42 */     this.b.add(new a(var0, var1));
/*  43 */     return this;
/*     */   }
/*     */   
/*     */   public in a(im var0, ir... var1) {
/*  47 */     return a(var0, (List<ir>)ImmutableList.copyOf((Object[])var1));
/*     */   }
/*     */   
/*     */   public in a(im var0, ir var1) {
/*  51 */     return a(var0, (List<ir>)ImmutableList.of(var1));
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonElement get() {
/*  56 */     BlockStateList<Block, IBlockData> var0 = this.a.getStates();
/*  57 */     this.b.forEach(var1 -> var1.a(var0));
/*     */     
/*  59 */     JsonArray var1 = new JsonArray();
/*  60 */     this.b.stream().map(b::get).forEach(var1::add);
/*     */     
/*  62 */     JsonObject var2 = new JsonObject();
/*  63 */     var2.add("multipart", (JsonElement)var1);
/*  64 */     return (JsonElement)var2;
/*     */   }
/*     */   
/*     */   static class b implements Supplier<JsonElement> {
/*     */     private final List<ir> a;
/*     */     
/*     */     private b(List<ir> var0) {
/*  71 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(BlockStateList<?, ?> var0) {}
/*     */ 
/*     */     
/*     */     public void a(JsonObject var0) {}
/*     */ 
/*     */     
/*     */     public JsonElement get() {
/*  82 */       JsonObject var0 = new JsonObject();
/*  83 */       a(var0);
/*  84 */       var0.add("apply", ir.a(this.a));
/*  85 */       return (JsonElement)var0;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a extends b {
/*     */     private final im a;
/*     */     
/*     */     private a(im var0, List<ir> var1) {
/*  93 */       super(var1);
/*  94 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(BlockStateList<?, ?> var0) {
/*  99 */       this.a.a(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject var0) {
/* 104 */       var0.add("when", this.a.get());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\in.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */