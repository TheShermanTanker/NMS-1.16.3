/*      */ package net.minecraft.server.v1_16_R2;
/*      */ 
/*      */ import com.google.common.collect.ImmutableSet;
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
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
/*      */ public class WorldGenMonumentPieces
/*      */ {
/*      */   public static abstract class WorldGenMonumentPiece
/*      */     extends StructurePiece
/*      */   {
/*   31 */     protected static final IBlockData a = Blocks.PRISMARINE.getBlockData();
/*   32 */     protected static final IBlockData b = Blocks.PRISMARINE_BRICKS.getBlockData();
/*   33 */     protected static final IBlockData c = Blocks.DARK_PRISMARINE.getBlockData();
/*      */     
/*   35 */     protected static final IBlockData d = b;
/*      */     
/*   37 */     protected static final IBlockData e = Blocks.SEA_LANTERN.getBlockData();
/*      */ 
/*      */     
/*   40 */     protected static final IBlockData f = Blocks.WATER.getBlockData();
/*   41 */     protected static final Set<Block> g = (Set<Block>)ImmutableSet.builder()
/*   42 */       .add(Blocks.ICE)
/*   43 */       .add(Blocks.PACKED_ICE)
/*   44 */       .add(Blocks.BLUE_ICE)
/*   45 */       .add(f.getBlock())
/*   46 */       .build();
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
/*   57 */     protected static final int h = b(2, 0, 0);
/*   58 */     protected static final int i = b(2, 2, 0);
/*   59 */     protected static final int j = b(0, 1, 0);
/*   60 */     protected static final int k = b(4, 1, 0);
/*      */ 
/*      */ 
/*      */     
/*      */     protected WorldGenMonumentPieces.WorldGenMonumentStateTracker l;
/*      */ 
/*      */ 
/*      */     
/*      */     protected static final int b(int var0, int var1, int var2) {
/*   69 */       return var1 * 25 + var2 * 5 + var0;
/*      */     }
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
/*      */     public WorldGenMonumentPiece(WorldGenFeatureStructurePieceType var0, int var1) {
/*   87 */       super(var0, var1);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPiece(WorldGenFeatureStructurePieceType var0, EnumDirection var1, StructureBoundingBox var2) {
/*   91 */       super(var0, 1);
/*   92 */       a(var1);
/*   93 */       this.n = var2;
/*      */     }
/*      */     
/*      */     protected WorldGenMonumentPiece(WorldGenFeatureStructurePieceType var0, int var1, EnumDirection var2, WorldGenMonumentPieces.WorldGenMonumentStateTracker var3, int var4, int var5, int var6) {
/*   97 */       super(var0, var1);
/*   98 */       a(var2);
/*   99 */       this.l = var3;
/*      */       
/*  101 */       int var7 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var3);
/*  102 */       int var8 = var7 % 5;
/*  103 */       int var9 = var7 / 5 % 5;
/*  104 */       int var10 = var7 / 25;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  109 */       if (var2 == EnumDirection.NORTH || var2 == EnumDirection.SOUTH) {
/*  110 */         this.n = new StructureBoundingBox(0, 0, 0, var4 * 8 - 1, var5 * 4 - 1, var6 * 8 - 1);
/*      */       } else {
/*      */         
/*  113 */         this.n = new StructureBoundingBox(0, 0, 0, var6 * 8 - 1, var5 * 4 - 1, var4 * 8 - 1);
/*      */       } 
/*      */       
/*  116 */       switch (WorldGenMonumentPieces.null.a[var2.ordinal()]) {
/*      */         case 1:
/*  118 */           this.n.a(var8 * 8, var10 * 4, -(var9 + var6) * 8 + 1);
/*      */           return;
/*      */         
/*      */         case 2:
/*  122 */           this.n.a(var8 * 8, var10 * 4, var9 * 8);
/*      */           return;
/*      */         case 3:
/*  125 */           this.n.a(-(var9 + var6) * 8 + 1, var10 * 4, var8 * 8);
/*      */           return;
/*      */       } 
/*  128 */       this.n.a(var9 * 8, var10 * 4, var8 * 8);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public WorldGenMonumentPiece(WorldGenFeatureStructurePieceType var0, NBTTagCompound var1) {
/*  134 */       super(var0, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {}
/*      */ 
/*      */     
/*      */     protected void a(GeneratorAccessSeed var0, StructureBoundingBox var1, int var2, int var3, int var4, int var5, int var6, int var7) {
/*  142 */       for (int var8 = var3; var8 <= var6; var8++) {
/*  143 */         for (int var9 = var2; var9 <= var5; var9++) {
/*  144 */           for (int var10 = var4; var10 <= var7; var10++) {
/*  145 */             IBlockData var11 = a(var0, var9, var8, var10, var1);
/*  146 */             if (!g.contains(var11.getBlock())) {
/*  147 */               if (d(var8) >= var0.getSeaLevel() && var11 != f) {
/*  148 */                 a(var0, Blocks.AIR.getBlockData(), var9, var8, var10, var1);
/*      */               } else {
/*  150 */                 a(var0, f, var9, var8, var10, var1);
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void a(GeneratorAccessSeed var0, StructureBoundingBox var1, int var2, int var3, boolean var4) {
/*  159 */       if (var4) {
/*  160 */         a(var0, var1, var2 + 0, 0, var3 + 0, var2 + 2, 0, var3 + 8 - 1, a, a, false);
/*  161 */         a(var0, var1, var2 + 5, 0, var3 + 0, var2 + 8 - 1, 0, var3 + 8 - 1, a, a, false);
/*  162 */         a(var0, var1, var2 + 3, 0, var3 + 0, var2 + 4, 0, var3 + 2, a, a, false);
/*  163 */         a(var0, var1, var2 + 3, 0, var3 + 5, var2 + 4, 0, var3 + 8 - 1, a, a, false);
/*      */         
/*  165 */         a(var0, var1, var2 + 3, 0, var3 + 2, var2 + 4, 0, var3 + 2, b, b, false);
/*  166 */         a(var0, var1, var2 + 3, 0, var3 + 5, var2 + 4, 0, var3 + 5, b, b, false);
/*  167 */         a(var0, var1, var2 + 2, 0, var3 + 3, var2 + 2, 0, var3 + 4, b, b, false);
/*  168 */         a(var0, var1, var2 + 5, 0, var3 + 3, var2 + 5, 0, var3 + 4, b, b, false);
/*      */       } else {
/*  170 */         a(var0, var1, var2 + 0, 0, var3 + 0, var2 + 8 - 1, 0, var3 + 8 - 1, a, a, false);
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void a(GeneratorAccessSeed var0, StructureBoundingBox var1, int var2, int var3, int var4, int var5, int var6, int var7, IBlockData var8) {
/*  175 */       for (int var9 = var3; var9 <= var6; var9++) {
/*  176 */         for (int var10 = var2; var10 <= var5; var10++) {
/*  177 */           for (int var11 = var4; var11 <= var7; var11++) {
/*  178 */             if (a(var0, var10, var9, var11, var1) == f)
/*      */             {
/*      */               
/*  181 */               a(var0, var8, var10, var9, var11, var1); } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     protected boolean a(StructureBoundingBox var0, int var1, int var2, int var3, int var4) {
/*  188 */       int var5 = a(var1, var2);
/*  189 */       int var6 = b(var1, var2);
/*  190 */       int var7 = a(var3, var4);
/*  191 */       int var8 = b(var3, var4);
/*  192 */       return var0.a(Math.min(var5, var7), Math.min(var6, var8), Math.max(var5, var7), Math.max(var6, var8));
/*      */     }
/*      */     
/*      */     protected boolean a(GeneratorAccessSeed var0, StructureBoundingBox var1, int var2, int var3, int var4) {
/*  196 */       int var5 = a(var2, var4);
/*  197 */       int var6 = d(var3);
/*  198 */       int var7 = b(var2, var4);
/*      */       
/*  200 */       if (var1.b(new BlockPosition(var5, var6, var7))) {
/*  201 */         EntityGuardianElder var8 = EntityTypes.ELDER_GUARDIAN.a(var0.getMinecraftWorld());
/*  202 */         var8.heal(var8.getMaxHealth());
/*  203 */         var8.setPositionRotation(var5 + 0.5D, var6, var7 + 0.5D, 0.0F, 0.0F);
/*  204 */         var8.prepare(var0, var0.getDamageScaler(var8.getChunkCoordinates()), EnumMobSpawn.STRUCTURE, (GroupDataEntity)null, (NBTTagCompound)null);
/*  205 */         var0.addAllEntities(var8);
/*  206 */         return true;
/*      */       } 
/*  208 */       return false;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenMonumentPiece1
/*      */     extends WorldGenMonumentPiece
/*      */   {
/*      */     private WorldGenMonumentPieces.WorldGenMonumentStateTracker p;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private WorldGenMonumentPieces.WorldGenMonumentStateTracker q;
/*      */ 
/*      */ 
/*      */     
/*  228 */     private final List<WorldGenMonumentPieces.WorldGenMonumentPiece> r = Lists.newArrayList();
/*      */     
/*      */     public WorldGenMonumentPiece1(Random var0, int var1, int var2, EnumDirection var3) {
/*  231 */       super(WorldGenFeatureStructurePieceType.M, 0);
/*      */       
/*  233 */       a(var3);
/*      */       
/*  235 */       EnumDirection var4 = i();
/*  236 */       if (var4.n() == EnumDirection.EnumAxis.Z) {
/*  237 */         this.n = new StructureBoundingBox(var1, 39, var2, var1 + 58 - 1, 61, var2 + 58 - 1);
/*      */       } else {
/*  239 */         this.n = new StructureBoundingBox(var1, 39, var2, var1 + 58 - 1, 61, var2 + 58 - 1);
/*      */       } 
/*      */       
/*  242 */       List<WorldGenMonumentPieces.WorldGenMonumentStateTracker> var5 = a(var0);
/*      */       
/*  244 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(this.p, true);
/*  245 */       this.r.add(new WorldGenMonumentPieces.WorldGenMonumentPieceEntry(var4, this.p));
/*  246 */       this.r.add(new WorldGenMonumentPieces.WorldGenMonumentPiece2(var4, this.q));
/*      */       
/*  248 */       List<WorldGenMonumentPieces.IWorldGenMonumentPieceSelector> var6 = Lists.newArrayList();
/*  249 */       var6.add(new WorldGenMonumentPieces.WorldGenMonumentPieceSelector6());
/*  250 */       var6.add(new WorldGenMonumentPieces.WorldGenMonumentPieceSelector4());
/*  251 */       var6.add(new WorldGenMonumentPieces.WorldGenMonumentPieceSelector3());
/*  252 */       var6.add(new WorldGenMonumentPieces.WorldGenMonumentPieceSelector7());
/*  253 */       var6.add(new WorldGenMonumentPieces.WorldGenMonumentPieceSelector5());
/*  254 */       var6.add(new WorldGenMonumentPieces.WorldGenMonumentPieceSelector1());
/*  255 */       var6.add(new WorldGenMonumentPieces.WorldGenMonumentPieceSelector2());
/*      */       
/*  257 */       for (WorldGenMonumentPieces.WorldGenMonumentStateTracker worldGenMonumentStateTracker : var5) {
/*  258 */         if (!WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(worldGenMonumentStateTracker) && !worldGenMonumentStateTracker.b())
/*      */         {
/*  260 */           for (WorldGenMonumentPieces.IWorldGenMonumentPieceSelector iWorldGenMonumentPieceSelector : var6) {
/*  261 */             if (iWorldGenMonumentPieceSelector.a(worldGenMonumentStateTracker)) {
/*  262 */               this.r.add(iWorldGenMonumentPieceSelector.a(var4, worldGenMonumentStateTracker, var0));
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  270 */       int var7 = this.n.b;
/*  271 */       int var8 = a(9, 22);
/*  272 */       int var9 = b(9, 22);
/*  273 */       for (WorldGenMonumentPieces.WorldGenMonumentPiece worldGenMonumentPiece : this.r) {
/*  274 */         worldGenMonumentPiece.g().a(var8, var7, var9);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  279 */       StructureBoundingBox var10 = StructureBoundingBox.a(a(1, 1), d(1), b(1, 1), a(23, 21), d(8), b(23, 21));
/*  280 */       StructureBoundingBox var11 = StructureBoundingBox.a(a(34, 1), d(1), b(34, 1), a(56, 21), d(8), b(56, 21));
/*  281 */       StructureBoundingBox var12 = StructureBoundingBox.a(a(22, 22), d(13), b(22, 22), a(35, 35), d(17), b(35, 35));
/*      */ 
/*      */       
/*  284 */       int var13 = var0.nextInt();
/*  285 */       this.r.add(new WorldGenMonumentPieces.WorldGenMonumentPiece8(var4, var10, var13++));
/*  286 */       this.r.add(new WorldGenMonumentPieces.WorldGenMonumentPiece8(var4, var11, var13++));
/*      */       
/*  288 */       this.r.add(new WorldGenMonumentPieces.WorldGenMonumentPiecePenthouse(var4, var12));
/*      */     }
/*      */ 
/*      */     
/*      */     public WorldGenMonumentPiece1(DefinedStructureManager var0, NBTTagCompound var1) {
/*  293 */       super(WorldGenFeatureStructurePieceType.M, var1);
/*      */     }
/*      */     
/*      */     private List<WorldGenMonumentPieces.WorldGenMonumentStateTracker> a(Random var0) {
/*  297 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker[] var1 = new WorldGenMonumentPieces.WorldGenMonumentStateTracker[75];
/*      */       int i;
/*  299 */       for (i = 0; i < 5; i++) {
/*  300 */         for (int j = 0; j < 4; j++) {
/*  301 */           int k = 0;
/*  302 */           int m = b(i, 0, j);
/*  303 */           var1[m] = new WorldGenMonumentPieces.WorldGenMonumentStateTracker(m);
/*      */         } 
/*      */       } 
/*  306 */       for (i = 0; i < 5; i++) {
/*  307 */         for (int j = 0; j < 4; j++) {
/*  308 */           int k = 1;
/*  309 */           int m = b(i, 1, j);
/*  310 */           var1[m] = new WorldGenMonumentPieces.WorldGenMonumentStateTracker(m);
/*      */         } 
/*      */       } 
/*  313 */       for (i = 1; i < 4; i++) {
/*  314 */         for (int j = 0; j < 2; j++) {
/*  315 */           int k = 2;
/*  316 */           int m = b(i, 2, j);
/*  317 */           var1[m] = new WorldGenMonumentPieces.WorldGenMonumentStateTracker(m);
/*      */         } 
/*      */       } 
/*      */       
/*  321 */       this.p = var1[h];
/*      */       
/*  323 */       for (i = 0; i < 5; i++) {
/*  324 */         for (int j = 0; j < 5; j++) {
/*  325 */           for (int k = 0; k < 3; k++) {
/*  326 */             int m = b(i, k, j);
/*  327 */             if (var1[m] != null)
/*      */             {
/*      */               
/*  330 */               for (EnumDirection var9 : EnumDirection.values()) {
/*  331 */                 int var10 = i + var9.getAdjacentX();
/*  332 */                 int var11 = k + var9.getAdjacentY();
/*  333 */                 int var12 = j + var9.getAdjacentZ();
/*  334 */                 if (var10 >= 0 && var10 < 5 && var12 >= 0 && var12 < 5 && var11 >= 0 && var11 < 3) {
/*  335 */                   int var13 = b(var10, var11, var12);
/*  336 */                   if (var1[var13] != null)
/*      */                   {
/*      */                     
/*  339 */                     if (var12 == j) {
/*  340 */                       var1[m].a(var9, var1[var13]);
/*      */                     } else {
/*  342 */                       var1[m].a(var9.opposite(), var1[var13]);
/*      */                     }  } 
/*      */                 } 
/*      */               } 
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*  350 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var2 = new WorldGenMonumentPieces.WorldGenMonumentStateTracker(1003);
/*  351 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var3 = new WorldGenMonumentPieces.WorldGenMonumentStateTracker(1001);
/*  352 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var4 = new WorldGenMonumentPieces.WorldGenMonumentStateTracker(1002);
/*  353 */       var1[i].a(EnumDirection.UP, var2);
/*  354 */       var1[j].a(EnumDirection.SOUTH, var3);
/*  355 */       var1[k].a(EnumDirection.SOUTH, var4);
/*  356 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var2, true);
/*  357 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var3, true);
/*  358 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var4, true);
/*  359 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(this.p, true);
/*      */ 
/*      */       
/*  362 */       this.q = var1[b(var0.nextInt(4), 0, 2)];
/*  363 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(this.q, true);
/*  364 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.q)[EnumDirection.EAST.c()], true);
/*  365 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.q)[EnumDirection.NORTH.c()], true);
/*  366 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.q)[EnumDirection.EAST.c()])[EnumDirection.NORTH.c()], true);
/*  367 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.q)[EnumDirection.UP.c()], true);
/*  368 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.q)[EnumDirection.EAST.c()])[EnumDirection.UP.c()], true);
/*  369 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.q)[EnumDirection.NORTH.c()])[EnumDirection.UP.c()], true);
/*  370 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.q)[EnumDirection.EAST.c()])[EnumDirection.NORTH.c()])[EnumDirection.UP.c()], true);
/*      */       
/*  372 */       List<WorldGenMonumentPieces.WorldGenMonumentStateTracker> var5 = Lists.newArrayList();
/*  373 */       for (WorldGenMonumentPieces.WorldGenMonumentStateTracker var9 : var1) {
/*  374 */         if (var9 != null) {
/*  375 */           var9.a();
/*  376 */           var5.add(var9);
/*      */         } 
/*      */       } 
/*  379 */       var2.a();
/*      */       
/*  381 */       Collections.shuffle(var5, var0);
/*  382 */       int var6 = 1;
/*  383 */       for (WorldGenMonumentPieces.WorldGenMonumentStateTracker var8 : var5) {
/*      */         
/*  385 */         int var9 = 0;
/*  386 */         int var10 = 0;
/*  387 */         while (var9 < 2 && var10 < 5) {
/*  388 */           var10++;
/*      */           
/*  390 */           int var11 = var0.nextInt(6);
/*  391 */           if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[var11]) {
/*  392 */             int var12 = EnumDirection.fromType1(var11).opposite().c();
/*      */ 
/*      */             
/*  395 */             WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[var11] = false;
/*  396 */             WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var8)[var11])[var12] = false;
/*      */             
/*  398 */             if (var8.a(var6++) && WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var8)[var11].a(var6++)) {
/*  399 */               var9++;
/*      */               continue;
/*      */             } 
/*  402 */             WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[var11] = true;
/*  403 */             WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var8)[var11])[var12] = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  408 */       var5.add(var2);
/*  409 */       var5.add(var3);
/*  410 */       var5.add(var4);
/*      */       
/*  412 */       return var5;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  417 */       int var7 = Math.max(var0.getSeaLevel(), 64) - this.n.b;
/*      */       
/*  419 */       a(var0, var4, 0, 0, 0, 58, var7, 58);
/*      */ 
/*      */       
/*  422 */       a(false, 0, var0, var3, var4);
/*      */ 
/*      */       
/*  425 */       a(true, 33, var0, var3, var4);
/*      */ 
/*      */       
/*  428 */       a(var0, var3, var4);
/*      */       
/*  430 */       b(var0, var3, var4);
/*  431 */       c(var0, var3, var4);
/*      */       
/*  433 */       d(var0, var3, var4);
/*  434 */       e(var0, var3, var4);
/*  435 */       f(var0, var3, var4);
/*      */       
/*      */       int var8;
/*  438 */       for (var8 = 0; var8 < 7; var8++) {
/*  439 */         for (int var9 = 0; var9 < 7; ) {
/*  440 */           if (var9 == 0 && var8 == 3)
/*      */           {
/*  442 */             var9 = 6;
/*      */           }
/*      */           
/*  445 */           int var10 = var8 * 9;
/*  446 */           int var11 = var9 * 9;
/*  447 */           for (int var12 = 0; var12 < 4; var12++) {
/*  448 */             for (int var13 = 0; var13 < 4; var13++) {
/*  449 */               a(var0, b, var10 + var12, 0, var11 + var13, var4);
/*  450 */               b(var0, b, var10 + var12, -1, var11 + var13, var4);
/*      */             } 
/*      */           } 
/*      */           
/*  454 */           if (var8 == 0 || var8 == 6) {
/*  455 */             var9++; continue;
/*      */           } 
/*  457 */           var9 += 6;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  463 */       for (var8 = 0; var8 < 5; var8++) {
/*  464 */         a(var0, var4, -1 - var8, 0 + var8 * 2, -1 - var8, -1 - var8, 23, 58 + var8);
/*  465 */         a(var0, var4, 58 + var8, 0 + var8 * 2, -1 - var8, 58 + var8, 23, 58 + var8);
/*  466 */         a(var0, var4, 0 - var8, 0 + var8 * 2, -1 - var8, 57 + var8, 23, -1 - var8);
/*  467 */         a(var0, var4, 0 - var8, 0 + var8 * 2, 58 + var8, 57 + var8, 23, 58 + var8);
/*      */       } 
/*      */       
/*  470 */       for (WorldGenMonumentPieces.WorldGenMonumentPiece var9 : this.r) {
/*  471 */         if (var9.g().b(var4)) {
/*  472 */           var9.a(var0, var1, var2, var3, var4, var5, var6);
/*      */         }
/*      */       } 
/*      */       
/*  476 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     private void a(boolean var0, int var1, GeneratorAccessSeed var2, Random var3, StructureBoundingBox var4) {
/*  481 */       int var5 = 24;
/*  482 */       if (a(var4, var1, 0, var1 + 23, 20)) {
/*  483 */         a(var2, var4, var1 + 0, 0, 0, var1 + 24, 0, 20, a, a, false);
/*      */         
/*  485 */         a(var2, var4, var1 + 0, 1, 0, var1 + 24, 10, 20);
/*      */         int var6;
/*  487 */         for (var6 = 0; var6 < 4; var6++) {
/*  488 */           a(var2, var4, var1 + var6, var6 + 1, var6, var1 + var6, var6 + 1, 20, b, b, false);
/*  489 */           a(var2, var4, var1 + var6 + 7, var6 + 5, var6 + 7, var1 + var6 + 7, var6 + 5, 20, b, b, false);
/*  490 */           a(var2, var4, var1 + 17 - var6, var6 + 5, var6 + 7, var1 + 17 - var6, var6 + 5, 20, b, b, false);
/*  491 */           a(var2, var4, var1 + 24 - var6, var6 + 1, var6, var1 + 24 - var6, var6 + 1, 20, b, b, false);
/*      */           
/*  493 */           a(var2, var4, var1 + var6 + 1, var6 + 1, var6, var1 + 23 - var6, var6 + 1, var6, b, b, false);
/*  494 */           a(var2, var4, var1 + var6 + 8, var6 + 5, var6 + 7, var1 + 16 - var6, var6 + 5, var6 + 7, b, b, false);
/*      */         } 
/*  496 */         a(var2, var4, var1 + 4, 4, 4, var1 + 6, 4, 20, a, a, false);
/*  497 */         a(var2, var4, var1 + 7, 4, 4, var1 + 17, 4, 6, a, a, false);
/*  498 */         a(var2, var4, var1 + 18, 4, 4, var1 + 20, 4, 20, a, a, false);
/*  499 */         a(var2, var4, var1 + 11, 8, 11, var1 + 13, 8, 20, a, a, false);
/*  500 */         a(var2, d, var1 + 12, 9, 12, var4);
/*  501 */         a(var2, d, var1 + 12, 9, 15, var4);
/*  502 */         a(var2, d, var1 + 12, 9, 18, var4);
/*      */         
/*  504 */         var6 = var1 + (var0 ? 19 : 5);
/*  505 */         int var7 = var1 + (var0 ? 5 : 19); int var8;
/*  506 */         for (var8 = 20; var8 >= 5; var8 -= 3) {
/*  507 */           a(var2, d, var6, 5, var8, var4);
/*      */         }
/*  509 */         for (var8 = 19; var8 >= 7; var8 -= 3) {
/*  510 */           a(var2, d, var7, 5, var8, var4);
/*      */         }
/*  512 */         for (var8 = 0; var8 < 4; var8++) {
/*  513 */           int var9 = var0 ? (var1 + 24 - 17 - var8 * 3) : (var1 + 17 - var8 * 3);
/*  514 */           a(var2, d, var9, 5, 5, var4);
/*      */         } 
/*  516 */         a(var2, d, var7, 5, 5, var4);
/*      */ 
/*      */         
/*  519 */         a(var2, var4, var1 + 11, 1, 12, var1 + 13, 7, 12, a, a, false);
/*  520 */         a(var2, var4, var1 + 12, 1, 11, var1 + 12, 7, 13, a, a, false);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private void a(GeneratorAccessSeed var0, Random var1, StructureBoundingBox var2) {
/*  526 */       if (a(var2, 22, 5, 35, 17)) {
/*      */         
/*  528 */         a(var0, var2, 25, 0, 0, 32, 8, 20);
/*      */ 
/*      */         
/*  531 */         for (int var3 = 0; var3 < 4; var3++) {
/*  532 */           a(var0, var2, 24, 2, 5 + var3 * 4, 24, 4, 5 + var3 * 4, b, b, false);
/*  533 */           a(var0, var2, 22, 4, 5 + var3 * 4, 23, 4, 5 + var3 * 4, b, b, false);
/*  534 */           a(var0, b, 25, 5, 5 + var3 * 4, var2);
/*  535 */           a(var0, b, 26, 6, 5 + var3 * 4, var2);
/*  536 */           a(var0, e, 26, 5, 5 + var3 * 4, var2);
/*      */           
/*  538 */           a(var0, var2, 33, 2, 5 + var3 * 4, 33, 4, 5 + var3 * 4, b, b, false);
/*  539 */           a(var0, var2, 34, 4, 5 + var3 * 4, 35, 4, 5 + var3 * 4, b, b, false);
/*  540 */           a(var0, b, 32, 5, 5 + var3 * 4, var2);
/*  541 */           a(var0, b, 31, 6, 5 + var3 * 4, var2);
/*  542 */           a(var0, e, 31, 5, 5 + var3 * 4, var2);
/*      */           
/*  544 */           a(var0, var2, 27, 6, 5 + var3 * 4, 30, 6, 5 + var3 * 4, a, a, false);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void b(GeneratorAccessSeed var0, Random var1, StructureBoundingBox var2) {
/*  552 */       if (a(var2, 15, 20, 42, 21)) {
/*  553 */         a(var0, var2, 15, 0, 21, 42, 0, 21, a, a, false);
/*      */         
/*  555 */         a(var0, var2, 26, 1, 21, 31, 3, 21);
/*      */ 
/*      */ 
/*      */         
/*  559 */         a(var0, var2, 21, 12, 21, 36, 12, 21, a, a, false);
/*  560 */         a(var0, var2, 17, 11, 21, 40, 11, 21, a, a, false);
/*  561 */         a(var0, var2, 16, 10, 21, 41, 10, 21, a, a, false);
/*  562 */         a(var0, var2, 15, 7, 21, 42, 9, 21, a, a, false);
/*  563 */         a(var0, var2, 16, 6, 21, 41, 6, 21, a, a, false);
/*  564 */         a(var0, var2, 17, 5, 21, 40, 5, 21, a, a, false);
/*  565 */         a(var0, var2, 21, 4, 21, 36, 4, 21, a, a, false);
/*  566 */         a(var0, var2, 22, 3, 21, 26, 3, 21, a, a, false);
/*  567 */         a(var0, var2, 31, 3, 21, 35, 3, 21, a, a, false);
/*  568 */         a(var0, var2, 23, 2, 21, 25, 2, 21, a, a, false);
/*  569 */         a(var0, var2, 32, 2, 21, 34, 2, 21, a, a, false);
/*      */ 
/*      */         
/*  572 */         a(var0, var2, 28, 4, 20, 29, 4, 21, b, b, false);
/*  573 */         a(var0, b, 27, 3, 21, var2);
/*  574 */         a(var0, b, 30, 3, 21, var2);
/*  575 */         a(var0, b, 26, 2, 21, var2);
/*  576 */         a(var0, b, 31, 2, 21, var2);
/*  577 */         a(var0, b, 25, 1, 21, var2);
/*  578 */         a(var0, b, 32, 1, 21, var2); int var3;
/*  579 */         for (var3 = 0; var3 < 7; var3++) {
/*  580 */           a(var0, c, 28 - var3, 6 + var3, 21, var2);
/*  581 */           a(var0, c, 29 + var3, 6 + var3, 21, var2);
/*      */         } 
/*  583 */         for (var3 = 0; var3 < 4; var3++) {
/*  584 */           a(var0, c, 28 - var3, 9 + var3, 21, var2);
/*  585 */           a(var0, c, 29 + var3, 9 + var3, 21, var2);
/*      */         } 
/*  587 */         a(var0, c, 28, 12, 21, var2);
/*  588 */         a(var0, c, 29, 12, 21, var2);
/*  589 */         for (var3 = 0; var3 < 3; var3++) {
/*  590 */           a(var0, c, 22 - var3 * 2, 8, 21, var2);
/*  591 */           a(var0, c, 22 - var3 * 2, 9, 21, var2);
/*      */           
/*  593 */           a(var0, c, 35 + var3 * 2, 8, 21, var2);
/*  594 */           a(var0, c, 35 + var3 * 2, 9, 21, var2);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  599 */         a(var0, var2, 15, 13, 21, 42, 15, 21);
/*  600 */         a(var0, var2, 15, 1, 21, 15, 6, 21);
/*  601 */         a(var0, var2, 16, 1, 21, 16, 5, 21);
/*  602 */         a(var0, var2, 17, 1, 21, 20, 4, 21);
/*  603 */         a(var0, var2, 21, 1, 21, 21, 3, 21);
/*  604 */         a(var0, var2, 22, 1, 21, 22, 2, 21);
/*  605 */         a(var0, var2, 23, 1, 21, 24, 1, 21);
/*  606 */         a(var0, var2, 42, 1, 21, 42, 6, 21);
/*  607 */         a(var0, var2, 41, 1, 21, 41, 5, 21);
/*  608 */         a(var0, var2, 37, 1, 21, 40, 4, 21);
/*  609 */         a(var0, var2, 36, 1, 21, 36, 3, 21);
/*  610 */         a(var0, var2, 33, 1, 21, 34, 1, 21);
/*  611 */         a(var0, var2, 35, 1, 21, 35, 2, 21);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void c(GeneratorAccessSeed var0, Random var1, StructureBoundingBox var2) {
/*  619 */       if (a(var2, 21, 21, 36, 36)) {
/*  620 */         a(var0, var2, 21, 0, 22, 36, 0, 36, a, a, false);
/*      */ 
/*      */ 
/*      */         
/*  624 */         a(var0, var2, 21, 1, 22, 36, 23, 36);
/*      */ 
/*      */         
/*  627 */         for (int var3 = 0; var3 < 4; var3++) {
/*  628 */           a(var0, var2, 21 + var3, 13 + var3, 21 + var3, 36 - var3, 13 + var3, 21 + var3, b, b, false);
/*  629 */           a(var0, var2, 21 + var3, 13 + var3, 36 - var3, 36 - var3, 13 + var3, 36 - var3, b, b, false);
/*  630 */           a(var0, var2, 21 + var3, 13 + var3, 22 + var3, 21 + var3, 13 + var3, 35 - var3, b, b, false);
/*  631 */           a(var0, var2, 36 - var3, 13 + var3, 22 + var3, 36 - var3, 13 + var3, 35 - var3, b, b, false);
/*      */         } 
/*  633 */         a(var0, var2, 25, 16, 25, 32, 16, 32, a, a, false);
/*  634 */         a(var0, var2, 25, 17, 25, 25, 19, 25, b, b, false);
/*  635 */         a(var0, var2, 32, 17, 25, 32, 19, 25, b, b, false);
/*  636 */         a(var0, var2, 25, 17, 32, 25, 19, 32, b, b, false);
/*  637 */         a(var0, var2, 32, 17, 32, 32, 19, 32, b, b, false);
/*      */         
/*  639 */         a(var0, b, 26, 20, 26, var2);
/*  640 */         a(var0, b, 27, 21, 27, var2);
/*  641 */         a(var0, e, 27, 20, 27, var2);
/*  642 */         a(var0, b, 26, 20, 31, var2);
/*  643 */         a(var0, b, 27, 21, 30, var2);
/*  644 */         a(var0, e, 27, 20, 30, var2);
/*  645 */         a(var0, b, 31, 20, 31, var2);
/*  646 */         a(var0, b, 30, 21, 30, var2);
/*  647 */         a(var0, e, 30, 20, 30, var2);
/*  648 */         a(var0, b, 31, 20, 26, var2);
/*  649 */         a(var0, b, 30, 21, 27, var2);
/*  650 */         a(var0, e, 30, 20, 27, var2);
/*      */         
/*  652 */         a(var0, var2, 28, 21, 27, 29, 21, 27, a, a, false);
/*  653 */         a(var0, var2, 27, 21, 28, 27, 21, 29, a, a, false);
/*  654 */         a(var0, var2, 28, 21, 30, 29, 21, 30, a, a, false);
/*  655 */         a(var0, var2, 30, 21, 28, 30, 21, 29, a, a, false);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void d(GeneratorAccessSeed var0, Random var1, StructureBoundingBox var2) {
/*  662 */       if (a(var2, 0, 21, 6, 58)) {
/*  663 */         a(var0, var2, 0, 0, 21, 6, 0, 57, a, a, false);
/*      */         
/*  665 */         a(var0, var2, 0, 1, 21, 6, 7, 57);
/*      */ 
/*      */         
/*  668 */         a(var0, var2, 4, 4, 21, 6, 4, 53, a, a, false); int var3;
/*  669 */         for (var3 = 0; var3 < 4; var3++) {
/*  670 */           a(var0, var2, var3, var3 + 1, 21, var3, var3 + 1, 57 - var3, b, b, false);
/*      */         }
/*  672 */         for (var3 = 23; var3 < 53; var3 += 3) {
/*  673 */           a(var0, d, 5, 5, var3, var2);
/*      */         }
/*  675 */         a(var0, d, 5, 5, 52, var2);
/*      */         
/*  677 */         for (var3 = 0; var3 < 4; var3++) {
/*  678 */           a(var0, var2, var3, var3 + 1, 21, var3, var3 + 1, 57 - var3, b, b, false);
/*      */         }
/*      */         
/*  681 */         a(var0, var2, 4, 1, 52, 6, 3, 52, a, a, false);
/*  682 */         a(var0, var2, 5, 1, 51, 5, 3, 53, a, a, false);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  687 */       if (a(var2, 51, 21, 58, 58)) {
/*  688 */         a(var0, var2, 51, 0, 21, 57, 0, 57, a, a, false);
/*      */         
/*  690 */         a(var0, var2, 51, 1, 21, 57, 7, 57);
/*      */ 
/*      */         
/*  693 */         a(var0, var2, 51, 4, 21, 53, 4, 53, a, a, false); int var3;
/*  694 */         for (var3 = 0; var3 < 4; var3++) {
/*  695 */           a(var0, var2, 57 - var3, var3 + 1, 21, 57 - var3, var3 + 1, 57 - var3, b, b, false);
/*      */         }
/*  697 */         for (var3 = 23; var3 < 53; var3 += 3) {
/*  698 */           a(var0, d, 52, 5, var3, var2);
/*      */         }
/*  700 */         a(var0, d, 52, 5, 52, var2);
/*      */ 
/*      */         
/*  703 */         a(var0, var2, 51, 1, 52, 53, 3, 52, a, a, false);
/*  704 */         a(var0, var2, 52, 1, 51, 52, 3, 53, a, a, false);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  709 */       if (a(var2, 0, 51, 57, 57)) {
/*  710 */         a(var0, var2, 7, 0, 51, 50, 0, 57, a, a, false);
/*      */         
/*  712 */         a(var0, var2, 7, 1, 51, 50, 10, 57);
/*      */ 
/*      */         
/*  715 */         for (int var3 = 0; var3 < 4; var3++) {
/*  716 */           a(var0, var2, var3 + 1, var3 + 1, 57 - var3, 56 - var3, var3 + 1, 57 - var3, b, b, false);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void e(GeneratorAccessSeed var0, Random var1, StructureBoundingBox var2) {
/*  724 */       if (a(var2, 7, 21, 13, 50)) {
/*  725 */         a(var0, var2, 7, 0, 21, 13, 0, 50, a, a, false);
/*      */         
/*  727 */         a(var0, var2, 7, 1, 21, 13, 10, 50);
/*      */ 
/*      */         
/*  730 */         a(var0, var2, 11, 8, 21, 13, 8, 53, a, a, false); int var3;
/*  731 */         for (var3 = 0; var3 < 4; var3++) {
/*  732 */           a(var0, var2, var3 + 7, var3 + 5, 21, var3 + 7, var3 + 5, 54, b, b, false);
/*      */         }
/*  734 */         for (var3 = 21; var3 <= 45; var3 += 3) {
/*  735 */           a(var0, d, 12, 9, var3, var2);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  741 */       if (a(var2, 44, 21, 50, 54)) {
/*  742 */         a(var0, var2, 44, 0, 21, 50, 0, 50, a, a, false);
/*      */         
/*  744 */         a(var0, var2, 44, 1, 21, 50, 10, 50);
/*      */ 
/*      */         
/*  747 */         a(var0, var2, 44, 8, 21, 46, 8, 53, a, a, false); int var3;
/*  748 */         for (var3 = 0; var3 < 4; var3++) {
/*  749 */           a(var0, var2, 50 - var3, var3 + 5, 21, 50 - var3, var3 + 5, 54, b, b, false);
/*      */         }
/*  751 */         for (var3 = 21; var3 <= 45; var3 += 3) {
/*  752 */           a(var0, d, 45, 9, var3, var2);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  758 */       if (a(var2, 8, 44, 49, 54)) {
/*  759 */         a(var0, var2, 14, 0, 44, 43, 0, 50, a, a, false);
/*      */         
/*  761 */         a(var0, var2, 14, 1, 44, 43, 10, 50);
/*      */         
/*      */         int var3;
/*  764 */         for (var3 = 12; var3 <= 45; var3 += 3) {
/*  765 */           a(var0, d, var3, 9, 45, var2);
/*  766 */           a(var0, d, var3, 9, 52, var2);
/*  767 */           if (var3 == 12 || var3 == 18 || var3 == 24 || var3 == 33 || var3 == 39 || var3 == 45) {
/*  768 */             a(var0, d, var3, 9, 47, var2);
/*  769 */             a(var0, d, var3, 9, 50, var2);
/*  770 */             a(var0, d, var3, 10, 45, var2);
/*  771 */             a(var0, d, var3, 10, 46, var2);
/*  772 */             a(var0, d, var3, 10, 51, var2);
/*  773 */             a(var0, d, var3, 10, 52, var2);
/*  774 */             a(var0, d, var3, 11, 47, var2);
/*  775 */             a(var0, d, var3, 11, 50, var2);
/*  776 */             a(var0, d, var3, 12, 48, var2);
/*  777 */             a(var0, d, var3, 12, 49, var2);
/*      */           } 
/*      */         } 
/*      */         
/*  781 */         for (var3 = 0; var3 < 3; var3++) {
/*  782 */           a(var0, var2, 8 + var3, 5 + var3, 54, 49 - var3, 5 + var3, 54, a, a, false);
/*      */         }
/*  784 */         a(var0, var2, 11, 8, 54, 46, 8, 54, b, b, false);
/*  785 */         a(var0, var2, 14, 8, 44, 43, 8, 53, a, a, false);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     private void f(GeneratorAccessSeed var0, Random var1, StructureBoundingBox var2) {
/*  792 */       if (a(var2, 14, 21, 20, 43)) {
/*  793 */         a(var0, var2, 14, 0, 21, 20, 0, 43, a, a, false);
/*      */         
/*  795 */         a(var0, var2, 14, 1, 22, 20, 14, 43);
/*      */ 
/*      */         
/*  798 */         a(var0, var2, 18, 12, 22, 20, 12, 39, a, a, false);
/*  799 */         a(var0, var2, 18, 12, 21, 20, 12, 21, b, b, false); int var3;
/*  800 */         for (var3 = 0; var3 < 4; var3++) {
/*  801 */           a(var0, var2, var3 + 14, var3 + 9, 21, var3 + 14, var3 + 9, 43 - var3, b, b, false);
/*      */         }
/*  803 */         for (var3 = 23; var3 <= 39; var3 += 3) {
/*  804 */           a(var0, d, 19, 13, var3, var2);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  810 */       if (a(var2, 37, 21, 43, 43)) {
/*  811 */         a(var0, var2, 37, 0, 21, 43, 0, 43, a, a, false);
/*      */         
/*  813 */         a(var0, var2, 37, 1, 22, 43, 14, 43);
/*      */ 
/*      */         
/*  816 */         a(var0, var2, 37, 12, 22, 39, 12, 39, a, a, false);
/*  817 */         a(var0, var2, 37, 12, 21, 39, 12, 21, b, b, false); int var3;
/*  818 */         for (var3 = 0; var3 < 4; var3++) {
/*  819 */           a(var0, var2, 43 - var3, var3 + 9, 21, 43 - var3, var3 + 9, 43 - var3, b, b, false);
/*      */         }
/*  821 */         for (var3 = 23; var3 <= 39; var3 += 3) {
/*  822 */           a(var0, d, 38, 13, var3, var2);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  828 */       if (a(var2, 15, 37, 42, 43)) {
/*  829 */         a(var0, var2, 21, 0, 37, 36, 0, 43, a, a, false);
/*      */         
/*  831 */         a(var0, var2, 21, 1, 37, 36, 14, 43);
/*      */ 
/*      */         
/*  834 */         a(var0, var2, 21, 12, 37, 36, 12, 39, a, a, false); int var3;
/*  835 */         for (var3 = 0; var3 < 4; var3++) {
/*  836 */           a(var0, var2, 15 + var3, var3 + 9, 43 - var3, 42 - var3, var3 + 9, 43 - var3, b, b, false);
/*      */         }
/*  838 */         for (var3 = 21; var3 <= 36; var3 += 3)
/*  839 */           a(var0, d, var3, 13, 38, var2); 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPieceEntry
/*      */     extends WorldGenMonumentPiece {
/*      */     public WorldGenMonumentPieceEntry(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1) {
/*  847 */       super(WorldGenFeatureStructurePieceType.T, 1, var0, var1, 1, 1, 1);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPieceEntry(DefinedStructureManager var0, NBTTagCompound var1) {
/*  851 */       super(WorldGenFeatureStructurePieceType.T, var1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  857 */       a(var0, var4, 0, 3, 0, 2, 3, 7, b, b, false);
/*  858 */       a(var0, var4, 5, 3, 0, 7, 3, 7, b, b, false);
/*  859 */       a(var0, var4, 0, 2, 0, 1, 2, 7, b, b, false);
/*  860 */       a(var0, var4, 6, 2, 0, 7, 2, 7, b, b, false);
/*  861 */       a(var0, var4, 0, 1, 0, 0, 1, 7, b, b, false);
/*  862 */       a(var0, var4, 7, 1, 0, 7, 1, 7, b, b, false);
/*      */ 
/*      */       
/*  865 */       a(var0, var4, 0, 1, 7, 7, 3, 7, b, b, false);
/*      */ 
/*      */       
/*  868 */       a(var0, var4, 1, 1, 0, 2, 3, 0, b, b, false);
/*  869 */       a(var0, var4, 5, 1, 0, 6, 3, 0, b, b, false);
/*      */       
/*  871 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.NORTH.c()]) {
/*  872 */         a(var0, var4, 3, 1, 7, 4, 2, 7);
/*      */       }
/*  874 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.WEST.c()]) {
/*  875 */         a(var0, var4, 0, 1, 3, 1, 2, 4);
/*      */       }
/*  877 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.EAST.c()]) {
/*  878 */         a(var0, var4, 6, 1, 3, 7, 2, 4);
/*      */       }
/*      */       
/*  881 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPieceSimple extends WorldGenMonumentPiece {
/*      */     private int p;
/*      */     
/*      */     public WorldGenMonumentPieceSimple(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1, Random var2) {
/*  889 */       super(WorldGenFeatureStructurePieceType.V, 1, var0, var1, 1, 1, 1);
/*  890 */       this.p = var2.nextInt(3);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPieceSimple(DefinedStructureManager var0, NBTTagCompound var1) {
/*  894 */       super(WorldGenFeatureStructurePieceType.V, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  899 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(this.l) / 25 > 0) {
/*  900 */         a(var0, var4, 0, 0, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.DOWN.c()]);
/*      */       }
/*  902 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.l)[EnumDirection.UP.c()] == null) {
/*  903 */         a(var0, var4, 1, 4, 1, 6, 4, 6, a);
/*      */       }
/*      */       
/*  906 */       boolean var7 = (this.p != 0 && var3.nextBoolean() && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.DOWN.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.UP.c()] && this.l.c() > 1);
/*      */       
/*  908 */       if (this.p == 0) {
/*      */         
/*  910 */         a(var0, var4, 0, 1, 0, 2, 1, 2, b, b, false);
/*  911 */         a(var0, var4, 0, 3, 0, 2, 3, 2, b, b, false);
/*  912 */         a(var0, var4, 0, 2, 0, 0, 2, 2, a, a, false);
/*  913 */         a(var0, var4, 1, 2, 0, 2, 2, 0, a, a, false);
/*  914 */         a(var0, e, 1, 2, 1, var4);
/*      */ 
/*      */         
/*  917 */         a(var0, var4, 5, 1, 0, 7, 1, 2, b, b, false);
/*  918 */         a(var0, var4, 5, 3, 0, 7, 3, 2, b, b, false);
/*  919 */         a(var0, var4, 7, 2, 0, 7, 2, 2, a, a, false);
/*  920 */         a(var0, var4, 5, 2, 0, 6, 2, 0, a, a, false);
/*  921 */         a(var0, e, 6, 2, 1, var4);
/*      */ 
/*      */         
/*  924 */         a(var0, var4, 0, 1, 5, 2, 1, 7, b, b, false);
/*  925 */         a(var0, var4, 0, 3, 5, 2, 3, 7, b, b, false);
/*  926 */         a(var0, var4, 0, 2, 5, 0, 2, 7, a, a, false);
/*  927 */         a(var0, var4, 1, 2, 7, 2, 2, 7, a, a, false);
/*  928 */         a(var0, e, 1, 2, 6, var4);
/*      */ 
/*      */         
/*  931 */         a(var0, var4, 5, 1, 5, 7, 1, 7, b, b, false);
/*  932 */         a(var0, var4, 5, 3, 5, 7, 3, 7, b, b, false);
/*  933 */         a(var0, var4, 7, 2, 5, 7, 2, 7, a, a, false);
/*  934 */         a(var0, var4, 5, 2, 7, 6, 2, 7, a, a, false);
/*  935 */         a(var0, e, 6, 2, 6, var4);
/*      */         
/*  937 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.SOUTH.c()]) {
/*  938 */           a(var0, var4, 3, 3, 0, 4, 3, 0, b, b, false);
/*      */         } else {
/*  940 */           a(var0, var4, 3, 3, 0, 4, 3, 1, b, b, false);
/*  941 */           a(var0, var4, 3, 2, 0, 4, 2, 0, a, a, false);
/*  942 */           a(var0, var4, 3, 1, 0, 4, 1, 1, b, b, false);
/*      */         } 
/*  944 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.NORTH.c()]) {
/*  945 */           a(var0, var4, 3, 3, 7, 4, 3, 7, b, b, false);
/*      */         } else {
/*  947 */           a(var0, var4, 3, 3, 6, 4, 3, 7, b, b, false);
/*  948 */           a(var0, var4, 3, 2, 7, 4, 2, 7, a, a, false);
/*  949 */           a(var0, var4, 3, 1, 6, 4, 1, 7, b, b, false);
/*      */         } 
/*  951 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.WEST.c()]) {
/*  952 */           a(var0, var4, 0, 3, 3, 0, 3, 4, b, b, false);
/*      */         } else {
/*  954 */           a(var0, var4, 0, 3, 3, 1, 3, 4, b, b, false);
/*  955 */           a(var0, var4, 0, 2, 3, 0, 2, 4, a, a, false);
/*  956 */           a(var0, var4, 0, 1, 3, 1, 1, 4, b, b, false);
/*      */         } 
/*  958 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.EAST.c()]) {
/*  959 */           a(var0, var4, 7, 3, 3, 7, 3, 4, b, b, false);
/*      */         } else {
/*  961 */           a(var0, var4, 6, 3, 3, 7, 3, 4, b, b, false);
/*  962 */           a(var0, var4, 7, 2, 3, 7, 2, 4, a, a, false);
/*  963 */           a(var0, var4, 6, 1, 3, 7, 1, 4, b, b, false);
/*      */         } 
/*  965 */       } else if (this.p == 1) {
/*      */         
/*  967 */         a(var0, var4, 2, 1, 2, 2, 3, 2, b, b, false);
/*  968 */         a(var0, var4, 2, 1, 5, 2, 3, 5, b, b, false);
/*  969 */         a(var0, var4, 5, 1, 5, 5, 3, 5, b, b, false);
/*  970 */         a(var0, var4, 5, 1, 2, 5, 3, 2, b, b, false);
/*  971 */         a(var0, e, 2, 2, 2, var4);
/*  972 */         a(var0, e, 2, 2, 5, var4);
/*  973 */         a(var0, e, 5, 2, 5, var4);
/*  974 */         a(var0, e, 5, 2, 2, var4);
/*      */ 
/*      */         
/*  977 */         a(var0, var4, 0, 1, 0, 1, 3, 0, b, b, false);
/*  978 */         a(var0, var4, 0, 1, 1, 0, 3, 1, b, b, false);
/*  979 */         a(var0, var4, 0, 1, 7, 1, 3, 7, b, b, false);
/*  980 */         a(var0, var4, 0, 1, 6, 0, 3, 6, b, b, false);
/*  981 */         a(var0, var4, 6, 1, 7, 7, 3, 7, b, b, false);
/*  982 */         a(var0, var4, 7, 1, 6, 7, 3, 6, b, b, false);
/*  983 */         a(var0, var4, 6, 1, 0, 7, 3, 0, b, b, false);
/*  984 */         a(var0, var4, 7, 1, 1, 7, 3, 1, b, b, false);
/*  985 */         a(var0, a, 1, 2, 0, var4);
/*  986 */         a(var0, a, 0, 2, 1, var4);
/*  987 */         a(var0, a, 1, 2, 7, var4);
/*  988 */         a(var0, a, 0, 2, 6, var4);
/*  989 */         a(var0, a, 6, 2, 7, var4);
/*  990 */         a(var0, a, 7, 2, 6, var4);
/*  991 */         a(var0, a, 6, 2, 0, var4);
/*  992 */         a(var0, a, 7, 2, 1, var4);
/*  993 */         if (!WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.SOUTH.c()]) {
/*  994 */           a(var0, var4, 1, 3, 0, 6, 3, 0, b, b, false);
/*  995 */           a(var0, var4, 1, 2, 0, 6, 2, 0, a, a, false);
/*  996 */           a(var0, var4, 1, 1, 0, 6, 1, 0, b, b, false);
/*      */         } 
/*  998 */         if (!WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.NORTH.c()]) {
/*  999 */           a(var0, var4, 1, 3, 7, 6, 3, 7, b, b, false);
/* 1000 */           a(var0, var4, 1, 2, 7, 6, 2, 7, a, a, false);
/* 1001 */           a(var0, var4, 1, 1, 7, 6, 1, 7, b, b, false);
/*      */         } 
/* 1003 */         if (!WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.WEST.c()]) {
/* 1004 */           a(var0, var4, 0, 3, 1, 0, 3, 6, b, b, false);
/* 1005 */           a(var0, var4, 0, 2, 1, 0, 2, 6, a, a, false);
/* 1006 */           a(var0, var4, 0, 1, 1, 0, 1, 6, b, b, false);
/*      */         } 
/* 1008 */         if (!WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.EAST.c()]) {
/* 1009 */           a(var0, var4, 7, 3, 1, 7, 3, 6, b, b, false);
/* 1010 */           a(var0, var4, 7, 2, 1, 7, 2, 6, a, a, false);
/* 1011 */           a(var0, var4, 7, 1, 1, 7, 1, 6, b, b, false);
/*      */         } 
/* 1013 */       } else if (this.p == 2) {
/* 1014 */         a(var0, var4, 0, 1, 0, 0, 1, 7, b, b, false);
/* 1015 */         a(var0, var4, 7, 1, 0, 7, 1, 7, b, b, false);
/* 1016 */         a(var0, var4, 1, 1, 0, 6, 1, 0, b, b, false);
/* 1017 */         a(var0, var4, 1, 1, 7, 6, 1, 7, b, b, false);
/*      */         
/* 1019 */         a(var0, var4, 0, 2, 0, 0, 2, 7, c, c, false);
/* 1020 */         a(var0, var4, 7, 2, 0, 7, 2, 7, c, c, false);
/* 1021 */         a(var0, var4, 1, 2, 0, 6, 2, 0, c, c, false);
/* 1022 */         a(var0, var4, 1, 2, 7, 6, 2, 7, c, c, false);
/*      */         
/* 1024 */         a(var0, var4, 0, 3, 0, 0, 3, 7, b, b, false);
/* 1025 */         a(var0, var4, 7, 3, 0, 7, 3, 7, b, b, false);
/* 1026 */         a(var0, var4, 1, 3, 0, 6, 3, 0, b, b, false);
/* 1027 */         a(var0, var4, 1, 3, 7, 6, 3, 7, b, b, false);
/*      */         
/* 1029 */         a(var0, var4, 0, 1, 3, 0, 2, 4, c, c, false);
/* 1030 */         a(var0, var4, 7, 1, 3, 7, 2, 4, c, c, false);
/* 1031 */         a(var0, var4, 3, 1, 0, 4, 2, 0, c, c, false);
/* 1032 */         a(var0, var4, 3, 1, 7, 4, 2, 7, c, c, false);
/*      */         
/* 1034 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.SOUTH.c()]) {
/* 1035 */           a(var0, var4, 3, 1, 0, 4, 2, 0);
/*      */         }
/* 1037 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.NORTH.c()]) {
/* 1038 */           a(var0, var4, 3, 1, 7, 4, 2, 7);
/*      */         }
/* 1040 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.WEST.c()]) {
/* 1041 */           a(var0, var4, 0, 1, 3, 0, 2, 4);
/*      */         }
/* 1043 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.EAST.c()]) {
/* 1044 */           a(var0, var4, 7, 1, 3, 7, 2, 4);
/*      */         }
/*      */       } 
/* 1047 */       if (var7) {
/* 1048 */         a(var0, var4, 3, 1, 3, 4, 1, 4, b, b, false);
/* 1049 */         a(var0, var4, 3, 2, 3, 4, 2, 4, a, a, false);
/* 1050 */         a(var0, var4, 3, 3, 3, 4, 3, 4, b, b, false);
/*      */       } 
/*      */       
/* 1053 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPieceSimpleT extends WorldGenMonumentPiece {
/*      */     public WorldGenMonumentPieceSimpleT(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1) {
/* 1059 */       super(WorldGenFeatureStructurePieceType.W, 1, var0, var1, 1, 1, 1);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPieceSimpleT(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1063 */       super(WorldGenFeatureStructurePieceType.W, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1068 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(this.l) / 25 > 0) {
/* 1069 */         a(var0, var4, 0, 0, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.DOWN.c()]);
/*      */       }
/* 1071 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.l)[EnumDirection.UP.c()] == null) {
/* 1072 */         a(var0, var4, 1, 4, 1, 6, 4, 6, a);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1077 */       for (int var7 = 1; var7 <= 6; var7++) {
/* 1078 */         for (int var8 = 1; var8 <= 6; var8++) {
/* 1079 */           if (var3.nextInt(3) != 0) {
/* 1080 */             int var9 = 2 + ((var3.nextInt(4) == 0) ? 0 : 1);
/* 1081 */             IBlockData var10 = Blocks.WET_SPONGE.getBlockData();
/* 1082 */             a(var0, var4, var7, var9, var8, var7, 3, var8, var10, var10, false);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1087 */       a(var0, var4, 0, 1, 0, 0, 1, 7, b, b, false);
/* 1088 */       a(var0, var4, 7, 1, 0, 7, 1, 7, b, b, false);
/* 1089 */       a(var0, var4, 1, 1, 0, 6, 1, 0, b, b, false);
/* 1090 */       a(var0, var4, 1, 1, 7, 6, 1, 7, b, b, false);
/*      */       
/* 1092 */       a(var0, var4, 0, 2, 0, 0, 2, 7, c, c, false);
/* 1093 */       a(var0, var4, 7, 2, 0, 7, 2, 7, c, c, false);
/* 1094 */       a(var0, var4, 1, 2, 0, 6, 2, 0, c, c, false);
/* 1095 */       a(var0, var4, 1, 2, 7, 6, 2, 7, c, c, false);
/*      */       
/* 1097 */       a(var0, var4, 0, 3, 0, 0, 3, 7, b, b, false);
/* 1098 */       a(var0, var4, 7, 3, 0, 7, 3, 7, b, b, false);
/* 1099 */       a(var0, var4, 1, 3, 0, 6, 3, 0, b, b, false);
/* 1100 */       a(var0, var4, 1, 3, 7, 6, 3, 7, b, b, false);
/*      */       
/* 1102 */       a(var0, var4, 0, 1, 3, 0, 2, 4, c, c, false);
/* 1103 */       a(var0, var4, 7, 1, 3, 7, 2, 4, c, c, false);
/* 1104 */       a(var0, var4, 3, 1, 0, 4, 2, 0, c, c, false);
/* 1105 */       a(var0, var4, 3, 1, 7, 4, 2, 7, c, c, false);
/*      */       
/* 1107 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.SOUTH.c()]) {
/* 1108 */         a(var0, var4, 3, 1, 0, 4, 2, 0);
/*      */       }
/*      */ 
/*      */       
/* 1112 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPiece5 extends WorldGenMonumentPiece {
/*      */     public WorldGenMonumentPiece5(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1) {
/* 1118 */       super(WorldGenFeatureStructurePieceType.Q, 1, var0, var1, 1, 2, 1);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPiece5(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1122 */       super(WorldGenFeatureStructurePieceType.Q, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1127 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(this.l) / 25 > 0) {
/* 1128 */         a(var0, var4, 0, 0, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(this.l)[EnumDirection.DOWN.c()]);
/*      */       }
/* 1130 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var7 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.l)[EnumDirection.UP.c()];
/* 1131 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var7)[EnumDirection.UP.c()] == null) {
/* 1132 */         a(var0, var4, 1, 8, 1, 6, 8, 6, a);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1137 */       a(var0, var4, 0, 4, 0, 0, 4, 7, b, b, false);
/* 1138 */       a(var0, var4, 7, 4, 0, 7, 4, 7, b, b, false);
/* 1139 */       a(var0, var4, 1, 4, 0, 6, 4, 0, b, b, false);
/* 1140 */       a(var0, var4, 1, 4, 7, 6, 4, 7, b, b, false);
/*      */       
/* 1142 */       a(var0, var4, 2, 4, 1, 2, 4, 2, b, b, false);
/* 1143 */       a(var0, var4, 1, 4, 2, 1, 4, 2, b, b, false);
/* 1144 */       a(var0, var4, 5, 4, 1, 5, 4, 2, b, b, false);
/* 1145 */       a(var0, var4, 6, 4, 2, 6, 4, 2, b, b, false);
/* 1146 */       a(var0, var4, 2, 4, 5, 2, 4, 6, b, b, false);
/* 1147 */       a(var0, var4, 1, 4, 5, 1, 4, 5, b, b, false);
/* 1148 */       a(var0, var4, 5, 4, 5, 5, 4, 6, b, b, false);
/* 1149 */       a(var0, var4, 6, 4, 5, 6, 4, 5, b, b, false);
/*      */       
/* 1151 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var8 = this.l;
/* 1152 */       for (int var9 = 1; var9 <= 5; var9 += 4) {
/* 1153 */         int var10 = 0;
/* 1154 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.SOUTH.c()]) {
/* 1155 */           a(var0, var4, 2, var9, var10, 2, var9 + 2, var10, b, b, false);
/* 1156 */           a(var0, var4, 5, var9, var10, 5, var9 + 2, var10, b, b, false);
/* 1157 */           a(var0, var4, 3, var9 + 2, var10, 4, var9 + 2, var10, b, b, false);
/*      */         } else {
/* 1159 */           a(var0, var4, 0, var9, var10, 7, var9 + 2, var10, b, b, false);
/* 1160 */           a(var0, var4, 0, var9 + 1, var10, 7, var9 + 1, var10, a, a, false);
/*      */         } 
/* 1162 */         var10 = 7;
/* 1163 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.NORTH.c()]) {
/* 1164 */           a(var0, var4, 2, var9, var10, 2, var9 + 2, var10, b, b, false);
/* 1165 */           a(var0, var4, 5, var9, var10, 5, var9 + 2, var10, b, b, false);
/* 1166 */           a(var0, var4, 3, var9 + 2, var10, 4, var9 + 2, var10, b, b, false);
/*      */         } else {
/* 1168 */           a(var0, var4, 0, var9, var10, 7, var9 + 2, var10, b, b, false);
/* 1169 */           a(var0, var4, 0, var9 + 1, var10, 7, var9 + 1, var10, a, a, false);
/*      */         } 
/* 1171 */         int var11 = 0;
/* 1172 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.WEST.c()]) {
/* 1173 */           a(var0, var4, var11, var9, 2, var11, var9 + 2, 2, b, b, false);
/* 1174 */           a(var0, var4, var11, var9, 5, var11, var9 + 2, 5, b, b, false);
/* 1175 */           a(var0, var4, var11, var9 + 2, 3, var11, var9 + 2, 4, b, b, false);
/*      */         } else {
/* 1177 */           a(var0, var4, var11, var9, 0, var11, var9 + 2, 7, b, b, false);
/* 1178 */           a(var0, var4, var11, var9 + 1, 0, var11, var9 + 1, 7, a, a, false);
/*      */         } 
/* 1180 */         var11 = 7;
/* 1181 */         if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.EAST.c()]) {
/* 1182 */           a(var0, var4, var11, var9, 2, var11, var9 + 2, 2, b, b, false);
/* 1183 */           a(var0, var4, var11, var9, 5, var11, var9 + 2, 5, b, b, false);
/* 1184 */           a(var0, var4, var11, var9 + 2, 3, var11, var9 + 2, 4, b, b, false);
/*      */         } else {
/* 1186 */           a(var0, var4, var11, var9, 0, var11, var9 + 2, 7, b, b, false);
/* 1187 */           a(var0, var4, var11, var9 + 1, 0, var11, var9 + 1, 7, a, a, false);
/*      */         } 
/* 1189 */         var8 = var7;
/*      */       } 
/*      */ 
/*      */       
/* 1193 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPiece3 extends WorldGenMonumentPiece {
/*      */     public WorldGenMonumentPiece3(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1) {
/* 1199 */       super(WorldGenFeatureStructurePieceType.O, 1, var0, var1, 2, 1, 1);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPiece3(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1203 */       super(WorldGenFeatureStructurePieceType.O, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1208 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var7 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.l)[EnumDirection.EAST.c()];
/* 1209 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var8 = this.l;
/* 1210 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(this.l) / 25 > 0) {
/* 1211 */         a(var0, var4, 8, 0, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.DOWN.c()]);
/* 1212 */         a(var0, var4, 0, 0, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.DOWN.c()]);
/*      */       } 
/* 1214 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var8)[EnumDirection.UP.c()] == null) {
/* 1215 */         a(var0, var4, 1, 4, 1, 7, 4, 6, a);
/*      */       }
/* 1217 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var7)[EnumDirection.UP.c()] == null) {
/* 1218 */         a(var0, var4, 8, 4, 1, 14, 4, 6, a);
/*      */       }
/*      */ 
/*      */       
/* 1222 */       a(var0, var4, 0, 3, 0, 0, 3, 7, b, b, false);
/* 1223 */       a(var0, var4, 15, 3, 0, 15, 3, 7, b, b, false);
/* 1224 */       a(var0, var4, 1, 3, 0, 15, 3, 0, b, b, false);
/* 1225 */       a(var0, var4, 1, 3, 7, 14, 3, 7, b, b, false);
/* 1226 */       a(var0, var4, 0, 2, 0, 0, 2, 7, a, a, false);
/* 1227 */       a(var0, var4, 15, 2, 0, 15, 2, 7, a, a, false);
/* 1228 */       a(var0, var4, 1, 2, 0, 15, 2, 0, a, a, false);
/* 1229 */       a(var0, var4, 1, 2, 7, 14, 2, 7, a, a, false);
/* 1230 */       a(var0, var4, 0, 1, 0, 0, 1, 7, b, b, false);
/* 1231 */       a(var0, var4, 15, 1, 0, 15, 1, 7, b, b, false);
/* 1232 */       a(var0, var4, 1, 1, 0, 15, 1, 0, b, b, false);
/* 1233 */       a(var0, var4, 1, 1, 7, 14, 1, 7, b, b, false);
/*      */ 
/*      */       
/* 1236 */       a(var0, var4, 5, 1, 0, 10, 1, 4, b, b, false);
/* 1237 */       a(var0, var4, 6, 2, 0, 9, 2, 3, a, a, false);
/* 1238 */       a(var0, var4, 5, 3, 0, 10, 3, 4, b, b, false);
/*      */       
/* 1240 */       a(var0, e, 6, 2, 3, var4);
/* 1241 */       a(var0, e, 9, 2, 3, var4);
/*      */ 
/*      */       
/* 1244 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.SOUTH.c()]) {
/* 1245 */         a(var0, var4, 3, 1, 0, 4, 2, 0);
/*      */       }
/* 1247 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.NORTH.c()]) {
/* 1248 */         a(var0, var4, 3, 1, 7, 4, 2, 7);
/*      */       }
/* 1250 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.WEST.c()]) {
/* 1251 */         a(var0, var4, 0, 1, 3, 0, 2, 4);
/*      */       }
/* 1253 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.SOUTH.c()]) {
/* 1254 */         a(var0, var4, 11, 1, 0, 12, 2, 0);
/*      */       }
/* 1256 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.NORTH.c()]) {
/* 1257 */         a(var0, var4, 11, 1, 7, 12, 2, 7);
/*      */       }
/* 1259 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.EAST.c()]) {
/* 1260 */         a(var0, var4, 15, 1, 3, 15, 2, 4);
/*      */       }
/*      */       
/* 1263 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPiece7 extends WorldGenMonumentPiece {
/*      */     public WorldGenMonumentPiece7(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1) {
/* 1269 */       super(WorldGenFeatureStructurePieceType.S, 1, var0, var1, 1, 1, 2);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPiece7(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1273 */       super(WorldGenFeatureStructurePieceType.S, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1278 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var7 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.l)[EnumDirection.NORTH.c()];
/* 1279 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var8 = this.l;
/* 1280 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(this.l) / 25 > 0) {
/* 1281 */         a(var0, var4, 0, 8, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.DOWN.c()]);
/* 1282 */         a(var0, var4, 0, 0, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.DOWN.c()]);
/*      */       } 
/* 1284 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var8)[EnumDirection.UP.c()] == null) {
/* 1285 */         a(var0, var4, 1, 4, 1, 6, 4, 7, a);
/*      */       }
/* 1287 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var7)[EnumDirection.UP.c()] == null) {
/* 1288 */         a(var0, var4, 1, 4, 8, 6, 4, 14, a);
/*      */       }
/*      */ 
/*      */       
/* 1292 */       a(var0, var4, 0, 3, 0, 0, 3, 15, b, b, false);
/* 1293 */       a(var0, var4, 7, 3, 0, 7, 3, 15, b, b, false);
/* 1294 */       a(var0, var4, 1, 3, 0, 7, 3, 0, b, b, false);
/* 1295 */       a(var0, var4, 1, 3, 15, 6, 3, 15, b, b, false);
/* 1296 */       a(var0, var4, 0, 2, 0, 0, 2, 15, a, a, false);
/* 1297 */       a(var0, var4, 7, 2, 0, 7, 2, 15, a, a, false);
/* 1298 */       a(var0, var4, 1, 2, 0, 7, 2, 0, a, a, false);
/* 1299 */       a(var0, var4, 1, 2, 15, 6, 2, 15, a, a, false);
/* 1300 */       a(var0, var4, 0, 1, 0, 0, 1, 15, b, b, false);
/* 1301 */       a(var0, var4, 7, 1, 0, 7, 1, 15, b, b, false);
/* 1302 */       a(var0, var4, 1, 1, 0, 7, 1, 0, b, b, false);
/* 1303 */       a(var0, var4, 1, 1, 15, 6, 1, 15, b, b, false);
/*      */ 
/*      */       
/* 1306 */       a(var0, var4, 1, 1, 1, 1, 1, 2, b, b, false);
/* 1307 */       a(var0, var4, 6, 1, 1, 6, 1, 2, b, b, false);
/* 1308 */       a(var0, var4, 1, 3, 1, 1, 3, 2, b, b, false);
/* 1309 */       a(var0, var4, 6, 3, 1, 6, 3, 2, b, b, false);
/* 1310 */       a(var0, var4, 1, 1, 13, 1, 1, 14, b, b, false);
/* 1311 */       a(var0, var4, 6, 1, 13, 6, 1, 14, b, b, false);
/* 1312 */       a(var0, var4, 1, 3, 13, 1, 3, 14, b, b, false);
/* 1313 */       a(var0, var4, 6, 3, 13, 6, 3, 14, b, b, false);
/*      */ 
/*      */       
/* 1316 */       a(var0, var4, 2, 1, 6, 2, 3, 6, b, b, false);
/* 1317 */       a(var0, var4, 5, 1, 6, 5, 3, 6, b, b, false);
/* 1318 */       a(var0, var4, 2, 1, 9, 2, 3, 9, b, b, false);
/* 1319 */       a(var0, var4, 5, 1, 9, 5, 3, 9, b, b, false);
/*      */       
/* 1321 */       a(var0, var4, 3, 2, 6, 4, 2, 6, b, b, false);
/* 1322 */       a(var0, var4, 3, 2, 9, 4, 2, 9, b, b, false);
/* 1323 */       a(var0, var4, 2, 2, 7, 2, 2, 8, b, b, false);
/* 1324 */       a(var0, var4, 5, 2, 7, 5, 2, 8, b, b, false);
/*      */       
/* 1326 */       a(var0, e, 2, 2, 5, var4);
/* 1327 */       a(var0, e, 5, 2, 5, var4);
/* 1328 */       a(var0, e, 2, 2, 10, var4);
/* 1329 */       a(var0, e, 5, 2, 10, var4);
/* 1330 */       a(var0, b, 2, 3, 5, var4);
/* 1331 */       a(var0, b, 5, 3, 5, var4);
/* 1332 */       a(var0, b, 2, 3, 10, var4);
/* 1333 */       a(var0, b, 5, 3, 10, var4);
/*      */ 
/*      */       
/* 1336 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.SOUTH.c()]) {
/* 1337 */         a(var0, var4, 3, 1, 0, 4, 2, 0);
/*      */       }
/* 1339 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.EAST.c()]) {
/* 1340 */         a(var0, var4, 7, 1, 3, 7, 2, 4);
/*      */       }
/* 1342 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.WEST.c()]) {
/* 1343 */         a(var0, var4, 0, 1, 3, 0, 2, 4);
/*      */       }
/* 1345 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.NORTH.c()]) {
/* 1346 */         a(var0, var4, 3, 1, 15, 4, 2, 15);
/*      */       }
/* 1348 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.WEST.c()]) {
/* 1349 */         a(var0, var4, 0, 1, 11, 0, 2, 12);
/*      */       }
/* 1351 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.EAST.c()]) {
/* 1352 */         a(var0, var4, 7, 1, 11, 7, 2, 12);
/*      */       }
/*      */       
/* 1355 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPiece4 extends WorldGenMonumentPiece {
/*      */     public WorldGenMonumentPiece4(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1) {
/* 1361 */       super(WorldGenFeatureStructurePieceType.P, 1, var0, var1, 2, 2, 1);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPiece4(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1365 */       super(WorldGenFeatureStructurePieceType.P, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1370 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var7 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.l)[EnumDirection.EAST.c()];
/* 1371 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var8 = this.l;
/* 1372 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var9 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var8)[EnumDirection.UP.c()];
/* 1373 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var10 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var7)[EnumDirection.UP.c()];
/*      */       
/* 1375 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(this.l) / 25 > 0) {
/* 1376 */         a(var0, var4, 8, 0, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.DOWN.c()]);
/* 1377 */         a(var0, var4, 0, 0, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.DOWN.c()]);
/*      */       } 
/* 1379 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var9)[EnumDirection.UP.c()] == null) {
/* 1380 */         a(var0, var4, 1, 8, 1, 7, 8, 6, a);
/*      */       }
/* 1382 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var10)[EnumDirection.UP.c()] == null) {
/* 1383 */         a(var0, var4, 8, 8, 1, 14, 8, 6, a);
/*      */       }
/*      */ 
/*      */       
/* 1387 */       for (int var11 = 1; var11 <= 7; var11++) {
/* 1388 */         IBlockData var12 = b;
/* 1389 */         if (var11 == 2 || var11 == 6) {
/* 1390 */           var12 = a;
/*      */         }
/* 1392 */         a(var0, var4, 0, var11, 0, 0, var11, 7, var12, var12, false);
/* 1393 */         a(var0, var4, 15, var11, 0, 15, var11, 7, var12, var12, false);
/* 1394 */         a(var0, var4, 1, var11, 0, 15, var11, 0, var12, var12, false);
/* 1395 */         a(var0, var4, 1, var11, 7, 14, var11, 7, var12, var12, false);
/*      */       } 
/*      */ 
/*      */       
/* 1399 */       a(var0, var4, 2, 1, 3, 2, 7, 4, b, b, false);
/* 1400 */       a(var0, var4, 3, 1, 2, 4, 7, 2, b, b, false);
/* 1401 */       a(var0, var4, 3, 1, 5, 4, 7, 5, b, b, false);
/* 1402 */       a(var0, var4, 13, 1, 3, 13, 7, 4, b, b, false);
/* 1403 */       a(var0, var4, 11, 1, 2, 12, 7, 2, b, b, false);
/* 1404 */       a(var0, var4, 11, 1, 5, 12, 7, 5, b, b, false);
/*      */       
/* 1406 */       a(var0, var4, 5, 1, 3, 5, 3, 4, b, b, false);
/* 1407 */       a(var0, var4, 10, 1, 3, 10, 3, 4, b, b, false);
/*      */       
/* 1409 */       a(var0, var4, 5, 7, 2, 10, 7, 5, b, b, false);
/* 1410 */       a(var0, var4, 5, 5, 2, 5, 7, 2, b, b, false);
/* 1411 */       a(var0, var4, 10, 5, 2, 10, 7, 2, b, b, false);
/* 1412 */       a(var0, var4, 5, 5, 5, 5, 7, 5, b, b, false);
/* 1413 */       a(var0, var4, 10, 5, 5, 10, 7, 5, b, b, false);
/* 1414 */       a(var0, b, 6, 6, 2, var4);
/* 1415 */       a(var0, b, 9, 6, 2, var4);
/* 1416 */       a(var0, b, 6, 6, 5, var4);
/* 1417 */       a(var0, b, 9, 6, 5, var4);
/*      */       
/* 1419 */       a(var0, var4, 5, 4, 3, 6, 4, 4, b, b, false);
/* 1420 */       a(var0, var4, 9, 4, 3, 10, 4, 4, b, b, false);
/* 1421 */       a(var0, e, 5, 4, 2, var4);
/* 1422 */       a(var0, e, 5, 4, 5, var4);
/* 1423 */       a(var0, e, 10, 4, 2, var4);
/* 1424 */       a(var0, e, 10, 4, 5, var4);
/*      */ 
/*      */       
/* 1427 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.SOUTH.c()]) {
/* 1428 */         a(var0, var4, 3, 1, 0, 4, 2, 0);
/*      */       }
/* 1430 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.NORTH.c()]) {
/* 1431 */         a(var0, var4, 3, 1, 7, 4, 2, 7);
/*      */       }
/* 1433 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.WEST.c()]) {
/* 1434 */         a(var0, var4, 0, 1, 3, 0, 2, 4);
/*      */       }
/* 1436 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.SOUTH.c()]) {
/* 1437 */         a(var0, var4, 11, 1, 0, 12, 2, 0);
/*      */       }
/* 1439 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.NORTH.c()]) {
/* 1440 */         a(var0, var4, 11, 1, 7, 12, 2, 7);
/*      */       }
/* 1442 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.EAST.c()]) {
/* 1443 */         a(var0, var4, 15, 1, 3, 15, 2, 4);
/*      */       }
/* 1445 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var9)[EnumDirection.SOUTH.c()]) {
/* 1446 */         a(var0, var4, 3, 5, 0, 4, 6, 0);
/*      */       }
/* 1448 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var9)[EnumDirection.NORTH.c()]) {
/* 1449 */         a(var0, var4, 3, 5, 7, 4, 6, 7);
/*      */       }
/* 1451 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var9)[EnumDirection.WEST.c()]) {
/* 1452 */         a(var0, var4, 0, 5, 3, 0, 6, 4);
/*      */       }
/* 1454 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var10)[EnumDirection.SOUTH.c()]) {
/* 1455 */         a(var0, var4, 11, 5, 0, 12, 6, 0);
/*      */       }
/* 1457 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var10)[EnumDirection.NORTH.c()]) {
/* 1458 */         a(var0, var4, 11, 5, 7, 12, 6, 7);
/*      */       }
/* 1460 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var10)[EnumDirection.EAST.c()]) {
/* 1461 */         a(var0, var4, 15, 5, 3, 15, 6, 4);
/*      */       }
/*      */       
/* 1464 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPiece6 extends WorldGenMonumentPiece {
/*      */     public WorldGenMonumentPiece6(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1) {
/* 1470 */       super(WorldGenFeatureStructurePieceType.R, 1, var0, var1, 1, 2, 2);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPiece6(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1474 */       super(WorldGenFeatureStructurePieceType.R, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1479 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var7 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(this.l)[EnumDirection.NORTH.c()];
/* 1480 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var8 = this.l;
/* 1481 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var9 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var7)[EnumDirection.UP.c()];
/* 1482 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var10 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var8)[EnumDirection.UP.c()];
/* 1483 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(this.l) / 25 > 0) {
/* 1484 */         a(var0, var4, 0, 8, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.DOWN.c()]);
/* 1485 */         a(var0, var4, 0, 0, WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.DOWN.c()]);
/*      */       } 
/* 1487 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var10)[EnumDirection.UP.c()] == null) {
/* 1488 */         a(var0, var4, 1, 8, 1, 6, 8, 7, a);
/*      */       }
/* 1490 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var9)[EnumDirection.UP.c()] == null) {
/* 1491 */         a(var0, var4, 1, 8, 8, 6, 8, 14, a);
/*      */       }
/*      */       
/*      */       int var11;
/* 1495 */       for (var11 = 1; var11 <= 7; var11++) {
/* 1496 */         IBlockData var12 = b;
/* 1497 */         if (var11 == 2 || var11 == 6) {
/* 1498 */           var12 = a;
/*      */         }
/* 1500 */         a(var0, var4, 0, var11, 0, 0, var11, 15, var12, var12, false);
/* 1501 */         a(var0, var4, 7, var11, 0, 7, var11, 15, var12, var12, false);
/* 1502 */         a(var0, var4, 1, var11, 0, 6, var11, 0, var12, var12, false);
/* 1503 */         a(var0, var4, 1, var11, 15, 6, var11, 15, var12, var12, false);
/*      */       } 
/*      */ 
/*      */       
/* 1507 */       for (var11 = 1; var11 <= 7; var11++) {
/* 1508 */         IBlockData var12 = c;
/* 1509 */         if (var11 == 2 || var11 == 6) {
/* 1510 */           var12 = e;
/*      */         }
/* 1512 */         a(var0, var4, 3, var11, 7, 4, var11, 8, var12, var12, false);
/*      */       } 
/*      */ 
/*      */       
/* 1516 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.SOUTH.c()]) {
/* 1517 */         a(var0, var4, 3, 1, 0, 4, 2, 0);
/*      */       }
/* 1519 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.EAST.c()]) {
/* 1520 */         a(var0, var4, 7, 1, 3, 7, 2, 4);
/*      */       }
/* 1522 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var8)[EnumDirection.WEST.c()]) {
/* 1523 */         a(var0, var4, 0, 1, 3, 0, 2, 4);
/*      */       }
/* 1525 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.NORTH.c()]) {
/* 1526 */         a(var0, var4, 3, 1, 15, 4, 2, 15);
/*      */       }
/* 1528 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.WEST.c()]) {
/* 1529 */         a(var0, var4, 0, 1, 11, 0, 2, 12);
/*      */       }
/* 1531 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var7)[EnumDirection.EAST.c()]) {
/* 1532 */         a(var0, var4, 7, 1, 11, 7, 2, 12);
/*      */       }
/*      */       
/* 1535 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var10)[EnumDirection.SOUTH.c()]) {
/* 1536 */         a(var0, var4, 3, 5, 0, 4, 6, 0);
/*      */       }
/* 1538 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var10)[EnumDirection.EAST.c()]) {
/* 1539 */         a(var0, var4, 7, 5, 3, 7, 6, 4);
/* 1540 */         a(var0, var4, 5, 4, 2, 6, 4, 5, b, b, false);
/* 1541 */         a(var0, var4, 6, 1, 2, 6, 3, 2, b, b, false);
/* 1542 */         a(var0, var4, 6, 1, 5, 6, 3, 5, b, b, false);
/*      */       } 
/* 1544 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var10)[EnumDirection.WEST.c()]) {
/* 1545 */         a(var0, var4, 0, 5, 3, 0, 6, 4);
/* 1546 */         a(var0, var4, 1, 4, 2, 2, 4, 5, b, b, false);
/* 1547 */         a(var0, var4, 1, 1, 2, 1, 3, 2, b, b, false);
/* 1548 */         a(var0, var4, 1, 1, 5, 1, 3, 5, b, b, false);
/*      */       } 
/* 1550 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var9)[EnumDirection.NORTH.c()]) {
/* 1551 */         a(var0, var4, 3, 5, 15, 4, 6, 15);
/*      */       }
/* 1553 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var9)[EnumDirection.WEST.c()]) {
/* 1554 */         a(var0, var4, 0, 5, 11, 0, 6, 12);
/* 1555 */         a(var0, var4, 1, 4, 10, 2, 4, 13, b, b, false);
/* 1556 */         a(var0, var4, 1, 1, 10, 1, 3, 10, b, b, false);
/* 1557 */         a(var0, var4, 1, 1, 13, 1, 3, 13, b, b, false);
/*      */       } 
/* 1559 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var9)[EnumDirection.EAST.c()]) {
/* 1560 */         a(var0, var4, 7, 5, 11, 7, 6, 12);
/* 1561 */         a(var0, var4, 5, 4, 10, 6, 4, 13, b, b, false);
/* 1562 */         a(var0, var4, 6, 1, 10, 6, 3, 10, b, b, false);
/* 1563 */         a(var0, var4, 6, 1, 13, 6, 3, 13, b, b, false);
/*      */       } 
/*      */       
/* 1566 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPiece2 extends WorldGenMonumentPiece {
/*      */     public WorldGenMonumentPiece2(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1) {
/* 1572 */       super(WorldGenFeatureStructurePieceType.N, 1, var0, var1, 2, 2, 2);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPiece2(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1576 */       super(WorldGenFeatureStructurePieceType.N, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1581 */       a(var0, var4, 1, 8, 0, 14, 8, 14, a);
/*      */ 
/*      */ 
/*      */       
/* 1585 */       int var7 = 7;
/* 1586 */       IBlockData var8 = b;
/* 1587 */       a(var0, var4, 0, 7, 0, 0, 7, 15, var8, var8, false);
/* 1588 */       a(var0, var4, 15, 7, 0, 15, 7, 15, var8, var8, false);
/* 1589 */       a(var0, var4, 1, 7, 0, 15, 7, 0, var8, var8, false);
/* 1590 */       a(var0, var4, 1, 7, 15, 14, 7, 15, var8, var8, false);
/*      */       
/* 1592 */       for (var7 = 1; var7 <= 6; var7++) {
/* 1593 */         var8 = b;
/* 1594 */         if (var7 == 2 || var7 == 6) {
/* 1595 */           var8 = a;
/*      */         }
/*      */         
/* 1598 */         for (int var9 = 0; var9 <= 15; var9 += 15) {
/* 1599 */           a(var0, var4, var9, var7, 0, var9, var7, 1, var8, var8, false);
/* 1600 */           a(var0, var4, var9, var7, 6, var9, var7, 9, var8, var8, false);
/* 1601 */           a(var0, var4, var9, var7, 14, var9, var7, 15, var8, var8, false);
/*      */         } 
/* 1603 */         a(var0, var4, 1, var7, 0, 1, var7, 0, var8, var8, false);
/* 1604 */         a(var0, var4, 6, var7, 0, 9, var7, 0, var8, var8, false);
/* 1605 */         a(var0, var4, 14, var7, 0, 14, var7, 0, var8, var8, false);
/*      */         
/* 1607 */         a(var0, var4, 1, var7, 15, 14, var7, 15, var8, var8, false);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1612 */       a(var0, var4, 6, 3, 6, 9, 6, 9, c, c, false);
/* 1613 */       a(var0, var4, 7, 4, 7, 8, 5, 8, Blocks.GOLD_BLOCK.getBlockData(), Blocks.GOLD_BLOCK.getBlockData(), false);
/* 1614 */       for (var7 = 3; var7 <= 6; var7 += 3) {
/* 1615 */         for (int i = 6; i <= 9; i += 3) {
/* 1616 */           a(var0, e, i, var7, 6, var4);
/* 1617 */           a(var0, e, i, var7, 9, var4);
/*      */         } 
/*      */       } 
/*      */       
/* 1621 */       a(var0, var4, 5, 1, 6, 5, 2, 6, b, b, false);
/* 1622 */       a(var0, var4, 5, 1, 9, 5, 2, 9, b, b, false);
/* 1623 */       a(var0, var4, 10, 1, 6, 10, 2, 6, b, b, false);
/* 1624 */       a(var0, var4, 10, 1, 9, 10, 2, 9, b, b, false);
/* 1625 */       a(var0, var4, 6, 1, 5, 6, 2, 5, b, b, false);
/* 1626 */       a(var0, var4, 9, 1, 5, 9, 2, 5, b, b, false);
/* 1627 */       a(var0, var4, 6, 1, 10, 6, 2, 10, b, b, false);
/* 1628 */       a(var0, var4, 9, 1, 10, 9, 2, 10, b, b, false);
/*      */       
/* 1630 */       a(var0, var4, 5, 2, 5, 5, 6, 5, b, b, false);
/* 1631 */       a(var0, var4, 5, 2, 10, 5, 6, 10, b, b, false);
/* 1632 */       a(var0, var4, 10, 2, 5, 10, 6, 5, b, b, false);
/* 1633 */       a(var0, var4, 10, 2, 10, 10, 6, 10, b, b, false);
/*      */       
/* 1635 */       a(var0, var4, 5, 7, 1, 5, 7, 6, b, b, false);
/* 1636 */       a(var0, var4, 10, 7, 1, 10, 7, 6, b, b, false);
/* 1637 */       a(var0, var4, 5, 7, 9, 5, 7, 14, b, b, false);
/* 1638 */       a(var0, var4, 10, 7, 9, 10, 7, 14, b, b, false);
/*      */       
/* 1640 */       a(var0, var4, 1, 7, 5, 6, 7, 5, b, b, false);
/* 1641 */       a(var0, var4, 1, 7, 10, 6, 7, 10, b, b, false);
/* 1642 */       a(var0, var4, 9, 7, 5, 14, 7, 5, b, b, false);
/* 1643 */       a(var0, var4, 9, 7, 10, 14, 7, 10, b, b, false);
/*      */ 
/*      */       
/* 1646 */       a(var0, var4, 2, 1, 2, 2, 1, 3, b, b, false);
/* 1647 */       a(var0, var4, 3, 1, 2, 3, 1, 2, b, b, false);
/* 1648 */       a(var0, var4, 13, 1, 2, 13, 1, 3, b, b, false);
/* 1649 */       a(var0, var4, 12, 1, 2, 12, 1, 2, b, b, false);
/* 1650 */       a(var0, var4, 2, 1, 12, 2, 1, 13, b, b, false);
/* 1651 */       a(var0, var4, 3, 1, 13, 3, 1, 13, b, b, false);
/* 1652 */       a(var0, var4, 13, 1, 12, 13, 1, 13, b, b, false);
/* 1653 */       a(var0, var4, 12, 1, 13, 12, 1, 13, b, b, false);
/*      */       
/* 1655 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPiece8 extends WorldGenMonumentPiece {
/*      */     private int p;
/*      */     
/*      */     public WorldGenMonumentPiece8(EnumDirection var0, StructureBoundingBox var1, int var2) {
/* 1663 */       super(WorldGenFeatureStructurePieceType.X, var0, var1);
/* 1664 */       this.p = var2 & 0x1;
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPiece8(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1668 */       super(WorldGenFeatureStructurePieceType.X, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1673 */       if (this.p == 0) {
/* 1674 */         int var7; for (var7 = 0; var7 < 4; var7++) {
/* 1675 */           a(var0, var4, 10 - var7, 3 - var7, 20 - var7, 12 + var7, 3 - var7, 20, b, b, false);
/*      */         }
/* 1677 */         a(var0, var4, 7, 0, 6, 15, 0, 16, b, b, false);
/* 1678 */         a(var0, var4, 6, 0, 6, 6, 3, 20, b, b, false);
/* 1679 */         a(var0, var4, 16, 0, 6, 16, 3, 20, b, b, false);
/* 1680 */         a(var0, var4, 7, 1, 7, 7, 1, 20, b, b, false);
/* 1681 */         a(var0, var4, 15, 1, 7, 15, 1, 20, b, b, false);
/*      */         
/* 1683 */         a(var0, var4, 7, 1, 6, 9, 3, 6, b, b, false);
/* 1684 */         a(var0, var4, 13, 1, 6, 15, 3, 6, b, b, false);
/* 1685 */         a(var0, var4, 8, 1, 7, 9, 1, 7, b, b, false);
/* 1686 */         a(var0, var4, 13, 1, 7, 14, 1, 7, b, b, false);
/* 1687 */         a(var0, var4, 9, 0, 5, 13, 0, 5, b, b, false);
/*      */         
/* 1689 */         a(var0, var4, 10, 0, 7, 12, 0, 7, c, c, false);
/* 1690 */         a(var0, var4, 8, 0, 10, 8, 0, 12, c, c, false);
/* 1691 */         a(var0, var4, 14, 0, 10, 14, 0, 12, c, c, false);
/*      */         
/* 1693 */         for (var7 = 18; var7 >= 7; var7 -= 3) {
/* 1694 */           a(var0, e, 6, 3, var7, var4);
/* 1695 */           a(var0, e, 16, 3, var7, var4);
/*      */         } 
/* 1697 */         a(var0, e, 10, 0, 10, var4);
/* 1698 */         a(var0, e, 12, 0, 10, var4);
/* 1699 */         a(var0, e, 10, 0, 12, var4);
/* 1700 */         a(var0, e, 12, 0, 12, var4);
/*      */         
/* 1702 */         a(var0, e, 8, 3, 6, var4);
/* 1703 */         a(var0, e, 14, 3, 6, var4);
/*      */ 
/*      */         
/* 1706 */         a(var0, b, 4, 2, 4, var4);
/* 1707 */         a(var0, e, 4, 1, 4, var4);
/* 1708 */         a(var0, b, 4, 0, 4, var4);
/*      */         
/* 1710 */         a(var0, b, 18, 2, 4, var4);
/* 1711 */         a(var0, e, 18, 1, 4, var4);
/* 1712 */         a(var0, b, 18, 0, 4, var4);
/*      */         
/* 1714 */         a(var0, b, 4, 2, 18, var4);
/* 1715 */         a(var0, e, 4, 1, 18, var4);
/* 1716 */         a(var0, b, 4, 0, 18, var4);
/*      */         
/* 1718 */         a(var0, b, 18, 2, 18, var4);
/* 1719 */         a(var0, e, 18, 1, 18, var4);
/* 1720 */         a(var0, b, 18, 0, 18, var4);
/*      */ 
/*      */         
/* 1723 */         a(var0, b, 9, 7, 20, var4);
/* 1724 */         a(var0, b, 13, 7, 20, var4);
/* 1725 */         a(var0, var4, 6, 0, 21, 7, 4, 21, b, b, false);
/* 1726 */         a(var0, var4, 15, 0, 21, 16, 4, 21, b, b, false);
/*      */         
/* 1728 */         a(var0, var4, 11, 2, 16);
/* 1729 */       } else if (this.p == 1) {
/* 1730 */         a(var0, var4, 9, 3, 18, 13, 3, 20, b, b, false);
/* 1731 */         a(var0, var4, 9, 0, 18, 9, 2, 18, b, b, false);
/* 1732 */         a(var0, var4, 13, 0, 18, 13, 2, 18, b, b, false);
/* 1733 */         int var7 = 9;
/* 1734 */         int var8 = 20;
/* 1735 */         int var9 = 5; int var10;
/* 1736 */         for (var10 = 0; var10 < 2; var10++) {
/* 1737 */           a(var0, b, var7, 6, 20, var4);
/* 1738 */           a(var0, e, var7, 5, 20, var4);
/* 1739 */           a(var0, b, var7, 4, 20, var4);
/* 1740 */           var7 = 13;
/*      */         } 
/*      */         
/* 1743 */         a(var0, var4, 7, 3, 7, 15, 3, 14, b, b, false);
/* 1744 */         var7 = 10;
/* 1745 */         for (var10 = 0; var10 < 2; var10++) {
/* 1746 */           a(var0, var4, var7, 0, 10, var7, 6, 10, b, b, false);
/* 1747 */           a(var0, var4, var7, 0, 12, var7, 6, 12, b, b, false);
/* 1748 */           a(var0, e, var7, 0, 10, var4);
/* 1749 */           a(var0, e, var7, 0, 12, var4);
/* 1750 */           a(var0, e, var7, 4, 10, var4);
/* 1751 */           a(var0, e, var7, 4, 12, var4);
/* 1752 */           var7 = 12;
/*      */         } 
/* 1754 */         var7 = 8;
/* 1755 */         for (var10 = 0; var10 < 2; var10++) {
/* 1756 */           a(var0, var4, var7, 0, 7, var7, 2, 7, b, b, false);
/* 1757 */           a(var0, var4, var7, 0, 14, var7, 2, 14, b, b, false);
/* 1758 */           var7 = 14;
/*      */         } 
/* 1760 */         a(var0, var4, 8, 3, 8, 8, 3, 13, c, c, false);
/* 1761 */         a(var0, var4, 14, 3, 8, 14, 3, 13, c, c, false);
/*      */         
/* 1763 */         a(var0, var4, 11, 5, 13);
/*      */       } 
/*      */       
/* 1766 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenMonumentPiecePenthouse extends WorldGenMonumentPiece {
/*      */     public WorldGenMonumentPiecePenthouse(EnumDirection var0, StructureBoundingBox var1) {
/* 1772 */       super(WorldGenFeatureStructurePieceType.U, var0, var1);
/*      */     }
/*      */     
/*      */     public WorldGenMonumentPiecePenthouse(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1776 */       super(WorldGenFeatureStructurePieceType.U, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1781 */       a(var0, var4, 2, -1, 2, 11, -1, 11, b, b, false);
/* 1782 */       a(var0, var4, 0, -1, 0, 1, -1, 11, a, a, false);
/* 1783 */       a(var0, var4, 12, -1, 0, 13, -1, 11, a, a, false);
/* 1784 */       a(var0, var4, 2, -1, 0, 11, -1, 1, a, a, false);
/* 1785 */       a(var0, var4, 2, -1, 12, 11, -1, 13, a, a, false);
/*      */       
/* 1787 */       a(var0, var4, 0, 0, 0, 0, 0, 13, b, b, false);
/* 1788 */       a(var0, var4, 13, 0, 0, 13, 0, 13, b, b, false);
/* 1789 */       a(var0, var4, 1, 0, 0, 12, 0, 0, b, b, false);
/* 1790 */       a(var0, var4, 1, 0, 13, 12, 0, 13, b, b, false);
/*      */       int var7;
/* 1792 */       for (var7 = 2; var7 <= 11; var7 += 3) {
/* 1793 */         a(var0, e, 0, 0, var7, var4);
/* 1794 */         a(var0, e, 13, 0, var7, var4);
/* 1795 */         a(var0, e, var7, 0, 0, var4);
/*      */       } 
/*      */       
/* 1798 */       a(var0, var4, 2, 0, 3, 4, 0, 9, b, b, false);
/* 1799 */       a(var0, var4, 9, 0, 3, 11, 0, 9, b, b, false);
/* 1800 */       a(var0, var4, 4, 0, 9, 9, 0, 11, b, b, false);
/* 1801 */       a(var0, b, 5, 0, 8, var4);
/* 1802 */       a(var0, b, 8, 0, 8, var4);
/* 1803 */       a(var0, b, 10, 0, 10, var4);
/* 1804 */       a(var0, b, 3, 0, 10, var4);
/* 1805 */       a(var0, var4, 3, 0, 3, 3, 0, 7, c, c, false);
/* 1806 */       a(var0, var4, 10, 0, 3, 10, 0, 7, c, c, false);
/* 1807 */       a(var0, var4, 6, 0, 10, 7, 0, 10, c, c, false);
/*      */       
/* 1809 */       var7 = 3;
/* 1810 */       for (int var8 = 0; var8 < 2; var8++) {
/* 1811 */         for (int var9 = 2; var9 <= 8; var9 += 3) {
/* 1812 */           a(var0, var4, var7, 0, var9, var7, 2, var9, b, b, false);
/*      */         }
/* 1814 */         var7 = 10;
/*      */       } 
/* 1816 */       a(var0, var4, 5, 0, 10, 5, 2, 10, b, b, false);
/* 1817 */       a(var0, var4, 8, 0, 10, 8, 2, 10, b, b, false);
/*      */       
/* 1819 */       a(var0, var4, 6, -1, 7, 7, -1, 8, c, c, false);
/*      */ 
/*      */       
/* 1822 */       a(var0, var4, 6, -1, 3, 7, -1, 4);
/*      */       
/* 1824 */       a(var0, var4, 6, 1, 6);
/*      */       
/* 1826 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   static class WorldGenMonumentStateTracker {
/*      */     private final int a;
/* 1832 */     private final WorldGenMonumentStateTracker[] b = new WorldGenMonumentStateTracker[6];
/* 1833 */     private final boolean[] c = new boolean[6];
/*      */     private boolean d;
/*      */     private boolean e;
/*      */     private int f;
/*      */     
/*      */     public WorldGenMonumentStateTracker(int var0) {
/* 1839 */       this.a = var0;
/*      */     }
/*      */     
/*      */     public void a(EnumDirection var0, WorldGenMonumentStateTracker var1) {
/* 1843 */       this.b[var0.c()] = var1;
/* 1844 */       var1.b[var0.opposite().c()] = this;
/*      */     }
/*      */     
/*      */     public void a() {
/* 1848 */       for (int var0 = 0; var0 < 6; var0++) {
/* 1849 */         this.c[var0] = (this.b[var0] != null);
/*      */       }
/*      */     }
/*      */     
/*      */     public boolean a(int var0) {
/* 1854 */       if (this.e) {
/* 1855 */         return true;
/*      */       }
/* 1857 */       this.f = var0;
/* 1858 */       for (int var1 = 0; var1 < 6; var1++) {
/* 1859 */         if (this.b[var1] != null && this.c[var1] && 
/* 1860 */           (this.b[var1]).f != var0 && this.b[var1].a(var0)) {
/* 1861 */           return true;
/*      */         }
/*      */       } 
/*      */       
/* 1865 */       return false;
/*      */     }
/*      */     
/*      */     public boolean b() {
/* 1869 */       return (this.a >= 75);
/*      */     }
/*      */     
/*      */     public int c() {
/* 1873 */       int var0 = 0;
/* 1874 */       for (int var1 = 0; var1 < 6; var1++) {
/* 1875 */         if (this.c[var1]) {
/* 1876 */           var0++;
/*      */         }
/*      */       } 
/* 1879 */       return var0;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static class WorldGenMonumentPieceSelector2
/*      */     implements IWorldGenMonumentPieceSelector
/*      */   {
/*      */     private WorldGenMonumentPieceSelector2() {}
/*      */ 
/*      */     
/*      */     public boolean a(WorldGenMonumentPieces.WorldGenMonumentStateTracker var0) {
/* 1892 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public WorldGenMonumentPieces.WorldGenMonumentPiece a(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1, Random var2) {
/* 1897 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var1, true);
/* 1898 */       return new WorldGenMonumentPieces.WorldGenMonumentPieceSimple(var0, var1, var2);
/*      */     }
/*      */   }
/*      */   
/*      */   static class WorldGenMonumentPieceSelector1 implements IWorldGenMonumentPieceSelector { private WorldGenMonumentPieceSelector1() {}
/*      */     
/*      */     public boolean a(WorldGenMonumentPieces.WorldGenMonumentStateTracker var0) {
/* 1905 */       return (!WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.WEST.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.EAST.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.NORTH.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.SOUTH.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.UP.c()]);
/*      */     }
/*      */ 
/*      */     
/*      */     public WorldGenMonumentPieces.WorldGenMonumentPiece a(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1, Random var2) {
/* 1910 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var1, true);
/* 1911 */       return new WorldGenMonumentPieces.WorldGenMonumentPieceSimpleT(var0, var1);
/*      */     } }
/*      */   
/*      */   static class WorldGenMonumentPieceSelector5 implements IWorldGenMonumentPieceSelector {
/*      */     private WorldGenMonumentPieceSelector5() {}
/*      */     
/*      */     public boolean a(WorldGenMonumentPieces.WorldGenMonumentStateTracker var0) {
/* 1918 */       return (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.UP.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var0)[EnumDirection.UP.c()]));
/*      */     }
/*      */ 
/*      */     
/*      */     public WorldGenMonumentPieces.WorldGenMonumentPiece a(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1, Random var2) {
/* 1923 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var1, true);
/* 1924 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.UP.c()], true);
/* 1925 */       return new WorldGenMonumentPieces.WorldGenMonumentPiece5(var0, var1);
/*      */     }
/*      */   }
/*      */   
/*      */   static class WorldGenMonumentPieceSelector7 implements IWorldGenMonumentPieceSelector { private WorldGenMonumentPieceSelector7() {}
/*      */     
/*      */     public boolean a(WorldGenMonumentPieces.WorldGenMonumentStateTracker var0) {
/* 1932 */       return (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.EAST.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var0)[EnumDirection.EAST.c()]));
/*      */     }
/*      */ 
/*      */     
/*      */     public WorldGenMonumentPieces.WorldGenMonumentPiece a(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1, Random var2) {
/* 1937 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var1, true);
/* 1938 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.EAST.c()], true);
/* 1939 */       return new WorldGenMonumentPieces.WorldGenMonumentPiece3(var0, var1);
/*      */     } }
/*      */   
/*      */   static class WorldGenMonumentPieceSelector3 implements IWorldGenMonumentPieceSelector {
/*      */     private WorldGenMonumentPieceSelector3() {}
/*      */     
/*      */     public boolean a(WorldGenMonumentPieces.WorldGenMonumentStateTracker var0) {
/* 1946 */       return (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.NORTH.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var0)[EnumDirection.NORTH.c()]));
/*      */     }
/*      */ 
/*      */     
/*      */     public WorldGenMonumentPieces.WorldGenMonumentPiece a(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1, Random var2) {
/* 1951 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker var3 = var1;
/* 1952 */       if (!WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var1)[EnumDirection.NORTH.c()] || WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.NORTH.c()])) {
/* 1953 */         var3 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.SOUTH.c()];
/*      */       }
/* 1955 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var3, true);
/* 1956 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var3)[EnumDirection.NORTH.c()], true);
/* 1957 */       return new WorldGenMonumentPieces.WorldGenMonumentPiece7(var0, var3);
/*      */     }
/*      */   }
/*      */   
/*      */   static class WorldGenMonumentPieceSelector6 implements IWorldGenMonumentPieceSelector { private WorldGenMonumentPieceSelector6() {}
/*      */     
/*      */     public boolean a(WorldGenMonumentPieces.WorldGenMonumentStateTracker var0) {
/* 1964 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.EAST.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var0)[EnumDirection.EAST.c()]) && 
/* 1965 */         WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.UP.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var0)[EnumDirection.UP.c()])) {
/* 1966 */         WorldGenMonumentPieces.WorldGenMonumentStateTracker var1 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var0)[EnumDirection.EAST.c()];
/*      */         
/* 1968 */         return (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var1)[EnumDirection.UP.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.UP.c()]));
/*      */       } 
/*      */       
/* 1971 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public WorldGenMonumentPieces.WorldGenMonumentPiece a(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1, Random var2) {
/* 1976 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var1, true);
/* 1977 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.EAST.c()], true);
/* 1978 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.UP.c()], true);
/* 1979 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.EAST.c()])[EnumDirection.UP.c()], true);
/* 1980 */       return new WorldGenMonumentPieces.WorldGenMonumentPiece4(var0, var1);
/*      */     } }
/*      */   
/*      */   static class WorldGenMonumentPieceSelector4 implements IWorldGenMonumentPieceSelector {
/*      */     private WorldGenMonumentPieceSelector4() {}
/*      */     
/*      */     public boolean a(WorldGenMonumentPieces.WorldGenMonumentStateTracker var0) {
/* 1987 */       if (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.NORTH.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var0)[EnumDirection.NORTH.c()]) && 
/* 1988 */         WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var0)[EnumDirection.UP.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var0)[EnumDirection.UP.c()])) {
/* 1989 */         WorldGenMonumentPieces.WorldGenMonumentStateTracker var1 = WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var0)[EnumDirection.NORTH.c()];
/*      */         
/* 1991 */         return (WorldGenMonumentPieces.WorldGenMonumentStateTracker.d(var1)[EnumDirection.UP.c()] && !WorldGenMonumentPieces.WorldGenMonumentStateTracker.b(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.UP.c()]));
/*      */       } 
/*      */       
/* 1994 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public WorldGenMonumentPieces.WorldGenMonumentPiece a(EnumDirection var0, WorldGenMonumentPieces.WorldGenMonumentStateTracker var1, Random var2) {
/* 1999 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(var1, true);
/* 2000 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.NORTH.c()], true);
/* 2001 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.UP.c()], true);
/* 2002 */       WorldGenMonumentPieces.WorldGenMonumentStateTracker.a(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(WorldGenMonumentPieces.WorldGenMonumentStateTracker.c(var1)[EnumDirection.NORTH.c()])[EnumDirection.UP.c()], true);
/* 2003 */       return new WorldGenMonumentPieces.WorldGenMonumentPiece6(var0, var1);
/*      */     }
/*      */   }
/*      */   
/*      */   static interface IWorldGenMonumentPieceSelector {
/*      */     boolean a(WorldGenMonumentPieces.WorldGenMonumentStateTracker param1WorldGenMonumentStateTracker);
/*      */     
/*      */     WorldGenMonumentPieces.WorldGenMonumentPiece a(EnumDirection param1EnumDirection, WorldGenMonumentPieces.WorldGenMonumentStateTracker param1WorldGenMonumentStateTracker, Random param1Random);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenMonumentPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */