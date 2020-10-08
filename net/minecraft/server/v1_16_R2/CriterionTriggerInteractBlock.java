/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerInteractBlock
/*    */   extends CriterionTriggerAbstract<CriterionTriggerInteractBlock.a>
/*    */ {
/* 12 */   private static final MinecraftKey a = new MinecraftKey("item_used_on_block");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 16 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 21 */     CriterionConditionLocation var3 = CriterionConditionLocation.a(var0.get("location"));
/* 22 */     CriterionConditionItem var4 = CriterionConditionItem.a(var0.get("item"));
/*    */     
/* 24 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, BlockPosition var1, ItemStack var2) {
/* 28 */     IBlockData var3 = var0.getWorldServer().getType(var1);
/*    */     
/* 30 */     a(var0, var4 -> var4.a(var0, var1.getWorldServer(), var2, var3));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionLocation a;
/*    */     private final CriterionConditionItem b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionLocation var1, CriterionConditionItem var2) {
/* 38 */       super(CriterionTriggerInteractBlock.b(), var0);
/* 39 */       this.a = var1;
/* 40 */       this.b = var2;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionLocation.a var0, CriterionConditionItem.a var1) {
/* 44 */       return new a(CriterionConditionEntity.b.a, var0.b(), var1.b());
/*    */     }
/*    */     
/*    */     public boolean a(IBlockData var0, WorldServer var1, BlockPosition var2, ItemStack var3) {
/* 48 */       if (!this.a.a(var1, var2.getX() + 0.5D, var2.getY() + 0.5D, var2.getZ() + 0.5D)) {
/* 49 */         return false;
/*    */       }
/* 51 */       return this.b.a(var3);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 56 */       JsonObject var1 = super.a(var0);
/*    */       
/* 58 */       var1.add("location", this.a.a());
/* 59 */       var1.add("item", this.b.a());
/*    */       
/* 61 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerInteractBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */