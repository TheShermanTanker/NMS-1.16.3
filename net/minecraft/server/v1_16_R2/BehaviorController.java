/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.MapCodec;
/*     */ import com.mojang.serialization.MapLike;
/*     */ import com.mojang.serialization.RecordBuilder;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableObject;
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
/*     */ public class BehaviorController<E extends EntityLiving>
/*     */ {
/*  49 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final Supplier<Codec<BehaviorController<E>>> b;
/*     */   
/*     */   public static final class b<E extends EntityLiving> {
/*     */     private final Collection<? extends MemoryModuleType<?>> a;
/*     */     private final Collection<? extends SensorType<? extends Sensor<? super E>>> b;
/*     */     private final Codec<BehaviorController<E>> c;
/*     */     
/*     */     private b(Collection<? extends MemoryModuleType<?>> var0, Collection<? extends SensorType<? extends Sensor<? super E>>> var1) {
/*  58 */       this.a = var0;
/*  59 */       this.b = var1;
/*  60 */       this.c = BehaviorController.b(var0, var1);
/*     */     }
/*     */     
/*     */     public BehaviorController<E> a(Dynamic<?> var0) {
/*  64 */       return this.c.parse(var0).resultOrPartial(BehaviorController.i()::error).orElseGet(() -> new BehaviorController<>(this.a, this.b, ImmutableList.of(), ()));
/*     */     }
/*     */   }
/*     */   
/*     */   public static <E extends EntityLiving> b<E> a(Collection<? extends MemoryModuleType<?>> var0, Collection<? extends SensorType<? extends Sensor<? super E>>> var1) {
/*  69 */     return new b<>(var0, var1);
/*     */   }
/*     */   
/*     */   public static <E extends EntityLiving> Codec<BehaviorController<E>> b(Collection<? extends MemoryModuleType<?>> var0, Collection<? extends SensorType<? extends Sensor<? super E>>> var1) {
/*  73 */     MutableObject<Codec<BehaviorController<E>>> var2 = new MutableObject();
/*     */     
/*  75 */     var2.setValue((new MapCodec<BehaviorController<E>>(var0, var1, var2)
/*     */         {
/*     */           public <T> Stream<T> keys(DynamicOps<T> var0) {
/*  78 */             return this.a.stream().flatMap(var0 -> SystemUtils.a(var0.getSerializer().map(()))).map(var1 -> var0.createString(var1.toString()));
/*     */           }
/*     */ 
/*     */           
/*     */           public <T> DataResult<BehaviorController<E>> decode(DynamicOps<T> var0, MapLike<T> var1) {
/*  83 */             MutableObject<DataResult<ImmutableList.Builder<BehaviorController.a<?>>>> var2 = new MutableObject(DataResult.success(ImmutableList.builder()));
/*     */             
/*  85 */             var1.entries().forEach(var2 -> {
/*     */                   DataResult<MemoryModuleType<?>> var3 = IRegistry.MEMORY_MODULE_TYPE.parse(var0, var2.getFirst());
/*     */                   
/*     */                   DataResult<? extends BehaviorController.a<?>> var4 = var3.flatMap(());
/*     */                   var1.setValue(((DataResult)var1.getValue()).apply2(ImmutableList.Builder::add, var4));
/*     */                 });
/*  91 */             ImmutableList<BehaviorController.a<?>> var3 = ((DataResult)var2.getValue()).resultOrPartial(BehaviorController.i()::error).map(ImmutableList.Builder::build).orElseGet(ImmutableList::of);
/*  92 */             return DataResult.success(new BehaviorController<>(this.a, this.b, var3, this.c::getValue));
/*     */           }
/*     */           
/*     */           private <T, U> DataResult<BehaviorController.a<U>> a(MemoryModuleType<U> var0, DynamicOps<T> var1, T var2) {
/*  96 */             return ((DataResult)var0.getSerializer().map(DataResult::success).orElseGet(() -> DataResult.error("No codec for memory: " + var0)))
/*  97 */               .flatMap(var2 -> var2.parse(var0, var1))
/*  98 */               .map(var1 -> new BehaviorController.a(var0, Optional.of(var1)));
/*     */           }
/*     */ 
/*     */           
/*     */           public <T> RecordBuilder<T> encode(BehaviorController<E> var0, DynamicOps<T> var1, RecordBuilder<T> var2) {
/* 103 */             BehaviorController.a(var0).forEach(var2 -> var2.a(var0, var1));
/* 104 */             return var2;
/*     */           }
/* 106 */         }).fieldOf("memories").codec());
/*     */     
/* 108 */     return (Codec<BehaviorController<E>>)var2.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 113 */   private final Map<MemoryModuleType<?>, Optional<? extends ExpirableMemory<?>>> memories = Maps.newHashMap();
/*     */ 
/*     */   
/* 116 */   private final Map<SensorType<? extends Sensor<? super E>>, Sensor<? super E>> sensors = Maps.newLinkedHashMap();
/*     */ 
/*     */   
/* 119 */   private final Map<Integer, Map<Activity, Set<Behavior<? super E>>>> e = Maps.newTreeMap();
/*     */   
/* 121 */   private Schedule schedule = Schedule.EMPTY;
/*     */ 
/*     */   
/* 124 */   private final Map<Activity, Set<Pair<MemoryModuleType<?>, MemoryStatus>>> g = Maps.newHashMap();
/*     */   
/* 126 */   private final Map<Activity, Set<MemoryModuleType<?>>> h = Maps.newHashMap();
/*     */ 
/*     */   
/* 129 */   private Set<Activity> i = Sets.newHashSet();
/*     */ 
/*     */   
/* 132 */   private final Set<Activity> j = Sets.newHashSet();
/*     */ 
/*     */   
/* 135 */   private Activity k = Activity.IDLE;
/*     */   
/* 137 */   private long l = -9999L;
/*     */   
/*     */   public BehaviorController(Collection<? extends MemoryModuleType<?>> var0, Collection<? extends SensorType<? extends Sensor<? super E>>> var1, ImmutableList<a<?>> var2, Supplier<Codec<BehaviorController<E>>> var3) {
/* 140 */     this.b = var3;
/* 141 */     for (MemoryModuleType<?> var5 : var0) {
/* 142 */       this.memories.put(var5, Optional.empty());
/*     */     }
/* 144 */     for (SensorType<? extends Sensor<? super E>> var5 : var1) {
/* 145 */       this.sensors.put(var5, var5.a());
/*     */     }
/*     */     
/* 148 */     for (Sensor<? super E> var5 : this.sensors.values()) {
/* 149 */       for (MemoryModuleType<?> var7 : var5.a()) {
/* 150 */         this.memories.put(var7, Optional.empty());
/*     */       }
/*     */     } 
/*     */     
/* 154 */     for (UnmodifiableIterator<a> unmodifiableIterator = var2.iterator(); unmodifiableIterator.hasNext(); ) { a<?> var5 = unmodifiableIterator.next();
/* 155 */       a.a(var5, this); }
/*     */   
/*     */   }
/*     */   
/*     */   public <T> DataResult<T> a(DynamicOps<T> var0) {
/* 160 */     return ((Codec)this.b.get()).encodeStart(var0, this);
/*     */   }
/*     */   
/*     */   static final class a<U>
/*     */   {
/*     */     private final MemoryModuleType<U> a;
/*     */     private final Optional<? extends ExpirableMemory<U>> b;
/*     */     
/*     */     private static <U> a<U> b(MemoryModuleType<U> var0, Optional<? extends ExpirableMemory<?>> var1) {
/* 169 */       return new a<>(var0, (Optional)var1);
/*     */     }
/*     */     
/*     */     private a(MemoryModuleType<U> var0, Optional<? extends ExpirableMemory<U>> var1) {
/* 173 */       this.a = var0;
/* 174 */       this.b = var1;
/*     */     }
/*     */     
/*     */     private void a(BehaviorController<?> var0) {
/* 178 */       BehaviorController.a(var0, this.a, this.b);
/*     */     }
/*     */     
/*     */     public <T> void a(DynamicOps<T> var0, RecordBuilder<T> var1) {
/* 182 */       this.a.getSerializer().ifPresent(var2 -> this.b.ifPresent(()));
/*     */     }
/*     */   }
/*     */   
/*     */   private Stream<a<?>> j() {
/* 187 */     return this.memories.entrySet().stream().map(var0 -> a.a((MemoryModuleType)var0.getKey(), (Optional)var0.getValue()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMemory(MemoryModuleType<?> var0) {
/* 194 */     return a(var0, MemoryStatus.VALUE_PRESENT);
/*     */   }
/*     */   
/*     */   public <U> void removeMemory(MemoryModuleType<U> var0) {
/* 198 */     setMemory(var0, Optional.empty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <U> void setMemory(MemoryModuleType<U> var0, @Nullable U var1) {
/* 206 */     setMemory(var0, Optional.ofNullable(var1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <U> void a(MemoryModuleType<U> var0, U var1, long var2) {
/* 215 */     b(var0, Optional.of(ExpirableMemory.a(var1, var2)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <U> void setMemory(MemoryModuleType<U> var0, Optional<? extends U> var1) {
/* 223 */     b(var0, var1.map(ExpirableMemory::a));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private <U> void b(MemoryModuleType<U> var0, Optional<? extends ExpirableMemory<?>> var1) {
/* 232 */     if (this.memories.containsKey(var0)) {
/* 233 */       if (var1.isPresent() && a(((ExpirableMemory)var1.get()).c())) {
/* 234 */         removeMemory(var0);
/*     */       } else {
/* 236 */         this.memories.put(var0, var1);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public <U> Optional<U> getMemory(MemoryModuleType<U> var0) {
/* 243 */     return ((Optional)this.memories.get(var0)).map(ExpirableMemory::c);
/*     */   }
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
/*     */   public <U> boolean b(MemoryModuleType<U> var0, U var1) {
/* 256 */     if (!hasMemory(var0)) {
/* 257 */       return false;
/*     */     }
/* 259 */     return getMemory(var0).filter(var1 -> var1.equals(var0)).isPresent();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(MemoryModuleType<?> var0, MemoryStatus var1) {
/* 264 */     Optional<? extends ExpirableMemory<?>> var2 = this.memories.get(var0);
/* 265 */     if (var2 == null) {
/* 266 */       return false;
/*     */     }
/*     */     
/* 269 */     return (var1 == MemoryStatus.REGISTERED || (var1 == MemoryStatus.VALUE_PRESENT && var2
/* 270 */       .isPresent()) || (var1 == MemoryStatus.VALUE_ABSENT && 
/* 271 */       !var2.isPresent()));
/*     */   }
/*     */   
/*     */   public Schedule getSchedule() {
/* 275 */     return this.schedule;
/*     */   }
/*     */   
/*     */   public void setSchedule(Schedule var0) {
/* 279 */     this.schedule = var0;
/*     */   }
/*     */   
/*     */   public void a(Set<Activity> var0) {
/* 283 */     this.i = var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public List<Behavior<? super E>> d() {
/* 295 */     ObjectArrayList<Behavior<? super E>> objectArrayList = new ObjectArrayList();
/* 296 */     for (Map<Activity, Set<Behavior<? super E>>> var2 : this.e.values()) {
/* 297 */       for (Set<Behavior<? super E>> var4 : var2.values()) {
/* 298 */         for (Behavior<? super E> var6 : var4) {
/* 299 */           if (var6.a() == Behavior.Status.RUNNING) {
/* 300 */             objectArrayList.add(var6);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 305 */     return (List<Behavior<? super E>>)objectArrayList;
/*     */   }
/*     */   
/*     */   public void e() {
/* 309 */     d(this.k);
/*     */   }
/*     */   
/*     */   public Optional<Activity> f() {
/* 313 */     for (Activity var1 : this.j) {
/* 314 */       if (!this.i.contains(var1)) {
/* 315 */         return Optional.of(var1);
/*     */       }
/*     */     } 
/* 318 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Activity var0) {
/* 327 */     if (f(var0)) {
/* 328 */       d(var0);
/*     */     } else {
/* 330 */       e();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void d(Activity var0) {
/* 335 */     if (c(var0)) {
/*     */       return;
/*     */     }
/*     */     
/* 339 */     e(var0);
/* 340 */     this.j.clear();
/* 341 */     this.j.addAll(this.i);
/* 342 */     this.j.add(var0);
/*     */   }
/*     */   
/*     */   private void e(Activity var0) {
/* 346 */     for (Activity var2 : this.j) {
/* 347 */       if (var2 != var0) {
/* 348 */         Set<MemoryModuleType<?>> var3 = this.h.get(var2);
/* 349 */         if (var3 != null) {
/* 350 */           for (MemoryModuleType<?> var5 : var3) {
/* 351 */             removeMemory(var5);
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(long var0, long var2) {
/* 363 */     if (var2 - this.l > 20L) {
/* 364 */       this.l = var2;
/* 365 */       Activity var4 = getSchedule().a((int)(var0 % 24000L));
/* 366 */       if (!this.j.contains(var4)) {
/* 367 */         a(var4);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(List<Activity> var0) {
/* 376 */     for (Activity var2 : var0) {
/* 377 */       if (f(var2)) {
/* 378 */         d(var2);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void b(Activity var0) {
/* 385 */     this.k = var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Activity var0, int var1, ImmutableList<? extends Behavior<? super E>> var2) {
/* 392 */     a(var0, a(var1, var2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Activity var0, int var1, ImmutableList<? extends Behavior<? super E>> var2, MemoryModuleType<?> var3) {
/* 401 */     ImmutableSet immutableSet1 = ImmutableSet.of(
/* 402 */         Pair.of(var3, MemoryStatus.VALUE_PRESENT));
/*     */     
/* 404 */     ImmutableSet immutableSet2 = ImmutableSet.of(var3);
/* 405 */     a(var0, a(var1, var2), (Set<Pair<MemoryModuleType<?>, MemoryStatus>>)immutableSet1, (Set<MemoryModuleType<?>>)immutableSet2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Activity var0, ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>> var1) {
/* 412 */     a(var0, var1, (Set<Pair<MemoryModuleType<?>, MemoryStatus>>)ImmutableSet.of(), Sets.newHashSet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Activity var0, ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>> var1, Set<Pair<MemoryModuleType<?>, MemoryStatus>> var2) {
/* 420 */     a(var0, var1, var2, Sets.newHashSet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(Activity var0, ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>> var1, Set<Pair<MemoryModuleType<?>, MemoryStatus>> var2, Set<MemoryModuleType<?>> var3) {
/* 429 */     this.g.put(var0, var2);
/* 430 */     if (!var3.isEmpty()) {
/* 431 */       this.h.put(var0, var3);
/*     */     }
/* 433 */     for (UnmodifiableIterator<Pair<Integer, ? extends Behavior<? super E>>> unmodifiableIterator = var1.iterator(); unmodifiableIterator.hasNext(); ) { Pair<Integer, ? extends Behavior<? super E>> var5 = unmodifiableIterator.next();
/* 434 */       ((Set<Object>)((Map<Activity, Set<Object>>)this.e
/* 435 */         .computeIfAbsent(var5.getFirst(), var0 -> Maps.newHashMap()))
/* 436 */         .computeIfAbsent(var0, var0 -> Sets.newLinkedHashSet()))
/* 437 */         .add(var5.getSecond()); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean c(Activity var0) {
/* 447 */     return this.j.contains(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public BehaviorController<E> h() {
/* 452 */     BehaviorController<E> var0 = new BehaviorController(this.memories.keySet(), this.sensors.keySet(), ImmutableList.of(), this.b);
/* 453 */     for (Map.Entry<MemoryModuleType<?>, Optional<? extends ExpirableMemory<?>>> var2 : this.memories.entrySet()) {
/* 454 */       MemoryModuleType<?> var3 = var2.getKey();
/* 455 */       if (((Optional)var2.getValue()).isPresent()) {
/* 456 */         var0.memories.put(var3, var2.getValue());
/*     */       }
/*     */     } 
/* 459 */     return var0;
/*     */   }
/*     */   
/*     */   public void a(WorldServer var0, E var1) {
/* 463 */     k();
/* 464 */     c(var0, var1);
/* 465 */     d(var0, var1);
/* 466 */     e(var0, var1);
/*     */   }
/*     */   
/*     */   private void c(WorldServer var0, E var1) {
/* 470 */     for (Sensor<? super E> var3 : this.sensors.values()) {
/* 471 */       var3.b(var0, var1);
/*     */     }
/*     */   }
/*     */   
/*     */   private void k() {
/* 476 */     for (Map.Entry<MemoryModuleType<?>, Optional<? extends ExpirableMemory<?>>> var1 : this.memories.entrySet()) {
/* 477 */       if (((Optional)var1.getValue()).isPresent()) {
/* 478 */         ExpirableMemory<?> var2 = ((Optional<ExpirableMemory>)var1.getValue()).get();
/* 479 */         var2.a();
/* 480 */         if (var2.d()) {
/* 481 */           removeMemory(var1.getKey());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void b(WorldServer var0, E var1) {
/* 488 */     long var2 = ((EntityLiving)var1).world.getTime();
/* 489 */     for (Behavior<? super E> var5 : d()) {
/* 490 */       var5.g(var0, var1, var2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void d(WorldServer var0, E var1) {
/* 498 */     long var2 = var0.getTime();
/* 499 */     for (Map<Activity, Set<Behavior<? super E>>> var5 : this.e.values()) {
/* 500 */       for (Map.Entry<Activity, Set<Behavior<? super E>>> var7 : var5.entrySet()) {
/* 501 */         Activity var8 = var7.getKey();
/* 502 */         if (this.j.contains(var8)) {
/* 503 */           Set<Behavior<? super E>> var9 = var7.getValue();
/* 504 */           for (Behavior<? super E> var11 : var9) {
/* 505 */             if (var11.a() == Behavior.Status.STOPPED) {
/* 506 */               var11.e(var0, var1, var2);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void e(WorldServer var0, E var1) {
/* 519 */     long var2 = var0.getTime();
/* 520 */     for (Behavior<? super E> var5 : d()) {
/* 521 */       var5.f(var0, var1, var2);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean f(Activity var0) {
/* 526 */     if (!this.g.containsKey(var0)) {
/* 527 */       return false;
/*     */     }
/*     */     
/* 530 */     for (Pair<MemoryModuleType<?>, MemoryStatus> var2 : this.g.get(var0)) {
/* 531 */       MemoryModuleType<?> var3 = (MemoryModuleType)var2.getFirst();
/* 532 */       MemoryStatus var4 = (MemoryStatus)var2.getSecond();
/* 533 */       if (!a(var3, var4)) {
/* 534 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 538 */     return true;
/*     */   }
/*     */   
/*     */   private boolean a(Object var0) {
/* 542 */     return (var0 instanceof Collection && ((Collection)var0).isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>> a(int var0, ImmutableList<? extends Behavior<? super E>> var1) {
/* 549 */     int var2 = var0;
/* 550 */     ImmutableList.Builder<Pair<Integer, ? extends Behavior<? super E>>> var3 = ImmutableList.builder();
/* 551 */     for (UnmodifiableIterator<Behavior<? super E>> unmodifiableIterator = var1.iterator(); unmodifiableIterator.hasNext(); ) { Behavior<? super E> var5 = unmodifiableIterator.next();
/* 552 */       var3.add(Pair.of(Integer.valueOf(var2++), var5)); }
/*     */     
/* 554 */     return var3.build();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */