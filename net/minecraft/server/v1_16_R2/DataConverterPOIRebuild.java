/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DataConverterPOIRebuild
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterPOIRebuild(Schema var0, boolean var1) {
/* 18 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 23 */     Type<Pair<String, Dynamic<?>>> var0 = DSL.named(DataConverterTypes.POI_CHUNK.typeName(), DSL.remainderType());
/*    */     
/* 25 */     if (!Objects.equals(var0, getInputSchema().getType(DataConverterTypes.POI_CHUNK))) {
/* 26 */       throw new IllegalStateException("Poi type is not what was expected.");
/*    */     }
/* 28 */     return fixTypeEverywhere("POI rebuild", var0, var0 -> ());
/*    */   }
/*    */   
/*    */   private static <T> Dynamic<T> a(Dynamic<T> var0) {
/* 32 */     return var0.update("Sections", var0 -> var0.updateMapValues(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterPOIRebuild.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */