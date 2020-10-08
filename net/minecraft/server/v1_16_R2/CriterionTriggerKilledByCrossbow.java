/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerKilledByCrossbow
/*    */   extends CriterionTriggerAbstract<CriterionTriggerKilledByCrossbow.a>
/*    */ {
/* 18 */   private static final MinecraftKey a = new MinecraftKey("killed_by_crossbow");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 22 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 27 */     CriterionConditionEntity.b[] var3 = CriterionConditionEntity.b.b(var0, "victims", var2);
/* 28 */     CriterionConditionValue.IntegerRange var4 = CriterionConditionValue.IntegerRange.a(var0.get("unique_entity_types"));
/* 29 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, Collection<Entity> var1) {
/* 33 */     List<LootTableInfo> var2 = Lists.newArrayList();
/* 34 */     Set<EntityTypes<?>> var3 = Sets.newHashSet();
/* 35 */     for (Entity var5 : var1) {
/* 36 */       var3.add(var5.getEntityType());
/* 37 */       var2.add(CriterionConditionEntity.b(var0, var5));
/*    */     } 
/*    */     
/* 40 */     a(var0, var2 -> var2.a(var0, var1.size()));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionEntity.b[] a;
/*    */     private final CriterionConditionValue.IntegerRange b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionEntity.b[] var1, CriterionConditionValue.IntegerRange var2) {
/* 48 */       super(CriterionTriggerKilledByCrossbow.b(), var0);
/* 49 */       this.a = var1;
/* 50 */       this.b = var2;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionEntity.a... var0) {
/* 54 */       CriterionConditionEntity.b[] var1 = new CriterionConditionEntity.b[var0.length];
/* 55 */       for (int var2 = 0; var2 < var0.length; var2++) {
/* 56 */         CriterionConditionEntity.a var3 = var0[var2];
/* 57 */         var1[var2] = CriterionConditionEntity.b.a(var3.b());
/*    */       } 
/* 59 */       return new a(CriterionConditionEntity.b.a, var1, CriterionConditionValue.IntegerRange.e);
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionValue.IntegerRange var0) {
/* 63 */       CriterionConditionEntity.b[] var1 = new CriterionConditionEntity.b[0];
/* 64 */       return new a(CriterionConditionEntity.b.a, var1, var0);
/*    */     }
/*    */     
/*    */     public boolean a(Collection<LootTableInfo> var0, int var1) {
/* 68 */       if (this.a.length > 0) {
/* 69 */         List<LootTableInfo> var2 = Lists.newArrayList(var0);
/* 70 */         for (CriterionConditionEntity.b var6 : this.a) {
/* 71 */           boolean var7 = false;
/* 72 */           for (Iterator<LootTableInfo> var8 = var2.iterator(); var8.hasNext(); ) {
/* 73 */             LootTableInfo var9 = var8.next();
/* 74 */             if (var6.a(var9)) {
/* 75 */               var8.remove();
/* 76 */               var7 = true;
/*    */               
/*    */               break;
/*    */             } 
/*    */           } 
/* 81 */           if (!var7) {
/* 82 */             return false;
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 87 */       return this.b.d(var1);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 92 */       JsonObject var1 = super.a(var0);
/*    */       
/* 94 */       var1.add("victims", CriterionConditionEntity.b.a(this.a, var0));
/* 95 */       var1.add("unique_entity_types", this.b.d());
/*    */       
/* 97 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerKilledByCrossbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */