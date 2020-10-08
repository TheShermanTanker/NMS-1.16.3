/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BehaviorFarm
/*     */   extends Behavior<EntityVillager> {
/*     */   @Nullable
/*     */   private BlockPosition farmBlock;
/*  14 */   private final List<BlockPosition> e = Lists.newArrayList(); private long c; private int d;
/*     */   
/*     */   public BehaviorFarm() {
/*  17 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.SECONDARY_JOB_SITE, MemoryStatus.VALUE_PRESENT));
/*     */   }
/*     */   
/*     */   protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
/*  21 */     if (!worldserver.getGameRules().getBoolean(GameRules.MOB_GRIEFING))
/*  22 */       return false; 
/*  23 */     if (entityvillager.getVillagerData().getProfession() != VillagerProfession.FARMER) {
/*  24 */       return false;
/*     */     }
/*  26 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = entityvillager.getChunkCoordinates().i();
/*     */     
/*  28 */     this.e.clear();
/*     */     
/*  30 */     for (int i = -1; i <= 1; i++) {
/*  31 */       for (int j = -1; j <= 1; j++) {
/*  32 */         for (int k = -1; k <= 1; k++) {
/*  33 */           blockposition_mutableblockposition.c(entityvillager.locX() + i, entityvillager.locY() + j, entityvillager.locZ() + k);
/*  34 */           if (a(blockposition_mutableblockposition, worldserver)) {
/*  35 */             this.e.add(new BlockPosition(blockposition_mutableblockposition));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  41 */     this.farmBlock = a(worldserver);
/*  42 */     return (this.farmBlock != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private BlockPosition a(WorldServer worldserver) {
/*  48 */     return this.e.isEmpty() ? null : this.e.get(worldserver.getRandom().nextInt(this.e.size()));
/*     */   }
/*     */   
/*     */   private boolean a(BlockPosition blockposition, WorldServer worldserver) {
/*  52 */     IBlockData iblockdata = worldserver.getType(blockposition);
/*  53 */     Block block = iblockdata.getBlock();
/*  54 */     Block block1 = worldserver.getType(blockposition.down()).getBlock();
/*     */     
/*  56 */     return ((block instanceof BlockCrops && ((BlockCrops)block).isRipe(iblockdata)) || (iblockdata.isAir() && block1 instanceof BlockSoil));
/*     */   }
/*     */   
/*     */   protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
/*  60 */     if (i > this.c && this.farmBlock != null) {
/*  61 */       entityvillager.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorTarget(this.farmBlock));
/*  62 */       entityvillager.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(new BehaviorTarget(this.farmBlock), 0.5F, 1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(WorldServer worldserver, EntityVillager entityvillager, long i) {
/*  68 */     entityvillager.getBehaviorController().removeMemory(MemoryModuleType.LOOK_TARGET);
/*  69 */     entityvillager.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
/*  70 */     this.d = 0;
/*  71 */     this.c = i + 40L;
/*     */   }
/*     */   
/*     */   protected void d(WorldServer worldserver, EntityVillager entityvillager, long i) {
/*  75 */     if (this.farmBlock == null || this.farmBlock.a(entityvillager.getPositionVector(), 1.0D)) {
/*  76 */       if (this.farmBlock != null && i > this.c) {
/*  77 */         IBlockData iblockdata = worldserver.getType(this.farmBlock);
/*  78 */         Block block = iblockdata.getBlock();
/*  79 */         Block block1 = worldserver.getType(this.farmBlock.down()).getBlock();
/*     */         
/*  81 */         if (block instanceof BlockCrops && ((BlockCrops)block).isRipe(iblockdata))
/*     */         {
/*  83 */           if (!CraftEventFactory.callEntityChangeBlockEvent(entityvillager, this.farmBlock, Blocks.AIR.getBlockData()).isCancelled()) {
/*  84 */             worldserver.a(this.farmBlock, true, entityvillager);
/*     */           }
/*     */         }
/*     */ 
/*     */         
/*  89 */         if (iblockdata.isAir() && block1 instanceof BlockSoil && entityvillager.canPlant()) {
/*  90 */           InventorySubcontainer inventorysubcontainer = entityvillager.getInventory();
/*     */           
/*  92 */           for (int j = 0; j < inventorysubcontainer.getSize(); j++) {
/*  93 */             ItemStack itemstack = inventorysubcontainer.getItem(j);
/*  94 */             boolean flag = false;
/*     */             
/*  96 */             if (!itemstack.isEmpty()) {
/*     */               
/*  98 */               Block planted = null;
/*  99 */               if (itemstack.getItem() == Items.WHEAT_SEEDS) {
/* 100 */                 planted = Blocks.WHEAT;
/* 101 */                 flag = true;
/* 102 */               } else if (itemstack.getItem() == Items.POTATO) {
/* 103 */                 planted = Blocks.POTATOES;
/* 104 */                 flag = true;
/* 105 */               } else if (itemstack.getItem() == Items.CARROT) {
/* 106 */                 planted = Blocks.CARROTS;
/* 107 */                 flag = true;
/* 108 */               } else if (itemstack.getItem() == Items.BEETROOT_SEEDS) {
/* 109 */                 planted = Blocks.BEETROOTS;
/* 110 */                 flag = true;
/*     */               } 
/*     */               
/* 113 */               if (planted != null && !CraftEventFactory.callEntityChangeBlockEvent(entityvillager, this.farmBlock, planted.getBlockData()).isCancelled()) {
/* 114 */                 worldserver.setTypeAndData(this.farmBlock, planted.getBlockData(), 3);
/*     */               } else {
/* 116 */                 flag = false;
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 121 */             if (flag) {
/* 122 */               worldserver.playSound((EntityHuman)null, this.farmBlock.getX(), this.farmBlock.getY(), this.farmBlock.getZ(), SoundEffects.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0F, 1.0F);
/* 123 */               itemstack.subtract(1);
/* 124 */               if (itemstack.isEmpty()) {
/* 125 */                 inventorysubcontainer.setItem(j, ItemStack.b);
/*     */               }
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/* 132 */         if (block instanceof BlockCrops && !((BlockCrops)block).isRipe(iblockdata)) {
/* 133 */           this.e.remove(this.farmBlock);
/* 134 */           this.farmBlock = a(worldserver);
/* 135 */           if (this.farmBlock != null) {
/* 136 */             this.c = i + 20L;
/* 137 */             entityvillager.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(new BehaviorTarget(this.farmBlock), 0.5F, 1));
/* 138 */             entityvillager.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorTarget(this.farmBlock));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 143 */       this.d++;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean b(WorldServer worldserver, EntityVillager entityvillager, long i) {
/* 148 */     return (this.d < 200);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorFarm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */