/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootSelectorDynamic
/*    */   extends LootSelectorEntry
/*    */ {
/*    */   private final MinecraftKey g;
/*    */   
/*    */   private LootSelectorDynamic(MinecraftKey var0, int var1, int var2, LootItemCondition[] var3, LootItemFunction[] var4) {
/* 19 */     super(var1, var2, var3, var4);
/* 20 */     this.g = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootEntryType a() {
/* 25 */     return LootEntries.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Consumer<ItemStack> var0, LootTableInfo var1) {
/* 30 */     var1.a(this.g, var0);
/*    */   }
/*    */   
/*    */   public static LootSelectorEntry.a<?> a(MinecraftKey var0) {
/* 34 */     return a((var1, var2, var3, var4) -> new LootSelectorDynamic(var0, var1, var2, var3, var4));
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootSelectorEntry.e<LootSelectorDynamic> {
/*    */     public void a(JsonObject var0, LootSelectorDynamic var1, JsonSerializationContext var2) {
/* 40 */       super.a(var0, var1, var2);
/* 41 */       var0.addProperty("name", LootSelectorDynamic.a(var1).toString());
/*    */     }
/*    */ 
/*    */     
/*    */     protected LootSelectorDynamic b(JsonObject var0, JsonDeserializationContext var1, int var2, int var3, LootItemCondition[] var4, LootItemFunction[] var5) {
/* 46 */       MinecraftKey var6 = new MinecraftKey(ChatDeserializer.h(var0, "name"));
/* 47 */       return new LootSelectorDynamic(var6, var2, var3, var4, var5);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootSelectorDynamic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */