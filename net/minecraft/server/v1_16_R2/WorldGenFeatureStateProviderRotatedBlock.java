/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureStateProviderRotatedBlock
/*    */   extends WorldGenFeatureStateProvider
/*    */ {
/*    */   public static final Codec<WorldGenFeatureStateProviderRotatedBlock> b;
/*    */   private final Block c;
/*    */   
/*    */   static {
/* 16 */     b = IBlockData.b.fieldOf("state").xmap(BlockBase.BlockData::getBlock, Block::getBlockData).xmap(WorldGenFeatureStateProviderRotatedBlock::new, var0 -> var0.c).codec();
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenFeatureStateProviderRotatedBlock(Block var0) {
/* 21 */     this.c = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFeatureStateProviders<?> a() {
/* 26 */     return WorldGenFeatureStateProviders.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(Random var0, BlockPosition var1) {
/* 31 */     EnumDirection.EnumAxis var2 = EnumDirection.EnumAxis.a(var0);
/* 32 */     return this.c.getBlockData().set(BlockRotatable.AXIS, var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureStateProviderRotatedBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */