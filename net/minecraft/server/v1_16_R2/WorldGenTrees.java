/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.OptionalInt;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenTrees
/*     */   extends WorldGenerator<WorldGenFeatureTreeConfiguration>
/*     */ {
/*     */   public WorldGenTrees(Codec<WorldGenFeatureTreeConfiguration> var0) {
/*  39 */     super(var0);
/*     */   }
/*     */   
/*     */   public static boolean c(VirtualLevelReadable var0, BlockPosition var1) {
/*  43 */     return (e(var0, var1) || var0.a(var1, var0 -> var0.a(TagsBlock.LOGS)));
/*     */   }
/*     */   
/*     */   private static boolean f(VirtualLevelReadable var0, BlockPosition var1) {
/*  47 */     return var0.a(var1, var0 -> var0.a(Blocks.VINE));
/*     */   }
/*     */   
/*     */   private static boolean g(VirtualLevelReadable var0, BlockPosition var1) {
/*  51 */     return var0.a(var1, var0 -> var0.a(Blocks.WATER));
/*     */   }
/*     */   
/*     */   public static boolean d(VirtualLevelReadable var0, BlockPosition var1) {
/*  55 */     return var0.a(var1, var0 -> (var0.isAir() || var0.a(TagsBlock.LEAVES)));
/*     */   }
/*     */   
/*     */   private static boolean h(VirtualLevelReadable var0, BlockPosition var1) {
/*  59 */     return var0.a(var1, var0 -> {
/*     */           Block var1 = var0.getBlock();
/*  61 */           return (b(var1) || var1 == Blocks.FARMLAND);
/*     */         });
/*     */   }
/*     */   
/*     */   private static boolean i(VirtualLevelReadable var0, BlockPosition var1) {
/*  66 */     return var0.a(var1, var0 -> {
/*     */           Material var1 = var0.getMaterial();
/*     */           return (var1 == Material.REPLACEABLE_PLANT);
/*     */         });
/*     */   }
/*     */   
/*     */   public static void b(IWorldWriter var0, BlockPosition var1, IBlockData var2) {
/*  73 */     var0.setTypeAndData(var1, var2, 19);
/*     */   }
/*     */   
/*     */   public static boolean e(VirtualLevelReadable var0, BlockPosition var1) {
/*  77 */     return (d(var0, var1) || i(var0, var1) || g(var0, var1));
/*     */   }
/*     */   private boolean a(VirtualLevelWritable var0, Random var1, BlockPosition var2, Set<BlockPosition> var3, Set<BlockPosition> var4, StructureBoundingBox var5, WorldGenFeatureTreeConfiguration var6) {
/*     */     BlockPosition var11;
/*  81 */     int var7 = var6.g.a(var1);
/*  82 */     int var8 = var6.f.a(var1, var7, var6);
/*  83 */     int var9 = var7 - var8;
/*     */     
/*  85 */     int var10 = var6.f.a(var1, var9);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     if (!var6.e) {
/*  91 */       int k, i = var0.getHighestBlockYAt(HeightMap.Type.OCEAN_FLOOR, var2).getY();
/*  92 */       int j = var0.getHighestBlockYAt(HeightMap.Type.WORLD_SURFACE, var2).getY();
/*     */       
/*  94 */       if (j - i > var6.i) {
/*  95 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  99 */       if (var6.l == HeightMap.Type.OCEAN_FLOOR) {
/* 100 */         k = i;
/* 101 */       } else if (var6.l == HeightMap.Type.WORLD_SURFACE) {
/* 102 */         k = j;
/*     */       } else {
/* 104 */         k = var0.getHighestBlockYAt(var6.l, var2).getY();
/*     */       } 
/*     */       
/* 107 */       var11 = new BlockPosition(var2.getX(), k, var2.getZ());
/*     */     } else {
/* 109 */       var11 = var2;
/*     */     } 
/*     */     
/* 112 */     if (var11.getY() < 1 || var11.getY() + var7 + 1 > 256) {
/* 113 */       return false;
/*     */     }
/*     */     
/* 116 */     if (!h(var0, var11.down())) {
/* 117 */       return false;
/*     */     }
/*     */     
/* 120 */     OptionalInt var12 = var6.h.c();
/*     */     
/* 122 */     int var13 = a(var0, var7, var11, var6);
/* 123 */     if (var13 < var7 && (!var12.isPresent() || var13 < var12.getAsInt())) {
/* 124 */       return false;
/*     */     }
/*     */     
/* 127 */     List<WorldGenFoilagePlacer.b> var14 = var6.g.a(var0, var1, var13, var11, var3, var5, var6);
/* 128 */     var14.forEach(var8 -> var0.f.a(var1, var2, var0, var3, var8, var4, var5, var6, var7));
/*     */ 
/*     */     
/* 131 */     return true;
/*     */   }
/*     */   
/*     */   private int a(VirtualLevelReadable var0, int var1, BlockPosition var2, WorldGenFeatureTreeConfiguration var3) {
/* 135 */     BlockPosition.MutableBlockPosition var4 = new BlockPosition.MutableBlockPosition();
/*     */     
/* 137 */     for (int var5 = 0; var5 <= var1 + 1; var5++) {
/* 138 */       int var6 = var3.h.a(var1, var5);
/* 139 */       for (int var7 = -var6; var7 <= var6; var7++) {
/* 140 */         for (int var8 = -var6; var8 <= var6; var8++) {
/* 141 */           var4.a(var2, var7, var5, var8);
/* 142 */           if (!c(var0, var4) || (!var3.j && f(var0, var4))) {
/* 143 */             return var5 - 2;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 149 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(IWorldWriter var0, BlockPosition var1, IBlockData var2) {
/* 154 */     b(var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureTreeConfiguration var4) {
/* 159 */     Set<BlockPosition> var5 = Sets.newHashSet();
/* 160 */     Set<BlockPosition> var6 = Sets.newHashSet();
/* 161 */     Set<BlockPosition> var7 = Sets.newHashSet();
/* 162 */     StructureBoundingBox var8 = StructureBoundingBox.a();
/* 163 */     boolean var9 = a(var0, var2, var3, var5, var6, var8, var4);
/*     */     
/* 165 */     if (var8.a > var8.d || !var9 || var5.isEmpty()) {
/* 166 */       return false;
/*     */     }
/*     */     
/* 169 */     if (!var4.d.isEmpty()) {
/*     */       
/* 171 */       List<BlockPosition> list1 = Lists.newArrayList(var5);
/* 172 */       List<BlockPosition> var11 = Lists.newArrayList(var6);
/* 173 */       list1.sort(Comparator.comparingInt(BaseBlockPosition::getY));
/* 174 */       var11.sort(Comparator.comparingInt(BaseBlockPosition::getY));
/* 175 */       var4.d.forEach(var6 -> var6.a(var0, var1, var2, var3, var4, var5));
/*     */     } 
/*     */     
/* 178 */     VoxelShapeDiscrete var10 = a(var0, var8, var5, var7);
/* 179 */     DefinedStructure.a(var0, 3, var10, var8.a, var8.b, var8.c);
/*     */     
/* 181 */     return true;
/*     */   }
/*     */   
/*     */   private VoxelShapeDiscrete a(GeneratorAccess var0, StructureBoundingBox var1, Set<BlockPosition> var2, Set<BlockPosition> var3) {
/* 185 */     List<Set<BlockPosition>> var4 = Lists.newArrayList();
/* 186 */     VoxelShapeDiscrete var5 = new VoxelShapeBitSet(var1.d(), var1.e(), var1.f());
/* 187 */     int var6 = 6;
/* 188 */     for (int i = 0; i < 6; i++) {
/* 189 */       var4.add(Sets.newHashSet());
/*     */     }
/*     */     
/* 192 */     BlockPosition.MutableBlockPosition var7 = new BlockPosition.MutableBlockPosition();
/* 193 */     for (BlockPosition var9 : Lists.newArrayList(var3)) {
/* 194 */       if (var1.b(var9)) {
/* 195 */         var5.a(var9.getX() - var1.a, var9.getY() - var1.b, var9.getZ() - var1.c, true, true);
/*     */       }
/*     */     } 
/*     */     
/* 199 */     for (BlockPosition var9 : Lists.newArrayList(var2)) {
/* 200 */       if (var1.b(var9)) {
/* 201 */         var5.a(var9.getX() - var1.a, var9.getY() - var1.b, var9.getZ() - var1.c, true, true);
/*     */       }
/* 203 */       for (EnumDirection var13 : EnumDirection.values()) {
/* 204 */         var7.a(var9, var13);
/* 205 */         if (!var2.contains(var7)) {
/* 206 */           IBlockData var14 = var0.getType(var7);
/* 207 */           if (var14.b(BlockProperties.an)) {
/* 208 */             ((Set<BlockPosition>)var4.get(0)).add(var7.immutableCopy());
/* 209 */             b(var0, var7, var14.set(BlockProperties.an, Integer.valueOf(1)));
/* 210 */             if (var1.b(var7)) {
/* 211 */               var5.a(var7.getX() - var1.a, var7.getY() - var1.b, var7.getZ() - var1.c, true, true);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 217 */     for (int var8 = 1; var8 < 6; var8++) {
/* 218 */       Set<BlockPosition> var9 = var4.get(var8 - 1);
/* 219 */       Set<BlockPosition> var10 = var4.get(var8);
/* 220 */       for (BlockPosition var12 : var9) {
/* 221 */         if (var1.b(var12)) {
/* 222 */           var5.a(var12.getX() - var1.a, var12.getY() - var1.b, var12.getZ() - var1.c, true, true);
/*     */         }
/* 224 */         for (EnumDirection var16 : EnumDirection.values()) {
/* 225 */           var7.a(var12, var16);
/* 226 */           if (!var9.contains(var7) && !var10.contains(var7)) {
/*     */ 
/*     */             
/* 229 */             IBlockData var17 = var0.getType(var7);
/* 230 */             if (var17.b(BlockProperties.an)) {
/*     */ 
/*     */               
/* 233 */               int var18 = ((Integer)var17.get(BlockProperties.an)).intValue();
/* 234 */               if (var18 > var8 + 1)
/* 235 */               { IBlockData var19 = var17.set(BlockProperties.an, Integer.valueOf(var8 + 1));
/* 236 */                 b(var0, var7, var19);
/* 237 */                 if (var1.b(var7)) {
/* 238 */                   var5.a(var7.getX() - var1.a, var7.getY() - var1.b, var7.getZ() - var1.c, true, true);
/*     */                 }
/* 240 */                 var10.add(var7.immutableCopy()); } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 245 */     }  return var5;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */