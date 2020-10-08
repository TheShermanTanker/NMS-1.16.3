/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterShulker extends DataConverterNamedEntity {
/*    */   public DataConverterShulker(Schema var0, boolean var1) {
/* 10 */     super(var0, var1, "EntityShulkerColorFix", DataConverterTypes.ENTITY, "minecraft:shulker");
/*    */   }
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 14 */     if (!var0.get("Color").map(Dynamic::asNumber).result().isPresent()) {
/* 15 */       return var0.set("Color", var0.createByte((byte)10));
/*    */     }
/* 17 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 22 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterShulker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */