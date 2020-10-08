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
/*    */ public class BehaviorStrollRandomUnconstrained
/*    */   extends Behavior<EntityCreature>
/*    */ {
/*    */   private final float b;
/*    */   private final int c;
/*    */   private final int d;
/*    */   
/*    */   public BehaviorStrollRandomUnconstrained(float var0) {
/* 27 */     this(var0, 10, 7);
/*    */   }
/*    */   
/*    */   public BehaviorStrollRandomUnconstrained(float var0, int var1, int var2) {
/* 31 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
/*    */ 
/*    */     
/* 34 */     this.b = var0;
/* 35 */     this.c = var1;
/* 36 */     this.d = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityCreature var1, long var2) {
/* 41 */     Optional<Vec3D> var4 = Optional.ofNullable(RandomPositionGenerator.b(var1, this.c, this.d));
/* 42 */     var1.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, var4.map(var0 -> new MemoryTarget(var0, this.b, 0)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStrollRandomUnconstrained.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */