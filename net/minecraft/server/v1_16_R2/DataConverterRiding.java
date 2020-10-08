/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Either;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.datafixers.util.Unit;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DataConverterRiding extends DataFix {
/*    */   public DataConverterRiding(Schema var0, boolean var1) {
/* 22 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 27 */     Schema var0 = getInputSchema();
/* 28 */     Schema var1 = getOutputSchema();
/*    */     
/* 30 */     Type<?> var2 = var0.getTypeRaw(DataConverterTypes.ENTITY_TREE);
/* 31 */     Type<?> var3 = var1.getTypeRaw(DataConverterTypes.ENTITY_TREE);
/* 32 */     Type<?> var4 = var0.getTypeRaw(DataConverterTypes.ENTITY);
/*    */     
/* 34 */     return a(var0, var1, var2, var3, var4);
/*    */   }
/*    */   
/*    */   private <OldEntityTree, NewEntityTree, Entity> TypeRewriteRule a(Schema var0, Schema var1, Type<OldEntityTree> var2, Type<NewEntityTree> var3, Type<Entity> var4) {
/* 38 */     Type<Pair<String, Pair<Either<OldEntityTree, Unit>, Entity>>> var5 = DSL.named(DataConverterTypes.ENTITY_TREE.typeName(), DSL.and(
/* 39 */           DSL.optional((Type)DSL.field("Riding", var2)), var4));
/*    */ 
/*    */ 
/*    */     
/* 43 */     Type<Pair<String, Pair<Either<List<NewEntityTree>, Unit>, Entity>>> var6 = DSL.named(DataConverterTypes.ENTITY_TREE.typeName(), DSL.and(
/* 44 */           DSL.optional((Type)DSL.field("Passengers", (Type)DSL.list(var3))), var4));
/*    */ 
/*    */ 
/*    */     
/* 48 */     Type<?> var7 = var0.getType(DataConverterTypes.ENTITY_TREE);
/* 49 */     Type<?> var8 = var1.getType(DataConverterTypes.ENTITY_TREE);
/*    */     
/* 51 */     if (!Objects.equals(var7, var5)) {
/* 52 */       throw new IllegalStateException("Old entity type is not what was expected.");
/*    */     }
/*    */     
/* 55 */     if (!var8.equals(var6, true, true)) {
/* 56 */       throw new IllegalStateException("New entity type is not what was expected.");
/*    */     }
/*    */     
/* 59 */     OpticFinder<Pair<String, Pair<Either<OldEntityTree, Unit>, Entity>>> var9 = DSL.typeFinder(var5);
/* 60 */     OpticFinder<Pair<String, Pair<Either<List<NewEntityTree>, Unit>, Entity>>> var10 = DSL.typeFinder(var6);
/* 61 */     OpticFinder<NewEntityTree> var11 = DSL.typeFinder(var3);
/*    */     
/* 63 */     Type<?> var12 = var0.getType(DataConverterTypes.PLAYER);
/* 64 */     Type<?> var13 = var1.getType(DataConverterTypes.PLAYER);
/*    */     
/* 66 */     return TypeRewriteRule.seq(
/* 67 */         fixTypeEverywhere("EntityRidingToPassengerFix", var5, var6, var5 -> ()), 
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
/*    */ 
/*    */ 
/*    */         
/* 90 */         writeAndRead("player RootVehicle injecter", var12, var13));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterRiding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */