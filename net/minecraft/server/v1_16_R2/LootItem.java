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
/*    */ 
/*    */ public class LootItem
/*    */   extends LootSelectorEntry
/*    */ {
/*    */   private final Item g;
/*    */   
/*    */   private LootItem(Item var0, int var1, int var2, LootItemCondition[] var3, LootItemFunction[] var4) {
/* 22 */     super(var1, var2, var3, var4);
/* 23 */     this.g = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootEntryType a() {
/* 28 */     return LootEntries.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Consumer<ItemStack> var0, LootTableInfo var1) {
/* 33 */     var0.accept(new ItemStack(this.g));
/*    */   }
/*    */   
/*    */   public static LootSelectorEntry.a<?> a(IMaterial var0) {
/* 37 */     return a((var1, var2, var3, var4) -> new LootItem(var0.getItem(), var1, var2, var3, var4));
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootSelectorEntry.e<LootItem> {
/*    */     public void a(JsonObject var0, LootItem var1, JsonSerializationContext var2) {
/* 43 */       super.a(var0, var1, var2);
/*    */       
/* 45 */       MinecraftKey var3 = IRegistry.ITEM.getKey(LootItem.a(var1));
/* 46 */       if (var3 == null) {
/* 47 */         throw new IllegalArgumentException("Can't serialize unknown item " + LootItem.a(var1));
/*    */       }
/*    */       
/* 50 */       var0.addProperty("name", var3.toString());
/*    */     }
/*    */ 
/*    */     
/*    */     protected LootItem b(JsonObject var0, JsonDeserializationContext var1, int var2, int var3, LootItemCondition[] var4, LootItemFunction[] var5) {
/* 55 */       Item var6 = ChatDeserializer.i(var0, "name");
/* 56 */       return new LootItem(var6, var2, var3, var4, var5);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */