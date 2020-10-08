/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterPlayerUUID extends DataConverterUUIDBase {
/*    */   public DataConverterPlayerUUID(Schema var0) {
/* 12 */     super(var0, DataConverterTypes.PLAYER);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 17 */     return fixTypeEverywhereTyped("PlayerUUIDFix", getInputSchema().getType(this.b), var0 -> {
/*    */           OpticFinder<?> var1 = var0.getType().findField("RootVehicle");
/*    */           return var0.updateTyped(var1, var1.type(), ()).update(DSL.remainderFinder(), ());
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterPlayerUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */