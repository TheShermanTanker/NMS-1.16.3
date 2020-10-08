/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class WorldGenWitchHut extends WorldGenScatteredPiece {
/*     */   private boolean e;
/*     */   private boolean f;
/*     */   
/*     */   public WorldGenWitchHut(Random random, int i, int j) {
/*  11 */     super(WorldGenFeatureStructurePieceType.K, random, i, 64, j, 7, 7, 9);
/*     */   }
/*     */   
/*     */   public WorldGenWitchHut(DefinedStructureManager definedstructuremanager, NBTTagCompound nbttagcompound) {
/*  15 */     super(WorldGenFeatureStructurePieceType.K, nbttagcompound);
/*  16 */     this.e = nbttagcompound.getBoolean("Witch");
/*  17 */     this.f = nbttagcompound.getBoolean("Cat");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound nbttagcompound) {
/*  22 */     super.a(nbttagcompound);
/*  23 */     nbttagcompound.setBoolean("Witch", this.e);
/*  24 */     nbttagcompound.setBoolean("Cat", this.f);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed generatoraccessseed, StructureManager structuremanager, ChunkGenerator chunkgenerator, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair, BlockPosition blockposition) {
/*  29 */     if (!a(generatoraccessseed, structureboundingbox, 0)) {
/*  30 */       return false;
/*     */     }
/*  32 */     a(generatoraccessseed, structureboundingbox, 1, 1, 1, 5, 1, 7, Blocks.SPRUCE_PLANKS.getBlockData(), Blocks.SPRUCE_PLANKS.getBlockData(), false);
/*  33 */     a(generatoraccessseed, structureboundingbox, 1, 4, 2, 5, 4, 7, Blocks.SPRUCE_PLANKS.getBlockData(), Blocks.SPRUCE_PLANKS.getBlockData(), false);
/*  34 */     a(generatoraccessseed, structureboundingbox, 2, 1, 0, 4, 1, 0, Blocks.SPRUCE_PLANKS.getBlockData(), Blocks.SPRUCE_PLANKS.getBlockData(), false);
/*  35 */     a(generatoraccessseed, structureboundingbox, 2, 2, 2, 3, 3, 2, Blocks.SPRUCE_PLANKS.getBlockData(), Blocks.SPRUCE_PLANKS.getBlockData(), false);
/*  36 */     a(generatoraccessseed, structureboundingbox, 1, 2, 3, 1, 3, 6, Blocks.SPRUCE_PLANKS.getBlockData(), Blocks.SPRUCE_PLANKS.getBlockData(), false);
/*  37 */     a(generatoraccessseed, structureboundingbox, 5, 2, 3, 5, 3, 6, Blocks.SPRUCE_PLANKS.getBlockData(), Blocks.SPRUCE_PLANKS.getBlockData(), false);
/*  38 */     a(generatoraccessseed, structureboundingbox, 2, 2, 7, 4, 3, 7, Blocks.SPRUCE_PLANKS.getBlockData(), Blocks.SPRUCE_PLANKS.getBlockData(), false);
/*  39 */     a(generatoraccessseed, structureboundingbox, 1, 0, 2, 1, 3, 2, Blocks.OAK_LOG.getBlockData(), Blocks.OAK_LOG.getBlockData(), false);
/*  40 */     a(generatoraccessseed, structureboundingbox, 5, 0, 2, 5, 3, 2, Blocks.OAK_LOG.getBlockData(), Blocks.OAK_LOG.getBlockData(), false);
/*  41 */     a(generatoraccessseed, structureboundingbox, 1, 0, 7, 1, 3, 7, Blocks.OAK_LOG.getBlockData(), Blocks.OAK_LOG.getBlockData(), false);
/*  42 */     a(generatoraccessseed, structureboundingbox, 5, 0, 7, 5, 3, 7, Blocks.OAK_LOG.getBlockData(), Blocks.OAK_LOG.getBlockData(), false);
/*  43 */     a(generatoraccessseed, Blocks.OAK_FENCE.getBlockData(), 2, 3, 2, structureboundingbox);
/*  44 */     a(generatoraccessseed, Blocks.OAK_FENCE.getBlockData(), 3, 3, 7, structureboundingbox);
/*  45 */     a(generatoraccessseed, Blocks.AIR.getBlockData(), 1, 3, 4, structureboundingbox);
/*  46 */     a(generatoraccessseed, Blocks.AIR.getBlockData(), 5, 3, 4, structureboundingbox);
/*  47 */     a(generatoraccessseed, Blocks.AIR.getBlockData(), 5, 3, 5, structureboundingbox);
/*  48 */     a(generatoraccessseed, Blocks.POTTED_RED_MUSHROOM.getBlockData(), 1, 3, 5, structureboundingbox);
/*  49 */     a(generatoraccessseed, Blocks.CRAFTING_TABLE.getBlockData(), 3, 2, 6, structureboundingbox);
/*  50 */     a(generatoraccessseed, Blocks.CAULDRON.getBlockData(), 4, 2, 6, structureboundingbox);
/*  51 */     a(generatoraccessseed, Blocks.OAK_FENCE.getBlockData(), 1, 2, 1, structureboundingbox);
/*  52 */     a(generatoraccessseed, Blocks.OAK_FENCE.getBlockData(), 5, 2, 1, structureboundingbox);
/*  53 */     IBlockData iblockdata = Blocks.SPRUCE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.NORTH);
/*  54 */     IBlockData iblockdata1 = Blocks.SPRUCE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.EAST);
/*  55 */     IBlockData iblockdata2 = Blocks.SPRUCE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.WEST);
/*  56 */     IBlockData iblockdata3 = Blocks.SPRUCE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.SOUTH);
/*     */     
/*  58 */     a(generatoraccessseed, structureboundingbox, 0, 4, 1, 6, 4, 1, iblockdata, iblockdata, false);
/*  59 */     a(generatoraccessseed, structureboundingbox, 0, 4, 2, 0, 4, 7, iblockdata1, iblockdata1, false);
/*  60 */     a(generatoraccessseed, structureboundingbox, 6, 4, 2, 6, 4, 7, iblockdata2, iblockdata2, false);
/*  61 */     a(generatoraccessseed, structureboundingbox, 0, 4, 8, 6, 4, 8, iblockdata3, iblockdata3, false);
/*  62 */     a(generatoraccessseed, iblockdata.set(BlockStairs.SHAPE, BlockPropertyStairsShape.OUTER_RIGHT), 0, 4, 1, structureboundingbox);
/*  63 */     a(generatoraccessseed, iblockdata.set(BlockStairs.SHAPE, BlockPropertyStairsShape.OUTER_LEFT), 6, 4, 1, structureboundingbox);
/*  64 */     a(generatoraccessseed, iblockdata3.set(BlockStairs.SHAPE, BlockPropertyStairsShape.OUTER_LEFT), 0, 4, 8, structureboundingbox);
/*  65 */     a(generatoraccessseed, iblockdata3.set(BlockStairs.SHAPE, BlockPropertyStairsShape.OUTER_RIGHT), 6, 4, 8, structureboundingbox);
/*     */ 
/*     */     
/*     */     int j;
/*     */     
/*  70 */     for (j = 2; j <= 7; j += 5) {
/*  71 */       for (int m = 1; m <= 5; m += 4) {
/*  72 */         b(generatoraccessseed, Blocks.OAK_LOG.getBlockData(), m, -1, j, structureboundingbox);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  77 */     j = a(2, 5);
/*  78 */     int i = d(2);
/*  79 */     int k = b(2, 5);
/*     */     
/*  81 */     if (!this.e && structureboundingbox.b(new BlockPosition(j, i, k))) {
/*  82 */       this.e = true;
/*  83 */       EntityWitch entitywitch = EntityTypes.WITCH.a(generatoraccessseed.getMinecraftWorld());
/*     */       
/*  85 */       entitywitch.setPersistent();
/*  86 */       entitywitch.setPositionRotation(j + 0.5D, i, k + 0.5D, 0.0F, 0.0F);
/*  87 */       entitywitch.prepare(generatoraccessseed, generatoraccessseed.getDamageScaler(new BlockPosition(j, i, k)), EnumMobSpawn.STRUCTURE, (GroupDataEntity)null, (NBTTagCompound)null);
/*  88 */       generatoraccessseed.addAllEntities(entitywitch, CreatureSpawnEvent.SpawnReason.CHUNK_GEN);
/*     */     } 
/*     */ 
/*     */     
/*  92 */     a(generatoraccessseed, structureboundingbox);
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(WorldAccess worldaccess, StructureBoundingBox structureboundingbox) {
/*  99 */     int i = a(2, 5);
/* 100 */     int j = d(2);
/* 101 */     int k = b(2, 5);
/*     */     
/* 103 */     if (!this.f && structureboundingbox.b(new BlockPosition(i, j, k))) {
/* 104 */       this.f = true;
/* 105 */       EntityCat entitycat = EntityTypes.CAT.a(worldaccess.getMinecraftWorld());
/*     */       
/* 107 */       entitycat.setPersistent();
/* 108 */       entitycat.setPositionRotation(i + 0.5D, j, k + 0.5D, 0.0F, 0.0F);
/* 109 */       entitycat.prepare(worldaccess, worldaccess.getDamageScaler(new BlockPosition(i, j, k)), EnumMobSpawn.STRUCTURE, (GroupDataEntity)null, (NBTTagCompound)null);
/* 110 */       worldaccess.addAllEntities(entitycat, CreatureSpawnEvent.SpawnReason.CHUNK_GEN);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenWitchHut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */