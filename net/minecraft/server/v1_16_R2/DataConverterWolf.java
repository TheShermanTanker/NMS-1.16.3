/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterWolf extends DataConverterNamedEntity {
/*    */   public DataConverterWolf(Schema var0, boolean var1) {
/* 10 */     super(var0, var1, "EntityWolfColorFix", DataConverterTypes.ENTITY, "minecraft:wolf");
/*    */   }
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 14 */     return var0.update("CollarColor", var0 -> var0.createByte((byte)(15 - var0.asInt(0))));
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 19 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */