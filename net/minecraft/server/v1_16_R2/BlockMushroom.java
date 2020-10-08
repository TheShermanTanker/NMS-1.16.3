/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import org.bukkit.TreeType;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockMushroom
/*     */   extends BlockPlant
/*     */   implements IBlockFragilePlantElement
/*     */ {
/*  12 */   protected static final VoxelShape a = Block.a(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
/*     */   
/*     */   public BlockMushroom(BlockBase.Info blockbase_info) {
/*  15 */     super(blockbase_info);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  20 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  25 */     if (random.nextInt(Math.max(1, (int)(100.0F / worldserver.spigotConfig.mushroomModifier) * 25)) == 0) {
/*  26 */       int i = 5;
/*  27 */       boolean flag = true;
/*  28 */       Iterator<BlockPosition> iterator = BlockPosition.a(blockposition.b(-4, -1, -4), blockposition.b(4, 1, 4)).iterator();
/*     */       
/*  30 */       while (iterator.hasNext()) {
/*  31 */         BlockPosition blockposition1 = iterator.next();
/*     */ 
/*     */         
/*  34 */         i--;
/*  35 */         if (worldserver.getType(blockposition1).a(this) && i <= 0) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  41 */       BlockPosition blockposition2 = blockposition.b(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
/*     */       
/*  43 */       for (int j = 0; j < 4; j++) {
/*  44 */         if (worldserver.isEmpty(blockposition2) && iblockdata.canPlace(worldserver, blockposition2)) {
/*  45 */           blockposition = blockposition2;
/*     */         }
/*     */         
/*  48 */         blockposition2 = blockposition.b(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
/*     */       } 
/*     */       
/*  51 */       if (worldserver.isEmpty(blockposition2) && iblockdata.canPlace(worldserver, blockposition2)) {
/*  52 */         CraftEventFactory.handleBlockSpreadEvent(worldserver, blockposition, blockposition2, iblockdata, 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  60 */     return iblockdata.i(iblockaccess, blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*  65 */     BlockPosition blockposition1 = blockposition.down();
/*  66 */     IBlockData iblockdata1 = iworldreader.getType(blockposition1);
/*     */     
/*  68 */     return iblockdata1.a(TagsBlock.aD) ? true : ((iworldreader.getLightLevel(blockposition, 0) < 13 && c(iblockdata1, iworldreader, blockposition1)));
/*     */   }
/*     */   public boolean a(WorldServer worldserver, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*     */     WorldGenFeatureConfigured<?, ?> worldgenfeatureconfigured;
/*  72 */     worldserver.a(blockposition, false);
/*     */ 
/*     */     
/*  75 */     if (this == Blocks.BROWN_MUSHROOM) {
/*  76 */       BlockSapling.treeType = TreeType.BROWN_MUSHROOM;
/*  77 */       worldgenfeatureconfigured = BiomeDecoratorGroups.HUGE_BROWN_MUSHROOM;
/*     */     } else {
/*  79 */       if (this != Blocks.RED_MUSHROOM) {
/*  80 */         worldserver.setTypeAndData(blockposition, iblockdata, 3);
/*  81 */         return false;
/*     */       } 
/*     */       
/*  84 */       BlockSapling.treeType = TreeType.RED_MUSHROOM;
/*  85 */       worldgenfeatureconfigured = BiomeDecoratorGroups.HUGE_RED_MUSHROOM;
/*     */     } 
/*     */     
/*  88 */     if (worldgenfeatureconfigured.a(worldserver, worldserver.getChunkProvider().getChunkGenerator(), random, blockposition)) {
/*  89 */       return true;
/*     */     }
/*  91 */     worldserver.setTypeAndData(blockposition, iblockdata, 3);
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 103 */     return (random.nextFloat() < 0.4D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 108 */     a(worldserver, blockposition, iblockdata, random);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */