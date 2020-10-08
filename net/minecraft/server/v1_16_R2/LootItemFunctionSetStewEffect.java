/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
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
/*     */ public class LootItemFunctionSetStewEffect
/*     */   extends LootItemFunctionConditional
/*     */ {
/*     */   private final Map<MobEffectList, LootValueBounds> a;
/*     */   
/*     */   private LootItemFunctionSetStewEffect(LootItemCondition[] var0, Map<MobEffectList, LootValueBounds> var1) {
/*  32 */     super(var0);
/*  33 */     this.a = (Map<MobEffectList, LootValueBounds>)ImmutableMap.copyOf(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemFunctionType b() {
/*  38 */     return LootItemFunctions.l;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/*  43 */     if (var0.getItem() != Items.SUSPICIOUS_STEW || this.a.isEmpty()) {
/*  44 */       return var0;
/*     */     }
/*     */     
/*  47 */     Random var2 = var1.a();
/*  48 */     int var3 = var2.nextInt(this.a.size());
/*  49 */     Map.Entry<MobEffectList, LootValueBounds> var4 = (Map.Entry<MobEffectList, LootValueBounds>)Iterables.get(this.a.entrySet(), var3);
/*     */     
/*  51 */     MobEffectList var5 = var4.getKey();
/*  52 */     int var6 = ((LootValueBounds)var4.getValue()).a(var2);
/*  53 */     if (!var5.isInstant()) {
/*  54 */       var6 *= 20;
/*     */     }
/*     */     
/*  57 */     ItemSuspiciousStew.a(var0, var5, var6);
/*  58 */     return var0;
/*     */   }
/*     */   
/*     */   public static class a extends LootItemFunctionConditional.a<a> {
/*  62 */     private final Map<MobEffectList, LootValueBounds> a = Maps.newHashMap();
/*     */ 
/*     */     
/*     */     protected a d() {
/*  66 */       return this;
/*     */     }
/*     */     
/*     */     public a a(MobEffectList var0, LootValueBounds var1) {
/*  70 */       this.a.put(var0, var1);
/*  71 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunction b() {
/*  76 */       return new LootItemFunctionSetStewEffect(g(), this.a);
/*     */     }
/*     */   }
/*     */   
/*     */   public static a c() {
/*  81 */     return new a();
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends LootItemFunctionConditional.c<LootItemFunctionSetStewEffect> {
/*     */     public void a(JsonObject var0, LootItemFunctionSetStewEffect var1, JsonSerializationContext var2) {
/*  87 */       super.a(var0, var1, var2);
/*     */       
/*  89 */       if (!LootItemFunctionSetStewEffect.a(var1).isEmpty()) {
/*  90 */         JsonArray var3 = new JsonArray();
/*  91 */         for (MobEffectList var5 : LootItemFunctionSetStewEffect.a(var1).keySet()) {
/*  92 */           JsonObject var6 = new JsonObject();
/*  93 */           MinecraftKey var7 = IRegistry.MOB_EFFECT.getKey(var5);
/*  94 */           if (var7 == null) {
/*  95 */             throw new IllegalArgumentException("Don't know how to serialize mob effect " + var5);
/*     */           }
/*  97 */           var6.add("type", (JsonElement)new JsonPrimitive(var7.toString()));
/*  98 */           var6.add("duration", var2.serialize(LootItemFunctionSetStewEffect.a(var1).get(var5)));
/*  99 */           var3.add((JsonElement)var6);
/*     */         } 
/* 101 */         var0.add("effects", (JsonElement)var3);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunctionSetStewEffect b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 107 */       Map<MobEffectList, LootValueBounds> var3 = Maps.newHashMap();
/* 108 */       if (var0.has("effects")) {
/* 109 */         JsonArray var4 = ChatDeserializer.u(var0, "effects");
/* 110 */         for (JsonElement var6 : var4) {
/* 111 */           String var7 = ChatDeserializer.h(var6.getAsJsonObject(), "type");
/*     */           
/* 113 */           MobEffectList var8 = (MobEffectList)IRegistry.MOB_EFFECT.getOptional(new MinecraftKey(var7)).orElseThrow(() -> new JsonSyntaxException("Unknown mob effect '" + var0 + "'"));
/* 114 */           LootValueBounds var9 = ChatDeserializer.<LootValueBounds>a(var6.getAsJsonObject(), "duration", var1, LootValueBounds.class);
/* 115 */           var3.put(var8, var9);
/*     */         } 
/*     */       } 
/*     */       
/* 119 */       return new LootItemFunctionSetStewEffect(var2, var3);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionSetStewEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */