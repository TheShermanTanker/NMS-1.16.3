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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PiglinBruteAI
/*     */ {
/*     */   protected static BehaviorController<?> a(EntityPiglinBrute var0, BehaviorController<EntityPiglinBrute> var1) {
/*  56 */     b(var0, var1);
/*     */     
/*  58 */     c(var0, var1);
/*  59 */     d(var0, var1);
/*     */     
/*  61 */     var1.a((Set<Activity>)ImmutableSet.of(Activity.CORE));
/*  62 */     var1.b(Activity.IDLE);
/*  63 */     var1.e();
/*     */     
/*  65 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void a(EntityPiglinBrute var0) {
/*  70 */     GlobalPos var1 = GlobalPos.create(var0.world.getDimensionKey(), var0.getChunkCoordinates());
/*  71 */     var0.getBehaviorController().setMemory(MemoryModuleType.HOME, var1);
/*     */   }
/*     */   
/*     */   private static void b(EntityPiglinBrute var0, BehaviorController<EntityPiglinBrute> var1) {
/*  75 */     var1.a(Activity.CORE, 0, ImmutableList.of(new BehaviorLook(45, 90), new BehavorMove(), new BehaviorInteractDoor(), new BehaviorForgetAnger<>()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void c(EntityPiglinBrute var0, BehaviorController<EntityPiglinBrute> var1) {
/*  84 */     var1.a(Activity.IDLE, 10, ImmutableList.of(new BehaviorAttackTargetSet<>(PiglinBruteAI::a), 
/*     */           
/*  86 */           a(), 
/*  87 */           b(), new BehaviorLookInteract(EntityTypes.PLAYER, 4)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void d(EntityPiglinBrute var0, BehaviorController<EntityPiglinBrute> var1) {
/*  93 */     var1.a(Activity.FLIGHT, 10, ImmutableList.of(new BehaviorAttackTargetForget<>(var1 -> !a(var0, var1)), new BehaviorWalkAwayOutOfRange(1.0F), new BehaviorAttack(20)), MemoryModuleType.ATTACK_TARGET);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static BehaviorGateSingle<EntityPiglinBrute> a() {
/* 101 */     return new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityPiglinBrute>, Integer>>)ImmutableList.of(
/* 102 */           Pair.of(new BehaviorLookTarget(EntityTypes.PLAYER, 8.0F), Integer.valueOf(1)), 
/* 103 */           Pair.of(new BehaviorLookTarget(EntityTypes.PIGLIN, 8.0F), Integer.valueOf(1)), 
/* 104 */           Pair.of(new BehaviorLookTarget(EntityTypes.PIGLIN_BRUTE, 8.0F), Integer.valueOf(1)), 
/* 105 */           Pair.of(new BehaviorLookTarget(8.0F), Integer.valueOf(1)), 
/* 106 */           Pair.of(new BehaviorNop(30, 60), Integer.valueOf(1))));
/*     */   }
/*     */ 
/*     */   
/*     */   private static BehaviorGateSingle<EntityPiglinBrute> b() {
/* 111 */     return new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityPiglinBrute>, Integer>>)ImmutableList.of(
/* 112 */           Pair.of(new BehaviorStrollRandomUnconstrained(0.6F), Integer.valueOf(2)), 
/* 113 */           Pair.of(BehaviorInteract.a((EntityTypes)EntityTypes.PIGLIN, 8, MemoryModuleType.INTERACTION_TARGET, 0.6F, 2), Integer.valueOf(2)), 
/* 114 */           Pair.of(BehaviorInteract.a((EntityTypes)EntityTypes.PIGLIN_BRUTE, 8, MemoryModuleType.INTERACTION_TARGET, 0.6F, 2), Integer.valueOf(2)), 
/* 115 */           Pair.of(new BehaviorStrollPlace(MemoryModuleType.HOME, 0.6F, 2, 100), Integer.valueOf(2)), 
/* 116 */           Pair.of(new BehaviorStrollPosition(MemoryModuleType.HOME, 0.6F, 5), Integer.valueOf(2)), 
/* 117 */           Pair.of(new BehaviorNop(30, 60), Integer.valueOf(1))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void b(EntityPiglinBrute var0) {
/* 122 */     BehaviorController<EntityPiglinBrute> var1 = var0.getBehaviorController();
/*     */ 
/*     */ 
/*     */     
/* 126 */     Activity var2 = var1.f().orElse(null);
/*     */ 
/*     */ 
/*     */     
/* 130 */     var1.a((List<Activity>)ImmutableList.of(Activity.FLIGHT, Activity.IDLE));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     Activity var3 = var1.f().orElse(null);
/* 136 */     if (var2 != var3)
/*     */     {
/* 138 */       d(var0);
/*     */     }
/*     */ 
/*     */     
/* 142 */     var0.setAggressive(var1.hasMemory(MemoryModuleType.ATTACK_TARGET));
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(EntityPiglinAbstract var0, EntityLiving var1) {
/* 147 */     return a(var0)
/* 148 */       .filter(var1 -> (var1 == var0))
/* 149 */       .isPresent();
/*     */   }
/*     */   
/*     */   private static Optional<? extends EntityLiving> a(EntityPiglinAbstract var0) {
/* 153 */     Optional<EntityLiving> var1 = BehaviorUtil.a(var0, MemoryModuleType.ANGRY_AT);
/* 154 */     if (var1.isPresent() && a(var1.get())) {
/* 155 */       return var1;
/*     */     }
/*     */     
/* 158 */     Optional<? extends EntityLiving> var2 = a(var0, (MemoryModuleType)MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER);
/* 159 */     if (var2.isPresent()) {
/* 160 */       return var2;
/*     */     }
/*     */     
/* 163 */     return var0.getBehaviorController().getMemory((MemoryModuleType)MemoryModuleType.NEAREST_VISIBLE_NEMSIS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean a(EntityLiving var0) {
/* 170 */     return IEntitySelector.f.test(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Optional<? extends EntityLiving> a(EntityPiglinAbstract var0, MemoryModuleType<? extends EntityLiving> var1) {
/* 175 */     return var0.getBehaviorController().<EntityLiving>getMemory(var1).filter(var1 -> var1.a(var0, 12.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void a(EntityPiglinBrute var0, EntityLiving var1) {
/* 180 */     if (var1 instanceof EntityPiglinAbstract) {
/*     */       return;
/*     */     }
/*     */     
/* 184 */     PiglinAI.a(var0, var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void c(EntityPiglinBrute var0) {
/* 193 */     if (var0.world.random.nextFloat() < 0.0125D) {
/* 194 */       d(var0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void d(EntityPiglinBrute var0) {
/* 200 */     var0.getBehaviorController().f().ifPresent(var1 -> {
/*     */           if (var1 == Activity.FLIGHT)
/*     */             var0.eT(); 
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PiglinBruteAI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */