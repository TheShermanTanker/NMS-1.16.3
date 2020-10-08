/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSyntaxException;
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
/*    */ public class LootItemConditionBlockStateProperty
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final Block a;
/*    */   private final CriterionTriggerProperties b;
/*    */   
/*    */   private LootItemConditionBlockStateProperty(Block var0, CriterionTriggerProperties var1) {
/* 25 */     this.a = var0;
/* 26 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 31 */     return LootItemConditions.h;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 36 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.BLOCK_STATE);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 41 */     IBlockData var1 = var0.<IBlockData>getContextParameter(LootContextParameters.BLOCK_STATE);
/* 42 */     return (var1 != null && this.a == var1.getBlock() && this.b.a(var1));
/*    */   }
/*    */   
/*    */   public static class a implements LootItemCondition.a {
/*    */     private final Block a;
/* 47 */     private CriterionTriggerProperties b = CriterionTriggerProperties.a;
/*    */     
/*    */     public a(Block var0) {
/* 50 */       this.a = var0;
/*    */     }
/*    */     
/*    */     public a a(CriterionTriggerProperties.a var0) {
/* 54 */       this.b = var0.b();
/* 55 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemCondition build() {
/* 60 */       return new LootItemConditionBlockStateProperty(this.a, this.b);
/*    */     }
/*    */   }
/*    */   
/*    */   public static a a(Block var0) {
/* 65 */     return new a(var0);
/*    */   }
/*    */   
/*    */   public static class b
/*    */     implements LootSerializer<LootItemConditionBlockStateProperty> {
/*    */     public void a(JsonObject var0, LootItemConditionBlockStateProperty var1, JsonSerializationContext var2) {
/* 71 */       var0.addProperty("block", IRegistry.BLOCK.getKey(LootItemConditionBlockStateProperty.a(var1)).toString());
/* 72 */       var0.add("properties", LootItemConditionBlockStateProperty.b(var1).a());
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionBlockStateProperty a(JsonObject var0, JsonDeserializationContext var1) {
/* 77 */       MinecraftKey var2 = new MinecraftKey(ChatDeserializer.h(var0, "block"));
/*    */       
/* 79 */       Block var3 = (Block)IRegistry.BLOCK.getOptional(var2).orElseThrow(() -> new IllegalArgumentException("Can't find block " + var0));
/* 80 */       CriterionTriggerProperties var4 = CriterionTriggerProperties.a(var0.get("properties"));
/* 81 */       var4.a(var3.getStates(), var1 -> {
/*    */             throw new JsonSyntaxException("Block " + var0 + " has no property " + var1);
/*    */           });
/*    */       
/* 85 */       return new LootItemConditionBlockStateProperty(var3, var4);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionBlockStateProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */