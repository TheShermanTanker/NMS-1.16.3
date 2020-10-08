/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.concurrent.Immutable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Immutable
/*    */ public class DifficultyDamageScaler
/*    */ {
/*    */   private final EnumDifficulty a;
/*    */   private final float b;
/*    */   
/*    */   public DifficultyDamageScaler(EnumDifficulty var0, long var1, long var3, float var5) {
/* 22 */     this.a = var0;
/* 23 */     this.b = a(var0, var1, var3, var5);
/*    */   }
/*    */   
/*    */   public EnumDifficulty a() {
/* 27 */     return this.a;
/*    */   }
/*    */   
/*    */   public float b() {
/* 31 */     return this.b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(float var0) {
/* 39 */     return (this.b > var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float d() {
/* 52 */     if (this.b < 2.0F) {
/* 53 */       return 0.0F;
/*    */     }
/* 55 */     if (this.b > 4.0F) {
/* 56 */       return 1.0F;
/*    */     }
/* 58 */     return (this.b - 2.0F) / 2.0F;
/*    */   }
/*    */   
/*    */   private float a(EnumDifficulty var0, long var1, long var3, float var5) {
/* 62 */     if (var0 == EnumDifficulty.PEACEFUL) {
/* 63 */       return 0.0F;
/*    */     }
/*    */     
/* 66 */     boolean var6 = (var0 == EnumDifficulty.HARD);
/* 67 */     float var7 = 0.75F;
/*    */ 
/*    */     
/* 70 */     float var8 = MathHelper.a(((float)var1 + -72000.0F) / 1440000.0F, 0.0F, 1.0F) * 0.25F;
/* 71 */     var7 += var8;
/*    */     
/* 73 */     float var9 = 0.0F;
/*    */ 
/*    */     
/* 76 */     var9 += MathHelper.a((float)var3 / 3600000.0F, 0.0F, 1.0F) * (var6 ? 1.0F : 0.75F);
/* 77 */     var9 += MathHelper.a(var5 * 0.25F, 0.0F, var8);
/*    */     
/* 79 */     if (var0 == EnumDifficulty.EASY) {
/* 80 */       var9 *= 0.5F;
/*    */     }
/* 82 */     var7 += var9;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 87 */     return var0.a() * var7;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DifficultyDamageScaler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */