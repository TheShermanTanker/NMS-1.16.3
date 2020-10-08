/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.destroystokyo.paper.Namespaced;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.EnumColor;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityBanner;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityBarrel;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityBeacon;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityBeehive;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityBell;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityBlastFurnace;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityBrewingStand;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityCampfire;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityChest;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityCommand;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityComparator;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityDispenser;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityDropper;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityEnchantTable;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityEndGateway;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityEnderChest;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityFurnaceFurnace;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityHopper;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityJigsaw;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityJukeBox;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityLectern;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityLightDetector;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityMobSpawner;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityShulkerBox;
/*     */ import net.minecraft.server.v1_16_R2.TileEntitySign;
/*     */ import net.minecraft.server.v1_16_R2.TileEntitySkull;
/*     */ import net.minecraft.server.v1_16_R2.TileEntitySmoker;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityStructure;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBanner;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBarrel;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBeacon;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBeehive;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBell;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlastFurnace;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockEntityState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftChest;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftComparator;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftDropper;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftEnderChest;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftFurnaceFurnace;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftHopper;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftLectern;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftSkull;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.meta.BlockStateMeta;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
/*     */ import org.bukkit.persistence.PersistentDataContainer;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ public class CraftMetaBlockState extends CraftMetaItem implements BlockStateMeta {
/*  78 */   static final CraftMetaItem.ItemMetaKey BLOCK_ENTITY_TAG = new CraftMetaItem.ItemMetaKey("BlockEntityTag");
/*     */   
/*     */   final Material material;
/*     */   NBTTagCompound blockEntityTag;
/*     */   
/*     */   CraftMetaBlockState(CraftMetaItem meta, Material material) {
/*  84 */     super(meta);
/*  85 */     this.material = material;
/*     */     
/*  87 */     if (!(meta instanceof CraftMetaBlockState) || ((CraftMetaBlockState)meta).material != material) {
/*     */       
/*  89 */       this.blockEntityTag = null;
/*     */       
/*     */       return;
/*     */     } 
/*  93 */     CraftMetaBlockState te = (CraftMetaBlockState)meta;
/*  94 */     this.blockEntityTag = te.blockEntityTag;
/*     */   }
/*     */   
/*     */   CraftMetaBlockState(NBTTagCompound tag, Material material) {
/*  98 */     super(tag);
/*  99 */     this.material = material;
/*     */     
/* 101 */     if (tag.hasKeyOfType(BLOCK_ENTITY_TAG.NBT, 10)) {
/* 102 */       this.blockEntityTag = tag.getCompound(BLOCK_ENTITY_TAG.NBT);
/*     */     } else {
/* 104 */       this.blockEntityTag = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   CraftMetaBlockState(Map<String, Object> map) {
/* 109 */     super(map);
/* 110 */     String matName = CraftMetaItem.SerializableMeta.getString(map, "blockMaterial", true);
/* 111 */     Material m = Material.getMaterial(matName);
/* 112 */     if (m != null) {
/* 113 */       this.material = m;
/*     */     } else {
/* 115 */       this.material = Material.AIR;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/* 121 */     super.applyToItem(tag);
/*     */     
/* 123 */     if (this.blockEntityTag != null) {
/* 124 */       tag.set(BLOCK_ENTITY_TAG.NBT, (NBTBase)this.blockEntityTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void deserializeInternal(NBTTagCompound tag, Object context) {
/* 130 */     super.deserializeInternal(tag, context);
/*     */     
/* 132 */     if (tag.hasKeyOfType(BLOCK_ENTITY_TAG.NBT, 10)) {
/* 133 */       this.blockEntityTag = tag.getCompound(BLOCK_ENTITY_TAG.NBT);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void serializeInternal(Map<String, NBTBase> internalTags) {
/* 139 */     if (this.blockEntityTag != null) {
/* 140 */       internalTags.put(BLOCK_ENTITY_TAG.NBT, this.blockEntityTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 146 */     super.serialize(builder);
/* 147 */     builder.put("blockMaterial", this.material.name());
/* 148 */     return builder;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 154 */     int original = super.applyHash(), hash = original;
/* 155 */     if (this.blockEntityTag != null) {
/* 156 */       hash = 61 * hash + this.blockEntityTag.hashCode();
/*     */     }
/* 158 */     return (original != hash) ? (CraftMetaBlockState.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equalsCommon(CraftMetaItem meta) {
/* 163 */     if (!super.equalsCommon(meta)) {
/* 164 */       return false;
/*     */     }
/* 166 */     if (meta instanceof CraftMetaBlockState) {
/* 167 */       CraftMetaBlockState that = (CraftMetaBlockState)meta;
/*     */       
/* 169 */       return Objects.equal(this.blockEntityTag, that.blockEntityTag);
/*     */     } 
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 176 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaBlockState || this.blockEntityTag == null));
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 181 */     return (super.isEmpty() && this.blockEntityTag == null);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 186 */     switch (type) {
/*     */       case FURNACE:
/*     */       case CHEST:
/*     */       case TRAPPED_CHEST:
/*     */       case JUKEBOX:
/*     */       case DISPENSER:
/*     */       case DROPPER:
/*     */       case ACACIA_SIGN:
/*     */       case ACACIA_WALL_SIGN:
/*     */       case BIRCH_SIGN:
/*     */       case BIRCH_WALL_SIGN:
/*     */       case CRIMSON_SIGN:
/*     */       case CRIMSON_WALL_SIGN:
/*     */       case DARK_OAK_SIGN:
/*     */       case DARK_OAK_WALL_SIGN:
/*     */       case JUNGLE_SIGN:
/*     */       case JUNGLE_WALL_SIGN:
/*     */       case OAK_SIGN:
/*     */       case OAK_WALL_SIGN:
/*     */       case SPRUCE_SIGN:
/*     */       case SPRUCE_WALL_SIGN:
/*     */       case WARPED_SIGN:
/*     */       case WARPED_WALL_SIGN:
/*     */       case SPAWNER:
/*     */       case BREWING_STAND:
/*     */       case ENCHANTING_TABLE:
/*     */       case COMMAND_BLOCK:
/*     */       case REPEATING_COMMAND_BLOCK:
/*     */       case CHAIN_COMMAND_BLOCK:
/*     */       case BEACON:
/*     */       case DAYLIGHT_DETECTOR:
/*     */       case HOPPER:
/*     */       case COMPARATOR:
/*     */       case SHIELD:
/*     */       case STRUCTURE_BLOCK:
/*     */       case SHULKER_BOX:
/*     */       case WHITE_SHULKER_BOX:
/*     */       case ORANGE_SHULKER_BOX:
/*     */       case MAGENTA_SHULKER_BOX:
/*     */       case LIGHT_BLUE_SHULKER_BOX:
/*     */       case YELLOW_SHULKER_BOX:
/*     */       case LIME_SHULKER_BOX:
/*     */       case PINK_SHULKER_BOX:
/*     */       case GRAY_SHULKER_BOX:
/*     */       case LIGHT_GRAY_SHULKER_BOX:
/*     */       case CYAN_SHULKER_BOX:
/*     */       case PURPLE_SHULKER_BOX:
/*     */       case BLUE_SHULKER_BOX:
/*     */       case BROWN_SHULKER_BOX:
/*     */       case GREEN_SHULKER_BOX:
/*     */       case RED_SHULKER_BOX:
/*     */       case BLACK_SHULKER_BOX:
/*     */       case ENDER_CHEST:
/*     */       case BARREL:
/*     */       case BELL:
/*     */       case BLAST_FURNACE:
/*     */       case CAMPFIRE:
/*     */       case SOUL_CAMPFIRE:
/*     */       case JIGSAW:
/*     */       case LECTERN:
/*     */       case SMOKER:
/*     */       case BEEHIVE:
/*     */       case BEE_NEST:
/* 249 */         return true;
/*     */     } 
/* 251 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaBlockState clone() {
/* 256 */     CraftMetaBlockState meta = (CraftMetaBlockState)super.clone();
/* 257 */     if (this.blockEntityTag != null) {
/* 258 */       meta.blockEntityTag = this.blockEntityTag.clone();
/*     */     }
/* 260 */     return meta;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBlockState() {
/* 265 */     return (this.blockEntityTag != null); } public BlockState getBlockState() { TileEntitySign tileEntitySign; TileEntityChest tileEntityChest; TileEntityFurnaceFurnace tileEntityFurnaceFurnace; TileEntityDispenser tileEntityDispenser; TileEntityDropper tileEntityDropper; TileEntityEndGateway tileEntityEndGateway; TileEntityHopper tileEntityHopper; TileEntityMobSpawner tileEntityMobSpawner; TileEntityJukeBox tileEntityJukeBox; TileEntityBrewingStand tileEntityBrewingStand; TileEntitySkull tileEntitySkull; TileEntityCommand tileEntityCommand; TileEntityBeacon tileEntityBeacon; TileEntityBanner tileEntityBanner; TileEntityStructure tileEntityStructure; TileEntityShulkerBox tileEntityShulkerBox; TileEntityEnchantTable tileEntityEnchantTable; TileEntityEnderChest tileEntityEnderChest; TileEntityLightDetector tileEntityLightDetector; TileEntityComparator tileEntityComparator; TileEntityBarrel tileEntityBarrel; TileEntityBell tileEntityBell; TileEntityBlastFurnace tileEntityBlastFurnace; TileEntityCampfire tileEntityCampfire;
/*     */     TileEntityJigsaw tileEntityJigsaw;
/*     */     TileEntityLectern tileEntityLectern;
/*     */     TileEntitySmoker tileEntitySmoker;
/*     */     TileEntityBeehive tileEntityBeehive;
/* 270 */     Material stateMaterial = this.material;
/* 271 */     if (this.blockEntityTag != null) {
/* 272 */       switch (this.material) {
/*     */         case SHIELD:
/* 274 */           this.blockEntityTag.setString("id", "banner");
/* 275 */           stateMaterial = shieldToBannerHack(this.blockEntityTag);
/*     */           break;
/*     */         case SHULKER_BOX:
/*     */         case WHITE_SHULKER_BOX:
/*     */         case ORANGE_SHULKER_BOX:
/*     */         case MAGENTA_SHULKER_BOX:
/*     */         case LIGHT_BLUE_SHULKER_BOX:
/*     */         case YELLOW_SHULKER_BOX:
/*     */         case LIME_SHULKER_BOX:
/*     */         case PINK_SHULKER_BOX:
/*     */         case GRAY_SHULKER_BOX:
/*     */         case LIGHT_GRAY_SHULKER_BOX:
/*     */         case CYAN_SHULKER_BOX:
/*     */         case PURPLE_SHULKER_BOX:
/*     */         case BLUE_SHULKER_BOX:
/*     */         case BROWN_SHULKER_BOX:
/*     */         case GREEN_SHULKER_BOX:
/*     */         case RED_SHULKER_BOX:
/*     */         case BLACK_SHULKER_BOX:
/* 294 */           this.blockEntityTag.setString("id", "shulker_box");
/*     */           break;
/*     */         case BEEHIVE:
/*     */         case BEE_NEST:
/* 298 */           this.blockEntityTag.setString("id", "beehive");
/*     */           break;
/*     */       } 
/*     */     }
/* 302 */     TileEntity te = (this.blockEntityTag == null) ? null : TileEntity.create(CraftMagicNumbers.getBlock(stateMaterial).getBlockData(), this.blockEntityTag);
/*     */     
/* 304 */     switch (this.material) {
/*     */       case ACACIA_SIGN:
/*     */       case ACACIA_WALL_SIGN:
/*     */       case BIRCH_SIGN:
/*     */       case BIRCH_WALL_SIGN:
/*     */       case CRIMSON_SIGN:
/*     */       case CRIMSON_WALL_SIGN:
/*     */       case DARK_OAK_SIGN:
/*     */       case DARK_OAK_WALL_SIGN:
/*     */       case JUNGLE_SIGN:
/*     */       case JUNGLE_WALL_SIGN:
/*     */       case OAK_SIGN:
/*     */       case OAK_WALL_SIGN:
/*     */       case SPRUCE_SIGN:
/*     */       case SPRUCE_WALL_SIGN:
/*     */       case WARPED_SIGN:
/*     */       case WARPED_WALL_SIGN:
/* 321 */         if (te == null) {
/* 322 */           tileEntitySign = new TileEntitySign();
/*     */         }
/* 324 */         return (BlockState)new CraftSign(this.material, tileEntitySign);
/*     */       case CHEST:
/*     */       case TRAPPED_CHEST:
/* 327 */         if (tileEntitySign == null) {
/* 328 */           tileEntityChest = new TileEntityChest();
/*     */         }
/* 330 */         return (BlockState)new CraftChest(this.material, tileEntityChest);
/*     */       case FURNACE:
/* 332 */         if (tileEntityChest == null) {
/* 333 */           tileEntityFurnaceFurnace = new TileEntityFurnaceFurnace();
/*     */         }
/* 335 */         return (BlockState)new CraftFurnaceFurnace(this.material, tileEntityFurnaceFurnace);
/*     */       case DISPENSER:
/* 337 */         if (tileEntityFurnaceFurnace == null) {
/* 338 */           tileEntityDispenser = new TileEntityDispenser();
/*     */         }
/* 340 */         return (BlockState)new CraftDispenser(this.material, tileEntityDispenser);
/*     */       case DROPPER:
/* 342 */         if (tileEntityDispenser == null) {
/* 343 */           tileEntityDropper = new TileEntityDropper();
/*     */         }
/* 345 */         return (BlockState)new CraftDropper(this.material, tileEntityDropper);
/*     */       case END_GATEWAY:
/* 347 */         if (tileEntityDropper == null) {
/* 348 */           tileEntityEndGateway = new TileEntityEndGateway();
/*     */         }
/* 350 */         return (BlockState)new CraftEndGateway(this.material, tileEntityEndGateway);
/*     */       case HOPPER:
/* 352 */         if (tileEntityEndGateway == null) {
/* 353 */           tileEntityHopper = new TileEntityHopper();
/*     */         }
/* 355 */         return (BlockState)new CraftHopper(this.material, tileEntityHopper);
/*     */       case SPAWNER:
/* 357 */         if (tileEntityHopper == null) {
/* 358 */           tileEntityMobSpawner = new TileEntityMobSpawner();
/*     */         }
/* 360 */         return (BlockState)new CraftCreatureSpawner(this.material, tileEntityMobSpawner);
/*     */       case JUKEBOX:
/* 362 */         if (tileEntityMobSpawner == null) {
/* 363 */           tileEntityJukeBox = new TileEntityJukeBox();
/*     */         }
/* 365 */         return (BlockState)new CraftJukebox(this.material, tileEntityJukeBox);
/*     */       case BREWING_STAND:
/* 367 */         if (tileEntityJukeBox == null) {
/* 368 */           tileEntityBrewingStand = new TileEntityBrewingStand();
/*     */         }
/* 370 */         return (BlockState)new CraftBrewingStand(this.material, tileEntityBrewingStand);
/*     */       case CREEPER_HEAD:
/*     */       case CREEPER_WALL_HEAD:
/*     */       case DRAGON_HEAD:
/*     */       case DRAGON_WALL_HEAD:
/*     */       case PLAYER_HEAD:
/*     */       case PLAYER_WALL_HEAD:
/*     */       case SKELETON_SKULL:
/*     */       case SKELETON_WALL_SKULL:
/*     */       case WITHER_SKELETON_SKULL:
/*     */       case WITHER_SKELETON_WALL_SKULL:
/*     */       case ZOMBIE_HEAD:
/*     */       case ZOMBIE_WALL_HEAD:
/* 383 */         if (tileEntityBrewingStand == null) {
/* 384 */           tileEntitySkull = new TileEntitySkull();
/*     */         }
/* 386 */         return (BlockState)new CraftSkull(this.material, tileEntitySkull);
/*     */       case COMMAND_BLOCK:
/*     */       case REPEATING_COMMAND_BLOCK:
/*     */       case CHAIN_COMMAND_BLOCK:
/* 390 */         if (tileEntitySkull == null) {
/* 391 */           tileEntityCommand = new TileEntityCommand();
/*     */         }
/* 393 */         return (BlockState)new CraftCommandBlock(this.material, tileEntityCommand);
/*     */       case BEACON:
/* 395 */         if (tileEntityCommand == null) {
/* 396 */           tileEntityBeacon = new TileEntityBeacon();
/*     */         }
/* 398 */         return (BlockState)new CraftBeacon(this.material, tileEntityBeacon);
/*     */       case SHIELD:
/* 400 */         if (tileEntityBeacon == null) {
/* 401 */           tileEntityBanner = new TileEntityBanner();
/*     */         }
/* 403 */         tileEntityBanner.color = (this.blockEntityTag == null) ? EnumColor.WHITE : EnumColor.fromColorIndex(this.blockEntityTag.getInt(CraftMetaBanner.BASE.NBT));
/*     */       case BLACK_BANNER:
/*     */       case BLACK_WALL_BANNER:
/*     */       case BLUE_BANNER:
/*     */       case BLUE_WALL_BANNER:
/*     */       case BROWN_BANNER:
/*     */       case BROWN_WALL_BANNER:
/*     */       case CYAN_BANNER:
/*     */       case CYAN_WALL_BANNER:
/*     */       case GRAY_BANNER:
/*     */       case GRAY_WALL_BANNER:
/*     */       case GREEN_BANNER:
/*     */       case GREEN_WALL_BANNER:
/*     */       case LIGHT_BLUE_BANNER:
/*     */       case LIGHT_BLUE_WALL_BANNER:
/*     */       case LIGHT_GRAY_BANNER:
/*     */       case LIGHT_GRAY_WALL_BANNER:
/*     */       case LIME_BANNER:
/*     */       case LIME_WALL_BANNER:
/*     */       case MAGENTA_BANNER:
/*     */       case MAGENTA_WALL_BANNER:
/*     */       case ORANGE_BANNER:
/*     */       case ORANGE_WALL_BANNER:
/*     */       case PINK_BANNER:
/*     */       case PINK_WALL_BANNER:
/*     */       case PURPLE_BANNER:
/*     */       case PURPLE_WALL_BANNER:
/*     */       case RED_BANNER:
/*     */       case RED_WALL_BANNER:
/*     */       case WHITE_BANNER:
/*     */       case WHITE_WALL_BANNER:
/*     */       case YELLOW_BANNER:
/*     */       case YELLOW_WALL_BANNER:
/* 436 */         if (tileEntityBanner == null) {
/* 437 */           tileEntityBanner = new TileEntityBanner();
/*     */         }
/* 439 */         return (BlockState)new CraftBanner((this.material == Material.SHIELD) ? shieldToBannerHack(this.blockEntityTag) : this.material, tileEntityBanner);
/*     */       case STRUCTURE_BLOCK:
/* 441 */         if (tileEntityBanner == null) {
/* 442 */           tileEntityStructure = new TileEntityStructure();
/*     */         }
/* 444 */         return (BlockState)new CraftStructureBlock(this.material, tileEntityStructure);
/*     */       case SHULKER_BOX:
/*     */       case WHITE_SHULKER_BOX:
/*     */       case ORANGE_SHULKER_BOX:
/*     */       case MAGENTA_SHULKER_BOX:
/*     */       case LIGHT_BLUE_SHULKER_BOX:
/*     */       case YELLOW_SHULKER_BOX:
/*     */       case LIME_SHULKER_BOX:
/*     */       case PINK_SHULKER_BOX:
/*     */       case GRAY_SHULKER_BOX:
/*     */       case LIGHT_GRAY_SHULKER_BOX:
/*     */       case CYAN_SHULKER_BOX:
/*     */       case PURPLE_SHULKER_BOX:
/*     */       case BLUE_SHULKER_BOX:
/*     */       case BROWN_SHULKER_BOX:
/*     */       case GREEN_SHULKER_BOX:
/*     */       case RED_SHULKER_BOX:
/*     */       case BLACK_SHULKER_BOX:
/* 462 */         if (tileEntityStructure == null) {
/* 463 */           tileEntityShulkerBox = new TileEntityShulkerBox();
/*     */         }
/* 465 */         return (BlockState)new CraftShulkerBox(this.material, tileEntityShulkerBox);
/*     */       case ENCHANTING_TABLE:
/* 467 */         if (tileEntityShulkerBox == null) {
/* 468 */           tileEntityEnchantTable = new TileEntityEnchantTable();
/*     */         }
/* 470 */         return (BlockState)new CraftEnchantingTable(this.material, tileEntityEnchantTable);
/*     */       case ENDER_CHEST:
/* 472 */         if (tileEntityEnchantTable == null) {
/* 473 */           tileEntityEnderChest = new TileEntityEnderChest();
/*     */         }
/* 475 */         return (BlockState)new CraftEnderChest(this.material, tileEntityEnderChest);
/*     */       case DAYLIGHT_DETECTOR:
/* 477 */         if (tileEntityEnderChest == null) {
/* 478 */           tileEntityLightDetector = new TileEntityLightDetector();
/*     */         }
/* 480 */         return (BlockState)new CraftDaylightDetector(this.material, tileEntityLightDetector);
/*     */       case COMPARATOR:
/* 482 */         if (tileEntityLightDetector == null) {
/* 483 */           tileEntityComparator = new TileEntityComparator();
/*     */         }
/* 485 */         return (BlockState)new CraftComparator(this.material, tileEntityComparator);
/*     */       case BARREL:
/* 487 */         if (tileEntityComparator == null) {
/* 488 */           tileEntityBarrel = new TileEntityBarrel();
/*     */         }
/* 490 */         return (BlockState)new CraftBarrel(this.material, tileEntityBarrel);
/*     */       case BELL:
/* 492 */         if (tileEntityBarrel == null) {
/* 493 */           tileEntityBell = new TileEntityBell();
/*     */         }
/* 495 */         return (BlockState)new CraftBell(this.material, tileEntityBell);
/*     */       case BLAST_FURNACE:
/* 497 */         if (tileEntityBell == null) {
/* 498 */           tileEntityBlastFurnace = new TileEntityBlastFurnace();
/*     */         }
/* 500 */         return (BlockState)new CraftBlastFurnace(this.material, tileEntityBlastFurnace);
/*     */       case CAMPFIRE:
/*     */       case SOUL_CAMPFIRE:
/* 503 */         if (tileEntityBlastFurnace == null) {
/* 504 */           tileEntityCampfire = new TileEntityCampfire();
/*     */         }
/* 506 */         return (BlockState)new CraftCampfire(this.material, tileEntityCampfire);
/*     */       case JIGSAW:
/* 508 */         if (tileEntityCampfire == null) {
/* 509 */           tileEntityJigsaw = new TileEntityJigsaw();
/*     */         }
/* 511 */         return (BlockState)new CraftJigsaw(this.material, tileEntityJigsaw);
/*     */       case LECTERN:
/* 513 */         if (tileEntityJigsaw == null) {
/* 514 */           tileEntityLectern = new TileEntityLectern();
/*     */         }
/* 516 */         return (BlockState)new CraftLectern(this.material, tileEntityLectern);
/*     */       case SMOKER:
/* 518 */         if (tileEntityLectern == null) {
/* 519 */           tileEntitySmoker = new TileEntitySmoker();
/*     */         }
/* 521 */         return (BlockState)new CraftSmoker(this.material, tileEntitySmoker);
/*     */       case BEEHIVE:
/*     */       case BEE_NEST:
/* 524 */         if (tileEntitySmoker == null) {
/* 525 */           tileEntityBeehive = new TileEntityBeehive();
/*     */         }
/* 527 */         return (BlockState)new CraftBeehive(this.material, tileEntityBeehive);
/*     */     } 
/* 529 */     throw new IllegalStateException("Missing blockState for " + this.material); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockState(BlockState blockState) {
/*     */     boolean valid;
/* 535 */     Validate.notNull(blockState, "blockState must not be null");
/*     */ 
/*     */     
/* 538 */     switch (this.material) {
/*     */       case ACACIA_SIGN:
/*     */       case ACACIA_WALL_SIGN:
/*     */       case BIRCH_SIGN:
/*     */       case BIRCH_WALL_SIGN:
/*     */       case CRIMSON_SIGN:
/*     */       case CRIMSON_WALL_SIGN:
/*     */       case DARK_OAK_SIGN:
/*     */       case DARK_OAK_WALL_SIGN:
/*     */       case JUNGLE_SIGN:
/*     */       case JUNGLE_WALL_SIGN:
/*     */       case OAK_SIGN:
/*     */       case OAK_WALL_SIGN:
/*     */       case SPRUCE_SIGN:
/*     */       case SPRUCE_WALL_SIGN:
/*     */       case WARPED_SIGN:
/*     */       case WARPED_WALL_SIGN:
/* 555 */         valid = blockState instanceof CraftSign;
/*     */         break;
/*     */       case CHEST:
/*     */       case TRAPPED_CHEST:
/* 559 */         valid = blockState instanceof CraftChest;
/*     */         break;
/*     */       case FURNACE:
/* 562 */         valid = blockState instanceof CraftFurnaceFurnace;
/*     */         break;
/*     */       case DISPENSER:
/* 565 */         valid = blockState instanceof CraftDispenser;
/*     */         break;
/*     */       case DROPPER:
/* 568 */         valid = blockState instanceof CraftDropper;
/*     */         break;
/*     */       case END_GATEWAY:
/* 571 */         valid = blockState instanceof CraftEndGateway;
/*     */         break;
/*     */       case HOPPER:
/* 574 */         valid = blockState instanceof CraftHopper;
/*     */         break;
/*     */       case SPAWNER:
/* 577 */         valid = blockState instanceof CraftCreatureSpawner;
/*     */         break;
/*     */       case JUKEBOX:
/* 580 */         valid = blockState instanceof CraftJukebox;
/*     */         break;
/*     */       case BREWING_STAND:
/* 583 */         valid = blockState instanceof CraftBrewingStand;
/*     */         break;
/*     */       case CREEPER_HEAD:
/*     */       case CREEPER_WALL_HEAD:
/*     */       case DRAGON_HEAD:
/*     */       case DRAGON_WALL_HEAD:
/*     */       case PLAYER_HEAD:
/*     */       case PLAYER_WALL_HEAD:
/*     */       case SKELETON_SKULL:
/*     */       case SKELETON_WALL_SKULL:
/*     */       case WITHER_SKELETON_SKULL:
/*     */       case WITHER_SKELETON_WALL_SKULL:
/*     */       case ZOMBIE_HEAD:
/*     */       case ZOMBIE_WALL_HEAD:
/* 597 */         valid = blockState instanceof CraftSkull;
/*     */         break;
/*     */       case COMMAND_BLOCK:
/*     */       case REPEATING_COMMAND_BLOCK:
/*     */       case CHAIN_COMMAND_BLOCK:
/* 602 */         valid = blockState instanceof CraftCommandBlock;
/*     */         break;
/*     */       case BEACON:
/* 605 */         valid = blockState instanceof CraftBeacon;
/*     */         break;
/*     */       case SHIELD:
/*     */       case BLACK_BANNER:
/*     */       case BLACK_WALL_BANNER:
/*     */       case BLUE_BANNER:
/*     */       case BLUE_WALL_BANNER:
/*     */       case BROWN_BANNER:
/*     */       case BROWN_WALL_BANNER:
/*     */       case CYAN_BANNER:
/*     */       case CYAN_WALL_BANNER:
/*     */       case GRAY_BANNER:
/*     */       case GRAY_WALL_BANNER:
/*     */       case GREEN_BANNER:
/*     */       case GREEN_WALL_BANNER:
/*     */       case LIGHT_BLUE_BANNER:
/*     */       case LIGHT_BLUE_WALL_BANNER:
/*     */       case LIGHT_GRAY_BANNER:
/*     */       case LIGHT_GRAY_WALL_BANNER:
/*     */       case LIME_BANNER:
/*     */       case LIME_WALL_BANNER:
/*     */       case MAGENTA_BANNER:
/*     */       case MAGENTA_WALL_BANNER:
/*     */       case ORANGE_BANNER:
/*     */       case ORANGE_WALL_BANNER:
/*     */       case PINK_BANNER:
/*     */       case PINK_WALL_BANNER:
/*     */       case PURPLE_BANNER:
/*     */       case PURPLE_WALL_BANNER:
/*     */       case RED_BANNER:
/*     */       case RED_WALL_BANNER:
/*     */       case WHITE_BANNER:
/*     */       case WHITE_WALL_BANNER:
/*     */       case YELLOW_BANNER:
/*     */       case YELLOW_WALL_BANNER:
/* 640 */         valid = blockState instanceof CraftBanner;
/*     */         break;
/*     */       case STRUCTURE_BLOCK:
/* 643 */         valid = blockState instanceof CraftStructureBlock;
/*     */         break;
/*     */       case SHULKER_BOX:
/*     */       case WHITE_SHULKER_BOX:
/*     */       case ORANGE_SHULKER_BOX:
/*     */       case MAGENTA_SHULKER_BOX:
/*     */       case LIGHT_BLUE_SHULKER_BOX:
/*     */       case YELLOW_SHULKER_BOX:
/*     */       case LIME_SHULKER_BOX:
/*     */       case PINK_SHULKER_BOX:
/*     */       case GRAY_SHULKER_BOX:
/*     */       case LIGHT_GRAY_SHULKER_BOX:
/*     */       case CYAN_SHULKER_BOX:
/*     */       case PURPLE_SHULKER_BOX:
/*     */       case BLUE_SHULKER_BOX:
/*     */       case BROWN_SHULKER_BOX:
/*     */       case GREEN_SHULKER_BOX:
/*     */       case RED_SHULKER_BOX:
/*     */       case BLACK_SHULKER_BOX:
/* 662 */         valid = blockState instanceof CraftShulkerBox;
/*     */         break;
/*     */       case ENCHANTING_TABLE:
/* 665 */         valid = blockState instanceof CraftEnchantingTable;
/*     */         break;
/*     */       case ENDER_CHEST:
/* 668 */         valid = blockState instanceof CraftEnderChest;
/*     */         break;
/*     */       case DAYLIGHT_DETECTOR:
/* 671 */         valid = blockState instanceof CraftDaylightDetector;
/*     */         break;
/*     */       case COMPARATOR:
/* 674 */         valid = blockState instanceof CraftComparator;
/*     */         break;
/*     */       case BARREL:
/* 677 */         valid = blockState instanceof CraftBarrel;
/*     */         break;
/*     */       case BELL:
/* 680 */         valid = blockState instanceof CraftBell;
/*     */         break;
/*     */       case BLAST_FURNACE:
/* 683 */         valid = blockState instanceof CraftBlastFurnace;
/*     */         break;
/*     */       case CAMPFIRE:
/*     */       case SOUL_CAMPFIRE:
/* 687 */         valid = blockState instanceof CraftCampfire;
/*     */         break;
/*     */       case JIGSAW:
/* 690 */         valid = blockState instanceof CraftJigsaw;
/*     */         break;
/*     */       case LECTERN:
/* 693 */         valid = blockState instanceof CraftLectern;
/*     */         break;
/*     */       case SMOKER:
/* 696 */         valid = blockState instanceof CraftSmoker;
/*     */         break;
/*     */       case BEEHIVE:
/*     */       case BEE_NEST:
/* 700 */         valid = blockState instanceof CraftBeehive;
/*     */         break;
/*     */       default:
/* 703 */         valid = false;
/*     */         break;
/*     */     } 
/*     */     
/* 707 */     Validate.isTrue(valid, "Invalid blockState for " + this.material);
/*     */     
/* 709 */     this.blockEntityTag = ((CraftBlockEntityState)blockState).getSnapshotNBT();
/*     */     
/* 711 */     if (this.material == Material.SHIELD) {
/* 712 */       this.blockEntityTag.setInt(CraftMetaBanner.BASE.NBT, ((CraftBanner)blockState).getBaseColor().getWoolData());
/*     */     }
/*     */   }
/*     */   
/*     */   private static Material shieldToBannerHack(NBTTagCompound tag) {
/* 717 */     if (tag == null || !tag.hasKeyOfType(CraftMetaBanner.BASE.NBT, 3)) {
/* 718 */       return Material.WHITE_BANNER;
/*     */     }
/*     */     
/* 721 */     switch (tag.getInt(CraftMetaBanner.BASE.NBT)) {
/*     */       case 0:
/* 723 */         return Material.WHITE_BANNER;
/*     */       case 1:
/* 725 */         return Material.ORANGE_BANNER;
/*     */       case 2:
/* 727 */         return Material.MAGENTA_BANNER;
/*     */       case 3:
/* 729 */         return Material.LIGHT_BLUE_BANNER;
/*     */       case 4:
/* 731 */         return Material.YELLOW_BANNER;
/*     */       case 5:
/* 733 */         return Material.LIME_BANNER;
/*     */       case 6:
/* 735 */         return Material.PINK_BANNER;
/*     */       case 7:
/* 737 */         return Material.GRAY_BANNER;
/*     */       case 8:
/* 739 */         return Material.LIGHT_GRAY_BANNER;
/*     */       case 9:
/* 741 */         return Material.CYAN_BANNER;
/*     */       case 10:
/* 743 */         return Material.PURPLE_BANNER;
/*     */       case 11:
/* 745 */         return Material.BLUE_BANNER;
/*     */       case 12:
/* 747 */         return Material.BROWN_BANNER;
/*     */       case 13:
/* 749 */         return Material.GREEN_BANNER;
/*     */       case 14:
/* 751 */         return Material.RED_BANNER;
/*     */       case 15:
/* 753 */         return Material.BLACK_BANNER;
/*     */     } 
/* 755 */     throw new IllegalArgumentException("Unknown banner colour");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */