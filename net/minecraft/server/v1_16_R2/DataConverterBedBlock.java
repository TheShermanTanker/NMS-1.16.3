/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.types.templates.List;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class DataConverterBedBlock
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterBedBlock(Schema var0, boolean var1) {
/* 26 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 31 */     Type<?> var0 = getOutputSchema().getType(DataConverterTypes.CHUNK);
/* 32 */     Type<?> var1 = var0.findFieldType("Level");
/* 33 */     Type<?> var2 = var1.findFieldType("TileEntities");
/* 34 */     if (!(var2 instanceof List.ListType)) {
/* 35 */       throw new IllegalStateException("Tile entity type is not a list type.");
/*    */     }
/* 37 */     List.ListType<?> var3 = (List.ListType)var2;
/*    */     
/* 39 */     return a(var1, var3);
/*    */   }
/*    */   
/*    */   private <TE> TypeRewriteRule a(Type<?> var0, List.ListType<TE> var1) {
/* 43 */     Type<TE> var2 = var1.getElement();
/* 44 */     OpticFinder<?> var3 = DSL.fieldFinder("Level", var0);
/* 45 */     OpticFinder<List<TE>> var4 = DSL.fieldFinder("TileEntities", (Type)var1);
/*    */ 
/*    */     
/* 48 */     int var5 = 416;
/*    */     
/* 50 */     return TypeRewriteRule.seq(
/* 51 */         fixTypeEverywhere("InjectBedBlockEntityType", (Type)getInputSchema().findChoiceType(DataConverterTypes.BLOCK_ENTITY), (Type)getOutputSchema().findChoiceType(DataConverterTypes.BLOCK_ENTITY), var0 -> ()), 
/* 52 */         fixTypeEverywhereTyped("BedBlockEntityInjecter", getOutputSchema().getType(DataConverterTypes.CHUNK), var3 -> {
/*    */             Typed<?> var4 = var3.getTyped(var0);
/*    */             Dynamic<?> var5 = (Dynamic)var4.get(DSL.remainderFinder());
/*    */             int var6 = var5.get("xPos").asInt(0);
/*    */             int var7 = var5.get("zPos").asInt(0);
/*    */             List<TE> var8 = Lists.newArrayList((Iterable)var4.getOrCreate(var1));
/*    */             List<? extends Dynamic<?>> var9 = var5.get("Sections").asList(Function.identity());
/*    */             for (int var10 = 0; var10 < var9.size(); var10++) {
/*    */               Dynamic<?> var11 = var9.get(var10);
/*    */               int var12 = var11.get("Y").asInt(0);
/*    */               Stream<Integer> var13 = var11.get("Blocks").asStream().map(());
/*    */               int var14 = 0;
/*    */               Iterator<?> iterator = var13::iterator.iterator();
/*    */               while (iterator.hasNext()) {
/*    */                 int var16 = ((Integer)iterator.next()).intValue();
/*    */                 if (416 == (var16 & 0xFF) << 4) {
/*    */                   int var17 = var14 & 0xF;
/*    */                   int var18 = var14 >> 8 & 0xF;
/*    */                   int var19 = var14 >> 4 & 0xF;
/*    */                   Map<Dynamic<?>, Dynamic<?>> var20 = Maps.newHashMap();
/*    */                   var20.put(var11.createString("id"), var11.createString("minecraft:bed"));
/*    */                   var20.put(var11.createString("x"), var11.createInt(var17 + (var6 << 4)));
/*    */                   var20.put(var11.createString("y"), var11.createInt(var18 + (var12 << 4)));
/*    */                   var20.put(var11.createString("z"), var11.createInt(var19 + (var7 << 4)));
/*    */                   var20.put(var11.createString("color"), var11.createShort((short)14));
/*    */                   var8.add((TE)((Pair)var2.read(var11.createMap(var20)).result().orElseThrow(())).getFirst());
/*    */                 } 
/*    */                 var14++;
/*    */               } 
/*    */             } 
/*    */             return !var8.isEmpty() ? var3.set(var0, var4.set(var1, var8)) : var3;
/*    */           }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBedBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */