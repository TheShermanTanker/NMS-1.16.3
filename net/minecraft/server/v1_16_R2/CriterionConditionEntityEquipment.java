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
/*     */ 
/*     */ public class CriterionConditionEntityEquipment
/*     */ {
/*  16 */   public static final CriterionConditionEntityEquipment a = new CriterionConditionEntityEquipment(CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a);
/*  17 */   public static final CriterionConditionEntityEquipment b = new CriterionConditionEntityEquipment(CriterionConditionItem.a.a().a(Items.WHITE_BANNER).a(Raid.s().getTag()).b(), CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a, CriterionConditionItem.a);
/*     */   
/*     */   private final CriterionConditionItem c;
/*     */   private final CriterionConditionItem d;
/*     */   private final CriterionConditionItem e;
/*     */   private final CriterionConditionItem f;
/*     */   private final CriterionConditionItem g;
/*     */   private final CriterionConditionItem h;
/*     */   
/*     */   public CriterionConditionEntityEquipment(CriterionConditionItem var0, CriterionConditionItem var1, CriterionConditionItem var2, CriterionConditionItem var3, CriterionConditionItem var4, CriterionConditionItem var5) {
/*  27 */     this.c = var0;
/*  28 */     this.d = var1;
/*  29 */     this.e = var2;
/*  30 */     this.f = var3;
/*  31 */     this.g = var4;
/*  32 */     this.h = var5;
/*     */   }
/*     */   
/*     */   public boolean a(@Nullable Entity var0) {
/*  36 */     if (this == a) {
/*  37 */       return true;
/*     */     }
/*  39 */     if (!(var0 instanceof EntityLiving)) {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     EntityLiving var1 = (EntityLiving)var0;
/*  44 */     if (!this.c.a(var1.getEquipment(EnumItemSlot.HEAD))) {
/*  45 */       return false;
/*     */     }
/*  47 */     if (!this.d.a(var1.getEquipment(EnumItemSlot.CHEST))) {
/*  48 */       return false;
/*     */     }
/*  50 */     if (!this.e.a(var1.getEquipment(EnumItemSlot.LEGS))) {
/*  51 */       return false;
/*     */     }
/*  53 */     if (!this.f.a(var1.getEquipment(EnumItemSlot.FEET))) {
/*  54 */       return false;
/*     */     }
/*  56 */     if (!this.g.a(var1.getEquipment(EnumItemSlot.MAINHAND))) {
/*  57 */       return false;
/*     */     }
/*  59 */     if (!this.h.a(var1.getEquipment(EnumItemSlot.OFFHAND))) {
/*  60 */       return false;
/*     */     }
/*     */     
/*  63 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionEntityEquipment a(@Nullable JsonElement var0) {
/*  67 */     if (var0 == null || var0.isJsonNull()) {
/*  68 */       return a;
/*     */     }
/*     */     
/*  71 */     JsonObject var1 = ChatDeserializer.m(var0, "equipment");
/*  72 */     CriterionConditionItem var2 = CriterionConditionItem.a(var1.get("head"));
/*  73 */     CriterionConditionItem var3 = CriterionConditionItem.a(var1.get("chest"));
/*  74 */     CriterionConditionItem var4 = CriterionConditionItem.a(var1.get("legs"));
/*  75 */     CriterionConditionItem var5 = CriterionConditionItem.a(var1.get("feet"));
/*  76 */     CriterionConditionItem var6 = CriterionConditionItem.a(var1.get("mainhand"));
/*  77 */     CriterionConditionItem var7 = CriterionConditionItem.a(var1.get("offhand"));
/*  78 */     return new CriterionConditionEntityEquipment(var2, var3, var4, var5, var6, var7);
/*     */   }
/*     */   
/*     */   public JsonElement a() {
/*  82 */     if (this == a) {
/*  83 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/*  86 */     JsonObject var0 = new JsonObject();
/*  87 */     var0.add("head", this.c.a());
/*  88 */     var0.add("chest", this.d.a());
/*  89 */     var0.add("legs", this.e.a());
/*  90 */     var0.add("feet", this.f.a());
/*  91 */     var0.add("mainhand", this.g.a());
/*  92 */     var0.add("offhand", this.h.a());
/*  93 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   public static class a {
/*  97 */     private CriterionConditionItem a = CriterionConditionItem.a;
/*  98 */     private CriterionConditionItem b = CriterionConditionItem.a;
/*  99 */     private CriterionConditionItem c = CriterionConditionItem.a;
/* 100 */     private CriterionConditionItem d = CriterionConditionItem.a;
/* 101 */     private CriterionConditionItem e = CriterionConditionItem.a;
/* 102 */     private CriterionConditionItem f = CriterionConditionItem.a;
/*     */     
/*     */     public static a a() {
/* 105 */       return new a();
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionItem var0) {
/* 109 */       this.a = var0;
/* 110 */       return this;
/*     */     }
/*     */     
/*     */     public a b(CriterionConditionItem var0) {
/* 114 */       this.b = var0;
/* 115 */       return this;
/*     */     }
/*     */     
/*     */     public a c(CriterionConditionItem var0) {
/* 119 */       this.c = var0;
/* 120 */       return this;
/*     */     }
/*     */     
/*     */     public a d(CriterionConditionItem var0) {
/* 124 */       this.d = var0;
/* 125 */       return this;
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
/*     */     public CriterionConditionEntityEquipment b() {
/* 139 */       return new CriterionConditionEntityEquipment(this.a, this.b, this.c, this.d, this.e, this.f);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionEntityEquipment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */