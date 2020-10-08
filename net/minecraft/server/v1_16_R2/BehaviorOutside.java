/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorOutside
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   private final float b;
/*    */   
/*    */   public BehaviorOutside(float var0) {
/* 21 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
/*    */     
/* 23 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 28 */     Optional<Vec3D> var4 = Optional.ofNullable(b(var0, var1));
/* 29 */     if (var4.isPresent()) {
/* 30 */       var1.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, var4.map(var0 -> new MemoryTarget(var0, this.b, 0)));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 36 */     return !var0.e(var1.getChunkCoordinates());
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private Vec3D b(WorldServer var0, EntityLiving var1) {
/* 41 */     Random var2 = var1.getRandom();
/* 42 */     BlockPosition var3 = var1.getChunkCoordinates();
/*    */     
/* 44 */     for (int var4 = 0; var4 < 10; var4++) {
/* 45 */       BlockPosition var5 = var3.b(var2.nextInt(20) - 10, var2.nextInt(6) - 3, var2.nextInt(20) - 10);
/*    */       
/* 47 */       if (a(var0, var1, var5)) {
/* 48 */         return Vec3D.c(var5);
/*    */       }
/*    */     } 
/* 51 */     return null;
/*    */   }
/*    */   
/*    */   public static boolean a(WorldServer var0, EntityLiving var1, BlockPosition var2) {
/* 55 */     return (var0.e(var2) && var0.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, var2).getY() <= var1.locY());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorOutside.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */