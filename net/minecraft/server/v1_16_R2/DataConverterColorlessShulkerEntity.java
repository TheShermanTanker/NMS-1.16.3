/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterColorlessShulkerEntity extends DataConverterNamedEntity {
/*    */   public DataConverterColorlessShulkerEntity(Schema var0, boolean var1) {
/*  9 */     super(var0, var1, "Colorless shulker entity fix", DataConverterTypes.ENTITY, "minecraft:shulker");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 14 */     return var0.update(DSL.remainderFinder(), var0 -> (var0.get("Color").asInt(0) == 10) ? var0.set("Color", var0.createByte((byte)16)) : var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterColorlessShulkerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */