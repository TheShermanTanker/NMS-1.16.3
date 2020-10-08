/*      */ package org.bukkit;
/*      */ 
/*      */ import com.destroystokyo.paper.HeightmapType;
/*      */ import com.google.common.base.Preconditions;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.function.Predicate;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.util.NumberConversions;
/*      */ import org.bukkit.util.Vector;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Location
/*      */   implements Cloneable, ConfigurationSerializable
/*      */ {
/*      */   private Reference<World> world;
/*      */   private double x;
/*      */   private double y;
/*      */   private double z;
/*      */   private float pitch;
/*      */   private float yaw;
/*      */   
/*      */   public Location(@UndefinedNullability World world, double x, double y, double z) {
/*   50 */     this(world, x, y, z, 0.0F, 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Location(@UndefinedNullability World world, double x, double y, double z, float yaw, float pitch) {
/*   64 */     if (world != null) {
/*   65 */       this.world = new WeakReference<>(world);
/*      */     }
/*      */     
/*   68 */     this.x = x;
/*   69 */     this.y = y;
/*   70 */     this.z = z;
/*   71 */     this.pitch = pitch;
/*   72 */     this.yaw = yaw;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWorld(@Nullable World world) {
/*   81 */     this.world = (world == null) ? null : new WeakReference<>(world);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWorldLoaded() {
/*   90 */     if (this.world == null) {
/*   91 */       return false;
/*      */     }
/*      */     
/*   94 */     World world = this.world.get();
/*   95 */     return (world != null && Bukkit.getWorld(world.getUID()) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @UndefinedNullability
/*      */   public World getWorld() {
/*  107 */     if (this.world == null) {
/*  108 */       return null;
/*      */     }
/*      */     
/*  111 */     World world = this.world.get();
/*  112 */     Preconditions.checkArgument((world != null), "World unloaded");
/*  113 */     return world;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Chunk getChunk() {
/*  123 */     return getWorld().getChunkAt(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Block getBlock() {
/*  133 */     return getWorld().getBlockAt(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setX(double x) {
/*  142 */     this.x = x;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getX() {
/*  151 */     return this.x;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBlockX() {
/*  161 */     return locToBlock(this.x);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setY(double y) {
/*  170 */     this.y = y;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getY() {
/*  179 */     return this.y;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBlockY() {
/*  189 */     return locToBlock(this.y);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setZ(double z) {
/*  198 */     this.z = z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getZ() {
/*  207 */     return this.z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBlockZ() {
/*  217 */     return locToBlock(this.z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setYaw(float yaw) {
/*  235 */     this.yaw = yaw;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getYaw() {
/*  253 */     return this.yaw;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPitch(float pitch) {
/*  269 */     this.pitch = pitch;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getPitch() {
/*  285 */     return this.pitch;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Vector getDirection() {
/*  297 */     Vector vector = new Vector();
/*      */     
/*  299 */     double rotX = getYaw();
/*  300 */     double rotY = getPitch();
/*      */     
/*  302 */     vector.setY(-Math.sin(Math.toRadians(rotY)));
/*      */     
/*  304 */     double xz = Math.cos(Math.toRadians(rotY));
/*      */     
/*  306 */     vector.setX(-xz * Math.sin(Math.toRadians(rotX)));
/*  307 */     vector.setZ(xz * Math.cos(Math.toRadians(rotX)));
/*      */     
/*  309 */     return vector;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location setDirection(@NotNull Vector vector) {
/*  329 */     double _2PI = 6.283185307179586D;
/*  330 */     double x = vector.getX();
/*  331 */     double z = vector.getZ();
/*      */     
/*  333 */     if (x == 0.0D && z == 0.0D) {
/*  334 */       this.pitch = (vector.getY() > 0.0D) ? -90.0F : 90.0F;
/*  335 */       return this;
/*      */     } 
/*      */     
/*  338 */     double theta = Math.atan2(-x, z);
/*  339 */     this.yaw = (float)Math.toDegrees((theta + 6.283185307179586D) % 6.283185307179586D);
/*      */     
/*  341 */     double x2 = NumberConversions.square(x);
/*  342 */     double z2 = NumberConversions.square(z);
/*  343 */     double xz = Math.sqrt(x2 + z2);
/*  344 */     this.pitch = (float)Math.toDegrees(Math.atan(-vector.getY() / xz));
/*      */     
/*  346 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location add(@NotNull Location vec) {
/*  359 */     if (vec == null || vec.getWorld() != getWorld()) {
/*  360 */       throw new IllegalArgumentException("Cannot add Locations of differing worlds");
/*      */     }
/*      */     
/*  363 */     this.x += vec.x;
/*  364 */     this.y += vec.y;
/*  365 */     this.z += vec.z;
/*  366 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location add(@NotNull Vector vec) {
/*  378 */     this.x += vec.getX();
/*  379 */     this.y += vec.getY();
/*  380 */     this.z += vec.getZ();
/*  381 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location add(double x, double y, double z) {
/*  395 */     this.x += x;
/*  396 */     this.y += y;
/*  397 */     this.z += z;
/*  398 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location subtract(@NotNull Location vec) {
/*  411 */     if (vec == null || vec.getWorld() != getWorld()) {
/*  412 */       throw new IllegalArgumentException("Cannot add Locations of differing worlds");
/*      */     }
/*      */     
/*  415 */     this.x -= vec.x;
/*  416 */     this.y -= vec.y;
/*  417 */     this.z -= vec.z;
/*  418 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location subtract(@NotNull Vector vec) {
/*  430 */     this.x -= vec.getX();
/*  431 */     this.y -= vec.getY();
/*  432 */     this.z -= vec.getZ();
/*  433 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location subtract(double x, double y, double z) {
/*  448 */     this.x -= x;
/*  449 */     this.y -= y;
/*  450 */     this.z -= z;
/*  451 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double length() {
/*  466 */     return Math.sqrt(NumberConversions.square(this.x) + NumberConversions.square(this.y) + NumberConversions.square(this.z));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double lengthSquared() {
/*  477 */     return NumberConversions.square(this.x) + NumberConversions.square(this.y) + NumberConversions.square(this.z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double distance(@NotNull Location o) {
/*  493 */     return Math.sqrt(distanceSquared(o));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double distanceSquared(@NotNull Location o) {
/*  505 */     if (o == null)
/*  506 */       throw new IllegalArgumentException("Cannot measure distance to a null location"); 
/*  507 */     if (o.getWorld() == null || getWorld() == null)
/*  508 */       throw new IllegalArgumentException("Cannot measure distance to a null world"); 
/*  509 */     if (o.getWorld() != getWorld()) {
/*  510 */       throw new IllegalArgumentException("Cannot measure distance between " + getWorld().getName() + " and " + o.getWorld().getName());
/*      */     }
/*      */     
/*  513 */     return NumberConversions.square(this.x - o.x) + NumberConversions.square(this.y - o.y) + NumberConversions.square(this.z - o.z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location multiply(double m) {
/*  526 */     this.x *= m;
/*  527 */     this.y *= m;
/*  528 */     this.z *= m;
/*  529 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location zero() {
/*  540 */     this.x = 0.0D;
/*  541 */     this.y = 0.0D;
/*  542 */     this.z = 0.0D;
/*  543 */     return this;
/*      */   }
/*      */   public boolean isChunkLoaded() {
/*  546 */     return getWorld().isChunkLoaded(locToBlock(this.x) >> 4, locToBlock(this.z) >> 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isGenerated() {
/*  555 */     World world = getWorld();
/*  556 */     Preconditions.checkNotNull(world, "Location has no world!");
/*  557 */     return world.isChunkGenerated(locToBlock(this.x) >> 4, locToBlock(this.z) >> 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location set(double x, double y, double z) {
/*  571 */     this.x = x;
/*  572 */     this.y = y;
/*  573 */     this.z = z;
/*  574 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location add(@NotNull Location base, double x, double y, double z) {
/*  589 */     return set(base.x + x, base.y + y, base.z + z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location subtract(@NotNull Location base, double x, double y, double z) {
/*  604 */     return set(base.x - x, base.y - y, base.z - z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location toBlockLocation() {
/*  612 */     Location blockLoc = clone();
/*  613 */     blockLoc.setX(getBlockX());
/*  614 */     blockLoc.setY(getBlockY());
/*  615 */     blockLoc.setZ(getBlockZ());
/*  616 */     return blockLoc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long toBlockKey() {
/*  625 */     return Block.getBlockKey(getBlockX(), getBlockY(), getBlockZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location toCenterLocation() {
/*  634 */     Location centerLoc = clone();
/*  635 */     centerLoc.setX(getBlockX() + 0.5D);
/*  636 */     centerLoc.setY(getBlockY() + 0.5D);
/*  637 */     centerLoc.setZ(getBlockZ() + 0.5D);
/*  638 */     return centerLoc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location toHighestLocation() {
/*  650 */     return toHighestLocation(HeightmapType.LIGHT_BLOCKING);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location toHighestLocation(@NotNull HeightmapType heightmap) {
/*  662 */     Location ret = clone();
/*  663 */     ret.setY(getWorld().getHighestBlockYAt(this, heightmap));
/*  664 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean createExplosion(float power) {
/*  677 */     return getWorld().createExplosion(this, power);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean createExplosion(float power, boolean setFire) {
/*  691 */     return getWorld().createExplosion(this, power, setFire);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean createExplosion(float power, boolean setFire, boolean breakBlocks) {
/*  704 */     return getWorld().createExplosion(this, power, setFire, breakBlocks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean createExplosion(@Nullable Entity source, float power) {
/*  717 */     return getWorld().createExplosion(source, this, power, true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean createExplosion(@Nullable Entity source, float power, boolean setFire) {
/*  732 */     return getWorld().createExplosion(source, this, power, setFire, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean createExplosion(@NotNull Entity source, float power, boolean setFire, boolean breakBlocks) {
/*  746 */     return getWorld().createExplosion(source, this, power, setFire, breakBlocks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<Entity> getNearbyEntities(double x, double y, double z) {
/*  761 */     World world = getWorld();
/*  762 */     if (world == null) {
/*  763 */       throw new IllegalArgumentException("Location has no world");
/*      */     }
/*  765 */     return world.getNearbyEntities(this, x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<LivingEntity> getNearbyLivingEntities(double radius) {
/*  775 */     return getNearbyEntitiesByType(LivingEntity.class, radius, radius, radius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<LivingEntity> getNearbyLivingEntities(double xzRadius, double yRadius) {
/*  786 */     return getNearbyEntitiesByType(LivingEntity.class, xzRadius, yRadius, xzRadius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<LivingEntity> getNearbyLivingEntities(double xRadius, double yRadius, double zRadius) {
/*  798 */     return getNearbyEntitiesByType(LivingEntity.class, xRadius, yRadius, zRadius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<LivingEntity> getNearbyLivingEntities(double radius, @Nullable Predicate<LivingEntity> predicate) {
/*  809 */     return getNearbyEntitiesByType((Class)LivingEntity.class, radius, radius, radius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<LivingEntity> getNearbyLivingEntities(double xzRadius, double yRadius, @Nullable Predicate<LivingEntity> predicate) {
/*  821 */     return getNearbyEntitiesByType((Class)LivingEntity.class, xzRadius, yRadius, xzRadius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<LivingEntity> getNearbyLivingEntities(double xRadius, double yRadius, double zRadius, @Nullable Predicate<LivingEntity> predicate) {
/*  834 */     return getNearbyEntitiesByType((Class)LivingEntity.class, xRadius, yRadius, zRadius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<Player> getNearbyPlayers(double radius) {
/*  844 */     return getNearbyEntitiesByType(Player.class, radius, radius, radius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<Player> getNearbyPlayers(double xzRadius, double yRadius) {
/*  855 */     return getNearbyEntitiesByType(Player.class, xzRadius, yRadius, xzRadius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<Player> getNearbyPlayers(double xRadius, double yRadius, double zRadius) {
/*  867 */     return getNearbyEntitiesByType(Player.class, xRadius, yRadius, zRadius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<Player> getNearbyPlayers(double radius, @Nullable Predicate<Player> predicate) {
/*  878 */     return getNearbyEntitiesByType((Class)Player.class, radius, radius, radius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<Player> getNearbyPlayers(double xzRadius, double yRadius, @Nullable Predicate<Player> predicate) {
/*  890 */     return getNearbyEntitiesByType((Class)Player.class, xzRadius, yRadius, xzRadius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Collection<Player> getNearbyPlayers(double xRadius, double yRadius, double zRadius, @Nullable Predicate<Player> predicate) {
/*  903 */     return getNearbyEntitiesByType((Class)Player.class, xRadius, yRadius, zRadius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, double radius) {
/*  915 */     return getNearbyEntitiesByType(clazz, radius, radius, radius, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, double xzRadius, double yRadius) {
/*  928 */     return getNearbyEntitiesByType(clazz, xzRadius, yRadius, xzRadius, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, double xRadius, double yRadius, double zRadius) {
/*  942 */     return getNearbyEntitiesByType(clazz, xRadius, yRadius, zRadius, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, double radius, @Nullable Predicate<T> predicate) {
/*  955 */     return getNearbyEntitiesByType(clazz, radius, radius, radius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, double xzRadius, double yRadius, @Nullable Predicate<T> predicate) {
/*  969 */     return getNearbyEntitiesByType(clazz, xzRadius, yRadius, xzRadius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends Entity> clazz, double xRadius, double yRadius, double zRadius, @Nullable Predicate<T> predicate) {
/*  984 */     World world = getWorld();
/*  985 */     if (world == null) {
/*  986 */       throw new IllegalArgumentException("Location has no world");
/*      */     }
/*  988 */     return world.getNearbyEntitiesByType(clazz, this, xRadius, yRadius, zRadius, predicate);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  993 */     if (obj == null) {
/*  994 */       return false;
/*      */     }
/*  996 */     if (getClass() != obj.getClass()) {
/*  997 */       return false;
/*      */     }
/*  999 */     Location other = (Location)obj;
/*      */     
/* 1001 */     World world = (this.world == null) ? null : this.world.get();
/* 1002 */     World otherWorld = (other.world == null) ? null : other.world.get();
/* 1003 */     if (world != otherWorld && (world == null || !world.equals(otherWorld))) {
/* 1004 */       return false;
/*      */     }
/* 1006 */     if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
/* 1007 */       return false;
/*      */     }
/* 1009 */     if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
/* 1010 */       return false;
/*      */     }
/* 1012 */     if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
/* 1013 */       return false;
/*      */     }
/* 1015 */     if (Float.floatToIntBits(this.pitch) != Float.floatToIntBits(other.pitch)) {
/* 1016 */       return false;
/*      */     }
/* 1018 */     if (Float.floatToIntBits(this.yaw) != Float.floatToIntBits(other.yaw)) {
/* 1019 */       return false;
/*      */     }
/* 1021 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 1026 */     int hash = 3;
/*      */     
/* 1028 */     World world = (this.world == null) ? null : this.world.get();
/* 1029 */     hash = 19 * hash + ((world != null) ? world.hashCode() : 0);
/* 1030 */     hash = 19 * hash + (int)(Double.doubleToLongBits(this.x) ^ Double.doubleToLongBits(this.x) >>> 32L);
/* 1031 */     hash = 19 * hash + (int)(Double.doubleToLongBits(this.y) ^ Double.doubleToLongBits(this.y) >>> 32L);
/* 1032 */     hash = 19 * hash + (int)(Double.doubleToLongBits(this.z) ^ Double.doubleToLongBits(this.z) >>> 32L);
/* 1033 */     hash = 19 * hash + Float.floatToIntBits(this.pitch);
/* 1034 */     hash = 19 * hash + Float.floatToIntBits(this.yaw);
/* 1035 */     return hash;
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1040 */     World world = (this.world == null) ? null : this.world.get();
/* 1041 */     return "Location{world=" + world + ",x=" + this.x + ",y=" + this.y + ",z=" + this.z + ",pitch=" + this.pitch + ",yaw=" + this.yaw + '}';
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Vector toVector() {
/* 1052 */     return new Vector(this.x, this.y, this.z);
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Location clone() {
/*      */     try {
/* 1059 */       return (Location)super.clone();
/* 1060 */     } catch (CloneNotSupportedException e) {
/* 1061 */       throw new Error(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void checkFinite() throws IllegalArgumentException {
/* 1071 */     NumberConversions.checkFinite(this.x, "x not finite");
/* 1072 */     NumberConversions.checkFinite(this.y, "y not finite");
/* 1073 */     NumberConversions.checkFinite(this.z, "z not finite");
/* 1074 */     NumberConversions.checkFinite(this.pitch, "pitch not finite");
/* 1075 */     NumberConversions.checkFinite(this.yaw, "yaw not finite");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int locToBlock(double loc) {
/* 1086 */     return NumberConversions.floor(loc);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Map<String, Object> serialize() {
/* 1093 */     Map<String, Object> data = new HashMap<>();
/*      */     
/* 1095 */     if (this.world != null) {
/* 1096 */       data.put("world", getWorld().getName());
/*      */     }
/*      */     
/* 1099 */     data.put("x", Double.valueOf(this.x));
/* 1100 */     data.put("y", Double.valueOf(this.y));
/* 1101 */     data.put("z", Double.valueOf(this.z));
/*      */     
/* 1103 */     data.put("yaw", Float.valueOf(this.yaw));
/* 1104 */     data.put("pitch", Float.valueOf(this.pitch));
/*      */     
/* 1106 */     return data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Location deserialize(@NotNull Map<String, Object> args) {
/* 1119 */     World world = null;
/* 1120 */     if (args.containsKey("world")) {
/* 1121 */       world = Bukkit.getWorld((String)args.get("world"));
/* 1122 */       if (world == null) {
/* 1123 */         throw new IllegalArgumentException("unknown world");
/*      */       }
/*      */     } 
/*      */     
/* 1127 */     return new Location(world, NumberConversions.toDouble(args.get("x")), NumberConversions.toDouble(args.get("y")), NumberConversions.toDouble(args.get("z")), NumberConversions.toFloat(args.get("yaw")), NumberConversions.toFloat(args.get("pitch")));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float normalizeYaw(float yaw) {
/* 1139 */     yaw %= 360.0F;
/* 1140 */     if (yaw >= 180.0F) {
/* 1141 */       yaw -= 360.0F;
/* 1142 */     } else if (yaw < -180.0F) {
/* 1143 */       yaw += 360.0F;
/*      */     } 
/* 1145 */     return yaw;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float normalizePitch(float pitch) {
/* 1157 */     if (pitch > 90.0F) {
/* 1158 */       pitch = 90.0F;
/* 1159 */     } else if (pitch < -90.0F) {
/* 1160 */       pitch = -90.0F;
/*      */     } 
/* 1162 */     return pitch;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Location.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */