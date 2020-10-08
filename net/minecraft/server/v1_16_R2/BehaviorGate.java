/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ public class BehaviorGate<E extends EntityLiving> extends Behavior<E> {
/*     */   private final Set<MemoryModuleType<?>> b;
/*     */   private final Order c;
/*     */   private final Execution d;
/*  15 */   private final WeightedList<Behavior<? super E>> e = new WeightedList<>(false);
/*     */   
/*     */   public BehaviorGate(Map<MemoryModuleType<?>, MemoryStatus> map, Set<MemoryModuleType<?>> set, Order behaviorgate_order, Execution behaviorgate_execution, List<Pair<Behavior<? super E>, Integer>> list) {
/*  18 */     super(map);
/*  19 */     this.b = set;
/*  20 */     this.c = behaviorgate_order;
/*  21 */     this.d = behaviorgate_execution;
/*  22 */     list.forEach(pair -> this.e.a((Behavior<? super E>)pair.getFirst(), ((Integer)pair.getSecond()).intValue()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean b(WorldServer worldserver, E e0, long i) {
/*  29 */     return this.e.c().filter(behavior -> (behavior.a() == Behavior.Status.RUNNING))
/*     */       
/*  31 */       .anyMatch(behavior -> behavior.b(worldserver, e0, i));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(long i) {
/*  38 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(WorldServer worldserver, E e0, long i) {
/*  43 */     this.c.a(this.e);
/*  44 */     this.d.a(this.e, worldserver, e0, i);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void d(WorldServer worldserver, E e0, long i) {
/*  49 */     this.e.c().filter(behavior -> (behavior.a() == Behavior.Status.RUNNING))
/*     */       
/*  51 */       .forEach(behavior -> behavior.f(worldserver, e0, i));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void c(WorldServer worldserver, E e0, long i) {
/*  58 */     this.e.c().filter(behavior -> (behavior.a() == Behavior.Status.RUNNING))
/*     */       
/*  60 */       .forEach(behavior -> behavior.g(worldserver, e0, i));
/*     */ 
/*     */     
/*  63 */     BehaviorController<?> behaviorcontroller = e0.getBehaviorController();
/*     */     
/*  65 */     Objects.requireNonNull(behaviorcontroller); this.b.forEach(behaviorcontroller::removeMemory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  72 */     Set<? extends Behavior<? super E>> set = (Set<? extends Behavior<? super E>>)this.e.c().filter(behavior -> (behavior.a() == Behavior.Status.RUNNING)).collect(Collectors.toSet());
/*     */     
/*  74 */     return "(" + getClass().getSimpleName() + "): " + set;
/*     */   }
/*     */   
/*     */   enum Execution
/*     */   {
/*  79 */     RUN_ONE
/*     */     {
/*     */       public <E extends EntityLiving> void a(WeightedList<Behavior<? super E>> weightedlist, WorldServer worldserver, E e0, long i) {
/*  82 */         weightedlist.c().filter(behavior -> (behavior.a() == Behavior.Status.STOPPED))
/*     */           
/*  84 */           .filter(behavior -> behavior.e(worldserver, e0, i))
/*     */           
/*  86 */           .findFirst();
/*     */       }
/*     */     },
/*  89 */     TRY_ALL
/*     */     {
/*     */       public <E extends EntityLiving> void a(WeightedList<Behavior<? super E>> weightedlist, WorldServer worldserver, E e0, long i) {
/*  92 */         weightedlist.c().filter(behavior -> (behavior.a() == Behavior.Status.STOPPED))
/*     */           
/*  94 */           .forEach(behavior -> behavior.e(worldserver, e0, i));
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */     
/*     */     public abstract <E extends EntityLiving> void a(WeightedList<Behavior<? super E>> param1WeightedList, WorldServer param1WorldServer, E param1E, long param1Long);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   enum Order
/*     */   {
/* 107 */     ORDERED((String)(weightedlist -> { 
/* 108 */       })), SHUFFLED((String)WeightedList::a);
/*     */     
/*     */     private final Consumer<WeightedList<?>> c;
/*     */     
/*     */     Order(Consumer<WeightedList<?>> consumer) {
/* 113 */       this.c = consumer;
/*     */     }
/*     */     
/*     */     public void a(WeightedList<?> weightedlist) {
/* 117 */       this.c.accept(weightedlist);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */