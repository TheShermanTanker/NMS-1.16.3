/*     */ package org.bukkit.map;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
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
/*     */ public final class MapPalette
/*     */ {
/*     */   @NotNull
/*     */   private static Color c(int r, int g, int b) {
/*  22 */     return new Color(r, g, b);
/*     */   }
/*     */   
/*     */   private static double getDistance(@NotNull Color c1, @NotNull Color c2) {
/*  26 */     double rmean = (c1.getRed() + c2.getRed()) / 2.0D;
/*  27 */     double r = (c1.getRed() - c2.getRed());
/*  28 */     double g = (c1.getGreen() - c2.getGreen());
/*  29 */     int b = c1.getBlue() - c2.getBlue();
/*  30 */     double weightR = 2.0D + rmean / 256.0D;
/*  31 */     double weightG = 4.0D;
/*  32 */     double weightB = 2.0D + (255.0D - rmean) / 256.0D;
/*  33 */     return weightR * r * r + weightG * g * g + weightB * b * b;
/*     */   }
/*     */   
/*     */   @NotNull
/*  37 */   static final Color[] colors = new Color[] { 
/*  38 */       c(0, 0, 0), c(0, 0, 0), c(0, 0, 0), c(0, 0, 0), 
/*  39 */       c(89, 125, 39), c(109, 153, 48), c(127, 178, 56), c(67, 94, 29), 
/*  40 */       c(174, 164, 115), c(213, 201, 140), c(247, 233, 163), c(130, 123, 86), 
/*  41 */       c(140, 140, 140), c(171, 171, 171), c(199, 199, 199), c(105, 105, 105), 
/*  42 */       c(180, 0, 0), c(220, 0, 0), c(255, 0, 0), c(135, 0, 0), 
/*  43 */       c(112, 112, 180), c(138, 138, 220), c(160, 160, 255), c(84, 84, 135), 
/*  44 */       c(117, 117, 117), c(144, 144, 144), c(167, 167, 167), c(88, 88, 88), 
/*  45 */       c(0, 87, 0), c(0, 106, 0), c(0, 124, 0), c(0, 65, 0), 
/*  46 */       c(180, 180, 180), c(220, 220, 220), c(255, 255, 255), c(135, 135, 135), 
/*  47 */       c(115, 118, 129), c(141, 144, 158), c(164, 168, 184), c(86, 88, 97), 
/*  48 */       c(106, 76, 54), c(130, 94, 66), c(151, 109, 77), c(79, 57, 40), 
/*  49 */       c(79, 79, 79), c(96, 96, 96), c(112, 112, 112), c(59, 59, 59), 
/*  50 */       c(45, 45, 180), c(55, 55, 220), c(64, 64, 255), c(33, 33, 135), 
/*  51 */       c(100, 84, 50), c(123, 102, 62), c(143, 119, 72), c(75, 63, 38), 
/*  52 */       c(180, 177, 172), c(220, 217, 211), c(255, 252, 245), c(135, 133, 129), 
/*  53 */       c(152, 89, 36), c(186, 109, 44), c(216, 127, 51), c(114, 67, 27), 
/*  54 */       c(125, 53, 152), c(153, 65, 186), c(178, 76, 216), c(94, 40, 114), 
/*  55 */       c(72, 108, 152), c(88, 132, 186), c(102, 153, 216), c(54, 81, 114), 
/*  56 */       c(161, 161, 36), c(197, 197, 44), c(229, 229, 51), c(121, 121, 27), 
/*  57 */       c(89, 144, 17), c(109, 176, 21), c(127, 204, 25), c(67, 108, 13), 
/*  58 */       c(170, 89, 116), c(208, 109, 142), c(242, 127, 165), c(128, 67, 87), 
/*  59 */       c(53, 53, 53), c(65, 65, 65), c(76, 76, 76), c(40, 40, 40), 
/*  60 */       c(108, 108, 108), c(132, 132, 132), c(153, 153, 153), c(81, 81, 81), 
/*  61 */       c(53, 89, 108), c(65, 109, 132), c(76, 127, 153), c(40, 67, 81), 
/*  62 */       c(89, 44, 125), c(109, 54, 153), c(127, 63, 178), c(67, 33, 94), 
/*  63 */       c(36, 53, 125), c(44, 65, 153), c(51, 76, 178), c(27, 40, 94), 
/*  64 */       c(72, 53, 36), c(88, 65, 44), c(102, 76, 51), c(54, 40, 27), 
/*  65 */       c(72, 89, 36), c(88, 109, 44), c(102, 127, 51), c(54, 67, 27), 
/*  66 */       c(108, 36, 36), c(132, 44, 44), c(153, 51, 51), c(81, 27, 27), 
/*  67 */       c(17, 17, 17), c(21, 21, 21), c(25, 25, 25), c(13, 13, 13), 
/*  68 */       c(176, 168, 54), c(215, 205, 66), c(250, 238, 77), c(132, 126, 40), 
/*  69 */       c(64, 154, 150), c(79, 188, 183), c(92, 219, 213), c(48, 115, 112), 
/*  70 */       c(52, 90, 180), c(63, 110, 220), c(74, 128, 255), c(39, 67, 135), 
/*  71 */       c(0, 153, 40), c(0, 187, 50), c(0, 217, 58), c(0, 114, 30), 
/*  72 */       c(91, 60, 34), c(111, 74, 42), c(129, 86, 49), c(68, 45, 25), 
/*  73 */       c(79, 1, 0), c(96, 1, 0), c(112, 2, 0), c(59, 1, 0), 
/*  74 */       c(147, 124, 113), c(180, 152, 138), c(209, 177, 161), c(110, 93, 85), 
/*  75 */       c(112, 57, 25), c(137, 70, 31), c(159, 82, 36), c(84, 43, 19), 
/*  76 */       c(105, 61, 76), c(128, 75, 93), c(149, 87, 108), c(78, 46, 57), 
/*  77 */       c(79, 76, 97), c(96, 93, 119), c(112, 108, 138), c(59, 57, 73), 
/*  78 */       c(131, 93, 25), c(160, 114, 31), c(186, 133, 36), c(98, 70, 19), 
/*  79 */       c(72, 82, 37), c(88, 100, 45), c(103, 117, 53), c(54, 61, 28), 
/*  80 */       c(112, 54, 55), c(138, 66, 67), c(160, 77, 78), c(84, 40, 41), 
/*  81 */       c(40, 28, 24), c(49, 35, 30), c(57, 41, 35), c(30, 21, 18), 
/*  82 */       c(95, 75, 69), c(116, 92, 84), c(135, 107, 98), c(71, 56, 51), 
/*  83 */       c(61, 64, 64), c(75, 79, 79), c(87, 92, 92), c(46, 48, 48), 
/*  84 */       c(86, 51, 62), c(105, 62, 75), c(122, 73, 88), c(64, 38, 46), 
/*  85 */       c(53, 43, 64), c(65, 53, 79), c(76, 62, 92), c(40, 32, 48), 
/*  86 */       c(53, 35, 24), c(65, 43, 30), c(76, 50, 35), c(40, 26, 18), 
/*  87 */       c(53, 57, 29), c(65, 70, 36), c(76, 82, 42), c(40, 43, 22), 
/*  88 */       c(100, 42, 32), c(122, 51, 39), c(142, 60, 46), c(75, 31, 24), 
/*  89 */       c(26, 15, 11), c(31, 18, 13), c(37, 22, 16), c(19, 11, 8), 
/*  90 */       c(133, 33, 34), c(163, 41, 42), c(189, 48, 49), c(100, 25, 25), 
/*  91 */       c(104, 44, 68), c(127, 54, 83), c(148, 63, 97), c(78, 33, 51), 
/*  92 */       c(64, 17, 20), c(79, 21, 25), c(92, 25, 29), c(48, 13, 15), 
/*  93 */       c(15, 88, 94), c(18, 108, 115), c(22, 126, 134), c(11, 66, 70), 
/*  94 */       c(40, 100, 98), c(50, 122, 120), c(58, 142, 140), c(30, 75, 74), 
/*  95 */       c(60, 31, 43), c(74, 37, 53), c(86, 44, 62), c(45, 23, 32), 
/*  96 */       c(14, 127, 93), c(17, 155, 114), c(20, 180, 133), c(10, 95, 70) };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte TRANSPARENT = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte LIGHT_GREEN = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte LIGHT_BROWN = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte GRAY_1 = 12;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte RED = 16;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte PALE_BLUE = 20;
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte GRAY_2 = 24;
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte DARK_GREEN = 28;
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte WHITE = 32;
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte LIGHT_GRAY = 36;
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte BROWN = 40;
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte DARK_GRAY = 44;
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte BLUE = 48;
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static final byte DARK_BROWN = 52;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static BufferedImage resizeImage(@Nullable Image image) {
/* 179 */     BufferedImage result = new BufferedImage(128, 128, 2);
/* 180 */     Graphics2D graphics = result.createGraphics();
/* 181 */     graphics.drawImage(image, 0, 0, 128, 128, null);
/* 182 */     graphics.dispose();
/* 183 */     return result;
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
/*     */   public static byte[] imageToBytes(@NotNull Image image) {
/* 196 */     BufferedImage temp = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
/* 197 */     Graphics2D graphics = temp.createGraphics();
/* 198 */     graphics.drawImage(image, 0, 0, (ImageObserver)null);
/* 199 */     graphics.dispose();
/*     */     
/* 201 */     int[] pixels = new int[temp.getWidth() * temp.getHeight()];
/* 202 */     temp.getRGB(0, 0, temp.getWidth(), temp.getHeight(), pixels, 0, temp.getWidth());
/*     */     
/* 204 */     byte[] result = new byte[temp.getWidth() * temp.getHeight()];
/* 205 */     for (int i = 0; i < pixels.length; i++) {
/* 206 */       result[i] = matchColor(new Color(pixels[i], true));
/*     */     }
/* 208 */     return result;
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
/*     */   @Deprecated
/*     */   public static byte matchColor(int r, int g, int b) {
/* 223 */     return matchColor(new Color(r, g, b));
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
/*     */   @Deprecated
/*     */   public static byte matchColor(@NotNull Color color) {
/* 236 */     if (color.getAlpha() < 128) return 0;
/*     */     
/* 238 */     int index = 0;
/* 239 */     double best = -1.0D;
/*     */     
/* 241 */     for (int i = 4; i < colors.length; i++) {
/* 242 */       double distance = getDistance(color, colors[i]);
/* 243 */       if (distance < best || best == -1.0D) {
/* 244 */         best = distance;
/* 245 */         index = i;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 250 */     return (byte)((index < 128) ? index : (-129 + index - 127));
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
/*     */   public static Color getColor(byte index) {
/* 263 */     if ((index > -21 && index < 0) || index > Byte.MAX_VALUE) {
/* 264 */       throw new IndexOutOfBoundsException();
/*     */     }
/*     */     
/* 267 */     return colors[(index >= 0) ? index : (index + 256)];
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\map\MapPalette.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */