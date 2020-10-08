/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenFossils
/*     */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*     */ {
/*  26 */   private static final MinecraftKey a = new MinecraftKey("fossil/spine_1");
/*  27 */   private static final MinecraftKey ab = new MinecraftKey("fossil/spine_2");
/*  28 */   private static final MinecraftKey ac = new MinecraftKey("fossil/spine_3");
/*  29 */   private static final MinecraftKey ad = new MinecraftKey("fossil/spine_4");
/*     */   
/*  31 */   private static final MinecraftKey ae = new MinecraftKey("fossil/spine_1_coal");
/*  32 */   private static final MinecraftKey af = new MinecraftKey("fossil/spine_2_coal");
/*  33 */   private static final MinecraftKey ag = new MinecraftKey("fossil/spine_3_coal");
/*  34 */   private static final MinecraftKey ah = new MinecraftKey("fossil/spine_4_coal");
/*     */   
/*  36 */   private static final MinecraftKey ai = new MinecraftKey("fossil/skull_1");
/*  37 */   private static final MinecraftKey aj = new MinecraftKey("fossil/skull_2");
/*  38 */   private static final MinecraftKey ak = new MinecraftKey("fossil/skull_3");
/*  39 */   private static final MinecraftKey al = new MinecraftKey("fossil/skull_4");
/*     */   
/*  41 */   private static final MinecraftKey am = new MinecraftKey("fossil/skull_1_coal");
/*  42 */   private static final MinecraftKey an = new MinecraftKey("fossil/skull_2_coal");
/*  43 */   private static final MinecraftKey ao = new MinecraftKey("fossil/skull_3_coal");
/*  44 */   private static final MinecraftKey ap = new MinecraftKey("fossil/skull_4_coal");
/*     */   
/*  46 */   private static final MinecraftKey[] aq = new MinecraftKey[] { a, ab, ac, ad, ai, aj, ak, al };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   private static final MinecraftKey[] ar = new MinecraftKey[] { ae, af, ag, ah, am, an, ao, ap };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenFossils(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/*  69 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/*  74 */     EnumBlockRotation var5 = EnumBlockRotation.a(var2);
/*     */     
/*  76 */     int var6 = var2.nextInt(aq.length);
/*     */ 
/*     */     
/*  79 */     DefinedStructureManager var7 = var0.getMinecraftWorld().getMinecraftServer().getDefinedStructureManager();
/*  80 */     DefinedStructure var8 = var7.a(aq[var6]);
/*  81 */     DefinedStructure var9 = var7.a(ar[var6]);
/*  82 */     ChunkCoordIntPair var10 = new ChunkCoordIntPair(var3);
/*  83 */     StructureBoundingBox var11 = new StructureBoundingBox(var10.d(), 0, var10.e(), var10.f(), 256, var10.g());
/*  84 */     DefinedStructureInfo var12 = (new DefinedStructureInfo()).a(var5).a(var11).a(var2).a(DefinedStructureProcessorBlockIgnore.d);
/*     */     
/*  86 */     BlockPosition var13 = var8.a(var5);
/*  87 */     int var14 = var2.nextInt(16 - var13.getX());
/*  88 */     int var15 = var2.nextInt(16 - var13.getZ());
/*     */     
/*  90 */     int var16 = 256; int var17;
/*  91 */     for (var17 = 0; var17 < var13.getX(); var17++) {
/*  92 */       for (int i = 0; i < var13.getZ(); i++) {
/*  93 */         var16 = Math.min(var16, var0.a(HeightMap.Type.OCEAN_FLOOR_WG, var3.getX() + var17 + var14, var3.getZ() + i + var15));
/*     */       }
/*     */     } 
/*  96 */     var17 = Math.max(var16 - 15 - var2.nextInt(10), 10);
/*     */     
/*  98 */     BlockPosition var18 = var8.a(var3.b(var14, var17, var15), EnumBlockMirror.NONE, var5);
/*     */     
/* 100 */     DefinedStructureProcessorRotation var19 = new DefinedStructureProcessorRotation(0.9F);
/* 101 */     var12.b().a(var19);
/* 102 */     var8.a(var0, var18, var18, var12, var2, 4);
/* 103 */     var12.b(var19);
/*     */     
/* 105 */     DefinedStructureProcessorRotation var20 = new DefinedStructureProcessorRotation(0.1F);
/* 106 */     var12.b().a(var20);
/* 107 */     var9.a(var0, var18, var18, var12, var2, 4);
/*     */     
/* 109 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFossils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */