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
/*    */ public class BehaviorStrollPlace
/*    */   extends Behavior<EntityCreature>
/*    */ {
/*    */   private final MemoryModuleType<GlobalPos> b;
/*    */   private final int c;
/*    */   private final int d;
/*    */   private final float e;
/*    */   private long f;
/*    */   
/*    */   public BehaviorStrollPlace(MemoryModuleType<GlobalPos> var0, float var1, int var2, int var3) {
/* 23 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, var0, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     this.b = var0;
/* 29 */     this.e = var1;
/* 30 */     this.c = var2;
/* 31 */     this.d = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityCreature var1) {
/* 36 */     Optional<GlobalPos> var2 = var1.getBehaviorController().getMemory(this.b);
/* 37 */     return (var2.isPresent() && var0.getDimensionKey() == ((GlobalPos)var2.get()).getDimensionManager() && ((GlobalPos)var2.get()).getBlockPosition().a(var1.getPositionVector(), this.d));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityCreature var1, long var2) {
/* 42 */     if (var2 > this.f) {
/* 43 */       BehaviorController<?> var4 = var1.getBehaviorController();
/* 44 */       Optional<GlobalPos> var5 = var4.getMemory(this.b);
/* 45 */       var5.ifPresent(var1 -> var0.setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var1.getBlockPosition(), this.e, this.c)));
/* 46 */       this.f = var2 + 80L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStrollPlace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */