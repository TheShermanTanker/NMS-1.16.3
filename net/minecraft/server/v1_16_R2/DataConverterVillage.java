/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class DataConverterVillage
/*    */   extends DataFix {
/*    */   public DataConverterVillage(Schema var0, boolean var1) {
/* 12 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 17 */     return writeFixAndRead("SavedDataVillageCropFix", getInputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE), getOutputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE), this::a);
/*    */   }
/*    */   
/*    */   private <T> Dynamic<T> a(Dynamic<T> var0) {
/* 21 */     return var0.update("Children", DataConverterVillage::b);
/*    */   }
/*    */   
/*    */   private static <T> Dynamic<T> b(Dynamic<T> var0) {
/* 25 */     return var0.asStreamOpt().map(DataConverterVillage::a).map(var0::createList).result().orElse(var0);
/*    */   }
/*    */   
/*    */   private static Stream<? extends Dynamic<?>> a(Stream<? extends Dynamic<?>> var0) {
/* 29 */     return var0.map(var0 -> {
/*    */           String var1 = var0.get("id").asString("");
/*    */           return "ViF".equals(var1) ? c(var0) : ("ViDF".equals(var1) ? d(var0) : var0);
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static <T> Dynamic<T> c(Dynamic<T> var0) {
/* 43 */     var0 = a(var0, "CA");
/* 44 */     return a(var0, "CB");
/*    */   }
/*    */   
/*    */   private static <T> Dynamic<T> d(Dynamic<T> var0) {
/* 48 */     var0 = a(var0, "CA");
/* 49 */     var0 = a(var0, "CB");
/* 50 */     var0 = a(var0, "CC");
/* 51 */     return a(var0, "CD");
/*    */   }
/*    */   
/*    */   private static <T> Dynamic<T> a(Dynamic<T> var0, String var1) {
/* 55 */     if (var0.get(var1).asNumber().result().isPresent()) {
/* 56 */       return var0.set(var1, DataConverterFlattenData.b(var0.get(var1).asInt(0) << 4));
/*    */     }
/* 58 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */