/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorAttack
/*    */   extends Behavior<EntityInsentient>
/*    */ {
/*    */   private final int b;
/*    */   
/*    */   public BehaviorAttack(int var0) {
/* 20 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryStatus.VALUE_ABSENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityInsentient var1) {
/* 30 */     EntityLiving var2 = b(var1);
/* 31 */     return (!a(var1) && 
/* 32 */       BehaviorUtil.c(var1, var2) && 
/* 33 */       BehaviorUtil.b(var1, var2));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean a(EntityInsentient var0) {
/* 38 */     return var0.a(var1 -> (var1 instanceof ItemProjectileWeapon && var0.a((ItemProjectileWeapon)var1)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityInsentient var1, long var2) {
/* 43 */     EntityLiving var4 = b(var1);
/* 44 */     BehaviorUtil.a(var1, var4);
/* 45 */     var1.swingHand(EnumHand.MAIN_HAND);
/* 46 */     var1.attackEntity(var4);
/* 47 */     var1.getBehaviorController().a(MemoryModuleType.ATTACK_COOLING_DOWN, Boolean.valueOf(true), this.b);
/*    */   }
/*    */   
/*    */   private EntityLiving b(EntityInsentient var0) {
/* 51 */     return var0.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.ATTACK_TARGET).get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */