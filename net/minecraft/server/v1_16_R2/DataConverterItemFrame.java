/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterItemFrame extends DataConverterNamedEntity {
/*    */   public DataConverterItemFrame(Schema var0, boolean var1) {
/* 10 */     super(var0, var1, "EntityItemFrameDirectionFix", DataConverterTypes.ENTITY, "minecraft:item_frame");
/*    */   }
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 14 */     return var0.set("Facing", var0.createByte(a(var0.get("Facing").asByte((byte)0))));
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 19 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */   
/*    */   private static byte a(byte var0) {
/* 23 */     switch (var0)
/*    */     
/*    */     { default:
/* 26 */         return 2;
/*    */       case 0:
/* 28 */         return 3;
/*    */       case 1:
/* 30 */         return 4;
/*    */       case 3:
/* 32 */         break; }  return 5;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */