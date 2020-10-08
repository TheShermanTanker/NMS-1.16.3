/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FoodInfo
/*    */ {
/*    */   private final int a;
/*    */   private final float b;
/*    */   private final boolean c;
/*    */   private final boolean d;
/*    */   private final boolean e;
/*    */   private final List<Pair<MobEffect, Float>> f;
/*    */   
/*    */   private FoodInfo(int var0, float var1, boolean var2, boolean var3, boolean var4, List<Pair<MobEffect, Float>> var5) {
/* 20 */     this.a = var0;
/* 21 */     this.b = var1;
/* 22 */     this.c = var2;
/* 23 */     this.d = var3;
/* 24 */     this.e = var4;
/* 25 */     this.f = var5;
/*    */   }
/*    */   
/*    */   public int getNutrition() {
/* 29 */     return this.a;
/*    */   }
/*    */   
/*    */   public float getSaturationModifier() {
/* 33 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 37 */     return this.c;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 41 */     return this.d;
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 45 */     return this.e;
/*    */   }
/*    */   
/*    */   public List<Pair<MobEffect, Float>> f() {
/* 49 */     return this.f;
/*    */   }
/*    */   
/*    */   public static class a
/*    */   {
/*    */     private int a;
/*    */     private float b;
/*    */     private boolean c;
/*    */     private boolean d;
/*    */     private boolean e;
/* 59 */     private final List<Pair<MobEffect, Float>> f = Lists.newArrayList();
/*    */     
/*    */     public a a(int var0) {
/* 62 */       this.a = var0;
/* 63 */       return this;
/*    */     }
/*    */     
/*    */     public a a(float var0) {
/* 67 */       this.b = var0;
/* 68 */       return this;
/*    */     }
/*    */     
/*    */     public a a() {
/* 72 */       this.c = true;
/* 73 */       return this;
/*    */     }
/*    */     
/*    */     public a b() {
/* 77 */       this.d = true;
/* 78 */       return this;
/*    */     }
/*    */     
/*    */     public a c() {
/* 82 */       this.e = true;
/* 83 */       return this;
/*    */     }
/*    */     
/*    */     public a a(MobEffect var0, float var1) {
/* 87 */       this.f.add(Pair.of(var0, Float.valueOf(var1)));
/* 88 */       return this;
/*    */     }
/*    */     
/*    */     public FoodInfo d() {
/* 92 */       return new FoodInfo(this.a, this.b, this.c, this.d, this.e, this.f);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FoodInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */