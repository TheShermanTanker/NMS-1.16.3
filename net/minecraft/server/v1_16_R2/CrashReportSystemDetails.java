/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrashReportSystemDetails
/*     */ {
/*     */   private final CrashReport a;
/*     */   private final String b;
/*  14 */   private final List<CrashReportDetail> c = Lists.newArrayList();
/*  15 */   private StackTraceElement[] d = new StackTraceElement[0];
/*     */   
/*     */   public CrashReportSystemDetails(CrashReport var0, String var1) {
/*  18 */     this.a = var0;
/*  19 */     this.b = var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(BlockPosition var0) {
/*  27 */     return a(var0.getX(), var0.getY(), var0.getZ());
/*     */   }
/*     */   
/*     */   public static String a(int var0, int var1, int var2) {
/*  31 */     StringBuilder var3 = new StringBuilder();
/*     */     
/*     */     try {
/*  34 */       var3.append(String.format("World: (%d,%d,%d)", new Object[] { Integer.valueOf(var0), Integer.valueOf(var1), Integer.valueOf(var2) }));
/*  35 */     } catch (Throwable var4) {
/*  36 */       var3.append("(Error finding world loc)");
/*     */     } 
/*     */     
/*  39 */     var3.append(", ");
/*     */     
/*     */     try {
/*  42 */       int var4 = var0 >> 4;
/*  43 */       int var5 = var2 >> 4;
/*  44 */       int var6 = var0 & 0xF;
/*  45 */       int var7 = var1 >> 4;
/*  46 */       int var8 = var2 & 0xF;
/*  47 */       int var9 = var4 << 4;
/*  48 */       int var10 = var5 << 4;
/*  49 */       int var11 = (var4 + 1 << 4) - 1;
/*  50 */       int var12 = (var5 + 1 << 4) - 1;
/*  51 */       var3.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", new Object[] { Integer.valueOf(var6), Integer.valueOf(var7), Integer.valueOf(var8), Integer.valueOf(var4), Integer.valueOf(var5), Integer.valueOf(var9), Integer.valueOf(var10), Integer.valueOf(var11), Integer.valueOf(var12) }));
/*  52 */     } catch (Throwable var4) {
/*  53 */       var3.append("(Error finding chunk loc)");
/*     */     } 
/*     */     
/*  56 */     var3.append(", ");
/*     */     
/*     */     try {
/*  59 */       int var4 = var0 >> 9;
/*  60 */       int var5 = var2 >> 9;
/*  61 */       int var6 = var4 << 5;
/*  62 */       int var7 = var5 << 5;
/*  63 */       int var8 = (var4 + 1 << 5) - 1;
/*  64 */       int var9 = (var5 + 1 << 5) - 1;
/*  65 */       int var10 = var4 << 9;
/*  66 */       int var11 = var5 << 9;
/*  67 */       int var12 = (var4 + 1 << 9) - 1;
/*  68 */       int var13 = (var5 + 1 << 9) - 1;
/*  69 */       var3.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", new Object[] { Integer.valueOf(var4), Integer.valueOf(var5), Integer.valueOf(var6), Integer.valueOf(var7), Integer.valueOf(var8), Integer.valueOf(var9), Integer.valueOf(var10), Integer.valueOf(var11), Integer.valueOf(var12), Integer.valueOf(var13) }));
/*  70 */     } catch (Throwable var4) {
/*  71 */       var3.append("(Error finding world loc)");
/*     */     } 
/*     */     
/*  74 */     return var3.toString();
/*     */   }
/*     */   
/*     */   public CrashReportSystemDetails a(String var0, CrashReportCallable<String> var1) {
/*     */     try {
/*  79 */       a(var0, var1.call());
/*  80 */     } catch (Throwable var2) {
/*  81 */       a(var0, var2);
/*     */     } 
/*  83 */     return this;
/*     */   }
/*     */   
/*     */   public CrashReportSystemDetails a(String var0, Object var1) {
/*  87 */     this.c.add(new CrashReportDetail(var0, var1));
/*  88 */     return this;
/*     */   }
/*     */   
/*     */   public void a(String var0, Throwable var1) {
/*  92 */     a(var0, var1);
/*     */   }
/*     */   
/*     */   public int a(int var0) {
/*  96 */     StackTraceElement[] var1 = Thread.currentThread().getStackTrace();
/*     */ 
/*     */     
/*  99 */     if (var1.length <= 0) {
/* 100 */       return 0;
/*     */     }
/*     */     
/* 103 */     this.d = new StackTraceElement[var1.length - 3 - var0];
/* 104 */     System.arraycopy(var1, 3 + var0, this.d, 0, this.d.length);
/* 105 */     return this.d.length;
/*     */   }
/*     */   
/*     */   public boolean a(StackTraceElement var0, StackTraceElement var1) {
/* 109 */     if (this.d.length == 0 || var0 == null) {
/* 110 */       return false;
/*     */     }
/*     */     
/* 113 */     StackTraceElement var2 = this.d[0];
/*     */ 
/*     */     
/* 116 */     if (var2.isNativeMethod() != var0.isNativeMethod() || 
/* 117 */       !var2.getClassName().equals(var0.getClassName()) || 
/* 118 */       !var2.getFileName().equals(var0.getFileName()) || 
/* 119 */       !var2.getMethodName().equals(var0.getMethodName()))
/*     */     {
/* 121 */       return false;
/*     */     }
/*     */     
/* 124 */     if (((var1 != null) ? true : false) != ((this.d.length > 1) ? true : false)) {
/* 125 */       return false;
/*     */     }
/* 127 */     if (var1 != null && !this.d[1].equals(var1)) {
/* 128 */       return false;
/*     */     }
/*     */     
/* 131 */     this.d[0] = var0;
/*     */     
/* 133 */     return true;
/*     */   }
/*     */   
/*     */   public void b(int var0) {
/* 137 */     StackTraceElement[] var1 = new StackTraceElement[this.d.length - var0];
/* 138 */     System.arraycopy(this.d, 0, var1, 0, var1.length);
/* 139 */     this.d = var1;
/*     */   }
/*     */   
/*     */   public void a(StringBuilder var0) {
/* 143 */     var0.append("-- ").append(this.b).append(" --\n");
/* 144 */     var0.append("Details:");
/*     */     
/* 146 */     for (CrashReportDetail var2 : this.c) {
/* 147 */       var0.append("\n\t");
/* 148 */       var0.append(var2.a());
/* 149 */       var0.append(": ");
/* 150 */       var0.append(var2.b());
/*     */     } 
/*     */     
/* 153 */     if (this.d != null && this.d.length > 0) {
/* 154 */       var0.append("\nStacktrace:");
/*     */       
/* 156 */       for (StackTraceElement var4 : this.d) {
/* 157 */         var0.append("\n\tat ");
/* 158 */         var0.append(var4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public StackTraceElement[] a() {
/* 164 */     return this.d;
/*     */   }
/*     */   
/*     */   public static void a(CrashReportSystemDetails var0, BlockPosition var1, @Nullable IBlockData var2) {
/* 168 */     if (var2 != null) {
/* 169 */       var0.a("Block", var2::toString);
/*     */     }
/*     */     
/* 172 */     var0.a("Block location", () -> a(var0));
/*     */   }
/*     */   
/*     */   static class CrashReportDetail {
/*     */     private final String a;
/*     */     private final String b;
/*     */     
/*     */     public CrashReportDetail(String var0, @Nullable Object var1) {
/* 180 */       this.a = var0;
/*     */       
/* 182 */       if (var1 == null) {
/* 183 */         this.b = "~~NULL~~";
/* 184 */       } else if (var1 instanceof Throwable) {
/* 185 */         Throwable var2 = (Throwable)var1;
/* 186 */         this.b = "~~ERROR~~ " + var2.getClass().getSimpleName() + ": " + var2.getMessage();
/*     */       } else {
/* 188 */         this.b = var1.toString();
/*     */       } 
/*     */     }
/*     */     
/*     */     public String a() {
/* 193 */       return this.a;
/*     */     }
/*     */     
/*     */     public String b() {
/* 197 */       return this.b;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CrashReportSystemDetails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */