/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorRaid
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   public BehaviorRaid() {
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
/* 25 */     if (var5 != null)
/*    */     {
/* 27 */       if (!var5.c() || var5.b()) {
/* 28 */         var4.b(Activity.PRE_RAID);
/* 29 */         var4.a(Activity.PRE_RAID);
/*    */       } else {
/* 31 */         var4.b(Activity.RAID);
/* 32 */         var4.a(Activity.RAID);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorRaid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */