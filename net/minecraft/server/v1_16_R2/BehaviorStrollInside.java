/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorStrollInside
/*    */   extends Behavior<EntityCreature>
/*    */ {
/*    */   private final float b;
/*    */   
/*    */   public BehaviorStrollInside(float var0) {
/* 20 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
/* 21 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityCreature var1) {
/* 26 */     return !var0.e(var1.getChunkCoordinates());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityCreature var1, long var2) {
/* 31 */     BlockPosition var4 = var1.getChunkCoordinates();
/* 32 */     List<BlockPosition> var5 = (List<BlockPosition>)BlockPosition.b(var4.b(-1, -1, -1), var4.b(1, 1, 1)).map(BlockPosition::immutableCopy).collect(Collectors.toList());
/* 33 */     Collections.shuffle(var5);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     Optional<BlockPosition> var6 = var5.stream().filter(var1 -> !var0.e(var1)).filter(var2 -> var0.a(var2, var1)).filter(var2 -> var0.getCubes(var1)).findFirst();
/*    */     
/* 42 */     var6.ifPresent(var1 -> var0.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var1, this.b, 0)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStrollInside.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */