/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorFollowAdult<E extends EntityAgeable>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final IntRange b;
/*    */   private final float c;
/*    */   
/*    */   public BehaviorFollowAdult(IntRange var0, float var1) {
/* 15 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.NEAREST_VISIBLE_ADULY, MemoryStatus.VALUE_PRESENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
/*    */ 
/*    */ 
/*    */     
/* 19 */     this.b = var0;
/* 20 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 25 */     if (!var1.isBaby()) {
/* 26 */       return false;
/*    */     }
/* 28 */     EntityAgeable var2 = a(var1);
/* 29 */     return (var1.a(var2, (this.b.b() + 1)) && 
/* 30 */       !var1.a(var2, this.b.a()));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 35 */     BehaviorUtil.a((EntityLiving)var1, a(var1), this.c, this.b.a() - 1);
/*    */   }
/*    */   
/*    */   private EntityAgeable a(E var0) {
/* 39 */     return var0.getBehaviorController().<EntityAgeable>getMemory(MemoryModuleType.NEAREST_VISIBLE_ADULY).get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorFollowAdult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */