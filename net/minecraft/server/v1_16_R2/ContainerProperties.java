/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class ContainerProperties implements IContainerProperties {
/*    */   private final int[] a;
/*    */   
/*    */   public ContainerProperties(int var0) {
/*  7 */     this.a = new int[var0];
/*    */   }
/*    */ 
/*    */   
/*    */   public int getProperty(int var0) {
/* 12 */     return this.a[var0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setProperty(int var0, int var1) {
/* 17 */     this.a[var0] = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 22 */     return this.a.length;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */