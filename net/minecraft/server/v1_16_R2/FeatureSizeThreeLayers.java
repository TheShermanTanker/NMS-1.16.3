/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.util.Function6;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.OptionalInt;
/*    */ 
/*    */ public class FeatureSizeThreeLayers extends FeatureSize {
/*    */   static {
/* 10 */     c = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.intRange(0, 80).fieldOf("limit").orElse(Integer.valueOf(1)).forGetter(()), (App)Codec.intRange(0, 80).fieldOf("upper_limit").orElse(Integer.valueOf(1)).forGetter(()), (App)Codec.intRange(0, 16).fieldOf("lower_size").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.intRange(0, 16).fieldOf("middle_size").orElse(Integer.valueOf(1)).forGetter(()), (App)Codec.intRange(0, 16).fieldOf("upper_size").orElse(Integer.valueOf(1)).forGetter(()), (App)a()).apply((Applicative)var0, FeatureSizeThreeLayers::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<FeatureSizeThreeLayers> c;
/*    */   
/*    */   private final int d;
/*    */   
/*    */   private final int e;
/*    */   
/*    */   private final int f;
/*    */   
/*    */   private final int g;
/*    */   private final int h;
/*    */   
/*    */   public FeatureSizeThreeLayers(int var0, int var1, int var2, int var3, int var4, OptionalInt var5) {
/* 26 */     super(var5);
/* 27 */     this.d = var0;
/* 28 */     this.e = var1;
/* 29 */     this.f = var2;
/* 30 */     this.g = var3;
/* 31 */     this.h = var4;
/*    */   }
/*    */ 
/*    */   
/*    */   protected FeatureSizeType<?> b() {
/* 36 */     return FeatureSizeType.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0, int var1) {
/* 41 */     if (var1 < this.d) {
/* 42 */       return this.f;
/*    */     }
/* 44 */     if (var1 >= var0 - this.e) {
/* 45 */       return this.h;
/*    */     }
/* 47 */     return this.g;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FeatureSizeThreeLayers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */