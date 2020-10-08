/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
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
/*    */ public class BehaviorLookTarget
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   private final Predicate<EntityLiving> b;
/*    */   private final float c;
/*    */   
/*    */   public BehaviorLookTarget(EnumCreatureType var0, float var1) {
/* 28 */     this(var1 -> var0.equals(var1.getEntityType().e()), var1);
/*    */   }
/*    */   
/*    */   public BehaviorLookTarget(EntityTypes<?> var0, float var1) {
/* 32 */     this(var1 -> var0.equals(var1.getEntityType()), var1);
/*    */   }
/*    */   
/*    */   public BehaviorLookTarget(float var0) {
/* 36 */     this(var0 -> true, var0);
/*    */   }
/*    */   
/*    */   public BehaviorLookTarget(Predicate<EntityLiving> var0, float var1) {
/* 40 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */     
/* 44 */     this.b = var0;
/* 45 */     this.c = var1 * var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 50 */     return ((List<EntityLiving>)var1.getBehaviorController().<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS).get()).stream()
/* 51 */       .anyMatch(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 56 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 57 */     var4.<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS).ifPresent(var2 -> var2.stream().filter(this.b).filter(()).findFirst().ifPresent(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorLookTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */