/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GameTestHarnessSequence
/*     */ {
/*     */   private final GameTestHarnessInfo a;
/*     */   private final List<GameTestHarnessEvent> b;
/*     */   private long c;
/*     */   
/*     */   public void a(long var0) {
/*     */     try {
/*  88 */       c(var0);
/*  89 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(long var0) {
/*     */     try {
/*  95 */       c(var0);
/*  96 */     } catch (Exception var2) {
/*  97 */       this.a.a(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void c(long var0) {
/* 110 */     Iterator<GameTestHarnessEvent> var2 = this.b.iterator();
/* 111 */     while (var2.hasNext()) {
/* 112 */       GameTestHarnessEvent var3 = var2.next();
/* 113 */       var3.b.run();
/* 114 */       var2.remove();
/* 115 */       long var4 = var0 - this.c;
/* 116 */       long var6 = this.c;
/* 117 */       this.c = var0;
/* 118 */       if (var3.a != null && var3.a.longValue() != var4) {
/* 119 */         this.a.a(new GameTestHarnessAssertion("Succeeded in invalid tick: expected " + (var6 + var3.a.longValue()) + ", but current tick is " + var0));
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessSequence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */