/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerProperties
/*     */ {
/*  24 */   public static final CriterionTriggerProperties a = new CriterionTriggerProperties((List<c>)ImmutableList.of());
/*     */   private final List<c> b;
/*     */   
/*     */   static abstract class c
/*     */   {
/*     */     public c(String var0) {
/*  30 */       this.a = var0;
/*     */     }
/*     */     private final String a;
/*     */     public <S extends IBlockDataHolder<?, S>> boolean a(BlockStateList<?, S> var0, S var1) {
/*  34 */       IBlockState<?> var2 = var0.a(this.a);
/*  35 */       if (var2 == null) {
/*  36 */         return false;
/*     */       }
/*     */       
/*  39 */       return a((IBlockDataHolder<?, ?>)var1, var2);
/*     */     }
/*     */     
/*     */     protected abstract <T extends Comparable<T>> boolean a(IBlockDataHolder<?, ?> param1IBlockDataHolder, IBlockState<T> param1IBlockState);
/*     */     
/*     */     public abstract JsonElement a();
/*     */     
/*     */     public String b() {
/*  47 */       return this.a;
/*     */     }
/*     */     
/*     */     public void a(BlockStateList<?, ?> var0, Consumer<String> var1) {
/*  51 */       IBlockState<?> var2 = var0.a(this.a);
/*  52 */       if (var2 == null)
/*  53 */         var1.accept(this.a); 
/*     */     }
/*     */   }
/*     */   
/*     */   static class b
/*     */     extends c {
/*     */     private final String a;
/*     */     
/*     */     public b(String var0, String var1) {
/*  62 */       super(var0);
/*  63 */       this.a = var1;
/*     */     }
/*     */ 
/*     */     
/*     */     protected <T extends Comparable<T>> boolean a(IBlockDataHolder<?, ?> var0, IBlockState<T> var1) {
/*  68 */       T var2 = var0.get(var1);
/*  69 */       Optional<T> var3 = var1.b(this.a);
/*  70 */       return (var3.isPresent() && var2.compareTo(var3.get()) == 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement a() {
/*  75 */       return (JsonElement)new JsonPrimitive(this.a);
/*     */     }
/*     */   }
/*     */   
/*     */   static class d
/*     */     extends c {
/*     */     @Nullable
/*     */     private final String a;
/*     */     @Nullable
/*     */     private final String b;
/*     */     
/*     */     public d(String var0, @Nullable String var1, @Nullable String var2) {
/*  87 */       super(var0);
/*  88 */       this.a = var1;
/*  89 */       this.b = var2;
/*     */     }
/*     */ 
/*     */     
/*     */     protected <T extends Comparable<T>> boolean a(IBlockDataHolder<?, ?> var0, IBlockState<T> var1) {
/*  94 */       T var2 = var0.get(var1);
/*     */       
/*  96 */       if (this.a != null) {
/*  97 */         Optional<T> var3 = var1.b(this.a);
/*  98 */         if (!var3.isPresent() || var2.compareTo(var3.get()) < 0) {
/*  99 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 103 */       if (this.b != null) {
/* 104 */         Optional<T> var3 = var1.b(this.b);
/* 105 */         if (!var3.isPresent() || var2.compareTo(var3.get()) > 0) {
/* 106 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 110 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement a() {
/* 115 */       JsonObject var0 = new JsonObject();
/* 116 */       if (this.a != null) {
/* 117 */         var0.addProperty("min", this.a);
/*     */       }
/* 119 */       if (this.b != null) {
/* 120 */         var0.addProperty("max", this.b);
/*     */       }
/* 122 */       return (JsonElement)var0;
/*     */     }
/*     */   }
/*     */   
/*     */   private static c a(String var0, JsonElement var1) {
/* 127 */     if (var1.isJsonPrimitive()) {
/* 128 */       String str = var1.getAsString();
/* 129 */       return new b(var0, str);
/*     */     } 
/*     */     
/* 132 */     JsonObject var2 = ChatDeserializer.m(var1, "value");
/* 133 */     String var3 = var2.has("min") ? b(var2.get("min")) : null;
/* 134 */     String var4 = var2.has("max") ? b(var2.get("max")) : null;
/* 135 */     return (var3 != null && var3.equals(var4)) ? new b(var0, var3) : new d(var0, var3, var4);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static String b(JsonElement var0) {
/* 140 */     if (var0.isJsonNull()) {
/* 141 */       return null;
/*     */     }
/* 143 */     return var0.getAsString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private CriterionTriggerProperties(List<c> var0) {
/* 149 */     this.b = (List<c>)ImmutableList.copyOf(var0);
/*     */   }
/*     */   
/*     */   public <S extends IBlockDataHolder<?, S>> boolean a(BlockStateList<?, S> var0, S var1) {
/* 153 */     for (c var3 : this.b) {
/* 154 */       if (!var3.a(var0, var1)) {
/* 155 */         return false;
/*     */       }
/*     */     } 
/* 158 */     return true;
/*     */   }
/*     */   
/*     */   public boolean a(IBlockData var0) {
/* 162 */     return a(var0.getBlock().getStates(), var0);
/*     */   }
/*     */   
/*     */   public boolean a(Fluid var0) {
/* 166 */     return a(var0.getType().g(), var0);
/*     */   }
/*     */   
/*     */   public void a(BlockStateList<?, ?> var0, Consumer<String> var1) {
/* 170 */     this.b.forEach(var2 -> var2.a(var0, var1));
/*     */   }
/*     */   
/*     */   public static CriterionTriggerProperties a(@Nullable JsonElement var0) {
/* 174 */     if (var0 == null || var0.isJsonNull()) {
/* 175 */       return a;
/*     */     }
/* 177 */     JsonObject var1 = ChatDeserializer.m(var0, "properties");
/*     */     
/* 179 */     List<c> var2 = Lists.newArrayList();
/* 180 */     for (Map.Entry<String, JsonElement> var4 : (Iterable<Map.Entry<String, JsonElement>>)var1.entrySet()) {
/* 181 */       var2.add(a(var4.getKey(), var4.getValue()));
/*     */     }
/*     */     
/* 184 */     return new CriterionTriggerProperties(var2);
/*     */   }
/*     */   
/*     */   public JsonElement a() {
/* 188 */     if (this == a) {
/* 189 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/* 192 */     JsonObject var0 = new JsonObject();
/*     */     
/* 194 */     if (!this.b.isEmpty()) {
/* 195 */       this.b.forEach(var1 -> var0.add(var1.b(), var1.a()));
/*     */     }
/*     */     
/* 198 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   public static class a {
/* 202 */     private final List<CriterionTriggerProperties.c> a = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static a a() {
/* 208 */       return new a();
/*     */     }
/*     */     
/*     */     public a a(IBlockState<?> var0, String var1) {
/* 212 */       this.a.add(new CriterionTriggerProperties.b(var0.getName(), var1));
/* 213 */       return this;
/*     */     }
/*     */     
/*     */     public a a(IBlockState<Integer> var0, int var1) {
/* 217 */       return a(var0, Integer.toString(var1));
/*     */     }
/*     */     
/*     */     public a a(IBlockState<Boolean> var0, boolean var1) {
/* 221 */       return a(var0, Boolean.toString(var1));
/*     */     }
/*     */     
/*     */     public <T extends Comparable<T> & INamable> a a(IBlockState<T> var0, T var1) {
/* 225 */       return a(var0, ((INamable)var1).getName());
/*     */     }
/*     */     
/*     */     public CriterionTriggerProperties b() {
/* 229 */       return new CriterionTriggerProperties(this.a);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */