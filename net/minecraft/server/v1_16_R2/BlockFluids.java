/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockFluids extends Block implements IFluidSource {
/*  10 */   public static final BlockStateInteger LEVEL = BlockProperties.av;
/*     */   protected final FluidTypeFlowing b;
/*     */   private final List<Fluid> d;
/*  13 */   public static final VoxelShape c = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
/*     */   
/*     */   protected BlockFluids(FluidTypeFlowing fluidtypeflowing, BlockBase.Info blockbase_info) {
/*  16 */     super(blockbase_info);
/*  17 */     this.b = fluidtypeflowing;
/*  18 */     this.d = Lists.newArrayList();
/*  19 */     this.d.add(fluidtypeflowing.a(false));
/*     */     
/*  21 */     for (int i = 1; i < 8; i++) {
/*  22 */       this.d.add(fluidtypeflowing.a(8 - i, false));
/*     */     }
/*     */     
/*  25 */     this.d.add(fluidtypeflowing.a(8, true));
/*  26 */     j(((IBlockData)this.blockStateList.getBlockData()).set(LEVEL, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  31 */     return (voxelshapecollision.a(c, blockposition, true) && ((Integer)iblockdata.get(LEVEL)).intValue() == 0 && voxelshapecollision.a(iblockaccess.getFluid(blockposition.up()), this.b)) ? c : VoxelShapes.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTicking(IBlockData iblockdata) {
/*  36 */     return iblockdata.getFluid().f();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  41 */     iblockdata.getFluid().b(worldserver, blockposition, random);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/*  51 */     return !this.b.a(TagsFluid.LAVA);
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData iblockdata) {
/*  56 */     int i = ((Integer)iblockdata.get(LEVEL)).intValue();
/*     */     
/*  58 */     return this.d.get(Math.min(i, 8));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/*  63 */     return EnumRenderType.INVISIBLE;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ItemStack> a(IBlockData iblockdata, LootTableInfo.Builder loottableinfo_builder) {
/*  68 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  73 */     return VoxelShapes.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  78 */     if (a(world, blockposition, iblockdata)) {
/*  79 */       world.getFluidTickList().a(blockposition, iblockdata.getFluid().getType(), getFlowSpeed(world, blockposition));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFlowSpeed(World world, BlockPosition blockposition) {
/*  86 */     if (this.material == Material.WATER && (
/*  87 */       world
/*  88 */       .getMaterialIfLoaded(blockposition.north(1)) == Material.LAVA || world
/*  89 */       .getMaterialIfLoaded(blockposition.south(1)) == Material.LAVA || world
/*  90 */       .getMaterialIfLoaded(blockposition.west(1)) == Material.LAVA || world
/*  91 */       .getMaterialIfLoaded(blockposition.east(1)) == Material.LAVA))
/*     */     {
/*  93 */       return world.paperConfig.waterOverLavaFlowSpeed;
/*     */     }
/*     */     
/*  96 */     return this.b.a(world);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 103 */     if (iblockdata.getFluid().isSource() || iblockdata1.getFluid().isSource()) {
/* 104 */       generatoraccess.getFluidTickList().a(blockposition, iblockdata.getFluid().getType(), this.b.a(generatoraccess));
/*     */     }
/*     */     
/* 107 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/* 112 */     if (a(world, blockposition, iblockdata)) {
/* 113 */       world.getFluidTickList().a(blockposition, iblockdata.getFluid().getType(), getFlowSpeed(world, blockposition));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 119 */     if (this.b.a(TagsFluid.LAVA)) {
/* 120 */       boolean flag = world.getType(blockposition.down()).a(Blocks.SOUL_SOIL);
/* 121 */       EnumDirection[] aenumdirection = EnumDirection.values();
/* 122 */       int i = aenumdirection.length;
/*     */       
/* 124 */       for (int j = 0; j < i; j++) {
/* 125 */         EnumDirection enumdirection = aenumdirection[j];
/*     */         
/* 127 */         if (enumdirection != EnumDirection.DOWN) {
/* 128 */           BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */           
/* 130 */           if (world.getFluid(blockposition1).a(TagsFluid.WATER)) {
/* 131 */             Block block = world.getFluid(blockposition).isSource() ? Blocks.OBSIDIAN : Blocks.COBBLESTONE;
/*     */ 
/*     */             
/* 134 */             if (CraftEventFactory.handleBlockFormEvent(world, blockposition, block.getBlockData())) {
/* 135 */               fizz(world, blockposition);
/*     */             }
/*     */             
/* 138 */             return false;
/*     */           } 
/*     */           
/* 141 */           if (flag && world.getType(blockposition1).a(Blocks.BLUE_ICE)) {
/*     */             
/* 143 */             if (CraftEventFactory.handleBlockFormEvent(world, blockposition, Blocks.BASALT.getBlockData())) {
/* 144 */               fizz(world, blockposition);
/*     */             }
/*     */             
/* 147 */             return false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     return true;
/*     */   }
/*     */   
/*     */   private void fizz(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 157 */     generatoraccess.triggerEffect(1501, blockposition, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 162 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { LEVEL });
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidType removeFluid(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata) {
/* 167 */     if (((Integer)iblockdata.get(LEVEL)).intValue() == 0) {
/* 168 */       generatoraccess.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 11);
/* 169 */       return this.b;
/*     */     } 
/* 171 */     return FluidTypes.EMPTY;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFluids.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */