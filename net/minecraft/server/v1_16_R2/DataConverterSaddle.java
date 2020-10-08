/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class DataConverterSaddle
/*    */   extends DataConverterNamedEntity
/*    */ {
/*    */   public DataConverterSaddle(Schema var0, boolean var1) {
/* 17 */     super(var0, var1, "EntityHorseSaddleFix", DataConverterTypes.ENTITY, "EntityHorse");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 24 */     OpticFinder<Pair<String, String>> var1 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a()));
/* 25 */     Type<?> var2 = getInputSchema().getTypeRaw(DataConverterTypes.ITEM_STACK);
/* 26 */     OpticFinder<?> var3 = DSL.fieldFinder("SaddleItem", var2);
/*    */     
/* 28 */     Optional<? extends Typed<?>> var4 = var0.getOptionalTyped(var3);
/* 29 */     Dynamic<?> var5 = (Dynamic)var0.get(DSL.remainderFinder());
/* 30 */     if (!var4.isPresent() && var5.get("Saddle").asBoolean(false)) {
/* 31 */       Typed<?> var6 = (Typed)var2.pointTyped(var0.getOps()).orElseThrow(IllegalStateException::new);
/* 32 */       var6 = var6.set(var1, Pair.of(DataConverterTypes.ITEM_NAME.typeName(), "minecraft:saddle"));
/*    */       
/* 34 */       Dynamic<?> var7 = var5.emptyMap();
/* 35 */       var7 = var7.set("Count", var7.createByte((byte)1));
/* 36 */       var7 = var7.set("Damage", var7.createShort((short)0));
/*    */       
/* 38 */       var6 = var6.set(DSL.remainderFinder(), var7);
/* 39 */       var5.remove("Saddle");
/*    */       
/* 41 */       return var0.set(var3, var6).set(DSL.remainderFinder(), var5);
/*    */     } 
/* 43 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSaddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */