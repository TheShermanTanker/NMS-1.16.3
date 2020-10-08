/*      */ package net.minecraft.server.v1_16_R2;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import javax.annotation.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WorldGenNetherPieces
/*      */ {
/*      */   static class WorldGenNetherPieceWeight
/*      */   {
/*      */     public final Class<? extends WorldGenNetherPieces.WorldGenNetherPiece> a;
/*      */     public final int b;
/*      */     public int c;
/*      */     public final int d;
/*      */     public final boolean e;
/*      */     
/*      */     public WorldGenNetherPieceWeight(Class<? extends WorldGenNetherPieces.WorldGenNetherPiece> var0, int var1, int var2, boolean var3) {
/*   42 */       this.a = var0;
/*   43 */       this.b = var1;
/*   44 */       this.d = var2;
/*   45 */       this.e = var3;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPieceWeight(Class<? extends WorldGenNetherPieces.WorldGenNetherPiece> var0, int var1, int var2) {
/*   49 */       this(var0, var1, var2, false);
/*      */     }
/*      */     
/*      */     public boolean a(int var0) {
/*   53 */       return (this.d == 0 || this.c < this.d);
/*      */     }
/*      */     
/*      */     public boolean a() {
/*   57 */       return (this.d == 0 || this.c < this.d);
/*      */     }
/*      */   }
/*      */   
/*   61 */   private static final WorldGenNetherPieceWeight[] a = new WorldGenNetherPieceWeight[] { new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece3.class, 30, 0, true), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece1.class, 10, 4), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece13.class, 10, 4), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece14.class, 10, 3), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece12.class, 5, 2), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece6.class, 5, 1) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   69 */   private static final WorldGenNetherPieceWeight[] b = new WorldGenNetherPieceWeight[] { new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece9.class, 25, 0, true), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece7.class, 15, 5), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece10.class, 5, 10), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece8.class, 5, 10), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece4.class, 10, 3, true), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece5.class, 7, 2), new WorldGenNetherPieceWeight((Class)WorldGenNetherPiece11.class, 5, 2) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static WorldGenNetherPiece b(WorldGenNetherPieceWeight var0, List<StructurePiece> var1, Random var2, int var3, int var4, int var5, EnumDirection var6, int var7) {
/*   80 */     Class<? extends WorldGenNetherPiece> var8 = var0.a;
/*   81 */     WorldGenNetherPiece var9 = null;
/*      */     
/*   83 */     if (var8 == WorldGenNetherPiece3.class) {
/*   84 */       var9 = WorldGenNetherPiece3.a(var1, var2, var3, var4, var5, var6, var7);
/*   85 */     } else if (var8 == WorldGenNetherPiece1.class) {
/*   86 */       var9 = WorldGenNetherPiece1.a(var1, var3, var4, var5, var6, var7);
/*   87 */     } else if (var8 == WorldGenNetherPiece13.class) {
/*   88 */       var9 = WorldGenNetherPiece13.a(var1, var3, var4, var5, var6, var7);
/*   89 */     } else if (var8 == WorldGenNetherPiece14.class) {
/*   90 */       var9 = WorldGenNetherPiece14.a(var1, var3, var4, var5, var7, var6);
/*   91 */     } else if (var8 == WorldGenNetherPiece12.class) {
/*   92 */       var9 = WorldGenNetherPiece12.a(var1, var3, var4, var5, var7, var6);
/*   93 */     } else if (var8 == WorldGenNetherPiece6.class) {
/*   94 */       var9 = WorldGenNetherPiece6.a(var1, var2, var3, var4, var5, var6, var7);
/*   95 */     } else if (var8 == WorldGenNetherPiece9.class) {
/*   96 */       var9 = WorldGenNetherPiece9.a(var1, var3, var4, var5, var6, var7);
/*   97 */     } else if (var8 == WorldGenNetherPiece10.class) {
/*   98 */       var9 = WorldGenNetherPiece10.a(var1, var2, var3, var4, var5, var6, var7);
/*   99 */     } else if (var8 == WorldGenNetherPiece8.class) {
/*  100 */       var9 = WorldGenNetherPiece8.a(var1, var2, var3, var4, var5, var6, var7);
/*  101 */     } else if (var8 == WorldGenNetherPiece4.class) {
/*  102 */       var9 = WorldGenNetherPiece4.a(var1, var3, var4, var5, var6, var7);
/*  103 */     } else if (var8 == WorldGenNetherPiece5.class) {
/*  104 */       var9 = WorldGenNetherPiece5.a(var1, var3, var4, var5, var6, var7);
/*  105 */     } else if (var8 == WorldGenNetherPiece7.class) {
/*  106 */       var9 = WorldGenNetherPiece7.a(var1, var3, var4, var5, var6, var7);
/*  107 */     } else if (var8 == WorldGenNetherPiece11.class) {
/*  108 */       var9 = WorldGenNetherPiece11.a(var1, var3, var4, var5, var6, var7);
/*      */     } 
/*  110 */     return var9;
/*      */   }
/*      */   
/*      */   static abstract class WorldGenNetherPiece extends StructurePiece {
/*      */     protected WorldGenNetherPiece(WorldGenFeatureStructurePieceType var0, int var1) {
/*  115 */       super(var0, var1);
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece(WorldGenFeatureStructurePieceType var0, NBTTagCompound var1) {
/*  119 */       super(var0, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {}
/*      */ 
/*      */     
/*      */     private int a(List<WorldGenNetherPieces.WorldGenNetherPieceWeight> var0) {
/*  127 */       boolean var1 = false;
/*  128 */       int var2 = 0;
/*  129 */       for (WorldGenNetherPieces.WorldGenNetherPieceWeight var4 : var0) {
/*  130 */         if (var4.d > 0 && var4.c < var4.d) {
/*  131 */           var1 = true;
/*      */         }
/*  133 */         var2 += var4.b;
/*      */       } 
/*  135 */       return var1 ? var2 : -1;
/*      */     }
/*      */     
/*      */     private WorldGenNetherPiece a(WorldGenNetherPieces.WorldGenNetherPiece15 var0, List<WorldGenNetherPieces.WorldGenNetherPieceWeight> var1, List<StructurePiece> var2, Random var3, int var4, int var5, int var6, EnumDirection var7, int var8) {
/*  139 */       int var9 = a(var1);
/*  140 */       boolean var10 = (var9 > 0 && var8 <= 30);
/*      */       
/*  142 */       int var11 = 0;
/*  143 */       while (var11 < 5 && var10) {
/*  144 */         var11++;
/*      */         
/*  146 */         int var12 = var3.nextInt(var9);
/*  147 */         for (WorldGenNetherPieces.WorldGenNetherPieceWeight var14 : var1) {
/*  148 */           var12 -= var14.b;
/*  149 */           if (var12 < 0) {
/*  150 */             if (!var14.a(var8) || (var14 == var0.a && !var14.e)) {
/*      */               break;
/*      */             }
/*      */             
/*  154 */             WorldGenNetherPiece var15 = WorldGenNetherPieces.a(var14, var2, var3, var4, var5, var6, var7, var8);
/*  155 */             if (var15 != null) {
/*  156 */               var14.c++;
/*  157 */               var0.a = var14;
/*      */               
/*  159 */               if (!var14.a()) {
/*  160 */                 var1.remove(var14);
/*      */               }
/*  162 */               return var15;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  167 */       return WorldGenNetherPieces.WorldGenNetherPiece2.a(var2, var3, var4, var5, var6, var7, var8);
/*      */     }
/*      */     
/*      */     private StructurePiece a(WorldGenNetherPieces.WorldGenNetherPiece15 var0, List<StructurePiece> var1, Random var2, int var3, int var4, int var5, @Nullable EnumDirection var6, int var7, boolean var8) {
/*  171 */       if (Math.abs(var3 - (var0.g()).a) > 112 || Math.abs(var5 - (var0.g()).c) > 112) {
/*  172 */         return WorldGenNetherPieces.WorldGenNetherPiece2.a(var1, var2, var3, var4, var5, var6, var7);
/*      */       }
/*  174 */       List<WorldGenNetherPieces.WorldGenNetherPieceWeight> var9 = var0.b;
/*  175 */       if (var8) {
/*  176 */         var9 = var0.c;
/*      */       }
/*  178 */       StructurePiece var10 = a(var0, var9, var1, var2, var3, var4, var5, var6, var7 + 1);
/*  179 */       if (var10 != null) {
/*  180 */         var1.add(var10);
/*  181 */         var0.d.add(var10);
/*      */       } 
/*  183 */       return var10;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     protected StructurePiece a(WorldGenNetherPieces.WorldGenNetherPiece15 var0, List<StructurePiece> var1, Random var2, int var3, int var4, boolean var5) {
/*  188 */       EnumDirection var6 = i();
/*  189 */       if (var6 != null) {
/*  190 */         switch (WorldGenNetherPieces.null.a[var6.ordinal()]) {
/*      */           case 1:
/*  192 */             return a(var0, var1, var2, this.n.a + var3, this.n.b + var4, this.n.c - 1, var6, h(), var5);
/*      */           case 2:
/*  194 */             return a(var0, var1, var2, this.n.a + var3, this.n.b + var4, this.n.f + 1, var6, h(), var5);
/*      */           case 3:
/*  196 */             return a(var0, var1, var2, this.n.a - 1, this.n.b + var4, this.n.c + var3, var6, h(), var5);
/*      */           case 4:
/*  198 */             return a(var0, var1, var2, this.n.d + 1, this.n.b + var4, this.n.c + var3, var6, h(), var5);
/*      */         } 
/*      */       }
/*  201 */       return null;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     protected StructurePiece b(WorldGenNetherPieces.WorldGenNetherPiece15 var0, List<StructurePiece> var1, Random var2, int var3, int var4, boolean var5) {
/*  206 */       EnumDirection var6 = i();
/*  207 */       if (var6 != null) {
/*  208 */         switch (WorldGenNetherPieces.null.a[var6.ordinal()]) {
/*      */           case 1:
/*  210 */             return a(var0, var1, var2, this.n.a - 1, this.n.b + var3, this.n.c + var4, EnumDirection.WEST, h(), var5);
/*      */           case 2:
/*  212 */             return a(var0, var1, var2, this.n.a - 1, this.n.b + var3, this.n.c + var4, EnumDirection.WEST, h(), var5);
/*      */           case 3:
/*  214 */             return a(var0, var1, var2, this.n.a + var4, this.n.b + var3, this.n.c - 1, EnumDirection.NORTH, h(), var5);
/*      */           case 4:
/*  216 */             return a(var0, var1, var2, this.n.a + var4, this.n.b + var3, this.n.c - 1, EnumDirection.NORTH, h(), var5);
/*      */         } 
/*      */       }
/*  219 */       return null;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     protected StructurePiece c(WorldGenNetherPieces.WorldGenNetherPiece15 var0, List<StructurePiece> var1, Random var2, int var3, int var4, boolean var5) {
/*  224 */       EnumDirection var6 = i();
/*  225 */       if (var6 != null) {
/*  226 */         switch (WorldGenNetherPieces.null.a[var6.ordinal()]) {
/*      */           case 1:
/*  228 */             return a(var0, var1, var2, this.n.d + 1, this.n.b + var3, this.n.c + var4, EnumDirection.EAST, h(), var5);
/*      */           case 2:
/*  230 */             return a(var0, var1, var2, this.n.d + 1, this.n.b + var3, this.n.c + var4, EnumDirection.EAST, h(), var5);
/*      */           case 3:
/*  232 */             return a(var0, var1, var2, this.n.a + var4, this.n.b + var3, this.n.f + 1, EnumDirection.SOUTH, h(), var5);
/*      */           case 4:
/*  234 */             return a(var0, var1, var2, this.n.a + var4, this.n.b + var3, this.n.f + 1, EnumDirection.SOUTH, h(), var5);
/*      */         } 
/*      */       }
/*  237 */       return null;
/*      */     }
/*      */     
/*      */     protected static boolean a(StructureBoundingBox var0) {
/*  241 */       return (var0 != null && var0.b > 10);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece15
/*      */     extends WorldGenNetherPiece1
/*      */   {
/*      */     public WorldGenNetherPieces.WorldGenNetherPieceWeight a;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<WorldGenNetherPieces.WorldGenNetherPieceWeight> b;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<WorldGenNetherPieces.WorldGenNetherPieceWeight> c;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  273 */     public final List<StructurePiece> d = Lists.newArrayList();
/*      */     
/*      */     public WorldGenNetherPiece15(Random var0, int var1, int var2) {
/*  276 */       super(var0, var1, var2);
/*      */       
/*  278 */       this.b = Lists.newArrayList();
/*  279 */       for (WorldGenNetherPieces.WorldGenNetherPieceWeight var6 : WorldGenNetherPieces.a()) {
/*  280 */         var6.c = 0;
/*  281 */         this.b.add(var6);
/*      */       } 
/*      */       
/*  284 */       this.c = Lists.newArrayList();
/*  285 */       for (WorldGenNetherPieces.WorldGenNetherPieceWeight var6 : WorldGenNetherPieces.b()) {
/*  286 */         var6.c = 0;
/*  287 */         this.c.add(var6);
/*      */       } 
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece15(DefinedStructureManager var0, NBTTagCompound var1) {
/*  292 */       super(WorldGenFeatureStructurePieceType.s, var1);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece3
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     public WorldGenNetherPiece3(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/*  302 */       super(WorldGenFeatureStructurePieceType.g, var0);
/*      */       
/*  304 */       a(var3);
/*  305 */       this.n = var2;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece3(DefinedStructureManager var0, NBTTagCompound var1) {
/*  309 */       super(WorldGenFeatureStructurePieceType.g, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  314 */       a((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 1, 3, false);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece3 a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/*  318 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, -3, 0, 5, 10, 19, var5);
/*      */       
/*  320 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*  321 */         return null;
/*      */       }
/*      */       
/*  324 */       return new WorldGenNetherPiece3(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  330 */       a(var0, var4, 0, 3, 0, 4, 4, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  332 */       a(var0, var4, 1, 5, 0, 3, 7, 18, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */ 
/*      */       
/*  335 */       a(var0, var4, 0, 5, 0, 0, 5, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  336 */       a(var0, var4, 4, 5, 0, 4, 5, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/*  339 */       a(var0, var4, 0, 2, 0, 4, 2, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  340 */       a(var0, var4, 0, 2, 13, 4, 2, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  341 */       a(var0, var4, 0, 0, 0, 4, 1, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  342 */       a(var0, var4, 0, 0, 15, 4, 1, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  344 */       for (int i = 0; i <= 4; i++) {
/*  345 */         for (int j = 0; j <= 2; j++) {
/*  346 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), i, -1, j, var4);
/*  347 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), i, -1, 18 - j, var4);
/*      */         } 
/*      */       } 
/*      */       
/*  351 */       IBlockData var7 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*  352 */       IBlockData var8 = var7.set(BlockFence.EAST, Boolean.valueOf(true));
/*  353 */       IBlockData var9 = var7.set(BlockFence.WEST, Boolean.valueOf(true));
/*  354 */       a(var0, var4, 0, 1, 1, 0, 4, 1, var8, var8, false);
/*  355 */       a(var0, var4, 0, 3, 4, 0, 4, 4, var8, var8, false);
/*  356 */       a(var0, var4, 0, 3, 14, 0, 4, 14, var8, var8, false);
/*  357 */       a(var0, var4, 0, 1, 17, 0, 4, 17, var8, var8, false);
/*  358 */       a(var0, var4, 4, 1, 1, 4, 4, 1, var9, var9, false);
/*  359 */       a(var0, var4, 4, 3, 4, 4, 4, 4, var9, var9, false);
/*  360 */       a(var0, var4, 4, 3, 14, 4, 4, 14, var9, var9, false);
/*  361 */       a(var0, var4, 4, 1, 17, 4, 4, 17, var9, var9, false);
/*      */       
/*  363 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece2
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     private final int a;
/*      */ 
/*      */     
/*      */     public WorldGenNetherPiece2(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/*  375 */       super(WorldGenFeatureStructurePieceType.f, var0);
/*      */       
/*  377 */       a(var3);
/*  378 */       this.n = var2;
/*  379 */       this.a = var1.nextInt();
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece2(DefinedStructureManager var0, NBTTagCompound var1) {
/*  383 */       super(WorldGenFeatureStructurePieceType.f, var1);
/*  384 */       this.a = var1.getInt("Seed");
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece2 a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/*  388 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, -3, 0, 5, 10, 8, var5);
/*      */       
/*  390 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*  391 */         return null;
/*      */       }
/*      */       
/*  394 */       return new WorldGenNetherPiece2(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/*  399 */       super.a(var0);
/*      */       
/*  401 */       var0.setInt("Seed", this.a);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  406 */       Random var7 = new Random(this.a);
/*      */       
/*      */       int var8;
/*  409 */       for (var8 = 0; var8 <= 4; var8++) {
/*  410 */         for (int var9 = 3; var9 <= 4; var9++) {
/*  411 */           int var10 = var7.nextInt(8);
/*  412 */           a(var0, var4, var8, var9, 0, var8, var9, var10, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  418 */       var8 = var7.nextInt(8);
/*  419 */       a(var0, var4, 0, 5, 0, 0, 5, var8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/*  422 */       var8 = var7.nextInt(8);
/*  423 */       a(var0, var4, 4, 5, 0, 4, 5, var8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */ 
/*      */       
/*  427 */       for (var8 = 0; var8 <= 4; var8++) {
/*  428 */         int var9 = var7.nextInt(5);
/*  429 */         a(var0, var4, var8, 2, 0, var8, 2, var9, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       } 
/*  431 */       for (var8 = 0; var8 <= 4; var8++) {
/*  432 */         for (int var9 = 0; var9 <= 1; var9++) {
/*  433 */           int var10 = var7.nextInt(3);
/*  434 */           a(var0, var4, var8, var9, 0, var8, var9, var10, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */         } 
/*      */       } 
/*      */       
/*  438 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece1
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     public WorldGenNetherPiece1(int var0, StructureBoundingBox var1, EnumDirection var2) {
/*  448 */       super(WorldGenFeatureStructurePieceType.e, var0);
/*      */       
/*  450 */       a(var2);
/*  451 */       this.n = var1;
/*      */     }
/*      */     
/*      */     protected WorldGenNetherPiece1(Random var0, int var1, int var2) {
/*  455 */       super(WorldGenFeatureStructurePieceType.e, 0);
/*      */       
/*  457 */       a(EnumDirection.EnumDirectionLimit.HORIZONTAL.a(var0));
/*      */       
/*  459 */       if (i().n() == EnumDirection.EnumAxis.Z) {
/*  460 */         this.n = new StructureBoundingBox(var1, 64, var2, var1 + 19 - 1, 73, var2 + 19 - 1);
/*      */       } else {
/*  462 */         this.n = new StructureBoundingBox(var1, 64, var2, var1 + 19 - 1, 73, var2 + 19 - 1);
/*      */       } 
/*      */     }
/*      */     
/*      */     protected WorldGenNetherPiece1(WorldGenFeatureStructurePieceType var0, NBTTagCompound var1) {
/*  467 */       super(var0, var1);
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece1(DefinedStructureManager var0, NBTTagCompound var1) {
/*  471 */       this(WorldGenFeatureStructurePieceType.e, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  476 */       a((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 8, 3, false);
/*  477 */       b((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 3, 8, false);
/*  478 */       c((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 3, 8, false);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece1 a(List<StructurePiece> var0, int var1, int var2, int var3, EnumDirection var4, int var5) {
/*  482 */       StructureBoundingBox var6 = StructureBoundingBox.a(var1, var2, var3, -8, -3, 0, 19, 10, 19, var4);
/*      */       
/*  484 */       if (!a(var6) || StructurePiece.a(var0, var6) != null) {
/*  485 */         return null;
/*      */       }
/*      */       
/*  488 */       return new WorldGenNetherPiece1(var5, var6, var4);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  494 */       a(var0, var4, 7, 3, 0, 11, 4, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  495 */       a(var0, var4, 0, 3, 7, 18, 4, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  497 */       a(var0, var4, 8, 5, 0, 10, 7, 18, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*  498 */       a(var0, var4, 0, 5, 8, 18, 7, 10, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */       
/*  500 */       a(var0, var4, 7, 5, 0, 7, 5, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  501 */       a(var0, var4, 7, 5, 11, 7, 5, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  502 */       a(var0, var4, 11, 5, 0, 11, 5, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  503 */       a(var0, var4, 11, 5, 11, 11, 5, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  504 */       a(var0, var4, 0, 5, 7, 7, 5, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  505 */       a(var0, var4, 11, 5, 7, 18, 5, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  506 */       a(var0, var4, 0, 5, 11, 7, 5, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  507 */       a(var0, var4, 11, 5, 11, 18, 5, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/*  510 */       a(var0, var4, 7, 2, 0, 11, 2, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  511 */       a(var0, var4, 7, 2, 13, 11, 2, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  512 */       a(var0, var4, 7, 0, 0, 11, 1, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  513 */       a(var0, var4, 7, 0, 15, 11, 1, 18, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false); int var7;
/*  514 */       for (var7 = 7; var7 <= 11; var7++) {
/*  515 */         for (int var8 = 0; var8 <= 2; var8++) {
/*  516 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var7, -1, var8, var4);
/*  517 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var7, -1, 18 - var8, var4);
/*      */         } 
/*      */       } 
/*      */       
/*  521 */       a(var0, var4, 0, 2, 7, 5, 2, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  522 */       a(var0, var4, 13, 2, 7, 18, 2, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  523 */       a(var0, var4, 0, 0, 7, 3, 1, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  524 */       a(var0, var4, 15, 0, 7, 18, 1, 11, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  525 */       for (var7 = 0; var7 <= 2; var7++) {
/*  526 */         for (int var8 = 7; var8 <= 11; var8++) {
/*  527 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var7, -1, var8, var4);
/*  528 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), 18 - var7, -1, var8, var4);
/*      */         } 
/*      */       } 
/*      */       
/*  532 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece13
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     public WorldGenNetherPiece13(int var0, StructureBoundingBox var1, EnumDirection var2) {
/*  542 */       super(WorldGenFeatureStructurePieceType.q, var0);
/*      */       
/*  544 */       a(var2);
/*  545 */       this.n = var1;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece13(DefinedStructureManager var0, NBTTagCompound var1) {
/*  549 */       super(WorldGenFeatureStructurePieceType.q, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  554 */       a((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 2, 0, false);
/*  555 */       b((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 0, 2, false);
/*  556 */       c((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 0, 2, false);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece13 a(List<StructurePiece> var0, int var1, int var2, int var3, EnumDirection var4, int var5) {
/*  560 */       StructureBoundingBox var6 = StructureBoundingBox.a(var1, var2, var3, -2, 0, 0, 7, 9, 7, var4);
/*      */       
/*  562 */       if (!a(var6) || StructurePiece.a(var0, var6) != null) {
/*  563 */         return null;
/*      */       }
/*      */       
/*  566 */       return new WorldGenNetherPiece13(var5, var6, var4);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  572 */       a(var0, var4, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  574 */       a(var0, var4, 0, 2, 0, 6, 7, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */ 
/*      */       
/*  577 */       a(var0, var4, 0, 2, 0, 1, 6, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  578 */       a(var0, var4, 0, 2, 6, 1, 6, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  579 */       a(var0, var4, 5, 2, 0, 6, 6, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  580 */       a(var0, var4, 5, 2, 6, 6, 6, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  581 */       a(var0, var4, 0, 2, 0, 0, 6, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  582 */       a(var0, var4, 0, 2, 5, 0, 6, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  583 */       a(var0, var4, 6, 2, 0, 6, 6, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  584 */       a(var0, var4, 6, 2, 5, 6, 6, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/*  587 */       IBlockData var7 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true));
/*  588 */       IBlockData var8 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*      */       
/*  590 */       a(var0, var4, 2, 6, 0, 4, 6, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  591 */       a(var0, var4, 2, 5, 0, 4, 5, 0, var7, var7, false);
/*  592 */       a(var0, var4, 2, 6, 6, 4, 6, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  593 */       a(var0, var4, 2, 5, 6, 4, 5, 6, var7, var7, false);
/*  594 */       a(var0, var4, 0, 6, 2, 0, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  595 */       a(var0, var4, 0, 5, 2, 0, 5, 4, var8, var8, false);
/*  596 */       a(var0, var4, 6, 6, 2, 6, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  597 */       a(var0, var4, 6, 5, 2, 6, 5, 4, var8, var8, false);
/*      */ 
/*      */       
/*  600 */       for (int var9 = 0; var9 <= 6; var9++) {
/*  601 */         for (int var10 = 0; var10 <= 6; var10++) {
/*  602 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var9, -1, var10, var4);
/*      */         }
/*      */       } 
/*      */       
/*  606 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece14
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     public WorldGenNetherPiece14(int var0, StructureBoundingBox var1, EnumDirection var2) {
/*  616 */       super(WorldGenFeatureStructurePieceType.r, var0);
/*      */       
/*  618 */       a(var2);
/*  619 */       this.n = var1;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece14(DefinedStructureManager var0, NBTTagCompound var1) {
/*  623 */       super(WorldGenFeatureStructurePieceType.r, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  628 */       c((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 6, 2, false);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece14 a(List<StructurePiece> var0, int var1, int var2, int var3, int var4, EnumDirection var5) {
/*  632 */       StructureBoundingBox var6 = StructureBoundingBox.a(var1, var2, var3, -2, 0, 0, 7, 11, 7, var5);
/*      */       
/*  634 */       if (!a(var6) || StructurePiece.a(var0, var6) != null) {
/*  635 */         return null;
/*      */       }
/*      */       
/*  638 */       return new WorldGenNetherPiece14(var4, var6, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  644 */       a(var0, var4, 0, 0, 0, 6, 1, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  646 */       a(var0, var4, 0, 2, 0, 6, 10, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */ 
/*      */       
/*  649 */       a(var0, var4, 0, 2, 0, 1, 8, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  650 */       a(var0, var4, 5, 2, 0, 6, 8, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  651 */       a(var0, var4, 0, 2, 1, 0, 8, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  652 */       a(var0, var4, 6, 2, 1, 6, 8, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  653 */       a(var0, var4, 1, 2, 6, 5, 8, 6, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/*  656 */       IBlockData var7 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true));
/*  657 */       IBlockData var8 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*      */       
/*  659 */       a(var0, var4, 0, 3, 2, 0, 5, 4, var8, var8, false);
/*  660 */       a(var0, var4, 6, 3, 2, 6, 5, 2, var8, var8, false);
/*  661 */       a(var0, var4, 6, 3, 4, 6, 5, 4, var8, var8, false);
/*      */ 
/*      */       
/*  664 */       a(var0, Blocks.NETHER_BRICKS.getBlockData(), 5, 2, 5, var4);
/*  665 */       a(var0, var4, 4, 2, 5, 4, 3, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  666 */       a(var0, var4, 3, 2, 5, 3, 4, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  667 */       a(var0, var4, 2, 2, 5, 2, 5, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  668 */       a(var0, var4, 1, 2, 5, 1, 6, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/*  671 */       a(var0, var4, 1, 7, 1, 5, 7, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  672 */       a(var0, var4, 6, 8, 2, 6, 8, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */ 
/*      */       
/*  675 */       a(var0, var4, 2, 6, 0, 4, 8, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  676 */       a(var0, var4, 2, 5, 0, 4, 5, 0, var7, var7, false);
/*      */       
/*  678 */       for (int var9 = 0; var9 <= 6; var9++) {
/*  679 */         for (int var10 = 0; var10 <= 6; var10++) {
/*  680 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var9, -1, var10, var4);
/*      */         }
/*      */       } 
/*      */       
/*  684 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece12
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     private boolean a;
/*      */ 
/*      */     
/*      */     public WorldGenNetherPiece12(int var0, StructureBoundingBox var1, EnumDirection var2) {
/*  696 */       super(WorldGenFeatureStructurePieceType.p, var0);
/*      */       
/*  698 */       a(var2);
/*  699 */       this.n = var1;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece12(DefinedStructureManager var0, NBTTagCompound var1) {
/*  703 */       super(WorldGenFeatureStructurePieceType.p, var1);
/*  704 */       this.a = var1.getBoolean("Mob");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/*  709 */       super.a(var0);
/*      */       
/*  711 */       var0.setBoolean("Mob", this.a);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece12 a(List<StructurePiece> var0, int var1, int var2, int var3, int var4, EnumDirection var5) {
/*  715 */       StructureBoundingBox var6 = StructureBoundingBox.a(var1, var2, var3, -2, 0, 0, 7, 8, 9, var5);
/*      */       
/*  717 */       if (!a(var6) || StructurePiece.a(var0, var6) != null) {
/*  718 */         return null;
/*      */       }
/*      */       
/*  721 */       return new WorldGenNetherPiece12(var4, var6, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  727 */       a(var0, var4, 0, 2, 0, 6, 7, 7, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */ 
/*      */       
/*  730 */       a(var0, var4, 1, 0, 0, 5, 1, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  731 */       a(var0, var4, 1, 2, 1, 5, 2, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  732 */       a(var0, var4, 1, 3, 2, 5, 3, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  733 */       a(var0, var4, 1, 4, 3, 5, 4, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/*  736 */       a(var0, var4, 1, 2, 0, 1, 4, 2, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  737 */       a(var0, var4, 5, 2, 0, 5, 4, 2, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  738 */       a(var0, var4, 1, 5, 2, 1, 5, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  739 */       a(var0, var4, 5, 5, 2, 5, 5, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  740 */       a(var0, var4, 0, 5, 3, 0, 5, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  741 */       a(var0, var4, 6, 5, 3, 6, 5, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  742 */       a(var0, var4, 1, 5, 8, 5, 5, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  744 */       IBlockData var7 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true));
/*  745 */       IBlockData var8 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*      */       
/*  747 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)), 1, 6, 3, var4);
/*  748 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, Boolean.valueOf(true)), 5, 6, 3, var4);
/*      */       
/*  750 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, Boolean.valueOf(true)).set(BlockFence.NORTH, Boolean.valueOf(true)), 0, 6, 3, var4);
/*  751 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.NORTH, Boolean.valueOf(true)), 6, 6, 3, var4);
/*      */       
/*  753 */       a(var0, var4, 0, 6, 4, 0, 6, 7, var8, var8, false);
/*  754 */       a(var0, var4, 6, 6, 4, 6, 6, 7, var8, var8, false);
/*      */       
/*  756 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true)), 0, 6, 8, var4);
/*  757 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true)), 6, 6, 8, var4);
/*      */       
/*  759 */       a(var0, var4, 1, 6, 8, 5, 6, 8, var7, var7, false);
/*      */       
/*  761 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, Boolean.valueOf(true)), 1, 7, 8, var4);
/*  762 */       a(var0, var4, 2, 7, 8, 4, 7, 8, var7, var7, false);
/*  763 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)), 5, 7, 8, var4);
/*      */       
/*  765 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, Boolean.valueOf(true)), 2, 8, 8, var4);
/*  766 */       a(var0, var7, 3, 8, 8, var4);
/*  767 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)), 4, 8, 8, var4);
/*      */       
/*  769 */       if (!this.a) {
/*  770 */         BlockPosition blockPosition = new BlockPosition(a(3, 5), d(5), b(3, 5));
/*  771 */         if (var4.b(blockPosition)) {
/*  772 */           this.a = true;
/*  773 */           var0.setTypeAndData(blockPosition, Blocks.SPAWNER.getBlockData(), 2);
/*      */           
/*  775 */           TileEntity var10 = var0.getTileEntity(blockPosition);
/*  776 */           if (var10 instanceof TileEntityMobSpawner) {
/*  777 */             ((TileEntityMobSpawner)var10).getSpawner().setMobName(EntityTypes.BLAZE);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  783 */       for (int var9 = 0; var9 <= 6; var9++) {
/*  784 */         for (int var10 = 0; var10 <= 6; var10++) {
/*  785 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var9, -1, var10, var4);
/*      */         }
/*      */       } 
/*      */       
/*  789 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece6
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     public WorldGenNetherPiece6(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/*  799 */       super(WorldGenFeatureStructurePieceType.j, var0);
/*      */       
/*  801 */       a(var3);
/*  802 */       this.n = var2;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece6(DefinedStructureManager var0, NBTTagCompound var1) {
/*  806 */       super(WorldGenFeatureStructurePieceType.j, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  811 */       a((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 5, 3, true);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece6 a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/*  815 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -5, -3, 0, 13, 14, 13, var5);
/*      */       
/*  817 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*  818 */         return null;
/*      */       }
/*      */       
/*  821 */       return new WorldGenNetherPiece6(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  827 */       a(var0, var4, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  829 */       a(var0, var4, 0, 5, 0, 12, 13, 12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */ 
/*      */       
/*  832 */       a(var0, var4, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  833 */       a(var0, var4, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  834 */       a(var0, var4, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  835 */       a(var0, var4, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  836 */       a(var0, var4, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  837 */       a(var0, var4, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  838 */       a(var0, var4, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  839 */       a(var0, var4, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/*  842 */       a(var0, var4, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/*  845 */       a(var0, var4, 5, 8, 0, 7, 8, 0, Blocks.NETHER_BRICK_FENCE.getBlockData(), Blocks.NETHER_BRICK_FENCE.getBlockData(), false);
/*      */       
/*  847 */       IBlockData var7 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true));
/*  848 */       IBlockData var8 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*      */       
/*      */       int i;
/*  851 */       for (i = 1; i <= 11; i += 2) {
/*  852 */         a(var0, var4, i, 10, 0, i, 11, 0, var7, var7, false);
/*  853 */         a(var0, var4, i, 10, 12, i, 11, 12, var7, var7, false);
/*  854 */         a(var0, var4, 0, 10, i, 0, 11, i, var8, var8, false);
/*  855 */         a(var0, var4, 12, 10, i, 12, 11, i, var8, var8, false);
/*  856 */         a(var0, Blocks.NETHER_BRICKS.getBlockData(), i, 13, 0, var4);
/*  857 */         a(var0, Blocks.NETHER_BRICKS.getBlockData(), i, 13, 12, var4);
/*  858 */         a(var0, Blocks.NETHER_BRICKS.getBlockData(), 0, 13, i, var4);
/*  859 */         a(var0, Blocks.NETHER_BRICKS.getBlockData(), 12, 13, i, var4);
/*  860 */         if (i != 11) {
/*  861 */           a(var0, var7, i + 1, 13, 0, var4);
/*  862 */           a(var0, var7, i + 1, 13, 12, var4);
/*  863 */           a(var0, var8, 0, 13, i + 1, var4);
/*  864 */           a(var0, var8, 12, 13, i + 1, var4);
/*      */         } 
/*      */       } 
/*  867 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true)), 0, 13, 0, var4);
/*  868 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.SOUTH, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true)), 0, 13, 12, var4);
/*  869 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.SOUTH, Boolean.valueOf(true)).set(BlockFence.WEST, Boolean.valueOf(true)), 12, 13, 12, var4);
/*  870 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.WEST, Boolean.valueOf(true)), 12, 13, 0, var4);
/*      */ 
/*      */       
/*  873 */       for (i = 3; i <= 9; i += 2) {
/*  874 */         a(var0, var4, 1, 7, i, 1, 8, i, var8.set(BlockFence.WEST, Boolean.valueOf(true)), var8.set(BlockFence.WEST, Boolean.valueOf(true)), false);
/*  875 */         a(var0, var4, 11, 7, i, 11, 8, i, var8.set(BlockFence.EAST, Boolean.valueOf(true)), var8.set(BlockFence.EAST, Boolean.valueOf(true)), false);
/*      */       } 
/*      */ 
/*      */       
/*  879 */       a(var0, var4, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  880 */       a(var0, var4, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  882 */       a(var0, var4, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  883 */       a(var0, var4, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  884 */       a(var0, var4, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  885 */       a(var0, var4, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  887 */       for (i = 4; i <= 8; i++) {
/*  888 */         for (int var10 = 0; var10 <= 2; var10++) {
/*  889 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), i, -1, var10, var4);
/*  890 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), i, -1, 12 - var10, var4);
/*      */         } 
/*      */       } 
/*  893 */       for (i = 0; i <= 2; i++) {
/*  894 */         for (int var10 = 4; var10 <= 8; var10++) {
/*  895 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), i, -1, var10, var4);
/*  896 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), 12 - i, -1, var10, var4);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  901 */       a(var0, var4, 5, 5, 5, 7, 5, 7, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  902 */       a(var0, var4, 6, 1, 6, 6, 4, 6, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*  903 */       a(var0, Blocks.NETHER_BRICKS.getBlockData(), 6, 0, 6, var4);
/*  904 */       a(var0, Blocks.LAVA.getBlockData(), 6, 5, 6, var4);
/*      */       
/*  906 */       BlockPosition var9 = new BlockPosition(a(6, 6), d(5), b(6, 6));
/*  907 */       if (var4.b(var9)) {
/*  908 */         var0.getFluidTickList().a(var9, FluidTypes.LAVA, 0);
/*      */       }
/*      */       
/*  911 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece11
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     public WorldGenNetherPiece11(int var0, StructureBoundingBox var1, EnumDirection var2) {
/*  921 */       super(WorldGenFeatureStructurePieceType.o, var0);
/*      */       
/*  923 */       a(var2);
/*  924 */       this.n = var1;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece11(DefinedStructureManager var0, NBTTagCompound var1) {
/*  928 */       super(WorldGenFeatureStructurePieceType.o, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  933 */       a((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 5, 3, true);
/*  934 */       a((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 5, 11, true);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece11 a(List<StructurePiece> var0, int var1, int var2, int var3, EnumDirection var4, int var5) {
/*  938 */       StructureBoundingBox var6 = StructureBoundingBox.a(var1, var2, var3, -5, -3, 0, 13, 14, 13, var4);
/*      */       
/*  940 */       if (!a(var6) || StructurePiece.a(var0, var6) != null) {
/*  941 */         return null;
/*      */       }
/*      */       
/*  944 */       return new WorldGenNetherPiece11(var5, var6, var4);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  950 */       a(var0, var4, 0, 3, 0, 12, 4, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  952 */       a(var0, var4, 0, 5, 0, 12, 13, 12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */ 
/*      */       
/*  955 */       a(var0, var4, 0, 5, 0, 1, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  956 */       a(var0, var4, 11, 5, 0, 12, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  957 */       a(var0, var4, 2, 5, 11, 4, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  958 */       a(var0, var4, 8, 5, 11, 10, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  959 */       a(var0, var4, 5, 9, 11, 7, 12, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  960 */       a(var0, var4, 2, 5, 0, 4, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  961 */       a(var0, var4, 8, 5, 0, 10, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*  962 */       a(var0, var4, 5, 9, 0, 7, 12, 1, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/*  965 */       a(var0, var4, 2, 11, 2, 10, 12, 10, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/*  967 */       IBlockData var7 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true));
/*  968 */       IBlockData var8 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*  969 */       IBlockData var9 = var8.set(BlockFence.WEST, Boolean.valueOf(true));
/*  970 */       IBlockData var10 = var8.set(BlockFence.EAST, Boolean.valueOf(true));
/*      */       
/*      */       int i;
/*  973 */       for (i = 1; i <= 11; i += 2) {
/*  974 */         a(var0, var4, i, 10, 0, i, 11, 0, var7, var7, false);
/*  975 */         a(var0, var4, i, 10, 12, i, 11, 12, var7, var7, false);
/*  976 */         a(var0, var4, 0, 10, i, 0, 11, i, var8, var8, false);
/*  977 */         a(var0, var4, 12, 10, i, 12, 11, i, var8, var8, false);
/*  978 */         a(var0, Blocks.NETHER_BRICKS.getBlockData(), i, 13, 0, var4);
/*  979 */         a(var0, Blocks.NETHER_BRICKS.getBlockData(), i, 13, 12, var4);
/*  980 */         a(var0, Blocks.NETHER_BRICKS.getBlockData(), 0, 13, i, var4);
/*  981 */         a(var0, Blocks.NETHER_BRICKS.getBlockData(), 12, 13, i, var4);
/*  982 */         if (i != 11) {
/*  983 */           a(var0, var7, i + 1, 13, 0, var4);
/*  984 */           a(var0, var7, i + 1, 13, 12, var4);
/*  985 */           a(var0, var8, 0, 13, i + 1, var4);
/*  986 */           a(var0, var8, 12, 13, i + 1, var4);
/*      */         } 
/*      */       } 
/*  989 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true)), 0, 13, 0, var4);
/*  990 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.SOUTH, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true)), 0, 13, 12, var4);
/*  991 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.SOUTH, Boolean.valueOf(true)).set(BlockFence.WEST, Boolean.valueOf(true)), 12, 13, 12, var4);
/*  992 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.WEST, Boolean.valueOf(true)), 12, 13, 0, var4);
/*      */ 
/*      */       
/*  995 */       for (i = 3; i <= 9; i += 2) {
/*  996 */         a(var0, var4, 1, 7, i, 1, 8, i, var9, var9, false);
/*  997 */         a(var0, var4, 11, 7, i, 11, 8, i, var10, var10, false);
/*      */       } 
/*      */ 
/*      */       
/* 1001 */       IBlockData var11 = Blocks.NETHER_BRICK_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.NORTH); int j;
/* 1002 */       for (j = 0; j <= 6; j++) {
/* 1003 */         int k = j + 4;
/* 1004 */         for (int m = 5; m <= 7; m++) {
/* 1005 */           a(var0, var11, m, 5 + j, k, var4);
/*      */         }
/* 1007 */         if (k >= 5 && k <= 8) {
/* 1008 */           a(var0, var4, 5, 5, k, 7, j + 4, k, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1009 */         } else if (k >= 9 && k <= 10) {
/* 1010 */           a(var0, var4, 5, 8, k, 7, j + 4, k, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */         } 
/* 1012 */         if (j >= 1) {
/* 1013 */           a(var0, var4, 5, 6 + j, k, 7, 9 + j, k, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */         }
/*      */       } 
/* 1016 */       for (j = 5; j <= 7; j++) {
/* 1017 */         a(var0, var11, j, 12, 11, var4);
/*      */       }
/* 1019 */       a(var0, var4, 5, 6, 7, 5, 7, 7, var10, var10, false);
/* 1020 */       a(var0, var4, 7, 6, 7, 7, 7, 7, var9, var9, false);
/* 1021 */       a(var0, var4, 5, 13, 12, 7, 13, 12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */ 
/*      */       
/* 1024 */       a(var0, var4, 2, 5, 2, 3, 5, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1025 */       a(var0, var4, 2, 5, 9, 3, 5, 10, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1026 */       a(var0, var4, 2, 5, 4, 2, 5, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1027 */       a(var0, var4, 9, 5, 2, 10, 5, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1028 */       a(var0, var4, 9, 5, 9, 10, 5, 10, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1029 */       a(var0, var4, 10, 5, 4, 10, 5, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1030 */       IBlockData var12 = var11.set(BlockStairs.FACING, EnumDirection.EAST);
/* 1031 */       IBlockData var13 = var11.set(BlockStairs.FACING, EnumDirection.WEST);
/* 1032 */       a(var0, var13, 4, 5, 2, var4);
/* 1033 */       a(var0, var13, 4, 5, 3, var4);
/* 1034 */       a(var0, var13, 4, 5, 9, var4);
/* 1035 */       a(var0, var13, 4, 5, 10, var4);
/* 1036 */       a(var0, var12, 8, 5, 2, var4);
/* 1037 */       a(var0, var12, 8, 5, 3, var4);
/* 1038 */       a(var0, var12, 8, 5, 9, var4);
/* 1039 */       a(var0, var12, 8, 5, 10, var4);
/*      */ 
/*      */       
/* 1042 */       a(var0, var4, 3, 4, 4, 4, 4, 8, Blocks.SOUL_SAND.getBlockData(), Blocks.SOUL_SAND.getBlockData(), false);
/* 1043 */       a(var0, var4, 8, 4, 4, 9, 4, 8, Blocks.SOUL_SAND.getBlockData(), Blocks.SOUL_SAND.getBlockData(), false);
/* 1044 */       a(var0, var4, 3, 5, 4, 4, 5, 8, Blocks.NETHER_WART.getBlockData(), Blocks.NETHER_WART.getBlockData(), false);
/* 1045 */       a(var0, var4, 8, 5, 4, 9, 5, 8, Blocks.NETHER_WART.getBlockData(), Blocks.NETHER_WART.getBlockData(), false);
/*      */ 
/*      */       
/* 1048 */       a(var0, var4, 4, 2, 0, 8, 2, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1049 */       a(var0, var4, 0, 2, 4, 12, 2, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/* 1051 */       a(var0, var4, 4, 0, 0, 8, 1, 3, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1052 */       a(var0, var4, 4, 0, 9, 8, 1, 12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1053 */       a(var0, var4, 0, 0, 4, 3, 1, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1054 */       a(var0, var4, 9, 0, 4, 12, 1, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       int var14;
/* 1056 */       for (var14 = 4; var14 <= 8; var14++) {
/* 1057 */         for (int var15 = 0; var15 <= 2; var15++) {
/* 1058 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var14, -1, var15, var4);
/* 1059 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var14, -1, 12 - var15, var4);
/*      */         } 
/*      */       } 
/* 1062 */       for (var14 = 0; var14 <= 2; var14++) {
/* 1063 */         for (int var15 = 4; var15 <= 8; var15++) {
/* 1064 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var14, -1, var15, var4);
/* 1065 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), 12 - var14, -1, var15, var4);
/*      */         } 
/*      */       } 
/*      */       
/* 1069 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece9
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     public WorldGenNetherPiece9(int var0, StructureBoundingBox var1, EnumDirection var2) {
/* 1079 */       super(WorldGenFeatureStructurePieceType.m, var0);
/*      */       
/* 1081 */       a(var2);
/* 1082 */       this.n = var1;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece9(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1086 */       super(WorldGenFeatureStructurePieceType.m, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 1091 */       a((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 1, 0, true);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece9 a(List<StructurePiece> var0, int var1, int var2, int var3, EnumDirection var4, int var5) {
/* 1095 */       StructureBoundingBox var6 = StructureBoundingBox.a(var1, var2, var3, -1, 0, 0, 5, 7, 5, var4);
/*      */       
/* 1097 */       if (!a(var6) || StructurePiece.a(var0, var6) != null) {
/* 1098 */         return null;
/*      */       }
/*      */       
/* 1101 */       return new WorldGenNetherPiece9(var5, var6, var4);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1107 */       a(var0, var4, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/* 1109 */       a(var0, var4, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */       
/* 1111 */       IBlockData var7 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*      */ 
/*      */       
/* 1114 */       a(var0, var4, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1115 */       a(var0, var4, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1116 */       a(var0, var4, 0, 3, 1, 0, 4, 1, var7, var7, false);
/* 1117 */       a(var0, var4, 0, 3, 3, 0, 4, 3, var7, var7, false);
/* 1118 */       a(var0, var4, 4, 3, 1, 4, 4, 1, var7, var7, false);
/* 1119 */       a(var0, var4, 4, 3, 3, 4, 4, 3, var7, var7, false);
/*      */ 
/*      */       
/* 1122 */       a(var0, var4, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/* 1125 */       for (int var8 = 0; var8 <= 4; var8++) {
/* 1126 */         for (int var9 = 0; var9 <= 4; var9++) {
/* 1127 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var8, -1, var9, var4);
/*      */         }
/*      */       } 
/*      */       
/* 1131 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece7
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     public WorldGenNetherPiece7(int var0, StructureBoundingBox var1, EnumDirection var2) {
/* 1141 */       super(WorldGenFeatureStructurePieceType.k, var0);
/*      */       
/* 1143 */       a(var2);
/* 1144 */       this.n = var1;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece7(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1148 */       super(WorldGenFeatureStructurePieceType.k, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 1153 */       a((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 1, 0, true);
/* 1154 */       b((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 0, 1, true);
/* 1155 */       c((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 0, 1, true);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece7 a(List<StructurePiece> var0, int var1, int var2, int var3, EnumDirection var4, int var5) {
/* 1159 */       StructureBoundingBox var6 = StructureBoundingBox.a(var1, var2, var3, -1, 0, 0, 5, 7, 5, var4);
/*      */       
/* 1161 */       if (!a(var6) || StructurePiece.a(var0, var6) != null) {
/* 1162 */         return null;
/*      */       }
/*      */       
/* 1165 */       return new WorldGenNetherPiece7(var5, var6, var4);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1171 */       a(var0, var4, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/* 1173 */       a(var0, var4, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */ 
/*      */       
/* 1176 */       a(var0, var4, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1177 */       a(var0, var4, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1178 */       a(var0, var4, 0, 2, 4, 0, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1179 */       a(var0, var4, 4, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/* 1182 */       a(var0, var4, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/* 1185 */       for (int var7 = 0; var7 <= 4; var7++) {
/* 1186 */         for (int var8 = 0; var8 <= 4; var8++) {
/* 1187 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var7, -1, var8, var4);
/*      */         }
/*      */       } 
/*      */       
/* 1191 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece10
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     private boolean a;
/*      */ 
/*      */     
/*      */     public WorldGenNetherPiece10(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/* 1203 */       super(WorldGenFeatureStructurePieceType.n, var0);
/*      */       
/* 1205 */       a(var3);
/* 1206 */       this.n = var2;
/*      */       
/* 1208 */       this.a = (var1.nextInt(3) == 0);
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece10(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1212 */       super(WorldGenFeatureStructurePieceType.n, var1);
/* 1213 */       this.a = var1.getBoolean("Chest");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/* 1218 */       super.a(var0);
/*      */       
/* 1220 */       var0.setBoolean("Chest", this.a);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 1225 */       c((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 0, 1, true);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece10 a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/* 1229 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
/*      */       
/* 1231 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/* 1232 */         return null;
/*      */       }
/*      */       
/* 1235 */       return new WorldGenNetherPiece10(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1241 */       a(var0, var4, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/* 1243 */       a(var0, var4, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */       
/* 1245 */       IBlockData var7 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true));
/* 1246 */       IBlockData var8 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*      */ 
/*      */       
/* 1249 */       a(var0, var4, 0, 2, 0, 0, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1250 */       a(var0, var4, 0, 3, 1, 0, 4, 1, var8, var8, false);
/* 1251 */       a(var0, var4, 0, 3, 3, 0, 4, 3, var8, var8, false);
/*      */       
/* 1253 */       a(var0, var4, 4, 2, 0, 4, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/* 1255 */       a(var0, var4, 1, 2, 4, 4, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1256 */       a(var0, var4, 1, 3, 4, 1, 4, 4, var7, var7, false);
/* 1257 */       a(var0, var4, 3, 3, 4, 3, 4, 4, var7, var7, false);
/*      */       
/* 1259 */       if (this.a && 
/* 1260 */         var4.b(new BlockPosition(a(1, 3), d(2), b(1, 3)))) {
/* 1261 */         this.a = false;
/* 1262 */         a(var0, var4, var3, 1, 2, 3, LootTables.v);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1267 */       a(var0, var4, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/* 1270 */       for (int var9 = 0; var9 <= 4; var9++) {
/* 1271 */         for (int var10 = 0; var10 <= 4; var10++) {
/* 1272 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var9, -1, var10, var4);
/*      */         }
/*      */       } 
/*      */       
/* 1276 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece8
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     private boolean a;
/*      */ 
/*      */     
/*      */     public WorldGenNetherPiece8(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/* 1288 */       super(WorldGenFeatureStructurePieceType.l, var0);
/*      */       
/* 1290 */       a(var3);
/* 1291 */       this.n = var2;
/*      */       
/* 1293 */       this.a = (var1.nextInt(3) == 0);
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece8(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1297 */       super(WorldGenFeatureStructurePieceType.l, var1);
/* 1298 */       this.a = var1.getBoolean("Chest");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/* 1303 */       super.a(var0);
/*      */       
/* 1305 */       var0.setBoolean("Chest", this.a);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 1310 */       b((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 0, 1, true);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece8 a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/* 1314 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
/*      */       
/* 1316 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/* 1317 */         return null;
/*      */       }
/*      */       
/* 1320 */       return new WorldGenNetherPiece8(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1326 */       a(var0, var4, 0, 0, 0, 4, 1, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/* 1328 */       a(var0, var4, 0, 2, 0, 4, 5, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */       
/* 1330 */       IBlockData var7 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true));
/* 1331 */       IBlockData var8 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*      */ 
/*      */       
/* 1334 */       a(var0, var4, 4, 2, 0, 4, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1335 */       a(var0, var4, 4, 3, 1, 4, 4, 1, var8, var8, false);
/* 1336 */       a(var0, var4, 4, 3, 3, 4, 4, 3, var8, var8, false);
/*      */       
/* 1338 */       a(var0, var4, 0, 2, 0, 0, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/* 1340 */       a(var0, var4, 0, 2, 4, 3, 5, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1341 */       a(var0, var4, 1, 3, 4, 1, 4, 4, var7, var7, false);
/* 1342 */       a(var0, var4, 3, 3, 4, 3, 4, 4, var7, var7, false);
/*      */       
/* 1344 */       if (this.a && 
/* 1345 */         var4.b(new BlockPosition(a(3, 3), d(2), b(3, 3)))) {
/* 1346 */         this.a = false;
/* 1347 */         a(var0, var4, var3, 3, 2, 3, LootTables.v);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1352 */       a(var0, var4, 0, 6, 0, 4, 6, 4, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/* 1355 */       for (int var9 = 0; var9 <= 4; var9++) {
/* 1356 */         for (int var10 = 0; var10 <= 4; var10++) {
/* 1357 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var9, -1, var10, var4);
/*      */         }
/*      */       } 
/*      */       
/* 1361 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece4
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     public WorldGenNetherPiece4(int var0, StructureBoundingBox var1, EnumDirection var2) {
/* 1371 */       super(WorldGenFeatureStructurePieceType.h, var0);
/*      */       
/* 1373 */       a(var2);
/* 1374 */       this.n = var1;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece4(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1378 */       super(WorldGenFeatureStructurePieceType.h, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 1383 */       a((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 1, 0, true);
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece4 a(List<StructurePiece> var0, int var1, int var2, int var3, EnumDirection var4, int var5) {
/* 1387 */       StructureBoundingBox var6 = StructureBoundingBox.a(var1, var2, var3, -1, -7, 0, 5, 14, 10, var4);
/*      */       
/* 1389 */       if (!a(var6) || StructurePiece.a(var0, var6) != null) {
/* 1390 */         return null;
/*      */       }
/*      */       
/* 1393 */       return new WorldGenNetherPiece4(var5, var6, var4);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1399 */       IBlockData var7 = Blocks.NETHER_BRICK_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.SOUTH);
/* 1400 */       IBlockData var8 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*      */       
/* 1402 */       for (int var9 = 0; var9 <= 9; var9++) {
/* 1403 */         int var10 = Math.max(1, 7 - var9);
/* 1404 */         int var11 = Math.min(Math.max(var10 + 5, 14 - var9), 13);
/* 1405 */         int var12 = var9;
/*      */ 
/*      */         
/* 1408 */         a(var0, var4, 0, 0, var12, 4, var10, var12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */         
/* 1410 */         a(var0, var4, 1, var10 + 1, var12, 3, var11 - 1, var12, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/* 1411 */         if (var9 <= 6) {
/* 1412 */           a(var0, var7, 1, var10 + 1, var12, var4);
/* 1413 */           a(var0, var7, 2, var10 + 1, var12, var4);
/* 1414 */           a(var0, var7, 3, var10 + 1, var12, var4);
/*      */         } 
/*      */         
/* 1417 */         a(var0, var4, 0, var11, var12, 4, var11, var12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */         
/* 1419 */         a(var0, var4, 0, var10 + 1, var12, 0, var11 - 1, var12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1420 */         a(var0, var4, 4, var10 + 1, var12, 4, var11 - 1, var12, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1421 */         if ((var9 & 0x1) == 0) {
/* 1422 */           a(var0, var4, 0, var10 + 2, var12, 0, var10 + 3, var12, var8, var8, false);
/* 1423 */           a(var0, var4, 4, var10 + 2, var12, 4, var10 + 3, var12, var8, var8, false);
/*      */         } 
/*      */ 
/*      */         
/* 1427 */         for (int var13 = 0; var13 <= 4; var13++) {
/* 1428 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var13, -1, var12, var4);
/*      */         }
/*      */       } 
/*      */       
/* 1432 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenNetherPiece5
/*      */     extends WorldGenNetherPiece
/*      */   {
/*      */     public WorldGenNetherPiece5(int var0, StructureBoundingBox var1, EnumDirection var2) {
/* 1442 */       super(WorldGenFeatureStructurePieceType.i, var0);
/*      */       
/* 1444 */       a(var2);
/* 1445 */       this.n = var1;
/*      */     }
/*      */     
/*      */     public WorldGenNetherPiece5(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1449 */       super(WorldGenFeatureStructurePieceType.i, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 1454 */       int var3 = 1;
/*      */       
/* 1456 */       EnumDirection var4 = i();
/* 1457 */       if (var4 == EnumDirection.WEST || var4 == EnumDirection.NORTH) {
/* 1458 */         var3 = 5;
/*      */       }
/*      */       
/* 1461 */       b((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 0, var3, (var2.nextInt(8) > 0));
/* 1462 */       c((WorldGenNetherPieces.WorldGenNetherPiece15)var0, var1, var2, 0, var3, (var2.nextInt(8) > 0));
/*      */     }
/*      */     
/*      */     public static WorldGenNetherPiece5 a(List<StructurePiece> var0, int var1, int var2, int var3, EnumDirection var4, int var5) {
/* 1466 */       StructureBoundingBox var6 = StructureBoundingBox.a(var1, var2, var3, -3, 0, 0, 9, 7, 9, var4);
/*      */       
/* 1468 */       if (!a(var6) || StructurePiece.a(var0, var6) != null) {
/* 1469 */         return null;
/*      */       }
/*      */       
/* 1472 */       return new WorldGenNetherPiece5(var5, var6, var4);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1477 */       IBlockData var7 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/* 1478 */       IBlockData var8 = Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true));
/*      */ 
/*      */       
/* 1481 */       a(var0, var4, 0, 0, 0, 8, 1, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */       
/* 1483 */       a(var0, var4, 0, 2, 0, 8, 5, 8, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */       
/* 1485 */       a(var0, var4, 0, 6, 0, 8, 6, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/*      */ 
/*      */       
/* 1488 */       a(var0, var4, 0, 2, 0, 2, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1489 */       a(var0, var4, 6, 2, 0, 8, 5, 0, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1490 */       a(var0, var4, 1, 3, 0, 1, 4, 0, var8, var8, false);
/* 1491 */       a(var0, var4, 7, 3, 0, 7, 4, 0, var8, var8, false);
/*      */ 
/*      */       
/* 1494 */       a(var0, var4, 0, 2, 4, 8, 2, 8, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1495 */       a(var0, var4, 1, 1, 4, 2, 2, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/* 1496 */       a(var0, var4, 6, 1, 4, 7, 2, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*      */ 
/*      */       
/* 1499 */       a(var0, var4, 1, 3, 8, 7, 3, 8, var8, var8, false);
/* 1500 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.EAST, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true)), 0, 3, 8, var4);
/* 1501 */       a(var0, Blocks.NETHER_BRICK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true)), 8, 3, 8, var4);
/* 1502 */       a(var0, var4, 0, 3, 6, 0, 3, 7, var7, var7, false);
/* 1503 */       a(var0, var4, 8, 3, 6, 8, 3, 7, var7, var7, false);
/*      */ 
/*      */       
/* 1506 */       a(var0, var4, 0, 3, 4, 0, 5, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1507 */       a(var0, var4, 8, 3, 4, 8, 5, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1508 */       a(var0, var4, 1, 3, 5, 2, 5, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1509 */       a(var0, var4, 6, 3, 5, 7, 5, 5, Blocks.NETHER_BRICKS.getBlockData(), Blocks.NETHER_BRICKS.getBlockData(), false);
/* 1510 */       a(var0, var4, 1, 4, 5, 1, 5, 5, var8, var8, false);
/* 1511 */       a(var0, var4, 7, 4, 5, 7, 5, 5, var8, var8, false);
/*      */ 
/*      */       
/* 1514 */       for (int var9 = 0; var9 <= 5; var9++) {
/* 1515 */         for (int var10 = 0; var10 <= 8; var10++) {
/* 1516 */           b(var0, Blocks.NETHER_BRICKS.getBlockData(), var10, -1, var9, var4);
/*      */         }
/*      */       } 
/*      */       
/* 1520 */       return true;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenNetherPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */