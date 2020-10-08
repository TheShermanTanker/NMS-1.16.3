/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFix;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.FieldFinder;
/*     */ import com.mojang.datafixers.OpticFinder;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.datafixers.types.templates.CompoundList;
/*     */ import com.mojang.datafixers.types.templates.TaggedChoice;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.datafixers.util.Unit;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterMissingDimension
/*     */   extends DataFix
/*     */ {
/*     */   public DataConverterMissingDimension(Schema var0, boolean var1) {
/*  36 */     super(var0, var1);
/*     */   }
/*     */   
/*     */   private static <A> Type<Pair<A, Dynamic<?>>> a(String var0, Type<A> var1) {
/*  40 */     return DSL.and((Type)DSL.field(var0, var1), DSL.remainderType());
/*     */   }
/*     */   
/*     */   private static <A> Type<Pair<Either<A, Unit>, Dynamic<?>>> b(String var0, Type<A> var1) {
/*  44 */     return DSL.and(DSL.optional((Type)DSL.field(var0, var1)), DSL.remainderType());
/*     */   }
/*     */   
/*     */   private static <A1, A2> Type<Pair<Either<A1, Unit>, Pair<Either<A2, Unit>, Dynamic<?>>>> a(String var0, Type<A1> var1, String var2, Type<A2> var3) {
/*  48 */     return DSL.and(
/*  49 */         DSL.optional((Type)DSL.field(var0, var1)), 
/*  50 */         DSL.optional((Type)DSL.field(var2, var3)), 
/*  51 */         DSL.remainderType());
/*     */   }
/*     */ 
/*     */   
/*     */   protected TypeRewriteRule makeRule() {
/*  56 */     Schema var0 = getInputSchema();
/*  57 */     TaggedChoice.TaggedChoiceType<String> var1 = new TaggedChoice.TaggedChoiceType("type", DSL.string(), (Map)ImmutableMap.of("minecraft:debug", 
/*  58 */           DSL.remainderType(), "minecraft:flat", 
/*  59 */           b("settings", a("biome", var0
/*  60 */               .getType(DataConverterTypes.BIOME), "layers", 
/*  61 */               (Type<?>)DSL.list(
/*  62 */                 b("block", var0
/*  63 */                   .getType(DataConverterTypes.BLOCK_NAME))))), "minecraft:noise", 
/*     */ 
/*     */ 
/*     */           
/*  67 */           a("biome_source", 
/*  68 */             DSL.taggedChoiceType("type", DSL.string(), (Map)ImmutableMap.of("minecraft:fixed", 
/*  69 */                 a("biome", var0.getType(DataConverterTypes.BIOME)), "minecraft:multi_noise", 
/*  70 */                 DSL.list(a("biome", var0.getType(DataConverterTypes.BIOME))), "minecraft:checkerboard", 
/*  71 */                 a("biomes", (Type<?>)DSL.list(var0.getType(DataConverterTypes.BIOME))), "minecraft:vanilla_layered", 
/*  72 */                 DSL.remainderType(), "minecraft:the_end", 
/*  73 */                 DSL.remainderType())), "settings", 
/*     */             
/*  75 */             DSL.or(DSL.string(), a("default_block", var0
/*  76 */                 .getType(DataConverterTypes.BLOCK_NAME), "default_fluid", var0
/*  77 */                 .getType(DataConverterTypes.BLOCK_NAME))))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     CompoundList.CompoundListType<String, ?> var2 = DSL.compoundList(DataConverterSchemaNamed.a(), a("generator", (Type<String>)var1));
/*  83 */     Type<?> var3 = DSL.and((Type)var2, DSL.remainderType());
/*     */     
/*  85 */     Type<?> var4 = var0.getType(DataConverterTypes.WORLD_GEN_SETTINGS);
/*     */     
/*  87 */     FieldFinder<?> var5 = new FieldFinder("dimensions", var3);
/*  88 */     if (!var4.findFieldType("dimensions").equals(var3)) {
/*  89 */       throw new IllegalStateException();
/*     */     }
/*  91 */     OpticFinder<? extends List<? extends Pair<String, ?>>> var6 = var2.finder();
/*  92 */     return fixTypeEverywhereTyped("MissingDimensionFix", var4, var3 -> var3.updateTyped((OpticFinder)var0, ()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private <T> Dynamic<T> a(Dynamic<T> var0) {
/* 107 */     long var1 = var0.get("seed").asLong(0L);
/* 108 */     return new Dynamic(var0.getOps(), DataConverterWorldGenSettingsBuilding.a(var0, var1, DataConverterWorldGenSettingsBuilding.a(var0, var1), false));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterMissingDimension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */