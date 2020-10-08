/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
/*     */ 
/*     */ public class BlockBed
/*     */   extends BlockFacingHorizontal implements ITileEntity {
/*  10 */   public static final BlockStateEnum<BlockPropertyBedPart> PART = BlockProperties.aE;
/*  11 */   public static final BlockStateBoolean OCCUPIED = BlockProperties.t;
/*  12 */   protected static final VoxelShape c = Block.a(0.0D, 3.0D, 0.0D, 16.0D, 9.0D, 16.0D);
/*  13 */   protected static final VoxelShape d = Block.a(0.0D, 0.0D, 0.0D, 3.0D, 3.0D, 3.0D);
/*  14 */   protected static final VoxelShape e = Block.a(0.0D, 0.0D, 13.0D, 3.0D, 3.0D, 16.0D);
/*  15 */   protected static final VoxelShape f = Block.a(13.0D, 0.0D, 0.0D, 16.0D, 3.0D, 3.0D);
/*  16 */   protected static final VoxelShape g = Block.a(13.0D, 0.0D, 13.0D, 16.0D, 3.0D, 16.0D);
/*  17 */   protected static final VoxelShape h = VoxelShapes.a(c, new VoxelShape[] { d, f });
/*  18 */   protected static final VoxelShape i = VoxelShapes.a(c, new VoxelShape[] { e, g });
/*  19 */   protected static final VoxelShape j = VoxelShapes.a(c, new VoxelShape[] { d, e });
/*  20 */   protected static final VoxelShape k = VoxelShapes.a(c, new VoxelShape[] { f, g });
/*     */   private final EnumColor color;
/*     */   
/*     */   public BlockBed(EnumColor enumcolor, BlockBase.Info blockbase_info) {
/*  24 */     super(blockbase_info);
/*  25 */     this.color = enumcolor;
/*  26 */     j(((IBlockData)this.blockStateList.getBlockData()).set(PART, BlockPropertyBedPart.FOOT).set(OCCUPIED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  31 */     if (world.isClientSide) {
/*  32 */       return EnumInteractionResult.CONSUME;
/*     */     }
/*  34 */     if (iblockdata.get(PART) != BlockPropertyBedPart.HEAD) {
/*  35 */       blockposition = blockposition.shift((EnumDirection)iblockdata.get(FACING));
/*  36 */       iblockdata = world.getType(blockposition);
/*  37 */       if (!iblockdata.a(this)) {
/*  38 */         return EnumInteractionResult.CONSUME;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     if (((Boolean)iblockdata.get(OCCUPIED)).booleanValue()) {
/*  54 */       if (!a(world, blockposition)) {
/*  55 */         entityhuman.a(new ChatMessage("block.minecraft.bed.occupied"), true);
/*     */       }
/*     */       
/*  58 */       return EnumInteractionResult.SUCCESS;
/*     */     } 
/*     */     
/*  61 */     IBlockData finaliblockdata = iblockdata;
/*  62 */     BlockPosition finalblockposition = blockposition;
/*     */     
/*  64 */     entityhuman.sleep(blockposition).ifLeft(entityhuman_enumbedresult -> {
/*     */           if (entityhuman_enumbedresult == EntityHuman.EnumBedResult.NOT_POSSIBLE_HERE) {
/*     */             explodeBed(finaliblockdata, world, finalblockposition);
/*     */           } else if (entityhuman_enumbedresult != null) {
/*     */             entityhuman.a(entityhuman_enumbedresult.a(), true);
/*     */           } 
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     return EnumInteractionResult.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EnumInteractionResult explodeBed(IBlockData iblockdata, World world, BlockPosition blockposition) {
/*  84 */     world.a(blockposition, false);
/*  85 */     BlockPosition blockposition1 = blockposition.shift(((EnumDirection)iblockdata.get(FACING)).opposite());
/*     */     
/*  87 */     if (world.getType(blockposition1).getBlock() == this) {
/*  88 */       world.a(blockposition1, false);
/*     */     }
/*     */     
/*  91 */     world.createExplosion((Entity)null, DamageSource.a(), (ExplosionDamageCalculator)null, blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D, 5.0F, true, Explosion.Effect.DESTROY);
/*  92 */     return EnumInteractionResult.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(World world) {
/* 100 */     return true;
/*     */   }
/*     */   
/*     */   private boolean a(World world, BlockPosition blockposition) {
/* 104 */     List<EntityVillager> list = world.a(EntityVillager.class, new AxisAlignedBB(blockposition), EntityLiving::isSleeping);
/*     */     
/* 106 */     if (list.isEmpty()) {
/* 107 */       return false;
/*     */     }
/* 109 */     ((EntityVillager)list.get(0)).entityWakeup();
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fallOn(World world, BlockPosition blockposition, Entity entity, float f) {
/* 116 */     super.fallOn(world, blockposition, entity, f * 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockAccess iblockaccess, Entity entity) {
/* 121 */     if (entity.bv()) {
/* 122 */       super.a(iblockaccess, entity);
/*     */     } else {
/* 124 */       a(entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(Entity entity) {
/* 130 */     Vec3D vec3d = entity.getMot();
/*     */     
/* 132 */     if (vec3d.y < 0.0D) {
/* 133 */       double d0 = (entity instanceof EntityLiving) ? 1.0D : 0.8D;
/*     */       
/* 135 */       entity.setMot(vec3d.x, -vec3d.y * 0.6600000262260437D * d0, vec3d.z);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 142 */     return (enumdirection == a((BlockPropertyBedPart)iblockdata.get(PART), (EnumDirection)iblockdata.get(FACING))) ? ((iblockdata1.a(this) && iblockdata1.get(PART) != iblockdata.get(PART)) ? iblockdata.set(OCCUPIED, iblockdata1.get(OCCUPIED)) : Blocks.AIR.getBlockData()) : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */   
/*     */   private static EnumDirection a(BlockPropertyBedPart blockpropertybedpart, EnumDirection enumdirection) {
/* 146 */     return (blockpropertybedpart == BlockPropertyBedPart.FOOT) ? enumdirection : enumdirection.opposite();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/* 151 */     if (!world.isClientSide && entityhuman.isCreative()) {
/* 152 */       BlockPropertyBedPart blockpropertybedpart = (BlockPropertyBedPart)iblockdata.get(PART);
/*     */       
/* 154 */       if (blockpropertybedpart == BlockPropertyBedPart.FOOT) {
/* 155 */         BlockPosition blockposition1 = blockposition.shift(a(blockpropertybedpart, (EnumDirection)iblockdata.get(FACING)));
/* 156 */         IBlockData iblockdata1 = world.getType(blockposition1);
/*     */         
/* 158 */         if (iblockdata1.getBlock() == this && iblockdata1.get(PART) == BlockPropertyBedPart.HEAD) {
/* 159 */           world.setTypeAndData(blockposition1, Blocks.AIR.getBlockData(), 35);
/* 160 */           world.a(entityhuman, 2001, blockposition1, Block.getCombinedId(iblockdata1));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 165 */     super.a(world, blockposition, iblockdata, entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 171 */     EnumDirection enumdirection = blockactioncontext.f();
/* 172 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/* 173 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */     
/* 175 */     return blockactioncontext.getWorld().getType(blockposition1).a(blockactioncontext) ? getBlockData().set(FACING, enumdirection) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 180 */     EnumDirection enumdirection = g(iblockdata).opposite();
/*     */     
/* 182 */     switch (enumdirection) {
/*     */       case NORTH:
/* 184 */         return h;
/*     */       case SOUTH:
/* 186 */         return i;
/*     */       case WEST:
/* 188 */         return j;
/*     */     } 
/* 190 */     return k;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumDirection g(IBlockData iblockdata) {
/* 195 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/*     */     
/* 197 */     return (iblockdata.get(PART) == BlockPropertyBedPart.HEAD) ? enumdirection.opposite() : enumdirection;
/*     */   }
/*     */   
/*     */   private static boolean b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 201 */     return iblockaccess.getType(blockposition.down()).getBlock() instanceof BlockBed;
/*     */   }
/*     */   
/*     */   public static Optional<Vec3D> a(EntityTypes<?> entitytypes, ICollisionAccess icollisionaccess, BlockPosition blockposition, float f) {
/* 205 */     EnumDirection enumdirection = (EnumDirection)icollisionaccess.getType(blockposition).get(FACING);
/* 206 */     EnumDirection enumdirection1 = enumdirection.g();
/* 207 */     EnumDirection enumdirection2 = enumdirection1.a(f) ? enumdirection1.opposite() : enumdirection1;
/*     */     
/* 209 */     if (b(icollisionaccess, blockposition)) {
/* 210 */       return a(entitytypes, icollisionaccess, blockposition, enumdirection, enumdirection2);
/*     */     }
/* 212 */     int[][] aint = a(enumdirection, enumdirection2);
/* 213 */     Optional<Vec3D> optional = a(entitytypes, icollisionaccess, blockposition, aint, true);
/*     */     
/* 215 */     return optional.isPresent() ? optional : a(entitytypes, icollisionaccess, blockposition, aint, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Optional<Vec3D> a(EntityTypes<?> entitytypes, ICollisionAccess icollisionaccess, BlockPosition blockposition, EnumDirection enumdirection, EnumDirection enumdirection1) {
/* 220 */     int[][] aint = b(enumdirection, enumdirection1);
/* 221 */     Optional<Vec3D> optional = a(entitytypes, icollisionaccess, blockposition, aint, true);
/*     */     
/* 223 */     if (optional.isPresent()) {
/* 224 */       return optional;
/*     */     }
/* 226 */     BlockPosition blockposition1 = blockposition.down();
/* 227 */     Optional<Vec3D> optional1 = a(entitytypes, icollisionaccess, blockposition1, aint, true);
/*     */     
/* 229 */     if (optional1.isPresent()) {
/* 230 */       return optional1;
/*     */     }
/* 232 */     int[][] aint1 = a(enumdirection);
/* 233 */     Optional<Vec3D> optional2 = a(entitytypes, icollisionaccess, blockposition, aint1, true);
/*     */     
/* 235 */     if (optional2.isPresent()) {
/* 236 */       return optional2;
/*     */     }
/* 238 */     Optional<Vec3D> optional3 = a(entitytypes, icollisionaccess, blockposition, aint, false);
/*     */     
/* 240 */     if (optional3.isPresent()) {
/* 241 */       return optional3;
/*     */     }
/* 243 */     Optional<Vec3D> optional4 = a(entitytypes, icollisionaccess, blockposition1, aint, false);
/*     */     
/* 245 */     return optional4.isPresent() ? optional4 : a(entitytypes, icollisionaccess, blockposition, aint1, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Optional<Vec3D> a(EntityTypes<?> entitytypes, ICollisionAccess icollisionaccess, BlockPosition blockposition, int[][] aint, boolean flag) {
/* 253 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 254 */     int[][] aint1 = aint;
/* 255 */     int i = aint.length;
/*     */     
/* 257 */     for (int j = 0; j < i; j++) {
/* 258 */       int[] aint2 = aint1[j];
/*     */       
/* 260 */       blockposition_mutableblockposition.d(blockposition.getX() + aint2[0], blockposition.getY(), blockposition.getZ() + aint2[1]);
/* 261 */       Vec3D vec3d = DismountUtil.a(entitytypes, icollisionaccess, blockposition_mutableblockposition, flag);
/*     */       
/* 263 */       if (vec3d != null) {
/* 264 */         return Optional.of(vec3d);
/*     */       }
/*     */     } 
/*     */     
/* 268 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumPistonReaction getPushReaction(IBlockData iblockdata) {
/* 273 */     return EnumPistonReaction.DESTROY;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/* 278 */     return EnumRenderType.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 283 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, PART, OCCUPIED });
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess iblockaccess) {
/* 288 */     return new TileEntityBed(this.color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, @Nullable EntityLiving entityliving, ItemStack itemstack) {
/* 293 */     super.postPlace(world, blockposition, iblockdata, entityliving, itemstack);
/* 294 */     if (!world.isClientSide) {
/* 295 */       BlockPosition blockposition1 = blockposition.shift((EnumDirection)iblockdata.get(FACING));
/*     */       
/* 297 */       world.setTypeAndData(blockposition1, iblockdata.set(PART, BlockPropertyBedPart.HEAD), 3);
/* 298 */       world.update(blockposition, Blocks.AIR);
/* 299 */       iblockdata.a(world, blockposition, 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 306 */     return false;
/*     */   }
/*     */   
/*     */   private static int[][] a(EnumDirection enumdirection, EnumDirection enumdirection1) {
/* 310 */     return (int[][])ArrayUtils.addAll((Object[])b(enumdirection, enumdirection1), (Object[])a(enumdirection));
/*     */   }
/*     */   
/*     */   private static int[][] b(EnumDirection enumdirection, EnumDirection enumdirection1) {
/* 314 */     return new int[][] { { enumdirection1.getAdjacentX(), enumdirection1.getAdjacentZ() }, { enumdirection1.getAdjacentX() - enumdirection.getAdjacentX(), enumdirection1.getAdjacentZ() - enumdirection.getAdjacentZ() }, { enumdirection1.getAdjacentX() - enumdirection.getAdjacentX() * 2, enumdirection1.getAdjacentZ() - enumdirection.getAdjacentZ() * 2 }, { -enumdirection.getAdjacentX() * 2, -enumdirection.getAdjacentZ() * 2 }, { -enumdirection1.getAdjacentX() - enumdirection.getAdjacentX() * 2, -enumdirection1.getAdjacentZ() - enumdirection.getAdjacentZ() * 2 }, { -enumdirection1.getAdjacentX() - enumdirection.getAdjacentX(), -enumdirection1.getAdjacentZ() - enumdirection.getAdjacentZ() }, { -enumdirection1.getAdjacentX(), -enumdirection1.getAdjacentZ() }, { -enumdirection1.getAdjacentX() + enumdirection.getAdjacentX(), -enumdirection1.getAdjacentZ() + enumdirection.getAdjacentZ() }, { enumdirection.getAdjacentX(), enumdirection.getAdjacentZ() }, { enumdirection1.getAdjacentX() + enumdirection.getAdjacentX(), enumdirection1.getAdjacentZ() + enumdirection.getAdjacentZ() } };
/*     */   }
/*     */   
/*     */   private static int[][] a(EnumDirection enumdirection) {
/* 318 */     return new int[][] { { 0, 0 }, { -enumdirection.getAdjacentX(), -enumdirection.getAdjacentZ() } };
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */