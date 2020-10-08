/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockBambooSapling extends Block implements IBlockFragilePlantElement {
/*  7 */   protected static final VoxelShape a = Block.a(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);
/*    */   
/*    */   public BlockBambooSapling(BlockBase.Info blockbase_info) {
/* 10 */     super(blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockBase.EnumRandomOffset ah_() {
/* 15 */     return BlockBase.EnumRandomOffset.XZ;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 20 */     Vec3D vec3d = iblockdata.n(iblockaccess, blockposition);
/*    */     
/* 22 */     return a.a(vec3d.x, vec3d.y, vec3d.z);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 27 */     if (random.nextInt(3) == 0 && worldserver.isEmpty(blockposition.up()) && worldserver.getLightLevel(blockposition.up(), 0) >= 9) {
/* 28 */       a(worldserver, blockposition);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 35 */     return iworldreader.getType(blockposition.down()).a(TagsBlock.BAMBOO_PLANTABLE_ON);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 40 */     if (!iblockdata.canPlace(generatoraccess, blockposition)) {
/* 41 */       return Blocks.AIR.getBlockData();
/*    */     }
/* 43 */     if (enumdirection == EnumDirection.UP && iblockdata1.a(Blocks.BAMBOO)) {
/* 44 */       generatoraccess.setTypeAndData(blockposition, Blocks.BAMBOO.getBlockData(), 2);
/*    */     }
/*    */     
/* 47 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 53 */     return iblockaccess.getType(blockposition.up()).isAir();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 63 */     a(worldserver, blockposition);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDamage(IBlockData iblockdata, EntityHuman entityhuman, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 68 */     return (entityhuman.getItemInMainHand().getItem() instanceof ItemSword) ? 1.0F : super.getDamage(iblockdata, entityhuman, iblockaccess, blockposition);
/*    */   }
/*    */   
/*    */   protected void a(World world, BlockPosition blockposition) {
/* 72 */     CraftEventFactory.handleBlockSpreadEvent(world, blockposition, blockposition.up(), Blocks.BAMBOO.getBlockData().set(BlockBamboo.e, BlockPropertyBambooSize.SMALL), 3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBambooSapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */