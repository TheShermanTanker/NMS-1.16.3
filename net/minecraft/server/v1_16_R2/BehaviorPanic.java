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
/*    */ public class BehaviorPanic
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   public BehaviorPanic() {
/* 16 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of());
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b(WorldServer var0, EntityVillager var1, long var2) {
/* 21 */     return (b(var1) || a(var1));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/* 26 */     if (b(var1) || a(var1)) {
/* 27 */       BehaviorController<?> var4 = var1.getBehaviorController();
/*    */ 
/*    */       
/* 30 */       if (!var4.c(Activity.PANIC)) {
/* 31 */         var4.removeMemory(MemoryModuleType.PATH);
/* 32 */         var4.removeMemory(MemoryModuleType.WALK_TARGET);
/* 33 */         var4.removeMemory(MemoryModuleType.LOOK_TARGET);
/* 34 */         var4.removeMemory(MemoryModuleType.BREED_TARGET);
/* 35 */         var4.removeMemory(MemoryModuleType.INTERACTION_TARGET);
/*    */       } 
/* 37 */       var4.a(Activity.PANIC);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void d(WorldServer var0, EntityVillager var1, long var2) {
/* 43 */     if (var2 % 100L == 0L) {
/* 44 */       var1.a(var0, var2, 3);
/*    */     }
/*    */   }
/*    */   
/*    */   public static boolean a(EntityLiving var0) {
/* 49 */     return var0.getBehaviorController().hasMemory(MemoryModuleType.NEAREST_HOSTILE);
/*    */   }
/*    */   
/*    */   public static boolean b(EntityLiving var0) {
/* 53 */     return var0.getBehaviorController().hasMemory(MemoryModuleType.HURT_BY);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorPanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */