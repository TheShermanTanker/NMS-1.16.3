/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorRunSometimes<E extends EntityLiving>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private boolean b;
/*    */   private boolean c;
/*    */   private final IntRange d;
/*    */   private final Behavior<? super E> e;
/*    */   private int f;
/*    */   
/*    */   public BehaviorRunSometimes(Behavior<? super E> var0, IntRange var1) {
/* 19 */     this(var0, false, var1);
/*    */   }
/*    */   
/*    */   public BehaviorRunSometimes(Behavior<? super E> var0, boolean var1, IntRange var2) {
/* 23 */     super(var0.a);
/*    */     
/* 25 */     this.e = var0;
/* 26 */     this.b = !var1;
/* 27 */     this.d = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 32 */     if (!this.e.a(var0, var1)) {
/* 33 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 37 */     if (this.b) {
/* 38 */       a(var0);
/* 39 */       this.b = false;
/*    */     } 
/*    */     
/* 42 */     if (this.f > 0) {
/* 43 */       this.f--;
/*    */     }
/*    */     
/* 46 */     return (!this.c && this.f == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 51 */     this.e.a(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer var0, E var1, long var2) {
/* 56 */     return this.e.b(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void d(WorldServer var0, E var1, long var2) {
/* 61 */     this.e.d(var0, var1, var2);
/*    */     
/* 63 */     this.c = (this.e.a() == Behavior.Status.RUNNING);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(WorldServer var0, E var1, long var2) {
/* 68 */     a(var0);
/* 69 */     this.e.c(var0, var1, var2);
/*    */   }
/*    */   
/*    */   private void a(WorldServer var0) {
/* 73 */     this.f = this.d.a(var0.random);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(long var0) {
/* 78 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 83 */     return "RunSometimes: " + this.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorRunSometimes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */