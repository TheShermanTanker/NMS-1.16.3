/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class BehaviorSchedule
/*    */   extends Behavior<EntityLiving> {
/*    */   public BehaviorSchedule() {
/*  9 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 14 */     var1.getBehaviorController().a(var0.getDayTime(), var0.getTime());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorSchedule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */