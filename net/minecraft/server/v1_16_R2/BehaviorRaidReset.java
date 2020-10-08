/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorRaidReset
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   public BehaviorRaidReset() {
/* 12 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of());
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 17 */     return (var0.random.nextInt(20) == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 22 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 23 */     Raid var5 = var0.b_(var1.getChunkCoordinates());
/*    */     
/* 25 */     if (var5 == null || var5.isStopped() || var5.isLoss()) {
/*    */       
/* 27 */       var4.b(Activity.IDLE);
/* 28 */       var4.a(var0.getDayTime(), var0.getTime());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorRaidReset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */