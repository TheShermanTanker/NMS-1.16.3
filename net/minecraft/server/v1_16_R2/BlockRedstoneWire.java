/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.util.RedstoneWireTurbo;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockRedstoneWire
/*     */   extends Block
/*     */ {
/*  18 */   public static final BlockStateEnum<BlockPropertyRedstoneSide> NORTH = BlockProperties.X;
/*  19 */   public static final BlockStateEnum<BlockPropertyRedstoneSide> EAST = BlockProperties.W;
/*  20 */   public static final BlockStateEnum<BlockPropertyRedstoneSide> SOUTH = BlockProperties.Y;
/*  21 */   public static final BlockStateEnum<BlockPropertyRedstoneSide> WEST = BlockProperties.Z;
/*  22 */   public static final BlockStateInteger POWER = BlockProperties.az;
/*  23 */   public static final Map<EnumDirection, BlockStateEnum<BlockPropertyRedstoneSide>> f = Maps.newEnumMap((Map)ImmutableMap.of(EnumDirection.NORTH, NORTH, EnumDirection.EAST, EAST, EnumDirection.SOUTH, SOUTH, EnumDirection.WEST, WEST));
/*  24 */   private static final VoxelShape g = Block.a(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D);
/*  25 */   private static final Map<EnumDirection, VoxelShape> h = Maps.newEnumMap((Map)ImmutableMap.of(EnumDirection.NORTH, Block.a(3.0D, 0.0D, 0.0D, 13.0D, 1.0D, 13.0D), EnumDirection.SOUTH, Block.a(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 16.0D), EnumDirection.EAST, Block.a(3.0D, 0.0D, 3.0D, 16.0D, 1.0D, 13.0D), EnumDirection.WEST, Block.a(0.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D)));
/*  26 */   private static final Map<EnumDirection, VoxelShape> i = Maps.newEnumMap((Map)ImmutableMap.of(EnumDirection.NORTH, VoxelShapes.a(h.get(EnumDirection.NORTH), Block.a(3.0D, 0.0D, 0.0D, 13.0D, 16.0D, 1.0D)), EnumDirection.SOUTH, VoxelShapes.a(h.get(EnumDirection.SOUTH), Block.a(3.0D, 0.0D, 15.0D, 13.0D, 16.0D, 16.0D)), EnumDirection.EAST, VoxelShapes.a(h.get(EnumDirection.EAST), Block.a(15.0D, 0.0D, 3.0D, 16.0D, 16.0D, 13.0D)), EnumDirection.WEST, VoxelShapes.a(h.get(EnumDirection.WEST), Block.a(0.0D, 0.0D, 3.0D, 1.0D, 16.0D, 13.0D))));
/*  27 */   private final Map<IBlockData, VoxelShape> j = Maps.newHashMap();
/*  28 */   private static final Vector3fa[] k = new Vector3fa[16]; private final IBlockData o; private boolean p = true; RedstoneWireTurbo turbo;
/*     */   
/*  30 */   public final boolean canProvidePower() { return this.p; } public final void setCanProvidePower(boolean value) { this.p = value; }
/*     */   private VoxelShape l(IBlockData iblockdata) { VoxelShape voxelshape = g; Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator(); while (iterator.hasNext()) { EnumDirection enumdirection = iterator.next(); BlockPropertyRedstoneSide blockpropertyredstoneside = (BlockPropertyRedstoneSide)iblockdata.get(f.get(enumdirection)); if (blockpropertyredstoneside == BlockPropertyRedstoneSide.SIDE) { voxelshape = VoxelShapes.a(voxelshape, h.get(enumdirection)); continue; }  if (blockpropertyredstoneside == BlockPropertyRedstoneSide.UP) voxelshape = VoxelShapes.a(voxelshape, i.get(enumdirection));  }  return voxelshape; }
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) { return this.j.get(iblockdata.set(POWER, Integer.valueOf(0))); }
/*  33 */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) { return a(blockactioncontext.getWorld(), this.o, blockactioncontext.getClickPosition()); } public BlockRedstoneWire(BlockBase.Info blockbase_info) { super(blockbase_info);
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
/*     */ 
/*     */ 
/*     */     
/* 219 */     this.turbo = new RedstoneWireTurbo(this); j(((IBlockData)this.blockStateList.getBlockData()).set(NORTH, BlockPropertyRedstoneSide.NONE).set(EAST, BlockPropertyRedstoneSide.NONE).set(SOUTH, BlockPropertyRedstoneSide.NONE).set(WEST, BlockPropertyRedstoneSide.NONE).set(POWER, Integer.valueOf(0))); this.o = getBlockData().set(NORTH, BlockPropertyRedstoneSide.SIDE).set(EAST, BlockPropertyRedstoneSide.SIDE).set(SOUTH, BlockPropertyRedstoneSide.SIDE).set(WEST, BlockPropertyRedstoneSide.SIDE); UnmodifiableIterator unmodifiableiterator = getStates().a().iterator(); while (unmodifiableiterator.hasNext()) { IBlockData iblockdata = (IBlockData)unmodifiableiterator.next(); if (((Integer)iblockdata.get(POWER)).intValue() == 0) this.j.put(iblockdata, l(iblockdata));  }  }
/*     */   private IBlockData a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition) { boolean flag = n(iblockdata); iblockdata = b(iblockaccess, getBlockData().set(POWER, iblockdata.get(POWER)), blockposition); if (flag && n(iblockdata)) return iblockdata;  boolean flag1 = ((BlockPropertyRedstoneSide)iblockdata.get(NORTH)).b(); boolean flag2 = ((BlockPropertyRedstoneSide)iblockdata.get(SOUTH)).b(); boolean flag3 = ((BlockPropertyRedstoneSide)iblockdata.get(EAST)).b(); boolean flag4 = ((BlockPropertyRedstoneSide)iblockdata.get(WEST)).b(); boolean flag5 = (!flag1 && !flag2); boolean flag6 = (!flag3 && !flag4); if (!flag4 && flag5) iblockdata = iblockdata.set(WEST, BlockPropertyRedstoneSide.SIDE);  if (!flag3 && flag5)
/*     */       iblockdata = iblockdata.set(EAST, BlockPropertyRedstoneSide.SIDE);  if (!flag1 && flag6)
/*     */       iblockdata = iblockdata.set(NORTH, BlockPropertyRedstoneSide.SIDE);  if (!flag2 && flag6)
/*     */       iblockdata = iblockdata.set(SOUTH, BlockPropertyRedstoneSide.SIDE);  return iblockdata; }
/*     */   private IBlockData b(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition) { boolean flag = !iblockaccess.getType(blockposition.up()).isOccluding(iblockaccess, blockposition); Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator(); while (iterator.hasNext()) { EnumDirection enumdirection = iterator.next(); if (!((BlockPropertyRedstoneSide)iblockdata.get(f.get(enumdirection))).b()) { BlockPropertyRedstoneSide blockpropertyredstoneside = a(iblockaccess, blockposition, enumdirection, flag); iblockdata = iblockdata.set(f.get(enumdirection), blockpropertyredstoneside); }  }  return iblockdata; }
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) { if (enumdirection == EnumDirection.DOWN)
/*     */       return iblockdata;  if (enumdirection == EnumDirection.UP)
/* 227 */       return a(generatoraccess, iblockdata, blockposition);  BlockPropertyRedstoneSide blockpropertyredstoneside = a(generatoraccess, blockposition, enumdirection); return (blockpropertyredstoneside.b() == ((BlockPropertyRedstoneSide)iblockdata.get(f.get(enumdirection))).b() && !m(iblockdata)) ? iblockdata.set(f.get(enumdirection), blockpropertyredstoneside) : a(generatoraccess, this.o.set(POWER, iblockdata.get(POWER)).set(f.get(enumdirection), blockpropertyredstoneside), blockposition); } private void updateSurroundingRedstone(World worldIn, BlockPosition pos, IBlockData state, BlockPosition source) { if (worldIn.paperConfig.useEigencraftRedstone) {
/* 228 */       this.turbo.updateSurroundingRedstone(worldIn, pos, state, source);
/*     */       return;
/*     */     } 
/* 231 */     a(worldIn, pos, state); } private static boolean m(IBlockData iblockdata) { return (((BlockPropertyRedstoneSide)iblockdata.get(NORTH)).b() && ((BlockPropertyRedstoneSide)iblockdata.get(SOUTH)).b() && ((BlockPropertyRedstoneSide)iblockdata.get(EAST)).b() && ((BlockPropertyRedstoneSide)iblockdata.get(WEST)).b()); }
/*     */   private static boolean n(IBlockData iblockdata) { return (!((BlockPropertyRedstoneSide)iblockdata.get(NORTH)).b() && !((BlockPropertyRedstoneSide)iblockdata.get(SOUTH)).b() && !((BlockPropertyRedstoneSide)iblockdata.get(EAST)).b() && !((BlockPropertyRedstoneSide)iblockdata.get(WEST)).b()); }
/*     */   public void a(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, int i, int j) { BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(); Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator(); while (iterator.hasNext()) { EnumDirection enumdirection = iterator.next(); BlockPropertyRedstoneSide blockpropertyredstoneside = (BlockPropertyRedstoneSide)iblockdata.get(f.get(enumdirection)); if (blockpropertyredstoneside != BlockPropertyRedstoneSide.NONE && !generatoraccess.getType(blockposition_mutableblockposition.a(blockposition, enumdirection)).a(this)) { blockposition_mutableblockposition.c(EnumDirection.DOWN); IBlockData iblockdata1 = generatoraccess.getType(blockposition_mutableblockposition); if (!iblockdata1.a(Blocks.OBSERVER)) { BlockPosition blockposition1 = blockposition_mutableblockposition.shift(enumdirection.opposite()); IBlockData iblockdata2 = iblockdata1.updateState(enumdirection.opposite(), generatoraccess.getType(blockposition1), generatoraccess, blockposition_mutableblockposition, blockposition1); a(iblockdata1, iblockdata2, generatoraccess, blockposition_mutableblockposition, i, j); }  blockposition_mutableblockposition.a(blockposition, enumdirection).c(EnumDirection.UP); IBlockData iblockdata3 = generatoraccess.getType(blockposition_mutableblockposition); if (!iblockdata3.a(Blocks.OBSERVER)) { BlockPosition blockposition2 = blockposition_mutableblockposition.shift(enumdirection.opposite()); IBlockData iblockdata4 = iblockdata3.updateState(enumdirection.opposite(), generatoraccess.getType(blockposition2), generatoraccess, blockposition_mutableblockposition, blockposition2); a(iblockdata3, iblockdata4, generatoraccess, blockposition_mutableblockposition, i, j); }  }
/*     */        }
/*     */      }
/*     */   private BlockPropertyRedstoneSide a(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) { return a(iblockaccess, blockposition, enumdirection, !iblockaccess.getType(blockposition.up()).isOccluding(iblockaccess, blockposition)); }
/*     */   private BlockPropertyRedstoneSide a(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection, boolean flag) { BlockPosition blockposition1 = blockposition.shift(enumdirection); IBlockData iblockdata = iblockaccess.getType(blockposition1); if (flag) { boolean flag1 = b(iblockaccess, blockposition1, iblockdata); if (flag1 && h(iblockaccess.getType(blockposition1.up()))) { if (iblockdata.d(iblockaccess, blockposition1, enumdirection.opposite()))
/*     */           return BlockPropertyRedstoneSide.UP;  return BlockPropertyRedstoneSide.SIDE; }
/*     */        }
/*     */      return (!a(iblockdata, enumdirection) && (iblockdata.isOccluding(iblockaccess, blockposition1) || !h(iblockaccess.getType(blockposition1.down())))) ? BlockPropertyRedstoneSide.NONE : BlockPropertyRedstoneSide.SIDE; }
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) { BlockPosition blockposition1 = blockposition.down(); IBlockData iblockdata1 = iworldreader.getType(blockposition1); return b(iworldreader, blockposition1, iblockdata1); }
/*     */   private boolean b(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) { return (iblockdata.d(iblockaccess, blockposition, EnumDirection.UP) || iblockdata.a(Blocks.HOPPER)); }
/* 243 */   public IBlockData calculateCurrentChanges(World worldIn, BlockPosition pos1, BlockPosition pos2, IBlockData state) { IBlockData iblockstate = state;
/* 244 */     int i = ((Integer)state.get(POWER)).intValue();
/* 245 */     int j = 0;
/* 246 */     j = getPower(j, worldIn.getType(pos2));
/* 247 */     setCanProvidePower(false);
/* 248 */     int k = worldIn.isBlockIndirectlyGettingPowered(pos1);
/* 249 */     setCanProvidePower(true);
/*     */     
/* 251 */     if (!worldIn.paperConfig.useEigencraftRedstone)
/*     */     {
/* 253 */       if (k > 0 && k > j - 1) {
/* 254 */         j = k;
/*     */       }
/*     */     }
/*     */     
/* 258 */     int l = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     if (!worldIn.paperConfig.useEigencraftRedstone || k < 15) {
/* 266 */       for (EnumDirection enumfacing : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 267 */         BlockPosition blockpos = pos1.shift(enumfacing);
/* 268 */         boolean flag = (blockpos.getX() != pos2.getX() || blockpos.getZ() != pos2.getZ());
/*     */         
/* 270 */         if (flag) {
/* 271 */           l = getPower(l, worldIn.getType(blockpos));
/*     */         }
/*     */         
/* 274 */         if (worldIn.getType(blockpos).isOccluding(worldIn, blockpos) && !worldIn.getType(pos1.up()).isOccluding(worldIn, pos1)) {
/* 275 */           if (flag && pos1.getY() >= pos2.getY())
/* 276 */             l = getPower(l, worldIn.getType(blockpos.up()));  continue;
/*     */         } 
/* 278 */         if (!worldIn.getType(blockpos).isOccluding(worldIn, blockpos) && flag && pos1.getY() <= pos2.getY()) {
/* 279 */           l = getPower(l, worldIn.getType(blockpos.down()));
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 284 */     if (!worldIn.paperConfig.useEigencraftRedstone) {
/*     */       
/* 286 */       if (l > j) {
/* 287 */         j = l - 1;
/* 288 */       } else if (j > 0) {
/* 289 */         j--;
/*     */       } else {
/* 291 */         j = 0;
/*     */       } 
/*     */       
/* 294 */       if (k > j - 1) {
/* 295 */         j = k;
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 301 */       j = l - 1;
/*     */ 
/*     */ 
/*     */       
/* 305 */       if (k > j) j = k;
/*     */     
/*     */     } 
/* 308 */     if (i != j) {
/* 309 */       state = state.set(POWER, Integer.valueOf(j));
/*     */       
/* 311 */       if (worldIn.getType(pos1) == iblockstate) {
/* 312 */         worldIn.setTypeAndData(pos1, state, 2);
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
/*     */     
/* 328 */     return state; }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 333 */     int i = a(world, blockposition);
/*     */ 
/*     */     
/* 336 */     int oldPower = ((Integer)iblockdata.get(POWER)).intValue();
/* 337 */     if (oldPower != i) {
/* 338 */       BlockRedstoneEvent event = new BlockRedstoneEvent(world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), oldPower, i);
/* 339 */       world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 341 */       i = event.getNewCurrent();
/*     */     } 
/* 343 */     if (oldPower != i) {
/*     */       
/* 345 */       if (world.getType(blockposition) == iblockdata) {
/* 346 */         world.setTypeAndData(blockposition, iblockdata.set(POWER, Integer.valueOf(i)), 2);
/*     */       }
/*     */       
/* 349 */       Set<BlockPosition> set = Sets.newHashSet();
/*     */       
/* 351 */       set.add(blockposition);
/* 352 */       EnumDirection[] aenumdirection = EnumDirection.values();
/* 353 */       int j = aenumdirection.length;
/*     */       
/* 355 */       for (int k = 0; k < j; k++) {
/* 356 */         EnumDirection enumdirection = aenumdirection[k];
/*     */         
/* 358 */         set.add(blockposition.shift(enumdirection));
/*     */       } 
/*     */       
/* 361 */       Iterator<BlockPosition> iterator = set.iterator();
/*     */       
/* 363 */       while (iterator.hasNext()) {
/* 364 */         BlockPosition blockposition1 = iterator.next();
/*     */         
/* 366 */         world.applyPhysics(blockposition1, this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int a(World world, BlockPosition blockposition) {
/* 373 */     this.p = false;
/* 374 */     int i = world.s(blockposition);
/*     */     
/* 376 */     this.p = true;
/* 377 */     int j = 0;
/*     */     
/* 379 */     if (i < 15) {
/* 380 */       Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */       
/* 382 */       while (iterator.hasNext()) {
/* 383 */         EnumDirection enumdirection = iterator.next();
/* 384 */         BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 385 */         IBlockData iblockdata = world.getType(blockposition1);
/*     */         
/* 387 */         j = Math.max(j, o(iblockdata));
/* 388 */         BlockPosition blockposition2 = blockposition.up();
/*     */         
/* 390 */         if (iblockdata.isOccluding(world, blockposition1) && !world.getType(blockposition2).isOccluding(world, blockposition2)) {
/* 391 */           j = Math.max(j, o(world.getType(blockposition1.up()))); continue;
/* 392 */         }  if (!iblockdata.isOccluding(world, blockposition1)) {
/* 393 */           j = Math.max(j, o(world.getType(blockposition1.down())));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 398 */     return Math.max(i, j - 1);
/*     */   }
/*     */   
/* 401 */   private int getPower(int min, IBlockData iblockdata) { return Math.max(min, getPower(iblockdata)); } private int getPower(IBlockData iblockdata) {
/* 402 */     return o(iblockdata);
/*     */   } private int o(IBlockData iblockdata) {
/* 404 */     return iblockdata.a(this) ? ((Integer)iblockdata.get(POWER)).intValue() : 0;
/*     */   }
/*     */   
/*     */   private void b(World world, BlockPosition blockposition) {
/* 408 */     if (world.getType(blockposition).a(this)) {
/* 409 */       world.applyPhysics(blockposition, this);
/* 410 */       EnumDirection[] aenumdirection = EnumDirection.values();
/* 411 */       int i = aenumdirection.length;
/*     */       
/* 413 */       for (int j = 0; j < i; j++) {
/* 414 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/* 416 */         world.applyPhysics(blockposition.shift(enumdirection), this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 424 */     if (!iblockdata1.a(iblockdata.getBlock()) && !world.isClientSide) {
/* 425 */       updateSurroundingRedstone(world, blockposition, iblockdata, (BlockPosition)null);
/* 426 */       Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.VERTICAL.iterator();
/*     */       
/* 428 */       while (iterator.hasNext()) {
/* 429 */         EnumDirection enumdirection = iterator.next();
/*     */         
/* 431 */         world.applyPhysics(blockposition.shift(enumdirection), this);
/*     */       } 
/*     */       
/* 434 */       d(world, blockposition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 440 */     if (!flag && !iblockdata.a(iblockdata1.getBlock())) {
/* 441 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/* 442 */       if (!world.isClientSide) {
/* 443 */         EnumDirection[] aenumdirection = EnumDirection.values();
/* 444 */         int i = aenumdirection.length;
/*     */         
/* 446 */         for (int j = 0; j < i; j++) {
/* 447 */           EnumDirection enumdirection = aenumdirection[j];
/*     */           
/* 449 */           world.applyPhysics(blockposition.shift(enumdirection), this);
/*     */         } 
/*     */         
/* 452 */         updateSurroundingRedstone(world, blockposition, iblockdata, (BlockPosition)null);
/* 453 */         d(world, blockposition);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void d(World world, BlockPosition blockposition) {
/* 459 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/* 463 */     while (iterator.hasNext()) {
/* 464 */       EnumDirection enumdirection = iterator.next();
/* 465 */       b(world, blockposition.shift(enumdirection));
/*     */     } 
/*     */     
/* 468 */     iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 470 */     while (iterator.hasNext()) {
/* 471 */       EnumDirection enumdirection = iterator.next();
/* 472 */       BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */       
/* 474 */       if (world.getType(blockposition1).isOccluding(world, blockposition1)) {
/* 475 */         b(world, blockposition1.up()); continue;
/*     */       } 
/* 477 */       b(world, blockposition1.down());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/* 485 */     if (!world.isClientSide) {
/* 486 */       if (iblockdata.canPlace(world, blockposition)) {
/* 487 */         updateSurroundingRedstone(world, blockposition, iblockdata, blockposition1);
/*     */       } else {
/* 489 */         c(iblockdata, world, blockposition);
/* 490 */         world.a(blockposition, false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 498 */     return !this.p ? 0 : iblockdata.b(iblockaccess, blockposition, enumdirection);
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 503 */     if (this.p && enumdirection != EnumDirection.DOWN) {
/* 504 */       int i = ((Integer)iblockdata.get(POWER)).intValue();
/*     */       
/* 506 */       return (i == 0) ? 0 : ((enumdirection != EnumDirection.UP && !((BlockPropertyRedstoneSide)a(iblockaccess, iblockdata, blockposition).get(f.get(enumdirection.opposite()))).b()) ? 0 : i);
/*     */     } 
/* 508 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean h(IBlockData iblockdata) {
/* 513 */     return a(iblockdata, (EnumDirection)null);
/*     */   }
/*     */   
/*     */   protected static boolean a(IBlockData iblockdata, @Nullable EnumDirection enumdirection) {
/* 517 */     if (iblockdata.a(Blocks.REDSTONE_WIRE))
/* 518 */       return true; 
/* 519 */     if (iblockdata.a(Blocks.REPEATER)) {
/* 520 */       EnumDirection enumdirection1 = (EnumDirection)iblockdata.get(BlockRepeater.FACING);
/*     */       
/* 522 */       return (enumdirection1 == enumdirection || enumdirection1.opposite() == enumdirection);
/*     */     } 
/* 524 */     return iblockdata.a(Blocks.OBSERVER) ? ((enumdirection == iblockdata.get(BlockObserver.FACING))) : ((iblockdata.isPowerSource() && enumdirection != null));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 530 */     return this.p;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 535 */     switch (enumblockrotation) {
/*     */       case LEFT_RIGHT:
/* 537 */         return iblockdata.set(NORTH, iblockdata.get(SOUTH)).set(EAST, iblockdata.get(WEST)).set(SOUTH, iblockdata.get(NORTH)).set(WEST, iblockdata.get(EAST));
/*     */       case FRONT_BACK:
/* 539 */         return iblockdata.set(NORTH, iblockdata.get(EAST)).set(EAST, iblockdata.get(SOUTH)).set(SOUTH, iblockdata.get(WEST)).set(WEST, iblockdata.get(NORTH));
/*     */       case null:
/* 541 */         return iblockdata.set(NORTH, iblockdata.get(WEST)).set(EAST, iblockdata.get(NORTH)).set(SOUTH, iblockdata.get(EAST)).set(WEST, iblockdata.get(SOUTH));
/*     */     } 
/* 543 */     return iblockdata;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 549 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 551 */         return iblockdata.set(NORTH, iblockdata.get(SOUTH)).set(SOUTH, iblockdata.get(NORTH));
/*     */       case FRONT_BACK:
/* 553 */         return iblockdata.set(EAST, iblockdata.get(WEST)).set(WEST, iblockdata.get(EAST));
/*     */     } 
/* 555 */     return super.a(iblockdata, enumblockmirror);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 561 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { NORTH, EAST, SOUTH, WEST, POWER });
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 566 */     if (!entityhuman.abilities.mayBuild) {
/* 567 */       return EnumInteractionResult.PASS;
/*     */     }
/* 569 */     if (m(iblockdata) || n(iblockdata)) {
/* 570 */       IBlockData iblockdata1 = m(iblockdata) ? getBlockData() : this.o;
/*     */       
/* 572 */       iblockdata1 = iblockdata1.set(POWER, iblockdata.get(POWER));
/* 573 */       iblockdata1 = a(world, iblockdata1, blockposition);
/* 574 */       if (iblockdata1 != iblockdata) {
/* 575 */         world.setTypeAndData(blockposition, iblockdata1, 3);
/* 576 */         a(world, blockposition, iblockdata, iblockdata1);
/* 577 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/*     */     } 
/*     */     
/* 581 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, IBlockData iblockdata, IBlockData iblockdata1) {
/* 586 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 588 */     while (iterator.hasNext()) {
/* 589 */       EnumDirection enumdirection = iterator.next();
/* 590 */       BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */       
/* 592 */       if (((BlockPropertyRedstoneSide)iblockdata.get(f.get(enumdirection))).b() != ((BlockPropertyRedstoneSide)iblockdata1.get(f.get(enumdirection))).b() && world.getType(blockposition1).isOccluding(world, blockposition1)) {
/* 593 */         world.a(blockposition1, iblockdata1.getBlock(), enumdirection.opposite());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 600 */     for (int i = 0; i <= 15; i++) {
/* 601 */       float f = i / 15.0F;
/* 602 */       float f1 = f * 0.6F + ((f > 0.0F) ? 0.4F : 0.3F);
/* 603 */       float f2 = MathHelper.a(f * f * 0.7F - 0.5F, 0.0F, 1.0F);
/* 604 */       float f3 = MathHelper.a(f * f * 0.6F - 0.7F, 0.0F, 1.0F);
/*     */       
/* 606 */       k[i] = new Vector3fa(f1, f2, f3);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockRedstoneWire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */