/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.types.templates.CompoundList;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public class DataConverterNewVillage
/*    */   extends DataFix {
/*    */   public DataConverterNewVillage(Schema var0, boolean var1) {
/* 22 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 27 */     CompoundList.CompoundListType<String, ?> var0 = DSL.compoundList(DSL.string(), getInputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE));
/* 28 */     OpticFinder<? extends List<? extends Pair<String, ?>>> var1 = var0.finder();
/*    */     
/* 30 */     return a(var0);
/*    */   }
/*    */   
/*    */   private <SF> TypeRewriteRule a(CompoundList.CompoundListType<String, SF> var0) {
/* 34 */     Type<?> var1 = getInputSchema().getType(DataConverterTypes.CHUNK);
/* 35 */     Type<?> var2 = getInputSchema().getType(DataConverterTypes.STRUCTURE_FEATURE);
/* 36 */     OpticFinder<?> var3 = var1.findField("Level");
/* 37 */     OpticFinder<?> var4 = var3.type().findField("Structures");
/* 38 */     OpticFinder<?> var5 = var4.type().findField("Starts");
/* 39 */     OpticFinder<List<Pair<String, SF>>> var6 = var0.finder();
/* 40 */     return TypeRewriteRule.seq(
/* 41 */         fixTypeEverywhereTyped("NewVillageFix", var1, var4 -> var4.updateTyped(var0, ())), 
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
/* 57 */         fixTypeEverywhereTyped("NewVillageStartFix", var2, var0 -> var0.update(DSL.remainderFinder(), ())));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterNewVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */