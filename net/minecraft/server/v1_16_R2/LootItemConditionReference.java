/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionReference
/*    */   implements LootItemCondition
/*    */ {
/* 14 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final MinecraftKey b;
/*    */   
/*    */   private LootItemConditionReference(MinecraftKey var0) {
/* 19 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 24 */     return LootItemConditions.o;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(LootCollector var0) {
/* 29 */     if (var0.b(this.b)) {
/* 30 */       var0.a("Condition " + this.b + " is recursively called");
/*    */       
/*    */       return;
/*    */     } 
/* 34 */     super.a(var0);
/*    */     
/* 36 */     LootItemCondition var1 = var0.d(this.b);
/* 37 */     if (var1 == null) {
/* 38 */       var0.a("Unknown condition table called " + this.b);
/*    */     } else {
/* 40 */       var1.a(var0.a(".{" + this.b + "}", this.b));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean test(LootTableInfo var0) {
/* 46 */     LootItemCondition var1 = var0.b(this.b);
/* 47 */     if (var0.a(var1)) {
/*    */       try {
/* 49 */         return var1.test(var0);
/*    */       } finally {
/* 51 */         var0.b(var1);
/*    */       } 
/*    */     }
/* 54 */     LOGGER.warn("Detected infinite loop in loot tables");
/* 55 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class a
/*    */     implements LootSerializer<LootItemConditionReference>
/*    */   {
/*    */     public void a(JsonObject var0, LootItemConditionReference var1, JsonSerializationContext var2) {
/* 66 */       var0.addProperty("name", LootItemConditionReference.a(var1).toString());
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionReference a(JsonObject var0, JsonDeserializationContext var1) {
/* 71 */       MinecraftKey var2 = new MinecraftKey(ChatDeserializer.h(var0, "name"));
/* 72 */       return new LootItemConditionReference(var2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */