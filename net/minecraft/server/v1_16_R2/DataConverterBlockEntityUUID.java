/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterBlockEntityUUID extends DataConverterUUIDBase {
/*    */   public DataConverterBlockEntityUUID(Schema var0) {
/*  9 */     super(var0, DataConverterTypes.BLOCK_ENTITY);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 14 */     return fixTypeEverywhereTyped("BlockEntityUUIDFix", getInputSchema().getType(this.b), var0 -> {
/*    */           var0 = a(var0, "minecraft:conduit", this::c);
/*    */           return a(var0, "minecraft:skull", this::b);
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   private Dynamic<?> b(Dynamic<?> var0) {
/* 22 */     return var0.get("Owner").get().map(var0 -> (Dynamic)a(var0, "Id", "Id").orElse(var0))
/*    */       
/* 24 */       .map(var1 -> var0.remove("Owner").set("SkullOwner", var1))
/*    */       
/* 26 */       .result().orElse(var0);
/*    */   }
/*    */   
/*    */   private Dynamic<?> c(Dynamic<?> var0) {
/* 30 */     return b(var0, "target_uuid", "Target").orElse(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBlockEntityUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */