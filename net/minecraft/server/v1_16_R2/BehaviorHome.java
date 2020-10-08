/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorHome
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   private final float b;
/*    */   private final int c;
/*    */   private final int d;
/* 21 */   private Optional<BlockPosition> e = Optional.empty();
/*    */   
/*    */   public BehaviorHome(int var0, float var1, int var2) {
/* 24 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.HOME, MemoryStatus.REGISTERED, MemoryModuleType.HIDING_PLACE, MemoryStatus.REGISTERED));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     this.c = var0;
/* 31 */     this.b = var1;
/* 32 */     this.d = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 37 */     Optional<BlockPosition> var2 = var0.y().c(var0 -> (var0 == VillagePlaceType.r), var0 -> true, var1.getChunkCoordinates(), this.d + 1, VillagePlace.Occupancy.ANY);
/*    */     
/* 39 */     if (var2.isPresent() && ((BlockPosition)var2.get()).a(var1.getPositionVector(), this.d)) {
/* 40 */       this.e = var2;
/*    */     } else {
/* 42 */       this.e = Optional.empty();
/*    */     } 
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 50 */     BehaviorController<?> var4 = var1.getBehaviorController();
/*    */     
/* 52 */     Optional<BlockPosition> var5 = this.e;
/*    */     
/* 54 */     if (!var5.isPresent()) {
/* 55 */       var5 = var0.y().a(var0 -> (var0 == VillagePlaceType.r), var0 -> true, VillagePlace.Occupancy.ANY, var1.getChunkCoordinates(), this.c, var1.getRandom());
/* 56 */       if (!var5.isPresent()) {
/* 57 */         Optional<GlobalPos> var6 = var4.getMemory(MemoryModuleType.HOME);
/* 58 */         if (var6.isPresent()) {
/* 59 */           var5 = Optional.of(((GlobalPos)var6.get()).getBlockPosition());
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 64 */     if (var5.isPresent()) {
/* 65 */       var4.removeMemory(MemoryModuleType.PATH);
/* 66 */       var4.removeMemory(MemoryModuleType.LOOK_TARGET);
/* 67 */       var4.removeMemory(MemoryModuleType.BREED_TARGET);
/* 68 */       var4.removeMemory(MemoryModuleType.INTERACTION_TARGET);
/* 69 */       var4.setMemory(MemoryModuleType.HIDING_PLACE, GlobalPos.create(var0.getDimensionKey(), var5.get()));
/* 70 */       if (!((BlockPosition)var5.get()).a(var1.getPositionVector(), this.d))
/* 71 */         var4.setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var5.get(), this.b, this.d)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorHome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */