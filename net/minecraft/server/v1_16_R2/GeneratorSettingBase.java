/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function8;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class GeneratorSettingBase
/*     */ {
/*     */   public static final Codec<GeneratorSettingBase> a;
/*     */   
/*     */   static {
/*  23 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)StructureSettings.a.fieldOf("structures").forGetter(GeneratorSettingBase::a), (App)NoiseSettings.a.fieldOf("noise").forGetter(GeneratorSettingBase::b), (App)IBlockData.b.fieldOf("default_block").forGetter(GeneratorSettingBase::c), (App)IBlockData.b.fieldOf("default_fluid").forGetter(GeneratorSettingBase::d), (App)Codec.intRange(-20, 276).fieldOf("bedrock_roof_position").forGetter(GeneratorSettingBase::e), (App)Codec.intRange(-20, 276).fieldOf("bedrock_floor_position").forGetter(GeneratorSettingBase::f), (App)Codec.intRange(0, 255).fieldOf("sea_level").forGetter(GeneratorSettingBase::g), (App)Codec.BOOL.fieldOf("disable_mob_generation").forGetter(GeneratorSettingBase::h)).apply((Applicative)var0, GeneratorSettingBase::new));
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
/*  34 */   public static final Codec<Supplier<GeneratorSettingBase>> b = RegistryFileCodec.a(IRegistry.ar, a);
/*     */   
/*     */   private final StructureSettings i;
/*     */   
/*     */   private final NoiseSettings j;
/*     */   
/*     */   private final IBlockData k;
/*     */   
/*     */   private final IBlockData l;
/*     */   private final int m;
/*     */   private final int n;
/*     */   private final int o;
/*     */   private final boolean p;
/*     */   
/*     */   private GeneratorSettingBase(StructureSettings var0, NoiseSettings var1, IBlockData var2, IBlockData var3, int var4, int var5, int var6, boolean var7) {
/*  49 */     this.i = var0;
/*  50 */     this.j = var1;
/*     */     
/*  52 */     this.k = var2;
/*  53 */     this.l = var3;
/*     */     
/*  55 */     this.m = var4;
/*  56 */     this.n = var5;
/*  57 */     this.o = var6;
/*     */     
/*  59 */     this.p = var7;
/*     */   }
/*     */   
/*     */   public StructureSettings a() {
/*  63 */     return this.i;
/*     */   }
/*     */   
/*     */   public NoiseSettings b() {
/*  67 */     return this.j;
/*     */   }
/*     */   
/*     */   public IBlockData c() {
/*  71 */     return this.k;
/*     */   }
/*     */   
/*     */   public IBlockData d() {
/*  75 */     return this.l;
/*     */   }
/*     */   
/*     */   public int e() {
/*  79 */     return this.m;
/*     */   }
/*     */   
/*     */   public int f() {
/*  83 */     return this.n;
/*     */   }
/*     */   
/*     */   public int g() {
/*  87 */     return this.o;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   protected boolean h() {
/*  92 */     return this.p;
/*     */   }
/*     */   
/*     */   public boolean a(ResourceKey<GeneratorSettingBase> var0) {
/*  96 */     return Objects.equals(this, RegistryGeneration.j.a(var0));
/*     */   }
/*     */   
/*     */   private static GeneratorSettingBase a(ResourceKey<GeneratorSettingBase> var0, GeneratorSettingBase var1) {
/* 100 */     RegistryGeneration.a(RegistryGeneration.j, var0.a(), var1);
/* 101 */     return var1;
/*     */   }
/*     */   
/* 104 */   public static final ResourceKey<GeneratorSettingBase> c = ResourceKey.a(IRegistry.ar, new MinecraftKey("overworld"));
/* 105 */   public static final ResourceKey<GeneratorSettingBase> d = ResourceKey.a(IRegistry.ar, new MinecraftKey("amplified"));
/* 106 */   public static final ResourceKey<GeneratorSettingBase> e = ResourceKey.a(IRegistry.ar, new MinecraftKey("nether"));
/* 107 */   public static final ResourceKey<GeneratorSettingBase> f = ResourceKey.a(IRegistry.ar, new MinecraftKey("end"));
/* 108 */   public static final ResourceKey<GeneratorSettingBase> g = ResourceKey.a(IRegistry.ar, new MinecraftKey("caves"));
/* 109 */   public static final ResourceKey<GeneratorSettingBase> h = ResourceKey.a(IRegistry.ar, new MinecraftKey("floating_islands"));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   private static final GeneratorSettingBase q = a(c, a(new StructureSettings(true), false, c.a())); static {
/* 115 */     a(d, a(new StructureSettings(true), true, d.a()));
/* 116 */     a(e, a(new StructureSettings(false), Blocks.NETHERRACK.getBlockData(), Blocks.LAVA.getBlockData(), e.a()));
/* 117 */     a(f, a(new StructureSettings(false), Blocks.END_STONE.getBlockData(), Blocks.AIR.getBlockData(), f.a(), true, true));
/* 118 */     a(g, a(new StructureSettings(true), Blocks.STONE.getBlockData(), Blocks.WATER.getBlockData(), g.a()));
/* 119 */     a(h, a(new StructureSettings(true), Blocks.STONE.getBlockData(), Blocks.WATER.getBlockData(), h.a(), false, false));
/*     */   }
/*     */   
/*     */   public static GeneratorSettingBase i() {
/* 123 */     return q;
/*     */   }
/*     */   
/*     */   private static GeneratorSettingBase a(StructureSettings var0, IBlockData var1, IBlockData var2, MinecraftKey var3, boolean var4, boolean var5) {
/* 127 */     return new GeneratorSettingBase(var0, new NoiseSettings(128, new NoiseSamplingSettings(2.0D, 1.0D, 80.0D, 160.0D), new NoiseSlideSettings(-3000, 64, -46), new NoiseSlideSettings(-30, 7, 1), 2, 1, 0.0D, 0.0D, true, false, var5, false), var1, var2, -10, -10, 0, var4);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static GeneratorSettingBase a(StructureSettings var0, IBlockData var1, IBlockData var2, MinecraftKey var3) {
/* 151 */     Map<StructureGenerator<?>, StructureSettingsFeature> var4 = Maps.newHashMap((Map)StructureSettings.b);
/* 152 */     var4.put(StructureGenerator.RUINED_PORTAL, new StructureSettingsFeature(25, 10, 34222645));
/*     */     
/* 154 */     return new GeneratorSettingBase(new StructureSettings(Optional.ofNullable(var0.b()), var4), new NoiseSettings(128, new NoiseSamplingSettings(1.0D, 3.0D, 80.0D, 60.0D), new NoiseSlideSettings(120, 3, 0), new NoiseSlideSettings(320, 4, -1), 1, 2, 0.0D, 0.019921875D, false, false, false, false), var1, var2, 0, 0, 32, false);
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
/*     */   private static GeneratorSettingBase a(StructureSettings var0, boolean var1, MinecraftKey var2) {
/* 182 */     double var3 = 0.9999999814507745D;
/* 183 */     return new GeneratorSettingBase(var0, new NoiseSettings(256, new NoiseSamplingSettings(0.9999999814507745D, 0.9999999814507745D, 80.0D, 160.0D), new NoiseSlideSettings(-10, 3, 0), new NoiseSlideSettings(-30, 0, 0), 1, 2, 1.0D, -0.46875D, true, true, false, var1), Blocks.STONE
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
/* 197 */         .getBlockData(), Blocks.WATER
/* 198 */         .getBlockData(), -10, 0, 63, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GeneratorSettingBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */