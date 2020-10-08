/*     */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.v1_16_R2.AxisAlignedBB;
/*     */ import net.minecraft.server.v1_16_R2.BiomeBase;
/*     */ import net.minecraft.server.v1_16_R2.BiomeManager;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.ChunkStatus;
/*     */ import net.minecraft.server.v1_16_R2.DifficultyDamageScaler;
/*     */ import net.minecraft.server.v1_16_R2.DimensionManager;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*     */ import net.minecraft.server.v1_16_R2.Fluid;
/*     */ import net.minecraft.server.v1_16_R2.FluidType;
/*     */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*     */ import net.minecraft.server.v1_16_R2.HeightMap;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IChunkAccess;
/*     */ import net.minecraft.server.v1_16_R2.IChunkProvider;
/*     */ import net.minecraft.server.v1_16_R2.IRegistryCustom;
/*     */ import net.minecraft.server.v1_16_R2.LightEngine;
/*     */ import net.minecraft.server.v1_16_R2.ParticleParam;
/*     */ import net.minecraft.server.v1_16_R2.SoundCategory;
/*     */ import net.minecraft.server.v1_16_R2.SoundEffect;
/*     */ import net.minecraft.server.v1_16_R2.TickList;
/*     */ import net.minecraft.server.v1_16_R2.TickListEmpty;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import net.minecraft.server.v1_16_R2.WorldBorder;
/*     */ import net.minecraft.server.v1_16_R2.WorldData;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ 
/*     */ public class DummyGeneratorAccess implements GeneratorAccess {
/*  37 */   public static final GeneratorAccess INSTANCE = new DummyGeneratorAccess();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TickList<Block> getBlockTickList() {
/*  44 */     return (TickList<Block>)TickListEmpty.b();
/*     */   }
/*     */ 
/*     */   
/*     */   public TickList<FluidType> getFluidTickList() {
/*  49 */     return (TickList<FluidType>)TickListEmpty.b();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldData getWorldData() {
/*  54 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public DifficultyDamageScaler getDamageScaler(BlockPosition blockposition) {
/*  59 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public IChunkProvider getChunkProvider() {
/*  64 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public Random getRandom() {
/*  69 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void playSound(EntityHuman entityhuman, BlockPosition blockposition, SoundEffect soundeffect, SoundCategory soundcategory, float f, float f1) {
/*  74 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void addParticle(ParticleParam particleparam, double d0, double d1, double d2, double d3, double d4, double d5) {
/*  79 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityHuman entityhuman, int i, BlockPosition blockposition, int j) {
/*  84 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldServer getMinecraftWorld() {
/*  89 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public IRegistryCustom r() {
/*  94 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Entity> getEntities(Entity entity, AxisAlignedBB aabb, Predicate<? super Entity> prdct) {
/*  99 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Entity> List<T> a(Class<? extends T> type, AxisAlignedBB aabb, Predicate<? super T> prdct) {
/* 104 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public List<? extends EntityHuman> getPlayers() {
/* 109 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public IChunkAccess getChunkAt(int i, int i1, ChunkStatus cs, boolean bln) {
/* 114 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(HeightMap.Type type, int i, int i1) {
/* 119 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public int c() {
/* 124 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeManager d() {
/* 129 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeBase a(int i, int i1, int i2) {
/* 134 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean s_() {
/* 139 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSeaLevel() {
/* 144 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public DimensionManager getDimensionManager() {
/* 149 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public LightEngine e() {
/* 154 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity getTileEntity(BlockPosition blockposition) {
/* 159 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getType(BlockPosition blockposition) {
/* 164 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid getFluid(BlockPosition blockposition) {
/* 169 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IChunkAccess getChunkIfLoadedImmediately(int x, int z) {
/* 175 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getTypeIfLoaded(BlockPosition blockposition) {
/* 180 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid getFluidIfLoaded(BlockPosition blockposition) {
/* 185 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldBorder getWorldBorder() {
/* 190 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition bp, Predicate<IBlockData> prdct) {
/* 195 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition blockposition, IBlockData iblockdata, int i, int j) {
/* 200 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition blockposition, boolean flag) {
/* 205 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition blockposition, boolean flag, Entity entity, int i) {
/* 210 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\DummyGeneratorAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */