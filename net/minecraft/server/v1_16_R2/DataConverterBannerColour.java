/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class DataConverterBannerColour extends DataConverterNamedEntity {
/*    */   public DataConverterBannerColour(Schema var0, boolean var1) {
/* 11 */     super(var0, var1, "BlockEntityBannerColorFix", DataConverterTypes.BLOCK_ENTITY, "minecraft:banner");
/*    */   }
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 15 */     var0 = var0.update("Base", var0 -> var0.createInt(15 - var0.asInt(0)));
/*    */     
/* 17 */     var0 = var0.update("Patterns", var0 -> (Dynamic)DataFixUtils.orElse(var0.asStreamOpt().map(()).map(var0::createList).result(), var0));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 28 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBannerColour.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */