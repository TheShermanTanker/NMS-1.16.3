/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import java.util.function.Supplier;
/*    */ import org.bukkit.TreeType;
/*    */ 
/*    */ public class BlockFungi extends BlockPlant implements IBlockFragilePlantElement {
/*  8 */   protected static final VoxelShape a = Block.a(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
/*    */   private final Supplier<WorldGenFeatureConfigured<WorldGenFeatureHugeFungiConfiguration, ?>> b;
/*    */   
/*    */   protected BlockFungi(BlockBase.Info blockbase_info, Supplier<WorldGenFeatureConfigured<WorldGenFeatureHugeFungiConfiguration, ?>> supplier) {
/* 12 */     super(blockbase_info);
/* 13 */     this.b = supplier;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 18 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 23 */     return (iblockdata.a(TagsBlock.NYLIUM) || iblockdata.a(Blocks.MYCELIUM) || iblockdata.a(Blocks.SOUL_SOIL) || super.c(iblockdata, iblockaccess, blockposition));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 28 */     Block block = ((WorldGenFeatureHugeFungiConfiguration)((WorldGenFeatureConfigured)this.b.get()).f).f.getBlock();
/* 29 */     Block block1 = iblockaccess.getType(blockposition.down()).getBlock();
/*    */     
/* 31 */     return (block1 == block);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 36 */     return (random.nextFloat() < 0.4D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 42 */     if (this == Blocks.WARPED_FUNGUS) {
/* 43 */       BlockSapling.treeType = TreeType.WARPED_FUNGUS;
/* 44 */     } else if (this == Blocks.CRIMSON_FUNGUS) {
/* 45 */       BlockSapling.treeType = TreeType.CRIMSON_FUNGUS;
/*    */     } 
/*    */     
/* 48 */     ((WorldGenFeatureConfigured)this.b.get()).a(worldserver, worldserver.getChunkProvider().getChunkGenerator(), random, blockposition);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFungi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */