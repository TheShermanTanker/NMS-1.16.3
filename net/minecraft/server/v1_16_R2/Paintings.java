/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class Paintings
/*    */ {
/*  6 */   public static final Paintings a = a("kebab", 16, 16);
/*  7 */   public static final Paintings b = a("aztec", 16, 16);
/*  8 */   public static final Paintings c = a("alban", 16, 16);
/*  9 */   public static final Paintings d = a("aztec2", 16, 16);
/* 10 */   public static final Paintings e = a("bomb", 16, 16);
/* 11 */   public static final Paintings f = a("plant", 16, 16);
/* 12 */   public static final Paintings g = a("wasteland", 16, 16);
/* 13 */   public static final Paintings h = a("pool", 32, 16);
/* 14 */   public static final Paintings i = a("courbet", 32, 16);
/* 15 */   public static final Paintings j = a("sea", 32, 16);
/* 16 */   public static final Paintings k = a("sunset", 32, 16);
/* 17 */   public static final Paintings l = a("creebet", 32, 16);
/* 18 */   public static final Paintings m = a("wanderer", 16, 32);
/* 19 */   public static final Paintings n = a("graham", 16, 32);
/* 20 */   public static final Paintings o = a("match", 32, 32);
/* 21 */   public static final Paintings p = a("bust", 32, 32);
/* 22 */   public static final Paintings q = a("stage", 32, 32);
/* 23 */   public static final Paintings r = a("void", 32, 32);
/* 24 */   public static final Paintings s = a("skull_and_roses", 32, 32);
/* 25 */   public static final Paintings t = a("wither", 32, 32);
/* 26 */   public static final Paintings u = a("fighters", 64, 32);
/* 27 */   public static final Paintings v = a("pointer", 64, 64);
/* 28 */   public static final Paintings w = a("pigscene", 64, 64);
/* 29 */   public static final Paintings x = a("burning_skull", 64, 64);
/* 30 */   public static final Paintings y = a("skeleton", 64, 48);
/* 31 */   public static final Paintings z = a("donkey_kong", 64, 48);
/*    */   
/*    */   private static Paintings a(String var0, int var1, int var2) {
/* 34 */     return IRegistry.<Paintings>a(IRegistry.MOTIVE, var0, new Paintings(var1, var2));
/*    */   }
/*    */   
/*    */   private final int A;
/*    */   private final int B;
/*    */   
/*    */   public Paintings(int var0, int var1) {
/* 41 */     this.A = var0;
/* 42 */     this.B = var1;
/*    */   }
/*    */   
/*    */   public int getWidth() {
/* 46 */     return this.A;
/*    */   }
/*    */   
/*    */   public int getHeight() {
/* 50 */     return this.B;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Paintings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */