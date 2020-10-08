/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootEnchantLevel
/*    */   extends LootItemFunctionConditional
/*    */ {
/*    */   private final LootValue a;
/*    */   private final boolean b;
/*    */   
/*    */   private LootEnchantLevel(LootItemCondition[] var0, LootValue var1, boolean var2) {
/* 21 */     super(var0);
/* 22 */     this.a = var1;
/* 23 */     this.b = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 28 */     return LootItemFunctions.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 33 */     Random var2 = var1.a();
/* 34 */     return EnchantmentManager.a(var2, var0, this.a.a(var2), this.b);
/*    */   }
/*    */   
/*    */   public static class a extends LootItemFunctionConditional.a<a> {
/*    */     private final LootValue a;
/*    */     private boolean b;
/*    */     
/*    */     public a(LootValue var0) {
/* 42 */       this.a = var0;
/*    */     }
/*    */ 
/*    */     
/*    */     protected a d() {
/* 47 */       return this;
/*    */     }
/*    */     
/*    */     public a e() {
/* 51 */       this.b = true;
/* 52 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunction b() {
/* 57 */       return new LootEnchantLevel(g(), this.a, this.b);
/*    */     }
/*    */   }
/*    */   
/*    */   public static a a(LootValue var0) {
/* 62 */     return new a(var0);
/*    */   }
/*    */   
/*    */   public static class b
/*    */     extends LootItemFunctionConditional.c<LootEnchantLevel> {
/*    */     public void a(JsonObject var0, LootEnchantLevel var1, JsonSerializationContext var2) {
/* 68 */       super.a(var0, var1, var2);
/*    */       
/* 70 */       var0.add("levels", LootValueGenerators.a(LootEnchantLevel.a(var1), var2));
/* 71 */       var0.addProperty("treasure", Boolean.valueOf(LootEnchantLevel.b(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootEnchantLevel b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 76 */       LootValue var3 = LootValueGenerators.a(var0.get("levels"), var1);
/* 77 */       boolean var4 = ChatDeserializer.a(var0, "treasure", false);
/* 78 */       return new LootEnchantLevel(var2, var3, var4);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootEnchantLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */