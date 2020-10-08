/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JsonRegistry
/*     */ {
/*     */   public static class a<E, T extends LootSerializerType<E>>
/*     */   {
/*     */     private final IRegistry<T> a;
/*     */     private final String b;
/*     */     private final String c;
/*     */     private final Function<E, T> d;
/*     */     @Nullable
/*     */     private Pair<T, JsonRegistry.b<? extends E>> e;
/*     */     
/*     */     private a(IRegistry<T> var0, String var1, String var2, Function<E, T> var3) {
/*  33 */       this.a = var0;
/*  34 */       this.b = var1;
/*  35 */       this.c = var2;
/*  36 */       this.d = var3;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object a() {
/*  45 */       return new JsonRegistry.c<>(this.a, this.b, this.c, this.d, this.e);
/*     */     }
/*     */   }
/*     */   
/*     */   public static <E, T extends LootSerializerType<E>> a<E, T> a(IRegistry<T> var0, String var1, String var2, Function<E, T> var3) {
/*  50 */     return new a<>(var0, var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   static class c<E, T extends LootSerializerType<E>>
/*     */     implements JsonDeserializer<E>, JsonSerializer<E>
/*     */   {
/*     */     private final IRegistry<T> a;
/*     */     private final String b;
/*     */     private final String c;
/*     */     private final Function<E, T> d;
/*     */     @Nullable
/*     */     private final Pair<T, JsonRegistry.b<? extends E>> e;
/*     */     
/*     */     private c(IRegistry<T> var0, String var1, String var2, Function<E, T> var3, @Nullable Pair<T, JsonRegistry.b<? extends E>> var4) {
/*  65 */       this.a = var0;
/*  66 */       this.b = var1;
/*  67 */       this.c = var2;
/*  68 */       this.d = var3;
/*  69 */       this.e = var4;
/*     */     }
/*     */ 
/*     */     
/*     */     public E deserialize(JsonElement var0, Type var1, JsonDeserializationContext var2) throws JsonParseException {
/*  74 */       if (var0.isJsonObject()) {
/*  75 */         JsonObject var3 = ChatDeserializer.m(var0, this.b);
/*  76 */         MinecraftKey var4 = new MinecraftKey(ChatDeserializer.h(var3, this.c));
/*     */         
/*  78 */         LootSerializerType<E> lootSerializerType = (LootSerializerType)this.a.get(var4);
/*  79 */         if (lootSerializerType == null) {
/*  80 */           throw new JsonSyntaxException("Unknown type '" + var4 + "'");
/*     */         }
/*     */         
/*  83 */         return lootSerializerType.a().a(var3, var2);
/*     */       } 
/*     */       
/*  86 */       if (this.e == null) {
/*  87 */         throw new UnsupportedOperationException("Object " + var0 + " can't be deserialized");
/*     */       }
/*  89 */       return ((JsonRegistry.b<E>)this.e.getSecond()).a(var0, var2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonElement serialize(E var0, Type var1, JsonSerializationContext var2) {
/*  95 */       LootSerializerType<E> lootSerializerType = (LootSerializerType)this.d.apply(var0);
/*  96 */       if (this.e != null && this.e.getFirst() == lootSerializerType) {
/*  97 */         return ((JsonRegistry.b<E>)this.e.getSecond()).a(var0, var2);
/*     */       }
/*     */       
/* 100 */       if (lootSerializerType == null) {
/* 101 */         throw new JsonSyntaxException("Unknown type: " + var0);
/*     */       }
/*     */       
/* 104 */       JsonObject var4 = new JsonObject();
/* 105 */       var4.addProperty(this.c, this.a.getKey((T)lootSerializerType).toString());
/* 106 */       lootSerializerType.a().a(var4, var0, var2);
/* 107 */       return (JsonElement)var4;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface b<T> {
/*     */     JsonElement a(T param1T, JsonSerializationContext param1JsonSerializationContext);
/*     */     
/*     */     T a(JsonElement param1JsonElement, JsonDeserializationContext param1JsonDeserializationContext);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\JsonRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */