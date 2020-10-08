/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function3;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrays;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenFeatureDefinedStructurePoolTemplate
/*     */ {
/*  32 */   private static final Logger LOGGER = LogManager.getLogger(); public static final Codec<WorldGenFeatureDefinedStructurePoolTemplate> a;
/*     */   static {
/*  34 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)MinecraftKey.a.fieldOf("name").forGetter(WorldGenFeatureDefinedStructurePoolTemplate::b), (App)MinecraftKey.a.fieldOf("fallback").forGetter(WorldGenFeatureDefinedStructurePoolTemplate::a), (App)Codec.mapPair(WorldGenFeatureDefinedStructurePoolStructure.e.fieldOf("element"), Codec.INT.fieldOf("weight")).codec().listOf().promotePartial(SystemUtils.a("Pool element: ", LOGGER::error)).fieldOf("elements").forGetter(())).apply((Applicative)var0, WorldGenFeatureDefinedStructurePoolTemplate::new));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final MinecraftKey d;
/*     */   
/*     */   private final List<Pair<WorldGenFeatureDefinedStructurePoolStructure, Integer>> e;
/*     */   
/*  43 */   public static final Codec<Supplier<WorldGenFeatureDefinedStructurePoolTemplate>> b = RegistryFileCodec.a(IRegistry.ax, a); private final List<WorldGenFeatureDefinedStructurePoolStructure> f;
/*     */   private final MinecraftKey g;
/*     */   
/*  46 */   public enum Matching implements INamable { TERRAIN_MATCHING("terrain_matching", 
/*     */       
/*  48 */       ImmutableList.of(new DefinedStructureProcessorGravity(HeightMap.Type.WORLD_SURFACE_WG, -1))),
/*     */     
/*  50 */     RIGID("rigid", 
/*     */       
/*  52 */       ImmutableList.of());
/*     */ 
/*     */     
/*  55 */     public static final Codec<Matching> c = INamable.a(Matching::values, Matching::a); private static final Map<String, Matching> d;
/*     */     static {
/*  57 */       d = (Map<String, Matching>)Arrays.<Matching>stream(values()).collect(Collectors.toMap(Matching::b, var0 -> var0));
/*     */     }
/*     */     private final String e; private final ImmutableList<DefinedStructureProcessor> f;
/*     */     
/*     */     Matching(String var2, ImmutableList<DefinedStructureProcessor> var3) {
/*  62 */       this.e = var2;
/*  63 */       this.f = var3;
/*     */     }
/*     */     
/*     */     public String b() {
/*  67 */       return this.e;
/*     */     }
/*     */     
/*     */     public static Matching a(String var0) {
/*  71 */       return d.get(var0);
/*     */     }
/*     */     
/*     */     public ImmutableList<DefinedStructureProcessor> c() {
/*  75 */       return this.f;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/*  80 */       return this.e;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   private int h = Integer.MIN_VALUE;
/*     */   
/*     */   public WorldGenFeatureDefinedStructurePoolTemplate(MinecraftKey var0, MinecraftKey var1, List<Pair<WorldGenFeatureDefinedStructurePoolStructure, Integer>> var2) {
/*  91 */     this.d = var0;
/*  92 */     this.e = var2;
/*  93 */     this.f = Lists.newArrayList();
/*  94 */     for (Pair<WorldGenFeatureDefinedStructurePoolStructure, Integer> var4 : var2) {
/*  95 */       WorldGenFeatureDefinedStructurePoolStructure var5 = (WorldGenFeatureDefinedStructurePoolStructure)var4.getFirst();
/*  96 */       for (int var6 = 0; var6 < ((Integer)var4.getSecond()).intValue(); var6++) {
/*  97 */         this.f.add(var5);
/*     */       }
/*     */     } 
/*     */     
/* 101 */     this.g = var1;
/*     */   }
/*     */   
/*     */   public WorldGenFeatureDefinedStructurePoolTemplate(MinecraftKey var0, MinecraftKey var1, List<Pair<Function<Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer>> var2, Matching var3) {
/* 105 */     this.d = var0;
/* 106 */     this.e = Lists.newArrayList();
/* 107 */     this.f = Lists.newArrayList();
/* 108 */     for (Pair<Function<Matching, ? extends WorldGenFeatureDefinedStructurePoolStructure>, Integer> var5 : var2) {
/* 109 */       WorldGenFeatureDefinedStructurePoolStructure var6 = ((Function<Matching, WorldGenFeatureDefinedStructurePoolStructure>)var5.getFirst()).apply(var3);
/* 110 */       this.e.add(Pair.of(var6, var5.getSecond()));
/* 111 */       for (int var7 = 0; var7 < ((Integer)var5.getSecond()).intValue(); var7++) {
/* 112 */         this.f.add(var6);
/*     */       }
/*     */     } 
/*     */     
/* 116 */     this.g = var1;
/*     */   }
/*     */   
/*     */   public int a(DefinedStructureManager var0) {
/* 120 */     if (this.h == Integer.MIN_VALUE) {
/* 121 */       this.h = this.f.stream().mapToInt(var1 -> var1.a(var0, BlockPosition.ZERO, EnumBlockRotation.NONE).e()).max().orElse(0);
/*     */     }
/* 123 */     return this.h;
/*     */   }
/*     */   
/*     */   public MinecraftKey a() {
/* 127 */     return this.g;
/*     */   }
/*     */   
/*     */   public WorldGenFeatureDefinedStructurePoolStructure a(Random var0) {
/* 131 */     return this.f.get(var0.nextInt(this.f.size()));
/*     */   }
/*     */   
/*     */   public List<WorldGenFeatureDefinedStructurePoolStructure> b(Random var0) {
/* 135 */     return (List<WorldGenFeatureDefinedStructurePoolStructure>)ImmutableList.copyOf(ObjectArrays.shuffle(this.f.toArray((Object[])new WorldGenFeatureDefinedStructurePoolStructure[0]), var0));
/*     */   }
/*     */   
/*     */   public MinecraftKey b() {
/* 139 */     return this.d;
/*     */   }
/*     */   
/*     */   public int c() {
/* 143 */     return this.f.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDefinedStructurePoolTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */