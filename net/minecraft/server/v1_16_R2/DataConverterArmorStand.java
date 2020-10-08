/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterArmorStand extends DataConverterNamedEntity {
/*    */   public DataConverterArmorStand(Schema var0, boolean var1) {
/* 10 */     super(var0, var1, "EntityArmorStandSilentFix", DataConverterTypes.ENTITY, "ArmorStand");
/*    */   }
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 14 */     if (var0.get("Silent").asBoolean(false) && !var0.get("Marker").asBoolean(false)) {
/* 15 */       return var0.remove("Silent");
/*    */     }
/* 17 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 22 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */