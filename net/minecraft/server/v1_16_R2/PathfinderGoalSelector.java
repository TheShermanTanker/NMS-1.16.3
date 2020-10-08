/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.util.math.IntegerUtil;
/*     */ import com.destroystokyo.paper.util.set.OptimizedSmallEnumSet;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.EnumMap;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Stream;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class PathfinderGoalSelector {
/*  17 */   private static final Logger LOGGER = LogManager.getLogger();
/*  18 */   private static final PathfinderGoalWrapped b = new PathfinderGoalWrapped(2147483647, new PathfinderGoal()
/*     */       {
/*     */         public boolean a() {
/*  21 */           return false;
/*     */         }
/*     */       })
/*     */     {
/*     */       public boolean g() {
/*  26 */         return false;
/*     */       }
/*     */     };
/*  29 */   private final Map<PathfinderGoal.Type, PathfinderGoalWrapped> c = new EnumMap<>(PathfinderGoal.Type.class); private final Supplier<GameProfilerFiller> e;
/*  30 */   private final Set<PathfinderGoalWrapped> d = Sets.newLinkedHashSet(); public final Set<PathfinderGoalWrapped> getTasks() { return this.d; }
/*     */   
/*  32 */   private final EnumSet<PathfinderGoal.Type> f = EnumSet.noneOf(PathfinderGoal.Type.class);
/*  33 */   private final OptimizedSmallEnumSet<PathfinderGoal.Type> goalTypes = new OptimizedSmallEnumSet(PathfinderGoal.Type.class);
/*  34 */   private int g = 3; private int curRate; private int getTickRate() { return this.g; }
/*  35 */   private int getCurRate() { return this.curRate; } private void incRate() { this.curRate++; }
/*     */   
/*     */   public PathfinderGoalSelector(Supplier<GameProfilerFiller> supplier) {
/*  38 */     this.e = supplier;
/*     */   }
/*     */   
/*  41 */   public void addGoal(int priority, PathfinderGoal goal) { a(priority, goal); } public void a(int i, PathfinderGoal pathfindergoal) {
/*  42 */     this.d.add(new PathfinderGoalWrapped(i, pathfindergoal));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean inactiveTick() {
/*  47 */     if (getCurRate() % getTickRate() != 0) {
/*  48 */       incRate();
/*  49 */       return false;
/*     */     } 
/*  51 */     return true;
/*     */   }
/*     */   
/*     */   public boolean hasTasks() {
/*  55 */     for (PathfinderGoalWrapped task : getTasks()) {
/*  56 */       if (task.isRunning()) {
/*  57 */         return true;
/*     */       }
/*     */     } 
/*  60 */     return false;
/*     */   }
/*     */   
/*     */   public void removeGoal(PathfinderGoal goal) {
/*  64 */     a(goal);
/*     */   } public void a(PathfinderGoal pathfindergoal) {
/*  66 */     for (Iterator<PathfinderGoalWrapped> iterator = this.d.iterator(); iterator.hasNext(); ) {
/*  67 */       PathfinderGoalWrapped goalWrapped = iterator.next();
/*  68 */       if (goalWrapped.j() != pathfindergoal) {
/*     */         continue;
/*     */       }
/*  71 */       if (goalWrapped.g()) {
/*  72 */         goalWrapped.d();
/*     */       }
/*  74 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  79 */   private static final PathfinderGoal.Type[] PATHFINDER_GOAL_TYPES = PathfinderGoal.Type.values();
/*     */   
/*     */   public void doTick() {
/*  82 */     GameProfilerFiller gameprofilerfiller = this.e.get();
/*     */     
/*  84 */     gameprofilerfiller.enter("goalCleanup");
/*     */     Iterator<PathfinderGoalWrapped> iterator;
/*  86 */     for (iterator = this.d.iterator(); iterator.hasNext(); ) {
/*  87 */       PathfinderGoalWrapped wrappedGoal = iterator.next();
/*  88 */       if (!wrappedGoal.g()) {
/*     */         continue;
/*     */       }
/*  91 */       if (!this.goalTypes.hasCommonElements(wrappedGoal.getGoalTypes()) && wrappedGoal.b()) {
/*     */         continue;
/*     */       }
/*  94 */       wrappedGoal.d();
/*     */     } 
/*     */     
/*  97 */     this.c.forEach((pathfindergoal_type, pathfindergoalwrapped) -> {
/*     */           if (!pathfindergoalwrapped.g()) {
/*     */             this.c.remove(pathfindergoal_type);
/*     */           }
/*     */         });
/*     */     
/* 103 */     gameprofilerfiller.exit();
/* 104 */     gameprofilerfiller.enter("goalUpdate");
/*     */     
/* 106 */     label48: for (iterator = this.d.iterator(); iterator.hasNext(); ) {
/* 107 */       PathfinderGoalWrapped wrappedGoal = iterator.next();
/* 108 */       if (wrappedGoal.g()) {
/*     */         continue;
/*     */       }
/*     */       
/* 112 */       OptimizedSmallEnumSet<PathfinderGoal.Type> wrappedGoalSet = wrappedGoal.getGoalTypes();
/*     */       
/* 114 */       if (this.goalTypes.hasCommonElements(wrappedGoalSet)) {
/*     */         continue;
/*     */       }
/*     */       
/* 118 */       long iterator1 = wrappedGoalSet.getBackingSet();
/* 119 */       int wrappedGoalSize = wrappedGoalSet.size(); int i;
/* 120 */       for (i = 0; i < wrappedGoalSize; i++) {
/* 121 */         PathfinderGoal.Type type = PATHFINDER_GOAL_TYPES[Long.numberOfTrailingZeros(iterator1)];
/* 122 */         iterator1 ^= IntegerUtil.getTrailingBit(iterator1);
/* 123 */         PathfinderGoalWrapped wrapped = this.c.getOrDefault(type, b);
/* 124 */         if (!wrapped.a(wrappedGoal)) {
/*     */           continue label48;
/*     */         }
/*     */       } 
/*     */       
/* 129 */       if (!wrappedGoal.a()) {
/*     */         continue;
/*     */       }
/*     */       
/* 133 */       iterator1 = wrappedGoalSet.getBackingSet();
/* 134 */       wrappedGoalSize = wrappedGoalSet.size();
/* 135 */       for (i = 0; i < wrappedGoalSize; i++) {
/* 136 */         PathfinderGoal.Type type = PATHFINDER_GOAL_TYPES[Long.numberOfTrailingZeros(iterator1)];
/* 137 */         iterator1 ^= IntegerUtil.getTrailingBit(iterator1);
/* 138 */         PathfinderGoalWrapped wrapped = this.c.getOrDefault(type, b);
/*     */         
/* 140 */         wrapped.d();
/* 141 */         this.c.put(type, wrappedGoal);
/*     */       } 
/*     */       
/* 144 */       wrappedGoal.c();
/*     */     } 
/*     */     
/* 147 */     gameprofilerfiller.exit();
/* 148 */     gameprofilerfiller.enter("goalTick");
/*     */     
/* 150 */     for (iterator = this.d.iterator(); iterator.hasNext(); ) {
/* 151 */       PathfinderGoalWrapped wrappedGoal = iterator.next();
/* 152 */       if (wrappedGoal.g()) {
/* 153 */         wrappedGoal.e();
/*     */       }
/*     */     } 
/*     */     
/* 157 */     gameprofilerfiller.exit();
/*     */   }
/*     */   public final Stream<PathfinderGoalWrapped> getExecutingGoals() {
/* 160 */     return d();
/*     */   } public Stream<PathfinderGoalWrapped> d() {
/* 162 */     return this.d.stream().filter(PathfinderGoalWrapped::g);
/*     */   }
/*     */   
/*     */   public void a(PathfinderGoal.Type pathfindergoal_type) {
/* 166 */     this.goalTypes.addUnchecked(pathfindergoal_type);
/*     */   }
/*     */   
/*     */   public void b(PathfinderGoal.Type pathfindergoal_type) {
/* 170 */     this.goalTypes.removeUnchecked(pathfindergoal_type);
/*     */   }
/*     */   
/*     */   public void a(PathfinderGoal.Type pathfindergoal_type, boolean flag) {
/* 174 */     if (flag) {
/* 175 */       b(pathfindergoal_type);
/*     */     } else {
/* 177 */       a(pathfindergoal_type);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */