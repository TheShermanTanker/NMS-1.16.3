/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterStriderGravity extends DataConverterNamedEntity {
/*    */   public DataConverterStriderGravity(Schema var0, boolean var1) {
/* 10 */     super(var0, var1, "StriderGravityFix", DataConverterTypes.ENTITY, "minecraft:strider");
/*    */   }
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 14 */     if (var0.get("NoGravity").asBoolean(false)) {
/* 15 */       return var0.set("NoGravity", var0.createBoolean(false));
/*    */     }
/* 17 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 22 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterStriderGravity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */