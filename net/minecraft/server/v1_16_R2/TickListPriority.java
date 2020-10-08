/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum TickListPriority {
/*  4 */   EXTREMELY_HIGH(-3),
/*  5 */   VERY_HIGH(-2),
/*  6 */   HIGH(-1),
/*  7 */   NORMAL(0),
/*  8 */   LOW(1),
/*  9 */   VERY_LOW(2),
/* 10 */   EXTREMELY_LOW(3);
/*    */   
/*    */   private final int h;
/*    */ 
/*    */   
/*    */   TickListPriority(int var2) {
/* 16 */     this.h = var2;
/*    */   }
/*    */   
/*    */   public static TickListPriority a(int var0) {
/* 20 */     for (TickListPriority var4 : values()) {
/* 21 */       if (var4.h == var0) {
/* 22 */         return var4;
/*    */       }
/*    */     } 
/* 25 */     if (var0 < EXTREMELY_HIGH.h) {
/* 26 */       return EXTREMELY_HIGH;
/*    */     }
/* 28 */     return EXTREMELY_LOW;
/*    */   }
/*    */   
/*    */   public int a() {
/* 32 */     return this.h;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TickListPriority.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */