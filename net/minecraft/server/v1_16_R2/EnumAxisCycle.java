/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum EnumAxisCycle {
/*  4 */   NONE
/*    */   {
/*    */     public int a(int var0, int var1, int var2, EnumDirection.EnumAxis var3) {
/*  7 */       return var3.a(var0, var1, var2);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public EnumDirection.EnumAxis a(EnumDirection.EnumAxis var0) {
/* 17 */       return var0;
/*    */     }
/*    */ 
/*    */     
/*    */     public EnumAxisCycle a() {
/* 22 */       return this;
/*    */     }
/*    */   },
/*    */ 
/*    */ 
/*    */   
/* 28 */   FORWARD
/*    */   {
/*    */     public int a(int var0, int var1, int var2, EnumDirection.EnumAxis var3) {
/* 31 */       return var3.a(var2, var0, var1);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public EnumDirection.EnumAxis a(EnumDirection.EnumAxis var0) {
/* 41 */       return d[Math.floorMod(var0.ordinal() + 1, 3)];
/*    */     }
/*    */ 
/*    */     
/*    */     public EnumAxisCycle a() {
/* 46 */       return BACKWARD;
/*    */     }
/*    */   },
/* 49 */   BACKWARD
/*    */   {
/*    */     public int a(int var0, int var1, int var2, EnumDirection.EnumAxis var3) {
/* 52 */       return var3.a(var1, var2, var0);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public EnumDirection.EnumAxis a(EnumDirection.EnumAxis var0) {
/* 62 */       return d[Math.floorMod(var0.ordinal() - 1, 3)];
/*    */     }
/*    */ 
/*    */     
/*    */     public EnumAxisCycle a() {
/* 67 */       return FORWARD;
/*    */     }
/*    */   };
/*    */   
/*    */   static {
/* 72 */     d = EnumDirection.EnumAxis.values();
/* 73 */     e = values();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final EnumDirection.EnumAxis[] d;
/*    */ 
/*    */ 
/*    */   
/*    */   public static final EnumAxisCycle[] e;
/*    */ 
/*    */ 
/*    */   
/*    */   public static EnumAxisCycle a(EnumDirection.EnumAxis var0, EnumDirection.EnumAxis var1) {
/* 88 */     return e[Math.floorMod(var1.ordinal() - var0.ordinal(), 3)];
/*    */   }
/*    */   
/*    */   public abstract int a(int paramInt1, int paramInt2, int paramInt3, EnumDirection.EnumAxis paramEnumAxis);
/*    */   
/*    */   public abstract EnumDirection.EnumAxis a(EnumDirection.EnumAxis paramEnumAxis);
/*    */   
/*    */   public abstract EnumAxisCycle a();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumAxisCycle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */