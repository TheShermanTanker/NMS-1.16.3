/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Either;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.datafixers.util.Unit;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterFurnaceRecipesUsed
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterFurnaceRecipesUsed(Schema var0, boolean var1) {
/* 29 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 34 */     return a(getOutputSchema().getTypeRaw(DataConverterTypes.RECIPE));
/*    */   }
/*    */   
/*    */   private <R> TypeRewriteRule a(Type<R> var0) {
/* 38 */     Type<Pair<Either<Pair<List<Pair<R, Integer>>, Dynamic<?>>, Unit>, Dynamic<?>>> var1 = DSL.and(
/* 39 */         DSL.optional((Type)DSL.field("RecipesUsed", DSL.and((Type)DSL.compoundList(var0, DSL.intType()), DSL.remainderType()))), 
/* 40 */         DSL.remainderType());
/*    */ 
/*    */     
/* 43 */     OpticFinder<?> var2 = DSL.namedChoice("minecraft:furnace", getInputSchema().getChoiceType(DataConverterTypes.BLOCK_ENTITY, "minecraft:furnace"));
/* 44 */     OpticFinder<?> var3 = DSL.namedChoice("minecraft:blast_furnace", getInputSchema().getChoiceType(DataConverterTypes.BLOCK_ENTITY, "minecraft:blast_furnace"));
/* 45 */     OpticFinder<?> var4 = DSL.namedChoice("minecraft:smoker", getInputSchema().getChoiceType(DataConverterTypes.BLOCK_ENTITY, "minecraft:smoker"));
/*    */     
/* 47 */     Type<?> var5 = getOutputSchema().getChoiceType(DataConverterTypes.BLOCK_ENTITY, "minecraft:furnace");
/* 48 */     Type<?> var6 = getOutputSchema().getChoiceType(DataConverterTypes.BLOCK_ENTITY, "minecraft:blast_furnace");
/* 49 */     Type<?> var7 = getOutputSchema().getChoiceType(DataConverterTypes.BLOCK_ENTITY, "minecraft:smoker");
/*    */     
/* 51 */     Type<?> var8 = getInputSchema().getType(DataConverterTypes.BLOCK_ENTITY);
/* 52 */     Type<?> var9 = getOutputSchema().getType(DataConverterTypes.BLOCK_ENTITY);
/* 53 */     return fixTypeEverywhereTyped("FurnaceRecipesFix", var8, var9, var8 -> var8.updateTyped(var0, var1, ()).updateTyped(var4, var5, ()).updateTyped(var6, var7, ()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private <R> Typed<?> a(Type<R> var0, Type<Pair<Either<Pair<List<Pair<R, Integer>>, Dynamic<?>>, Unit>, Dynamic<?>>> var1, Typed<?> var2) {
/* 62 */     Dynamic<?> var3 = (Dynamic)var2.getOrCreate(DSL.remainderFinder());
/*    */     
/* 64 */     int var4 = var3.get("RecipesUsedSize").asInt(0);
/* 65 */     var3 = var3.remove("RecipesUsedSize");
/*    */     
/* 67 */     List<Pair<R, Integer>> var5 = Lists.newArrayList();
/* 68 */     for (int var6 = 0; var6 < var4; var6++) {
/* 69 */       String var7 = "RecipeLocation" + var6;
/* 70 */       String var8 = "RecipeAmount" + var6;
/*    */       
/* 72 */       Optional<? extends Dynamic<?>> var9 = var3.get(var7).result();
/* 73 */       int var10 = var3.get(var8).asInt(0);
/* 74 */       if (var10 > 0) {
/* 75 */         var9.ifPresent(var3 -> {
/*    */               Optional<? extends Pair<R, ? extends Dynamic<?>>> var4 = var0.read(var3).result();
/*    */               
/*    */               var4.ifPresent(());
/*    */             });
/*    */       }
/* 81 */       var3 = var3.remove(var7).remove(var8);
/*    */     } 
/*    */     
/* 84 */     return var2.set(DSL.remainderFinder(), var1, Pair.of(Either.left(Pair.of(var5, var3.emptyMap())), var3));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterFurnaceRecipesUsed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */