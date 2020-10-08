/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.BiPredicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorStopRiding<E extends EntityLiving, T extends Entity>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final int b;
/*    */   private final BiPredicate<E, Entity> c;
/*    */   
/*    */   public BehaviorStopRiding(int var0, BiPredicate<E, Entity> var1) {
/* 21 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.RIDE_TARGET, MemoryStatus.REGISTERED));
/*    */ 
/*    */     
/* 24 */     this.b = var0;
/* 25 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 30 */     Entity var2 = var1.getVehicle();
/* 31 */     Entity var3 = var1.getBehaviorController().<Entity>getMemory(MemoryModuleType.RIDE_TARGET).orElse(null);
/* 32 */     if (var2 == null && var3 == null) {
/* 33 */       return false;
/*    */     }
/* 35 */     Entity var4 = (var2 == null) ? var3 : var2;
/* 36 */     return (!a(var1, var4) || this.c.test(var1, var4));
/*    */   }
/*    */   
/*    */   private boolean a(E var0, Entity var1) {
/* 40 */     return (var1.isAlive() && var1
/* 41 */       .a((Entity)var0, this.b) && var1.world == ((EntityLiving)var0).world);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 47 */     var1.stopRiding();
/* 48 */     var1.getBehaviorController().removeMemory(MemoryModuleType.RIDE_TARGET);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStopRiding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */