/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorNearestVillage
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   private final float b;
/*    */   private final int c;
/*    */   
/*    */   public BehaviorNearestVillage(float var0, int var1) {
/* 20 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
/*    */ 
/*    */     
/* 23 */     this.b = var0;
/* 24 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityVillager var1) {
/* 29 */     return !var0.a_(var1.getChunkCoordinates());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/* 34 */     VillagePlace var4 = var0.y();
/* 35 */     int var5 = var4.a(SectionPosition.a(var1.getChunkCoordinates()));
/*    */     
/* 37 */     Vec3D var6 = null;
/* 38 */     for (int var7 = 0; var7 < 5; var7++) {
/* 39 */       Vec3D var8 = RandomPositionGenerator.a(var1, 15, 7, var1 -> -var0.b(SectionPosition.a(var1)));
/* 40 */       if (var8 != null) {
/*    */ 
/*    */ 
/*    */         
/* 44 */         int var9 = var4.a(SectionPosition.a(new BlockPosition(var8)));
/* 45 */         if (var9 < var5) {
/* 46 */           var6 = var8; break;
/*    */         } 
/* 48 */         if (var9 == var5) {
/* 49 */           var6 = var8;
/*    */         }
/*    */       } 
/*    */     } 
/* 53 */     if (var6 != null)
/* 54 */       var1.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var6, this.b, this.c)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorNearestVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */