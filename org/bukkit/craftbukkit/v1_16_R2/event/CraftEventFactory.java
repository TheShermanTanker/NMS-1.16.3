/*      */ package org.bukkit.craftbukkit.v1_16_R2.event;
/*      */ import com.destroystokyo.paper.event.entity.EntityZapEvent;
/*      */ import com.destroystokyo.paper.event.entity.ProjectileCollideEvent;
/*      */ import com.destroystokyo.paper.event.inventory.PrepareGrindstoneEvent;
/*      */ import com.destroystokyo.paper.event.inventory.PrepareResultEvent;
/*      */ import com.google.common.base.Function;
/*      */ import com.google.common.base.Functions;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.mojang.datafixers.util.Either;
/*      */ import java.net.InetAddress;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.EnumMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.Supplier;
/*      */ import java.util.stream.Collectors;
/*      */ import javax.annotation.Nullable;
/*      */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*      */ import net.minecraft.server.v1_16_R2.BlockPropertyInstrument;
/*      */ import net.minecraft.server.v1_16_R2.ChatMessage;
/*      */ import net.minecraft.server.v1_16_R2.ChatModifier;
/*      */ import net.minecraft.server.v1_16_R2.Container;
/*      */ import net.minecraft.server.v1_16_R2.ContainerMerchant;
/*      */ import net.minecraft.server.v1_16_R2.DamageSource;
/*      */ import net.minecraft.server.v1_16_R2.Entity;
/*      */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*      */ import net.minecraft.server.v1_16_R2.EntityAreaEffectCloud;
/*      */ import net.minecraft.server.v1_16_R2.EntityDamageSourceIndirect;
/*      */ import net.minecraft.server.v1_16_R2.EntityExperienceOrb;
/*      */ import net.minecraft.server.v1_16_R2.EntityFireworks;
/*      */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*      */ import net.minecraft.server.v1_16_R2.EntityIllagerWizard;
/*      */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*      */ import net.minecraft.server.v1_16_R2.EntityItem;
/*      */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*      */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*      */ import net.minecraft.server.v1_16_R2.EntityPotion;
/*      */ import net.minecraft.server.v1_16_R2.EntityRaider;
/*      */ import net.minecraft.server.v1_16_R2.EntityStrider;
/*      */ import net.minecraft.server.v1_16_R2.EntityVillager;
/*      */ import net.minecraft.server.v1_16_R2.EnumDirection;
/*      */ import net.minecraft.server.v1_16_R2.EnumHand;
/*      */ import net.minecraft.server.v1_16_R2.EnumItemSlot;
/*      */ import net.minecraft.server.v1_16_R2.Explosion;
/*      */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*      */ import net.minecraft.server.v1_16_R2.IBlockData;
/*      */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*      */ import net.minecraft.server.v1_16_R2.IInventory;
/*      */ import net.minecraft.server.v1_16_R2.IProjectile;
/*      */ import net.minecraft.server.v1_16_R2.Item;
/*      */ import net.minecraft.server.v1_16_R2.ItemActionContext;
/*      */ import net.minecraft.server.v1_16_R2.ItemStack;
/*      */ import net.minecraft.server.v1_16_R2.Items;
/*      */ import net.minecraft.server.v1_16_R2.LootContextParameters;
/*      */ import net.minecraft.server.v1_16_R2.LootTable;
/*      */ import net.minecraft.server.v1_16_R2.LootTableInfo;
/*      */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*      */ import net.minecraft.server.v1_16_R2.MobEffect;
/*      */ import net.minecraft.server.v1_16_R2.MovingObjectPosition;
/*      */ import net.minecraft.server.v1_16_R2.MovingObjectPositionBlock;
/*      */ import net.minecraft.server.v1_16_R2.MovingObjectPositionEntity;
/*      */ import net.minecraft.server.v1_16_R2.PacketPlayInCloseWindow;
/*      */ import net.minecraft.server.v1_16_R2.Raid;
/*      */ import net.minecraft.server.v1_16_R2.SoundCategory;
/*      */ import net.minecraft.server.v1_16_R2.SoundEffect;
/*      */ import net.minecraft.server.v1_16_R2.Statistic;
/*      */ import net.minecraft.server.v1_16_R2.Unit;
/*      */ import net.minecraft.server.v1_16_R2.World;
/*      */ import net.minecraft.server.v1_16_R2.WorldServer;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Instrument;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.NamespacedKey;
/*      */ import org.bukkit.Note;
/*      */ import org.bukkit.Raid;
/*      */ import org.bukkit.Server;
/*      */ import org.bukkit.Statistic;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.attribute.Attribute;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.block.BlockFace;
/*      */ import org.bukkit.block.BlockState;
/*      */ import org.bukkit.block.CreatureSpawner;
/*      */ import org.bukkit.block.data.BlockData;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftLootTable;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftRaid;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftSound;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftStatistic;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftRaider;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftSpellcaster;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryCrafting;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftMetaBook;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.potion.CraftPotionUtil;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftDamageSource;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*      */ import org.bukkit.entity.AbstractHorse;
/*      */ import org.bukkit.entity.AnimalTamer;
/*      */ import org.bukkit.entity.Animals;
/*      */ import org.bukkit.entity.AreaEffectCloud;
/*      */ import org.bukkit.entity.Bat;
/*      */ import org.bukkit.entity.Creeper;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.EntityType;
/*      */ import org.bukkit.entity.ExperienceOrb;
/*      */ import org.bukkit.entity.HumanEntity;
/*      */ import org.bukkit.entity.Item;
/*      */ import org.bukkit.entity.LightningStrike;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.entity.PigZombie;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.entity.Projectile;
/*      */ import org.bukkit.entity.Raider;
/*      */ import org.bukkit.entity.Spellcaster;
/*      */ import org.bukkit.entity.Strider;
/*      */ import org.bukkit.entity.ThrownExpBottle;
/*      */ import org.bukkit.entity.ThrownPotion;
/*      */ import org.bukkit.entity.Vehicle;
/*      */ import org.bukkit.entity.Villager;
/*      */ import org.bukkit.event.Cancellable;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.block.Action;
/*      */ import org.bukkit.event.block.BlockDamageEvent;
/*      */ import org.bukkit.event.block.BlockDropItemEvent;
/*      */ import org.bukkit.event.block.BlockFadeEvent;
/*      */ import org.bukkit.event.block.BlockFormEvent;
/*      */ import org.bukkit.event.block.BlockGrowEvent;
/*      */ import org.bukkit.event.block.BlockIgniteEvent;
/*      */ import org.bukkit.event.block.BlockMultiPlaceEvent;
/*      */ import org.bukkit.event.block.BlockPhysicsEvent;
/*      */ import org.bukkit.event.block.BlockPlaceEvent;
/*      */ import org.bukkit.event.block.BlockRedstoneEvent;
/*      */ import org.bukkit.event.block.BlockShearEntityEvent;
/*      */ import org.bukkit.event.block.BlockSpreadEvent;
/*      */ import org.bukkit.event.block.EntityBlockFormEvent;
/*      */ import org.bukkit.event.block.FluidLevelChangeEvent;
/*      */ import org.bukkit.event.block.MoistureChangeEvent;
/*      */ import org.bukkit.event.block.NotePlayEvent;
/*      */ import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
/*      */ import org.bukkit.event.entity.ArrowBodyCountChangeEvent;
/*      */ import org.bukkit.event.entity.BatToggleSleepEvent;
/*      */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*      */ import org.bukkit.event.entity.CreeperPowerEvent;
/*      */ import org.bukkit.event.entity.EntityBreakDoorEvent;
/*      */ import org.bukkit.event.entity.EntityBreedEvent;
/*      */ import org.bukkit.event.entity.EntityChangeBlockEvent;
/*      */ import org.bukkit.event.entity.EntityDamageByBlockEvent;
/*      */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*      */ import org.bukkit.event.entity.EntityDamageEvent;
/*      */ import org.bukkit.event.entity.EntityDeathEvent;
/*      */ import org.bukkit.event.entity.EntityEnterLoveModeEvent;
/*      */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*      */ import org.bukkit.event.entity.EntityPlaceEvent;
/*      */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*      */ import org.bukkit.event.entity.EntityShootBowEvent;
/*      */ import org.bukkit.event.entity.EntitySpawnEvent;
/*      */ import org.bukkit.event.entity.EntitySpellCastEvent;
/*      */ import org.bukkit.event.entity.EntityTameEvent;
/*      */ import org.bukkit.event.entity.EntityTargetEvent;
/*      */ import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
/*      */ import org.bukkit.event.entity.EntityToggleGlideEvent;
/*      */ import org.bukkit.event.entity.EntityToggleSwimEvent;
/*      */ import org.bukkit.event.entity.EntityTransformEvent;
/*      */ import org.bukkit.event.entity.ExpBottleEvent;
/*      */ import org.bukkit.event.entity.FireworkExplodeEvent;
/*      */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*      */ import org.bukkit.event.entity.HorseJumpEvent;
/*      */ import org.bukkit.event.entity.ItemDespawnEvent;
/*      */ import org.bukkit.event.entity.ItemMergeEvent;
/*      */ import org.bukkit.event.entity.ItemSpawnEvent;
/*      */ import org.bukkit.event.entity.LingeringPotionSplashEvent;
/*      */ import org.bukkit.event.entity.PigZapEvent;
/*      */ import org.bukkit.event.entity.PlayerDeathEvent;
/*      */ import org.bukkit.event.entity.PlayerLeashEntityEvent;
/*      */ import org.bukkit.event.entity.PotionSplashEvent;
/*      */ import org.bukkit.event.entity.ProjectileHitEvent;
/*      */ import org.bukkit.event.entity.ProjectileLaunchEvent;
/*      */ import org.bukkit.event.entity.SpawnerSpawnEvent;
/*      */ import org.bukkit.event.entity.StriderTemperatureChangeEvent;
/*      */ import org.bukkit.event.entity.VillagerCareerChangeEvent;
/*      */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*      */ import org.bukkit.event.inventory.InventoryOpenEvent;
/*      */ import org.bukkit.event.inventory.PrepareAnvilEvent;
/*      */ import org.bukkit.event.inventory.PrepareItemCraftEvent;
/*      */ import org.bukkit.event.inventory.PrepareSmithingEvent;
/*      */ import org.bukkit.event.inventory.TradeSelectEvent;
/*      */ import org.bukkit.event.player.PlayerBedEnterEvent;
/*      */ import org.bukkit.event.player.PlayerBucketEmptyEvent;
/*      */ import org.bukkit.event.player.PlayerBucketFillEvent;
/*      */ import org.bukkit.event.player.PlayerEditBookEvent;
/*      */ import org.bukkit.event.player.PlayerEvent;
/*      */ import org.bukkit.event.player.PlayerExpChangeEvent;
/*      */ import org.bukkit.event.player.PlayerHarvestBlockEvent;
/*      */ import org.bukkit.event.player.PlayerInteractEvent;
/*      */ import org.bukkit.event.player.PlayerItemBreakEvent;
/*      */ import org.bukkit.event.player.PlayerItemMendEvent;
/*      */ import org.bukkit.event.player.PlayerLevelChangeEvent;
/*      */ import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
/*      */ import org.bukkit.event.player.PlayerShearEntityEvent;
/*      */ import org.bukkit.event.player.PlayerStatisticIncrementEvent;
/*      */ import org.bukkit.event.player.PlayerUnleashEntityEvent;
/*      */ import org.bukkit.event.raid.RaidFinishEvent;
/*      */ import org.bukkit.event.raid.RaidSpawnWaveEvent;
/*      */ import org.bukkit.event.raid.RaidStopEvent;
/*      */ import org.bukkit.event.raid.RaidTriggerEvent;
/*      */ import org.bukkit.event.server.ServerListPingEvent;
/*      */ import org.bukkit.event.vehicle.VehicleCreateEvent;
/*      */ import org.bukkit.event.world.LootGenerateEvent;
/*      */ import org.bukkit.inventory.CraftingInventory;
/*      */ import org.bukkit.inventory.EquipmentSlot;
/*      */ import org.bukkit.inventory.InventoryView;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.meta.BookMeta;
/*      */ import org.bukkit.inventory.meta.ItemMeta;
/*      */ import org.bukkit.potion.PotionEffect;
/*      */ 
/*      */ public class CraftEventFactory {
/*  231 */   public static final DamageSource MELTING = CraftDamageSource.copyOf(DamageSource.BURN);
/*  232 */   public static final DamageSource POISON = CraftDamageSource.copyOf(DamageSource.MAGIC);
/*      */   
/*      */   public static Block blockDamage;
/*      */   public static Entity entityDamage;
/*      */   
/*      */   private static boolean canBuild(World world, Player player, int x, int z) {
/*  238 */     int spawnSize = Bukkit.getServer().getSpawnRadius();
/*      */     
/*  240 */     if (world.getDimensionKey() != World.OVERWORLD) return true; 
/*  241 */     if (spawnSize <= 0) return true; 
/*  242 */     if (((CraftServer)Bukkit.getServer()).getHandle().getOPs().isEmpty()) return true; 
/*  243 */     if (player.isOp()) return true;
/*      */     
/*  245 */     BlockPosition chunkcoordinates = world.getSpawn();
/*      */     
/*  247 */     int distanceFromSpawn = Math.max(Math.abs(x - chunkcoordinates.getX()), Math.abs(z - chunkcoordinates.getZ()));
/*  248 */     return (distanceFromSpawn > spawnSize);
/*      */   }
/*      */   
/*      */   public static <T extends Event> T callEvent(T event) {
/*  252 */     Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*  253 */     return event;
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
/*      */   public static Either<EntityHuman.EnumBedResult, Unit> callPlayerBedEnterEvent(EntityHuman player, BlockPosition bed, Either<EntityHuman.EnumBedResult, Unit> nmsBedResult) {
/*  276 */     PlayerBedEnterEvent.BedEnterResult bedEnterResult = (PlayerBedEnterEvent.BedEnterResult)nmsBedResult.mapBoth((Function)new Function<EntityHuman.EnumBedResult, PlayerBedEnterEvent.BedEnterResult>() { public PlayerBedEnterEvent.BedEnterResult apply(EntityHuman.EnumBedResult t) { // Byte code:
/*      */             //   0: getstatic org/bukkit/craftbukkit/v1_16_R2/event/CraftEventFactory$2.$SwitchMap$net$minecraft$server$EntityHuman$EnumBedResult : [I
/*      */             //   3: aload_1
/*      */             //   4: invokevirtual ordinal : ()I
/*      */             //   7: iaload
/*      */             //   8: tableswitch default -> 56, 1 -> 40, 2 -> 44, 3 -> 48, 4 -> 52
/*      */             //   40: getstatic org/bukkit/event/player/PlayerBedEnterEvent$BedEnterResult.NOT_POSSIBLE_HERE : Lorg/bukkit/event/player/PlayerBedEnterEvent$BedEnterResult;
/*      */             //   43: areturn
/*      */             //   44: getstatic org/bukkit/event/player/PlayerBedEnterEvent$BedEnterResult.NOT_POSSIBLE_NOW : Lorg/bukkit/event/player/PlayerBedEnterEvent$BedEnterResult;
/*      */             //   47: areturn
/*      */             //   48: getstatic org/bukkit/event/player/PlayerBedEnterEvent$BedEnterResult.TOO_FAR_AWAY : Lorg/bukkit/event/player/PlayerBedEnterEvent$BedEnterResult;
/*      */             //   51: areturn
/*      */             //   52: getstatic org/bukkit/event/player/PlayerBedEnterEvent$BedEnterResult.NOT_SAFE : Lorg/bukkit/event/player/PlayerBedEnterEvent$BedEnterResult;
/*      */             //   55: areturn
/*      */             //   56: getstatic org/bukkit/event/player/PlayerBedEnterEvent$BedEnterResult.OTHER_PROBLEM : Lorg/bukkit/event/player/PlayerBedEnterEvent$BedEnterResult;
/*      */             //   59: areturn
/*      */             // Line number table:
/*      */             //   Java source line number -> byte code offset
/*      */             //   #263	-> 0
/*      */             //   #265	-> 40
/*      */             //   #267	-> 44
/*      */             //   #269	-> 48
/*      */             //   #271	-> 52
/*      */             //   #273	-> 56
/*      */             // Local variable table:
/*      */             //   start	length	slot	name	descriptor
/*      */             //   0	60	0	this	Lorg/bukkit/craftbukkit/v1_16_R2/event/CraftEventFactory$1;
/*  276 */             //   0	60	1	t	Lnet/minecraft/server/v1_16_R2/EntityHuman$EnumBedResult; } }, t -> PlayerBedEnterEvent.BedEnterResult.OK).map(Function.identity(), Function.identity());
/*      */     
/*  278 */     PlayerBedEnterEvent event = new PlayerBedEnterEvent((Player)player.getBukkitEntity(), (Block)CraftBlock.at((GeneratorAccess)player.world, bed), bedEnterResult);
/*  279 */     Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*      */     
/*  281 */     Event.Result result = event.useBed();
/*  282 */     if (result == Event.Result.ALLOW)
/*  283 */       return Either.right(Unit.INSTANCE); 
/*  284 */     if (result == Event.Result.DENY) {
/*  285 */       return Either.left(EntityHuman.EnumBedResult.OTHER_PROBLEM);
/*      */     }
/*      */     
/*  288 */     return nmsBedResult;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EntityEnterLoveModeEvent callEntityEnterLoveModeEvent(EntityHuman entityHuman, EntityAnimal entityAnimal, int loveTicks) {
/*  295 */     EntityEnterLoveModeEvent entityEnterLoveModeEvent = new EntityEnterLoveModeEvent((Animals)entityAnimal.getBukkitEntity(), (entityHuman != null) ? (HumanEntity)entityHuman.getBukkitEntity() : null, loveTicks);
/*  296 */     Bukkit.getPluginManager().callEvent((Event)entityEnterLoveModeEvent);
/*  297 */     return entityEnterLoveModeEvent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PlayerHarvestBlockEvent callPlayerHarvestBlockEvent(World world, BlockPosition blockposition, EntityHuman who, List<ItemStack> itemsToHarvest) {
/*  304 */     List<ItemStack> bukkitItemsToHarvest = new ArrayList<>((Collection<? extends ItemStack>)itemsToHarvest.stream().map(CraftItemStack::asBukkitCopy).collect(Collectors.toList()));
/*  305 */     Player player = (Player)who.getBukkitEntity();
/*  306 */     PlayerHarvestBlockEvent playerHarvestBlockEvent = new PlayerHarvestBlockEvent(player, (Block)CraftBlock.at((GeneratorAccess)world, blockposition), bukkitItemsToHarvest);
/*  307 */     Bukkit.getPluginManager().callEvent((Event)playerHarvestBlockEvent);
/*  308 */     return playerHarvestBlockEvent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static TradeSelectEvent callTradeSelectEvent(EntityPlayer player, int newIndex, ContainerMerchant merchant) {
/*  315 */     TradeSelectEvent tradeSelectEvent = new TradeSelectEvent((InventoryView)merchant.getBukkitView(), newIndex);
/*  316 */     Bukkit.getPluginManager().callEvent((Event)tradeSelectEvent);
/*  317 */     return tradeSelectEvent;
/*      */   }
/*      */ 
/*      */   
/*      */   public static BlockMultiPlaceEvent callBlockMultiPlaceEvent(WorldServer world, EntityHuman who, EnumHand hand, List<BlockState> blockStates, int clickedX, int clickedY, int clickedZ) {
/*      */     ItemStack item;
/*      */     EquipmentSlot equipmentSlot;
/*  324 */     CraftWorld craftWorld = world.getWorld();
/*  325 */     CraftServer craftServer = world.getServer();
/*  326 */     Player player = (Player)who.getBukkitEntity();
/*      */     
/*  328 */     Block blockClicked = craftWorld.getBlockAt(clickedX, clickedY, clickedZ);
/*      */     
/*  330 */     boolean canBuild = true;
/*  331 */     for (int i = 0; i < blockStates.size(); i++) {
/*  332 */       if (!canBuild((World)world, player, ((BlockState)blockStates.get(i)).getX(), ((BlockState)blockStates.get(i)).getZ())) {
/*  333 */         canBuild = false;
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  341 */     if (hand == EnumHand.MAIN_HAND) {
/*  342 */       item = player.getInventory().getItemInMainHand();
/*  343 */       equipmentSlot = EquipmentSlot.HAND;
/*      */     } else {
/*  345 */       item = player.getInventory().getItemInOffHand();
/*  346 */       equipmentSlot = EquipmentSlot.OFF_HAND;
/*      */     } 
/*      */     
/*  349 */     BlockMultiPlaceEvent event = new BlockMultiPlaceEvent(blockStates, blockClicked, item, player, canBuild, equipmentSlot);
/*      */     
/*  351 */     craftServer.getPluginManager().callEvent((Event)event);
/*      */     
/*  353 */     return event;
/*      */   } public static BlockPlaceEvent callBlockPlaceEvent(WorldServer world, EntityHuman who, EnumHand hand, BlockState replacedBlockState, int clickedX, int clickedY, int clickedZ) {
/*      */     ItemStack item;
/*      */     EquipmentSlot equipmentSlot;
/*  357 */     CraftWorld craftWorld = world.getWorld();
/*  358 */     CraftServer craftServer = world.getServer();
/*      */     
/*  360 */     Player player = (Player)who.getBukkitEntity();
/*      */     
/*  362 */     Block blockClicked = craftWorld.getBlockAt(clickedX, clickedY, clickedZ);
/*  363 */     Block placedBlock = replacedBlockState.getBlock();
/*      */     
/*  365 */     boolean canBuild = canBuild((World)world, player, placedBlock.getX(), placedBlock.getZ());
/*      */ 
/*      */ 
/*      */     
/*  369 */     if (hand == EnumHand.MAIN_HAND) {
/*  370 */       item = player.getInventory().getItemInMainHand();
/*  371 */       equipmentSlot = EquipmentSlot.HAND;
/*      */     } else {
/*  373 */       item = player.getInventory().getItemInOffHand();
/*  374 */       equipmentSlot = EquipmentSlot.OFF_HAND;
/*      */     } 
/*      */     
/*  377 */     BlockPlaceEvent event = new BlockPlaceEvent(placedBlock, replacedBlockState, blockClicked, item, player, canBuild, equipmentSlot);
/*  378 */     craftServer.getPluginManager().callEvent((Event)event);
/*      */     
/*  380 */     return event;
/*      */   }
/*      */   
/*      */   public static void handleBlockDropItemEvent(Block block, BlockState state, EntityPlayer player, List<EntityItem> items) {
/*  384 */     BlockDropItemEvent event = new BlockDropItemEvent(block, state, (Player)player.getBukkitEntity(), Lists.transform(items, item -> (Item)item.getBukkitEntity()));
/*  385 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */     
/*  387 */     if (!event.isCancelled()) {
/*  388 */       for (EntityItem item : items) {
/*  389 */         item.world.addEntity((Entity)item);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static EntityPlaceEvent callEntityPlaceEvent(ItemActionContext itemactioncontext, Entity entity) {
/*  395 */     return callEntityPlaceEvent(itemactioncontext.getWorld(), itemactioncontext.getClickPosition(), itemactioncontext.getClickedFace(), itemactioncontext.getEntity(), entity);
/*      */   }
/*      */   
/*      */   public static EntityPlaceEvent callEntityPlaceEvent(World world, BlockPosition clickPosition, EnumDirection clickedFace, EntityHuman human, Entity entity) {
/*  399 */     Player who = (human == null) ? null : (Player)human.getBukkitEntity();
/*  400 */     CraftBlock craftBlock = CraftBlock.at((GeneratorAccess)world, clickPosition);
/*  401 */     BlockFace blockFace = CraftBlock.notchToBlockFace(clickedFace);
/*      */     
/*  403 */     EntityPlaceEvent event = new EntityPlaceEvent((Entity)entity.getBukkitEntity(), who, (Block)craftBlock, blockFace);
/*  404 */     entity.world.getServer().getPluginManager().callEvent((Event)event);
/*      */     
/*  406 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PlayerBucketEmptyEvent callPlayerBucketEmptyEvent(WorldServer world, EntityHuman who, BlockPosition changed, BlockPosition clicked, EnumDirection clickedFace, ItemStack itemInHand) {
/*  413 */     return (PlayerBucketEmptyEvent)getPlayerBucketEvent(false, world, who, changed, clicked, clickedFace, itemInHand, Items.BUCKET);
/*      */   }
/*      */   
/*      */   public static PlayerBucketFillEvent callPlayerBucketFillEvent(WorldServer world, EntityHuman who, BlockPosition changed, BlockPosition clicked, EnumDirection clickedFace, ItemStack itemInHand, Item bucket) {
/*  417 */     return (PlayerBucketFillEvent)getPlayerBucketEvent(true, world, who, clicked, changed, clickedFace, itemInHand, bucket);
/*      */   }
/*      */ 
/*      */   
/*      */   private static PlayerEvent getPlayerBucketEvent(boolean isFilling, WorldServer world, EntityHuman who, BlockPosition changed, BlockPosition clicked, EnumDirection clickedFace, ItemStack itemstack, Item item) {
/*  422 */     return getPlayerBucketEvent(isFilling, (World)world, who, changed, clicked, clickedFace, itemstack, item, null);
/*      */   }
/*      */   
/*      */   public static PlayerBucketEmptyEvent callPlayerBucketEmptyEvent(World world, EntityHuman who, BlockPosition changed, BlockPosition clicked, EnumDirection clickedFace, ItemStack itemstack, EnumHand enumHand) {
/*  426 */     return (PlayerBucketEmptyEvent)getPlayerBucketEvent(false, world, who, changed, clicked, clickedFace, itemstack, Items.BUCKET, enumHand);
/*      */   }
/*      */   
/*      */   public static PlayerBucketFillEvent callPlayerBucketFillEvent(World world, EntityHuman who, BlockPosition changed, BlockPosition clicked, EnumDirection clickedFace, ItemStack itemInHand, Item bucket, EnumHand enumHand) {
/*  430 */     return (PlayerBucketFillEvent)getPlayerBucketEvent(true, world, who, clicked, changed, clickedFace, itemInHand, bucket, enumHand);
/*      */   }
/*      */   
/*      */   private static PlayerEvent getPlayerBucketEvent(boolean isFilling, World world, EntityHuman who, BlockPosition changed, BlockPosition clicked, EnumDirection clickedFace, ItemStack itemstack, Item item, EnumHand enumHand) {
/*      */     PlayerBucketEmptyEvent playerBucketEmptyEvent;
/*  435 */     Player player = (Player)who.getBukkitEntity();
/*  436 */     CraftItemStack itemInHand = CraftItemStack.asNewCraftStack(item);
/*  437 */     Material bucket = CraftMagicNumbers.getMaterial(itemstack.getItem());
/*      */     
/*  439 */     CraftServer craftServer = (CraftServer)player.getServer();
/*      */     
/*  441 */     CraftBlock craftBlock1 = CraftBlock.at((GeneratorAccess)world, changed);
/*  442 */     CraftBlock craftBlock2 = CraftBlock.at((GeneratorAccess)world, clicked);
/*  443 */     BlockFace blockFace = CraftBlock.notchToBlockFace(clickedFace);
/*      */ 
/*      */     
/*  446 */     if (isFilling) {
/*  447 */       PlayerBucketFillEvent playerBucketFillEvent = new PlayerBucketFillEvent(player, (Block)craftBlock1, (Block)craftBlock2, blockFace, bucket, (ItemStack)itemInHand, (enumHand == null) ? null : ((enumHand == EnumHand.OFF_HAND) ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND));
/*  448 */       playerBucketFillEvent.setCancelled(!canBuild(world, player, changed.getX(), changed.getZ()));
/*      */     } else {
/*  450 */       playerBucketEmptyEvent = new PlayerBucketEmptyEvent(player, (Block)craftBlock1, (Block)craftBlock2, blockFace, bucket, (ItemStack)itemInHand, (enumHand == null) ? null : ((enumHand == EnumHand.OFF_HAND) ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND));
/*  451 */       playerBucketEmptyEvent.setCancelled(!canBuild(world, player, changed.getX(), changed.getZ()));
/*      */     } 
/*      */     
/*  454 */     craftServer.getPluginManager().callEvent((Event)playerBucketEmptyEvent);
/*      */     
/*  456 */     return (PlayerEvent)playerBucketEmptyEvent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PlayerInteractEvent callPlayerInteractEvent(EntityHuman who, Action action, ItemStack itemstack, EnumHand hand) {
/*  463 */     if (action != Action.LEFT_CLICK_AIR && action != Action.RIGHT_CLICK_AIR) {
/*  464 */       throw new AssertionError(String.format("%s performing %s with %s", new Object[] { who, action, itemstack }));
/*      */     }
/*  466 */     return callPlayerInteractEvent(who, action, null, EnumDirection.SOUTH, itemstack, hand);
/*      */   }
/*      */   
/*      */   public static PlayerInteractEvent callPlayerInteractEvent(EntityHuman who, Action action, BlockPosition position, EnumDirection direction, ItemStack itemstack, EnumHand hand) {
/*  470 */     return callPlayerInteractEvent(who, action, position, direction, itemstack, false, hand);
/*      */   }
/*      */   
/*      */   public static PlayerInteractEvent callPlayerInteractEvent(EntityHuman who, Action action, BlockPosition position, EnumDirection direction, ItemStack itemstack, boolean cancelledBlock, EnumHand hand) {
/*  474 */     Player player = (who == null) ? null : (Player)who.getBukkitEntity();
/*  475 */     CraftItemStack itemInHand = CraftItemStack.asCraftMirror(itemstack);
/*      */     
/*  477 */     CraftWorld craftWorld = (CraftWorld)player.getWorld();
/*  478 */     CraftServer craftServer = (CraftServer)player.getServer();
/*      */     
/*  480 */     Block blockClicked = null;
/*  481 */     if (position != null) {
/*  482 */       blockClicked = craftWorld.getBlockAt(position.getX(), position.getY(), position.getZ());
/*      */     } else {
/*  484 */       switch (action) {
/*      */         case FALL_ONE_CM:
/*  486 */           action = Action.LEFT_CLICK_AIR;
/*      */           break;
/*      */         case BOAT_ONE_CM:
/*  489 */           action = Action.RIGHT_CLICK_AIR;
/*      */           break;
/*      */       } 
/*      */     } 
/*  493 */     BlockFace blockFace = CraftBlock.notchToBlockFace(direction);
/*      */     
/*  495 */     if (itemInHand.getType() == Material.AIR || itemInHand.getAmount() == 0) {
/*  496 */       itemInHand = null;
/*      */     }
/*      */     
/*  499 */     PlayerInteractEvent event = new PlayerInteractEvent(player, action, (ItemStack)itemInHand, blockClicked, blockFace, (hand == null) ? null : ((hand == EnumHand.OFF_HAND) ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND));
/*  500 */     if (cancelledBlock) {
/*  501 */       event.setUseInteractedBlock(Event.Result.DENY);
/*      */     }
/*  503 */     craftServer.getPluginManager().callEvent((Event)event);
/*      */     
/*  505 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EntityTransformEvent callEntityTransformEvent(EntityLiving original, EntityLiving coverted, EntityTransformEvent.TransformReason transformReason) {
/*  512 */     return callEntityTransformEvent(original, Collections.singletonList(coverted), transformReason);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EntityTransformEvent callEntityTransformEvent(EntityLiving original, List<EntityLiving> convertedList, EntityTransformEvent.TransformReason convertType) {
/*  519 */     List<Entity> list = new ArrayList<>();
/*  520 */     for (EntityLiving entityLiving : convertedList) {
/*  521 */       list.add(entityLiving.getBukkitEntity());
/*      */     }
/*      */     
/*  524 */     EntityTransformEvent event = new EntityTransformEvent((Entity)original.getBukkitEntity(), list, convertType);
/*  525 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */     
/*  527 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EntityShootBowEvent callEntityShootBowEvent(EntityLiving who, ItemStack bow, ItemStack consumableItem, Entity entityArrow, EnumHand hand, float force, boolean consumeItem) {
/*  534 */     LivingEntity shooter = (LivingEntity)who.getBukkitEntity();
/*  535 */     CraftItemStack itemInHand = CraftItemStack.asCraftMirror(bow);
/*  536 */     CraftItemStack itemConsumable = CraftItemStack.asCraftMirror(consumableItem);
/*  537 */     CraftEntity craftEntity = entityArrow.getBukkitEntity();
/*  538 */     EquipmentSlot handSlot = (hand == EnumHand.MAIN_HAND) ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND;
/*      */     
/*  540 */     if (itemInHand != null && (itemInHand.getType() == Material.AIR || itemInHand.getAmount() == 0)) {
/*  541 */       itemInHand = null;
/*      */     }
/*      */     
/*  544 */     EntityShootBowEvent event = new EntityShootBowEvent(shooter, (ItemStack)itemInHand, (ItemStack)itemConsumable, (Entity)craftEntity, handSlot, force, consumeItem);
/*  545 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */     
/*  547 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static VillagerCareerChangeEvent callVillagerCareerChangeEvent(EntityVillager vilager, Villager.Profession future, VillagerCareerChangeEvent.ChangeReason reason) {
/*  554 */     VillagerCareerChangeEvent event = new VillagerCareerChangeEvent((Villager)vilager.getBukkitEntity(), future, reason);
/*  555 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */     
/*  557 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BlockDamageEvent callBlockDamageEvent(EntityHuman who, int x, int y, int z, ItemStack itemstack, boolean instaBreak) {
/*  564 */     Player player = (who == null) ? null : (Player)who.getBukkitEntity();
/*  565 */     CraftItemStack itemInHand = CraftItemStack.asCraftMirror(itemstack);
/*      */     
/*  567 */     CraftWorld craftWorld = (CraftWorld)player.getWorld();
/*  568 */     CraftServer craftServer = (CraftServer)player.getServer();
/*      */     
/*  570 */     Block blockClicked = craftWorld.getBlockAt(x, y, z);
/*      */     
/*  572 */     BlockDamageEvent event = new BlockDamageEvent(player, blockClicked, (ItemStack)itemInHand, instaBreak);
/*  573 */     craftServer.getPluginManager().callEvent((Event)event);
/*      */     
/*  575 */     return event;
/*      */   }
/*      */   public static boolean doEntityAddEventCalling(World world, Entity entity, CreatureSpawnEvent.SpawnReason spawnReason) {
/*      */     EntitySpawnEvent entitySpawnEvent;
/*  579 */     if (entity == null) return false;
/*      */     
/*  581 */     Cancellable event = null;
/*  582 */     if (entity instanceof EntityLiving && !(entity instanceof EntityPlayer)) {
/*  583 */       boolean isAnimal = (entity instanceof EntityAnimal || entity instanceof net.minecraft.server.v1_16_R2.EntityWaterAnimal || entity instanceof net.minecraft.server.v1_16_R2.EntityGolem);
/*  584 */       boolean isMonster = (entity instanceof net.minecraft.server.v1_16_R2.EntityMonster || entity instanceof net.minecraft.server.v1_16_R2.EntityGhast || entity instanceof net.minecraft.server.v1_16_R2.EntitySlime);
/*  585 */       boolean isNpc = entity instanceof net.minecraft.server.v1_16_R2.NPC;
/*      */       
/*  587 */       if (spawnReason != CreatureSpawnEvent.SpawnReason.CUSTOM && ((
/*  588 */         isAnimal && !world.getWorld().getAllowAnimals()) || (isMonster && !world.getWorld().getAllowMonsters()) || (isNpc && !world.getServer().getServer().getSpawnNPCs()))) {
/*  589 */         entity.dead = true;
/*  590 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*  594 */       CreatureSpawnEvent creatureSpawnEvent = callCreatureSpawnEvent((EntityLiving)entity, spawnReason);
/*  595 */     } else if (entity instanceof EntityItem) {
/*  596 */       ItemSpawnEvent itemSpawnEvent = callItemSpawnEvent((EntityItem)entity);
/*  597 */     } else if (entity.getBukkitEntity() instanceof Projectile) {
/*      */       
/*  599 */       ProjectileLaunchEvent projectileLaunchEvent = callProjectileLaunchEvent(entity);
/*  600 */     } else if (entity.getBukkitEntity() instanceof Vehicle) {
/*  601 */       VehicleCreateEvent vehicleCreateEvent = callVehicleCreateEvent(entity);
/*      */     }
/*  603 */     else if (entity instanceof EntityExperienceOrb) {
/*  604 */       EntityExperienceOrb xp = (EntityExperienceOrb)entity;
/*  605 */       double radius = world.spigotConfig.expMerge;
/*  606 */       if (radius > 0.0D) {
/*      */         
/*  608 */         int maxValue = world.paperConfig.expMergeMaxValue;
/*  609 */         boolean mergeUnconditionally = (world.paperConfig.expMergeMaxValue <= 0);
/*  610 */         if (mergeUnconditionally || xp.value < maxValue)
/*      */         {
/*  612 */           List<Entity> entities = world.getEntities(entity, entity.getBoundingBox().grow(radius, radius, radius));
/*  613 */           for (Entity e : entities) {
/*  614 */             if (e instanceof EntityExperienceOrb) {
/*  615 */               EntityExperienceOrb loopItem = (EntityExperienceOrb)e;
/*      */               
/*  617 */               if (!loopItem.dead && (maxValue <= 0 || loopItem.value < maxValue) && (new ExperienceOrbMergeEvent((ExperienceOrb)entity.getBukkitEntity(), (ExperienceOrb)loopItem.getBukkitEntity())).callEvent()) {
/*  618 */                 long newTotal = xp.value + loopItem.value;
/*  619 */                 if ((int)newTotal < 0)
/*  620 */                   continue;  if (maxValue > 0 && newTotal > maxValue) {
/*  621 */                   loopItem.value = (int)(newTotal - maxValue);
/*  622 */                   xp.value = maxValue; continue;
/*      */                 } 
/*  624 */                 xp.value += loopItem.value;
/*  625 */                 loopItem.die();
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } 
/*  635 */     } else if (!(entity instanceof EntityPlayer)) {
/*  636 */       entitySpawnEvent = callEntitySpawnEvent(entity);
/*      */     } 
/*      */     
/*  639 */     if (entitySpawnEvent != null && (entitySpawnEvent.isCancelled() || entity.dead)) {
/*  640 */       Entity vehicle = entity.getVehicle();
/*  641 */       if (vehicle != null) {
/*  642 */         vehicle.dead = true;
/*      */       }
/*  644 */       for (Entity passenger : entity.getAllPassengers()) {
/*  645 */         passenger.dead = true;
/*      */       }
/*  647 */       entity.dead = true;
/*  648 */       return false;
/*      */     } 
/*      */     
/*  651 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EntitySpawnEvent callEntitySpawnEvent(Entity entity) {
/*  658 */     CraftEntity craftEntity = entity.getBukkitEntity();
/*      */     
/*  660 */     EntitySpawnEvent event = new EntitySpawnEvent((Entity)craftEntity);
/*  661 */     craftEntity.getServer().getPluginManager().callEvent((Event)event);
/*  662 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CreatureSpawnEvent callCreatureSpawnEvent(EntityLiving entityliving, CreatureSpawnEvent.SpawnReason spawnReason) {
/*  669 */     LivingEntity entity = (LivingEntity)entityliving.getBukkitEntity();
/*  670 */     CraftServer craftServer = (CraftServer)entity.getServer();
/*      */     
/*  672 */     CreatureSpawnEvent event = new CreatureSpawnEvent(entity, spawnReason);
/*  673 */     craftServer.getPluginManager().callEvent((Event)event);
/*  674 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EntityTameEvent callEntityTameEvent(EntityInsentient entity, EntityHuman tamer) {
/*  681 */     CraftEntity craftEntity = entity.getBukkitEntity();
/*  682 */     CraftHumanEntity craftHumanEntity = (tamer != null) ? tamer.getBukkitEntity() : null;
/*  683 */     CraftServer craftServer = (CraftServer)craftEntity.getServer();
/*      */     
/*  685 */     entity.persistent = true;
/*      */     
/*  687 */     EntityTameEvent event = new EntityTameEvent((LivingEntity)craftEntity, (AnimalTamer)craftHumanEntity);
/*  688 */     craftServer.getPluginManager().callEvent((Event)event);
/*  689 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ItemSpawnEvent callItemSpawnEvent(EntityItem entityitem) {
/*  696 */     Item entity = (Item)entityitem.getBukkitEntity();
/*  697 */     CraftServer craftServer = (CraftServer)entity.getServer();
/*      */     
/*  699 */     ItemSpawnEvent event = new ItemSpawnEvent(entity);
/*      */     
/*  701 */     craftServer.getPluginManager().callEvent((Event)event);
/*  702 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ItemDespawnEvent callItemDespawnEvent(EntityItem entityitem) {
/*  709 */     Item entity = (Item)entityitem.getBukkitEntity();
/*      */     
/*  711 */     ItemDespawnEvent event = new ItemDespawnEvent(entity, entity.getLocation());
/*      */     
/*  713 */     entity.getServer().getPluginManager().callEvent((Event)event);
/*  714 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ItemMergeEvent callItemMergeEvent(EntityItem merging, EntityItem mergingWith) {
/*  721 */     Item entityMerging = (Item)merging.getBukkitEntity();
/*  722 */     Item entityMergingWith = (Item)mergingWith.getBukkitEntity();
/*      */     
/*  724 */     ItemMergeEvent event = new ItemMergeEvent(entityMerging, entityMergingWith);
/*      */     
/*  726 */     Bukkit.getPluginManager().callEvent((Event)event);
/*  727 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PotionSplashEvent callPotionSplashEvent(EntityPotion potion, Map<LivingEntity, Double> affectedEntities) {
/*  734 */     ThrownPotion thrownPotion = (ThrownPotion)potion.getBukkitEntity();
/*      */     
/*  736 */     PotionSplashEvent event = new PotionSplashEvent(thrownPotion, affectedEntities);
/*  737 */     Bukkit.getPluginManager().callEvent((Event)event);
/*  738 */     return event;
/*      */   }
/*      */   
/*      */   public static LingeringPotionSplashEvent callLingeringPotionSplashEvent(EntityPotion potion, EntityAreaEffectCloud cloud) {
/*  742 */     ThrownPotion thrownPotion = (ThrownPotion)potion.getBukkitEntity();
/*  743 */     AreaEffectCloud effectCloud = (AreaEffectCloud)cloud.getBukkitEntity();
/*      */     
/*  745 */     LingeringPotionSplashEvent event = new LingeringPotionSplashEvent(thrownPotion, effectCloud);
/*  746 */     Bukkit.getPluginManager().callEvent((Event)event);
/*  747 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BlockFadeEvent callBlockFadeEvent(GeneratorAccess world, BlockPosition pos, IBlockData newBlock) {
/*  754 */     CraftBlockState state = CraftBlockState.getBlockState(world, pos);
/*  755 */     state.setData(newBlock);
/*      */     
/*  757 */     BlockFadeEvent event = new BlockFadeEvent((Block)state.getBlock(), (BlockState)state);
/*  758 */     Bukkit.getPluginManager().callEvent((Event)event);
/*  759 */     return event;
/*      */   }
/*      */   
/*      */   public static boolean handleMoistureChangeEvent(World world, BlockPosition pos, IBlockData newBlock, int flag) {
/*  763 */     CraftBlockState state = CraftBlockState.getBlockState(world, pos, flag);
/*  764 */     state.setData(newBlock);
/*      */     
/*  766 */     MoistureChangeEvent event = new MoistureChangeEvent((Block)state.getBlock(), (BlockState)state);
/*  767 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */     
/*  769 */     if (!event.isCancelled()) {
/*  770 */       state.update(true);
/*      */     }
/*  772 */     return !event.isCancelled();
/*      */   }
/*      */   
/*      */   public static boolean handleBlockSpreadEvent(World world, BlockPosition source, BlockPosition target, IBlockData block) {
/*  776 */     return handleBlockSpreadEvent(world, source, target, block, 2);
/*      */   }
/*      */   
/*      */   public static boolean handleBlockSpreadEvent(World world, BlockPosition source, BlockPosition target, IBlockData block, int flag) {
/*  780 */     CraftBlockState state = CraftBlockState.getBlockState(world, target, flag);
/*  781 */     state.setData(block);
/*      */     
/*  783 */     BlockSpreadEvent event = new BlockSpreadEvent(world.getWorld().getBlockAt(target.getX(), target.getY(), target.getZ()), world.getWorld().getBlockAt(source.getX(), source.getY(), source.getZ()), (BlockState)state);
/*  784 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */     
/*  786 */     if (!event.isCancelled()) {
/*  787 */       state.update(true);
/*      */     }
/*  789 */     return !event.isCancelled();
/*      */   }
/*      */   
/*      */   public static EntityDeathEvent callEntityDeathEvent(EntityLiving victim) {
/*  793 */     return callEntityDeathEvent(victim, new ArrayList<>(0));
/*      */   }
/*      */   
/*      */   public static EntityDeathEvent callEntityDeathEvent(EntityLiving victim, List<ItemStack> drops) {
/*  797 */     CraftLivingEntity entity = (CraftLivingEntity)victim.getBukkitEntity();
/*  798 */     EntityDeathEvent event = new EntityDeathEvent((LivingEntity)entity, drops, victim.getExpReward());
/*  799 */     populateFields(victim, event);
/*  800 */     CraftWorld world = (CraftWorld)entity.getWorld();
/*  801 */     Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*      */ 
/*      */     
/*  804 */     if (event.isCancelled()) {
/*  805 */       return event;
/*      */     }
/*  807 */     playDeathSound(victim, event);
/*      */     
/*  809 */     victim.expToDrop = event.getDroppedExp();
/*      */     
/*  811 */     for (ItemStack stack : event.getDrops()) {
/*  812 */       if (stack == null || stack.getType() == Material.AIR || stack.getAmount() == 0)
/*      */         continue; 
/*  814 */       world.dropItem(entity.getLocation(), stack);
/*  815 */       if (stack instanceof CraftItemStack) stack.setAmount(0);
/*      */     
/*      */     } 
/*  818 */     return event;
/*      */   }
/*      */   
/*      */   public static PlayerDeathEvent callPlayerDeathEvent(EntityPlayer victim, List<ItemStack> drops, String deathMessage, boolean keepInventory) {
/*  822 */     CraftPlayer entity = victim.getBukkitEntity();
/*  823 */     PlayerDeathEvent event = new PlayerDeathEvent((Player)entity, drops, victim.getExpReward(), 0, deathMessage);
/*  824 */     event.setKeepInventory(keepInventory);
/*  825 */     populateFields((EntityLiving)victim, (EntityDeathEvent)event);
/*  826 */     World world = entity.getWorld();
/*  827 */     Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*      */     
/*  829 */     if (event.isCancelled()) {
/*  830 */       return event;
/*      */     }
/*  832 */     playDeathSound((EntityLiving)victim, (EntityDeathEvent)event);
/*      */ 
/*      */     
/*  835 */     victim.keepLevel = event.getKeepLevel();
/*  836 */     victim.newLevel = event.getNewLevel();
/*  837 */     victim.newTotalExp = event.getNewTotalExp();
/*  838 */     victim.expToDrop = event.getDroppedExp();
/*  839 */     victim.newExp = event.getNewExp();
/*      */     
/*  841 */     for (ItemStack stack : event.getDrops()) {
/*  842 */       if (stack == null || stack.getType() == Material.AIR)
/*      */         continue; 
/*  844 */       world.dropItem(entity.getLocation(), stack);
/*      */     } 
/*      */     
/*  847 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void populateFields(EntityLiving victim, EntityDeathEvent event) {
/*  853 */     event.setReviveHealth(event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
/*  854 */     event.setShouldPlayDeathSound((!victim.silentDeath && !victim.isSilent()));
/*  855 */     SoundEffect soundEffect = victim.getDeathSoundEffect();
/*  856 */     event.setDeathSound((soundEffect != null) ? CraftSound.getSoundByEffect(soundEffect) : null);
/*  857 */     event.setDeathSoundCategory(SoundCategory.valueOf(victim.getSoundCategory().name()));
/*  858 */     event.setDeathSoundVolume(victim.getDeathSoundVolume());
/*  859 */     event.setDeathSoundPitch(victim.getSoundPitch());
/*      */   }
/*      */ 
/*      */   
/*      */   private static void playDeathSound(EntityLiving victim, EntityDeathEvent event) {
/*  864 */     if (event.shouldPlayDeathSound() && event.getDeathSound() != null && event.getDeathSoundCategory() != null) {
/*  865 */       EntityHuman source = (victim instanceof EntityHuman) ? (EntityHuman)victim : null;
/*  866 */       double x = event.getEntity().getLocation().getX();
/*  867 */       double y = event.getEntity().getLocation().getY();
/*  868 */       double z = event.getEntity().getLocation().getZ();
/*  869 */       SoundEffect soundEffect = CraftSound.getSoundEffect(event.getDeathSound());
/*  870 */       SoundCategory soundCategory = SoundCategory.valueOf(event.getDeathSoundCategory().name());
/*  871 */       victim.world.playSound(source, x, y, z, soundEffect, soundCategory, event.getDeathSoundVolume(), event.getDeathSoundPitch());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ServerListPingEvent callServerListPingEvent(Server craftServer, InetAddress address, String motd, int numPlayers, int maxPlayers) {
/*  879 */     ServerListPingEvent event = new ServerListPingEvent(address, motd, numPlayers, maxPlayers);
/*  880 */     craftServer.getPluginManager().callEvent((Event)event);
/*  881 */     return event;
/*      */   }
/*      */   
/*      */   private static EntityDamageEvent handleEntityDamageEvent(Entity entity, DamageSource source, Map<EntityDamageEvent.DamageModifier, Double> modifiers, Map<EntityDamageEvent.DamageModifier, Function<? super Double, Double>> modifierFunctions) {
/*  885 */     return handleEntityDamageEvent(entity, source, modifiers, modifierFunctions, false);
/*      */   }
/*      */   
/*      */   private static EntityDamageEvent handleEntityDamageEvent(Entity entity, DamageSource source, Map<EntityDamageEvent.DamageModifier, Double> modifiers, Map<EntityDamageEvent.DamageModifier, Function<? super Double, Double>> modifierFunctions, boolean cancelled) {
/*  889 */     if (source.isExplosion()) {
/*      */       EntityDamageByEntityEvent entityDamageByEntityEvent;
/*  891 */       Entity damager = entityDamage;
/*  892 */       entityDamage = null;
/*      */       
/*  894 */       if (damager == null)
/*  895 */       { EntityDamageByBlockEvent entityDamageByBlockEvent = new EntityDamageByBlockEvent(null, (Entity)entity.getBukkitEntity(), EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, modifiers, modifierFunctions); }
/*  896 */       else { EntityDamageEvent.DamageCause damageCause; if (entity instanceof net.minecraft.server.v1_16_R2.EntityEnderDragon);
/*      */ 
/*      */         
/*  899 */         if (damager instanceof org.bukkit.entity.TNTPrimed) {
/*  900 */           damageCause = EntityDamageEvent.DamageCause.BLOCK_EXPLOSION;
/*      */         } else {
/*  902 */           damageCause = EntityDamageEvent.DamageCause.ENTITY_EXPLOSION;
/*      */         } 
/*  904 */         entityDamageByEntityEvent = new EntityDamageByEntityEvent((Entity)damager.getBukkitEntity(), (Entity)entity.getBukkitEntity(), damageCause, modifiers, modifierFunctions); }
/*      */       
/*  906 */       entityDamageByEntityEvent.setCancelled(cancelled);
/*      */       
/*  908 */       callEvent(entityDamageByEntityEvent);
/*      */       
/*  910 */       if (!entityDamageByEntityEvent.isCancelled()) {
/*  911 */         entityDamageByEntityEvent.getEntity().setLastDamageCause((EntityDamageEvent)entityDamageByEntityEvent);
/*      */       }
/*  913 */       return (EntityDamageEvent)entityDamageByEntityEvent;
/*  914 */     }  if (source instanceof net.minecraft.server.v1_16_R2.EntityDamageSource) {
/*  915 */       Entity damager = source.getEntity();
/*  916 */       EntityDamageEvent.DamageCause damageCause = source.isSweep() ? EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK : EntityDamageEvent.DamageCause.ENTITY_ATTACK;
/*      */       
/*  918 */       if (source instanceof EntityDamageSourceIndirect) {
/*  919 */         damager = ((EntityDamageSourceIndirect)source).getProximateDamageSource();
/*  920 */         if (damager.getBukkitEntity() instanceof ThrownPotion) {
/*  921 */           damageCause = EntityDamageEvent.DamageCause.MAGIC;
/*  922 */         } else if (damager.getBukkitEntity() instanceof Projectile) {
/*  923 */           damageCause = EntityDamageEvent.DamageCause.PROJECTILE;
/*      */         } 
/*  925 */       } else if ("thorns".equals(source.translationIndex)) {
/*  926 */         damageCause = EntityDamageEvent.DamageCause.THORNS;
/*      */       } 
/*      */       
/*  929 */       return callEntityDamageEvent(damager, entity, damageCause, modifiers, modifierFunctions, cancelled);
/*  930 */     }  if (source == DamageSource.OUT_OF_WORLD) {
/*  931 */       EntityDamageByBlockEvent entityDamageByBlockEvent = new EntityDamageByBlockEvent(null, (Entity)entity.getBukkitEntity(), EntityDamageEvent.DamageCause.VOID, modifiers, modifierFunctions);
/*  932 */       entityDamageByBlockEvent.setCancelled(cancelled);
/*  933 */       callEvent(entityDamageByBlockEvent);
/*  934 */       if (!entityDamageByBlockEvent.isCancelled()) {
/*  935 */         entityDamageByBlockEvent.getEntity().setLastDamageCause((EntityDamageEvent)entityDamageByBlockEvent);
/*      */       }
/*  937 */       return (EntityDamageEvent)entityDamageByBlockEvent;
/*  938 */     }  if (source == DamageSource.LAVA) {
/*  939 */       EntityDamageByBlockEvent entityDamageByBlockEvent = new EntityDamageByBlockEvent(null, (Entity)entity.getBukkitEntity(), EntityDamageEvent.DamageCause.LAVA, modifiers, modifierFunctions);
/*  940 */       entityDamageByBlockEvent.setCancelled(cancelled);
/*  941 */       callEvent(entityDamageByBlockEvent);
/*  942 */       if (!entityDamageByBlockEvent.isCancelled()) {
/*  943 */         entityDamageByBlockEvent.getEntity().setLastDamageCause((EntityDamageEvent)entityDamageByBlockEvent);
/*      */       }
/*  945 */       return (EntityDamageEvent)entityDamageByBlockEvent;
/*  946 */     }  if (blockDamage != null) {
/*  947 */       EntityDamageEvent.DamageCause damageCause = null;
/*  948 */       Block damager = blockDamage;
/*  949 */       blockDamage = null;
/*  950 */       if (source == DamageSource.CACTUS || source == DamageSource.SWEET_BERRY_BUSH) {
/*  951 */         damageCause = EntityDamageEvent.DamageCause.CONTACT;
/*  952 */       } else if (source == DamageSource.HOT_FLOOR) {
/*  953 */         damageCause = EntityDamageEvent.DamageCause.HOT_FLOOR;
/*  954 */       } else if (source == DamageSource.MAGIC) {
/*  955 */         damageCause = EntityDamageEvent.DamageCause.MAGIC;
/*      */       } else {
/*  957 */         throw new IllegalStateException(String.format("Unhandled damage of %s by %s from %s", new Object[] { entity, damager, source.translationIndex }));
/*      */       } 
/*  959 */       EntityDamageByBlockEvent entityDamageByBlockEvent = new EntityDamageByBlockEvent(damager, (Entity)entity.getBukkitEntity(), damageCause, modifiers, modifierFunctions);
/*  960 */       entityDamageByBlockEvent.setCancelled(cancelled);
/*  961 */       callEvent(entityDamageByBlockEvent);
/*  962 */       if (!entityDamageByBlockEvent.isCancelled()) {
/*  963 */         entityDamageByBlockEvent.getEntity().setLastDamageCause((EntityDamageEvent)entityDamageByBlockEvent);
/*      */       }
/*  965 */       return (EntityDamageEvent)entityDamageByBlockEvent;
/*  966 */     }  if (entityDamage != null) {
/*  967 */       EntityDamageEvent.DamageCause damageCause = null;
/*  968 */       CraftEntity damager = entityDamage.getBukkitEntity();
/*  969 */       entityDamage = null;
/*  970 */       if (source == DamageSource.ANVIL || source == DamageSource.FALLING_BLOCK) {
/*  971 */         damageCause = EntityDamageEvent.DamageCause.FALLING_BLOCK;
/*  972 */       } else if (damager instanceof LightningStrike) {
/*  973 */         damageCause = EntityDamageEvent.DamageCause.LIGHTNING;
/*  974 */       } else if (source == DamageSource.FALL) {
/*  975 */         damageCause = EntityDamageEvent.DamageCause.FALL;
/*  976 */       } else if (source == DamageSource.DRAGON_BREATH) {
/*  977 */         damageCause = EntityDamageEvent.DamageCause.DRAGON_BREATH;
/*  978 */       } else if (source == DamageSource.MAGIC) {
/*  979 */         damageCause = EntityDamageEvent.DamageCause.MAGIC;
/*      */       } else {
/*  981 */         throw new IllegalStateException(String.format("Unhandled damage of %s by %s from %s", new Object[] { entity, damager.getHandle(), source.translationIndex }));
/*      */       } 
/*  983 */       EntityDamageByEntityEvent entityDamageByEntityEvent = new EntityDamageByEntityEvent((Entity)damager, (Entity)entity.getBukkitEntity(), damageCause, modifiers, modifierFunctions);
/*  984 */       entityDamageByEntityEvent.setCancelled(cancelled);
/*  985 */       callEvent(entityDamageByEntityEvent);
/*  986 */       if (!entityDamageByEntityEvent.isCancelled()) {
/*  987 */         entityDamageByEntityEvent.getEntity().setLastDamageCause((EntityDamageEvent)entityDamageByEntityEvent);
/*      */       }
/*  989 */       return (EntityDamageEvent)entityDamageByEntityEvent;
/*      */     } 
/*      */     
/*  992 */     EntityDamageEvent.DamageCause cause = null;
/*  993 */     if (source == DamageSource.FIRE) {
/*  994 */       cause = EntityDamageEvent.DamageCause.FIRE;
/*  995 */     } else if (source == DamageSource.STARVE) {
/*  996 */       cause = EntityDamageEvent.DamageCause.STARVATION;
/*  997 */     } else if (source == DamageSource.WITHER) {
/*  998 */       cause = EntityDamageEvent.DamageCause.WITHER;
/*  999 */     } else if (source == DamageSource.STUCK) {
/* 1000 */       cause = EntityDamageEvent.DamageCause.SUFFOCATION;
/* 1001 */     } else if (source == DamageSource.DROWN) {
/* 1002 */       cause = EntityDamageEvent.DamageCause.DROWNING;
/* 1003 */     } else if (source == DamageSource.BURN) {
/* 1004 */       cause = EntityDamageEvent.DamageCause.FIRE_TICK;
/* 1005 */     } else if (source == MELTING) {
/* 1006 */       cause = EntityDamageEvent.DamageCause.MELTING;
/* 1007 */     } else if (source == POISON) {
/* 1008 */       cause = EntityDamageEvent.DamageCause.POISON;
/* 1009 */     } else if (source == DamageSource.MAGIC) {
/* 1010 */       cause = EntityDamageEvent.DamageCause.MAGIC;
/* 1011 */     } else if (source == DamageSource.FALL) {
/* 1012 */       cause = EntityDamageEvent.DamageCause.FALL;
/* 1013 */     } else if (source == DamageSource.FLY_INTO_WALL) {
/* 1014 */       cause = EntityDamageEvent.DamageCause.FLY_INTO_WALL;
/* 1015 */     } else if (source == DamageSource.CRAMMING) {
/* 1016 */       cause = EntityDamageEvent.DamageCause.CRAMMING;
/* 1017 */     } else if (source == DamageSource.DRYOUT) {
/* 1018 */       cause = EntityDamageEvent.DamageCause.DRYOUT;
/* 1019 */     } else if (source == DamageSource.GENERIC) {
/* 1020 */       cause = EntityDamageEvent.DamageCause.CUSTOM;
/*      */     } 
/*      */     
/* 1023 */     if (cause != null) {
/* 1024 */       return callEntityDamageEvent(null, entity, cause, modifiers, modifierFunctions, cancelled);
/*      */     }
/*      */     
/* 1027 */     throw new IllegalStateException(String.format("Unhandled damage of %s from %s", new Object[] { entity, source.translationIndex }));
/*      */   }
/*      */   
/*      */   private static EntityDamageEvent callEntityDamageEvent(Entity damager, Entity damagee, EntityDamageEvent.DamageCause cause, Map<EntityDamageEvent.DamageModifier, Double> modifiers, Map<EntityDamageEvent.DamageModifier, Function<? super Double, Double>> modifierFunctions) {
/* 1031 */     return callEntityDamageEvent(damager, damagee, cause, modifiers, modifierFunctions, false);
/*      */   }
/*      */   
/*      */   private static EntityDamageEvent callEntityDamageEvent(Entity damager, Entity damagee, EntityDamageEvent.DamageCause cause, Map<EntityDamageEvent.DamageModifier, Double> modifiers, Map<EntityDamageEvent.DamageModifier, Function<? super Double, Double>> modifierFunctions, boolean cancelled) {
/*      */     EntityDamageEvent event;
/* 1036 */     if (damager != null) {
/* 1037 */       EntityDamageByEntityEvent entityDamageByEntityEvent = new EntityDamageByEntityEvent((Entity)damager.getBukkitEntity(), (Entity)damagee.getBukkitEntity(), cause, modifiers, modifierFunctions);
/*      */     } else {
/* 1039 */       event = new EntityDamageEvent((Entity)damagee.getBukkitEntity(), cause, modifiers, modifierFunctions);
/*      */     } 
/* 1041 */     event.setCancelled(cancelled);
/* 1042 */     callEvent(event);
/*      */     
/* 1044 */     if (!event.isCancelled()) {
/* 1045 */       event.getEntity().setLastDamageCause(event);
/*      */     }
/*      */     
/* 1048 */     return event;
/*      */   }
/*      */   
/* 1051 */   private static final Function<? super Double, Double> ZERO = Functions.constant(Double.valueOf(-0.0D));
/*      */   
/*      */   public static EntityDamageEvent handleLivingEntityDamageEvent(Entity damagee, DamageSource source, double rawDamage, double hardHatModifier, double blockingModifier, double armorModifier, double resistanceModifier, double magicModifier, double absorptionModifier, Function<Double, Double> hardHat, Function<Double, Double> blocking, Function<Double, Double> armor, Function<Double, Double> resistance, Function<Double, Double> magic, Function<Double, Double> absorption) {
/* 1054 */     Map<EntityDamageEvent.DamageModifier, Double> modifiers = new EnumMap<>(EntityDamageEvent.DamageModifier.class);
/* 1055 */     Map<EntityDamageEvent.DamageModifier, Function<? super Double, Double>> modifierFunctions = new EnumMap<>(EntityDamageEvent.DamageModifier.class);
/* 1056 */     modifiers.put(EntityDamageEvent.DamageModifier.BASE, Double.valueOf(rawDamage));
/* 1057 */     modifierFunctions.put(EntityDamageEvent.DamageModifier.BASE, ZERO);
/* 1058 */     if (source == DamageSource.FALLING_BLOCK || source == DamageSource.ANVIL) {
/* 1059 */       modifiers.put(EntityDamageEvent.DamageModifier.HARD_HAT, Double.valueOf(hardHatModifier));
/* 1060 */       modifierFunctions.put(EntityDamageEvent.DamageModifier.HARD_HAT, hardHat);
/*      */     } 
/* 1062 */     if (damagee instanceof EntityHuman) {
/* 1063 */       modifiers.put(EntityDamageEvent.DamageModifier.BLOCKING, Double.valueOf(blockingModifier));
/* 1064 */       modifierFunctions.put(EntityDamageEvent.DamageModifier.BLOCKING, blocking);
/*      */     } 
/* 1066 */     modifiers.put(EntityDamageEvent.DamageModifier.ARMOR, Double.valueOf(armorModifier));
/* 1067 */     modifierFunctions.put(EntityDamageEvent.DamageModifier.ARMOR, armor);
/* 1068 */     modifiers.put(EntityDamageEvent.DamageModifier.RESISTANCE, Double.valueOf(resistanceModifier));
/* 1069 */     modifierFunctions.put(EntityDamageEvent.DamageModifier.RESISTANCE, resistance);
/* 1070 */     modifiers.put(EntityDamageEvent.DamageModifier.MAGIC, Double.valueOf(magicModifier));
/* 1071 */     modifierFunctions.put(EntityDamageEvent.DamageModifier.MAGIC, magic);
/* 1072 */     modifiers.put(EntityDamageEvent.DamageModifier.ABSORPTION, Double.valueOf(absorptionModifier));
/* 1073 */     modifierFunctions.put(EntityDamageEvent.DamageModifier.ABSORPTION, absorption);
/* 1074 */     return handleEntityDamageEvent(damagee, source, modifiers, modifierFunctions);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean handleNonLivingEntityDamageEvent(Entity entity, DamageSource source, double damage) {
/* 1079 */     return handleNonLivingEntityDamageEvent(entity, source, damage, true);
/*      */   }
/*      */   
/*      */   public static boolean handleNonLivingEntityDamageEvent(Entity entity, DamageSource source, double damage, boolean cancelOnZeroDamage) {
/* 1083 */     return handleNonLivingEntityDamageEvent(entity, source, damage, cancelOnZeroDamage, false);
/*      */   }
/*      */   
/*      */   public static boolean handleNonLivingEntityDamageEvent(Entity entity, DamageSource source, double damage, boolean cancelOnZeroDamage, boolean cancelled) {
/* 1087 */     EnumMap<EntityDamageEvent.DamageModifier, Double> modifiers = new EnumMap<>(EntityDamageEvent.DamageModifier.class);
/* 1088 */     EnumMap<EntityDamageEvent.DamageModifier, Function<? super Double, Double>> functions = new EnumMap<>(EntityDamageEvent.DamageModifier.class);
/*      */     
/* 1090 */     modifiers.put(EntityDamageEvent.DamageModifier.BASE, Double.valueOf(damage));
/* 1091 */     functions.put(EntityDamageEvent.DamageModifier.BASE, ZERO);
/*      */     
/* 1093 */     EntityDamageEvent event = handleEntityDamageEvent(entity, source, modifiers, functions, cancelled);
/*      */     
/* 1095 */     if (event == null) {
/* 1096 */       return false;
/*      */     }
/* 1098 */     return (event.isCancelled() || (cancelOnZeroDamage && event.getDamage() == 0.0D));
/*      */   }
/*      */   
/*      */   public static PlayerLevelChangeEvent callPlayerLevelChangeEvent(Player player, int oldLevel, int newLevel) {
/* 1102 */     PlayerLevelChangeEvent event = new PlayerLevelChangeEvent(player, oldLevel, newLevel);
/* 1103 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1104 */     return event;
/*      */   }
/*      */   
/*      */   public static PlayerExpChangeEvent callPlayerExpChangeEvent(EntityHuman entity, int expAmount) {
/* 1108 */     Player player = (Player)entity.getBukkitEntity();
/* 1109 */     PlayerExpChangeEvent event = new PlayerExpChangeEvent(player, expAmount);
/* 1110 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1111 */     return event;
/*      */   }
/*      */   
/*      */   public static PlayerItemMendEvent callPlayerItemMendEvent(EntityHuman entity, EntityExperienceOrb orb, ItemStack nmsMendedItem, int repairAmount) {
/* 1115 */     Player player = (Player)entity.getBukkitEntity();
/* 1116 */     CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(nmsMendedItem);
/* 1117 */     PlayerItemMendEvent event = new PlayerItemMendEvent(player, (ItemStack)craftItemStack, (ExperienceOrb)orb.getBukkitEntity(), repairAmount);
/* 1118 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1119 */     return event;
/*      */   }
/*      */ 
/*      */   
/*      */   public static PlayerExpChangeEvent callPlayerExpChangeEvent(EntityHuman entity, EntityExperienceOrb entityOrb) {
/* 1124 */     Player player = (Player)entity.getBukkitEntity();
/* 1125 */     ExperienceOrb source = (ExperienceOrb)entityOrb.getBukkitEntity();
/* 1126 */     int expAmount = source.getExperience();
/* 1127 */     PlayerExpChangeEvent event = new PlayerExpChangeEvent(player, (Entity)source, expAmount);
/* 1128 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1129 */     return event;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean handleBlockGrowEvent(World world, BlockPosition pos, IBlockData block) {
/* 1134 */     return handleBlockGrowEvent(world, pos, block, 3);
/*      */   }
/*      */   
/*      */   public static boolean handleBlockGrowEvent(World world, BlockPosition pos, IBlockData newData, int flag) {
/* 1138 */     Block block = world.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ());
/* 1139 */     CraftBlockState state = (CraftBlockState)block.getState();
/* 1140 */     state.setData(newData);
/*      */     
/* 1142 */     BlockGrowEvent event = new BlockGrowEvent(block, (BlockState)state);
/* 1143 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */     
/* 1145 */     if (!event.isCancelled()) {
/* 1146 */       state.update(true);
/*      */     }
/*      */     
/* 1149 */     return !event.isCancelled();
/*      */   }
/*      */   
/*      */   public static FluidLevelChangeEvent callFluidLevelChangeEvent(World world, BlockPosition block, IBlockData newData) {
/* 1153 */     FluidLevelChangeEvent event = new FluidLevelChangeEvent((Block)CraftBlock.at((GeneratorAccess)world, block), (BlockData)CraftBlockData.fromData(newData));
/* 1154 */     world.getServer().getPluginManager().callEvent((Event)event);
/* 1155 */     return event;
/*      */   }
/*      */   
/*      */   public static FoodLevelChangeEvent callFoodLevelChangeEvent(EntityHuman entity, int level) {
/* 1159 */     return callFoodLevelChangeEvent(entity, level, null);
/*      */   }
/*      */   
/*      */   public static FoodLevelChangeEvent callFoodLevelChangeEvent(EntityHuman entity, int level, ItemStack item) {
/* 1163 */     FoodLevelChangeEvent event = new FoodLevelChangeEvent((HumanEntity)entity.getBukkitEntity(), level, (item == null) ? null : CraftItemStack.asBukkitCopy(item));
/* 1164 */     entity.getBukkitEntity().getServer().getPluginManager().callEvent((Event)event);
/* 1165 */     return event;
/*      */   }
/*      */   
/*      */   public static PigZapEvent callPigZapEvent(Entity pig, Entity lightning, Entity pigzombie) {
/* 1169 */     PigZapEvent event = new PigZapEvent((Pig)pig.getBukkitEntity(), (LightningStrike)lightning.getBukkitEntity(), (PigZombie)pigzombie.getBukkitEntity());
/* 1170 */     pig.getBukkitEntity().getServer().getPluginManager().callEvent((Event)event);
/* 1171 */     return event;
/*      */   }
/*      */ 
/*      */   
/*      */   public static EntityZapEvent callEntityZapEvent(Entity entity, Entity lightning, Entity changedEntity) {
/* 1176 */     EntityZapEvent event = new EntityZapEvent((Entity)entity.getBukkitEntity(), (LightningStrike)lightning.getBukkitEntity(), (Entity)changedEntity.getBukkitEntity());
/* 1177 */     entity.getBukkitEntity().getServer().getPluginManager().callEvent((Event)event);
/* 1178 */     return event;
/*      */   }
/*      */ 
/*      */   
/*      */   public static HorseJumpEvent callHorseJumpEvent(Entity horse, float power) {
/* 1183 */     HorseJumpEvent event = new HorseJumpEvent((AbstractHorse)horse.getBukkitEntity(), power);
/* 1184 */     horse.getBukkitEntity().getServer().getPluginManager().callEvent((Event)event);
/* 1185 */     return event;
/*      */   }
/*      */   
/*      */   public static EntityChangeBlockEvent callEntityChangeBlockEvent(Entity entity, BlockPosition position, IBlockData newBlock) {
/* 1189 */     return callEntityChangeBlockEvent(entity, position, newBlock, false);
/*      */   }
/*      */   
/*      */   public static EntityChangeBlockEvent callEntityChangeBlockEvent(Entity entity, BlockPosition position, IBlockData newBlock, boolean cancelled) {
/* 1193 */     Block block = entity.world.getWorld().getBlockAt(position.getX(), position.getY(), position.getZ());
/*      */     
/* 1195 */     EntityChangeBlockEvent event = new EntityChangeBlockEvent((Entity)entity.getBukkitEntity(), block, (BlockData)CraftBlockData.fromData(newBlock));
/* 1196 */     event.setCancelled(cancelled);
/* 1197 */     event.getEntity().getServer().getPluginManager().callEvent((Event)event);
/* 1198 */     return event;
/*      */   }
/*      */   
/*      */   public static CreeperPowerEvent callCreeperPowerEvent(Entity creeper, Entity lightning, CreeperPowerEvent.PowerCause cause) {
/* 1202 */     CreeperPowerEvent event = new CreeperPowerEvent((Creeper)creeper.getBukkitEntity(), (LightningStrike)lightning.getBukkitEntity(), cause);
/* 1203 */     creeper.getBukkitEntity().getServer().getPluginManager().callEvent((Event)event);
/* 1204 */     return event;
/*      */   }
/*      */   
/*      */   public static EntityTargetEvent callEntityTargetEvent(Entity entity, Entity target, EntityTargetEvent.TargetReason reason) {
/* 1208 */     EntityTargetEvent event = new EntityTargetEvent((Entity)entity.getBukkitEntity(), (target == null) ? null : (Entity)target.getBukkitEntity(), reason);
/* 1209 */     entity.getBukkitEntity().getServer().getPluginManager().callEvent((Event)event);
/* 1210 */     return event;
/*      */   }
/*      */   
/*      */   public static EntityTargetLivingEntityEvent callEntityTargetLivingEvent(Entity entity, EntityLiving target, EntityTargetEvent.TargetReason reason) {
/* 1214 */     EntityTargetLivingEntityEvent event = new EntityTargetLivingEntityEvent((Entity)entity.getBukkitEntity(), (target == null) ? null : (LivingEntity)target.getBukkitEntity(), reason);
/* 1215 */     entity.getBukkitEntity().getServer().getPluginManager().callEvent((Event)event);
/* 1216 */     return event;
/*      */   }
/*      */   
/*      */   public static EntityBreakDoorEvent callEntityBreakDoorEvent(Entity entity, BlockPosition pos) {
/* 1220 */     CraftEntity craftEntity = entity.getBukkitEntity();
/* 1221 */     CraftBlock craftBlock = CraftBlock.at((GeneratorAccess)entity.world, pos);
/*      */     
/* 1223 */     EntityBreakDoorEvent event = new EntityBreakDoorEvent((LivingEntity)craftEntity, (Block)craftBlock);
/* 1224 */     craftEntity.getServer().getPluginManager().callEvent((Event)event);
/*      */     
/* 1226 */     return event;
/*      */   }
/*      */   
/*      */   public static Container callInventoryOpenEvent(EntityPlayer player, Container container) {
/* 1230 */     return callInventoryOpenEvent(player, container, false);
/*      */   }
/*      */   
/*      */   public static Container callInventoryOpenEvent(EntityPlayer player, Container container, boolean cancelled) {
/* 1234 */     if (player.activeContainer != player.defaultContainer) {
/* 1235 */       player.playerConnection.handleContainerClose(new PacketPlayInCloseWindow(player.activeContainer.windowId), InventoryCloseEvent.Reason.OPEN_NEW);
/*      */     }
/*      */     
/* 1238 */     CraftServer server = player.world.getServer();
/* 1239 */     CraftPlayer craftPlayer = player.getBukkitEntity();
/* 1240 */     player.activeContainer.transferTo(container, (CraftHumanEntity)craftPlayer);
/*      */     
/* 1242 */     InventoryOpenEvent event = new InventoryOpenEvent(container.getBukkitView());
/* 1243 */     event.setCancelled(cancelled);
/* 1244 */     server.getPluginManager().callEvent((Event)event);
/*      */     
/* 1246 */     if (event.isCancelled()) {
/* 1247 */       container.transferTo(player.activeContainer, (CraftHumanEntity)craftPlayer);
/* 1248 */       return null;
/*      */     } 
/*      */     
/* 1251 */     return container;
/*      */   }
/*      */   
/*      */   public static ItemStack callPreCraftEvent(IInventory matrix, IInventory resultInventory, ItemStack result, InventoryView lastCraftView, boolean isRepair) {
/* 1255 */     CraftInventoryCrafting inventory = new CraftInventoryCrafting(matrix, resultInventory);
/* 1256 */     inventory.setResult((ItemStack)CraftItemStack.asCraftMirror(result));
/*      */     
/* 1258 */     PrepareItemCraftEvent event = new PrepareItemCraftEvent((CraftingInventory)inventory, lastCraftView, isRepair);
/* 1259 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */     
/* 1261 */     ItemStack bitem = event.getInventory().getResult();
/*      */     
/* 1263 */     return CraftItemStack.asNMSCopy(bitem);
/*      */   }
/*      */ 
/*      */   
/*      */   public static ProjectileCollideEvent callProjectileCollideEvent(Entity entity, MovingObjectPositionEntity position) {
/* 1268 */     Projectile projectile = (Projectile)entity.getBukkitEntity();
/* 1269 */     CraftEntity craftEntity = position.getEntity().getBukkitEntity();
/* 1270 */     ProjectileCollideEvent event = new ProjectileCollideEvent(projectile, (Entity)craftEntity);
/*      */     
/* 1272 */     if (projectile.getShooter() instanceof Player && craftEntity instanceof Player && 
/* 1273 */       !((Player)projectile.getShooter()).canSee((Player)craftEntity)) {
/* 1274 */       event.setCancelled(true);
/* 1275 */       return event;
/*      */     } 
/*      */ 
/*      */     
/* 1279 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1280 */     return event;
/*      */   }
/*      */ 
/*      */   
/*      */   public static ProjectileLaunchEvent callProjectileLaunchEvent(Entity entity) {
/* 1285 */     Projectile bukkitEntity = (Projectile)entity.getBukkitEntity();
/* 1286 */     ProjectileLaunchEvent event = new ProjectileLaunchEvent((Entity)bukkitEntity);
/* 1287 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1288 */     return event;
/*      */   } public static void callProjectileHitEvent(Entity entity, MovingObjectPosition position) {
/*      */     CraftBlock craftBlock;
/*      */     CraftEntity craftEntity;
/* 1292 */     if (position.getType() == MovingObjectPosition.EnumMovingObjectType.MISS) {
/*      */       return;
/*      */     }
/*      */     
/* 1296 */     Block hitBlock = null;
/* 1297 */     BlockFace hitFace = null;
/* 1298 */     if (position.getType() == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 1299 */       MovingObjectPositionBlock positionBlock = (MovingObjectPositionBlock)position;
/* 1300 */       craftBlock = CraftBlock.at((GeneratorAccess)entity.world, positionBlock.getBlockPosition());
/* 1301 */       hitFace = CraftBlock.notchToBlockFace(positionBlock.getDirection());
/*      */     } 
/*      */     
/* 1304 */     Entity hitEntity = null;
/* 1305 */     if (position.getType() == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
/* 1306 */       craftEntity = ((MovingObjectPositionEntity)position).getEntity().getBukkitEntity();
/*      */     }
/*      */     
/* 1309 */     ProjectileHitEvent event = new ProjectileHitEvent((Projectile)entity.getBukkitEntity(), (Entity)craftEntity, (Block)craftBlock, hitFace);
/* 1310 */     entity.world.getServer().getPluginManager().callEvent((Event)event);
/*      */   }
/*      */   
/*      */   public static ExpBottleEvent callExpBottleEvent(Entity entity, int exp) {
/* 1314 */     ThrownExpBottle bottle = (ThrownExpBottle)entity.getBukkitEntity();
/* 1315 */     ExpBottleEvent event = new ExpBottleEvent(bottle, exp);
/* 1316 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1317 */     return event;
/*      */   }
/*      */   
/*      */   public static BlockRedstoneEvent callRedstoneChange(World world, BlockPosition pos, int oldCurrent, int newCurrent) {
/* 1321 */     BlockRedstoneEvent event = new BlockRedstoneEvent(world.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ()), oldCurrent, newCurrent);
/* 1322 */     world.getServer().getPluginManager().callEvent((Event)event);
/* 1323 */     return event;
/*      */   }
/*      */   
/*      */   public static NotePlayEvent callNotePlayEvent(World world, BlockPosition pos, BlockPropertyInstrument instrument, int note) {
/* 1327 */     NotePlayEvent event = new NotePlayEvent(world.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ()), Instrument.getByType((byte)instrument.ordinal()), new Note(note));
/* 1328 */     world.getServer().getPluginManager().callEvent((Event)event);
/* 1329 */     return event;
/*      */   }
/*      */   
/*      */   public static void callPlayerItemBreakEvent(EntityHuman human, ItemStack brokenItem) {
/* 1333 */     CraftItemStack item = CraftItemStack.asCraftMirror(brokenItem);
/* 1334 */     PlayerItemBreakEvent event = new PlayerItemBreakEvent((Player)human.getBukkitEntity(), (ItemStack)item);
/* 1335 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */   }
/*      */   
/*      */   public static BlockIgniteEvent callBlockIgniteEvent(World world, BlockPosition block, BlockPosition source) {
/* 1339 */     CraftWorld craftWorld = world.getWorld();
/* 1340 */     Block igniter = craftWorld.getBlockAt(source.getX(), source.getY(), source.getZ());
/*      */     
/* 1342 */     switch (igniter.getType())
/*      */     { case FALL_ONE_CM:
/* 1344 */         cause = BlockIgniteEvent.IgniteCause.LAVA;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1354 */         event = new BlockIgniteEvent(craftWorld.getBlockAt(block.getX(), block.getY(), block.getZ()), cause, igniter);
/* 1355 */         world.getServer().getPluginManager().callEvent((Event)event);
/* 1356 */         return event;case BOAT_ONE_CM: cause = BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL; event = new BlockIgniteEvent(craftWorld.getBlockAt(block.getX(), block.getY(), block.getZ()), cause, igniter); world.getServer().getPluginManager().callEvent((Event)event); return event; }  BlockIgniteEvent.IgniteCause cause = BlockIgniteEvent.IgniteCause.SPREAD; BlockIgniteEvent event = new BlockIgniteEvent(craftWorld.getBlockAt(block.getX(), block.getY(), block.getZ()), cause, igniter); world.getServer().getPluginManager().callEvent((Event)event); return event;
/*      */   }
/*      */   public static BlockIgniteEvent callBlockIgniteEvent(World world, BlockPosition pos, Entity igniter) {
/*      */     BlockIgniteEvent.IgniteCause cause;
/* 1360 */     CraftWorld craftWorld = world.getWorld();
/* 1361 */     CraftEntity craftEntity = igniter.getBukkitEntity();
/*      */     
/* 1363 */     switch (craftEntity.getType()) {
/*      */       case FALL_ONE_CM:
/* 1365 */         cause = BlockIgniteEvent.IgniteCause.ENDER_CRYSTAL;
/*      */         break;
/*      */       case BOAT_ONE_CM:
/* 1368 */         cause = BlockIgniteEvent.IgniteCause.LIGHTNING;
/*      */         break;
/*      */       case CLIMB_ONE_CM:
/*      */       case WALK_ON_WATER_ONE_CM:
/* 1372 */         cause = BlockIgniteEvent.IgniteCause.FIREBALL;
/*      */         break;
/*      */       case WALK_UNDER_WATER_ONE_CM:
/* 1375 */         cause = BlockIgniteEvent.IgniteCause.ARROW;
/*      */         break;
/*      */       default:
/* 1378 */         cause = BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL;
/*      */         break;
/*      */     } 
/* 1381 */     if (igniter instanceof IProjectile) {
/* 1382 */       Entity shooter = ((IProjectile)igniter).getShooter();
/* 1383 */       if (shooter != null) {
/* 1384 */         craftEntity = shooter.getBukkitEntity();
/*      */       }
/*      */     } 
/*      */     
/* 1388 */     BlockIgniteEvent event = new BlockIgniteEvent(craftWorld.getBlockAt(pos.getX(), pos.getY(), pos.getZ()), cause, (Entity)craftEntity);
/* 1389 */     world.getServer().getPluginManager().callEvent((Event)event);
/* 1390 */     return event;
/*      */   }
/*      */   
/*      */   public static BlockIgniteEvent callBlockIgniteEvent(World world, int x, int y, int z, Explosion explosion) {
/* 1394 */     CraftWorld craftWorld = world.getWorld();
/* 1395 */     CraftEntity craftEntity = (explosion.source == null) ? null : explosion.source.getBukkitEntity();
/*      */     
/* 1397 */     BlockIgniteEvent event = new BlockIgniteEvent(craftWorld.getBlockAt(x, y, z), BlockIgniteEvent.IgniteCause.EXPLOSION, (Entity)craftEntity);
/* 1398 */     world.getServer().getPluginManager().callEvent((Event)event);
/* 1399 */     return event;
/*      */   }
/*      */   
/*      */   public static BlockIgniteEvent callBlockIgniteEvent(World world, BlockPosition pos, BlockIgniteEvent.IgniteCause cause, Entity igniter) {
/* 1403 */     BlockIgniteEvent event = new BlockIgniteEvent(world.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ()), cause, (Entity)igniter.getBukkitEntity());
/* 1404 */     world.getServer().getPluginManager().callEvent((Event)event);
/* 1405 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void handleInventoryCloseEvent(EntityHuman human) {
/* 1415 */     handleInventoryCloseEvent(human, InventoryCloseEvent.Reason.UNKNOWN);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void handleInventoryCloseEvent(EntityHuman human, InventoryCloseEvent.Reason reason) {
/* 1420 */     if (human.activeContainer == human.defaultContainer) {
/*      */       return;
/*      */     }
/* 1423 */     InventoryCloseEvent event = new InventoryCloseEvent(human.activeContainer.getBukkitView(), reason);
/* 1424 */     human.world.getServer().getPluginManager().callEvent((Event)event);
/* 1425 */     human.activeContainer.transferTo((Container)human.defaultContainer, human.getBukkitEntity());
/*      */   }
/*      */   
/*      */   public static ItemStack handleEditBookEvent(EntityPlayer player, EnumItemSlot slot, ItemStack itemInHand, ItemStack newBookItem) {
/* 1429 */     int itemInHandIndex = (slot == EnumItemSlot.MAINHAND) ? player.inventory.itemInHandIndex : -1;
/*      */     
/* 1431 */     PlayerEditBookEvent editBookEvent = new PlayerEditBookEvent((Player)player.getBukkitEntity(), itemInHandIndex, (BookMeta)CraftItemStack.getItemMeta(itemInHand), (BookMeta)CraftItemStack.getItemMeta(newBookItem), (newBookItem.getItem() == Items.WRITTEN_BOOK));
/* 1432 */     player.world.getServer().getPluginManager().callEvent((Event)editBookEvent);
/*      */ 
/*      */     
/* 1435 */     if (itemInHand != null && itemInHand.getItem() == Items.WRITABLE_BOOK && 
/* 1436 */       !editBookEvent.isCancelled()) {
/* 1437 */       if (editBookEvent.isSigning()) {
/* 1438 */         itemInHand.setItem(Items.WRITTEN_BOOK);
/*      */       }
/* 1440 */       CraftMetaBook meta = (CraftMetaBook)editBookEvent.getNewBookMeta();
/* 1441 */       List<IChatBaseComponent> pages = meta.pages;
/* 1442 */       for (int i = 0; i < pages.size(); i++) {
/* 1443 */         pages.set(i, stripEvents(pages.get(i)));
/*      */       }
/* 1445 */       CraftItemStack.setItemMeta(itemInHand, (ItemMeta)meta);
/*      */     } 
/*      */ 
/*      */     
/* 1449 */     return itemInHand;
/*      */   }
/*      */   
/*      */   private static IChatBaseComponent stripEvents(IChatBaseComponent c) {
/* 1453 */     ChatModifier modi = c.getChatModifier();
/* 1454 */     if (modi != null) {
/* 1455 */       modi = modi.setChatClickable(null);
/* 1456 */       modi = modi.setChatHoverable(null);
/*      */     } 
/* 1458 */     if (c instanceof ChatMessage) {
/* 1459 */       ChatMessage cm = (ChatMessage)c;
/* 1460 */       Object[] oo = cm.getArgs();
/* 1461 */       for (int i = 0; i < oo.length; i++) {
/* 1462 */         Object o = oo[i];
/* 1463 */         if (o instanceof IChatBaseComponent) {
/* 1464 */           oo[i] = stripEvents((IChatBaseComponent)o);
/*      */         }
/*      */       } 
/*      */     } 
/* 1468 */     List<IChatBaseComponent> ls = c.getSiblings();
/* 1469 */     if (ls != null) {
/* 1470 */       for (int i = 0; i < ls.size(); i++) {
/* 1471 */         ls.set(i, stripEvents(ls.get(i)));
/*      */       }
/*      */     }
/* 1474 */     return (IChatBaseComponent)c.mutableCopy().setChatModifier(modi);
/*      */   }
/*      */   
/*      */   public static PlayerUnleashEntityEvent callPlayerUnleashEntityEvent(EntityInsentient entity, EntityHuman player) {
/* 1478 */     PlayerUnleashEntityEvent event = new PlayerUnleashEntityEvent((Entity)entity.getBukkitEntity(), (Player)player.getBukkitEntity());
/* 1479 */     entity.world.getServer().getPluginManager().callEvent((Event)event);
/* 1480 */     return event;
/*      */   }
/*      */   
/*      */   public static PlayerLeashEntityEvent callPlayerLeashEntityEvent(EntityInsentient entity, Entity leashHolder, EntityHuman player) {
/* 1484 */     PlayerLeashEntityEvent event = new PlayerLeashEntityEvent((Entity)entity.getBukkitEntity(), (Entity)leashHolder.getBukkitEntity(), (Player)player.getBukkitEntity());
/* 1485 */     entity.world.getServer().getPluginManager().callEvent((Event)event);
/* 1486 */     return event;
/*      */   }
/*      */   
/*      */   public static BlockShearEntityEvent callBlockShearEntityEvent(Entity animal, Block dispenser, CraftItemStack is) {
/* 1490 */     BlockShearEntityEvent bse = new BlockShearEntityEvent(dispenser, (Entity)animal.getBukkitEntity(), (ItemStack)is);
/* 1491 */     Bukkit.getPluginManager().callEvent((Event)bse);
/* 1492 */     return bse;
/*      */   }
/*      */   
/*      */   public static boolean handlePlayerShearEntityEvent(EntityHuman player, Entity sheared, ItemStack shears, EnumHand hand) {
/* 1496 */     if (!(player instanceof EntityPlayer)) {
/* 1497 */       return true;
/*      */     }
/*      */     
/* 1500 */     PlayerShearEntityEvent event = new PlayerShearEntityEvent((Player)player.getBukkitEntity(), (Entity)sheared.getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(shears), (hand == EnumHand.OFF_HAND) ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND);
/* 1501 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1502 */     return !event.isCancelled();
/*      */   }
/*      */   public static Cancellable handleStatisticsIncrease(EntityHuman entityHuman, Statistic<?> statistic, int current, int newValue) {
/*      */     PlayerStatisticIncrementEvent playerStatisticIncrementEvent;
/* 1506 */     CraftPlayer craftPlayer = ((EntityPlayer)entityHuman).getBukkitEntity();
/*      */ 
/*      */     
/* 1509 */     Statistic stat = CraftStatistic.getBukkitStatistic(statistic);
/* 1510 */     if (stat == null) {
/* 1511 */       System.err.println("Unhandled statistic: " + statistic);
/* 1512 */       return null;
/*      */     } 
/* 1514 */     switch (stat) {
/*      */       
/*      */       case FALL_ONE_CM:
/*      */       case BOAT_ONE_CM:
/*      */       case CLIMB_ONE_CM:
/*      */       case WALK_ON_WATER_ONE_CM:
/*      */       case WALK_UNDER_WATER_ONE_CM:
/*      */       case FLY_ONE_CM:
/*      */       case HORSE_ONE_CM:
/*      */       case MINECART_ONE_CM:
/*      */       case PIG_ONE_CM:
/*      */       case PLAY_ONE_MINUTE:
/*      */       case SWIM_ONE_CM:
/*      */       case WALK_ONE_CM:
/*      */       case SPRINT_ONE_CM:
/*      */       case CROUCH_ONE_CM:
/*      */       case TIME_SINCE_DEATH:
/*      */       case SNEAK_TIME:
/* 1532 */         return null;
/*      */     } 
/*      */     
/* 1535 */     if (stat.getType() == Statistic.Type.UNTYPED) {
/* 1536 */       playerStatisticIncrementEvent = new PlayerStatisticIncrementEvent((Player)craftPlayer, stat, current, newValue);
/* 1537 */     } else if (stat.getType() == Statistic.Type.ENTITY) {
/* 1538 */       EntityType entityType = CraftStatistic.getEntityTypeFromStatistic(statistic);
/* 1539 */       playerStatisticIncrementEvent = new PlayerStatisticIncrementEvent((Player)craftPlayer, stat, current, newValue, entityType);
/*      */     } else {
/* 1541 */       Material material = CraftStatistic.getMaterialFromStatistic(statistic);
/* 1542 */       playerStatisticIncrementEvent = new PlayerStatisticIncrementEvent((Player)craftPlayer, stat, current, newValue, material);
/*      */     } 
/*      */     
/* 1545 */     entityHuman.world.getServer().getPluginManager().callEvent((Event)playerStatisticIncrementEvent);
/* 1546 */     return (Cancellable)playerStatisticIncrementEvent;
/*      */   }
/*      */   
/*      */   public static FireworkExplodeEvent callFireworkExplodeEvent(EntityFireworks firework) {
/* 1550 */     FireworkExplodeEvent event = new FireworkExplodeEvent((Firework)firework.getBukkitEntity());
/* 1551 */     firework.world.getServer().getPluginManager().callEvent((Event)event);
/* 1552 */     return event;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void callPrepareAnvilEvent(InventoryView view, ItemStack item) {
/* 1557 */     PrepareAnvilEvent event = new PrepareAnvilEvent(view, (ItemStack)CraftItemStack.asCraftMirror(item));
/*      */     
/* 1559 */     event.getInventory().setItem(2, event.getResult());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void callPrepareSmithingEvent(InventoryView view, ItemStack item) {
/* 1566 */     PrepareSmithingEvent event = new PrepareSmithingEvent(view, (ItemStack)CraftItemStack.asCraftMirror(item));
/*      */     
/* 1568 */     event.getInventory().setItem(2, event.getResult());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void callPrepareResultEvent(Container container, int resultSlot) {
/*      */     PrepareResultEvent event;
/* 1576 */     InventoryView view = container.getBukkitView();
/* 1577 */     ItemStack origItem = view.getTopInventory().getItem(resultSlot);
/* 1578 */     CraftItemStack result = (origItem != null) ? CraftItemStack.asCraftCopy(origItem) : null;
/* 1579 */     if (view.getTopInventory() instanceof org.bukkit.inventory.AnvilInventory) {
/* 1580 */       PrepareAnvilEvent prepareAnvilEvent = new PrepareAnvilEvent(view, (ItemStack)result);
/* 1581 */     } else if (view.getTopInventory() instanceof org.bukkit.inventory.GrindstoneInventory) {
/* 1582 */       PrepareGrindstoneEvent prepareGrindstoneEvent = new PrepareGrindstoneEvent(view, (ItemStack)result);
/* 1583 */     } else if (view.getTopInventory() instanceof org.bukkit.inventory.SmithingInventory) {
/* 1584 */       PrepareSmithingEvent prepareSmithingEvent = new PrepareSmithingEvent(view, (ItemStack)result);
/*      */     } else {
/* 1586 */       event = new PrepareResultEvent(view, (ItemStack)result);
/*      */     } 
/* 1588 */     event.callEvent();
/* 1589 */     event.getInventory().setItem(resultSlot, event.getResult());
/* 1590 */     container.notifyListeners();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static SpawnerSpawnEvent callSpawnerSpawnEvent(Entity spawnee, BlockPosition pos) {
/* 1598 */     CraftEntity entity = spawnee.getBukkitEntity();
/* 1599 */     BlockState state = entity.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ()).getState();
/* 1600 */     if (!(state instanceof CreatureSpawner)) {
/* 1601 */       state = null;
/*      */     }
/*      */     
/* 1604 */     SpawnerSpawnEvent event = new SpawnerSpawnEvent((Entity)entity, (CreatureSpawner)state);
/* 1605 */     entity.getServer().getPluginManager().callEvent((Event)event);
/* 1606 */     return event;
/*      */   }
/*      */   
/*      */   public static EntityToggleGlideEvent callToggleGlideEvent(EntityLiving entity, boolean gliding) {
/* 1610 */     EntityToggleGlideEvent event = new EntityToggleGlideEvent((LivingEntity)entity.getBukkitEntity(), gliding);
/* 1611 */     entity.world.getServer().getPluginManager().callEvent((Event)event);
/* 1612 */     return event;
/*      */   }
/*      */   
/*      */   public static EntityToggleSwimEvent callToggleSwimEvent(EntityLiving entity, boolean swimming) {
/* 1616 */     EntityToggleSwimEvent event = new EntityToggleSwimEvent((LivingEntity)entity.getBukkitEntity(), swimming);
/* 1617 */     entity.world.getServer().getPluginManager().callEvent((Event)event);
/* 1618 */     return event;
/*      */   }
/*      */   
/*      */   public static AreaEffectCloudApplyEvent callAreaEffectCloudApplyEvent(EntityAreaEffectCloud cloud, List<LivingEntity> entities) {
/* 1622 */     AreaEffectCloudApplyEvent event = new AreaEffectCloudApplyEvent((AreaEffectCloud)cloud.getBukkitEntity(), entities);
/* 1623 */     cloud.world.getServer().getPluginManager().callEvent((Event)event);
/* 1624 */     return event;
/*      */   }
/*      */   
/*      */   public static VehicleCreateEvent callVehicleCreateEvent(Entity entity) {
/* 1628 */     Vehicle bukkitEntity = (Vehicle)entity.getBukkitEntity();
/* 1629 */     VehicleCreateEvent event = new VehicleCreateEvent(bukkitEntity);
/* 1630 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1631 */     return event;
/*      */   }
/*      */   
/*      */   public static EntityBreedEvent callEntityBreedEvent(EntityLiving child, EntityLiving mother, EntityLiving father, EntityLiving breeder, ItemStack bredWith, int experience) {
/* 1635 */     LivingEntity breederEntity = (breeder == null) ? null : (LivingEntity)breeder.getBukkitEntity();
/* 1636 */     CraftItemStack bredWithStack = (bredWith == null) ? null : CraftItemStack.asCraftMirror(bredWith).clone();
/*      */     
/* 1638 */     EntityBreedEvent event = new EntityBreedEvent((LivingEntity)child.getBukkitEntity(), (LivingEntity)mother.getBukkitEntity(), (LivingEntity)father.getBukkitEntity(), breederEntity, (ItemStack)bredWithStack, experience);
/* 1639 */     child.world.getServer().getPluginManager().callEvent((Event)event);
/* 1640 */     return event;
/*      */   }
/*      */   
/*      */   public static BlockPhysicsEvent callBlockPhysicsEvent(GeneratorAccess world, BlockPosition blockposition) {
/* 1644 */     CraftBlock craftBlock = CraftBlock.at(world, blockposition);
/* 1645 */     BlockPhysicsEvent event = new BlockPhysicsEvent((Block)craftBlock, craftBlock.getBlockData());
/*      */     
/* 1647 */     if (world instanceof World) {
/* 1648 */       (((World)world).getMinecraftServer()).server.getPluginManager().callEvent((Event)event);
/*      */     }
/* 1650 */     return event;
/*      */   }
/*      */   
/*      */   public static boolean handleBlockFormEvent(World world, BlockPosition pos, IBlockData block) {
/* 1654 */     return handleBlockFormEvent(world, pos, block, 3);
/*      */   }
/*      */   
/*      */   public static EntityPotionEffectEvent callEntityPotionEffectChangeEvent(EntityLiving entity, @Nullable MobEffect oldEffect, @Nullable MobEffect newEffect, EntityPotionEffectEvent.Cause cause) {
/* 1658 */     return callEntityPotionEffectChangeEvent(entity, oldEffect, newEffect, cause, true);
/*      */   }
/*      */   
/*      */   public static EntityPotionEffectEvent callEntityPotionEffectChangeEvent(EntityLiving entity, @Nullable MobEffect oldEffect, @Nullable MobEffect newEffect, EntityPotionEffectEvent.Cause cause, EntityPotionEffectEvent.Action action) {
/* 1662 */     return callEntityPotionEffectChangeEvent(entity, oldEffect, newEffect, cause, action, true);
/*      */   }
/*      */   
/*      */   public static EntityPotionEffectEvent callEntityPotionEffectChangeEvent(EntityLiving entity, @Nullable MobEffect oldEffect, @Nullable MobEffect newEffect, EntityPotionEffectEvent.Cause cause, boolean willOverride) {
/* 1666 */     EntityPotionEffectEvent.Action action = EntityPotionEffectEvent.Action.CHANGED;
/* 1667 */     if (oldEffect == null) {
/* 1668 */       action = EntityPotionEffectEvent.Action.ADDED;
/* 1669 */     } else if (newEffect == null) {
/* 1670 */       action = EntityPotionEffectEvent.Action.REMOVED;
/*      */     } 
/*      */     
/* 1673 */     return callEntityPotionEffectChangeEvent(entity, oldEffect, newEffect, cause, action, willOverride);
/*      */   }
/*      */   
/*      */   public static EntityPotionEffectEvent callEntityPotionEffectChangeEvent(EntityLiving entity, @Nullable MobEffect oldEffect, @Nullable MobEffect newEffect, EntityPotionEffectEvent.Cause cause, EntityPotionEffectEvent.Action action, boolean willOverride) {
/* 1677 */     PotionEffect bukkitOldEffect = (oldEffect == null) ? null : CraftPotionUtil.toBukkit(oldEffect);
/* 1678 */     PotionEffect bukkitNewEffect = (newEffect == null) ? null : CraftPotionUtil.toBukkit(newEffect);
/*      */     
/* 1680 */     if (bukkitOldEffect == null && bukkitNewEffect == null) {
/* 1681 */       throw new IllegalStateException("Old and new potion effect are both null");
/*      */     }
/*      */     
/* 1684 */     EntityPotionEffectEvent event = new EntityPotionEffectEvent((LivingEntity)entity.getBukkitEntity(), bukkitOldEffect, bukkitNewEffect, cause, action, willOverride);
/* 1685 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */     
/* 1687 */     return event;
/*      */   }
/*      */   
/*      */   public static boolean handleBlockFormEvent(World world, BlockPosition pos, IBlockData block, @Nullable Entity entity) {
/* 1691 */     return handleBlockFormEvent(world, pos, block, 3, entity);
/*      */   }
/*      */   
/*      */   public static boolean handleBlockFormEvent(World world, BlockPosition pos, IBlockData block, int flag) {
/* 1695 */     return handleBlockFormEvent(world, pos, block, flag, null);
/*      */   }
/*      */   
/*      */   public static boolean handleBlockFormEvent(World world, BlockPosition pos, IBlockData block, int flag, @Nullable Entity entity) {
/* 1699 */     CraftBlockState blockState = CraftBlockState.getBlockState(world, pos, flag);
/* 1700 */     blockState.setData(block);
/*      */     
/* 1702 */     BlockFormEvent event = (entity == null) ? new BlockFormEvent((Block)blockState.getBlock(), (BlockState)blockState) : (BlockFormEvent)new EntityBlockFormEvent((Entity)entity.getBukkitEntity(), (Block)blockState.getBlock(), (BlockState)blockState);
/* 1703 */     world.getServer().getPluginManager().callEvent((Event)event);
/*      */     
/* 1705 */     if (!event.isCancelled()) {
/* 1706 */       blockState.update(true);
/*      */     }
/*      */     
/* 1709 */     return !event.isCancelled();
/*      */   }
/*      */   
/*      */   public static boolean handleBatToggleSleepEvent(Entity bat, boolean awake) {
/* 1713 */     BatToggleSleepEvent event = new BatToggleSleepEvent((Bat)bat.getBukkitEntity(), awake);
/* 1714 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1715 */     return !event.isCancelled();
/*      */   }
/*      */   
/*      */   public static boolean handlePlayerRecipeListUpdateEvent(EntityHuman who, MinecraftKey recipe) {
/* 1719 */     PlayerRecipeDiscoverEvent event = new PlayerRecipeDiscoverEvent((Player)who.getBukkitEntity(), CraftNamespacedKey.fromMinecraft(recipe));
/* 1720 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1721 */     return !event.isCancelled();
/*      */   }
/*      */   
/*      */   public static EntityPickupItemEvent callEntityPickupItemEvent(Entity who, EntityItem item, int remaining, boolean cancelled) {
/* 1725 */     EntityPickupItemEvent event = new EntityPickupItemEvent((LivingEntity)who.getBukkitEntity(), (Item)item.getBukkitEntity(), remaining);
/* 1726 */     event.setCancelled(cancelled);
/* 1727 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1728 */     return event;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean callRaidTriggerEvent(Raid raid, EntityPlayer player) {
/* 1735 */     RaidTriggerEvent event = new RaidTriggerEvent((Raid)new CraftRaid(raid), (World)raid.getWorld().getWorld(), (Player)player.getBukkitEntity());
/* 1736 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1737 */     return !event.isCancelled();
/*      */   }
/*      */   
/*      */   public static void callRaidFinishEvent(Raid raid, List<Player> players) {
/* 1741 */     RaidFinishEvent event = new RaidFinishEvent((Raid)new CraftRaid(raid), (World)raid.getWorld().getWorld(), players);
/* 1742 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */   }
/*      */   
/*      */   public static void callRaidStopEvent(Raid raid, RaidStopEvent.Reason reason) {
/* 1746 */     RaidStopEvent event = new RaidStopEvent((Raid)new CraftRaid(raid), (World)raid.getWorld().getWorld(), reason);
/* 1747 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */   }
/*      */   
/*      */   public static void callRaidSpawnWaveEvent(Raid raid, EntityRaider leader, List<EntityRaider> raiders) {
/* 1751 */     CraftRaider craftRaider = (CraftRaider)leader.getBukkitEntity();
/* 1752 */     List<Raider> craftRaiders = new ArrayList<>();
/* 1753 */     for (EntityRaider entityRaider : raiders) {
/* 1754 */       craftRaiders.add((Raider)entityRaider.getBukkitEntity());
/*      */     }
/* 1756 */     RaidSpawnWaveEvent event = new RaidSpawnWaveEvent((Raid)new CraftRaid(raid), (World)raid.getWorld().getWorld(), (Raider)craftRaider, craftRaiders);
/* 1757 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */   }
/*      */   
/*      */   public static LootGenerateEvent callLootGenerateEvent(IInventory inventory, LootTable lootTable, LootTableInfo lootInfo, List<ItemStack> loot, boolean plugin) {
/* 1761 */     CraftWorld world = lootInfo.getWorld().getWorld();
/* 1762 */     Entity entity = (Entity)lootInfo.getContextParameter(LootContextParameters.THIS_ENTITY);
/* 1763 */     NamespacedKey key = CraftNamespacedKey.fromMinecraft((MinecraftKey)(world.getHandle().getMinecraftServer().getLootTableRegistry()).lootTableToKey.get(lootTable));
/* 1764 */     CraftLootTable craftLootTable = new CraftLootTable(key, lootTable);
/* 1765 */     List<ItemStack> bukkitLoot = (List<ItemStack>)loot.stream().map(CraftItemStack::asCraftMirror).collect(Collectors.toCollection(ArrayList::new));
/*      */     
/* 1767 */     LootGenerateEvent event = new LootGenerateEvent((World)world, (entity != null) ? (Entity)entity.getBukkitEntity() : null, inventory.getOwner(), (LootTable)craftLootTable, CraftLootTable.convertContext(lootInfo), bukkitLoot, plugin);
/* 1768 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1769 */     return event;
/*      */   }
/*      */   
/*      */   public static void callStriderTemperatureChangeEvent(EntityStrider strider, boolean shivering) {
/* 1773 */     StriderTemperatureChangeEvent event = new StriderTemperatureChangeEvent((Strider)strider.getBukkitEntity(), shivering);
/* 1774 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */   }
/*      */   
/*      */   public static boolean handleEntitySpellCastEvent(EntityIllagerWizard caster, EntityIllagerWizard.Spell spell) {
/* 1778 */     EntitySpellCastEvent event = new EntitySpellCastEvent((Spellcaster)caster.getBukkitEntity(), CraftSpellcaster.toBukkitSpell(spell));
/* 1779 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 1780 */     return !event.isCancelled();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ArrowBodyCountChangeEvent callArrowBodyCountChangeEvent(EntityLiving entity, int oldAmount, int newAmount, boolean isReset) {
/* 1787 */     LivingEntity bukkitEntity = (LivingEntity)entity.getBukkitEntity();
/*      */     
/* 1789 */     ArrowBodyCountChangeEvent event = new ArrowBodyCountChangeEvent(bukkitEntity, oldAmount, newAmount, isReset);
/* 1790 */     Bukkit.getPluginManager().callEvent((Event)event);
/*      */     
/* 1792 */     return event;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\event\CraftEventFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */