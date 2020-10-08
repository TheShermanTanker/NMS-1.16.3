/*     */ package org.bukkit.potion;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.configuration.serialization.SerializableAs;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.jetbrains.annotations.Contract;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ @SerializableAs("PotionEffect")
/*     */ public class PotionEffect
/*     */   implements ConfigurationSerializable
/*     */ {
/*     */   private static final String AMPLIFIER = "amplifier";
/*     */   private static final String DURATION = "duration";
/*     */   private static final String TYPE = "effect";
/*     */   private static final String AMBIENT = "ambient";
/*     */   private static final String PARTICLES = "has-particles";
/*     */   private static final String ICON = "has-icon";
/*     */   private final int amplifier;
/*     */   private final int duration;
/*     */   private final PotionEffectType type;
/*     */   private final boolean ambient;
/*     */   private final boolean particles;
/*     */   private final boolean icon;
/*     */   
/*     */   public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean icon) {
/*  47 */     Validate.notNull(type, "effect type cannot be null");
/*  48 */     this.type = type;
/*  49 */     this.duration = duration;
/*  50 */     this.amplifier = amplifier;
/*  51 */     this.ambient = ambient;
/*  52 */     this.particles = particles;
/*  53 */     this.icon = icon;
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
/*     */   public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles) {
/*  67 */     this(type, duration, amplifier, ambient, particles, particles);
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
/*     */   public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient) {
/*  80 */     this(type, duration, amplifier, ambient, true);
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
/*     */   public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier) {
/*  92 */     this(type, duration, amplifier, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PotionEffect(@NotNull Map<String, Object> map) {
/* 101 */     this(getEffectType(map), getInt(map, "duration"), getInt(map, "amplifier"), getBool(map, "ambient", false), getBool(map, "has-particles", true), getBool(map, "has-icon", getBool(map, "has-particles", true)));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PotionEffect withType(@NotNull PotionEffectType type) {
/* 107 */     return new PotionEffect(type, this.duration, this.amplifier, this.ambient, this.particles, this.icon);
/*     */   }
/*     */   @NotNull
/*     */   public PotionEffect withDuration(int duration) {
/* 111 */     return new PotionEffect(this.type, duration, this.amplifier, this.ambient, this.particles, this.icon);
/*     */   }
/*     */   @NotNull
/*     */   public PotionEffect withAmplifier(int amplifier) {
/* 115 */     return new PotionEffect(this.type, this.duration, amplifier, this.ambient, this.particles, this.icon);
/*     */   }
/*     */   @NotNull
/*     */   public PotionEffect withAmbient(boolean ambient) {
/* 119 */     return new PotionEffect(this.type, this.duration, this.amplifier, ambient, this.particles, this.icon);
/*     */   }
/*     */   @NotNull
/*     */   public PotionEffect withParticles(boolean particles) {
/* 123 */     return new PotionEffect(this.type, this.duration, this.amplifier, this.ambient, particles, this.icon);
/*     */   }
/*     */   @NotNull
/*     */   public PotionEffect withIcon(boolean icon) {
/* 127 */     return new PotionEffect(this.type, this.duration, this.amplifier, this.ambient, this.particles, icon);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private static PotionEffectType getEffectType(@NotNull Map<?, ?> map) {
/* 133 */     int type = getInt(map, "effect");
/* 134 */     PotionEffectType effect = PotionEffectType.getById(type);
/* 135 */     if (effect != null) {
/* 136 */       return effect;
/*     */     }
/* 138 */     throw new NoSuchElementException(map + " does not contain " + "effect");
/*     */   }
/*     */   
/*     */   private static int getInt(@NotNull Map<?, ?> map, @NotNull Object key) {
/* 142 */     Object num = map.get(key);
/* 143 */     if (num instanceof Integer) {
/* 144 */       return ((Integer)num).intValue();
/*     */     }
/* 146 */     throw new NoSuchElementException(map + " does not contain " + key);
/*     */   }
/*     */   
/*     */   private static boolean getBool(@NotNull Map<?, ?> map, @NotNull Object key, boolean def) {
/* 150 */     Object bool = map.get(key);
/* 151 */     if (bool instanceof Boolean) {
/* 152 */       return ((Boolean)bool).booleanValue();
/*     */     }
/* 154 */     return def;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<String, Object> serialize() {
/* 160 */     return (Map<String, Object>)ImmutableMap.builder()
/* 161 */       .put("effect", Integer.valueOf(this.type.getId()))
/* 162 */       .put("duration", Integer.valueOf(this.duration))
/* 163 */       .put("amplifier", Integer.valueOf(this.amplifier))
/* 164 */       .put("ambient", Boolean.valueOf(this.ambient))
/* 165 */       .put("has-particles", Boolean.valueOf(this.particles))
/* 166 */       .put("has-icon", Boolean.valueOf(this.icon))
/* 167 */       .build();
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
/*     */   public boolean apply(@NotNull LivingEntity entity) {
/* 179 */     return entity.addPotionEffect(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 184 */     if (this == obj) {
/* 185 */       return true;
/*     */     }
/* 187 */     if (!(obj instanceof PotionEffect)) {
/* 188 */       return false;
/*     */     }
/* 190 */     PotionEffect that = (PotionEffect)obj;
/* 191 */     return (this.type.equals(that.type) && this.ambient == that.ambient && this.amplifier == that.amplifier && this.duration == that.duration && this.particles == that.particles && this.icon == that.icon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAmplifier() {
/* 202 */     return this.amplifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDuration() {
/* 212 */     return this.duration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PotionEffectType getType() {
/* 222 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAmbient() {
/* 231 */     return this.ambient;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasParticles() {
/* 238 */     return this.particles;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   @Contract("-> null")
/*     */   public Color getColor() {
/* 249 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasIcon() {
/* 256 */     return this.icon;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 261 */     int hash = 1;
/* 262 */     hash = hash * 31 + this.type.hashCode();
/* 263 */     hash = hash * 31 + this.amplifier;
/* 264 */     hash = hash * 31 + this.duration;
/* 265 */     hash ^= 572662306 >> (this.ambient ? 1 : -1);
/* 266 */     hash ^= 572662306 >> (this.particles ? 1 : -1);
/* 267 */     hash ^= 572662306 >> (this.icon ? 1 : -1);
/* 268 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 273 */     return this.type.getName() + (this.ambient ? ":(" : ":") + this.duration + "t-x" + this.amplifier + (this.ambient ? ")" : "");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\potion\PotionEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */