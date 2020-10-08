/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockCrops
/*     */   extends BlockPlant
/*     */   implements IBlockFragilePlantElement {
/*   9 */   public static final BlockStateInteger AGE = BlockProperties.ai;
/*  10 */   private static final VoxelShape[] a = new VoxelShape[] { Block.a(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };
/*     */   
/*     */   protected BlockCrops(BlockBase.Info blockbase_info) {
/*  13 */     super(blockbase_info);
/*  14 */     j(((IBlockData)this.blockStateList.getBlockData()).set(c(), Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  19 */     return a[((Integer)iblockdata.get(c())).intValue()];
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  24 */     return iblockdata.a(Blocks.FARMLAND);
/*     */   }
/*     */   
/*     */   public BlockStateInteger c() {
/*  28 */     return AGE;
/*     */   }
/*     */   
/*     */   public int d() {
/*  32 */     return 7;
/*     */   }
/*     */   
/*     */   protected int g(IBlockData iblockdata) {
/*  36 */     return ((Integer)iblockdata.get(c())).intValue();
/*     */   }
/*     */   
/*     */   public IBlockData setAge(int i) {
/*  40 */     return getBlockData().set(c(), Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public boolean isRipe(IBlockData iblockdata) {
/*  44 */     return (((Integer)iblockdata.get(c())).intValue() >= d());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTicking(IBlockData iblockdata) {
/*  49 */     return !isRipe(iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  54 */     if (worldserver.getLightLevel(blockposition, 0) >= 9) {
/*  55 */       int i = g(iblockdata);
/*     */       
/*  57 */       if (i < d()) {
/*  58 */         int modifier; float f = a(this, worldserver, blockposition);
/*     */ 
/*     */ 
/*     */         
/*  62 */         if (this == Blocks.BEETROOTS) {
/*  63 */           modifier = worldserver.spigotConfig.beetrootModifier;
/*  64 */         } else if (this == Blocks.CARROTS) {
/*  65 */           modifier = worldserver.spigotConfig.carrotModifier;
/*  66 */         } else if (this == Blocks.POTATOES) {
/*  67 */           modifier = worldserver.spigotConfig.potatoModifier;
/*     */         } else {
/*  69 */           modifier = worldserver.spigotConfig.wheatModifier;
/*     */         } 
/*     */         
/*  72 */         if (random.nextInt((int)(100.0F / modifier * 25.0F / f) + 1) == 0)
/*     */         {
/*  74 */           CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition, setAge(i + 1), 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  82 */     int i = g(iblockdata) + a(world);
/*  83 */     int j = d();
/*     */     
/*  85 */     if (i > j) {
/*  86 */       i = j;
/*     */     }
/*     */     
/*  89 */     CraftEventFactory.handleBlockGrowEvent(world, blockposition, setAge(i), 2);
/*     */   }
/*     */   
/*     */   protected int a(World world) {
/*  93 */     return MathHelper.nextInt(world.random, 2, 5);
/*     */   }
/*     */   
/*     */   protected static float a(Block block, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  97 */     float f = 1.0F;
/*  98 */     BlockPosition blockposition1 = blockposition.down();
/*     */     
/* 100 */     for (int i = -1; i <= 1; i++) {
/* 101 */       for (int j = -1; j <= 1; j++) {
/* 102 */         float f1 = 0.0F;
/* 103 */         IBlockData iblockdata = iblockaccess.getType(blockposition1.b(i, 0, j));
/*     */         
/* 105 */         if (iblockdata.a(Blocks.FARMLAND)) {
/* 106 */           f1 = 1.0F;
/* 107 */           if (((Integer)iblockdata.get(BlockSoil.MOISTURE)).intValue() > 0) {
/* 108 */             f1 = 3.0F;
/*     */           }
/*     */         } 
/*     */         
/* 112 */         if (i != 0 || j != 0) {
/* 113 */           f1 /= 4.0F;
/*     */         }
/*     */         
/* 116 */         f += f1;
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     BlockPosition blockposition2 = blockposition.north();
/* 121 */     BlockPosition blockposition3 = blockposition.south();
/* 122 */     BlockPosition blockposition4 = blockposition.west();
/* 123 */     BlockPosition blockposition5 = blockposition.east();
/* 124 */     boolean flag = (block == iblockaccess.getType(blockposition4).getBlock() || block == iblockaccess.getType(blockposition5).getBlock());
/* 125 */     boolean flag1 = (block == iblockaccess.getType(blockposition2).getBlock() || block == iblockaccess.getType(blockposition3).getBlock());
/*     */     
/* 127 */     if (flag && flag1) {
/* 128 */       f /= 2.0F;
/*     */     } else {
/* 130 */       boolean flag2 = (block == iblockaccess.getType(blockposition4.north()).getBlock() || block == iblockaccess.getType(blockposition5.north()).getBlock() || block == iblockaccess.getType(blockposition5.south()).getBlock() || block == iblockaccess.getType(blockposition4.south()).getBlock());
/*     */       
/* 132 */       if (flag2) {
/* 133 */         f /= 2.0F;
/*     */       }
/*     */     } 
/*     */     
/* 137 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 142 */     return ((iworldreader.getLightLevel(blockposition, 0) >= 8 || iworldreader.e(blockposition)) && super.canPlace(iblockdata, iworldreader, blockposition));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/* 147 */     if (entity instanceof EntityRavager && !CraftEventFactory.callEntityChangeBlockEvent(entity, blockposition, Blocks.AIR.getBlockData(), !world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)).isCancelled()) {
/* 148 */       world.a(blockposition, true, entity);
/*     */     }
/*     */     
/* 151 */     super.a(iblockdata, world, blockposition, entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 156 */     return !isRipe(iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 161 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 166 */     a(worldserver, blockposition, iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 171 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { AGE });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */