/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterVillagerFollowRange
/*    */   extends DataConverterNamedEntity
/*    */ {
/*    */   public DataConverterVillagerFollowRange(Schema var0) {
/* 17 */     super(var0, false, "Villager Follow Range Fix", DataConverterTypes.ENTITY, "minecraft:villager");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 22 */     return var0.update(DSL.remainderFinder(), DataConverterVillagerFollowRange::a);
/*    */   }
/*    */   
/*    */   private static Dynamic<?> a(Dynamic<?> var0) {
/* 26 */     return var0.update("Attributes", var1 -> var0.createList(var1.asStream().map(())));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterVillagerFollowRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */