/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockCampfire extends BlockTileEntity implements IBlockWaterlogged {
/*   9 */   protected static final VoxelShape a = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
/*  10 */   public static final BlockStateBoolean b = BlockProperties.r;
/*  11 */   public static final BlockStateBoolean c = BlockProperties.y;
/*  12 */   public static final BlockStateBoolean d = BlockProperties.C;
/*  13 */   public static final BlockStateDirection e = BlockProperties.O;
/*  14 */   private static final VoxelShape f = Block.a(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
/*     */   private final boolean g;
/*     */   private final int h;
/*     */   
/*     */   public BlockCampfire(boolean flag, int i, BlockBase.Info blockbase_info) {
/*  19 */     super(blockbase_info);
/*  20 */     this.g = flag;
/*  21 */     this.h = i;
/*  22 */     j(((IBlockData)this.blockStateList.getBlockData()).set(b, Boolean.valueOf(true)).set(c, Boolean.valueOf(false)).set(d, Boolean.valueOf(false)).set(e, EnumDirection.NORTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  27 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/*  29 */     if (tileentity instanceof TileEntityCampfire) {
/*  30 */       TileEntityCampfire tileentitycampfire = (TileEntityCampfire)tileentity;
/*  31 */       ItemStack itemstack = entityhuman.b(enumhand);
/*  32 */       Optional<RecipeCampfire> optional = tileentitycampfire.a(itemstack);
/*     */       
/*  34 */       if (optional.isPresent()) {
/*  35 */         if (!world.isClientSide && tileentitycampfire.a(entityhuman.abilities.canInstantlyBuild ? itemstack.cloneItemStack() : itemstack, ((RecipeCampfire)optional.get()).getCookingTime())) {
/*  36 */           entityhuman.a(StatisticList.INTERACT_WITH_CAMPFIRE);
/*  37 */           return EnumInteractionResult.SUCCESS;
/*     */         } 
/*     */         
/*  40 */         return EnumInteractionResult.CONSUME;
/*     */       } 
/*     */     } 
/*     */     
/*  44 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/*  49 */     if (!entity.isFireProof() && ((Boolean)iblockdata.get(b)).booleanValue() && entity instanceof EntityLiving && !EnchantmentManager.i((EntityLiving)entity)) {
/*  50 */       entity.damageEntity(DamageSource.FIRE, this.h);
/*     */     }
/*     */     
/*  53 */     super.a(iblockdata, world, blockposition, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  58 */     if (!iblockdata.a(iblockdata1.getBlock())) {
/*  59 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/*  61 */       if (tileentity instanceof TileEntityCampfire) {
/*  62 */         InventoryUtils.a(world, blockposition, ((TileEntityCampfire)tileentity).getItems());
/*     */       }
/*     */       
/*  65 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  72 */     World world = blockactioncontext.getWorld();
/*  73 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/*  74 */     boolean flag = (world.getFluid(blockposition).getType() == FluidTypes.WATER);
/*     */     
/*  76 */     return getBlockData().set(d, Boolean.valueOf(flag)).set(c, Boolean.valueOf(l(world.getType(blockposition.down())))).set(b, Boolean.valueOf(!flag)).set(e, blockactioncontext.f());
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  81 */     if (((Boolean)iblockdata.get(d)).booleanValue()) {
/*  82 */       generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a(generatoraccess));
/*     */     }
/*     */     
/*  85 */     return (enumdirection == EnumDirection.DOWN) ? iblockdata.set(c, Boolean.valueOf(l(iblockdata1))) : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */   
/*     */   private boolean l(IBlockData iblockdata) {
/*  89 */     return iblockdata.a(Blocks.HAY_BLOCK);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  94 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/*  99 */     return EnumRenderType.MODEL;
/*     */   }
/*     */   
/*     */   public static void c(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata) {
/* 103 */     if (generatoraccess.s_()) {
/* 104 */       for (int i = 0; i < 20; i++) {
/* 105 */         a((World)generatoraccess, blockposition, ((Boolean)iblockdata.get(c)).booleanValue(), true);
/*     */       }
/*     */     }
/*     */     
/* 109 */     TileEntity tileentity = generatoraccess.getTileEntity(blockposition);
/*     */     
/* 111 */     if (tileentity instanceof TileEntityCampfire) {
/* 112 */       ((TileEntityCampfire)tileentity).f();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean place(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata, Fluid fluid) {
/* 119 */     if (!((Boolean)iblockdata.get(BlockProperties.C)).booleanValue() && fluid.getType() == FluidTypes.WATER) {
/* 120 */       boolean flag = ((Boolean)iblockdata.get(b)).booleanValue();
/*     */       
/* 122 */       if (flag) {
/* 123 */         if (!generatoraccess.s_()) {
/* 124 */           generatoraccess.playSound((EntityHuman)null, blockposition, SoundEffects.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */         }
/*     */         
/* 127 */         c(generatoraccess, blockposition, iblockdata);
/*     */       } 
/*     */       
/* 130 */       generatoraccess.setTypeAndData(blockposition, iblockdata.set(d, Boolean.valueOf(true)).set(b, Boolean.valueOf(false)), 3);
/* 131 */       generatoraccess.getFluidTickList().a(blockposition, fluid.getType(), fluid.getType().a(generatoraccess));
/* 132 */       return true;
/*     */     } 
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World world, IBlockData iblockdata, MovingObjectPositionBlock movingobjectpositionblock, IProjectile iprojectile) {
/* 140 */     if (!world.isClientSide && iprojectile.isBurning()) {
/* 141 */       Entity entity = iprojectile.getShooter();
/* 142 */       boolean flag = (entity == null || entity instanceof EntityHuman || world.getGameRules().getBoolean(GameRules.MOB_GRIEFING));
/*     */       
/* 144 */       if (flag && !((Boolean)iblockdata.get(b)).booleanValue() && !((Boolean)iblockdata.get(d)).booleanValue()) {
/* 145 */         BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();
/*     */ 
/*     */         
/* 148 */         if (CraftEventFactory.callBlockIgniteEvent(world, blockposition, iprojectile).isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/* 152 */         world.setTypeAndData(blockposition, iblockdata.set(BlockProperties.r, Boolean.valueOf(true)), 11);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(World world, BlockPosition blockposition, boolean flag, boolean flag1) {
/* 159 */     Random random = world.getRandom();
/* 160 */     ParticleType particletype = flag ? Particles.CAMPFIRE_SIGNAL_SMOKE : Particles.CAMPFIRE_COSY_SMOKE;
/*     */     
/* 162 */     world.b(particletype, true, blockposition.getX() + 0.5D + random.nextDouble() / 3.0D * (random.nextBoolean() ? true : -1), blockposition.getY() + random.nextDouble() + random.nextDouble(), blockposition.getZ() + 0.5D + random.nextDouble() / 3.0D * (random.nextBoolean() ? true : -1), 0.0D, 0.07D, 0.0D);
/* 163 */     if (flag1) {
/* 164 */       world.addParticle(Particles.SMOKE, blockposition.getX() + 0.25D + random.nextDouble() / 2.0D * (random.nextBoolean() ? true : -1), blockposition.getY() + 0.4D, blockposition.getZ() + 0.25D + random.nextDouble() / 2.0D * (random.nextBoolean() ? true : -1), 0.0D, 0.005D, 0.0D);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(World world, BlockPosition blockposition) {
/* 170 */     for (int i = 1; i <= 5; i++) {
/* 171 */       BlockPosition blockposition1 = blockposition.down(i);
/* 172 */       IBlockData iblockdata = world.getType(blockposition1);
/*     */       
/* 174 */       if (g(iblockdata)) {
/* 175 */         return true;
/*     */       }
/*     */       
/* 178 */       boolean flag = VoxelShapes.c(f, iblockdata.b(world, blockposition, VoxelShapeCollision.a()), OperatorBoolean.AND);
/*     */       
/* 180 */       if (flag) {
/* 181 */         IBlockData iblockdata1 = world.getType(blockposition1.down());
/*     */         
/* 183 */         return g(iblockdata1);
/*     */       } 
/*     */     } 
/*     */     
/* 187 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean g(IBlockData iblockdata) {
/* 191 */     return (iblockdata.b(b) && iblockdata.a(TagsBlock.CAMPFIRES) && ((Boolean)iblockdata.get(b)).booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData iblockdata) {
/* 196 */     return ((Boolean)iblockdata.get(d)).booleanValue() ? FluidTypes.WATER.a(false) : super.d(iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 201 */     return iblockdata.set(e, enumblockrotation.a((EnumDirection)iblockdata.get(e)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 206 */     return iblockdata.a(enumblockmirror.a((EnumDirection)iblockdata.get(e)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 211 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { b, c, d, e });
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess iblockaccess) {
/* 216 */     return new TileEntityCampfire();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 221 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean h(IBlockData iblockdata) {
/* 225 */     return (iblockdata.a(TagsBlock.CAMPFIRES, blockbase_blockdata -> 
/* 226 */         (blockbase_blockdata.b(BlockProperties.C) && blockbase_blockdata.b(BlockProperties.r))) && 
/* 227 */       !((Boolean)iblockdata.get(BlockProperties.C)).booleanValue() && !((Boolean)iblockdata.get(BlockProperties.r)).booleanValue());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCampfire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */