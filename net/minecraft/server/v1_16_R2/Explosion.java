/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockExplodeEvent;
/*     */ import org.bukkit.event.entity.EntityExplodeEvent;
/*     */ 
/*     */ public class Explosion {
/*  27 */   private static final ExplosionDamageCalculator a = new ExplosionDamageCalculator();
/*     */   private final boolean b;
/*     */   private final Effect c;
/*  30 */   private final Random d = new Random();
/*     */   private final World world;
/*     */   private final double posX;
/*     */   private final double posY;
/*     */   private final double posZ;
/*     */   @Nullable
/*     */   public final Entity source;
/*     */   private final float size;
/*     */   private final DamageSource k;
/*     */   private final ExplosionDamageCalculator l;
/*  40 */   private final List<BlockPosition> blocks = Lists.newArrayList();
/*  41 */   private final Map<EntityHuman, Vec3D> n = Maps.newHashMap();
/*     */   public boolean wasCanceled = false;
/*     */   
/*     */   public Explosion(World world, @Nullable Entity entity, @Nullable DamageSource damagesource, @Nullable ExplosionDamageCalculator explosiondamagecalculator, double d0, double d1, double d2, float f, boolean flag, Effect explosion_effect) {
/*  45 */     this.world = world;
/*  46 */     this.source = entity;
/*  47 */     this.size = (float)Math.max(f, 0.0D);
/*  48 */     this.posX = d0;
/*  49 */     this.posY = d1;
/*  50 */     this.posZ = d2;
/*  51 */     this.b = flag;
/*  52 */     this.c = explosion_effect;
/*  53 */     this.k = (damagesource == null) ? DamageSource.explosion(this) : damagesource;
/*  54 */     this.l = (explosiondamagecalculator == null) ? a(entity) : explosiondamagecalculator;
/*     */   }
/*     */   
/*     */   private ExplosionDamageCalculator a(@Nullable Entity entity) {
/*  58 */     return (entity == null) ? a : new ExplosionDamageCalculatorEntity(entity);
/*     */   }
/*     */   
/*     */   public static float a(Vec3D vec3d, Entity entity) {
/*  62 */     AxisAlignedBB axisalignedbb = entity.getBoundingBox();
/*  63 */     double d0 = 1.0D / ((axisalignedbb.maxX - axisalignedbb.minX) * 2.0D + 1.0D);
/*  64 */     double d1 = 1.0D / ((axisalignedbb.maxY - axisalignedbb.minY) * 2.0D + 1.0D);
/*  65 */     double d2 = 1.0D / ((axisalignedbb.maxZ - axisalignedbb.minZ) * 2.0D + 1.0D);
/*  66 */     double d3 = (1.0D - Math.floor(1.0D / d0) * d0) / 2.0D;
/*  67 */     double d4 = (1.0D - Math.floor(1.0D / d2) * d2) / 2.0D;
/*     */     
/*  69 */     if (d0 >= 0.0D && d1 >= 0.0D && d2 >= 0.0D) {
/*  70 */       int i = 0;
/*  71 */       int j = 0;
/*     */       float f;
/*  73 */       for (f = 0.0F; f <= 1.0F; f = (float)(f + d0)) {
/*  74 */         float f1; for (f1 = 0.0F; f1 <= 1.0F; f1 = (float)(f1 + d1)) {
/*  75 */           float f2; for (f2 = 0.0F; f2 <= 1.0F; f2 = (float)(f2 + d2)) {
/*  76 */             double d5 = MathHelper.d(f, axisalignedbb.minX, axisalignedbb.maxX);
/*  77 */             double d6 = MathHelper.d(f1, axisalignedbb.minY, axisalignedbb.maxY);
/*  78 */             double d7 = MathHelper.d(f2, axisalignedbb.minZ, axisalignedbb.maxZ);
/*  79 */             Vec3D vec3d1 = new Vec3D(d5 + d3, d6, d7 + d4);
/*     */             
/*  81 */             if (entity.world.rayTrace(new RayTrace(vec3d1, vec3d, RayTrace.BlockCollisionOption.COLLIDER, RayTrace.FluidCollisionOption.NONE, entity)).getType() == MovingObjectPosition.EnumMovingObjectType.MISS) {
/*  82 */               i++;
/*     */             }
/*     */             
/*  85 */             j++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  90 */       return i / j;
/*     */     } 
/*  92 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() {
/*  98 */     if (this.size < 0.1F) {
/*     */       return;
/*     */     }
/*     */     
/* 102 */     Set<BlockPosition> set = Sets.newHashSet();
/* 103 */     boolean flag = true;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     for (int k = 0; k < 16; k++) {
/* 109 */       for (int m = 0; m < 16; m++) {
/* 110 */         for (int n = 0; n < 16; n++) {
/* 111 */           if (k == 0 || k == 15 || m == 0 || m == 15 || n == 0 || n == 15) {
/* 112 */             double d0 = (k / 15.0F * 2.0F - 1.0F);
/* 113 */             double d1 = (m / 15.0F * 2.0F - 1.0F);
/* 114 */             double d2 = (n / 15.0F * 2.0F - 1.0F);
/* 115 */             double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */             
/* 117 */             d0 /= d3;
/* 118 */             d1 /= d3;
/* 119 */             d2 /= d3;
/* 120 */             float f = this.size * (0.7F + this.world.random.nextFloat() * 0.6F);
/* 121 */             double d4 = this.posX;
/* 122 */             double d5 = this.posY;
/* 123 */             double d6 = this.posZ;
/*     */             
/* 125 */             for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
/* 126 */               BlockPosition blockposition = new BlockPosition(d4, d5, d6);
/* 127 */               IBlockData iblockdata = this.world.getType(blockposition);
/* 128 */               if (iblockdata.isDestroyable()) {
/* 129 */                 Fluid fluid = iblockdata.getFluid();
/* 130 */                 Optional<Float> optional = this.l.a(this, this.world, blockposition, iblockdata, fluid);
/*     */                 
/* 132 */                 if (optional.isPresent()) {
/* 133 */                   f -= (((Float)optional.get()).floatValue() + 0.3F) * 0.3F;
/*     */                 }
/*     */                 
/* 136 */                 if (f > 0.0F && this.l.a(this, this.world, blockposition, iblockdata, f) && blockposition.getY() < 256 && blockposition.getY() >= 0) {
/* 137 */                   set.add(blockposition);
/*     */                   
/* 139 */                   if (!PaperConfig.allowHeadlessPistons && iblockdata.getBlock() == Blocks.MOVING_PISTON) {
/* 140 */                     TileEntityPiston extension = (TileEntityPiston)this.world.getTileEntity(blockposition);
/* 141 */                     if (extension.isHead()) {
/* 142 */                       EnumDirection direction = (EnumDirection)iblockdata.get(BlockPistonExtension.FACING);
/* 143 */                       set.add(blockposition.shift(direction.opposite()));
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */                 
/* 149 */                 d4 += d0 * 0.30000001192092896D;
/* 150 */                 d5 += d1 * 0.30000001192092896D;
/* 151 */                 d6 += d2 * 0.30000001192092896D;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 158 */     this.blocks.addAll(set);
/* 159 */     float f2 = this.size * 2.0F;
/*     */     
/* 161 */     int i = MathHelper.floor(this.posX - f2 - 1.0D);
/* 162 */     int j = MathHelper.floor(this.posX + f2 + 1.0D);
/* 163 */     int l = MathHelper.floor(this.posY - f2 - 1.0D);
/* 164 */     int i1 = MathHelper.floor(this.posY + f2 + 1.0D);
/* 165 */     int j1 = MathHelper.floor(this.posZ - f2 - 1.0D);
/* 166 */     int k1 = MathHelper.floor(this.posZ + f2 + 1.0D);
/* 167 */     List<Entity> list = this.world.getEntities(this.source, new AxisAlignedBB(i, l, j1, j, i1, k1), (Predicate<? super Entity>)(entity -> (entity.isAlive() && !entity.isSpectator())));
/* 168 */     Vec3D vec3d = new Vec3D(this.posX, this.posY, this.posZ);
/*     */     
/* 170 */     for (int l1 = 0; l1 < list.size(); l1++) {
/* 171 */       Entity entity = list.get(l1);
/*     */       
/* 173 */       if (!entity.ch()) {
/* 174 */         double d7 = (MathHelper.sqrt(entity.e(vec3d)) / f2);
/*     */         
/* 176 */         if (d7 <= 1.0D) {
/* 177 */           double d8 = entity.locX() - this.posX;
/* 178 */           double d9 = ((entity instanceof EntityTNTPrimed) ? entity.locY() : entity.getHeadY()) - this.posY;
/* 179 */           double d10 = entity.locZ() - this.posZ;
/* 180 */           double d11 = MathHelper.sqrt(d8 * d8 + d9 * d9 + d10 * d10);
/*     */           
/* 182 */           if (d11 != 0.0D) {
/* 183 */             d8 /= d11;
/* 184 */             d9 /= d11;
/* 185 */             d10 /= d11;
/* 186 */             double d12 = getBlockDensity(vec3d, entity);
/* 187 */             double d13 = (1.0D - d7) * d12;
/*     */ 
/*     */ 
/*     */             
/* 191 */             CraftEventFactory.entityDamage = this.source;
/* 192 */             entity.forceExplosionKnockback = false;
/* 193 */             boolean wasDamaged = entity.damageEntity(b(), (int)((d13 * d13 + d13) / 2.0D * 7.0D * f2 + 1.0D));
/* 194 */             CraftEventFactory.entityDamage = null;
/* 195 */             if (wasDamaged || entity instanceof EntityTNTPrimed || entity instanceof EntityFallingBlock || entity.forceExplosionKnockback) {
/*     */ 
/*     */ 
/*     */               
/* 199 */               double d14 = d13;
/*     */               
/* 201 */               if (entity instanceof EntityLiving) {
/* 202 */                 d14 = (entity instanceof EntityHuman && this.world.paperConfig.disableExplosionKnockback) ? 0.0D : EnchantmentProtection.a((EntityLiving)entity, d13);
/*     */               }
/*     */               
/* 205 */               entity.setMot(entity.getMot().add(d8 * d14, d9 * d14, d10 * d14));
/* 206 */               if (entity instanceof EntityHuman) {
/* 207 */                 EntityHuman entityhuman = (EntityHuman)entity;
/*     */                 
/* 209 */                 if (!entityhuman.isSpectator() && (!entityhuman.isCreative() || !entityhuman.abilities.isFlying) && !this.world.paperConfig.disableExplosionKnockback) {
/* 210 */                   this.n.put(entityhuman, new Vec3D(d8 * d13, d9 * d13, d10 * d13));
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 221 */     if (this.world.isClientSide) {
/* 222 */       this.world.a(this.posX, this.posY, this.posZ, SoundEffects.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F, false);
/*     */     }
/*     */     
/* 225 */     boolean flag1 = (this.c != Effect.NONE);
/*     */     
/* 227 */     if (flag) {
/* 228 */       if (this.size >= 2.0F && flag1) {
/* 229 */         this.world.addParticle(Particles.EXPLOSION_EMITTER, this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D);
/*     */       } else {
/* 231 */         this.world.addParticle(Particles.EXPLOSION, this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     }
/*     */     
/* 235 */     if (flag1) {
/* 236 */       boolean cancelled; List<Block> bukkitBlocks; float yield; ObjectArrayList<Pair<ItemStack, BlockPosition>> objectarraylist = new ObjectArrayList();
/*     */       
/* 238 */       Collections.shuffle(this.blocks, this.world.random);
/* 239 */       Iterator<BlockPosition> iterator = this.blocks.iterator();
/*     */       
/* 241 */       CraftWorld craftWorld = this.world.getWorld();
/* 242 */       CraftEntity craftEntity = (this.source == null) ? null : this.source.getBukkitEntity();
/* 243 */       Location location = new Location((World)craftWorld, this.posX, this.posY, this.posZ);
/*     */       
/* 245 */       List<Block> blockList = Lists.newArrayList();
/* 246 */       for (int i1 = this.blocks.size() - 1; i1 >= 0; i1--) {
/* 247 */         BlockPosition cpos = this.blocks.get(i1);
/* 248 */         Block bblock = craftWorld.getBlockAt(cpos.getX(), cpos.getY(), cpos.getZ());
/* 249 */         if (!bblock.getType().isAir()) {
/* 250 */           blockList.add(bblock);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 258 */       if (craftEntity != null) {
/* 259 */         EntityExplodeEvent event = new EntityExplodeEvent((Entity)craftEntity, location, blockList, (this.c == Effect.DESTROY) ? (1.0F / this.size) : 1.0F);
/* 260 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/* 261 */         cancelled = event.isCancelled();
/* 262 */         bukkitBlocks = event.blockList();
/* 263 */         yield = event.getYield();
/*     */       } else {
/* 265 */         BlockExplodeEvent event = new BlockExplodeEvent(location.getBlock(), blockList, (this.c == Effect.DESTROY) ? (1.0F / this.size) : 1.0F);
/* 266 */         this.world.getServer().getPluginManager().callEvent((Event)event);
/* 267 */         cancelled = event.isCancelled();
/* 268 */         bukkitBlocks = event.blockList();
/* 269 */         yield = event.getYield();
/*     */       } 
/*     */       
/* 272 */       this.blocks.clear();
/*     */       
/* 274 */       for (Block bblock : bukkitBlocks) {
/* 275 */         BlockPosition coords = new BlockPosition(bblock.getX(), bblock.getY(), bblock.getZ());
/* 276 */         this.blocks.add(coords);
/*     */       } 
/*     */       
/* 279 */       if (cancelled) {
/* 280 */         this.wasCanceled = true;
/*     */         
/*     */         return;
/*     */       } 
/* 284 */       iterator = this.blocks.iterator();
/*     */       
/* 286 */       while (iterator.hasNext()) {
/* 287 */         BlockPosition blockposition = iterator.next();
/* 288 */         IBlockData iblockdata = this.world.getType(blockposition);
/* 289 */         Block block = iblockdata.getBlock();
/*     */         
/* 291 */         if (!iblockdata.isAir() && iblockdata.isDestroyable()) {
/* 292 */           BlockPosition blockposition1 = blockposition.immutableCopy();
/*     */           
/* 294 */           this.world.getMethodProfiler().enter("explosion_blocks");
/* 295 */           if (block.a(this) && this.world instanceof WorldServer) {
/* 296 */             TileEntity tileentity = block.isTileEntity() ? this.world.getTileEntity(blockposition) : null;
/* 297 */             LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder((WorldServer)this.world)).a(this.world.random).<Vec3D>set(LootContextParameters.ORIGIN, Vec3D.a(blockposition)).<ItemStack>set(LootContextParameters.TOOL, ItemStack.b).<TileEntity>setOptional(LootContextParameters.BLOCK_ENTITY, tileentity).setOptional(LootContextParameters.THIS_ENTITY, this.source);
/*     */             
/* 299 */             if (this.c == Effect.DESTROY || yield < 1.0F) {
/* 300 */               loottableinfo_builder.set(LootContextParameters.EXPLOSION_RADIUS, Float.valueOf(1.0F / yield));
/*     */             }
/*     */             
/* 303 */             iblockdata.a(loottableinfo_builder).forEach(itemstack -> a(objectarraylist, itemstack, blockposition1));
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 308 */           this.world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 3);
/* 309 */           block.wasExploded(this.world, blockposition, this);
/* 310 */           this.world.getMethodProfiler().exit();
/*     */         } 
/*     */       } 
/*     */       
/* 314 */       ObjectListIterator objectlistiterator = objectarraylist.iterator();
/*     */       
/* 316 */       while (objectlistiterator.hasNext()) {
/* 317 */         Pair<ItemStack, BlockPosition> pair = (Pair<ItemStack, BlockPosition>)objectlistiterator.next();
/*     */         
/* 319 */         Block.a(this.world, (BlockPosition)pair.getSecond(), (ItemStack)pair.getFirst());
/*     */       } 
/*     */     } 
/*     */     
/* 323 */     if (this.b) {
/* 324 */       Iterator<BlockPosition> iterator1 = this.blocks.iterator();
/*     */       
/* 326 */       while (iterator1.hasNext()) {
/* 327 */         BlockPosition blockposition2 = iterator1.next();
/*     */         
/* 329 */         if (this.d.nextInt(3) == 0 && this.world.getType(blockposition2).isAir() && this.world.getType(blockposition2.down()).i(this.world, blockposition2.down()))
/*     */         {
/* 331 */           if (!CraftEventFactory.callBlockIgniteEvent(this.world, blockposition2.getX(), blockposition2.getY(), blockposition2.getZ(), this).isCancelled()) {
/* 332 */             this.world.setTypeUpdate(blockposition2, BlockFireAbstract.a(this.world, blockposition2));
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(ObjectArrayList<Pair<ItemStack, BlockPosition>> objectarraylist, ItemStack itemstack, BlockPosition blockposition) {
/* 342 */     if (itemstack.isEmpty())
/* 343 */       return;  int i = objectarraylist.size();
/*     */     
/* 345 */     for (int j = 0; j < i; j++) {
/* 346 */       Pair<ItemStack, BlockPosition> pair = (Pair<ItemStack, BlockPosition>)objectarraylist.get(j);
/* 347 */       ItemStack itemstack1 = (ItemStack)pair.getFirst();
/*     */       
/* 349 */       if (EntityItem.a(itemstack1, itemstack)) {
/* 350 */         ItemStack itemstack2 = EntityItem.a(itemstack1, itemstack, 16);
/*     */         
/* 352 */         objectarraylist.set(j, Pair.of(itemstack2, pair.getSecond()));
/* 353 */         if (itemstack.isEmpty()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 359 */     objectarraylist.add(Pair.of(itemstack, blockposition));
/*     */   }
/*     */   
/*     */   public DamageSource b() {
/* 363 */     return this.k;
/*     */   }
/*     */   
/*     */   public Map<EntityHuman, Vec3D> c() {
/* 367 */     return this.n;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getSource() {
/* 372 */     if (this.source == null)
/* 373 */       return null; 
/* 374 */     if (this.source instanceof EntityTNTPrimed)
/* 375 */       return ((EntityTNTPrimed)this.source).getSource(); 
/* 376 */     if (this.source instanceof EntityLiving) {
/* 377 */       return (EntityLiving)this.source;
/*     */     }
/* 379 */     if (this.source instanceof IProjectile) {
/* 380 */       Entity entity = ((IProjectile)this.source).getShooter();
/*     */       
/* 382 */       if (entity instanceof EntityLiving) {
/* 383 */         return (EntityLiving)entity;
/*     */       }
/*     */     } 
/*     */     
/* 387 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearBlocks() {
/* 392 */     this.blocks.clear();
/*     */   }
/*     */   
/*     */   public List<BlockPosition> getBlocks() {
/* 396 */     return this.blocks;
/*     */   }
/*     */   
/*     */   public enum Effect
/*     */   {
/* 401 */     NONE, BREAK, DESTROY;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float getBlockDensity(Vec3D vec3d, Entity entity) {
/* 407 */     if (!this.world.paperConfig.optimizeExplosions) {
/* 408 */       return a(vec3d, entity);
/*     */     }
/* 410 */     CacheKey key = new CacheKey(this, entity.getBoundingBox());
/* 411 */     Float blockDensity = this.world.explosionDensityCache.get(key);
/* 412 */     if (blockDensity == null) {
/* 413 */       blockDensity = Float.valueOf(a(vec3d, entity));
/* 414 */       this.world.explosionDensityCache.put(key, blockDensity);
/*     */     } 
/*     */     
/* 417 */     return blockDensity.floatValue();
/*     */   }
/*     */   
/*     */   static class CacheKey { private final World world;
/*     */     private final double posX;
/*     */     private final double posY;
/*     */     private final double posZ;
/*     */     private final double minX;
/*     */     
/*     */     public CacheKey(Explosion explosion, AxisAlignedBB aabb) {
/* 427 */       this.world = explosion.world;
/* 428 */       this.posX = explosion.posX;
/* 429 */       this.posY = explosion.posY;
/* 430 */       this.posZ = explosion.posZ;
/* 431 */       this.minX = aabb.minX;
/* 432 */       this.minY = aabb.minY;
/* 433 */       this.minZ = aabb.minZ;
/* 434 */       this.maxX = aabb.maxX;
/* 435 */       this.maxY = aabb.maxY;
/* 436 */       this.maxZ = aabb.maxZ;
/*     */     }
/*     */     private final double minY; private final double minZ; private final double maxX; private final double maxY; private final double maxZ;
/*     */     
/*     */     public boolean equals(Object o) {
/* 441 */       if (this == o) return true; 
/* 442 */       if (o == null || getClass() != o.getClass()) return false;
/*     */       
/* 444 */       CacheKey cacheKey = (CacheKey)o;
/*     */       
/* 446 */       if (Double.compare(cacheKey.posX, this.posX) != 0) return false; 
/* 447 */       if (Double.compare(cacheKey.posY, this.posY) != 0) return false; 
/* 448 */       if (Double.compare(cacheKey.posZ, this.posZ) != 0) return false; 
/* 449 */       if (Double.compare(cacheKey.minX, this.minX) != 0) return false; 
/* 450 */       if (Double.compare(cacheKey.minY, this.minY) != 0) return false; 
/* 451 */       if (Double.compare(cacheKey.minZ, this.minZ) != 0) return false; 
/* 452 */       if (Double.compare(cacheKey.maxX, this.maxX) != 0) return false; 
/* 453 */       if (Double.compare(cacheKey.maxY, this.maxY) != 0) return false; 
/* 454 */       if (Double.compare(cacheKey.maxZ, this.maxZ) != 0) return false; 
/* 455 */       return this.world.equals(cacheKey.world);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 462 */       int result = this.world.hashCode();
/* 463 */       long temp = Double.doubleToLongBits(this.posX);
/* 464 */       result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 465 */       temp = Double.doubleToLongBits(this.posY);
/* 466 */       result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 467 */       temp = Double.doubleToLongBits(this.posZ);
/* 468 */       result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 469 */       temp = Double.doubleToLongBits(this.minX);
/* 470 */       result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 471 */       temp = Double.doubleToLongBits(this.minY);
/* 472 */       result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 473 */       temp = Double.doubleToLongBits(this.minZ);
/* 474 */       result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 475 */       temp = Double.doubleToLongBits(this.maxX);
/* 476 */       result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 477 */       temp = Double.doubleToLongBits(this.maxY);
/* 478 */       result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 479 */       temp = Double.doubleToLongBits(this.maxZ);
/* 480 */       result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 481 */       return result;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Explosion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */