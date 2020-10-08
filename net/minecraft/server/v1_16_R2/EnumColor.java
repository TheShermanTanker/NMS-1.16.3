/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ public enum EnumColor
/*     */   implements INamable {
/*     */   private static final EnumColor[] q;
/*     */   private static final Int2ObjectOpenHashMap<EnumColor> r;
/*  13 */   WHITE(0, "white", 16383998, MaterialMapColor.j, 15790320, 16777215),
/*  14 */   ORANGE(1, "orange", 16351261, MaterialMapColor.q, 15435844, 16738335),
/*  15 */   MAGENTA(2, "magenta", 13061821, MaterialMapColor.r, 12801229, 16711935),
/*  16 */   LIGHT_BLUE(3, "light_blue", 3847130, MaterialMapColor.s, 6719955, 10141901),
/*  17 */   YELLOW(4, "yellow", 16701501, MaterialMapColor.t, 14602026, 16776960),
/*  18 */   LIME(5, "lime", 8439583, MaterialMapColor.u, 4312372, 12582656),
/*  19 */   PINK(6, "pink", 15961002, MaterialMapColor.v, 14188952, 16738740),
/*  20 */   GRAY(7, "gray", 4673362, MaterialMapColor.w, 4408131, 8421504),
/*  21 */   LIGHT_GRAY(8, "light_gray", 10329495, MaterialMapColor.x, 11250603, 13882323),
/*  22 */   CYAN(9, "cyan", 1481884, MaterialMapColor.y, 2651799, 65535),
/*  23 */   PURPLE(10, "purple", 8991416, MaterialMapColor.z, 8073150, 10494192),
/*  24 */   BLUE(11, "blue", 3949738, MaterialMapColor.A, 2437522, 255),
/*  25 */   BROWN(12, "brown", 8606770, MaterialMapColor.B, 5320730, 9127187),
/*  26 */   GREEN(13, "green", 6192150, MaterialMapColor.C, 3887386, 65280),
/*  27 */   RED(14, "red", 11546150, MaterialMapColor.D, 11743532, 16711680),
/*  28 */   BLACK(15, "black", 1908001, MaterialMapColor.E, 1973019, 0);
/*     */   
/*     */   static {
/*  31 */     q = (EnumColor[])Arrays.<EnumColor>stream(values()).sorted(Comparator.comparingInt(EnumColor::getColorIndex)).toArray(var0 -> new EnumColor[var0]);
/*  32 */     r = new Int2ObjectOpenHashMap((Map)Arrays.<EnumColor>stream(values()).collect(Collectors.toMap(var0 -> Integer.valueOf(var0.y), var0 -> var0)));
/*     */   }
/*     */ 
/*     */   
/*     */   private final int s;
/*     */   
/*     */   private final String t;
/*     */   
/*     */   private final MaterialMapColor u;
/*     */   private final int v;
/*     */   
/*     */   EnumColor(int var2, String var3, int var4, MaterialMapColor var5, int var6, int var7) {
/*  44 */     this.s = var2;
/*  45 */     this.t = var3;
/*  46 */     this.v = var4;
/*  47 */     this.u = var5;
/*  48 */     this.z = var7;
/*     */     
/*  50 */     int var8 = (var4 & 0xFF0000) >> 16;
/*  51 */     int var9 = (var4 & 0xFF00) >> 8;
/*  52 */     int var10 = (var4 & 0xFF) >> 0;
/*  53 */     this.w = var10 << 16 | var9 << 8 | var8 << 0;
/*  54 */     this.x = new float[] { var8 / 255.0F, var9 / 255.0F, var10 / 255.0F };
/*  55 */     this.y = var6;
/*     */   }
/*     */   private final int w; private final float[] x; private final int y; private final int z;
/*     */   public int getColorIndex() {
/*  59 */     return this.s;
/*     */   }
/*     */   
/*     */   public String c() {
/*  63 */     return this.t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getColor() {
/*  71 */     return this.x;
/*     */   }
/*     */   
/*     */   public MaterialMapColor f() {
/*  75 */     return this.u;
/*     */   }
/*     */   
/*     */   public int getFireworksColor() {
/*  79 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumColor fromColorIndex(int var0) {
/*  87 */     if (var0 < 0 || var0 >= q.length) {
/*  88 */       var0 = 0;
/*     */     }
/*  90 */     return q[var0];
/*     */   }
/*     */   
/*     */   public static EnumColor a(String var0, EnumColor var1) {
/*  94 */     for (EnumColor var5 : values()) {
/*  95 */       if (var5.t.equals(var0)) {
/*  96 */         return var5;
/*     */       }
/*     */     } 
/*     */     
/* 100 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 110 */     return this.t;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 115 */     return this.t;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */