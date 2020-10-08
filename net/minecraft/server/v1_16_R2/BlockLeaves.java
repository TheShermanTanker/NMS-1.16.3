/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.LeavesDecayEvent;
/*    */ 
/*    */ public class BlockLeaves
/*    */   extends Block {
/*  9 */   public static final BlockStateInteger DISTANCE = BlockProperties.an;
/* 10 */   public static final BlockStateBoolean PERSISTENT = BlockProperties.v;
/*    */   
/*    */   public BlockLeaves(BlockBase.Info blockbase_info) {
/* 13 */     super(blockbase_info);
/* 14 */     j(((IBlockData)this.blockStateList.getBlockData()).set(DISTANCE, Integer.valueOf(7)).set(PERSISTENT, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape e(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 19 */     return VoxelShapes.a();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTicking(IBlockData iblockdata) {
/* 24 */     return (((Integer)iblockdata.get(DISTANCE)).intValue() == 7 && !((Boolean)iblockdata.get(PERSISTENT)).booleanValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 29 */     if (!((Boolean)iblockdata.get(PERSISTENT)).booleanValue() && ((Integer)iblockdata.get(DISTANCE)).intValue() == 7) {
/*    */       
/* 31 */       LeavesDecayEvent event = new LeavesDecayEvent(worldserver.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 32 */       worldserver.getServer().getPluginManager().callEvent((Event)event);
/*    */       
/* 34 */       if (event.isCancelled() || worldserver.getType(blockposition).getBlock() != this) {
/*    */         return;
/*    */       }
/*    */       
/* 38 */       c(iblockdata, worldserver, blockposition);
/* 39 */       worldserver.a(blockposition, false);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 46 */     worldserver.setTypeAndData(blockposition, a(iblockdata, worldserver, blockposition), 3);
/*    */   }
/*    */ 
/*    */   
/*    */   public int f(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 51 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 56 */     int i = h(iblockdata1) + 1;
/*    */     
/* 58 */     if (i != 1 || ((Integer)iblockdata.get(DISTANCE)).intValue() != i) {
/* 59 */       generatoraccess.getBlockTickList().a(blockposition, this, 1);
/*    */     }
/*    */     
/* 62 */     return iblockdata;
/*    */   }
/*    */   
/*    */   private static IBlockData a(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 66 */     int i = 7;
/* 67 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 68 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 69 */     int j = aenumdirection.length;
/*    */     
/* 71 */     for (int k = 0; k < j; k++) {
/* 72 */       EnumDirection enumdirection = aenumdirection[k];
/*    */       
/* 74 */       blockposition_mutableblockposition.a(blockposition, enumdirection);
/* 75 */       i = Math.min(i, h(generatoraccess.getType(blockposition_mutableblockposition)) + 1);
/* 76 */       if (i == 1) {
/*    */         break;
/*    */       }
/*    */     } 
/*    */     
/* 81 */     return iblockdata.set(DISTANCE, Integer.valueOf(i));
/*    */   }
/*    */   
/*    */   private static int h(IBlockData iblockdata) {
/* 85 */     return TagsBlock.LOGS.isTagged(iblockdata.getBlock()) ? 0 : ((iblockdata.getBlock() instanceof BlockLeaves) ? ((Integer)iblockdata.get(DISTANCE)).intValue() : 7);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 90 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { DISTANCE, PERSISTENT });
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 95 */     return a(getBlockData().set(PERSISTENT, Boolean.valueOf(true)), blockactioncontext.getWorld(), blockactioncontext.getClickPosition());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */