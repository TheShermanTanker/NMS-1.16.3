/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootSelectorEmpty
/*    */   extends LootSelectorEntry
/*    */ {
/*    */   private LootSelectorEmpty(int var0, int var1, LootItemCondition[] var2, LootItemFunction[] var3) {
/* 14 */     super(var0, var1, var2, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootEntryType a() {
/* 19 */     return LootEntries.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Consumer<ItemStack> var0, LootTableInfo var1) {}
/*    */ 
/*    */   
/*    */   public static LootSelectorEntry.a<?> b() {
/* 27 */     return a(LootSelectorEmpty::new);
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootSelectorEntry.e<LootSelectorEmpty> {
/*    */     public LootSelectorEmpty b(JsonObject var0, JsonDeserializationContext var1, int var2, int var3, LootItemCondition[] var4, LootItemFunction[] var5) {
/* 33 */       return new LootSelectorEmpty(var2, var3, var4, var5);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootSelectorEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */