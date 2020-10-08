/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerFishingRodHooked
/*    */   extends CriterionTriggerAbstract<CriterionTriggerFishingRodHooked.a>
/*    */ {
/* 16 */   private static final MinecraftKey a = new MinecraftKey("fishing_rod_hooked");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 20 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 25 */     CriterionConditionItem var3 = CriterionConditionItem.a(var0.get("rod"));
/* 26 */     CriterionConditionEntity.b var4 = CriterionConditionEntity.b.a(var0, "entity", var2);
/* 27 */     CriterionConditionItem var5 = CriterionConditionItem.a(var0.get("item"));
/* 28 */     return new a(var1, var3, var4, var5);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, ItemStack var1, EntityFishingHook var2, Collection<ItemStack> var3) {
/* 32 */     LootTableInfo var4 = CriterionConditionEntity.b(var0, (var2.k() != null) ? var2.k() : var2);
/* 33 */     a(var0, var3 -> var3.a(var0, var1, var2));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionItem a;
/*    */     private final CriterionConditionEntity.b b;
/*    */     private final CriterionConditionItem c;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionItem var1, CriterionConditionEntity.b var2, CriterionConditionItem var3) {
/* 42 */       super(CriterionTriggerFishingRodHooked.b(), var0);
/* 43 */       this.a = var1;
/* 44 */       this.b = var2;
/* 45 */       this.c = var3;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionItem var0, CriterionConditionEntity var1, CriterionConditionItem var2) {
/* 49 */       return new a(CriterionConditionEntity.b.a, var0, CriterionConditionEntity.b.a(var1), var2);
/*    */     }
/*    */     
/*    */     public boolean a(ItemStack var0, LootTableInfo var1, Collection<ItemStack> var2) {
/* 53 */       if (!this.a.a(var0)) {
/* 54 */         return false;
/*    */       }
/* 56 */       if (!this.b.a(var1)) {
/* 57 */         return false;
/*    */       }
/* 59 */       if (this.c != CriterionConditionItem.a) {
/* 60 */         boolean var3 = false;
/*    */         
/* 62 */         Entity var4 = var1.<Entity>getContextParameter(LootContextParameters.THIS_ENTITY);
/* 63 */         if (var4 instanceof EntityItem) {
/* 64 */           EntityItem var5 = (EntityItem)var4;
/* 65 */           if (this.c.a(var5.getItemStack())) {
/* 66 */             var3 = true;
/*    */           }
/*    */         } 
/* 69 */         for (ItemStack var6 : var2) {
/* 70 */           if (this.c.a(var6)) {
/* 71 */             var3 = true;
/*    */             break;
/*    */           } 
/*    */         } 
/* 75 */         if (!var3) {
/* 76 */           return false;
/*    */         }
/*    */       } 
/* 79 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 84 */       JsonObject var1 = super.a(var0);
/*    */       
/* 86 */       var1.add("rod", this.a.a());
/* 87 */       var1.add("entity", this.b.a(var0));
/* 88 */       var1.add("item", this.c.a());
/*    */       
/* 90 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerFishingRodHooked.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */