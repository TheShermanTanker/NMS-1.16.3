/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionSetTable
/*    */   extends LootItemFunctionConditional
/*    */ {
/*    */   private final MinecraftKey a;
/*    */   private final long b;
/*    */   
/*    */   private LootItemFunctionSetTable(LootItemCondition[] var0, MinecraftKey var1, long var2) {
/* 22 */     super(var0);
/* 23 */     this.a = var1;
/* 24 */     this.b = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 29 */     return LootItemFunctions.q;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 34 */     if (var0.isEmpty()) {
/* 35 */       return var0;
/*    */     }
/*    */     
/* 38 */     NBTTagCompound var2 = new NBTTagCompound();
/* 39 */     var2.setString("LootTable", this.a.toString());
/* 40 */     if (this.b != 0L) {
/* 41 */       var2.setLong("LootTableSeed", this.b);
/*    */     }
/* 43 */     var0.getOrCreateTag().set("BlockEntityTag", var2);
/* 44 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(LootCollector var0) {
/* 49 */     if (var0.a(this.a)) {
/* 50 */       var0.a("Table " + this.a + " is recursively called");
/*    */       
/*    */       return;
/*    */     } 
/* 54 */     super.a(var0);
/*    */     
/* 56 */     LootTable var1 = var0.c(this.a);
/* 57 */     if (var1 == null) {
/* 58 */       var0.a("Unknown loot table called " + this.a);
/*    */     } else {
/* 60 */       var1.a(var0.a("->{" + this.a + "}", this.a));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class a
/*    */     extends LootItemFunctionConditional.c<LootItemFunctionSetTable>
/*    */   {
/*    */     public void a(JsonObject var0, LootItemFunctionSetTable var1, JsonSerializationContext var2) {
/* 75 */       super.a(var0, var1, var2);
/*    */       
/* 77 */       var0.addProperty("name", LootItemFunctionSetTable.a(var1).toString());
/* 78 */       if (LootItemFunctionSetTable.b(var1) != 0L) {
/* 79 */         var0.addProperty("seed", Long.valueOf(LootItemFunctionSetTable.b(var1)));
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionSetTable b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 85 */       MinecraftKey var3 = new MinecraftKey(ChatDeserializer.h(var0, "name"));
/* 86 */       long var4 = ChatDeserializer.a(var0, "seed", 0L);
/* 87 */       return new LootItemFunctionSetTable(var2, var3, var4);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionSetTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */