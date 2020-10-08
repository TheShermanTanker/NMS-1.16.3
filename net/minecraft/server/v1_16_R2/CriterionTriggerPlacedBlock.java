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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerPlacedBlock
/*    */   extends CriterionTriggerAbstract<CriterionTriggerPlacedBlock.a>
/*    */ {
/* 18 */   private static final MinecraftKey a = new MinecraftKey("placed_block");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 22 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 27 */     Block var3 = a(var0);
/* 28 */     CriterionTriggerProperties var4 = CriterionTriggerProperties.a(var0.get("state"));
/* 29 */     if (var3 != null) {
/* 30 */       var4.a(var3.getStates(), var1 -> {
/*    */             throw new JsonSyntaxException("Block " + var0 + " has no property " + var1 + ":");
/*    */           });
/*    */     }
/* 34 */     CriterionConditionLocation var5 = CriterionConditionLocation.a(var0.get("location"));
/* 35 */     CriterionConditionItem var6 = CriterionConditionItem.a(var0.get("item"));
/*    */     
/* 37 */     return new a(var1, var3, var4, var5, var6);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private static Block a(JsonObject var0) {
/* 42 */     if (var0.has("block")) {
/* 43 */       MinecraftKey var1 = new MinecraftKey(ChatDeserializer.h(var0, "block"));
/* 44 */       return (Block)IRegistry.BLOCK.getOptional(var1).orElseThrow(() -> new JsonSyntaxException("Unknown block type '" + var0 + "'"));
/*    */     } 
/* 46 */     return null;
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, BlockPosition var1, ItemStack var2) {
/* 50 */     IBlockData var3 = var0.getWorldServer().getType(var1);
/* 51 */     a(var0, var4 -> var4.a(var0, var1, var2.getWorldServer(), var3));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final Block a;
/*    */     private final CriterionTriggerProperties b;
/*    */     private final CriterionConditionLocation c;
/*    */     private final CriterionConditionItem d;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, @Nullable Block var1, CriterionTriggerProperties var2, CriterionConditionLocation var3, CriterionConditionItem var4) {
/* 61 */       super(CriterionTriggerPlacedBlock.b(), var0);
/* 62 */       this.a = var1;
/* 63 */       this.b = var2;
/* 64 */       this.c = var3;
/* 65 */       this.d = var4;
/*    */     }
/*    */     
/*    */     public static a a(Block var0) {
/* 69 */       return new a(CriterionConditionEntity.b.a, var0, CriterionTriggerProperties.a, CriterionConditionLocation.a, CriterionConditionItem.a);
/*    */     }
/*    */     
/*    */     public boolean a(IBlockData var0, BlockPosition var1, WorldServer var2, ItemStack var3) {
/* 73 */       if (this.a != null && !var0.a(this.a)) {
/* 74 */         return false;
/*    */       }
/* 76 */       if (!this.b.a(var0)) {
/* 77 */         return false;
/*    */       }
/* 79 */       if (!this.c.a(var2, var1.getX(), var1.getY(), var1.getZ())) {
/* 80 */         return false;
/*    */       }
/* 82 */       if (!this.d.a(var3)) {
/* 83 */         return false;
/*    */       }
/* 85 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 90 */       JsonObject var1 = super.a(var0);
/*    */       
/* 92 */       if (this.a != null) {
/* 93 */         var1.addProperty("block", IRegistry.BLOCK.getKey(this.a).toString());
/*    */       }
/* 95 */       var1.add("state", this.b.a());
/* 96 */       var1.add("location", this.c.a());
/* 97 */       var1.add("item", this.d.a());
/*    */       
/* 99 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerPlacedBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */