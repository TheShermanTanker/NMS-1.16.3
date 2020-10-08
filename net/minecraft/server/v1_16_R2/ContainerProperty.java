/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public abstract class ContainerProperty {
/*    */   public static ContainerProperty a(IContainerProperties var0, int var1) {
/*  5 */     return new ContainerProperty(var0, var1)
/*    */       {
/*    */         public int get() {
/*  8 */           return this.a.getProperty(this.b);
/*    */         }
/*    */ 
/*    */         
/*    */         public void set(int var0) {
/* 13 */           this.a.setProperty(this.b, var0);
/*    */         }
/*    */       };
/*    */   }
/*    */   private int a;
/*    */   public static ContainerProperty a(int[] var0, int var1) {
/* 19 */     return new ContainerProperty(var0, var1)
/*    */       {
/*    */         public int get() {
/* 22 */           return this.a[this.b];
/*    */         }
/*    */ 
/*    */         
/*    */         public void set(int var0) {
/* 27 */           this.a[this.b] = var0;
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   public static ContainerProperty a() {
/* 33 */     return new ContainerProperty()
/*    */       {
/*    */         private int a;
/*    */         
/*    */         public int get() {
/* 38 */           return this.a;
/*    */         }
/*    */ 
/*    */         
/*    */         public void set(int var0) {
/* 43 */           this.a = var0;
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract int get();
/*    */ 
/*    */   
/*    */   public abstract void set(int paramInt);
/*    */   
/*    */   public boolean c() {
/* 55 */     int var0 = get();
/* 56 */     boolean var1 = (var0 != this.a);
/* 57 */     this.a = var0;
/* 58 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */