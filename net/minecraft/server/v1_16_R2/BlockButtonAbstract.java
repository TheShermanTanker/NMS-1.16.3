/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ 
/*     */ public abstract class BlockButtonAbstract
/*     */   extends BlockAttachable {
/*  14 */   public static final BlockStateBoolean POWERED = BlockProperties.w;
/*  15 */   protected static final VoxelShape b = Block.a(6.0D, 14.0D, 5.0D, 10.0D, 16.0D, 11.0D);
/*  16 */   protected static final VoxelShape c = Block.a(5.0D, 14.0D, 6.0D, 11.0D, 16.0D, 10.0D);
/*  17 */   protected static final VoxelShape d = Block.a(6.0D, 0.0D, 5.0D, 10.0D, 2.0D, 11.0D);
/*  18 */   protected static final VoxelShape e = Block.a(5.0D, 0.0D, 6.0D, 11.0D, 2.0D, 10.0D);
/*  19 */   protected static final VoxelShape f = Block.a(5.0D, 6.0D, 14.0D, 11.0D, 10.0D, 16.0D);
/*  20 */   protected static final VoxelShape g = Block.a(5.0D, 6.0D, 0.0D, 11.0D, 10.0D, 2.0D);
/*  21 */   protected static final VoxelShape h = Block.a(14.0D, 6.0D, 5.0D, 16.0D, 10.0D, 11.0D);
/*  22 */   protected static final VoxelShape i = Block.a(0.0D, 6.0D, 5.0D, 2.0D, 10.0D, 11.0D);
/*  23 */   protected static final VoxelShape j = Block.a(6.0D, 15.0D, 5.0D, 10.0D, 16.0D, 11.0D);
/*  24 */   protected static final VoxelShape k = Block.a(5.0D, 15.0D, 6.0D, 11.0D, 16.0D, 10.0D);
/*  25 */   protected static final VoxelShape o = Block.a(6.0D, 0.0D, 5.0D, 10.0D, 1.0D, 11.0D);
/*  26 */   protected static final VoxelShape p = Block.a(5.0D, 0.0D, 6.0D, 11.0D, 1.0D, 10.0D);
/*  27 */   protected static final VoxelShape q = Block.a(5.0D, 6.0D, 15.0D, 11.0D, 10.0D, 16.0D);
/*  28 */   protected static final VoxelShape r = Block.a(5.0D, 6.0D, 0.0D, 11.0D, 10.0D, 1.0D);
/*  29 */   protected static final VoxelShape s = Block.a(15.0D, 6.0D, 5.0D, 16.0D, 10.0D, 11.0D);
/*  30 */   protected static final VoxelShape t = Block.a(0.0D, 6.0D, 5.0D, 1.0D, 10.0D, 11.0D);
/*     */   private final boolean v;
/*     */   
/*     */   protected BlockButtonAbstract(boolean flag, BlockBase.Info blockbase_info) {
/*  34 */     super(blockbase_info);
/*  35 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(POWERED, Boolean.valueOf(false)).set(FACE, BlockPropertyAttachPosition.WALL));
/*  36 */     this.v = flag;
/*     */   }
/*     */   
/*     */   private int c() {
/*  40 */     return this.v ? 30 : 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  45 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/*  46 */     boolean flag = ((Boolean)iblockdata.get(POWERED)).booleanValue();
/*     */     
/*  48 */     switch ((BlockPropertyAttachPosition)iblockdata.get(FACE)) {
/*     */       case FLOOR:
/*  50 */         if (enumdirection.n() == EnumDirection.EnumAxis.X) {
/*  51 */           return flag ? o : d;
/*     */         }
/*     */         
/*  54 */         return flag ? p : e;
/*     */       case WALL:
/*  56 */         switch (enumdirection) {
/*     */           case FLOOR:
/*  58 */             return flag ? t : i;
/*     */           case WALL:
/*  60 */             return flag ? s : h;
/*     */           case CEILING:
/*  62 */             return flag ? r : g;
/*     */         } 
/*     */         
/*  65 */         return flag ? q : f;
/*     */     } 
/*     */ 
/*     */     
/*  69 */     return (enumdirection.n() == EnumDirection.EnumAxis.X) ? (flag ? j : b) : (flag ? k : c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/*  75 */     if (((Boolean)iblockdata.get(POWERED)).booleanValue()) {
/*  76 */       return EnumInteractionResult.CONSUME;
/*     */     }
/*     */     
/*  79 */     boolean powered = ((Boolean)iblockdata.get(POWERED)).booleanValue();
/*  80 */     Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  81 */     int old = powered ? 15 : 0;
/*  82 */     int current = !powered ? 15 : 0;
/*     */     
/*  84 */     BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, old, current);
/*  85 */     world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */     
/*  87 */     if (((eventRedstone.getNewCurrent() > 0) ? true : false) != (!powered ? true : false)) {
/*  88 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  91 */     d(iblockdata, world, blockposition);
/*  92 */     a(entityhuman, world, blockposition, true);
/*  93 */     return EnumInteractionResult.a(world.isClientSide);
/*     */   }
/*     */ 
/*     */   
/*     */   public void d(IBlockData iblockdata, World world, BlockPosition blockposition) {
/*  98 */     world.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(true)), 3);
/*  99 */     f(iblockdata, world, blockposition);
/* 100 */     world.getBlockTickList().a(blockposition, this, c());
/*     */   }
/*     */   
/*     */   protected void a(@Nullable EntityHuman entityhuman, GeneratorAccess generatoraccess, BlockPosition blockposition, boolean flag) {
/* 104 */     generatoraccess.playSound(flag ? entityhuman : null, blockposition, a(flag), SoundCategory.BLOCKS, 0.3F, flag ? 0.6F : 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract SoundEffect a(boolean paramBoolean);
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 111 */     if (!flag && !iblockdata.a(iblockdata1.getBlock())) {
/* 112 */       if (((Boolean)iblockdata.get(POWERED)).booleanValue()) {
/* 113 */         f(iblockdata, world, blockposition);
/*     */       }
/*     */       
/* 116 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 122 */     return ((Boolean)iblockdata.get(POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 127 */     return (((Boolean)iblockdata.get(POWERED)).booleanValue() && h(iblockdata) == enumdirection) ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 132 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 137 */     if (((Boolean)iblockdata.get(POWERED)).booleanValue()) {
/* 138 */       if (this.v) {
/* 139 */         e(iblockdata, worldserver, blockposition);
/*     */       } else {
/*     */         
/* 142 */         Block block = worldserver.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */         
/* 144 */         BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, 15, 0);
/* 145 */         worldserver.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */         
/* 147 */         if (eventRedstone.getNewCurrent() > 0) {
/*     */           return;
/*     */         }
/*     */         
/* 151 */         worldserver.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(false)), 3);
/* 152 */         f(iblockdata, worldserver, blockposition);
/* 153 */         a((EntityHuman)null, worldserver, blockposition, false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/* 161 */     if (!world.isClientSide && this.v && !((Boolean)iblockdata.get(POWERED)).booleanValue()) {
/* 162 */       e(iblockdata, world, blockposition);
/*     */     }
/*     */   }
/*     */   
/*     */   private void e(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 167 */     List<? extends Entity> list = world.a((Class)EntityArrow.class, iblockdata.getShape(world, blockposition).getBoundingBox().a(blockposition));
/* 168 */     boolean flag = !list.isEmpty();
/* 169 */     boolean flag1 = ((Boolean)iblockdata.get(POWERED)).booleanValue();
/*     */ 
/*     */     
/* 172 */     if (flag1 != flag && flag) {
/* 173 */       Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 174 */       boolean allowed = false;
/*     */ 
/*     */       
/* 177 */       for (Entity object : list) {
/* 178 */         if (object != null) {
/* 179 */           EntityInteractEvent event = new EntityInteractEvent((Entity)((Entity)object).getBukkitEntity(), block);
/* 180 */           world.getServer().getPluginManager().callEvent((Event)event);
/*     */           
/* 182 */           if (!event.isCancelled()) {
/* 183 */             allowed = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 189 */       if (!allowed) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 195 */     if (flag != flag1) {
/*     */       
/* 197 */       boolean powered = flag1;
/* 198 */       Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 199 */       int old = powered ? 15 : 0;
/* 200 */       int current = !powered ? 15 : 0;
/*     */       
/* 202 */       BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, old, current);
/* 203 */       world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */       
/* 205 */       if ((flag && eventRedstone.getNewCurrent() <= 0) || (!flag && eventRedstone.getNewCurrent() > 0)) {
/*     */         return;
/*     */       }
/*     */       
/* 209 */       world.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(flag)), 3);
/* 210 */       f(iblockdata, world, blockposition);
/* 211 */       a((EntityHuman)null, world, blockposition, flag);
/*     */     } 
/*     */     
/* 214 */     if (flag) {
/* 215 */       world.getBlockTickList().a(new BlockPosition(blockposition), this, c());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void f(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 221 */     world.applyPhysics(blockposition, this);
/* 222 */     world.applyPhysics(blockposition.shift(h(iblockdata).opposite()), this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 227 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, POWERED, FACE });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockButtonAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */