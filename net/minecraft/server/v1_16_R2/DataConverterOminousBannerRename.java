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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterOminousBannerRename
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterOminousBannerRename(Schema var0, boolean var1) {
/* 23 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   private Dynamic<?> a(Dynamic<?> var0) {
/* 27 */     Optional<? extends Dynamic<?>> var1 = var0.get("display").result();
/* 28 */     if (var1.isPresent()) {
/* 29 */       Dynamic<?> var2 = var1.get();
/* 30 */       Optional<String> var3 = var2.get("Name").asString().result();
/* 31 */       if (var3.isPresent()) {
/* 32 */         String var4 = var3.get();
/* 33 */         var4 = var4.replace("\"translate\":\"block.minecraft.illager_banner\"", "\"translate\":\"block.minecraft.ominous_banner\"");
/* 34 */         var2 = var2.set("Name", var2.createString(var4));
/*    */       } 
/* 36 */       return var0.set("display", var2);
/*    */     } 
/* 38 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 43 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.ITEM_STACK);
/* 44 */     OpticFinder<Pair<String, String>> var1 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a()));
/* 45 */     OpticFinder<?> var2 = var0.findField("tag");
/*    */     
/* 47 */     return fixTypeEverywhereTyped("OminousBannerRenameFix", var0, var2 -> {
/*    */           Optional<Pair<String, String>> var3 = var2.getOptional(var0);
/*    */           if (var3.isPresent() && Objects.equals(((Pair)var3.get()).getSecond(), "minecraft:white_banner")) {
/*    */             Optional<? extends Typed<?>> var4 = var2.getOptionalTyped(var1);
/*    */             if (var4.isPresent()) {
/*    */               Typed<?> var5 = var4.get();
/*    */               Dynamic<?> var6 = (Dynamic)var5.get(DSL.remainderFinder());
/*    */               return var2.set(var1, var5.set(DSL.remainderFinder(), a(var6)));
/*    */             } 
/*    */           } 
/*    */           return var2;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterOminousBannerRename.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */