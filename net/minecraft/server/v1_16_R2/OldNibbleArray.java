/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class OldNibbleArray {
/*    */   public final byte[] a;
/*    */   private final int b;
/*    */   private final int c;
/*    */   
/*    */   public OldNibbleArray(byte[] var0, int var1) {
/*  9 */     this.a = var0;
/* 10 */     this.b = var1;
/* 11 */     this.c = var1 + 4;
/*    */   }
/*    */   
/*    */   public int a(int var0, int var1, int var2) {
/* 15 */     int var3 = var0 << this.c | var2 << this.b | var1;
/* 16 */     int var4 = var3 >> 1;
/* 17 */     int var5 = var3 & 0x1;
/*    */     
/* 19 */     if (var5 == 0) {
/* 20 */       return this.a[var4] & 0xF;
/*    */     }
/* 22 */     return this.a[var4] >> 4 & 0xF;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\OldNibbleArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */