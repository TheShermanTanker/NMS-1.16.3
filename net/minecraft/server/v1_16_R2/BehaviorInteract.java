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
/*    */ public class BehaviorInteract<E extends EntityLiving, T extends EntityLiving>
/*    */   extends Behavior<E>
/*    */ {
/*    */   private final int b;
/*    */   private final float c;
/*    */   private final EntityTypes<? extends T> d;
/*    */   private final int e;
/*    */   private final Predicate<T> f;
/*    */   private final Predicate<E> g;
/*    */   private final MemoryModuleType<T> h;
/*    */   
/*    */   public BehaviorInteract(EntityTypes<? extends T> var0, int var1, Predicate<E> var2, Predicate<T> var3, MemoryModuleType<T> var4, float var5, int var6) {
/* 28 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     this.d = var0;
/* 35 */     this.c = var5;
/* 36 */     this.e = var1 * var1;
/* 37 */     this.b = var6;
/* 38 */     this.f = var3;
/* 39 */     this.g = var2;
/* 40 */     this.h = var4;
/*    */   }
/*    */   
/*    */   public static <T extends EntityLiving> BehaviorInteract<EntityLiving, T> a(EntityTypes<? extends T> var0, int var1, MemoryModuleType<T> var2, float var3, int var4) {
/* 44 */     return new BehaviorInteract<>(var0, var1, var0 -> true, var0 -> true, var2, var3, var4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, E var1) {
/* 53 */     return (this.g.test(var1) && a(var1));
/*    */   }
/*    */   
/*    */   private boolean a(E var0) {
/* 57 */     List<EntityLiving> var1 = var0.getBehaviorController().<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS).get();
/* 58 */     return var1.stream().anyMatch(this::b);
/*    */   }
/*    */   
/*    */   private boolean b(EntityLiving var0) {
/* 62 */     return (this.d.equals(var0.getEntityType()) && this.f.test((T)var0));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, E var1, long var2) {
/* 67 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 68 */     var4.<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS).ifPresent(var2 -> var2.stream().filter(()).map(()).filter(()).filter(this.f).findFirst().ifPresent(()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorInteract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */