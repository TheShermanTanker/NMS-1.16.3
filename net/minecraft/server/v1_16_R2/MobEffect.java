/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ComparisonChain;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MobEffect
/*     */   implements Comparable<MobEffect>
/*     */ {
/*  14 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final MobEffectList b;
/*     */   
/*     */   private int duration;
/*     */   private int amplification;
/*     */   private boolean splash;
/*     */   private boolean ambient;
/*     */   private boolean showParticles;
/*     */   private boolean showIcon;
/*     */   @Nullable
/*     */   private MobEffect hiddenEffect;
/*     */   
/*     */   public MobEffect(MobEffectList var0) {
/*  28 */     this(var0, 0, 0);
/*     */   }
/*     */   
/*     */   public MobEffect(MobEffectList var0, int var1) {
/*  32 */     this(var0, var1, 0);
/*     */   }
/*     */   
/*     */   public MobEffect(MobEffectList var0, int var1, int var2) {
/*  36 */     this(var0, var1, var2, false, true);
/*     */   }
/*     */   
/*     */   public MobEffect(MobEffectList var0, int var1, int var2, boolean var3, boolean var4) {
/*  40 */     this(var0, var1, var2, var3, var4, var4);
/*     */   }
/*     */   
/*     */   public MobEffect(MobEffectList var0, int var1, int var2, boolean var3, boolean var4, boolean var5) {
/*  44 */     this(var0, var1, var2, var3, var4, var5, null);
/*     */   }
/*     */   
/*     */   public MobEffect(MobEffectList var0, int var1, int var2, boolean var3, boolean var4, boolean var5, @Nullable MobEffect var6) {
/*  48 */     this.b = var0;
/*  49 */     this.duration = var1;
/*  50 */     this.amplification = var2;
/*  51 */     this.ambient = var3;
/*  52 */     this.showParticles = var4;
/*  53 */     this.showIcon = var5;
/*  54 */     this.hiddenEffect = var6;
/*     */   }
/*     */   
/*     */   public MobEffect(MobEffect var0) {
/*  58 */     this.b = var0.b;
/*  59 */     a(var0);
/*     */   }
/*     */   
/*     */   void a(MobEffect var0) {
/*  63 */     this.duration = var0.duration;
/*  64 */     this.amplification = var0.amplification;
/*  65 */     this.ambient = var0.ambient;
/*  66 */     this.showParticles = var0.showParticles;
/*  67 */     this.showIcon = var0.showIcon;
/*     */   }
/*     */   
/*     */   public boolean b(MobEffect var0) {
/*  71 */     if (this.b != var0.b) {
/*  72 */       LOGGER.warn("This method should only be called for matching effects!");
/*     */     }
/*  74 */     boolean var1 = false;
/*  75 */     if (var0.amplification > this.amplification) {
/*  76 */       if (var0.duration < this.duration) {
/*  77 */         MobEffect var2 = this.hiddenEffect;
/*  78 */         this.hiddenEffect = new MobEffect(this);
/*  79 */         this.hiddenEffect.hiddenEffect = var2;
/*     */       } 
/*  81 */       this.amplification = var0.amplification;
/*  82 */       this.duration = var0.duration;
/*  83 */       var1 = true;
/*  84 */     } else if (var0.duration > this.duration) {
/*  85 */       if (var0.amplification == this.amplification) {
/*  86 */         this.duration = var0.duration;
/*  87 */         var1 = true;
/*     */       }
/*  89 */       else if (this.hiddenEffect == null) {
/*  90 */         this.hiddenEffect = new MobEffect(var0);
/*     */       } else {
/*  92 */         this.hiddenEffect.b(var0);
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     if ((!var0.ambient && this.ambient) || var1) {
/*  97 */       this.ambient = var0.ambient;
/*  98 */       var1 = true;
/*     */     } 
/* 100 */     if (var0.showParticles != this.showParticles) {
/* 101 */       this.showParticles = var0.showParticles;
/* 102 */       var1 = true;
/*     */     } 
/* 104 */     if (var0.showIcon != this.showIcon) {
/* 105 */       this.showIcon = var0.showIcon;
/* 106 */       var1 = true;
/*     */     } 
/* 108 */     return var1;
/*     */   }
/*     */   
/*     */   public MobEffectList getMobEffect() {
/* 112 */     return this.b;
/*     */   }
/*     */   
/*     */   public int getDuration() {
/* 116 */     return this.duration;
/*     */   }
/*     */   
/*     */   public int getAmplifier() {
/* 120 */     return this.amplification;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAmbient() {
/* 128 */     return this.ambient;
/*     */   }
/*     */   
/*     */   public boolean isShowParticles() {
/* 132 */     return this.showParticles;
/*     */   }
/*     */   
/*     */   public boolean isShowIcon() {
/* 136 */     return this.showIcon;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tick(EntityLiving var0, Runnable var1) {
/* 146 */     if (this.duration > 0) {
/* 147 */       if (this.b.a(this.duration, this.amplification)) {
/* 148 */         a(var0);
/*     */       }
/* 150 */       i();
/* 151 */       if (this.duration == 0 && this.hiddenEffect != null) {
/* 152 */         a(this.hiddenEffect);
/* 153 */         this.hiddenEffect = this.hiddenEffect.hiddenEffect;
/* 154 */         var1.run();
/*     */       } 
/*     */     } 
/* 157 */     return (this.duration > 0);
/*     */   }
/*     */   
/*     */   private int i() {
/* 161 */     if (this.hiddenEffect != null) {
/* 162 */       this.hiddenEffect.i();
/*     */     }
/* 164 */     return --this.duration;
/*     */   }
/*     */   
/*     */   public void a(EntityLiving var0) {
/* 168 */     if (this.duration > 0) {
/* 169 */       this.b.tick(var0, this.amplification);
/*     */     }
/*     */   }
/*     */   
/*     */   public String g() {
/* 174 */     return this.b.c();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     String var0;
/* 180 */     if (this.amplification > 0) {
/* 181 */       var0 = g() + " x " + (this.amplification + 1) + ", Duration: " + this.duration;
/*     */     } else {
/* 183 */       var0 = g() + ", Duration: " + this.duration;
/*     */     } 
/* 185 */     if (this.splash) {
/* 186 */       var0 = var0 + ", Splash: true";
/*     */     }
/* 188 */     if (!this.showParticles) {
/* 189 */       var0 = var0 + ", Particles: false";
/*     */     }
/* 191 */     if (!this.showIcon) {
/* 192 */       var0 = var0 + ", Show Icon: false";
/*     */     }
/*     */     
/* 195 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/* 200 */     if (this == var0) {
/* 201 */       return true;
/*     */     }
/*     */     
/* 204 */     if (var0 instanceof MobEffect) {
/* 205 */       MobEffect var1 = (MobEffect)var0;
/*     */       
/* 207 */       return (this.duration == var1.duration && this.amplification == var1.amplification && this.splash == var1.splash && this.ambient == var1.ambient && this.b.equals(var1.b));
/*     */     } 
/* 209 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 214 */     int var0 = this.b.hashCode();
/* 215 */     var0 = 31 * var0 + this.duration;
/* 216 */     var0 = 31 * var0 + this.amplification;
/* 217 */     var0 = 31 * var0 + (this.splash ? 1 : 0);
/* 218 */     var0 = 31 * var0 + (this.ambient ? 1 : 0);
/* 219 */     return var0;
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound var0) {
/* 223 */     var0.setByte("Id", (byte)MobEffectList.getId(getMobEffect()));
/* 224 */     c(var0);
/* 225 */     return var0;
/*     */   }
/*     */   
/*     */   private void c(NBTTagCompound var0) {
/* 229 */     var0.setByte("Amplifier", (byte)getAmplifier());
/* 230 */     var0.setInt("Duration", getDuration());
/* 231 */     var0.setBoolean("Ambient", isAmbient());
/* 232 */     var0.setBoolean("ShowParticles", isShowParticles());
/* 233 */     var0.setBoolean("ShowIcon", isShowIcon());
/* 234 */     if (this.hiddenEffect != null) {
/* 235 */       NBTTagCompound var1 = new NBTTagCompound();
/* 236 */       this.hiddenEffect.a(var1);
/* 237 */       var0.set("HiddenEffect", var1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static MobEffect b(NBTTagCompound var0) {
/* 242 */     int var1 = var0.getByte("Id");
/* 243 */     MobEffectList var2 = MobEffectList.fromId(var1);
/* 244 */     if (var2 == null) {
/* 245 */       return null;
/*     */     }
/* 247 */     return a(var2, var0);
/*     */   }
/*     */   
/*     */   private static MobEffect a(MobEffectList var0, NBTTagCompound var1) {
/* 251 */     int var2 = var1.getByte("Amplifier");
/* 252 */     int var3 = var1.getInt("Duration");
/* 253 */     boolean var4 = var1.getBoolean("Ambient");
/* 254 */     boolean var5 = true;
/* 255 */     if (var1.hasKeyOfType("ShowParticles", 1)) {
/* 256 */       var5 = var1.getBoolean("ShowParticles");
/*     */     }
/* 258 */     boolean var6 = var5;
/* 259 */     if (var1.hasKeyOfType("ShowIcon", 1)) {
/* 260 */       var6 = var1.getBoolean("ShowIcon");
/*     */     }
/* 262 */     MobEffect var7 = null;
/* 263 */     if (var1.hasKeyOfType("HiddenEffect", 10)) {
/* 264 */       var7 = a(var0, var1.getCompound("HiddenEffect"));
/*     */     }
/* 266 */     return new MobEffect(var0, var3, (var2 < 0) ? 0 : var2, var4, var5, var6, var7);
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
/*     */   public int compareTo(MobEffect var0) {
/* 279 */     int var1 = 32147;
/* 280 */     if ((getDuration() > 32147 && var0.getDuration() > 32147) || (isAmbient() && var0.isAmbient()))
/*     */     {
/* 282 */       return ComparisonChain.start()
/* 283 */         .compare(Boolean.valueOf(isAmbient()), Boolean.valueOf(var0.isAmbient()))
/* 284 */         .compare(getMobEffect().getColor(), var0.getMobEffect().getColor())
/* 285 */         .result();
/*     */     }
/* 287 */     return ComparisonChain.start()
/* 288 */       .compare(Boolean.valueOf(isAmbient()), Boolean.valueOf(var0.isAmbient()))
/* 289 */       .compare(getDuration(), var0.getDuration())
/* 290 */       .compare(getMobEffect().getColor(), var0.getMobEffect().getColor())
/* 291 */       .result();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */