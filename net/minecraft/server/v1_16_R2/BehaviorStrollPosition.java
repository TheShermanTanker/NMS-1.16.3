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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorStrollPosition
/*    */   extends Behavior<EntityCreature>
/*    */ {
/*    */   private final MemoryModuleType<GlobalPos> b;
/*    */   private long c;
/*    */   private final int d;
/*    */   private float e;
/*    */   
/*    */   public BehaviorStrollPosition(MemoryModuleType<GlobalPos> var0, float var1, int var2) {
/* 30 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, var0, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     this.b = var0;
/* 36 */     this.e = var1;
/* 37 */     this.d = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityCreature var1) {
/* 42 */     Optional<GlobalPos> var2 = var1.getBehaviorController().getMemory(this.b);
/* 43 */     return (var2.isPresent() && var0
/* 44 */       .getDimensionKey() == ((GlobalPos)var2.get()).getDimensionManager() && ((GlobalPos)var2
/* 45 */       .get()).getBlockPosition().a(var1.getPositionVector(), this.d));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityCreature var1, long var2) {
/* 50 */     if (var2 > this.c) {
/* 51 */       Optional<Vec3D> var4 = Optional.ofNullable(RandomPositionGenerator.b(var1, 8, 6));
/* 52 */       var1.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, var4.map(var0 -> new MemoryTarget(var0, this.e, 1)));
/* 53 */       this.c = var2 + 180L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStrollPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */