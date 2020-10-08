/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterItemStackUUID
/*    */   extends DataConverterUUIDBase
/*    */ {
/*    */   public DataConverterItemStackUUID(Schema var0) {
/* 15 */     super(var0, DataConverterTypes.ITEM_STACK);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 20 */     OpticFinder<Pair<String, String>> var0 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a()));
/*    */     
/* 22 */     return fixTypeEverywhereTyped("ItemStackUUIDFix", getInputSchema().getType(this.b), var1 -> {
/*    */           OpticFinder<?> var2 = var1.getType().findField("tag");
/*    */           return var1.updateTyped(var2, ());
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Dynamic<?> b(Dynamic<?> var0) {
/* 38 */     return var0.update("AttributeModifiers", var1 -> var0.createList(var1.asStream().map(())));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Dynamic<?> c(Dynamic<?> var0) {
/* 46 */     return var0.update("SkullOwner", var0 -> (Dynamic)a(var0, "Id", "Id").orElse(var0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterItemStackUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */