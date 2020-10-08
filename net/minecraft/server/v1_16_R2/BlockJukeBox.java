/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class BlockJukeBox
/*     */   extends BlockTileEntity {
/*   7 */   public static final BlockStateBoolean HAS_RECORD = BlockProperties.n;
/*     */   
/*     */   protected BlockJukeBox(BlockBase.Info blockbase_info) {
/*  10 */     super(blockbase_info);
/*  11 */     j(((IBlockData)this.blockStateList.getBlockData()).set(HAS_RECORD, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, @Nullable EntityLiving entityliving, ItemStack itemstack) {
/*  16 */     super.postPlace(world, blockposition, iblockdata, entityliving, itemstack);
/*  17 */     NBTTagCompound nbttagcompound = itemstack.getOrCreateTag();
/*     */     
/*  19 */     if (nbttagcompound.hasKey("BlockEntityTag")) {
/*  20 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("BlockEntityTag");
/*     */       
/*  22 */       if (nbttagcompound1.hasKey("RecordItem")) {
/*  23 */         world.setTypeAndData(blockposition, iblockdata.set(HAS_RECORD, Boolean.valueOf(true)), 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  31 */     if (((Boolean)iblockdata.get(HAS_RECORD)).booleanValue()) {
/*  32 */       dropRecord(world, blockposition);
/*  33 */       iblockdata = iblockdata.set(HAS_RECORD, Boolean.valueOf(false));
/*  34 */       world.setTypeAndData(blockposition, iblockdata, 2);
/*  35 */       return EnumInteractionResult.a(world.isClientSide);
/*     */     } 
/*  37 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata, ItemStack itemstack) {
/*  42 */     TileEntity tileentity = generatoraccess.getTileEntity(blockposition);
/*     */     
/*  44 */     if (tileentity instanceof TileEntityJukeBox) {
/*     */       
/*  46 */       itemstack = itemstack.cloneItemStack();
/*  47 */       if (!itemstack.isEmpty()) {
/*  48 */         itemstack.setCount(1);
/*     */       }
/*  50 */       ((TileEntityJukeBox)tileentity).setRecord(itemstack);
/*     */       
/*  52 */       generatoraccess.setTypeAndData(blockposition, iblockdata.set(HAS_RECORD, Boolean.valueOf(true)), 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dropRecord(World world, BlockPosition blockposition) {
/*  57 */     if (!world.isClientSide) {
/*  58 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/*  60 */       if (tileentity instanceof TileEntityJukeBox) {
/*  61 */         TileEntityJukeBox tileentityjukebox = (TileEntityJukeBox)tileentity;
/*  62 */         ItemStack itemstack = tileentityjukebox.getRecord();
/*     */         
/*  64 */         if (!itemstack.isEmpty()) {
/*  65 */           world.triggerEffect(1010, blockposition, 0);
/*  66 */           tileentityjukebox.clear();
/*  67 */           float f = 0.7F;
/*  68 */           double d0 = (world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
/*  69 */           double d1 = (world.random.nextFloat() * 0.7F) + 0.06000000238418579D + 0.6D;
/*  70 */           double d2 = (world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
/*  71 */           ItemStack itemstack1 = itemstack.cloneItemStack();
/*  72 */           EntityItem entityitem = new EntityItem(world, blockposition.getX() + d0, blockposition.getY() + d1, blockposition.getZ() + d2, itemstack1);
/*     */           
/*  74 */           entityitem.defaultPickupDelay();
/*  75 */           world.addEntity(entityitem);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  83 */     if (!iblockdata.a(iblockdata1.getBlock())) {
/*  84 */       dropRecord(world, blockposition);
/*  85 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess iblockaccess) {
/*  91 */     return new TileEntityJukeBox();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 101 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 103 */     if (tileentity instanceof TileEntityJukeBox) {
/* 104 */       Item item = ((TileEntityJukeBox)tileentity).getRecord().getItem();
/*     */       
/* 106 */       if (item instanceof ItemRecord) {
/* 107 */         return ((ItemRecord)item).f();
/*     */       }
/*     */     } 
/*     */     
/* 111 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/* 116 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 121 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { HAS_RECORD });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockJukeBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */