/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function12;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Arrays;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.OptionalInt;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ public class BiomeFog {
/*     */   static {
/*  16 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("fog_color").forGetter(()), (App)Codec.INT.fieldOf("water_color").forGetter(()), (App)Codec.INT.fieldOf("water_fog_color").forGetter(()), (App)Codec.INT.fieldOf("sky_color").forGetter(()), (App)Codec.INT.optionalFieldOf("foliage_color").forGetter(()), (App)Codec.INT.optionalFieldOf("grass_color").forGetter(()), (App)GrassColor.d.optionalFieldOf("grass_color_modifier", GrassColor.NONE).forGetter(()), (App)BiomeParticles.a.optionalFieldOf("particle").forGetter(()), (App)SoundEffect.a.optionalFieldOf("ambient_sound").forGetter(()), (App)CaveSoundSettings.a.optionalFieldOf("mood_sound").forGetter(()), (App)CaveSound.a.optionalFieldOf("additions_sound").forGetter(()), (App)Music.a.optionalFieldOf("music").forGetter(())).apply((Applicative)var0, BiomeFog::new));
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Codec<BiomeFog> a;
/*     */   
/*     */   private final int b;
/*     */   
/*     */   private final int c;
/*     */   
/*     */   private final int d;
/*     */   
/*     */   private final int e;
/*     */   
/*     */   private final Optional<Integer> f;
/*     */   
/*     */   private final Optional<Integer> g;
/*     */   
/*     */   private final GrassColor h;
/*     */   
/*     */   private final Optional<BiomeParticles> i;
/*     */   
/*     */   private final Optional<SoundEffect> j;
/*     */   
/*     */   private final Optional<CaveSoundSettings> k;
/*     */   private final Optional<CaveSound> l;
/*     */   private final Optional<Music> m;
/*     */   
/*     */   private BiomeFog(int var0, int var1, int var2, int var3, Optional<Integer> var4, Optional<Integer> var5, GrassColor var6, Optional<BiomeParticles> var7, Optional<SoundEffect> var8, Optional<CaveSoundSettings> var9, Optional<CaveSound> var10, Optional<Music> var11) {
/*  45 */     this.b = var0;
/*  46 */     this.c = var1;
/*  47 */     this.d = var2;
/*  48 */     this.e = var3;
/*  49 */     this.f = var4;
/*  50 */     this.g = var5;
/*  51 */     this.h = var6;
/*  52 */     this.i = var7;
/*  53 */     this.j = var8;
/*  54 */     this.k = var9;
/*  55 */     this.l = var10;
/*  56 */     this.m = var11;
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
/*     */   public static class a
/*     */   {
/* 108 */     private OptionalInt a = OptionalInt.empty();
/* 109 */     private OptionalInt b = OptionalInt.empty();
/* 110 */     private OptionalInt c = OptionalInt.empty();
/* 111 */     private OptionalInt d = OptionalInt.empty();
/* 112 */     private Optional<Integer> e = Optional.empty();
/* 113 */     private Optional<Integer> f = Optional.empty();
/* 114 */     private BiomeFog.GrassColor g = BiomeFog.GrassColor.NONE;
/* 115 */     private Optional<BiomeParticles> h = Optional.empty();
/* 116 */     private Optional<SoundEffect> i = Optional.empty();
/* 117 */     private Optional<CaveSoundSettings> j = Optional.empty();
/* 118 */     private Optional<CaveSound> k = Optional.empty();
/* 119 */     private Optional<Music> l = Optional.empty();
/*     */     
/*     */     public a a(int var0) {
/* 122 */       this.a = OptionalInt.of(var0);
/* 123 */       return this;
/*     */     }
/*     */     
/*     */     public a b(int var0) {
/* 127 */       this.b = OptionalInt.of(var0);
/* 128 */       return this;
/*     */     }
/*     */     
/*     */     public a c(int var0) {
/* 132 */       this.c = OptionalInt.of(var0);
/* 133 */       return this;
/*     */     }
/*     */     
/*     */     public a d(int var0) {
/* 137 */       this.d = OptionalInt.of(var0);
/* 138 */       return this;
/*     */     }
/*     */     
/*     */     public a e(int var0) {
/* 142 */       this.e = Optional.of(Integer.valueOf(var0));
/* 143 */       return this;
/*     */     }
/*     */     
/*     */     public a f(int var0) {
/* 147 */       this.f = Optional.of(Integer.valueOf(var0));
/* 148 */       return this;
/*     */     }
/*     */     
/*     */     public a a(BiomeFog.GrassColor var0) {
/* 152 */       this.g = var0;
/* 153 */       return this;
/*     */     }
/*     */     
/*     */     public a a(BiomeParticles var0) {
/* 157 */       this.h = Optional.of(var0);
/* 158 */       return this;
/*     */     }
/*     */     
/*     */     public a a(SoundEffect var0) {
/* 162 */       this.i = Optional.of(var0);
/* 163 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CaveSoundSettings var0) {
/* 167 */       this.j = Optional.of(var0);
/* 168 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CaveSound var0) {
/* 172 */       this.k = Optional.of(var0);
/* 173 */       return this;
/*     */     }
/*     */     
/*     */     public a a(Music var0) {
/* 177 */       this.l = Optional.of(var0);
/* 178 */       return this;
/*     */     }
/*     */     
/*     */     public BiomeFog a() {
/* 182 */       return new BiomeFog(this.a
/* 183 */           .orElseThrow(() -> new IllegalStateException("Missing 'fog' color.")), this.b
/* 184 */           .orElseThrow(() -> new IllegalStateException("Missing 'water' color.")), this.c
/* 185 */           .orElseThrow(() -> new IllegalStateException("Missing 'water fog' color.")), this.d
/* 186 */           .orElseThrow(() -> new IllegalStateException("Missing 'sky' color.")), this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum GrassColor
/*     */     implements INamable
/*     */   {
/* 198 */     NONE("none")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*     */     },
/* 204 */     DARK_FOREST("dark_forest")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*     */     },
/* 210 */     SWAMP("swamp")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */     
/*     */     private final String e;
/*     */ 
/*     */     
/*     */     private static final Map<String, GrassColor> f;
/*     */ 
/*     */ 
/*     */     
/*     */     GrassColor(String var2) {
/*     */       this.e = var2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 229 */     public static final Codec<GrassColor> d = INamable.a(GrassColor::values, GrassColor::a);
/*     */     static {
/* 231 */       f = (Map<String, GrassColor>)Arrays.<GrassColor>stream(values()).collect(Collectors.toMap(GrassColor::b, var0 -> var0));
/*     */     }
/*     */     public String b() {
/* 234 */       return this.e;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 239 */       return this.e;
/*     */     }
/*     */     
/*     */     public static GrassColor a(String var0) {
/* 243 */       return f.get(var0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomeFog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */