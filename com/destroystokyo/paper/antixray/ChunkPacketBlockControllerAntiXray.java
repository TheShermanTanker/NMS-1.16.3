/*     */ package com.destroystokyo.paper.antixray;
/*     */ 
/*     */ import java.util.function.IntSupplier;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.Chunk;
/*     */ import net.minecraft.server.v1_16_R2.ChunkSection;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ 
/*     */ public final class ChunkPacketBlockControllerAntiXray extends ChunkPacketBlockController {
/*     */   private final Executor executor;
/*     */   private final EngineMode engineMode;
/*     */   private final int maxChunkSectionIndex;
/*     */   private final int updateRadius;
/*     */   private final IBlockData[] predefinedBlockData;
/*     */   private final IBlockData[] predefinedBlockDataStone;
/*     */   private final IBlockData[] predefinedBlockDataNetherrack;
/*     */   private final IBlockData[] predefinedBlockDataEndStone;
/*     */   private final int[] predefinedBlockDataBitsGlobal;
/*     */   private final int[] predefinedBlockDataBitsStoneGlobal;
/*     */   private final int[] predefinedBlockDataBitsNetherrackGlobal;
/*     */   private final int[] predefinedBlockDataBitsEndStoneGlobal;
/*     */   private final boolean[] solidGlobal;
/*     */   private final boolean[] obfuscateGlobal;
/*     */   private final ChunkSection[] emptyNearbyChunkSections;
/*     */   private final int maxBlockYUpdatePosition;
/*     */   private final ThreadLocal<int[]> predefinedBlockDataBits;
/*     */   
/*     */   public ChunkPacketBlockControllerAntiXray(World world, Executor executor) { List<String> toObfuscate;
/*  31 */     this.solidGlobal = new boolean[Block.REGISTRY_ID.size()];
/*  32 */     this.obfuscateGlobal = new boolean[Block.REGISTRY_ID.size()];
/*  33 */     this.emptyNearbyChunkSections = new ChunkSection[] { Chunk.EMPTY_CHUNK_SECTION, Chunk.EMPTY_CHUNK_SECTION, Chunk.EMPTY_CHUNK_SECTION, Chunk.EMPTY_CHUNK_SECTION };
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
/* 168 */     this.predefinedBlockDataBits = ThreadLocal.withInitial(() -> new int[getPredefinedBlockDataLength()]); PaperWorldConfig paperWorldConfig = world.paperConfig; this.engineMode = paperWorldConfig.engineMode; this.maxChunkSectionIndex = paperWorldConfig.maxChunkSectionIndex; this.updateRadius = paperWorldConfig.updateRadius; this.executor = executor; if (this.engineMode == EngineMode.HIDE) { toObfuscate = paperWorldConfig.hiddenBlocks; this.predefinedBlockData = null; this.predefinedBlockDataStone = new IBlockData[] { Blocks.STONE.getBlockData() }; this.predefinedBlockDataNetherrack = new IBlockData[] { Blocks.NETHERRACK.getBlockData() }; this.predefinedBlockDataEndStone = new IBlockData[] { Blocks.END_STONE.getBlockData() }; this.predefinedBlockDataBitsGlobal = null; this.predefinedBlockDataBitsStoneGlobal = new int[] { ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(Blocks.STONE.getBlockData()) }; this.predefinedBlockDataBitsNetherrackGlobal = new int[] { ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(Blocks.NETHERRACK.getBlockData()) }; this.predefinedBlockDataBitsEndStoneGlobal = new int[] { ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(Blocks.END_STONE.getBlockData()) }; } else { toObfuscate = new ArrayList<>(paperWorldConfig.replacementBlocks); Set<IBlockData> predefinedBlockDataSet = new HashSet<>(); for (String id : paperWorldConfig.hiddenBlocks) { Block block = IRegistry.BLOCK.getOptional(new MinecraftKey(id)).orElse(null); if (block != null && !block.isTileEntity()) { toObfuscate.add(id); predefinedBlockDataSet.add(block.getBlockData()); }  }  (new IBlockData[1])[0] = Blocks.DIAMOND_ORE.getBlockData(); this.predefinedBlockData = (predefinedBlockDataSet.size() == 0) ? new IBlockData[1] : predefinedBlockDataSet.<IBlockData>toArray(new IBlockData[0]); this.predefinedBlockDataStone = null; this.predefinedBlockDataNetherrack = null; this.predefinedBlockDataEndStone = null; this.predefinedBlockDataBitsGlobal = new int[this.predefinedBlockData.length]; for (int j = 0; j < this.predefinedBlockData.length; j++) this.predefinedBlockDataBitsGlobal[j] = ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(this.predefinedBlockData[j]);  this.predefinedBlockDataBitsStoneGlobal = null; this.predefinedBlockDataBitsNetherrackGlobal = null; this.predefinedBlockDataBitsEndStoneGlobal = null; }  for (String id : toObfuscate) { Block block = IRegistry.BLOCK.getOptional(new MinecraftKey(id)).orElse(null); if (block != null && !block.getBlockData().isAir()) this.obfuscateGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(block.getBlockData())] = true;  }  ChunkEmpty emptyChunk = new ChunkEmpty(world, new ChunkCoordIntPair(0, 0)); BlockPosition zeroPos = new BlockPosition(0, 0, 0); for (int i = 0; i < this.solidGlobal.length; i++) { IBlockData blockData = (IBlockData)ChunkSection.GLOBAL_PALETTE.getObject(i); if (blockData != null) this.solidGlobal[i] = (blockData.isOccluding((IBlockAccess)emptyChunk, zeroPos) && blockData.getBlock() != Blocks.SPAWNER && blockData.getBlock() != Blocks.BARRIER && blockData.getBlock() != Blocks.SHULKER_BOX);  }  this.maxBlockYUpdatePosition = (this.maxChunkSectionIndex + 1) * 16 + this.updateRadius - 1; }
/* 169 */   private int getPredefinedBlockDataLength() { return (this.engineMode == EngineMode.HIDE) ? 1 : this.predefinedBlockData.length; } public IBlockData[] getPredefinedBlockData(World world, IChunkAccess chunk, ChunkSection chunkSection, boolean initializeBlocks) { if (chunkSection.getYPosition() >> 4 <= this.maxChunkSectionIndex) { switch (this.engineMode) { case HIDE: switch (world.getWorld().getEnvironment()) { case HIDE: return this.predefinedBlockDataNetherrack;case null: return this.predefinedBlockDataEndStone; }  return this.predefinedBlockDataStone; }  return this.predefinedBlockData; }  return null; } private static final ThreadLocal<boolean[]> solid = (ThreadLocal)ThreadLocal.withInitial(() -> new boolean[Block.REGISTRY_ID.size()]);
/* 170 */   public ChunkPacketInfoAntiXray getChunkPacketInfo(PacketPlayOutMapChunk packetPlayOutMapChunk, Chunk chunk, int chunkSectionSelector) { ChunkPacketInfoAntiXray chunkPacketInfoAntiXray = new ChunkPacketInfoAntiXray(packetPlayOutMapChunk, chunk, chunkSectionSelector, this); return chunkPacketInfoAntiXray; } public void modifyBlocks(PacketPlayOutMapChunk packetPlayOutMapChunk, ChunkPacketInfo<IBlockData> chunkPacketInfo) { if (!Bukkit.isPrimaryThread()) { MinecraftServer.getServer().scheduleOnMain(() -> modifyBlocks(packetPlayOutMapChunk, chunkPacketInfo)); return; }  Chunk chunk = chunkPacketInfo.getChunk(); int x = (chunk.getPos()).x; int z = (chunk.getPos()).z; WorldServer world = chunk.world; ((ChunkPacketInfoAntiXray)chunkPacketInfo).setNearbyChunks(new Chunk[] { (Chunk)world.getChunkIfLoadedImmediately(x - 1, z), (Chunk)world.getChunkIfLoadedImmediately(x + 1, z), (Chunk)world.getChunkIfLoadedImmediately(x, z - 1), (Chunk)world.getChunkIfLoadedImmediately(x, z + 1) }); this.executor.execute((ChunkPacketInfoAntiXray)chunkPacketInfo); } private static final ThreadLocal<boolean[]> obfuscate = (ThreadLocal)ThreadLocal.withInitial(() -> new boolean[Block.REGISTRY_ID.size()]);
/*     */   
/* 172 */   private static final ThreadLocal<boolean[][]> current = (ThreadLocal)ThreadLocal.withInitial(() -> new boolean[16][16]);
/* 173 */   private static final ThreadLocal<boolean[][]> next = (ThreadLocal)ThreadLocal.withInitial(() -> new boolean[16][16]);
/* 174 */   private static final ThreadLocal<boolean[][]> nextNext = (ThreadLocal)ThreadLocal.withInitial(() -> new boolean[16][16]);
/*     */   
/*     */   public void obfuscate(ChunkPacketInfoAntiXray chunkPacketInfoAntiXray) {
/* 177 */     int[] predefinedBlockDataBits = this.predefinedBlockDataBits.get();
/* 178 */     this; boolean[] solid = ChunkPacketBlockControllerAntiXray.solid.get();
/* 179 */     this; boolean[] obfuscate = ChunkPacketBlockControllerAntiXray.obfuscate.get();
/* 180 */     this; boolean[][] current = ChunkPacketBlockControllerAntiXray.current.get();
/* 181 */     this; boolean[][] next = ChunkPacketBlockControllerAntiXray.next.get();
/* 182 */     this; boolean[][] nextNext = ChunkPacketBlockControllerAntiXray.nextNext.get();
/*     */     
/* 184 */     DataBitsReader dataBitsReader = new DataBitsReader();
/* 185 */     DataBitsWriter dataBitsWriter = new DataBitsWriter();
/* 186 */     ChunkSection[] nearbyChunkSections = new ChunkSection[4];
/* 187 */     boolean[] solidTemp = null;
/* 188 */     boolean[] obfuscateTemp = null;
/* 189 */     dataBitsReader.setDataBits(chunkPacketInfoAntiXray.getData());
/* 190 */     dataBitsWriter.setDataBits(chunkPacketInfoAntiXray.getData());
/* 191 */     final int numberOfBlocks = predefinedBlockDataBits.length;
/*     */     
/* 193 */     IntSupplier random = (numberOfBlocks == 1) ? (() -> 0) : new IntSupplier()
/*     */       {
/*     */         private int state;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public int getAsInt() {
/* 203 */           this.state ^= this.state << 13;
/* 204 */           this.state ^= this.state >>> 17;
/* 205 */           this.state ^= this.state << 5;
/*     */           
/* 207 */           return (int)(Integer.toUnsignedLong(this.state) * numberOfBlocks >>> 32L);
/*     */         }
/*     */       };
/*     */     
/* 211 */     for (int chunkSectionIndex = 0; chunkSectionIndex <= this.maxChunkSectionIndex; chunkSectionIndex++) {
/* 212 */       if (chunkPacketInfoAntiXray.isWritten(chunkSectionIndex) && chunkPacketInfoAntiXray.getPredefinedObjects(chunkSectionIndex) != null) {
/*     */         int[] predefinedBlockDataBitsTemp;
/*     */         
/* 215 */         if (chunkPacketInfoAntiXray.getDataPalette(chunkSectionIndex) == ChunkSection.GLOBAL_PALETTE) {
/* 216 */           predefinedBlockDataBitsTemp = (this.engineMode == EngineMode.HIDE) ? (((chunkPacketInfoAntiXray.getChunk()).world.getWorld().getEnvironment() == World.Environment.NETHER) ? this.predefinedBlockDataBitsNetherrackGlobal : (((chunkPacketInfoAntiXray.getChunk()).world.getWorld().getEnvironment() == World.Environment.THE_END) ? this.predefinedBlockDataBitsEndStoneGlobal : this.predefinedBlockDataBitsStoneGlobal)) : this.predefinedBlockDataBitsGlobal;
/*     */         } else {
/* 218 */           predefinedBlockDataBitsTemp = predefinedBlockDataBits;
/*     */           
/* 220 */           for (int i = 0; i < predefinedBlockDataBitsTemp.length; i++) {
/* 221 */             predefinedBlockDataBitsTemp[i] = chunkPacketInfoAntiXray.getDataPalette(chunkSectionIndex).getOrCreateIdFor(chunkPacketInfoAntiXray.getPredefinedObjects(chunkSectionIndex)[i]);
/*     */           }
/*     */         } 
/*     */         
/* 225 */         dataBitsWriter.setIndex(chunkPacketInfoAntiXray.getDataBitsIndex(chunkSectionIndex));
/*     */ 
/*     */         
/* 228 */         if (chunkSectionIndex == 0 || !chunkPacketInfoAntiXray.isWritten(chunkSectionIndex - 1) || chunkPacketInfoAntiXray.getPredefinedObjects(chunkSectionIndex - 1) == null) {
/*     */           
/* 230 */           dataBitsReader.setBitsPerObject(chunkPacketInfoAntiXray.getBitsPerObject(chunkSectionIndex));
/* 231 */           dataBitsReader.setIndex(chunkPacketInfoAntiXray.getDataBitsIndex(chunkSectionIndex));
/* 232 */           solidTemp = readDataPalette(chunkPacketInfoAntiXray.getDataPalette(chunkSectionIndex), solid, this.solidGlobal);
/* 233 */           obfuscateTemp = readDataPalette(chunkPacketInfoAntiXray.getDataPalette(chunkSectionIndex), obfuscate, this.obfuscateGlobal);
/*     */           
/* 235 */           ChunkSection belowChunkSection = null;
/* 236 */           boolean skipFirstLayer = (chunkSectionIndex == 0 || (belowChunkSection = chunkPacketInfoAntiXray.getChunk().getSections()[chunkSectionIndex - 1]) == Chunk.EMPTY_CHUNK_SECTION);
/*     */           
/* 238 */           for (int z = 0; z < 16; z++) {
/* 239 */             for (int x = 0; x < 16; x++) {
/* 240 */               current[z][x] = true;
/* 241 */               next[z][x] = (skipFirstLayer || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(belowChunkSection.getType(x, 15, z))]);
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 246 */           dataBitsWriter.setBitsPerObject(0);
/* 247 */           obfuscateLayer(-1, dataBitsReader, dataBitsWriter, solidTemp, obfuscateTemp, predefinedBlockDataBitsTemp, current, next, nextNext, this.emptyNearbyChunkSections, random);
/*     */         } 
/*     */         
/* 250 */         dataBitsWriter.setBitsPerObject(chunkPacketInfoAntiXray.getBitsPerObject(chunkSectionIndex));
/* 251 */         nearbyChunkSections[0] = (chunkPacketInfoAntiXray.getNearbyChunks()[0] == null) ? Chunk.EMPTY_CHUNK_SECTION : chunkPacketInfoAntiXray.getNearbyChunks()[0].getSections()[chunkSectionIndex];
/* 252 */         nearbyChunkSections[1] = (chunkPacketInfoAntiXray.getNearbyChunks()[1] == null) ? Chunk.EMPTY_CHUNK_SECTION : chunkPacketInfoAntiXray.getNearbyChunks()[1].getSections()[chunkSectionIndex];
/* 253 */         nearbyChunkSections[2] = (chunkPacketInfoAntiXray.getNearbyChunks()[2] == null) ? Chunk.EMPTY_CHUNK_SECTION : chunkPacketInfoAntiXray.getNearbyChunks()[2].getSections()[chunkSectionIndex];
/* 254 */         nearbyChunkSections[3] = (chunkPacketInfoAntiXray.getNearbyChunks()[3] == null) ? Chunk.EMPTY_CHUNK_SECTION : chunkPacketInfoAntiXray.getNearbyChunks()[3].getSections()[chunkSectionIndex];
/*     */ 
/*     */         
/* 257 */         for (int y = 0; y < 15; y++) {
/* 258 */           boolean[][] temp = current;
/* 259 */           current = next;
/* 260 */           next = nextNext;
/* 261 */           nextNext = temp;
/* 262 */           obfuscateLayer(y, dataBitsReader, dataBitsWriter, solidTemp, obfuscateTemp, predefinedBlockDataBitsTemp, current, next, nextNext, nearbyChunkSections, random);
/*     */         } 
/*     */ 
/*     */         
/* 266 */         if (chunkSectionIndex == this.maxChunkSectionIndex || !chunkPacketInfoAntiXray.isWritten(chunkSectionIndex + 1) || chunkPacketInfoAntiXray.getPredefinedObjects(chunkSectionIndex + 1) == null) {
/*     */           ChunkSection aboveChunkSection;
/*     */ 
/*     */           
/* 270 */           if (chunkSectionIndex != 15 && (aboveChunkSection = chunkPacketInfoAntiXray.getChunk().getSections()[chunkSectionIndex + 1]) != Chunk.EMPTY_CHUNK_SECTION) {
/* 271 */             boolean[][] temp = current;
/* 272 */             current = next;
/* 273 */             next = nextNext;
/* 274 */             nextNext = temp;
/*     */             
/* 276 */             for (int z = 0; z < 16; z++) {
/* 277 */               for (int x = 0; x < 16; x++) {
/* 278 */                 if (!this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(aboveChunkSection.getType(x, 0, z))]) {
/* 279 */                   current[z][x] = true;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */ 
/*     */             
/* 285 */             dataBitsReader.setBitsPerObject(0);
/* 286 */             solid[0] = true;
/* 287 */             obfuscateLayer(15, dataBitsReader, dataBitsWriter, solid, obfuscateTemp, predefinedBlockDataBitsTemp, current, next, nextNext, nearbyChunkSections, random);
/*     */           } 
/*     */         } else {
/*     */           
/* 291 */           dataBitsReader.setBitsPerObject(chunkPacketInfoAntiXray.getBitsPerObject(chunkSectionIndex + 1));
/* 292 */           dataBitsReader.setIndex(chunkPacketInfoAntiXray.getDataBitsIndex(chunkSectionIndex + 1));
/* 293 */           solidTemp = readDataPalette(chunkPacketInfoAntiXray.getDataPalette(chunkSectionIndex + 1), solid, this.solidGlobal);
/* 294 */           obfuscateTemp = readDataPalette(chunkPacketInfoAntiXray.getDataPalette(chunkSectionIndex + 1), obfuscate, this.obfuscateGlobal);
/* 295 */           boolean[][] temp = current;
/* 296 */           current = next;
/* 297 */           next = nextNext;
/* 298 */           nextNext = temp;
/* 299 */           obfuscateLayer(15, dataBitsReader, dataBitsWriter, solidTemp, obfuscateTemp, predefinedBlockDataBitsTemp, current, next, nextNext, nearbyChunkSections, random);
/*     */         } 
/*     */         
/* 302 */         dataBitsWriter.finish();
/*     */       } 
/*     */     } 
/*     */     
/* 306 */     chunkPacketInfoAntiXray.getPacketPlayOutMapChunk().setReady(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private void obfuscateLayer(int y, DataBitsReader dataBitsReader, DataBitsWriter dataBitsWriter, boolean[] solid, boolean[] obfuscate, int[] predefinedBlockDataBits, boolean[][] current, boolean[][] next, boolean[][] nextNext, ChunkSection[] nearbyChunkSections, IntSupplier random) {
/* 311 */     int dataBits = dataBitsReader.read();
/*     */     
/* 313 */     nextNext[0][0] = !solid[dataBits]; if (!solid[dataBits]) {
/* 314 */       dataBitsWriter.skip();
/* 315 */       next[0][1] = true;
/* 316 */       next[1][0] = true;
/*     */     }
/* 318 */     else if (nearbyChunkSections[2] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[2].getType(0, y, 15))] || nearbyChunkSections[0] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[0].getType(15, y, 0))] || current[0][0]) {
/* 319 */       dataBitsWriter.skip();
/*     */     } else {
/* 321 */       dataBitsWriter.write(predefinedBlockDataBits[random.getAsInt()]);
/*     */     } 
/*     */ 
/*     */     
/* 325 */     if (!obfuscate[dataBits]) {
/* 326 */       next[0][0] = true;
/*     */     }
/*     */ 
/*     */     
/* 330 */     for (int i = 1; i < 15; i++) {
/* 331 */       dataBits = dataBitsReader.read();
/*     */       
/* 333 */       nextNext[0][i] = !solid[dataBits]; if (!solid[dataBits]) {
/* 334 */         dataBitsWriter.skip();
/* 335 */         next[0][i - 1] = true;
/* 336 */         next[0][i + 1] = true;
/* 337 */         next[1][i] = true;
/*     */       }
/* 339 */       else if (nearbyChunkSections[2] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[2].getType(i, y, 15))] || current[0][i]) {
/* 340 */         dataBitsWriter.skip();
/*     */       } else {
/* 342 */         dataBitsWriter.write(predefinedBlockDataBits[random.getAsInt()]);
/*     */       } 
/*     */ 
/*     */       
/* 346 */       if (!obfuscate[dataBits]) {
/* 347 */         next[0][i] = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 352 */     dataBits = dataBitsReader.read();
/*     */     
/* 354 */     nextNext[0][15] = !solid[dataBits]; if (!solid[dataBits]) {
/* 355 */       dataBitsWriter.skip();
/* 356 */       next[0][14] = true;
/* 357 */       next[1][15] = true;
/*     */     }
/* 359 */     else if (nearbyChunkSections[2] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[2].getType(15, y, 15))] || nearbyChunkSections[1] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[1].getType(0, y, 0))] || current[0][15]) {
/* 360 */       dataBitsWriter.skip();
/*     */     } else {
/* 362 */       dataBitsWriter.write(predefinedBlockDataBits[random.getAsInt()]);
/*     */     } 
/*     */ 
/*     */     
/* 366 */     if (!obfuscate[dataBits]) {
/* 367 */       next[0][15] = true;
/*     */     }
/*     */ 
/*     */     
/* 371 */     for (int z = 1; z < 15; z++) {
/*     */       
/* 373 */       dataBits = dataBitsReader.read();
/*     */       
/* 375 */       nextNext[z][0] = !solid[dataBits]; if (!solid[dataBits]) {
/* 376 */         dataBitsWriter.skip();
/* 377 */         next[z][1] = true;
/* 378 */         next[z - 1][0] = true;
/* 379 */         next[z + 1][0] = true;
/*     */       }
/* 381 */       else if (nearbyChunkSections[0] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[0].getType(15, y, z))] || current[z][0]) {
/* 382 */         dataBitsWriter.skip();
/*     */       } else {
/* 384 */         dataBitsWriter.write(predefinedBlockDataBits[random.getAsInt()]);
/*     */       } 
/*     */ 
/*     */       
/* 388 */       if (!obfuscate[dataBits]) {
/* 389 */         next[z][0] = true;
/*     */       }
/*     */ 
/*     */       
/* 393 */       for (int j = 1; j < 15; j++) {
/* 394 */         dataBits = dataBitsReader.read();
/*     */         
/* 396 */         nextNext[z][j] = !solid[dataBits]; if (!solid[dataBits]) {
/* 397 */           dataBitsWriter.skip();
/* 398 */           next[z][j - 1] = true;
/* 399 */           next[z][j + 1] = true;
/* 400 */           next[z - 1][j] = true;
/* 401 */           next[z + 1][j] = true;
/*     */         }
/* 403 */         else if (current[z][j]) {
/* 404 */           dataBitsWriter.skip();
/*     */         } else {
/* 406 */           dataBitsWriter.write(predefinedBlockDataBits[random.getAsInt()]);
/*     */         } 
/*     */ 
/*     */         
/* 410 */         if (!obfuscate[dataBits]) {
/* 411 */           next[z][j] = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 416 */       dataBits = dataBitsReader.read();
/*     */       
/* 418 */       nextNext[z][15] = !solid[dataBits]; if (!solid[dataBits]) {
/* 419 */         dataBitsWriter.skip();
/* 420 */         next[z][14] = true;
/* 421 */         next[z - 1][15] = true;
/* 422 */         next[z + 1][15] = true;
/*     */       }
/* 424 */       else if (nearbyChunkSections[1] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[1].getType(0, y, z))] || current[z][15]) {
/* 425 */         dataBitsWriter.skip();
/*     */       } else {
/* 427 */         dataBitsWriter.write(predefinedBlockDataBits[random.getAsInt()]);
/*     */       } 
/*     */ 
/*     */       
/* 431 */       if (!obfuscate[dataBits]) {
/* 432 */         next[z][15] = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 437 */     dataBits = dataBitsReader.read();
/*     */     
/* 439 */     nextNext[15][0] = !solid[dataBits]; if (!solid[dataBits]) {
/* 440 */       dataBitsWriter.skip();
/* 441 */       next[15][1] = true;
/* 442 */       next[14][0] = true;
/*     */     }
/* 444 */     else if (nearbyChunkSections[3] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[3].getType(0, y, 0))] || nearbyChunkSections[0] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[0].getType(15, y, 15))] || current[15][0]) {
/* 445 */       dataBitsWriter.skip();
/*     */     } else {
/* 447 */       dataBitsWriter.write(predefinedBlockDataBits[random.getAsInt()]);
/*     */     } 
/*     */ 
/*     */     
/* 451 */     if (!obfuscate[dataBits]) {
/* 452 */       next[15][0] = true;
/*     */     }
/*     */ 
/*     */     
/* 456 */     for (int x = 1; x < 15; x++) {
/* 457 */       dataBits = dataBitsReader.read();
/*     */       
/* 459 */       nextNext[15][x] = !solid[dataBits]; if (!solid[dataBits]) {
/* 460 */         dataBitsWriter.skip();
/* 461 */         next[15][x - 1] = true;
/* 462 */         next[15][x + 1] = true;
/* 463 */         next[14][x] = true;
/*     */       }
/* 465 */       else if (nearbyChunkSections[3] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[3].getType(x, y, 0))] || current[15][x]) {
/* 466 */         dataBitsWriter.skip();
/*     */       } else {
/* 468 */         dataBitsWriter.write(predefinedBlockDataBits[random.getAsInt()]);
/*     */       } 
/*     */ 
/*     */       
/* 472 */       if (!obfuscate[dataBits]) {
/* 473 */         next[15][x] = true;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 478 */     dataBits = dataBitsReader.read();
/*     */     
/* 480 */     nextNext[15][15] = !solid[dataBits]; if (!solid[dataBits]) {
/* 481 */       dataBitsWriter.skip();
/* 482 */       next[15][14] = true;
/* 483 */       next[14][15] = true;
/*     */     }
/* 485 */     else if (nearbyChunkSections[3] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[3].getType(15, y, 0))] || nearbyChunkSections[1] == Chunk.EMPTY_CHUNK_SECTION || !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(nearbyChunkSections[1].getType(0, y, 15))] || current[15][15]) {
/* 486 */       dataBitsWriter.skip();
/*     */     } else {
/* 488 */       dataBitsWriter.write(predefinedBlockDataBits[random.getAsInt()]);
/*     */     } 
/*     */ 
/*     */     
/* 492 */     if (!obfuscate[dataBits]) {
/* 493 */       next[15][15] = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean[] readDataPalette(DataPalette<IBlockData> dataPalette, boolean[] temp, boolean[] global) {
/* 498 */     if (dataPalette == ChunkSection.GLOBAL_PALETTE) {
/* 499 */       return global;
/*     */     }
/*     */     
/*     */     IBlockData blockData;
/*     */     
/* 504 */     for (int i = 0; (blockData = (IBlockData)dataPalette.getObject(i)) != null; i++) {
/* 505 */       temp[i] = global[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(blockData)];
/*     */     }
/*     */     
/* 508 */     return temp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockChange(World world, BlockPosition blockPosition, IBlockData newBlockData, IBlockData oldBlockData, int flag) {
/* 513 */     if (oldBlockData != null && this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(oldBlockData)] && !this.solidGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(newBlockData)] && blockPosition.getY() <= this.maxBlockYUpdatePosition) {
/* 514 */       updateNearbyBlocks(world, blockPosition);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlayerLeftClickBlock(PlayerInteractManager playerInteractManager, BlockPosition blockPosition, EnumDirection enumDirection) {
/* 520 */     if (blockPosition.getY() <= this.maxBlockYUpdatePosition) {
/* 521 */       updateNearbyBlocks((World)playerInteractManager.world, blockPosition);
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateNearbyBlocks(World world, BlockPosition blockPosition) {
/* 526 */     if (this.updateRadius >= 2) {
/* 527 */       BlockPosition temp = blockPosition.west();
/* 528 */       updateBlock(world, temp);
/* 529 */       updateBlock(world, temp.west());
/* 530 */       updateBlock(world, temp.down());
/* 531 */       updateBlock(world, temp.up());
/* 532 */       updateBlock(world, temp.north());
/* 533 */       updateBlock(world, temp.south());
/* 534 */       updateBlock(world, temp = blockPosition.east());
/* 535 */       updateBlock(world, temp.east());
/* 536 */       updateBlock(world, temp.down());
/* 537 */       updateBlock(world, temp.up());
/* 538 */       updateBlock(world, temp.north());
/* 539 */       updateBlock(world, temp.south());
/* 540 */       updateBlock(world, temp = blockPosition.down());
/* 541 */       updateBlock(world, temp.down());
/* 542 */       updateBlock(world, temp.north());
/* 543 */       updateBlock(world, temp.south());
/* 544 */       updateBlock(world, temp = blockPosition.up());
/* 545 */       updateBlock(world, temp.up());
/* 546 */       updateBlock(world, temp.north());
/* 547 */       updateBlock(world, temp.south());
/* 548 */       updateBlock(world, temp = blockPosition.north());
/* 549 */       updateBlock(world, temp.north());
/* 550 */       updateBlock(world, temp = blockPosition.south());
/* 551 */       updateBlock(world, temp.south());
/* 552 */     } else if (this.updateRadius == 1) {
/* 553 */       updateBlock(world, blockPosition.west());
/* 554 */       updateBlock(world, blockPosition.east());
/* 555 */       updateBlock(world, blockPosition.down());
/* 556 */       updateBlock(world, blockPosition.up());
/* 557 */       updateBlock(world, blockPosition.north());
/* 558 */       updateBlock(world, blockPosition.south());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateBlock(World world, BlockPosition blockPosition) {
/* 565 */     IBlockData blockData = world.getTypeIfLoaded(blockPosition);
/*     */     
/* 567 */     if (blockData != null && this.obfuscateGlobal[ChunkSection.GLOBAL_PALETTE.getOrCreateIdFor(blockData)])
/*     */     {
/* 569 */       ((WorldServer)world).getChunkProvider().flagDirty(blockPosition);
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EngineMode
/*     */   {
/* 575 */     HIDE(1, "hide ores"),
/* 576 */     OBFUSCATE(2, "obfuscate");
/*     */     
/*     */     private final int id;
/*     */     private final String description;
/*     */     
/*     */     EngineMode(int id, String description) {
/* 582 */       this.id = id;
/* 583 */       this.description = description;
/*     */     }
/*     */     
/*     */     public static EngineMode getById(int id) {
/* 587 */       for (EngineMode engineMode : values()) {
/* 588 */         if (engineMode.id == id) {
/* 589 */           return engineMode;
/*     */         }
/*     */       } 
/*     */       
/* 593 */       return null;
/*     */     }
/*     */     
/*     */     public int getId() {
/* 597 */       return this.id;
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 601 */       return this.description;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\antixray\ChunkPacketBlockControllerAntiXray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */