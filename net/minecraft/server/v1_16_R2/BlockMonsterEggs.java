/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ 
/*    */ public class BlockMonsterEggs
/*    */   extends Block
/*    */ {
/*    */   private final Block a;
/* 11 */   private static final Map<Block, Block> b = Maps.newIdentityHashMap();
/*    */   
/*    */   public BlockMonsterEggs(Block block, BlockBase.Info blockbase_info) {
/* 14 */     super(blockbase_info);
/* 15 */     this.a = block;
/* 16 */     b.put(block, this);
/*    */   }
/*    */   
/*    */   public Block c() {
/* 20 */     return this.a;
/*    */   }
/*    */   
/*    */   public static boolean h(IBlockData iblockdata) {
/* 24 */     return b.containsKey(iblockdata.getBlock());
/*    */   }
/*    */   
/*    */   private void a(WorldServer worldserver, BlockPosition blockposition) {
/* 28 */     EntitySilverfish entitysilverfish = EntityTypes.SILVERFISH.a(worldserver);
/*    */     
/* 30 */     entitysilverfish.setPositionRotation(blockposition.getX() + 0.5D, blockposition.getY(), blockposition.getZ() + 0.5D, 0.0F, 0.0F);
/* 31 */     worldserver.addEntity(entitysilverfish, CreatureSpawnEvent.SpawnReason.SILVERFISH_BLOCK);
/* 32 */     entitysilverfish.doSpawnEffect();
/*    */   }
/*    */ 
/*    */   
/*    */   public void dropNaturally(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, ItemStack itemstack) {
/* 37 */     super.dropNaturally(iblockdata, worldserver, blockposition, itemstack);
/* 38 */     if (worldserver.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentManager.getEnchantmentLevel(Enchantments.SILK_TOUCH, itemstack) == 0) {
/* 39 */       a(worldserver, blockposition);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void wasExploded(World world, BlockPosition blockposition, Explosion explosion) {
/* 46 */     if (world instanceof WorldServer) {
/* 47 */       a((WorldServer)world, blockposition);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static IBlockData c(Block block) {
/* 53 */     return ((Block)b.get(block)).getBlockData();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockMonsterEggs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */