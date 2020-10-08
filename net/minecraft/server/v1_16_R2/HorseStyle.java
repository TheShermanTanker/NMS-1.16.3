/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public enum HorseStyle {
/*  7 */   NONE(0),
/*  8 */   WHITE(1),
/*  9 */   WHITE_FIELD(2),
/* 10 */   WHITE_DOTS(3),
/* 11 */   BLACK_DOTS(4); private static final HorseStyle[] f;
/*    */   
/*    */   static {
/* 14 */     f = (HorseStyle[])Arrays.<HorseStyle>stream(values()).sorted(Comparator.comparingInt(HorseStyle::a)).toArray(var0 -> new HorseStyle[var0]);
/*    */   }
/*    */   private final int g;
/*    */   HorseStyle(int var2) {
/* 18 */     this.g = var2;
/*    */   }
/*    */   
/*    */   public int a() {
/* 22 */     return this.g;
/*    */   }
/*    */   
/*    */   public static HorseStyle a(int var0) {
/* 26 */     return f[var0 % f.length];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\HorseStyle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */