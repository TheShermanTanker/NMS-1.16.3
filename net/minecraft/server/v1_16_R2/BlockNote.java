/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.event.block.NotePlayEvent;
/*    */ 
/*    */ public class BlockNote extends Block {
/*  5 */   public static final BlockStateEnum<BlockPropertyInstrument> INSTRUMENT = BlockProperties.aI;
/*  6 */   public static final BlockStateBoolean POWERED = BlockProperties.w;
/*  7 */   public static final BlockStateInteger NOTE = BlockProperties.ax;
/*    */   
/*    */   public BlockNote(BlockBase.Info blockbase_info) {
/* 10 */     super(blockbase_info);
/* 11 */     j(((IBlockData)this.blockStateList.getBlockData()).set(INSTRUMENT, BlockPropertyInstrument.HARP).set(NOTE, Integer.valueOf(0)).set(POWERED, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 16 */     return getBlockData().set(INSTRUMENT, BlockPropertyInstrument.a(blockactioncontext.getWorld().getType(blockactioncontext.getClickPosition().down())));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 21 */     return (enumdirection == EnumDirection.DOWN) ? iblockdata.set(INSTRUMENT, BlockPropertyInstrument.a(iblockdata1)) : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/* 26 */     boolean flag1 = world.isBlockIndirectlyPowered(blockposition);
/*    */     
/* 28 */     if (flag1 != ((Boolean)iblockdata.get(POWERED)).booleanValue()) {
/* 29 */       if (flag1) {
/* 30 */         play(world, blockposition, iblockdata);
/* 31 */         iblockdata = world.getType(blockposition);
/*    */       } 
/*    */       
/* 34 */       world.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(flag1)), 3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void play(World world, BlockPosition blockposition, IBlockData data) {
/* 40 */     if (world.getType(blockposition.up()).isAir()) {
/*    */       
/* 42 */       NotePlayEvent event = CraftEventFactory.callNotePlayEvent(world, blockposition, (BlockPropertyInstrument)data.get(INSTRUMENT), ((Integer)data.get(NOTE)).intValue());
/* 43 */       if (!event.isCancelled()) {
/* 44 */         world.playBlockAction(blockposition, this, 0, 0);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 53 */     if (world.isClientSide) {
/* 54 */       return EnumInteractionResult.SUCCESS;
/*    */     }
/* 56 */     iblockdata = iblockdata.a(NOTE);
/* 57 */     world.setTypeAndData(blockposition, iblockdata, 3);
/* 58 */     play(world, blockposition, iblockdata);
/* 59 */     entityhuman.a(StatisticList.TUNE_NOTEBLOCK);
/* 60 */     return EnumInteractionResult.CONSUME;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void attack(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman) {
/* 66 */     if (!world.isClientSide) {
/* 67 */       play(world, blockposition, iblockdata);
/* 68 */       entityhuman.a(StatisticList.PLAY_NOTEBLOCK);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, int i, int j) {
/* 74 */     int k = ((Integer)iblockdata.get(NOTE)).intValue();
/* 75 */     float f = (float)Math.pow(2.0D, (k - 12) / 12.0D);
/*    */     
/* 77 */     world.playSound((EntityHuman)null, blockposition, ((BlockPropertyInstrument)iblockdata.get(INSTRUMENT)).b(), SoundCategory.RECORDS, 3.0F, f);
/* 78 */     world.addParticle(Particles.NOTE, blockposition.getX() + 0.5D, blockposition.getY() + 1.2D, blockposition.getZ() + 0.5D, k / 24.0D, 0.0D, 0.0D);
/* 79 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 84 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { INSTRUMENT, POWERED, NOTE });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockNote.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */