/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionTableBonus
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final Enchantment a;
/*    */   private final float[] b;
/*    */   
/*    */   private LootItemConditionTableBonus(Enchantment var0, float[] var1) {
/* 26 */     this.a = var0;
/* 27 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 32 */     return LootItemConditions.j;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 37 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.TOOL);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 42 */     ItemStack var1 = var0.<ItemStack>getContextParameter(LootContextParameters.TOOL);
/*    */     
/* 44 */     int var2 = (var1 != null) ? EnchantmentManager.getEnchantmentLevel(this.a, var1) : 0;
/* 45 */     float var3 = this.b[Math.min(var2, this.b.length - 1)];
/* 46 */     return (var0.a().nextFloat() < var3);
/*    */   }
/*    */   
/*    */   public static LootItemCondition.a a(Enchantment var0, float... var1) {
/* 50 */     return () -> new LootItemConditionTableBonus(var0, var1);
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionTableBonus> {
/*    */     public void a(JsonObject var0, LootItemConditionTableBonus var1, JsonSerializationContext var2) {
/* 56 */       var0.addProperty("enchantment", IRegistry.ENCHANTMENT.getKey(LootItemConditionTableBonus.a(var1)).toString());
/* 57 */       var0.add("chances", var2.serialize(LootItemConditionTableBonus.b(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionTableBonus a(JsonObject var0, JsonDeserializationContext var1) {
/* 62 */       MinecraftKey var2 = new MinecraftKey(ChatDeserializer.h(var0, "enchantment"));
/*    */       
/* 64 */       Enchantment var3 = (Enchantment)IRegistry.ENCHANTMENT.getOptional(var2).orElseThrow(() -> new JsonParseException("Invalid enchantment id: " + var0));
/* 65 */       float[] var4 = ChatDeserializer.<float[]>a(var0, "chances", var1, (Class)float[].class);
/* 66 */       return new LootItemConditionTableBonus(var3, var4);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionTableBonus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */