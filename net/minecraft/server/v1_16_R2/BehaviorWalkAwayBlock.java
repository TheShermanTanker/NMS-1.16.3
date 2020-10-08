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
/*     */ public class BehaviorWalkAwayBlock
/*     */   extends Behavior<EntityVillager>
/*     */ {
/*     */   private final MemoryModuleType<GlobalPos> b;
/*     */   private final float c;
/*     */   private final int d;
/*     */   private final int e;
/*     */   private final int f;
/*     */   
/*     */   public BehaviorWalkAwayBlock(MemoryModuleType<GlobalPos> var0, float var1, int var2, int var3, int var4) {
/*  34 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryStatus.REGISTERED, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, var0, MemoryStatus.VALUE_PRESENT));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  39 */     this.b = var0;
/*  40 */     this.c = var1;
/*  41 */     this.d = var2;
/*  42 */     this.e = var3;
/*  43 */     this.f = var4;
/*     */   }
/*     */   
/*     */   private void a(EntityVillager var0, long var1) {
/*  47 */     BehaviorController<?> var3 = var0.getBehaviorController();
/*     */     
/*  49 */     var0.a(this.b);
/*  50 */     var3.removeMemory(this.b);
/*  51 */     var3.setMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, Long.valueOf(var1));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/*  56 */     BehaviorController<?> var4 = var1.getBehaviorController();
/*  57 */     var4.<GlobalPos>getMemory(this.b).ifPresent(var5 -> {
/*     */           if (a(var0, var5) || a(var0, var1)) {
/*     */             a(var1, var2);
/*     */           } else if (a(var1, var5)) {
/*     */             Vec3D var6 = null;
/*     */             int var7 = 0;
/*     */             int var8 = 1000;
/*     */             while (var7 < 1000 && (var6 == null || a(var1, GlobalPos.create(var0.getDimensionKey(), new BlockPosition(var6))))) {
/*     */               var6 = RandomPositionGenerator.b(var1, 15, 7, Vec3D.c(var5.getBlockPosition()));
/*     */               var7++;
/*     */             } 
/*     */             if (var7 == 1000) {
/*     */               a(var1, var2);
/*     */               return;
/*     */             } 
/*     */             var4.setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var6, this.c, this.d));
/*     */           } else if (!a(var0, var1, var5)) {
/*     */             var4.setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var5.getBlockPosition(), this.c, this.d));
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(WorldServer var0, EntityVillager var1) {
/*  88 */     Optional<Long> var2 = var1.getBehaviorController().getMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
/*  89 */     if (var2.isPresent()) {
/*  90 */       return (var0.getTime() - ((Long)var2.get()).longValue() > this.f);
/*     */     }
/*  92 */     return false;
/*     */   }
/*     */   
/*     */   private boolean a(EntityVillager var0, GlobalPos var1) {
/*  96 */     return (var1.getBlockPosition().k(var0.getChunkCoordinates()) > this.e);
/*     */   }
/*     */   
/*     */   private boolean a(WorldServer var0, GlobalPos var1) {
/* 100 */     return (var1.getDimensionManager() != var0.getDimensionKey());
/*     */   }
/*     */   
/*     */   private boolean a(WorldServer var0, EntityVillager var1, GlobalPos var2) {
/* 104 */     return (var2.getDimensionManager() == var0.getDimensionKey() && var2
/* 105 */       .getBlockPosition().k(var1.getChunkCoordinates()) <= this.d);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorWalkAwayBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */