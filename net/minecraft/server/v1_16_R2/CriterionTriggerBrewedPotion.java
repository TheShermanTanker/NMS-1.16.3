/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerBrewedPotion
/*    */   extends CriterionTriggerAbstract<CriterionTriggerBrewedPotion.a>
/*    */ {
/* 14 */   private static final MinecraftKey a = new MinecraftKey("brewed_potion");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 18 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 23 */     PotionRegistry var3 = null;
/* 24 */     if (var0.has("potion")) {
/* 25 */       MinecraftKey var4 = new MinecraftKey(ChatDeserializer.h(var0, "potion"));
/* 26 */       var3 = (PotionRegistry)IRegistry.POTION.getOptional(var4).orElseThrow(() -> new JsonSyntaxException("Unknown potion '" + var0 + "'"));
/*    */     } 
/* 28 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, PotionRegistry var1) {
/* 32 */     a(var0, var1 -> var1.a(var0));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final PotionRegistry a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, @Nullable PotionRegistry var1) {
/* 39 */       super(CriterionTriggerBrewedPotion.b(), var0);
/* 40 */       this.a = var1;
/*    */     }
/*    */     
/*    */     public static a c() {
/* 44 */       return new a(CriterionConditionEntity.b.a, null);
/*    */     }
/*    */     
/*    */     public boolean a(PotionRegistry var0) {
/* 48 */       if (this.a != null && this.a != var0) {
/* 49 */         return false;
/*    */       }
/* 51 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 56 */       JsonObject var1 = super.a(var0);
/*    */       
/* 58 */       if (this.a != null) {
/* 59 */         var1.addProperty("potion", IRegistry.POTION.getKey(this.a).toString());
/*    */       }
/*    */       
/* 62 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerBrewedPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */