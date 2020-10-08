/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionSetCount
/*    */   extends LootItemFunctionConditional
/*    */ {
/*    */   private final LootValue a;
/*    */   
/*    */   private LootItemFunctionSetCount(LootItemCondition[] var0, LootValue var1) {
/* 16 */     super(var0);
/* 17 */     this.a = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 22 */     return LootItemFunctions.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 27 */     var0.setCount(this.a.a(var1.a()));
/* 28 */     return var0;
/*    */   }
/*    */   
/*    */   public static LootItemFunctionConditional.a<?> a(LootValue var0) {
/* 32 */     return a(var1 -> new LootItemFunctionSetCount(var1, var0));
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootItemFunctionConditional.c<LootItemFunctionSetCount> {
/*    */     public void a(JsonObject var0, LootItemFunctionSetCount var1, JsonSerializationContext var2) {
/* 38 */       super.a(var0, var1, var2);
/*    */       
/* 40 */       var0.add("count", LootValueGenerators.a(LootItemFunctionSetCount.a(var1), var2));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionSetCount b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 45 */       LootValue var3 = LootValueGenerators.a(var0.get("count"), var1);
/* 46 */       return new LootItemFunctionSetCount(var2, var3);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionSetCount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */