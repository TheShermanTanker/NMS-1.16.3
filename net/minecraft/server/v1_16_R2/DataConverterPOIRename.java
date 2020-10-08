/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public abstract class DataConverterPOIRename extends DataFix {
/*    */   public DataConverterPOIRename(Schema var0, boolean var1) {
/* 19 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 24 */     Type<Pair<String, Dynamic<?>>> var0 = DSL.named(DataConverterTypes.POI_CHUNK.typeName(), DSL.remainderType());
/*    */     
/* 26 */     if (!Objects.equals(var0, getInputSchema().getType(DataConverterTypes.POI_CHUNK))) {
/* 27 */       throw new IllegalStateException("Poi type is not what was expected.");
/*    */     }
/* 29 */     return fixTypeEverywhere("POI rename", var0, var0 -> ());
/*    */   }
/*    */   
/*    */   private <T> Dynamic<T> a(Dynamic<T> var0) {
/* 33 */     return var0.update("Sections", var0 -> var0.updateMapValues(()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private <T> Optional<Dynamic<T>> b(Dynamic<T> var0) {
/* 39 */     return var0.asStreamOpt().map(var1 -> var0.createList(var1.map(())))
/*    */ 
/*    */       
/* 42 */       .result();
/*    */   }
/*    */   
/*    */   protected abstract String a(String paramString);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterPOIRename.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */