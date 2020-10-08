/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorHide
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   private final int b;
/*    */   private final int c;
/*    */   private int d;
/*    */   
/*    */   public BehaviorHide(int var0, int var1) {
/* 29 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.HIDING_PLACE, MemoryStatus.VALUE_PRESENT, MemoryModuleType.HEARD_BELL_TIME, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     this.c = var0 * 20;
/* 35 */     this.d = 0;
/* 36 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 41 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 42 */     Optional<Long> var5 = var4.getMemory(MemoryModuleType.HEARD_BELL_TIME);
/* 43 */     boolean var6 = (((Long)var5.get()).longValue() + 300L <= var2);
/*    */     
/* 45 */     if (this.d > this.c || var6) {
/* 46 */       var4.removeMemory(MemoryModuleType.HEARD_BELL_TIME);
/* 47 */       var4.removeMemory(MemoryModuleType.HIDING_PLACE);
/* 48 */       var4.a(var0.getDayTime(), var0.getTime());
/* 49 */       this.d = 0;
/*    */       
/*    */       return;
/*    */     } 
/* 53 */     BlockPosition var7 = ((GlobalPos)var4.<GlobalPos>getMemory(MemoryModuleType.HIDING_PLACE).get()).getBlockPosition();
/* 54 */     if (var7.a(var1.getChunkCoordinates(), this.b))
/* 55 */       this.d++; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorHide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */