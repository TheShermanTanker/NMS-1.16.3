/*      */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*      */ import com.google.common.base.Function;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.CompletableFuture;
/*      */ import net.md_5.bungee.api.chat.BaseComponent;
/*      */ import net.minecraft.server.v1_16_R2.AxisAlignedBB;
/*      */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*      */ import net.minecraft.server.v1_16_R2.Chunk;
/*      */ import net.minecraft.server.v1_16_R2.ChunkProviderServer;
/*      */ import net.minecraft.server.v1_16_R2.DamageSource;
/*      */ import net.minecraft.server.v1_16_R2.Entity;
/*      */ import net.minecraft.server.v1_16_R2.EntityAmbient;
/*      */ import net.minecraft.server.v1_16_R2.EntityAreaEffectCloud;
/*      */ import net.minecraft.server.v1_16_R2.EntityArmorStand;
/*      */ import net.minecraft.server.v1_16_R2.EntityArrow;
/*      */ import net.minecraft.server.v1_16_R2.EntityBat;
/*      */ import net.minecraft.server.v1_16_R2.EntityBee;
/*      */ import net.minecraft.server.v1_16_R2.EntityBlaze;
/*      */ import net.minecraft.server.v1_16_R2.EntityCat;
/*      */ import net.minecraft.server.v1_16_R2.EntityCaveSpider;
/*      */ import net.minecraft.server.v1_16_R2.EntityChicken;
/*      */ import net.minecraft.server.v1_16_R2.EntityCod;
/*      */ import net.minecraft.server.v1_16_R2.EntityComplexPart;
/*      */ import net.minecraft.server.v1_16_R2.EntityCow;
/*      */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*      */ import net.minecraft.server.v1_16_R2.EntityCreeper;
/*      */ import net.minecraft.server.v1_16_R2.EntityDolphin;
/*      */ import net.minecraft.server.v1_16_R2.EntityDragonFireball;
/*      */ import net.minecraft.server.v1_16_R2.EntityDrowned;
/*      */ import net.minecraft.server.v1_16_R2.EntityEgg;
/*      */ import net.minecraft.server.v1_16_R2.EntityEnderCrystal;
/*      */ import net.minecraft.server.v1_16_R2.EntityEnderDragon;
/*      */ import net.minecraft.server.v1_16_R2.EntityEnderPearl;
/*      */ import net.minecraft.server.v1_16_R2.EntityEnderSignal;
/*      */ import net.minecraft.server.v1_16_R2.EntityEnderman;
/*      */ import net.minecraft.server.v1_16_R2.EntityEndermite;
/*      */ import net.minecraft.server.v1_16_R2.EntityEvoker;
/*      */ import net.minecraft.server.v1_16_R2.EntityEvokerFangs;
/*      */ import net.minecraft.server.v1_16_R2.EntityExperienceOrb;
/*      */ import net.minecraft.server.v1_16_R2.EntityFallingBlock;
/*      */ import net.minecraft.server.v1_16_R2.EntityFireball;
/*      */ import net.minecraft.server.v1_16_R2.EntityFireworks;
/*      */ import net.minecraft.server.v1_16_R2.EntityFish;
/*      */ import net.minecraft.server.v1_16_R2.EntityFishingHook;
/*      */ import net.minecraft.server.v1_16_R2.EntityFox;
/*      */ import net.minecraft.server.v1_16_R2.EntityGiantZombie;
/*      */ import net.minecraft.server.v1_16_R2.EntityGuardian;
/*      */ import net.minecraft.server.v1_16_R2.EntityGuardianElder;
/*      */ import net.minecraft.server.v1_16_R2.EntityHanging;
/*      */ import net.minecraft.server.v1_16_R2.EntityHoglin;
/*      */ import net.minecraft.server.v1_16_R2.EntityHorse;
/*      */ import net.minecraft.server.v1_16_R2.EntityHorseDonkey;
/*      */ import net.minecraft.server.v1_16_R2.EntityHorseMule;
/*      */ import net.minecraft.server.v1_16_R2.EntityHorseSkeleton;
/*      */ import net.minecraft.server.v1_16_R2.EntityHorseZombie;
/*      */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*      */ import net.minecraft.server.v1_16_R2.EntityIllagerAbstract;
/*      */ import net.minecraft.server.v1_16_R2.EntityIllagerIllusioner;
/*      */ import net.minecraft.server.v1_16_R2.EntityIllagerWizard;
/*      */ import net.minecraft.server.v1_16_R2.EntityIronGolem;
/*      */ import net.minecraft.server.v1_16_R2.EntityItem;
/*      */ import net.minecraft.server.v1_16_R2.EntityItemFrame;
/*      */ import net.minecraft.server.v1_16_R2.EntityLargeFireball;
/*      */ import net.minecraft.server.v1_16_R2.EntityLeash;
/*      */ import net.minecraft.server.v1_16_R2.EntityLightning;
/*      */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*      */ import net.minecraft.server.v1_16_R2.EntityLlama;
/*      */ import net.minecraft.server.v1_16_R2.EntityLlamaSpit;
/*      */ import net.minecraft.server.v1_16_R2.EntityLlamaTrader;
/*      */ import net.minecraft.server.v1_16_R2.EntityMagmaCube;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartAbstract;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartChest;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartCommandBlock;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartFurnace;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartHopper;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartMobSpawner;
/*      */ import net.minecraft.server.v1_16_R2.EntityMinecartTNT;
/*      */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*      */ import net.minecraft.server.v1_16_R2.EntityMushroomCow;
/*      */ import net.minecraft.server.v1_16_R2.EntityOcelot;
/*      */ import net.minecraft.server.v1_16_R2.EntityPainting;
/*      */ import net.minecraft.server.v1_16_R2.EntityPanda;
/*      */ import net.minecraft.server.v1_16_R2.EntityParrot;
/*      */ import net.minecraft.server.v1_16_R2.EntityPhantom;
/*      */ import net.minecraft.server.v1_16_R2.EntityPig;
/*      */ import net.minecraft.server.v1_16_R2.EntityPigZombie;
/*      */ import net.minecraft.server.v1_16_R2.EntityPiglin;
/*      */ import net.minecraft.server.v1_16_R2.EntityPiglinAbstract;
/*      */ import net.minecraft.server.v1_16_R2.EntityPiglinBrute;
/*      */ import net.minecraft.server.v1_16_R2.EntityPillager;
/*      */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*      */ import net.minecraft.server.v1_16_R2.EntityPolarBear;
/*      */ import net.minecraft.server.v1_16_R2.EntityPotion;
/*      */ import net.minecraft.server.v1_16_R2.EntityPufferFish;
/*      */ import net.minecraft.server.v1_16_R2.EntityRabbit;
/*      */ import net.minecraft.server.v1_16_R2.EntityRavager;
/*      */ import net.minecraft.server.v1_16_R2.EntitySalmon;
/*      */ import net.minecraft.server.v1_16_R2.EntityShulker;
/*      */ import net.minecraft.server.v1_16_R2.EntityShulkerBullet;
/*      */ import net.minecraft.server.v1_16_R2.EntitySilverfish;
/*      */ import net.minecraft.server.v1_16_R2.EntitySkeletonAbstract;
/*      */ import net.minecraft.server.v1_16_R2.EntitySkeletonStray;
/*      */ import net.minecraft.server.v1_16_R2.EntitySkeletonWither;
/*      */ import net.minecraft.server.v1_16_R2.EntitySlime;
/*      */ import net.minecraft.server.v1_16_R2.EntitySmallFireball;
/*      */ import net.minecraft.server.v1_16_R2.EntitySnowball;
/*      */ import net.minecraft.server.v1_16_R2.EntitySnowman;
/*      */ import net.minecraft.server.v1_16_R2.EntitySpectralArrow;
/*      */ import net.minecraft.server.v1_16_R2.EntitySpider;
/*      */ import net.minecraft.server.v1_16_R2.EntitySquid;
/*      */ import net.minecraft.server.v1_16_R2.EntityStrider;
/*      */ import net.minecraft.server.v1_16_R2.EntityTNTPrimed;
/*      */ import net.minecraft.server.v1_16_R2.EntityThrownTrident;
/*      */ import net.minecraft.server.v1_16_R2.EntityTippedArrow;
/*      */ import net.minecraft.server.v1_16_R2.EntityTropicalFish;
/*      */ import net.minecraft.server.v1_16_R2.EntityTurtle;
/*      */ import net.minecraft.server.v1_16_R2.EntityVex;
/*      */ import net.minecraft.server.v1_16_R2.EntityVillager;
/*      */ import net.minecraft.server.v1_16_R2.EntityVillagerAbstract;
/*      */ import net.minecraft.server.v1_16_R2.EntityVillagerTrader;
/*      */ import net.minecraft.server.v1_16_R2.EntityVindicator;
/*      */ import net.minecraft.server.v1_16_R2.EntityWitch;
/*      */ import net.minecraft.server.v1_16_R2.EntityWither;
/*      */ import net.minecraft.server.v1_16_R2.EntityWitherSkull;
/*      */ import net.minecraft.server.v1_16_R2.EntityWolf;
/*      */ import net.minecraft.server.v1_16_R2.EntityZoglin;
/*      */ import net.minecraft.server.v1_16_R2.EntityZombie;
/*      */ import net.minecraft.server.v1_16_R2.EntityZombieHusk;
/*      */ import net.minecraft.server.v1_16_R2.EntityZombieVillager;
/*      */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*      */ import net.minecraft.server.v1_16_R2.IChunkAccess;
/*      */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*      */ import net.minecraft.server.v1_16_R2.NBTBase;
/*      */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*      */ import net.minecraft.server.v1_16_R2.TicketType;
/*      */ import net.minecraft.server.v1_16_R2.WorldServer;
/*      */ import org.bukkit.Chunk;
/*      */ import org.bukkit.EntityEffect;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Server;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.block.BlockFace;
/*      */ import org.bukkit.block.PistonMoveReaction;
/*      */ import org.bukkit.command.CommandSender;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.persistence.CraftPersistentDataContainer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.persistence.CraftPersistentDataTypeRegistry;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftVector;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.Pose;
/*      */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*      */ import org.bukkit.event.entity.EntityDamageEvent;
/*      */ import org.bukkit.event.player.PlayerTeleportEvent;
/*      */ import org.bukkit.metadata.MetadataValue;
/*      */ import org.bukkit.permissions.PermissibleBase;
/*      */ import org.bukkit.permissions.Permission;
/*      */ import org.bukkit.permissions.PermissionAttachment;
/*      */ import org.bukkit.permissions.PermissionAttachmentInfo;
/*      */ import org.bukkit.permissions.ServerOperator;
/*      */ import org.bukkit.persistence.PersistentDataContainer;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ import org.bukkit.util.BoundingBox;
/*      */ import org.bukkit.util.NumberConversions;
/*      */ import org.bukkit.util.Vector;
/*      */ import org.spigotmc.AsyncCatcher;
/*      */ 
/*      */ public abstract class CraftEntity implements Entity {
/*  176 */   private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();
/*      */   private static PermissibleBase perm;
/*      */   protected final CraftServer server;
/*      */   protected Entity entity;
/*      */   private EntityDamageEvent lastDamageEvent;
/*  181 */   private final CraftPersistentDataContainer persistentDataContainer = new CraftPersistentDataContainer(DATA_TYPE_REGISTRY);
/*      */ 
/*      */ 
/*      */   
/*      */   private final Entity.Spigot spigot;
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk getChunk() {
/*  190 */     Chunk currentChunk = this.entity.getCurrentChunk();
/*  191 */     return (currentChunk != null) ? currentChunk.bukkitChunk : getLocation().getChunk();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CraftEntity getEntity(CraftServer server, Entity entity) {
/*  199 */     if (entity instanceof EntityLiving)
/*      */     
/*  201 */     { if (entity instanceof EntityHuman) {
/*  202 */         if (entity instanceof EntityPlayer) return new CraftPlayer(server, (EntityPlayer)entity); 
/*  203 */         return new CraftHumanEntity(server, (EntityHuman)entity);
/*      */       } 
/*      */       
/*  206 */       if (entity instanceof EntityWaterAnimal) {
/*  207 */         if (entity instanceof EntitySquid) return new CraftSquid(server, (EntitySquid)entity); 
/*  208 */         if (entity instanceof EntityFish) {
/*  209 */           if (entity instanceof EntityCod) return new CraftCod(server, (EntityCod)entity); 
/*  210 */           if (entity instanceof EntityPufferFish) return new CraftPufferFish(server, (EntityPufferFish)entity); 
/*  211 */           if (entity instanceof EntitySalmon) return new CraftSalmon(server, (EntitySalmon)entity); 
/*  212 */           if (entity instanceof EntityTropicalFish) return new CraftTropicalFish(server, (EntityTropicalFish)entity); 
/*  213 */           return new CraftFish(server, (EntityFish)entity);
/*      */         } 
/*  215 */         if (entity instanceof EntityDolphin) return new CraftDolphin(server, (EntityDolphin)entity); 
/*  216 */         return new CraftWaterMob(server, (EntityWaterAnimal)entity);
/*      */       } 
/*  218 */       if (entity instanceof EntityCreature)
/*      */       
/*  220 */       { if (entity instanceof EntityAnimal)
/*  221 */         { if (entity instanceof EntityChicken) return new CraftChicken(server, (EntityChicken)entity); 
/*  222 */           if (entity instanceof EntityCow) {
/*  223 */             if (entity instanceof EntityMushroomCow) return new CraftMushroomCow(server, (EntityMushroomCow)entity); 
/*  224 */             return new CraftCow(server, (EntityCow)entity);
/*      */           } 
/*  226 */           if (entity instanceof EntityPig) return new CraftPig(server, (EntityPig)entity); 
/*  227 */           if (entity instanceof net.minecraft.server.v1_16_R2.EntityTameableAnimal) {
/*  228 */             if (entity instanceof EntityWolf) return new CraftWolf(server, (EntityWolf)entity); 
/*  229 */             if (entity instanceof EntityCat) return new CraftCat(server, (EntityCat)entity); 
/*  230 */             if (entity instanceof EntityParrot) return new CraftParrot(server, (EntityParrot)entity); 
/*      */           } else {
/*  232 */             if (entity instanceof EntitySheep) return new CraftSheep(server, (EntitySheep)entity); 
/*  233 */             if (entity instanceof net.minecraft.server.v1_16_R2.EntityHorseAbstract)
/*  234 */             { if (entity instanceof net.minecraft.server.v1_16_R2.EntityHorseChestedAbstract)
/*  235 */               { if (entity instanceof EntityHorseDonkey) return new CraftDonkey(server, (EntityHorseDonkey)entity); 
/*  236 */                 if (entity instanceof EntityHorseMule) return new CraftMule(server, (EntityHorseMule)entity); 
/*  237 */                 if (entity instanceof EntityLlamaTrader) return new CraftTraderLlama(server, (EntityLlamaTrader)entity); 
/*  238 */                 if (entity instanceof EntityLlama) return new CraftLlama(server, (EntityLlama)entity);  }
/*  239 */               else { if (entity instanceof EntityHorse) return new CraftHorse(server, (EntityHorse)entity); 
/*  240 */                 if (entity instanceof EntityHorseSkeleton) return new CraftSkeletonHorse(server, (EntityHorseSkeleton)entity); 
/*  241 */                 if (entity instanceof EntityHorseZombie) return new CraftZombieHorse(server, (EntityHorseZombie)entity);  }
/*      */                }
/*  243 */             else { if (entity instanceof EntityRabbit) return new CraftRabbit(server, (EntityRabbit)entity); 
/*  244 */               if (entity instanceof EntityPolarBear) return new CraftPolarBear(server, (EntityPolarBear)entity); 
/*  245 */               if (entity instanceof EntityTurtle) return new CraftTurtle(server, (EntityTurtle)entity); 
/*  246 */               if (entity instanceof EntityOcelot) return new CraftOcelot(server, (EntityOcelot)entity); 
/*  247 */               if (entity instanceof EntityPanda) return new CraftPanda(server, (EntityPanda)entity); 
/*  248 */               if (entity instanceof EntityFox) return new CraftFox(server, (EntityFox)entity); 
/*  249 */               if (entity instanceof EntityBee) return new CraftBee(server, (EntityBee)entity); 
/*  250 */               if (entity instanceof EntityHoglin) return new CraftHoglin(server, (EntityHoglin)entity); 
/*  251 */               if (entity instanceof EntityStrider) return new CraftStrider(server, (EntityStrider)entity); 
/*  252 */               return new CraftAnimals(server, (EntityAnimal)entity); }
/*      */           
/*      */           }  }
/*  255 */         else { if (entity instanceof EntityMonster) {
/*  256 */             if (entity instanceof EntityZombie) {
/*  257 */               if (entity instanceof EntityPigZombie) return new CraftPigZombie(server, (EntityPigZombie)entity); 
/*  258 */               if (entity instanceof EntityZombieHusk) return new CraftHusk(server, (EntityZombieHusk)entity); 
/*  259 */               if (entity instanceof EntityZombieVillager) return new CraftVillagerZombie(server, (EntityZombieVillager)entity); 
/*  260 */               if (entity instanceof EntityDrowned) return new CraftDrowned(server, (EntityDrowned)entity); 
/*  261 */               return new CraftZombie(server, (EntityZombie)entity);
/*      */             } 
/*  263 */             if (entity instanceof EntityCreeper) return new CraftCreeper(server, (EntityCreeper)entity); 
/*  264 */             if (entity instanceof EntityEnderman) return new CraftEnderman(server, (EntityEnderman)entity); 
/*  265 */             if (entity instanceof EntitySilverfish) return new CraftSilverfish(server, (EntitySilverfish)entity); 
/*  266 */             if (entity instanceof EntityGiantZombie) return new CraftGiant(server, (EntityGiantZombie)entity); 
/*  267 */             if (entity instanceof EntitySkeletonAbstract) {
/*  268 */               if (entity instanceof EntitySkeletonStray) return new CraftStray(server, (EntitySkeletonStray)entity); 
/*  269 */               if (entity instanceof EntitySkeletonWither) return new CraftWitherSkeleton(server, (EntitySkeletonWither)entity); 
/*  270 */               return new CraftSkeleton(server, (EntitySkeletonAbstract)entity);
/*      */             } 
/*  272 */             if (entity instanceof EntityBlaze) return new CraftBlaze(server, (EntityBlaze)entity); 
/*  273 */             if (entity instanceof EntityWitch) return new CraftWitch(server, (EntityWitch)entity); 
/*  274 */             if (entity instanceof EntityWither) return new CraftWither(server, (EntityWither)entity); 
/*  275 */             if (entity instanceof EntitySpider) {
/*  276 */               if (entity instanceof EntityCaveSpider) return new CraftCaveSpider(server, (EntityCaveSpider)entity); 
/*  277 */               return new CraftSpider(server, (EntitySpider)entity);
/*      */             } 
/*  279 */             if (entity instanceof EntityEndermite) return new CraftEndermite(server, (EntityEndermite)entity); 
/*  280 */             if (entity instanceof EntityGuardian) {
/*  281 */               if (entity instanceof EntityGuardianElder) return new CraftElderGuardian(server, (EntityGuardianElder)entity); 
/*  282 */               return new CraftGuardian(server, (EntityGuardian)entity);
/*      */             } 
/*  284 */             if (entity instanceof EntityVex) return new CraftVex(server, (EntityVex)entity); 
/*  285 */             if (entity instanceof EntityIllagerAbstract) {
/*  286 */               if (entity instanceof EntityIllagerWizard) {
/*  287 */                 if (entity instanceof EntityEvoker) return new CraftEvoker(server, (EntityEvoker)entity); 
/*  288 */                 if (entity instanceof EntityIllagerIllusioner) return new CraftIllusioner(server, (EntityIllagerIllusioner)entity); 
/*  289 */                 return new CraftSpellcaster(server, (EntityIllagerWizard)entity);
/*      */               } 
/*  291 */               if (entity instanceof EntityVindicator) return new CraftVindicator(server, (EntityVindicator)entity); 
/*  292 */               if (entity instanceof EntityPillager) return new CraftPillager(server, (EntityPillager)entity); 
/*  293 */               return new CraftIllager(server, (EntityIllagerAbstract)entity);
/*      */             } 
/*  295 */             if (entity instanceof EntityRavager) return new CraftRavager(server, (EntityRavager)entity); 
/*  296 */             if (entity instanceof EntityPiglinAbstract) {
/*  297 */               if (entity instanceof EntityPiglin) return new CraftPiglin(server, (EntityPiglin)entity); 
/*  298 */               if (entity instanceof EntityPiglinBrute) return new CraftPiglinBrute(server, (EntityPiglinBrute)entity); 
/*  299 */               return new CraftPiglinAbstract(server, (EntityPiglinAbstract)entity);
/*      */             } 
/*  301 */             if (entity instanceof EntityZoglin) return new CraftZoglin(server, (EntityZoglin)entity);
/*      */             
/*  303 */             return new CraftMonster(server, (EntityMonster)entity);
/*      */           } 
/*  305 */           if (entity instanceof net.minecraft.server.v1_16_R2.EntityGolem) {
/*  306 */             if (entity instanceof EntitySnowman) return new CraftSnowman(server, (EntitySnowman)entity); 
/*  307 */             if (entity instanceof EntityIronGolem) return new CraftIronGolem(server, (EntityIronGolem)entity); 
/*  308 */             if (entity instanceof EntityShulker) return new CraftShulker(server, (EntityShulker)entity); 
/*      */           } else {
/*  310 */             if (entity instanceof EntityVillagerAbstract) {
/*  311 */               if (entity instanceof EntityVillager) return new CraftVillager(server, (EntityVillager)entity); 
/*  312 */               if (entity instanceof EntityVillagerTrader) return new CraftWanderingTrader(server, (EntityVillagerTrader)entity); 
/*  313 */               return new CraftAbstractVillager(server, (EntityVillagerAbstract)entity);
/*      */             } 
/*  315 */             return new CraftCreature(server, (EntityCreature)entity);
/*      */           }  }
/*      */          }
/*  318 */       else { if (entity instanceof EntitySlime) {
/*  319 */           if (entity instanceof EntityMagmaCube) return new CraftMagmaCube(server, (EntityMagmaCube)entity); 
/*  320 */           return new CraftSlime(server, (EntitySlime)entity);
/*      */         } 
/*      */         
/*  323 */         if (entity instanceof EntityFlying) {
/*  324 */           if (entity instanceof EntityGhast) return new CraftGhast(server, (EntityGhast)entity); 
/*  325 */           if (entity instanceof EntityPhantom) return new CraftPhantom(server, (EntityPhantom)entity); 
/*  326 */           return new CraftFlying(server, (EntityFlying)entity);
/*      */         } 
/*  328 */         if (entity instanceof EntityEnderDragon) {
/*  329 */           return new CraftEnderDragon(server, (EntityEnderDragon)entity);
/*      */         }
/*      */         
/*  332 */         if (entity instanceof EntityAmbient) {
/*  333 */           if (entity instanceof EntityBat) return new CraftBat(server, (EntityBat)entity); 
/*  334 */           return new CraftAmbient(server, (EntityAmbient)entity);
/*      */         } 
/*  336 */         if (entity instanceof EntityArmorStand) return new CraftArmorStand(server, (EntityArmorStand)entity); 
/*  337 */         return new CraftLivingEntity(server, (EntityLiving)entity); }
/*      */        }
/*  339 */     else { if (entity instanceof EntityComplexPart) {
/*  340 */         EntityComplexPart part = (EntityComplexPart)entity;
/*  341 */         if (part.owner instanceof EntityEnderDragon) return new CraftEnderDragonPart(server, (EntityComplexPart)entity); 
/*  342 */         return new CraftComplexPart(server, (EntityComplexPart)entity);
/*      */       } 
/*  344 */       if (entity instanceof EntityExperienceOrb) return new CraftExperienceOrb(server, (EntityExperienceOrb)entity); 
/*  345 */       if (entity instanceof EntityTippedArrow) return new CraftTippedArrow(server, (EntityTippedArrow)entity); 
/*  346 */       if (entity instanceof EntitySpectralArrow) return new CraftSpectralArrow(server, (EntitySpectralArrow)entity); 
/*  347 */       if (entity instanceof EntityArrow) {
/*  348 */         if (entity instanceof EntityThrownTrident) return new CraftTrident(server, (EntityThrownTrident)entity); 
/*  349 */         return new CraftArrow(server, (EntityArrow)entity);
/*      */       } 
/*  351 */       if (entity instanceof EntityBoat) return new CraftBoat(server, (EntityBoat)entity); 
/*  352 */       if (entity instanceof net.minecraft.server.v1_16_R2.EntityProjectile) {
/*  353 */         if (entity instanceof EntityEgg) return new CraftEgg(server, (EntityEgg)entity); 
/*  354 */         if (entity instanceof EntitySnowball) return new CraftSnowball(server, (EntitySnowball)entity); 
/*  355 */         if (entity instanceof EntityPotion) return new CraftThrownPotion(server, (EntityPotion)entity); 
/*  356 */         if (entity instanceof EntityEnderPearl) return new CraftEnderPearl(server, (EntityEnderPearl)entity); 
/*  357 */         if (entity instanceof EntityThrownExpBottle) return new CraftThrownExpBottle(server, (EntityThrownExpBottle)entity); 
/*      */       } else {
/*  359 */         if (entity instanceof EntityFallingBlock) return new CraftFallingBlock(server, (EntityFallingBlock)entity); 
/*  360 */         if (entity instanceof EntityFireball) {
/*  361 */           if (entity instanceof EntitySmallFireball) return new CraftSmallFireball(server, (EntitySmallFireball)entity); 
/*  362 */           if (entity instanceof EntityLargeFireball) return new CraftLargeFireball(server, (EntityLargeFireball)entity); 
/*  363 */           if (entity instanceof EntityWitherSkull) return new CraftWitherSkull(server, (EntityWitherSkull)entity); 
/*  364 */           if (entity instanceof EntityDragonFireball) return new CraftDragonFireball(server, (EntityDragonFireball)entity); 
/*  365 */           return new CraftFireball(server, (EntityFireball)entity);
/*      */         } 
/*  367 */         if (entity instanceof EntityEnderSignal) return new CraftEnderSignal(server, (EntityEnderSignal)entity); 
/*  368 */         if (entity instanceof EntityEnderCrystal) return new CraftEnderCrystal(server, (EntityEnderCrystal)entity); 
/*  369 */         if (entity instanceof EntityFishingHook) return new CraftFishHook(server, (EntityFishingHook)entity); 
/*  370 */         if (entity instanceof EntityItem) return new CraftItem(server, (EntityItem)entity); 
/*  371 */         if (entity instanceof EntityLightning) return new CraftLightningStrike(server, (EntityLightning)entity); 
/*  372 */         if (entity instanceof EntityMinecartAbstract)
/*  373 */         { if (entity instanceof EntityMinecartFurnace) return new CraftMinecartFurnace(server, (EntityMinecartFurnace)entity); 
/*  374 */           if (entity instanceof EntityMinecartChest) return new CraftMinecartChest(server, (EntityMinecartChest)entity); 
/*  375 */           if (entity instanceof EntityMinecartTNT) return new CraftMinecartTNT(server, (EntityMinecartTNT)entity); 
/*  376 */           if (entity instanceof EntityMinecartHopper) return new CraftMinecartHopper(server, (EntityMinecartHopper)entity); 
/*  377 */           if (entity instanceof EntityMinecartMobSpawner) return new CraftMinecartMobSpawner(server, (EntityMinecartMobSpawner)entity); 
/*  378 */           if (entity instanceof net.minecraft.server.v1_16_R2.EntityMinecartRideable) return new CraftMinecartRideable(server, (EntityMinecartAbstract)entity); 
/*  379 */           if (entity instanceof EntityMinecartCommandBlock) return new CraftMinecartCommand(server, (EntityMinecartCommandBlock)entity);  }
/*  380 */         else { if (entity instanceof EntityHanging) {
/*  381 */             if (entity instanceof EntityPainting) return new CraftPainting(server, (EntityPainting)entity); 
/*  382 */             if (entity instanceof EntityItemFrame) return new CraftItemFrame(server, (EntityItemFrame)entity); 
/*  383 */             if (entity instanceof EntityLeash) return new CraftLeash(server, (EntityLeash)entity); 
/*  384 */             return new CraftHanging(server, (EntityHanging)entity);
/*      */           } 
/*  386 */           if (entity instanceof EntityTNTPrimed) return new CraftTNTPrimed(server, (EntityTNTPrimed)entity); 
/*  387 */           if (entity instanceof EntityFireworks) return new CraftFirework(server, (EntityFireworks)entity); 
/*  388 */           if (entity instanceof EntityShulkerBullet) return new CraftShulkerBullet(server, (EntityShulkerBullet)entity); 
/*  389 */           if (entity instanceof EntityAreaEffectCloud) return new CraftAreaEffectCloud(server, (EntityAreaEffectCloud)entity); 
/*  390 */           if (entity instanceof EntityEvokerFangs) return new CraftEvokerFangs(server, (EntityEvokerFangs)entity); 
/*  391 */           if (entity instanceof EntityLlamaSpit) return new CraftLlamaSpit(server, (EntityLlamaSpit)entity);  }
/*      */       
/*      */       }  }
/*  394 */      throw new AssertionError("Unknown entity " + ((entity == null) ? null : entity.getClass()));
/*      */   }
/*      */ 
/*      */   
/*      */   public Location getLocation() {
/*  399 */     return new Location(getWorld(), this.entity.locX(), this.entity.locY(), this.entity.locZ(), this.entity.getBukkitYaw(), this.entity.pitch);
/*      */   }
/*      */ 
/*      */   
/*      */   public Location getLocation(Location loc) {
/*  404 */     if (loc != null) {
/*  405 */       loc.setWorld(getWorld());
/*  406 */       loc.setX(this.entity.locX());
/*  407 */       loc.setY(this.entity.locY());
/*  408 */       loc.setZ(this.entity.locZ());
/*  409 */       loc.setYaw(this.entity.getBukkitYaw());
/*  410 */       loc.setPitch(this.entity.pitch);
/*      */     } 
/*      */     
/*  413 */     return loc;
/*      */   }
/*      */ 
/*      */   
/*      */   public Vector getVelocity() {
/*  418 */     return CraftVector.toBukkit(this.entity.getMot());
/*      */   }
/*      */ 
/*      */   
/*      */   public void setVelocity(Vector velocity) {
/*  423 */     Preconditions.checkArgument((velocity != null), "velocity");
/*  424 */     velocity.checkFinite();
/*      */     
/*  426 */     if (!(this instanceof org.bukkit.entity.Projectile) && isUnsafeVelocity(velocity)) {
/*  427 */       CraftServer.excessiveVelEx = new Exception("Excessive velocity set detected: tried to set velocity of entity " + this.entity.getName() + " id #" + getEntityId() + " to (" + velocity.getX() + "," + velocity.getY() + "," + velocity.getZ() + ").");
/*      */     }
/*      */ 
/*      */     
/*  431 */     this.entity.setMot(CraftVector.toNMS(velocity));
/*  432 */     this.entity.velocityChanged = true;
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
/*      */   private static boolean isUnsafeVelocity(Vector vel) {
/*  448 */     double x = vel.getX();
/*  449 */     double y = vel.getY();
/*  450 */     double z = vel.getZ();
/*      */     
/*  452 */     if (x > 4.0D || x < -4.0D || y > 4.0D || y < -4.0D || z > 4.0D || z < -4.0D) {
/*  453 */       return true;
/*      */     }
/*      */     
/*  456 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getHeight() {
/*  462 */     return getHandle().getHeight();
/*      */   }
/*      */ 
/*      */   
/*      */   public double getWidth() {
/*  467 */     return getHandle().getWidth();
/*      */   }
/*      */ 
/*      */   
/*      */   public BoundingBox getBoundingBox() {
/*  472 */     AxisAlignedBB bb = getHandle().getBoundingBox();
/*  473 */     return new BoundingBox(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOnGround() {
/*  478 */     if (this.entity instanceof EntityArrow) {
/*  479 */       return ((EntityArrow)this.entity).inGround;
/*      */     }
/*  481 */     return this.entity.isOnGround();
/*      */   }
/*      */ 
/*      */   
/*      */   public World getWorld() {
/*  486 */     return (World)this.entity.world.getWorld();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRotation(float yaw, float pitch) {
/*  491 */     NumberConversions.checkFinite(pitch, "pitch not finite");
/*  492 */     NumberConversions.checkFinite(yaw, "yaw not finite");
/*      */     
/*  494 */     yaw = Location.normalizeYaw(yaw);
/*  495 */     pitch = Location.normalizePitch(pitch);
/*      */     
/*  497 */     this.entity.yaw = yaw;
/*  498 */     this.entity.pitch = pitch;
/*  499 */     this.entity.lastYaw = yaw;
/*  500 */     this.entity.lastPitch = pitch;
/*  501 */     this.entity.setHeadRotation(yaw);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean teleport(Location location) {
/*  508 */     return teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
/*  513 */     Preconditions.checkArgument((location != null), "location");
/*  514 */     location.checkFinite();
/*      */     
/*  516 */     if (this.entity.isVehicle() || this.entity.dead) {
/*  517 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  521 */     this.entity.stopRiding();
/*      */ 
/*      */     
/*  524 */     if (!location.getWorld().equals(getWorld())) {
/*  525 */       this.entity.teleportTo(((CraftWorld)location.getWorld()).getHandle(), new BlockPosition(location.getX(), location.getY(), location.getZ()));
/*  526 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  530 */     this.entity.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
/*      */     
/*  532 */     this.entity.setHeadRotation(location.getYaw());
/*  533 */     ((WorldServer)this.entity.world).chunkCheck(this.entity);
/*      */     
/*  535 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public CompletableFuture<Boolean> teleportAsync(Location location, PlayerTeleportEvent.TeleportCause cause) {
/*  541 */     Preconditions.checkArgument((location != null), "location");
/*  542 */     location.checkFinite();
/*  543 */     Location locationClone = location.clone();
/*      */     
/*  545 */     WorldServer world = ((CraftWorld)locationClone.getWorld()).getHandle();
/*  546 */     CompletableFuture<Boolean> ret = new CompletableFuture<>();
/*      */     
/*  548 */     world.loadChunksForMoveAsync(getHandle().getBoundingBoxAt(locationClone.getX(), locationClone.getY(), locationClone.getZ()), location.getX(), location.getZ(), list -> {
/*      */           ChunkProviderServer chunkProviderServer = world.getChunkProvider();
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           for (IChunkAccess chunk : list) {
/*      */             chunkProviderServer.addTicketAtLevel(TicketType.POST_TELEPORT, chunk.getPos(), 33, Integer.valueOf(getEntityId()));
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           MinecraftServer.getServer().scheduleOnMain(());
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  565 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean teleport(Entity destination) {
/*  571 */     return teleport(destination.getLocation());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean teleport(Entity destination, PlayerTeleportEvent.TeleportCause cause) {
/*  576 */     return teleport(destination.getLocation(), cause);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Entity> getNearbyEntities(double x, double y, double z) {
/*  581 */     AsyncCatcher.catchOp("getNearbyEntities");
/*  582 */     List<Entity> notchEntityList = this.entity.world.getEntities(this.entity, this.entity.getBoundingBox().grow(x, y, z), null);
/*  583 */     List<Entity> bukkitEntityList = new ArrayList<>(notchEntityList.size());
/*      */     
/*  585 */     for (Entity e : notchEntityList) {
/*  586 */       bukkitEntityList.add(e.getBukkitEntity());
/*      */     }
/*  588 */     return bukkitEntityList;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEntityId() {
/*  593 */     return this.entity.getId();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFireTicks() {
/*  598 */     return this.entity.fireTicks;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxFireTicks() {
/*  603 */     return this.entity.getMaxFireTicks();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFireTicks(int ticks) {
/*  608 */     this.entity.fireTicks = ticks;
/*      */   }
/*      */ 
/*      */   
/*      */   public void remove() {
/*  613 */     this.entity.die();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isDead() {
/*  618 */     return !this.entity.isAlive();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isValid() {
/*  623 */     return (this.entity.isAlive() && this.entity.valid && this.entity.isChunkLoaded());
/*      */   }
/*      */ 
/*      */   
/*      */   public Server getServer() {
/*  628 */     return (Server)this.server;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPersistent() {
/*  633 */     return this.entity.persist;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPersistent(boolean persistent) {
/*  638 */     this.entity.persist = persistent;
/*      */   }
/*      */   
/*      */   public Vector getMomentum() {
/*  642 */     return getVelocity();
/*      */   }
/*      */   
/*      */   public void setMomentum(Vector value) {
/*  646 */     setVelocity(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getPassenger() {
/*  651 */     return isEmpty() ? null : ((Entity)(getHandle()).passengers.get(0)).getBukkitEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setPassenger(Entity passenger) {
/*  656 */     Preconditions.checkArgument(!equals(passenger), "Entity cannot ride itself.");
/*  657 */     if (passenger instanceof CraftEntity) {
/*  658 */       eject();
/*  659 */       return ((CraftEntity)passenger).getHandle().startRiding(getHandle());
/*      */     } 
/*  661 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Entity> getPassengers() {
/*  667 */     return Lists.newArrayList(Lists.transform((getHandle()).passengers, new Function<Entity, Entity>()
/*      */           {
/*      */             public Entity apply(Entity input) {
/*  670 */               return input.getBukkitEntity();
/*      */             }
/*      */           }));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addPassenger(Entity passenger) {
/*  677 */     Preconditions.checkArgument((passenger != null), "passenger == null");
/*      */     
/*  679 */     return ((CraftEntity)passenger).getHandle().a(getHandle(), true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removePassenger(Entity passenger) {
/*  684 */     Preconditions.checkArgument((passenger != null), "passenger == null");
/*      */     
/*  686 */     ((CraftEntity)passenger).getHandle().stopRiding();
/*  687 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEmpty() {
/*  692 */     return !getHandle().isVehicle();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean eject() {
/*  697 */     if (isEmpty()) {
/*  698 */       return false;
/*      */     }
/*      */     
/*  701 */     getHandle().ejectPassengers();
/*  702 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getFallDistance() {
/*  707 */     return (getHandle()).fallDistance;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFallDistance(float distance) {
/*  712 */     (getHandle()).fallDistance = distance;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastDamageCause(EntityDamageEvent event) {
/*  717 */     this.lastDamageEvent = event;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityDamageEvent getLastDamageCause() {
/*  722 */     return this.lastDamageEvent;
/*      */   }
/*      */ 
/*      */   
/*      */   public UUID getUniqueId() {
/*  727 */     return getHandle().getUniqueID();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTicksLived() {
/*  732 */     return (getHandle()).ticksLived;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTicksLived(int value) {
/*  737 */     if (value <= 0) {
/*  738 */       throw new IllegalArgumentException("Age must be at least 1 tick");
/*      */     }
/*  740 */     (getHandle()).ticksLived = value;
/*      */   }
/*      */   
/*      */   public Entity getHandle() {
/*  744 */     return this.entity;
/*      */   }
/*      */ 
/*      */   
/*      */   public void playEffect(EntityEffect type) {
/*  749 */     Preconditions.checkArgument((type != null), "type");
/*      */     
/*  751 */     if (type.getApplicable().isInstance(this)) {
/*  752 */       (getHandle()).world.broadcastEntityEffect(getHandle(), type.getData());
/*      */     }
/*      */   }
/*      */   
/*      */   public void setHandle(Entity entity) {
/*  757 */     this.entity = entity;
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/*  762 */     return "CraftEntity{id=" + getEntityId() + '}';
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  767 */     if (obj == null) {
/*  768 */       return false;
/*      */     }
/*  770 */     if (getClass() != obj.getClass()) {
/*  771 */       return false;
/*      */     }
/*  773 */     CraftEntity other = (CraftEntity)obj;
/*  774 */     return (getHandle() == other.getHandle());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  781 */     return getUniqueId().hashCode();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
/*  787 */     this.server.getEntityMetadata().setMetadata(this, metadataKey, newMetadataValue);
/*      */   }
/*      */ 
/*      */   
/*      */   public List<MetadataValue> getMetadata(String metadataKey) {
/*  792 */     return this.server.getEntityMetadata().getMetadata(this, metadataKey);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasMetadata(String metadataKey) {
/*  797 */     return this.server.getEntityMetadata().hasMetadata(this, metadataKey);
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeMetadata(String metadataKey, Plugin owningPlugin) {
/*  802 */     this.server.getEntityMetadata().removeMetadata(this, metadataKey, owningPlugin);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInsideVehicle() {
/*  807 */     return getHandle().isPassenger();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean leaveVehicle() {
/*  812 */     if (!isInsideVehicle()) {
/*  813 */       return false;
/*      */     }
/*      */     
/*  816 */     getHandle().stopRiding();
/*  817 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getVehicle() {
/*  822 */     if (!isInsideVehicle()) {
/*  823 */       return null;
/*      */     }
/*      */     
/*  826 */     return getHandle().getVehicle().getBukkitEntity();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCustomName(String name) {
/*  832 */     if (name != null && name.length() > 256) {
/*  833 */       name = name.substring(0, 256);
/*      */     }
/*      */     
/*  836 */     getHandle().setCustomName(CraftChatMessage.fromStringOrNull(name));
/*      */   }
/*      */ 
/*      */   
/*      */   public String getCustomName() {
/*  841 */     IChatBaseComponent name = getHandle().getCustomName();
/*      */     
/*  843 */     if (name == null) {
/*  844 */       return null;
/*      */     }
/*      */     
/*  847 */     return CraftChatMessage.fromComponent(name);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCustomNameVisible(boolean flag) {
/*  852 */     getHandle().setCustomNameVisible(flag);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isCustomNameVisible() {
/*  857 */     return getHandle().getCustomNameVisible();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendMessage(String message) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendMessage(String[] messages) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/*  872 */     return CraftChatMessage.fromComponent(getHandle().getDisplayName());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPermissionSet(String name) {
/*  877 */     return getPermissibleBase().isPermissionSet(name);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPermissionSet(Permission perm) {
/*  882 */     return getPermissibleBase().isPermissionSet(perm);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasPermission(String name) {
/*  887 */     return getPermissibleBase().hasPermission(name);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasPermission(Permission perm) {
/*  892 */     return getPermissibleBase().hasPermission(perm);
/*      */   }
/*      */ 
/*      */   
/*      */   public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
/*  897 */     return getPermissibleBase().addAttachment(plugin, name, value);
/*      */   }
/*      */ 
/*      */   
/*      */   public PermissionAttachment addAttachment(Plugin plugin) {
/*  902 */     return getPermissibleBase().addAttachment(plugin);
/*      */   }
/*      */ 
/*      */   
/*      */   public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
/*  907 */     return getPermissibleBase().addAttachment(plugin, name, value, ticks);
/*      */   }
/*      */ 
/*      */   
/*      */   public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
/*  912 */     return getPermissibleBase().addAttachment(plugin, ticks);
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeAttachment(PermissionAttachment attachment) {
/*  917 */     getPermissibleBase().removeAttachment(attachment);
/*      */   }
/*      */ 
/*      */   
/*      */   public void recalculatePermissions() {
/*  922 */     getPermissibleBase().recalculatePermissions();
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<PermissionAttachmentInfo> getEffectivePermissions() {
/*  927 */     return getPermissibleBase().getEffectivePermissions();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOp() {
/*  932 */     return getPermissibleBase().isOp();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOp(boolean value) {
/*  937 */     getPermissibleBase().setOp(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGlowing(boolean flag) {
/*  942 */     (getHandle()).glowing = flag;
/*  943 */     Entity e = getHandle();
/*  944 */     if (e.getFlag(6) != flag) {
/*  945 */       e.setFlag(6, flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isGlowing() {
/*  951 */     return (getHandle()).glowing;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInvulnerable(boolean flag) {
/*  956 */     getHandle().setInvulnerable(flag);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInvulnerable() {
/*  961 */     return getHandle().isInvulnerable(DamageSource.GENERIC);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSilent() {
/*  966 */     return getHandle().isSilent();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSilent(boolean flag) {
/*  971 */     getHandle().setSilent(flag);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasGravity() {
/*  976 */     return !getHandle().isNoGravity();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setGravity(boolean gravity) {
/*  981 */     getHandle().setNoGravity(!gravity);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPortalCooldown() {
/*  986 */     return (getHandle()).portalCooldown;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPortalCooldown(int cooldown) {
/*  991 */     (getHandle()).portalCooldown = cooldown;
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<String> getScoreboardTags() {
/*  996 */     return getHandle().getScoreboardTags();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addScoreboardTag(String tag) {
/* 1001 */     return getHandle().addScoreboardTag(tag);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeScoreboardTag(String tag) {
/* 1006 */     return getHandle().removeScoreboardTag(tag);
/*      */   }
/*      */ 
/*      */   
/*      */   public PistonMoveReaction getPistonMoveReaction() {
/* 1011 */     return PistonMoveReaction.getById(getHandle().getPushReaction().ordinal());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public BlockFace getFacing() {
/* 1017 */     return CraftBlock.notchToBlockFace(getHandle().getAdjustedDirection());
/*      */   }
/*      */ 
/*      */   
/*      */   public CraftPersistentDataContainer getPersistentDataContainer() {
/* 1022 */     return this.persistentDataContainer;
/*      */   }
/*      */ 
/*      */   
/*      */   public Pose getPose() {
/* 1027 */     return Pose.values()[getHandle().getPose().ordinal()];
/*      */   }
/*      */   
/*      */   public void storeBukkitValues(NBTTagCompound c) {
/* 1031 */     if (!this.persistentDataContainer.isEmpty()) {
/* 1032 */       c.set("BukkitValues", (NBTBase)this.persistentDataContainer.toTagCompound());
/*      */     }
/*      */   }
/*      */   
/*      */   public void readBukkitValues(NBTTagCompound c) {
/* 1037 */     NBTTagCompound base = c.getCompound("BukkitValues");
/* 1038 */     if (base != null) {
/* 1039 */       this.persistentDataContainer.putAll(base);
/*      */     }
/*      */   }
/*      */   
/*      */   protected NBTTagCompound save() {
/* 1044 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*      */     
/* 1046 */     nbttagcompound.setString("id", getHandle().getSaveID());
/* 1047 */     getHandle().save(nbttagcompound);
/*      */     
/* 1049 */     return nbttagcompound;
/*      */   }
/*      */   
/*      */   private static PermissibleBase getPermissibleBase() {
/* 1053 */     if (perm == null) {
/* 1054 */       perm = new PermissibleBase(new ServerOperator()
/*      */           {
/*      */             public boolean isOp()
/*      */             {
/* 1058 */               return false;
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             public void setOp(boolean value) {}
/*      */           });
/*      */     }
/* 1067 */     return perm;
/*      */   }
/*      */   
/*      */   public CraftEntity(CraftServer server, Entity entity) {
/* 1071 */     this.spigot = new Entity.Spigot()
/*      */       {
/*      */         public void sendMessage(BaseComponent component) {}
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void sendMessage(BaseComponent... components) {}
/*      */       };
/*      */     this.server = server;
/*      */     this.entity = entity;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity.Spigot spigot() {
/* 1087 */     return this.spigot;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Location getOrigin() {
/* 1094 */     Location origin = (getHandle()).origin;
/* 1095 */     return (origin == null) ? null : origin.clone();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean fromMobSpawner() {
/* 1100 */     return (getHandle()).spawnedViaMobSpawner;
/*      */   }
/*      */ 
/*      */   
/*      */   public CreatureSpawnEvent.SpawnReason getEntitySpawnReason() {
/* 1105 */     return (getHandle()).spawnReason;
/*      */   }
/*      */   
/*      */   public boolean isInWater() {
/* 1109 */     return getHandle().isInWater();
/*      */   }
/*      */   
/*      */   public boolean isInRain() {
/* 1113 */     return getHandle().isInRain();
/*      */   }
/*      */   
/*      */   public boolean isInBubbleColumn() {
/* 1117 */     return getHandle().isInBubbleColumn();
/*      */   }
/*      */   
/*      */   public boolean isInWaterOrRain() {
/* 1121 */     return getHandle().isInWaterOrRain();
/*      */   }
/*      */   
/*      */   public boolean isInWaterOrBubbleColumn() {
/* 1125 */     return getHandle().isInWaterOrBubbleColumn();
/*      */   }
/*      */   
/*      */   public boolean isInWaterOrRainOrBubbleColumn() {
/* 1129 */     return getHandle().isInWaterOrRainOrBubble();
/*      */   }
/*      */   
/*      */   public boolean isInLava() {
/* 1133 */     return getHandle().isInLava();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */