/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class DataConverterIglooMetadataRemoval extends DataFix {
/*    */   public DataConverterIglooMetadataRemoval(Schema var0, boolean var1) {
/* 11 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 16 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE);
/* 17 */     Type<?> var1 = getOutputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE);
/*    */     
/* 19 */     return writeFixAndRead("IglooMetadataRemovalFix", var0, var1, DataConverterIglooMetadataRemoval::a);
/*    */   }
/*    */   
/*    */   private static <T> Dynamic<T> a(Dynamic<T> var0) {
/* 23 */     boolean var1 = ((Boolean)var0.get("Children").asStreamOpt().map(var0 -> Boolean.valueOf(var0.allMatch(DataConverterIglooMetadataRemoval::c))).result().orElse(Boolean.valueOf(false))).booleanValue();
/*    */     
/* 25 */     if (var1) {
/* 26 */       return var0.set("id", var0.createString("Igloo")).remove("Children");
/*    */     }
/* 28 */     return var0.update("Children", DataConverterIglooMetadataRemoval::b);
/*    */   }
/*    */ 
/*    */   
/*    */   private static <T> Dynamic<T> b(Dynamic<T> var0) {
/* 33 */     return var0.asStreamOpt().map(var0 -> var0.filter(())).map(var0::createList).result().orElse(var0);
/*    */   }
/*    */   
/*    */   private static boolean c(Dynamic<?> var0) {
/* 37 */     return var0.get("id").asString("").equals("Iglu");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterIglooMetadataRemoval.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */