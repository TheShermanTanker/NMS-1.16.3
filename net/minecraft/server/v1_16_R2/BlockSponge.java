/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.BlockStateListPopulator;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.SpongeAbsorbEvent;
/*     */ 
/*     */ public class BlockSponge
/*     */   extends Block {
/*     */   protected BlockSponge(BlockBase.Info blockbase_info) {
/*  15 */     super(blockbase_info);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  20 */     if (!iblockdata1.a(iblockdata.getBlock())) {
/*  21 */       a(world, blockposition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/*  27 */     a(world, blockposition);
/*  28 */     super.doPhysics(iblockdata, world, blockposition, block, blockposition1, flag);
/*     */   }
/*     */   
/*     */   protected void a(World world, BlockPosition blockposition) {
/*  32 */     if (b(world, blockposition)) {
/*  33 */       world.setTypeAndData(blockposition, Blocks.WET_SPONGE.getBlockData(), 2);
/*  34 */       world.triggerEffect(2001, blockposition, Block.getCombinedId(Blocks.WATER.getBlockData()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean b(World world, BlockPosition blockposition) {
/*  40 */     Queue<Tuple<BlockPosition, Integer>> queue = Lists.newLinkedList();
/*     */     
/*  42 */     queue.add(new Tuple<>(blockposition, Integer.valueOf(0)));
/*  43 */     int i = 0;
/*  44 */     BlockStateListPopulator blockList = new BlockStateListPopulator(world);
/*     */     
/*  46 */     while (!queue.isEmpty()) {
/*  47 */       Tuple<BlockPosition, Integer> tuple = queue.poll();
/*  48 */       BlockPosition blockposition1 = tuple.a();
/*  49 */       int j = ((Integer)tuple.b()).intValue();
/*  50 */       EnumDirection[] aenumdirection = EnumDirection.values();
/*  51 */       int k = aenumdirection.length;
/*     */       
/*  53 */       for (int l = 0; l < k; l++) {
/*  54 */         EnumDirection enumdirection = aenumdirection[l];
/*  55 */         BlockPosition blockposition2 = blockposition1.shift(enumdirection);
/*     */         
/*  57 */         IBlockData iblockdata = blockList.getType(blockposition2);
/*  58 */         Fluid fluid = blockList.getFluid(blockposition2);
/*     */         
/*  60 */         Material material = iblockdata.getMaterial();
/*     */         
/*  62 */         if (fluid.a(TagsFluid.WATER)) {
/*  63 */           if (iblockdata.getBlock() instanceof IFluidSource && ((IFluidSource)iblockdata.getBlock()).removeFluid((GeneratorAccess)blockList, blockposition2, iblockdata) != FluidTypes.EMPTY) {
/*  64 */             i++;
/*  65 */             if (j < 6) {
/*  66 */               queue.add(new Tuple<>(blockposition2, Integer.valueOf(j + 1)));
/*     */             }
/*  68 */           } else if (iblockdata.getBlock() instanceof BlockFluids) {
/*  69 */             blockList.setTypeAndData(blockposition2, Blocks.AIR.getBlockData(), 3);
/*  70 */             i++;
/*  71 */             if (j < 6) {
/*  72 */               queue.add(new Tuple<>(blockposition2, Integer.valueOf(j + 1)));
/*     */             }
/*  74 */           } else if (material == Material.WATER_PLANT || material == Material.REPLACEABLE_WATER_PLANT) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  79 */             blockList.setTypeAndData(blockposition2, Blocks.AIR.getBlockData(), 3);
/*     */             
/*  81 */             i++;
/*  82 */             if (j < 6) {
/*  83 */               queue.add(new Tuple<>(blockposition2, Integer.valueOf(j + 1)));
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/*  89 */       if (i > 64) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/*  94 */     List<CraftBlockState> blocks = blockList.getList();
/*  95 */     if (!blocks.isEmpty()) {
/*  96 */       Block bblock = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*     */       
/*  98 */       SpongeAbsorbEvent event = new SpongeAbsorbEvent(bblock, blocks);
/*  99 */       world.getServer().getPluginManager().callEvent((Event)event);
/*     */       
/* 101 */       if (event.isCancelled()) {
/* 102 */         return false;
/*     */       }
/*     */       
/* 105 */       for (CraftBlockState block : blocks) {
/* 106 */         BlockPosition blockposition2 = block.getPosition();
/* 107 */         IBlockData iblockdata = world.getType(blockposition2);
/* 108 */         Fluid fluid = world.getFluid(blockposition2);
/* 109 */         Material material = iblockdata.getMaterial();
/*     */         
/* 111 */         if (fluid.a(TagsFluid.WATER) && (
/* 112 */           !(iblockdata.getBlock() instanceof IFluidSource) || ((IFluidSource)iblockdata.getBlock()).removeFluid((GeneratorAccess)blockList, blockposition2, iblockdata) == FluidTypes.EMPTY))
/*     */         {
/* 114 */           if (!(iblockdata.getBlock() instanceof BlockFluids))
/*     */           {
/* 116 */             if (material == Material.WATER_PLANT || material == Material.REPLACEABLE_WATER_PLANT) {
/* 117 */               TileEntity tileentity = iblockdata.getBlock().isTileEntity() ? world.getTileEntity(blockposition2) : null;
/*     */               
/* 119 */               if (block.getHandle().getMaterial() == Material.AIR) {
/* 120 */                 dropNaturally(iblockdata, world, blockposition2, tileentity);
/*     */               }
/*     */             } 
/*     */           }
/*     */         }
/* 125 */         world.setTypeAndData(blockposition2, block.getHandle(), block.getFlag());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 130 */     return (i > 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSponge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */