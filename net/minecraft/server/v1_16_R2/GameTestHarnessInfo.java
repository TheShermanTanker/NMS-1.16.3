/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Stopwatch;
/*     */ import com.google.common.collect.Lists;
/*     */ import it.unimi.dsi.fastutil.objects.Object2LongMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.Collection;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class GameTestHarnessInfo
/*     */ {
/*     */   private final GameTestHarnessTestFunction a;
/*     */   @Nullable
/*     */   private BlockPosition b;
/*     */   private final WorldServer c;
/*  30 */   private final Collection<GameTestHarnessListener> d = Lists.newArrayList();
/*     */   
/*     */   private final int e;
/*  33 */   private final Collection<GameTestHarnessSequence> f = Lists.newCopyOnWriteArrayList();
/*  34 */   private Object2LongMap<Runnable> g = (Object2LongMap<Runnable>)new Object2LongOpenHashMap();
/*     */   
/*     */   private long h;
/*     */   private long i;
/*     */   private boolean j = false;
/*  39 */   private final Stopwatch k = Stopwatch.createUnstarted();
/*     */   
/*     */   private boolean l = false;
/*     */   
/*     */   private final EnumBlockRotation m;
/*     */   @Nullable
/*     */   private Throwable n;
/*     */   
/*     */   public GameTestHarnessInfo(GameTestHarnessTestFunction var0, EnumBlockRotation var1, WorldServer var2) {
/*  48 */     this.a = var0;
/*  49 */     this.c = var2;
/*  50 */     this.e = var0.c();
/*  51 */     this.m = var0.g().a(var1);
/*     */   }
/*     */   
/*     */   void a(BlockPosition var0) {
/*  55 */     this.b = var0;
/*     */   }
/*     */   
/*     */   void a() {
/*  59 */     this.h = this.c.getTime() + 1L + this.a.f();
/*  60 */     this.k.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b() {
/*  67 */     if (k()) {
/*     */       return;
/*     */     }
/*  70 */     this.i = this.c.getTime() - this.h;
/*  71 */     if (this.i < 0L) {
/*     */       return;
/*     */     }
/*  74 */     if (this.i == 0L) {
/*  75 */       v();
/*     */     }
/*  77 */     ObjectIterator<Object2LongMap.Entry<Runnable>> var0 = this.g.object2LongEntrySet().iterator();
/*  78 */     while (var0.hasNext()) {
/*  79 */       Object2LongMap.Entry<Runnable> var1 = (Object2LongMap.Entry<Runnable>)var0.next();
/*  80 */       if (var1.getLongValue() <= this.i) {
/*     */         try {
/*  82 */           ((Runnable)var1.getKey()).run();
/*  83 */         } catch (Exception var2) {
/*  84 */           a(var2);
/*     */         } 
/*  86 */         var0.remove();
/*     */       } 
/*     */     } 
/*  89 */     if (this.i > this.e) {
/*     */       
/*  91 */       if (this.f.isEmpty()) {
/*  92 */         a(new GameTestHarnessTimeout("Didn't succeed or fail within " + this.a.c() + " ticks"));
/*     */       } else {
/*  94 */         this.f.forEach(var0 -> var0.b(this.i));
/*  95 */         if (this.n == null) {
/*  96 */           a(new GameTestHarnessTimeout("No sequences finished"));
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 101 */       this.f.forEach(var0 -> var0.a(this.i));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void v() {
/* 106 */     if (this.j) {
/* 107 */       throw new IllegalStateException("Test already started");
/*     */     }
/* 109 */     this.j = true;
/*     */     try {
/* 111 */       this.a.a(new GameTestHarnessHelper(this));
/* 112 */     } catch (Exception var0) {
/* 113 */       a(var0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String c() {
/* 122 */     return this.a.a();
/*     */   }
/*     */   
/*     */   public BlockPosition d() {
/* 126 */     return this.b;
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
/*     */   public WorldServer g() {
/* 154 */     return this.c;
/*     */   }
/*     */   
/*     */   public boolean h() {
/* 158 */     return (this.l && this.n == null);
/*     */   }
/*     */   
/*     */   public boolean i() {
/* 162 */     return (this.n != null);
/*     */   }
/*     */   
/*     */   public boolean j() {
/* 166 */     return this.j;
/*     */   }
/*     */   
/*     */   public boolean k() {
/* 170 */     return this.l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void x() {
/* 178 */     if (!this.l) {
/* 179 */       this.l = true;
/* 180 */       this.k.stop();
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
/*     */   public void a(Throwable var0) {
/* 193 */     x();
/* 194 */     this.n = var0;
/* 195 */     this.d.forEach(var0 -> var0.c(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Throwable n() {
/* 203 */     return this.n;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 208 */     return c();
/*     */   }
/*     */   
/*     */   public void a(GameTestHarnessListener var0) {
/* 212 */     this.d.add(var0);
/*     */   }
/*     */   
/*     */   public void a(BlockPosition var0, int var1) {
/* 216 */     TileEntityStructure var2 = GameTestHarnessStructures.a(s(), var0, t(), var1, this.c, false);
/* 217 */     a(var2.getPosition());
/*     */     
/* 219 */     var2.setStructureName(c());
/* 220 */     GameTestHarnessStructures.a(this.b, new BlockPosition(1, 0, -1), t(), this.c);
/*     */     
/* 222 */     this.d.forEach(var0 -> var0.a(this));
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
/*     */ 
/*     */   
/*     */   public boolean q() {
/* 236 */     return this.a.d();
/*     */   }
/*     */   
/*     */   public boolean r() {
/* 240 */     return !this.a.d();
/*     */   }
/*     */   
/*     */   public String s() {
/* 244 */     return this.a.b();
/*     */   }
/*     */   
/*     */   public EnumBlockRotation t() {
/* 248 */     return this.m;
/*     */   }
/*     */   
/*     */   public GameTestHarnessTestFunction u() {
/* 252 */     return this.a;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */