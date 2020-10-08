/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import it.unimi.dsi.fastutil.objects.Object2LongMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2LongMaps;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ObjectUtils;
/*     */ 
/*     */ 
/*     */ public class MethodProfilerResultsFilled
/*     */   implements MethodProfilerResults
/*     */ {
/*  28 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  30 */   private static final MethodProfilerResult b = new MethodProfilerResult()
/*     */     {
/*     */       public long a() {
/*  33 */         return 0L;
/*     */       }
/*     */ 
/*     */       
/*     */       public long b() {
/*  38 */         return 0L;
/*     */       }
/*     */ 
/*     */       
/*     */       public Object2LongMap<String> c() {
/*  43 */         return Object2LongMaps.emptyMap();
/*     */       }
/*     */     };
/*     */   private static final Comparator<Map.Entry<String, a>> d;
/*  47 */   private static final Splitter c = Splitter.on('\036'); static {
/*  48 */     d = Map.Entry.<String, a>comparingByValue(Comparator.comparingLong(var0 -> a.a(var0))).reversed();
/*     */   }
/*     */   private final Map<String, ? extends MethodProfilerResult> e;
/*     */   private final long f;
/*     */   private final int g;
/*     */   private final long h;
/*     */   private final int i;
/*     */   private final int j;
/*     */   
/*     */   public MethodProfilerResultsFilled(Map<String, ? extends MethodProfilerResult> var0, long var1, int var3, long var4, int var6) {
/*  58 */     this.e = var0;
/*  59 */     this.f = var1;
/*  60 */     this.g = var3;
/*  61 */     this.h = var4;
/*  62 */     this.i = var6;
/*  63 */     this.j = var6 - var3;
/*     */   }
/*     */ 
/*     */   
/*     */   private MethodProfilerResult c(String var0) {
/*  68 */     MethodProfilerResult var1 = this.e.get(var0);
/*  69 */     return (var1 != null) ? var1 : b;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<MethodProfilerResultsField> a(String var0) {
/*  74 */     String var1 = var0;
/*  75 */     MethodProfilerResult var2 = c("root");
/*  76 */     long var3 = var2.a();
/*  77 */     MethodProfilerResult var5 = c(var0);
/*  78 */     long var6 = var5.a();
/*  79 */     long var8 = var5.b();
/*     */     
/*  81 */     List<MethodProfilerResultsField> var10 = Lists.newArrayList();
/*     */     
/*  83 */     if (!var0.isEmpty()) {
/*  84 */       var0 = var0 + '\036';
/*     */     }
/*  86 */     long var11 = 0L;
/*     */     
/*  88 */     for (String var14 : this.e.keySet()) {
/*  89 */       if (a(var0, var14)) {
/*  90 */         var11 += c(var14).a();
/*     */       }
/*     */     } 
/*     */     
/*  94 */     float var13 = (float)var11;
/*  95 */     if (var11 < var6) {
/*  96 */       var11 = var6;
/*     */     }
/*  98 */     if (var3 < var11) {
/*  99 */       var3 = var11;
/*     */     }
/*     */     
/* 102 */     for (String var15 : this.e.keySet()) {
/* 103 */       if (a(var0, var15)) {
/* 104 */         MethodProfilerResult var16 = c(var15);
/* 105 */         long var17 = var16.a();
/* 106 */         double var19 = var17 * 100.0D / var11;
/* 107 */         double var21 = var17 * 100.0D / var3;
/* 108 */         String var23 = var15.substring(var0.length());
/* 109 */         var10.add(new MethodProfilerResultsField(var23, var19, var21, var16.b()));
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     if ((float)var11 > var13) {
/* 114 */       var10.add(new MethodProfilerResultsField("unspecified", ((float)var11 - var13) * 100.0D / var11, ((float)var11 - var13) * 100.0D / var3, var8));
/*     */     }
/*     */     
/* 117 */     Collections.sort(var10);
/* 118 */     var10.add(0, new MethodProfilerResultsField(var1, 100.0D, var11 * 100.0D / var3, var8));
/* 119 */     return var10;
/*     */   }
/*     */   
/*     */   private static boolean a(String var0, String var1) {
/* 123 */     return (var1.length() > var0.length() && var1.startsWith(var0) && var1.indexOf('\036', var0.length() + 1) < 0);
/*     */   }
/*     */   
/*     */   private Map<String, a> h() {
/* 127 */     Map<String, a> var0 = Maps.newTreeMap();
/* 128 */     this.e.forEach((var1, var2) -> {
/*     */           Object2LongMap<String> var3 = var2.c();
/*     */           
/*     */           if (!var3.isEmpty()) {
/*     */             List<String> var4 = c.splitToList(var1);
/*     */             
/*     */             var3.forEach(());
/*     */           } 
/*     */         });
/* 137 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public long a() {
/* 142 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b() {
/* 147 */     return this.g;
/*     */   }
/*     */ 
/*     */   
/*     */   public long c() {
/* 152 */     return this.h;
/*     */   }
/*     */ 
/*     */   
/*     */   public int d() {
/* 157 */     return this.i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(File var0) {
/* 162 */     var0.getParentFile().mkdirs();
/*     */     
/* 164 */     Writer var1 = null;
/*     */     try {
/* 166 */       var1 = new OutputStreamWriter(new FileOutputStream(var0), StandardCharsets.UTF_8);
/* 167 */       var1.write(a(g(), f()));
/* 168 */       return true;
/* 169 */     } catch (Throwable var2) {
/* 170 */       LOGGER.error("Could not save profiler results to {}", var0, var2);
/* 171 */       return false;
/*     */     } finally {
/* 173 */       IOUtils.closeQuietly(var1);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String a(long var0, int var2) {
/* 178 */     StringBuilder var3 = new StringBuilder();
/*     */     
/* 180 */     var3.append("---- Minecraft Profiler Results ----\n");
/* 181 */     var3.append("// ");
/* 182 */     var3.append(i());
/* 183 */     var3.append("\n\n");
/*     */     
/* 185 */     var3.append("Version: ").append(SharedConstants.getGameVersion().getId()).append('\n');
/* 186 */     var3.append("Time span: ").append(var0 / 1000000L).append(" ms\n");
/* 187 */     var3.append("Tick span: ").append(var2).append(" ticks\n");
/* 188 */     var3.append("// This is approximately ").append(String.format(Locale.ROOT, "%.2f", new Object[] { Float.valueOf(var2 / (float)var0 / 1.0E9F) })).append(" ticks per second. It should be ").append(20).append(" ticks per second\n\n");
/*     */     
/* 190 */     var3.append("--- BEGIN PROFILE DUMP ---\n\n");
/*     */     
/* 192 */     a(0, "root", var3);
/*     */     
/* 194 */     var3.append("--- END PROFILE DUMP ---\n\n");
/*     */     
/* 196 */     Map<String, a> var4 = h();
/*     */     
/* 198 */     if (!var4.isEmpty()) {
/* 199 */       var3.append("--- BEGIN COUNTER DUMP ---\n\n");
/* 200 */       a(var4, var3, var2);
/* 201 */       var3.append("--- END COUNTER DUMP ---\n\n");
/*     */     } 
/*     */     
/* 204 */     return var3.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static StringBuilder a(StringBuilder var0, int var1) {
/* 215 */     var0.append(String.format("[%02d] ", new Object[] { Integer.valueOf(var1) }));
/* 216 */     for (int var2 = 0; var2 < var1; var2++) {
/* 217 */       var0.append("|   ");
/*     */     }
/* 219 */     return var0;
/*     */   }
/*     */   
/*     */   private void a(int var0, String var1, StringBuilder var2) {
/* 223 */     List<MethodProfilerResultsField> var3 = a(var1);
/*     */     
/* 225 */     Object2LongMap<String> var4 = ((MethodProfilerResult)ObjectUtils.firstNonNull((Object[])new MethodProfilerResult[] { this.e.get(var1), b })).c();
/* 226 */     var4.forEach((var2, var3) -> a(var0, var1).append('#').append(var2).append(' ').append(var3).append('/').append(var3.longValue() / this.j).append('\n'));
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
/* 237 */     if (var3.size() < 3) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 242 */     for (int var5 = 1; var5 < var3.size(); var5++) {
/* 243 */       MethodProfilerResultsField var6 = var3.get(var5);
/*     */       
/* 245 */       a(var2, var0)
/* 246 */         .append(var6.d)
/* 247 */         .append('(')
/* 248 */         .append(var6.c)
/* 249 */         .append('/')
/* 250 */         .append(String.format(Locale.ROOT, "%.0f", new Object[] { Float.valueOf((float)var6.c / this.j)
/* 251 */             })).append(')')
/* 252 */         .append(" - ")
/* 253 */         .append(String.format(Locale.ROOT, "%.2f", new Object[] { Double.valueOf(var6.a) })).append("%/")
/* 254 */         .append(String.format(Locale.ROOT, "%.2f", new Object[] { Double.valueOf(var6.b) })).append("%\n");
/*     */       
/* 256 */       if (!"unspecified".equals(var6.d)) {
/*     */         try {
/* 258 */           a(var0 + 1, var1 + '\036' + var6.d, var2);
/* 259 */         } catch (Exception var7) {
/* 260 */           var2.append("[[ EXCEPTION ").append(var7).append(" ]]");
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(int var0, String var1, a var2, int var3, StringBuilder var4) {
/* 267 */     a(var4, var0)
/* 268 */       .append(var1).append(" total:")
/* 269 */       .append(a.b(var2)).append('/')
/* 270 */       .append(a.a(var2)).append(" average: ")
/* 271 */       .append(a.b(var2) / var3)
/* 272 */       .append('/')
/* 273 */       .append(a.a(var2) / var3)
/* 274 */       .append('\n');
/* 275 */     a.c(var2).entrySet().stream().sorted(d).forEach(var3 -> a(var0 + 1, (String)var3.getKey(), (a)var3.getValue(), var1, var2));
/*     */   }
/*     */   
/*     */   private void a(Map<String, a> var0, StringBuilder var1, int var2) {
/* 279 */     var0.forEach((var2, var3) -> {
/*     */           var0.append("-- Counter: ").append(var2).append(" --\n");
/*     */           a(0, "root", (a)a.c(var3).get("root"), var1, var0);
/*     */           var0.append("\n\n");
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private static String i() {
/* 288 */     String[] var0 = { "Shiny numbers!", "Am I not running fast enough? :(", "I'm working as hard as I can!", "Will I ever be good enough for you? :(", "Speedy. Zoooooom!", "Hello world", "40% better than a crash report.", "Now with extra numbers", "Now with less numbers", "Now with the same numbers", "You should add flames to things, it makes them go faster!", "Do you feel the need for... optimization?", "*cracks redstone whip*", "Maybe if you treated it better then it'll have more motivation to work faster! Poor server." };
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
/*     */     try {
/* 306 */       return var0[(int)(SystemUtils.getMonotonicNanos() % var0.length)];
/* 307 */     } catch (Throwable var1) {
/* 308 */       return "Witty comment unavailable :(";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int f() {
/* 314 */     return this.j;
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private long a;
/*     */     private long b;
/* 320 */     private final Map<String, a> c = Maps.newHashMap();
/*     */     
/*     */     public void a(Iterator<String> var0, long var1) {
/* 323 */       this.b += var1;
/* 324 */       if (!var0.hasNext()) {
/* 325 */         this.a += var1;
/*     */       } else {
/* 327 */         ((a)this.c.computeIfAbsent(var0.next(), var0 -> new a())).a(var0, var1);
/*     */       } 
/*     */     }
/*     */     
/*     */     private a() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MethodProfilerResultsFilled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */