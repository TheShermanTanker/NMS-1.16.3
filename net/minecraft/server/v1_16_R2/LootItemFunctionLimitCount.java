/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionLimitCount
/*    */   extends LootItemFunctionConditional
/*    */ {
/*    */   private final LootIntegerLimit a;
/*    */   
/*    */   private LootItemFunctionLimitCount(LootItemCondition[] var0, LootIntegerLimit var1) {
/* 16 */     super(var0);
/* 17 */     this.a = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 22 */     return LootItemFunctions.o;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 27 */     int var2 = this.a.applyAsInt(var0.getCount());
/* 28 */     var0.setCount(var2);
/* 29 */     return var0;
/*    */   }
/*    */   
/*    */   public static LootItemFunctionConditional.a<?> a(LootIntegerLimit var0) {
/* 33 */     return a(var1 -> new LootItemFunctionLimitCount(var1, var0));
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootItemFunctionConditional.c<LootItemFunctionLimitCount> {
/*    */     public void a(JsonObject var0, LootItemFunctionLimitCount var1, JsonSerializationContext var2) {
/* 39 */       super.a(var0, var1, var2);
/*    */       
/* 41 */       var0.add("limit", var2.serialize(LootItemFunctionLimitCount.a(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionLimitCount b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 46 */       LootIntegerLimit var3 = ChatDeserializer.<LootIntegerLimit>a(var0, "limit", var1, LootIntegerLimit.class);
/* 47 */       return new LootItemFunctionLimitCount(var2, var3);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionLimitCount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */