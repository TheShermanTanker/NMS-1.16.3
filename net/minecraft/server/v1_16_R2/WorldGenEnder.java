/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.cache.CacheBuilder;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function5;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.stream.Collector;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.IntStream;
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
/*     */ public class WorldGenEnder
/*     */   extends WorldGenerator<WorldGenFeatureEndSpikeConfiguration>
/*     */ {
/*  34 */   private static final LoadingCache<Long, List<Spike>> a = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.MINUTES).build(new b());
/*     */   
/*     */   public WorldGenEnder(Codec<WorldGenFeatureEndSpikeConfiguration> var0) {
/*  37 */     super(var0);
/*     */   }
/*     */   
/*     */   public static List<Spike> a(GeneratorAccessSeed var0) {
/*  41 */     Random var1 = new Random(var0.getSeed());
/*  42 */     long var2 = var1.nextLong() & 0xFFFFL;
/*  43 */     return (List<Spike>)a.getUnchecked(Long.valueOf(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEndSpikeConfiguration var4) {
/*  48 */     List<Spike> var5 = var4.c();
/*  49 */     if (var5.isEmpty()) {
/*  50 */       var5 = a(var0);
/*     */     }
/*     */     
/*  53 */     for (Spike var7 : var5) {
/*  54 */       if (var7.a(var3)) {
/*  55 */         a(var0, var2, var4, var7);
/*     */       }
/*     */     } 
/*     */     
/*  59 */     return true;
/*     */   }
/*     */   
/*     */   private void a(WorldAccess var0, Random var1, WorldGenFeatureEndSpikeConfiguration var2, Spike var3) {
/*  63 */     int var4 = var3.c();
/*  64 */     for (BlockPosition var6 : BlockPosition.a(new BlockPosition(var3.a() - var4, 0, var3.b() - var4), new BlockPosition(var3.a() + var4, var3.d() + 10, var3.b() + var4))) {
/*  65 */       if (var6.distanceSquared(var3.a(), var6.getY(), var3.b(), false) <= (var4 * var4 + 1) && var6.getY() < var3.d()) {
/*  66 */         a(var0, var6, Blocks.OBSIDIAN.getBlockData()); continue;
/*  67 */       }  if (var6.getY() > 65) {
/*  68 */         a(var0, var6, Blocks.AIR.getBlockData());
/*     */       }
/*     */     } 
/*     */     
/*  72 */     if (var3.e()) {
/*  73 */       int i = -2;
/*  74 */       int var6 = 2;
/*  75 */       int var7 = 3;
/*     */       
/*  77 */       BlockPosition.MutableBlockPosition var8 = new BlockPosition.MutableBlockPosition();
/*  78 */       for (int var9 = -2; var9 <= 2; var9++) {
/*  79 */         for (int var10 = -2; var10 <= 2; var10++) {
/*  80 */           for (int var11 = 0; var11 <= 3; var11++) {
/*  81 */             boolean var12 = (MathHelper.a(var9) == 2);
/*  82 */             boolean var13 = (MathHelper.a(var10) == 2);
/*  83 */             boolean var14 = (var11 == 3);
/*     */             
/*  85 */             if (var12 || var13 || var14) {
/*  86 */               boolean var15 = (var9 == -2 || var9 == 2 || var14);
/*  87 */               boolean var16 = (var10 == -2 || var10 == 2 || var14);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*  93 */               IBlockData var17 = Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf((var15 && var10 != -2))).set(BlockIronBars.SOUTH, Boolean.valueOf((var15 && var10 != 2))).set(BlockIronBars.WEST, Boolean.valueOf((var16 && var9 != -2))).set(BlockIronBars.EAST, Boolean.valueOf((var16 && var9 != 2)));
/*     */               
/*  95 */               a(var0, var8.d(var3.a() + var9, var3.d() + var11, var3.b() + var10), var17);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 102 */     EntityEnderCrystal var5 = EntityTypes.END_CRYSTAL.a(var0.getMinecraftWorld());
/* 103 */     var5.setBeamTarget(var2.d());
/* 104 */     var5.setInvulnerable(var2.b());
/* 105 */     var5.setPositionRotation(var3.a() + 0.5D, (var3.d() + 1), var3.b() + 0.5D, var1.nextFloat() * 360.0F, 0.0F);
/* 106 */     var0.addEntity(var5);
/* 107 */     a(var0, new BlockPosition(var3.a(), var3.d(), var3.b()), Blocks.BEDROCK.getBlockData());
/*     */   }
/*     */   
/*     */   public static class Spike { static {
/* 111 */       a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("centerX").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.INT.fieldOf("centerZ").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.INT.fieldOf("radius").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.INT.fieldOf("height").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.BOOL.fieldOf("guarded").orElse(Boolean.valueOf(false)).forGetter(())).apply((Applicative)var0, Spike::new));
/*     */     }
/*     */ 
/*     */     
/*     */     public static final Codec<Spike> a;
/*     */     
/*     */     private final int b;
/*     */     
/*     */     private final int c;
/*     */     
/*     */     private final int d;
/*     */     private final int e;
/*     */     private final boolean f;
/*     */     private final AxisAlignedBB g;
/*     */     
/*     */     public Spike(int var0, int var1, int var2, int var3, boolean var4) {
/* 127 */       this.b = var0;
/* 128 */       this.c = var1;
/* 129 */       this.d = var2;
/* 130 */       this.e = var3;
/* 131 */       this.f = var4;
/*     */       
/* 133 */       this.g = new AxisAlignedBB((var0 - var2), 0.0D, (var1 - var2), (var0 + var2), 256.0D, (var1 + var2));
/*     */     }
/*     */     
/*     */     public boolean a(BlockPosition var0) {
/* 137 */       return (var0.getX() >> 4 == this.b >> 4 && var0
/* 138 */         .getZ() >> 4 == this.c >> 4);
/*     */     }
/*     */     
/*     */     public int a() {
/* 142 */       return this.b;
/*     */     }
/*     */     
/*     */     public int b() {
/* 146 */       return this.c;
/*     */     }
/*     */     
/*     */     public int c() {
/* 150 */       return this.d;
/*     */     }
/*     */     
/*     */     public int d() {
/* 154 */       return this.e;
/*     */     }
/*     */     
/*     */     public boolean e() {
/* 158 */       return this.f;
/*     */     }
/*     */     
/*     */     public AxisAlignedBB f() {
/* 162 */       return this.g;
/*     */     } }
/*     */   
/*     */   static class b extends CacheLoader<Long, List<Spike>> {
/*     */     private b() {}
/*     */     
/*     */     public List<WorldGenEnder.Spike> load(Long var0) {
/* 169 */       List<Integer> var1 = IntStream.range(0, 10).boxed().collect((Collector)Collectors.toList());
/* 170 */       Collections.shuffle(var1, new Random(var0.longValue()));
/*     */       
/* 172 */       List<WorldGenEnder.Spike> var2 = Lists.newArrayList();
/* 173 */       for (int var3 = 0; var3 < 10; var3++) {
/* 174 */         int var4 = MathHelper.floor(42.0D * Math.cos(2.0D * (-3.141592653589793D + 0.3141592653589793D * var3)));
/* 175 */         int var5 = MathHelper.floor(42.0D * Math.sin(2.0D * (-3.141592653589793D + 0.3141592653589793D * var3)));
/* 176 */         int var6 = ((Integer)var1.get(var3)).intValue();
/* 177 */         int var7 = 2 + var6 / 3;
/* 178 */         int var8 = 76 + var6 * 3;
/* 179 */         boolean var9 = (var6 == 1 || var6 == 2);
/* 180 */         var2.add(new WorldGenEnder.Spike(var4, var5, var7, var8, var9));
/*     */       } 
/* 182 */       return var2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenEnder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */