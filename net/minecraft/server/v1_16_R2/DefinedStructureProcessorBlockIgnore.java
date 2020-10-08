/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefinedStructureProcessorBlockIgnore
/*    */   extends DefinedStructureProcessor
/*    */ {
/*    */   public static final Codec<DefinedStructureProcessorBlockIgnore> a;
/*    */   
/*    */   static {
/* 21 */     a = IBlockData.b.xmap(BlockBase.BlockData::getBlock, Block::getBlockData).listOf().fieldOf("blocks").xmap(DefinedStructureProcessorBlockIgnore::new, var0 -> var0.e).codec();
/*    */   }
/* 23 */   public static final DefinedStructureProcessorBlockIgnore b = new DefinedStructureProcessorBlockIgnore((List<Block>)ImmutableList.of(Blocks.STRUCTURE_BLOCK));
/* 24 */   public static final DefinedStructureProcessorBlockIgnore c = new DefinedStructureProcessorBlockIgnore((List<Block>)ImmutableList.of(Blocks.AIR));
/* 25 */   public static final DefinedStructureProcessorBlockIgnore d = new DefinedStructureProcessorBlockIgnore((List<Block>)ImmutableList.of(Blocks.AIR, Blocks.STRUCTURE_BLOCK));
/*    */   
/*    */   private final ImmutableList<Block> e;
/*    */   
/*    */   public DefinedStructureProcessorBlockIgnore(List<Block> var0) {
/* 30 */     this.e = ImmutableList.copyOf(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DefinedStructure.BlockInfo a(IWorldReader var0, BlockPosition var1, BlockPosition var2, DefinedStructure.BlockInfo var3, DefinedStructure.BlockInfo var4, DefinedStructureInfo var5) {
/* 36 */     if (this.e.contains(var4.b.getBlock())) {
/* 37 */       return null;
/*    */     }
/* 39 */     return var4;
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureStructureProcessorType<?> a() {
/* 44 */     return DefinedStructureStructureProcessorType.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessorBlockIgnore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */