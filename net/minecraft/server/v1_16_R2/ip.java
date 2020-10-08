/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ 
/*     */ public abstract class ip
/*     */ {
/*  17 */   private final Map<iq, List<ir>> a = Maps.newHashMap();
/*     */   
/*     */   protected void a(iq var0, List<ir> var1) {
/*  20 */     List<ir> var2 = this.a.put(var0, var1);
/*  21 */     if (var2 != null) {
/*  22 */       throw new IllegalStateException("Value " + var0 + " is already defined");
/*     */     }
/*     */   }
/*     */   
/*     */   Map<iq, List<ir>> a() {
/*  27 */     c();
/*  28 */     return (Map<iq, List<ir>>)ImmutableMap.copyOf(this.a);
/*     */   }
/*     */   
/*     */   private void c() {
/*  32 */     List<IBlockState<?>> var0 = b();
/*  33 */     Stream<iq> var1 = Stream.of(iq.a());
/*  34 */     for (IBlockState<?> var3 : var0) {
/*  35 */       var1 = var1.flatMap(var1 -> var0.c().map(var1::a));
/*     */     }
/*  37 */     List<iq> var2 = (List<iq>)var1.filter(var0 -> !this.a.containsKey(var0)).collect(Collectors.toList());
/*  38 */     if (!var2.isEmpty()) {
/*  39 */       throw new IllegalStateException("Missing definition for properties: " + var2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T1 extends Comparable<T1>> a<T1> a(IBlockState<T1> var0) {
/*  46 */     return new a<>(var0);
/*     */   }
/*     */   
/*     */   public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> b<T1, T2> a(IBlockState<T1> var0, IBlockState<T2> var1) {
/*  50 */     return new b<>(var0, var1);
/*     */   }
/*     */   
/*     */   public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> c<T1, T2, T3> a(IBlockState<T1> var0, IBlockState<T2> var1, IBlockState<T3> var2) {
/*  54 */     return new c<>(var0, var1, var2);
/*     */   }
/*     */   
/*     */   public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>> d<T1, T2, T3, T4> a(IBlockState<T1> var0, IBlockState<T2> var1, IBlockState<T3> var2, IBlockState<T4> var3) {
/*  58 */     return new d<>(var0, var1, var2, var3);
/*     */   }
/*     */   
/*     */   public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>, T5 extends Comparable<T5>> e<T1, T2, T3, T4, T5> a(IBlockState<T1> var0, IBlockState<T2> var1, IBlockState<T3> var2, IBlockState<T4> var3, IBlockState<T5> var4) {
/*  62 */     return new e<>(var0, var1, var2, var3, var4);
/*     */   }
/*     */   
/*     */   abstract List<IBlockState<?>> b();
/*     */   
/*     */   public static class a<T1 extends Comparable<T1>> extends ip {
/*     */     private a(IBlockState<T1> var0) {
/*  69 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<IBlockState<?>> b() {
/*  74 */       return (List<IBlockState<?>>)ImmutableList.of(this.a);
/*     */     }
/*     */     private final IBlockState<T1> a;
/*     */     public a<T1> a(T1 var0, List<ir> var1) {
/*  78 */       iq var2 = iq.a((IBlockState.a<?>[])new IBlockState.a[] { this.a
/*  79 */             .b(var0) });
/*     */       
/*  81 */       a(var2, var1);
/*  82 */       return this;
/*     */     }
/*     */     
/*     */     public a<T1> a(T1 var0, ir var1) {
/*  86 */       return a(var0, Collections.singletonList(var1));
/*     */     }
/*     */     
/*     */     public ip a(Function<T1, ir> var0) {
/*  90 */       this.a.getValues().forEach(var1 -> a((T1)var1, var0.apply(var1)));
/*     */ 
/*     */       
/*  93 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class b<T1 extends Comparable<T1>, T2 extends Comparable<T2>>
/*     */     extends ip
/*     */   {
/*     */     private final IBlockState<T1> a;
/*     */ 
/*     */     
/*     */     private final IBlockState<T2> b;
/*     */ 
/*     */     
/*     */     private b(IBlockState<T1> var0, IBlockState<T2> var1) {
/* 109 */       this.a = var0;
/* 110 */       this.b = var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<IBlockState<?>> b() {
/* 115 */       return (List<IBlockState<?>>)ImmutableList.of(this.a, this.b);
/*     */     }
/*     */     
/*     */     public b<T1, T2> a(T1 var0, T2 var1, List<ir> var2) {
/* 119 */       iq var3 = iq.a((IBlockState.a<?>[])new IBlockState.a[] { this.a
/* 120 */             .b(var0), this.b
/* 121 */             .b(var1) });
/*     */       
/* 123 */       a(var3, var2);
/* 124 */       return this;
/*     */     }
/*     */     
/*     */     public b<T1, T2> a(T1 var0, T2 var1, ir var2) {
/* 128 */       return a(var0, var1, Collections.singletonList(var2));
/*     */     }
/*     */     
/*     */     public ip a(BiFunction<T1, T2, ir> var0) {
/* 132 */       this.a.getValues().forEach(var1 -> this.b.getValues().forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       return this;
/*     */     }
/*     */     
/*     */     public ip b(BiFunction<T1, T2, List<ir>> var0) {
/* 141 */       this.a.getValues().forEach(var1 -> this.b.getValues().forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 146 */       return this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class c<T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> extends ip {
/*     */     private final IBlockState<T1> a;
/*     */     private final IBlockState<T2> b;
/*     */     private final IBlockState<T3> c;
/*     */     
/*     */     private c(IBlockState<T1> var0, IBlockState<T2> var1, IBlockState<T3> var2) {
/* 156 */       this.a = var0;
/* 157 */       this.b = var1;
/* 158 */       this.c = var2;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<IBlockState<?>> b() {
/* 163 */       return (List<IBlockState<?>>)ImmutableList.of(this.a, this.b, this.c);
/*     */     }
/*     */     
/*     */     public c<T1, T2, T3> a(T1 var0, T2 var1, T3 var2, List<ir> var3) {
/* 167 */       iq var4 = iq.a((IBlockState.a<?>[])new IBlockState.a[] { this.a
/* 168 */             .b(var0), this.b
/* 169 */             .b(var1), this.c
/* 170 */             .b(var2) });
/*     */       
/* 172 */       a(var4, var3);
/* 173 */       return this;
/*     */     }
/*     */     
/*     */     public c<T1, T2, T3> a(T1 var0, T2 var1, T3 var2, ir var3) {
/* 177 */       return a(var0, var1, var2, Collections.singletonList(var3));
/*     */     }
/*     */     
/*     */     public ip a(ip.h<T1, T2, T3, ir> var0) {
/* 181 */       this.a.getValues().forEach(var1 -> this.b.getValues().forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 188 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class d<T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>>
/*     */     extends ip
/*     */   {
/*     */     private final IBlockState<T1> a;
/*     */ 
/*     */     
/*     */     private final IBlockState<T2> b;
/*     */ 
/*     */     
/*     */     private final IBlockState<T3> c;
/*     */ 
/*     */     
/*     */     private final IBlockState<T4> d;
/*     */ 
/*     */     
/*     */     private d(IBlockState<T1> var0, IBlockState<T2> var1, IBlockState<T3> var2, IBlockState<T4> var3) {
/* 210 */       this.a = var0;
/* 211 */       this.b = var1;
/* 212 */       this.c = var2;
/* 213 */       this.d = var3;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<IBlockState<?>> b() {
/* 218 */       return (List<IBlockState<?>>)ImmutableList.of(this.a, this.b, this.c, this.d);
/*     */     }
/*     */     
/*     */     public d<T1, T2, T3, T4> a(T1 var0, T2 var1, T3 var2, T4 var3, List<ir> var4) {
/* 222 */       iq var5 = iq.a((IBlockState.a<?>[])new IBlockState.a[] { this.a
/* 223 */             .b(var0), this.b
/* 224 */             .b(var1), this.c
/* 225 */             .b(var2), this.d
/* 226 */             .b(var3) });
/*     */       
/* 228 */       a(var5, var4);
/* 229 */       return this;
/*     */     }
/*     */     
/*     */     public d<T1, T2, T3, T4> a(T1 var0, T2 var1, T3 var2, T4 var3, ir var4) {
/* 233 */       return a(var0, var1, var2, var3, Collections.singletonList(var4));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class e<T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>, T5 extends Comparable<T5>>
/*     */     extends ip
/*     */   {
/*     */     private final IBlockState<T1> a;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final IBlockState<T2> b;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final IBlockState<T3> c;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final IBlockState<T4> d;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final IBlockState<T5> e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private e(IBlockState<T1> var0, IBlockState<T2> var1, IBlockState<T3> var2, IBlockState<T4> var3, IBlockState<T5> var4) {
/* 271 */       this.a = var0;
/* 272 */       this.b = var1;
/* 273 */       this.c = var2;
/* 274 */       this.d = var3;
/* 275 */       this.e = var4;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<IBlockState<?>> b() {
/* 280 */       return (List<IBlockState<?>>)ImmutableList.of(this.a, this.b, this.c, this.d, this.e);
/*     */     }
/*     */     
/*     */     public e<T1, T2, T3, T4, T5> a(T1 var0, T2 var1, T3 var2, T4 var3, T5 var4, List<ir> var5) {
/* 284 */       iq var6 = iq.a((IBlockState.a<?>[])new IBlockState.a[] { this.a
/* 285 */             .b(var0), this.b
/* 286 */             .b(var1), this.c
/* 287 */             .b(var2), this.d
/* 288 */             .b(var3), this.e
/* 289 */             .b(var4) });
/*     */       
/* 291 */       a(var6, var5);
/* 292 */       return this;
/*     */     }
/*     */     
/*     */     public e<T1, T2, T3, T4, T5> a(T1 var0, T2 var1, T3 var2, T4 var3, T5 var4, ir var5) {
/* 296 */       return a(var0, var1, var2, var3, var4, Collections.singletonList(var5));
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface h<P1, P2, P3, R> {
/*     */     R apply(P1 param1P1, P2 param1P2, P3 param1P3);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */