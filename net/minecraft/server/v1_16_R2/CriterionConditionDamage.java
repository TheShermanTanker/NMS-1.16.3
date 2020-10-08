/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionConditionDamage
/*     */ {
/*  13 */   public static final CriterionConditionDamage a = a.a().b();
/*     */   
/*     */   private final CriterionConditionValue.FloatRange b;
/*     */   private final CriterionConditionValue.FloatRange c;
/*     */   private final CriterionConditionEntity d;
/*     */   private final Boolean e;
/*     */   private final CriterionConditionDamageSource f;
/*     */   
/*     */   public CriterionConditionDamage() {
/*  22 */     this.b = CriterionConditionValue.FloatRange.e;
/*  23 */     this.c = CriterionConditionValue.FloatRange.e;
/*  24 */     this.d = CriterionConditionEntity.a;
/*  25 */     this.e = null;
/*  26 */     this.f = CriterionConditionDamageSource.a;
/*     */   }
/*     */   
/*     */   public CriterionConditionDamage(CriterionConditionValue.FloatRange var0, CriterionConditionValue.FloatRange var1, CriterionConditionEntity var2, @Nullable Boolean var3, CriterionConditionDamageSource var4) {
/*  30 */     this.b = var0;
/*  31 */     this.c = var1;
/*  32 */     this.d = var2;
/*  33 */     this.e = var3;
/*  34 */     this.f = var4;
/*     */   }
/*     */   
/*     */   public boolean a(EntityPlayer var0, DamageSource var1, float var2, float var3, boolean var4) {
/*  38 */     if (this == a) {
/*  39 */       return true;
/*     */     }
/*  41 */     if (!this.b.d(var2)) {
/*  42 */       return false;
/*     */     }
/*  44 */     if (!this.c.d(var3)) {
/*  45 */       return false;
/*     */     }
/*  47 */     if (!this.d.a(var0, var1.getEntity())) {
/*  48 */       return false;
/*     */     }
/*  50 */     if (this.e != null && this.e.booleanValue() != var4) {
/*  51 */       return false;
/*     */     }
/*  53 */     if (!this.f.a(var0, var1)) {
/*  54 */       return false;
/*     */     }
/*  56 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionDamage a(@Nullable JsonElement var0) {
/*  60 */     if (var0 == null || var0.isJsonNull()) {
/*  61 */       return a;
/*     */     }
/*  63 */     JsonObject var1 = ChatDeserializer.m(var0, "damage");
/*  64 */     CriterionConditionValue.FloatRange var2 = CriterionConditionValue.FloatRange.a(var1.get("dealt"));
/*  65 */     CriterionConditionValue.FloatRange var3 = CriterionConditionValue.FloatRange.a(var1.get("taken"));
/*  66 */     Boolean var4 = var1.has("blocked") ? Boolean.valueOf(ChatDeserializer.j(var1, "blocked")) : null;
/*  67 */     CriterionConditionEntity var5 = CriterionConditionEntity.a(var1.get("source_entity"));
/*  68 */     CriterionConditionDamageSource var6 = CriterionConditionDamageSource.a(var1.get("type"));
/*  69 */     return new CriterionConditionDamage(var2, var3, var5, var4, var6);
/*     */   }
/*     */   
/*     */   public JsonElement a() {
/*  73 */     if (this == a) {
/*  74 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/*  77 */     JsonObject var0 = new JsonObject();
/*     */     
/*  79 */     var0.add("dealt", this.b.d());
/*  80 */     var0.add("taken", this.c.d());
/*  81 */     var0.add("source_entity", this.d.a());
/*  82 */     var0.add("type", this.f.a());
/*     */     
/*  84 */     if (this.e != null) {
/*  85 */       var0.addProperty("blocked", this.e);
/*     */     }
/*     */     
/*  88 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   public static class a {
/*  92 */     private CriterionConditionValue.FloatRange a = CriterionConditionValue.FloatRange.e;
/*  93 */     private CriterionConditionValue.FloatRange b = CriterionConditionValue.FloatRange.e;
/*  94 */     private CriterionConditionEntity c = CriterionConditionEntity.a;
/*     */     private Boolean d;
/*  96 */     private CriterionConditionDamageSource e = CriterionConditionDamageSource.a;
/*     */     
/*     */     public static a a() {
/*  99 */       return new a();
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
/*     */ 
/*     */     
/*     */     public a a(Boolean var0) {
/* 118 */       this.d = var0;
/* 119 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public a a(CriterionConditionDamageSource.a var0) {
/* 128 */       this.e = var0.b();
/* 129 */       return this;
/*     */     }
/*     */     
/*     */     public CriterionConditionDamage b() {
/* 133 */       return new CriterionConditionDamage(this.a, this.b, this.c, this.d, this.e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */