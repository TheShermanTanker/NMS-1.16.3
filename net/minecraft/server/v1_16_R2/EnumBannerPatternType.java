/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EnumBannerPatternType
/*     */ {
/*  15 */   BASE("base", "b", false),
/*  16 */   SQUARE_BOTTOM_LEFT("square_bottom_left", "bl"),
/*  17 */   SQUARE_BOTTOM_RIGHT("square_bottom_right", "br"),
/*  18 */   SQUARE_TOP_LEFT("square_top_left", "tl"),
/*  19 */   SQUARE_TOP_RIGHT("square_top_right", "tr"),
/*  20 */   STRIPE_BOTTOM("stripe_bottom", "bs"),
/*  21 */   STRIPE_TOP("stripe_top", "ts"),
/*  22 */   STRIPE_LEFT("stripe_left", "ls"),
/*  23 */   STRIPE_RIGHT("stripe_right", "rs"),
/*  24 */   STRIPE_CENTER("stripe_center", "cs"),
/*  25 */   STRIPE_MIDDLE("stripe_middle", "ms"),
/*  26 */   STRIPE_DOWNRIGHT("stripe_downright", "drs"),
/*  27 */   STRIPE_DOWNLEFT("stripe_downleft", "dls"),
/*  28 */   STRIPE_SMALL("small_stripes", "ss"),
/*  29 */   CROSS("cross", "cr"),
/*  30 */   STRAIGHT_CROSS("straight_cross", "sc"),
/*  31 */   TRIANGLE_BOTTOM("triangle_bottom", "bt"),
/*  32 */   TRIANGLE_TOP("triangle_top", "tt"),
/*  33 */   TRIANGLES_BOTTOM("triangles_bottom", "bts"),
/*  34 */   TRIANGLES_TOP("triangles_top", "tts"),
/*  35 */   DIAGONAL_LEFT("diagonal_left", "ld"),
/*  36 */   DIAGONAL_RIGHT("diagonal_up_right", "rd"),
/*  37 */   DIAGONAL_LEFT_MIRROR("diagonal_up_left", "lud"),
/*  38 */   DIAGONAL_RIGHT_MIRROR("diagonal_right", "rud"),
/*  39 */   CIRCLE_MIDDLE("circle", "mc"),
/*  40 */   RHOMBUS_MIDDLE("rhombus", "mr"),
/*  41 */   HALF_VERTICAL("half_vertical", "vh"),
/*  42 */   HALF_HORIZONTAL("half_horizontal", "hh"),
/*  43 */   HALF_VERTICAL_MIRROR("half_vertical_right", "vhr"),
/*  44 */   HALF_HORIZONTAL_MIRROR("half_horizontal_bottom", "hhb"),
/*  45 */   BORDER("border", "bo"),
/*  46 */   CURLY_BORDER("curly_border", "cbo"),
/*  47 */   GRADIENT("gradient", "gra"),
/*  48 */   GRADIENT_UP("gradient_up", "gru"),
/*  49 */   BRICKS("bricks", "bri"),
/*     */ 
/*     */ 
/*     */   
/*  53 */   GLOBE("globe", "glb", true),
/*  54 */   CREEPER("creeper", "cre", true),
/*  55 */   SKULL("skull", "sku", true),
/*  56 */   FLOWER("flower", "flo", true),
/*  57 */   MOJANG("mojang", "moj", true),
/*  58 */   PIGLIN("piglin", "pig", true);
/*     */   
/*     */   static {
/*  61 */     S = values();
/*  62 */     P = S.length;
/*  63 */     Q = (int)Arrays.<EnumBannerPatternType>stream(S).filter(var0 -> var0.T).count();
/*  64 */     R = P - Q - 1;
/*     */   }
/*     */   private static final EnumBannerPatternType[] S;
/*     */   public static final int P;
/*     */   public static final int Q;
/*     */   public static final int R;
/*     */   private final boolean T;
/*     */   private final String U;
/*     */   private final String V;
/*     */   
/*     */   EnumBannerPatternType(String var2, String var3, boolean var4) {
/*  75 */     this.U = var2;
/*  76 */     this.V = var3;
/*  77 */     this.T = var4;
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
/*     */   public String b() {
/*  90 */     return this.V;
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
/*     */   public static class a
/*     */   {
/* 104 */     private final List<Pair<EnumBannerPatternType, EnumColor>> a = Lists.newArrayList();
/*     */     
/*     */     public a a(EnumBannerPatternType var0, EnumColor var1) {
/* 107 */       this.a.add(Pair.of(var0, var1));
/* 108 */       return this;
/*     */     }
/*     */     
/*     */     public NBTTagList a() {
/* 112 */       NBTTagList var0 = new NBTTagList();
/*     */       
/* 114 */       for (Pair<EnumBannerPatternType, EnumColor> var2 : this.a) {
/* 115 */         NBTTagCompound var3 = new NBTTagCompound();
/* 116 */         var3.setString("Pattern", EnumBannerPatternType.a((EnumBannerPatternType)var2.getLeft()));
/* 117 */         var3.setInt("Color", ((EnumColor)var2.getRight()).getColorIndex());
/* 118 */         var0.add(var3);
/*     */       } 
/*     */       
/* 121 */       return var0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumBannerPatternType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */