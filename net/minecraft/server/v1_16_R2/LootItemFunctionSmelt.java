/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionSmelt
/*    */   extends LootItemFunctionConditional
/*    */ {
/* 17 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private LootItemFunctionSmelt(LootItemCondition[] var0) {
/* 20 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 25 */     return LootItemFunctions.f;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 30 */     if (var0.isEmpty()) {
/* 31 */       return var0;
/*    */     }
/*    */     
/* 34 */     Optional<FurnaceRecipe> var2 = var1.getWorld().getCraftingManager().craft(Recipes.SMELTING, new InventorySubcontainer(new ItemStack[] { var0 }, ), var1.getWorld());
/* 35 */     if (var2.isPresent()) {
/* 36 */       ItemStack var3 = ((FurnaceRecipe)var2.get()).getResult();
/*    */       
/* 38 */       if (!var3.isEmpty()) {
/* 39 */         ItemStack var4 = var3.cloneItemStack();
/* 40 */         var4.setCount(var0.getCount());
/* 41 */         return var4;
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     LOGGER.warn("Couldn't smelt {} because there is no smelting recipe", var0);
/* 46 */     return var0;
/*    */   }
/*    */   
/*    */   public static LootItemFunctionConditional.a<?> c() {
/* 50 */     return a(LootItemFunctionSmelt::new);
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootItemFunctionConditional.c<LootItemFunctionSmelt> {
/*    */     public LootItemFunctionSmelt b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 56 */       return new LootItemFunctionSmelt(var2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionSmelt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */