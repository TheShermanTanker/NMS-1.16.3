/*      */ package org.bukkit.craftbukkit.v1_16_R2;
/*      */ import com.destroystokyo.paper.HeightmapType;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import com.mojang.datafixers.util.Either;
/*      */ import io.papermc.paper.world.MoonPhase;
/*      */ import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
/*      */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*      */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*      */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.CompletableFuture;
/*      */ import java.util.concurrent.CompletionStage;
/*      */ import java.util.concurrent.Executor;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.stream.Collectors;
/*      */ import net.minecraft.server.v1_16_R2.ArraySetSorted;
/*      */ import net.minecraft.server.v1_16_R2.AxisAlignedBB;
/*      */ import net.minecraft.server.v1_16_R2.BiomeBase;
/*      */ import net.minecraft.server.v1_16_R2.BiomeDecoratorGroups;
/*      */ import net.minecraft.server.v1_16_R2.BlockChorusFlower;
/*      */ import net.minecraft.server.v1_16_R2.BlockDiodeAbstract;
/*      */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*      */ import net.minecraft.server.v1_16_R2.Blocks;
/*      */ import net.minecraft.server.v1_16_R2.Chunk;
/*      */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*      */ import net.minecraft.server.v1_16_R2.ChunkMapDistance;
/*      */ import net.minecraft.server.v1_16_R2.ChunkStatus;
/*      */ import net.minecraft.server.v1_16_R2.Entity;
/*      */ import net.minecraft.server.v1_16_R2.EntityAreaEffectCloud;
/*      */ import net.minecraft.server.v1_16_R2.EntityArmorStand;
/*      */ import net.minecraft.server.v1_16_R2.EntityArrow;
/*      */ import net.minecraft.server.v1_16_R2.EntityBoat;
/*      */ import net.minecraft.server.v1_16_R2.EntityEgg;
/*      */ import net.minecraft.server.v1_16_R2.EntityEnderSignal;
/*      */ import net.minecraft.server.v1_16_R2.EntityEvokerFangs;
/*      */ import net.minecraft.server.v1_16_R2.EntityExperienceOrb;
/*      */ import net.minecraft.server.v1_16_R2.EntityFallingBlock;
/*      */ import net.minecraft.server.v1_16_R2.EntityFireball;
/*      */ import net.minecraft.server.v1_16_R2.EntityFireworks;
/*      */ import net.minecraft.server.v1_16_R2.EntityHanging;
/*      */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*      */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*      */ import net.minecraft.server.v1_16_R2.EntityItem;
/*      */ import net.minecraft.server.v1_16_R2.EntityItemFrame;
/*      */ import net.minecraft.server.v1_16_R2.EntityLeash;
/*      */ import net.minecraft.server.v1_16_R2.EntityLightning;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartChest;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartCommandBlock;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartFurnace;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartHopper;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartMobSpawner;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartRideable;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartTNT;
/*      */ import net.minecraft.server.v1_16_R2.EntityPainting;
/*      */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*      */ import net.minecraft.server.v1_16_R2.EntityPotion;
/*      */ import net.minecraft.server.v1_16_R2.EntitySnowball;
/*      */ import net.minecraft.server.v1_16_R2.EntityTNTPrimed;
/*      */ import net.minecraft.server.v1_16_R2.EntityTippedArrow;
/*      */ import net.minecraft.server.v1_16_R2.EntityTypes;
/*      */ import net.minecraft.server.v1_16_R2.EntityZombie;
/*      */ import net.minecraft.server.v1_16_R2.EnumDifficulty;
/*      */ import net.minecraft.server.v1_16_R2.EnumDirection;
/*      */ import net.minecraft.server.v1_16_R2.EnumMobSpawn;
/*      */ import net.minecraft.server.v1_16_R2.Explosion;
/*      */ import net.minecraft.server.v1_16_R2.GameRules;
/*      */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*      */ import net.minecraft.server.v1_16_R2.GeneratorAccessSeed;
/*      */ import net.minecraft.server.v1_16_R2.HeightMap;
/*      */ import net.minecraft.server.v1_16_R2.IBlockData;
/*      */ import net.minecraft.server.v1_16_R2.IChunkAccess;
/*      */ import net.minecraft.server.v1_16_R2.IMaterial;
/*      */ import net.minecraft.server.v1_16_R2.IRegistry;
/*      */ import net.minecraft.server.v1_16_R2.ItemStack;
/*      */ import net.minecraft.server.v1_16_R2.MCUtil;
/*      */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*      */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*      */ import net.minecraft.server.v1_16_R2.MovingObjectPosition;
/*      */ import net.minecraft.server.v1_16_R2.MovingObjectPositionBlock;
/*      */ import net.minecraft.server.v1_16_R2.Packet;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutCustomSoundEffect;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutUpdateTime;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayOutWorldEvent;
/*      */ import net.minecraft.server.v1_16_R2.PersistentRaid;
/*      */ import net.minecraft.server.v1_16_R2.PlayerChunk;
/*      */ import net.minecraft.server.v1_16_R2.PlayerChunkMap;
/*      */ import net.minecraft.server.v1_16_R2.Raid;
/*      */ import net.minecraft.server.v1_16_R2.RayTrace;
/*      */ import net.minecraft.server.v1_16_R2.SavedFile;
/*      */ import net.minecraft.server.v1_16_R2.SoundCategory;
/*      */ import net.minecraft.server.v1_16_R2.StructureGenerator;
/*      */ import net.minecraft.server.v1_16_R2.Ticket;
/*      */ import net.minecraft.server.v1_16_R2.TicketType;
/*      */ import net.minecraft.server.v1_16_R2.Unit;
/*      */ import net.minecraft.server.v1_16_R2.Vec3D;
/*      */ import net.minecraft.server.v1_16_R2.World;
/*      */ import net.minecraft.server.v1_16_R2.WorldGenFeatureConfigured;
/*      */ import net.minecraft.server.v1_16_R2.WorldServer;
/*      */ import org.apache.commons.lang.Validate;
/*      */ import org.bukkit.BlockChangeDelegate;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Chunk;
/*      */ import org.bukkit.Difficulty;
/*      */ import org.bukkit.Effect;
/*      */ import org.bukkit.FluidCollisionMode;
/*      */ import org.bukkit.GameRule;
/*      */ import org.bukkit.HeightMap;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.Particle;
/*      */ import org.bukkit.Raid;
/*      */ import org.bukkit.Sound;
/*      */ import org.bukkit.SoundCategory;
/*      */ import org.bukkit.StructureType;
/*      */ import org.bukkit.TreeType;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.WorldBorder;
/*      */ import org.bukkit.WorldType;
/*      */ import org.bukkit.block.Biome;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.block.BlockFace;
/*      */ import org.bukkit.block.BlockState;
/*      */ import org.bukkit.block.data.BlockData;
/*      */ import org.bukkit.boss.DragonBattle;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.boss.CraftDragonBattle;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.metadata.BlockMetadataStore;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.potion.CraftPotionUtil;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftRayTraceResult;
/*      */ import org.bukkit.entity.AbstractArrow;
/*      */ import org.bukkit.entity.AbstractHorse;
/*      */ import org.bukkit.entity.AbstractVillager;
/*      */ import org.bukkit.entity.Ambient;
/*      */ import org.bukkit.entity.AreaEffectCloud;
/*      */ import org.bukkit.entity.ArmorStand;
/*      */ import org.bukkit.entity.Arrow;
/*      */ import org.bukkit.entity.Bat;
/*      */ import org.bukkit.entity.Bee;
/*      */ import org.bukkit.entity.Blaze;
/*      */ import org.bukkit.entity.Boat;
/*      */ import org.bukkit.entity.CaveSpider;
/*      */ import org.bukkit.entity.ChestedHorse;
/*      */ import org.bukkit.entity.Cod;
/*      */ import org.bukkit.entity.ComplexLivingEntity;
/*      */ import org.bukkit.entity.Creeper;
/*      */ import org.bukkit.entity.Dolphin;
/*      */ import org.bukkit.entity.Donkey;
/*      */ import org.bukkit.entity.DragonFireball;
/*      */ import org.bukkit.entity.Drowned;
/*      */ import org.bukkit.entity.Egg;
/*      */ import org.bukkit.entity.ElderGuardian;
/*      */ import org.bukkit.entity.EnderCrystal;
/*      */ import org.bukkit.entity.EnderDragon;
/*      */ import org.bukkit.entity.EnderPearl;
/*      */ import org.bukkit.entity.EnderSignal;
/*      */ import org.bukkit.entity.Enderman;
/*      */ import org.bukkit.entity.Endermite;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.EntityType;
/*      */ import org.bukkit.entity.Evoker;
/*      */ import org.bukkit.entity.EvokerFangs;
/*      */ import org.bukkit.entity.ExperienceOrb;
/*      */ import org.bukkit.entity.FallingBlock;
/*      */ import org.bukkit.entity.Fireball;
/*      */ import org.bukkit.entity.Fox;
/*      */ import org.bukkit.entity.Ghast;
/*      */ import org.bukkit.entity.Giant;
/*      */ import org.bukkit.entity.Hanging;
/*      */ import org.bukkit.entity.Hoglin;
/*      */ import org.bukkit.entity.Husk;
/*      */ import org.bukkit.entity.Illager;
/*      */ import org.bukkit.entity.Illusioner;
/*      */ import org.bukkit.entity.IronGolem;
/*      */ import org.bukkit.entity.Item;
/*      */ import org.bukkit.entity.ItemFrame;
/*      */ import org.bukkit.entity.LeashHitch;
/*      */ import org.bukkit.entity.LightningStrike;
/*      */ import org.bukkit.entity.LingeringPotion;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.entity.Llama;
/*      */ import org.bukkit.entity.Minecart;
/*      */ import org.bukkit.entity.Mule;
/*      */ import org.bukkit.entity.MushroomCow;
/*      */ import org.bukkit.entity.Painting;
/*      */ import org.bukkit.entity.Parrot;
/*      */ import org.bukkit.entity.Phantom;
/*      */ import org.bukkit.entity.PigZombie;
/*      */ import org.bukkit.entity.Piglin;
/*      */ import org.bukkit.entity.PiglinBrute;
/*      */ import org.bukkit.entity.Pillager;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.entity.PolarBear;
/*      */ import org.bukkit.entity.Projectile;
/*      */ import org.bukkit.entity.PufferFish;
/*      */ import org.bukkit.entity.Rabbit;
/*      */ import org.bukkit.entity.Ravager;
/*      */ import org.bukkit.entity.Salmon;
/*      */ import org.bukkit.entity.Sheep;
/*      */ import org.bukkit.entity.Shulker;
/*      */ import org.bukkit.entity.ShulkerBullet;
/*      */ import org.bukkit.entity.Silverfish;
/*      */ import org.bukkit.entity.Skeleton;
/*      */ import org.bukkit.entity.SkeletonHorse;
/*      */ import org.bukkit.entity.Slime;
/*      */ import org.bukkit.entity.Snowball;
/*      */ import org.bukkit.entity.Snowman;
/*      */ import org.bukkit.entity.SpectralArrow;
/*      */ import org.bukkit.entity.Spellcaster;
/*      */ import org.bukkit.entity.Squid;
/*      */ import org.bukkit.entity.Stray;
/*      */ import org.bukkit.entity.Strider;
/*      */ import org.bukkit.entity.TNTPrimed;
/*      */ import org.bukkit.entity.ThrownExpBottle;
/*      */ import org.bukkit.entity.ThrownPotion;
/*      */ import org.bukkit.entity.TippedArrow;
/*      */ import org.bukkit.entity.Trident;
/*      */ import org.bukkit.entity.TropicalFish;
/*      */ import org.bukkit.entity.Turtle;
/*      */ import org.bukkit.entity.Villager;
/*      */ import org.bukkit.entity.Vindicator;
/*      */ import org.bukkit.entity.WanderingTrader;
/*      */ import org.bukkit.entity.Witch;
/*      */ import org.bukkit.entity.Wither;
/*      */ import org.bukkit.entity.WitherSkeleton;
/*      */ import org.bukkit.entity.WitherSkull;
/*      */ import org.bukkit.entity.Zoglin;
/*      */ import org.bukkit.entity.Zombie;
/*      */ import org.bukkit.entity.ZombieHorse;
/*      */ import org.bukkit.entity.ZombieVillager;
/*      */ import org.bukkit.entity.minecart.CommandMinecart;
/*      */ import org.bukkit.entity.minecart.ExplosiveMinecart;
/*      */ import org.bukkit.entity.minecart.HopperMinecart;
/*      */ import org.bukkit.entity.minecart.PoweredMinecart;
/*      */ import org.bukkit.entity.minecart.StorageMinecart;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*      */ import org.bukkit.event.world.TimeSkipEvent;
/*      */ import org.bukkit.generator.BlockPopulator;
/*      */ import org.bukkit.generator.ChunkGenerator;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.material.MaterialData;
/*      */ import org.bukkit.metadata.MetadataValue;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ import org.bukkit.potion.PotionData;
/*      */ import org.bukkit.potion.PotionType;
/*      */ import org.bukkit.util.BoundingBox;
/*      */ import org.bukkit.util.Consumer;
/*      */ import org.bukkit.util.RayTraceResult;
/*      */ import org.bukkit.util.Vector;
/*      */ import org.spigotmc.AsyncCatcher;
/*      */ 
/*      */ public class CraftWorld implements World {
/*      */   public static final int CUSTOM_DIMENSION_OFFSET = 10;
/*      */   private final WorldServer world;
/*  277 */   private final CraftServer server = (CraftServer)Bukkit.getServer(); private WorldBorder worldBorder; private World.Environment environment;
/*      */   private final ChunkGenerator generator;
/*  279 */   private final List<BlockPopulator> populators = new ArrayList<>();
/*  280 */   private final BlockMetadataStore blockMetadata = new BlockMetadataStore(this);
/*  281 */   private int monsterSpawn = -1;
/*  282 */   private int animalSpawn = -1;
/*  283 */   private int waterAnimalSpawn = -1;
/*  284 */   private int waterAmbientSpawn = -1;
/*  285 */   private int ambientSpawn = -1;
/*      */ 
/*      */   
/*      */   public int getEntityCount() {
/*  289 */     int ret = 0;
/*  290 */     for (ObjectIterator<Entity> objectIterator = this.world.entitiesById.values().iterator(); objectIterator.hasNext(); ) { Entity entity = objectIterator.next();
/*  291 */       if (entity.isChunkLoaded()) {
/*  292 */         ret++;
/*      */       } }
/*      */     
/*  295 */     return ret;
/*      */   }
/*      */   public int getTileEntityCount() {
/*  298 */     return ((Integer)MCUtil.ensureMain(() -> { Long2ObjectLinkedOpenHashMap<PlayerChunk> chunks = (this.world.getChunkProvider()).playerChunkMap.visibleChunks; int size = 0; ObjectIterator<PlayerChunk> objectIterator = chunks.values().iterator(); while (objectIterator.hasNext()) { PlayerChunk playerchunk = objectIterator.next(); Chunk chunk = playerchunk.getChunk(); if (chunk == null) continue;  size += chunk.tileEntities.size(); }  return Integer.valueOf(size); })).intValue();
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
/*      */   public int getTickableTileEntityCount() {
/*  313 */     return this.world.tileEntityListTick.size();
/*      */   }
/*      */   public int getChunkCount() {
/*  316 */     return ((Integer)MCUtil.ensureMain(() -> { int ret = 0; ObjectIterator<PlayerChunk> objectIterator = (this.world.getChunkProvider()).playerChunkMap.visibleChunks.values().iterator(); while (objectIterator.hasNext()) { PlayerChunk chunkHolder = objectIterator.next(); if (chunkHolder.getChunk() != null) ret++;  }  return Integer.valueOf(ret); })).intValue();
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
/*      */   public int getPlayerCount() {
/*  328 */     return this.world.players.size();
/*      */   }
/*      */ 
/*      */   
/*      */   public MoonPhase getMoonPhase() {
/*  333 */     return MoonPhase.getPhase(getFullTime() / 24000L);
/*      */   }
/*      */ 
/*      */   
/*  337 */   private static final Random rand = new Random();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Map<String, GameRules.GameRuleKey<?>> gamerules;
/*      */ 
/*      */ 
/*      */   
/*      */   private static Map<String, GameRules.GameRuleDefinition<?>> gameruleDefinitions;
/*      */ 
/*      */ 
/*      */   
/*      */   private final World.Spigot spigot;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Block getBlockAt(int x, int y, int z) {
/*  356 */     return (Block)CraftBlock.at((GeneratorAccess)this.world, new BlockPosition(x, y, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHighestBlockYAt(int x, int z) {
/*  361 */     return getHighestBlockYAt(x, z, HeightMap.MOTION_BLOCKING);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHighestBlockYAt(int x, int z, HeightmapType heightmap) throws UnsupportedOperationException {
/*  367 */     getChunkAt(x >> 4, z >> 4);
/*      */     
/*  369 */     switch (heightmap) {
/*      */       case BIG_TREE:
/*  371 */         throw new UnsupportedOperationException();
/*      */       
/*      */       case BIRCH:
/*  374 */         return this.world.getHighestBlockY(HeightMap.Type.WORLD_SURFACE, x, z);
/*      */       case REDWOOD:
/*  376 */         return this.world.getHighestBlockY(HeightMap.Type.OCEAN_FLOOR, x, z);
/*      */       case TALL_REDWOOD:
/*  378 */         return this.world.getHighestBlockY(HeightMap.Type.MOTION_BLOCKING, x, z);
/*      */       case JUNGLE:
/*  380 */         return this.world.getHighestBlockY(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, x, z);
/*      */     } 
/*  382 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Location getSpawnLocation() {
/*  389 */     BlockPosition spawn = this.world.getSpawn();
/*  390 */     return new Location(this, spawn.getX(), spawn.getY(), spawn.getZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setSpawnLocation(Location location) {
/*  395 */     Preconditions.checkArgument((location != null), "location");
/*      */     
/*  397 */     return equals(location.getWorld()) ? setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ(), location.getYaw()) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setSpawnLocation(int x, int y, int z, float angle) {
/*      */     try {
/*  403 */       Location previousLocation = getSpawnLocation();
/*  404 */       this.world.setSpawn(new BlockPosition(x, y, z), angle);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  412 */       return true;
/*  413 */     } catch (Exception e) {
/*  414 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setSpawnLocation(int x, int y, int z) {
/*  420 */     return setSpawnLocation(x, y, z, 0.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public Chunk getChunkAt(int x, int z) {
/*  425 */     return (this.world.getChunkProvider().getChunkAt(x, z, true)).bukkitChunk;
/*      */   }
/*      */ 
/*      */   
/*      */   private void addTicket(int x, int z) {
/*  430 */     MCUtil.MAIN_EXECUTOR.execute(() -> this.world.getChunkProvider().addTicket(TicketType.PLUGIN, new ChunkCoordIntPair(x, z), 0, Unit.INSTANCE));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk getChunkAt(Block block) {
/*  436 */     Preconditions.checkArgument((block != null), "null block");
/*      */     
/*  438 */     return getChunkAt(block.getX() >> 4, block.getZ() >> 4);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isChunkLoaded(int x, int z) {
/*  443 */     return (this.world.getChunkProvider().getChunkAtIfLoadedImmediately(x, z) != null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isChunkGenerated(int x, int z) {
/*  449 */     if (!Bukkit.isPrimaryThread()) {
/*  450 */       return ((Boolean)CompletableFuture.<Boolean>supplyAsync(() -> Boolean.valueOf(isChunkGenerated(x, z)), 
/*      */           
/*  452 */           (Executor)(this.world.getChunkProvider()).serverThreadQueue).join()).booleanValue();
/*      */     }
/*  454 */     IChunkAccess chunk = this.world.getChunkProvider().getChunkAtImmediately(x, z);
/*  455 */     if (chunk == null) {
/*  456 */       chunk = (this.world.getChunkProvider()).playerChunkMap.getUnloadingChunk(x, z);
/*      */     }
/*  458 */     if (chunk != null) {
/*  459 */       return (chunk instanceof net.minecraft.server.v1_16_R2.ProtoChunkExtension || chunk instanceof Chunk);
/*      */     }
/*      */     try {
/*  462 */       return ((this.world.getChunkProvider()).playerChunkMap.getChunkStatusOnDisk(new ChunkCoordIntPair(x, z)) == ChunkStatus.FULL);
/*      */     }
/*  464 */     catch (IOException ex) {
/*  465 */       throw new RuntimeException(ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk[] getLoadedChunks() {
/*  472 */     if (Thread.currentThread() != (this.world.getMinecraftWorld()).serverThread) {
/*  473 */       synchronized ((this.world.getChunkProvider()).playerChunkMap.visibleChunks) {
/*  474 */         Long2ObjectLinkedOpenHashMap<PlayerChunk> long2ObjectLinkedOpenHashMap = (this.world.getChunkProvider()).playerChunkMap.visibleChunks;
/*  475 */         return (Chunk[])long2ObjectLinkedOpenHashMap.values().stream().map(PlayerChunk::getFullChunk).filter(Objects::nonNull).map(Chunk::getBukkitChunk).toArray(x$0 -> new Chunk[x$0]);
/*      */       } 
/*      */     }
/*      */     
/*  479 */     Long2ObjectLinkedOpenHashMap<PlayerChunk> chunks = (this.world.getChunkProvider()).playerChunkMap.visibleChunks;
/*  480 */     return (Chunk[])chunks.values().stream().map(PlayerChunk::getFullChunk).filter(Objects::nonNull).map(Chunk::getBukkitChunk).toArray(x$0 -> new Chunk[x$0]);
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadChunk(int x, int z) {
/*  485 */     loadChunk(x, z, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean unloadChunk(Chunk chunk) {
/*  490 */     return unloadChunk(chunk.getX(), chunk.getZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean unloadChunk(int x, int z) {
/*  495 */     return unloadChunk(x, z, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean unloadChunk(int x, int z, boolean save) {
/*  500 */     return unloadChunk0(x, z, save);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean unloadChunkRequest(int x, int z) {
/*  505 */     AsyncCatcher.catchOp("chunk unload");
/*  506 */     if (isChunkLoaded(x, z)) {
/*  507 */       this.world.getChunkProvider().removeTicket(TicketType.PLUGIN, new ChunkCoordIntPair(x, z), 0, Unit.INSTANCE);
/*  508 */       (this.world.getChunkProvider()).playerChunkMap.chunkDistanceManager.removeTickets(ChunkCoordIntPair.pair(x, z), TicketType.DELAYED_UNLOAD);
/*      */     } 
/*      */     
/*  511 */     return true;
/*      */   }
/*      */   
/*      */   private boolean unloadChunk0(int x, int z, boolean save) {
/*  515 */     AsyncCatcher.catchOp("chunk unload");
/*  516 */     if (!isChunkLoaded(x, z)) {
/*  517 */       return true;
/*      */     }
/*  519 */     Chunk chunk = this.world.getChunkAt(x, z);
/*      */     
/*  521 */     chunk.mustNotSave = !save;
/*  522 */     unloadChunkRequest(x, z);
/*      */     
/*  524 */     this.world.getChunkProvider().purgeUnload();
/*  525 */     return !isChunkLoaded(x, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean regenerateChunk(int x, int z) {
/*  530 */     AsyncCatcher.catchOp("chunk regenerate");
/*  531 */     throw new UnsupportedOperationException("Not supported in this Minecraft version! Unless you can fix it, this is not a bug :)");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean refreshChunk(int x, int z) {
/*  556 */     if (!isChunkLoaded(x, z)) {
/*  557 */       return false;
/*      */     }
/*      */     
/*  560 */     int px = x << 4;
/*  561 */     int pz = z << 4;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  566 */     int height = getMaxHeight() / 16;
/*  567 */     for (int idx = 0; idx < 64; idx++) {
/*  568 */       this.world.notify(new BlockPosition(px + idx / height, idx % height * 16, pz), Blocks.AIR.getBlockData(), Blocks.STONE.getBlockData(), 3);
/*      */     }
/*  570 */     this.world.notify(new BlockPosition(px + 15, height * 16 - 1, pz + 15), Blocks.AIR.getBlockData(), Blocks.STONE.getBlockData(), 3);
/*      */     
/*  572 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isChunkInUse(int x, int z) {
/*  577 */     return isChunkLoaded(x, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean loadChunk(int x, int z, boolean generate) {
/*  582 */     AsyncCatcher.catchOp("chunk load");
/*      */     
/*  584 */     ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(x, z);
/*  585 */     Chunk chunk = this.world.getChunkProvider().getChunkAtIfLoadedImmediately(x, z);
/*  586 */     if (chunk != null) return true;
/*      */     
/*  588 */     if (!generate) {
/*      */       IChunkAccess iChunkAccess;
/*      */       
/*  591 */       if (chunk == null) {
/*  592 */         iChunkAccess = (this.world.getChunkProvider()).playerChunkMap.getUnloadingChunk(x, z);
/*      */       }
/*  594 */       if (iChunkAccess != null) {
/*  595 */         if (!(iChunkAccess instanceof net.minecraft.server.v1_16_R2.ProtoChunkExtension) && !(iChunkAccess instanceof Chunk)) {
/*  596 */           return false;
/*      */         }
/*  598 */         this.world.getChunkProvider().addTicket(TicketType.PLUGIN, chunkPos, 0, Unit.INSTANCE);
/*  599 */         this.world.getChunkAt(x, z);
/*  600 */         return true;
/*      */       } 
/*      */       
/*  603 */       ChunkStatus status = (this.world.getChunkProvider()).playerChunkMap.getStatusOnDiskNoLoad(x, z);
/*      */ 
/*      */       
/*  606 */       if (status == ChunkStatus.EMPTY)
/*      */       {
/*  608 */         return false;
/*      */       }
/*      */       
/*  611 */       if (status == null) {
/*  612 */         IChunkAccess iChunkAccess1 = this.world.getChunkProvider().getChunkAt(x, z, ChunkStatus.EMPTY, true);
/*  613 */         if (!(iChunkAccess1 instanceof net.minecraft.server.v1_16_R2.ProtoChunkExtension) && !(iChunkAccess1 instanceof Chunk)) {
/*  614 */           return false;
/*      */         }
/*  616 */       } else if (status != ChunkStatus.FULL) {
/*  617 */         return false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  625 */     this.world.getChunkProvider().addTicket(TicketType.PLUGIN, chunkPos, 0, Unit.INSTANCE);
/*  626 */     this.world.getChunkProvider().getChunkAt(x, z, ChunkStatus.FULL, true);
/*  627 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isChunkLoaded(Chunk chunk) {
/*  633 */     Preconditions.checkArgument((chunk != null), "null chunk");
/*      */     
/*  635 */     return isChunkLoaded(chunk.getX(), chunk.getZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadChunk(Chunk chunk) {
/*  640 */     Preconditions.checkArgument((chunk != null), "null chunk");
/*      */     
/*  642 */     loadChunk(chunk.getX(), chunk.getZ());
/*  643 */     (((CraftChunk)getChunkAt(chunk.getX(), chunk.getZ())).getHandle()).bukkitChunk = chunk;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addPluginChunkTicket(int x, int z, Plugin plugin) {
/*  648 */     Preconditions.checkArgument((plugin != null), "null plugin");
/*  649 */     Preconditions.checkArgument(plugin.isEnabled(), "plugin is not enabled");
/*      */     
/*  651 */     PlayerChunkMap.a a = (this.world.getChunkProvider()).playerChunkMap.chunkDistanceManager;
/*      */     
/*  653 */     if (a.addTicketAtLevel(TicketType.PLUGIN_TICKET, new ChunkCoordIntPair(x, z), 31, plugin)) {
/*  654 */       getChunkAt(x, z);
/*  655 */       return true;
/*      */     } 
/*      */     
/*  658 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removePluginChunkTicket(int x, int z, Plugin plugin) {
/*  663 */     Preconditions.checkNotNull(plugin, "null plugin");
/*      */     
/*  665 */     PlayerChunkMap.a a = (this.world.getChunkProvider()).playerChunkMap.chunkDistanceManager;
/*  666 */     return a.removeTicketAtLevel(TicketType.PLUGIN_TICKET, new ChunkCoordIntPair(x, z), 31, plugin);
/*      */   }
/*      */ 
/*      */   
/*      */   public void removePluginChunkTickets(Plugin plugin) {
/*  671 */     Preconditions.checkNotNull(plugin, "null plugin");
/*      */     
/*  673 */     PlayerChunkMap.a a = (this.world.getChunkProvider()).playerChunkMap.chunkDistanceManager;
/*  674 */     a.removeAllTicketsFor(TicketType.PLUGIN_TICKET, 31, plugin);
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<Plugin> getPluginChunkTickets(int x, int z) {
/*  679 */     PlayerChunkMap.a a = (this.world.getChunkProvider()).playerChunkMap.chunkDistanceManager;
/*  680 */     ArraySetSorted<Ticket<?>> tickets = (ArraySetSorted<Ticket<?>>)((ChunkMapDistance)a).tickets.get(ChunkCoordIntPair.pair(x, z));
/*      */     
/*  682 */     if (tickets == null) {
/*  683 */       return Collections.emptyList();
/*      */     }
/*      */     
/*  686 */     ImmutableList.Builder<Plugin> ret = ImmutableList.builder();
/*  687 */     for (Ticket<?> ticket : tickets) {
/*  688 */       if (ticket.getTicketType() == TicketType.PLUGIN_TICKET) {
/*  689 */         ret.add(ticket.identifier);
/*      */       }
/*      */     } 
/*      */     
/*  693 */     return (Collection<Plugin>)ret.build();
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<Plugin, Collection<Chunk>> getPluginChunkTickets() {
/*  698 */     Map<Plugin, ImmutableList.Builder<Chunk>> ret = new HashMap<>();
/*  699 */     PlayerChunkMap.a a = (this.world.getChunkProvider()).playerChunkMap.chunkDistanceManager;
/*      */     
/*  701 */     for (ObjectIterator<Long2ObjectMap.Entry<ArraySetSorted<Ticket<?>>>> objectIterator = ((ChunkMapDistance)a).tickets.long2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Long2ObjectMap.Entry<ArraySetSorted<Ticket<?>>> chunkTickets = objectIterator.next();
/*  702 */       long chunkKey = chunkTickets.getLongKey();
/*  703 */       ArraySetSorted<Ticket<?>> tickets = (ArraySetSorted<Ticket<?>>)chunkTickets.getValue();
/*      */       
/*  705 */       Chunk chunk = null;
/*  706 */       for (Ticket<?> ticket : tickets) {
/*  707 */         if (ticket.getTicketType() != TicketType.PLUGIN_TICKET) {
/*      */           continue;
/*      */         }
/*      */         
/*  711 */         if (chunk == null) {
/*  712 */           chunk = getChunkAt(ChunkCoordIntPair.getX(chunkKey), ChunkCoordIntPair.getZ(chunkKey));
/*      */         }
/*      */         
/*  715 */         ((ImmutableList.Builder)ret.computeIfAbsent((Plugin)ticket.identifier, key -> ImmutableList.builder())).add(chunk);
/*      */       }  }
/*      */ 
/*      */     
/*  719 */     return (Map<Plugin, Collection<Chunk>>)ret.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> ((ImmutableList.Builder)entry.getValue()).build()));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isChunkForceLoaded(int x, int z) {
/*  724 */     return getHandle().getForceLoadedChunks().contains(ChunkCoordIntPair.pair(x, z));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setChunkForceLoaded(int x, int z, boolean forced) {
/*  729 */     getHandle().setForceLoaded(x, z, forced);
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<Chunk> getForceLoadedChunks() {
/*  734 */     Set<Chunk> chunks = new HashSet<>();
/*      */     
/*  736 */     for (LongIterator<Long> longIterator = getHandle().getForceLoadedChunks().iterator(); longIterator.hasNext(); ) { long coord = ((Long)longIterator.next()).longValue();
/*  737 */       chunks.add(getChunkAt(ChunkCoordIntPair.getX(coord), ChunkCoordIntPair.getZ(coord))); }
/*      */ 
/*      */     
/*  740 */     return Collections.unmodifiableCollection(chunks);
/*      */   }
/*      */   
/*      */   public WorldServer getHandle() {
/*  744 */     return this.world;
/*      */   }
/*      */ 
/*      */   
/*      */   public Item dropItem(Location loc, ItemStack item) {
/*  749 */     Validate.notNull(item, "Cannot drop a Null item.");
/*  750 */     EntityItem entity = new EntityItem((World)this.world, loc.getX(), loc.getY(), loc.getZ(), CraftItemStack.asNMSCopy(item));
/*  751 */     entity.pickupDelay = 10;
/*  752 */     this.world.addEntity((Entity)entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
/*  753 */     return (Item)entity.getBukkitEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   public Item dropItemNaturally(Location loc, ItemStack item) {
/*  758 */     double xs = (this.world.random.nextFloat() * 0.5F) + 0.25D;
/*  759 */     double ys = (this.world.random.nextFloat() * 0.5F) + 0.25D;
/*  760 */     double zs = (this.world.random.nextFloat() * 0.5F) + 0.25D;
/*  761 */     loc = loc.clone();
/*  762 */     loc.setX(loc.getX() + xs);
/*  763 */     loc.setY(loc.getY() + ys);
/*  764 */     loc.setZ(loc.getZ() + zs);
/*  765 */     return dropItem(loc, item);
/*      */   }
/*      */ 
/*      */   
/*      */   public Arrow spawnArrow(Location loc, Vector velocity, float speed, float spread) {
/*  770 */     return spawnArrow(loc, velocity, speed, spread, Arrow.class);
/*      */   }
/*      */   
/*      */   public <T extends AbstractArrow> T spawnArrow(Location loc, Vector velocity, float speed, float spread, Class<T> clazz) {
/*      */     EntityArrow arrow;
/*  775 */     Validate.notNull(loc, "Can not spawn arrow with a null location");
/*  776 */     Validate.notNull(velocity, "Can not spawn arrow with a null velocity");
/*  777 */     Validate.notNull(clazz, "Can not spawn an arrow with no class");
/*      */ 
/*      */     
/*  780 */     if (TippedArrow.class.isAssignableFrom(clazz)) {
/*  781 */       arrow = (EntityArrow)EntityTypes.ARROW.a((World)this.world);
/*  782 */       ((EntityTippedArrow)arrow).setType(CraftPotionUtil.fromBukkit(new PotionData(PotionType.WATER, false, false)));
/*  783 */     } else if (SpectralArrow.class.isAssignableFrom(clazz)) {
/*  784 */       arrow = (EntityArrow)EntityTypes.SPECTRAL_ARROW.a((World)this.world);
/*  785 */     } else if (Trident.class.isAssignableFrom(clazz)) {
/*  786 */       arrow = (EntityArrow)EntityTypes.TRIDENT.a((World)this.world);
/*      */     } else {
/*  788 */       arrow = (EntityArrow)EntityTypes.ARROW.a((World)this.world);
/*      */     } 
/*      */     
/*  791 */     arrow.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
/*  792 */     arrow.shoot(velocity.getX(), velocity.getY(), velocity.getZ(), speed, spread);
/*  793 */     this.world.addEntity((Entity)arrow);
/*  794 */     return (T)arrow.getBukkitEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity spawnEntity(Location loc, EntityType entityType) {
/*  799 */     return spawn(loc, entityType.getEntityClass());
/*      */   }
/*      */ 
/*      */   
/*      */   public LightningStrike strikeLightning(Location loc) {
/*  804 */     EntityLightning lightning = (EntityLightning)EntityTypes.LIGHTNING_BOLT.a((World)this.world);
/*  805 */     lightning.teleportAndSync(loc.getX(), loc.getY(), loc.getZ());
/*  806 */     this.world.strikeLightning((Entity)lightning);
/*  807 */     return (LightningStrike)lightning.getBukkitEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   public LightningStrike strikeLightningEffect(Location loc) {
/*  812 */     EntityLightning lightning = (EntityLightning)EntityTypes.LIGHTNING_BOLT.a((World)this.world);
/*  813 */     lightning.teleportAndSync(loc.getX(), loc.getY(), loc.getZ());
/*  814 */     lightning.setEffect(true);
/*  815 */     this.world.strikeLightning((Entity)lightning);
/*  816 */     return (LightningStrike)lightning.getBukkitEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean generateTree(Location loc, TreeType type) {
/*  821 */     BlockPosition pos = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
/*      */ 
/*      */     
/*  824 */     switch (type)
/*      */     { case BIG_TREE:
/*  826 */         gen = BiomeDecoratorGroups.FANCY_OAK;
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
/*      */         
/*  885 */         return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case BIRCH: gen = BiomeDecoratorGroups.BIRCH; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case REDWOOD: gen = BiomeDecoratorGroups.SPRUCE; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case TALL_REDWOOD: gen = BiomeDecoratorGroups.PINE; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case JUNGLE: gen = BiomeDecoratorGroups.MEGA_JUNGLE_TREE; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case SMALL_JUNGLE: gen = BiomeDecoratorGroups.JUNGLE_TREE_NO_VINE; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case COCOA_TREE: gen = BiomeDecoratorGroups.JUNGLE_TREE; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case JUNGLE_BUSH: gen = BiomeDecoratorGroups.JUNGLE_BUSH; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case RED_MUSHROOM: gen = BiomeDecoratorGroups.HUGE_RED_MUSHROOM; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case BROWN_MUSHROOM: gen = BiomeDecoratorGroups.HUGE_BROWN_MUSHROOM; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case SWAMP: gen = BiomeDecoratorGroups.SWAMP_TREE; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case ACACIA: gen = BiomeDecoratorGroups.ACACIA; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case DARK_OAK: gen = BiomeDecoratorGroups.DARK_OAK; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case MEGA_REDWOOD: gen = BiomeDecoratorGroups.MEGA_PINE; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case TALL_BIRCH: gen = BiomeDecoratorGroups.SUPER_BIRCH_BEES_0002; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case CHORUS_PLANT: (BlockChorusFlower)Blocks.CHORUS_FLOWER; BlockChorusFlower.a((GeneratorAccess)this.world, pos, rand, 8); return true;case CRIMSON_FUNGUS: gen = BiomeDecoratorGroups.CRIMSON_FUNGI; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);case WARPED_FUNGUS: gen = BiomeDecoratorGroups.WARPED_FUNGI; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f); }  WorldGenFeatureConfigured gen = BiomeDecoratorGroups.OAK; return gen.e.generate((GeneratorAccessSeed)this.world, this.world.getChunkProvider().getChunkGenerator(), rand, pos, gen.f);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean generateTree(Location loc, TreeType type, BlockChangeDelegate delegate) {
/*  890 */     this.world.captureTreeGeneration = true;
/*  891 */     this.world.captureBlockStates = true;
/*  892 */     boolean grownTree = generateTree(loc, type);
/*  893 */     this.world.captureBlockStates = false;
/*  894 */     this.world.captureTreeGeneration = false;
/*  895 */     if (grownTree) {
/*  896 */       for (BlockState blockstate : this.world.capturedBlockStates.values()) {
/*  897 */         BlockPosition position = ((CraftBlockState)blockstate).getPosition();
/*  898 */         IBlockData oldBlock = this.world.getType(position);
/*  899 */         int flag = ((CraftBlockState)blockstate).getFlag();
/*  900 */         delegate.setBlockData(blockstate.getX(), blockstate.getY(), blockstate.getZ(), blockstate.getBlockData());
/*  901 */         IBlockData newBlock = this.world.getType(position);
/*  902 */         this.world.notifyAndUpdatePhysics(position, null, oldBlock, newBlock, newBlock, flag, 512);
/*      */       } 
/*  904 */       this.world.capturedBlockStates.clear();
/*  905 */       return true;
/*      */     } 
/*  907 */     this.world.capturedBlockStates.clear();
/*  908 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/*  914 */     return this.world.worldDataServer.getName();
/*      */   }
/*      */ 
/*      */   
/*      */   public UUID getUID() {
/*  919 */     return this.world.uuid;
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/*  924 */     return "CraftWorld{name=" + getName() + '}';
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTime() {
/*  929 */     long time = getFullTime() % 24000L;
/*  930 */     if (time < 0L) time += 24000L; 
/*  931 */     return time;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTime(long time) {
/*  936 */     long margin = (time - getFullTime()) % 24000L;
/*  937 */     if (margin < 0L) margin += 24000L; 
/*  938 */     setFullTime(getFullTime() + margin);
/*      */   }
/*      */ 
/*      */   
/*      */   public long getFullTime() {
/*  943 */     return this.world.getDayTime();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFullTime(long time) {
/*  949 */     TimeSkipEvent event = new TimeSkipEvent(this, TimeSkipEvent.SkipReason.CUSTOM, time - this.world.getDayTime());
/*  950 */     this.server.getPluginManager().callEvent((Event)event);
/*  951 */     if (event.isCancelled()) {
/*      */       return;
/*      */     }
/*      */     
/*  955 */     this.world.setDayTime(this.world.getDayTime() + event.getSkipAmount());
/*      */ 
/*      */     
/*  958 */     for (Player p : getPlayers()) {
/*  959 */       CraftPlayer cp = (CraftPlayer)p;
/*  960 */       if ((cp.getHandle()).playerConnection == null)
/*      */         continue; 
/*  962 */       (cp.getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutUpdateTime((cp.getHandle()).world.getTime(), cp.getHandle().getPlayerTime(), (cp.getHandle()).world.getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDayTime() {
/*  969 */     return getHandle().isDay();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean createExplosion(double x, double y, double z, float power) {
/*  975 */     return createExplosion(x, y, z, power, false, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean createExplosion(double x, double y, double z, float power, boolean setFire) {
/*  980 */     return createExplosion(x, y, z, power, setFire, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks) {
/*  985 */     return createExplosion(x, y, z, power, setFire, breakBlocks, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks, Entity source) {
/*  990 */     return !(this.world.createExplosion((source == null) ? null : ((CraftEntity)source).getHandle(), x, y, z, power, setFire, breakBlocks ? Explosion.Effect.BREAK : Explosion.Effect.NONE)).wasCanceled;
/*      */   }
/*      */   
/*      */   public boolean createExplosion(Entity source, Location loc, float power, boolean setFire, boolean breakBlocks) {
/*  994 */     return !(this.world.createExplosion((source != null) ? ((CraftEntity)source).getHandle() : null, loc.getX(), loc.getY(), loc.getZ(), power, setFire, breakBlocks ? Explosion.Effect.BREAK : Explosion.Effect.NONE)).wasCanceled;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean createExplosion(Location loc, float power) {
/* 1000 */     return createExplosion(loc, power, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean createExplosion(Location loc, float power, boolean setFire) {
/* 1005 */     return createExplosion(loc, power, setFire, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean createExplosion(Location loc, float power, boolean setFire, boolean breakBlocks) {
/* 1010 */     return createExplosion(loc, power, setFire, breakBlocks, (Entity)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean createExplosion(Location loc, float power, boolean setFire, boolean breakBlocks, Entity source) {
/* 1015 */     Preconditions.checkArgument((loc != null), "Location is null");
/* 1016 */     Preconditions.checkArgument(equals(loc.getWorld()), "Location not in world");
/*      */     
/* 1018 */     return createExplosion(loc.getX(), loc.getY(), loc.getZ(), power, setFire, breakBlocks, source);
/*      */   }
/*      */ 
/*      */   
/*      */   public World.Environment getEnvironment() {
/* 1023 */     return this.environment;
/*      */   }
/*      */ 
/*      */   
/*      */   public Block getBlockAt(Location location) {
/* 1028 */     return getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHighestBlockYAt(Location location) {
/* 1033 */     return getHighestBlockYAt(location.getBlockX(), location.getBlockZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public Chunk getChunkAt(Location location) {
/* 1038 */     return getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChunkGenerator getGenerator() {
/* 1043 */     return this.generator;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<BlockPopulator> getPopulators() {
/* 1048 */     return this.populators;
/*      */   }
/*      */ 
/*      */   
/*      */   public Block getHighestBlockAt(int x, int z) {
/* 1053 */     return getBlockAt(x, getHighestBlockYAt(x, z), z);
/*      */   }
/*      */ 
/*      */   
/*      */   public Block getHighestBlockAt(Location location) {
/* 1058 */     return getHighestBlockAt(location.getBlockX(), location.getBlockZ());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHighestBlockYAt(int x, int z, HeightMap heightMap) {
/* 1064 */     return this.world.getChunkAt(x >> 4, z >> 4).getHighestBlock(CraftHeightMap.toNMS(heightMap), x, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHighestBlockYAt(Location location, HeightMap heightMap) {
/* 1069 */     return getHighestBlockYAt(location.getBlockX(), location.getBlockZ(), heightMap);
/*      */   }
/*      */ 
/*      */   
/*      */   public Block getHighestBlockAt(int x, int z, HeightMap heightMap) {
/* 1074 */     return getBlockAt(x, getHighestBlockYAt(x, z, heightMap), z);
/*      */   }
/*      */ 
/*      */   
/*      */   public Block getHighestBlockAt(Location location, HeightMap heightMap) {
/* 1079 */     return getHighestBlockAt(location.getBlockX(), location.getBlockZ(), heightMap);
/*      */   }
/*      */ 
/*      */   
/*      */   public Biome getBiome(int x, int z) {
/* 1084 */     return getBiome(x, 0, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public Biome getBiome(int x, int y, int z) {
/* 1089 */     return CraftBlock.biomeBaseToBiome((IRegistry)getHandle().r().b(IRegistry.ay), this.world.getBiome(x >> 2, y >> 2, z >> 2));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBiome(int x, int z, Biome bio) {
/* 1094 */     for (int y = 0; y < getMaxHeight(); y++) {
/* 1095 */       setBiome(x, y, z, bio);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBiome(int x, int y, int z, Biome bio) {
/* 1101 */     BiomeBase bb = CraftBlock.biomeToBiomeBase((IRegistry)getHandle().r().b(IRegistry.ay), bio);
/* 1102 */     BlockPosition pos = new BlockPosition(x, 0, z);
/* 1103 */     if (this.world.isLoaded(pos)) {
/* 1104 */       Chunk chunk = this.world.getChunkAtWorldCoords(pos);
/*      */       
/* 1106 */       if (chunk != null) {
/* 1107 */         chunk.getBiomeIndex().setBiome(x >> 2, y >> 2, z >> 2, bb);
/*      */         
/* 1109 */         chunk.markDirty();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public double getTemperature(int x, int z) {
/* 1116 */     return getTemperature(x, 0, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public double getTemperature(int x, int y, int z) {
/* 1121 */     BlockPosition pos = new BlockPosition(x, y, z);
/* 1122 */     return this.world.getBiome(x >> 2, y >> 2, z >> 2).getAdjustedTemperature(pos);
/*      */   }
/*      */ 
/*      */   
/*      */   public double getHumidity(int x, int z) {
/* 1127 */     return getHumidity(x, 0, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public double getHumidity(int x, int y, int z) {
/* 1132 */     return this.world.getBiome(x >> 2, y >> 2, z >> 2).getHumidity();
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Entity> getEntities() {
/* 1137 */     List<Entity> list = new ArrayList<>();
/*      */     
/* 1139 */     for (ObjectIterator objectIterator = this.world.entitiesById.values().iterator(); objectIterator.hasNext(); ) { Object o = objectIterator.next();
/* 1140 */       if (o instanceof Entity) {
/* 1141 */         Entity mcEnt = (Entity)o;
/* 1142 */         if (mcEnt.shouldBeRemoved)
/* 1143 */           continue;  CraftEntity craftEntity = mcEnt.getBukkitEntity();
/*      */ 
/*      */         
/* 1146 */         if (craftEntity != null && craftEntity.isValid()) {
/* 1147 */           list.add(craftEntity);
/*      */         }
/*      */       }  }
/*      */ 
/*      */     
/* 1152 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<LivingEntity> getLivingEntities() {
/* 1157 */     List<LivingEntity> list = new ArrayList<>();
/*      */     
/* 1159 */     for (ObjectIterator objectIterator = this.world.entitiesById.values().iterator(); objectIterator.hasNext(); ) { Object o = objectIterator.next();
/* 1160 */       if (o instanceof Entity) {
/* 1161 */         Entity mcEnt = (Entity)o;
/* 1162 */         if (mcEnt.shouldBeRemoved)
/* 1163 */           continue;  CraftEntity craftEntity = mcEnt.getBukkitEntity();
/*      */ 
/*      */         
/* 1166 */         if (craftEntity != null && craftEntity instanceof LivingEntity && craftEntity.isValid()) {
/* 1167 */           list.add((LivingEntity)craftEntity);
/*      */         }
/*      */       }  }
/*      */ 
/*      */     
/* 1172 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public <T extends Entity> Collection<T> getEntitiesByClass(Class<T>... classes) {
/* 1179 */     return (Collection)getEntitiesByClasses((Class<?>[])classes);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public <T extends Entity> Collection<T> getEntitiesByClass(Class<T> clazz) {
/* 1185 */     Collection<T> list = new ArrayList<>();
/*      */     
/* 1187 */     for (ObjectIterator objectIterator = this.world.entitiesById.values().iterator(); objectIterator.hasNext(); ) { Object entity = objectIterator.next();
/* 1188 */       if (!(entity instanceof Entity) || 
/* 1189 */         ((Entity)entity).shouldBeRemoved)
/* 1190 */         continue;  CraftEntity craftEntity = ((Entity)entity).getBukkitEntity();
/*      */       
/* 1192 */       if (craftEntity == null) {
/*      */         continue;
/*      */       }
/*      */       
/* 1196 */       Class<?> bukkitClass = craftEntity.getClass();
/*      */       
/* 1198 */       if (clazz.isAssignableFrom(bukkitClass) && craftEntity.isValid()) {
/* 1199 */         list.add((T)craftEntity);
/*      */       } }
/*      */ 
/*      */ 
/*      */     
/* 1204 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<Entity> getEntitiesByClasses(Class<?>... classes) {
/* 1209 */     Collection<Entity> list = new ArrayList<>();
/*      */     
/* 1211 */     for (ObjectIterator objectIterator = this.world.entitiesById.values().iterator(); objectIterator.hasNext(); ) { Object entity = objectIterator.next();
/* 1212 */       if (!(entity instanceof Entity) || 
/* 1213 */         ((Entity)entity).shouldBeRemoved)
/* 1214 */         continue;  CraftEntity craftEntity = ((Entity)entity).getBukkitEntity();
/*      */       
/* 1216 */       if (craftEntity == null) {
/*      */         continue;
/*      */       }
/*      */       
/* 1220 */       Class<?> bukkitClass = craftEntity.getClass();
/*      */       
/* 1222 */       for (Class<?> clazz : classes) {
/* 1223 */         if (clazz.isAssignableFrom(bukkitClass)) {
/* 1224 */           if (craftEntity.isValid()) {
/* 1225 */             list.add(craftEntity);
/*      */           }
/*      */           
/*      */           break;
/*      */         } 
/*      */       }  }
/*      */ 
/*      */     
/* 1233 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<Entity> getNearbyEntities(Location location, double x, double y, double z) {
/* 1238 */     return getNearbyEntities(location, x, y, z, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<Entity> getNearbyEntities(Location location, double x, double y, double z, Predicate<Entity> filter) {
/* 1243 */     Validate.notNull(location, "Location is null!");
/* 1244 */     Validate.isTrue(equals(location.getWorld()), "Location is from different world!");
/*      */     
/* 1246 */     BoundingBox aabb = BoundingBox.of(location, x, y, z);
/* 1247 */     return getNearbyEntities(aabb, filter);
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<Entity> getNearbyEntities(BoundingBox boundingBox) {
/* 1252 */     return getNearbyEntities(boundingBox, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<Entity> getNearbyEntities(BoundingBox boundingBox, Predicate<Entity> filter) {
/* 1257 */     AsyncCatcher.catchOp("getNearbyEntities");
/* 1258 */     Validate.notNull(boundingBox, "Bounding box is null!");
/*      */     
/* 1260 */     AxisAlignedBB bb = new AxisAlignedBB(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getMinZ(), boundingBox.getMaxX(), boundingBox.getMaxY(), boundingBox.getMaxZ());
/* 1261 */     List<Entity> entityList = getHandle().getEntities((Entity)null, bb, null);
/* 1262 */     List<Entity> bukkitEntityList = new ArrayList<>(entityList.size());
/*      */     
/* 1264 */     for (Entity entity : entityList) {
/* 1265 */       CraftEntity craftEntity = entity.getBukkitEntity();
/* 1266 */       if (filter == null || filter.test(craftEntity)) {
/* 1267 */         bukkitEntityList.add(craftEntity);
/*      */       }
/*      */     } 
/*      */     
/* 1271 */     return bukkitEntityList;
/*      */   }
/*      */ 
/*      */   
/*      */   public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance) {
/* 1276 */     return rayTraceEntities(start, direction, maxDistance, (Predicate<Entity>)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance, double raySize) {
/* 1281 */     return rayTraceEntities(start, direction, maxDistance, raySize, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance, Predicate<Entity> filter) {
/* 1286 */     return rayTraceEntities(start, direction, maxDistance, 0.0D, filter);
/*      */   }
/*      */ 
/*      */   
/*      */   public RayTraceResult rayTraceEntities(Location start, Vector direction, double maxDistance, double raySize, Predicate<Entity> filter) {
/* 1291 */     Validate.notNull(start, "Start location is null!");
/* 1292 */     Validate.isTrue(equals(start.getWorld()), "Start location is from different world!");
/* 1293 */     start.checkFinite();
/*      */     
/* 1295 */     Validate.notNull(direction, "Direction is null!");
/* 1296 */     direction.checkFinite();
/*      */     
/* 1298 */     Validate.isTrue((direction.lengthSquared() > 0.0D), "Direction's magnitude is 0!");
/*      */     
/* 1300 */     if (maxDistance < 0.0D) {
/* 1301 */       return null;
/*      */     }
/*      */     
/* 1304 */     Vector startPos = start.toVector();
/* 1305 */     Vector dir = direction.clone().normalize().multiply(maxDistance);
/* 1306 */     BoundingBox aabb = BoundingBox.of(startPos, startPos).expandDirectional(dir).expand(raySize);
/* 1307 */     Collection<Entity> entities = getNearbyEntities(aabb, filter);
/*      */     
/* 1309 */     Entity nearestHitEntity = null;
/* 1310 */     RayTraceResult nearestHitResult = null;
/* 1311 */     double nearestDistanceSq = Double.MAX_VALUE;
/*      */     
/* 1313 */     for (Entity entity : entities) {
/* 1314 */       BoundingBox boundingBox = entity.getBoundingBox().expand(raySize);
/* 1315 */       RayTraceResult hitResult = boundingBox.rayTrace(startPos, direction, maxDistance);
/*      */       
/* 1317 */       if (hitResult != null) {
/* 1318 */         double distanceSq = startPos.distanceSquared(hitResult.getHitPosition());
/*      */         
/* 1320 */         if (distanceSq < nearestDistanceSq) {
/* 1321 */           nearestHitEntity = entity;
/* 1322 */           nearestHitResult = hitResult;
/* 1323 */           nearestDistanceSq = distanceSq;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1328 */     return (nearestHitEntity == null) ? null : new RayTraceResult(nearestHitResult.getHitPosition(), nearestHitEntity, nearestHitResult.getHitBlockFace());
/*      */   }
/*      */ 
/*      */   
/*      */   public RayTraceResult rayTraceBlocks(Location start, Vector direction, double maxDistance) {
/* 1333 */     return rayTraceBlocks(start, direction, maxDistance, FluidCollisionMode.NEVER, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public RayTraceResult rayTraceBlocks(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode) {
/* 1338 */     return rayTraceBlocks(start, direction, maxDistance, fluidCollisionMode, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public RayTraceResult rayTraceBlocks(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks) {
/* 1343 */     Validate.notNull(start, "Start location is null!");
/* 1344 */     Validate.isTrue(equals(start.getWorld()), "Start location is from different world!");
/* 1345 */     start.checkFinite();
/*      */     
/* 1347 */     Validate.notNull(direction, "Direction is null!");
/* 1348 */     direction.checkFinite();
/*      */     
/* 1350 */     Validate.isTrue((direction.lengthSquared() > 0.0D), "Direction's magnitude is 0!");
/* 1351 */     Validate.notNull(fluidCollisionMode, "Fluid collision mode is null!");
/*      */     
/* 1353 */     if (maxDistance < 0.0D) {
/* 1354 */       return null;
/*      */     }
/*      */     
/* 1357 */     Vector dir = direction.clone().normalize().multiply(maxDistance);
/* 1358 */     Vec3D startPos = new Vec3D(start.getX(), start.getY(), start.getZ());
/* 1359 */     Vec3D endPos = new Vec3D(start.getX() + dir.getX(), start.getY() + dir.getY(), start.getZ() + dir.getZ());
/* 1360 */     MovingObjectPositionBlock movingObjectPositionBlock = getHandle().rayTrace(new RayTrace(startPos, endPos, ignorePassableBlocks ? RayTrace.BlockCollisionOption.COLLIDER : RayTrace.BlockCollisionOption.OUTLINE, CraftFluidCollisionMode.toNMS(fluidCollisionMode), null));
/*      */     
/* 1362 */     return CraftRayTraceResult.fromNMS(this, (MovingObjectPosition)movingObjectPositionBlock);
/*      */   }
/*      */ 
/*      */   
/*      */   public RayTraceResult rayTrace(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks, double raySize, Predicate<Entity> filter) {
/* 1367 */     RayTraceResult blockHit = rayTraceBlocks(start, direction, maxDistance, fluidCollisionMode, ignorePassableBlocks);
/* 1368 */     Vector startVec = null;
/* 1369 */     double blockHitDistance = maxDistance;
/*      */ 
/*      */     
/* 1372 */     if (blockHit != null) {
/* 1373 */       startVec = start.toVector();
/* 1374 */       blockHitDistance = startVec.distance(blockHit.getHitPosition());
/*      */     } 
/*      */     
/* 1377 */     RayTraceResult entityHit = rayTraceEntities(start, direction, blockHitDistance, raySize, filter);
/* 1378 */     if (blockHit == null) {
/* 1379 */       return entityHit;
/*      */     }
/*      */     
/* 1382 */     if (entityHit == null) {
/* 1383 */       return blockHit;
/*      */     }
/*      */ 
/*      */     
/* 1387 */     double entityHitDistanceSquared = startVec.distanceSquared(entityHit.getHitPosition());
/* 1388 */     if (entityHitDistanceSquared < blockHitDistance * blockHitDistance) {
/* 1389 */       return entityHit;
/*      */     }
/*      */     
/* 1392 */     return blockHit;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Player> getPlayers() {
/* 1397 */     List<Player> list = new ArrayList<>(this.world.getPlayers().size());
/*      */     
/* 1399 */     for (EntityHuman human : this.world.getPlayers()) {
/* 1400 */       CraftHumanEntity craftHumanEntity = human.getBukkitEntity();
/*      */       
/* 1402 */       if (craftHumanEntity != null && craftHumanEntity instanceof Player) {
/* 1403 */         list.add((Player)craftHumanEntity);
/*      */       }
/*      */     } 
/*      */     
/* 1407 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity getEntity(UUID uuid) {
/* 1413 */     Validate.notNull(uuid, "UUID cannot be null");
/* 1414 */     Entity entity = this.world.getEntity(uuid);
/* 1415 */     return (entity == null) ? null : (Entity)entity.getBukkitEntity();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void save() {
/* 1421 */     AsyncCatcher.catchOp("world save");
/* 1422 */     this.server.checkSaveState();
/* 1423 */     boolean oldSave = this.world.savingDisabled;
/*      */     
/* 1425 */     this.world.savingDisabled = false;
/* 1426 */     this.world.save(null, false, false);
/*      */     
/* 1428 */     this.world.savingDisabled = oldSave;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAutoSave() {
/* 1433 */     return !this.world.savingDisabled;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAutoSave(boolean value) {
/* 1438 */     this.world.savingDisabled = !value;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDifficulty(Difficulty difficulty) {
/* 1443 */     (getHandle()).worldDataServer.setDifficulty(EnumDifficulty.getById(difficulty.getValue()));
/*      */   }
/*      */ 
/*      */   
/*      */   public Difficulty getDifficulty() {
/* 1448 */     return Difficulty.getByValue(getHandle().getDifficulty().ordinal());
/*      */   }
/*      */   
/*      */   public BlockMetadataStore getBlockMetadata() {
/* 1452 */     return this.blockMetadata;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasStorm() {
/* 1457 */     return this.world.worldData.hasStorm();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStorm(boolean hasStorm) {
/* 1462 */     this.world.worldData.setStorm(hasStorm);
/* 1463 */     setWeatherDuration(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getWeatherDuration() {
/* 1468 */     return this.world.worldDataServer.getWeatherDuration();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWeatherDuration(int duration) {
/* 1473 */     this.world.worldDataServer.setWeatherDuration(duration);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isThundering() {
/* 1478 */     return this.world.worldData.isThundering();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setThundering(boolean thundering) {
/* 1483 */     this.world.worldDataServer.setThundering(thundering);
/* 1484 */     setThunderDuration(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getThunderDuration() {
/* 1489 */     return this.world.worldDataServer.getThunderDuration();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setThunderDuration(int duration) {
/* 1494 */     this.world.worldDataServer.setThunderDuration(duration);
/*      */   }
/*      */ 
/*      */   
/*      */   public long getSeed() {
/* 1499 */     return this.world.getSeed();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getPVP() {
/* 1504 */     return this.world.pvpMode;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPVP(boolean pvp) {
/* 1509 */     this.world.pvpMode = pvp;
/*      */   }
/*      */   
/*      */   public void playEffect(Player player, Effect effect, int data) {
/* 1513 */     playEffect(player.getLocation(), effect, data, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void playEffect(Location location, Effect effect, int data) {
/* 1518 */     playEffect(location, effect, data, 64);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> void playEffect(Location loc, Effect effect, T data) {
/* 1523 */     playEffect(loc, effect, data, 64);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> void playEffect(Location loc, Effect effect, T data, int radius) {
/* 1528 */     if (data != null) {
/* 1529 */       Validate.isTrue((effect.getData() != null && effect.getData().isAssignableFrom(data.getClass())), "Wrong kind of data for this effect!");
/*      */     } else {
/* 1531 */       Validate.isTrue((effect.getData() == null), "Wrong kind of data for this effect!");
/*      */     } 
/*      */     
/* 1534 */     int datavalue = (data == null) ? 0 : CraftEffect.<T>getDataValue(effect, data);
/* 1535 */     playEffect(loc, effect, datavalue, radius);
/*      */   }
/*      */ 
/*      */   
/*      */   public void playEffect(Location location, Effect effect, int data, int radius) {
/* 1540 */     Validate.notNull(location, "Location cannot be null");
/* 1541 */     Validate.notNull(effect, "Effect cannot be null");
/* 1542 */     Validate.notNull(location.getWorld(), "World cannot be null");
/* 1543 */     int packetData = effect.getId();
/* 1544 */     PacketPlayOutWorldEvent packet = new PacketPlayOutWorldEvent(packetData, new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()), data, false);
/*      */     
/* 1546 */     radius *= radius;
/*      */     
/* 1548 */     for (Player player : getPlayers()) {
/* 1549 */       if ((((CraftPlayer)player).getHandle()).playerConnection == null || 
/* 1550 */         !location.getWorld().equals(player.getWorld()))
/*      */         continue; 
/* 1552 */       int distance = (int)player.getLocation().distanceSquared(location);
/* 1553 */       if (distance <= radius) {
/* 1554 */         (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)packet);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public <T extends Entity> T spawn(Location location, Class<T> clazz) throws IllegalArgumentException {
/* 1561 */     return spawn(location, clazz, null, CreatureSpawnEvent.SpawnReason.CUSTOM);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T extends Entity> T spawn(Location location, Class<T> clazz, Consumer<T> function) throws IllegalArgumentException {
/* 1566 */     return spawn(location, clazz, function, CreatureSpawnEvent.SpawnReason.CUSTOM);
/*      */   }
/*      */ 
/*      */   
/*      */   public FallingBlock spawnFallingBlock(Location location, MaterialData data) throws IllegalArgumentException {
/* 1571 */     Validate.notNull(data, "MaterialData cannot be null");
/* 1572 */     return spawnFallingBlock(location, data.getItemType(), data.getData());
/*      */   }
/*      */ 
/*      */   
/*      */   public FallingBlock spawnFallingBlock(Location location, Material material, byte data) throws IllegalArgumentException {
/* 1577 */     Validate.notNull(location, "Location cannot be null");
/* 1578 */     Validate.notNull(material, "Material cannot be null");
/* 1579 */     Validate.isTrue(material.isBlock(), "Material must be a block");
/*      */     
/* 1581 */     EntityFallingBlock entity = new EntityFallingBlock((World)this.world, location.getX(), location.getY(), location.getZ(), CraftMagicNumbers.getBlock(material).getBlockData());
/* 1582 */     entity.ticksLived = 1;
/*      */     
/* 1584 */     this.world.addEntity((Entity)entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
/* 1585 */     return (FallingBlock)entity.getBukkitEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   public FallingBlock spawnFallingBlock(Location location, BlockData data) throws IllegalArgumentException {
/* 1590 */     Validate.notNull(location, "Location cannot be null");
/* 1591 */     Validate.notNull(data, "Material cannot be null");
/*      */     
/* 1593 */     EntityFallingBlock entity = new EntityFallingBlock((World)this.world, location.getX(), location.getY(), location.getZ(), ((CraftBlockData)data).getState());
/* 1594 */     entity.ticksLived = 1;
/*      */     
/* 1596 */     this.world.addEntity((Entity)entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
/* 1597 */     return (FallingBlock)entity.getBukkitEntity();
/*      */   }
/*      */   
/*      */   public Entity createEntity(Location location, Class<? extends Entity> clazz) throws IllegalArgumentException {
/*      */     EntityEvokerFangs entityEvokerFangs;
/* 1602 */     if (location == null || clazz == null) {
/* 1603 */       throw new IllegalArgumentException("Location or entity class cannot be null");
/*      */     }
/*      */     
/* 1606 */     Entity entity = null;
/*      */     
/* 1608 */     double x = location.getX();
/* 1609 */     double y = location.getY();
/* 1610 */     double z = location.getZ();
/* 1611 */     float pitch = location.getPitch();
/* 1612 */     float yaw = location.getYaw();
/*      */ 
/*      */     
/* 1615 */     if (Boat.class.isAssignableFrom(clazz)) {
/* 1616 */       EntityBoat entityBoat = new EntityBoat((World)this.world, x, y, z);
/* 1617 */       entityBoat.setPositionRotation(x, y, z, yaw, pitch);
/*      */     }
/* 1619 */     else if (Item.class.isAssignableFrom(clazz)) {
/* 1620 */       EntityItem entityItem = new EntityItem((World)this.world, x, y, z, new ItemStack((IMaterial)Item.getItemOf(Blocks.DIRT)));
/*      */     }
/* 1622 */     else if (FallingBlock.class.isAssignableFrom(clazz)) {
/* 1623 */       EntityFallingBlock entityFallingBlock = new EntityFallingBlock((World)this.world, x, y, z, this.world.getType(new BlockPosition(x, y, z)));
/* 1624 */     } else if (Projectile.class.isAssignableFrom(clazz)) {
/* 1625 */       if (Snowball.class.isAssignableFrom(clazz)) {
/* 1626 */         EntitySnowball entitySnowball = new EntitySnowball((World)this.world, x, y, z);
/* 1627 */       } else if (Egg.class.isAssignableFrom(clazz)) {
/* 1628 */         EntityEgg entityEgg = new EntityEgg((World)this.world, x, y, z);
/* 1629 */       } else if (AbstractArrow.class.isAssignableFrom(clazz)) {
/* 1630 */         if (TippedArrow.class.isAssignableFrom(clazz)) {
/* 1631 */           entity = EntityTypes.ARROW.a((World)this.world);
/* 1632 */           ((EntityTippedArrow)entity).setType(CraftPotionUtil.fromBukkit(new PotionData(PotionType.WATER, false, false)));
/* 1633 */         } else if (SpectralArrow.class.isAssignableFrom(clazz)) {
/* 1634 */           entity = EntityTypes.SPECTRAL_ARROW.a((World)this.world);
/* 1635 */         } else if (Trident.class.isAssignableFrom(clazz)) {
/* 1636 */           entity = EntityTypes.TRIDENT.a((World)this.world);
/*      */         } else {
/* 1638 */           entity = EntityTypes.ARROW.a((World)this.world);
/*      */         } 
/* 1640 */         entity.setPositionRotation(x, y, z, 0.0F, 0.0F);
/* 1641 */       } else if (ThrownExpBottle.class.isAssignableFrom(clazz)) {
/* 1642 */         entity = EntityTypes.EXPERIENCE_BOTTLE.a((World)this.world);
/* 1643 */         entity.setPositionRotation(x, y, z, 0.0F, 0.0F);
/* 1644 */       } else if (EnderPearl.class.isAssignableFrom(clazz)) {
/* 1645 */         entity = EntityTypes.ENDER_PEARL.a((World)this.world);
/* 1646 */         entity.setPositionRotation(x, y, z, 0.0F, 0.0F);
/* 1647 */       } else if (ThrownPotion.class.isAssignableFrom(clazz)) {
/* 1648 */         if (LingeringPotion.class.isAssignableFrom(clazz)) {
/* 1649 */           EntityPotion entityPotion = new EntityPotion((World)this.world, x, y, z);
/* 1650 */           entityPotion.setItem(CraftItemStack.asNMSCopy(new ItemStack(Material.LINGERING_POTION, 1)));
/*      */         } else {
/* 1652 */           EntityPotion entityPotion = new EntityPotion((World)this.world, x, y, z);
/* 1653 */           entityPotion.setItem(CraftItemStack.asNMSCopy(new ItemStack(Material.SPLASH_POTION, 1)));
/*      */         } 
/* 1655 */       } else if (Fireball.class.isAssignableFrom(clazz)) {
/* 1656 */         if (SmallFireball.class.isAssignableFrom(clazz)) {
/* 1657 */           entity = EntityTypes.SMALL_FIREBALL.a((World)this.world);
/* 1658 */         } else if (WitherSkull.class.isAssignableFrom(clazz)) {
/* 1659 */           entity = EntityTypes.WITHER_SKULL.a((World)this.world);
/* 1660 */         } else if (DragonFireball.class.isAssignableFrom(clazz)) {
/* 1661 */           entity = EntityTypes.DRAGON_FIREBALL.a((World)this.world);
/*      */         } else {
/* 1663 */           entity = EntityTypes.FIREBALL.a((World)this.world);
/*      */         } 
/* 1665 */         entity.setPositionRotation(x, y, z, yaw, pitch);
/* 1666 */         Vector direction = location.getDirection().multiply(10);
/* 1667 */         ((EntityFireball)entity).setDirection(direction.getX(), direction.getY(), direction.getZ());
/* 1668 */       } else if (ShulkerBullet.class.isAssignableFrom(clazz)) {
/* 1669 */         entity = EntityTypes.SHULKER_BULLET.a((World)this.world);
/* 1670 */         entity.setPositionRotation(x, y, z, yaw, pitch);
/* 1671 */       } else if (LlamaSpit.class.isAssignableFrom(clazz)) {
/* 1672 */         entity = EntityTypes.LLAMA_SPIT.a((World)this.world);
/* 1673 */         entity.setPositionRotation(x, y, z, yaw, pitch);
/* 1674 */       } else if (Firework.class.isAssignableFrom(clazz)) {
/* 1675 */         EntityFireworks entityFireworks = new EntityFireworks((World)this.world, x, y, z, ItemStack.b);
/*      */       } 
/* 1677 */     } else if (Minecart.class.isAssignableFrom(clazz)) {
/* 1678 */       if (PoweredMinecart.class.isAssignableFrom(clazz)) {
/* 1679 */         EntityMinecartFurnace entityMinecartFurnace = new EntityMinecartFurnace((World)this.world, x, y, z);
/* 1680 */       } else if (StorageMinecart.class.isAssignableFrom(clazz)) {
/* 1681 */         EntityMinecartChest entityMinecartChest = new EntityMinecartChest((World)this.world, x, y, z);
/* 1682 */       } else if (ExplosiveMinecart.class.isAssignableFrom(clazz)) {
/* 1683 */         EntityMinecartTNT entityMinecartTNT = new EntityMinecartTNT((World)this.world, x, y, z);
/* 1684 */       } else if (HopperMinecart.class.isAssignableFrom(clazz)) {
/* 1685 */         EntityMinecartHopper entityMinecartHopper = new EntityMinecartHopper((World)this.world, x, y, z);
/* 1686 */       } else if (SpawnerMinecart.class.isAssignableFrom(clazz)) {
/* 1687 */         EntityMinecartMobSpawner entityMinecartMobSpawner = new EntityMinecartMobSpawner((World)this.world, x, y, z);
/* 1688 */       } else if (CommandMinecart.class.isAssignableFrom(clazz)) {
/* 1689 */         EntityMinecartCommandBlock entityMinecartCommandBlock = new EntityMinecartCommandBlock((World)this.world, x, y, z);
/*      */       } else {
/* 1691 */         EntityMinecartRideable entityMinecartRideable = new EntityMinecartRideable((World)this.world, x, y, z);
/*      */       } 
/* 1693 */     } else if (EnderSignal.class.isAssignableFrom(clazz)) {
/* 1694 */       EntityEnderSignal entityEnderSignal = new EntityEnderSignal((World)this.world, x, y, z);
/* 1695 */     } else if (EnderCrystal.class.isAssignableFrom(clazz)) {
/* 1696 */       entity = EntityTypes.END_CRYSTAL.a((World)this.world);
/* 1697 */       entity.setPositionRotation(x, y, z, 0.0F, 0.0F);
/* 1698 */     } else if (LivingEntity.class.isAssignableFrom(clazz)) {
/* 1699 */       if (Chicken.class.isAssignableFrom(clazz)) {
/* 1700 */         entity = EntityTypes.CHICKEN.a((World)this.world);
/* 1701 */       } else if (Cow.class.isAssignableFrom(clazz)) {
/* 1702 */         if (MushroomCow.class.isAssignableFrom(clazz)) {
/* 1703 */           entity = EntityTypes.MOOSHROOM.a((World)this.world);
/*      */         } else {
/* 1705 */           entity = EntityTypes.COW.a((World)this.world);
/*      */         } 
/* 1707 */       } else if (Golem.class.isAssignableFrom(clazz)) {
/* 1708 */         if (Snowman.class.isAssignableFrom(clazz)) {
/* 1709 */           entity = EntityTypes.SNOW_GOLEM.a((World)this.world);
/* 1710 */         } else if (IronGolem.class.isAssignableFrom(clazz)) {
/* 1711 */           entity = EntityTypes.IRON_GOLEM.a((World)this.world);
/* 1712 */         } else if (Shulker.class.isAssignableFrom(clazz)) {
/* 1713 */           entity = EntityTypes.SHULKER.a((World)this.world);
/*      */         } 
/* 1715 */       } else if (Creeper.class.isAssignableFrom(clazz)) {
/* 1716 */         entity = EntityTypes.CREEPER.a((World)this.world);
/* 1717 */       } else if (Ghast.class.isAssignableFrom(clazz)) {
/* 1718 */         entity = EntityTypes.GHAST.a((World)this.world);
/* 1719 */       } else if (Pig.class.isAssignableFrom(clazz)) {
/* 1720 */         entity = EntityTypes.PIG.a((World)this.world);
/* 1721 */       } else if (!Player.class.isAssignableFrom(clazz)) {
/*      */         
/* 1723 */         if (Sheep.class.isAssignableFrom(clazz)) {
/* 1724 */           entity = EntityTypes.SHEEP.a((World)this.world);
/* 1725 */         } else if (AbstractHorse.class.isAssignableFrom(clazz)) {
/* 1726 */           if (ChestedHorse.class.isAssignableFrom(clazz)) {
/* 1727 */             if (Donkey.class.isAssignableFrom(clazz)) {
/* 1728 */               entity = EntityTypes.DONKEY.a((World)this.world);
/* 1729 */             } else if (Mule.class.isAssignableFrom(clazz)) {
/* 1730 */               entity = EntityTypes.MULE.a((World)this.world);
/* 1731 */             } else if (Llama.class.isAssignableFrom(clazz)) {
/* 1732 */               if (TraderLlama.class.isAssignableFrom(clazz)) {
/* 1733 */                 entity = EntityTypes.TRADER_LLAMA.a((World)this.world);
/*      */               } else {
/* 1735 */                 entity = EntityTypes.LLAMA.a((World)this.world);
/*      */               } 
/*      */             } 
/* 1738 */           } else if (SkeletonHorse.class.isAssignableFrom(clazz)) {
/* 1739 */             entity = EntityTypes.SKELETON_HORSE.a((World)this.world);
/* 1740 */           } else if (ZombieHorse.class.isAssignableFrom(clazz)) {
/* 1741 */             entity = EntityTypes.ZOMBIE_HORSE.a((World)this.world);
/*      */           } else {
/* 1743 */             entity = EntityTypes.HORSE.a((World)this.world);
/*      */           } 
/* 1745 */         } else if (Skeleton.class.isAssignableFrom(clazz)) {
/* 1746 */           if (Stray.class.isAssignableFrom(clazz)) {
/* 1747 */             entity = EntityTypes.STRAY.a((World)this.world);
/* 1748 */           } else if (WitherSkeleton.class.isAssignableFrom(clazz)) {
/* 1749 */             entity = EntityTypes.WITHER_SKELETON.a((World)this.world);
/*      */           } else {
/* 1751 */             entity = EntityTypes.SKELETON.a((World)this.world);
/*      */           } 
/* 1753 */         } else if (Slime.class.isAssignableFrom(clazz)) {
/* 1754 */           if (MagmaCube.class.isAssignableFrom(clazz)) {
/* 1755 */             entity = EntityTypes.MAGMA_CUBE.a((World)this.world);
/*      */           } else {
/* 1757 */             entity = EntityTypes.SLIME.a((World)this.world);
/*      */           } 
/* 1759 */         } else if (Spider.class.isAssignableFrom(clazz)) {
/* 1760 */           if (CaveSpider.class.isAssignableFrom(clazz)) {
/* 1761 */             entity = EntityTypes.CAVE_SPIDER.a((World)this.world);
/*      */           } else {
/* 1763 */             entity = EntityTypes.SPIDER.a((World)this.world);
/*      */           } 
/* 1765 */         } else if (Squid.class.isAssignableFrom(clazz)) {
/* 1766 */           entity = EntityTypes.SQUID.a((World)this.world);
/* 1767 */         } else if (Tameable.class.isAssignableFrom(clazz)) {
/* 1768 */           if (Wolf.class.isAssignableFrom(clazz)) {
/* 1769 */             entity = EntityTypes.WOLF.a((World)this.world);
/* 1770 */           } else if (Parrot.class.isAssignableFrom(clazz)) {
/* 1771 */             entity = EntityTypes.PARROT.a((World)this.world);
/* 1772 */           } else if (Cat.class.isAssignableFrom(clazz)) {
/* 1773 */             entity = EntityTypes.CAT.a((World)this.world);
/*      */           } 
/* 1775 */         } else if (PigZombie.class.isAssignableFrom(clazz)) {
/* 1776 */           entity = EntityTypes.ZOMBIFIED_PIGLIN.a((World)this.world);
/* 1777 */         } else if (Zombie.class.isAssignableFrom(clazz)) {
/* 1778 */           if (Husk.class.isAssignableFrom(clazz)) {
/* 1779 */             entity = EntityTypes.HUSK.a((World)this.world);
/* 1780 */           } else if (ZombieVillager.class.isAssignableFrom(clazz)) {
/* 1781 */             entity = EntityTypes.ZOMBIE_VILLAGER.a((World)this.world);
/* 1782 */           } else if (Drowned.class.isAssignableFrom(clazz)) {
/* 1783 */             entity = EntityTypes.DROWNED.a((World)this.world);
/*      */           } else {
/* 1785 */             EntityZombie entityZombie = new EntityZombie((World)this.world);
/*      */           } 
/* 1787 */         } else if (Giant.class.isAssignableFrom(clazz)) {
/* 1788 */           entity = EntityTypes.GIANT.a((World)this.world);
/* 1789 */         } else if (Silverfish.class.isAssignableFrom(clazz)) {
/* 1790 */           entity = EntityTypes.SILVERFISH.a((World)this.world);
/* 1791 */         } else if (Enderman.class.isAssignableFrom(clazz)) {
/* 1792 */           entity = EntityTypes.ENDERMAN.a((World)this.world);
/* 1793 */         } else if (Blaze.class.isAssignableFrom(clazz)) {
/* 1794 */           entity = EntityTypes.BLAZE.a((World)this.world);
/* 1795 */         } else if (AbstractVillager.class.isAssignableFrom(clazz)) {
/* 1796 */           if (Villager.class.isAssignableFrom(clazz)) {
/* 1797 */             entity = EntityTypes.VILLAGER.a((World)this.world);
/* 1798 */           } else if (WanderingTrader.class.isAssignableFrom(clazz)) {
/* 1799 */             entity = EntityTypes.WANDERING_TRADER.a((World)this.world);
/*      */           } 
/* 1801 */         } else if (Witch.class.isAssignableFrom(clazz)) {
/* 1802 */           entity = EntityTypes.WITCH.a((World)this.world);
/* 1803 */         } else if (Wither.class.isAssignableFrom(clazz)) {
/* 1804 */           entity = EntityTypes.WITHER.a((World)this.world);
/* 1805 */         } else if (ComplexLivingEntity.class.isAssignableFrom(clazz)) {
/* 1806 */           if (EnderDragon.class.isAssignableFrom(clazz)) {
/* 1807 */             entity = EntityTypes.ENDER_DRAGON.a((World)this.world);
/*      */           }
/* 1809 */         } else if (Ambient.class.isAssignableFrom(clazz)) {
/* 1810 */           if (Bat.class.isAssignableFrom(clazz)) {
/* 1811 */             entity = EntityTypes.BAT.a((World)this.world);
/*      */           }
/* 1813 */         } else if (Rabbit.class.isAssignableFrom(clazz)) {
/* 1814 */           entity = EntityTypes.RABBIT.a((World)this.world);
/* 1815 */         } else if (Endermite.class.isAssignableFrom(clazz)) {
/* 1816 */           entity = EntityTypes.ENDERMITE.a((World)this.world);
/* 1817 */         } else if (Guardian.class.isAssignableFrom(clazz)) {
/* 1818 */           if (ElderGuardian.class.isAssignableFrom(clazz)) {
/* 1819 */             entity = EntityTypes.ELDER_GUARDIAN.a((World)this.world);
/*      */           } else {
/* 1821 */             entity = EntityTypes.GUARDIAN.a((World)this.world);
/*      */           } 
/* 1823 */         } else if (ArmorStand.class.isAssignableFrom(clazz)) {
/* 1824 */           EntityArmorStand entityArmorStand = new EntityArmorStand((World)this.world, x, y, z);
/* 1825 */         } else if (PolarBear.class.isAssignableFrom(clazz)) {
/* 1826 */           entity = EntityTypes.POLAR_BEAR.a((World)this.world);
/* 1827 */         } else if (Vex.class.isAssignableFrom(clazz)) {
/* 1828 */           entity = EntityTypes.VEX.a((World)this.world);
/* 1829 */         } else if (Illager.class.isAssignableFrom(clazz)) {
/* 1830 */           if (Spellcaster.class.isAssignableFrom(clazz)) {
/* 1831 */             if (Evoker.class.isAssignableFrom(clazz)) {
/* 1832 */               entity = EntityTypes.EVOKER.a((World)this.world);
/* 1833 */             } else if (Illusioner.class.isAssignableFrom(clazz)) {
/* 1834 */               entity = EntityTypes.ILLUSIONER.a((World)this.world);
/*      */             } 
/* 1836 */           } else if (Vindicator.class.isAssignableFrom(clazz)) {
/* 1837 */             entity = EntityTypes.VINDICATOR.a((World)this.world);
/* 1838 */           } else if (Pillager.class.isAssignableFrom(clazz)) {
/* 1839 */             entity = EntityTypes.PILLAGER.a((World)this.world);
/*      */           } 
/* 1841 */         } else if (Turtle.class.isAssignableFrom(clazz)) {
/* 1842 */           entity = EntityTypes.TURTLE.a((World)this.world);
/* 1843 */         } else if (Phantom.class.isAssignableFrom(clazz)) {
/* 1844 */           entity = EntityTypes.PHANTOM.a((World)this.world);
/* 1845 */         } else if (Fish.class.isAssignableFrom(clazz)) {
/* 1846 */           if (Cod.class.isAssignableFrom(clazz)) {
/* 1847 */             entity = EntityTypes.COD.a((World)this.world);
/* 1848 */           } else if (PufferFish.class.isAssignableFrom(clazz)) {
/* 1849 */             entity = EntityTypes.PUFFERFISH.a((World)this.world);
/* 1850 */           } else if (Salmon.class.isAssignableFrom(clazz)) {
/* 1851 */             entity = EntityTypes.SALMON.a((World)this.world);
/* 1852 */           } else if (TropicalFish.class.isAssignableFrom(clazz)) {
/* 1853 */             entity = EntityTypes.TROPICAL_FISH.a((World)this.world);
/*      */           } 
/* 1855 */         } else if (Dolphin.class.isAssignableFrom(clazz)) {
/* 1856 */           entity = EntityTypes.DOLPHIN.a((World)this.world);
/* 1857 */         } else if (Ocelot.class.isAssignableFrom(clazz)) {
/* 1858 */           entity = EntityTypes.OCELOT.a((World)this.world);
/* 1859 */         } else if (Ravager.class.isAssignableFrom(clazz)) {
/* 1860 */           entity = EntityTypes.RAVAGER.a((World)this.world);
/* 1861 */         } else if (Panda.class.isAssignableFrom(clazz)) {
/* 1862 */           entity = EntityTypes.PANDA.a((World)this.world);
/* 1863 */         } else if (Fox.class.isAssignableFrom(clazz)) {
/* 1864 */           entity = EntityTypes.FOX.a((World)this.world);
/* 1865 */         } else if (Bee.class.isAssignableFrom(clazz)) {
/* 1866 */           entity = EntityTypes.BEE.a((World)this.world);
/* 1867 */         } else if (Hoglin.class.isAssignableFrom(clazz)) {
/* 1868 */           entity = EntityTypes.HOGLIN.a((World)this.world);
/* 1869 */         } else if (Piglin.class.isAssignableFrom(clazz)) {
/* 1870 */           entity = EntityTypes.PIGLIN.a((World)this.world);
/* 1871 */         } else if (PiglinBrute.class.isAssignableFrom(clazz)) {
/* 1872 */           entity = EntityTypes.PIGLIN_BRUTE.a((World)this.world);
/* 1873 */         } else if (Strider.class.isAssignableFrom(clazz)) {
/* 1874 */           entity = EntityTypes.STRIDER.a((World)this.world);
/* 1875 */         } else if (Zoglin.class.isAssignableFrom(clazz)) {
/* 1876 */           entity = EntityTypes.ZOGLIN.a((World)this.world);
/*      */         } 
/*      */       } 
/* 1879 */       if (entity != null) {
/* 1880 */         entity.setLocation(x, y, z, yaw, pitch);
/* 1881 */         entity.setHeadRotation(yaw);
/*      */       } 
/* 1883 */     } else if (Hanging.class.isAssignableFrom(clazz)) {
/* 1884 */       EntityItemFrame entityItemFrame; BlockFace face = BlockFace.SELF;
/*      */       
/* 1886 */       int width = 16;
/* 1887 */       int height = 16;
/*      */       
/* 1889 */       if (ItemFrame.class.isAssignableFrom(clazz)) {
/* 1890 */         width = 12;
/* 1891 */         height = 12;
/* 1892 */       } else if (LeashHitch.class.isAssignableFrom(clazz)) {
/* 1893 */         width = 9;
/* 1894 */         height = 9;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1900 */       (new BlockFace[6])[0] = BlockFace.EAST; (new BlockFace[6])[1] = BlockFace.NORTH; (new BlockFace[6])[2] = BlockFace.WEST; (new BlockFace[6])[3] = BlockFace.SOUTH; (new BlockFace[6])[4] = BlockFace.UP; (new BlockFace[6])[5] = BlockFace.DOWN;
/* 1901 */       (new BlockFace[4])[0] = BlockFace.EAST; (new BlockFace[4])[1] = BlockFace.NORTH; (new BlockFace[4])[2] = BlockFace.WEST; (new BlockFace[4])[3] = BlockFace.SOUTH; BlockFace[] faces = ItemFrame.class.isAssignableFrom(clazz) ? new BlockFace[6] : new BlockFace[4];
/*      */       
/* 1903 */       BlockPosition pos = new BlockPosition(x, y, z);
/* 1904 */       for (BlockFace dir : faces) {
/* 1905 */         IBlockData nmsBlock = this.world.getType(pos.shift(CraftBlock.blockFaceToNotch(dir)));
/* 1906 */         if (nmsBlock.getMaterial().isBuildable() || BlockDiodeAbstract.isDiode(nmsBlock)) {
/* 1907 */           boolean taken = false;
/*      */ 
/*      */           
/* 1910 */           AxisAlignedBB bb = ItemFrame.class.isAssignableFrom(clazz) ? EntityItemFrame.calculateBoundingBox(null, pos, CraftBlock.blockFaceToNotch(dir).opposite(), width, height) : EntityHanging.calculateBoundingBox(null, pos, CraftBlock.blockFaceToNotch(dir).opposite(), width, height);
/* 1911 */           List<Entity> list = this.world.getEntities(null, bb);
/* 1912 */           for (Iterator<Entity> it = list.iterator(); !taken && it.hasNext(); ) {
/* 1913 */             Entity e = it.next();
/* 1914 */             if (e instanceof EntityHanging) {
/* 1915 */               taken = true;
/*      */             }
/*      */           } 
/*      */           
/* 1919 */           if (!taken) {
/* 1920 */             face = dir;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1926 */       if (LeashHitch.class.isAssignableFrom(clazz)) {
/* 1927 */         EntityLeash entityLeash = new EntityLeash((World)this.world, new BlockPosition(x, y, z));
/* 1928 */         ((Entity)entityLeash).attachedToPlayer = true;
/*      */       } else {
/*      */         
/* 1931 */         Preconditions.checkArgument((face != BlockFace.SELF), "Cannot spawn hanging entity for %s at %s (no free face)", clazz.getName(), location);
/*      */         
/* 1933 */         EnumDirection dir = CraftBlock.blockFaceToNotch(face).opposite();
/* 1934 */         if (Painting.class.isAssignableFrom(clazz)) {
/* 1935 */           EntityPainting entityPainting = new EntityPainting((World)this.world, new BlockPosition(x, y, z), dir);
/* 1936 */         } else if (ItemFrame.class.isAssignableFrom(clazz)) {
/* 1937 */           entityItemFrame = new EntityItemFrame((World)this.world, new BlockPosition(x, y, z), dir);
/*      */         } 
/*      */       } 
/*      */       
/* 1941 */       if (entityItemFrame != null && !((EntityHanging)entityItemFrame).survives()) {
/* 1942 */         throw new IllegalArgumentException("Cannot spawn hanging entity for " + clazz.getName() + " at " + location);
/*      */       }
/* 1944 */     } else if (TNTPrimed.class.isAssignableFrom(clazz)) {
/* 1945 */       EntityTNTPrimed entityTNTPrimed = new EntityTNTPrimed((World)this.world, x, y, z, null);
/* 1946 */     } else if (ExperienceOrb.class.isAssignableFrom(clazz)) {
/* 1947 */       EntityExperienceOrb entityExperienceOrb = new EntityExperienceOrb((World)this.world, x, y, z, 0, ExperienceOrb.SpawnReason.CUSTOM, null, null);
/* 1948 */     } else if (LightningStrike.class.isAssignableFrom(clazz)) {
/* 1949 */       entity = EntityTypes.LIGHTNING_BOLT.a((World)this.world);
/* 1950 */     } else if (AreaEffectCloud.class.isAssignableFrom(clazz)) {
/* 1951 */       EntityAreaEffectCloud entityAreaEffectCloud = new EntityAreaEffectCloud((World)this.world, x, y, z);
/* 1952 */     } else if (EvokerFangs.class.isAssignableFrom(clazz)) {
/* 1953 */       entityEvokerFangs = new EntityEvokerFangs((World)this.world, x, y, z, (float)Math.toRadians(yaw), 0, null);
/*      */     } 
/*      */     
/* 1956 */     if (entityEvokerFangs != null) {
/* 1957 */       return (Entity)entityEvokerFangs;
/*      */     }
/*      */     
/* 1960 */     throw new IllegalArgumentException("Cannot spawn an entity for " + clazz.getName());
/*      */   }
/*      */ 
/*      */   
/*      */   public <T extends Entity> T addEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) throws IllegalArgumentException {
/* 1965 */     return addEntity(entity, reason, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T extends Entity> T addEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason, Consumer<T> function) throws IllegalArgumentException {
/* 1970 */     Preconditions.checkArgument((entity != null), "Cannot spawn null entity");
/*      */     
/* 1972 */     if (entity instanceof EntityInsentient) {
/* 1973 */       ((EntityInsentient)entity).prepare((WorldAccess)getHandle(), getHandle().getDamageScaler(entity.getChunkCoordinates()), EnumMobSpawn.COMMAND, (GroupDataEntity)null, null);
/*      */     }
/*      */     
/* 1976 */     if (function != null) {
/* 1977 */       function.accept(entity.getBukkitEntity());
/*      */     }
/*      */     
/* 1980 */     this.world.addEntity(entity, reason);
/* 1981 */     return (T)entity.getBukkitEntity();
/*      */   }
/*      */   
/*      */   public <T extends Entity> T spawn(Location location, Class<T> clazz, Consumer<T> function, CreatureSpawnEvent.SpawnReason reason) throws IllegalArgumentException {
/* 1985 */     Entity entity = createEntity(location, clazz);
/*      */     
/* 1987 */     return addEntity(entity, reason, function);
/*      */   }
/*      */ 
/*      */   
/*      */   public ChunkSnapshot getEmptyChunkSnapshot(int x, int z, boolean includeBiome, boolean includeBiomeTempRain) {
/* 1992 */     return CraftChunk.getEmptyChunkSnapshot(x, z, this, includeBiome, includeBiomeTempRain);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSpawnFlags(boolean allowMonsters, boolean allowAnimals) {
/* 1997 */     this.world.setSpawnFlags(allowMonsters, allowAnimals);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAllowAnimals() {
/* 2002 */     return (this.world.getChunkProvider()).allowAnimals;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAllowMonsters() {
/* 2007 */     return (this.world.getChunkProvider()).allowMonsters;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxHeight() {
/* 2012 */     return this.world.getBuildHeight();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSeaLevel() {
/* 2017 */     return this.world.getSeaLevel();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getKeepSpawnInMemory() {
/* 2022 */     return this.world.keepSpawnInMemory;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setKeepSpawnInMemory(boolean keepLoaded) {
/* 2028 */     if (keepLoaded == this.world.keepSpawnInMemory) {
/*      */       return;
/*      */     }
/*      */     
/* 2032 */     this.world.keepSpawnInMemory = keepLoaded;
/*      */     
/* 2034 */     BlockPosition prevSpawn = this.world.getSpawn();
/* 2035 */     if (keepLoaded) {
/* 2036 */       this.world.addTicketsForSpawn(this.world.paperConfig.keepLoadedRange, prevSpawn);
/*      */     } else {
/*      */       
/* 2039 */       this.world.removeTicketsForSpawn(this.world.paperConfig.keepLoadedRange, prevSpawn);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 2046 */     return getUID().hashCode();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/* 2051 */     if (obj == null) {
/* 2052 */       return false;
/*      */     }
/* 2054 */     if (getClass() != obj.getClass()) {
/* 2055 */       return false;
/*      */     }
/*      */     
/* 2058 */     CraftWorld other = (CraftWorld)obj;
/*      */     
/* 2060 */     return (getUID() == other.getUID());
/*      */   }
/*      */ 
/*      */   
/*      */   public File getWorldFolder() {
/* 2065 */     return this.world.convertable.getWorldFolder(SavedFile.ROOT).toFile().getParentFile();
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPluginMessage(Plugin source, String channel, byte[] message) {
/* 2070 */     StandardMessenger.validatePluginMessage(this.server.getMessenger(), source, channel, message);
/*      */     
/* 2072 */     for (Player player : getPlayers()) {
/* 2073 */       player.sendPluginMessage(source, channel, message);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<String> getListeningPluginChannels() {
/* 2079 */     Set<String> result = new HashSet<>();
/*      */     
/* 2081 */     for (Player player : getPlayers()) {
/* 2082 */       result.addAll(player.getListeningPluginChannels());
/*      */     }
/*      */     
/* 2085 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldType getWorldType() {
/* 2090 */     return this.world.isFlatWorld() ? WorldType.FLAT : WorldType.NORMAL;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canGenerateStructures() {
/* 2095 */     return this.world.worldDataServer.getGeneratorSettings().shouldGenerateMapFeatures();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHardcore() {
/* 2100 */     return this.world.getWorldData().isHardcore();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHardcore(boolean hardcore) {
/* 2105 */     this.world.worldDataServer.b.hardcore = hardcore;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTicksPerAnimalSpawns() {
/* 2110 */     return this.world.ticksPerAnimalSpawns;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTicksPerAnimalSpawns(int ticksPerAnimalSpawns) {
/* 2115 */     this.world.ticksPerAnimalSpawns = ticksPerAnimalSpawns;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTicksPerMonsterSpawns() {
/* 2120 */     return this.world.ticksPerMonsterSpawns;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTicksPerMonsterSpawns(int ticksPerMonsterSpawns) {
/* 2125 */     this.world.ticksPerMonsterSpawns = ticksPerMonsterSpawns;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTicksPerWaterSpawns() {
/* 2130 */     return this.world.ticksPerWaterSpawns;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTicksPerWaterSpawns(int ticksPerWaterSpawns) {
/* 2135 */     this.world.ticksPerWaterSpawns = ticksPerWaterSpawns;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTicksPerWaterAmbientSpawns() {
/* 2140 */     return this.world.ticksPerWaterAmbientSpawns;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTicksPerWaterAmbientSpawns(int ticksPerWaterAmbientSpawns) {
/* 2145 */     this.world.ticksPerWaterAmbientSpawns = ticksPerWaterAmbientSpawns;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTicksPerAmbientSpawns() {
/* 2150 */     return this.world.ticksPerAmbientSpawns;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTicksPerAmbientSpawns(int ticksPerAmbientSpawns) {
/* 2155 */     this.world.ticksPerAmbientSpawns = ticksPerAmbientSpawns;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
/* 2160 */     this.server.getWorldMetadata().setMetadata(this, metadataKey, newMetadataValue);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<MetadataValue> getMetadata(String metadataKey) {
/* 2165 */     return this.server.getWorldMetadata().getMetadata(this, metadataKey);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasMetadata(String metadataKey) {
/* 2170 */     return this.server.getWorldMetadata().hasMetadata(this, metadataKey);
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeMetadata(String metadataKey, Plugin owningPlugin) {
/* 2175 */     this.server.getWorldMetadata().removeMetadata(this, metadataKey, owningPlugin);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMonsterSpawnLimit() {
/* 2180 */     if (this.monsterSpawn < 0) {
/* 2181 */       return this.server.getMonsterSpawnLimit();
/*      */     }
/*      */     
/* 2184 */     return this.monsterSpawn;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMonsterSpawnLimit(int limit) {
/* 2189 */     this.monsterSpawn = limit;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAnimalSpawnLimit() {
/* 2194 */     if (this.animalSpawn < 0) {
/* 2195 */       return this.server.getAnimalSpawnLimit();
/*      */     }
/*      */     
/* 2198 */     return this.animalSpawn;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAnimalSpawnLimit(int limit) {
/* 2203 */     this.animalSpawn = limit;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getWaterAnimalSpawnLimit() {
/* 2208 */     if (this.waterAnimalSpawn < 0) {
/* 2209 */       return this.server.getWaterAnimalSpawnLimit();
/*      */     }
/*      */     
/* 2212 */     return this.waterAnimalSpawn;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWaterAnimalSpawnLimit(int limit) {
/* 2217 */     this.waterAnimalSpawn = limit;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getWaterAmbientSpawnLimit() {
/* 2222 */     if (this.waterAmbientSpawn < 0) {
/* 2223 */       return this.server.getWaterAmbientSpawnLimit();
/*      */     }
/*      */     
/* 2226 */     return this.waterAmbientSpawn;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWaterAmbientSpawnLimit(int limit) {
/* 2231 */     this.waterAmbientSpawn = limit;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAmbientSpawnLimit() {
/* 2236 */     if (this.ambientSpawn < 0) {
/* 2237 */       return this.server.getAmbientSpawnLimit();
/*      */     }
/*      */     
/* 2240 */     return this.ambientSpawn;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAmbientSpawnLimit(int limit) {
/* 2245 */     this.ambientSpawn = limit;
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSound(Location loc, Sound sound, float volume, float pitch) {
/* 2250 */     playSound(loc, sound, SoundCategory.MASTER, volume, pitch);
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSound(Location loc, String sound, float volume, float pitch) {
/* 2255 */     playSound(loc, sound, SoundCategory.MASTER, volume, pitch);
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSound(Location loc, Sound sound, SoundCategory category, float volume, float pitch) {
/* 2260 */     if (loc == null || sound == null || category == null)
/*      */       return; 
/* 2262 */     double x = loc.getX();
/* 2263 */     double y = loc.getY();
/* 2264 */     double z = loc.getZ();
/*      */     
/* 2266 */     getHandle().playSound(null, x, y, z, CraftSound.getSoundEffect(CraftSound.getSound(sound)), SoundCategory.valueOf(category.name()), volume, pitch);
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSound(Location loc, String sound, SoundCategory category, float volume, float pitch) {
/* 2271 */     if (loc == null || sound == null || category == null)
/*      */       return; 
/* 2273 */     double x = loc.getX();
/* 2274 */     double y = loc.getY();
/* 2275 */     double z = loc.getZ();
/*      */     
/* 2277 */     PacketPlayOutCustomSoundEffect packet = new PacketPlayOutCustomSoundEffect(new MinecraftKey(sound), SoundCategory.valueOf(category.name()), new Vec3D(x, y, z), volume, pitch);
/* 2278 */     this.world.getMinecraftServer().getPlayerList().sendPacketNearby(null, x, y, z, (volume > 1.0F) ? (16.0F * volume) : 16.0D, this.world.getDimensionKey(), (Packet)packet);
/*      */   }
/*      */ 
/*      */   
/*      */   public static synchronized Map<String, GameRules.GameRuleKey<?>> getGameRulesNMS() {
/* 2283 */     if (CraftWorld.gamerules != null) {
/* 2284 */       return CraftWorld.gamerules;
/*      */     }
/*      */     
/* 2287 */     final Map<String, GameRules.GameRuleKey<?>> gamerules = new HashMap<>();
/* 2288 */     GameRules.a(new GameRules.GameRuleVisitor()
/*      */         {
/*      */           public <T extends GameRules.GameRuleValue<T>> void a(GameRules.GameRuleKey<T> gamerules_gamerulekey, GameRules.GameRuleDefinition<T> gamerules_gameruledefinition) {
/* 2291 */             gamerules.put(gamerules_gamerulekey.a(), gamerules_gamerulekey);
/*      */           }
/*      */         });
/*      */     
/* 2295 */     return CraftWorld.gamerules = gamerules;
/*      */   }
/*      */ 
/*      */   
/*      */   public static synchronized Map<String, GameRules.GameRuleDefinition<?>> getGameRuleDefinitions() {
/* 2300 */     if (CraftWorld.gameruleDefinitions != null) {
/* 2301 */       return CraftWorld.gameruleDefinitions;
/*      */     }
/*      */     
/* 2304 */     final Map<String, GameRules.GameRuleDefinition<?>> gameruleDefinitions = new HashMap<>();
/* 2305 */     GameRules.a(new GameRules.GameRuleVisitor()
/*      */         {
/*      */           public <T extends GameRules.GameRuleValue<T>> void a(GameRules.GameRuleKey<T> gamerules_gamerulekey, GameRules.GameRuleDefinition<T> gamerules_gameruledefinition) {
/* 2308 */             gameruleDefinitions.put(gamerules_gamerulekey.a(), gamerules_gameruledefinition);
/*      */           }
/*      */         });
/*      */     
/* 2312 */     return CraftWorld.gameruleDefinitions = gameruleDefinitions;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getGameRuleValue(String rule) {
/* 2318 */     if (rule == null) {
/* 2319 */       return null;
/*      */     }
/*      */     
/* 2322 */     GameRules.GameRuleValue<?> value = getHandle().getGameRules().get(getGameRulesNMS().get(rule));
/* 2323 */     return (value != null) ? value.toString() : "";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setGameRuleValue(String rule, String value) {
/* 2329 */     if (rule == null || value == null) return false;
/*      */     
/* 2331 */     if (!isGameRule(rule)) return false;
/*      */     
/* 2333 */     GameRules.GameRuleValue<?> handle = getHandle().getGameRules().get(getGameRulesNMS().get(rule));
/* 2334 */     handle.setValue(value);
/* 2335 */     handle.onChange(getHandle().getMinecraftServer());
/* 2336 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public String[] getGameRules() {
/* 2341 */     return (String[])getGameRulesNMS().keySet().toArray((Object[])new String[getGameRulesNMS().size()]);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isGameRule(String rule) {
/* 2346 */     Validate.isTrue((rule != null && !rule.isEmpty()), "Rule cannot be null nor empty");
/* 2347 */     return getGameRulesNMS().containsKey(rule);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> T getGameRuleValue(GameRule<T> rule) {
/* 2352 */     Validate.notNull(rule, "GameRule cannot be null");
/* 2353 */     return convert(rule, getHandle().getGameRules().get(getGameRulesNMS().get(rule.getName())));
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> T getGameRuleDefault(GameRule<T> rule) {
/* 2358 */     Validate.notNull(rule, "GameRule cannot be null");
/* 2359 */     return convert(rule, ((GameRules.GameRuleDefinition)getGameRuleDefinitions().get(rule.getName())).getValue());
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> boolean setGameRule(GameRule<T> rule, T newValue) {
/* 2364 */     Validate.notNull(rule, "GameRule cannot be null");
/* 2365 */     Validate.notNull(newValue, "GameRule value cannot be null");
/*      */     
/* 2367 */     if (!isGameRule(rule.getName())) return false;
/*      */     
/* 2369 */     GameRules.GameRuleValue<?> handle = getHandle().getGameRules().get(getGameRulesNMS().get(rule.getName()));
/* 2370 */     handle.setValue(newValue.toString());
/* 2371 */     handle.onChange(getHandle().getMinecraftServer());
/* 2372 */     return true;
/*      */   }
/*      */   
/*      */   private <T> T convert(GameRule<T> rule, GameRules.GameRuleValue<?> value) {
/* 2376 */     if (value == null) {
/* 2377 */       return null;
/*      */     }
/*      */     
/* 2380 */     if (value instanceof GameRules.GameRuleBoolean)
/* 2381 */       return rule.getType().cast(Boolean.valueOf(((GameRules.GameRuleBoolean)value).a())); 
/* 2382 */     if (value instanceof GameRules.GameRuleInt) {
/* 2383 */       return rule.getType().cast(Integer.valueOf(value.getIntValue()));
/*      */     }
/* 2385 */     throw new IllegalArgumentException("Invalid GameRule type (" + value + ") for GameRule " + rule.getName());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public WorldBorder getWorldBorder() {
/* 2391 */     if (this.worldBorder == null) {
/* 2392 */       this.worldBorder = new CraftWorldBorder(this);
/*      */     }
/*      */     
/* 2395 */     return this.worldBorder;
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnParticle(Particle particle, Location location, int count) {
/* 2400 */     spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnParticle(Particle particle, double x, double y, double z, int count) {
/* 2405 */     spawnParticle(particle, x, y, z, count, (Object)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> void spawnParticle(Particle particle, Location location, int count, T data) {
/* 2410 */     spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, data);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, T data) {
/* 2415 */     spawnParticle(particle, x, y, z, count, 0.0D, 0.0D, 0.0D, data);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {
/* 2420 */     spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
/* 2425 */     spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, (Object)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, T data) {
/* 2430 */     spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, data);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) {
/* 2435 */     spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 1.0D, data);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
/* 2440 */     spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra);
/*      */   }
/*      */ 
/*      */   
/*      */   public void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
/* 2445 */     spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
/* 2450 */     spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
/* 2455 */     spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, data, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data, boolean force) {
/* 2460 */     spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data, force);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data, boolean force) {
/* 2466 */     spawnParticle(particle, null, null, x, y, z, count, offsetX, offsetY, offsetZ, extra, data, force);
/*      */   }
/*      */   
/*      */   public <T> void spawnParticle(Particle particle, List<Player> receivers, Player sender, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data, boolean force) {
/* 2470 */     if (data != null && !particle.getDataType().isInstance(data)) {
/* 2471 */       throw new IllegalArgumentException("data should be " + particle.getDataType() + " got " + data.getClass());
/*      */     }
/* 2473 */     getHandle().sendParticles(
/* 2474 */         (receivers == null) ? (getHandle()).players : (List)receivers.stream().map(player -> ((CraftPlayer)player).getHandle()).collect(Collectors.toList()), 
/* 2475 */         (sender != null) ? ((CraftPlayer)sender).getHandle() : null, 
/* 2476 */         CraftParticle.toNMS(particle, data), x, y, z, count, offsetX, offsetY, offsetZ, extra, force);
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
/*      */   public Location locateNearestStructure(Location origin, StructureType structureType, int radius, boolean findUnexplored) {
/* 2488 */     BlockPosition originPos = new BlockPosition(origin.getX(), origin.getY(), origin.getZ());
/* 2489 */     BlockPosition nearest = getHandle().getChunkProvider().getChunkGenerator().findNearestMapFeature(getHandle(), (StructureGenerator)StructureGenerator.a.get(structureType.getName()), originPos, radius, findUnexplored);
/* 2490 */     return (nearest == null) ? null : new Location(this, nearest.getX(), nearest.getY(), nearest.getZ());
/*      */   }
/*      */ 
/*      */   
/*      */   public Raid locateNearestRaid(Location location, int radius) {
/* 2495 */     Validate.notNull(location, "Location cannot be null");
/* 2496 */     Validate.isTrue((radius >= 0), "Radius cannot be negative");
/*      */     
/* 2498 */     PersistentRaid persistentRaid = this.world.getPersistentRaid();
/* 2499 */     Raid raid = persistentRaid.getNearbyRaid(new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()), radius * radius);
/* 2500 */     return (raid == null) ? null : new CraftRaid(raid);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Raid> getRaids() {
/* 2505 */     PersistentRaid persistentRaid = this.world.getPersistentRaid();
/* 2506 */     return (List<Raid>)persistentRaid.raids.values().stream().map(CraftRaid::new).collect(Collectors.toList());
/*      */   }
/*      */ 
/*      */   
/*      */   public DragonBattle getEnderDragonBattle() {
/* 2511 */     return (getHandle().getDragonBattle() == null) ? null : (DragonBattle)new CraftDragonBattle(getHandle().getDragonBattle());
/*      */   }
/*      */ 
/*      */   
/*      */   public CompletableFuture<Chunk> getChunkAtAsync(int x, int z, boolean gen, boolean urgent) {
/* 2516 */     if (Bukkit.isPrimaryThread()) {
/* 2517 */       Chunk immediate = this.world.getChunkProvider().getChunkAtIfLoadedImmediately(x, z);
/* 2518 */       if (immediate != null) {
/* 2519 */         return CompletableFuture.completedFuture(immediate.getBukkitChunk());
/*      */       }
/*      */     } else {
/* 2522 */       CompletableFuture<Chunk> future = new CompletableFuture<>();
/* 2523 */       this.world.getMinecraftServer().execute(() -> getChunkAtAsync(x, z, gen, urgent).whenComplete(()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2532 */       return future;
/*      */     } 
/*      */     
/* 2535 */     if (!urgent)
/*      */     {
/* 2537 */       this.world.getChunkProvider().markHighPriority(new ChunkCoordIntPair(x, z), 1);
/*      */     }
/* 2539 */     return this.world.getChunkProvider().getChunkAtAsynchronously(x, z, gen, urgent).thenComposeAsync(either -> {
/*      */           Chunk chunk = either.left().orElse(null);
/*      */           
/*      */           return CompletableFuture.completedFuture((chunk == null) ? null : chunk.getBukkitChunk());
/* 2543 */         }(Executor)MinecraftServer.getServer());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getViewDistance() {
/* 2550 */     return (getHandle().getChunkProvider()).playerChunkMap.getEffectiveViewDistance();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setViewDistance(int viewDistance) {
/* 2557 */     if (viewDistance < 2 || viewDistance > 32) {
/* 2558 */       throw new IllegalArgumentException("View distance " + viewDistance + " is out of range of [2, 32]");
/*      */     }
/* 2560 */     PlayerChunkMap chunkMap = (getHandle().getChunkProvider()).playerChunkMap;
/* 2561 */     if (viewDistance != chunkMap.getEffectiveViewDistance()) {
/* 2562 */       chunkMap.setViewDistance(viewDistance);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNoTickViewDistance() {
/* 2568 */     return (getHandle().getChunkProvider()).playerChunkMap.getEffectiveNoTickViewDistance();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setNoTickViewDistance(int viewDistance) {
/* 2573 */     if ((viewDistance < 2 || viewDistance > 32) && viewDistance != -1) {
/* 2574 */       throw new IllegalArgumentException("View distance " + viewDistance + " is out of range of [2, 32]");
/*      */     }
/* 2576 */     PlayerChunkMap chunkMap = (getHandle().getChunkProvider()).playerChunkMap;
/* 2577 */     if (viewDistance != chunkMap.getRawNoTickViewDistance()) {
/* 2578 */       chunkMap.setNoTickViewDistance(viewDistance);
/*      */     }
/*      */   }
/*      */   
/*      */   public CraftWorld(WorldServer world, ChunkGenerator gen, World.Environment env)
/*      */   {
/* 2584 */     this.spigot = new World.Spigot()
/*      */       {
/*      */ 
/*      */         
/*      */         public LightningStrike strikeLightning(Location loc, boolean isSilent)
/*      */         {
/* 2590 */           EntityLightning lightning = (EntityLightning)EntityTypes.LIGHTNING_BOLT.a((World)CraftWorld.this.world);
/* 2591 */           lightning.teleportAndSync(loc.getX(), loc.getY(), loc.getZ());
/* 2592 */           lightning.isSilent = isSilent;
/* 2593 */           CraftWorld.this.world.strikeLightning((Entity)lightning);
/* 2594 */           return (LightningStrike)lightning.getBukkitEntity();
/*      */         }
/*      */ 
/*      */         
/*      */         public LightningStrike strikeLightningEffect(Location loc, boolean isSilent)
/*      */         {
/* 2600 */           EntityLightning lightning = (EntityLightning)EntityTypes.LIGHTNING_BOLT.a((World)CraftWorld.this.world);
/* 2601 */           lightning.teleportAndSync(loc.getX(), loc.getY(), loc.getZ());
/* 2602 */           lightning.isEffect = true;
/* 2603 */           lightning.isSilent = isSilent;
/* 2604 */           CraftWorld.this.world.strikeLightning((Entity)lightning);
/* 2605 */           return (LightningStrike)lightning.getBukkitEntity(); } }; this.world = world; this.generator = gen;
/*      */     this.environment = env;
/*      */     this.monsterSpawn = world.tuinityConfig.spawnLimitMonsters;
/*      */     this.animalSpawn = world.tuinityConfig.spawnLimitAnimals;
/*      */     this.waterAmbientSpawn = world.tuinityConfig.spawnLimitWaterAmbient;
/*      */     this.waterAnimalSpawn = world.tuinityConfig.spawnLimitWaterAnimals;
/* 2611 */     this.ambientSpawn = world.tuinityConfig.spawnLimitAmbient; } public World.Spigot spigot() { return this.spigot; }
/*      */ 
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */