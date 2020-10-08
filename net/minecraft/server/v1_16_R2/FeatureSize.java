/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Optional;
/*    */ import java.util.OptionalInt;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FeatureSize
/*    */ {
/* 12 */   public static final Codec<FeatureSize> a = IRegistry.FEATURE_SIZE_TYPE.dispatch(FeatureSize::b, FeatureSizeType::a);
/*    */   protected final OptionalInt b;
/*    */   
/*    */   protected static <S extends FeatureSize> RecordCodecBuilder<S, OptionalInt> a() {
/* 16 */     return Codec.intRange(0, 80).optionalFieldOf("min_clipped_height")
/* 17 */       .xmap(var0 -> (OptionalInt)var0.map(OptionalInt::of).orElse(OptionalInt.empty()), var0 -> var0.isPresent() ? Optional.<Integer>of(Integer.valueOf(var0.getAsInt())) : Optional.empty()).forGetter(var0 -> var0.b);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public FeatureSize(OptionalInt var0) {
/* 23 */     this.b = var0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public OptionalInt c() {
/* 31 */     return this.b;
/*    */   }
/*    */   
/*    */   protected abstract FeatureSizeType<?> b();
/*    */   
/*    */   public abstract int a(int paramInt1, int paramInt2);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FeatureSize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */