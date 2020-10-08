/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorFindAdmirableItem<E extends EntityLiving>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final Predicate<E> b;
/*    */   private final int c;
/*    */   private final float d;
/*    */   
/*    */   public BehaviorFindAdmirableItem(float var0, boolean var1, int var2) {
/* 18 */     this(var0 -> true, var0, var1, var2);
/*    */   }
/*    */   public BehaviorFindAdmirableItem(Predicate<E> var0, float var1, boolean var2, int var3) {
/* 21 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.WALK_TARGET, var2 ? MemoryStatus.REGISTERED : MemoryStatus.VALUE_ABSENT, MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 26 */     this.b = var0;
/* 27 */     this.c = var3;
/* 28 */     this.d = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 33 */     return (this.b.test(var1) && a(var1).a((Entity)var1, this.c));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 38 */     BehaviorUtil.a((EntityLiving)var1, a(var1), this.d, 0);
/*    */   }
/*    */   
/*    */   private EntityItem a(E var0) {
/* 42 */     return var0.getBehaviorController().<EntityItem>getMemory(MemoryModuleType.NEAREST_VISIBLE_WANTED_ITEM).get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorFindAdmirableItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */