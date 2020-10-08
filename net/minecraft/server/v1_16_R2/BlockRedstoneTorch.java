/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class BlockRedstoneTorch
/*     */   extends BlockTorch {
/*  13 */   public static final BlockStateBoolean LIT = BlockProperties.r;
/*     */ 
/*     */   
/*     */   protected BlockRedstoneTorch(BlockBase.Info blockbase_info) {
/*  17 */     super(blockbase_info, ParticleParamRedstone.a);
/*  18 */     j(((IBlockData)this.blockStateList.getBlockData()).set(LIT, Boolean.valueOf(true)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  23 */     EnumDirection[] aenumdirection = EnumDirection.values();
/*  24 */     int i = aenumdirection.length;
/*     */     
/*  26 */     for (int j = 0; j < i; j++) {
/*  27 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/*  29 */       world.applyPhysics(blockposition.shift(enumdirection), this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  36 */     if (!flag) {
/*  37 */       EnumDirection[] aenumdirection = EnumDirection.values();
/*  38 */       int i = aenumdirection.length;
/*     */       
/*  40 */       for (int j = 0; j < i; j++) {
/*  41 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/*  43 */         world.applyPhysics(blockposition.shift(enumdirection), this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  51 */     return (((Boolean)iblockdata.get(LIT)).booleanValue() && EnumDirection.UP != enumdirection) ? 15 : 0;
/*     */   }
/*     */   
/*     */   protected boolean a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  55 */     return world.isBlockFacePowered(blockposition.down(), EnumDirection.DOWN);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  60 */     boolean flag = a(worldserver, blockposition, iblockdata);
/*     */     
/*  62 */     ArrayDeque<RedstoneUpdateInfo> redstoneUpdateInfos = worldserver.redstoneUpdateInfos;
/*  63 */     if (redstoneUpdateInfos != null) {
/*     */       RedstoneUpdateInfo curr;
/*  65 */       while ((curr = redstoneUpdateInfos.peek()) != null && worldserver.getTime() - curr.getTime() > 60L) {
/*  66 */         redstoneUpdateInfos.poll();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  72 */     PluginManager manager = worldserver.getServer().getPluginManager();
/*  73 */     Block block = worldserver.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  74 */     int oldCurrent = ((Boolean)iblockdata.get(LIT)).booleanValue() ? 15 : 0;
/*     */     
/*  76 */     BlockRedstoneEvent event = new BlockRedstoneEvent(block, oldCurrent, oldCurrent);
/*     */     
/*  78 */     if (((Boolean)iblockdata.get(LIT)).booleanValue()) {
/*  79 */       if (flag) {
/*     */         
/*  81 */         if (oldCurrent != 0) {
/*  82 */           event.setNewCurrent(0);
/*  83 */           manager.callEvent((Event)event);
/*  84 */           if (event.getNewCurrent() != 0) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */         
/*  89 */         worldserver.setTypeAndData(blockposition, iblockdata.set(LIT, Boolean.valueOf(false)), 3);
/*  90 */         if (a(worldserver, blockposition, true)) {
/*  91 */           worldserver.triggerEffect(1502, blockposition, 0);
/*  92 */           worldserver.getBlockTickList().a(blockposition, worldserver.getType(blockposition).getBlock(), 160);
/*     */         } 
/*     */       } 
/*  95 */     } else if (!flag && !a(worldserver, blockposition, false)) {
/*     */       
/*  97 */       if (oldCurrent != 15) {
/*  98 */         event.setNewCurrent(15);
/*  99 */         manager.callEvent((Event)event);
/* 100 */         if (event.getNewCurrent() != 15) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/* 105 */       worldserver.setTypeAndData(blockposition, iblockdata.set(LIT, Boolean.valueOf(true)), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/* 112 */     if (((Boolean)iblockdata.get(LIT)).booleanValue() == a(world, blockposition, iblockdata) && !world.getBlockTickList().b(blockposition, this)) {
/* 113 */       world.getBlockTickList().a(blockposition, this, 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 120 */     return (enumdirection == EnumDirection.DOWN) ? iblockdata.b(iblockaccess, blockposition, enumdirection) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowerSource(IBlockData iblockdata) {
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 130 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { LIT });
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(World world, BlockPosition blockposition, boolean flag) {
/* 135 */     ArrayDeque<RedstoneUpdateInfo> list = world.redstoneUpdateInfos;
/* 136 */     if (list == null) {
/* 137 */       list = world.redstoneUpdateInfos = new ArrayDeque<>();
/*     */     }
/*     */ 
/*     */     
/* 141 */     if (flag) {
/* 142 */       list.add(new RedstoneUpdateInfo(blockposition.immutableCopy(), world.getTime()));
/*     */     }
/*     */     
/* 145 */     int i = 0;
/*     */     
/* 147 */     for (Iterator<RedstoneUpdateInfo> iterator = list.iterator(); iterator.hasNext(); ) {
/* 148 */       RedstoneUpdateInfo blockredstonetorch_redstoneupdateinfo = iterator.next();
/*     */ 
/*     */       
/* 151 */       i++;
/* 152 */       if (blockredstonetorch_redstoneupdateinfo.a.equals(blockposition) && i >= 8) {
/* 153 */         return true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 158 */     return false;
/*     */   }
/*     */   
/*     */   public static class RedstoneUpdateInfo { private final BlockPosition a;
/*     */     
/*     */     final long getTime() {
/* 164 */       return this.b;
/*     */     } private final long b;
/*     */     public RedstoneUpdateInfo(BlockPosition blockposition, long i) {
/* 167 */       this.a = blockposition;
/* 168 */       this.b = i;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockRedstoneTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */