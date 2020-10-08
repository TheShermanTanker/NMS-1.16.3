/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.GsonBuilder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootSerialization
/*    */ {
/*    */   public static GsonBuilder a() {
/* 13 */     return (new GsonBuilder())
/* 14 */       .registerTypeAdapter(LootValueBounds.class, new LootValueBounds.a())
/* 15 */       .registerTypeAdapter(LootValueBinomial.class, new LootValueBinomial.a())
/* 16 */       .registerTypeAdapter(LootValueConstant.class, new LootValueConstant.a())
/* 17 */       .registerTypeHierarchyAdapter(LootItemCondition.class, LootItemConditions.a())
/* 18 */       .registerTypeHierarchyAdapter(LootTableInfo.EntityTarget.class, new LootTableInfo.EntityTarget.a());
/*    */   }
/*    */   
/*    */   public static GsonBuilder b() {
/* 22 */     return a()
/* 23 */       .registerTypeAdapter(LootIntegerLimit.class, new LootIntegerLimit.a())
/* 24 */       .registerTypeHierarchyAdapter(LootEntryAbstract.class, LootEntries.a())
/* 25 */       .registerTypeHierarchyAdapter(LootItemFunction.class, LootItemFunctions.a());
/*    */   }
/*    */   
/*    */   public static GsonBuilder c() {
/* 29 */     return b()
/* 30 */       .registerTypeAdapter(LootSelector.class, new LootSelector.b())
/* 31 */       .registerTypeAdapter(LootTable.class, new LootTable.b());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootSerialization.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */