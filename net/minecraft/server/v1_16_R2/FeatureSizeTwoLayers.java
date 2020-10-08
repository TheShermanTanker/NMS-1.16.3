/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function4;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.OptionalInt;
/*    */ 
/*    */ public class FeatureSizeTwoLayers extends FeatureSize {
/*    */   static {
/* 11 */     c = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.intRange(0, 81).fieldOf("limit").orElse(Integer.valueOf(1)).forGetter(()), (App)Codec.intRange(0, 16).fieldOf("lower_size").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.intRange(0, 16).fieldOf("upper_size").orElse(Integer.valueOf(1)).forGetter(()), (App)a()).apply((Applicative)var0, FeatureSizeTwoLayers::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<FeatureSizeTwoLayers> c;
/*    */   
/*    */   private final int d;
/*    */   
/*    */   private final int e;
/*    */   
/*    */   private final int f;
/*    */   
/*    */   public FeatureSizeTwoLayers(int var0, int var1, int var2) {
/* 24 */     this(var0, var1, var2, OptionalInt.empty());
/*    */   }
/*    */   
/*    */   public FeatureSizeTwoLayers(int var0, int var1, int var2, OptionalInt var3) {
/* 28 */     super(var3);
/* 29 */     this.d = var0;
/* 30 */     this.e = var1;
/* 31 */     this.f = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected FeatureSizeType<?> b() {
/* 36 */     return FeatureSizeType.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int var0, int var1) {
/* 41 */     return (var1 < this.d) ? this.e : this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FeatureSizeTwoLayers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */