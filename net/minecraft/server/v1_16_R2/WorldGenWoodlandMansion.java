/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenWoodlandMansion
/*     */   extends StructureGenerator<WorldGenFeatureEmptyConfiguration>
/*     */ {
/*     */   public WorldGenWoodlandMansion(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/*  32 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b() {
/*  37 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(ChunkGenerator var0, WorldChunkManager var1, long var2, SeededRandom var4, int var5, int var6, BiomeBase var7, ChunkCoordIntPair var8, WorldGenFeatureEmptyConfiguration var9) {
/*  43 */     Set<BiomeBase> var10 = var1.a(var5 * 16 + 9, var0.getSeaLevel(), var6 * 16 + 9, 32);
/*  44 */     for (BiomeBase var12 : var10) {
/*  45 */       if (!var12.e().a(this)) {
/*  46 */         return false;
/*     */       }
/*     */     } 
/*  49 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructureGenerator.a<WorldGenFeatureEmptyConfiguration> a() {
/*  54 */     return a::new;
/*     */   }
/*     */   
/*     */   public static class a extends StructureStart<WorldGenFeatureEmptyConfiguration> {
/*     */     public a(StructureGenerator<WorldGenFeatureEmptyConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/*  59 */       super(var0, var1, var2, var3, var4, var5);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureEmptyConfiguration var6) {
/*  64 */       EnumBlockRotation var7 = EnumBlockRotation.a(this.d);
/*     */       
/*  66 */       int var8 = 5;
/*  67 */       int var9 = 5;
/*  68 */       if (var7 == EnumBlockRotation.CLOCKWISE_90) {
/*  69 */         var8 = -5;
/*  70 */       } else if (var7 == EnumBlockRotation.CLOCKWISE_180) {
/*  71 */         var8 = -5;
/*  72 */         var9 = -5;
/*  73 */       } else if (var7 == EnumBlockRotation.COUNTERCLOCKWISE_90) {
/*  74 */         var9 = -5;
/*     */       } 
/*     */       
/*  77 */       int var10 = (var3 << 4) + 7;
/*  78 */       int var11 = (var4 << 4) + 7;
/*  79 */       int var12 = var1.c(var10, var11, HeightMap.Type.WORLD_SURFACE_WG);
/*  80 */       int var13 = var1.c(var10, var11 + var9, HeightMap.Type.WORLD_SURFACE_WG);
/*  81 */       int var14 = var1.c(var10 + var8, var11, HeightMap.Type.WORLD_SURFACE_WG);
/*  82 */       int var15 = var1.c(var10 + var8, var11 + var9, HeightMap.Type.WORLD_SURFACE_WG);
/*     */       
/*  84 */       int var16 = Math.min(Math.min(var12, var13), Math.min(var14, var15));
/*     */ 
/*     */ 
/*     */       
/*  88 */       if (var16 < 60) {
/*     */         return;
/*     */       }
/*     */       
/*  92 */       BlockPosition var17 = new BlockPosition(var3 * 16 + 8, var16 + 1, var4 * 16 + 8);
/*  93 */       List<WorldGenWoodlandMansionPieces.i> var18 = Lists.newLinkedList();
/*  94 */       WorldGenWoodlandMansionPieces.a(var2, var17, var7, var18, this.d);
/*  95 */       this.b.addAll((Collection)var18);
/*     */       
/*  97 */       b();
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5) {
/* 102 */       super.a(var0, var1, var2, var3, var4, var5);
/*     */ 
/*     */       
/* 105 */       int var6 = this.c.b;
/* 106 */       for (int var7 = var4.a; var7 <= var4.d; var7++) {
/* 107 */         for (int var8 = var4.c; var8 <= var4.f; var8++) {
/* 108 */           BlockPosition var9 = new BlockPosition(var7, var6, var8);
/* 109 */           if (!var0.isEmpty(var9) && this.c.b(var9)) {
/*     */             
/* 111 */             boolean var10 = false;
/* 112 */             for (StructurePiece var12 : this.b) {
/* 113 */               if (var12.g().b(var9)) {
/* 114 */                 var10 = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 118 */             if (var10)
/*     */             {
/*     */               
/* 121 */               for (int var11 = var6 - 1; var11 > 1; ) {
/* 122 */                 BlockPosition var12 = new BlockPosition(var7, var11, var8);
/* 123 */                 if (var0.isEmpty(var12) || var0.getType(var12).getMaterial().isLiquid()) {
/* 124 */                   var0.setTypeAndData(var12, Blocks.COBBLESTONE.getBlockData(), 2);
/*     */                   var11--;
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenWoodlandMansion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */