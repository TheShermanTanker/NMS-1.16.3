/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class AttributeBase
/*    */ {
/*    */   private final double a;
/*    */   private boolean b;
/*    */   private final String c;
/*    */   
/*    */   protected AttributeBase(String var0, double var1) {
/* 10 */     this.a = var1;
/* 11 */     this.c = var0;
/*    */   }
/*    */   
/*    */   public double getDefault() {
/* 15 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 20 */     return this.b;
/*    */   }
/*    */   
/*    */   public AttributeBase a(boolean var0) {
/* 24 */     this.b = var0;
/* 25 */     return this;
/*    */   }
/*    */   
/*    */   public double a(double var0) {
/* 29 */     return var0;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 33 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AttributeBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */