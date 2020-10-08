/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterJukeBox extends DataConverterNamedEntity {
/*    */   public DataConverterJukeBox(Schema var0, boolean var1) {
/* 12 */     super(var0, var1, "BlockEntityJukeboxFix", DataConverterTypes.BLOCK_ENTITY, "minecraft:jukebox");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 17 */     Type<?> var1 = getInputSchema().getChoiceType(DataConverterTypes.BLOCK_ENTITY, "minecraft:jukebox");
/* 18 */     Type<?> var2 = var1.findFieldType("RecordItem");
/* 19 */     OpticFinder<?> var3 = DSL.fieldFinder("RecordItem", var2);
/* 20 */     Dynamic<?> var4 = (Dynamic)var0.get(DSL.remainderFinder());
/* 21 */     int var5 = var4.get("Record").asInt(0);
/* 22 */     if (var5 > 0) {
/* 23 */       var4.remove("Record");
/*    */       
/* 25 */       String var6 = DataConverterFlatten.a(DataConverterMaterialId.a(var5), 0);
/* 26 */       if (var6 != null) {
/* 27 */         Dynamic<?> var7 = var4.emptyMap();
/* 28 */         var7 = var7.set("id", var7.createString(var6));
/* 29 */         var7 = var7.set("Count", var7.createByte((byte)1));
/* 30 */         return var0.set(var3, (Typed)((Pair)var2.readTyped(var7).result().orElseThrow(() -> new IllegalStateException("Could not create record item stack."))).getFirst()).set(DSL.remainderFinder(), var4);
/*    */       } 
/*    */     } 
/* 33 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterJukeBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */