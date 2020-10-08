/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class DefinedStructureProcessorNop
/*    */   extends DefinedStructureProcessor
/*    */ {
/* 10 */   public static final Codec<DefinedStructureProcessorNop> a = Codec.unit(() -> b);
/*    */   
/* 12 */   public static final DefinedStructureProcessorNop b = new DefinedStructureProcessorNop();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DefinedStructure.BlockInfo a(IWorldReader var0, BlockPosition var1, BlockPosition var2, DefinedStructure.BlockInfo var3, DefinedStructure.BlockInfo var4, DefinedStructureInfo var5) {
/* 20 */     return var4;
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureStructureProcessorType<?> a() {
/* 25 */     return DefinedStructureStructureProcessorType.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessorNop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */