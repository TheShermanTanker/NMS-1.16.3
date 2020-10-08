/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefinedStructureProcessorLavaSubmergedBlock
/*    */   extends DefinedStructureProcessor
/*    */ {
/* 16 */   public static final Codec<DefinedStructureProcessorLavaSubmergedBlock> a = Codec.unit(() -> b);
/* 17 */   public static final DefinedStructureProcessorLavaSubmergedBlock b = new DefinedStructureProcessorLavaSubmergedBlock();
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DefinedStructure.BlockInfo a(IWorldReader var0, BlockPosition var1, BlockPosition var2, DefinedStructure.BlockInfo var3, DefinedStructure.BlockInfo var4, DefinedStructureInfo var5) {
/* 22 */     BlockPosition var6 = var4.a;
/* 23 */     boolean var7 = var0.getType(var6).a(Blocks.LAVA);
/* 24 */     if (var7 && !Block.a(var4.b.getShape(var0, var6))) {
/* 25 */       return new DefinedStructure.BlockInfo(var6, Blocks.LAVA.getBlockData(), var4.c);
/*    */     }
/* 27 */     return var4;
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureStructureProcessorType<?> a() {
/* 32 */     return DefinedStructureStructureProcessorType.i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessorLavaSubmergedBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */