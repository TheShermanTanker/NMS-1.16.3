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
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterEquipment
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterEquipment(Schema var0, boolean var1) {
/* 32 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 37 */     return a(getInputSchema().getTypeRaw(DataConverterTypes.ITEM_STACK));
/*    */   }
/*    */ 
/*    */   
/*    */   private <IS> TypeRewriteRule a(Type<IS> var0) {
/* 42 */     Type<Pair<Either<List<IS>, Unit>, Dynamic<?>>> var1 = DSL.and(
/* 43 */         DSL.optional((Type)DSL.field("Equipment", (Type)DSL.list(var0))), 
/* 44 */         DSL.remainderType());
/*    */ 
/*    */     
/* 47 */     Type<Pair<Either<List<IS>, Unit>, Pair<Either<List<IS>, Unit>, Dynamic<?>>>> var2 = DSL.and(
/* 48 */         DSL.optional((Type)DSL.field("ArmorItems", (Type)DSL.list(var0))), 
/* 49 */         DSL.optional((Type)DSL.field("HandItems", (Type)DSL.list(var0))), 
/* 50 */         DSL.remainderType());
/*    */     
/* 52 */     OpticFinder<Pair<Either<List<IS>, Unit>, Dynamic<?>>> var3 = DSL.typeFinder(var1);
/*    */     
/* 54 */     OpticFinder<List<IS>> var4 = DSL.fieldFinder("Equipment", (Type)DSL.list(var0));
/*    */     
/* 56 */     return fixTypeEverywhereTyped("EntityEquipmentToArmorAndHandFix", getInputSchema().getType(DataConverterTypes.ENTITY), getOutputSchema().getType(DataConverterTypes.ENTITY), var4 -> {
/*    */           Either<List<IS>, Unit> var5 = Either.right(DSL.unit());
/*    */           Either<List<IS>, Unit> var6 = Either.right(DSL.unit());
/*    */           Dynamic<?> var7 = (Dynamic)var4.getOrCreate(DSL.remainderFinder());
/*    */           Optional<List<IS>> var8 = var4.getOptional(var0);
/*    */           if (var8.isPresent()) {
/*    */             List<IS> list = var8.get();
/*    */             IS iS = (IS)((Pair)var1.read(var7.emptyMap()).result().orElseThrow(())).getFirst();
/*    */             if (!list.isEmpty())
/*    */               var5 = Either.left(Lists.newArrayList(new Object[] { list.get(0), iS })); 
/*    */             if (list.size() > 1) {
/*    */               List<IS> var11 = Lists.newArrayList(new Object[] { iS, iS, iS, iS });
/*    */               for (int var12 = 1; var12 < Math.min(list.size(), 5); var12++)
/*    */                 var11.set(var12 - 1, list.get(var12)); 
/*    */               var6 = Either.left(var11);
/*    */             } 
/*    */           } 
/*    */           Dynamic<?> var9 = var7;
/*    */           Optional<? extends Stream<? extends Dynamic<?>>> var10 = var7.get("DropChances").asStreamOpt().result();
/*    */           if (var10.isPresent()) {
/*    */             Iterator<? extends Dynamic<?>> var11 = Stream.<Dynamic<?>>concat(var10.get(), Stream.generate(())).iterator();
/*    */             float var12 = ((Dynamic)var11.next()).asFloat(0.0F);
/*    */             if (!var7.get("HandDropChances").result().isPresent()) {
/*    */               Dynamic<?> var13 = var7.createList(Stream.<Float>of(new Float[] { Float.valueOf(var12), Float.valueOf(0.0F) }).map(var7::createFloat));
/*    */               var7 = var7.set("HandDropChances", var13);
/*    */             } 
/*    */             if (!var7.get("ArmorDropChances").result().isPresent()) {
/*    */               Dynamic<?> var13 = var7.createList(Stream.<Float>of(new Float[] { Float.valueOf(((Dynamic)var11.next()).asFloat(0.0F)), Float.valueOf(((Dynamic)var11.next()).asFloat(0.0F)), Float.valueOf(((Dynamic)var11.next()).asFloat(0.0F)), Float.valueOf(((Dynamic)var11.next()).asFloat(0.0F)) }).map(var7::createFloat));
/*    */               var7 = var7.set("ArmorDropChances", var13);
/*    */             } 
/*    */             var7 = var7.remove("DropChances");
/*    */           } 
/*    */           return var4.set(var2, var3, Pair.of(var5, Pair.of(var6, var7)));
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEquipment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */