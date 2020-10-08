/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ public enum PointGroupS
/*    */ {
/*  8 */   P123(0, 1, 2),
/*  9 */   P213(1, 0, 2),
/* 10 */   P132(0, 2, 1),
/* 11 */   P231(1, 2, 0),
/* 12 */   P312(2, 0, 1),
/* 13 */   P321(2, 1, 0);
/*    */   
/*    */   private final int[] g;
/*    */   private final Matrix3f h;
/*    */   private static final PointGroupS[][] i;
/*    */   
/*    */   PointGroupS(int var2, int var3, int var4) {
/* 20 */     this.g = new int[] { var2, var3, var4 };
/* 21 */     this.h = new Matrix3f();
/* 22 */     this.h.a(0, a(0), 1.0F);
/* 23 */     this.h.a(1, a(1), 1.0F);
/* 24 */     this.h.a(2, a(2), 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   static {
/* 29 */     i = SystemUtils.<PointGroupS[][]>a(new PointGroupS[(values()).length][(values()).length], var0 -> {
/*    */           for (PointGroupS var4 : values()) {
/*    */             for (PointGroupS var8 : values()) {
/*    */               int[] var9 = new int[3];
/*    */               for (int i = 0; i < 3; i++) {
/*    */                 var9[i] = var4.g[var8.g[i]];
/*    */               }
/*    */               PointGroupS var10 = Arrays.<PointGroupS>stream(values()).filter(()).findFirst().get();
/*    */               var0[var4.ordinal()][var8.ordinal()] = var10;
/*    */             } 
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PointGroupS a(PointGroupS var0) {
/* 46 */     return i[ordinal()][var0.ordinal()];
/*    */   }
/*    */   
/*    */   public int a(int var0) {
/* 50 */     return this.g[var0];
/*    */   }
/*    */   
/*    */   public Matrix3f a() {
/* 54 */     return this.h;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PointGroupS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */