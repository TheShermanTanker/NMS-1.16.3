/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockTallPlant extends BlockPlant {
/*  7 */   public static final BlockStateEnum<BlockPropertyDoubleBlockHalf> HALF = BlockProperties.aa;
/*    */   
/*    */   public BlockTallPlant(BlockBase.Info blockbase_info) {
/* 10 */     super(blockbase_info);
/* 11 */     j(((IBlockData)this.blockStateList.getBlockData()).set(HALF, BlockPropertyDoubleBlockHalf.LOWER));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 16 */     BlockPropertyDoubleBlockHalf blockpropertydoubleblockhalf = (BlockPropertyDoubleBlockHalf)iblockdata.get(HALF);
/*    */     
/* 18 */     if (enumdirection.n() == EnumDirection.EnumAxis.Y) if (((blockpropertydoubleblockhalf == BlockPropertyDoubleBlockHalf.LOWER) ? true : false) == ((enumdirection == EnumDirection.UP) ? true : false) && (!iblockdata1.a(this) || iblockdata1.get(HALF) == blockpropertydoubleblockhalf));  return (blockpropertydoubleblockhalf == BlockPropertyDoubleBlockHalf.LOWER && enumdirection == EnumDirection.DOWN && !iblockdata.canPlace(generatoraccess, blockposition)) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 24 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/*    */     
/* 26 */     return (blockposition.getY() < 255 && blockactioncontext.getWorld().getType(blockposition.up()).a(blockactioncontext)) ? super.getPlacedState(blockactioncontext) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/* 31 */     world.setTypeAndData(blockposition.up(), getBlockData().set(HALF, BlockPropertyDoubleBlockHalf.UPPER), 3);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 36 */     if (iblockdata.get(HALF) != BlockPropertyDoubleBlockHalf.UPPER) {
/* 37 */       return super.canPlace(iblockdata, iworldreader, blockposition);
/*    */     }
/* 39 */     IBlockData iblockdata1 = iworldreader.getType(blockposition.down());
/*    */     
/* 41 */     return (iblockdata1.a(this) && iblockdata1.get(HALF) == BlockPropertyDoubleBlockHalf.LOWER);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(GeneratorAccess generatoraccess, BlockPosition blockposition, int i) {
/* 46 */     generatoraccess.setTypeAndData(blockposition, getBlockData().set(HALF, BlockPropertyDoubleBlockHalf.LOWER), i);
/* 47 */     generatoraccess.setTypeAndData(blockposition.up(), getBlockData().set(HALF, BlockPropertyDoubleBlockHalf.UPPER), i);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/* 52 */     if (!world.isClientSide) {
/* 53 */       if (entityhuman.isCreative()) {
/* 54 */         b(world, blockposition, iblockdata, entityhuman);
/*    */       } else {
/* 56 */         dropItems(iblockdata, world, blockposition, (TileEntity)null, entityhuman, entityhuman.getItemInMainHand());
/*    */       } 
/*    */     }
/*    */     
/* 60 */     super.a(world, blockposition, iblockdata, entityhuman);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/* 65 */     super.a(world, entityhuman, blockposition, Blocks.AIR.getBlockData(), tileentity, itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   protected static void b(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/* 70 */     if (((WorldServer)world).hasPhysicsEvent && CraftEventFactory.callBlockPhysicsEvent(world, blockposition).isCancelled()) {
/*    */       return;
/*    */     }
/*    */     
/* 74 */     BlockPropertyDoubleBlockHalf blockpropertydoubleblockhalf = (BlockPropertyDoubleBlockHalf)iblockdata.get(HALF);
/*    */     
/* 76 */     if (blockpropertydoubleblockhalf == BlockPropertyDoubleBlockHalf.UPPER) {
/* 77 */       BlockPosition blockposition1 = blockposition.down();
/* 78 */       IBlockData iblockdata1 = world.getType(blockposition1);
/*    */       
/* 80 */       if (iblockdata1.getBlock() == iblockdata.getBlock() && iblockdata1.get(HALF) == BlockPropertyDoubleBlockHalf.LOWER) {
/* 81 */         world.setTypeAndData(blockposition1, Blocks.AIR.getBlockData(), 35);
/* 82 */         world.a(entityhuman, 2001, blockposition1, Block.getCombinedId(iblockdata1));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 90 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { HALF });
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockBase.EnumRandomOffset ah_() {
/* 95 */     return BlockBase.EnumRandomOffset.XZ;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTallPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */