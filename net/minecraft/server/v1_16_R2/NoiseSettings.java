/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ 
/*     */ public class NoiseSettings {
/*     */   static {
/*   9 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.intRange(0, 256).fieldOf("height").forGetter(NoiseSettings::a), (App)NoiseSamplingSettings.a.fieldOf("sampling").forGetter(NoiseSettings::b), (App)NoiseSlideSettings.a.fieldOf("top_slide").forGetter(NoiseSettings::c), (App)NoiseSlideSettings.a.fieldOf("bottom_slide").forGetter(NoiseSettings::d), (App)Codec.intRange(1, 4).fieldOf("size_horizontal").forGetter(NoiseSettings::e), (App)Codec.intRange(1, 4).fieldOf("size_vertical").forGetter(NoiseSettings::f), (App)Codec.DOUBLE.fieldOf("density_factor").forGetter(NoiseSettings::g), (App)Codec.DOUBLE.fieldOf("density_offset").forGetter(NoiseSettings::h), (App)Codec.BOOL.fieldOf("simplex_surface_noise").forGetter(NoiseSettings::i), (App)Codec.BOOL.optionalFieldOf("random_density_offset", Boolean.valueOf(false), Lifecycle.experimental()).forGetter(NoiseSettings::j), (App)Codec.BOOL.optionalFieldOf("island_noise_override", Boolean.valueOf(false), Lifecycle.experimental()).forGetter(NoiseSettings::k), (App)Codec.BOOL.optionalFieldOf("amplified", Boolean.valueOf(false), Lifecycle.experimental()).forGetter(NoiseSettings::l)).apply((Applicative)var0, NoiseSettings::new));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final Codec<NoiseSettings> a;
/*     */ 
/*     */   
/*     */   private final int b;
/*     */ 
/*     */   
/*     */   private final NoiseSamplingSettings c;
/*     */ 
/*     */   
/*     */   private final NoiseSlideSettings d;
/*     */   
/*     */   private final NoiseSlideSettings e;
/*     */   
/*     */   private final int f;
/*     */   
/*     */   private final int g;
/*     */   
/*     */   private final double h;
/*     */   
/*     */   private final double i;
/*     */   
/*     */   private final boolean j;
/*     */   
/*     */   private final boolean k;
/*     */   
/*     */   private final boolean l;
/*     */   
/*     */   private final boolean m;
/*     */ 
/*     */   
/*     */   public NoiseSettings(int var0, NoiseSamplingSettings var1, NoiseSlideSettings var2, NoiseSlideSettings var3, int var4, int var5, double var6, double var8, boolean var10, boolean var11, boolean var12, boolean var13) {
/*  45 */     this.b = var0;
/*     */     
/*  47 */     this.c = var1;
/*     */     
/*  49 */     this.d = var2;
/*  50 */     this.e = var3;
/*     */     
/*  52 */     this.f = var4;
/*  53 */     this.g = var5;
/*     */     
/*  55 */     this.h = var6;
/*  56 */     this.i = var8;
/*     */     
/*  58 */     this.j = var10;
/*  59 */     this.k = var11;
/*  60 */     this.l = var12;
/*  61 */     this.m = var13;
/*     */   }
/*     */   
/*     */   public int a() {
/*  65 */     return this.b;
/*     */   }
/*     */   
/*     */   public NoiseSamplingSettings b() {
/*  69 */     return this.c;
/*     */   }
/*     */   
/*     */   public NoiseSlideSettings c() {
/*  73 */     return this.d;
/*     */   }
/*     */   
/*     */   public NoiseSlideSettings d() {
/*  77 */     return this.e;
/*     */   }
/*     */   
/*     */   public int e() {
/*  81 */     return this.f;
/*     */   }
/*     */   
/*     */   public int f() {
/*  85 */     return this.g;
/*     */   }
/*     */   
/*     */   public double g() {
/*  89 */     return this.h;
/*     */   }
/*     */   
/*     */   public double h() {
/*  93 */     return this.i;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean i() {
/*  98 */     return this.j;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean j() {
/* 103 */     return this.k;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean k() {
/* 108 */     return this.l;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean l() {
/* 113 */     return this.m;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NoiseSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */