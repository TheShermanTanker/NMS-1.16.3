/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class BlockTripwire extends Block {
/*  12 */   public static final BlockStateBoolean POWERED = BlockProperties.w;
/*  13 */   public static final BlockStateBoolean ATTACHED = BlockProperties.a;
/*  14 */   public static final BlockStateBoolean DISARMED = BlockProperties.d;
/*  15 */   public static final BlockStateBoolean NORTH = BlockSprawling.a;
/*  16 */   public static final BlockStateBoolean EAST = BlockSprawling.b;
/*  17 */   public static final BlockStateBoolean SOUTH = BlockSprawling.c;
/*  18 */   public static final BlockStateBoolean WEST = BlockSprawling.d;
/*  19 */   private static final Map<EnumDirection, BlockStateBoolean> j = BlockTall.f;
/*  20 */   protected static final VoxelShape h = Block.a(0.0D, 1.0D, 0.0D, 16.0D, 2.5D, 16.0D);
/*  21 */   protected static final VoxelShape i = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
/*     */   private final BlockTripwireHook k;
/*     */   
/*     */   public BlockTripwire(BlockTripwireHook blocktripwirehook, BlockBase.Info blockbase_info) {
/*  25 */     super(blockbase_info);
/*  26 */     j(((IBlockData)this.blockStateList.getBlockData()).set(POWERED, Boolean.valueOf(false)).set(ATTACHED, Boolean.valueOf(false)).set(DISARMED, Boolean.valueOf(false)).set(NORTH, Boolean.valueOf(false)).set(EAST, Boolean.valueOf(false)).set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false)));
/*  27 */     this.k = blocktripwirehook;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  32 */     return ((Boolean)iblockdata.get(ATTACHED)).booleanValue() ? h : i;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  37 */     World world = blockactioncontext.getWorld();
/*  38 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/*     */     
/*  40 */     return getBlockData().set(NORTH, Boolean.valueOf(a(world.getType(blockposition.north()), EnumDirection.NORTH))).set(EAST, Boolean.valueOf(a(world.getType(blockposition.east()), EnumDirection.EAST))).set(SOUTH, Boolean.valueOf(a(world.getType(blockposition.south()), EnumDirection.SOUTH))).set(WEST, Boolean.valueOf(a(world.getType(blockposition.west()), EnumDirection.WEST)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  45 */     return enumdirection.n().d() ? iblockdata.set(j.get(enumdirection), Boolean.valueOf(a(iblockdata1, enumdirection))) : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  50 */     if (!iblockdata1.a(iblockdata.getBlock())) {
/*  51 */       a(world, blockposition, iblockdata);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  57 */     if (!flag && !iblockdata.a(iblockdata1.getBlock())) {
/*  58 */       a(world, blockposition, iblockdata.set(POWERED, Boolean.valueOf(true)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/*  64 */     if (!world.isClientSide && !entityhuman.getItemInMainHand().isEmpty() && entityhuman.getItemInMainHand().getItem() == Items.SHEARS) {
/*  65 */       world.setTypeAndData(blockposition, iblockdata.set(DISARMED, Boolean.valueOf(true)), 4);
/*     */     }
/*     */     
/*  68 */     super.a(world, blockposition, iblockdata, entityhuman);
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  72 */     EnumDirection[] aenumdirection = { EnumDirection.SOUTH, EnumDirection.WEST };
/*  73 */     int i = aenumdirection.length;
/*  74 */     int j = 0;
/*     */     
/*  76 */     while (j < i) {
/*  77 */       EnumDirection enumdirection = aenumdirection[j];
/*  78 */       int k = 1;
/*     */ 
/*     */       
/*  81 */       while (k < 42) {
/*  82 */         BlockPosition blockposition1 = blockposition.shift(enumdirection, k);
/*  83 */         IBlockData iblockdata1 = world.getType(blockposition1);
/*     */         
/*  85 */         if (iblockdata1.a(this.k)) {
/*  86 */           if (iblockdata1.get(BlockTripwireHook.FACING) == enumdirection.opposite())
/*  87 */             this.k.a(world, blockposition1, iblockdata1, false, true, k, iblockdata);  break;
/*     */         } 
/*  89 */         if (iblockdata1.a(this)) {
/*  90 */           k++;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  95 */       j++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/* 104 */     if (!world.isClientSide && 
/* 105 */       !((Boolean)iblockdata.get(POWERED)).booleanValue()) {
/* 106 */       a(world, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 113 */     if (((Boolean)worldserver.getType(blockposition).get(POWERED)).booleanValue()) {
/* 114 */       a(worldserver, blockposition);
/*     */     }
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition) {
/* 119 */     IBlockData iblockdata = world.getType(blockposition);
/* 120 */     boolean flag = ((Boolean)iblockdata.get(POWERED)).booleanValue();
/* 121 */     boolean flag1 = false;
/* 122 */     List<? extends Entity> list = world.getEntities((Entity)null, iblockdata.getShape(world, blockposition).getBoundingBox().a(blockposition));
/*     */     
/* 124 */     if (!list.isEmpty()) {
/* 125 */       Iterator<? extends Entity> iterator = list.iterator();
/*     */       
/* 127 */       while (iterator.hasNext()) {
/* 128 */         Entity entity = iterator.next();
/*     */         
/* 130 */         if (!entity.isIgnoreBlockTrigger()) {
/* 131 */           flag1 = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 138 */     if (flag != flag1 && flag1 && ((Boolean)iblockdata.get(ATTACHED)).booleanValue()) {
/* 139 */       CraftWorld craftWorld = world.getWorld();
/* 140 */       PluginManager manager = world.getServer().getPluginManager();
/* 141 */       Block block = craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 142 */       boolean allowed = false;
/*     */ 
/*     */       
/* 145 */       for (Entity object : list) {
/* 146 */         if (object != null) {
/*     */           EntityInteractEvent entityInteractEvent;
/*     */           
/* 149 */           if (object instanceof EntityHuman) {
/* 150 */             PlayerInteractEvent playerInteractEvent = CraftEventFactory.callPlayerInteractEvent((EntityHuman)object, Action.PHYSICAL, blockposition, null, null, null);
/* 151 */           } else if (object instanceof Entity) {
/* 152 */             entityInteractEvent = new EntityInteractEvent((Entity)((Entity)object).getBukkitEntity(), block);
/* 153 */             manager.callEvent((Event)entityInteractEvent);
/*     */           } else {
/*     */             continue;
/*     */           } 
/*     */           
/* 158 */           if (!entityInteractEvent.isCancelled()) {
/* 159 */             allowed = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 165 */       if (!allowed) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 171 */     if (flag1 != flag) {
/* 172 */       iblockdata = iblockdata.set(POWERED, Boolean.valueOf(flag1));
/* 173 */       world.setTypeAndData(blockposition, iblockdata, 3);
/* 174 */       a(world, blockposition, iblockdata);
/*     */     } 
/*     */     
/* 177 */     if (flag1) {
/* 178 */       world.getBlockTickList().a(new BlockPosition(blockposition), this, 10);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, EnumDirection enumdirection) {
/* 184 */     Block block = iblockdata.getBlock();
/*     */     
/* 186 */     return (block == this.k) ? ((iblockdata.get(BlockTripwireHook.FACING) == enumdirection.opposite())) : ((block == this));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 191 */     switch (enumblockrotation) {
/*     */       case LEFT_RIGHT:
/* 193 */         return iblockdata.set(NORTH, iblockdata.get(SOUTH)).set(EAST, iblockdata.get(WEST)).set(SOUTH, iblockdata.get(NORTH)).set(WEST, iblockdata.get(EAST));
/*     */       case FRONT_BACK:
/* 195 */         return iblockdata.set(NORTH, iblockdata.get(EAST)).set(EAST, iblockdata.get(SOUTH)).set(SOUTH, iblockdata.get(WEST)).set(WEST, iblockdata.get(NORTH));
/*     */       case null:
/* 197 */         return iblockdata.set(NORTH, iblockdata.get(WEST)).set(EAST, iblockdata.get(NORTH)).set(SOUTH, iblockdata.get(EAST)).set(WEST, iblockdata.get(SOUTH));
/*     */     } 
/* 199 */     return iblockdata;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 205 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 207 */         return iblockdata.set(NORTH, iblockdata.get(SOUTH)).set(SOUTH, iblockdata.get(NORTH));
/*     */       case FRONT_BACK:
/* 209 */         return iblockdata.set(EAST, iblockdata.get(WEST)).set(WEST, iblockdata.get(EAST));
/*     */     } 
/* 211 */     return super.a(iblockdata, enumblockmirror);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 217 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { POWERED, ATTACHED, DISARMED, NORTH, EAST, WEST, SOUTH });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTripwire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */