/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DataConverterEntityShulkerRotation
/*    */   extends DataConverterNamedEntity {
/*    */   public DataConverterEntityShulkerRotation(Schema var0) {
/* 12 */     super(var0, false, "EntityShulkerRotationFix", DataConverterTypes.ENTITY, "minecraft:shulker");
/*    */   }
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 16 */     List<Double> var1 = var0.get("Rotation").asList(var0 -> Double.valueOf(var0.asDouble(180.0D)));
/* 17 */     if (!var1.isEmpty()) {
/* 18 */       var1.set(0, Double.valueOf(((Double)var1.get(0)).doubleValue() - 180.0D));
/* 19 */       return var0.set("Rotation", var0.createList(var1.stream().map(var0::createDouble)));
/*    */     } 
/* 21 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 26 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityShulkerRotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */