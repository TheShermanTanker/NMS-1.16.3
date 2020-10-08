/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Random;
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
/*     */ public class WorldGenDungeons
/*     */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*     */ {
/*  26 */   private static final Logger LOGGER = LogManager.getLogger();
/*  27 */   private static final EntityTypes<?>[] ab = new EntityTypes[] { EntityTypes.SKELETON, EntityTypes.ZOMBIE, EntityTypes.ZOMBIE, EntityTypes.SPIDER };
/*  28 */   private static final IBlockData ac = Blocks.CAVE_AIR.getBlockData();
/*     */   
/*     */   public WorldGenDungeons(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/*  31 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/*  36 */     int var5 = 3;
/*  37 */     int var6 = var2.nextInt(2) + 2;
/*  38 */     int var7 = -var6 - 1;
/*  39 */     int var8 = var6 + 1;
/*     */     
/*  41 */     int var9 = -1;
/*  42 */     int var10 = 4;
/*     */     
/*  44 */     int var11 = var2.nextInt(2) + 2;
/*  45 */     int var12 = -var11 - 1;
/*  46 */     int var13 = var11 + 1;
/*     */     
/*  48 */     int var14 = 0; int i;
/*  49 */     for (i = var7; i <= var8; i++) {
/*  50 */       for (int var16 = -1; var16 <= 4; var16++) {
/*  51 */         for (int var17 = var12; var17 <= var13; var17++) {
/*  52 */           BlockPosition var18 = var3.b(i, var16, var17);
/*  53 */           Material var19 = var0.getType(var18).getMaterial();
/*  54 */           boolean var20 = var19.isBuildable();
/*     */           
/*  56 */           if (var16 == -1 && !var20) {
/*  57 */             return false;
/*     */           }
/*  59 */           if (var16 == 4 && !var20) {
/*  60 */             return false;
/*     */           }
/*     */           
/*  63 */           if ((i == var7 || i == var8 || var17 == var12 || var17 == var13) && 
/*  64 */             var16 == 0 && var0.isEmpty(var18) && var0.isEmpty(var18.up())) {
/*  65 */             var14++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  72 */     if (var14 < 1 || var14 > 5) {
/*  73 */       return false;
/*     */     }
/*     */     
/*  76 */     for (i = var7; i <= var8; i++) {
/*  77 */       for (int var16 = 3; var16 >= -1; var16--) {
/*  78 */         for (int var17 = var12; var17 <= var13; var17++) {
/*  79 */           BlockPosition var18 = var3.b(i, var16, var17);
/*     */           
/*  81 */           IBlockData var19 = var0.getType(var18);
/*  82 */           if (i == var7 || var16 == -1 || var17 == var12 || i == var8 || var16 == 4 || var17 == var13) {
/*  83 */             if (var18.getY() >= 0 && !var0.getType(var18.down()).getMaterial().isBuildable()) {
/*  84 */               var0.setTypeAndData(var18, ac, 2);
/*  85 */             } else if (var19.getMaterial().isBuildable() && 
/*  86 */               !var19.a(Blocks.CHEST)) {
/*  87 */               if (var16 == -1 && var2.nextInt(4) != 0) {
/*  88 */                 var0.setTypeAndData(var18, Blocks.MOSSY_COBBLESTONE.getBlockData(), 2);
/*     */               } else {
/*  90 */                 var0.setTypeAndData(var18, Blocks.COBBLESTONE.getBlockData(), 2);
/*     */               }
/*     */             
/*     */             }
/*     */           
/*  95 */           } else if (!var19.a(Blocks.CHEST) && !var19.a(Blocks.SPAWNER)) {
/*  96 */             var0.setTypeAndData(var18, ac, 2);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 103 */     for (i = 0; i < 2; i++) {
/* 104 */       for (int var16 = 0; var16 < 3; var16++) {
/* 105 */         int var17 = var3.getX() + var2.nextInt(var6 * 2 + 1) - var6;
/* 106 */         int var18 = var3.getY();
/* 107 */         int var19 = var3.getZ() + var2.nextInt(var11 * 2 + 1) - var11;
/* 108 */         BlockPosition var20 = new BlockPosition(var17, var18, var19);
/*     */         
/* 110 */         if (var0.isEmpty(var20)) {
/*     */ 
/*     */ 
/*     */           
/* 114 */           int var21 = 0;
/* 115 */           for (EnumDirection var23 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 116 */             if (var0.getType(var20.shift(var23)).getMaterial().isBuildable()) {
/* 117 */               var21++;
/*     */             }
/*     */           } 
/*     */           
/* 121 */           if (var21 == 1) {
/*     */ 
/*     */ 
/*     */             
/* 125 */             var0.setTypeAndData(var20, StructurePiece.a(var0, var20, Blocks.CHEST.getBlockData()), 2);
/* 126 */             TileEntityLootable.a(var0, var2, var20, LootTables.d);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 132 */     var0.setTypeAndData(var3, Blocks.SPAWNER.getBlockData(), 2);
/* 133 */     TileEntity var15 = var0.getTileEntity(var3);
/*     */     
/* 135 */     if (var15 instanceof TileEntityMobSpawner) {
/* 136 */       ((TileEntityMobSpawner)var15).getSpawner().setMobName(a(var2));
/*     */     } else {
/* 138 */       LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", Integer.valueOf(var3.getX()), Integer.valueOf(var3.getY()), Integer.valueOf(var3.getZ()));
/*     */     } 
/*     */     
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   private EntityTypes<?> a(Random var0) {
/* 145 */     return SystemUtils.<EntityTypes>a((EntityTypes[])ab, var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDungeons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */