/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterSavedDataUUID extends DataConverterUUIDBase {
/*    */   public DataConverterSavedDataUUID(Schema var0) {
/* 10 */     super(var0, DataConverterTypes.SAVED_DATA);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 15 */     return fixTypeEverywhereTyped("SavedDataUUIDFix", getInputSchema().getType(this.b), var0 -> var0.updateTyped(var0.getType().findField("data"), ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSavedDataUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */