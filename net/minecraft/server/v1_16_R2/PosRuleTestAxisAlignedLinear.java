/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function5;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class PosRuleTestAxisAlignedLinear extends PosRuleTest {
/*    */   static {
/* 12 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.FLOAT.fieldOf("min_chance").orElse(Float.valueOf(0.0F)).forGetter(()), (App)Codec.FLOAT.fieldOf("max_chance").orElse(Float.valueOf(0.0F)).forGetter(()), (App)Codec.INT.fieldOf("min_dist").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.INT.fieldOf("max_dist").orElse(Integer.valueOf(0)).forGetter(()), (App)EnumDirection.EnumAxis.d.fieldOf("axis").orElse(EnumDirection.EnumAxis.Y).forGetter(())).apply((Applicative)var0, PosRuleTestAxisAlignedLinear::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<PosRuleTestAxisAlignedLinear> a;
/*    */   
/*    */   private final float b;
/*    */   
/*    */   private final float d;
/*    */   
/*    */   private final int e;
/*    */   private final int f;
/*    */   private final EnumDirection.EnumAxis g;
/*    */   
/*    */   public PosRuleTestAxisAlignedLinear(float var0, float var1, int var2, int var3, EnumDirection.EnumAxis var4) {
/* 27 */     if (var2 >= var3) {
/* 28 */       throw new IllegalArgumentException("Invalid range: [" + var2 + "," + var3 + "]");
/*    */     }
/* 30 */     this.b = var0;
/* 31 */     this.d = var1;
/* 32 */     this.e = var2;
/* 33 */     this.f = var3;
/* 34 */     this.g = var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(BlockPosition var0, BlockPosition var1, BlockPosition var2, Random var3) {
/* 39 */     EnumDirection var4 = EnumDirection.a(EnumDirection.EnumAxisDirection.POSITIVE, this.g);
/* 40 */     float var5 = Math.abs((var1.getX() - var2.getX()) * var4.getAdjacentX());
/* 41 */     float var6 = Math.abs((var1.getY() - var2.getY()) * var4.getAdjacentY());
/* 42 */     float var7 = Math.abs((var1.getZ() - var2.getZ()) * var4.getAdjacentZ());
/* 43 */     int var8 = (int)(var5 + var6 + var7);
/*    */     
/* 45 */     float var9 = var3.nextFloat();
/* 46 */     return (var9 <= MathHelper.b(this.b, this.d, MathHelper.c(var8, this.e, this.f)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected PosRuleTestType<?> a() {
/* 51 */     return PosRuleTestType.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PosRuleTestAxisAlignedLinear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */