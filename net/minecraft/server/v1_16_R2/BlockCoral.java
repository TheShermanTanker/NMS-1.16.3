/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockCoral extends Block {
/*    */   private final Block a;
/*    */   
/*    */   public BlockCoral(Block block, BlockBase.Info blockbase_info) {
/* 11 */     super(blockbase_info);
/* 12 */     this.a = block;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 17 */     if (!a(worldserver, blockposition)) {
/*    */       
/* 19 */       if (CraftEventFactory.callBlockFadeEvent(worldserver, blockposition, this.a.getBlockData()).isCancelled()) {
/*    */         return;
/*    */       }
/*    */       
/* 23 */       worldserver.setTypeAndData(blockposition, this.a.getBlockData(), 2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 30 */     if (!a(generatoraccess, blockposition)) {
/* 31 */       generatoraccess.getBlockTickList().a(blockposition, this, 60 + generatoraccess.getRandom().nextInt(40));
/*    */     }
/*    */     
/* 34 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */   
/*    */   protected boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 38 */     EnumDirection[] aenumdirection = EnumDirection.values();
/* 39 */     int i = aenumdirection.length;
/*    */     
/* 41 */     for (int j = 0; j < i; j++) {
/* 42 */       EnumDirection enumdirection = aenumdirection[j];
/* 43 */       Fluid fluid = iblockaccess.getFluid(blockposition.shift(enumdirection));
/*    */       
/* 45 */       if (fluid.a(TagsFluid.WATER)) {
/* 46 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 56 */     if (!a(blockactioncontext.getWorld(), blockactioncontext.getClickPosition())) {
/* 57 */       blockactioncontext.getWorld().getBlockTickList().a(blockactioncontext.getClickPosition(), this, 60 + blockactioncontext.getWorld().getRandom().nextInt(40));
/*    */     }
/*    */     
/* 60 */     return getBlockData();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCoral.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */