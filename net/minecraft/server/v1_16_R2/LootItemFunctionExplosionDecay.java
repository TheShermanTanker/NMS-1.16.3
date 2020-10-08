/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionExplosionDecay
/*    */   extends LootItemFunctionConditional
/*    */ {
/*    */   private LootItemFunctionExplosionDecay(LootItemCondition[] var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 19 */     return LootItemFunctions.r;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 24 */     Float var2 = var1.<Float>getContextParameter(LootContextParameters.EXPLOSION_RADIUS);
/*    */     
/* 26 */     if (var2 != null) {
/* 27 */       Random var3 = var1.a();
/*    */       
/* 29 */       float var4 = 1.0F / var2.floatValue();
/* 30 */       int var5 = var0.getCount();
/* 31 */       int var6 = 0;
/* 32 */       for (int var7 = 0; var7 < var5; var7++) {
/* 33 */         if (var3.nextFloat() <= var4) {
/* 34 */           var6++;
/*    */         }
/*    */       } 
/*    */       
/* 38 */       var0.setCount(var6);
/*    */     } 
/* 40 */     return var0;
/*    */   }
/*    */   
/*    */   public static LootItemFunctionConditional.a<?> c() {
/* 44 */     return a(LootItemFunctionExplosionDecay::new);
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootItemFunctionConditional.c<LootItemFunctionExplosionDecay> {
/*    */     public LootItemFunctionExplosionDecay b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 50 */       return new LootItemFunctionExplosionDecay(var2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionExplosionDecay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */