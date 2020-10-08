/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockTripwireHook extends Block {
/*  11 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  12 */   public static final BlockStateBoolean POWERED = BlockProperties.w;
/*  13 */   public static final BlockStateBoolean ATTACHED = BlockProperties.a;
/*  14 */   protected static final VoxelShape d = Block.a(5.0D, 0.0D, 10.0D, 11.0D, 10.0D, 16.0D);
/*  15 */   protected static final VoxelShape e = Block.a(5.0D, 0.0D, 0.0D, 11.0D, 10.0D, 6.0D);
/*  16 */   protected static final VoxelShape f = Block.a(10.0D, 0.0D, 5.0D, 16.0D, 10.0D, 11.0D);
/*  17 */   protected static final VoxelShape g = Block.a(0.0D, 0.0D, 5.0D, 6.0D, 10.0D, 11.0D);
/*     */   
/*     */   public BlockTripwireHook(BlockBase.Info blockbase_info) {
/*  20 */     super(blockbase_info);
/*  21 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(POWERED, Boolean.valueOf(false)).set(ATTACHED, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  26 */     switch ((EnumDirection)iblockdata.get(FACING))
/*     */     
/*     */     { default:
/*  29 */         return g;
/*     */       case WEST:
/*  31 */         return f;
/*     */       case SOUTH:
/*  33 */         return e;
/*     */       case NORTH:
/*  35 */         break; }  return d;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*  41 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/*  42 */     BlockPosition blockposition1 = blockposition.shift(enumdirection.opposite());
/*  43 */     IBlockData iblockdata1 = iworldreader.getType(blockposition1);
/*     */     
/*  45 */     return (enumdirection.n().d() && iblockdata1.d(iworldreader, blockposition1, enumdirection));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  50 */     return (enumdirection.opposite() == iblockdata.get(FACING) && !iblockdata.canPlace(generatoraccess, blockposition)) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  56 */     IBlockData iblockdata = getBlockData().set(POWERED, Boolean.valueOf(false)).set(ATTACHED, Boolean.valueOf(false));
/*  57 */     World world = blockactioncontext.getWorld();
/*  58 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/*  59 */     EnumDirection[] aenumdirection = blockactioncontext.e();
/*  60 */     EnumDirection[] aenumdirection1 = aenumdirection;
/*  61 */     int i = aenumdirection.length;
/*     */     
/*  63 */     for (int j = 0; j < i; j++) {
/*  64 */       EnumDirection enumdirection = aenumdirection1[j];
/*     */       
/*  66 */       if (enumdirection.n().d()) {
/*  67 */         EnumDirection enumdirection1 = enumdirection.opposite();
/*     */         
/*  69 */         iblockdata = iblockdata.set(FACING, enumdirection1);
/*  70 */         if (iblockdata.canPlace(world, blockposition)) {
/*  71 */           return iblockdata;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/*  81 */     a(world, blockposition, iblockdata, false, false, -1, (IBlockData)null);
/*     */   }
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag, boolean flag1, int i, @Nullable IBlockData iblockdata1) {
/*     */     int n;
/*  85 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(FACING);
/*  86 */     int flag2 = ((Boolean)iblockdata.get(ATTACHED)).booleanValue();
/*  87 */     boolean flag3 = ((Boolean)iblockdata.get(POWERED)).booleanValue();
/*  88 */     boolean flag4 = !flag;
/*  89 */     boolean flag5 = false;
/*  90 */     int j = 0;
/*  91 */     IBlockData[] aiblockdata = new IBlockData[42];
/*     */ 
/*     */ 
/*     */     
/*  95 */     for (int k = 1; k < 42; k++) {
/*  96 */       BlockPosition blockposition1 = blockposition.shift(enumdirection, k);
/*  97 */       IBlockData iblockdata2 = world.getType(blockposition1);
/*     */       
/*  99 */       if (iblockdata2.a(Blocks.TRIPWIRE_HOOK)) {
/* 100 */         if (iblockdata2.get(FACING) == enumdirection.opposite()) {
/* 101 */           j = k;
/*     */         }
/*     */         
/*     */         break;
/*     */       } 
/* 106 */       if (!iblockdata2.a(Blocks.TRIPWIRE) && k != i) {
/* 107 */         aiblockdata[k] = null;
/* 108 */         flag4 = false;
/*     */       } else {
/* 110 */         if (k == i) {
/* 111 */           iblockdata2 = (IBlockData)MoreObjects.firstNonNull(iblockdata1, iblockdata2);
/*     */         }
/*     */         
/* 114 */         boolean flag6 = !((Boolean)iblockdata2.get(BlockTripwire.DISARMED)).booleanValue();
/* 115 */         boolean flag7 = ((Boolean)iblockdata2.get(BlockTripwire.POWERED)).booleanValue();
/*     */         
/* 117 */         n = flag5 | ((flag6 && flag7) ? 1 : 0);
/* 118 */         aiblockdata[k] = iblockdata2;
/* 119 */         if (k == i) {
/* 120 */           world.getBlockTickList().a(blockposition, this, 10);
/* 121 */           flag4 &= flag6;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     int m = flag4 & ((j > 1) ? 1 : 0);
/* 127 */     n &= m;
/* 128 */     IBlockData iblockdata3 = getBlockData().set(ATTACHED, Boolean.valueOf(m)).set(POWERED, Boolean.valueOf(n));
/*     */     
/* 130 */     if (j > 0) {
/* 131 */       BlockPosition blockposition1 = blockposition.shift(enumdirection, j);
/* 132 */       EnumDirection enumdirection1 = enumdirection.opposite();
/*     */       
/* 134 */       world.setTypeAndData(blockposition1, iblockdata3.set(FACING, enumdirection1), 3);
/* 135 */       a(world, blockposition1, enumdirection1);
/* 136 */       a(world, blockposition1, m, n, flag2, flag3);
/*     */     } 
/*     */ 
/*     */     
/* 140 */     Block block = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */     
/* 142 */     BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, 15, 0);
/* 143 */     world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*     */     
/* 145 */     if (eventRedstone.getNewCurrent() > 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 150 */     a(world, blockposition, m, n, flag2, flag3);
/* 151 */     if (!flag) {
/* 152 */       if (world.getType(blockposition).getBlock() == Blocks.TRIPWIRE_HOOK)
/* 153 */         world.setTypeAndData(blockposition, iblockdata3.set(FACING, enumdirection), 3); 
/* 154 */       if (flag1) {
/* 155 */         a(world, blockposition, enumdirection);
/*     */       }
/*     */     } 
/*     */     
/* 159 */     if (flag2 != m) {
/* 160 */       for (int l = 1; l < j; l++) {
/* 161 */         BlockPosition blockposition2 = blockposition.shift(enumdirection, l);
/* 162 */         IBlockData iblockdata4 = aiblockdata[l];
/*     */         
/* 164 */         if (iblockdata4 != null) {
/* 165 */           world.setTypeAndData(blockposition2, iblockdata4.set(ATTACHED, Boolean.valueOf(m)), 3);
/* 166 */           if (!world.getType(blockposition2).isAir());
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 177 */     a(worldserver, blockposition, iblockdata, false, true, -1, (IBlockData)null);
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, boolean flag, boolean flag1, boolean flag2, boolean flag3) {
/* 181 */     if (flag1 && !flag3) {
/* 182 */       world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_TRIPWIRE_CLICK_ON, SoundCategory.BLOCKS, 0.4F, 0.6F);
/* 183 */     } else if (!flag1 && flag3) {
/* 184 */       world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_TRIPWIRE_CLICK_OFF, SoundCategory.BLOCKS, 0.4F, 0.5F);
/* 185 */     } else if (flag && !flag2) {
/* 186 */       world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_TRIPWIRE_ATTACH, SoundCategory.BLOCKS, 0.4F, 0.7F);
/* 187 */     } else if (!flag && flag2) {
/* 188 */       world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_TRIPWIRE_DETACH, SoundCategory.BLOCKS, 0.4F, 1.2F / (world.random.nextFloat() * 0.2F + 0.9F));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/* 194 */     world.applyPhysics(blockposition, this);
/* 195 */     world.applyPhysics(blockposition.shift(enumdirection.opposite()), this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 200 */     if (!flag && !iblockdata.a(iblockdata1.getBlock())) {
/* 201 */       boolean flag1 = ((Boolean)iblockdata.get(ATTACHED)).booleanValue();
/* 202 */       boolean flag2 = ((Boolean)iblockdata.get(POWERED)).booleanValue();
/*     */       
/* 204 */       if (flag1 || flag2) {
/* 205 */         a(world, blockposition, iblockdata, true, false, -1, (IBlockData)null);
/*     */       }
/*     */       
/* 208 */       if (flag2) {
/* 209 */         world.applyPhysics(blockposition, this);
/* 210 */         world.applyPhysics(blockposition.shift(((EnumDirection)iblockdata.get(FACING)).opposite()), this);
/*     */       } 
/*     */       
/* 213 */       super.remove(iblockdata, world, blockposition, iblockdata1, flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 219 */     return ((Boolean)iblockdata.get(POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 224 */     return !((Boolean)iblockdata.get(POWERED)).booleanValue() ? 0 : ((iblockdata.get(FACING) == enumdirection) ? 15 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 229 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 234 */     return iblockdata.set(FACING, enumblockrotation.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 239 */     return iblockdata.a(enumblockmirror.a((EnumDirection)iblockdata.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 244 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FACING, POWERED, ATTACHED });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTripwireHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */