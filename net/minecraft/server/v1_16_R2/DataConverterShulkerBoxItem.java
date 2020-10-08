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
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ public class DataConverterShulkerBoxItem
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterShulkerBoxItem(Schema var0, boolean var1) {
/* 20 */     super(var0, var1);
/*    */   }
/*    */   
/* 23 */   public static final String[] a = new String[] { "minecraft:white_shulker_box", "minecraft:orange_shulker_box", "minecraft:magenta_shulker_box", "minecraft:light_blue_shulker_box", "minecraft:yellow_shulker_box", "minecraft:lime_shulker_box", "minecraft:pink_shulker_box", "minecraft:gray_shulker_box", "minecraft:silver_shulker_box", "minecraft:cyan_shulker_box", "minecraft:purple_shulker_box", "minecraft:blue_shulker_box", "minecraft:brown_shulker_box", "minecraft:green_shulker_box", "minecraft:red_shulker_box", "minecraft:black_shulker_box" };
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 44 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.ITEM_STACK);
/*    */     
/* 46 */     OpticFinder<Pair<String, String>> var1 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a()));
/* 47 */     OpticFinder<?> var2 = var0.findField("tag");
/* 48 */     OpticFinder<?> var3 = var2.type().findField("BlockEntityTag");
/*    */     
/* 50 */     return fixTypeEverywhereTyped("ItemShulkerBoxColorFix", var0, var3 -> {
/*    */           Optional<Pair<String, String>> var4 = var3.getOptional(var0);
/*    */           if (var4.isPresent() && Objects.equals(((Pair)var4.get()).getSecond(), "minecraft:shulker_box")) {
/*    */             Optional<? extends Typed<?>> var5 = var3.getOptionalTyped(var1);
/*    */             if (var5.isPresent()) {
/*    */               Typed<?> var6 = var5.get();
/*    */               Optional<? extends Typed<?>> var7 = var6.getOptionalTyped(var2);
/*    */               if (var7.isPresent()) {
/*    */                 Typed<?> var8 = var7.get();
/*    */                 Dynamic<?> var9 = (Dynamic)var8.get(DSL.remainderFinder());
/*    */                 int var10 = var9.get("Color").asInt(0);
/*    */                 var9.remove("Color");
/*    */                 return var3.set(var1, var6.set(var2, var8.set(DSL.remainderFinder(), var9))).set(var0, Pair.of(DataConverterTypes.ITEM_NAME.typeName(), a[var10 % 16]));
/*    */               } 
/*    */             } 
/*    */           } 
/*    */           return var3;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterShulkerBoxItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */