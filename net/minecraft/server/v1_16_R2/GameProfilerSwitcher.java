/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.IntSupplier;
/*    */ import java.util.function.LongSupplier;
/*    */ 
/*    */ public class GameProfilerSwitcher {
/*    */   private final LongSupplier a;
/*    */   private final IntSupplier b;
/*  9 */   private GameProfilerFillerActive c = GameProfilerDisabled.a;
/*    */   
/*    */   public GameProfilerSwitcher(LongSupplier var0, IntSupplier var1) {
/* 12 */     this.a = var0;
/* 13 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 17 */     return (this.c != GameProfilerDisabled.a);
/*    */   }
/*    */   
/*    */   public void b() {
/* 21 */     this.c = GameProfilerDisabled.a;
/*    */   }
/*    */   
/*    */   public void c() {
/* 25 */     this.c = new MethodProfiler(this.a, this.b, true);
/*    */   }
/*    */   
/*    */   public GameProfilerFiller d() {
/* 29 */     return this.c;
/*    */   }
/*    */   
/*    */   public MethodProfilerResults e() {
/* 33 */     return this.c.d();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameProfilerSwitcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */