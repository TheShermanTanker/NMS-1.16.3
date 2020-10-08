/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockMinecartDetector extends BlockMinecartTrackAbstract {
/*  13 */   public static final BlockStateEnum<BlockPropertyTrackPosition> SHAPE = BlockProperties.ad;
/*  14 */   public static final BlockStateBoolean POWERED = BlockProperties.w;
/*     */   
/*     */   public BlockMinecartDetector(BlockBase.Info blockbase_info) {
/*  17 */     super(true, blockbase_info);
/*  18 */     j(((IBlockData)this.blockStateList.getBlockData()).set(POWERED, Boolean.valueOf(false)).set(SHAPE, BlockPropertyTrackPosition.NORTH_SOUTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/*  23 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/*  28 */     if (!world.isClientSide && 
/*  29 */       !((Boolean)iblockdata.get(POWERED)).booleanValue()) {
/*  30 */       a(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  37 */     if (((Boolean)iblockdata.get(POWERED)).booleanValue()) {
/*  38 */       a(worldserver, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  44 */     return ((Boolean)iblockdata.get(POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  49 */     return !((Boolean)iblockdata.get(POWERED)).booleanValue() ? 0 : ((enumdirection == EnumDirection.UP) ? 15 : 0);
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  53 */     if (canPlace(iblockdata, world, blockposition)) {
/*  54 */       if (iblockdata.getBlock() != this)
/*  55 */         return;  boolean flag = ((Boolean)iblockdata.get(POWERED)).booleanValue();
/*  56 */       boolean flag1 = false;
/*  57 */       List<EntityMinecartAbstract> list = a(world, blockposition, EntityMinecartAbstract.class, (Predicate<Entity>)null);
/*     */       
/*  59 */       if (!list.isEmpty()) {
/*  60 */         flag1 = true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  65 */       if (flag != flag1) {
/*  66 */         Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */         
/*  68 */         BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, flag ? 15 : 0, flag1 ? 15 : 0);
/*  69 */         world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */         
/*  71 */         flag1 = (eventRedstone.getNewCurrent() > 0);
/*     */       } 
/*     */ 
/*     */       
/*  75 */       if (flag1 && !flag) {
/*  76 */         IBlockData iblockdata1 = iblockdata.set(POWERED, Boolean.valueOf(true));
/*  77 */         world.setTypeAndData(blockposition, iblockdata1, 3);
/*  78 */         b(world, blockposition, iblockdata1, true);
/*  79 */         world.applyPhysics(blockposition, this);
/*  80 */         world.applyPhysics(blockposition.down(), this);
/*  81 */         world.b(blockposition, iblockdata, iblockdata1);
/*     */       } 
/*     */       
/*  84 */       if (!flag1 && flag) {
/*  85 */         IBlockData iblockdata1 = iblockdata.set(POWERED, Boolean.valueOf(false));
/*  86 */         world.setTypeAndData(blockposition, iblockdata1, 3);
/*  87 */         b(world, blockposition, iblockdata1, false);
/*  88 */         world.applyPhysics(blockposition, this);
/*  89 */         world.applyPhysics(blockposition.down(), this);
/*  90 */         world.b(blockposition, iblockdata, iblockdata1);
/*     */       } 
/*     */       
/*  93 */       if (flag1) {
/*  94 */         world.getBlockTickList().a(blockposition, this, 20);
/*     */       }
/*     */       
/*  97 */       world.updateAdjacentComparators(blockposition, this);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void b(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 102 */     MinecartTrackLogic minecarttracklogic = new MinecartTrackLogic(world, blockposition, iblockdata);
/* 103 */     List<BlockPosition> list = minecarttracklogic.a();
/* 104 */     Iterator<BlockPosition> iterator = list.iterator();
/*     */     
/* 106 */     while (iterator.hasNext()) {
/* 107 */       BlockPosition blockposition1 = iterator.next();
/* 108 */       IBlockData iblockdata1 = world.getType(blockposition1);
/*     */       
/* 110 */       iblockdata1.doPhysics(world, blockposition1, iblockdata1.getBlock(), blockposition, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 117 */     if (!iblockdata1.a(iblockdata.getBlock())) {
/* 118 */       a(world, blockposition, a(iblockdata, world, blockposition, flag));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState<BlockPropertyTrackPosition> d() {
/* 124 */     return SHAPE;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 129 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 134 */     if (((Boolean)iblockdata.get(POWERED)).booleanValue()) {
/* 135 */       List<EntityMinecartCommandBlock> list = a(world, blockposition, EntityMinecartCommandBlock.class, (Predicate<Entity>)null);
/*     */       
/* 137 */       if (!list.isEmpty()) {
/* 138 */         return ((EntityMinecartCommandBlock)list.get(0)).getCommandBlock().i();
/*     */       }
/*     */       
/* 141 */       List<EntityMinecartAbstract> list1 = a(world, blockposition, EntityMinecartAbstract.class, IEntitySelector.d);
/*     */       
/* 143 */       if (!list1.isEmpty()) {
/* 144 */         return Container.b((IInventory)list1.get(0));
/*     */       }
/*     */     } 
/*     */     
/* 148 */     return 0;
/*     */   }
/*     */   
/*     */   protected <T extends EntityMinecartAbstract> List<T> a(World world, BlockPosition blockposition, Class<T> oclass, @Nullable Predicate<Entity> predicate) {
/* 152 */     return (List)world.a((Class)oclass, a(blockposition), predicate);
/*     */   }
/*     */   
/*     */   private AxisAlignedBB a(BlockPosition blockposition) {
/* 156 */     double d0 = 0.2D;
/*     */     
/* 158 */     return new AxisAlignedBB(blockposition.getX() + 0.2D, blockposition.getY(), blockposition.getZ() + 0.2D, (blockposition.getX() + 1) - 0.2D, (blockposition.getY() + 1) - 0.2D, (blockposition.getZ() + 1) - 0.2D);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 163 */     switch (enumblockrotation) {
/*     */       case LEFT_RIGHT:
/* 165 */         switch ((BlockPropertyTrackPosition)iblockdata.get(SHAPE)) {
/*     */           case LEFT_RIGHT:
/* 167 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case FRONT_BACK:
/* 169 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           case null:
/* 171 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case null:
/* 173 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case null:
/* 175 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */           case null:
/* 177 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case null:
/* 179 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */           case null:
/* 181 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */         } 
/*     */       case FRONT_BACK:
/* 184 */         switch ((BlockPropertyTrackPosition)iblockdata.get(SHAPE)) {
/*     */           case LEFT_RIGHT:
/* 186 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case FRONT_BACK:
/* 188 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case null:
/* 190 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case null:
/* 192 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           case null:
/* 194 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case null:
/* 196 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */           case null:
/* 198 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case null:
/* 200 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */           case null:
/* 202 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.EAST_WEST);
/*     */           case null:
/* 204 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_SOUTH);
/*     */         } 
/*     */       case null:
/* 207 */         switch ((BlockPropertyTrackPosition)iblockdata.get(SHAPE)) {
/*     */           case LEFT_RIGHT:
/* 209 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case FRONT_BACK:
/* 211 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case null:
/* 213 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           case null:
/* 215 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case null:
/* 217 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case null:
/* 219 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */           case null:
/* 221 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case null:
/* 223 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */           case null:
/* 225 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.EAST_WEST);
/*     */           case null:
/* 227 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_SOUTH);
/*     */         }  break;
/*     */     } 
/* 230 */     return iblockdata;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 236 */     BlockPropertyTrackPosition blockpropertytrackposition = (BlockPropertyTrackPosition)iblockdata.get(SHAPE);
/*     */     
/* 238 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 240 */         switch (blockpropertytrackposition) {
/*     */           case null:
/* 242 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case null:
/* 244 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case null:
/* 246 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case null:
/* 248 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */           case null:
/* 250 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case null:
/* 252 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */         } 
/* 254 */         return super.a(iblockdata, enumblockmirror);
/*     */       
/*     */       case FRONT_BACK:
/* 257 */         switch (blockpropertytrackposition)
/*     */         { case LEFT_RIGHT:
/* 259 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case FRONT_BACK:
/* 261 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           
/*     */           default:
/*     */             break;
/*     */           
/*     */           case null:
/* 267 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case null:
/* 269 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */           case null:
/* 271 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case null:
/* 273 */             break; }  return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */     } 
/*     */ 
/*     */     
/* 277 */     return super.a(iblockdata, enumblockmirror);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 282 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { SHAPE, POWERED });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockMinecartDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */