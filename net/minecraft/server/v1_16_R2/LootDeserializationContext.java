/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootDeserializationContext
/*    */ {
/* 15 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   private final MinecraftKey b;
/*    */   private final LootPredicateManager c;
/* 18 */   private final Gson d = LootSerialization.a().create();
/*    */   
/*    */   public LootDeserializationContext(MinecraftKey var0, LootPredicateManager var1) {
/* 21 */     this.b = var0;
/* 22 */     this.c = var1;
/*    */   }
/*    */   
/*    */   public final LootItemCondition[] a(JsonArray var0, String var1, LootContextParameterSet var2) {
/* 26 */     LootItemCondition[] var3 = (LootItemCondition[])this.d.fromJson((JsonElement)var0, LootItemCondition[].class);
/* 27 */     LootCollector var4 = new LootCollector(var2, this.c::a, var0 -> null);
/* 28 */     for (LootItemCondition var8 : var3) {
/* 29 */       var8.a(var4);
/* 30 */       var4.a().forEach((var1, var2) -> LOGGER.warn("Found validation problem in advancement trigger {}/{}: {}", var0, var1, var2));
/*    */     } 
/* 32 */     return var3;
/*    */   }
/*    */   
/*    */   public MinecraftKey a() {
/* 36 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootDeserializationContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */