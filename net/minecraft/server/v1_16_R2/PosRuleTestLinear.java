/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function4;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class PosRuleTestLinear extends PosRuleTest {
/*    */   static {
/* 11 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.FLOAT.fieldOf("min_chance").orElse(Float.valueOf(0.0F)).forGetter(()), (App)Codec.FLOAT.fieldOf("max_chance").orElse(Float.valueOf(0.0F)).forGetter(()), (App)Codec.INT.fieldOf("min_dist").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.INT.fieldOf("max_dist").orElse(Integer.valueOf(0)).forGetter(())).apply((Applicative)var0, PosRuleTestLinear::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<PosRuleTestLinear> a;
/*    */   
/*    */   private final float b;
/*    */   
/*    */   private final float d;
/*    */   private final int e;
/*    */   private final int f;
/*    */   
/*    */   public PosRuleTestLinear(float var0, float var1, int var2, int var3) {
/* 24 */     if (var2 >= var3) {
/* 25 */       throw new IllegalArgumentException("Invalid range: [" + var2 + "," + var3 + "]");
/*    */     }
/*    */     
/* 28 */     this.b = var0;
/* 29 */     this.d = var1;
/* 30 */     this.e = var2;
/* 31 */     this.f = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(BlockPosition var0, BlockPosition var1, BlockPosition var2, Random var3) {
/* 36 */     int var4 = var1.k(var2);
/*    */     
/* 38 */     float var5 = var3.nextFloat();
/* 39 */     return (var5 <= MathHelper.b(this.b, this.d, MathHelper.c(var4, this.e, this.f)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected PosRuleTestType<?> a() {
/* 44 */     return PosRuleTestType.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PosRuleTestLinear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */