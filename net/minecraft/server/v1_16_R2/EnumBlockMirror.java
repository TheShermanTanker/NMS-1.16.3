/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumBlockMirror
/*    */ {
/*  8 */   NONE(PointGroupO.IDENTITY),
/*  9 */   LEFT_RIGHT(PointGroupO.INVERT_Z),
/* 10 */   FRONT_BACK(PointGroupO.INVERT_X);
/*    */   
/*    */   private final PointGroupO d;
/*    */ 
/*    */   
/*    */   EnumBlockMirror(PointGroupO var2) {
/* 16 */     this.d = var2;
/*    */   }
/*    */   
/*    */   public int a(int var0, int var1) {
/* 20 */     int var2 = var1 / 2;
/* 21 */     int var3 = (var0 > var2) ? (var0 - var1) : var0;
/* 22 */     switch (null.a[ordinal()]) {
/*    */       case 1:
/* 24 */         return (var1 - var3) % var1;
/*    */       case 2:
/* 26 */         return (var2 - var3 + var1) % var1;
/*    */     } 
/* 28 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumBlockRotation a(EnumDirection var0) {
/* 33 */     EnumDirection.EnumAxis var1 = var0.n();
/* 34 */     return ((this == LEFT_RIGHT && var1 == EnumDirection.EnumAxis.Z) || (this == FRONT_BACK && var1 == EnumDirection.EnumAxis.X)) ? EnumBlockRotation.CLOCKWISE_180 : EnumBlockRotation.NONE;
/*    */   }
/*    */   
/*    */   public EnumDirection b(EnumDirection var0) {
/* 38 */     if (this == FRONT_BACK && var0.n() == EnumDirection.EnumAxis.X) {
/* 39 */       return var0.opposite();
/*    */     }
/* 41 */     if (this == LEFT_RIGHT && var0.n() == EnumDirection.EnumAxis.Z) {
/* 42 */       return var0.opposite();
/*    */     }
/* 44 */     return var0;
/*    */   }
/*    */   
/*    */   public PointGroupO a() {
/* 48 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumBlockMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */