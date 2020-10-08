/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterRemoveGolemGossip
/*    */   extends DataConverterNamedEntity {
/*    */   public DataConverterRemoveGolemGossip(Schema var0, boolean var1) {
/* 11 */     super(var0, var1, "Remove Golem Gossip Fix", DataConverterTypes.ENTITY, "minecraft:villager");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 16 */     return var0.update(DSL.remainderFinder(), DataConverterRemoveGolemGossip::a);
/*    */   }
/*    */   
/*    */   private static Dynamic<?> a(Dynamic<?> var0) {
/* 20 */     return var0.update("Gossips", var1 -> var0.createList(var1.asStream().filter(())));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterRemoveGolemGossip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */