/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ public class DataConverterPotionWater
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterPotionWater(Schema var0, boolean var1) {
/* 19 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 24 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.ITEM_STACK);
/*    */     
/* 26 */     OpticFinder<Pair<String, String>> var1 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a()));
/* 27 */     OpticFinder<?> var2 = var0.findField("tag");
/*    */     
/* 29 */     return fixTypeEverywhereTyped("ItemWaterPotionFix", var0, var2 -> {
/*    */           Optional<Pair<String, String>> var3 = var2.getOptional(var0);
/*    */           if (var3.isPresent()) {
/*    */             String var4 = (String)((Pair)var3.get()).getSecond();
/*    */             if ("minecraft:potion".equals(var4) || "minecraft:splash_potion".equals(var4) || "minecraft:lingering_potion".equals(var4) || "minecraft:tipped_arrow".equals(var4)) {
/*    */               Typed<?> var5 = var2.getOrCreateTyped(var1);
/*    */               Dynamic<?> var6 = (Dynamic)var5.get(DSL.remainderFinder());
/*    */               if (!var6.get("Potion").asString().result().isPresent())
/*    */                 var6 = var6.set("Potion", var6.createString("minecraft:water")); 
/*    */               return var2.set(var1, var5.set(DSL.remainderFinder(), var6));
/*    */             } 
/*    */           } 
/*    */           return var2;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterPotionWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */