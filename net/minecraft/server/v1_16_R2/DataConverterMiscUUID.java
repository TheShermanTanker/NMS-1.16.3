/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterMiscUUID extends DataConverterUUIDBase {
/*    */   public DataConverterMiscUUID(Schema var0) {
/* 12 */     super(var0, DataConverterTypes.LEVEL);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 17 */     return fixTypeEverywhereTyped("LevelUUIDFix", getInputSchema().getType(this.b), var0 -> var0.updateTyped(DSL.remainderFinder(), ()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Dynamic<?> b(Dynamic<?> var0) {
/* 28 */     return a(var0, "WanderingTraderId", "WanderingTraderId").orElse(var0);
/*    */   }
/*    */   
/*    */   private Dynamic<?> c(Dynamic<?> var0) {
/* 32 */     return var0.update("DimensionData", var0 -> var0.updateMapValues(()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Dynamic<?> d(Dynamic<?> var0) {
/* 42 */     return var0.update("CustomBossEvents", var0 -> var0.updateMapValues(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterMiscUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */