/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerPlayerGeneratesContainerLoot
/*    */   extends CriterionTriggerAbstract<CriterionTriggerPlayerGeneratesContainerLoot.a>
/*    */ {
/*  9 */   private static final MinecraftKey a = new MinecraftKey("player_generates_container_loot");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 13 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   protected a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 18 */     MinecraftKey var3 = new MinecraftKey(ChatDeserializer.h(var0, "loot_table"));
/*    */     
/* 20 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, MinecraftKey var1) {
/* 24 */     a(var0, var1 -> var1.b(var0));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final MinecraftKey a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, MinecraftKey var1) {
/* 31 */       super(CriterionTriggerPlayerGeneratesContainerLoot.b(), var0);
/* 32 */       this.a = var1;
/*    */     }
/*    */     
/*    */     public static a a(MinecraftKey var0) {
/* 36 */       return new a(CriterionConditionEntity.b.a, var0);
/*    */     }
/*    */     
/*    */     public boolean b(MinecraftKey var0) {
/* 40 */       return this.a.equals(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 45 */       JsonObject var1 = super.a(var0);
/* 46 */       var1.addProperty("loot_table", this.a.toString());
/* 47 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerPlayerGeneratesContainerLoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */