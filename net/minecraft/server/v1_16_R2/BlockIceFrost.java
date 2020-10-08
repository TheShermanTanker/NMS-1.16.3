/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BlockIceFrost
/*    */   extends BlockIce {
/*  7 */   public static final BlockStateInteger a = BlockProperties.ag;
/*    */   
/*    */   public BlockIceFrost(BlockBase.Info blockbase_info) {
/* 10 */     super(blockbase_info);
/* 11 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 16 */     tickAlways(iblockdata, worldserver, blockposition, random);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 21 */     if (!worldserver.paperConfig.frostedIceEnabled)
/* 22 */       return;  if ((random.nextInt(3) == 0 || a(worldserver, blockposition, 4)) && worldserver.getLightLevel(blockposition) > 11 - ((Integer)iblockdata.get(a)).intValue() - iblockdata.b(worldserver, blockposition) && e(iblockdata, worldserver, blockposition)) {
/* 23 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 24 */       EnumDirection[] aenumdirection = EnumDirection.values();
/* 25 */       int i = aenumdirection.length;
/*    */       
/* 27 */       for (int j = 0; j < i; j++) {
/* 28 */         EnumDirection enumdirection = aenumdirection[j];
/*    */         
/* 30 */         blockposition_mutableblockposition.a(blockposition, enumdirection);
/* 31 */         IBlockData iblockdata1 = worldserver.getTypeIfLoaded(blockposition_mutableblockposition);
/* 32 */         if (iblockdata1 != null)
/*    */         {
/* 34 */           if (iblockdata1.a(this) && !e(iblockdata1, worldserver, blockposition_mutableblockposition)) {
/* 35 */             worldserver.getBlockTickList().a(blockposition_mutableblockposition, this, MathHelper.nextInt(random, worldserver.paperConfig.frostedIceDelayMin, worldserver.paperConfig.frostedIceDelayMax));
/*    */           }
/*    */         }
/*    */       } 
/*    */     } else {
/* 40 */       worldserver.getBlockTickList().a(blockposition, this, MathHelper.nextInt(random, worldserver.paperConfig.frostedIceDelayMin, worldserver.paperConfig.frostedIceDelayMax));
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean e(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 45 */     int i = ((Integer)iblockdata.get(a)).intValue();
/*    */     
/* 47 */     if (i < 3) {
/* 48 */       world.setTypeAndData(blockposition, iblockdata.set(a, Integer.valueOf(i + 1)), 2);
/* 49 */       return false;
/*    */     } 
/* 51 */     melt(iblockdata, world, blockposition);
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/* 58 */     if (block == this && a(world, blockposition, 2)) {
/* 59 */       melt(iblockdata, world, blockposition);
/*    */     }
/*    */     
/* 62 */     super.doPhysics(iblockdata, world, blockposition, block, blockposition1, flag);
/*    */   }
/*    */   
/*    */   private boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, int i) {
/* 66 */     int j = 0;
/* 67 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 68 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 69 */     int k = aenumdirection.length;
/*    */     
/* 71 */     for (int l = 0; l < k; l++) {
/* 72 */       EnumDirection enumdirection = aenumdirection[l];
/*    */       
/* 74 */       blockposition_mutableblockposition.a(blockposition, enumdirection);
/*    */       
/* 76 */       IBlockData type = iblockaccess.getTypeIfLoaded(blockposition_mutableblockposition);
/*    */       
/* 78 */       j++;
/* 79 */       if (type != null && type.a(this) && j >= i) {
/* 80 */         return false;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 85 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 90 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockIceFrost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */