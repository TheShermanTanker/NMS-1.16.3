/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.types.templates.List;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ public class DataConverterVillagerTrade
/*    */   extends DataConverterNamedEntity
/*    */ {
/*    */   public DataConverterVillagerTrade(Schema var0, boolean var1) {
/* 18 */     super(var0, var1, "Villager trade fix", DataConverterTypes.ENTITY, "minecraft:villager");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 23 */     OpticFinder<?> var1 = var0.getType().findField("Offers");
/* 24 */     OpticFinder<?> var2 = var1.type().findField("Recipes");
/* 25 */     Type<?> var3 = var2.type();
/* 26 */     if (!(var3 instanceof List.ListType)) {
/* 27 */       throw new IllegalStateException("Recipes are expected to be a list.");
/*    */     }
/* 29 */     List.ListType<?> var4 = (List.ListType)var3;
/* 30 */     Type<?> var5 = var4.getElement();
/* 31 */     OpticFinder<?> var6 = DSL.typeFinder(var5);
/* 32 */     OpticFinder<?> var7 = var5.findField("buy");
/* 33 */     OpticFinder<?> var8 = var5.findField("buyB");
/* 34 */     OpticFinder<?> var9 = var5.findField("sell");
/*    */     
/* 36 */     OpticFinder<Pair<String, String>> var10 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a()));
/* 37 */     Function<Typed<?>, Typed<?>> var11 = var1 -> a(var0, var1);
/*    */     
/* 39 */     return var0.updateTyped(var1, var6 -> var6.updateTyped(var0, ()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Typed<?> a(OpticFinder<Pair<String, String>> var0, Typed<?> var1) {
/* 48 */     return var1.update(var0, var0 -> var0.mapSecond(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterVillagerTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */