/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class PiglinAI {
/*  15 */   public static final Item a = Items.GOLD_INGOT;
/*  16 */   private static final IntRange b = TimeRange.a(30, 120);
/*  17 */   private static final IntRange c = TimeRange.a(10, 40);
/*  18 */   private static final IntRange d = TimeRange.a(10, 30);
/*  19 */   private static final IntRange e = TimeRange.a(5, 20);
/*  20 */   private static final IntRange f = TimeRange.a(5, 7);
/*  21 */   private static final IntRange g = TimeRange.a(5, 7);
/*  22 */   private static final Set<Item> h = (Set<Item>)ImmutableSet.of(Items.PORKCHOP, Items.COOKED_PORKCHOP);
/*     */   
/*     */   protected static BehaviorController<?> a(EntityPiglin entitypiglin, BehaviorController<EntityPiglin> behaviorcontroller) {
/*  25 */     a(behaviorcontroller);
/*  26 */     b(behaviorcontroller);
/*  27 */     d(behaviorcontroller);
/*  28 */     b(entitypiglin, behaviorcontroller);
/*  29 */     c(behaviorcontroller);
/*  30 */     e(behaviorcontroller);
/*  31 */     f(behaviorcontroller);
/*  32 */     behaviorcontroller.a((Set<Activity>)ImmutableSet.of(Activity.CORE));
/*  33 */     behaviorcontroller.b(Activity.IDLE);
/*  34 */     behaviorcontroller.e();
/*  35 */     return behaviorcontroller;
/*     */   }
/*     */   
/*     */   protected static void a(EntityPiglin entitypiglin) {
/*  39 */     int i = b.a(entitypiglin.world.random);
/*     */     
/*  41 */     entitypiglin.getBehaviorController().a(MemoryModuleType.HUNTED_RECENTLY, Boolean.valueOf(true), i);
/*     */   }
/*     */   
/*     */   private static void a(BehaviorController<EntityPiglin> behaviorcontroller) {
/*  45 */     behaviorcontroller.a(Activity.CORE, 0, ImmutableList.of(new BehaviorLook(45, 90), new BehavorMove(), new BehaviorInteractDoor(), d(), e(), new BehaviorStopAdmiring<>(), new BehaviorStartAdmiringItem<>(120), new BehaviorCelebrateDeath(300, PiglinAI::a), new BehaviorForgetAnger<>()));
/*     */   }
/*     */   
/*     */   private static void b(BehaviorController<EntityPiglin> behaviorcontroller) {
/*  49 */     behaviorcontroller.a(Activity.IDLE, 10, ImmutableList.of(new BehaviorLookTarget(PiglinAI::b, 14.0F), new BehaviorAttackTargetSet<>(EntityPiglinAbstract::eM, PiglinAI::k), new BehaviorRunIf<>(EntityPiglin::m, new BehaviorHuntHoglin<>()), c(), f(), a(), b(), new BehaviorLookInteract(EntityTypes.PLAYER, 4)));
/*     */   }
/*     */   
/*     */   private static void b(EntityPiglin entitypiglin, BehaviorController<EntityPiglin> behaviorcontroller) {
/*  53 */     behaviorcontroller.a(Activity.FLIGHT, 10, ImmutableList.of(new BehaviorAttackTargetForget<>(entityliving -> !b(entitypiglin, entityliving)), new BehaviorRunIf<>(l -> c(l), new BehaviorRetreat<>(5, 0.75F)), new BehaviorWalkAwayOutOfRange(1.0F), new BehaviorAttack(20), new BehaviorCrossbowAttack<>(), new BehaviorRememberHuntedHoglin<>(), new BehaviorRemoveMemory<>(PiglinAI::j, MemoryModuleType.ATTACK_TARGET)), MemoryModuleType.ATTACK_TARGET);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void c(BehaviorController<EntityPiglin> behaviorcontroller) {
/*  60 */     behaviorcontroller.a(Activity.CELEBRATE, 10, ImmutableList.of(c(), new BehaviorLookTarget(PiglinAI::b, 14.0F), new BehaviorAttackTargetSet<>(EntityPiglinAbstract::eM, PiglinAI::k), new BehaviorRunIf<>(entitypiglin -> !entitypiglin.eU(), new BehaviorCelebrateLocation<>(2, 1.0F)), new BehaviorRunIf<>(EntityPiglin::eU, new BehaviorCelebrateLocation<>(4, 0.6F)), new BehaviorGateSingle<>(
/*     */             
/*  62 */             (List<Pair<Behavior<? super EntityLiving>, Integer>>)ImmutableList.of(Pair.of(new BehaviorLookTarget(EntityTypes.PIGLIN, 8.0F), Integer.valueOf(1)), Pair.of(new BehaviorStrollRandomUnconstrained(0.6F, 2, 1), Integer.valueOf(1)), Pair.of(new BehaviorNop(10, 20), Integer.valueOf(1))))), MemoryModuleType.CELEBRATE_LOCATION);
/*     */   }
/*     */   
/*     */   private static void d(BehaviorController<EntityPiglin> behaviorcontroller) {
/*  66 */     behaviorcontroller.a(Activity.ADMIRE_ITEM, 10, ImmutableList.of(new BehaviorFindAdmirableItem<>(PiglinAI::z, 1.0F, true, 9), new BehaviorStopAdmiringItem<>(9), new BehaviorAdmireTimeout<>(200, 200)), MemoryModuleType.ADMIRING_ITEM);
/*     */   }
/*     */   
/*     */   private static void e(BehaviorController<EntityPiglin> behaviorcontroller) {
/*  70 */     behaviorcontroller.a(Activity.AVOID, 10, ImmutableList.of(BehaviorWalkAway.b((MemoryModuleType)MemoryModuleType.AVOID_TARGET, 1.0F, 12, true), a(), b(), new BehaviorRemoveMemory<>(PiglinAI::o, MemoryModuleType.AVOID_TARGET)), MemoryModuleType.AVOID_TARGET);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void f(BehaviorController<EntityPiglin> behaviorcontroller) {
/*  75 */     behaviorcontroller.a(Activity.RIDE, 10, ImmutableList.of(new BehaviorStartRiding<>(0.8F), new BehaviorLookTarget(PiglinAI::b, 8.0F), new BehaviorRunIf<>(Entity::isPassenger, a()), new BehaviorStopRiding<>(8, PiglinAI::a)), MemoryModuleType.RIDE_TARGET);
/*     */   }
/*     */   
/*     */   private static BehaviorGateSingle<EntityPiglin> a() {
/*  79 */     return new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityPiglin>, Integer>>)ImmutableList.of(Pair.of(new BehaviorLookTarget(EntityTypes.PLAYER, 8.0F), Integer.valueOf(1)), Pair.of(new BehaviorLookTarget(EntityTypes.PIGLIN, 8.0F), Integer.valueOf(1)), Pair.of(new BehaviorLookTarget(8.0F), Integer.valueOf(1)), Pair.of(new BehaviorNop(30, 60), Integer.valueOf(1))));
/*     */   }
/*     */ 
/*     */   
/*     */   private static BehaviorGateSingle<EntityPiglin> b() {
/*  84 */     return new BehaviorGateSingle<>((List<Pair<Behavior<? super EntityPiglin>, Integer>>)ImmutableList.of(Pair.of(new BehaviorStrollRandomUnconstrained(0.6F), Integer.valueOf(2)), Pair.of(BehaviorInteract.a((EntityTypes)EntityTypes.PIGLIN, 8, MemoryModuleType.INTERACTION_TARGET, 0.6F, 2), Integer.valueOf(2)), Pair.of(new BehaviorRunIf<>(PiglinAI::g, new BehaviorLookWalk(0.6F, 3)), Integer.valueOf(2)), Pair.of(new BehaviorNop(30, 60), Integer.valueOf(1))));
/*     */   }
/*     */   
/*     */   private static BehaviorWalkAway<BlockPosition> c() {
/*  88 */     return BehaviorWalkAway.a(MemoryModuleType.NEAREST_REPELLENT, 1.0F, 8, false);
/*     */   }
/*     */   
/*     */   private static BehaviorExpirableMemory<EntityPiglin, EntityLiving> d() {
/*  92 */     return new BehaviorExpirableMemory<>(EntityPiglin::isBaby, (MemoryModuleType)MemoryModuleType.NEAREST_VISIBLE_NEMSIS, MemoryModuleType.AVOID_TARGET, g);
/*     */   }
/*     */   
/*     */   private static BehaviorExpirableMemory<EntityPiglin, EntityLiving> e() {
/*  96 */     return new BehaviorExpirableMemory<>(PiglinAI::j, MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED, MemoryModuleType.AVOID_TARGET, f);
/*     */   }
/*     */   
/*     */   protected static void b(EntityPiglin entitypiglin) {
/* 100 */     BehaviorController<EntityPiglin> behaviorcontroller = entitypiglin.getBehaviorController();
/* 101 */     Activity activity = behaviorcontroller.f().orElse(null);
/*     */     
/* 103 */     behaviorcontroller.a((List<Activity>)ImmutableList.of(Activity.ADMIRE_ITEM, Activity.FLIGHT, Activity.AVOID, Activity.CELEBRATE, Activity.RIDE, Activity.IDLE));
/* 104 */     Activity activity1 = behaviorcontroller.f().orElse(null);
/*     */     
/* 106 */     if (activity != activity1) {
/* 107 */       Objects.requireNonNull(entitypiglin); d(entitypiglin).ifPresent(entitypiglin::a);
/*     */     } 
/*     */     
/* 110 */     entitypiglin.setAggressive(behaviorcontroller.hasMemory(MemoryModuleType.ATTACK_TARGET));
/* 111 */     if (!behaviorcontroller.hasMemory(MemoryModuleType.RIDE_TARGET) && h(entitypiglin)) {
/* 112 */       entitypiglin.stopRiding();
/*     */     }
/*     */     
/* 115 */     if (!behaviorcontroller.hasMemory(MemoryModuleType.CELEBRATE_LOCATION)) {
/* 116 */       behaviorcontroller.removeMemory(MemoryModuleType.DANCING);
/*     */     }
/*     */     
/* 119 */     entitypiglin.u(behaviorcontroller.hasMemory(MemoryModuleType.DANCING));
/*     */   }
/*     */   
/*     */   private static boolean h(EntityPiglin entitypiglin) {
/* 123 */     if (!entitypiglin.isBaby()) {
/* 124 */       return false;
/*     */     }
/* 126 */     Entity entity = entitypiglin.getVehicle();
/*     */     
/* 128 */     return ((entity instanceof EntityPiglin && ((EntityPiglin)entity).isBaby()) || (entity instanceof EntityHoglin && ((EntityHoglin)entity).isBaby()));
/*     */   }
/*     */   
/*     */   protected static void a(EntityPiglin entitypiglin, EntityItem entityitem) {
/*     */     ItemStack itemstack;
/* 133 */     n(entitypiglin);
/*     */ 
/*     */ 
/*     */     
/* 137 */     if (entityitem.getItemStack().getItem() == Items.GOLD_NUGGET && !CraftEventFactory.callEntityPickupItemEvent(entitypiglin, entityitem, 0, false).isCancelled()) {
/* 138 */       entitypiglin.receive(entityitem, entityitem.getItemStack().getCount());
/* 139 */       itemstack = entityitem.getItemStack();
/* 140 */       entityitem.die();
/* 141 */     } else if (!CraftEventFactory.callEntityPickupItemEvent(entitypiglin, entityitem, entityitem.getItemStack().getCount() - 1, false).isCancelled()) {
/* 142 */       entitypiglin.receive(entityitem, 1);
/* 143 */       itemstack = a(entityitem);
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 149 */     Item item = itemstack.getItem();
/*     */     
/* 151 */     if (a(item)) {
/* 152 */       entitypiglin.getBehaviorController().removeMemory(MemoryModuleType.TIME_TRYING_TO_REACH_ADMIRE_ITEM);
/* 153 */       c(entitypiglin, itemstack);
/* 154 */       d(entitypiglin);
/* 155 */     } else if (c(item) && !u(entitypiglin)) {
/* 156 */       s(entitypiglin);
/*     */     } else {
/* 158 */       boolean flag = entitypiglin.g(itemstack, entityitem);
/*     */       
/* 160 */       if (!flag) {
/* 161 */         d(entitypiglin, itemstack);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void c(EntityPiglin entitypiglin, ItemStack itemstack) {
/* 167 */     if (y(entitypiglin)) {
/* 168 */       entitypiglin.a(entitypiglin.b(EnumHand.OFF_HAND));
/*     */     }
/*     */     
/* 171 */     entitypiglin.n(itemstack);
/*     */   }
/*     */   
/*     */   private static ItemStack a(EntityItem entityitem) {
/* 175 */     ItemStack itemstack = entityitem.getItemStack();
/* 176 */     ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/*     */     
/* 178 */     if (itemstack.isEmpty()) {
/* 179 */       entityitem.die();
/*     */     } else {
/* 181 */       entityitem.setItemStack(itemstack);
/*     */     } 
/*     */     
/* 184 */     return itemstack1;
/*     */   }
/*     */   
/*     */   protected static void a(EntityPiglin entitypiglin, boolean flag) {
/* 188 */     ItemStack itemstack = entitypiglin.b(EnumHand.OFF_HAND);
/*     */     
/* 190 */     entitypiglin.a(EnumHand.OFF_HAND, ItemStack.b);
/*     */ 
/*     */     
/* 193 */     if (entitypiglin.eM()) {
/* 194 */       boolean flag1 = b(itemstack.getItem());
/* 195 */       if (flag && flag1) {
/* 196 */         a(entitypiglin, i(entitypiglin));
/* 197 */       } else if (!flag1) {
/* 198 */         boolean flag2 = entitypiglin.g(itemstack);
/*     */         
/* 200 */         if (!flag2) {
/* 201 */           d(entitypiglin, itemstack);
/*     */         }
/*     */       } 
/*     */     } else {
/* 205 */       boolean flag1 = entitypiglin.g(itemstack);
/* 206 */       if (!flag1) {
/* 207 */         ItemStack itemstack1 = entitypiglin.getItemInMainHand();
/*     */         
/* 209 */         if (a(itemstack1.getItem())) {
/* 210 */           d(entitypiglin, itemstack1);
/*     */         } else {
/* 212 */           a(entitypiglin, Collections.singletonList(itemstack1));
/*     */         } 
/*     */         
/* 215 */         entitypiglin.m(itemstack);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void c(EntityPiglin entitypiglin) {
/* 222 */     if (v(entitypiglin) && !entitypiglin.getItemInOffHand().isEmpty()) {
/* 223 */       entitypiglin.a(entitypiglin.getItemInOffHand());
/* 224 */       entitypiglin.a(EnumHand.OFF_HAND, ItemStack.b);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void d(EntityPiglin entitypiglin, ItemStack itemstack) {
/* 230 */     ItemStack itemstack1 = entitypiglin.k(itemstack);
/*     */     
/* 232 */     b(entitypiglin, Collections.singletonList(itemstack1));
/*     */   }
/*     */   
/*     */   private static void a(EntityPiglin entitypiglin, List<ItemStack> list) {
/* 236 */     Optional<EntityHuman> optional = entitypiglin.getBehaviorController().getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER);
/*     */     
/* 238 */     if (optional.isPresent()) {
/* 239 */       a(entitypiglin, optional.get(), list);
/*     */     } else {
/* 241 */       b(entitypiglin, list);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void b(EntityPiglin entitypiglin, List<ItemStack> list) {
/* 247 */     a(entitypiglin, list, t(entitypiglin));
/*     */   }
/*     */   
/*     */   private static void a(EntityPiglin entitypiglin, EntityHuman entityhuman, List<ItemStack> list) {
/* 251 */     a(entitypiglin, list, entityhuman.getPositionVector());
/*     */   }
/*     */   
/*     */   private static void a(EntityPiglin entitypiglin, List<ItemStack> list, Vec3D vec3d) {
/* 255 */     if (!list.isEmpty()) {
/* 256 */       entitypiglin.swingHand(EnumHand.OFF_HAND);
/* 257 */       Iterator<ItemStack> iterator = list.iterator();
/*     */       
/* 259 */       while (iterator.hasNext()) {
/* 260 */         ItemStack itemstack = iterator.next();
/*     */         
/* 262 */         BehaviorUtil.a(entitypiglin, itemstack, vec3d.add(0.0D, 1.0D, 0.0D));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<ItemStack> i(EntityPiglin entitypiglin) {
/* 269 */     LootTable loottable = entitypiglin.world.getMinecraftServer().getLootTableRegistry().getLootTable(LootTables.ay);
/* 270 */     List<ItemStack> list = loottable.populateLoot((new LootTableInfo.Builder((WorldServer)entitypiglin.world)).<Entity>set(LootContextParameters.THIS_ENTITY, entitypiglin).a(entitypiglin.world.random).build(LootContextParameterSets.BARTER));
/*     */     
/* 272 */     return list;
/*     */   }
/*     */   
/*     */   private static boolean a(EntityLiving entityliving, EntityLiving entityliving1) {
/* 276 */     return (entityliving1.getEntityType() != EntityTypes.HOGLIN) ? false : (((new Random(entityliving.world.getTime())).nextFloat() < 0.1F));
/*     */   }
/*     */   
/*     */   protected static boolean a(EntityPiglin entitypiglin, ItemStack itemstack) {
/* 280 */     Item item = itemstack.getItem();
/*     */     
/* 282 */     if (item.a(TagsItem.PIGLIN_REPELLENTS))
/* 283 */       return false; 
/* 284 */     if (x(entitypiglin) && entitypiglin.getBehaviorController().hasMemory(MemoryModuleType.ATTACK_TARGET))
/* 285 */       return false; 
/* 286 */     if (b(item)) {
/* 287 */       return z(entitypiglin);
/*     */     }
/* 289 */     boolean flag = entitypiglin.l(itemstack);
/*     */     
/* 291 */     return (item == Items.GOLD_NUGGET) ? flag : (c(item) ? ((!u(entitypiglin) && flag)) : (!a(item) ? entitypiglin.o(itemstack) : ((z(entitypiglin) && flag))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean a(Item item) {
/* 296 */     return item.a(TagsItem.PIGLIN_LOVED);
/*     */   }
/*     */   
/*     */   private static boolean a(EntityPiglin entitypiglin, Entity entity) {
/* 300 */     if (!(entity instanceof EntityInsentient)) {
/* 301 */       return false;
/*     */     }
/* 303 */     EntityInsentient entityinsentient = (EntityInsentient)entity;
/*     */     
/* 305 */     return (!entityinsentient.isBaby() || !entityinsentient.isAlive() || h(entitypiglin) || h(entityinsentient) || (entityinsentient instanceof EntityPiglin && entityinsentient.getVehicle() == null));
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean b(EntityPiglin entitypiglin, EntityLiving entityliving) {
/* 310 */     return k(entitypiglin).filter(entityliving1 -> (entityliving1 == entityliving))
/*     */       
/* 312 */       .isPresent();
/*     */   }
/*     */   
/*     */   private static boolean j(EntityPiglin entitypiglin) {
/* 316 */     BehaviorController<EntityPiglin> behaviorcontroller = entitypiglin.getBehaviorController();
/*     */     
/* 318 */     if (behaviorcontroller.hasMemory(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED)) {
/* 319 */       EntityLiving entityliving = behaviorcontroller.<EntityLiving>getMemory(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED).get();
/*     */       
/* 321 */       return entitypiglin.a(entityliving, 6.0D);
/*     */     } 
/* 323 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Optional<? extends EntityLiving> k(EntityPiglin entitypiglin) {
/* 328 */     BehaviorController<EntityPiglin> behaviorcontroller = entitypiglin.getBehaviorController();
/*     */     
/* 330 */     if (j(entitypiglin)) {
/* 331 */       return Optional.empty();
/*     */     }
/* 333 */     Optional<EntityLiving> optional = BehaviorUtil.a(entitypiglin, MemoryModuleType.ANGRY_AT);
/*     */     
/* 335 */     if (optional.isPresent() && e(optional.get())) {
/* 336 */       return optional;
/*     */     }
/*     */ 
/*     */     
/* 340 */     if (behaviorcontroller.hasMemory(MemoryModuleType.UNIVERSAL_ANGER)) {
/* 341 */       Optional<EntityHuman> optional3 = behaviorcontroller.getMemory(MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER);
/* 342 */       if (optional3.isPresent()) {
/* 343 */         return (Optional)optional3;
/*     */       }
/*     */     } 
/*     */     
/* 347 */     Optional<EntityInsentient> optional1 = behaviorcontroller.getMemory(MemoryModuleType.NEAREST_VISIBLE_NEMSIS);
/* 348 */     if (optional1.isPresent()) {
/* 349 */       return (Optional)optional1;
/*     */     }
/* 351 */     Optional<EntityHuman> optional2 = behaviorcontroller.getMemory(MemoryModuleType.NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD);
/*     */     
/* 353 */     return (optional2.isPresent() && e(optional2.get())) ? (Optional)optional2 : Optional.<EntityLiving>empty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(EntityHuman entityhuman, boolean flag) {
/* 360 */     List<EntityPiglinAbstract> list = entityhuman.world.a((Class)EntityPiglin.class, entityhuman.getBoundingBox().g(16.0D));
/*     */     
/* 362 */     list.stream().filter(PiglinAI::d).filter(entitypiglin -> 
/* 363 */         (!flag || BehaviorUtil.c(entitypiglin, entityhuman)))
/* 364 */       .forEach(entitypiglin -> {
/*     */           if (entitypiglin.world.getGameRules().getBoolean(GameRules.UNIVERSAL_ANGER)) {
/*     */             d(entitypiglin, entityhuman);
/*     */           } else {
/*     */             c(entitypiglin, entityhuman);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumInteractionResult a(EntityPiglin entitypiglin, EntityHuman entityhuman, EnumHand enumhand) {
/* 375 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 377 */     if (b(entitypiglin, itemstack)) {
/* 378 */       ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/*     */       
/* 380 */       c(entitypiglin, itemstack1);
/* 381 */       d(entitypiglin);
/* 382 */       n(entitypiglin);
/* 383 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/* 385 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static boolean b(EntityPiglin entitypiglin, ItemStack itemstack) {
/* 390 */     return (!x(entitypiglin) && !v(entitypiglin) && entitypiglin.eM() && b(itemstack.getItem()));
/*     */   }
/*     */   
/*     */   protected static void a(EntityPiglin entitypiglin, EntityLiving entityliving) {
/* 394 */     if (!(entityliving instanceof EntityPiglin)) {
/* 395 */       if (y(entitypiglin)) {
/* 396 */         a(entitypiglin, false);
/*     */       }
/*     */       
/* 399 */       BehaviorController<EntityPiglin> behaviorcontroller = entitypiglin.getBehaviorController();
/*     */       
/* 401 */       behaviorcontroller.removeMemory(MemoryModuleType.CELEBRATE_LOCATION);
/* 402 */       behaviorcontroller.removeMemory(MemoryModuleType.DANCING);
/* 403 */       behaviorcontroller.removeMemory(MemoryModuleType.ADMIRING_ITEM);
/* 404 */       if (entityliving instanceof EntityHuman) {
/* 405 */         behaviorcontroller.a(MemoryModuleType.ADMIRING_DISABLED, Boolean.valueOf(true), 400L);
/*     */       }
/*     */       
/* 408 */       g(entitypiglin).ifPresent(entityliving1 -> {
/*     */             if (entityliving1.getEntityType() != entityliving.getEntityType()) {
/*     */               behaviorcontroller.removeMemory(MemoryModuleType.AVOID_TARGET);
/*     */             }
/*     */           });
/*     */       
/* 414 */       if (entitypiglin.isBaby()) {
/* 415 */         behaviorcontroller.a(MemoryModuleType.AVOID_TARGET, entityliving, 100L);
/* 416 */         if (e(entityliving)) {
/* 417 */           b(entitypiglin, entityliving);
/*     */         }
/*     */       }
/* 420 */       else if (entityliving.getEntityType() == EntityTypes.HOGLIN && q(entitypiglin)) {
/* 421 */         e(entitypiglin, entityliving);
/* 422 */         c(entitypiglin, entityliving);
/*     */       } else {
/* 424 */         a(entitypiglin, entityliving);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static void a(EntityPiglinAbstract entitypiglinabstract, EntityLiving entityliving) {
/* 430 */     if (!entitypiglinabstract.getBehaviorController().c(Activity.AVOID) && 
/* 431 */       e(entityliving) && 
/* 432 */       !BehaviorUtil.a(entitypiglinabstract, entityliving, 4.0D)) {
/* 433 */       if (entityliving.getEntityType() == EntityTypes.PLAYER && entitypiglinabstract.world.getGameRules().getBoolean(GameRules.UNIVERSAL_ANGER)) {
/* 434 */         d(entitypiglinabstract, entityliving);
/* 435 */         a(entitypiglinabstract);
/*     */       } else {
/* 437 */         c(entitypiglinabstract, entityliving);
/* 438 */         b(entitypiglinabstract, entityliving);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Optional<SoundEffect> d(EntityPiglin entitypiglin) {
/* 447 */     return entitypiglin.getBehaviorController().f().map(activity -> a(entitypiglin, activity));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static SoundEffect a(EntityPiglin entitypiglin, Activity activity) {
/* 453 */     return (activity == Activity.FLIGHT) ? SoundEffects.ENTITY_PIGLIN_ANGRY : (entitypiglin.isConverting() ? SoundEffects.ENTITY_PIGLIN_RETREAT : ((activity == Activity.AVOID && l(entitypiglin)) ? SoundEffects.ENTITY_PIGLIN_RETREAT : ((activity == Activity.ADMIRE_ITEM) ? SoundEffects.ENTITY_PIGLIN_ADMIRING_ITEM : ((activity == Activity.CELEBRATE) ? SoundEffects.ENTITY_PIGLIN_CELEBRATE : (f(entitypiglin) ? SoundEffects.ENTITY_PIGLIN_JEALOUS : (w(entitypiglin) ? SoundEffects.ENTITY_PIGLIN_RETREAT : SoundEffects.ENTITY_PIGLIN_AMBIENT))))));
/*     */   }
/*     */   
/*     */   private static boolean l(EntityPiglin entitypiglin) {
/* 457 */     BehaviorController<EntityPiglin> behaviorcontroller = entitypiglin.getBehaviorController();
/*     */     
/* 459 */     return !behaviorcontroller.hasMemory(MemoryModuleType.AVOID_TARGET) ? false : ((EntityLiving)behaviorcontroller.<EntityLiving>getMemory(MemoryModuleType.AVOID_TARGET).get()).a(entitypiglin, 12.0D);
/*     */   }
/*     */   
/*     */   protected static boolean e(EntityPiglin entitypiglin) {
/* 463 */     return (entitypiglin.getBehaviorController().hasMemory(MemoryModuleType.HUNTED_RECENTLY) || m(entitypiglin).stream().anyMatch(entitypiglinabstract -> entitypiglinabstract.getBehaviorController().hasMemory(MemoryModuleType.HUNTED_RECENTLY)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<EntityPiglinAbstract> m(EntityPiglin entitypiglin) {
/* 469 */     return (List<EntityPiglinAbstract>)entitypiglin.getBehaviorController().getMemory(MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS).orElse(ImmutableList.of());
/*     */   }
/*     */   
/*     */   private static List<EntityPiglinAbstract> e(EntityPiglinAbstract entitypiglinabstract) {
/* 473 */     return (List<EntityPiglinAbstract>)entitypiglinabstract.getBehaviorController().getMemory(MemoryModuleType.NEARBY_ADULT_PIGLINS).orElse(ImmutableList.of());
/*     */   }
/*     */   public static boolean a(EntityLiving entityliving) {
/*     */     Item item;
/* 477 */     Iterable<ItemStack> iterable = entityliving.getArmorItems();
/* 478 */     Iterator<ItemStack> iterator = iterable.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 483 */       if (!iterator.hasNext()) {
/* 484 */         return false;
/*     */       }
/*     */       
/* 487 */       ItemStack itemstack = iterator.next();
/*     */       
/* 489 */       item = itemstack.getItem();
/* 490 */     } while (!(item instanceof ItemArmor) || ((ItemArmor)item).ab_() != EnumArmorMaterial.GOLD);
/*     */     
/* 492 */     return true;
/*     */   }
/*     */   
/*     */   private static void n(EntityPiglin entitypiglin) {
/* 496 */     entitypiglin.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
/* 497 */     entitypiglin.getNavigation().o();
/*     */   }
/*     */   
/*     */   private static BehaviorRunSometimes<EntityPiglin> f() {
/* 501 */     return new BehaviorRunSometimes<>(new BehaviorExpirableMemory<>(EntityPiglin::isBaby, (MemoryModuleType)MemoryModuleType.NEAREST_VISIBLE_BABY_HOGLIN, MemoryModuleType.RIDE_TARGET, d), c);
/*     */   }
/*     */   
/*     */   protected static void b(EntityPiglinAbstract entitypiglinabstract, EntityLiving entityliving) {
/* 505 */     e(entitypiglinabstract).forEach(entitypiglinabstract1 -> {
/*     */           if (entityliving.getEntityType() != EntityTypes.HOGLIN || (entitypiglinabstract1.m() && ((EntityHoglin)entityliving).eO())) {
/*     */             e(entitypiglinabstract1, entityliving);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   protected static void a(EntityPiglinAbstract entitypiglinabstract) {
/* 513 */     e(entitypiglinabstract).forEach(entitypiglinabstract1 -> b(entitypiglinabstract1).ifPresent(()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void f(EntityPiglin entitypiglin) {
/* 521 */     m(entitypiglin).forEach(PiglinAI::c);
/*     */   }
/*     */   
/*     */   protected static void c(EntityPiglinAbstract entitypiglinabstract, EntityLiving entityliving) {
/* 525 */     if (e(entityliving)) {
/* 526 */       entitypiglinabstract.getBehaviorController().removeMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
/* 527 */       entitypiglinabstract.getBehaviorController().a(MemoryModuleType.ANGRY_AT, entityliving.getUniqueID(), 600L);
/* 528 */       if (entityliving.getEntityType() == EntityTypes.HOGLIN && entitypiglinabstract.m()) {
/* 529 */         c(entitypiglinabstract);
/*     */       }
/*     */       
/* 532 */       if (entityliving.getEntityType() == EntityTypes.PLAYER && entitypiglinabstract.world.getGameRules().getBoolean(GameRules.UNIVERSAL_ANGER)) {
/* 533 */         entitypiglinabstract.getBehaviorController().a(MemoryModuleType.UNIVERSAL_ANGER, Boolean.valueOf(true), 600L);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void d(EntityPiglinAbstract entitypiglinabstract, EntityLiving entityliving) {
/* 540 */     Optional<EntityHuman> optional = b(entitypiglinabstract);
/*     */     
/* 542 */     if (optional.isPresent()) {
/* 543 */       c(entitypiglinabstract, optional.get());
/*     */     } else {
/* 545 */       c(entitypiglinabstract, entityliving);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void e(EntityPiglinAbstract entitypiglinabstract, EntityLiving entityliving) {
/* 551 */     Optional<EntityLiving> optional = f(entitypiglinabstract);
/* 552 */     EntityLiving entityliving1 = BehaviorUtil.a(entitypiglinabstract, optional, entityliving);
/*     */     
/* 554 */     if (!optional.isPresent() || optional.get() != entityliving1) {
/* 555 */       c(entitypiglinabstract, entityliving1);
/*     */     }
/*     */   }
/*     */   
/*     */   private static Optional<EntityLiving> f(EntityPiglinAbstract entitypiglinabstract) {
/* 560 */     return BehaviorUtil.a(entitypiglinabstract, MemoryModuleType.ANGRY_AT);
/*     */   }
/*     */   
/*     */   public static Optional<EntityLiving> g(EntityPiglin entitypiglin) {
/* 564 */     return entitypiglin.getBehaviorController().hasMemory(MemoryModuleType.AVOID_TARGET) ? entitypiglin.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.AVOID_TARGET) : Optional.<EntityLiving>empty();
/*     */   }
/*     */   
/*     */   public static Optional<EntityHuman> b(EntityPiglinAbstract entitypiglinabstract) {
/* 568 */     return entitypiglinabstract.getBehaviorController().hasMemory(MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER) ? entitypiglinabstract.getBehaviorController().<EntityHuman>getMemory(MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER) : Optional.<EntityHuman>empty();
/*     */   }
/*     */   
/*     */   private static void c(EntityPiglin entitypiglin, EntityLiving entityliving) {
/* 572 */     m(entitypiglin).stream().filter(entitypiglinabstract -> entitypiglinabstract instanceof EntityPiglin)
/*     */       
/* 574 */       .forEach(entitypiglinabstract -> d((EntityPiglin)entitypiglinabstract, entityliving));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void d(EntityPiglin entitypiglin, EntityLiving entityliving) {
/* 580 */     BehaviorController<EntityPiglin> behaviorcontroller = entitypiglin.getBehaviorController();
/* 581 */     EntityLiving entityliving1 = BehaviorUtil.a(entitypiglin, behaviorcontroller.getMemory(MemoryModuleType.AVOID_TARGET), entityliving);
/*     */     
/* 583 */     entityliving1 = BehaviorUtil.a(entitypiglin, behaviorcontroller.getMemory(MemoryModuleType.ATTACK_TARGET), entityliving1);
/* 584 */     e(entitypiglin, entityliving1);
/*     */   }
/*     */   
/*     */   private static boolean o(EntityPiglin entitypiglin) {
/* 588 */     BehaviorController<EntityPiglin> behaviorcontroller = entitypiglin.getBehaviorController();
/*     */     
/* 590 */     if (!behaviorcontroller.hasMemory(MemoryModuleType.AVOID_TARGET)) {
/* 591 */       return true;
/*     */     }
/* 593 */     EntityLiving entityliving = behaviorcontroller.<EntityLiving>getMemory(MemoryModuleType.AVOID_TARGET).get();
/* 594 */     EntityTypes<?> entitytypes = entityliving.getEntityType();
/*     */     
/* 596 */     return (entitytypes == EntityTypes.HOGLIN) ? p(entitypiglin) : (a(entitytypes) ? (!behaviorcontroller.b(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED, entityliving)) : false);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean p(EntityPiglin entitypiglin) {
/* 601 */     return !q(entitypiglin);
/*     */   }
/*     */   
/*     */   private static boolean q(EntityPiglin entitypiglin) {
/* 605 */     int i = ((Integer)entitypiglin.getBehaviorController().<Integer>getMemory(MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT).orElse(Integer.valueOf(0))).intValue() + 1;
/* 606 */     int j = ((Integer)entitypiglin.getBehaviorController().<Integer>getMemory(MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT).orElse(Integer.valueOf(0))).intValue();
/*     */     
/* 608 */     return (j > i);
/*     */   }
/*     */   
/*     */   private static void e(EntityPiglin entitypiglin, EntityLiving entityliving) {
/* 612 */     entitypiglin.getBehaviorController().removeMemory(MemoryModuleType.ANGRY_AT);
/* 613 */     entitypiglin.getBehaviorController().removeMemory(MemoryModuleType.ATTACK_TARGET);
/* 614 */     entitypiglin.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
/* 615 */     entitypiglin.getBehaviorController().a(MemoryModuleType.AVOID_TARGET, entityliving, e.a(entitypiglin.world.random));
/* 616 */     c(entitypiglin);
/*     */   }
/*     */   
/*     */   protected static void c(EntityPiglinAbstract entitypiglinabstract) {
/* 620 */     entitypiglinabstract.getBehaviorController().a(MemoryModuleType.HUNTED_RECENTLY, Boolean.valueOf(true), b.a(entitypiglinabstract.world.random));
/*     */   }
/*     */   
/*     */   private static void s(EntityPiglin entitypiglin) {
/* 624 */     entitypiglin.getBehaviorController().a(MemoryModuleType.ATE_RECENTLY, Boolean.valueOf(true), 200L);
/*     */   }
/*     */   
/*     */   private static Vec3D t(EntityPiglin entitypiglin) {
/* 628 */     Vec3D vec3d = RandomPositionGenerator.b(entitypiglin, 4, 2);
/*     */     
/* 630 */     return (vec3d == null) ? entitypiglin.getPositionVector() : vec3d;
/*     */   }
/*     */   
/*     */   private static boolean u(EntityPiglin entitypiglin) {
/* 634 */     return entitypiglin.getBehaviorController().hasMemory(MemoryModuleType.ATE_RECENTLY);
/*     */   }
/*     */   
/*     */   protected static boolean d(EntityPiglinAbstract entitypiglinabstract) {
/* 638 */     return entitypiglinabstract.getBehaviorController().c(Activity.IDLE);
/*     */   }
/*     */   
/*     */   private static boolean c(EntityLiving entityliving) {
/* 642 */     return entityliving.a(Items.CROSSBOW);
/*     */   }
/*     */   
/*     */   private static void d(EntityLiving entityliving) {
/* 646 */     entityliving.getBehaviorController().a(MemoryModuleType.ADMIRING_ITEM, Boolean.valueOf(true), 120L);
/*     */   }
/*     */   
/*     */   private static boolean v(EntityPiglin entitypiglin) {
/* 650 */     return entitypiglin.getBehaviorController().hasMemory(MemoryModuleType.ADMIRING_ITEM);
/*     */   }
/*     */   
/*     */   private static boolean b(Item item) {
/* 654 */     return (item == a);
/*     */   }
/*     */   
/*     */   private static boolean c(Item item) {
/* 658 */     return h.contains(item);
/*     */   }
/*     */   
/*     */   private static boolean e(EntityLiving entityliving) {
/* 662 */     return IEntitySelector.f.test(entityliving);
/*     */   }
/*     */   
/*     */   private static boolean w(EntityPiglin entitypiglin) {
/* 666 */     return entitypiglin.getBehaviorController().hasMemory(MemoryModuleType.NEAREST_REPELLENT);
/*     */   }
/*     */   
/*     */   private static boolean f(EntityLiving entityliving) {
/* 670 */     return entityliving.getBehaviorController().hasMemory(MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM);
/*     */   }
/*     */   
/*     */   private static boolean g(EntityLiving entityliving) {
/* 674 */     return !f(entityliving);
/*     */   }
/*     */   
/*     */   public static boolean b(EntityLiving entityliving) {
/* 678 */     return (entityliving.getEntityType() == EntityTypes.PLAYER && entityliving.a(PiglinAI::a));
/*     */   }
/*     */   
/*     */   private static boolean x(EntityPiglin entitypiglin) {
/* 682 */     return entitypiglin.getBehaviorController().hasMemory(MemoryModuleType.ADMIRING_DISABLED);
/*     */   }
/*     */   
/*     */   private static boolean h(EntityLiving entityliving) {
/* 686 */     return entityliving.getBehaviorController().hasMemory(MemoryModuleType.HURT_BY);
/*     */   }
/*     */   
/*     */   private static boolean y(EntityPiglin entitypiglin) {
/* 690 */     return !entitypiglin.getItemInOffHand().isEmpty();
/*     */   }
/*     */   
/*     */   private static boolean z(EntityPiglin entitypiglin) {
/* 694 */     return (entitypiglin.getItemInOffHand().isEmpty() || !a(entitypiglin.getItemInOffHand().getItem()));
/*     */   }
/*     */   
/*     */   public static boolean a(EntityTypes<EntityPigZombie> entitytypes) {
/* 698 */     return (entitytypes == EntityTypes.ZOMBIFIED_PIGLIN || entitytypes == EntityTypes.ZOGLIN);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PiglinAI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */