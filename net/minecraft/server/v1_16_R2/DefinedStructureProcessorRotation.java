/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class DefinedStructureProcessorRotation extends DefinedStructureProcessor {
/*    */   public static final Codec<DefinedStructureProcessorRotation> a;
/*    */   
/*    */   static {
/* 11 */     a = Codec.FLOAT.fieldOf("integrity").orElse(Float.valueOf(1.0F)).xmap(DefinedStructureProcessorRotation::new, var0 -> Float.valueOf(var0.b)).codec();
/*    */   }
/*    */   private final float b;
/*    */   
/*    */   public DefinedStructureProcessorRotation(float var0) {
/* 16 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DefinedStructure.BlockInfo a(IWorldReader var0, BlockPosition var1, BlockPosition var2, DefinedStructure.BlockInfo var3, DefinedStructure.BlockInfo var4, DefinedStructureInfo var5) {
/* 22 */     Random var6 = var5.b(var4.a);
/*    */     
/* 24 */     if (this.b >= 1.0F || var6.nextFloat() <= this.b) {
/* 25 */       return var4;
/*    */     }
/* 27 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureStructureProcessorType<?> a() {
/* 32 */     return DefinedStructureStructureProcessorType.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessorRotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */