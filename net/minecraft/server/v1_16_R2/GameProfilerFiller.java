/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface GameProfilerFiller
/*    */ {
/*    */   void a();
/*    */   
/*    */   void b();
/*    */   
/*    */   void enter(String paramString);
/*    */   
/*    */   void a(Supplier<String> paramSupplier);
/*    */   
/*    */   void exit();
/*    */   
/*    */   void exitEnter(String paramString);
/*    */   
/*    */   void c(String paramString);
/*    */   
/*    */   void c(Supplier<String> paramSupplier);
/*    */   
/*    */   static GameProfilerFiller a(GameProfilerFiller var0, GameProfilerFiller var1) {
/* 27 */     if (var0 == GameProfilerDisabled.a) {
/* 28 */       return var1;
/*    */     }
/* 30 */     if (var1 == GameProfilerDisabled.a) {
/* 31 */       return var0;
/*    */     }
/* 33 */     return new GameProfilerFiller(var0, var1)
/*    */       {
/*    */         public void a() {
/* 36 */           this.a.a();
/* 37 */           this.b.a();
/*    */         }
/*    */ 
/*    */         
/*    */         public void b() {
/* 42 */           this.a.b();
/* 43 */           this.b.b();
/*    */         }
/*    */ 
/*    */         
/*    */         public void enter(String var0) {
/* 48 */           this.a.enter(var0);
/* 49 */           this.b.enter(var0);
/*    */         }
/*    */ 
/*    */         
/*    */         public void a(Supplier<String> var0) {
/* 54 */           this.a.a(var0);
/* 55 */           this.b.a(var0);
/*    */         }
/*    */ 
/*    */         
/*    */         public void exit() {
/* 60 */           this.a.exit();
/* 61 */           this.b.exit();
/*    */         }
/*    */ 
/*    */         
/*    */         public void exitEnter(String var0) {
/* 66 */           this.a.exitEnter(var0);
/* 67 */           this.b.exitEnter(var0);
/*    */         }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         public void c(String var0) {
/* 78 */           this.a.c(var0);
/* 79 */           this.b.c(var0);
/*    */         }
/*    */ 
/*    */         
/*    */         public void c(Supplier<String> var0) {
/* 84 */           this.a.c(var0);
/* 85 */           this.b.c(var0);
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameProfilerFiller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */