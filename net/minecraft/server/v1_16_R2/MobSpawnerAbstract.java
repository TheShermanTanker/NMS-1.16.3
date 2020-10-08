/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.entity.PreSpawnerSpawnEvent;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public abstract class MobSpawnerAbstract {
/*  13 */   private static final Logger LOGGER = LogManager.getLogger();
/*  14 */   public int spawnDelay = 20;
/*  15 */   public final List<MobSpawnerData> mobs = Lists.newArrayList();
/*  16 */   public MobSpawnerData spawnData = new MobSpawnerData();
/*     */   private double e;
/*     */   private double f;
/*  19 */   public int minSpawnDelay = 200;
/*  20 */   public int maxSpawnDelay = 800;
/*  21 */   public int spawnCount = 4;
/*     */   @Nullable
/*     */   private Entity j;
/*  24 */   public int maxNearbyEntities = 6;
/*  25 */   public int requiredPlayerRange = 16;
/*  26 */   public int spawnRange = 4;
/*  27 */   private int tickDelay = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MinecraftKey getMobName() {
/*  33 */     String s = this.spawnData.getEntity().getString("id");
/*     */     
/*     */     try {
/*  36 */       return UtilColor.b(s) ? null : new MinecraftKey(s);
/*  37 */     } catch (ResourceKeyInvalidException resourcekeyinvalidexception) {
/*  38 */       BlockPosition blockposition = b();
/*     */       
/*  40 */       LOGGER.warn("Invalid entity id '{}' at spawner {}:[{},{},{}]", s, a().getDimensionKey().a(), Integer.valueOf(blockposition.getX()), Integer.valueOf(blockposition.getY()), Integer.valueOf(blockposition.getZ()));
/*  41 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setMobName(EntityTypes<?> entitytypes) {
/*  46 */     this.spawnData.getEntity().setString("id", IRegistry.ENTITY_TYPE.getKey(entitytypes).toString());
/*  47 */     this.mobs.clear();
/*     */   }
/*     */   public boolean isActivated() {
/*  50 */     return h();
/*     */   } private boolean h() {
/*  52 */     BlockPosition blockposition = b();
/*     */     
/*  54 */     return a().isAffectsSpawningPlayerNearby(blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D, this.requiredPlayerRange);
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  59 */     if (this.spawnDelay > 0 && --this.tickDelay > 0)
/*  60 */       return;  this.tickDelay = (a()).paperConfig.mobSpawnerTickRate;
/*     */     
/*  62 */     if (!h()) {
/*  63 */       this.f = this.e;
/*     */     } else {
/*  65 */       World world = a();
/*  66 */       BlockPosition blockposition = b();
/*     */       
/*  68 */       if (!(world instanceof WorldServer)) {
/*  69 */         double d0 = blockposition.getX() + world.random.nextDouble();
/*  70 */         double d1 = blockposition.getY() + world.random.nextDouble();
/*  71 */         double d2 = blockposition.getZ() + world.random.nextDouble();
/*     */         
/*  73 */         world.addParticle(Particles.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*  74 */         world.addParticle(Particles.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
/*  75 */         if (this.spawnDelay > 0) {
/*  76 */           this.spawnDelay -= this.tickDelay;
/*     */         }
/*     */         
/*  79 */         this.f = this.e;
/*  80 */         this.e = (this.e + (1000.0F / (this.spawnDelay + 200.0F))) % 360.0D;
/*     */       } else {
/*  82 */         if (this.spawnDelay < -this.tickDelay) {
/*  83 */           i();
/*     */         }
/*     */         
/*  86 */         if (this.spawnDelay > 0) {
/*  87 */           this.spawnDelay -= this.tickDelay;
/*     */           
/*     */           return;
/*     */         } 
/*  91 */         boolean flag = false;
/*     */         
/*  93 */         for (int i = 0; i < this.spawnCount; i++) {
/*  94 */           NBTTagCompound nbttagcompound = this.spawnData.getEntity();
/*  95 */           Optional<EntityTypes<?>> optional = EntityTypes.a(nbttagcompound);
/*     */           
/*  97 */           if (!optional.isPresent()) {
/*  98 */             i();
/*     */             
/*     */             return;
/*     */           } 
/* 102 */           NBTTagList nbttaglist = nbttagcompound.getList("Pos", 6);
/* 103 */           int j = nbttaglist.size();
/* 104 */           double d3 = (j >= 1) ? nbttaglist.h(0) : (blockposition.getX() + (world.random.nextDouble() - world.random.nextDouble()) * this.spawnRange + 0.5D);
/* 105 */           double d4 = (j >= 2) ? nbttaglist.h(1) : (blockposition.getY() + world.random.nextInt(3) - 1);
/* 106 */           double d5 = (j >= 3) ? nbttaglist.h(2) : (blockposition.getZ() + (world.random.nextDouble() - world.random.nextDouble()) * this.spawnRange + 0.5D);
/*     */ 
/*     */           
/* 109 */           WorldServer worldserver = (WorldServer)world;
/*     */           
/* 111 */           if (world.b(((EntityTypes)optional.get()).a(d3, d4, d5)) && EntityPositionTypes.a((EntityTypes<Entity>)optional.get(), worldserver, EnumMobSpawn.SPAWNER, new BlockPosition(d3, d4, d5), world.getRandom())) {
/*     */             
/* 113 */             EntityTypes<?> entityType = optional.get();
/* 114 */             String key = EntityTypes.getName(entityType).getKey();
/*     */             
/* 116 */             EntityType type = EntityType.fromName(key);
/* 117 */             if (type != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 122 */               PreSpawnerSpawnEvent event = new PreSpawnerSpawnEvent(MCUtil.toLocation(world, d3, d4, d5), type, MCUtil.toLocation(world, blockposition));
/*     */               
/* 124 */               if (!event.callEvent()) {
/* 125 */                 flag = true;
/* 126 */                 if (event.shouldAbortSpawn()) {
/*     */                   break;
/*     */                 }
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */             } 
/* 133 */             Entity entity = EntityTypes.a(nbttagcompound, world, entity1 -> {
/*     */                   entity1.setPositionRotation(d3, d4, d5, entity1.yaw, entity1.pitch);
/*     */                   
/*     */                   return entity1;
/*     */                 });
/* 138 */             if (entity == null) {
/* 139 */               i();
/*     */               
/*     */               return;
/*     */             } 
/* 143 */             int k = world.a(entity.getClass(), (new AxisAlignedBB(blockposition.getX(), blockposition.getY(), blockposition.getZ(), (blockposition.getX() + 1), (blockposition.getY() + 1), (blockposition.getZ() + 1))).g(this.spawnRange)).size();
/*     */             
/* 145 */             if (k >= this.maxNearbyEntities) {
/* 146 */               i();
/*     */               
/*     */               return;
/*     */             } 
/* 150 */             entity.setPositionRotation(entity.locX(), entity.locY(), entity.locZ(), world.random.nextFloat() * 360.0F, 0.0F);
/* 151 */             if (entity instanceof EntityInsentient) {
/* 152 */               EntityInsentient entityinsentient = (EntityInsentient)entity;
/*     */               
/* 154 */               if (!entityinsentient.a(world, EnumMobSpawn.SPAWNER) || !entityinsentient.a(world)) {
/*     */                 continue;
/*     */               }
/*     */               
/* 158 */               if (this.spawnData.getEntity().e() == 1 && this.spawnData.getEntity().hasKeyOfType("id", 8)) {
/* 159 */                 ((EntityInsentient)entity).prepare(worldserver, world.getDamageScaler(entity.getChunkCoordinates()), EnumMobSpawn.SPAWNER, (GroupDataEntity)null, (NBTTagCompound)null);
/*     */               }
/*     */               
/* 162 */               if (entityinsentient.world.spigotConfig.nerfSpawnerMobs)
/*     */               {
/* 164 */                 entityinsentient.aware = false;
/*     */               }
/*     */             } 
/*     */             
/* 168 */             entity.spawnedViaMobSpawner = true;
/* 169 */             entity.spawnReason = CreatureSpawnEvent.SpawnReason.SPAWNER;
/* 170 */             flag = true;
/*     */             
/* 172 */             if (CraftEventFactory.callSpawnerSpawnEvent(entity, blockposition).isCancelled()) {
/* 173 */               Entity vehicle = entity.getVehicle();
/* 174 */               if (vehicle != null) {
/* 175 */                 vehicle.dead = true;
/*     */               }
/* 177 */               for (Entity passenger : entity.getAllPassengers()) {
/* 178 */                 passenger.dead = true;
/*     */               
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 184 */               if (!worldserver.addAllEntitiesSafely(entity, CreatureSpawnEvent.SpawnReason.SPAWNER)) {
/* 185 */                 i();
/*     */                 
/*     */                 return;
/*     */               } 
/* 189 */               world.triggerEffect(2004, blockposition, 0);
/* 190 */               if (entity instanceof EntityInsentient) {
/* 191 */                 ((EntityInsentient)entity).doSpawnEffect();
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 199 */         if (flag) {
/* 200 */           i();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void resetTimer() {
/* 207 */     i();
/*     */   } private void i() {
/* 209 */     if (this.maxSpawnDelay <= this.minSpawnDelay) {
/* 210 */       this.spawnDelay = this.minSpawnDelay;
/*     */     } else {
/* 212 */       int i = this.maxSpawnDelay - this.minSpawnDelay;
/*     */       
/* 214 */       this.spawnDelay = this.minSpawnDelay + (a()).random.nextInt(i);
/*     */     } 
/*     */     
/* 217 */     if (!this.mobs.isEmpty()) {
/* 218 */       setSpawnData(WeightedRandom.<MobSpawnerData>a((a()).random, this.mobs));
/*     */     }
/*     */     
/* 221 */     a(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 226 */     if (nbttagcompound.hasKey("Paper.Delay")) {
/* 227 */       this.spawnDelay = nbttagcompound.getInt("Paper.Delay");
/*     */     } else {
/* 229 */       this.spawnDelay = nbttagcompound.getShort("Delay");
/*     */     } 
/*     */     
/* 232 */     this.mobs.clear();
/* 233 */     if (nbttagcompound.hasKeyOfType("SpawnPotentials", 9)) {
/* 234 */       NBTTagList nbttaglist = nbttagcompound.getList("SpawnPotentials", 10);
/*     */       
/* 236 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 237 */         this.mobs.add(new MobSpawnerData(nbttaglist.getCompound(i)));
/*     */       }
/*     */     } 
/*     */     
/* 241 */     if (nbttagcompound.hasKeyOfType("SpawnData", 10)) {
/* 242 */       setSpawnData(new MobSpawnerData(1, nbttagcompound.getCompound("SpawnData")));
/* 243 */     } else if (!this.mobs.isEmpty()) {
/* 244 */       setSpawnData(WeightedRandom.<MobSpawnerData>a((a()).random, this.mobs));
/*     */     } 
/*     */     
/* 247 */     if (nbttagcompound.hasKeyOfType("Paper.MinSpawnDelay", 99)) {
/* 248 */       this.minSpawnDelay = nbttagcompound.getInt("Paper.MinSpawnDelay");
/* 249 */       this.maxSpawnDelay = nbttagcompound.getInt("Paper.MaxSpawnDelay");
/* 250 */       this.spawnCount = nbttagcompound.getShort("SpawnCount");
/*     */     }
/* 252 */     else if (nbttagcompound.hasKeyOfType("MinSpawnDelay", 99)) {
/* 253 */       this.minSpawnDelay = nbttagcompound.getInt("MinSpawnDelay");
/* 254 */       this.maxSpawnDelay = nbttagcompound.getInt("MaxSpawnDelay");
/* 255 */       this.spawnCount = nbttagcompound.getShort("SpawnCount");
/*     */     } 
/*     */     
/* 258 */     if (nbttagcompound.hasKeyOfType("MaxNearbyEntities", 99)) {
/* 259 */       this.maxNearbyEntities = nbttagcompound.getShort("MaxNearbyEntities");
/* 260 */       this.requiredPlayerRange = nbttagcompound.getShort("RequiredPlayerRange");
/*     */     } 
/*     */     
/* 263 */     if (nbttagcompound.hasKeyOfType("SpawnRange", 99)) {
/* 264 */       this.spawnRange = nbttagcompound.getShort("SpawnRange");
/*     */     }
/*     */     
/* 267 */     if (a() != null) {
/* 268 */       this.j = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b(NBTTagCompound nbttagcompound) {
/* 274 */     MinecraftKey minecraftkey = getMobName();
/*     */     
/* 276 */     if (minecraftkey == null) {
/* 277 */       return nbttagcompound;
/*     */     }
/*     */     
/* 280 */     if (this.spawnDelay > 32767) {
/* 281 */       nbttagcompound.setInt("Paper.Delay", this.spawnDelay);
/*     */     }
/* 283 */     nbttagcompound.setShort("Delay", (short)Math.min(32767, this.spawnDelay));
/*     */     
/* 285 */     if (this.minSpawnDelay > 32767 || this.maxSpawnDelay > 32767) {
/* 286 */       nbttagcompound.setInt("Paper.MinSpawnDelay", this.minSpawnDelay);
/* 287 */       nbttagcompound.setInt("Paper.MaxSpawnDelay", this.maxSpawnDelay);
/*     */     } 
/*     */     
/* 290 */     nbttagcompound.setShort("MinSpawnDelay", (short)Math.min(32767, this.minSpawnDelay));
/* 291 */     nbttagcompound.setShort("MaxSpawnDelay", (short)Math.min(32767, this.maxSpawnDelay));
/*     */     
/* 293 */     nbttagcompound.setShort("SpawnCount", (short)this.spawnCount);
/* 294 */     nbttagcompound.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
/* 295 */     nbttagcompound.setShort("RequiredPlayerRange", (short)this.requiredPlayerRange);
/* 296 */     nbttagcompound.setShort("SpawnRange", (short)this.spawnRange);
/* 297 */     nbttagcompound.set("SpawnData", this.spawnData.getEntity().clone());
/* 298 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 300 */     if (this.mobs.isEmpty()) {
/* 301 */       nbttaglist.add(this.spawnData.a());
/*     */     } else {
/* 303 */       Iterator<MobSpawnerData> iterator = this.mobs.iterator();
/*     */       
/* 305 */       while (iterator.hasNext()) {
/* 306 */         MobSpawnerData mobspawnerdata = iterator.next();
/*     */         
/* 308 */         nbttaglist.add(mobspawnerdata.a());
/*     */       } 
/*     */     } 
/*     */     
/* 312 */     nbttagcompound.set("SpawnPotentials", nbttaglist);
/* 313 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(int i) {
/* 318 */     if (i == 1 && (a()).isClientSide) {
/* 319 */       this.spawnDelay = this.minSpawnDelay;
/* 320 */       return true;
/*     */     } 
/* 322 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSpawnData(MobSpawnerData mobspawnerdata) {
/* 327 */     this.spawnData = mobspawnerdata;
/*     */   }
/*     */   
/*     */   public abstract void a(int paramInt);
/*     */   
/*     */   public abstract World a();
/*     */   
/*     */   public abstract BlockPosition b();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobSpawnerAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */