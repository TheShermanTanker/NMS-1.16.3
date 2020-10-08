/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenContextArea
/*    */   implements AreaContextTransformed<AreaLazy>
/*    */ {
/*    */   private final Long2IntLinkedOpenHashMap a;
/*    */   private final int b;
/*    */   private final NoiseGeneratorPerlin c;
/*    */   private final long d;
/*    */   private long e;
/*    */   
/*    */   public WorldGenContextArea(int var0, long var1, long var3) {
/* 23 */     this.d = b(var1, var3);
/* 24 */     this.c = new NoiseGeneratorPerlin(new Random(var1));
/*    */     
/* 26 */     this.a = new Long2IntLinkedOpenHashMap(16, 0.25F);
/* 27 */     this.a.defaultReturnValue(-2147483648);
/* 28 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public AreaLazy a(AreaTransformer8 var0) {
/* 33 */     return new AreaLazy(this.a, this.b, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public AreaLazy a(AreaTransformer8 var0, AreaLazy var1) {
/* 38 */     return new AreaLazy(this.a, Math.min(1024, var1.a() * 4), var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public AreaLazy a(AreaTransformer8 var0, AreaLazy var1, AreaLazy var2) {
/* 43 */     return new AreaLazy(this.a, Math.min(1024, Math.max(var1.a(), var2.a()) * 4), var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(long var0, long var2) {
/* 48 */     long var4 = this.d;
/* 49 */     var4 = LinearCongruentialGenerator.a(var4, var0);
/* 50 */     var4 = LinearCongruentialGenerator.a(var4, var2);
/* 51 */     var4 = LinearCongruentialGenerator.a(var4, var0);
/* 52 */     var4 = LinearCongruentialGenerator.a(var4, var2);
/* 53 */     this.e = var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0) {
/* 58 */     int var1 = (int)Math.floorMod(this.e >> 24L, var0);
/* 59 */     this.e = LinearCongruentialGenerator.a(this.e, this.d);
/* 60 */     return var1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NoiseGeneratorPerlin b() {
/* 70 */     return this.c;
/*    */   }
/*    */   
/*    */   private static long b(long var0, long var2) {
/* 74 */     long var4 = var2;
/* 75 */     var4 = LinearCongruentialGenerator.a(var4, var2);
/* 76 */     var4 = LinearCongruentialGenerator.a(var4, var2);
/* 77 */     var4 = LinearCongruentialGenerator.a(var4, var2);
/*    */     
/* 79 */     long var6 = var0;
/* 80 */     var6 = LinearCongruentialGenerator.a(var6, var4);
/* 81 */     var6 = LinearCongruentialGenerator.a(var6, var4);
/* 82 */     var6 = LinearCongruentialGenerator.a(var6, var4);
/* 83 */     return var6;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenContextArea.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */