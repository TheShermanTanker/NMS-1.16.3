/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class iz
/*     */ {
/*  15 */   private final Map<ja, MinecraftKey> a = Maps.newHashMap();
/*  16 */   private final Set<ja> b = Sets.newHashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public iz a(ja var0, MinecraftKey var1) {
/*  22 */     this.a.put(var0, var1);
/*  23 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Stream<ja> a() {
/*  33 */     return this.b.stream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public iz b(ja var0, ja var1) {
/*  42 */     this.a.put(var1, this.a.get(var0));
/*  43 */     this.b.add(var1);
/*  44 */     return this;
/*     */   }
/*     */   
/*     */   public MinecraftKey a(ja var0) {
/*  48 */     ja var1 = var0;
/*  49 */     while (var1 != null) {
/*  50 */       MinecraftKey var2 = this.a.get(var1);
/*  51 */       if (var2 != null) {
/*  52 */         return var2;
/*     */       }
/*  54 */       var1 = var1.b();
/*     */     } 
/*  56 */     throw new IllegalStateException("Can't find texture for slot " + var0);
/*     */   }
/*     */   
/*     */   public iz c(ja var0, MinecraftKey var1) {
/*  60 */     iz var2 = new iz();
/*  61 */     var2.a.putAll(this.a);
/*  62 */     var2.b.addAll(this.b);
/*  63 */     var2.a(var0, var1);
/*  64 */     return var2;
/*     */   }
/*     */   
/*     */   public static iz a(Block var0) {
/*  68 */     MinecraftKey var1 = C(var0);
/*  69 */     return b(var1);
/*     */   }
/*     */   
/*     */   public static iz b(Block var0) {
/*  73 */     MinecraftKey var1 = C(var0);
/*  74 */     return a(var1);
/*     */   }
/*     */   
/*     */   public static iz a(MinecraftKey var0) {
/*  78 */     return (new iz()).a(ja.b, var0);
/*     */   }
/*     */   
/*     */   public static iz b(MinecraftKey var0) {
/*  82 */     return (new iz()).a(ja.a, var0);
/*     */   }
/*     */   
/*     */   public static iz c(Block var0) {
/*  86 */     return d(ja.p, C(var0));
/*     */   }
/*     */   
/*     */   public static iz c(MinecraftKey var0) {
/*  90 */     return d(ja.p, var0);
/*     */   }
/*     */   
/*     */   public static iz d(Block var0) {
/*  94 */     return d(ja.q, C(var0));
/*     */   }
/*     */   
/*     */   public static iz d(MinecraftKey var0) {
/*  98 */     return d(ja.q, var0);
/*     */   }
/*     */   
/*     */   public static iz e(Block var0) {
/* 102 */     return d(ja.s, C(var0));
/*     */   }
/*     */   
/*     */   public static iz e(MinecraftKey var0) {
/* 106 */     return d(ja.s, var0);
/*     */   }
/*     */   
/*     */   public static iz f(Block var0) {
/* 110 */     return d(ja.t, C(var0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static iz g(Block var0) {
/* 118 */     return d(ja.y, C(var0));
/*     */   }
/*     */   
/*     */   public static iz a(Block var0, Block var1) {
/* 122 */     return (new iz())
/* 123 */       .a(ja.y, C(var0))
/* 124 */       .a(ja.z, C(var1));
/*     */   }
/*     */ 
/*     */   
/*     */   public static iz h(Block var0) {
/* 129 */     return d(ja.u, C(var0));
/*     */   }
/*     */   
/*     */   public static iz i(Block var0) {
/* 133 */     return d(ja.x, C(var0));
/*     */   }
/*     */   
/*     */   public static iz g(MinecraftKey var0) {
/* 137 */     return d(ja.A, var0);
/*     */   }
/*     */   
/*     */   public static iz b(Block var0, Block var1) {
/* 141 */     return (new iz()).a(ja.v, C(var0)).a(ja.w, a(var1, "_top"));
/*     */   }
/*     */   
/*     */   public static iz d(ja var0, MinecraftKey var1) {
/* 145 */     return (new iz()).a(var0, var1);
/*     */   }
/*     */   
/*     */   public static iz j(Block var0) {
/* 149 */     return (new iz())
/* 150 */       .a(ja.i, a(var0, "_side"))
/* 151 */       .a(ja.d, a(var0, "_top"));
/*     */   }
/*     */   
/*     */   public static iz k(Block var0) {
/* 155 */     return (new iz())
/* 156 */       .a(ja.i, a(var0, "_side"))
/* 157 */       .a(ja.f, a(var0, "_top"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static iz l(Block var0) {
/* 162 */     return (new iz()).a(ja.i, C(var0)).a(ja.d, a(var0, "_top"));
/*     */   }
/*     */   
/*     */   public static iz a(MinecraftKey var0, MinecraftKey var1) {
/* 166 */     return (new iz()).a(ja.i, var0).a(ja.d, var1);
/*     */   }
/*     */   
/*     */   public static iz m(Block var0) {
/* 170 */     return (new iz())
/* 171 */       .a(ja.i, a(var0, "_side"))
/* 172 */       .a(ja.f, a(var0, "_top"))
/* 173 */       .a(ja.e, a(var0, "_bottom"));
/*     */   }
/*     */   
/*     */   public static iz n(Block var0) {
/* 177 */     MinecraftKey var1 = C(var0);
/* 178 */     return (new iz())
/* 179 */       .a(ja.r, var1)
/* 180 */       .a(ja.i, var1)
/* 181 */       .a(ja.f, a(var0, "_top"))
/* 182 */       .a(ja.e, a(var0, "_bottom"));
/*     */   }
/*     */   
/*     */   public static iz o(Block var0) {
/* 186 */     MinecraftKey var1 = C(var0);
/* 187 */     return (new iz())
/* 188 */       .a(ja.r, var1)
/* 189 */       .a(ja.i, var1)
/* 190 */       .a(ja.d, a(var0, "_top"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static iz p(Block var0) {
/* 198 */     return (new iz()).a(ja.f, a(var0, "_top")).a(ja.e, a(var0, "_bottom"));
/*     */   }
/*     */   
/*     */   public static iz q(Block var0) {
/* 202 */     return (new iz()).a(ja.c, C(var0));
/*     */   }
/*     */   
/*     */   public static iz h(MinecraftKey var0) {
/* 206 */     return (new iz()).a(ja.c, var0);
/*     */   }
/*     */   
/*     */   public static iz r(Block var0) {
/* 210 */     return (new iz()).a(ja.C, a(var0, "_0"));
/*     */   }
/*     */   
/*     */   public static iz s(Block var0) {
/* 214 */     return (new iz()).a(ja.C, a(var0, "_1"));
/*     */   }
/*     */   
/*     */   public static iz t(Block var0) {
/* 218 */     return (new iz()).a(ja.D, C(var0));
/*     */   }
/*     */   
/*     */   public static iz u(Block var0) {
/* 222 */     return (new iz()).a(ja.G, C(var0));
/*     */   }
/*     */   
/*     */   public static iz i(MinecraftKey var0) {
/* 226 */     return (new iz()).a(ja.G, var0);
/*     */   }
/*     */   
/*     */   public static iz a(Item var0) {
/* 230 */     return (new iz()).a(ja.c, c(var0));
/*     */   }
/*     */   
/*     */   public static iz v(Block var0) {
/* 234 */     return (new iz())
/* 235 */       .a(ja.i, a(var0, "_side"))
/* 236 */       .a(ja.g, a(var0, "_front"))
/* 237 */       .a(ja.h, a(var0, "_back"));
/*     */   }
/*     */   
/*     */   public static iz w(Block var0) {
/* 241 */     return (new iz())
/* 242 */       .a(ja.i, a(var0, "_side"))
/* 243 */       .a(ja.g, a(var0, "_front"))
/* 244 */       .a(ja.f, a(var0, "_top"))
/* 245 */       .a(ja.e, a(var0, "_bottom"));
/*     */   }
/*     */   
/*     */   public static iz x(Block var0) {
/* 249 */     return (new iz())
/* 250 */       .a(ja.i, a(var0, "_side"))
/* 251 */       .a(ja.g, a(var0, "_front"))
/* 252 */       .a(ja.f, a(var0, "_top"));
/*     */   }
/*     */   
/*     */   public static iz y(Block var0) {
/* 256 */     return (new iz())
/* 257 */       .a(ja.i, a(var0, "_side"))
/* 258 */       .a(ja.g, a(var0, "_front"))
/* 259 */       .a(ja.d, a(var0, "_end"));
/*     */   }
/*     */   
/*     */   public static iz z(Block var0) {
/* 263 */     return (new iz()).a(ja.f, a(var0, "_top"));
/*     */   }
/*     */   
/*     */   public static iz c(Block var0, Block var1) {
/* 267 */     return (new iz())
/* 268 */       .a(ja.c, a(var0, "_front"))
/* 269 */       .a(ja.o, C(var1))
/* 270 */       .a(ja.n, a(var0, "_top"))
/* 271 */       .a(ja.j, a(var0, "_front"))
/* 272 */       .a(ja.l, a(var0, "_side"))
/* 273 */       .a(ja.k, a(var0, "_side"))
/* 274 */       .a(ja.m, a(var0, "_front"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static iz d(Block var0, Block var1) {
/* 279 */     return (new iz())
/* 280 */       .a(ja.c, a(var0, "_front"))
/* 281 */       .a(ja.o, C(var1))
/* 282 */       .a(ja.n, a(var0, "_top"))
/* 283 */       .a(ja.j, a(var0, "_front"))
/* 284 */       .a(ja.k, a(var0, "_front"))
/* 285 */       .a(ja.l, a(var0, "_side"))
/* 286 */       .a(ja.m, a(var0, "_side"));
/*     */   }
/*     */   
/*     */   public static iz A(Block var0) {
/* 290 */     return (new iz())
/* 291 */       .a(ja.I, a(var0, "_log_lit"))
/* 292 */       .a(ja.C, a(var0, "_fire"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static iz b(Item var0) {
/* 297 */     return (new iz()).a(ja.H, c(var0));
/*     */   }
/*     */   
/*     */   public static iz B(Block var0) {
/* 301 */     return (new iz()).a(ja.H, C(var0));
/*     */   }
/*     */   
/*     */   public static iz j(MinecraftKey var0) {
/* 305 */     return (new iz()).a(ja.H, var0);
/*     */   }
/*     */   
/*     */   public static MinecraftKey C(Block var0) {
/* 309 */     MinecraftKey var1 = IRegistry.BLOCK.getKey(var0);
/* 310 */     return new MinecraftKey(var1.getNamespace(), "block/" + var1.getKey());
/*     */   }
/*     */   
/*     */   public static MinecraftKey a(Block var0, String var1) {
/* 314 */     MinecraftKey var2 = IRegistry.BLOCK.getKey(var0);
/* 315 */     return new MinecraftKey(var2.getNamespace(), "block/" + var2.getKey() + var1);
/*     */   }
/*     */   
/*     */   public static MinecraftKey c(Item var0) {
/* 319 */     MinecraftKey var1 = IRegistry.ITEM.getKey(var0);
/* 320 */     return new MinecraftKey(var1.getNamespace(), "item/" + var1.getKey());
/*     */   }
/*     */   
/*     */   public static MinecraftKey a(Item var0, String var1) {
/* 324 */     MinecraftKey var2 = IRegistry.ITEM.getKey(var0);
/* 325 */     return new MinecraftKey(var2.getNamespace(), "item/" + var2.getKey() + var1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\iz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */