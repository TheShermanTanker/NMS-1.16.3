/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerKilled
/*     */   extends CriterionTriggerAbstract<CriterionTriggerKilled.a>
/*     */ {
/*     */   private final MinecraftKey a;
/*     */   
/*     */   public CriterionTriggerKilled(MinecraftKey var0) {
/*  15 */     this.a = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  20 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/*  25 */     return new a(this.a, var1, CriterionConditionEntity.b.a(var0, "entity", var2), CriterionConditionDamageSource.a(var0.get("killing_blow")));
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer var0, Entity var1, DamageSource var2) {
/*  29 */     LootTableInfo var3 = CriterionConditionEntity.b(var0, var1);
/*  30 */     a(var0, var3 -> var3.a(var0, var1, var2));
/*     */   }
/*     */   
/*     */   public static class a extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionEntity.b a;
/*     */     private final CriterionConditionDamageSource b;
/*     */     
/*     */     public a(MinecraftKey var0, CriterionConditionEntity.b var1, CriterionConditionEntity.b var2, CriterionConditionDamageSource var3) {
/*  38 */       super(var0, var1);
/*  39 */       this.a = var2;
/*  40 */       this.b = var3;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static a a(CriterionConditionEntity.a var0) {
/*  48 */       return new a(CriterionTriggerKilled.a(CriterionTriggers.b), CriterionConditionEntity.b.a, CriterionConditionEntity.b.a(var0.b()), CriterionConditionDamageSource.a);
/*     */     }
/*     */     
/*     */     public static a c() {
/*  52 */       return new a(CriterionTriggerKilled.a(CriterionTriggers.b), CriterionConditionEntity.b.a, CriterionConditionEntity.b.a, CriterionConditionDamageSource.a);
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
/*     */     public static a a(CriterionConditionEntity.a var0, CriterionConditionDamageSource.a var1) {
/*  68 */       return new a(CriterionTriggerKilled.a(CriterionTriggers.b), CriterionConditionEntity.b.a, CriterionConditionEntity.b.a(var0.b()), var1.b());
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
/*     */     public static a d() {
/*  80 */       return new a(CriterionTriggerKilled.a(CriterionTriggers.c), CriterionConditionEntity.b.a, CriterionConditionEntity.b.a, CriterionConditionDamageSource.a);
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
/*     */     public boolean a(EntityPlayer var0, LootTableInfo var1, DamageSource var2) {
/* 100 */       if (!this.b.a(var0, var2)) {
/* 101 */         return false;
/*     */       }
/* 103 */       return this.a.a(var1);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject a(LootSerializationContext var0) {
/* 108 */       JsonObject var1 = super.a(var0);
/*     */       
/* 110 */       var1.add("entity", this.a.a(var0));
/* 111 */       var1.add("killing_blow", this.b.a());
/*     */       
/* 113 */       return var1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerKilled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */