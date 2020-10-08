/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterChunkLightRemove extends DataFix {
/*    */   public DataConverterChunkLightRemove(Schema var0, boolean var1) {
/* 12 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 17 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.CHUNK);
/* 18 */     Type<?> var1 = var0.findFieldType("Level");
/*    */     
/* 20 */     OpticFinder<?> var2 = DSL.fieldFinder("Level", var1);
/*    */     
/* 22 */     return fixTypeEverywhereTyped("ChunkLightRemoveFix", var0, getOutputSchema().getType(DataConverterTypes.CHUNK), var1 -> var1.updateTyped(var0, ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterChunkLightRemove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */