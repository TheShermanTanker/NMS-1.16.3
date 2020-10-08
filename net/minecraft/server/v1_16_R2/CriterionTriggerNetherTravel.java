/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerNetherTravel
/*    */   extends CriterionTriggerAbstract<CriterionTriggerNetherTravel.a>
/*    */ {
/* 10 */   private static final MinecraftKey a = new MinecraftKey("nether_travel");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 14 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 19 */     CriterionConditionLocation var3 = CriterionConditionLocation.a(var0.get("entered"));
/* 20 */     CriterionConditionLocation var4 = CriterionConditionLocation.a(var0.get("exited"));
/* 21 */     CriterionConditionDistance var5 = CriterionConditionDistance.a(var0.get("distance"));
/* 22 */     return new a(var1, var3, var4, var5);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, Vec3D var1) {
/* 26 */     a(var0, var2 -> var2.a(var0.getWorldServer(), var1, var0.locX(), var0.locY(), var0.locZ()));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionLocation a;
/*    */     private final CriterionConditionLocation b;
/*    */     private final CriterionConditionDistance c;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionLocation var1, CriterionConditionLocation var2, CriterionConditionDistance var3) {
/* 35 */       super(CriterionTriggerNetherTravel.b(), var0);
/* 36 */       this.a = var1;
/* 37 */       this.b = var2;
/* 38 */       this.c = var3;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionDistance var0) {
/* 42 */       return new a(CriterionConditionEntity.b.a, CriterionConditionLocation.a, CriterionConditionLocation.a, var0);
/*    */     }
/*    */     
/*    */     public boolean a(WorldServer var0, Vec3D var1, double var2, double var4, double var6) {
/* 46 */       if (!this.a.a(var0, var1.x, var1.y, var1.z)) {
/* 47 */         return false;
/*    */       }
/* 49 */       if (!this.b.a(var0, var2, var4, var6)) {
/* 50 */         return false;
/*    */       }
/* 52 */       if (!this.c.a(var1.x, var1.y, var1.z, var2, var4, var6)) {
/* 53 */         return false;
/*    */       }
/* 55 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 60 */       JsonObject var1 = super.a(var0);
/*    */       
/* 62 */       var1.add("entered", this.a.a());
/* 63 */       var1.add("exited", this.b.a());
/* 64 */       var1.add("distance", this.c.a());
/*    */       
/* 66 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerNetherTravel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */