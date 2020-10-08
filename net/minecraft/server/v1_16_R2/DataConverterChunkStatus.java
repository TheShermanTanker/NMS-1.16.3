/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class DataConverterChunkStatus extends DataFix {
/*    */   public DataConverterChunkStatus(Schema var0, boolean var1) {
/* 15 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 20 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.CHUNK);
/* 21 */     Type<?> var1 = var0.findFieldType("Level");
/*    */     
/* 23 */     OpticFinder<?> var2 = DSL.fieldFinder("Level", var1);
/*    */     
/* 25 */     return fixTypeEverywhereTyped("ChunkStatusFix", var0, getOutputSchema().getType(DataConverterTypes.CHUNK), var1 -> var1.updateTyped(var0, ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterChunkStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */