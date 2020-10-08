/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.serialization.MapCodec;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.ToIntFunction;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ public abstract class BlockBase {
/*  16 */   protected static final EnumDirection[] ar = new EnumDirection[] { EnumDirection.WEST, EnumDirection.EAST, EnumDirection.NORTH, EnumDirection.SOUTH, EnumDirection.DOWN, EnumDirection.UP };
/*     */   protected final Material material;
/*     */   protected final boolean at;
/*     */   protected final float durability;
/*     */   protected final boolean av;
/*     */   protected final SoundEffectType stepSound;
/*     */   protected final float frictionFactor;
/*     */   protected final float speedFactor;
/*     */   protected final float jumpFactor;
/*     */   protected final boolean aA;
/*     */   protected final Info aB;
/*     */   @Nullable
/*     */   protected MinecraftKey aC;
/*     */   
/*     */   public BlockBase(Info blockbase_info) {
/*  31 */     this.material = blockbase_info.a;
/*  32 */     this.at = blockbase_info.c;
/*  33 */     this.aC = blockbase_info.m;
/*  34 */     this.durability = blockbase_info.f;
/*  35 */     this.av = blockbase_info.i;
/*  36 */     this.stepSound = blockbase_info.d;
/*  37 */     this.frictionFactor = blockbase_info.j;
/*  38 */     this.speedFactor = blockbase_info.k;
/*  39 */     this.jumpFactor = blockbase_info.l;
/*  40 */     this.aA = blockbase_info.v;
/*  41 */     this.aB = blockbase_info;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void a(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, int i, int j) {}
/*     */   
/*     */   @Deprecated
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/*  49 */     switch (pathmode) {
/*     */       case LAND:
/*  51 */         return !iblockdata.r(iblockaccess, blockposition);
/*     */       case WATER:
/*  53 */         return iblockaccess.getFluid(blockposition).a(TagsFluid.WATER);
/*     */       case AIR:
/*  55 */         return !iblockdata.r(iblockaccess, blockposition);
/*     */     } 
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  63 */     return iblockdata;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/*  68 */     PacketDebug.a(world, blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag, ItemActionContext itemActionContext) {
/*  74 */     onPlace(iblockdata, world, blockposition, iblockdata1, flag);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  79 */     AsyncCatcher.catchOp("block onPlace");
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  84 */     AsyncCatcher.catchOp("block remove");
/*  85 */     if (isTileEntity() && !iblockdata.a(iblockdata1.getBlock())) {
/*  86 */       world.removeTileEntity(blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  93 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, int i, int j) {
/*  98 */     return false;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/* 103 */     return EnumRenderType.MODEL;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean c_(IBlockData iblockdata) {
/* 108 */     return false;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 113 */     return false;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public EnumPistonReaction getPushReaction(IBlockData iblockdata) {
/* 118 */     return this.material.getPushReaction();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Fluid d(IBlockData iblockdata) {
/* 123 */     return FluidTypes.EMPTY.h();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   public EnumRandomOffset ah_() {
/* 132 */     return EnumRandomOffset.NONE;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 137 */     return iblockdata;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 142 */     return iblockdata;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean a(IBlockData iblockdata, BlockActionContext blockactioncontext) {
/* 147 */     return (this.material.isReplaceable() && (blockactioncontext.getItemStack().isEmpty() || blockactioncontext.getItemStack().getItem() != getItem()) && (iblockdata.isDestroyable() || (blockactioncontext.getEntity() != null && (blockactioncontext.getEntity()).abilities.canInstantlyBuild)));
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean a(IBlockData iblockdata, FluidType fluidtype) {
/* 152 */     return (this.material.isReplaceable() || !this.material.isBuildable());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public List<ItemStack> a(IBlockData iblockdata, LootTableInfo.Builder loottableinfo_builder) {
/* 157 */     MinecraftKey minecraftkey = r();
/*     */     
/* 159 */     if (minecraftkey == LootTables.a) {
/* 160 */       return Collections.emptyList();
/*     */     }
/* 162 */     LootTableInfo loottableinfo = loottableinfo_builder.<IBlockData>set(LootContextParameters.BLOCK_STATE, iblockdata).build(LootContextParameterSets.BLOCK);
/* 163 */     WorldServer worldserver = loottableinfo.getWorld();
/* 164 */     LootTable loottable = worldserver.getMinecraftServer().getLootTableRegistry().getLootTable(minecraftkey);
/*     */     
/* 166 */     return loottable.populateLoot(loottableinfo);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public VoxelShape d(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 172 */     return iblockdata.getShape(iblockaccess, blockposition);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public VoxelShape e(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 177 */     return c(iblockdata, iblockaccess, blockposition, VoxelShapeCollision.a());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public VoxelShape a_(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 182 */     return VoxelShapes.a();
/*     */   }
/*     */   @Deprecated
/* 185 */   public final int getOpacity(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) { return f(iblockdata, iblockaccess, blockposition); } @Deprecated
/*     */   public int f(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 187 */     return iblockdata.i(iblockaccess, blockposition) ? iblockaccess.J() : (iblockdata.a(iblockaccess, blockposition) ? 0 : 1);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   @Deprecated
/*     */   public ITileInventory getInventory(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 193 */     return null;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 198 */     return true;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 203 */     return 0;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 208 */     return VoxelShapes.b();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public VoxelShape c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 213 */     return this.at ? iblockdata.getShape(iblockaccess, blockposition) : VoxelShapes.a();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 218 */     return c(iblockdata, iblockaccess, blockposition, voxelshapecollision);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 223 */     tickAlways(iblockdata, worldserver, blockposition, random);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {}
/*     */   
/*     */   @Deprecated
/*     */   public float getDamage(IBlockData iblockdata, EntityHuman entityhuman, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 231 */     float f = iblockdata.h(iblockaccess, blockposition);
/*     */     
/* 233 */     if (f == -1.0F) {
/* 234 */       return 0.0F;
/*     */     }
/* 236 */     int i = entityhuman.hasBlock(iblockdata) ? 30 : 100;
/*     */     
/* 238 */     return entityhuman.c(iblockdata) / f / i;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void dropNaturally(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, ItemStack itemstack) {}
/*     */   
/*     */   @Deprecated
/*     */   public void attack(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman) {}
/*     */   
/*     */   @Deprecated
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 250 */     return 0;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {}
/*     */   
/*     */   @Deprecated
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 258 */     return 0;
/*     */   }
/*     */   
/*     */   public final boolean isTileEntity() {
/* 262 */     return this instanceof ITileEntity;
/*     */   }
/*     */   
/*     */   public final MinecraftKey r() {
/* 266 */     if (this.aC == null) {
/* 267 */       MinecraftKey minecraftkey = IRegistry.BLOCK.getKey(p());
/*     */       
/* 269 */       this.aC = new MinecraftKey(minecraftkey.getNamespace(), "blocks/" + minecraftkey.getKey());
/*     */     } 
/*     */     
/* 272 */     return this.aC;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void a(World world, IBlockData iblockdata, MovingObjectPositionBlock movingobjectpositionblock, IProjectile iprojectile) {}
/*     */   
/*     */   public abstract Item getItem();
/*     */   
/*     */   protected abstract Block p();
/*     */   
/*     */   public MaterialMapColor s() {
/* 283 */     return this.aB.b.apply(p().getBlockData());
/*     */   }
/*     */   public static abstract class BlockData extends IBlockDataHolder<Block, IBlockData> { private final int b; private final boolean e; private final boolean f; private final Material g; private final MaterialMapColor h; public final float strength; private final boolean j; private final boolean k;
/*     */     private final BlockBase.e l;
/*     */     private final BlockBase.e m;
/*     */     private final BlockBase.e n;
/*     */     private final BlockBase.e o;
/*     */     private final BlockBase.e p;
/*     */     @Nullable
/*     */     protected Cache a;
/*     */     private CraftBlockData cachedCraftBlockData;
/*     */     protected boolean shapeExceedsCube;
/*     */     protected boolean isTicking;
/*     */     protected Fluid fluid;
/*     */     
/* 298 */     public final int getEmittedLight() { return this.b; } public final boolean isTransparentOnSomeFaces() {
/* 299 */       return this.e;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final boolean isOpaque() {
/* 305 */       return this.k;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected BlockData(Block block, ImmutableMap<IBlockState<?>, Comparable<?>> immutablemap, MapCodec<IBlockData> mapcodec) {
/* 315 */       super(block, immutablemap, mapcodec);
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
/*     */ 
/*     */       
/* 342 */       this.shapeExceedsCube = true; BlockBase.Info blockbase_info = block.aB; this.b = blockbase_info.e.applyAsInt(p()); this.e = block.c_(p()); this.f = blockbase_info.o; this.g = blockbase_info.a; this.h = blockbase_info.b.apply(p()); this.strength = blockbase_info.g; this.j = blockbase_info.h; this.k = blockbase_info.n; this.l = blockbase_info.q; this.m = blockbase_info.r; this.n = blockbase_info.s;
/*     */       this.o = blockbase_info.t;
/* 344 */       this.p = blockbase_info.u; } public final boolean shapeExceedsCube() { return this.shapeExceedsCube; }
/*     */ 
/*     */     
/*     */     public CraftBlockData createCraftBlockData() {
/*     */       if (this.cachedCraftBlockData == null)
/*     */         this.cachedCraftBlockData = CraftBlockData.createData(getBlockData()); 
/*     */       return (CraftBlockData)this.cachedCraftBlockData.clone();
/*     */     }
/*     */     
/*     */     public void a() {
/* 354 */       this.fluid = getBlock().d(p());
/* 355 */       this.isTicking = getBlock().isTicking(p());
/* 356 */       if (!getBlock().o()) {
/* 357 */         this.a = new Cache(p());
/*     */       }
/* 359 */       this.shapeExceedsCube = (this.a == null || this.a.c);
/*     */     }
/*     */ 
/*     */     
/*     */     public Block getBlock() {
/* 364 */       return this.c;
/*     */     }
/*     */     
/*     */     public final boolean isDestroyable() {
/* 368 */       return getBlock().isDestroyable();
/*     */     }
/*     */     
/*     */     public Material getMaterial() {
/* 372 */       return this.g;
/*     */     }
/*     */     
/*     */     public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, EntityTypes<?> entitytypes) {
/* 376 */       return (getBlock()).aB.p.test(p(), iblockaccess, blockposition, entitytypes);
/*     */     }
/*     */     
/*     */     public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 380 */       return (this.a != null) ? this.a.g : getBlock().b(p(), iblockaccess, blockposition);
/*     */     }
/*     */     public final int getOpacity(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 383 */       return b(iblockaccess, blockposition);
/*     */     } public int b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 385 */       return (this.a != null) ? this.a.h : getBlock().f(p(), iblockaccess, blockposition);
/*     */     }
/*     */     public final VoxelShape getCullingFace(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 388 */       return a(iblockaccess, blockposition, enumdirection);
/*     */     } public VoxelShape a(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 390 */       return (this.a != null && this.a.i != null) ? this.a.i[enumdirection.ordinal()] : VoxelShapes.a(c(iblockaccess, blockposition), enumdirection);
/*     */     }
/*     */     
/*     */     public VoxelShape c(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 394 */       return getBlock().d(p(), iblockaccess, blockposition);
/*     */     }
/*     */     
/*     */     public final boolean d() {
/* 398 */       return this.shapeExceedsCube;
/*     */     }
/*     */     
/*     */     public final boolean e() {
/* 402 */       return this.e;
/*     */     }
/*     */     
/*     */     public final int f() {
/* 406 */       return this.b;
/*     */     }
/*     */     
/*     */     public final boolean isAir() {
/* 410 */       return this.f;
/*     */     }
/*     */     
/*     */     public MaterialMapColor d(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 414 */       return this.h;
/*     */     }
/*     */     
/*     */     public IBlockData a(EnumBlockRotation enumblockrotation) {
/* 418 */       return getBlock().a(p(), enumblockrotation);
/*     */     }
/*     */     
/*     */     public IBlockData a(EnumBlockMirror enumblockmirror) {
/* 422 */       return getBlock().a(p(), enumblockmirror);
/*     */     }
/*     */     
/*     */     public EnumRenderType h() {
/* 426 */       return getBlock().b(p());
/*     */     }
/*     */     
/*     */     public boolean isOccluding(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 430 */       return this.l.test(p(), iblockaccess, blockposition);
/*     */     }
/*     */     
/*     */     public boolean isPowerSource() {
/* 434 */       return getBlock().isPowerSource(p());
/*     */     }
/*     */     
/*     */     public int b(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 438 */       return getBlock().a(p(), iblockaccess, blockposition, enumdirection);
/*     */     }
/*     */     
/*     */     public boolean isComplexRedstone() {
/* 442 */       return getBlock().isComplexRedstone(p());
/*     */     }
/*     */     
/*     */     public int a(World world, BlockPosition blockposition) {
/* 446 */       return getBlock().a(p(), world, blockposition);
/*     */     }
/*     */     
/*     */     public float h(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 450 */       return this.strength;
/*     */     }
/*     */     
/*     */     public float getDamage(EntityHuman entityhuman, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 454 */       return getBlock().getDamage(p(), entityhuman, iblockaccess, blockposition);
/*     */     }
/*     */     
/*     */     public int c(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 458 */       return getBlock().b(p(), iblockaccess, blockposition, enumdirection);
/*     */     }
/*     */     
/*     */     public EnumPistonReaction getPushReaction() {
/* 462 */       return !isDestroyable() ? EnumPistonReaction.BLOCK : getBlock().getPushReaction(p());
/*     */     }
/*     */     
/*     */     public boolean i(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 466 */       if (this.a != null) {
/* 467 */         return this.a.a;
/*     */       }
/* 469 */       IBlockData iblockdata = p();
/*     */       
/* 471 */       return iblockdata.l() ? Block.a(iblockdata.c(iblockaccess, blockposition)) : false;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean l() {
/* 476 */       return this.k;
/*     */     }
/*     */     
/*     */     public VoxelShape getShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 480 */       return a(iblockaccess, blockposition, VoxelShapeCollision.a());
/*     */     }
/*     */     
/*     */     public VoxelShape a(IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 484 */       return getBlock().b(p(), iblockaccess, blockposition, voxelshapecollision);
/*     */     }
/*     */     
/*     */     public VoxelShape getCollisionShape(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 488 */       return (this.a != null) ? this.a.b : b(iblockaccess, blockposition, VoxelShapeCollision.a());
/*     */     }
/*     */     public final VoxelShape getCollisionShape(IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 491 */       return b(iblockaccess, blockposition, voxelshapecollision);
/*     */     } public VoxelShape b(IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 493 */       return getBlock().c(p(), iblockaccess, blockposition, voxelshapecollision);
/*     */     }
/*     */     
/*     */     public VoxelShape l(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 497 */       return getBlock().e(p(), iblockaccess, blockposition);
/*     */     }
/*     */     
/*     */     public VoxelShape c(IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 501 */       return getBlock().a(p(), iblockaccess, blockposition, voxelshapecollision);
/*     */     }
/*     */     
/*     */     public VoxelShape m(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 505 */       return getBlock().a_(p(), iblockaccess, blockposition);
/*     */     }
/*     */     
/*     */     public final boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, Entity entity) {
/* 509 */       return a(iblockaccess, blockposition, entity, EnumDirection.UP);
/*     */     }
/*     */     
/*     */     public final boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, Entity entity, EnumDirection enumdirection) {
/* 513 */       return Block.a(b(iblockaccess, blockposition, VoxelShapeCollision.a(entity)), enumdirection);
/*     */     }
/*     */     
/*     */     public Vec3D n(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 517 */       BlockBase.EnumRandomOffset blockbase_enumrandomoffset = getBlock().ah_();
/*     */       
/* 519 */       if (blockbase_enumrandomoffset == BlockBase.EnumRandomOffset.NONE) {
/* 520 */         return Vec3D.ORIGIN;
/*     */       }
/* 522 */       long i = MathHelper.c(blockposition.getX(), 0, blockposition.getZ());
/*     */       
/* 524 */       return new Vec3D((((float)(i & 0xFL) / 15.0F) - 0.5D) * 0.5D, (blockbase_enumrandomoffset == BlockBase.EnumRandomOffset.XYZ) ? ((((float)(i >> 4L & 0xFL) / 15.0F) - 1.0D) * 0.2D) : 0.0D, (((float)(i >> 8L & 0xFL) / 15.0F) - 0.5D) * 0.5D);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(World world, BlockPosition blockposition, int i, int j) {
/* 529 */       return getBlock().a(p(), world, blockposition, i, j);
/*     */     }
/*     */     
/*     */     public void doPhysics(World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/* 533 */       getBlock().doPhysics(p(), world, blockposition, block, blockposition1, flag);
/*     */     }
/*     */     
/*     */     public final void a(GeneratorAccess generatoraccess, BlockPosition blockposition, int i) {
/* 537 */       a(generatoraccess, blockposition, i, 512);
/*     */     }
/*     */     
/*     */     public final void a(GeneratorAccess generatoraccess, BlockPosition blockposition, int i, int j) {
/* 541 */       getBlock();
/* 542 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 543 */       EnumDirection[] aenumdirection = BlockBase.ar;
/* 544 */       int k = aenumdirection.length;
/*     */       
/* 546 */       for (int l = 0; l < k; l++) {
/* 547 */         EnumDirection enumdirection = aenumdirection[l];
/*     */         
/* 549 */         blockposition_mutableblockposition.a(blockposition, enumdirection);
/* 550 */         IBlockData iblockdata = generatoraccess.getType(blockposition_mutableblockposition);
/* 551 */         IBlockData iblockdata1 = iblockdata.updateState(enumdirection.opposite(), p(), generatoraccess, blockposition_mutableblockposition, blockposition);
/*     */         
/* 553 */         Block.a(iblockdata, iblockdata1, generatoraccess, blockposition_mutableblockposition, i, j);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public final void b(GeneratorAccess generatoraccess, BlockPosition blockposition, int i) {
/* 559 */       b(generatoraccess, blockposition, i, 512);
/*     */     }
/*     */     
/*     */     public void b(GeneratorAccess generatoraccess, BlockPosition blockposition, int i, int j) {
/* 563 */       getBlock().a(p(), generatoraccess, blockposition, i, j);
/*     */     }
/*     */     
/*     */     public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 567 */       getBlock().onPlace(p(), world, blockposition, iblockdata, flag);
/*     */     }
/*     */     
/*     */     public void remove(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 571 */       getBlock().remove(p(), world, blockposition, iblockdata, flag);
/*     */     }
/*     */     
/*     */     public void a(WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 575 */       getBlock().tickAlways(p(), worldserver, blockposition, random);
/*     */     }
/*     */     
/*     */     public void b(WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 579 */       getBlock().tick(p(), worldserver, blockposition, random);
/*     */     }
/*     */     
/*     */     public void a(World world, BlockPosition blockposition, Entity entity) {
/* 583 */       getBlock().a(p(), world, blockposition, entity);
/*     */     }
/*     */     
/*     */     public void dropNaturally(WorldServer worldserver, BlockPosition blockposition, ItemStack itemstack) {
/* 587 */       getBlock().dropNaturally(p(), worldserver, blockposition, itemstack);
/*     */     }
/*     */     
/*     */     public List<ItemStack> a(LootTableInfo.Builder loottableinfo_builder) {
/* 591 */       return getBlock().a(p(), loottableinfo_builder);
/*     */     }
/*     */     
/*     */     public EnumInteractionResult interact(World world, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 595 */       return getBlock().interact(p(), world, movingobjectpositionblock.getBlockPosition(), entityhuman, enumhand, movingobjectpositionblock);
/*     */     }
/*     */     
/*     */     public void attack(World world, BlockPosition blockposition, EntityHuman entityhuman) {
/* 599 */       getBlock().attack(p(), world, blockposition, entityhuman);
/*     */     }
/*     */     
/*     */     public boolean o(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 603 */       return this.m.test(p(), iblockaccess, blockposition);
/*     */     }
/*     */     
/*     */     public IBlockData updateState(EnumDirection enumdirection, IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 607 */       return getBlock().updateState(p(), enumdirection, iblockdata, generatoraccess, blockposition, blockposition1);
/*     */     }
/*     */     
/*     */     public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 611 */       return getBlock().a(p(), iblockaccess, blockposition, pathmode);
/*     */     }
/*     */     
/*     */     public boolean a(BlockActionContext blockactioncontext) {
/* 615 */       return getBlock().a(p(), blockactioncontext);
/*     */     }
/*     */     
/*     */     public boolean a(FluidType fluidtype) {
/* 619 */       return getBlock().a(p(), fluidtype);
/*     */     }
/*     */     
/*     */     public boolean canPlace(IWorldReader iworldreader, BlockPosition blockposition) {
/* 623 */       return getBlock().canPlace(p(), iworldreader, blockposition);
/*     */     }
/*     */     
/*     */     public boolean q(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 627 */       return this.o.test(p(), iblockaccess, blockposition);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public ITileInventory b(World world, BlockPosition blockposition) {
/* 632 */       return getBlock().getInventory(p(), world, blockposition);
/*     */     }
/*     */     
/*     */     public boolean a(Tag<Block> tag) {
/* 636 */       return getBlock().a(tag);
/*     */     }
/*     */     
/*     */     public boolean a(Tag<Block> tag, Predicate<BlockData> predicate) {
/* 640 */       return (getBlock().a(tag) && predicate.test(this));
/*     */     }
/*     */     
/*     */     public boolean a(Block block) {
/* 644 */       return getBlock().a(block);
/*     */     }
/*     */     
/*     */     public final Fluid getFluid() {
/* 648 */       return this.fluid;
/*     */     }
/*     */     
/*     */     public final boolean isTicking() {
/* 652 */       return this.isTicking;
/*     */     }
/*     */     
/*     */     public SoundEffectType getStepSound() {
/* 656 */       return getBlock().getStepSound(p());
/*     */     }
/*     */     
/*     */     public void a(World world, IBlockData iblockdata, MovingObjectPositionBlock movingobjectpositionblock, IProjectile iprojectile) {
/* 660 */       getBlock().a(world, iblockdata, movingobjectpositionblock, iprojectile);
/*     */     }
/*     */     
/*     */     public boolean d(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 664 */       return a(iblockaccess, blockposition, enumdirection, EnumBlockSupport.FULL);
/*     */     }
/*     */     
/*     */     public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection, EnumBlockSupport enumblocksupport) {
/* 668 */       return (this.a != null) ? this.a.a(enumdirection, enumblocksupport) : enumblocksupport.a(p(), iblockaccess, blockposition, enumdirection);
/*     */     }
/*     */     
/*     */     public boolean r(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 672 */       return (this.a != null) ? this.a.d : Block.a(getCollisionShape(iblockaccess, blockposition));
/*     */     }
/*     */     public final IBlockData getBlockData() {
/* 675 */       return p();
/*     */     }
/*     */     protected abstract IBlockData p();
/*     */     public boolean isRequiresSpecialTool() {
/* 679 */       return this.j;
/*     */     }
/*     */     
/*     */     static final class Cache
/*     */     {
/* 684 */       private static final EnumDirection[] e = EnumDirection.values();
/* 685 */       private static final int f = (EnumBlockSupport.values()).length;
/*     */       protected final boolean a;
/*     */       private final boolean g;
/*     */       private final int h;
/*     */       @Nullable
/*     */       private final VoxelShape[] i;
/*     */       protected final VoxelShape b;
/*     */       protected final boolean c;
/*     */       private final boolean[] j;
/*     */       protected final boolean d;
/*     */       
/*     */       private Cache(IBlockData iblockdata) {
/* 697 */         Block block = iblockdata.getBlock();
/*     */         
/* 699 */         this.a = iblockdata.i(BlockAccessAir.INSTANCE, BlockPosition.ZERO);
/* 700 */         this.g = block.b(iblockdata, BlockAccessAir.INSTANCE, BlockPosition.ZERO);
/* 701 */         this.h = block.f(iblockdata, BlockAccessAir.INSTANCE, BlockPosition.ZERO);
/*     */ 
/*     */         
/* 704 */         if (!iblockdata.l()) {
/* 705 */           this.i = null;
/*     */         } else {
/* 707 */           this.i = new VoxelShape[e.length];
/* 708 */           VoxelShape voxelshape = block.d(iblockdata, BlockAccessAir.INSTANCE, BlockPosition.ZERO);
/* 709 */           EnumDirection[] aenumdirection = e;
/*     */           
/* 711 */           int m = aenumdirection.length;
/*     */           
/* 713 */           for (int j = 0; j < m; j++) {
/* 714 */             EnumDirection enumdirection = aenumdirection[j];
/*     */             
/* 716 */             this.i[enumdirection.ordinal()] = VoxelShapes.a(voxelshape, enumdirection);
/*     */           } 
/*     */         } 
/*     */         
/* 720 */         this.b = block.c(iblockdata, BlockAccessAir.INSTANCE, BlockPosition.ZERO, VoxelShapeCollision.a());
/* 721 */         this.c = Arrays.<EnumDirection.EnumAxis>stream(EnumDirection.EnumAxis.values()).anyMatch(enumdirection_enumaxis -> 
/* 722 */             (this.b.b(enumdirection_enumaxis) < 0.0D || this.b.c(enumdirection_enumaxis) > 1.0D));
/*     */         
/* 724 */         this.j = new boolean[e.length * f];
/* 725 */         EnumDirection[] aenumdirection1 = e;
/* 726 */         int k = aenumdirection1.length;
/*     */         
/* 728 */         for (int i = 0; i < k; i++) {
/* 729 */           EnumDirection enumdirection1 = aenumdirection1[i];
/* 730 */           EnumBlockSupport[] aenumblocksupport = EnumBlockSupport.values();
/* 731 */           int l = aenumblocksupport.length;
/*     */           
/* 733 */           for (int i1 = 0; i1 < l; i1++) {
/* 734 */             EnumBlockSupport enumblocksupport = aenumblocksupport[i1];
/*     */             
/* 736 */             this.j[b(enumdirection1, enumblocksupport)] = enumblocksupport.a(iblockdata, BlockAccessAir.INSTANCE, BlockPosition.ZERO, enumdirection1);
/*     */           } 
/*     */         } 
/*     */         
/* 740 */         this.d = Block.a(iblockdata.getCollisionShape(BlockAccessAir.INSTANCE, BlockPosition.ZERO));
/*     */       }
/*     */       
/*     */       public boolean a(EnumDirection enumdirection, EnumBlockSupport enumblocksupport) {
/* 744 */         return this.j[b(enumdirection, enumblocksupport)];
/*     */       }
/*     */       
/*     */       private static int b(EnumDirection enumdirection, EnumBlockSupport enumblocksupport) {
/* 748 */         return enumdirection.ordinal() * f + enumblocksupport.ordinal();
/*     */       }
/*     */     } }
/*     */ 
/*     */   
/*     */   public static class Info
/*     */   {
/*     */     private Material a;
/*     */     private Function<IBlockData, MaterialMapColor> b;
/*     */     private boolean c;
/*     */     private SoundEffectType d;
/*     */     private ToIntFunction<IBlockData> e;
/*     */     private float f;
/*     */     private float g;
/*     */     private boolean h;
/*     */     private boolean i;
/*     */     private float j;
/*     */     private float k;
/*     */     private float l;
/*     */     private MinecraftKey m;
/*     */     private boolean n;
/*     */     private boolean o;
/*     */     private BlockBase.d<EntityTypes<?>> p;
/*     */     private BlockBase.e q;
/*     */     private BlockBase.e r;
/*     */     private BlockBase.e s;
/*     */     private BlockBase.e t;
/*     */     private BlockBase.e u;
/*     */     private boolean v;
/*     */     
/*     */     private Info(Material material, MaterialMapColor materialmapcolor) {
/* 779 */       this(material, iblockdata -> materialmapcolor);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Info(Material material, Function<IBlockData, MaterialMapColor> function) {
/* 785 */       this.c = true;
/* 786 */       this.d = SoundEffectType.e;
/* 787 */       this.e = (iblockdata -> 0);
/*     */ 
/*     */       
/* 790 */       this.j = 0.6F;
/* 791 */       this.k = 1.0F;
/* 792 */       this.l = 1.0F;
/* 793 */       this.n = true;
/* 794 */       this.p = ((iblockdata, iblockaccess, blockposition, entitytypes) -> 
/* 795 */         (iblockdata.d(iblockaccess, blockposition, EnumDirection.UP) && iblockdata.f() < 14));
/*     */       
/* 797 */       this.q = ((iblockdata, iblockaccess, blockposition) -> 
/* 798 */         (iblockdata.getMaterial().f() && iblockdata.r(iblockaccess, blockposition)));
/*     */       
/* 800 */       this.r = ((iblockdata, iblockaccess, blockposition) -> 
/* 801 */         (this.a.isSolid() && iblockdata.r(iblockaccess, blockposition)));
/*     */       
/* 803 */       this.s = this.r;
/* 804 */       this.t = ((iblockdata, iblockaccess, blockposition) -> false);
/*     */ 
/*     */       
/* 807 */       this.u = ((iblockdata, iblockaccess, blockposition) -> false);
/*     */ 
/*     */       
/* 810 */       this.a = material;
/* 811 */       this.b = function;
/*     */     }
/*     */     
/*     */     public static Info a(Material material) {
/* 815 */       return a(material, material.h());
/*     */     }
/*     */     
/*     */     public static Info a(Material material, EnumColor enumcolor) {
/* 819 */       return a(material, enumcolor.f());
/*     */     }
/*     */     
/*     */     public static Info a(Material material, MaterialMapColor materialmapcolor) {
/* 823 */       return new Info(material, materialmapcolor);
/*     */     }
/*     */     
/*     */     public static Info a(Material material, Function<IBlockData, MaterialMapColor> function) {
/* 827 */       return new Info(material, function);
/*     */     }
/*     */     
/*     */     public static Info a(BlockBase blockbase) {
/* 831 */       Info blockbase_info = new Info(blockbase.material, blockbase.aB.b);
/*     */       
/* 833 */       blockbase_info.a = blockbase.aB.a;
/* 834 */       blockbase_info.g = blockbase.aB.g;
/* 835 */       blockbase_info.f = blockbase.aB.f;
/* 836 */       blockbase_info.c = blockbase.aB.c;
/* 837 */       blockbase_info.i = blockbase.aB.i;
/* 838 */       blockbase_info.e = blockbase.aB.e;
/* 839 */       blockbase_info.b = blockbase.aB.b;
/* 840 */       blockbase_info.d = blockbase.aB.d;
/* 841 */       blockbase_info.j = blockbase.aB.j;
/* 842 */       blockbase_info.k = blockbase.aB.k;
/* 843 */       blockbase_info.v = blockbase.aB.v;
/* 844 */       blockbase_info.n = blockbase.aB.n;
/* 845 */       blockbase_info.o = blockbase.aB.o;
/* 846 */       blockbase_info.h = blockbase.aB.h;
/* 847 */       return blockbase_info;
/*     */     }
/*     */     
/*     */     public Info a() {
/* 851 */       this.c = false;
/* 852 */       this.n = false;
/* 853 */       return this;
/*     */     }
/*     */     
/*     */     public Info b() {
/* 857 */       this.n = false;
/* 858 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(float f) {
/* 862 */       this.j = f;
/* 863 */       return this;
/*     */     }
/*     */     
/*     */     public Info b(float f) {
/* 867 */       this.k = f;
/* 868 */       return this;
/*     */     }
/*     */     
/*     */     public Info c(float f) {
/* 872 */       this.l = f;
/* 873 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(SoundEffectType soundeffecttype) {
/* 877 */       this.d = soundeffecttype;
/* 878 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(ToIntFunction<IBlockData> tointfunction) {
/* 882 */       this.e = tointfunction;
/* 883 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(float f, float f1) {
/* 887 */       this.g = f;
/* 888 */       this.f = Math.max(0.0F, f1);
/* 889 */       return this;
/*     */     }
/*     */     
/*     */     public Info c() {
/* 893 */       return d(0.0F);
/*     */     }
/*     */     
/*     */     public Info d(float f) {
/* 897 */       a(f, f);
/* 898 */       return this;
/*     */     }
/*     */     
/*     */     public Info d() {
/* 902 */       this.i = true;
/* 903 */       return this;
/*     */     }
/*     */     
/*     */     public Info e() {
/* 907 */       this.v = true;
/* 908 */       return this;
/*     */     }
/*     */     
/*     */     public Info f() {
/* 912 */       this.m = LootTables.a;
/* 913 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(Block block) {
/* 917 */       this.m = block.r();
/* 918 */       return this;
/*     */     }
/*     */     
/*     */     public Info g() {
/* 922 */       this.o = true;
/* 923 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(BlockBase.d<EntityTypes<?>> blockbase_d) {
/* 927 */       this.p = blockbase_d;
/* 928 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(BlockBase.e blockbase_e) {
/* 932 */       this.q = blockbase_e;
/* 933 */       return this;
/*     */     }
/*     */     
/*     */     public Info b(BlockBase.e blockbase_e) {
/* 937 */       this.r = blockbase_e;
/* 938 */       return this;
/*     */     }
/*     */     
/*     */     public Info c(BlockBase.e blockbase_e) {
/* 942 */       this.s = blockbase_e;
/* 943 */       return this;
/*     */     }
/*     */     
/*     */     public Info d(BlockBase.e blockbase_e) {
/* 947 */       this.t = blockbase_e;
/* 948 */       return this;
/*     */     }
/*     */     
/*     */     public Info e(BlockBase.e blockbase_e) {
/* 952 */       this.u = blockbase_e;
/* 953 */       return this;
/*     */     }
/*     */     
/*     */     public Info h() {
/* 957 */       this.h = true;
/* 958 */       return this;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumRandomOffset
/*     */   {
/* 964 */     NONE, XZ, XYZ;
/*     */   }
/*     */   
/*     */   public static interface e {
/*     */     boolean test(IBlockData param1IBlockData, IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition);
/*     */   }
/*     */   
/*     */   public static interface d<A> {
/*     */     boolean test(IBlockData param1IBlockData, IBlockAccess param1IBlockAccess, BlockPosition param1BlockPosition, A param1A);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */