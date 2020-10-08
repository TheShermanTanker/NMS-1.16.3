/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.util.Either;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nullable;
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
/*    */ public abstract class WorldGenFeatureDefinedStructurePoolStructure
/*    */ {
/* 28 */   public static final Codec<WorldGenFeatureDefinedStructurePoolStructure> e = IRegistry.STRUCTURE_POOL_ELEMENT.dispatch("element_type", WorldGenFeatureDefinedStructurePoolStructure::a, WorldGenFeatureDefinedStructurePools::codec);
/*    */   
/*    */   protected static <E extends WorldGenFeatureDefinedStructurePoolStructure> RecordCodecBuilder<E, WorldGenFeatureDefinedStructurePoolTemplate.Matching> d() {
/* 31 */     return WorldGenFeatureDefinedStructurePoolTemplate.Matching.c.fieldOf("projection").forGetter(WorldGenFeatureDefinedStructurePoolStructure::e);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private volatile WorldGenFeatureDefinedStructurePoolTemplate.Matching a;
/*    */   
/*    */   protected WorldGenFeatureDefinedStructurePoolStructure(WorldGenFeatureDefinedStructurePoolTemplate.Matching var0) {
/* 38 */     this.a = var0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(GeneratorAccess var0, DefinedStructure.BlockInfo var1, BlockPosition var2, EnumBlockRotation var3, Random var4, StructureBoundingBox var5) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenFeatureDefinedStructurePoolStructure a(WorldGenFeatureDefinedStructurePoolTemplate.Matching var0) {
/* 55 */     this.a = var0;
/* 56 */     return this;
/*    */   }
/*    */   
/*    */   public WorldGenFeatureDefinedStructurePoolTemplate.Matching e() {
/* 60 */     WorldGenFeatureDefinedStructurePoolTemplate.Matching var0 = this.a;
/* 61 */     if (var0 == null) {
/* 62 */       throw new IllegalStateException();
/*    */     }
/* 64 */     return var0;
/*    */   }
/*    */   
/*    */   public int f() {
/* 68 */     return 1;
/*    */   }
/*    */   
/*    */   public static Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, WorldGenFeatureDefinedStructurePoolEmpty> g() {
/* 72 */     return var0 -> WorldGenFeatureDefinedStructurePoolEmpty.b;
/*    */   }
/*    */   
/*    */   public static Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, WorldGenFeatureDefinedStructurePoolLegacySingle> a(String var0) {
/* 76 */     return var1 -> new WorldGenFeatureDefinedStructurePoolLegacySingle(Either.left(new MinecraftKey(var0)), (), var1);
/*    */   }
/*    */   
/*    */   public static Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, WorldGenFeatureDefinedStructurePoolLegacySingle> a(String var0, ProcessorList var1) {
/* 80 */     return var2 -> new WorldGenFeatureDefinedStructurePoolLegacySingle(Either.left(new MinecraftKey(var0)), (), var2);
/*    */   }
/*    */   
/*    */   public static Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, WorldGenFeatureDefinedStructurePoolSingle> b(String var0) {
/* 84 */     return var1 -> new WorldGenFeatureDefinedStructurePoolSingle(Either.left(new MinecraftKey(var0)), (), var1);
/*    */   }
/*    */   
/*    */   public static Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, WorldGenFeatureDefinedStructurePoolSingle> b(String var0, ProcessorList var1) {
/* 88 */     return var2 -> new WorldGenFeatureDefinedStructurePoolSingle(Either.left(new MinecraftKey(var0)), (), var2);
/*    */   }
/*    */   
/*    */   public static Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, WorldGenFeatureDefinedStructurePoolFeature> a(WorldGenFeatureConfigured<?, ?> var0) {
/* 92 */     return var1 -> new WorldGenFeatureDefinedStructurePoolFeature((), var1);
/*    */   }
/*    */   
/*    */   public static Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, WorldGenFeatureDefinedStructurePoolList> a(List<Function<WorldGenFeatureDefinedStructurePoolTemplate.Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>> var0) {
/* 96 */     return var1 -> new WorldGenFeatureDefinedStructurePoolList((List<WorldGenFeatureDefinedStructurePoolStructure>)var0.stream().map(()).collect(Collectors.toList()), var1);
/*    */   }
/*    */   
/*    */   public abstract List<DefinedStructure.BlockInfo> a(DefinedStructureManager paramDefinedStructureManager, BlockPosition paramBlockPosition, EnumBlockRotation paramEnumBlockRotation, Random paramRandom);
/*    */   
/*    */   public abstract StructureBoundingBox a(DefinedStructureManager paramDefinedStructureManager, BlockPosition paramBlockPosition, EnumBlockRotation paramEnumBlockRotation);
/*    */   
/*    */   public abstract boolean a(DefinedStructureManager paramDefinedStructureManager, GeneratorAccessSeed paramGeneratorAccessSeed, StructureManager paramStructureManager, ChunkGenerator paramChunkGenerator, BlockPosition paramBlockPosition1, BlockPosition paramBlockPosition2, EnumBlockRotation paramEnumBlockRotation, StructureBoundingBox paramStructureBoundingBox, Random paramRandom, boolean paramBoolean);
/*    */   
/*    */   public abstract WorldGenFeatureDefinedStructurePools<?> a();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDefinedStructurePoolStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */