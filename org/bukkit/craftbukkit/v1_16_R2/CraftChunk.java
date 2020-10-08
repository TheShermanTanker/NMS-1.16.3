/*     */ package org.bukkit.craftbukkit.v1_16_R2;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.server.v1_16_R2.BiomeStorage;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.Blocks;
/*     */ import net.minecraft.server.v1_16_R2.Chunk;
/*     */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*     */ import net.minecraft.server.v1_16_R2.ChunkSection;
/*     */ import net.minecraft.server.v1_16_R2.DataPaletteBlock;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EnumSkyBlock;
/*     */ import net.minecraft.server.v1_16_R2.GameProfileSerializer;
/*     */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*     */ import net.minecraft.server.v1_16_R2.HeightMap;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import net.minecraft.server.v1_16_R2.LightEngineThreaded;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NibbleArray;
/*     */ import net.minecraft.server.v1_16_R2.Registry;
/*     */ import net.minecraft.server.v1_16_R2.SectionPosition;
/*     */ import net.minecraft.server.v1_16_R2.SeededRandom;
/*     */ import net.minecraft.server.v1_16_R2.WorldChunkManager;
/*     */ import org.bukkit.ChunkSnapshot;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class CraftChunk implements Chunk {
/*     */   private WeakReference<Chunk> weakChunk;
/*     */   private final WorldServer worldServer;
/*  44 */   private static final DataPaletteBlock<IBlockData> emptyBlockIDs = (new ChunkSection(0, null, null, true)).getBlocks(); private final int x; private final int z;
/*  45 */   private static final byte[] emptyLight = new byte[2048];
/*     */   
/*     */   public CraftChunk(Chunk chunk) {
/*  48 */     this.weakChunk = new WeakReference<>(chunk);
/*     */     
/*  50 */     this.worldServer = (getHandle()).world;
/*  51 */     this.x = (getHandle().getPos()).x;
/*  52 */     this.z = (getHandle().getPos()).z;
/*     */   }
/*     */ 
/*     */   
/*     */   public World getWorld() {
/*  57 */     return this.worldServer.getWorld();
/*     */   }
/*     */   
/*     */   public CraftWorld getCraftWorld() {
/*  61 */     return (CraftWorld)getWorld();
/*     */   }
/*     */   
/*     */   public Chunk getHandle() {
/*  65 */     Chunk c = this.weakChunk.get();
/*     */     
/*  67 */     if (c == null) {
/*  68 */       c = this.worldServer.getChunkAt(this.x, this.z);
/*     */       
/*  70 */       this.weakChunk = new WeakReference<>(c);
/*     */     } 
/*     */     
/*  73 */     return c;
/*     */   }
/*     */   
/*     */   void breakLink() {
/*  77 */     this.weakChunk.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/*  82 */     return this.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getZ() {
/*  87 */     return this.z;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  92 */     return "CraftChunk{x=" + getX() + "z=" + getZ() + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlock(int x, int y, int z) {
/*  97 */     validateChunkCoordinates(x, y, z);
/*     */     
/*  99 */     return (Block)new CraftBlock((GeneratorAccess)this.worldServer, new BlockPosition(this.x << 4 | x, y, this.z << 4 | z));
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity[] getEntities() {
/* 104 */     if (!isLoaded()) {
/* 105 */       getWorld().getChunkAt(this.x, this.z);
/*     */     }
/* 107 */     int count = 0, index = 0;
/* 108 */     Chunk chunk = getHandle();
/*     */     
/* 110 */     for (int i = 0; i < 16; i++) {
/* 111 */       count += chunk.entitySlices[i].size();
/*     */     }
/*     */     
/* 114 */     Entity[] entities = new Entity[count];
/*     */     
/* 116 */     for (int j = 0; j < 16; j++) {
/*     */       
/* 118 */       for (Entity entity : chunk.entitySlices[j]) {
/* 119 */         if (entity == null) {
/*     */           continue;
/*     */         }
/* 122 */         entities[index++] = (Entity)entity.getBukkitEntity();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 127 */     return entities;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockState[] getTileEntities() {
/* 133 */     return getTileEntities(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockState[] getTileEntities(boolean useSnapshot) {
/* 138 */     if (!isLoaded()) {
/* 139 */       getWorld().getChunkAt(this.x, this.z);
/*     */     }
/*     */     
/* 142 */     int index = 0;
/* 143 */     Chunk chunk = getHandle();
/*     */     
/* 145 */     BlockState[] entities = new BlockState[chunk.tileEntities.size()];
/*     */     
/* 147 */     for (Object obj : chunk.tileEntities.keySet().toArray()) {
/* 148 */       if (obj instanceof BlockPosition) {
/*     */ 
/*     */ 
/*     */         
/* 152 */         BlockPosition position = (BlockPosition)obj;
/* 153 */         entities[index++] = this.worldServer.getWorld().getBlockAt(position.getX(), position.getY(), position.getZ()).getState(useSnapshot);
/*     */       } 
/*     */     } 
/* 156 */     return entities;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLoaded() {
/* 161 */     return getWorld().isChunkLoaded(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean load() {
/* 166 */     return getWorld().loadChunk(getX(), getZ(), true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean load(boolean generate) {
/* 171 */     return getWorld().loadChunk(getX(), getZ(), generate);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean unload() {
/* 176 */     return getWorld().unloadChunk(getX(), getZ());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSlimeChunk() {
/* 182 */     return (SeededRandom.a(getX(), getZ(), getWorld().getSeed(), this.worldServer.spigotConfig.slimeSeed).nextInt(10) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean unload(boolean save) {
/* 187 */     return getWorld().unloadChunk(getX(), getZ(), save);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isForceLoaded() {
/* 192 */     return getWorld().isChunkForceLoaded(getX(), getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForceLoaded(boolean forced) {
/* 197 */     getWorld().setChunkForceLoaded(getX(), getZ(), forced);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addPluginChunkTicket(Plugin plugin) {
/* 202 */     return getWorld().addPluginChunkTicket(getX(), getZ(), plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removePluginChunkTicket(Plugin plugin) {
/* 207 */     return getWorld().removePluginChunkTicket(getX(), getZ(), plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<Plugin> getPluginChunkTickets() {
/* 212 */     return getWorld().getPluginChunkTickets(getX(), getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public long getInhabitedTime() {
/* 217 */     return getHandle().getInhabitedTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInhabitedTime(long ticks) {
/* 222 */     Preconditions.checkArgument((ticks >= 0L), "ticks cannot be negative");
/*     */     
/* 224 */     getHandle().setInhabitedTime(ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(BlockData block) {
/* 229 */     Preconditions.checkArgument((block != null), "Block cannot be null");
/*     */     
/* 231 */     Predicate predicate = Predicates.equalTo(((CraftBlockData)block).getState());
/* 232 */     for (ChunkSection section : getHandle().getSections()) {
/* 233 */       if (section != null && section.getBlocks().contains((Predicate)predicate)) {
/* 234 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 238 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkSnapshot getChunkSnapshot() {
/* 243 */     return getChunkSnapshot(true, false, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkSnapshot getChunkSnapshot(boolean includeMaxBlockY, boolean includeBiome, boolean includeBiomeTempRain) {
/* 248 */     Chunk chunk = getHandle();
/*     */     
/* 250 */     ChunkSection[] cs = chunk.getSections();
/* 251 */     DataPaletteBlock[] sectionBlockIDs = new DataPaletteBlock[cs.length];
/* 252 */     byte[][] sectionSkyLights = new byte[cs.length][];
/* 253 */     byte[][] sectionEmitLights = new byte[cs.length][];
/* 254 */     boolean[] sectionEmpty = new boolean[cs.length];
/*     */     
/* 256 */     for (int i = 0; i < cs.length; i++) {
/* 257 */       if (cs[i] == null) {
/* 258 */         sectionBlockIDs[i] = emptyBlockIDs;
/* 259 */         sectionSkyLights[i] = emptyLight;
/* 260 */         sectionEmitLights[i] = emptyLight;
/* 261 */         sectionEmpty[i] = true;
/*     */       } else {
/* 263 */         NBTTagCompound data = new NBTTagCompound();
/* 264 */         cs[i].getBlocks().a(data, "Palette", "BlockStates");
/*     */         
/* 266 */         DataPaletteBlock blockids = new DataPaletteBlock(ChunkSection.GLOBAL_PALETTE, Block.REGISTRY_ID, GameProfileSerializer::c, GameProfileSerializer::a, Blocks.AIR.getBlockData(), null, false);
/* 267 */         blockids.a(data.getList("Palette", 10), data.getLongArray("BlockStates"));
/*     */         
/* 269 */         sectionBlockIDs[i] = blockids;
/*     */         
/* 271 */         LightEngineThreaded lightEngineThreaded = chunk.world.getChunkProvider().getLightEngine();
/* 272 */         NibbleArray skyLightArray = lightEngineThreaded.a(EnumSkyBlock.SKY).a(SectionPosition.a(this.x, i, this.z));
/* 273 */         if (skyLightArray == null) {
/* 274 */           sectionSkyLights[i] = emptyLight;
/*     */         } else {
/* 276 */           sectionSkyLights[i] = new byte[2048];
/* 277 */           System.arraycopy(skyLightArray.getIfSet(), 0, sectionSkyLights[i], 0, 2048);
/*     */         } 
/* 279 */         NibbleArray emitLightArray = lightEngineThreaded.a(EnumSkyBlock.BLOCK).a(SectionPosition.a(this.x, i, this.z));
/* 280 */         if (emitLightArray == null) {
/* 281 */           sectionEmitLights[i] = emptyLight;
/*     */         } else {
/* 283 */           sectionEmitLights[i] = new byte[2048];
/* 284 */           System.arraycopy(emitLightArray.getIfSet(), 0, sectionEmitLights[i], 0, 2048);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 289 */     HeightMap hmap = null;
/*     */     
/* 291 */     if (includeMaxBlockY) {
/* 292 */       hmap = new HeightMap(null, HeightMap.Type.MOTION_BLOCKING);
/* 293 */       hmap.a(((HeightMap)chunk.heightMap.get(HeightMap.Type.MOTION_BLOCKING)).a());
/*     */     } 
/*     */     
/* 296 */     BiomeStorage biome = null;
/*     */     
/* 298 */     if (includeBiome || includeBiomeTempRain) {
/* 299 */       biome = chunk.getBiomeIndex();
/*     */     }
/*     */     
/* 302 */     World world = getWorld();
/* 303 */     return new CraftChunkSnapshot(getX(), getZ(), world.getName(), world.getFullTime(), (DataPaletteBlock<IBlockData>[])sectionBlockIDs, sectionSkyLights, sectionEmitLights, sectionEmpty, hmap, biome);
/*     */   }
/*     */   
/*     */   public static ChunkSnapshot getEmptyChunkSnapshot(int x, int z, CraftWorld world, boolean includeBiome, boolean includeBiomeTempRain) {
/* 307 */     BiomeStorage biome = null;
/*     */     
/* 309 */     if (includeBiome || includeBiomeTempRain) {
/* 310 */       WorldChunkManager wcm = world.getHandle().getChunkProvider().getChunkGenerator().getWorldChunkManager();
/* 311 */       biome = new BiomeStorage((Registry)world.getHandle().r().b(IRegistry.ay), new ChunkCoordIntPair(x, z), wcm);
/*     */     } 
/*     */ 
/*     */     
/* 315 */     int hSection = world.getMaxHeight() >> 4;
/* 316 */     DataPaletteBlock[] blockIDs = new DataPaletteBlock[hSection];
/* 317 */     byte[][] skyLight = new byte[hSection][];
/* 318 */     byte[][] emitLight = new byte[hSection][];
/* 319 */     boolean[] empty = new boolean[hSection];
/*     */     
/* 321 */     for (int i = 0; i < hSection; i++) {
/* 322 */       blockIDs[i] = emptyBlockIDs;
/* 323 */       skyLight[i] = emptyLight;
/* 324 */       emitLight[i] = emptyLight;
/* 325 */       empty[i] = true;
/*     */     } 
/*     */     
/* 328 */     return new CraftChunkSnapshot(x, z, world.getName(), world.getFullTime(), (DataPaletteBlock<IBlockData>[])blockIDs, skyLight, emitLight, empty, new HeightMap(null, HeightMap.Type.MOTION_BLOCKING), biome);
/*     */   }
/*     */   
/*     */   static void validateChunkCoordinates(int x, int y, int z) {
/* 332 */     Preconditions.checkArgument((0 <= x && x <= 15), "x out of range (expected 0-15, got %s)", x);
/* 333 */     Preconditions.checkArgument((0 <= y && y <= 255), "y out of range (expected 0-255, got %s)", y);
/* 334 */     Preconditions.checkArgument((0 <= z && z <= 15), "z out of range (expected 0-15, got %s)", z);
/*     */   }
/*     */   
/*     */   static {
/* 338 */     Arrays.fill(emptyLight, (byte)-1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */