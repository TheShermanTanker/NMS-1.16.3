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
/*    */ 
/*    */ 
/*    */ public class BehaviorStrollRandom
/*    */   extends Behavior<EntityCreature>
/*    */ {
/*    */   private final float b;
/*    */   private final int c;
/*    */   private final int d;
/*    */   
/*    */   public BehaviorStrollRandom(float var0) {
/* 31 */     this(var0, 10, 7);
/*    */   }
/*    */   
/*    */   public BehaviorStrollRandom(float var0, int var1, int var2) {
/* 35 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
/*    */ 
/*    */     
/* 38 */     this.b = var0;
/* 39 */     this.c = var1;
/* 40 */     this.d = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityCreature var1, long var2) {
/* 45 */     BlockPosition var4 = var1.getChunkCoordinates();
/* 46 */     if (var0.a_(var4)) {
/* 47 */       a(var1);
/*    */     } else {
/* 49 */       SectionPosition var5 = SectionPosition.a(var4);
/* 50 */       SectionPosition var6 = BehaviorUtil.a(var0, var5, 2);
/*    */       
/* 52 */       if (var6 != var5) {
/* 53 */         a(var1, var6);
/*    */       } else {
/* 55 */         a(var1);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void a(EntityCreature var0, SectionPosition var1) {
/* 61 */     Optional<Vec3D> var2 = Optional.ofNullable(RandomPositionGenerator.b(var0, this.c, this.d, Vec3D.c(var1.q())));
/* 62 */     var0.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, var2.map(var0 -> new MemoryTarget(var0, this.b, 0)));
/*    */   }
/*    */   
/*    */   private void a(EntityCreature var0) {
/* 66 */     Optional<Vec3D> var1 = Optional.ofNullable(RandomPositionGenerator.b(var0, this.c, this.d));
/* 67 */     var0.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, var1.map(var0 -> new MemoryTarget(var0, this.b, 0)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStrollRandom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */