/*     */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*     */ import com.destroystokyo.paper.block.BlockSoundGroup;
/*     */ import com.destroystokyo.paper.block.CraftBlockSoundGroup;
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.server.v1_16_R2.AxisAlignedBB;
/*     */ import net.minecraft.server.v1_16_R2.BiomeBase;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.BlockRedstoneWire;
/*     */ import net.minecraft.server.v1_16_R2.Blocks;
/*     */ import net.minecraft.server.v1_16_R2.EnumDirection;
/*     */ import net.minecraft.server.v1_16_R2.EnumHand;
/*     */ import net.minecraft.server.v1_16_R2.EnumInteractionResult;
/*     */ import net.minecraft.server.v1_16_R2.EnumSkyBlock;
/*     */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*     */ import net.minecraft.server.v1_16_R2.IBlockAccess;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import net.minecraft.server.v1_16_R2.ItemActionContext;
/*     */ import net.minecraft.server.v1_16_R2.ItemBoneMeal;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.Items;
/*     */ import net.minecraft.server.v1_16_R2.MovingObjectPosition;
/*     */ import net.minecraft.server.v1_16_R2.MovingObjectPositionBlock;
/*     */ import net.minecraft.server.v1_16_R2.RayTrace;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import net.minecraft.server.v1_16_R2.Vec3D;
/*     */ import net.minecraft.server.v1_16_R2.VoxelShape;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.FluidCollisionMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.PistonMoveReaction;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.util.BlockVector;
/*     */ import org.bukkit.util.BoundingBox;
/*     */ import org.bukkit.util.RayTraceResult;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class CraftBlock implements Block {
/*     */   private final GeneratorAccess world;
/*     */   
/*     */   public CraftBlock(GeneratorAccess world, BlockPosition position) {
/*  66 */     this.world = world;
/*  67 */     this.position = position.immutableCopy();
/*     */   }
/*     */   private final BlockPosition position;
/*     */   public static CraftBlock at(GeneratorAccess world, BlockPosition position) {
/*  71 */     return new CraftBlock(world, position);
/*     */   }
/*     */   
/*     */   private Block getNMSBlock() {
/*  75 */     return getNMS().getBlock();
/*     */   }
/*     */   
/*     */   public IBlockData getNMS() {
/*  79 */     return this.world.getType(this.position);
/*     */   }
/*     */   
/*     */   public BlockPosition getPosition() {
/*  83 */     return this.position;
/*     */   }
/*     */ 
/*     */   
/*     */   public World getWorld() {
/*  88 */     return (World)this.world.getMinecraftWorld().getWorld();
/*     */   }
/*     */   
/*     */   public CraftWorld getCraftWorld() {
/*  92 */     return (CraftWorld)getWorld();
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  97 */     return new Location(getWorld(), this.position.getX(), this.position.getY(), this.position.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation(Location loc) {
/* 102 */     if (loc != null) {
/* 103 */       loc.setWorld(getWorld());
/* 104 */       loc.setX(this.position.getX());
/* 105 */       loc.setY(this.position.getY());
/* 106 */       loc.setZ(this.position.getZ());
/* 107 */       loc.setYaw(0.0F);
/* 108 */       loc.setPitch(0.0F);
/*     */     } 
/*     */     
/* 111 */     return loc;
/*     */   }
/*     */   
/*     */   public BlockVector getVector() {
/* 115 */     return new BlockVector(getX(), getY(), getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/* 120 */     return this.position.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/* 125 */     return this.position.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getZ() {
/* 130 */     return this.position.getZ();
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getChunk() {
/* 135 */     return getWorld().getChunkAt(this);
/*     */   }
/*     */   
/*     */   public void setData(byte data) {
/* 139 */     setData(data, 3);
/*     */   }
/*     */   
/*     */   public void setData(byte data, boolean applyPhysics) {
/* 143 */     if (applyPhysics) {
/* 144 */       setData(data, 3);
/*     */     } else {
/* 146 */       setData(data, 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setData(byte data, int flag) {
/* 151 */     this.world.setTypeAndData(this.position, CraftMagicNumbers.getBlock(getType(), data), flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getData() {
/* 156 */     IBlockData blockData = this.world.getType(this.position);
/* 157 */     return CraftMagicNumbers.toLegacyData(blockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockData getBlockData() {
/* 162 */     return (BlockData)CraftBlockData.fromData(getNMS());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(Material type) {
/* 167 */     setType(type, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(Material type, boolean applyPhysics) {
/* 172 */     Preconditions.checkArgument((type != null), "Material cannot be null");
/* 173 */     setBlockData(type.createBlockData(), applyPhysics);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockData(BlockData data) {
/* 178 */     setBlockData(data, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockData(BlockData data, boolean applyPhysics) {
/* 183 */     Preconditions.checkArgument((data != null), "BlockData cannot be null");
/* 184 */     setTypeAndData(((CraftBlockData)data).getState(), applyPhysics);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setTypeAndData(IBlockData blockData, boolean applyPhysics) {
/* 189 */     if (!blockData.isAir() && blockData.getBlock() instanceof net.minecraft.server.v1_16_R2.BlockTileEntity && blockData.getBlock() != getNMSBlock())
/*     */     {
/* 191 */       if (this.world instanceof World) {
/* 192 */         ((World)this.world).removeTileEntity(this.position);
/*     */       } else {
/* 194 */         this.world.setTypeAndData(this.position, Blocks.AIR.getBlockData(), 0);
/*     */       } 
/*     */     }
/*     */     
/* 198 */     if (applyPhysics) {
/* 199 */       return this.world.setTypeAndData(this.position, blockData, 3);
/*     */     }
/* 201 */     IBlockData old = this.world.getType(this.position);
/* 202 */     boolean success = this.world.setTypeAndData(this.position, blockData, 1042);
/* 203 */     if (success) {
/* 204 */       this.world.getMinecraftWorld().notify(this.position, old, blockData, 3);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     return success;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Material getType() {
/* 217 */     return this.world.getType(this.position).getBukkitMaterial();
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getLightLevel() {
/* 222 */     return (byte)this.world.getMinecraftWorld().getLightLevel(this.position);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getLightFromSky() {
/* 227 */     return (byte)this.world.getBrightness(EnumSkyBlock.SKY, this.position);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getLightFromBlocks() {
/* 232 */     return (byte)this.world.getBrightness(EnumSkyBlock.BLOCK, this.position);
/*     */   }
/*     */   
/*     */   public Block getFace(BlockFace face) {
/* 236 */     return getRelative(face, 1);
/*     */   }
/*     */   
/*     */   public Block getFace(BlockFace face, int distance) {
/* 240 */     return getRelative(face, distance);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getRelative(int modX, int modY, int modZ) {
/* 245 */     return getWorld().getBlockAt(getX() + modX, getY() + modY, getZ() + modZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getRelative(BlockFace face) {
/* 250 */     return getRelative(face, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getRelative(BlockFace face, int distance) {
/* 255 */     return getRelative(face.getModX() * distance, face.getModY() * distance, face.getModZ() * distance);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getFace(Block block) {
/* 260 */     BlockFace[] values = BlockFace.values();
/*     */     
/* 262 */     for (BlockFace face : values) {
/* 263 */       if (getX() + face.getModX() == block.getX() && getY() + face.getModY() == block.getY() && getZ() + face.getModZ() == block.getZ()) {
/* 264 */         return face;
/*     */       }
/*     */     } 
/*     */     
/* 268 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 273 */     return "CraftBlock{pos=" + this.position + ",type=" + getType() + ",data=" + getNMS() + ",fluid=" + this.world.getFluid(this.position) + '}';
/*     */   }
/*     */   
/*     */   public static BlockFace notchToBlockFace(EnumDirection notch) {
/* 277 */     if (notch == null) {
/* 278 */       return BlockFace.SELF;
/*     */     }
/* 280 */     switch (notch) {
/*     */       case ACACIA_SIGN:
/* 282 */         return BlockFace.DOWN;
/*     */       case ACACIA_WALL_SIGN:
/* 284 */         return BlockFace.UP;
/*     */       case BIRCH_SIGN:
/* 286 */         return BlockFace.NORTH;
/*     */       case BIRCH_WALL_SIGN:
/* 288 */         return BlockFace.SOUTH;
/*     */       case CRIMSON_SIGN:
/* 290 */         return BlockFace.WEST;
/*     */       case CRIMSON_WALL_SIGN:
/* 292 */         return BlockFace.EAST;
/*     */     } 
/* 294 */     return BlockFace.SELF;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumDirection blockFaceToNotch(BlockFace face) {
/* 299 */     switch (face) {
/*     */       case ACACIA_SIGN:
/* 301 */         return EnumDirection.DOWN;
/*     */       case ACACIA_WALL_SIGN:
/* 303 */         return EnumDirection.UP;
/*     */       case BIRCH_SIGN:
/* 305 */         return EnumDirection.NORTH;
/*     */       case BIRCH_WALL_SIGN:
/* 307 */         return EnumDirection.SOUTH;
/*     */       case CRIMSON_SIGN:
/* 309 */         return EnumDirection.WEST;
/*     */       case CRIMSON_WALL_SIGN:
/* 311 */         return EnumDirection.EAST;
/*     */     } 
/* 313 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState getState() {
/* 320 */     return getState(true);
/*     */   }
/*     */   public BlockState getState(boolean useSnapshot) {
/* 323 */     boolean prev = CraftBlockEntityState.DISABLE_SNAPSHOT;
/* 324 */     CraftBlockEntityState.DISABLE_SNAPSHOT = !useSnapshot;
/*     */     try {
/* 326 */       return getState0();
/*     */     } finally {
/* 328 */       CraftBlockEntityState.DISABLE_SNAPSHOT = prev;
/*     */     } 
/*     */   }
/*     */   
/*     */   public BlockState getState0() {
/* 333 */     Material material = getType();
/*     */     
/* 335 */     switch (material) {
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
/* 352 */         return new CraftSign(this);
/*     */       case CHEST:
/*     */       case TRAPPED_CHEST:
/* 355 */         return new CraftChest(this);
/*     */       case FURNACE:
/* 357 */         return new CraftFurnaceFurnace(this);
/*     */       case DISPENSER:
/* 359 */         return new CraftDispenser(this);
/*     */       case DROPPER:
/* 361 */         return new CraftDropper(this);
/*     */       case END_GATEWAY:
/* 363 */         return new CraftEndGateway(this);
/*     */       case HOPPER:
/* 365 */         return new CraftHopper(this);
/*     */       case SPAWNER:
/* 367 */         return new CraftCreatureSpawner(this);
/*     */       case JUKEBOX:
/* 369 */         return new CraftJukebox(this);
/*     */       case BREWING_STAND:
/* 371 */         return new CraftBrewingStand(this);
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
/* 384 */         return new CraftSkull(this);
/*     */       case COMMAND_BLOCK:
/*     */       case CHAIN_COMMAND_BLOCK:
/*     */       case REPEATING_COMMAND_BLOCK:
/* 388 */         return new CraftCommandBlock(this);
/*     */       case BEACON:
/* 390 */         return new CraftBeacon(this);
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
/* 423 */         return new CraftBanner(this);
/*     */       case STRUCTURE_BLOCK:
/* 425 */         return new CraftStructureBlock(this);
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
/* 443 */         return new CraftShulkerBox(this);
/*     */       case ENCHANTING_TABLE:
/* 445 */         return new CraftEnchantingTable(this);
/*     */       case ENDER_CHEST:
/* 447 */         return new CraftEnderChest(this);
/*     */       case DAYLIGHT_DETECTOR:
/* 449 */         return new CraftDaylightDetector(this);
/*     */       case COMPARATOR:
/* 451 */         return new CraftComparator(this);
/*     */       case BLACK_BED:
/*     */       case BLUE_BED:
/*     */       case BROWN_BED:
/*     */       case CYAN_BED:
/*     */       case GRAY_BED:
/*     */       case GREEN_BED:
/*     */       case LIGHT_BLUE_BED:
/*     */       case LIGHT_GRAY_BED:
/*     */       case LIME_BED:
/*     */       case MAGENTA_BED:
/*     */       case ORANGE_BED:
/*     */       case PINK_BED:
/*     */       case PURPLE_BED:
/*     */       case RED_BED:
/*     */       case WHITE_BED:
/*     */       case YELLOW_BED:
/* 468 */         return new CraftBed(this);
/*     */       case CONDUIT:
/* 470 */         return new CraftConduit(this);
/*     */       case BARREL:
/* 472 */         return new CraftBarrel(this);
/*     */       case BELL:
/* 474 */         return new CraftBell(this);
/*     */       case BLAST_FURNACE:
/* 476 */         return new CraftBlastFurnace(this);
/*     */       case CAMPFIRE:
/*     */       case SOUL_CAMPFIRE:
/* 479 */         return new CraftCampfire(this);
/*     */       case JIGSAW:
/* 481 */         return new CraftJigsaw(this);
/*     */       case LECTERN:
/* 483 */         return new CraftLectern(this);
/*     */       case SMOKER:
/* 485 */         return new CraftSmoker(this);
/*     */       case BEEHIVE:
/*     */       case BEE_NEST:
/* 488 */         return new CraftBeehive(this);
/*     */     } 
/* 490 */     TileEntity tileEntity = this.world.getTileEntity(this.position);
/* 491 */     if (tileEntity != null)
/*     */     {
/* 493 */       return new CraftBlockEntityState(this, tileEntity.getClass());
/*     */     }
/*     */     
/* 496 */     return new CraftBlockState(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Biome getBiome() {
/* 503 */     return getWorld().getBiome(getX(), getY(), getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBiome(Biome bio) {
/* 508 */     getWorld().setBiome(getX(), getY(), getZ(), bio);
/*     */   }
/*     */   
/*     */   public static Biome biomeBaseToBiome(IRegistry<BiomeBase> registry, BiomeBase base) {
/* 512 */     if (base == null) {
/* 513 */       return null;
/*     */     }
/*     */     
/* 516 */     return (Biome)Registry.BIOME.get(CraftNamespacedKey.fromMinecraft(registry.getKey(base)));
/*     */   }
/*     */   
/*     */   public static BiomeBase biomeToBiomeBase(IRegistry<BiomeBase> registry, Biome bio) {
/* 520 */     if (bio == null) {
/* 521 */       return null;
/*     */     }
/*     */     
/* 524 */     return (BiomeBase)registry.get(CraftNamespacedKey.toMinecraft(bio.getKey()));
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTemperature() {
/* 529 */     return this.world.getBiome(this.position).getAdjustedTemperature(this.position);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getHumidity() {
/* 534 */     return getWorld().getHumidity(getX(), getY(), getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockPowered() {
/* 539 */     return (this.world.getMinecraftWorld().getBlockPower(this.position) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockIndirectlyPowered() {
/* 544 */     return this.world.getMinecraftWorld().isBlockIndirectlyPowered(this.position);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 549 */     if (o == this) {
/* 550 */       return true;
/*     */     }
/* 552 */     if (!(o instanceof CraftBlock)) {
/* 553 */       return false;
/*     */     }
/* 555 */     CraftBlock other = (CraftBlock)o;
/*     */     
/* 557 */     return (this.position.equals(other.position) && getWorld().equals(other.getWorld()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 562 */     return this.position.hashCode() ^ getWorld().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockFacePowered(BlockFace face) {
/* 567 */     return this.world.getMinecraftWorld().isBlockFacePowered(this.position, blockFaceToNotch(face));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBlockFaceIndirectlyPowered(BlockFace face) {
/* 572 */     int power = this.world.getMinecraftWorld().getBlockFacePower(this.position, blockFaceToNotch(face));
/*     */     
/* 574 */     Block relative = getRelative(face);
/* 575 */     if (relative.getType() == Material.REDSTONE_WIRE) {
/* 576 */       return (Math.max(power, relative.getData()) > 0);
/*     */     }
/*     */     
/* 579 */     return (power > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockPower(BlockFace face) {
/* 584 */     int power = 0;
/* 585 */     WorldServer worldServer = this.world.getMinecraftWorld();
/* 586 */     int x = getX();
/* 587 */     int y = getY();
/* 588 */     int z = getZ();
/* 589 */     if ((face == BlockFace.DOWN || face == BlockFace.SELF) && worldServer.isBlockFacePowered(new BlockPosition(x, y - 1, z), EnumDirection.DOWN)) power = getPower(power, worldServer.getType(new BlockPosition(x, y - 1, z))); 
/* 590 */     if ((face == BlockFace.UP || face == BlockFace.SELF) && worldServer.isBlockFacePowered(new BlockPosition(x, y + 1, z), EnumDirection.UP)) power = getPower(power, worldServer.getType(new BlockPosition(x, y + 1, z))); 
/* 591 */     if ((face == BlockFace.EAST || face == BlockFace.SELF) && worldServer.isBlockFacePowered(new BlockPosition(x + 1, y, z), EnumDirection.EAST)) power = getPower(power, worldServer.getType(new BlockPosition(x + 1, y, z))); 
/* 592 */     if ((face == BlockFace.WEST || face == BlockFace.SELF) && worldServer.isBlockFacePowered(new BlockPosition(x - 1, y, z), EnumDirection.WEST)) power = getPower(power, worldServer.getType(new BlockPosition(x - 1, y, z))); 
/* 593 */     if ((face == BlockFace.NORTH || face == BlockFace.SELF) && worldServer.isBlockFacePowered(new BlockPosition(x, y, z - 1), EnumDirection.NORTH)) power = getPower(power, worldServer.getType(new BlockPosition(x, y, z - 1))); 
/* 594 */     if ((face == BlockFace.SOUTH || face == BlockFace.SELF) && worldServer.isBlockFacePowered(new BlockPosition(x, y, z + 1), EnumDirection.SOUTH)) power = getPower(power, worldServer.getType(new BlockPosition(x, y, z + 1))); 
/* 595 */     return (power > 0) ? power : (((face == BlockFace.SELF) ? isBlockIndirectlyPowered() : isBlockFaceIndirectlyPowered(face)) ? 15 : 0);
/*     */   }
/*     */   
/*     */   private static int getPower(int i, IBlockData iblockdata) {
/* 599 */     if (!iblockdata.getBlock().a(Blocks.REDSTONE_WIRE)) {
/* 600 */       return i;
/*     */     }
/* 602 */     int j = ((Integer)iblockdata.get((IBlockState)BlockRedstoneWire.POWER)).intValue();
/*     */     
/* 604 */     return (j > i) ? j : i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockPower() {
/* 610 */     return getBlockPower(BlockFace.SELF);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 615 */     return getNMS().isAir();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLiquid() {
/* 620 */     return getNMS().getMaterial().isLiquid();
/*     */   }
/*     */ 
/*     */   
/*     */   public PistonMoveReaction getPistonMoveReaction() {
/* 625 */     return PistonMoveReaction.getById(getNMS().getPushReaction().ordinal());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean breakNaturally() {
/* 630 */     return breakNaturally(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean breakNaturally(ItemStack item) {
/* 636 */     return breakNaturally(item, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean breakNaturally(ItemStack item, boolean triggerEffect) {
/* 643 */     IBlockData iblockdata = getNMS();
/* 644 */     Block block = iblockdata.getBlock();
/* 645 */     ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
/* 646 */     boolean result = false;
/*     */ 
/*     */     
/* 649 */     if (block != Blocks.AIR && (item == null || !iblockdata.isRequiresSpecialTool() || nmsItem.canDestroySpecialBlock(iblockdata))) {
/* 650 */       Block.dropItems(iblockdata, (World)this.world.getMinecraftWorld(), this.position, this.world.getTileEntity(this.position), null, nmsItem);
/* 651 */       if (triggerEffect) this.world.triggerEffect(Effect.STEP_SOUND.getId(), this.position, Block.getCombinedId(block.getBlockData())); 
/* 652 */       result = true;
/*     */     } 
/*     */     
/* 655 */     return (setTypeAndData(Blocks.AIR.getBlockData(), true) && result);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean applyBoneMeal(BlockFace face) {
/* 660 */     EnumDirection direction = blockFaceToNotch(face);
/* 661 */     ItemActionContext context = new ItemActionContext((World)getCraftWorld().getHandle(), null, EnumHand.MAIN_HAND, Items.BONE_MEAL.createItemStack(), new MovingObjectPositionBlock(Vec3D.ORIGIN, direction, getPosition(), false));
/*     */     
/* 663 */     return (ItemBoneMeal.applyBonemeal(context) == EnumInteractionResult.SUCCESS);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<ItemStack> getDrops() {
/* 668 */     return getDrops(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<ItemStack> getDrops(ItemStack item) {
/* 673 */     return getDrops(item, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<ItemStack> getDrops(ItemStack item, Entity entity) {
/* 678 */     IBlockData iblockdata = getNMS();
/* 679 */     ItemStack nms = CraftItemStack.asNMSCopy(item);
/*     */ 
/*     */     
/* 682 */     if (item == null || !iblockdata.isRequiresSpecialTool() || nms.canDestroySpecialBlock(iblockdata)) {
/* 683 */       return (Collection<ItemStack>)Block.getDrops(iblockdata, this.world.getMinecraftWorld(), this.position, this.world.getTileEntity(this.position), (entity == null) ? null : ((CraftEntity)entity).getHandle(), nms)
/* 684 */         .stream().map(CraftItemStack::asBukkitCopy).collect(Collectors.toList());
/*     */     }
/* 686 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
/* 692 */     getCraftWorld().getBlockMetadata().setMetadata(this, metadataKey, newMetadataValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<MetadataValue> getMetadata(String metadataKey) {
/* 697 */     return getCraftWorld().getBlockMetadata().getMetadata(this, metadataKey);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMetadata(String metadataKey) {
/* 702 */     return getCraftWorld().getBlockMetadata().hasMetadata(this, metadataKey);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMetadata(String metadataKey, Plugin owningPlugin) {
/* 707 */     getCraftWorld().getBlockMetadata().removeMetadata(this, metadataKey, owningPlugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassable() {
/* 712 */     return getNMS().getCollisionShape((IBlockAccess)this.world, this.position).isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public RayTraceResult rayTrace(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode) {
/* 717 */     Validate.notNull(start, "Start location is null!");
/* 718 */     Validate.isTrue(getWorld().equals(start.getWorld()), "Start location is from different world!");
/* 719 */     start.checkFinite();
/*     */     
/* 721 */     Validate.notNull(direction, "Direction is null!");
/* 722 */     direction.checkFinite();
/* 723 */     Validate.isTrue((direction.lengthSquared() > 0.0D), "Direction's magnitude is 0!");
/*     */     
/* 725 */     Validate.notNull(fluidCollisionMode, "Fluid collision mode is null!");
/* 726 */     if (maxDistance < 0.0D) {
/* 727 */       return null;
/*     */     }
/*     */     
/* 730 */     Vector dir = direction.clone().normalize().multiply(maxDistance);
/* 731 */     Vec3D startPos = new Vec3D(start.getX(), start.getY(), start.getZ());
/* 732 */     Vec3D endPos = new Vec3D(start.getX() + dir.getX(), start.getY() + dir.getY(), start.getZ() + dir.getZ());
/*     */     
/* 734 */     MovingObjectPositionBlock movingObjectPositionBlock = this.world.rayTraceBlock(new RayTrace(startPos, endPos, RayTrace.BlockCollisionOption.OUTLINE, CraftFluidCollisionMode.toNMS(fluidCollisionMode), null), this.position);
/* 735 */     return CraftRayTraceResult.fromNMS(getWorld(), (MovingObjectPosition)movingObjectPositionBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   public BoundingBox getBoundingBox() {
/* 740 */     VoxelShape shape = getNMS().getShape((IBlockAccess)this.world, this.position);
/*     */     
/* 742 */     if (shape.isEmpty()) {
/* 743 */       return new BoundingBox();
/*     */     }
/*     */     
/* 746 */     AxisAlignedBB aabb = shape.getBoundingBox();
/* 747 */     return new BoundingBox(getX() + aabb.minX, getY() + aabb.minY, getZ() + aabb.minZ, getX() + aabb.maxX, getY() + aabb.maxY, getZ() + aabb.maxZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockSoundGroup getSoundGroup() {
/* 753 */     return (BlockSoundGroup)new CraftBlockSoundGroup(getNMSBlock().getBlockData().getStepSound());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTranslationKey() {
/* 758 */     return Bukkit.getUnsafe().getTranslationKey(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */