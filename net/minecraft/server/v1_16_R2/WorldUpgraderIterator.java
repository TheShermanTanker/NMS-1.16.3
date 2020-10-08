/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class WorldUpgraderIterator
/*     */ {
/*  36 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   static boolean a(Convertable.ConversionSession var0, IProgressUpdate var1) {
/*     */     WorldChunkManager var14;
/*  40 */     var1.a(0);
/*     */     
/*  42 */     List<File> var2 = Lists.newArrayList();
/*  43 */     List<File> var3 = Lists.newArrayList();
/*  44 */     List<File> var4 = Lists.newArrayList();
/*  45 */     File var5 = var0.a(World.OVERWORLD);
/*  46 */     File var6 = var0.a(World.THE_NETHER);
/*  47 */     File var7 = var0.a(World.THE_END);
/*     */     
/*  49 */     LOGGER.info("Scanning folders...");
/*     */ 
/*     */     
/*  52 */     a(var5, var2);
/*     */ 
/*     */     
/*  55 */     if (var6.exists()) {
/*  56 */       a(var6, var3);
/*     */     }
/*  58 */     if (var7.exists()) {
/*  59 */       a(var7, var4);
/*     */     }
/*     */     
/*  62 */     int var8 = var2.size() + var3.size() + var4.size();
/*  63 */     LOGGER.info("Total conversion count is {}", Integer.valueOf(var8));
/*     */     
/*  65 */     IRegistryCustom.Dimension var9 = IRegistryCustom.b();
/*  66 */     RegistryReadOps<NBTBase> var10 = RegistryReadOps.a(DynamicOpsNBT.a, IResourceManager.Empty.INSTANCE, var9);
/*     */     
/*  68 */     SaveData var11 = var0.a(var10, DataPackConfiguration.a);
/*  69 */     long var12 = (var11 != null) ? var11.getGeneratorSettings().getSeed() : 0L;
/*     */ 
/*     */ 
/*     */     
/*  73 */     IRegistry<BiomeBase> var15 = var9.b(IRegistry.ay);
/*     */     
/*  75 */     if (var11 != null && var11.getGeneratorSettings().isFlatWorld()) {
/*  76 */       var14 = new WorldChunkManagerHell(var15.d(Biomes.PLAINS));
/*     */     } else {
/*  78 */       var14 = new WorldChunkManagerOverworld(var12, false, false, var15);
/*     */     } 
/*     */ 
/*     */     
/*  82 */     a(var9, new File(var5, "region"), var2, var14, 0, var8, var1);
/*     */     
/*  84 */     a(var9, new File(var6, "region"), var3, new WorldChunkManagerHell(var15.d(Biomes.NETHER_WASTES)), var2.size(), var8, var1);
/*     */     
/*  86 */     a(var9, new File(var7, "region"), var4, new WorldChunkManagerHell(var15.d(Biomes.THE_END)), var2.size() + var3.size(), var8, var1);
/*     */     
/*  88 */     a(var0);
/*     */     
/*  90 */     var0.a(var9, var11);
/*  91 */     return true;
/*     */   }
/*     */   
/*     */   private static void a(Convertable.ConversionSession var0) {
/*  95 */     File var1 = var0.getWorldFolder(SavedFile.LEVEL_DAT).toFile();
/*  96 */     if (!var1.exists()) {
/*  97 */       LOGGER.warn("Unable to create level.dat_mcr backup");
/*     */       
/*     */       return;
/*     */     } 
/* 101 */     File var2 = new File(var1.getParent(), "level.dat_mcr");
/* 102 */     if (!var1.renameTo(var2)) {
/* 103 */       LOGGER.warn("Unable to create level.dat_mcr backup");
/*     */     }
/*     */   }
/*     */   
/*     */   private static void a(IRegistryCustom.Dimension var0, File var1, Iterable<File> var2, WorldChunkManager var3, int var4, int var5, IProgressUpdate var6) {
/* 108 */     for (File var8 : var2) {
/* 109 */       a(var0, var1, var8, var3, var4, var5, var6);
/*     */       
/* 111 */       var4++;
/* 112 */       int var9 = (int)Math.round(100.0D * var4 / var5);
/* 113 */       var6.a(var9);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a(IRegistryCustom.Dimension var0, File var1, File var2, WorldChunkManager var3, int var4, int var5, IProgressUpdate var6) {
/* 118 */     String var7 = var2.getName();
/*     */ 
/*     */     
/* 121 */     try(RegionFile var8 = new RegionFile(var2, var1, true); 
/* 122 */         RegionFile var10 = new RegionFile(new File(var1, var7.substring(0, var7.length() - ".mcr".length()) + ".mca"), var1, true)) {
/*     */       
/* 124 */       for (int var12 = 0; var12 < 32; var12++) {
/* 125 */         int var13; for (var13 = 0; var13 < 32; var13++) {
/* 126 */           ChunkCoordIntPair chunkCoordIntPair = new ChunkCoordIntPair(var12, var13);
/* 127 */           if (var8.chunkExists(chunkCoordIntPair) && !var10.chunkExists(chunkCoordIntPair)) {
/*     */             try {
/* 129 */               NBTTagCompound var16; DataInputStream dataInputStream = var8.a(chunkCoordIntPair); Throwable throwable = null;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             }
/* 135 */             catch (IOException var16) {
/* 136 */               LOGGER.warn("Failed to read data for chunk {}", chunkCoordIntPair, var16);
/*     */             } 
/*     */           }
/*     */         } 
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
/* 153 */         var13 = (int)Math.round(100.0D * (var4 * 1024) / (var5 * 1024));
/* 154 */         int var14 = (int)Math.round(100.0D * ((var12 + 1) * 32 + var4 * 1024) / (var5 * 1024));
/* 155 */         if (var14 > var13) {
/* 156 */           var6.a(var14);
/*     */         }
/*     */       } 
/* 159 */     } catch (IOException var8) {
/* 160 */       LOGGER.error("Failed to upgrade region file {}", var2, var8);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a(File var0, Collection<File> var1) {
/* 165 */     File var2 = new File(var0, "region");
/* 166 */     File[] var3 = var2.listFiles((var0, var1) -> var1.endsWith(".mcr"));
/*     */     
/* 168 */     if (var3 != null)
/* 169 */       Collections.addAll(var1, var3); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldUpgraderIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */