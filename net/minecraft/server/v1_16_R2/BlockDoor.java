/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockDoor extends Block {
/*   9 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  10 */   public static final BlockStateBoolean OPEN = BlockProperties.u;
/*  11 */   public static final BlockStateEnum<BlockPropertyDoorHinge> HINGE = BlockProperties.aH;
/*  12 */   public static final BlockStateBoolean POWERED = BlockProperties.w;
/*  13 */   public static final BlockStateEnum<BlockPropertyDoubleBlockHalf> HALF = BlockProperties.aa;
/*  14 */   protected static final VoxelShape f = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
/*  15 */   protected static final VoxelShape g = Block.a(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
/*  16 */   protected static final VoxelShape h = Block.a(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  17 */   protected static final VoxelShape i = Block.a(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
/*     */   
/*     */   protected BlockDoor(BlockBase.Info blockbase_info) {
/*  20 */     super(blockbase_info);
/*  21 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(OPEN, Boolean.valueOf(false)).set(HINGE, BlockPropertyDoorHinge.LEFT).set(POWERED, Boolean.valueOf(false)).set(HALF, BlockPropertyDoubleBlockHalf.LOWER));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  26 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/*  27 */     boolean flag = !((Boolean)iblockdata.get(OPEN)).booleanValue();
/*  28 */     boolean flag1 = (iblockdata.get(HINGE) == BlockPropertyDoorHinge.RIGHT);
/*     */     
/*  30 */     switch (enumdirection)
/*     */     
/*     */     { default:
/*  33 */         return flag ? i : (flag1 ? g : f);
/*     */       case WATER:
/*  35 */         return flag ? f : (flag1 ? i : h);
/*     */       case AIR:
/*  37 */         return flag ? h : (flag1 ? f : g);
/*     */       case null:
/*  39 */         break; }  return flag ? g : (flag1 ? h : i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  45 */     BlockPropertyDoubleBlockHalf blockpropertydoubleblockhalf = (BlockPropertyDoubleBlockHalf)iblockdata.get(HALF);
/*     */     
/*  47 */     if (enumdirection.n() == EnumDirection.EnumAxis.Y) if (((blockpropertydoubleblockhalf == BlockPropertyDoubleBlockHalf.LOWER) ? true : false) == ((enumdirection == EnumDirection.UP) ? true : false)) return (iblockdata1.a(this) && iblockdata1.get(HALF) != blockpropertydoubleblockhalf) ? iblockdata.set(FACING, iblockdata1.get(FACING)).set(OPEN, iblockdata1.get(OPEN)).set(HINGE, iblockdata1.get(HINGE)).set(POWERED, iblockdata1.get(POWERED)) : Blocks.AIR.getBlockData();   return (blockpropertydoubleblockhalf == BlockPropertyDoubleBlockHalf.LOWER && enumdirection == EnumDirection.DOWN && !iblockdata.canPlace(generatoraccess, blockposition)) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/*  52 */     if (!world.isClientSide && entityhuman.isCreative()) {
/*  53 */       BlockTallPlant.b(world, blockposition, iblockdata, entityhuman);
/*     */     }
/*     */     
/*  56 */     super.a(world, blockposition, iblockdata, entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/*  61 */     switch (pathmode) {
/*     */       case LAND:
/*  63 */         return ((Boolean)iblockdata.get(OPEN)).booleanValue();
/*     */       case WATER:
/*  65 */         return false;
/*     */       case AIR:
/*  67 */         return ((Boolean)iblockdata.get(OPEN)).booleanValue();
/*     */     } 
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private int c() {
/*  74 */     return (this.material == Material.ORE) ? 1011 : 1012;
/*     */   }
/*     */   
/*     */   private int d() {
/*  78 */     return (this.material == Material.ORE) ? 1005 : 1006;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  84 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/*     */     
/*  86 */     if (blockposition.getY() < 255 && blockactioncontext.getWorld().getType(blockposition.up()).a(blockactioncontext)) {
/*  87 */       World world = blockactioncontext.getWorld();
/*  88 */       boolean flag = (world.isBlockIndirectlyPowered(blockposition) || world.isBlockIndirectlyPowered(blockposition.up()));
/*     */       
/*  90 */       return getBlockData().set(FACING, blockactioncontext.f()).set(HINGE, b(blockactioncontext)).set(POWERED, Boolean.valueOf(flag)).set(OPEN, Boolean.valueOf(flag)).set(HALF, BlockPropertyDoubleBlockHalf.LOWER);
/*     */     } 
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/*  98 */     world.setTypeAndData(blockposition.up(), iblockdata.set(HALF, BlockPropertyDoubleBlockHalf.UPPER), 3);
/*     */   }
/*     */   
/*     */   private BlockPropertyDoorHinge b(BlockActionContext blockactioncontext) {
/* 102 */     World world = blockactioncontext.getWorld();
/* 103 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/* 104 */     EnumDirection enumdirection = blockactioncontext.f();
/* 105 */     BlockPosition blockposition1 = blockposition.up();
/* 106 */     EnumDirection enumdirection1 = enumdirection.h();
/* 107 */     BlockPosition blockposition2 = blockposition.shift(enumdirection1);
/* 108 */     IBlockData iblockdata = world.getType(blockposition2);
/* 109 */     BlockPosition blockposition3 = blockposition1.shift(enumdirection1);
/* 110 */     IBlockData iblockdata1 = world.getType(blockposition3);
/* 111 */     EnumDirection enumdirection2 = enumdirection.g();
/* 112 */     BlockPosition blockposition4 = blockposition.shift(enumdirection2);
/* 113 */     IBlockData iblockdata2 = world.getType(blockposition4);
/* 114 */     BlockPosition blockposition5 = blockposition1.shift(enumdirection2);
/* 115 */     IBlockData iblockdata3 = world.getType(blockposition5);
/* 116 */     int i = (iblockdata.r(world, blockposition2) ? -1 : 0) + (iblockdata1.r(world, blockposition3) ? -1 : 0) + (iblockdata2.r(world, blockposition4) ? 1 : 0) + (iblockdata3.r(world, blockposition5) ? 1 : 0);
/* 117 */     boolean flag = (iblockdata.a(this) && iblockdata.get(HALF) == BlockPropertyDoubleBlockHalf.LOWER);
/* 118 */     boolean flag1 = (iblockdata2.a(this) && iblockdata2.get(HALF) == BlockPropertyDoubleBlockHalf.LOWER);
/*     */     
/* 120 */     if ((!flag || flag1) && i <= 0) {
/* 121 */       if ((!flag1 || flag) && i >= 0) {
/* 122 */         int j = enumdirection.getAdjacentX();
/* 123 */         int k = enumdirection.getAdjacentZ();
/* 124 */         Vec3D vec3d = blockactioncontext.getPos();
/* 125 */         double d0 = vec3d.x - blockposition.getX();
/* 126 */         double d1 = vec3d.z - blockposition.getZ();
/*     */         
/* 128 */         return ((j >= 0 || d1 >= 0.5D) && (j <= 0 || d1 <= 0.5D) && (k >= 0 || d0 <= 0.5D) && (k <= 0 || d0 >= 0.5D)) ? BlockPropertyDoorHinge.LEFT : BlockPropertyDoorHinge.RIGHT;
/*     */       } 
/* 130 */       return BlockPropertyDoorHinge.LEFT;
/*     */     } 
/*     */     
/* 133 */     return BlockPropertyDoorHinge.RIGHT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 139 */     if (this.material == Material.ORE) {
/* 140 */       return EnumInteractionResult.PASS;
/*     */     }
/* 142 */     iblockdata = iblockdata.a(OPEN);
/* 143 */     world.setTypeAndData(blockposition, iblockdata, 10);
/* 144 */     world.a(entityhuman, ((Boolean)iblockdata.get(OPEN)).booleanValue() ? d() : c(), blockposition, 0);
/* 145 */     return EnumInteractionResult.a(world.isClientSide);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean h(IBlockData iblockdata) {
/* 150 */     return ((Boolean)iblockdata.get(OPEN)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setDoor(World world, IBlockData iblockdata, BlockPosition blockposition, boolean flag) {
/* 154 */     if (iblockdata.a(this) && ((Boolean)iblockdata.get(OPEN)).booleanValue() != flag) {
/* 155 */       world.setTypeAndData(blockposition, iblockdata.set(OPEN, Boolean.valueOf(flag)), 10);
/* 156 */       a(world, blockposition, flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/* 163 */     BlockPosition otherHalf = blockposition.shift((iblockdata.get(HALF) == BlockPropertyDoubleBlockHalf.LOWER) ? EnumDirection.UP : EnumDirection.DOWN);
/*     */     
/* 165 */     CraftWorld craftWorld = world.getWorld();
/* 166 */     Block bukkitBlock = craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 167 */     Block blockTop = craftWorld.getBlockAt(otherHalf.getX(), otherHalf.getY(), otherHalf.getZ());
/*     */     
/* 169 */     int power = bukkitBlock.getBlockPower();
/* 170 */     int powerTop = blockTop.getBlockPower();
/* 171 */     if (powerTop > power) power = powerTop; 
/* 172 */     int oldPower = ((Boolean)iblockdata.get(POWERED)).booleanValue() ? 15 : 0;
/*     */     
/* 174 */     if ((((oldPower == 0) ? 1 : 0) ^ ((power == 0) ? 1 : 0)) != 0) {
/* 175 */       BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(bukkitBlock, oldPower, power);
/* 176 */       world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */       
/* 178 */       boolean flag1 = (eventRedstone.getNewCurrent() > 0);
/*     */       
/* 180 */       if (flag1 != ((Boolean)iblockdata.get(OPEN)).booleanValue()) {
/* 181 */         a(world, blockposition, flag1);
/*     */       }
/*     */       
/* 184 */       world.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(flag1)).set(OPEN, Boolean.valueOf(flag1)), 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 191 */     BlockPosition blockposition1 = blockposition.down();
/* 192 */     IBlockData iblockdata1 = iworldreader.getType(blockposition1);
/*     */     
/* 194 */     return (iblockdata.get(HALF) == BlockPropertyDoubleBlockHalf.LOWER) ? iblockdata1.d(iworldreader, blockposition1, EnumDirection.UP) : iblockdata1.a(this);
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, boolean flag) {
/* 198 */     world.a((EntityHuman)null, flag ? d() : c(), blockposition, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumPistonReaction getPushReaction(IBlockData iblockdata) {
/* 203 */     return EnumPistonReaction.DESTROY;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 208 */     return iblockdata.set(FACING, enumblockrotation.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 213 */     return (enumblockmirror == EnumBlockMirror.NONE) ? iblockdata : iblockdata.a(enumblockmirror.a((EnumDirection)iblockdata.get(FACING))).a(HINGE);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 218 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { HALF, FACING, OPEN, HINGE, POWERED });
/*     */   }
/*     */   
/*     */   public static boolean a(World world, BlockPosition blockposition) {
/* 222 */     return l(world.getType(blockposition));
/*     */   }
/*     */   
/*     */   public static boolean l(IBlockData iblockdata) {
/* 226 */     return (iblockdata.getBlock() instanceof BlockDoor && (iblockdata.getMaterial() == Material.WOOD || iblockdata.getMaterial() == Material.NETHER_WOOD));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */