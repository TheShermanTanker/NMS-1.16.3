/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ContiguousSet;
/*     */ import com.google.common.collect.DiscreteDomain;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Range;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class EnderDragonBattle {
/*  21 */   private static final Logger LOGGER = LogManager.getLogger(); public final BossBattleServer bossBattle; public final WorldServer world; private final List<Integer> gateways; private final ShapeDetector f; private int g; private int h; private int i; private int j;
/*  22 */   private static final Predicate<Entity> b = IEntitySelector.a.and(IEntitySelector.a(0.0D, 128.0D, 0.0D, 192.0D));
/*     */   
/*     */   private boolean dragonKilled;
/*     */   private boolean previouslyKilled;
/*     */   public UUID dragonUUID;
/*     */   private boolean n;
/*     */   public BlockPosition exitPortalLocation;
/*     */   public EnumDragonRespawn respawnPhase;
/*     */   private int q;
/*     */   private List<EntityEnderCrystal> r;
/*     */   
/*     */   private void setScanForLegacyFight(boolean scanForLegacyFight) {
/*  34 */     this.n = scanForLegacyFight; } private boolean scanForLegacyFight() { return this.n; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnderDragonBattle(WorldServer worldserver, long i, NBTTagCompound nbttagcompound) {
/*  41 */     this.bossBattle = (BossBattleServer)(new BossBattleServer(new ChatMessage("entity.minecraft.ender_dragon"), BossBattle.BarColor.PINK, BossBattle.BarStyle.PROGRESS)).setPlayMusic(true).c(true);
/*  42 */     this.gateways = Lists.newArrayList();
/*  43 */     this.n = true;
/*     */     
/*  45 */     setScanForLegacyFight(worldserver.paperConfig.scanForLegacyEnderDragon);
/*  46 */     if (!scanForLegacyFight()) this.dragonKilled = true;
/*     */     
/*  48 */     this.world = worldserver;
/*  49 */     if (nbttagcompound.hasKeyOfType("DragonKilled", 99)) {
/*  50 */       if (nbttagcompound.b("Dragon")) {
/*  51 */         this.dragonUUID = nbttagcompound.a("Dragon");
/*     */       }
/*     */       
/*  54 */       this.dragonKilled = nbttagcompound.getBoolean("DragonKilled");
/*  55 */       this.previouslyKilled = nbttagcompound.getBoolean("PreviouslyKilled");
/*  56 */       if (nbttagcompound.getBoolean("IsRespawning")) {
/*  57 */         this.respawnPhase = EnumDragonRespawn.START;
/*     */       }
/*     */       
/*  60 */       if (nbttagcompound.hasKeyOfType("ExitPortalLocation", 10)) {
/*  61 */         this.exitPortalLocation = GameProfileSerializer.b(nbttagcompound.getCompound("ExitPortalLocation"));
/*     */       }
/*     */     } else {
/*  64 */       this.dragonKilled = true;
/*  65 */       this.previouslyKilled = true;
/*     */     } 
/*     */     
/*  68 */     if (nbttagcompound.hasKeyOfType("Gateways", 9)) {
/*  69 */       NBTTagList nbttaglist = nbttagcompound.getList("Gateways", 3);
/*     */       
/*  71 */       for (int j = 0; j < nbttaglist.size(); j++) {
/*  72 */         this.gateways.add(Integer.valueOf(nbttaglist.e(j)));
/*     */       }
/*     */     } else {
/*  75 */       this.gateways.addAll((Collection<? extends Integer>)ContiguousSet.create(Range.closedOpen(Integer.valueOf(0), Integer.valueOf(20)), DiscreteDomain.integers()));
/*  76 */       Collections.shuffle(this.gateways, new Random(i));
/*     */     } 
/*     */     
/*  79 */     this.f = ShapeDetectorBuilder.a().a(new String[] { "       ", "       ", "       ", "   #   ", "       ", "       ", "       " }).a(new String[] { "       ", "       ", "       ", "   #   ", "       ", "       ", "       " }).a(new String[] { "       ", "       ", "       ", "   #   ", "       ", "       ", "       " }).a(new String[] { "  ###  ", " #   # ", "#     #", "#  #  #", "#     #", " #   # ", "  ###  " }).a(new String[] { "       ", "  ###  ", " ##### ", " ##### ", " ##### ", "  ###  ", "       " }).a('#', ShapeDetectorBlock.a(BlockPredicate.a(Blocks.BEDROCK))).b();
/*     */   }
/*     */   
/*     */   public NBTTagCompound a() {
/*  83 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/*  85 */     if (this.dragonUUID != null) {
/*  86 */       nbttagcompound.a("Dragon", this.dragonUUID);
/*     */     }
/*     */     
/*  89 */     nbttagcompound.setBoolean("DragonKilled", this.dragonKilled);
/*  90 */     nbttagcompound.setBoolean("PreviouslyKilled", this.previouslyKilled);
/*  91 */     if (this.exitPortalLocation != null) {
/*  92 */       nbttagcompound.set("ExitPortalLocation", GameProfileSerializer.a(this.exitPortalLocation));
/*     */     }
/*     */     
/*  95 */     NBTTagList nbttaglist = new NBTTagList();
/*  96 */     Iterator<Integer> iterator = this.gateways.iterator();
/*     */     
/*  98 */     while (iterator.hasNext()) {
/*  99 */       int i = ((Integer)iterator.next()).intValue();
/*     */       
/* 101 */       nbttaglist.add(NBTTagInt.a(i));
/*     */     } 
/*     */     
/* 104 */     nbttagcompound.set("Gateways", nbttaglist);
/* 105 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public void b() {
/* 109 */     this.bossBattle.setVisible(!this.dragonKilled);
/* 110 */     if (++this.j >= 20) {
/* 111 */       l();
/* 112 */       this.j = 0;
/*     */     } 
/*     */     
/* 115 */     if (!this.bossBattle.getPlayers().isEmpty()) {
/* 116 */       this.world.getChunkProvider().addTicket(TicketType.DRAGON, new ChunkCoordIntPair(0, 0), 9, Unit.INSTANCE);
/* 117 */       boolean flag = k();
/*     */       
/* 119 */       if (this.n && flag) {
/* 120 */         g();
/* 121 */         this.n = false;
/*     */       } 
/*     */       
/* 124 */       if (this.respawnPhase != null) {
/* 125 */         if (this.r == null && flag) {
/* 126 */           this.respawnPhase = null;
/* 127 */           initiateRespawn();
/*     */         } 
/*     */         
/* 130 */         this.respawnPhase.a(this.world, this, this.r, this.q++, this.exitPortalLocation);
/*     */       } 
/*     */       
/* 133 */       if (!this.dragonKilled) {
/* 134 */         if ((this.dragonUUID == null || ++this.g >= 1200) && flag) {
/* 135 */           h();
/* 136 */           this.g = 0;
/*     */         } 
/*     */         
/* 139 */         if (++this.i >= 100 && flag) {
/* 140 */           m();
/* 141 */           this.i = 0;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 145 */       this.world.getChunkProvider().removeTicket(TicketType.DRAGON, new ChunkCoordIntPair(0, 0), 9, Unit.INSTANCE);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void g() {
/* 151 */     LOGGER.info("Scanning for legacy world dragon fight...");
/* 152 */     boolean flag = i();
/*     */     
/* 154 */     if (flag) {
/* 155 */       LOGGER.info("Found that the dragon has been killed in this world already.");
/* 156 */       this.previouslyKilled = true;
/*     */     } else {
/* 158 */       LOGGER.info("Found that the dragon has not yet been killed in this world.");
/* 159 */       this.previouslyKilled = false;
/* 160 */       if (getExitPortalShape() == null) {
/* 161 */         generateExitPortal(false);
/*     */       }
/*     */     } 
/*     */     
/* 165 */     List<EntityEnderDragon> list = this.world.g();
/*     */     
/* 167 */     if (list.isEmpty()) {
/* 168 */       this.dragonKilled = true;
/*     */     } else {
/* 170 */       EntityEnderDragon entityenderdragon = list.get(0);
/*     */       
/* 172 */       this.dragonUUID = entityenderdragon.getUniqueID();
/* 173 */       LOGGER.info("Found that there's a dragon still alive ({})", entityenderdragon);
/* 174 */       this.dragonKilled = false;
/* 175 */       if (!flag) {
/* 176 */         LOGGER.info("But we didn't have a portal, let's remove it.");
/* 177 */         entityenderdragon.die();
/* 178 */         this.dragonUUID = null;
/*     */       } 
/*     */     } 
/*     */     
/* 182 */     if (!this.previouslyKilled && this.dragonKilled) {
/* 183 */       this.dragonKilled = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void h() {
/* 189 */     List<EntityEnderDragon> list = this.world.g();
/*     */     
/* 191 */     if (list.isEmpty()) {
/* 192 */       LOGGER.debug("Haven't seen the dragon, respawning it");
/* 193 */       o();
/*     */     } else {
/* 195 */       LOGGER.debug("Haven't seen our dragon, but found another one to use.");
/* 196 */       this.dragonUUID = ((EntityEnderDragon)list.get(0)).getUniqueID();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRespawnPhase(EnumDragonRespawn enumdragonrespawn) {
/* 202 */     if (this.respawnPhase == null) {
/* 203 */       throw new IllegalStateException("Dragon respawn isn't in progress, can't skip ahead in the animation.");
/*     */     }
/* 205 */     this.q = 0;
/* 206 */     if (enumdragonrespawn == EnumDragonRespawn.END) {
/* 207 */       this.respawnPhase = null;
/* 208 */       this.dragonKilled = false;
/* 209 */       EntityEnderDragon entityenderdragon = o();
/* 210 */       Iterator<EntityPlayer> iterator = this.bossBattle.getPlayers().iterator();
/*     */       
/* 212 */       while (iterator.hasNext()) {
/* 213 */         EntityPlayer entityplayer = iterator.next();
/*     */         
/* 215 */         CriterionTriggers.n.a(entityplayer, entityenderdragon);
/*     */       } 
/*     */     } else {
/* 218 */       this.respawnPhase = enumdragonrespawn;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean i() {
/* 225 */     for (int i = -8; i <= 8; i++) {
/* 226 */       int j = -8;
/*     */ 
/*     */       
/* 229 */       label17: while (j <= 8) {
/* 230 */         TileEntity tileentity; Chunk chunk = this.world.getChunkAt(i, j);
/* 231 */         Iterator<TileEntity> iterator = chunk.getTileEntities().values().iterator();
/*     */ 
/*     */ 
/*     */         
/*     */         do {
/* 236 */           if (!iterator.hasNext()) {
/* 237 */             j++;
/*     */             
/*     */             continue label17;
/*     */           } 
/* 241 */           tileentity = iterator.next();
/* 242 */         } while (!(tileentity instanceof TileEntityEnderPortal));
/*     */         
/* 244 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 248 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ShapeDetector.ShapeDetectorCollection getExitPortalShape() {
/*     */     int i;
/* 256 */     for (i = -8; i <= 8; i++) {
/* 257 */       for (int k = -8; k <= 8; k++) {
/* 258 */         Chunk chunk = this.world.getChunkAt(i, k);
/* 259 */         Iterator<TileEntity> iterator = chunk.getTileEntities().values().iterator();
/*     */         
/* 261 */         while (iterator.hasNext()) {
/* 262 */           TileEntity tileentity = iterator.next();
/*     */           
/* 264 */           if (tileentity instanceof TileEntityEnderPortal) {
/* 265 */             ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = this.f.a(this.world, tileentity.getPosition());
/*     */             
/* 267 */             if (shapedetector_shapedetectorcollection != null) {
/* 268 */               BlockPosition blockposition = shapedetector_shapedetectorcollection.a(3, 3, 3).getPosition();
/*     */               
/* 270 */               if (this.exitPortalLocation == null && blockposition.getX() == 0 && blockposition.getZ() == 0) {
/* 271 */                 this.exitPortalLocation = blockposition;
/*     */               }
/*     */               
/* 274 */               return shapedetector_shapedetectorcollection;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 281 */     i = this.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, WorldGenEndTrophy.a).getY();
/*     */     
/* 283 */     for (int j = i; j >= 0; j--) {
/* 284 */       ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection1 = this.f.a(this.world, new BlockPosition(WorldGenEndTrophy.a.getX(), j, WorldGenEndTrophy.a.getZ()));
/*     */       
/* 286 */       if (shapedetector_shapedetectorcollection1 != null) {
/* 287 */         if (this.exitPortalLocation == null) {
/* 288 */           this.exitPortalLocation = shapedetector_shapedetectorcollection1.a(3, 3, 3).getPosition();
/*     */         }
/*     */         
/* 291 */         return shapedetector_shapedetectorcollection1;
/*     */       } 
/*     */     } 
/*     */     
/* 295 */     return null;
/*     */   }
/*     */   
/*     */   private boolean k() {
/* 299 */     for (int i = -8; i <= 8; i++) {
/* 300 */       for (int j = 8; j <= 8; j++) {
/* 301 */         IChunkAccess ichunkaccess = this.world.getChunkAt(i, j, ChunkStatus.FULL, false);
/*     */         
/* 303 */         if (!(ichunkaccess instanceof Chunk)) {
/* 304 */           return false;
/*     */         }
/*     */         
/* 307 */         PlayerChunk.State playerchunk_state = ((Chunk)ichunkaccess).getState();
/*     */         
/* 309 */         if (!playerchunk_state.isAtLeast(PlayerChunk.State.TICKING)) {
/* 310 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 315 */     return true;
/*     */   }
/*     */   
/*     */   private void l() {
/* 319 */     Set<EntityPlayer> set = Sets.newHashSet();
/* 320 */     Iterator<EntityPlayer> iterator = this.world.a((Predicate)b).iterator();
/*     */     
/* 322 */     while (iterator.hasNext()) {
/* 323 */       EntityPlayer entityplayer = iterator.next();
/*     */       
/* 325 */       this.bossBattle.addPlayer(entityplayer);
/* 326 */       set.add(entityplayer);
/*     */     } 
/*     */     
/* 329 */     Set<EntityPlayer> set1 = Sets.newHashSet(this.bossBattle.getPlayers());
/*     */     
/* 331 */     set1.removeAll(set);
/* 332 */     Iterator<EntityPlayer> iterator1 = set1.iterator();
/*     */     
/* 334 */     while (iterator1.hasNext()) {
/* 335 */       EntityPlayer entityplayer1 = iterator1.next();
/*     */       
/* 337 */       this.bossBattle.removePlayer(entityplayer1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void m() {
/* 343 */     this.i = 0;
/* 344 */     this.h = 0;
/*     */ 
/*     */ 
/*     */     
/* 348 */     for (Iterator<WorldGenEnder.Spike> iterator = WorldGenEnder.a(this.world).iterator(); iterator.hasNext(); this.h += this.world.<EntityEnderCrystal>a(EntityEnderCrystal.class, worldgenender_spike.f()).size()) {
/* 349 */       WorldGenEnder.Spike worldgenender_spike = iterator.next();
/*     */     }
/*     */     
/* 352 */     LOGGER.debug("Found {} end crystals still alive", Integer.valueOf(this.h));
/*     */   }
/*     */   
/*     */   public void a(EntityEnderDragon entityenderdragon) {
/* 356 */     if (entityenderdragon.getUniqueID().equals(this.dragonUUID)) {
/* 357 */       this.bossBattle.setProgress(0.0F);
/* 358 */       this.bossBattle.setVisible(false);
/* 359 */       generateExitPortal(true);
/* 360 */       n();
/* 361 */       if (!this.previouslyKilled) {
/* 362 */         this.world.setTypeUpdate(this.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, WorldGenEndTrophy.a), Blocks.DRAGON_EGG.getBlockData());
/*     */       }
/*     */       
/* 365 */       this.previouslyKilled = true;
/* 366 */       this.dragonKilled = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void n() {
/* 372 */     if (!this.gateways.isEmpty()) {
/* 373 */       int i = ((Integer)this.gateways.remove(this.gateways.size() - 1)).intValue();
/* 374 */       int j = MathHelper.floor(96.0D * Math.cos(2.0D * (-3.141592653589793D + 0.15707963267948966D * i)));
/* 375 */       int k = MathHelper.floor(96.0D * Math.sin(2.0D * (-3.141592653589793D + 0.15707963267948966D * i)));
/*     */       
/* 377 */       a(new BlockPosition(j, 75, k));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(BlockPosition blockposition) {
/* 382 */     this.world.triggerEffect(3000, blockposition, 0);
/* 383 */     BiomeDecoratorGroups.END_GATEWAY_DELAYED.a(this.world, this.world.getChunkProvider().getChunkGenerator(), new Random(), blockposition);
/*     */   }
/*     */   
/*     */   public void generateExitPortal(boolean flag) {
/* 387 */     WorldGenEndTrophy worldgenendtrophy = new WorldGenEndTrophy(flag);
/*     */     
/* 389 */     if (this.exitPortalLocation == null) {
/* 390 */       for (this.exitPortalLocation = this.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, WorldGenEndTrophy.a).down(); this.world.getType(this.exitPortalLocation).a(Blocks.BEDROCK) && this.exitPortalLocation.getY() > this.world.getSeaLevel(); this.exitPortalLocation = this.exitPortalLocation.down());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 395 */     worldgenendtrophy.b(WorldGenFeatureConfiguration.k).a(this.world, this.world.getChunkProvider().getChunkGenerator(), new Random(), this.exitPortalLocation);
/*     */   }
/*     */   
/*     */   private EntityEnderDragon o() {
/* 399 */     this.world.getChunkAtWorldCoords(new BlockPosition(0, 128, 0));
/* 400 */     EntityEnderDragon entityenderdragon = EntityTypes.ENDER_DRAGON.a(this.world);
/*     */     
/* 402 */     entityenderdragon.getDragonControllerManager().setControllerPhase(DragonControllerPhase.HOLDING_PATTERN);
/* 403 */     entityenderdragon.setPositionRotation(0.0D, 128.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
/* 404 */     this.world.addEntity(entityenderdragon);
/* 405 */     this.dragonUUID = entityenderdragon.getUniqueID();
/* 406 */     return entityenderdragon;
/*     */   }
/*     */   
/*     */   public void b(EntityEnderDragon entityenderdragon) {
/* 410 */     if (entityenderdragon.getUniqueID().equals(this.dragonUUID)) {
/* 411 */       this.bossBattle.setProgress(entityenderdragon.getHealth() / entityenderdragon.getMaxHealth());
/* 412 */       this.g = 0;
/* 413 */       if (entityenderdragon.hasCustomName()) {
/* 414 */         this.bossBattle.a(entityenderdragon.getScoreboardDisplayName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int c() {
/* 421 */     return this.h;
/*     */   }
/*     */   
/*     */   public void a(EntityEnderCrystal entityendercrystal, DamageSource damagesource) {
/* 425 */     if (this.respawnPhase != null && this.r.contains(entityendercrystal)) {
/* 426 */       LOGGER.debug("Aborting respawn sequence");
/* 427 */       this.respawnPhase = null;
/* 428 */       this.q = 0;
/* 429 */       resetCrystals();
/* 430 */       generateExitPortal(true);
/*     */     } else {
/* 432 */       m();
/* 433 */       Entity entity = this.world.getEntity(this.dragonUUID);
/*     */       
/* 435 */       if (entity instanceof EntityEnderDragon) {
/* 436 */         ((EntityEnderDragon)entity).a(entityendercrystal, entityendercrystal.getChunkCoordinates(), damagesource);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPreviouslyKilled() {
/* 443 */     return this.previouslyKilled;
/*     */   }
/*     */   
/*     */   public void initiateRespawn() {
/* 447 */     if (this.dragonKilled && this.respawnPhase == null) {
/* 448 */       BlockPosition blockposition = this.exitPortalLocation;
/*     */       
/* 450 */       if (blockposition == null) {
/* 451 */         LOGGER.debug("Tried to respawn, but need to find the portal first.");
/* 452 */         ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = getExitPortalShape();
/*     */         
/* 454 */         if (shapedetector_shapedetectorcollection == null) {
/* 455 */           LOGGER.debug("Couldn't find a portal, so we made one.");
/* 456 */           generateExitPortal(true);
/*     */         } else {
/* 458 */           LOGGER.debug("Found the exit portal & temporarily using it.");
/*     */         } 
/*     */         
/* 461 */         blockposition = this.exitPortalLocation;
/*     */       } 
/*     */       
/* 464 */       List<EntityEnderCrystal> list = Lists.newArrayList();
/* 465 */       BlockPosition blockposition1 = blockposition.up(1);
/* 466 */       Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */       
/* 468 */       while (iterator.hasNext()) {
/* 469 */         EnumDirection enumdirection = iterator.next();
/* 470 */         List<EntityEnderCrystal> list1 = this.world.a(EntityEnderCrystal.class, new AxisAlignedBB(blockposition1.shift(enumdirection, 2)));
/*     */         
/* 472 */         if (list1.isEmpty()) {
/*     */           return;
/*     */         }
/*     */         
/* 476 */         list.addAll(list1);
/*     */       } 
/*     */       
/* 479 */       LOGGER.debug("Found all crystals, respawning dragon.");
/* 480 */       a(list);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(List<EntityEnderCrystal> list) {
/* 486 */     if (this.dragonKilled && this.respawnPhase == null) {
/* 487 */       for (ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = getExitPortalShape(); shapedetector_shapedetectorcollection != null; shapedetector_shapedetectorcollection = getExitPortalShape()) {
/* 488 */         for (int i = 0; i < this.f.c(); i++) {
/* 489 */           for (int j = 0; j < this.f.b(); j++) {
/* 490 */             for (int k = 0; k < this.f.a(); k++) {
/* 491 */               ShapeDetectorBlock shapedetectorblock = shapedetector_shapedetectorcollection.a(i, j, k);
/*     */               
/* 493 */               if (shapedetectorblock.a().a(Blocks.BEDROCK) || shapedetectorblock.a().a(Blocks.END_PORTAL)) {
/* 494 */                 this.world.setTypeUpdate(shapedetectorblock.getPosition(), Blocks.END_STONE.getBlockData());
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 501 */       this.respawnPhase = EnumDragonRespawn.START;
/* 502 */       this.q = 0;
/* 503 */       generateExitPortal(false);
/* 504 */       this.r = list;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetCrystals() {
/* 510 */     Iterator<WorldGenEnder.Spike> iterator = WorldGenEnder.a(this.world).iterator();
/*     */     
/* 512 */     while (iterator.hasNext()) {
/* 513 */       WorldGenEnder.Spike worldgenender_spike = iterator.next();
/* 514 */       List<EntityEnderCrystal> list = this.world.a(EntityEnderCrystal.class, worldgenender_spike.f());
/* 515 */       Iterator<EntityEnderCrystal> iterator1 = list.iterator();
/*     */       
/* 517 */       while (iterator1.hasNext()) {
/* 518 */         EntityEnderCrystal entityendercrystal = iterator1.next();
/*     */         
/* 520 */         entityendercrystal.setInvulnerable(false);
/* 521 */         entityendercrystal.setBeamTarget((BlockPosition)null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnderDragonBattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */