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
/*     */ 
/*     */ 
/*     */ public class CriterionConditionDamageSource
/*     */ {
/*  15 */   public static final CriterionConditionDamageSource a = a.a().b();
/*     */   
/*     */   private final Boolean b;
/*     */   private final Boolean c;
/*     */   private final Boolean d;
/*     */   private final Boolean e;
/*     */   private final Boolean f;
/*     */   private final Boolean g;
/*     */   private final Boolean h;
/*     */   private final Boolean i;
/*     */   private final CriterionConditionEntity j;
/*     */   private final CriterionConditionEntity k;
/*     */   
/*     */   public CriterionConditionDamageSource(@Nullable Boolean var0, @Nullable Boolean var1, @Nullable Boolean var2, @Nullable Boolean var3, @Nullable Boolean var4, @Nullable Boolean var5, @Nullable Boolean var6, @Nullable Boolean var7, CriterionConditionEntity var8, CriterionConditionEntity var9) {
/*  29 */     this.b = var0;
/*  30 */     this.c = var1;
/*  31 */     this.d = var2;
/*  32 */     this.e = var3;
/*  33 */     this.f = var4;
/*  34 */     this.g = var5;
/*  35 */     this.h = var6;
/*  36 */     this.i = var7;
/*  37 */     this.j = var8;
/*  38 */     this.k = var9;
/*     */   }
/*     */   
/*     */   public boolean a(EntityPlayer var0, DamageSource var1) {
/*  42 */     return a(var0.getWorldServer(), var0.getPositionVector(), var1);
/*     */   }
/*     */   
/*     */   public boolean a(WorldServer var0, Vec3D var1, DamageSource var2) {
/*  46 */     if (this == a) {
/*  47 */       return true;
/*     */     }
/*  49 */     if (this.b != null && this.b.booleanValue() != var2.b()) {
/*  50 */       return false;
/*     */     }
/*  52 */     if (this.c != null && this.c.booleanValue() != var2.isExplosion()) {
/*  53 */       return false;
/*     */     }
/*  55 */     if (this.d != null && this.d.booleanValue() != var2.ignoresArmor()) {
/*  56 */       return false;
/*     */     }
/*  58 */     if (this.e != null && this.e.booleanValue() != var2.ignoresInvulnerability()) {
/*  59 */       return false;
/*     */     }
/*  61 */     if (this.f != null && this.f.booleanValue() != var2.isStarvation()) {
/*  62 */       return false;
/*     */     }
/*  64 */     if (this.g != null && this.g.booleanValue() != var2.isFire()) {
/*  65 */       return false;
/*     */     }
/*  67 */     if (this.h != null && this.h.booleanValue() != var2.isMagic()) {
/*  68 */       return false;
/*     */     }
/*  70 */     if (this.i != null && this.i.booleanValue() != ((var2 == DamageSource.LIGHTNING))) {
/*  71 */       return false;
/*     */     }
/*  73 */     if (!this.j.a(var0, var1, var2.j())) {
/*  74 */       return false;
/*     */     }
/*  76 */     if (!this.k.a(var0, var1, var2.getEntity())) {
/*  77 */       return false;
/*     */     }
/*  79 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionDamageSource a(@Nullable JsonElement var0) {
/*  83 */     if (var0 == null || var0.isJsonNull()) {
/*  84 */       return a;
/*     */     }
/*  86 */     JsonObject var1 = ChatDeserializer.m(var0, "damage type");
/*  87 */     Boolean var2 = a(var1, "is_projectile");
/*  88 */     Boolean var3 = a(var1, "is_explosion");
/*  89 */     Boolean var4 = a(var1, "bypasses_armor");
/*  90 */     Boolean var5 = a(var1, "bypasses_invulnerability");
/*  91 */     Boolean var6 = a(var1, "bypasses_magic");
/*  92 */     Boolean var7 = a(var1, "is_fire");
/*  93 */     Boolean var8 = a(var1, "is_magic");
/*  94 */     Boolean var9 = a(var1, "is_lightning");
/*  95 */     CriterionConditionEntity var10 = CriterionConditionEntity.a(var1.get("direct_entity"));
/*  96 */     CriterionConditionEntity var11 = CriterionConditionEntity.a(var1.get("source_entity"));
/*  97 */     return new CriterionConditionDamageSource(var2, var3, var4, var5, var6, var7, var8, var9, var10, var11);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static Boolean a(JsonObject var0, String var1) {
/* 102 */     return var0.has(var1) ? Boolean.valueOf(ChatDeserializer.j(var0, var1)) : null;
/*     */   }
/*     */   
/*     */   public JsonElement a() {
/* 106 */     if (this == a) {
/* 107 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/* 110 */     JsonObject var0 = new JsonObject();
/*     */     
/* 112 */     a(var0, "is_projectile", this.b);
/* 113 */     a(var0, "is_explosion", this.c);
/* 114 */     a(var0, "bypasses_armor", this.d);
/* 115 */     a(var0, "bypasses_invulnerability", this.e);
/* 116 */     a(var0, "bypasses_magic", this.f);
/* 117 */     a(var0, "is_fire", this.g);
/* 118 */     a(var0, "is_magic", this.h);
/* 119 */     a(var0, "is_lightning", this.i);
/* 120 */     var0.add("direct_entity", this.j.a());
/* 121 */     var0.add("source_entity", this.k.a());
/*     */     
/* 123 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   private void a(JsonObject var0, String var1, @Nullable Boolean var2) {
/* 127 */     if (var2 != null)
/* 128 */       var0.addProperty(var1, var2); 
/*     */   }
/*     */   
/*     */   public static class a
/*     */   {
/*     */     private Boolean a;
/*     */     private Boolean b;
/*     */     private Boolean c;
/*     */     private Boolean d;
/*     */     private Boolean e;
/*     */     private Boolean f;
/*     */     private Boolean g;
/*     */     private Boolean h;
/* 141 */     private CriterionConditionEntity i = CriterionConditionEntity.a;
/* 142 */     private CriterionConditionEntity j = CriterionConditionEntity.a;
/*     */     
/*     */     public static a a() {
/* 145 */       return new a();
/*     */     }
/*     */     
/*     */     public a a(Boolean var0) {
/* 149 */       this.a = var0;
/* 150 */       return this;
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
/*     */     public a h(Boolean var0) {
/* 184 */       this.h = var0;
/* 185 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public a a(CriterionConditionEntity.a var0) {
/* 194 */       this.i = var0.b();
/* 195 */       return this;
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
/*     */     public CriterionConditionDamageSource b() {
/* 209 */       return new CriterionConditionDamageSource(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */