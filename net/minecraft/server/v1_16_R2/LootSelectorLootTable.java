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
/*    */ 
/*    */ 
/*    */ public class LootSelectorLootTable
/*    */   extends LootSelectorEntry
/*    */ {
/*    */   private final MinecraftKey g;
/*    */   
/*    */   private LootSelectorLootTable(MinecraftKey var0, int var1, int var2, LootItemCondition[] var3, LootItemFunction[] var4) {
/* 21 */     super(var1, var2, var3, var4);
/* 22 */     this.g = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootEntryType a() {
/* 27 */     return LootEntries.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Consumer<ItemStack> var0, LootTableInfo var1) {
/* 32 */     LootTable var2 = var1.a(this.g);
/* 33 */     var2.populateLootDirect(var1, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(LootCollector var0) {
/* 38 */     if (var0.a(this.g)) {
/* 39 */       var0.a("Table " + this.g + " is recursively called");
/*    */       
/*    */       return;
/*    */     } 
/* 43 */     super.a(var0);
/*    */     
/* 45 */     LootTable var1 = var0.c(this.g);
/* 46 */     if (var1 == null) {
/* 47 */       var0.a("Unknown loot table called " + this.g);
/*    */     } else {
/* 49 */       var1.a(var0.a("->{" + this.g + "}", this.g));
/*    */     } 
/*    */   }
/*    */   
/*    */   public static LootSelectorEntry.a<?> a(MinecraftKey var0) {
/* 54 */     return a((var1, var2, var3, var4) -> new LootSelectorLootTable(var0, var1, var2, var3, var4));
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootSelectorEntry.e<LootSelectorLootTable> {
/*    */     public void a(JsonObject var0, LootSelectorLootTable var1, JsonSerializationContext var2) {
/* 60 */       super.a(var0, var1, var2);
/* 61 */       var0.addProperty("name", LootSelectorLootTable.a(var1).toString());
/*    */     }
/*    */ 
/*    */     
/*    */     protected LootSelectorLootTable b(JsonObject var0, JsonDeserializationContext var1, int var2, int var3, LootItemCondition[] var4, LootItemFunction[] var5) {
/* 66 */       MinecraftKey var6 = new MinecraftKey(ChatDeserializer.h(var0, "name"));
/* 67 */       return new LootSelectorLootTable(var6, var2, var3, var4, var5);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootSelectorLootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */