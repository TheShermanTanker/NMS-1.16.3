/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class BehaviorWorkComposter
/*    */   extends BehaviorWork {
/*  9 */   private static final List<Item> b = (List<Item>)ImmutableList.of(Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void doWork(WorldServer worldserver, EntityVillager entityvillager) {
/* 15 */     Optional<GlobalPos> optional = entityvillager.getBehaviorController().getMemory(MemoryModuleType.JOB_SITE);
/*    */     
/* 17 */     if (optional.isPresent()) {
/* 18 */       GlobalPos globalpos = optional.get();
/* 19 */       IBlockData iblockdata = worldserver.getType(globalpos.getBlockPosition());
/*    */       
/* 21 */       if (iblockdata.a(Blocks.COMPOSTER)) {
/* 22 */         a(entityvillager);
/* 23 */         a(worldserver, entityvillager, globalpos, iblockdata);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void a(WorldServer worldserver, EntityVillager entityvillager, GlobalPos globalpos, IBlockData iblockdata) {
/* 30 */     BlockPosition blockposition = globalpos.getBlockPosition();
/*    */     
/* 32 */     if (((Integer)iblockdata.get(BlockComposter.a)).intValue() == 8) {
/* 33 */       iblockdata = BlockComposter.d(iblockdata, worldserver, blockposition, entityvillager);
/*    */     }
/*    */     
/* 36 */     int i = 20;
/* 37 */     boolean flag = true;
/* 38 */     int[] aint = new int[b.size()];
/* 39 */     InventorySubcontainer inventorysubcontainer = entityvillager.getInventory();
/* 40 */     int j = inventorysubcontainer.getSize();
/* 41 */     IBlockData iblockdata1 = iblockdata;
/*    */     
/* 43 */     for (int k = j - 1; k >= 0 && i > 0; k--) {
/* 44 */       ItemStack itemstack = inventorysubcontainer.getItem(k);
/* 45 */       int l = b.indexOf(itemstack.getItem());
/*    */       
/* 47 */       if (l != -1) {
/* 48 */         int i1 = itemstack.getCount();
/* 49 */         int j1 = aint[l] + i1;
/*    */         
/* 51 */         aint[l] = j1;
/* 52 */         int k1 = Math.min(Math.min(j1 - 10, i), i1);
/*    */         
/* 54 */         if (k1 > 0) {
/* 55 */           i -= k1;
/*    */           
/* 57 */           for (int l1 = 0; l1 < k1; l1++) {
/* 58 */             iblockdata1 = BlockComposter.a(iblockdata1, worldserver, itemstack, blockposition, entityvillager);
/* 59 */             if (((Integer)iblockdata1.get(BlockComposter.a)).intValue() == 7) {
/* 60 */               a(worldserver, iblockdata, blockposition, iblockdata1);
/*    */               
/*    */               return;
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 68 */     a(worldserver, iblockdata, blockposition, iblockdata1);
/*    */   }
/*    */   
/*    */   private void a(WorldServer worldserver, IBlockData iblockdata, BlockPosition blockposition, IBlockData iblockdata1) {
/* 72 */     worldserver.triggerEffect(1500, blockposition, (iblockdata1 != iblockdata) ? 1 : 0);
/*    */   }
/*    */   
/*    */   private void a(EntityVillager entityvillager) {
/* 76 */     InventorySubcontainer inventorysubcontainer = entityvillager.getInventory();
/*    */     
/* 78 */     if (inventorysubcontainer.a(Items.BREAD) <= 36) {
/* 79 */       int i = inventorysubcontainer.a(Items.WHEAT);
/* 80 */       boolean flag = true;
/* 81 */       boolean flag1 = true;
/* 82 */       int j = Math.min(3, i / 3);
/*    */       
/* 84 */       if (j != 0) {
/* 85 */         int k = j * 3;
/*    */         
/* 87 */         inventorysubcontainer.a(Items.WHEAT, k);
/* 88 */         ItemStack itemstack = inventorysubcontainer.a(new ItemStack(Items.BREAD, j));
/*    */         
/* 90 */         if (!itemstack.isEmpty())
/* 91 */           entityvillager.a(itemstack, 0.5F); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorWorkComposter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */