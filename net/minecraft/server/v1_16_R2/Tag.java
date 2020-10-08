/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Tag<T>
/*     */ {
/*     */   static <T> Codec<Tag<T>> a(Supplier<Tags<T>> var0) {
/*  24 */     return MinecraftKey.a.flatXmap(var1 -> (DataResult)Optional.ofNullable(((Tags)var0.get()).a(var1)).map(DataResult::success).orElseGet(()), var1 -> (DataResult)Optional.<MinecraftKey>ofNullable(((Tags)var0.get()).a(var1)).map(DataResult::success).orElseGet(()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default T a(Random var0) {
/*  35 */     List<T> var1 = getTagged();
/*  36 */     return var1.get(var0.nextInt(var1.size()));
/*     */   }
/*     */   
/*     */   public static class b {
/*     */     private final Tag.d a;
/*     */     private final String b;
/*     */     
/*     */     private b(Tag.d var0, String var1) {
/*  44 */       this.a = var0;
/*  45 */       this.b = var1;
/*     */     }
/*     */     
/*     */     public Tag.d a() {
/*  49 */       return this.a;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*  58 */       return this.a.toString() + " (from " + this.b + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class a {
/*  63 */     private final List<Tag.b> a = Lists.newArrayList();
/*     */     
/*     */     public static a a() {
/*  66 */       return new a();
/*     */     }
/*     */     
/*     */     public a a(Tag.b var0) {
/*  70 */       this.a.add(var0);
/*  71 */       return this;
/*     */     }
/*     */     
/*     */     public a a(Tag.d var0, String var1) {
/*  75 */       return a(new Tag.b(var0, var1));
/*     */     }
/*     */     
/*     */     public a a(MinecraftKey var0, String var1) {
/*  79 */       return a(new Tag.c(var0), var1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public a c(MinecraftKey var0, String var1) {
/*  87 */       return a(new Tag.h(var0), var1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> Optional<Tag<T>> a(Function<MinecraftKey, Tag<T>> var0, Function<MinecraftKey, T> var1) {
/*  95 */       ImmutableSet.Builder<T> var2 = ImmutableSet.builder();
/*  96 */       for (Tag.b var4 : this.a) {
/*  97 */         if (!var4.a().a(var0, var1, var2::add)) {
/*  98 */           return Optional.empty();
/*     */         }
/*     */       } 
/* 101 */       return Optional.of(Tag.b((Set<T>)var2.build()));
/*     */     }
/*     */     
/*     */     public Stream<Tag.b> b() {
/* 105 */       return this.a.stream();
/*     */     }
/*     */     
/*     */     public <T> Stream<Tag.b> b(Function<MinecraftKey, Tag<T>> var0, Function<MinecraftKey, T> var1) {
/* 109 */       return b().filter(var2 -> !var2.a().a(var0, var1, ()));
/*     */     }
/*     */     
/*     */     public a a(JsonObject var0, String var1) {
/* 113 */       JsonArray var2 = ChatDeserializer.u(var0, "values");
/*     */ 
/*     */       
/* 116 */       List<Tag.d> var3 = Lists.newArrayList();
/* 117 */       for (JsonElement var5 : var2) {
/* 118 */         var3.add(a(var5));
/*     */       }
/*     */       
/* 121 */       if (ChatDeserializer.a(var0, "replace", false)) {
/* 122 */         this.a.clear();
/*     */       }
/*     */       
/* 125 */       var3.forEach(var1 -> this.a.add(new Tag.b(var1, var0)));
/* 126 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     private static Tag.d a(JsonElement var0) {
/*     */       String var1;
/*     */       boolean var2;
/* 133 */       if (var0.isJsonObject()) {
/* 134 */         JsonObject jsonObject = var0.getAsJsonObject();
/* 135 */         var1 = ChatDeserializer.h(jsonObject, "id");
/* 136 */         var2 = ChatDeserializer.a(jsonObject, "required", true);
/*     */       } else {
/* 138 */         var1 = ChatDeserializer.a(var0, "id");
/* 139 */         var2 = true;
/*     */       } 
/*     */       
/* 142 */       if (var1.startsWith("#")) {
/* 143 */         MinecraftKey minecraftKey = new MinecraftKey(var1.substring(1));
/* 144 */         return var2 ? new Tag.h(minecraftKey) : new Tag.g(minecraftKey);
/*     */       } 
/* 146 */       MinecraftKey var3 = new MinecraftKey(var1);
/* 147 */       return var2 ? new Tag.c(var3) : new Tag.f(var3);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject c() {
/* 152 */       JsonObject var0 = new JsonObject();
/* 153 */       JsonArray var1 = new JsonArray();
/*     */       
/* 155 */       for (Tag.b var3 : this.a) {
/* 156 */         var3.a().a(var1);
/*     */       }
/*     */       
/* 159 */       var0.addProperty("replace", Boolean.valueOf(false));
/* 160 */       var0.add("values", (JsonElement)var1);
/*     */       
/* 162 */       return var0;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface d {
/*     */     <T> boolean a(Function<MinecraftKey, Tag<T>> param1Function, Function<MinecraftKey, T> param1Function1, Consumer<T> param1Consumer);
/*     */     
/*     */     void a(JsonArray param1JsonArray);
/*     */   }
/*     */   
/*     */   public static class c implements d {
/*     */     private final MinecraftKey a;
/*     */     
/*     */     public c(MinecraftKey var0) {
/* 176 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> boolean a(Function<MinecraftKey, Tag<T>> var0, Function<MinecraftKey, T> var1, Consumer<T> var2) {
/* 181 */       T var3 = var1.apply(this.a);
/* 182 */       if (var3 == null) {
/* 183 */         return false;
/*     */       }
/* 185 */       var2.accept(var3);
/* 186 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonArray var0) {
/* 191 */       var0.add(this.a.toString());
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 196 */       return this.a.toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class f implements d {
/*     */     private final MinecraftKey a;
/*     */     
/*     */     public f(MinecraftKey var0) {
/* 204 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> boolean a(Function<MinecraftKey, Tag<T>> var0, Function<MinecraftKey, T> var1, Consumer<T> var2) {
/* 209 */       T var3 = var1.apply(this.a);
/* 210 */       if (var3 != null) {
/* 211 */         var2.accept(var3);
/*     */       }
/* 213 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonArray var0) {
/* 218 */       JsonObject var1 = new JsonObject();
/* 219 */       var1.addProperty("id", this.a.toString());
/* 220 */       var1.addProperty("required", Boolean.valueOf(false));
/* 221 */       var0.add((JsonElement)var1);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 226 */       return this.a.toString() + "?";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class h implements d {
/*     */     private final MinecraftKey a;
/*     */     
/*     */     public h(MinecraftKey var0) {
/* 234 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> boolean a(Function<MinecraftKey, Tag<T>> var0, Function<MinecraftKey, T> var1, Consumer<T> var2) {
/* 239 */       Tag<T> var3 = var0.apply(this.a);
/* 240 */       if (var3 == null) {
/* 241 */         return false;
/*     */       }
/* 243 */       var3.getTagged().forEach(var2);
/* 244 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonArray var0) {
/* 249 */       var0.add("#" + this.a);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 254 */       return "#" + this.a;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class g implements d {
/*     */     private final MinecraftKey a;
/*     */     
/*     */     public g(MinecraftKey var0) {
/* 262 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public <T> boolean a(Function<MinecraftKey, Tag<T>> var0, Function<MinecraftKey, T> var1, Consumer<T> var2) {
/* 267 */       Tag<T> var3 = var0.apply(this.a);
/* 268 */       if (var3 != null) {
/* 269 */         var3.getTagged().forEach(var2);
/*     */       }
/* 271 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonArray var0) {
/* 276 */       JsonObject var1 = new JsonObject();
/* 277 */       var1.addProperty("id", "#" + this.a);
/* 278 */       var1.addProperty("required", Boolean.valueOf(false));
/* 279 */       var0.add((JsonElement)var1);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 284 */       return "#" + this.a + "?";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static <T> Tag<T> b(Set<T> var0) {
/* 293 */     return TagSet.a(var0);
/*     */   }
/*     */   
/*     */   boolean isTagged(T paramT);
/*     */   
/*     */   List<T> getTagged();
/*     */   
/*     */   public static interface e<T> extends Tag<T> {
/*     */     MinecraftKey a();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Tag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */