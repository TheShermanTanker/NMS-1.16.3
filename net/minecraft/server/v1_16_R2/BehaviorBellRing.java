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
/*    */ 
/*    */ public class BehaviorBellRing
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   public BehaviorBellRing() {
/* 19 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.MEETING_POINT, MemoryStatus.VALUE_PRESENT));
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 24 */     return (var0.random.nextFloat() > 0.95F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 29 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 30 */     BlockPosition var5 = ((GlobalPos)var4.<GlobalPos>getMemory(MemoryModuleType.MEETING_POINT).get()).getBlockPosition();
/*    */     
/* 32 */     if (var5.a(var1.getChunkCoordinates(), 3.0D)) {
/* 33 */       IBlockData var6 = var0.getType(var5);
/* 34 */       if (var6.a(Blocks.BELL)) {
/* 35 */         BlockBell var7 = (BlockBell)var6.getBlock();
/* 36 */         var7.a(var0, var5, (EnumDirection)null);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorBellRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */