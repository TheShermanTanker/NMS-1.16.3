/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import com.mojang.datafixers.util.Function3;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenFeatureDefinedStructurePoolSingle
/*     */   extends WorldGenFeatureDefinedStructurePoolStructure
/*     */ {
/*     */   private static <T> DataResult<T> a(Either<MinecraftKey, DefinedStructure> var0, DynamicOps<T> var1, T var2) {
/*  37 */     Optional<MinecraftKey> var3 = var0.left();
/*  38 */     if (!var3.isPresent()) {
/*  39 */       return DataResult.error("Can not serialize a runtime pool element");
/*     */     }
/*  41 */     return MinecraftKey.a.encode(var3.get(), var1, var2);
/*     */   }
/*     */   
/*  44 */   private static final Codec<Either<MinecraftKey, DefinedStructure>> a = Codec.of(WorldGenFeatureDefinedStructurePoolSingle::a, MinecraftKey.a
/*     */       
/*  46 */       .map(Either::left));
/*     */   
/*     */   static {
/*  49 */     b = RecordCodecBuilder.create(var0 -> var0.group((App)c(), (App)b(), (App)d()).apply((Applicative)var0, WorldGenFeatureDefinedStructurePoolSingle::new));
/*     */   }
/*     */   public static final Codec<WorldGenFeatureDefinedStructurePoolSingle> b;
/*     */   protected final Either<MinecraftKey, DefinedStructure> c;
/*     */   protected final Supplier<ProcessorList> d;
/*     */   
/*     */   protected static <E extends WorldGenFeatureDefinedStructurePoolSingle> RecordCodecBuilder<E, Supplier<ProcessorList>> b() {
/*  56 */     return DefinedStructureStructureProcessorType.m.fieldOf("processors").forGetter(var0 -> var0.d);
/*     */   }
/*     */   
/*     */   protected static <E extends WorldGenFeatureDefinedStructurePoolSingle> RecordCodecBuilder<E, Either<MinecraftKey, DefinedStructure>> c() {
/*  60 */     return a.fieldOf("location").forGetter(var0 -> var0.c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenFeatureDefinedStructurePoolSingle(Either<MinecraftKey, DefinedStructure> var0, Supplier<ProcessorList> var1, WorldGenFeatureDefinedStructurePoolTemplate.Matching var2) {
/*  67 */     super(var2);
/*  68 */     this.c = var0;
/*  69 */     this.d = var1;
/*     */   }
/*     */   
/*     */   public WorldGenFeatureDefinedStructurePoolSingle(DefinedStructure var0) {
/*  73 */     this(Either.right(var0), () -> ProcessorLists.a, WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DefinedStructure a(DefinedStructureManager var0) {
/*  83 */     return (DefinedStructure)this.c.map(var0::a, Function.identity());
/*     */   }
/*     */   
/*     */   public List<DefinedStructure.BlockInfo> a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, boolean var3) {
/*  87 */     DefinedStructure var4 = a(var0);
/*  88 */     List<DefinedStructure.BlockInfo> var5 = var4.a(var1, (new DefinedStructureInfo()).a(var2), Blocks.STRUCTURE_BLOCK, var3);
/*  89 */     List<DefinedStructure.BlockInfo> var6 = Lists.newArrayList();
/*  90 */     for (DefinedStructure.BlockInfo var8 : var5) {
/*  91 */       if (var8.c == null) {
/*     */         continue;
/*     */       }
/*     */       
/*  95 */       BlockPropertyStructureMode var9 = BlockPropertyStructureMode.valueOf(var8.c.getString("mode"));
/*  96 */       if (var9 != BlockPropertyStructureMode.DATA) {
/*     */         continue;
/*     */       }
/*     */       
/* 100 */       var6.add(var8);
/*     */     } 
/*     */     
/* 103 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DefinedStructure.BlockInfo> a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, Random var3) {
/* 108 */     DefinedStructure var4 = a(var0);
/* 109 */     List<DefinedStructure.BlockInfo> var5 = var4.a(var1, (new DefinedStructureInfo()).a(var2), Blocks.JIGSAW, true);
/* 110 */     Collections.shuffle(var5, var3);
/* 111 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructureBoundingBox a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2) {
/* 116 */     DefinedStructure var3 = a(var0);
/* 117 */     return var3.b((new DefinedStructureInfo()).a(var2), var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(DefinedStructureManager var0, GeneratorAccessSeed var1, StructureManager var2, ChunkGenerator var3, BlockPosition var4, BlockPosition var5, EnumBlockRotation var6, StructureBoundingBox var7, Random var8, boolean var9) {
/* 122 */     DefinedStructure var10 = a(var0);
/* 123 */     DefinedStructureInfo var11 = a(var6, var7, var9);
/*     */     
/* 125 */     if (var10.a(var1, var4, var5, var11, var8, 18)) {
/* 126 */       List<DefinedStructure.BlockInfo> var12 = DefinedStructure.a(var1, var4, var5, var11, a(var0, var4, var6, false));
/* 127 */       for (DefinedStructure.BlockInfo var14 : var12) {
/* 128 */         a(var1, var14, var4, var6, var8, var7);
/*     */       }
/*     */       
/* 131 */       return true;
/*     */     } 
/* 133 */     return false;
/*     */   }
/*     */   
/*     */   protected DefinedStructureInfo a(EnumBlockRotation var0, StructureBoundingBox var1, boolean var2) {
/* 137 */     DefinedStructureInfo var3 = new DefinedStructureInfo();
/* 138 */     var3.a(var1);
/* 139 */     var3.a(var0);
/* 140 */     var3.c(true);
/* 141 */     var3.a(false);
/* 142 */     var3.a(DefinedStructureProcessorBlockIgnore.b);
/* 143 */     var3.d(true);
/* 144 */     if (!var2) {
/* 145 */       var3.a(DefinedStructureProcessorJigsawReplacement.b);
/*     */     }
/* 147 */     ((ProcessorList)this.d.get()).a().forEach(var3::a);
/* 148 */     e().c().forEach(var3::a);
/* 149 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenFeatureDefinedStructurePools<?> a() {
/* 154 */     return WorldGenFeatureDefinedStructurePools.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 159 */     return "Single[" + this.c + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDefinedStructurePoolSingle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */