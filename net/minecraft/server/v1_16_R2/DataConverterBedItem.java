/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DataConverterBedItem
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterBedItem(Schema var0, boolean var1) {
/* 18 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 23 */     OpticFinder<Pair<String, String>> var0 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a()));
/*    */     
/* 25 */     return fixTypeEverywhereTyped("BedItemColorFix", getInputSchema().getType(DataConverterTypes.ITEM_STACK), var1 -> {
/*    */           Optional<Pair<String, String>> var2 = var1.getOptional(var0);
/*    */           if (var2.isPresent() && Objects.equals(((Pair)var2.get()).getSecond(), "minecraft:bed")) {
/*    */             Dynamic<?> var3 = (Dynamic)var1.get(DSL.remainderFinder());
/*    */             if (var3.get("Damage").asInt(0) == 0)
/*    */               return var1.set(DSL.remainderFinder(), var3.set("Damage", var3.createShort((short)14))); 
/*    */           } 
/*    */           return var1;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */