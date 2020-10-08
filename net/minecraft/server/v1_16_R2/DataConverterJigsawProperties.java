/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterJigsawProperties extends DataConverterNamedEntity {
/*    */   public DataConverterJigsawProperties(Schema var0, boolean var1) {
/* 10 */     super(var0, var1, "JigsawPropertiesFix", DataConverterTypes.BLOCK_ENTITY, "minecraft:jigsaw");
/*    */   }
/*    */   
/*    */   private static Dynamic<?> a(Dynamic<?> var0) {
/* 14 */     String var1 = var0.get("attachement_type").asString("minecraft:empty");
/* 15 */     String var2 = var0.get("target_pool").asString("minecraft:empty");
/* 16 */     return var0
/* 17 */       .set("name", var0.createString(var1))
/* 18 */       .set("target", var0.createString(var1))
/* 19 */       .remove("attachement_type")
/* 20 */       .set("pool", var0.createString(var2))
/* 21 */       .remove("target_pool");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 26 */     return var0.update(DSL.remainderFinder(), DataConverterJigsawProperties::a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterJigsawProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */