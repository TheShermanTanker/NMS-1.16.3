/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.mojang.serialization.Codec;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public enum EnumDirection
/*     */   implements INamable
/*     */ {
/*     */   private final int g;
/*     */   private final int h;
/*     */   private final int i;
/*     */   private final String j;
/*     */   private final EnumAxis k;
/*     */   private final EnumAxisDirection l;
/*  28 */   DOWN(0, 1, -1, "down", EnumAxisDirection.NEGATIVE, EnumAxis.Y, new BaseBlockPosition(0, -1, 0)),
/*  29 */   UP(1, 0, -1, "up", EnumAxisDirection.POSITIVE, EnumAxis.Y, new BaseBlockPosition(0, 1, 0)),
/*  30 */   NORTH(2, 3, 2, "north", EnumAxisDirection.NEGATIVE, EnumAxis.Z, new BaseBlockPosition(0, 0, -1)),
/*  31 */   SOUTH(3, 2, 0, "south", EnumAxisDirection.POSITIVE, EnumAxis.Z, new BaseBlockPosition(0, 0, 1)),
/*  32 */   WEST(4, 5, 1, "west", EnumAxisDirection.NEGATIVE, EnumAxis.X, new BaseBlockPosition(-1, 0, 0)),
/*  33 */   EAST(5, 4, 3, "east", EnumAxisDirection.POSITIVE, EnumAxis.X, new BaseBlockPosition(1, 0, 0));
/*     */   
/*     */   private final BaseBlockPosition m;
/*     */   
/*     */   private static final EnumDirection[] n;
/*     */   private static final Map<String, EnumDirection> o;
/*     */   private static final EnumDirection[] p;
/*     */   private static final EnumDirection[] q;
/*     */   private static final Long2ObjectMap<EnumDirection> r;
/*     */   
/*     */   static {
/*  44 */     n = values();
/*     */     
/*  46 */     o = (Map<String, EnumDirection>)Arrays.<EnumDirection>stream(n).collect(Collectors.toMap(EnumDirection::m, var0 -> var0));
/*  47 */     p = (EnumDirection[])Arrays.<EnumDirection>stream(n).sorted(Comparator.comparingInt(var0 -> var0.g)).toArray(var0 -> new EnumDirection[var0]);
/*  48 */     q = (EnumDirection[])Arrays.<EnumDirection>stream(n).filter(var0 -> var0.n().d()).sorted(Comparator.comparingInt(var0 -> var0.i)).toArray(var0 -> new EnumDirection[var0]);
/*  49 */     r = (Long2ObjectMap<EnumDirection>)Arrays.<EnumDirection>stream(n).collect(Collectors.toMap(var0 -> Long.valueOf((new BlockPosition(var0.p())).asLong()), var0 -> var0, (var0, var1) -> { throw new IllegalArgumentException("Duplicate keys"); }it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap::new));
/*     */   }
/*     */   EnumDirection(int var2, int var3, int var4, String var5, EnumAxisDirection var6, EnumAxis var7, BaseBlockPosition var8) {
/*  52 */     this.g = var2;
/*  53 */     this.i = var4;
/*  54 */     this.h = var3;
/*  55 */     this.j = var5;
/*  56 */     this.k = var7;
/*  57 */     this.l = var6;
/*  58 */     this.m = var8;
/*     */   }
/*     */   
/*     */   public static EnumDirection[] a(Entity var0) {
/*  62 */     float var1 = var0.g(1.0F) * 0.017453292F;
/*  63 */     float var2 = -var0.h(1.0F) * 0.017453292F;
/*     */     
/*  65 */     float var3 = MathHelper.sin(var1);
/*  66 */     float var4 = MathHelper.cos(var1);
/*  67 */     float var5 = MathHelper.sin(var2);
/*  68 */     float var6 = MathHelper.cos(var2);
/*     */     
/*  70 */     boolean var7 = (var5 > 0.0F);
/*  71 */     boolean var8 = (var3 < 0.0F);
/*  72 */     boolean var9 = (var6 > 0.0F);
/*     */     
/*  74 */     float var10 = var7 ? var5 : -var5;
/*  75 */     float var11 = var8 ? -var3 : var3;
/*  76 */     float var12 = var9 ? var6 : -var6;
/*     */     
/*  78 */     float var13 = var10 * var4;
/*  79 */     float var14 = var12 * var4;
/*     */     
/*  81 */     EnumDirection var15 = var7 ? EAST : WEST;
/*  82 */     EnumDirection var16 = var8 ? UP : DOWN;
/*  83 */     EnumDirection var17 = var9 ? SOUTH : NORTH;
/*     */     
/*  85 */     if (var10 > var12) {
/*  86 */       if (var11 > var13)
/*  87 */         return a(var16, var15, var17); 
/*  88 */       if (var14 > var11) {
/*  89 */         return a(var15, var17, var16);
/*     */       }
/*  91 */       return a(var15, var16, var17);
/*     */     } 
/*     */     
/*  94 */     if (var11 > var14)
/*  95 */       return a(var16, var17, var15); 
/*  96 */     if (var13 > var11) {
/*  97 */       return a(var17, var15, var16);
/*     */     }
/*  99 */     return a(var17, var16, var15);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static EnumDirection[] a(EnumDirection var0, EnumDirection var1, EnumDirection var2) {
/* 105 */     return new EnumDirection[] { var0, var1, var2, var2.opposite(), var1.opposite(), var0.opposite() };
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
/*     */   public int c() {
/* 139 */     return this.g;
/*     */   }
/*     */   
/*     */   public int get2DRotationValue() {
/* 143 */     return this.i;
/*     */   }
/*     */   
/*     */   public EnumAxisDirection e() {
/* 147 */     return this.l;
/*     */   }
/*     */   
/*     */   public EnumDirection opposite() {
/* 151 */     return fromType1(this.h);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumDirection g() {
/* 205 */     switch (null.a[ordinal()]) {
/*     */       case 3:
/* 207 */         return EAST;
/*     */       case 6:
/* 209 */         return SOUTH;
/*     */       case 4:
/* 211 */         return WEST;
/*     */       case 5:
/* 213 */         return NORTH;
/*     */     } 
/* 215 */     throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
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
/*     */   public EnumDirection h() {
/* 280 */     switch (null.a[ordinal()]) {
/*     */       case 3:
/* 282 */         return WEST;
/*     */       case 6:
/* 284 */         return NORTH;
/*     */       case 4:
/* 286 */         return EAST;
/*     */       case 5:
/* 288 */         return SOUTH;
/*     */     } 
/* 290 */     throw new IllegalStateException("Unable to get CCW facing of " + this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAdjacentX() {
/* 295 */     return this.m.getX();
/*     */   }
/*     */   
/*     */   public int getAdjacentY() {
/* 299 */     return this.m.getY();
/*     */   }
/*     */   
/*     */   public int getAdjacentZ() {
/* 303 */     return this.m.getZ();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String m() {
/* 311 */     return this.j;
/*     */   }
/*     */   
/*     */   public EnumAxis n() {
/* 315 */     return this.k;
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
/*     */   public static EnumDirection fromType1(int var0) {
/* 327 */     return p[MathHelper.a(var0 % p.length)];
/*     */   }
/*     */   
/*     */   public static EnumDirection fromType2(int var0) {
/* 331 */     return q[MathHelper.a(var0 % q.length)];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static EnumDirection a(int var0, int var1, int var2) {
/* 341 */     return (EnumDirection)r.get(BlockPosition.a(var0, var1, var2));
/*     */   }
/*     */   
/*     */   public static EnumDirection fromAngle(double var0) {
/* 345 */     return fromType2(MathHelper.floor(var0 / 90.0D + 0.5D) & 0x3);
/*     */   }
/*     */   
/*     */   public static EnumDirection a(EnumAxis var0, EnumAxisDirection var1) {
/* 349 */     switch (null.b[var0.ordinal()]) {
/*     */       case 1:
/* 351 */         return (var1 == EnumAxisDirection.POSITIVE) ? EAST : WEST;
/*     */       case 2:
/* 353 */         return (var1 == EnumAxisDirection.POSITIVE) ? UP : DOWN;
/*     */     } 
/*     */     
/* 356 */     return (var1 == EnumAxisDirection.POSITIVE) ? SOUTH : NORTH;
/*     */   }
/*     */ 
/*     */   
/*     */   public float o() {
/* 361 */     return ((this.i & 0x3) * 90);
/*     */   }
/*     */   
/*     */   public static EnumDirection a(Random var0) {
/* 365 */     return SystemUtils.<EnumDirection>a(n, var0);
/*     */   }
/*     */   
/*     */   public static EnumDirection a(double var0, double var2, double var4) {
/* 369 */     return a((float)var0, (float)var2, (float)var4);
/*     */   }
/*     */   
/*     */   public static EnumDirection a(float var0, float var1, float var2) {
/* 373 */     EnumDirection var3 = NORTH;
/* 374 */     float var4 = Float.MIN_VALUE;
/* 375 */     for (EnumDirection var8 : n) {
/* 376 */       float var9 = var0 * var8.m.getX() + var1 * var8.m.getY() + var2 * var8.m.getZ();
/*     */       
/* 378 */       if (var9 > var4) {
/* 379 */         var4 = var9;
/* 380 */         var3 = var8;
/*     */       } 
/*     */     } 
/* 383 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 388 */     return this.j;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 393 */     return this.j;
/*     */   }
/*     */   
/*     */   public static EnumDirection a(EnumAxisDirection var0, EnumAxis var1) {
/* 397 */     for (EnumDirection var5 : n) {
/* 398 */       if (var5.e() == var0 && var5.n() == var1) {
/* 399 */         return var5;
/*     */       }
/*     */     } 
/* 402 */     throw new IllegalArgumentException("No such direction: " + var0 + " " + var1);
/*     */   }
/*     */   
/*     */   public enum EnumAxis implements INamable, Predicate<EnumDirection> {
/* 406 */     X("x")
/*     */     {
/*     */       public int a(int var0, int var1, int var2) {
/* 409 */         return var0;
/*     */       }
/*     */ 
/*     */       
/*     */       public double a(double var0, double var2, double var4) {
/* 414 */         return var0;
/*     */       }
/*     */     },
/* 417 */     Y("y")
/*     */     {
/*     */       public int a(int var0, int var1, int var2) {
/* 420 */         return var1;
/*     */       }
/*     */ 
/*     */       
/*     */       public double a(double var0, double var2, double var4) {
/* 425 */         return var2;
/*     */       }
/*     */     },
/* 428 */     Z("z")
/*     */     {
/*     */       public int a(int var0, int var1, int var2) {
/* 431 */         return var2;
/*     */       }
/*     */ 
/*     */       
/*     */       public double a(double var0, double var2, double var4) {
/* 436 */         return var4;
/*     */       }
/*     */     };
/*     */ 
/*     */     
/* 441 */     private static final EnumAxis[] e = values();
/*     */     
/* 443 */     public static final Codec<EnumAxis> d = INamable.a(EnumAxis::values, EnumAxis::a); private static final Map<String, EnumAxis> f;
/*     */     static {
/* 445 */       f = (Map<String, EnumAxis>)Arrays.<EnumAxis>stream(e).collect(Collectors.toMap(EnumAxis::b, var0 -> var0));
/*     */     }
/*     */     private final String g;
/*     */     
/*     */     EnumAxis(String var2) {
/* 450 */       this.g = var2;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public static EnumAxis a(String var0) {
/* 455 */       return f.get(var0.toLowerCase(Locale.ROOT));
/*     */     }
/*     */     
/*     */     public String b() {
/* 459 */       return this.g;
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 463 */       return (this == Y);
/*     */     }
/*     */     
/*     */     public boolean d() {
/* 467 */       return (this == X || this == Z);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 472 */       return this.g;
/*     */     }
/*     */     
/*     */     public static EnumAxis a(Random var0) {
/* 476 */       return SystemUtils.<EnumAxis>a(e, var0);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean test(@Nullable EnumDirection var0) {
/* 481 */       return (var0 != null && var0.n() == this);
/*     */     }
/*     */     
/*     */     public EnumDirection.EnumDirectionLimit e() {
/* 485 */       switch (EnumDirection.null.b[ordinal()]) {
/*     */         case 1:
/*     */         case 3:
/* 488 */           return EnumDirection.EnumDirectionLimit.HORIZONTAL;
/*     */         case 2:
/* 490 */           return EnumDirection.EnumDirectionLimit.VERTICAL;
/*     */       } 
/* 492 */       throw new Error("Someone's been tampering with the universe!");
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 497 */       return this.g;
/*     */     }
/*     */     
/*     */     public abstract int a(int param1Int1, int param1Int2, int param1Int3);
/*     */     
/*     */     public abstract double a(double param1Double1, double param1Double2, double param1Double3);
/*     */   }
/*     */   
/*     */   public enum EnumAxisDirection {
/* 506 */     POSITIVE(1, "Towards positive"),
/* 507 */     NEGATIVE(-1, "Towards negative");
/*     */     
/*     */     private final int c;
/*     */     
/*     */     private final String d;
/*     */     
/*     */     EnumAxisDirection(int var2, String var3) {
/* 514 */       this.c = var2;
/* 515 */       this.d = var3;
/*     */     }
/*     */     
/*     */     public int a() {
/* 519 */       return this.c;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 528 */       return this.d;
/*     */     }
/*     */     
/*     */     public EnumAxisDirection c() {
/* 532 */       return (this == POSITIVE) ? NEGATIVE : POSITIVE;
/*     */     }
/*     */   }
/*     */   
/*     */   public BaseBlockPosition p() {
/* 537 */     return this.m;
/*     */   }
/*     */   
/*     */   public boolean a(float var0) {
/* 541 */     float var1 = var0 * 0.017453292F;
/* 542 */     float var2 = -MathHelper.sin(var1);
/* 543 */     float var3 = MathHelper.cos(var1);
/* 544 */     return (this.m.getX() * var2 + this.m.getZ() * var3 > 0.0F);
/*     */   }
/*     */   
/*     */   public enum EnumDirectionLimit implements Iterable<EnumDirection>, Predicate<EnumDirection> {
/* 548 */     HORIZONTAL((String)new EnumDirection[] { EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.WEST }, new EnumDirection.EnumAxis[] { EnumDirection.EnumAxis.X, EnumDirection.EnumAxis.Z }),
/* 549 */     VERTICAL((String)new EnumDirection[] { EnumDirection.UP, EnumDirection.DOWN }, new EnumDirection.EnumAxis[] { EnumDirection.EnumAxis.Y });
/*     */     
/*     */     private final EnumDirection[] c;
/*     */     
/*     */     private final EnumDirection.EnumAxis[] d;
/*     */     
/*     */     EnumDirectionLimit(EnumDirection[] var2, EnumDirection.EnumAxis[] var3) {
/* 556 */       this.c = var2;
/* 557 */       this.d = var3;
/*     */     }
/*     */     
/*     */     public EnumDirection a(Random var0) {
/* 561 */       return SystemUtils.<EnumDirection>a(this.c, var0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean test(@Nullable EnumDirection var0) {
/* 570 */       return (var0 != null && var0.n().e() == this);
/*     */     }
/*     */ 
/*     */     
/*     */     public Iterator<EnumDirection> iterator() {
/* 575 */       return (Iterator<EnumDirection>)Iterators.forArray((Object[])this.c);
/*     */     }
/*     */     
/*     */     public Stream<EnumDirection> a() {
/* 579 */       return Arrays.stream(this.c);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumDirection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */