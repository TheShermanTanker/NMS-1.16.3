/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import it.unimi.dsi.fastutil.shorts.ShortArrayList;
/*    */ import it.unimi.dsi.fastutil.shorts.ShortList;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import java.util.stream.Collectors;
/*    */ import java.util.stream.IntStream;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class DataConverterProtoChunk
/*    */   extends DataFix {
/*    */   public DataConverterProtoChunk(Schema var0, boolean var1) {
/* 26 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 31 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.CHUNK);
/* 32 */     Type<?> var1 = getOutputSchema().getType(DataConverterTypes.CHUNK);
/* 33 */     Type<?> var2 = var0.findFieldType("Level");
/* 34 */     Type<?> var3 = var1.findFieldType("Level");
/* 35 */     Type<?> var4 = var2.findFieldType("TileTicks");
/*    */     
/* 37 */     OpticFinder<?> var5 = DSL.fieldFinder("Level", var2);
/* 38 */     OpticFinder<?> var6 = DSL.fieldFinder("TileTicks", var4);
/*    */     
/* 40 */     return TypeRewriteRule.seq(
/* 41 */         fixTypeEverywhereTyped("ChunkToProtoChunkFix", var0, getOutputSchema().getType(DataConverterTypes.CHUNK), var3 -> var3.updateTyped(var0, var1, ())), 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 84 */         writeAndRead("Structure biome inject", getInputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE), getOutputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE)));
/*    */   }
/*    */ 
/*    */   
/*    */   private static short a(int var0, int var1, int var2) {
/* 89 */     return (short)(var0 & 0xF | (var1 & 0xF) << 4 | (var2 & 0xF) << 8);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterProtoChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */