/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterPiston extends DataConverterNamedEntity {
/*    */   public DataConverterPiston(Schema var0, boolean var1) {
/* 12 */     super(var0, var1, "BlockEntityBlockStateFix", DataConverterTypes.BLOCK_ENTITY, "minecraft:piston");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 17 */     Type<?> var1 = getOutputSchema().getChoiceType(DataConverterTypes.BLOCK_ENTITY, "minecraft:piston");
/*    */     
/* 19 */     Type<?> var2 = var1.findFieldType("blockState");
/* 20 */     OpticFinder<?> var3 = DSL.fieldFinder("blockState", var2);
/* 21 */     Dynamic<?> var4 = (Dynamic)var0.get(DSL.remainderFinder());
/*    */     
/* 23 */     int var5 = var4.get("blockId").asInt(0);
/* 24 */     var4 = var4.remove("blockId");
/* 25 */     int var6 = var4.get("blockData").asInt(0) & 0xF;
/* 26 */     var4 = var4.remove("blockData");
/*    */     
/* 28 */     Dynamic<?> var7 = DataConverterFlattenData.b(var5 << 4 | var6);
/* 29 */     Typed<?> var8 = (Typed)var1.pointTyped(var0.getOps()).orElseThrow(() -> new IllegalStateException("Could not create new piston block entity."));
/* 30 */     return var8.set(DSL.remainderFinder(), var4).set(var3, (Typed)((Pair)var2.readTyped(var7).result().orElseThrow(() -> new IllegalStateException("Could not parse newly created block state tag."))).getFirst());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterPiston.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */