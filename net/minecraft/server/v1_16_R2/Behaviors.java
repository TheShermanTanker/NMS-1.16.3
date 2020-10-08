/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class Behaviors
/*     */ {
/*     */   public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> a(VillagerProfession var0, float var1) {
/*  35 */     return ImmutableList.of(
/*  36 */         Pair.of(Integer.valueOf(0), new BehaviorSwim(0.8F)), 
/*  37 */         Pair.of(Integer.valueOf(0), new BehaviorInteractDoor()), 
/*  38 */         Pair.of(Integer.valueOf(0), new BehaviorLook(45, 90)), 
/*  39 */         Pair.of(Integer.valueOf(0), new BehaviorPanic()), 
/*  40 */         Pair.of(Integer.valueOf(0), new BehaviorWake()), 
/*  41 */         Pair.of(Integer.valueOf(0), new BehaviorBellAlert()), 
/*  42 */         Pair.of(Integer.valueOf(0), new BehaviorRaid()), 
/*  43 */         Pair.of(Integer.valueOf(0), new BehaviorPositionValidate(var0.b(), MemoryModuleType.JOB_SITE)), 
/*  44 */         Pair.of(Integer.valueOf(0), new BehaviorPositionValidate(var0.b(), MemoryModuleType.POTENTIAL_JOB_SITE)), 
/*  45 */         Pair.of(Integer.valueOf(1), new BehavorMove()), 
/*  46 */         Pair.of(Integer.valueOf(2), new BehaviorBetterJob(var0)), 
/*  47 */         Pair.of(Integer.valueOf(3), new BehaviorInteractPlayer(var1)), (Object[])new Pair[] {
/*  48 */           Pair.of(Integer.valueOf(5), new BehaviorFindAdmirableItem<>(var1, false, 4)), 
/*     */           
/*  50 */           Pair.of(Integer.valueOf(6), new BehaviorFindPosition(var0.b(), MemoryModuleType.JOB_SITE, MemoryModuleType.POTENTIAL_JOB_SITE, true, Optional.empty())), 
/*  51 */           Pair.of(Integer.valueOf(7), new BehaviorPotentialJobSite(var1)), 
/*  52 */           Pair.of(Integer.valueOf(8), new BehaviorLeaveJob(var1)), 
/*  53 */           Pair.of(Integer.valueOf(10), new BehaviorFindPosition(VillagePlaceType.r, MemoryModuleType.HOME, false, Optional.of(Byte.valueOf((byte)14)))), 
/*  54 */           Pair.of(Integer.valueOf(10), new BehaviorFindPosition(VillagePlaceType.s, MemoryModuleType.MEETING_POINT, true, Optional.of(Byte.valueOf((byte)14)))), 
/*  55 */           Pair.of(Integer.valueOf(10), new BehaviorCareer()), 
/*  56 */           Pair.of(Integer.valueOf(10), new BehaviorProfession())
/*     */         });
/*     */   }
/*     */   
/*     */   public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> b(VillagerProfession var0, float var1) {
/*     */     BehaviorWork var2;
/*  62 */     if (var0 == VillagerProfession.FARMER) {
/*  63 */       var2 = new BehaviorWorkComposter();
/*     */     } else {
/*  65 */       var2 = new BehaviorWork();
/*     */     } 
/*     */     
/*  68 */     return ImmutableList.of(
/*  69 */         b(), 
/*  70 */         Pair.of(Integer.valueOf(5), new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/*  71 */               Pair.of(var2, Integer.valueOf(7)), 
/*  72 */               Pair.of(new BehaviorStrollPosition(MemoryModuleType.JOB_SITE, 0.4F, 4), Integer.valueOf(2)), 
/*  73 */               Pair.of(new BehaviorStrollPlace(MemoryModuleType.JOB_SITE, 0.4F, 1, 10), Integer.valueOf(5)), 
/*  74 */               Pair.of(new BehaviorStrollPlaceList(MemoryModuleType.SECONDARY_JOB_SITE, var1, 1, 6, MemoryModuleType.JOB_SITE), Integer.valueOf(5)), 
/*  75 */               Pair.of(new BehaviorFarm(), Integer.valueOf((var0 == VillagerProfession.FARMER) ? 2 : 5)), 
/*  76 */               Pair.of(new BehaviorBonemeal(), Integer.valueOf((var0 == VillagerProfession.FARMER) ? 4 : 7))))), 
/*     */         
/*  78 */         Pair.of(Integer.valueOf(10), new BehaviorTradePlayer(400, 1600)), 
/*  79 */         Pair.of(Integer.valueOf(10), new BehaviorLookInteract(EntityTypes.PLAYER, 4)), 
/*  80 */         Pair.of(Integer.valueOf(2), new BehaviorWalkAwayBlock(MemoryModuleType.JOB_SITE, var1, 9, 100, 1200)), 
/*  81 */         Pair.of(Integer.valueOf(3), new BehaviorVillageHeroGift(100)), 
/*  82 */         Pair.of(Integer.valueOf(99), new BehaviorSchedule()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> a(float var0) {
/*  87 */     return ImmutableList.of(
/*  88 */         Pair.of(Integer.valueOf(0), new BehavorMove(80, 120)), 
/*  89 */         a(), 
/*  90 */         Pair.of(Integer.valueOf(5), new BehaviorPlay()), 
/*  91 */         Pair.of(Integer.valueOf(5), new BehaviorGateSingle<>(
/*  92 */             (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.VISIBLE_VILLAGER_BABIES, MemoryStatus.VALUE_ABSENT), 
/*     */ 
/*     */ 
/*     */             
/*  96 */             (List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/*  97 */               Pair.of(BehaviorInteract.a((EntityTypes)EntityTypes.VILLAGER, 8, MemoryModuleType.INTERACTION_TARGET, var0, 2), Integer.valueOf(2)), 
/*  98 */               Pair.of(BehaviorInteract.a((EntityTypes)EntityTypes.CAT, 8, MemoryModuleType.INTERACTION_TARGET, var0, 2), Integer.valueOf(1)), 
/*  99 */               Pair.of(new BehaviorStrollRandom(var0), Integer.valueOf(1)), 
/* 100 */               Pair.of(new BehaviorLookWalk(var0, 2), Integer.valueOf(1)), 
/* 101 */               Pair.of(new BehaviorBedJump(var0), Integer.valueOf(2)), 
/* 102 */               Pair.of(new BehaviorNop(20, 40), Integer.valueOf(2))))), 
/*     */ 
/*     */         
/* 105 */         Pair.of(Integer.valueOf(99), new BehaviorSchedule()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> c(VillagerProfession var0, float var1) {
/* 110 */     return ImmutableList.of(
/* 111 */         Pair.of(Integer.valueOf(2), new BehaviorWalkAwayBlock(MemoryModuleType.HOME, var1, 1, 150, 1200)), 
/* 112 */         Pair.of(Integer.valueOf(3), new BehaviorPositionValidate(VillagePlaceType.r, MemoryModuleType.HOME)), 
/* 113 */         Pair.of(Integer.valueOf(3), new BehaviorSleep()), 
/* 114 */         Pair.of(Integer.valueOf(5), new BehaviorGateSingle<>(
/* 115 */             (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.HOME, MemoryStatus.VALUE_ABSENT), 
/*     */ 
/*     */ 
/*     */             
/* 119 */             (List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 120 */               Pair.of(new BehaviorWalkHome(var1), Integer.valueOf(1)), 
/* 121 */               Pair.of(new BehaviorStrollInside(var1), Integer.valueOf(4)), 
/* 122 */               Pair.of(new BehaviorNearestVillage(var1, 4), Integer.valueOf(2)), 
/* 123 */               Pair.of(new BehaviorNop(20, 40), Integer.valueOf(2))))), 
/*     */ 
/*     */         
/* 126 */         b(), 
/* 127 */         Pair.of(Integer.valueOf(99), new BehaviorSchedule()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> d(VillagerProfession var0, float var1) {
/* 132 */     return ImmutableList.of(
/* 133 */         Pair.of(Integer.valueOf(2), new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 134 */               Pair.of(new BehaviorStrollPosition(MemoryModuleType.MEETING_POINT, 0.4F, 40), Integer.valueOf(2)), 
/* 135 */               Pair.of(new BehaviorBell(), Integer.valueOf(2))))), 
/*     */         
/* 137 */         Pair.of(Integer.valueOf(10), new BehaviorTradePlayer(400, 1600)), 
/* 138 */         Pair.of(Integer.valueOf(10), new BehaviorLookInteract(EntityTypes.PLAYER, 4)), 
/* 139 */         Pair.of(Integer.valueOf(2), new BehaviorWalkAwayBlock(MemoryModuleType.MEETING_POINT, var1, 6, 100, 200)), 
/* 140 */         Pair.of(Integer.valueOf(3), new BehaviorVillageHeroGift(100)), 
/* 141 */         Pair.of(Integer.valueOf(3), new BehaviorPositionValidate(VillagePlaceType.s, MemoryModuleType.MEETING_POINT)), 
/* 142 */         Pair.of(Integer.valueOf(3), new BehaviorGate<>(
/* 143 */             (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(), 
/* 144 */             (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.INTERACTION_TARGET), BehaviorGate.Order.ORDERED, BehaviorGate.Execution.RUN_ONE, 
/*     */ 
/*     */             
/* 147 */             (List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 148 */               Pair.of(new BehaviorTradeVillager(), Integer.valueOf(1))))), 
/*     */ 
/*     */         
/* 151 */         a(), 
/* 152 */         Pair.of(Integer.valueOf(99), new BehaviorSchedule()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> e(VillagerProfession var0, float var1) {
/* 157 */     return ImmutableList.of(
/* 158 */         Pair.of(Integer.valueOf(2), new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 159 */               Pair.of(BehaviorInteract.a((EntityTypes)EntityTypes.VILLAGER, 8, MemoryModuleType.INTERACTION_TARGET, var1, 2), Integer.valueOf(2)), 
/* 160 */               Pair.of(new BehaviorInteract<>((EntityTypes)EntityTypes.VILLAGER, 8, EntityAgeable::canBreed, EntityAgeable::canBreed, MemoryModuleType.BREED_TARGET, var1, 2), Integer.valueOf(1)), 
/* 161 */               Pair.of(BehaviorInteract.a((EntityTypes)EntityTypes.CAT, 8, MemoryModuleType.INTERACTION_TARGET, var1, 2), Integer.valueOf(1)), 
/* 162 */               Pair.of(new BehaviorStrollRandom(var1), Integer.valueOf(1)), 
/* 163 */               Pair.of(new BehaviorLookWalk(var1, 2), Integer.valueOf(1)), 
/* 164 */               Pair.of(new BehaviorBedJump(var1), Integer.valueOf(1)), 
/* 165 */               Pair.of(new BehaviorNop(30, 60), Integer.valueOf(1))))), 
/*     */         
/* 167 */         Pair.of(Integer.valueOf(3), new BehaviorVillageHeroGift(100)), 
/* 168 */         Pair.of(Integer.valueOf(3), new BehaviorLookInteract(EntityTypes.PLAYER, 4)), 
/* 169 */         Pair.of(Integer.valueOf(3), new BehaviorTradePlayer(400, 1600)), 
/* 170 */         Pair.of(Integer.valueOf(3), new BehaviorGate<>(
/* 171 */             (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(), 
/* 172 */             (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.INTERACTION_TARGET), BehaviorGate.Order.ORDERED, BehaviorGate.Execution.RUN_ONE, 
/*     */ 
/*     */             
/* 175 */             (List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 176 */               Pair.of(new BehaviorTradeVillager(), Integer.valueOf(1))))), 
/*     */ 
/*     */         
/* 179 */         Pair.of(Integer.valueOf(3), new BehaviorGate<>(
/* 180 */             (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(), 
/* 181 */             (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.BREED_TARGET), BehaviorGate.Order.ORDERED, BehaviorGate.Execution.RUN_ONE, 
/*     */ 
/*     */             
/* 184 */             (List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 185 */               Pair.of(new BehaviorMakeLove(), Integer.valueOf(1))))), 
/*     */ 
/*     */         
/* 188 */         a(), 
/* 189 */         Pair.of(Integer.valueOf(99), new BehaviorSchedule()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> f(VillagerProfession var0, float var1) {
/* 194 */     float var2 = var1 * 1.5F;
/*     */     
/* 196 */     return ImmutableList.of(
/* 197 */         Pair.of(Integer.valueOf(0), new BehaviorCooldown()), 
/* 198 */         Pair.of(Integer.valueOf(1), BehaviorWalkAway.b((MemoryModuleType)MemoryModuleType.NEAREST_HOSTILE, var2, 6, false)), 
/* 199 */         Pair.of(Integer.valueOf(1), BehaviorWalkAway.b((MemoryModuleType)MemoryModuleType.HURT_BY_ENTITY, var2, 6, false)), 
/* 200 */         Pair.of(Integer.valueOf(3), new BehaviorStrollRandom(var2, 2, 2)), 
/* 201 */         b());
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> g(VillagerProfession var0, float var1) {
/* 206 */     return ImmutableList.of(
/* 207 */         Pair.of(Integer.valueOf(0), new BehaviorBellRing()), 
/* 208 */         Pair.of(Integer.valueOf(0), new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 209 */               Pair.of(new BehaviorWalkAwayBlock(MemoryModuleType.MEETING_POINT, var1 * 1.5F, 2, 150, 200), Integer.valueOf(6)), 
/* 210 */               Pair.of(new BehaviorStrollRandom(var1 * 1.5F), Integer.valueOf(2))))), 
/*     */         
/* 212 */         b(), 
/* 213 */         Pair.of(Integer.valueOf(99), new BehaviorRaidReset()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> h(VillagerProfession var0, float var1) {
/* 218 */     return ImmutableList.of(
/* 219 */         Pair.of(Integer.valueOf(0), new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 220 */               Pair.of(new BehaviorOutsideCelebrate(var1), Integer.valueOf(5)), 
/* 221 */               Pair.of(new BehaviorVictory(var1 * 1.1F), Integer.valueOf(2))))), 
/*     */         
/* 223 */         Pair.of(Integer.valueOf(0), new BehaviorCelebrate(600, 600)), 
/* 224 */         Pair.of(Integer.valueOf(2), new BehaviorHomeRaid(24, var1 * 1.4F)), 
/* 225 */         b(), 
/* 226 */         Pair.of(Integer.valueOf(99), new BehaviorRaidReset()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> i(VillagerProfession var0, float var1) {
/* 231 */     int var2 = 2;
/* 232 */     return ImmutableList.of(
/* 233 */         Pair.of(Integer.valueOf(0), new BehaviorHide(15, 3)), 
/* 234 */         Pair.of(Integer.valueOf(1), new BehaviorHome(32, var1 * 1.25F, 2)), 
/* 235 */         b());
/*     */   }
/*     */ 
/*     */   
/*     */   private static Pair<Integer, Behavior<EntityLiving>> a() {
/* 240 */     return Pair.of(Integer.valueOf(5), new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 241 */             Pair.of(new BehaviorLookTarget(EntityTypes.CAT, 8.0F), Integer.valueOf(8)), 
/* 242 */             Pair.of(new BehaviorLookTarget(EntityTypes.VILLAGER, 8.0F), Integer.valueOf(2)), 
/* 243 */             Pair.of(new BehaviorLookTarget(EntityTypes.PLAYER, 8.0F), Integer.valueOf(2)), 
/* 244 */             Pair.of(new BehaviorLookTarget(EnumCreatureType.CREATURE, 8.0F), Integer.valueOf(1)), 
/* 245 */             Pair.of(new BehaviorLookTarget(EnumCreatureType.WATER_CREATURE, 8.0F), Integer.valueOf(1)), 
/* 246 */             Pair.of(new BehaviorLookTarget(EnumCreatureType.WATER_AMBIENT, 8.0F), Integer.valueOf(1)), 
/* 247 */             Pair.of(new BehaviorLookTarget(EnumCreatureType.MONSTER, 8.0F), Integer.valueOf(1)), 
/* 248 */             Pair.of(new BehaviorNop(30, 60), Integer.valueOf(2)))));
/*     */   }
/*     */ 
/*     */   
/*     */   private static Pair<Integer, Behavior<EntityLiving>> b() {
/* 253 */     return Pair.of(Integer.valueOf(5), new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(
/* 254 */             Pair.of(new BehaviorLookTarget(EntityTypes.VILLAGER, 8.0F), Integer.valueOf(2)), 
/* 255 */             Pair.of(new BehaviorLookTarget(EntityTypes.PLAYER, 8.0F), Integer.valueOf(2)), 
/* 256 */             Pair.of(new BehaviorNop(30, 60), Integer.valueOf(8)))));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Behaviors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */