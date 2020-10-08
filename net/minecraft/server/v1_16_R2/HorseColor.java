/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public enum HorseColor {
/*  7 */   WHITE(0),
/*  8 */   CREAMY(1),
/*  9 */   CHESTNUT(2),
/* 10 */   BROWN(3),
/* 11 */   BLACK(4),
/* 12 */   GRAY(5),
/* 13 */   DARKBROWN(6); private static final HorseColor[] h;
/*    */   
/*    */   static {
/* 16 */     h = (HorseColor[])Arrays.<HorseColor>stream(values()).sorted(Comparator.comparingInt(HorseColor::a)).toArray(var0 -> new HorseColor[var0]);
/*    */   }
/*    */   private final int i;
/*    */   HorseColor(int var2) {
/* 20 */     this.i = var2;
/*    */   }
/*    */   
/*    */   public int a() {
/* 24 */     return this.i;
/*    */   }
/*    */   
/*    */   public static HorseColor a(int var0) {
/* 28 */     return h[var0 % h.length];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\HorseColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */