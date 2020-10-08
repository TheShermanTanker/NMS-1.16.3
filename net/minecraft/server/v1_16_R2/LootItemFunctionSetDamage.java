/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionSetDamage
/*    */   extends LootItemFunctionConditional
/*    */ {
/* 16 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final LootValueBounds b;
/*    */   
/*    */   private LootItemFunctionSetDamage(LootItemCondition[] var0, LootValueBounds var1) {
/* 21 */     super(var0);
/* 22 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 27 */     return LootItemFunctions.h;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 32 */     if (var0.e()) {
/* 33 */       float var2 = 1.0F - this.b.b(var1.a());
/* 34 */       var0.setDamage(MathHelper.d(var2 * var0.h()));
/*    */     } else {
/* 36 */       LOGGER.warn("Couldn't set damage of loot item {}", var0);
/*    */     } 
/* 38 */     return var0;
/*    */   }
/*    */   
/*    */   public static LootItemFunctionConditional.a<?> a(LootValueBounds var0) {
/* 42 */     return a(var1 -> new LootItemFunctionSetDamage(var1, var0));
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootItemFunctionConditional.c<LootItemFunctionSetDamage> {
/*    */     public void a(JsonObject var0, LootItemFunctionSetDamage var1, JsonSerializationContext var2) {
/* 48 */       super.a(var0, var1, var2);
/*    */       
/* 50 */       var0.add("damage", var2.serialize(LootItemFunctionSetDamage.a(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionSetDamage b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 55 */       return new LootItemFunctionSetDamage(var2, ChatDeserializer.<LootValueBounds>a(var0, "damage", var1, LootValueBounds.class));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionSetDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */