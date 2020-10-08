/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.raid.RaidStopEvent;
/*     */ 
/*     */ public class Raid {
/*  22 */   private static final IChatBaseComponent a = new ChatMessage("event.minecraft.raid");
/*  23 */   private static final IChatBaseComponent b = new ChatMessage("event.minecraft.raid.victory");
/*  24 */   private static final IChatBaseComponent c = new ChatMessage("event.minecraft.raid.defeat");
/*  25 */   private static final IChatBaseComponent d = a.mutableCopy().c(" - ").addSibling(b);
/*  26 */   private static final IChatBaseComponent e = a.mutableCopy().c(" - ").addSibling(c);
/*  27 */   private final Map<Integer, EntityRaider> f = Maps.newHashMap();
/*  28 */   private final Map<Integer, Set<EntityRaider>> raiders = Maps.newHashMap();
/*  29 */   public final Set<UUID> heroes = Sets.newHashSet();
/*     */   public long ticksActive;
/*     */   private BlockPosition center;
/*     */   private final WorldServer world;
/*     */   private boolean started;
/*     */   private final int id;
/*     */   public float totalHealth;
/*     */   public int badOmenLevel;
/*     */   private boolean active;
/*     */   private int groupsSpawned;
/*     */   private final BossBattleServer bossBattle;
/*     */   private int postRaidTicks;
/*     */   private int preRaidTicks;
/*     */   private final Random random;
/*     */   public final int numGroups;
/*     */   private Status status;
/*     */   private int x;
/*     */   private Optional<BlockPosition> y;
/*     */   
/*     */   public Raid(int i, WorldServer worldserver, BlockPosition blockposition) {
/*  49 */     this.bossBattle = new BossBattleServer(a, BossBattle.BarColor.RED, BossBattle.BarStyle.NOTCHED_10);
/*  50 */     this.random = new Random();
/*  51 */     this.y = Optional.empty();
/*  52 */     this.id = i;
/*  53 */     this.world = worldserver;
/*  54 */     this.active = true;
/*  55 */     this.preRaidTicks = 300;
/*  56 */     this.bossBattle.setProgress(0.0F);
/*  57 */     this.center = blockposition;
/*  58 */     this.numGroups = a(worldserver.getDifficulty());
/*  59 */     this.status = Status.ONGOING;
/*     */   }
/*     */   
/*     */   public Raid(WorldServer worldserver, NBTTagCompound nbttagcompound) {
/*  63 */     this.bossBattle = new BossBattleServer(a, BossBattle.BarColor.RED, BossBattle.BarStyle.NOTCHED_10);
/*  64 */     this.random = new Random();
/*  65 */     this.y = Optional.empty();
/*  66 */     this.world = worldserver;
/*  67 */     this.id = nbttagcompound.getInt("Id");
/*  68 */     this.started = nbttagcompound.getBoolean("Started");
/*  69 */     this.active = nbttagcompound.getBoolean("Active");
/*  70 */     this.ticksActive = nbttagcompound.getLong("TicksActive");
/*  71 */     this.badOmenLevel = nbttagcompound.getInt("BadOmenLevel");
/*  72 */     this.groupsSpawned = nbttagcompound.getInt("GroupsSpawned");
/*  73 */     this.preRaidTicks = nbttagcompound.getInt("PreRaidTicks");
/*  74 */     this.postRaidTicks = nbttagcompound.getInt("PostRaidTicks");
/*  75 */     this.totalHealth = nbttagcompound.getFloat("TotalHealth");
/*  76 */     this.center = new BlockPosition(nbttagcompound.getInt("CX"), nbttagcompound.getInt("CY"), nbttagcompound.getInt("CZ"));
/*  77 */     this.numGroups = nbttagcompound.getInt("NumGroups");
/*  78 */     this.status = Status.b(nbttagcompound.getString("Status"));
/*  79 */     this.heroes.clear();
/*  80 */     if (nbttagcompound.hasKeyOfType("HeroesOfTheVillage", 9)) {
/*  81 */       NBTTagList nbttaglist = nbttagcompound.getList("HeroesOfTheVillage", 11);
/*     */       
/*  83 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  84 */         this.heroes.add(GameProfileSerializer.a(nbttaglist.get(i)));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  91 */     return (isVictory() || isLoss());
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  95 */     return (c() && r() == 0 && this.preRaidTicks > 0);
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  99 */     return (this.groupsSpawned > 0);
/*     */   }
/*     */   
/*     */   public boolean isStopped() {
/* 103 */     return (this.status == Status.STOPPED);
/*     */   }
/*     */   
/*     */   public boolean isVictory() {
/* 107 */     return (this.status == Status.VICTORY);
/*     */   }
/*     */   
/*     */   public boolean isLoss() {
/* 111 */     return (this.status == Status.LOSS);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInProgress() {
/* 116 */     return (this.status == Status.ONGOING);
/*     */   }
/*     */ 
/*     */   
/*     */   public World getWorld() {
/* 121 */     return this.world;
/*     */   }
/*     */   
/*     */   public boolean isStarted() {
/* 125 */     return this.started;
/*     */   }
/*     */   
/*     */   public int getGroupsSpawned() {
/* 129 */     return this.groupsSpawned;
/*     */   }
/*     */   
/*     */   private Predicate<EntityPlayer> x() {
/* 133 */     return entityplayer -> {
/*     */         BlockPosition blockposition = entityplayer.getChunkCoordinates();
/*     */         
/* 136 */         return (entityplayer.isAlive() && this.world.b_(blockposition) == this);
/*     */       };
/*     */   }
/*     */   
/*     */   private void y() {
/* 141 */     Set<EntityPlayer> set = Sets.newHashSet(this.bossBattle.getPlayers());
/* 142 */     List<EntityPlayer> list = this.world.a(x());
/* 143 */     Iterator<EntityPlayer> iterator = list.iterator();
/*     */ 
/*     */ 
/*     */     
/* 147 */     while (iterator.hasNext()) {
/* 148 */       EntityPlayer entityplayer = iterator.next();
/* 149 */       if (!set.contains(entityplayer)) {
/* 150 */         this.bossBattle.addPlayer(entityplayer);
/*     */       }
/*     */     } 
/*     */     
/* 154 */     iterator = set.iterator();
/*     */     
/* 156 */     while (iterator.hasNext()) {
/* 157 */       EntityPlayer entityplayer = iterator.next();
/* 158 */       if (!list.contains(entityplayer)) {
/* 159 */         this.bossBattle.removePlayer(entityplayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxBadOmenLevel() {
/* 166 */     return 5;
/*     */   }
/*     */   
/*     */   public int getBadOmenLevel() {
/* 170 */     return this.badOmenLevel;
/*     */   }
/*     */   
/*     */   public void a(EntityHuman entityhuman) {
/* 174 */     if (entityhuman.hasEffect(MobEffects.BAD_OMEN)) {
/* 175 */       this.badOmenLevel += entityhuman.getEffect(MobEffects.BAD_OMEN).getAmplifier() + 1;
/* 176 */       this.badOmenLevel = MathHelper.clamp(this.badOmenLevel, 0, getMaxBadOmenLevel());
/*     */     } 
/*     */     
/* 179 */     entityhuman.removeEffect(MobEffects.BAD_OMEN);
/*     */   }
/*     */   
/*     */   public void stop() {
/* 183 */     this.active = false;
/* 184 */     this.bossBattle.b();
/* 185 */     this.status = Status.STOPPED;
/*     */   }
/*     */   
/*     */   public void o() {
/* 189 */     if (!isStopped()) {
/* 190 */       if (this.status == Status.ONGOING) {
/* 191 */         boolean flag = this.active;
/*     */         
/* 193 */         this.active = this.world.isLoaded(this.center);
/* 194 */         if (this.world.getDifficulty() == EnumDifficulty.PEACEFUL) {
/* 195 */           CraftEventFactory.callRaidStopEvent(this, RaidStopEvent.Reason.PEACE);
/* 196 */           stop();
/*     */           
/*     */           return;
/*     */         } 
/* 200 */         if (flag != this.active) {
/* 201 */           this.bossBattle.setVisible(this.active);
/*     */         }
/*     */         
/* 204 */         if (!this.active) {
/*     */           return;
/*     */         }
/*     */         
/* 208 */         if (!this.world.a_(this.center)) {
/* 209 */           z();
/*     */         }
/*     */         
/* 212 */         if (!this.world.a_(this.center)) {
/* 213 */           if (this.groupsSpawned > 0) {
/* 214 */             this.status = Status.LOSS;
/* 215 */             CraftEventFactory.callRaidFinishEvent(this, new ArrayList());
/*     */           } else {
/* 217 */             CraftEventFactory.callRaidStopEvent(this, RaidStopEvent.Reason.NOT_IN_VILLAGE);
/* 218 */             stop();
/*     */           } 
/*     */         }
/*     */         
/* 222 */         this.ticksActive++;
/* 223 */         if (this.ticksActive >= 48000L) {
/* 224 */           CraftEventFactory.callRaidStopEvent(this, RaidStopEvent.Reason.TIMEOUT);
/* 225 */           stop();
/*     */           
/*     */           return;
/*     */         } 
/* 229 */         int i = r();
/*     */ 
/*     */         
/* 232 */         if (i == 0 && A()) {
/* 233 */           if (this.preRaidTicks > 0) {
/* 234 */             boolean bool1 = this.y.isPresent();
/* 235 */             boolean flag2 = (!bool1 && this.preRaidTicks % 5 == 0);
/*     */             
/* 237 */             if (bool1 && !this.world.getChunkProvider().a(new ChunkCoordIntPair(this.y.get()))) {
/* 238 */               flag2 = true;
/*     */             }
/*     */             
/* 241 */             if (flag2) {
/* 242 */               byte b0 = 0;
/*     */               
/* 244 */               if (this.preRaidTicks < 100) {
/* 245 */                 b0 = 1;
/* 246 */               } else if (this.preRaidTicks < 40) {
/* 247 */                 b0 = 2;
/*     */               } 
/*     */               
/* 250 */               this.y = d(b0);
/*     */             } 
/*     */             
/* 253 */             if (this.preRaidTicks == 300 || this.preRaidTicks % 20 == 0) {
/* 254 */               y();
/*     */             }
/*     */             
/* 257 */             this.preRaidTicks--;
/* 258 */             this.bossBattle.setProgress(MathHelper.a((300 - this.preRaidTicks) / 300.0F, 0.0F, 1.0F));
/* 259 */           } else if (this.preRaidTicks == 0 && this.groupsSpawned > 0) {
/* 260 */             this.preRaidTicks = 300;
/* 261 */             this.bossBattle.a(a);
/*     */             
/*     */             return;
/*     */           } 
/*     */         }
/* 266 */         if (this.ticksActive % 20L == 0L) {
/* 267 */           y();
/* 268 */           F();
/* 269 */           if (i > 0) {
/* 270 */             if (i <= 2) {
/* 271 */               this.bossBattle.a(a.mutableCopy().c(" - ").addSibling(new ChatMessage("event.minecraft.raid.raiders_remaining", new Object[] { Integer.valueOf(i) })));
/*     */             } else {
/* 273 */               this.bossBattle.a(a);
/*     */             } 
/*     */           } else {
/* 276 */             this.bossBattle.a(a);
/*     */           } 
/*     */         } 
/*     */         
/* 280 */         boolean flag1 = false;
/* 281 */         int j = 0;
/*     */         
/* 283 */         while (G()) {
/* 284 */           BlockPosition blockposition = this.y.isPresent() ? this.y.get() : a(j, 20);
/*     */           
/* 286 */           if (blockposition != null) {
/* 287 */             this.started = true;
/* 288 */             b(blockposition);
/* 289 */             if (!flag1) {
/* 290 */               a(blockposition);
/* 291 */               flag1 = true;
/*     */             } 
/*     */           } else {
/* 294 */             j++;
/*     */           } 
/*     */           
/* 297 */           if (j > 3) {
/* 298 */             CraftEventFactory.callRaidStopEvent(this, RaidStopEvent.Reason.UNSPAWNABLE);
/* 299 */             stop();
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 304 */         if (isStarted() && !A() && i == 0) {
/* 305 */           if (this.postRaidTicks < 40) {
/* 306 */             this.postRaidTicks++;
/*     */           } else {
/* 308 */             this.status = Status.VICTORY;
/* 309 */             Iterator<UUID> iterator = this.heroes.iterator();
/*     */             
/* 311 */             List<Player> winners = new ArrayList<>();
/* 312 */             while (iterator.hasNext()) {
/* 313 */               UUID uuid = iterator.next();
/* 314 */               Entity entity = this.world.getEntity(uuid);
/*     */               
/* 316 */               if (entity instanceof EntityLiving && !entity.isSpectator()) {
/* 317 */                 EntityLiving entityliving = (EntityLiving)entity;
/*     */                 
/* 319 */                 entityliving.addEffect(new MobEffect(MobEffects.HERO_OF_THE_VILLAGE, 48000, this.badOmenLevel - 1, false, false, true));
/* 320 */                 if (entityliving instanceof EntityPlayer) {
/* 321 */                   EntityPlayer entityplayer = (EntityPlayer)entityliving;
/*     */                   
/* 323 */                   entityplayer.a(StatisticList.RAID_WIN);
/* 324 */                   CriterionTriggers.H.a(entityplayer);
/* 325 */                   winners.add(entityplayer.getBukkitEntity());
/*     */                 } 
/*     */               } 
/*     */             } 
/* 329 */             CraftEventFactory.callRaidFinishEvent(this, winners);
/*     */           } 
/*     */         }
/*     */         
/* 333 */         H();
/* 334 */       } else if (a()) {
/* 335 */         this.x++;
/* 336 */         if (this.x >= 600) {
/* 337 */           CraftEventFactory.callRaidStopEvent(this, RaidStopEvent.Reason.FINISHED);
/* 338 */           stop();
/*     */           
/*     */           return;
/*     */         } 
/* 342 */         if (this.x % 20 == 0) {
/* 343 */           y();
/* 344 */           this.bossBattle.setVisible(true);
/* 345 */           if (isVictory()) {
/* 346 */             this.bossBattle.setProgress(0.0F);
/* 347 */             this.bossBattle.a(d);
/*     */           } else {
/* 349 */             this.bossBattle.a(e);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void z() {
/* 358 */     Stream<SectionPosition> stream = SectionPosition.a(SectionPosition.a(this.center), 2);
/* 359 */     WorldServer worldserver = this.world;
/*     */     
/* 361 */     this.world.getClass();
/* 362 */     Objects.requireNonNull(worldserver); stream.filter(worldserver::a).map(SectionPosition::q).min(Comparator.comparingDouble(blockposition -> blockposition.j(this.center)))
/*     */       
/* 364 */       .ifPresent(this::c);
/*     */   }
/*     */   
/*     */   private Optional<BlockPosition> d(int i) {
/* 368 */     for (int j = 0; j < 3; j++) {
/* 369 */       BlockPosition blockposition = a(i, 1);
/*     */       
/* 371 */       if (blockposition != null) {
/* 372 */         return Optional.of(blockposition);
/*     */       }
/*     */     } 
/*     */     
/* 376 */     return Optional.empty();
/*     */   }
/*     */   
/*     */   private boolean A() {
/* 380 */     return C() ? (!D()) : (!B());
/*     */   }
/*     */   
/*     */   private boolean B() {
/* 384 */     return (getGroupsSpawned() == this.numGroups);
/*     */   }
/*     */   
/*     */   private boolean C() {
/* 388 */     return (this.badOmenLevel > 1);
/*     */   }
/*     */   
/*     */   private boolean D() {
/* 392 */     return (getGroupsSpawned() > this.numGroups);
/*     */   }
/*     */   
/*     */   private boolean E() {
/* 396 */     return (B() && r() == 0 && C());
/*     */   }
/*     */   
/*     */   private void F() {
/* 400 */     Iterator<Set<EntityRaider>> iterator = this.raiders.values().iterator();
/* 401 */     HashSet<EntityRaider> hashset = Sets.newHashSet();
/*     */     
/* 403 */     while (iterator.hasNext()) {
/* 404 */       Set<EntityRaider> set = iterator.next();
/* 405 */       Iterator<EntityRaider> iterator1 = set.iterator();
/*     */       
/* 407 */       while (iterator1.hasNext()) {
/* 408 */         EntityRaider entityraider = iterator1.next();
/* 409 */         BlockPosition blockposition = entityraider.getChunkCoordinates();
/*     */         
/* 411 */         if (!entityraider.dead && entityraider.world.getDimensionKey() == this.world.getDimensionKey() && this.center.j(blockposition) < 12544.0D) {
/* 412 */           if (entityraider.ticksLived > 600) {
/* 413 */             if (this.world.getEntity(entityraider.getUniqueID()) == null) {
/* 414 */               hashset.add(entityraider);
/*     */             }
/*     */             
/* 417 */             if (!this.world.a_(blockposition) && entityraider.dc() > 2400) {
/* 418 */               entityraider.b(entityraider.fe() + 1);
/*     */             }
/*     */             
/* 421 */             if (entityraider.fe() >= 30)
/* 422 */               hashset.add(entityraider); 
/*     */           } 
/*     */           continue;
/*     */         } 
/* 426 */         hashset.add(entityraider);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 431 */     Iterator<EntityRaider> iterator2 = hashset.iterator();
/*     */     
/* 433 */     while (iterator2.hasNext()) {
/* 434 */       EntityRaider entityraider1 = iterator2.next();
/*     */       
/* 436 */       a(entityraider1, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(BlockPosition blockposition) {
/* 442 */     float f = 13.0F;
/* 443 */     boolean flag = true;
/* 444 */     Collection<EntityPlayer> collection = this.bossBattle.getPlayers();
/* 445 */     Iterator<EntityPlayer> iterator = this.world.getPlayers().iterator();
/*     */     
/* 447 */     while (iterator.hasNext()) {
/* 448 */       EntityPlayer entityplayer = iterator.next();
/* 449 */       Vec3D vec3d = entityplayer.getPositionVector();
/* 450 */       Vec3D vec3d1 = Vec3D.a(blockposition);
/* 451 */       float f1 = MathHelper.sqrt((vec3d1.x - vec3d.x) * (vec3d1.x - vec3d.x) + (vec3d1.z - vec3d.z) * (vec3d1.z - vec3d.z));
/* 452 */       double d0 = vec3d.x + (13.0F / f1) * (vec3d1.x - vec3d.x);
/* 453 */       double d1 = vec3d.z + (13.0F / f1) * (vec3d1.z - vec3d.z);
/*     */       
/* 455 */       if (f1 <= 64.0F || collection.contains(entityplayer)) {
/* 456 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(SoundEffects.EVENT_RAID_HORN, SoundCategory.NEUTRAL, d0, entityplayer.locY(), d1, 64.0F, 1.0F));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void b(BlockPosition blockposition) {
/* 463 */     boolean flag = false;
/* 464 */     int i = this.groupsSpawned + 1;
/*     */     
/* 466 */     this.totalHealth = 0.0F;
/* 467 */     DifficultyDamageScaler difficultydamagescaler = this.world.getDamageScaler(blockposition);
/* 468 */     boolean flag1 = E();
/* 469 */     Wave[] araid_wave = Wave.f;
/* 470 */     int j = araid_wave.length;
/*     */ 
/*     */     
/* 473 */     EntityRaider leader = null;
/* 474 */     List<EntityRaider> raiders = new ArrayList<>();
/*     */     
/* 476 */     for (int k = 0; k < j; k++) {
/* 477 */       Wave raid_wave = araid_wave[k];
/* 478 */       int l = a(raid_wave, i, flag1) + a(raid_wave, this.random, i, difficultydamagescaler, flag1);
/* 479 */       int i1 = 0;
/*     */       
/* 481 */       for (int j1 = 0; j1 < l; j1++) {
/* 482 */         EntityRaider entityraider = raid_wave.g.a(this.world);
/*     */         
/* 484 */         if (!flag && entityraider.eN()) {
/* 485 */           entityraider.setPatrolLeader(true);
/* 486 */           a(i, entityraider);
/* 487 */           flag = true;
/* 488 */           leader = entityraider;
/*     */         } 
/*     */         
/* 491 */         a(i, entityraider, blockposition, false);
/* 492 */         raiders.add(entityraider);
/* 493 */         if (raid_wave.g == EntityTypes.RAVAGER) {
/* 494 */           EntityRaider entityraider1 = null;
/*     */           
/* 496 */           if (i == a(EnumDifficulty.NORMAL)) {
/* 497 */             entityraider1 = EntityTypes.PILLAGER.a(this.world);
/* 498 */           } else if (i >= a(EnumDifficulty.HARD)) {
/* 499 */             if (i1 == 0) {
/* 500 */               entityraider1 = EntityTypes.EVOKER.a(this.world);
/*     */             } else {
/* 502 */               entityraider1 = EntityTypes.VINDICATOR.a(this.world);
/*     */             } 
/*     */           } 
/*     */           
/* 506 */           i1++;
/* 507 */           if (entityraider1 != null) {
/* 508 */             a(i, entityraider1, blockposition, false);
/* 509 */             entityraider1.setPositionRotation(blockposition, 0.0F, 0.0F);
/* 510 */             entityraider1.startRiding(entityraider);
/* 511 */             raiders.add(entityraider);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 517 */     this.y = Optional.empty();
/* 518 */     this.groupsSpawned++;
/* 519 */     updateProgress();
/* 520 */     H();
/* 521 */     CraftEventFactory.callRaidSpawnWaveEvent(this, leader, raiders);
/*     */   }
/*     */   
/*     */   public void a(int i, EntityRaider entityraider, @Nullable BlockPosition blockposition, boolean flag) {
/* 525 */     boolean flag1 = b(i, entityraider);
/*     */     
/* 527 */     if (flag1) {
/* 528 */       entityraider.a(this);
/* 529 */       entityraider.a(i);
/* 530 */       entityraider.setCanJoinRaid(true);
/* 531 */       entityraider.b(0);
/* 532 */       if (!flag && blockposition != null) {
/* 533 */         entityraider.setPosition(blockposition.getX() + 0.5D, blockposition.getY() + 1.0D, blockposition.getZ() + 0.5D);
/* 534 */         entityraider.prepare(this.world, this.world.getDamageScaler(blockposition), EnumMobSpawn.EVENT, (GroupDataEntity)null, (NBTTagCompound)null);
/* 535 */         entityraider.a(i, false);
/* 536 */         entityraider.setOnGround(true);
/* 537 */         this.world.addAllEntities(entityraider, CreatureSpawnEvent.SpawnReason.RAID);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateProgress() {
/* 544 */     this.bossBattle.setProgress(MathHelper.a(sumMobHealth() / this.totalHealth, 0.0F, 1.0F));
/*     */   }
/*     */   
/*     */   public float sumMobHealth() {
/* 548 */     float f = 0.0F;
/* 549 */     Iterator<Set<EntityRaider>> iterator = this.raiders.values().iterator();
/*     */     
/* 551 */     while (iterator.hasNext()) {
/* 552 */       Set<EntityRaider> set = iterator.next();
/*     */ 
/*     */ 
/*     */       
/* 556 */       for (Iterator<EntityRaider> iterator1 = set.iterator(); iterator1.hasNext(); f += entityraider.getHealth()) {
/* 557 */         EntityRaider entityraider = iterator1.next();
/*     */       }
/*     */     } 
/*     */     
/* 561 */     return f;
/*     */   }
/*     */   
/*     */   private boolean G() {
/* 565 */     return (this.preRaidTicks == 0 && (this.groupsSpawned < this.numGroups || E()) && r() == 0);
/*     */   }
/*     */   
/*     */   public int r() {
/* 569 */     return this.raiders.values().stream().mapToInt(Set::size).sum();
/*     */   }
/*     */   
/*     */   public void a(EntityRaider entityraider, boolean flag) {
/* 573 */     Set<EntityRaider> set = this.raiders.get(Integer.valueOf(entityraider.fc()));
/*     */     
/* 575 */     if (set != null) {
/* 576 */       boolean flag1 = set.remove(entityraider);
/*     */       
/* 578 */       if (flag1) {
/* 579 */         if (flag) {
/* 580 */           this.totalHealth -= entityraider.getHealth();
/*     */         }
/*     */         
/* 583 */         entityraider.a((Raid)null);
/* 584 */         updateProgress();
/* 585 */         H();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void H() {
/* 592 */     this.world.getPersistentRaid().b();
/*     */   }
/*     */   
/*     */   public static ItemStack s() {
/* 596 */     ItemStack itemstack = new ItemStack(Items.WHITE_BANNER);
/* 597 */     NBTTagCompound nbttagcompound = itemstack.a("BlockEntityTag");
/* 598 */     NBTTagList nbttaglist = (new EnumBannerPatternType.a()).a(EnumBannerPatternType.RHOMBUS_MIDDLE, EnumColor.CYAN).a(EnumBannerPatternType.STRIPE_BOTTOM, EnumColor.LIGHT_GRAY).a(EnumBannerPatternType.STRIPE_CENTER, EnumColor.GRAY).a(EnumBannerPatternType.BORDER, EnumColor.LIGHT_GRAY).a(EnumBannerPatternType.STRIPE_MIDDLE, EnumColor.BLACK).a(EnumBannerPatternType.HALF_HORIZONTAL, EnumColor.LIGHT_GRAY).a(EnumBannerPatternType.CIRCLE_MIDDLE, EnumColor.LIGHT_GRAY).a(EnumBannerPatternType.BORDER, EnumColor.BLACK).a();
/*     */     
/* 600 */     nbttagcompound.set("Patterns", nbttaglist);
/* 601 */     itemstack.a(ItemStack.HideFlags.ADDITIONAL);
/* 602 */     itemstack.a((new ChatMessage("block.minecraft.ominous_banner")).a(EnumChatFormat.GOLD));
/* 603 */     return itemstack;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityRaider b(int i) {
/* 608 */     return this.f.get(Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private BlockPosition a(int i, int j) {
/* 613 */     int k = (i == 0) ? 2 : (2 - i);
/* 614 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/* 616 */     for (int l = 0; l < j; l++) {
/* 617 */       float f = this.world.random.nextFloat() * 6.2831855F;
/* 618 */       int i1 = this.center.getX() + MathHelper.d(MathHelper.cos(f) * 32.0F * k) + this.world.random.nextInt(5);
/* 619 */       int j1 = this.center.getZ() + MathHelper.d(MathHelper.sin(f) * 32.0F * k) + this.world.random.nextInt(5);
/* 620 */       int k1 = this.world.a(HeightMap.Type.WORLD_SURFACE, i1, j1);
/*     */       
/* 622 */       blockposition_mutableblockposition.d(i1, k1, j1);
/* 623 */       if ((!this.world.a_(blockposition_mutableblockposition) || i >= 2) && this.world.isAreaLoaded(blockposition_mutableblockposition.getX() - 10, blockposition_mutableblockposition.getY() - 10, blockposition_mutableblockposition.getZ() - 10, blockposition_mutableblockposition.getX() + 10, blockposition_mutableblockposition.getY() + 10, blockposition_mutableblockposition.getZ() + 10) && this.world.getChunkProvider().a(new ChunkCoordIntPair(blockposition_mutableblockposition)) && (SpawnerCreature.a(EntityPositionTypes.Surface.ON_GROUND, this.world, blockposition_mutableblockposition, EntityTypes.RAVAGER) || (this.world.getType(blockposition_mutableblockposition.down()).a(Blocks.SNOW) && this.world.getType(blockposition_mutableblockposition).isAir()))) {
/* 624 */         return blockposition_mutableblockposition;
/*     */       }
/*     */     } 
/*     */     
/* 628 */     return null;
/*     */   }
/*     */   
/*     */   private boolean b(int i, EntityRaider entityraider) {
/* 632 */     return a(i, entityraider, true);
/*     */   }
/*     */   
/*     */   public boolean a(int i, EntityRaider entityraider, boolean flag) {
/* 636 */     this.raiders.computeIfAbsent(Integer.valueOf(i), integer -> Sets.newHashSet());
/*     */ 
/*     */     
/* 639 */     Set<EntityRaider> set = this.raiders.get(Integer.valueOf(i));
/* 640 */     EntityRaider entityraider1 = null;
/* 641 */     Iterator<EntityRaider> iterator = set.iterator();
/*     */     
/* 643 */     while (iterator.hasNext()) {
/* 644 */       EntityRaider entityraider2 = iterator.next();
/*     */       
/* 646 */       if (entityraider2.getUniqueID().equals(entityraider.getUniqueID())) {
/* 647 */         entityraider1 = entityraider2;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 652 */     if (entityraider1 != null) {
/* 653 */       set.remove(entityraider1);
/* 654 */       set.add(entityraider);
/*     */     } 
/*     */     
/* 657 */     set.add(entityraider);
/* 658 */     if (flag) {
/* 659 */       this.totalHealth += entityraider.getHealth();
/*     */     }
/*     */     
/* 662 */     updateProgress();
/* 663 */     H();
/* 664 */     return true;
/*     */   }
/*     */   
/*     */   public void a(int i, EntityRaider entityraider) {
/* 668 */     this.f.put(Integer.valueOf(i), entityraider);
/* 669 */     entityraider.setSlot(EnumItemSlot.HEAD, s());
/* 670 */     entityraider.a(EnumItemSlot.HEAD, 2.0F);
/*     */   }
/*     */   
/*     */   public void c(int i) {
/* 674 */     this.f.remove(Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public BlockPosition getCenter() {
/* 678 */     return this.center;
/*     */   }
/*     */   
/*     */   private void c(BlockPosition blockposition) {
/* 682 */     this.center = blockposition;
/*     */   }
/*     */   
/*     */   public int getId() {
/* 686 */     return this.id;
/*     */   }
/*     */   
/*     */   private int a(Wave raid_wave, int i, boolean flag) {
/* 690 */     return flag ? raid_wave.h[this.numGroups] : raid_wave.h[i];
/*     */   }
/*     */   private int a(Wave raid_wave, Random random, int i, DifficultyDamageScaler difficultydamagescaler, boolean flag) {
/*     */     int j;
/* 694 */     EnumDifficulty enumdifficulty = difficultydamagescaler.a();
/* 695 */     boolean flag1 = (enumdifficulty == EnumDifficulty.EASY);
/* 696 */     boolean flag2 = (enumdifficulty == EnumDifficulty.NORMAL);
/*     */ 
/*     */     
/* 699 */     switch (raid_wave) {
/*     */       case EASY:
/* 701 */         if (flag1 || i <= 2 || i == 4) {
/* 702 */           return 0;
/*     */         }
/*     */         
/* 705 */         j = 1;
/*     */         break;
/*     */       case NORMAL:
/*     */       case HARD:
/* 709 */         if (flag1) {
/* 710 */           j = random.nextInt(2); break;
/* 711 */         }  if (flag2) {
/* 712 */           j = 1; break;
/*     */         } 
/* 714 */         j = 2;
/*     */         break;
/*     */       
/*     */       case null:
/* 718 */         j = (!flag1 && flag) ? 1 : 0;
/*     */         break;
/*     */       default:
/* 721 */         return 0;
/*     */     } 
/*     */     
/* 724 */     return (j > 0) ? random.nextInt(j + 1) : 0;
/*     */   }
/*     */   
/*     */   public boolean v() {
/* 728 */     return this.active;
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound nbttagcompound) {
/* 732 */     nbttagcompound.setInt("Id", this.id);
/* 733 */     nbttagcompound.setBoolean("Started", this.started);
/* 734 */     nbttagcompound.setBoolean("Active", this.active);
/* 735 */     nbttagcompound.setLong("TicksActive", this.ticksActive);
/* 736 */     nbttagcompound.setInt("BadOmenLevel", this.badOmenLevel);
/* 737 */     nbttagcompound.setInt("GroupsSpawned", this.groupsSpawned);
/* 738 */     nbttagcompound.setInt("PreRaidTicks", this.preRaidTicks);
/* 739 */     nbttagcompound.setInt("PostRaidTicks", this.postRaidTicks);
/* 740 */     nbttagcompound.setFloat("TotalHealth", this.totalHealth);
/* 741 */     nbttagcompound.setInt("NumGroups", this.numGroups);
/* 742 */     nbttagcompound.setString("Status", this.status.a());
/* 743 */     nbttagcompound.setInt("CX", this.center.getX());
/* 744 */     nbttagcompound.setInt("CY", this.center.getY());
/* 745 */     nbttagcompound.setInt("CZ", this.center.getZ());
/* 746 */     NBTTagList nbttaglist = new NBTTagList();
/* 747 */     Iterator<UUID> iterator = this.heroes.iterator();
/*     */     
/* 749 */     while (iterator.hasNext()) {
/* 750 */       UUID uuid = iterator.next();
/*     */       
/* 752 */       nbttaglist.add(GameProfileSerializer.a(uuid));
/*     */     } 
/*     */     
/* 755 */     nbttagcompound.set("HeroesOfTheVillage", nbttaglist);
/* 756 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public int a(EnumDifficulty enumdifficulty) {
/* 760 */     switch (enumdifficulty) {
/*     */       case EASY:
/* 762 */         return 3;
/*     */       case NORMAL:
/* 764 */         return 5;
/*     */       case HARD:
/* 766 */         return 7;
/*     */     } 
/* 768 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public float w() {
/* 773 */     int i = getBadOmenLevel();
/*     */     
/* 775 */     return (i == 2) ? 0.1F : ((i == 3) ? 0.25F : ((i == 4) ? 0.5F : ((i == 5) ? 0.75F : 0.0F)));
/*     */   }
/*     */   
/*     */   public void a(Entity entity) {
/* 779 */     this.heroes.add(entity.getUniqueID());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<EntityRaider> getRaiders() {
/* 784 */     return (Collection<EntityRaider>)this.raiders.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
/*     */   }
/*     */ 
/*     */   
/*     */   enum Wave
/*     */   {
/* 790 */     VINDICATOR((String)EntityTypes.VINDICATOR, new int[] { 0, 0, 2, 0, 1, 4, 2, 5 }), EVOKER((String)EntityTypes.EVOKER, new int[] { 0, 0, 0, 0, 0, 1, 1, 2 }), PILLAGER((String)EntityTypes.PILLAGER, new int[] { 0, 4, 3, 3, 4, 4, 4, 2 }), WITCH((String)EntityTypes.WITCH, new int[] { 0, 0, 0, 0, 3, 0, 0, 1 }), RAVAGER((String)EntityTypes.RAVAGER, new int[] { 0, 0, 0, 1, 0, 1, 0, 2 }); private final int[] h;
/*     */     private final EntityTypes<? extends EntityRaider> g;
/* 792 */     private static final Wave[] f = values();
/*     */     static {
/*     */     
/*     */     }
/*     */     Wave(EntityTypes<? extends EntityRaider> entitytypes, int[] aint) {
/* 797 */       this.g = entitytypes;
/* 798 */       this.h = aint;
/*     */     }
/*     */   }
/*     */   
/*     */   enum Status
/*     */   {
/* 804 */     ONGOING, VICTORY, LOSS, STOPPED;
/*     */     
/* 806 */     private static final Status[] e = values();
/*     */     static {
/*     */     
/*     */     }
/*     */     private static Status b(String s) {
/* 811 */       Status[] araid_status = e;
/* 812 */       int i = araid_status.length;
/*     */       
/* 814 */       for (int j = 0; j < i; j++) {
/* 815 */         Status raid_status = araid_status[j];
/*     */         
/* 817 */         if (s.equalsIgnoreCase(raid_status.name())) {
/* 818 */           return raid_status;
/*     */         }
/*     */       } 
/*     */       
/* 822 */       return ONGOING;
/*     */     }
/*     */     
/*     */     public String a() {
/* 826 */       return name().toLowerCase(Locale.ROOT);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Raid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */