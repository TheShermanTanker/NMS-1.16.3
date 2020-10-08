/*     */ package org.bukkit.craftbukkit.v1_16_R2.generator;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Random;
/*     */ import net.minecraft.server.v1_16_R2.BiomeBase;
/*     */ import net.minecraft.server.v1_16_R2.BiomeManager;
/*     */ import net.minecraft.server.v1_16_R2.BiomeStorage;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.ChunkGenerator;
/*     */ import net.minecraft.server.v1_16_R2.ChunkSection;
/*     */ import net.minecraft.server.v1_16_R2.DefinedStructureManager;
/*     */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*     */ import net.minecraft.server.v1_16_R2.GeneratorAccessSeed;
/*     */ import net.minecraft.server.v1_16_R2.HeightMap;
/*     */ import net.minecraft.server.v1_16_R2.IBlockAccess;
/*     */ import net.minecraft.server.v1_16_R2.IChunkAccess;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import net.minecraft.server.v1_16_R2.IRegistryCustom;
/*     */ import net.minecraft.server.v1_16_R2.ITileEntity;
/*     */ import net.minecraft.server.v1_16_R2.ProtoChunk;
/*     */ import net.minecraft.server.v1_16_R2.RegionLimitedWorldAccess;
/*     */ import net.minecraft.server.v1_16_R2.Registry;
/*     */ import net.minecraft.server.v1_16_R2.StructureManager;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import net.minecraft.server.v1_16_R2.WorldChunkManager;
/*     */ import net.minecraft.server.v1_16_R2.WorldGenStage;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Biome;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.generator.ChunkGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomChunkGenerator
/*     */   extends InternalChunkGenerator
/*     */ {
/*     */   private final ChunkGenerator delegate;
/*     */   private final ChunkGenerator generator;
/*     */   private final WorldServer world;
/*  46 */   private final Random random = new Random();
/*     */   
/*     */   private class CustomBiomeGrid
/*     */     implements ChunkGenerator.BiomeGrid {
/*     */     private final BiomeStorage biome;
/*     */     
/*     */     public CustomBiomeGrid(BiomeStorage biome) {
/*  53 */       this.biome = biome;
/*     */     }
/*     */ 
/*     */     
/*     */     public Biome getBiome(int x, int z) {
/*  58 */       return getBiome(x, 0, z);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setBiome(int x, int z, Biome bio) {
/*  63 */       for (int y = 0; y < CustomChunkGenerator.this.world.getWorld().getMaxHeight(); y += 4) {
/*  64 */         setBiome(x, y, z, bio);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public Biome getBiome(int x, int y, int z) {
/*  70 */       return CraftBlock.biomeBaseToBiome((IRegistry)this.biome.g, this.biome.getBiome(x >> 2, y >> 2, z >> 2));
/*     */     }
/*     */ 
/*     */     
/*     */     public void setBiome(int x, int y, int z, Biome bio) {
/*  75 */       this.biome.setBiome(x >> 2, y >> 2, z >> 2, CraftBlock.biomeToBiomeBase((IRegistry)this.biome.g, bio));
/*     */     }
/*     */   }
/*     */   
/*     */   public CustomChunkGenerator(WorldServer world, ChunkGenerator delegate, ChunkGenerator generator) {
/*  80 */     super(delegate.getWorldChunkManager(), delegate.getSettings());
/*     */     
/*  82 */     this.world = world;
/*  83 */     this.delegate = delegate;
/*  84 */     this.generator = generator;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void createBiomes(IRegistry<BiomeBase> iregistry, IChunkAccess ichunkaccess) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldChunkManager getWorldChunkManager() {
/*  94 */     return this.delegate.getWorldChunkManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public void storeStructures(GeneratorAccessSeed generatoraccessseed, StructureManager structuremanager, IChunkAccess ichunkaccess) {
/*  99 */     this.delegate.storeStructures(generatoraccessseed, structuremanager, ichunkaccess);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSeaLevel() {
/* 104 */     return this.delegate.getSeaLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildBase(RegionLimitedWorldAccess regionlimitedworldaccess, IChunkAccess ichunkaccess) {
/*     */     ChunkGenerator.ChunkData data;
/* 110 */     int x = (ichunkaccess.getPos()).x;
/* 111 */     int z = (ichunkaccess.getPos()).z;
/* 112 */     this.random.setSeed(x * 341873128712L + z * 132897987541L);
/*     */ 
/*     */     
/* 115 */     CustomBiomeGrid biomegrid = new CustomBiomeGrid(new BiomeStorage((Registry)this.world.r().b(IRegistry.ay), ichunkaccess.getPos(), getWorldChunkManager()));
/*     */ 
/*     */     
/* 118 */     if (this.generator.isParallelCapable()) {
/* 119 */       data = this.generator.generateChunkData((World)this.world.getWorld(), this.random, x, z, biomegrid);
/*     */     } else {
/* 121 */       synchronized (this) {
/* 122 */         data = this.generator.generateChunkData((World)this.world.getWorld(), this.random, x, z, biomegrid);
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     Preconditions.checkArgument(data instanceof CraftChunkData, "Plugins must use createChunkData(World) rather than implementing ChunkData: %s", data);
/* 127 */     CraftChunkData craftData = (CraftChunkData)data;
/* 128 */     ChunkSection[] sections = craftData.getRawChunkData();
/*     */     
/* 130 */     ChunkSection[] csect = ichunkaccess.getSections();
/* 131 */     int scnt = Math.min(csect.length, sections.length);
/*     */ 
/*     */     
/* 134 */     for (int sec = 0; sec < scnt; sec++) {
/* 135 */       if (sections[sec] != null) {
/*     */ 
/*     */         
/* 138 */         ChunkSection section = sections[sec];
/*     */         
/* 140 */         csect[sec] = section;
/*     */       } 
/*     */     } 
/*     */     
/* 144 */     ((ProtoChunk)ichunkaccess).a(biomegrid.biome);
/*     */     
/* 146 */     if (craftData.getTiles() != null) {
/* 147 */       for (BlockPosition pos : craftData.getTiles()) {
/* 148 */         int tx = pos.getX();
/* 149 */         int ty = pos.getY();
/* 150 */         int tz = pos.getZ();
/* 151 */         Block block = craftData.getTypeId(tx, ty, tz).getBlock();
/*     */         
/* 153 */         if (block.isTileEntity()) {
/* 154 */           TileEntity tile = ((ITileEntity)block).createTile((IBlockAccess)this.world);
/* 155 */           ichunkaccess.setTileEntity(new BlockPosition((x << 4) + tx, ty, (z << 4) + tz), tile);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void createStructures(IRegistryCustom iregistrycustom, StructureManager structuremanager, IChunkAccess ichunkaccess, DefinedStructureManager definedstructuremanager, long i) {
/* 163 */     if (this.generator.shouldGenerateStructures())
/*     */     {
/*     */       
/* 166 */       this.delegate.createStructures(iregistrycustom, structuremanager, ichunkaccess, definedstructuremanager, i);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCarving(long i, BiomeManager biomemanager, IChunkAccess ichunkaccess, WorldGenStage.Features worldgenstage_features) {
/* 173 */     if (this.generator.shouldGenerateCaves()) {
/* 174 */       this.delegate.doCarving(i, biomemanager, ichunkaccess, worldgenstage_features);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildNoise(GeneratorAccess generatoraccess, StructureManager structuremanager, IChunkAccess ichunkaccess) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBaseHeight(int i, int j, HeightMap.Type heightmap_type) {
/* 185 */     return this.delegate.getBaseHeight(i, j, heightmap_type);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addDecorations(RegionLimitedWorldAccess regionlimitedworldaccess, StructureManager structuremanager) {
/* 190 */     if (this.generator.shouldGenerateDecorations()) {
/* 191 */       this.delegate.addDecorations(regionlimitedworldaccess, structuremanager);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMobs(RegionLimitedWorldAccess regionlimitedworldaccess) {
/* 197 */     if (this.generator.shouldGenerateMobs()) {
/* 198 */       this.delegate.addMobs(regionlimitedworldaccess);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpawnHeight() {
/* 204 */     return this.delegate.getSpawnHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGenerationDepth() {
/* 209 */     return this.delegate.getGenerationDepth();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockAccess a(int i, int j) {
/* 214 */     return this.delegate.a(i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Codec<? extends ChunkGenerator> a() {
/* 219 */     throw new UnsupportedOperationException("Cannot serialize CustomChunkGenerator");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\generator\CustomChunkGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */