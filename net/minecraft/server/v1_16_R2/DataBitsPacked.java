/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataBitsPacked
/*    */ {
/*    */   private final long[] a;
/*    */   private final int b;
/*    */   private final long c;
/*    */   private final int d;
/*    */   
/*    */   public DataBitsPacked(int var0, int var1) {
/* 26 */     this(var0, var1, new long[MathHelper.c(var1 * var0, 64) / 64]);
/*    */   }
/*    */   
/*    */   public DataBitsPacked(int var0, int var1, long[] var2) {
/* 30 */     Validate.inclusiveBetween(1L, 32L, var0);
/*    */     
/* 32 */     this.d = var1;
/* 33 */     this.b = var0;
/* 34 */     this.a = var2;
/* 35 */     this.c = (1L << var0) - 1L;
/*    */     
/* 37 */     int var3 = MathHelper.c(var1 * var0, 64) / 64;
/* 38 */     if (var2.length != var3) {
/* 39 */       throw new IllegalArgumentException("Invalid length given for storage, got: " + var2.length + " but expected: " + var3);
/*    */     }
/*    */   }
/*    */   
/*    */   public void a(int var0, int var1) {
/* 44 */     Validate.inclusiveBetween(0L, (this.d - 1), var0);
/* 45 */     Validate.inclusiveBetween(0L, this.c, var1);
/*    */     
/* 47 */     int var2 = var0 * this.b;
/* 48 */     int var3 = var2 >> 6;
/* 49 */     int var4 = (var0 + 1) * this.b - 1 >> 6;
/* 50 */     int var5 = var2 ^ var3 << 6;
/*    */     
/* 52 */     this.a[var3] = this.a[var3] & (this.c << var5 ^ 0xFFFFFFFFFFFFFFFFL) | (var1 & this.c) << var5;
/* 53 */     if (var3 != var4) {
/* 54 */       int var6 = 64 - var5;
/* 55 */       int var7 = this.b - var6;
/* 56 */       this.a[var4] = this.a[var4] >>> var7 << var7 | (var1 & this.c) >> var6;
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a(int var0) {
/* 61 */     Validate.inclusiveBetween(0L, (this.d - 1), var0);
/*    */     
/* 63 */     int var1 = var0 * this.b;
/* 64 */     int var2 = var1 >> 6;
/* 65 */     int var3 = (var0 + 1) * this.b - 1 >> 6;
/* 66 */     int var4 = var1 ^ var2 << 6;
/*    */     
/* 68 */     if (var2 == var3) {
/* 69 */       return (int)(this.a[var2] >>> var4 & this.c);
/*    */     }
/* 71 */     int var5 = 64 - var4;
/* 72 */     return (int)((this.a[var2] >>> var4 | this.a[var3] << var5) & this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public long[] a() {
/* 77 */     return this.a;
/*    */   }
/*    */   
/*    */   public int b() {
/* 81 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataBitsPacked.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */