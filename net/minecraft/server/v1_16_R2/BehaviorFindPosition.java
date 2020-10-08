/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BehaviorFindPosition
/*     */   extends Behavior<EntityCreature>
/*     */ {
/*     */   private final VillagePlaceType b;
/*     */   private final MemoryModuleType<GlobalPos> c;
/*     */   private final boolean d;
/*     */   private final Optional<Byte> e;
/*     */   private long f;
/*  42 */   private final Long2ObjectMap<a> g = (Long2ObjectMap<a>)new Long2ObjectOpenHashMap();
/*     */   
/*     */   public BehaviorFindPosition(VillagePlaceType var0, MemoryModuleType<GlobalPos> var1, MemoryModuleType<GlobalPos> var2, boolean var3, Optional<Byte> var4) {
/*  45 */     super((Map<MemoryModuleType<?>, MemoryStatus>)a(var1, var2));
/*  46 */     this.b = var0;
/*  47 */     this.c = var2;
/*  48 */     this.d = var3;
/*  49 */     this.e = var4;
/*     */   }
/*     */   
/*     */   public BehaviorFindPosition(VillagePlaceType var0, MemoryModuleType<GlobalPos> var1, boolean var2, Optional<Byte> var3) {
/*  53 */     this(var0, var1, var1, var2, var3);
/*     */   }
/*     */   
/*     */   private static ImmutableMap<MemoryModuleType<?>, MemoryStatus> a(MemoryModuleType<GlobalPos> var0, MemoryModuleType<GlobalPos> var1) {
/*  57 */     ImmutableMap.Builder<MemoryModuleType<?>, MemoryStatus> var2 = ImmutableMap.builder();
/*  58 */     var2.put(var0, MemoryStatus.VALUE_ABSENT);
/*  59 */     if (var1 != var0) {
/*  60 */       var2.put(var1, MemoryStatus.VALUE_ABSENT);
/*     */     }
/*  62 */     return var2.build();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(WorldServer var0, EntityCreature var1) {
/*  67 */     if (this.d && var1.isBaby()) {
/*  68 */       return false;
/*     */     }
/*     */     
/*  71 */     if (this.f == 0L) {
/*  72 */       this.f = var1.world.getTime() + var0.random.nextInt(20);
/*  73 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     return (var0.getTime() >= this.f);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, EntityCreature var1, long var2) {
/*  84 */     this.f = var2 + 20L + var0.getRandom().nextInt(20);
/*     */     
/*  86 */     VillagePlace var4 = var0.y();
/*     */     
/*  88 */     this.g.long2ObjectEntrySet().removeIf(var2 -> !((a)var2.getValue()).b(var0));
/*     */     
/*  90 */     Predicate<BlockPosition> var5 = var2 -> {
/*     */         a var3 = (a)this.g.get(var2.asLong());
/*     */         
/*     */         if (var3 == null) {
/*     */           return true;
/*     */         }
/*     */         
/*     */         if (!var3.c(var0)) {
/*     */           return false;
/*     */         }
/*     */         
/*     */         var3.a(var0);
/*     */         
/*     */         return true;
/*     */       };
/*     */     
/* 106 */     Set<BlockPosition> var6 = (Set<BlockPosition>)var4.b(this.b.c(), var5, var1.getChunkCoordinates(), 48, VillagePlace.Occupancy.HAS_SPACE).limit(5L).collect(Collectors.toSet());
/*     */     
/* 108 */     PathEntity var7 = var1.getNavigation().a(var6, this.b.d());
/*     */     
/* 110 */     if (var7 != null && var7.j()) {
/* 111 */       BlockPosition var8 = var7.m();
/* 112 */       var4.c(var8).ifPresent(var4 -> {
/*     */             var0.a(this.b.c(), (), var1, 1);
/*     */             var2.getBehaviorController().setMemory(this.c, GlobalPos.create(var3.getDimensionKey(), var1));
/*     */             this.e.ifPresent(());
/*     */             this.g.clear();
/*     */             PacketDebug.c(var3, var1);
/*     */           });
/*     */     } else {
/* 120 */       for (BlockPosition var9 : var6) {
/* 121 */         this.g.computeIfAbsent(var9.asLong(), var3 -> new a(var0.world.random, var1));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static class a
/*     */   {
/*     */     private final Random a;
/*     */     
/*     */     private long b;
/*     */     
/*     */     private long c;
/*     */     
/*     */     private int d;
/*     */     
/*     */     a(Random var0, long var1) {
/* 138 */       this.a = var0;
/* 139 */       a(var1);
/*     */     }
/*     */     
/*     */     public void a(long var0) {
/* 143 */       this.b = var0;
/* 144 */       int var2 = this.d + this.a.nextInt(40) + 40;
/* 145 */       this.d = Math.min(var2, 400);
/* 146 */       this.c = var0 + this.d;
/*     */     }
/*     */     
/*     */     public boolean b(long var0) {
/* 150 */       return (var0 - this.b < 400L);
/*     */     }
/*     */     
/*     */     public boolean c(long var0) {
/* 154 */       return (var0 >= this.c);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 159 */       return "RetryMarker{, previousAttemptAt=" + this.b + ", nextScheduledAttemptAt=" + this.c + ", currentDelay=" + this.d + '}';
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorFindPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */