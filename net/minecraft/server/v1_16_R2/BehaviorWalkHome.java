/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import it.unimi.dsi.fastutil.longs.Long2LongMap;
/*    */ import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
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
/*    */ 
/*    */ public class BehaviorWalkHome
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   private final float b;
/* 31 */   private final Long2LongMap c = (Long2LongMap)new Long2LongOpenHashMap();
/*    */   private int d;
/*    */   private long e;
/*    */   
/*    */   public BehaviorWalkHome(float var0) {
/* 36 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.HOME, MemoryStatus.VALUE_ABSENT));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 46 */     if (var0.getTime() - this.e < 20L) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     EntityCreature var2 = (EntityCreature)var1;
/* 51 */     VillagePlace var3 = var0.y();
/*    */     
/* 53 */     Optional<BlockPosition> var4 = var3.d(VillagePlaceType.r.c(), var1.getChunkCoordinates(), 48, VillagePlace.Occupancy.ANY);
/* 54 */     return (var4.isPresent() && ((BlockPosition)var4.get()).j(var2.getChunkCoordinates()) > 4.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 59 */     this.d = 0;
/* 60 */     this.e = var0.getTime() + var0.getRandom().nextInt(20);
/*    */     
/* 62 */     EntityCreature var4 = (EntityCreature)var1;
/* 63 */     VillagePlace var5 = var0.y();
/*    */     
/* 65 */     Predicate<BlockPosition> var6 = var0 -> {
/*    */         long var1 = var0.asLong();
/*    */         
/*    */         if (this.c.containsKey(var1)) {
/*    */           return false;
/*    */         }
/*    */         
/*    */         if (++this.d >= 5) {
/*    */           return false;
/*    */         }
/*    */         
/*    */         this.c.put(var1, this.e + 40L);
/*    */         return true;
/*    */       };
/* 79 */     Stream<BlockPosition> var7 = var5.a(VillagePlaceType.r.c(), var6, var1.getChunkCoordinates(), 48, VillagePlace.Occupancy.ANY);
/* 80 */     PathEntity var8 = var4.getNavigation().a(var7, VillagePlaceType.r.d());
/*    */     
/* 82 */     if (var8 != null && var8.j()) {
/* 83 */       BlockPosition var9 = var8.m();
/* 84 */       Optional<VillagePlaceType> var10 = var5.c(var9);
/* 85 */       if (var10.isPresent()) {
/*    */         
/* 87 */         var1.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var9, this.b, 1));
/* 88 */         PacketDebug.c(var0, var9);
/*    */       } 
/* 90 */     } else if (this.d < 5) {
/* 91 */       this.c.long2LongEntrySet().removeIf(var0 -> (var0.getLongValue() < this.e));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorWalkHome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */