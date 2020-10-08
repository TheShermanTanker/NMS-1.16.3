/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.TreeType;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.world.StructureGrowEvent;
/*    */ 
/*    */ public class BlockSapling extends BlockPlant implements IBlockFragilePlantElement {
/* 14 */   public static final BlockStateInteger STAGE = BlockProperties.aA;
/* 15 */   protected static final VoxelShape b = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
/*    */   private final WorldGenTreeProvider c;
/*    */   public static TreeType treeType;
/*    */   
/*    */   protected BlockSapling(WorldGenTreeProvider worldgentreeprovider, BlockBase.Info blockbase_info) {
/* 20 */     super(blockbase_info);
/* 21 */     this.c = worldgentreeprovider;
/* 22 */     j(((IBlockData)this.blockStateList.getBlockData()).set(STAGE, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 27 */     return b;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 32 */     if (worldserver.getLightLevel(blockposition.up()) >= 9 && random.nextInt(Math.max(2, (int)(100.0F / worldserver.spigotConfig.saplingModifier * 7.0F + 0.5F))) == 0) {
/*    */       
/* 34 */       worldserver.captureTreeGeneration = true;
/*    */       
/* 36 */       grow(worldserver, blockposition, iblockdata, random);
/*    */       
/* 38 */       worldserver.captureTreeGeneration = false;
/* 39 */       if (worldserver.capturedBlockStates.size() > 0) {
/* 40 */         TreeType treeType = BlockSapling.treeType;
/* 41 */         BlockSapling.treeType = null;
/* 42 */         Location location = new Location((World)worldserver.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 43 */         List<BlockState> blocks = new ArrayList<>((Collection)worldserver.capturedBlockStates.values());
/* 44 */         worldserver.capturedBlockStates.clear();
/* 45 */         StructureGrowEvent event = null;
/* 46 */         if (treeType != null) {
/* 47 */           event = new StructureGrowEvent(location, treeType, false, null, blocks);
/* 48 */           Bukkit.getPluginManager().callEvent((Event)event);
/*    */         } 
/* 50 */         if (event == null || !event.isCancelled()) {
/* 51 */           for (BlockState blockstate : blocks) {
/* 52 */             blockstate.update(true);
/*    */           }
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void grow(WorldServer worldserver, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 62 */     if (((Integer)iblockdata.get(STAGE)).intValue() == 0) {
/* 63 */       worldserver.setTypeAndData(blockposition, iblockdata.a(STAGE), 4);
/*    */     } else {
/* 65 */       this.c.a(worldserver, worldserver.getChunkProvider().getChunkGenerator(), blockposition, iblockdata, random);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 77 */     return (world.random.nextFloat() < 0.45D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 82 */     grow(worldserver, blockposition, iblockdata, random);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 87 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { STAGE });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */