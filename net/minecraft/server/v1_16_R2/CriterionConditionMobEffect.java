/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionConditionMobEffect
/*     */ {
/*  21 */   public static final CriterionConditionMobEffect a = new CriterionConditionMobEffect(Collections.emptyMap());
/*     */   
/*     */   private final Map<MobEffectList, a> b;
/*     */   
/*     */   public CriterionConditionMobEffect(Map<MobEffectList, a> var0) {
/*  26 */     this.b = var0;
/*     */   }
/*     */   
/*     */   public static CriterionConditionMobEffect a() {
/*  30 */     return new CriterionConditionMobEffect(Maps.newLinkedHashMap());
/*     */   }
/*     */   
/*     */   public CriterionConditionMobEffect a(MobEffectList var0) {
/*  34 */     this.b.put(var0, new a());
/*  35 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(Entity var0) {
/*  44 */     if (this == a) {
/*  45 */       return true;
/*     */     }
/*  47 */     if (var0 instanceof EntityLiving) {
/*  48 */       return a(((EntityLiving)var0).dh());
/*     */     }
/*  50 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(EntityLiving var0) {
/*  54 */     if (this == a) {
/*  55 */       return true;
/*     */     }
/*  57 */     return a(var0.dh());
/*     */   }
/*     */   
/*     */   public boolean a(Map<MobEffectList, MobEffect> var0) {
/*  61 */     if (this == a) {
/*  62 */       return true;
/*     */     }
/*     */     
/*  65 */     for (Map.Entry<MobEffectList, a> var2 : this.b.entrySet()) {
/*  66 */       MobEffect var3 = var0.get(var2.getKey());
/*  67 */       if (!((a)var2.getValue()).a(var3)) {
/*  68 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  72 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionMobEffect a(@Nullable JsonElement var0) {
/*  76 */     if (var0 == null || var0.isJsonNull()) {
/*  77 */       return a;
/*     */     }
/*  79 */     JsonObject var1 = ChatDeserializer.m(var0, "effects");
/*  80 */     Map<MobEffectList, a> var2 = Maps.newLinkedHashMap();
/*     */     
/*  82 */     for (Map.Entry<String, JsonElement> var4 : (Iterable<Map.Entry<String, JsonElement>>)var1.entrySet()) {
/*  83 */       MinecraftKey var5 = new MinecraftKey(var4.getKey());
/*  84 */       MobEffectList var6 = (MobEffectList)IRegistry.MOB_EFFECT.getOptional(var5).orElseThrow(() -> new JsonSyntaxException("Unknown effect '" + var0 + "'"));
/*  85 */       a var7 = a.a(ChatDeserializer.m(var4.getValue(), var4.getKey()));
/*  86 */       var2.put(var6, var7);
/*     */     } 
/*     */     
/*  89 */     return new CriterionConditionMobEffect(var2);
/*     */   }
/*     */   
/*     */   public JsonElement b() {
/*  93 */     if (this == a) {
/*  94 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/*  97 */     JsonObject var0 = new JsonObject();
/*     */     
/*  99 */     for (Map.Entry<MobEffectList, a> var2 : this.b.entrySet()) {
/* 100 */       var0.add(IRegistry.MOB_EFFECT.getKey(var2.getKey()).toString(), ((a)var2.getValue()).a());
/*     */     }
/*     */     
/* 103 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   public static class a {
/*     */     private final CriterionConditionValue.IntegerRange a;
/*     */     private final CriterionConditionValue.IntegerRange b;
/*     */     @Nullable
/*     */     private final Boolean c;
/*     */     @Nullable
/*     */     private final Boolean d;
/*     */     
/*     */     public a(CriterionConditionValue.IntegerRange var0, CriterionConditionValue.IntegerRange var1, @Nullable Boolean var2, @Nullable Boolean var3) {
/* 115 */       this.a = var0;
/* 116 */       this.b = var1;
/* 117 */       this.c = var2;
/* 118 */       this.d = var3;
/*     */     }
/*     */     
/*     */     public a() {
/* 122 */       this(CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, null, null);
/*     */     }
/*     */     
/*     */     public boolean a(@Nullable MobEffect var0) {
/* 126 */       if (var0 == null) {
/* 127 */         return false;
/*     */       }
/* 129 */       if (!this.a.d(var0.getAmplifier())) {
/* 130 */         return false;
/*     */       }
/* 132 */       if (!this.b.d(var0.getDuration())) {
/* 133 */         return false;
/*     */       }
/* 135 */       if (this.c != null && this.c.booleanValue() != var0.isAmbient()) {
/* 136 */         return false;
/*     */       }
/* 138 */       if (this.d != null && this.d.booleanValue() != var0.isShowParticles()) {
/* 139 */         return false;
/*     */       }
/* 141 */       return true;
/*     */     }
/*     */     
/*     */     public JsonElement a() {
/* 145 */       JsonObject var0 = new JsonObject();
/*     */       
/* 147 */       var0.add("amplifier", this.a.d());
/* 148 */       var0.add("duration", this.b.d());
/* 149 */       var0.addProperty("ambient", this.c);
/* 150 */       var0.addProperty("visible", this.d);
/*     */       
/* 152 */       return (JsonElement)var0;
/*     */     }
/*     */     
/*     */     public static a a(JsonObject var0) {
/* 156 */       CriterionConditionValue.IntegerRange var1 = CriterionConditionValue.IntegerRange.a(var0.get("amplifier"));
/* 157 */       CriterionConditionValue.IntegerRange var2 = CriterionConditionValue.IntegerRange.a(var0.get("duration"));
/* 158 */       Boolean var3 = var0.has("ambient") ? Boolean.valueOf(ChatDeserializer.j(var0, "ambient")) : null;
/* 159 */       Boolean var4 = var0.has("visible") ? Boolean.valueOf(ChatDeserializer.j(var0, "visible")) : null;
/* 160 */       return new a(var1, var2, var3, var4);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionMobEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */