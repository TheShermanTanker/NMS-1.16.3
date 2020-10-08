/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import it.unimi.dsi.fastutil.longs.LongArrayList;
/*     */ import it.unimi.dsi.fastutil.longs.LongList;
/*     */ import it.unimi.dsi.fastutil.objects.Object2LongMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2LongMaps;
/*     */ import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
/*     */ import java.time.Duration;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.IntSupplier;
/*     */ import java.util.function.LongSupplier;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.util.Supplier;
/*     */ 
/*     */ public class MethodProfiler
/*     */   implements GameProfilerFillerActive {
/*  23 */   private static final long a = Duration.ofMillis(100L).toNanos();
/*  24 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  26 */   private final List<String> c = Lists.newArrayList();
/*  27 */   private final LongList d = (LongList)new LongArrayList();
/*  28 */   private final Map<String, a> e = Maps.newHashMap();
/*     */   private final IntSupplier f;
/*     */   private final LongSupplier g;
/*     */   private final long h;
/*     */   private final int i;
/*  33 */   private String j = "";
/*     */   
/*     */   private boolean k;
/*     */   
/*     */   @Nullable
/*     */   private a l;
/*     */   private final boolean m;
/*     */   
/*     */   public MethodProfiler(LongSupplier var0, IntSupplier var1, boolean var2) {
/*  42 */     this.h = var0.getAsLong();
/*  43 */     this.g = var0;
/*  44 */     this.i = var1.getAsInt();
/*  45 */     this.f = var1;
/*  46 */     this.m = var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a() {
/*  51 */     if (this.k) {
/*  52 */       LOGGER.error("Profiler tick already started - missing endTick()?");
/*     */       
/*     */       return;
/*     */     } 
/*  56 */     this.k = true;
/*  57 */     this.j = "";
/*  58 */     this.c.clear();
/*  59 */     enter("root");
/*     */   }
/*     */ 
/*     */   
/*     */   public void b() {
/*  64 */     if (!this.k) {
/*  65 */       LOGGER.error("Profiler tick already ended - missing startTick()?");
/*     */       
/*     */       return;
/*     */     } 
/*  69 */     exit();
/*  70 */     this.k = false;
/*     */     
/*  72 */     if (!this.j.isEmpty()) {
/*  73 */       LOGGER.error("Profiler tick ended before path was fully popped (remainder: '{}'). Mismatched push/pop?", new Supplier[] { () -> MethodProfilerResults.b(this.j) });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void enter(String var0) {
/*  79 */     if (!this.k) {
/*  80 */       LOGGER.error("Cannot push '{}' to profiler if profiler tick hasn't started - missing startTick()?", var0);
/*     */       
/*     */       return;
/*     */     } 
/*  84 */     if (!this.j.isEmpty()) {
/*  85 */       this.j += '\036';
/*     */     }
/*  87 */     this.j += var0;
/*  88 */     this.c.add(this.j);
/*  89 */     this.d.add(SystemUtils.getMonotonicNanos());
/*  90 */     this.l = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Supplier<String> var0) {
/*  95 */     enter(var0.get());
/*     */   }
/*     */ 
/*     */   
/*     */   public void exit() {
/* 100 */     if (!this.k) {
/* 101 */       LOGGER.error("Cannot pop from profiler if profiler tick hasn't started - missing startTick()?");
/*     */       return;
/*     */     } 
/* 104 */     if (this.d.isEmpty()) {
/* 105 */       LOGGER.error("Tried to pop one too many times! Mismatched push() and pop()?");
/*     */       return;
/*     */     } 
/* 108 */     long var0 = SystemUtils.getMonotonicNanos();
/* 109 */     long var2 = this.d.removeLong(this.d.size() - 1);
/* 110 */     this.c.remove(this.c.size() - 1);
/* 111 */     long var4 = var0 - var2;
/*     */     
/* 113 */     a var6 = e();
/* 114 */     a.a(var6, a.a(var6) + var4);
/* 115 */     a.b(var6, a.b(var6) + 1L);
/*     */     
/* 117 */     if (this.m && var4 > a) {
/* 118 */       LOGGER.warn("Something's taking too long! '{}' took aprox {} ms", new Supplier[] { () -> MethodProfilerResults.b(this.j), () -> Double.valueOf(var0 / 1000000.0D) });
/*     */     }
/*     */     
/* 121 */     this.j = this.c.isEmpty() ? "" : this.c.get(this.c.size() - 1);
/* 122 */     this.l = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void exitEnter(String var0) {
/* 127 */     exit();
/* 128 */     enter(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private a e() {
/* 138 */     if (this.l == null) {
/* 139 */       this.l = this.e.computeIfAbsent(this.j, var0 -> new a());
/*     */     }
/*     */     
/* 142 */     return this.l;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(String var0) {
/* 147 */     a.c(e()).addTo(var0, 1L);
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(Supplier<String> var0) {
/* 152 */     a.c(e()).addTo(var0.get(), 1L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodProfilerResults d() {
/* 158 */     return new MethodProfilerResultsFilled((Map)this.e, this.h, this.i, this.g.getAsLong(), this.f.getAsInt());
/*     */   }
/*     */   
/*     */   static class a implements MethodProfilerResult {
/*     */     private long a;
/*     */     private long b;
/* 164 */     private Object2LongOpenHashMap<String> c = new Object2LongOpenHashMap();
/*     */ 
/*     */     
/*     */     public long a() {
/* 168 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public long b() {
/* 173 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object2LongMap<String> c() {
/* 178 */       return Object2LongMaps.unmodifiable((Object2LongMap)this.c);
/*     */     }
/*     */     
/*     */     private a() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MethodProfiler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */