/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class AttributeRanged
/*    */   extends AttributeBase {
/*    */   private final double a;
/*    */   public double maximum;
/*    */   
/*    */   public AttributeRanged(String s, double d0, double d1, double d2) {
/*  9 */     super(s, d0);
/* 10 */     this.a = d1;
/* 11 */     this.maximum = d2;
/* 12 */     if (d1 > d2)
/* 13 */       throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!"); 
/* 14 */     if (d0 < d1)
/* 15 */       throw new IllegalArgumentException("Default value cannot be lower than minimum value!"); 
/* 16 */     if (d0 > d2) {
/* 17 */       throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public double a(double d0) {
/* 23 */     if (d0 != d0) return getDefault();
/*    */     
/* 25 */     d0 = MathHelper.a(d0, this.a, this.maximum);
/* 26 */     return d0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AttributeRanged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */