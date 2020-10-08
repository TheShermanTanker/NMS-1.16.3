/*     */ package org.bukkit.craftbukkit.v1_16_R2.generator;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.Blocks;
/*     */ import net.minecraft.server.v1_16_R2.ChunkSection;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.generator.ChunkGenerator;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public final class CraftChunkData
/*     */   implements ChunkGenerator.ChunkData {
/*     */   private final int maxHeight;
/*     */   private ChunkSection[] sections;
/*     */   private Set<BlockPosition> tiles;
/*     */   private World world;
/*     */   
/*     */   public CraftChunkData(World world) {
/*  27 */     this(world.getMaxHeight());
/*  28 */     this.world = world;
/*     */   }
/*     */   
/*     */   CraftChunkData(int maxHeight) {
/*  32 */     if (maxHeight > 256) {
/*  33 */       throw new IllegalArgumentException("World height exceeded max chunk height");
/*     */     }
/*  35 */     this.maxHeight = maxHeight;
/*  36 */     this.sections = new ChunkSection[maxHeight >> 4];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxHeight() {
/*  41 */     return this.maxHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlock(int x, int y, int z, Material material) {
/*  46 */     setBlock(x, y, z, material.createBlockData());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlock(int x, int y, int z, MaterialData material) {
/*  51 */     setBlock(x, y, z, CraftMagicNumbers.getBlock(material));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlock(int x, int y, int z, BlockData blockData) {
/*  56 */     setBlock(x, y, z, ((CraftBlockData)blockData).getState());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, Material material) {
/*  61 */     setRegion(xMin, yMin, zMin, xMax, yMax, zMax, material.createBlockData());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, MaterialData material) {
/*  66 */     setRegion(xMin, yMin, zMin, xMax, yMax, zMax, CraftMagicNumbers.getBlock(material));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, BlockData blockData) {
/*  71 */     setRegion(xMin, yMin, zMin, xMax, yMax, zMax, ((CraftBlockData)blockData).getState());
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getType(int x, int y, int z) {
/*  76 */     return getTypeId(x, y, z).getBukkitMaterial();
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialData getTypeAndData(int x, int y, int z) {
/*  81 */     return CraftMagicNumbers.getMaterial(getTypeId(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockData getBlockData(int x, int y, int z) {
/*  86 */     return (BlockData)CraftBlockData.fromData(getTypeId(x, y, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRegion(int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, IBlockData type) {
/*  91 */     if (xMin > 15 || yMin >= this.maxHeight || zMin > 15) {
/*     */       return;
/*     */     }
/*  94 */     if (xMin < 0) {
/*  95 */       xMin = 0;
/*     */     }
/*  97 */     if (yMin < 0) {
/*  98 */       yMin = 0;
/*     */     }
/* 100 */     if (zMin < 0) {
/* 101 */       zMin = 0;
/*     */     }
/* 103 */     if (xMax > 16) {
/* 104 */       xMax = 16;
/*     */     }
/* 106 */     if (yMax > this.maxHeight) {
/* 107 */       yMax = this.maxHeight;
/*     */     }
/* 109 */     if (zMax > 16) {
/* 110 */       zMax = 16;
/*     */     }
/* 112 */     if (xMin >= xMax || yMin >= yMax || zMin >= zMax) {
/*     */       return;
/*     */     }
/* 115 */     for (int y = yMin; y < yMax; y++) {
/* 116 */       ChunkSection section = getChunkSection(y, true);
/* 117 */       int offsetBase = y & 0xF;
/* 118 */       for (int x = xMin; x < xMax; x++) {
/* 119 */         for (int z = zMin; z < zMax; z++) {
/* 120 */           section.setType(x, offsetBase, z, type);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public IBlockData getTypeId(int x, int y, int z) {
/* 127 */     if (x != (x & 0xF) || y < 0 || y >= this.maxHeight || z != (z & 0xF)) {
/* 128 */       return Blocks.AIR.getBlockData();
/*     */     }
/* 130 */     ChunkSection section = getChunkSection(y, false);
/* 131 */     if (section == null) {
/* 132 */       return Blocks.AIR.getBlockData();
/*     */     }
/* 134 */     return section.getType(x, y & 0xF, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getData(int x, int y, int z) {
/* 140 */     return CraftMagicNumbers.toLegacyData(getTypeId(x, y, z));
/*     */   }
/*     */   
/*     */   private void setBlock(int x, int y, int z, IBlockData type) {
/* 144 */     if (x != (x & 0xF) || y < 0 || y >= this.maxHeight || z != (z & 0xF)) {
/*     */       return;
/*     */     }
/* 147 */     ChunkSection section = getChunkSection(y, true);
/* 148 */     section.setType(x, y & 0xF, z, type);
/*     */     
/* 150 */     if (type.getBlock().isTileEntity()) {
/* 151 */       if (this.tiles == null) {
/* 152 */         this.tiles = new HashSet<>();
/*     */       }
/*     */       
/* 155 */       this.tiles.add(new BlockPosition(x, y, z));
/*     */     } 
/*     */   }
/*     */   
/*     */   private ChunkSection getChunkSection(int y, boolean create) {
/* 160 */     ChunkSection section = this.sections[y >> 4];
/* 161 */     if (create && section == null) {
/* 162 */       this.sections[y >> 4] = section = new ChunkSection(y >> 4 << 4, null, (this.world instanceof CraftWorld) ? (World)((CraftWorld)this.world).getHandle() : null, true);
/*     */     }
/* 164 */     return section;
/*     */   }
/*     */   
/*     */   ChunkSection[] getRawChunkData() {
/* 168 */     return this.sections;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRawChunkData(ChunkSection[] sections) {
/* 173 */     this.sections = sections;
/*     */   }
/*     */ 
/*     */   
/*     */   Set<BlockPosition> getTiles() {
/* 178 */     return this.tiles;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\generator\CraftChunkData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */