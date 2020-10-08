/*     */ package org.bukkit;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum DyeColor
/*     */ {
/*  16 */   WHITE(0, 15, Color.fromRGB(16383998), Color.fromRGB(15790320)),
/*     */ 
/*     */ 
/*     */   
/*  20 */   ORANGE(1, 14, Color.fromRGB(16351261), Color.fromRGB(15435844)),
/*     */ 
/*     */ 
/*     */   
/*  24 */   MAGENTA(2, 13, Color.fromRGB(13061821), Color.fromRGB(12801229)),
/*     */ 
/*     */ 
/*     */   
/*  28 */   LIGHT_BLUE(3, 12, Color.fromRGB(3847130), Color.fromRGB(6719955)),
/*     */ 
/*     */ 
/*     */   
/*  32 */   YELLOW(4, 11, Color.fromRGB(16701501), Color.fromRGB(14602026)),
/*     */ 
/*     */ 
/*     */   
/*  36 */   LIME(5, 10, Color.fromRGB(8439583), Color.fromRGB(4312372)),
/*     */ 
/*     */ 
/*     */   
/*  40 */   PINK(6, 9, Color.fromRGB(15961002), Color.fromRGB(14188952)),
/*     */ 
/*     */ 
/*     */   
/*  44 */   GRAY(7, 8, Color.fromRGB(4673362), Color.fromRGB(4408131)),
/*     */ 
/*     */ 
/*     */   
/*  48 */   LIGHT_GRAY(8, 7, Color.fromRGB(10329495), Color.fromRGB(11250603)),
/*     */ 
/*     */ 
/*     */   
/*  52 */   CYAN(9, 6, Color.fromRGB(1481884), Color.fromRGB(2651799)),
/*     */ 
/*     */ 
/*     */   
/*  56 */   PURPLE(10, 5, Color.fromRGB(8991416), Color.fromRGB(8073150)),
/*     */ 
/*     */ 
/*     */   
/*  60 */   BLUE(11, 4, Color.fromRGB(3949738), Color.fromRGB(2437522)),
/*     */ 
/*     */ 
/*     */   
/*  64 */   BROWN(12, 3, Color.fromRGB(8606770), Color.fromRGB(5320730)),
/*     */ 
/*     */ 
/*     */   
/*  68 */   GREEN(13, 2, Color.fromRGB(6192150), Color.fromRGB(3887386)),
/*     */ 
/*     */ 
/*     */   
/*  72 */   RED(14, 1, Color.fromRGB(11546150), Color.fromRGB(11743532)),
/*     */ 
/*     */ 
/*     */   
/*  76 */   BLACK(15, 0, Color.fromRGB(1908001), Color.fromRGB(1973019));
/*     */   
/*     */   private final byte woolData;
/*     */   private final byte dyeData;
/*     */   private final Color color;
/*     */   private final Color firework;
/*     */   private static final DyeColor[] BY_WOOL_DATA;
/*     */   private static final DyeColor[] BY_DYE_DATA;
/*     */   private static final Map<Color, DyeColor> BY_COLOR;
/*     */   private static final Map<Color, DyeColor> BY_FIREWORK;
/*     */   
/*     */   DyeColor(int woolData, int dyeData, Color color, Color firework) {
/*  88 */     this.woolData = (byte)woolData;
/*  89 */     this.dyeData = (byte)dyeData;
/*  90 */     this.color = color;
/*  91 */     this.firework = firework;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public byte getWoolData() {
/* 103 */     return this.woolData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public byte getDyeData() {
/* 115 */     return this.dyeData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Color getColor() {
/* 125 */     return this.color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Color getFireworkColor() {
/* 135 */     return this.firework;
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
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public static DyeColor getByWoolData(byte data) {
/* 150 */     int i = 0xFF & data;
/* 151 */     if (i >= BY_WOOL_DATA.length) {
/* 152 */       return null;
/*     */     }
/* 154 */     return BY_WOOL_DATA[i];
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
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public static DyeColor getByDyeData(byte data) {
/* 169 */     int i = 0xFF & data;
/* 170 */     if (i >= BY_DYE_DATA.length) {
/* 171 */       return null;
/*     */     }
/* 173 */     return BY_DYE_DATA[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static DyeColor getByColor(@NotNull Color color) {
/* 185 */     return BY_COLOR.get(color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static DyeColor getByFireworkColor(@NotNull Color color) {
/* 197 */     return BY_FIREWORK.get(color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public static DyeColor legacyValueOf(@Nullable String name) {
/* 210 */     return "SILVER".equals(name) ? LIGHT_GRAY : valueOf(name);
/*     */   }
/*     */   
/*     */   static {
/* 214 */     BY_WOOL_DATA = values();
/* 215 */     BY_DYE_DATA = values();
/* 216 */     ImmutableMap.Builder<Color, DyeColor> byColor = ImmutableMap.builder();
/* 217 */     ImmutableMap.Builder<Color, DyeColor> byFirework = ImmutableMap.builder();
/*     */     
/* 219 */     for (DyeColor color : values()) {
/* 220 */       BY_WOOL_DATA[color.woolData & 0xFF] = color;
/* 221 */       BY_DYE_DATA[color.dyeData & 0xFF] = color;
/* 222 */       byColor.put(color.getColor(), color);
/* 223 */       byFirework.put(color.getFireworkColor(), color);
/*     */     } 
/*     */     
/* 226 */     BY_COLOR = (Map<Color, DyeColor>)byColor.build();
/* 227 */     BY_FIREWORK = (Map<Color, DyeColor>)byFirework.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\DyeColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */