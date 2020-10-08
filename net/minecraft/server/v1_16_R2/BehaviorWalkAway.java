/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorWalkAway<T>
/*    */   extends Behavior<EntityCreature>
/*    */ {
/*    */   private final MemoryModuleType<T> b;
/*    */   private final float c;
/*    */   private final int d;
/*    */   private final Function<T, Vec3D> e;
/*    */   
/*    */   public BehaviorWalkAway(MemoryModuleType<T> var0, float var1, int var2, boolean var3, Function<T, Vec3D> var4) {
/* 23 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, var3 ? MemoryStatus.REGISTERED : MemoryStatus.VALUE_ABSENT, var0, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     this.b = var0;
/* 29 */     this.c = var1;
/* 30 */     this.d = var2;
/* 31 */     this.e = var4;
/*    */   }
/*    */   
/*    */   public static BehaviorWalkAway<BlockPosition> a(MemoryModuleType<BlockPosition> var0, float var1, int var2, boolean var3) {
/* 35 */     return new BehaviorWalkAway<>(var0, var1, var2, var3, Vec3D::c);
/*    */   }
/*    */   
/*    */   public static BehaviorWalkAway<? extends Entity> b(MemoryModuleType<? extends Entity> var0, float var1, int var2, boolean var3) {
/* 39 */     return new BehaviorWalkAway<>(var0, var1, var2, var3, Entity::getPositionVector);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityCreature var1) {
/* 44 */     if (b(var1)) {
/* 45 */       return false;
/*    */     }
/* 47 */     return var1.getPositionVector().a(a(var1), this.d);
/*    */   }
/*    */   
/*    */   private Vec3D a(EntityCreature var0) {
/* 51 */     return this.e.apply(var0.getBehaviorController().<T>getMemory(this.b).get());
/*    */   }
/*    */   
/*    */   private boolean b(EntityCreature var0) {
/* 55 */     if (!var0.getBehaviorController().hasMemory(MemoryModuleType.WALK_TARGET)) {
/* 56 */       return false;
/*    */     }
/*    */     
/* 59 */     MemoryTarget var1 = var0.getBehaviorController().<MemoryTarget>getMemory(MemoryModuleType.WALK_TARGET).get();
/* 60 */     if (var1.b() != this.c) {
/* 61 */       return false;
/*    */     }
/*    */     
/* 64 */     Vec3D var2 = var1.a().a().d(var0.getPositionVector());
/* 65 */     Vec3D var3 = a(var0).d(var0.getPositionVector());
/* 66 */     return (var2.b(var3) < 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityCreature var1, long var2) {
/* 71 */     a(var1, a(var1), this.c);
/*    */   }
/*    */   
/*    */   private static void a(EntityCreature var0, Vec3D var1, float var2) {
/* 75 */     for (int var3 = 0; var3 < 10; var3++) {
/* 76 */       Vec3D var4 = RandomPositionGenerator.d(var0, 16, 7, var1);
/*    */       
/* 78 */       if (var4 != null) {
/* 79 */         var0.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var4, var2, 0));
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorWalkAway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */