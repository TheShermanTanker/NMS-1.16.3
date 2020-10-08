/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Set;
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
/*    */ public class SensorSecondaryPlaces
/*    */   extends Sensor<EntityVillager>
/*    */ {
/*    */   public SensorSecondaryPlaces() {
/* 21 */     super(40);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityVillager var1) {
/* 26 */     ResourceKey<World> var2 = var0.getDimensionKey();
/* 27 */     BlockPosition var3 = var1.getChunkCoordinates();
/* 28 */     List<GlobalPos> var4 = Lists.newArrayList();
/*    */     
/* 30 */     int var5 = 4;
/* 31 */     for (int i = -4; i <= 4; i++) {
/* 32 */       for (int var7 = -2; var7 <= 2; var7++) {
/* 33 */         for (int var8 = -4; var8 <= 4; var8++) {
/* 34 */           BlockPosition var9 = var3.b(i, var7, var8);
/* 35 */           if (var1.getVillagerData().getProfession().d().contains(var0.getType(var9).getBlock())) {
/* 36 */             var4.add(GlobalPos.create(var2, var9));
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 42 */     BehaviorController<?> var6 = var1.getBehaviorController();
/* 43 */     if (!var4.isEmpty()) {
/* 44 */       var6.setMemory(MemoryModuleType.SECONDARY_JOB_SITE, var4);
/*    */     } else {
/* 46 */       var6.removeMemory(MemoryModuleType.SECONDARY_JOB_SITE);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MemoryModuleType<?>> a() {
/* 52 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.SECONDARY_JOB_SITE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorSecondaryPlaces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */