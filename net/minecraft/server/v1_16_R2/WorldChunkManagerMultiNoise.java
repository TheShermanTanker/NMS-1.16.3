/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import com.mojang.datafixers.util.Function3;
/*     */ import com.mojang.datafixers.util.Function6;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.MapCodec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ public class WorldChunkManagerMultiNoise extends WorldChunkManager {
/*     */   static class a {
/*     */     private final int b;
/*     */     private final DoubleList c;
/*     */     public static final Codec<a> a;
/*     */     
/*     */     static {
/*  33 */       a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("firstOctave").forGetter(a::a), (App)Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(a::b)).apply((Applicative)var0, a::new));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public a(int var0, List<Double> var1) {
/*  39 */       this.b = var0;
/*  40 */       this.c = (DoubleList)new DoubleArrayList(var1);
/*     */     }
/*     */     
/*     */     public int a() {
/*  44 */       return this.b;
/*     */     }
/*     */     
/*     */     public DoubleList b() {
/*  48 */       return this.c;
/*     */     }
/*     */   }
/*     */   
/*  52 */   private static final a g = new a(-7, (List<Double>)ImmutableList.of(Double.valueOf(1.0D), Double.valueOf(1.0D)));
/*     */   static {
/*  54 */     e = RecordCodecBuilder.mapCodec(var0 -> var0.group((App)Codec.LONG.fieldOf("seed").forGetter(()), (App)RecordCodecBuilder.create(()).listOf().fieldOf("biomes").forGetter(()), (App)a.a.fieldOf("temperature_noise").forGetter(()), (App)a.a.fieldOf("humidity_noise").forGetter(()), (App)a.a.fieldOf("altitude_noise").forGetter(()), (App)a.a.fieldOf("weirdness_noise").forGetter(())).apply((Applicative)var0, WorldChunkManagerMultiNoise::new));
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
/*     */ 
/*     */     
/*  72 */     f = Codec.mapEither(c.a, e).xmap(var0 -> (WorldChunkManagerMultiNoise)var0.map(c::d, Function.identity()), var0 -> (Either)var0.d().<Either>map(Either::left).orElseGet(())).codec();
/*     */   }
/*     */ 
/*     */   
/*     */   public static final MapCodec<WorldChunkManagerMultiNoise> e;
/*     */   
/*     */   public static final Codec<WorldChunkManagerMultiNoise> f;
/*     */   private final a h;
/*     */   private final a i;
/*     */   private final a j;
/*     */   private final a k;
/*     */   private final NoiseGeneratorNormal l;
/*     */   private final NoiseGeneratorNormal m;
/*     */   private final NoiseGeneratorNormal n;
/*     */   private final NoiseGeneratorNormal o;
/*     */   private final List<Pair<BiomeBase.c, Supplier<BiomeBase>>> p;
/*     */   private final boolean q;
/*     */   private final long r;
/*     */   private final Optional<Pair<IRegistry<BiomeBase>, b>> s;
/*     */   
/*     */   private WorldChunkManagerMultiNoise(long var0, List<Pair<BiomeBase.c, Supplier<BiomeBase>>> var2, Optional<Pair<IRegistry<BiomeBase>, b>> var3) {
/*  93 */     this(var0, var2, g, g, g, g, var3);
/*     */   }
/*     */   
/*     */   private WorldChunkManagerMultiNoise(long var0, List<Pair<BiomeBase.c, Supplier<BiomeBase>>> var2, a var3, a var4, a var5, a var6) {
/*  97 */     this(var0, var2, var3, var4, var5, var6, Optional.empty());
/*     */   }
/*     */   
/*     */   private WorldChunkManagerMultiNoise(long var0, List<Pair<BiomeBase.c, Supplier<BiomeBase>>> var2, a var3, a var4, a var5, a var6, Optional<Pair<IRegistry<BiomeBase>, b>> var7) {
/* 101 */     super(var2.stream().map(Pair::getSecond));
/* 102 */     this.r = var0;
/* 103 */     this.s = var7;
/*     */     
/* 105 */     this.h = var3;
/* 106 */     this.i = var4;
/* 107 */     this.j = var5;
/* 108 */     this.k = var6;
/*     */     
/* 110 */     this.l = NoiseGeneratorNormal.a(new SeededRandom(var0), var3.a(), var3.b());
/* 111 */     this.m = NoiseGeneratorNormal.a(new SeededRandom(var0 + 1L), var4.a(), var4.b());
/* 112 */     this.n = NoiseGeneratorNormal.a(new SeededRandom(var0 + 2L), var5.a(), var5.b());
/* 113 */     this.o = NoiseGeneratorNormal.a(new SeededRandom(var0 + 3L), var6.a(), var6.b());
/* 114 */     this.p = var2;
/* 115 */     this.q = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Codec<? extends WorldChunkManager> a() {
/* 120 */     return (Codec)f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Optional<c> d() {
/* 129 */     return this.s.map(var0 -> new c((b)var0.getSecond(), (IRegistry)var0.getFirst(), this.r));
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeBase getBiome(int var0, int var1, int var2) {
/* 134 */     int var3 = this.q ? var1 : 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     BiomeBase.c var4 = new BiomeBase.c((float)this.l.a(var0, var3, var2), (float)this.m.a(var0, var3, var2), (float)this.n.a(var0, var3, var2), (float)this.o.a(var0, var3, var2), 0.0F);
/*     */ 
/*     */ 
/*     */     
/* 144 */     return this.p.stream()
/* 145 */       .min(Comparator.comparing(var1 -> Float.valueOf(((BiomeBase.c)var1.getFirst()).a(var0))))
/* 146 */       .map(Pair::getSecond)
/* 147 */       .map(Supplier::get)
/* 148 */       .orElse(BiomeRegistry.b);
/*     */   }
/*     */   
/*     */   public boolean b(long var0) {
/* 152 */     return (this.r == var0 && this.s.isPresent() && Objects.equals(((Pair)this.s.get()).getSecond(), b.a));
/*     */   }
/*     */   
/*     */   static final class c { static {
/* 156 */       a = RecordCodecBuilder.mapCodec(var0 -> var0.group((App)MinecraftKey.a.flatXmap((), ()).fieldOf("preset").stable().forGetter(c::a), (App)RegistryLookupCodec.<BiomeBase>a(IRegistry.ay).forGetter(c::b), (App)Codec.LONG.fieldOf("seed").stable().forGetter(c::c)).apply((Applicative)var0, var0.stable(c::new)));
/*     */     }
/*     */ 
/*     */     
/*     */     public static final MapCodec<c> a;
/*     */     
/*     */     private final WorldChunkManagerMultiNoise.b b;
/*     */     
/*     */     private final IRegistry<BiomeBase> c;
/*     */     
/*     */     private final long d;
/*     */ 
/*     */     
/*     */     private c(WorldChunkManagerMultiNoise.b var0, IRegistry<BiomeBase> var1, long var2) {
/* 170 */       this.b = var0;
/* 171 */       this.c = var1;
/* 172 */       this.d = var2;
/*     */     }
/*     */     
/*     */     public WorldChunkManagerMultiNoise.b a() {
/* 176 */       return this.b;
/*     */     }
/*     */     
/*     */     public IRegistry<BiomeBase> b() {
/* 180 */       return this.c;
/*     */     }
/*     */     
/*     */     public long c() {
/* 184 */       return this.d;
/*     */     }
/*     */     
/*     */     public WorldChunkManagerMultiNoise d() {
/* 188 */       return this.b.a(this.c, this.d);
/*     */     } }
/*     */ 
/*     */   
/*     */   public static class b {
/* 193 */     private static final Map<MinecraftKey, b> b = Maps.newHashMap();
/*     */     static {
/* 195 */       a = new b(new MinecraftKey("nether"), (var0, var1, var2) -> new WorldChunkManagerMultiNoise(var2.longValue(), (List)ImmutableList.of(Pair.of(new BiomeBase.c(0.0F, 0.0F, 0.0F, 0.0F, 0.0F), ()), Pair.of(new BiomeBase.c(0.0F, -0.5F, 0.0F, 0.0F, 0.0F), ()), Pair.of(new BiomeBase.c(0.4F, 0.0F, 0.0F, 0.0F, 0.0F), ()), Pair.of(new BiomeBase.c(0.0F, 0.5F, 0.0F, 0.0F, 0.375F), ()), Pair.of(new BiomeBase.c(-0.5F, 0.0F, 0.0F, 0.0F, 0.175F), ())), Optional.of(Pair.of(var1, var0))));
/*     */     }
/*     */ 
/*     */     
/*     */     public static final b a;
/*     */     
/*     */     private final MinecraftKey c;
/*     */     
/*     */     private final Function3<b, IRegistry<BiomeBase>, Long, WorldChunkManagerMultiNoise> d;
/*     */ 
/*     */     
/*     */     public b(MinecraftKey var0, Function3<b, IRegistry<BiomeBase>, Long, WorldChunkManagerMultiNoise> var1) {
/* 207 */       this.c = var0;
/* 208 */       this.d = var1;
/* 209 */       b.put(var0, this);
/*     */     }
/*     */     
/*     */     public WorldChunkManagerMultiNoise a(IRegistry<BiomeBase> var0, long var1) {
/* 213 */       return (WorldChunkManagerMultiNoise)this.d.apply(this, var0, Long.valueOf(var1));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldChunkManagerMultiNoise.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */