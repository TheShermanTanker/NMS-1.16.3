/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
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
/*     */ public class HoglinAI
/*     */ {
/*  52 */   private static final IntRange a = TimeRange.a(5, 20);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private static final IntRange b = IntRange.a(5, 16);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static BehaviorController<?> a(BehaviorController<EntityHoglin> var0) {
/*  69 */     b(var0);
/*     */     
/*  71 */     c(var0);
/*  72 */     d(var0);
/*  73 */     e(var0);
/*     */     
/*  75 */     var0.a((Set<Activity>)ImmutableSet.of(Activity.CORE));
/*  76 */     var0.b(Activity.IDLE);
/*  77 */     var0.e();
/*  78 */     return var0;
/*     */   }
/*     */   
/*     */   private static void b(BehaviorController<EntityHoglin> var0) {
/*  82 */     var0.a(Activity.CORE, 0, ImmutableList.of(new BehaviorLook(45, 90), new BehavorMove()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void c(BehaviorController<EntityHoglin> var0) {
/*  89 */     var0.a(Activity.IDLE, 10, ImmutableList.of(new BehaviorPacify(MemoryModuleType.NEAREST_REPELLENT, 200), new BehaviorMakeLoveAnimal((EntityTypes)EntityTypes.HOGLIN, 0.6F), 
/*     */ 
/*     */           
/*  92 */           BehaviorWalkAway.a(MemoryModuleType.NEAREST_REPELLENT, 1.0F, 8, true), new BehaviorAttackTargetSet<>(HoglinAI::d), new BehaviorRunIf<>(EntityHoglin::eL, 
/*     */             
/*  94 */             (Behavior)BehaviorWalkAway.b((MemoryModuleType)MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLIN, 0.4F, 8, false)), new BehaviorRunSometimes<>(new BehaviorLookTarget(8.0F), 
/*  95 */             IntRange.a(30, 60)), new BehaviorFollowAdult<>(b, 0.6F), 
/*     */           
/*  97 */           a()));
/*     */   }
/*     */ 
/*     */   
/*     */   private static void d(BehaviorController<EntityHoglin> var0) {
/* 102 */     var0.a(Activity.FLIGHT, 10, ImmutableList.of(new BehaviorPacify(MemoryModuleType.NEAREST_REPELLENT, 200), new BehaviorMakeLoveAnimal((EntityTypes)EntityTypes.HOGLIN, 0.6F), new BehaviorWalkAwayOutOfRange(1.0F), new BehaviorRunIf<>(EntityHoglin::eL, new BehaviorAttack(40)), new BehaviorRunIf<>(EntityAgeable::isBaby, new BehaviorAttack(15)), new BehaviorAttackTargetForget<>(), new BehaviorRemoveMemory<>(HoglinAI::i, MemoryModuleType.ATTACK_TARGET)), MemoryModuleType.ATTACK_TARGET);
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
/*     */   private static void e(BehaviorController<EntityHoglin> var0) {
/* 114 */     var0.a(Activity.AVOID, 10, ImmutableList.of(
/* 115 */           BehaviorWalkAway.b((MemoryModuleType)MemoryModuleType.AVOID_TARGET, 1.3F, 15, false), 
/* 116 */           a(), new BehaviorRunSometimes<>(new BehaviorLookTarget(8.0F), 
/* 117 */             IntRange.a(30, 60)), new BehaviorRemoveMemory<>(HoglinAI::e, MemoryModuleType.AVOID_TARGET)), MemoryModuleType.AVOID_TARGET);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static BehaviorGateSingle<EntityHoglin> a() {
/* 123 */     return new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityHoglin>, Integer>>)ImmutableList.of(
/* 124 */           Pair.of(new BehaviorStrollRandomUnconstrained(0.4F), Integer.valueOf(2)), 
/* 125 */           Pair.of(new BehaviorLookWalk(0.4F, 3), Integer.valueOf(2)), 
/* 126 */           Pair.of(new BehaviorNop(30, 60), Integer.valueOf(1))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void a(EntityHoglin var0) {
/* 131 */     BehaviorController<EntityHoglin> var1 = var0.getBehaviorController();
/*     */     
/* 133 */     Activity var2 = var1.f().orElse(null);
/*     */ 
/*     */     
/* 136 */     var1.a((List<Activity>)ImmutableList.of(Activity.FLIGHT, Activity.AVOID, Activity.IDLE));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     Activity var3 = var1.f().orElse(null);
/* 143 */     if (var2 != var3)
/*     */     {
/* 145 */       b(var0).ifPresent(var0::a);
/*     */     }
/*     */ 
/*     */     
/* 149 */     var0.setAggressive(var1.hasMemory(MemoryModuleType.ATTACK_TARGET));
/*     */   }
/*     */   
/*     */   protected static void a(EntityHoglin var0, EntityLiving var1) {
/* 153 */     if (var0.isBaby()) {
/*     */       return;
/*     */     }
/*     */     
/* 157 */     if (var1.getEntityType() == EntityTypes.PIGLIN && f(var0)) {
/*     */       
/* 159 */       e(var0, var1);
/* 160 */       c(var0, var1);
/*     */       return;
/*     */     } 
/* 163 */     h(var0, var1);
/*     */   }
/*     */   
/*     */   private static void c(EntityHoglin var0, EntityLiving var1) {
/* 167 */     g(var0).forEach(var1 -> d(var1, var0));
/*     */   }
/*     */   
/*     */   private static void d(EntityHoglin var0, EntityLiving var1) {
/* 171 */     EntityLiving var2 = var1;
/*     */     
/* 173 */     BehaviorController<EntityHoglin> var3 = var0.getBehaviorController();
/* 174 */     var2 = BehaviorUtil.a(var0, var3.getMemory(MemoryModuleType.AVOID_TARGET), var2);
/* 175 */     var2 = BehaviorUtil.a(var0, var3.getMemory(MemoryModuleType.ATTACK_TARGET), var2);
/*     */     
/* 177 */     e(var0, var2);
/*     */   }
/*     */   
/*     */   private static void e(EntityHoglin var0, EntityLiving var1) {
/* 181 */     var0.getBehaviorController().removeMemory(MemoryModuleType.ATTACK_TARGET);
/* 182 */     var0.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
/* 183 */     var0.getBehaviorController().a(MemoryModuleType.AVOID_TARGET, var1, a.a(var0.world.random));
/*     */   }
/*     */   
/*     */   private static Optional<? extends EntityLiving> d(EntityHoglin var0) {
/* 187 */     if (c(var0) || i(var0))
/*     */     {
/* 189 */       return Optional.empty();
/*     */     }
/*     */ 
/*     */     
/* 193 */     return var0.getBehaviorController().getMemory((MemoryModuleType)MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER);
/*     */   }
/*     */   
/*     */   static boolean a(EntityHoglin var0, BlockPosition var1) {
/* 197 */     Optional<BlockPosition> var2 = var0.getBehaviorController().getMemory(MemoryModuleType.NEAREST_REPELLENT);
/* 198 */     return (var2.isPresent() && ((BlockPosition)var2.get()).a(var1, 8.0D));
/*     */   }
/*     */   
/*     */   private static boolean e(EntityHoglin var0) {
/* 202 */     return (var0.eL() && !f(var0));
/*     */   }
/*     */   
/*     */   private static boolean f(EntityHoglin var0) {
/* 206 */     if (var0.isBaby()) {
/* 207 */       return false;
/*     */     }
/*     */     
/* 210 */     int var1 = ((Integer)var0.getBehaviorController().<Integer>getMemory(MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT).orElse(Integer.valueOf(0))).intValue();
/* 211 */     int var2 = ((Integer)var0.getBehaviorController().<Integer>getMemory(MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT).orElse(Integer.valueOf(0))).intValue() + 1;
/* 212 */     return (var1 > var2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void b(EntityHoglin var0, EntityLiving var1) {
/* 218 */     BehaviorController<EntityHoglin> var2 = var0.getBehaviorController();
/* 219 */     var2.removeMemory(MemoryModuleType.PACIFIED);
/* 220 */     var2.removeMemory(MemoryModuleType.BREED_TARGET);
/*     */     
/* 222 */     if (var0.isBaby()) {
/*     */       
/* 224 */       d(var0, var1);
/*     */       
/*     */       return;
/*     */     } 
/* 228 */     f(var0, var1);
/*     */   }
/*     */   
/*     */   private static void f(EntityHoglin var0, EntityLiving var1) {
/* 232 */     if (var0.getBehaviorController().c(Activity.AVOID) && var1.getEntityType() == EntityTypes.PIGLIN) {
/*     */       return;
/*     */     }
/* 235 */     if (!IEntitySelector.f.test(var1)) {
/*     */       return;
/*     */     }
/* 238 */     if (var1.getEntityType() == EntityTypes.HOGLIN) {
/*     */       return;
/*     */     }
/* 241 */     if (BehaviorUtil.a(var0, var1, 4.0D)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 246 */     g(var0, var1);
/* 247 */     h(var0, var1);
/*     */   }
/*     */   
/*     */   private static void g(EntityHoglin var0, EntityLiving var1) {
/* 251 */     BehaviorController<EntityHoglin> var2 = var0.getBehaviorController();
/* 252 */     var2.removeMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
/* 253 */     var2.removeMemory(MemoryModuleType.BREED_TARGET);
/* 254 */     var2.a(MemoryModuleType.ATTACK_TARGET, var1, 200L);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void h(EntityHoglin var0, EntityLiving var1) {
/* 259 */     g(var0).forEach(var1 -> i(var1, var0));
/*     */   }
/*     */   
/*     */   private static void i(EntityHoglin var0, EntityLiving var1) {
/* 263 */     if (c(var0)) {
/*     */       return;
/*     */     }
/*     */     
/* 267 */     Optional<EntityLiving> var2 = var0.getBehaviorController().getMemory(MemoryModuleType.ATTACK_TARGET);
/* 268 */     EntityLiving var3 = BehaviorUtil.a(var0, var2, var1);
/* 269 */     g(var0, var3);
/*     */   }
/*     */   
/*     */   public static Optional<SoundEffect> b(EntityHoglin var0) {
/* 273 */     return var0.getBehaviorController().f().map(var1 -> a(var0, var1));
/*     */   }
/*     */   
/*     */   private static SoundEffect a(EntityHoglin var0, Activity var1) {
/* 277 */     if (var1 == Activity.AVOID || var0.isConverting())
/* 278 */       return SoundEffects.ENTITY_HOGLIN_RETREAT; 
/* 279 */     if (var1 == Activity.FLIGHT)
/* 280 */       return SoundEffects.ENTITY_HOGLIN_ANGRY; 
/* 281 */     if (h(var0)) {
/* 282 */       return SoundEffects.ENTITY_HOGLIN_RETREAT;
/*     */     }
/* 284 */     return SoundEffects.ENTITY_HOGLIN_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<EntityHoglin> g(EntityHoglin var0) {
/* 289 */     return (List<EntityHoglin>)var0.getBehaviorController().getMemory(MemoryModuleType.NEAREST_VISIBLE_ADULT_HOGLINS).orElse(ImmutableList.of());
/*     */   }
/*     */   
/*     */   private static boolean h(EntityHoglin var0) {
/* 293 */     return var0.getBehaviorController().hasMemory(MemoryModuleType.NEAREST_REPELLENT);
/*     */   }
/*     */   
/*     */   private static boolean i(EntityHoglin var0) {
/* 297 */     return var0.getBehaviorController().hasMemory(MemoryModuleType.BREED_TARGET);
/*     */   }
/*     */   
/*     */   protected static boolean c(EntityHoglin var0) {
/* 301 */     return var0.getBehaviorController().hasMemory(MemoryModuleType.PACIFIED);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\HoglinAI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */