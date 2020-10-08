/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class DataConverterBanner
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterBanner(Schema var0, boolean var1) {
/* 22 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 27 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.ITEM_STACK);
/*    */     
/* 29 */     OpticFinder<Pair<String, String>> var1 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a()));
/* 30 */     OpticFinder<?> var2 = var0.findField("tag");
/* 31 */     OpticFinder<?> var3 = var2.type().findField("BlockEntityTag");
/*    */     
/* 33 */     return fixTypeEverywhereTyped("ItemBannerColorFix", var0, var3 -> {
/*    */           Optional<Pair<String, String>> var4 = var3.getOptional(var0);
/*    */           if (var4.isPresent() && Objects.equals(((Pair)var4.get()).getSecond(), "minecraft:banner")) {
/*    */             Dynamic<?> var5 = (Dynamic)var3.get(DSL.remainderFinder());
/*    */             Optional<? extends Typed<?>> var6 = var3.getOptionalTyped(var1);
/*    */             if (var6.isPresent()) {
/*    */               Typed<?> var7 = var6.get();
/*    */               Optional<? extends Typed<?>> var8 = var7.getOptionalTyped(var2);
/*    */               if (var8.isPresent()) {
/*    */                 Typed<?> var9 = var8.get();
/*    */                 Dynamic<?> var10 = (Dynamic)var7.get(DSL.remainderFinder());
/*    */                 Dynamic<?> var11 = (Dynamic)var9.getOrCreate(DSL.remainderFinder());
/*    */                 if (var11.get("Base").asNumber().result().isPresent()) {
/*    */                   var5 = var5.set("Damage", var5.createShort((short)(var11.get("Base").asInt(0) & 0xF)));
/*    */                   Optional<? extends Dynamic<?>> var12 = var10.get("display").result();
/*    */                   if (var12.isPresent()) {
/*    */                     Dynamic<?> var13 = var12.get();
/*    */                     Dynamic<?> var14 = var13.createMap((Map)ImmutableMap.of(var13.createString("Lore"), var13.createList(Stream.of(var13.createString("(+NBT")))));
/*    */                     if (Objects.equals(var13, var14))
/*    */                       return var3.set(DSL.remainderFinder(), var5); 
/*    */                   } 
/*    */                   var11.remove("Base");
/*    */                   return var3.set(DSL.remainderFinder(), var5).set(var1, var7.set(var2, var9.set(DSL.remainderFinder(), var11)));
/*    */                 } 
/*    */               } 
/*    */             } 
/*    */             return var3.set(DSL.remainderFinder(), var5);
/*    */           } 
/*    */           return var3;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */