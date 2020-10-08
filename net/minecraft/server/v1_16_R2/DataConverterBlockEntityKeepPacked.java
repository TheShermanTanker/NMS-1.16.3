/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterBlockEntityKeepPacked extends DataConverterNamedEntity {
/*    */   public DataConverterBlockEntityKeepPacked(Schema var0, boolean var1) {
/* 10 */     super(var0, var1, "BlockEntityKeepPacked", DataConverterTypes.BLOCK_ENTITY, "DUMMY");
/*    */   }
/*    */   
/*    */   private static Dynamic<?> a(Dynamic<?> var0) {
/* 14 */     return var0.set("keepPacked", var0.createBoolean(true));
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 19 */     return var0.update(DSL.remainderFinder(), DataConverterBlockEntityKeepPacked::a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBlockEntityKeepPacked.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */