/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockPistonExtendEvent;
/*     */ import org.bukkit.event.block.BlockPistonRetractEvent;
/*     */ 
/*     */ public class BlockPiston
/*     */   extends BlockDirectional
/*     */ {
/*  20 */   public static final BlockStateBoolean EXTENDED = BlockProperties.g;
/*  21 */   protected static final VoxelShape c = Block.a(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
/*  22 */   protected static final VoxelShape d = Block.a(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  23 */   protected static final VoxelShape e = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
/*  24 */   protected static final VoxelShape f = Block.a(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
/*  25 */   protected static final VoxelShape g = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
/*  26 */   protected static final VoxelShape h = Block.a(0.0D, 4.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*     */   private final boolean sticky;
/*     */   
/*     */   public BlockPiston(boolean flag, BlockBase.Info blockbase_info) {
/*  30 */     super(blockbase_info);
/*  31 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(EXTENDED, Boolean.valueOf(false)));
/*  32 */     this.sticky = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  37 */     if (((Boolean)iblockdata.get(EXTENDED)).booleanValue()) {
/*  38 */       switch ((EnumDirection)iblockdata.get(FACING))
/*     */       { case BLOCK:
/*  40 */           return h;
/*     */         
/*     */         default:
/*  43 */           return g;
/*     */         case PUSH_ONLY:
/*  45 */           return f;
/*     */         case null:
/*  47 */           return e;
/*     */         case null:
/*  49 */           return d;
/*     */         case null:
/*  51 */           break; }  return c;
/*     */     } 
/*     */     
/*  54 */     return VoxelShapes.b();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/*  60 */     if (!world.isClientSide) {
/*  61 */       a(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/*  68 */     if (!world.isClientSide) {
/*  69 */       a(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  76 */     if (!iblockdata1.a(iblockdata.getBlock()) && 
/*  77 */       !world.isClientSide && world.getTileEntity(blockposition) == null) {
/*  78 */       a(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  86 */     return getBlockData().set(FACING, blockactioncontext.d().opposite()).set(EXTENDED, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  90 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/*  91 */     boolean flag = a(world, blockposition, enumdirection);
/*     */     
/*  93 */     if (flag && !((Boolean)iblockdata.get(EXTENDED)).booleanValue()) {
/*  94 */       if ((new PistonExtendsChecker(world, blockposition, enumdirection, true)).a()) {
/*  95 */         world.playBlockAction(blockposition, this, 0, enumdirection.c());
/*     */       }
/*  97 */     } else if (!flag && ((Boolean)iblockdata.get(EXTENDED)).booleanValue()) {
/*  98 */       BlockPosition blockposition1 = blockposition.shift(enumdirection, 2);
/*  99 */       IBlockData iblockdata1 = world.getType(blockposition1);
/* 100 */       byte b0 = 1;
/*     */       
/* 102 */       if (iblockdata1.a(Blocks.MOVING_PISTON) && iblockdata1.get(FACING) == enumdirection) {
/* 103 */         TileEntity tileentity = world.getTileEntity(blockposition1);
/*     */         
/* 105 */         if (tileentity instanceof TileEntityPiston) {
/* 106 */           TileEntityPiston tileentitypiston = (TileEntityPiston)tileentity;
/*     */           
/* 108 */           if (tileentitypiston.d() && (tileentitypiston.a(0.0F) < 0.5F || world.getTime() == tileentitypiston.m() || ((WorldServer)world).m_())) {
/* 109 */             b0 = 2;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 116 */       Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 117 */       BlockPistonRetractEvent event = new BlockPistonRetractEvent(block, (List)ImmutableList.of(), CraftBlock.notchToBlockFace(enumdirection));
/* 118 */       world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 120 */       if (event.isCancelled()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 126 */       world.playBlockAction(blockposition, this, b0, enumdirection.c());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/* 132 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 133 */     int i = aenumdirection.length;
/*     */     
/*     */     int j;
/*     */     
/* 137 */     for (j = 0; j < i; j++) {
/* 138 */       EnumDirection enumdirection1 = aenumdirection[j];
/*     */       
/* 140 */       if (enumdirection1 != enumdirection && world.isBlockFacePowered(blockposition.shift(enumdirection1), enumdirection1)) {
/* 141 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 145 */     if (world.isBlockFacePowered(blockposition, EnumDirection.DOWN)) {
/* 146 */       return true;
/*     */     }
/* 148 */     BlockPosition blockposition1 = blockposition.up();
/* 149 */     EnumDirection[] aenumdirection1 = EnumDirection.values();
/*     */     
/* 151 */     j = aenumdirection1.length;
/*     */     
/* 153 */     for (int k = 0; k < j; k++) {
/* 154 */       EnumDirection enumdirection2 = aenumdirection1[k];
/*     */       
/* 156 */       if (enumdirection2 != EnumDirection.DOWN && world.isBlockFacePowered(blockposition1.shift(enumdirection2), enumdirection2)) {
/* 157 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, int i, int j) {
/* 167 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/*     */     
/* 169 */     EnumDirection directionQueuedAs = EnumDirection.fromType1(j & 0x7);
/* 170 */     if (!PaperConfig.allowBlockPermanentBreakingExploits && enumdirection != directionQueuedAs) {
/* 171 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 175 */     if (!world.isClientSide) {
/* 176 */       boolean flag = a(world, blockposition, enumdirection);
/*     */       
/* 178 */       if (flag && (i == 1 || i == 2)) {
/* 179 */         world.setTypeAndData(blockposition, iblockdata.set(EXTENDED, Boolean.valueOf(true)), 2);
/* 180 */         return false;
/*     */       } 
/*     */       
/* 183 */       if (!flag && i == 0) {
/* 184 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 188 */     if (i == 0) {
/* 189 */       if (!a(world, blockposition, enumdirection, true)) {
/* 190 */         return false;
/*     */       }
/*     */       
/* 193 */       world.setTypeAndData(blockposition, iblockdata.set(EXTENDED, Boolean.valueOf(true)), 67);
/* 194 */       world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.25F + 0.6F);
/* 195 */     } else if (i == 1 || i == 2) {
/* 196 */       TileEntity tileentity = world.getTileEntity(blockposition.shift(enumdirection));
/*     */       
/* 198 */       if (tileentity instanceof TileEntityPiston) {
/* 199 */         ((TileEntityPiston)tileentity).l();
/*     */       }
/*     */       
/* 202 */       IBlockData iblockdata1 = Blocks.MOVING_PISTON.getBlockData().set(BlockPistonMoving.a, enumdirection).set(BlockPistonMoving.b, this.sticky ? BlockPropertyPistonType.STICKY : BlockPropertyPistonType.DEFAULT);
/*     */       
/* 204 */       world.setTypeAndData(blockposition, iblockdata1, 20);
/* 205 */       world.setTileEntity(blockposition, BlockPistonMoving.a(getBlockData().set(FACING, EnumDirection.fromType1(j & 0x7)), enumdirection, false, true));
/* 206 */       world.update(blockposition, iblockdata1.getBlock());
/* 207 */       iblockdata1.a(world, blockposition, 2);
/* 208 */       if (this.sticky) {
/* 209 */         BlockPosition blockposition1 = blockposition.b(enumdirection.getAdjacentX() * 2, enumdirection.getAdjacentY() * 2, enumdirection.getAdjacentZ() * 2);
/* 210 */         IBlockData iblockdata2 = world.getType(blockposition1);
/* 211 */         boolean flag1 = false;
/*     */         
/* 213 */         if (iblockdata2.a(Blocks.MOVING_PISTON)) {
/* 214 */           TileEntity tileentity1 = world.getTileEntity(blockposition1);
/*     */           
/* 216 */           if (tileentity1 instanceof TileEntityPiston) {
/* 217 */             TileEntityPiston tileentitypiston = (TileEntityPiston)tileentity1;
/*     */             
/* 219 */             if (tileentitypiston.f() == enumdirection && tileentitypiston.d()) {
/* 220 */               tileentitypiston.l();
/* 221 */               flag1 = true;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 226 */         if (!flag1) {
/* 227 */           if (i == 1 && !iblockdata2.isAir() && a(iblockdata2, world, blockposition1, enumdirection.opposite(), false, enumdirection) && (iblockdata2.getPushReaction() == EnumPistonReaction.NORMAL || iblockdata2.a(Blocks.PISTON) || iblockdata2.a(Blocks.STICKY_PISTON))) {
/* 228 */             a(world, blockposition, enumdirection, false);
/*     */           } else {
/* 230 */             world.a(blockposition.shift(enumdirection), false);
/*     */           } 
/*     */         }
/*     */       } else {
/*     */         
/* 235 */         BlockPosition headPos = blockposition.shift(enumdirection);
/* 236 */         if (PaperConfig.allowBlockPermanentBreakingExploits || world.getType(headPos) == Blocks.PISTON_HEAD.getBlockData().set(FACING, enumdirection)) {
/* 237 */           world.setAir(headPos, false);
/*     */         } else {
/* 239 */           ((WorldServer)world).getChunkProvider().flagDirty(headPos);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 244 */       world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.5F, world.random.nextFloat() * 0.15F + 0.6F);
/*     */     } 
/*     */     
/* 247 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, EnumDirection enumdirection, boolean flag, EnumDirection enumdirection1) {
/* 251 */     if (blockposition.getY() >= 0 && blockposition.getY() <= world.getBuildHeight() - 1 && world.getWorldBorder().a(blockposition)) {
/* 252 */       if (iblockdata.isAir())
/* 253 */         return true; 
/* 254 */       if (!iblockdata.a(Blocks.OBSIDIAN) && !iblockdata.a(Blocks.CRYING_OBSIDIAN) && !iblockdata.a(Blocks.RESPAWN_ANCHOR)) {
/* 255 */         if (enumdirection == EnumDirection.DOWN && blockposition.getY() == 0)
/* 256 */           return false; 
/* 257 */         if (enumdirection == EnumDirection.UP && blockposition.getY() == world.getBuildHeight() - 1) {
/* 258 */           return false;
/*     */         }
/* 260 */         if (!iblockdata.a(Blocks.PISTON) && !iblockdata.a(Blocks.STICKY_PISTON)) {
/* 261 */           if (iblockdata.h(world, blockposition) == -1.0F) {
/* 262 */             return false;
/*     */           }
/*     */           
/* 265 */           switch (iblockdata.getPushReaction()) {
/*     */             case BLOCK:
/* 267 */               return false;
/*     */             case DESTROY:
/* 269 */               return flag;
/*     */             case PUSH_ONLY:
/* 271 */               return (enumdirection == enumdirection1);
/*     */           } 
/* 273 */         } else if (((Boolean)iblockdata.get(EXTENDED)).booleanValue()) {
/* 274 */           return false;
/*     */         } 
/*     */         
/* 277 */         return !iblockdata.getBlock().isTileEntity();
/*     */       } 
/*     */       
/* 280 */       return false;
/*     */     } 
/*     */     
/* 283 */     return false;
/*     */   }
/*     */   
/*     */   private boolean a(World world, BlockPosition blockposition, EnumDirection enumdirection, boolean flag) {
/*     */     BlockPistonRetractEvent blockPistonRetractEvent;
/* 288 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */     
/* 290 */     if (!flag && world.getType(blockposition1).a(Blocks.PISTON_HEAD)) {
/* 291 */       world.setTypeAndData(blockposition1, Blocks.AIR.getBlockData(), 20);
/*     */     }
/*     */     
/* 294 */     PistonExtendsChecker pistonextendschecker = new PistonExtendsChecker(world, blockposition, enumdirection, flag);
/*     */     
/* 296 */     if (!pistonextendschecker.a()) {
/* 297 */       return false;
/*     */     }
/* 299 */     Map<BlockPosition, IBlockData> map = Maps.newHashMap();
/* 300 */     List<BlockPosition> list = pistonextendschecker.getMovedBlocks();
/* 301 */     List<IBlockData> list1 = Lists.newArrayList();
/*     */     
/* 303 */     for (int i = 0; i < list.size(); i++) {
/* 304 */       BlockPosition blockposition2 = list.get(i);
/* 305 */       IBlockData iblockdata = world.getType(blockposition2);
/*     */       
/* 307 */       list1.add(iblockdata);
/* 308 */       map.put(blockposition2, iblockdata);
/*     */     } 
/*     */     
/* 311 */     List<BlockPosition> list2 = pistonextendschecker.getBrokenBlocks();
/* 312 */     IBlockData[] aiblockdata = new IBlockData[list.size() + list2.size()];
/* 313 */     EnumDirection enumdirection1 = flag ? enumdirection : enumdirection.opposite();
/* 314 */     int j = 0;
/*     */     
/* 316 */     final Block bblock = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */     
/* 318 */     final List<BlockPosition> moved = pistonextendschecker.getMovedBlocks();
/* 319 */     final List<BlockPosition> broken = pistonextendschecker.getBrokenBlocks();
/*     */     
/* 321 */     List<Block> blocks = new AbstractList<Block>()
/*     */       {
/*     */         public int size()
/*     */         {
/* 325 */           return moved.size() + broken.size();
/*     */         }
/*     */ 
/*     */         
/*     */         public Block get(int index) {
/* 330 */           if (index >= size() || index < 0) {
/* 331 */             throw new ArrayIndexOutOfBoundsException(index);
/*     */           }
/* 333 */           BlockPosition pos = (index < moved.size()) ? moved.get(index) : broken.get(index - moved.size());
/* 334 */           return bblock.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ());
/*     */         }
/*     */       };
/*     */     
/* 338 */     if (flag) {
/* 339 */       BlockPistonExtendEvent blockPistonExtendEvent = new BlockPistonExtendEvent(bblock, blocks, CraftBlock.notchToBlockFace(enumdirection1));
/*     */     } else {
/* 341 */       blockPistonRetractEvent = new BlockPistonRetractEvent(bblock, blocks, CraftBlock.notchToBlockFace(enumdirection1));
/*     */     } 
/* 343 */     world.getServer().getPluginManager().callEvent((Event)blockPistonRetractEvent);
/*     */     
/* 345 */     if (blockPistonRetractEvent.isCancelled()) {
/* 346 */       for (BlockPosition b : broken) {
/* 347 */         world.notify(b, Blocks.AIR.getBlockData(), world.getType(b), 3);
/*     */       }
/* 349 */       for (BlockPosition b : moved) {
/* 350 */         world.notify(b, Blocks.AIR.getBlockData(), world.getType(b), 3);
/* 351 */         b = b.shift(enumdirection1);
/* 352 */         world.notify(b, Blocks.AIR.getBlockData(), world.getType(b), 3);
/*     */       } 
/* 354 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     int k;
/*     */ 
/*     */     
/* 362 */     for (k = list2.size() - 1; k >= 0; k--) {
/* 363 */       BlockPosition blockposition3 = list2.get(k);
/* 364 */       IBlockData iblockdata1 = world.getType(blockposition3);
/* 365 */       TileEntity tileentity = iblockdata1.getBlock().isTileEntity() ? world.getTileEntity(blockposition3) : null;
/*     */       
/* 367 */       a(iblockdata1, world, blockposition3, tileentity);
/* 368 */       world.setTypeAndData(blockposition3, Blocks.AIR.getBlockData(), 18);
/* 369 */       aiblockdata[j++] = iblockdata1;
/*     */     } 
/*     */     
/* 372 */     for (k = list.size() - 1; k >= 0; k--) {
/*     */       
/* 374 */       boolean allowDesync = PaperConfig.allowPistonDuplication;
/* 375 */       BlockPosition blockposition3 = list.get(k), oldPos = blockposition3;
/* 376 */       IBlockData iblockdata1 = allowDesync ? world.getType(oldPos) : null;
/*     */       
/* 378 */       blockposition3 = blockposition3.shift(enumdirection1);
/* 379 */       map.remove(blockposition3);
/* 380 */       world.setTypeAndData(blockposition3, Blocks.MOVING_PISTON.getBlockData().set(FACING, enumdirection), 68);
/*     */       
/* 382 */       if (!allowDesync) {
/* 383 */         iblockdata1 = world.getType(oldPos);
/* 384 */         map.replace(oldPos, iblockdata1);
/*     */       } 
/* 386 */       world.setTileEntity(blockposition3, BlockPistonMoving.a(allowDesync ? list1.get(k) : iblockdata1, enumdirection, flag, false));
/* 387 */       if (!allowDesync) {
/* 388 */         world.setTypeAndData(oldPos, Blocks.AIR.getBlockData(), 1046);
/*     */       }
/*     */       
/* 391 */       aiblockdata[j++] = iblockdata1;
/*     */     } 
/*     */     
/* 394 */     if (flag) {
/* 395 */       BlockPropertyPistonType blockpropertypistontype = this.sticky ? BlockPropertyPistonType.STICKY : BlockPropertyPistonType.DEFAULT;
/* 396 */       IBlockData iblockdata2 = Blocks.PISTON_HEAD.getBlockData().set(BlockPistonExtension.FACING, enumdirection).set(BlockPistonExtension.TYPE, blockpropertypistontype);
/*     */       
/* 398 */       IBlockData iblockdata1 = Blocks.MOVING_PISTON.getBlockData().set(BlockPistonMoving.a, enumdirection).set(BlockPistonMoving.b, this.sticky ? BlockPropertyPistonType.STICKY : BlockPropertyPistonType.DEFAULT);
/* 399 */       map.remove(blockposition1);
/* 400 */       world.setTypeAndData(blockposition1, iblockdata1, 68);
/* 401 */       world.setTileEntity(blockposition1, BlockPistonMoving.a(iblockdata2, enumdirection, true, true));
/*     */     } 
/*     */     
/* 404 */     IBlockData iblockdata3 = Blocks.AIR.getBlockData();
/* 405 */     Iterator<BlockPosition> iterator = map.keySet().iterator();
/*     */     
/* 407 */     while (iterator.hasNext()) {
/* 408 */       BlockPosition blockposition4 = iterator.next();
/*     */       
/* 410 */       world.setTypeAndData(blockposition4, iblockdata3, 82);
/*     */     } 
/*     */     
/* 413 */     iterator = map.entrySet().iterator();
/*     */ 
/*     */ 
/*     */     
/* 417 */     while (iterator.hasNext()) {
/* 418 */       Map.Entry<BlockPosition, IBlockData> entry = (Map.Entry<BlockPosition, IBlockData>)iterator.next();
/*     */       
/* 420 */       BlockPosition blockposition5 = entry.getKey();
/* 421 */       IBlockData iblockdata4 = entry.getValue();
/*     */       
/* 423 */       iblockdata4.b(world, blockposition5, 2);
/* 424 */       iblockdata3.a(world, blockposition5, 2);
/* 425 */       iblockdata3.b(world, blockposition5, 2);
/*     */     } 
/*     */     
/* 428 */     j = 0;
/*     */     
/*     */     int l;
/*     */     
/* 432 */     for (l = list2.size() - 1; l >= 0; l--) {
/* 433 */       IBlockData iblockdata1 = aiblockdata[j++];
/* 434 */       BlockPosition blockposition5 = list2.get(l);
/* 435 */       iblockdata1.b(world, blockposition5, 2);
/* 436 */       world.applyPhysics(blockposition5, iblockdata1.getBlock());
/*     */     } 
/*     */     
/* 439 */     for (l = list.size() - 1; l >= 0; l--) {
/* 440 */       world.applyPhysics(list.get(l), aiblockdata[j++].getBlock());
/*     */     }
/*     */     
/* 443 */     if (flag) {
/* 444 */       world.applyPhysics(blockposition1, Blocks.PISTON_HEAD);
/*     */     }
/*     */     
/* 447 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 453 */     return iblockdata.set(FACING, enumblockrotation.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 458 */     return iblockdata.a(enumblockmirror.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 463 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, EXTENDED });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c_(IBlockData iblockdata) {
/* 468 */     return ((Boolean)iblockdata.get(EXTENDED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 473 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPiston.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */