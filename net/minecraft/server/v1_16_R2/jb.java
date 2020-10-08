/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class jb
/*    */ {
/* 13 */   public static final a a = a(iz::a, iy.c);
/* 14 */   public static final a b = a(iz::a, iy.d);
/* 15 */   public static final a c = a(iz::j, iy.e);
/* 16 */   public static final a d = a(iz::j, iy.f);
/* 17 */   public static final a e = a(iz::m, iy.h);
/* 18 */   public static final a f = a(iz::k, iy.g);
/*    */   
/* 20 */   public static final a g = a(iz::x, iy.i);
/* 21 */   public static final a h = a(iz::w, iy.j);
/*    */   
/* 23 */   public static final a i = a(iz::f, iy.aa);
/* 24 */   public static final a j = a(iz::h, iy.ad);
/* 25 */   public static final a k = a(iz::i, iy.ab);
/* 26 */   public static final a l = a(iz::q, iy.F);
/* 27 */   public static final a m = a(iz::z, iy.am);
/* 28 */   public static final a n = a(iz::a, iy.I);
/* 29 */   public static final a o = a(iz::t, iy.ax);
/* 30 */   public static final a p = a(iz::t, iy.ay);
/* 31 */   public static final a q = a(iz::b, iy.aE);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public static final a r = a(iz::l, iy.e);
/* 37 */   public static final a s = a(iz::l, iy.f);
/*    */ 
/*    */   
/* 40 */   public static final a t = a(iz::n, iy.h);
/*    */ 
/*    */   
/* 43 */   public static final a u = a(iz::o, iy.e);
/*    */   
/*    */   private final iz v;
/*    */   private final ix w;
/*    */   
/*    */   private jb(iz var0, ix var1) {
/* 49 */     this.v = var0;
/* 50 */     this.w = var1;
/*    */   }
/*    */   
/*    */   public ix a() {
/* 54 */     return this.w;
/*    */   }
/*    */   
/*    */   public iz b() {
/* 58 */     return this.v;
/*    */   }
/*    */   
/*    */   public jb a(Consumer<iz> var0) {
/* 62 */     var0.accept(this.v);
/* 63 */     return this;
/*    */   }
/*    */   
/*    */   public MinecraftKey a(Block var0, BiConsumer<MinecraftKey, Supplier<JsonElement>> var1) {
/* 67 */     return this.w.a(var0, this.v, var1);
/*    */   }
/*    */   
/*    */   public MinecraftKey a(Block var0, String var1, BiConsumer<MinecraftKey, Supplier<JsonElement>> var2) {
/* 71 */     return this.w.a(var0, var1, this.v, var2);
/*    */   }
/*    */   
/*    */   private static a a(Function<Block, iz> var0, ix var1) {
/* 75 */     return var2 -> new jb(var0.apply(var2), var1);
/*    */   }
/*    */   
/*    */   public static jb a(MinecraftKey var0) {
/* 79 */     return new jb(iz.b(var0), iy.c);
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface a {
/*    */     jb get(Block param1Block);
/*    */     
/*    */     default MinecraftKey a(Block var0, BiConsumer<MinecraftKey, Supplier<JsonElement>> var1) {
/* 87 */       return get(var0).a(var0, var1);
/*    */     }
/*    */     
/*    */     default MinecraftKey a(Block var0, String var1, BiConsumer<MinecraftKey, Supplier<JsonElement>> var2) {
/* 91 */       return get(var0).a(var0, var1, var2);
/*    */     }
/*    */     
/*    */     default a a(Consumer<iz> var0) {
/* 95 */       return var1 -> get(var1).a(var0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */