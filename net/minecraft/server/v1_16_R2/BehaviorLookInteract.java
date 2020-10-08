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
/*    */ public class BehaviorLookInteract
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   private final EntityTypes<?> b;
/*    */   private final int c;
/*    */   private final Predicate<EntityLiving> d;
/*    */   private final Predicate<EntityLiving> e;
/*    */   
/*    */   public BehaviorLookInteract(EntityTypes<?> var0, int var1, Predicate<EntityLiving> var2, Predicate<EntityLiving> var3) {
/* 21 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.INTERACTION_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 27 */     this.b = var0;
/* 28 */     this.c = var1 * var1;
/* 29 */     this.d = var3;
/* 30 */     this.e = var2;
/*    */   }
/*    */   
/*    */   public BehaviorLookInteract(EntityTypes<?> var0, int var1) {
/* 34 */     this(var0, var1, var0 -> true, var0 -> true);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(WorldServer var0, EntityLiving var1) {
/* 39 */     return (this.e.test(var1) && b(var1).stream().anyMatch(this::a));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer var0, EntityLiving var1, long var2) {
/* 44 */     super.a(var0, var1, var2);
/* 45 */     BehaviorController<?> var4 = var1.getBehaviorController();
/*    */     
/* 47 */     var4.<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS).ifPresent(var2 -> var2.stream().filter(()).filter(this::a).findFirst().ifPresent(()));
/*    */   }
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
/*    */   private boolean a(EntityLiving var0) {
/* 60 */     return (this.b.equals(var0.getEntityType()) && this.d.test(var0));
/*    */   }
/*    */   
/*    */   private List<EntityLiving> b(EntityLiving var0) {
/* 64 */     return var0.getBehaviorController().<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS).get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorLookInteract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */