/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Map;
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
/*     */ public abstract class Behavior<E extends EntityLiving>
/*     */ {
/*     */   protected final Map<MemoryModuleType<?>, MemoryStatus> a;
/*  18 */   private Status b = Status.STOPPED;
/*     */   private long c;
/*     */   private final int d;
/*     */   private final int e;
/*     */   
/*     */   public Behavior(Map<MemoryModuleType<?>, MemoryStatus> var0) {
/*  24 */     this(var0, 60);
/*     */   }
/*     */   
/*     */   public Behavior(Map<MemoryModuleType<?>, MemoryStatus> var0, int var1) {
/*  28 */     this(var0, var1, var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Behavior(Map<MemoryModuleType<?>, MemoryStatus> var0, int var1, int var2) {
/*  35 */     this.d = var1;
/*  36 */     this.e = var2;
/*  37 */     this.a = var0;
/*     */   }
/*     */   
/*     */   public Status a() {
/*  41 */     return this.b;
/*     */   }
/*     */   
/*     */   public final boolean e(WorldServer var0, E var1, long var2) {
/*  45 */     if (a(var1) && a(var0, var1)) {
/*  46 */       this.b = Status.RUNNING;
/*  47 */       int var4 = this.d + var0.getRandom().nextInt(this.e + 1 - this.d);
/*  48 */       this.c = var2 + var4;
/*  49 */       a(var0, var1, var2);
/*  50 */       return true;
/*     */     } 
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, E var1, long var2) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public final void f(WorldServer var0, E var1, long var2) {
/*  62 */     if (!a(var2) && b(var0, var1, var2)) {
/*  63 */       d(var0, var1, var2);
/*     */     } else {
/*  65 */       g(var0, var1, var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void d(WorldServer var0, E var1, long var2) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public final void g(WorldServer var0, E var1, long var2) {
/*  76 */     this.b = Status.STOPPED;
/*  77 */     c(var0, var1, var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void c(WorldServer var0, E var1, long var2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean b(WorldServer var0, E var1, long var2) {
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(long var0) {
/* 102 */     return (var0 > this.c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(WorldServer var0, E var1) {
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 115 */     return getClass().getSimpleName();
/*     */   }
/*     */   
/*     */   private boolean a(E var0) {
/* 119 */     for (Map.Entry<MemoryModuleType<?>, MemoryStatus> var2 : this.a.entrySet()) {
/* 120 */       MemoryModuleType<?> var3 = var2.getKey();
/* 121 */       MemoryStatus var4 = var2.getValue();
/* 122 */       if (!var0.getBehaviorController().a(var3, var4)) {
/* 123 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 127 */     return true;
/*     */   }
/*     */   
/*     */   public enum Status {
/* 131 */     STOPPED,
/* 132 */     RUNNING;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Behavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */