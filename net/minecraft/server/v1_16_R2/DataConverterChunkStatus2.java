/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class DataConverterChunkStatus2 extends DataFix {
/* 16 */   private static final Map<String, String> a = (Map<String, String>)ImmutableMap.builder()
/* 17 */     .put("structure_references", "empty")
/* 18 */     .put("biomes", "empty")
/* 19 */     .put("base", "surface")
/* 20 */     .put("carved", "carvers")
/* 21 */     .put("liquid_carved", "liquid_carvers")
/* 22 */     .put("decorated", "features")
/* 23 */     .put("lighted", "light")
/* 24 */     .put("mobs_spawned", "spawn")
/* 25 */     .put("finalized", "heightmaps")
/* 26 */     .put("fullchunk", "full")
/* 27 */     .build();
/*    */   
/*    */   public DataConverterChunkStatus2(Schema var0, boolean var1) {
/* 30 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 35 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.CHUNK);
/* 36 */     Type<?> var1 = var0.findFieldType("Level");
/*    */     
/* 38 */     OpticFinder<?> var2 = DSL.fieldFinder("Level", var1);
/*    */     
/* 40 */     return fixTypeEverywhereTyped("ChunkStatusFix2", var0, getOutputSchema().getType(DataConverterTypes.CHUNK), var1 -> var1.updateTyped(var0, ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterChunkStatus2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */