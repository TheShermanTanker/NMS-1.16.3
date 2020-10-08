/*     */ package org.bukkit;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.configuration.serialization.SerializableAs;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SerializableAs("Color")
/*     */ public final class Color
/*     */   implements ConfigurationSerializable
/*     */ {
/*     */   private static final int BIT_MASK = 255;
/*  22 */   public static final Color WHITE = fromRGB(16777215);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  27 */   public static final Color SILVER = fromRGB(12632256);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static final Color GRAY = fromRGB(8421504);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   public static final Color BLACK = fromRGB(0);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public static final Color RED = fromRGB(16711680);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static final Color MAROON = fromRGB(8388608);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public static final Color YELLOW = fromRGB(16776960);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public static final Color OLIVE = fromRGB(8421376);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public static final Color LIME = fromRGB(65280);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public static final Color GREEN = fromRGB(32768);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public static final Color AQUA = fromRGB(65535);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public static final Color TEAL = fromRGB(32896);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public static final Color BLUE = fromRGB(255);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static final Color NAVY = fromRGB(128);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static final Color FUCHSIA = fromRGB(16711935);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public static final Color PURPLE = fromRGB(8388736);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public static final Color ORANGE = fromRGB(16753920);
/*     */ 
/*     */ 
/*     */   
/*     */   private final byte red;
/*     */ 
/*     */ 
/*     */   
/*     */   private final byte green;
/*     */ 
/*     */   
/*     */   private final byte blue;
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Color fromRGB(int red, int green, int blue) throws IllegalArgumentException {
/* 119 */     return new Color(red, green, blue);
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
/*     */   @NotNull
/*     */   public static Color fromBGR(int blue, int green, int red) throws IllegalArgumentException {
/* 133 */     return new Color(red, green, blue);
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
/*     */   @NotNull
/*     */   public static Color fromRGB(int rgb) throws IllegalArgumentException {
/* 147 */     Validate.isTrue((rgb >> 24 == 0), "Extrenuous data in: ", rgb);
/* 148 */     return fromRGB(rgb >> 16 & 0xFF, rgb >> 8 & 0xFF, rgb >> 0 & 0xFF);
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
/*     */   @NotNull
/*     */   public static Color fromBGR(int bgr) throws IllegalArgumentException {
/* 162 */     Validate.isTrue((bgr >> 24 == 0), "Extrenuous data in: ", bgr);
/* 163 */     return fromBGR(bgr >> 16 & 0xFF, bgr >> 8 & 0xFF, bgr >> 0 & 0xFF);
/*     */   }
/*     */   
/*     */   private Color(int red, int green, int blue) {
/* 167 */     Validate.isTrue((red >= 0 && red <= 255), "Red is not between 0-255: ", red);
/* 168 */     Validate.isTrue((green >= 0 && green <= 255), "Green is not between 0-255: ", green);
/* 169 */     Validate.isTrue((blue >= 0 && blue <= 255), "Blue is not between 0-255: ", blue);
/*     */     
/* 171 */     this.red = (byte)red;
/* 172 */     this.green = (byte)green;
/* 173 */     this.blue = (byte)blue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRed() {
/* 182 */     return 0xFF & this.red;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Color setRed(int red) {
/* 193 */     return fromRGB(red, getGreen(), getBlue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGreen() {
/* 202 */     return 0xFF & this.green;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Color setGreen(int green) {
/* 213 */     return fromRGB(getRed(), green, getBlue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlue() {
/* 222 */     return 0xFF & this.blue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Color setBlue(int blue) {
/* 233 */     return fromRGB(getRed(), getGreen(), blue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int asRGB() {
/* 242 */     return getRed() << 16 | getGreen() << 8 | getBlue() << 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int asBGR() {
/* 251 */     return getBlue() << 16 | getGreen() << 8 | getRed() << 0;
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
/*     */   @NotNull
/*     */   public Color mixDyes(@NotNull DyeColor... colors) {
/* 264 */     Validate.noNullElements((Object[])colors, "Colors cannot be null");
/*     */     
/* 266 */     Color[] toPass = new Color[colors.length];
/* 267 */     for (int i = 0; i < colors.length; i++) {
/* 268 */       toPass[i] = colors[i].getColor();
/*     */     }
/*     */     
/* 271 */     return mixColors(toPass);
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
/*     */   @NotNull
/*     */   public Color mixColors(@NotNull Color... colors) {
/* 284 */     Validate.noNullElements((Object[])colors, "Colors cannot be null");
/*     */     
/* 286 */     int totalRed = getRed();
/* 287 */     int totalGreen = getGreen();
/* 288 */     int totalBlue = getBlue();
/* 289 */     int totalMax = Math.max(Math.max(totalRed, totalGreen), totalBlue);
/* 290 */     for (Color color : colors) {
/* 291 */       totalRed += color.getRed();
/* 292 */       totalGreen += color.getGreen();
/* 293 */       totalBlue += color.getBlue();
/* 294 */       totalMax += Math.max(Math.max(color.getRed(), color.getGreen()), color.getBlue());
/*     */     } 
/*     */     
/* 297 */     float averageRed = (totalRed / (colors.length + 1));
/* 298 */     float averageGreen = (totalGreen / (colors.length + 1));
/* 299 */     float averageBlue = (totalBlue / (colors.length + 1));
/* 300 */     float averageMax = (totalMax / (colors.length + 1));
/*     */     
/* 302 */     float maximumOfAverages = Math.max(Math.max(averageRed, averageGreen), averageBlue);
/* 303 */     float gainFactor = averageMax / maximumOfAverages;
/*     */     
/* 305 */     return fromRGB((int)(averageRed * gainFactor), (int)(averageGreen * gainFactor), (int)(averageBlue * gainFactor));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 310 */     if (!(o instanceof Color)) {
/* 311 */       return false;
/*     */     }
/* 313 */     Color that = (Color)o;
/* 314 */     return (this.blue == that.blue && this.green == that.green && this.red == that.red);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 319 */     return asRGB() ^ Color.class.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<String, Object> serialize() {
/* 325 */     return (Map<String, Object>)ImmutableMap.of("RED", 
/* 326 */         Integer.valueOf(getRed()), "BLUE", 
/* 327 */         Integer.valueOf(getBlue()), "GREEN", 
/* 328 */         Integer.valueOf(getGreen()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Color deserialize(@NotNull Map<String, Object> map) {
/* 335 */     return fromRGB(
/* 336 */         asInt("RED", map), 
/* 337 */         asInt("GREEN", map), 
/* 338 */         asInt("BLUE", map));
/*     */   }
/*     */ 
/*     */   
/*     */   private static int asInt(@NotNull String string, @NotNull Map<String, Object> map) {
/* 343 */     Object value = map.get(string);
/* 344 */     if (value == null) {
/* 345 */       throw new IllegalArgumentException(string + " not in map " + map);
/*     */     }
/* 347 */     if (!(value instanceof Number)) {
/* 348 */       throw new IllegalArgumentException(string + '(' + value + ") is not a number");
/*     */     }
/* 350 */     return ((Number)value).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 355 */     return "Color:[rgb0x" + Integer.toHexString(getRed()).toUpperCase() + Integer.toHexString(getGreen()).toUpperCase() + Integer.toHexString(getBlue()).toUpperCase() + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Color.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */