/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerChanneledLightning
/*    */   extends CriterionTriggerAbstract<CriterionTriggerChanneledLightning.a>
/*    */ {
/* 15 */   private static final MinecraftKey a = new MinecraftKey("channeled_lightning");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 19 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 24 */     CriterionConditionEntity.b[] var3 = CriterionConditionEntity.b.b(var0, "victims", var2);
/* 25 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, Collection<? extends Entity> var1) {
/* 29 */     List<LootTableInfo> var2 = (List<LootTableInfo>)var1.stream().map(var1 -> CriterionConditionEntity.b(var0, var1)).collect(Collectors.toList());
/* 30 */     a(var0, var1 -> var1.a(var0));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionEntity.b[] a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionEntity.b[] var1) {
/* 37 */       super(CriterionTriggerChanneledLightning.b(), var0);
/* 38 */       this.a = var1;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionEntity... var0) {
/* 42 */       return new a(CriterionConditionEntity.b.a, (CriterionConditionEntity.b[])Stream.<CriterionConditionEntity>of(var0).map(CriterionConditionEntity.b::a).toArray(var0 -> new CriterionConditionEntity.b[var0]));
/*    */     }
/*    */     
/*    */     public boolean a(Collection<? extends LootTableInfo> var0) {
/* 46 */       for (CriterionConditionEntity.b var4 : this.a) {
/* 47 */         boolean var5 = false;
/* 48 */         for (LootTableInfo var7 : var0) {
/* 49 */           if (var4.a(var7)) {
/* 50 */             var5 = true;
/*    */             break;
/*    */           } 
/*    */         } 
/* 54 */         if (!var5) {
/* 55 */           return false;
/*    */         }
/*    */       } 
/* 58 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 63 */       JsonObject var1 = super.a(var0);
/*    */       
/* 65 */       var1.add("victims", CriterionConditionEntity.b.a(this.a, var0));
/*    */       
/* 67 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerChanneledLightning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */