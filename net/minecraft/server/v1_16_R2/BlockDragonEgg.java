/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockFromToEvent;
/*    */ 
/*    */ public class BlockDragonEgg extends BlockFalling {
/*  7 */   protected static final VoxelShape a = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
/*    */   
/*    */   public BlockDragonEgg(BlockBase.Info blockbase_info) {
/* 10 */     super(blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 15 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 20 */     d(iblockdata, world, blockposition);
/* 21 */     return EnumInteractionResult.a(world.isClientSide);
/*    */   }
/*    */ 
/*    */   
/*    */   public void attack(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman) {
/* 26 */     d(iblockdata, world, blockposition);
/*    */   }
/*    */   
/*    */   private void d(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 30 */     for (int i = 0; i < 1000; i++) {
/* 31 */       BlockPosition blockposition1 = blockposition.b(world.random.nextInt(16) - world.random.nextInt(16), world.random.nextInt(8) - world.random.nextInt(8), world.random.nextInt(16) - world.random.nextInt(16));
/*    */       
/* 33 */       if (world.getType(blockposition1).isAir()) {
/*    */         
/* 35 */         Block from = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 36 */         Block to = world.getWorld().getBlockAt(blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
/* 37 */         BlockFromToEvent event = new BlockFromToEvent(from, to);
/* 38 */         Bukkit.getPluginManager().callEvent((Event)event);
/*    */         
/* 40 */         if (event.isCancelled()) {
/*    */           return;
/*    */         }
/*    */         
/* 44 */         blockposition1 = new BlockPosition(event.getToBlock().getX(), event.getToBlock().getY(), event.getToBlock().getZ());
/*    */         
/* 46 */         if (world.isClientSide) {
/* 47 */           for (int j = 0; j < 128; j++) {
/* 48 */             double d0 = world.random.nextDouble();
/* 49 */             float f = (world.random.nextFloat() - 0.5F) * 0.2F;
/* 50 */             float f1 = (world.random.nextFloat() - 0.5F) * 0.2F;
/* 51 */             float f2 = (world.random.nextFloat() - 0.5F) * 0.2F;
/* 52 */             double d1 = MathHelper.d(d0, blockposition1.getX(), blockposition.getX()) + world.random.nextDouble() - 0.5D + 0.5D;
/* 53 */             double d2 = MathHelper.d(d0, blockposition1.getY(), blockposition.getY()) + world.random.nextDouble() - 0.5D;
/* 54 */             double d3 = MathHelper.d(d0, blockposition1.getZ(), blockposition.getZ()) + world.random.nextDouble() - 0.5D + 0.5D;
/*    */             
/* 56 */             world.addParticle(Particles.PORTAL, d1, d2, d3, f, f1, f2);
/*    */           } 
/*    */         } else {
/* 59 */           world.setTypeAndData(blockposition1, iblockdata, 2);
/* 60 */           world.a(blockposition, false);
/*    */         } 
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int c() {
/* 71 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 76 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockDragonEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */