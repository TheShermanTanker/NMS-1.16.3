/*     */ package org.bukkit;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.configuration.serialization.SerializableAs;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ @SerializableAs("Firework")
/*     */ public final class FireworkEffect implements ConfigurationSerializable {
/*     */   private static final String FLICKER = "flicker";
/*     */   private static final String TRAIL = "trail";
/*     */   private static final String COLORS = "colors";
/*     */   private static final String FADE_COLORS = "fade-colors";
/*     */   private static final String TYPE = "type";
/*     */   private final boolean flicker;
/*     */   private final boolean trail;
/*     */   private final ImmutableList<Color> colors;
/*     */   private final ImmutableList<Color> fadeColors;
/*     */   private final Type type;
/*     */   
/*     */   public enum Type {
/*  25 */     BALL,
/*     */ 
/*     */ 
/*     */     
/*  29 */     BALL_LARGE,
/*     */ 
/*     */ 
/*     */     
/*  33 */     STAR,
/*     */ 
/*     */ 
/*     */     
/*  37 */     BURST,
/*     */ 
/*     */ 
/*     */     
/*  41 */     CREEPER;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Builder builder() {
/*  52 */     return new Builder();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */   {
/*     */     boolean flicker = false;
/*     */     
/*     */     boolean trail = false;
/*     */     
/*  63 */     final ImmutableList.Builder<Color> colors = ImmutableList.builder();
/*  64 */     ImmutableList.Builder<Color> fadeColors = null;
/*  65 */     FireworkEffect.Type type = FireworkEffect.Type.BALL;
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
/*     */     @NotNull
/*     */     public Builder with(@NotNull FireworkEffect.Type type) throws IllegalArgumentException {
/*  78 */       Validate.notNull(type, "Cannot have null type");
/*  79 */       this.type = type;
/*  80 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder withFlicker() {
/*  90 */       this.flicker = true;
/*  91 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder flicker(boolean flicker) {
/* 102 */       this.flicker = flicker;
/* 103 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder withTrail() {
/* 113 */       this.trail = true;
/* 114 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder trail(boolean trail) {
/* 125 */       this.trail = trail;
/* 126 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Builder withColor(@NotNull Color color) throws IllegalArgumentException {
/* 138 */       Validate.notNull(color, "Cannot have null color");
/*     */       
/* 140 */       this.colors.add(color);
/*     */       
/* 142 */       return this;
/*     */     }
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
/*     */     @NotNull
/*     */     public Builder withColor(@NotNull Color... colors) throws IllegalArgumentException {
/* 156 */       Validate.notNull(colors, "Cannot have null colors");
/* 157 */       if (colors.length == 0) {
/* 158 */         return this;
/*     */       }
/*     */       
/* 161 */       ImmutableList.Builder<Color> list = this.colors;
/* 162 */       for (Color color : colors) {
/* 163 */         Validate.notNull(color, "Color cannot be null");
/* 164 */         list.add(color);
/*     */       } 
/*     */       
/* 167 */       return this;
/*     */     }
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
/*     */     @NotNull
/*     */     public Builder withColor(@NotNull Iterable<?> colors) throws IllegalArgumentException {
/* 182 */       Validate.notNull(colors, "Cannot have null colors");
/*     */       
/* 184 */       ImmutableList.Builder<Color> list = this.colors;
/* 185 */       for (Object color : colors) {
/* 186 */         if (!(color instanceof Color)) {
/* 187 */           throw new IllegalArgumentException(color + " is not a Color in " + colors);
/*     */         }
/* 189 */         list.add(color);
/*     */       } 
/*     */       
/* 192 */       return this;
/*     */     }
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
/*     */     @NotNull
/*     */     public Builder withFade(@NotNull Color color) throws IllegalArgumentException {
/* 206 */       Validate.notNull(color, "Cannot have null color");
/*     */       
/* 208 */       if (this.fadeColors == null) {
/* 209 */         this.fadeColors = ImmutableList.builder();
/*     */       }
/*     */       
/* 212 */       this.fadeColors.add(color);
/*     */       
/* 214 */       return this;
/*     */     }
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
/*     */     @NotNull
/*     */     public Builder withFade(@NotNull Color... colors) throws IllegalArgumentException {
/* 228 */       Validate.notNull(colors, "Cannot have null colors");
/* 229 */       if (colors.length == 0) {
/* 230 */         return this;
/*     */       }
/*     */       
/* 233 */       ImmutableList.Builder<Color> list = this.fadeColors;
/* 234 */       if (list == null) {
/* 235 */         list = this.fadeColors = ImmutableList.builder();
/*     */       }
/*     */       
/* 238 */       for (Color color : colors) {
/* 239 */         Validate.notNull(color, "Color cannot be null");
/* 240 */         list.add(color);
/*     */       } 
/*     */       
/* 243 */       return this;
/*     */     }
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
/*     */     @NotNull
/*     */     public Builder withFade(@NotNull Iterable<?> colors) throws IllegalArgumentException {
/* 258 */       Validate.notNull(colors, "Cannot have null colors");
/*     */       
/* 260 */       ImmutableList.Builder<Color> list = this.fadeColors;
/* 261 */       if (list == null) {
/* 262 */         list = this.fadeColors = ImmutableList.builder();
/*     */       }
/*     */       
/* 265 */       for (Object color : colors) {
/* 266 */         if (!(color instanceof Color)) {
/* 267 */           throw new IllegalArgumentException(color + " is not a Color in " + colors);
/*     */         }
/* 269 */         list.add(color);
/*     */       } 
/*     */       
/* 272 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public FireworkEffect build() {
/* 285 */       return new FireworkEffect(this.flicker, this.trail, this.colors
/*     */ 
/*     */           
/* 288 */           .build(), 
/* 289 */           (this.fadeColors == null) ? ImmutableList.of() : this.fadeColors.build(), this.type);
/*     */     }
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
/* 306 */   private String string = null;
/*     */   
/*     */   FireworkEffect(boolean flicker, boolean trail, @NotNull ImmutableList<Color> colors, @NotNull ImmutableList<Color> fadeColors, @NotNull Type type) {
/* 309 */     if (colors.isEmpty()) {
/* 310 */       throw new IllegalStateException("Cannot make FireworkEffect without any color");
/*     */     }
/* 312 */     this.flicker = flicker;
/* 313 */     this.trail = trail;
/* 314 */     this.colors = colors;
/* 315 */     this.fadeColors = fadeColors;
/* 316 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFlicker() {
/* 325 */     return this.flicker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTrail() {
/* 334 */     return this.trail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Color> getColors() {
/* 344 */     return (List<Color>)this.colors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Color> getFadeColors() {
/* 354 */     return (List<Color>)this.fadeColors;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Type getType() {
/* 364 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static ConfigurationSerializable deserialize(@NotNull Map<String, Object> map) {
/* 374 */     Type type = Type.valueOf((String)map.get("type"));
/*     */     
/* 376 */     return builder()
/* 377 */       .flicker(((Boolean)map.get("flicker")).booleanValue())
/* 378 */       .trail(((Boolean)map.get("trail")).booleanValue())
/* 379 */       .withColor((Iterable)map.get("colors"))
/* 380 */       .withFade((Iterable)map.get("fade-colors"))
/* 381 */       .with(type)
/* 382 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<String, Object> serialize() {
/* 388 */     return (Map<String, Object>)ImmutableMap.of("flicker", 
/* 389 */         Boolean.valueOf(this.flicker), "trail", 
/* 390 */         Boolean.valueOf(this.trail), "colors", this.colors, "fade-colors", this.fadeColors, "type", this.type
/*     */ 
/*     */         
/* 393 */         .name());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 399 */     String string = this.string;
/* 400 */     if (string == null) {
/* 401 */       return this.string = "FireworkEffect:" + serialize();
/*     */     }
/* 403 */     return string;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 409 */     int PRIME = 31, TRUE = 1231, FALSE = 1237;
/* 410 */     int hash = 1;
/* 411 */     hash = hash * 31 + (this.flicker ? 1231 : 1237);
/* 412 */     hash = hash * 31 + (this.trail ? 1231 : 1237);
/* 413 */     hash = hash * 31 + this.type.hashCode();
/* 414 */     hash = hash * 31 + this.colors.hashCode();
/* 415 */     hash = hash * 31 + this.fadeColors.hashCode();
/* 416 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 421 */     if (this == obj) {
/* 422 */       return true;
/*     */     }
/*     */     
/* 425 */     if (!(obj instanceof FireworkEffect)) {
/* 426 */       return false;
/*     */     }
/*     */     
/* 429 */     FireworkEffect that = (FireworkEffect)obj;
/* 430 */     return (this.flicker == that.flicker && this.trail == that.trail && this.type == that.type && this.colors
/*     */ 
/*     */       
/* 433 */       .equals(that.colors) && this.fadeColors
/* 434 */       .equals(that.fadeColors));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\FireworkEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */