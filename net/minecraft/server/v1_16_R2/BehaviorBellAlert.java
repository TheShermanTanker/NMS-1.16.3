/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorBellAlert
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   public BehaviorBellAlert() {
/* 14 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.HEARD_BELL_TIME, MemoryStatus.VALUE_PRESENT));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 19 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 20 */     Raid var5 = var0.b_(var1.getChunkCoordinates());
/*    */ 
/*    */     
/* 23 */     if (var5 == null)
/* 24 */       var4.a(Activity.HIDE); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorBellAlert.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */