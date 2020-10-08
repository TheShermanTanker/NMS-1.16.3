/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import it.unimi.dsi.fastutil.longs.Long2LongMap;
/*    */ import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.stream.Stream;
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
/*    */ public class SensorNearestBed
/*    */   extends Sensor<EntityInsentient>
/*    */ {
/* 29 */   private final Long2LongMap a = (Long2LongMap)new Long2LongOpenHashMap();
/*    */   private int b;
/*    */   private long c;
/*    */   
/*    */   public SensorNearestBed() {
/* 34 */     super(20);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<MemoryModuleType<?>> a() {
/* 39 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.NEAREST_BED);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityInsentient var1) {
/* 44 */     if (!var1.isBaby()) {
/*    */       return;
/*    */     }
/*    */     
/* 48 */     this.b = 0;
/* 49 */     this.c = var0.getTime() + var0.getRandom().nextInt(20);
/*    */     
/* 51 */     VillagePlace var2 = var0.y();
/*    */     
/* 53 */     Predicate<BlockPosition> var3 = var0 -> {
/*    */         long var1 = var0.asLong();
/*    */         
/*    */         if (this.a.containsKey(var1)) {
/*    */           return false;
/*    */         }
/*    */         
/*    */         if (++this.b >= 5) {
/*    */           return false;
/*    */         }
/*    */         
/*    */         this.a.put(var1, this.c + 40L);
/*    */         return true;
/*    */       };
/* 67 */     Stream<BlockPosition> var4 = var2.a(VillagePlaceType.r.c(), var3, var1.getChunkCoordinates(), 48, VillagePlace.Occupancy.ANY);
/* 68 */     PathEntity var5 = var1.getNavigation().a(var4, VillagePlaceType.r.d());
/*    */     
/* 70 */     if (var5 != null && var5.j()) {
/* 71 */       BlockPosition var6 = var5.m();
/* 72 */       Optional<VillagePlaceType> var7 = var2.c(var6);
/* 73 */       if (var7.isPresent())
/*    */       {
/* 75 */         var1.getBehaviorController().setMemory(MemoryModuleType.NEAREST_BED, var6);
/*    */       }
/* 77 */     } else if (this.b < 5) {
/* 78 */       this.a.long2LongEntrySet().removeIf(var0 -> (var0.getLongValue() < this.c));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorNearestBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */