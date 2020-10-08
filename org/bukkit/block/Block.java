/*     */ package org.bukkit.block;
/*     */ 
/*     */ import com.destroystokyo.paper.block.BlockSoundGroup;
/*     */ import java.util.Collection;
/*     */ import org.bukkit.Chunk;
/*     */ import org.bukkit.FluidCollisionMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.metadata.Metadatable;
/*     */ import org.bukkit.util.BoundingBox;
/*     */ import org.bukkit.util.RayTraceResult;
/*     */ import org.bukkit.util.Vector;
/*     */ import org.jetbrains.annotations.Contract;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Block
/*     */   extends Metadatable
/*     */ {
/*     */   @Deprecated
/*     */   byte getData();
/*     */   
/*     */   @NotNull
/*     */   BlockData getBlockData();
/*     */   
/*     */   @NotNull
/*     */   Block getRelative(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   @NotNull
/*     */   Block getRelative(@NotNull BlockFace paramBlockFace);
/*     */   
/*     */   @NotNull
/*     */   Block getRelative(@NotNull BlockFace paramBlockFace, int paramInt);
/*     */   
/*     */   @NotNull
/*     */   Material getType();
/*     */   
/*     */   byte getLightLevel();
/*     */   
/*     */   byte getLightFromSky();
/*     */   
/*     */   byte getLightFromBlocks();
/*     */   
/*     */   @NotNull
/*     */   World getWorld();
/*     */   
/*     */   int getX();
/*     */   
/*     */   int getY();
/*     */   
/*     */   int getZ();
/*     */   
/*     */   default long getBlockKey() {
/* 165 */     return getBlockKey(getX(), getY(), getZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static long getBlockKey(int x, int y, int z) {
/* 189 */     return x & 0x7FFFFFFL | (z & 0x7FFFFFFL) << 27L | y << 54L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getBlockKeyX(long packed) {
/* 199 */     return (int)(packed << 37L >> 37L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getBlockKeyY(long packed) {
/* 209 */     return (int)(packed >>> 54L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getBlockKeyZ(long packed) {
/* 219 */     return (int)(packed << 10L >> 37L);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   Location getLocation();
/*     */   
/*     */   @Contract("null -> null; !null -> !null")
/*     */   @Nullable
/*     */   Location getLocation(@Nullable Location paramLocation);
/*     */   
/*     */   @NotNull
/*     */   Chunk getChunk();
/*     */   
/*     */   void setBlockData(@NotNull BlockData paramBlockData);
/*     */   
/*     */   void setBlockData(@NotNull BlockData paramBlockData, boolean paramBoolean);
/*     */   
/*     */   void setType(@NotNull Material paramMaterial);
/*     */   
/*     */   void setType(@NotNull Material paramMaterial, boolean paramBoolean);
/*     */   
/*     */   @Nullable
/*     */   BlockFace getFace(@NotNull Block paramBlock);
/*     */   
/*     */   @NotNull
/*     */   BlockState getState();
/*     */   
/*     */   @NotNull
/*     */   BlockState getState(boolean paramBoolean);
/*     */   
/*     */   @NotNull
/*     */   Biome getBiome();
/*     */   
/*     */   void setBiome(@NotNull Biome paramBiome);
/*     */   
/*     */   boolean isBlockPowered();
/*     */   
/*     */   boolean isBlockIndirectlyPowered();
/*     */   
/*     */   boolean isBlockFacePowered(@NotNull BlockFace paramBlockFace);
/*     */   
/*     */   boolean isBlockFaceIndirectlyPowered(@NotNull BlockFace paramBlockFace);
/*     */   
/*     */   int getBlockPower(@NotNull BlockFace paramBlockFace);
/*     */   
/*     */   int getBlockPower();
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   boolean isLiquid();
/*     */   
/*     */   double getTemperature();
/*     */   
/*     */   double getHumidity();
/*     */   
/*     */   @NotNull
/*     */   PistonMoveReaction getPistonMoveReaction();
/*     */   
/*     */   boolean breakNaturally();
/*     */   
/*     */   boolean breakNaturally(@Nullable ItemStack paramItemStack);
/*     */   
/*     */   boolean breakNaturally(@NotNull ItemStack paramItemStack, boolean paramBoolean);
/*     */   
/*     */   boolean applyBoneMeal(@NotNull BlockFace paramBlockFace);
/*     */   
/*     */   @NotNull
/*     */   Collection<ItemStack> getDrops();
/*     */   
/*     */   @NotNull
/*     */   Collection<ItemStack> getDrops(@Nullable ItemStack paramItemStack);
/*     */   
/*     */   @NotNull
/*     */   Collection<ItemStack> getDrops(@NotNull ItemStack paramItemStack, @Nullable Entity paramEntity);
/*     */   
/*     */   boolean isPassable();
/*     */   
/*     */   @Nullable
/*     */   RayTraceResult rayTrace(@NotNull Location paramLocation, @NotNull Vector paramVector, double paramDouble, @NotNull FluidCollisionMode paramFluidCollisionMode);
/*     */   
/*     */   @NotNull
/*     */   BoundingBox getBoundingBox();
/*     */   
/*     */   @NotNull
/*     */   BlockSoundGroup getSoundGroup();
/*     */   
/*     */   @NotNull
/*     */   String getTranslationKey();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Block.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */