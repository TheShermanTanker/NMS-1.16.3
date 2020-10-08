/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BehaviorBonemeal
/*     */   extends Behavior<EntityVillager>
/*     */ {
/*     */   private long b;
/*     */   private long c;
/*     */   private int d;
/*  30 */   private Optional<BlockPosition> e = Optional.empty();
/*     */   
/*     */   public BehaviorBonemeal() {
/*  33 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(WorldServer var0, EntityVillager var1) {
/*  41 */     if (var1.ticksLived % 10 != 0 || (this.c != 0L && this.c + 160L > var1.ticksLived)) {
/*  42 */       return false;
/*     */     }
/*     */     
/*  45 */     if (var1.getInventory().a(Items.BONE_MEAL) <= 0) {
/*  46 */       return false;
/*     */     }
/*  48 */     this.e = b(var0, var1);
/*  49 */     return this.e.isPresent();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(WorldServer var0, EntityVillager var1, long var2) {
/*  54 */     return (this.d < 80 && this.e.isPresent());
/*     */   }
/*     */   
/*     */   private Optional<BlockPosition> b(WorldServer var0, EntityVillager var1) {
/*  58 */     BlockPosition.MutableBlockPosition var2 = new BlockPosition.MutableBlockPosition();
/*  59 */     Optional<BlockPosition> var3 = Optional.empty();
/*  60 */     int var4 = 0;
/*  61 */     for (int var5 = -1; var5 <= 1; var5++) {
/*  62 */       for (int var6 = -1; var6 <= 1; var6++) {
/*  63 */         for (int var7 = -1; var7 <= 1; var7++) {
/*  64 */           var2.a(var1.getChunkCoordinates(), var5, var6, var7);
/*  65 */           if (a(var2, var0) && 
/*  66 */             var0.random.nextInt(++var4) == 0) {
/*  67 */             var3 = Optional.of(var2.immutableCopy());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  74 */     return var3;
/*     */   }
/*     */   
/*     */   private boolean a(BlockPosition var0, WorldServer var1) {
/*  78 */     IBlockData var2 = var1.getType(var0);
/*  79 */     Block var3 = var2.getBlock();
/*  80 */     return (var3 instanceof BlockCrops && !((BlockCrops)var3).isRipe(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/*  85 */     a(var1);
/*     */     
/*  87 */     var1.setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.BONE_MEAL));
/*     */     
/*  89 */     this.b = var2;
/*  90 */     this.d = 0;
/*     */   }
/*     */   
/*     */   private void a(EntityVillager var0) {
/*  94 */     this.e.ifPresent(var1 -> {
/*     */           BehaviorTarget var2 = new BehaviorTarget(var1);
/*     */           var0.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, var2);
/*     */           var0.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var2, 0.5F, 1));
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(WorldServer var0, EntityVillager var1, long var2) {
/* 103 */     var1.setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/* 104 */     this.c = var1.ticksLived;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void d(WorldServer var0, EntityVillager var1, long var2) {
/* 109 */     BlockPosition var4 = this.e.get();
/* 110 */     if (var2 < this.b || !var4.a(var1.getPositionVector(), 1.0D)) {
/*     */       return;
/*     */     }
/*     */     
/* 114 */     ItemStack var5 = ItemStack.b;
/* 115 */     InventorySubcontainer var6 = var1.getInventory();
/* 116 */     int var7 = var6.getSize();
/* 117 */     for (int var8 = 0; var8 < var7; var8++) {
/* 118 */       ItemStack var9 = var6.getItem(var8);
/* 119 */       if (var9.getItem() == Items.BONE_MEAL) {
/* 120 */         var5 = var9;
/*     */         break;
/*     */       } 
/*     */     } 
/* 124 */     if (!var5.isEmpty() && ItemBoneMeal.a(var5, var0, var4)) {
/* 125 */       var0.triggerEffect(2005, var4, 0);
/*     */       
/* 127 */       this.e = b(var0, var1);
/* 128 */       a(var1);
/* 129 */       this.b = var2 + 40L;
/*     */     } 
/*     */     
/* 132 */     this.d++;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorBonemeal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */