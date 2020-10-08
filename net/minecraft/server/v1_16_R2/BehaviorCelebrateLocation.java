/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorCelebrateLocation<E extends EntityInsentient>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final int b;
/*    */   private final float c;
/*    */   
/*    */   public BehaviorCelebrateLocation(int var0, float var1) {
/* 17 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.CELEBRATE_LOCATION, MemoryStatus.VALUE_PRESENT, MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     this.b = var0;
/* 24 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityInsentient var1, long var2) {
/* 29 */     BlockPosition var4 = a(var1);
/* 30 */     boolean var5 = var4.a(var1.getChunkCoordinates(), this.b);
/* 31 */     if (!var5) {
/* 32 */       BehaviorUtil.a(var1, a(var1, var4), this.c, this.b);
/*    */     }
/*    */   }
/*    */   
/*    */   private static BlockPosition a(EntityInsentient var0, BlockPosition var1) {
/* 37 */     Random var2 = var0.world.random;
/* 38 */     return var1.b(a(var2), 0, a(var2));
/*    */   }
/*    */   
/*    */   private static int a(Random var0) {
/* 42 */     return var0.nextInt(3) - 1;
/*    */   }
/*    */   
/*    */   private static BlockPosition a(EntityInsentient var0) {
/* 46 */     return var0.getBehaviorController().<BlockPosition>getMemory(MemoryModuleType.CELEBRATE_LOCATION).get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorCelebrateLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */