/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public abstract class BlockDirtSnowSpreadable extends BlockDirtSnow {
/*    */   protected BlockDirtSnowSpreadable(BlockBase.Info blockbase_info) {
/*  8 */     super(blockbase_info);
/*    */   }
/*    */   
/*    */   private static boolean b(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 12 */     BlockPosition blockposition1 = blockposition.up();
/* 13 */     IBlockData iblockdata1 = iworldreader.getType(blockposition1);
/*    */     
/* 15 */     if (iblockdata1.a(Blocks.SNOW) && ((Integer)iblockdata1.get(BlockSnow.LAYERS)).intValue() == 1)
/* 16 */       return true; 
/* 17 */     if (iblockdata1.getFluid().e() == 8) {
/* 18 */       return false;
/*    */     }
/* 20 */     int i = LightEngineLayer.a(iworldreader, iblockdata, blockposition, iblockdata1, blockposition1, EnumDirection.UP, iblockdata1.b(iworldreader, blockposition1));
/*    */     
/* 22 */     return (i < iworldreader.J());
/*    */   }
/*    */ 
/*    */   
/*    */   private static boolean c(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 27 */     BlockPosition blockposition1 = blockposition.up();
/*    */     
/* 29 */     return (b(iblockdata, iworldreader, blockposition) && !iworldreader.getFluid(blockposition1).a(TagsFluid.WATER));
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 34 */     if (this instanceof BlockGrass && worldserver.paperConfig.grassUpdateRate != 1 && (worldserver.paperConfig.grassUpdateRate < 1 || (MinecraftServer.currentTick + blockposition.hashCode()) % worldserver.paperConfig.grassUpdateRate != 0))
/* 35 */       return;  if (!b(iblockdata, worldserver, blockposition)) {
/*    */       
/* 37 */       if (CraftEventFactory.callBlockFadeEvent(worldserver, blockposition, Blocks.DIRT.getBlockData()).isCancelled()) {
/*    */         return;
/*    */       }
/*    */       
/* 41 */       worldserver.setTypeUpdate(blockposition, Blocks.DIRT.getBlockData());
/*    */     }
/* 43 */     else if (worldserver.getLightLevel(blockposition.up()) >= 9) {
/* 44 */       IBlockData iblockdata1 = getBlockData();
/*    */       
/* 46 */       for (int i = 0; i < 4; i++) {
/* 47 */         BlockPosition blockposition1 = blockposition.b(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
/*    */         
/* 49 */         if (worldserver.getType(blockposition1).a(Blocks.DIRT) && c(iblockdata1, worldserver, blockposition1))
/* 50 */           CraftEventFactory.handleBlockSpreadEvent(worldserver, blockposition, blockposition1, iblockdata1.set(a, Boolean.valueOf(worldserver.getType(blockposition1.up()).a(Blocks.SNOW)))); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockDirtSnowSpreadable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */