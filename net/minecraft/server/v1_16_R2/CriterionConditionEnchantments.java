/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonNull;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionConditionEnchantments
/*    */ {
/* 17 */   public static final CriterionConditionEnchantments a = new CriterionConditionEnchantments();
/* 18 */   public static final CriterionConditionEnchantments[] b = new CriterionConditionEnchantments[0];
/*    */   
/*    */   private final Enchantment c;
/*    */   private final CriterionConditionValue.IntegerRange d;
/*    */   
/*    */   public CriterionConditionEnchantments() {
/* 24 */     this.c = null;
/* 25 */     this.d = CriterionConditionValue.IntegerRange.e;
/*    */   }
/*    */   
/*    */   public CriterionConditionEnchantments(@Nullable Enchantment var0, CriterionConditionValue.IntegerRange var1) {
/* 29 */     this.c = var0;
/* 30 */     this.d = var1;
/*    */   }
/*    */   
/*    */   public boolean a(Map<Enchantment, Integer> var0) {
/* 34 */     if (this.c != null) {
/*    */       
/* 36 */       if (!var0.containsKey(this.c)) {
/* 37 */         return false;
/*    */       }
/* 39 */       int var1 = ((Integer)var0.get(this.c)).intValue();
/* 40 */       if (this.d != null && !this.d.d(var1)) {
/* 41 */         return false;
/*    */       }
/* 43 */     } else if (this.d != null) {
/*    */       
/* 45 */       for (Integer var2 : var0.values()) {
/* 46 */         if (this.d.d(var2.intValue())) {
/* 47 */           return true;
/*    */         }
/*    */       } 
/* 50 */       return false;
/*    */     } 
/*    */     
/* 53 */     return true;
/*    */   }
/*    */   
/*    */   public JsonElement a() {
/* 57 */     if (this == a) {
/* 58 */       return (JsonElement)JsonNull.INSTANCE;
/*    */     }
/*    */     
/* 61 */     JsonObject var0 = new JsonObject();
/*    */     
/* 63 */     if (this.c != null) {
/* 64 */       var0.addProperty("enchantment", IRegistry.ENCHANTMENT.getKey(this.c).toString());
/*    */     }
/* 66 */     var0.add("levels", this.d.d());
/*    */     
/* 68 */     return (JsonElement)var0;
/*    */   }
/*    */   
/*    */   public static CriterionConditionEnchantments a(@Nullable JsonElement var0) {
/* 72 */     if (var0 == null || var0.isJsonNull()) {
/* 73 */       return a;
/*    */     }
/* 75 */     JsonObject var1 = ChatDeserializer.m(var0, "enchantment");
/*    */     
/* 77 */     Enchantment var2 = null;
/* 78 */     if (var1.has("enchantment")) {
/* 79 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(var1, "enchantment"));
/* 80 */       var2 = (Enchantment)IRegistry.ENCHANTMENT.getOptional(minecraftKey).orElseThrow(() -> new JsonSyntaxException("Unknown enchantment '" + var0 + "'"));
/*    */     } 
/* 82 */     CriterionConditionValue.IntegerRange var3 = CriterionConditionValue.IntegerRange.a(var1.get("levels"));
/*    */     
/* 84 */     return new CriterionConditionEnchantments(var2, var3);
/*    */   }
/*    */   
/*    */   public static CriterionConditionEnchantments[] b(@Nullable JsonElement var0) {
/* 88 */     if (var0 == null || var0.isJsonNull()) {
/* 89 */       return b;
/*    */     }
/*    */     
/* 92 */     JsonArray var1 = ChatDeserializer.n(var0, "enchantments");
/* 93 */     CriterionConditionEnchantments[] var2 = new CriterionConditionEnchantments[var1.size()];
/* 94 */     for (int var3 = 0; var3 < var2.length; var3++) {
/* 95 */       var2[var3] = a(var1.get(var3));
/*    */     }
/*    */     
/* 98 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionEnchantments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */