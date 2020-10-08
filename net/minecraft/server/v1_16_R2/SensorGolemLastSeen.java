/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
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
/*    */ 
/*    */ public class SensorGolemLastSeen
/*    */   extends Sensor<EntityLiving>
/*    */ {
/*    */   public SensorGolemLastSeen() {
/* 22 */     this(200);
/*    */   }
/*    */   
/*    */   public SensorGolemLastSeen(int var0) {
/* 26 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1) {
/* 31 */     a(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MemoryModuleType<?>> a() {
/* 36 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.MOBS);
/*    */   }
/*    */   
/*    */   public static void a(EntityLiving var0) {
/* 40 */     Optional<List<EntityLiving>> var1 = var0.getBehaviorController().getMemory(MemoryModuleType.MOBS);
/* 41 */     if (!var1.isPresent()) {
/*    */       return;
/*    */     }
/*    */     
/* 45 */     boolean var2 = ((List)var1.get()).stream().anyMatch(var0 -> var0.getEntityType().equals(EntityTypes.IRON_GOLEM));
/*    */     
/* 47 */     if (var2) {
/* 48 */       b(var0);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void b(EntityLiving var0) {
/* 53 */     var0.getBehaviorController().a(MemoryModuleType.GOLEM_DETECTED_RECENTLY, Boolean.valueOf(true), 600L);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorGolemLastSeen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */