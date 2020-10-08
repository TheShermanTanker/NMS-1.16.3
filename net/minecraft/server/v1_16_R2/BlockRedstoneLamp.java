/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockRedstoneLamp
/*    */   extends Block
/*    */ {
/* 10 */   public static final BlockStateBoolean a = BlockRedstoneTorch.LIT;
/*    */   
/*    */   public BlockRedstoneLamp(BlockBase.Info blockbase_info) {
/* 13 */     super(blockbase_info);
/* 14 */     j(getBlockData().set(a, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 20 */     return getBlockData().set(a, Boolean.valueOf(blockactioncontext.getWorld().isBlockIndirectlyPowered(blockactioncontext.getClickPosition())));
/*    */   }
/*    */ 
/*    */   
/*    */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/* 25 */     if (!world.isClientSide) {
/* 26 */       boolean flag1 = ((Boolean)iblockdata.get(a)).booleanValue();
/*    */       
/* 28 */       if (flag1 != world.isBlockIndirectlyPowered(blockposition)) {
/* 29 */         if (flag1) {
/* 30 */           world.getBlockTickList().a(blockposition, this, 4);
/*    */         } else {
/*    */           
/* 33 */           if (CraftEventFactory.callRedstoneChange(world, blockposition, 0, 15).getNewCurrent() != 15) {
/*    */             return;
/*    */           }
/*    */           
/* 37 */           world.setTypeAndData(blockposition, iblockdata.a(a), 2);
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 46 */     if (((Boolean)iblockdata.get(a)).booleanValue() && !worldserver.isBlockIndirectlyPowered(blockposition)) {
/*    */       
/* 48 */       if (CraftEventFactory.callRedstoneChange(worldserver, blockposition, 15, 0).getNewCurrent() != 0) {
/*    */         return;
/*    */       }
/*    */       
/* 52 */       worldserver.setTypeAndData(blockposition, iblockdata.a(a), 2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 59 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockRedstoneLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */