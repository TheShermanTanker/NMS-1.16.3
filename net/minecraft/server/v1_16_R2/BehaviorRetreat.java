/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorRetreat<E extends EntityInsentient>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final int b;
/*    */   private final float c;
/*    */   
/*    */   public BehaviorRetreat(int var0, float var1) {
/* 19 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     this.b = var0;
/* 26 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 31 */     return (a(var1) && b(var1));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 36 */     var1.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorPositionEntity(c(var1), true));
/* 37 */     var1.getControllerMove().a(-this.c, 0.0F);
/*    */ 
/*    */ 
/*    */     
/* 41 */     ((EntityInsentient)var1).yaw = MathHelper.b(((EntityInsentient)var1).yaw, ((EntityInsentient)var1).aC, 0.0F);
/*    */   }
/*    */   
/*    */   private boolean a(E var0) {
/* 45 */     return ((List)var0.getBehaviorController().<List>getMemory((MemoryModuleType)MemoryModuleType.VISIBLE_MOBS).get()).contains(c(var0));
/*    */   }
/*    */   
/*    */   private boolean b(E var0) {
/* 49 */     return c(var0).a((Entity)var0, this.b);
/*    */   }
/*    */   
/*    */   private EntityLiving c(E var0) {
/* 53 */     return var0.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.ATTACK_TARGET).get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorRetreat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */