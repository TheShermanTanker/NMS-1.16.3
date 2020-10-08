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
/*    */ 
/*    */ public class CriterionTriggerBeeNestDestroyed
/*    */   extends CriterionTriggerAbstract<CriterionTriggerBeeNestDestroyed.a>
/*    */ {
/* 15 */   private static final MinecraftKey a = new MinecraftKey("bee_nest_destroyed");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 19 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 24 */     Block var3 = a(var0);
/* 25 */     CriterionConditionItem var4 = CriterionConditionItem.a(var0.get("item"));
/* 26 */     CriterionConditionValue.IntegerRange var5 = CriterionConditionValue.IntegerRange.a(var0.get("num_bees_inside"));
/*    */     
/* 28 */     return new a(var1, var3, var4, var5);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private static Block a(JsonObject var0) {
/* 33 */     if (var0.has("block")) {
/* 34 */       MinecraftKey var1 = new MinecraftKey(ChatDeserializer.h(var0, "block"));
/* 35 */       return (Block)IRegistry.BLOCK.getOptional(var1).orElseThrow(() -> new JsonSyntaxException("Unknown block type '" + var0 + "'"));
/*    */     } 
/* 37 */     return null;
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, Block var1, ItemStack var2, int var3) {
/* 41 */     a(var0, var3 -> var3.a(var0, var1, var2));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     @Nullable
/*    */     private final Block a;
/*    */     private final CriterionConditionItem b;
/*    */     private final CriterionConditionValue.IntegerRange c;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, @Nullable Block var1, CriterionConditionItem var2, CriterionConditionValue.IntegerRange var3) {
/* 51 */       super(CriterionTriggerBeeNestDestroyed.b(), var0);
/* 52 */       this.a = var1;
/* 53 */       this.b = var2;
/* 54 */       this.c = var3;
/*    */     }
/*    */     
/*    */     public static a a(Block var0, CriterionConditionItem.a var1, CriterionConditionValue.IntegerRange var2) {
/* 58 */       return new a(CriterionConditionEntity.b.a, var0, var1.b(), var2);
/*    */     }
/*    */     
/*    */     public boolean a(Block var0, ItemStack var1, int var2) {
/* 62 */       if (this.a != null && var0 != this.a) {
/* 63 */         return false;
/*    */       }
/* 65 */       if (!this.b.a(var1)) {
/* 66 */         return false;
/*    */       }
/* 68 */       return this.c.d(var2);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 73 */       JsonObject var1 = super.a(var0);
/*    */       
/* 75 */       if (this.a != null) {
/* 76 */         var1.addProperty("block", IRegistry.BLOCK.getKey(this.a).toString());
/*    */       }
/* 78 */       var1.add("item", this.b.a());
/* 79 */       var1.add("num_bees_inside", this.c.d());
/*    */       
/* 81 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerBeeNestDestroyed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */