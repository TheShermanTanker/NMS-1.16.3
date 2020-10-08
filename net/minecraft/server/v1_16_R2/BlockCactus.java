/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockCactus
/*     */   extends Block
/*     */ {
/*  10 */   public static final BlockStateInteger AGE = BlockProperties.aj;
/*  11 */   protected static final VoxelShape b = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
/*  12 */   protected static final VoxelShape c = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
/*     */   
/*     */   protected BlockCactus(BlockBase.Info blockbase_info) {
/*  15 */     super(blockbase_info);
/*  16 */     j(((IBlockData)this.blockStateList.getBlockData()).set(AGE, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  21 */     if (!iblockdata.canPlace(worldserver, blockposition)) {
/*  22 */       worldserver.b(blockposition, true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  29 */     BlockPosition blockposition1 = blockposition.up();
/*     */     
/*  31 */     if (worldserver.isEmpty(blockposition1)) {
/*     */       int i;
/*     */       
/*  34 */       for (i = 1; worldserver.getType(blockposition.down(i)).a(this); i++);
/*     */ 
/*     */ 
/*     */       
/*  38 */       if (i < worldserver.paperConfig.cactusMaxHeight) {
/*  39 */         int j = ((Integer)iblockdata.get(AGE)).intValue();
/*     */         
/*  41 */         if (j >= (byte)(int)range(3.0F, 100.0F / worldserver.spigotConfig.cactusModifier * 15.0F + 0.5F, 15.0F)) {
/*  42 */           CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition1, getBlockData());
/*  43 */           IBlockData iblockdata1 = iblockdata.set(AGE, Integer.valueOf(0));
/*     */           
/*  45 */           worldserver.setTypeAndData(blockposition, iblockdata1, 4);
/*  46 */           iblockdata1.doPhysics(worldserver, blockposition1, this, blockposition, false);
/*     */         } else {
/*  48 */           worldserver.setTypeAndData(blockposition, iblockdata.set(AGE, Integer.valueOf(j + 1)), 4);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  57 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  62 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  67 */     if (!iblockdata.canPlace(generatoraccess, blockposition)) {
/*  68 */       generatoraccess.getBlockTickList().a(blockposition, this, 1);
/*     */     }
/*     */     
/*  71 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*     */     EnumDirection enumdirection;
/*     */     Material material;
/*  76 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  82 */       if (!iterator.hasNext()) {
/*  83 */         IBlockData iblockdata1 = iworldreader.getType(blockposition.down());
/*     */         
/*  85 */         return ((iblockdata1.a(Blocks.CACTUS) || iblockdata1.a(Blocks.SAND) || iblockdata1.a(Blocks.RED_SAND)) && !iworldreader.getType(blockposition.up()).getMaterial().isLiquid());
/*     */       } 
/*     */       
/*  88 */       enumdirection = iterator.next();
/*  89 */       IBlockData iblockdata2 = iworldreader.getType(blockposition.shift(enumdirection));
/*     */       
/*  91 */       material = iblockdata2.getMaterial();
/*  92 */     } while (!material.isBuildable() && !iworldreader.getFluid(blockposition.shift(enumdirection)).a(TagsFluid.LAVA));
/*     */     
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/*  99 */     CraftEventFactory.blockDamage = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 100 */     entity.damageEntity(DamageSource.CACTUS, 1.0F);
/* 101 */     CraftEventFactory.blockDamage = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 106 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { AGE });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 111 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCactus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */