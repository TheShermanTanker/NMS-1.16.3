/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Map;
/*     */ import java.util.Timer;
/*     */ import java.util.UUID;
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
/*     */ public class MojangStatisticsGenerator
/*     */ {
/*  21 */   private final Map<String, Object> a = Maps.newHashMap();
/*  22 */   private final Map<String, Object> b = Maps.newHashMap();
/*     */   
/*  24 */   private final String c = UUID.randomUUID().toString();
/*     */   private final URL d;
/*     */   private final IMojangStatistics e;
/*  27 */   private final Timer f = new Timer("Snooper Timer", true);
/*  28 */   private final Object g = new Object();
/*     */   
/*     */   private final long h;
/*     */   private boolean i;
/*     */   
/*     */   public MojangStatisticsGenerator(String var0, IMojangStatistics var1, long var2) {
/*     */     try {
/*  35 */       this.d = new URL("http://snoop.minecraft.net/" + var0 + "?version=" + '\002');
/*  36 */     } catch (MalformedURLException var4) {
/*  37 */       throw new IllegalArgumentException();
/*     */     } 
/*     */     
/*  40 */     this.e = var1;
/*  41 */     this.h = var2;
/*     */   }
/*     */   
/*     */   public void a() {
/*  45 */     if (!this.i);
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
/*     */   public void b() {
/*  96 */     b("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
/*  97 */     b("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
/*  98 */     b("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
/*  99 */     b("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
/*     */     
/* 101 */     this.e.a(this);
/*     */   }
/*     */   
/*     */   public void a(String var0, Object var1) {
/* 105 */     synchronized (this.g) {
/* 106 */       this.b.put(var0, var1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void b(String var0, Object var1) {
/* 111 */     synchronized (this.g) {
/* 112 */       this.a.put(var0, var1);
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
/*     */   public boolean d() {
/* 135 */     return this.i;
/*     */   }
/*     */   
/*     */   public void e() {
/* 139 */     this.f.cancel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long g() {
/* 147 */     return this.h;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MojangStatisticsGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */