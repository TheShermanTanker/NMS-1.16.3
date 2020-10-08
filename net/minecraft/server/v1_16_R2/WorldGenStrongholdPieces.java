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
/*      */ public class WorldGenStrongholdPieces
/*      */ {
/*      */   static class WorldGenStrongholdPieceWeight
/*      */   {
/*      */     public final Class<? extends WorldGenStrongholdPieces.WorldGenStrongholdPiece> a;
/*      */     public final int b;
/*      */     public int c;
/*      */     public final int d;
/*      */     
/*      */     public WorldGenStrongholdPieceWeight(Class<? extends WorldGenStrongholdPieces.WorldGenStrongholdPiece> var0, int var1, int var2) {
/*   55 */       this.a = var0;
/*   56 */       this.b = var1;
/*   57 */       this.d = var2;
/*      */     }
/*      */     
/*      */     public boolean a(int var0) {
/*   61 */       return (this.d == 0 || this.c < this.d);
/*      */     }
/*      */     
/*      */     public boolean a() {
/*   65 */       return (this.d == 0 || this.c < this.d);
/*      */     }
/*      */   }
/*      */   
/*   69 */   private static final WorldGenStrongholdPieceWeight[] a = new WorldGenStrongholdPieceWeight[] { new WorldGenStrongholdPieceWeight((Class)WorldGenStrongholdStairs.class, 40, 0), new WorldGenStrongholdPieceWeight((Class)WorldGenStrongholdPrison.class, 5, 5), new WorldGenStrongholdPieceWeight((Class)WorldGenStrongholdLeftTurn.class, 20, 0), new WorldGenStrongholdPieceWeight((Class)WorldGenStrongholdRightTurn.class, 20, 0), new WorldGenStrongholdPieceWeight((Class)WorldGenStrongholdRoomCrossing.class, 10, 6), new WorldGenStrongholdPieceWeight((Class)WorldGenStrongholdStairsStraight.class, 5, 5), new WorldGenStrongholdPieceWeight((Class)WorldGenStrongholdStairs2.class, 5, 5), new WorldGenStrongholdPieceWeight((Class)WorldGenStrongholdCrossing.class, 5, 4), new WorldGenStrongholdPieceWeight((Class)WorldGenStrongholdChestCorridor.class, 5, 4), new WorldGenStrongholdPieceWeight(WorldGenStrongholdLibrary.class, 10, 2)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public boolean a(int var0)
/*      */         {
/*   82 */           return (super.a(var0) && var0 > 4);
/*      */         }
/*      */       }, 
/*      */       new WorldGenStrongholdPieceWeight(WorldGenStrongholdPortalRoom.class, 20, 1)
/*      */       {
/*      */         public boolean a(int var0) {
/*   88 */           return (super.a(var0) && var0 > 5);
/*      */         }
/*      */       } };
/*      */   
/*      */   private static List<WorldGenStrongholdPieceWeight> b;
/*      */   private static Class<? extends WorldGenStrongholdPiece> c;
/*      */   private static int d;
/*      */   
/*      */   public static void a() {
/*   97 */     b = Lists.newArrayList();
/*   98 */     for (WorldGenStrongholdPieceWeight var3 : a) {
/*   99 */       var3.c = 0;
/*  100 */       b.add(var3);
/*      */     } 
/*  102 */     c = null;
/*      */   }
/*      */   
/*      */   private static boolean c() {
/*  106 */     boolean var0 = false;
/*  107 */     d = 0;
/*  108 */     for (WorldGenStrongholdPieceWeight var2 : b) {
/*  109 */       if (var2.d > 0 && var2.c < var2.d) {
/*  110 */         var0 = true;
/*      */       }
/*  112 */       d += var2.b;
/*      */     } 
/*  114 */     return var0;
/*      */   }
/*      */   
/*      */   private static WorldGenStrongholdPiece a(Class<? extends WorldGenStrongholdPiece> var0, List<StructurePiece> var1, Random var2, int var3, int var4, int var5, @Nullable EnumDirection var6, int var7) {
/*  118 */     WorldGenStrongholdPiece var8 = null;
/*      */     
/*  120 */     if (var0 == WorldGenStrongholdStairs.class) {
/*  121 */       var8 = WorldGenStrongholdStairs.a(var1, var2, var3, var4, var5, var6, var7);
/*  122 */     } else if (var0 == WorldGenStrongholdPrison.class) {
/*  123 */       var8 = WorldGenStrongholdPrison.a(var1, var2, var3, var4, var5, var6, var7);
/*  124 */     } else if (var0 == WorldGenStrongholdLeftTurn.class) {
/*  125 */       var8 = WorldGenStrongholdLeftTurn.a(var1, var2, var3, var4, var5, var6, var7);
/*  126 */     } else if (var0 == WorldGenStrongholdRightTurn.class) {
/*  127 */       var8 = WorldGenStrongholdRightTurn.a(var1, var2, var3, var4, var5, var6, var7);
/*  128 */     } else if (var0 == WorldGenStrongholdRoomCrossing.class) {
/*  129 */       var8 = WorldGenStrongholdRoomCrossing.a(var1, var2, var3, var4, var5, var6, var7);
/*  130 */     } else if (var0 == WorldGenStrongholdStairsStraight.class) {
/*  131 */       var8 = WorldGenStrongholdStairsStraight.a(var1, var2, var3, var4, var5, var6, var7);
/*  132 */     } else if (var0 == WorldGenStrongholdStairs2.class) {
/*  133 */       var8 = WorldGenStrongholdStairs2.a(var1, var2, var3, var4, var5, var6, var7);
/*  134 */     } else if (var0 == WorldGenStrongholdCrossing.class) {
/*  135 */       var8 = WorldGenStrongholdCrossing.a(var1, var2, var3, var4, var5, var6, var7);
/*  136 */     } else if (var0 == WorldGenStrongholdChestCorridor.class) {
/*  137 */       var8 = WorldGenStrongholdChestCorridor.a(var1, var2, var3, var4, var5, var6, var7);
/*  138 */     } else if (var0 == WorldGenStrongholdLibrary.class) {
/*  139 */       var8 = WorldGenStrongholdLibrary.a(var1, var2, var3, var4, var5, var6, var7);
/*  140 */     } else if (var0 == WorldGenStrongholdPortalRoom.class) {
/*  141 */       var8 = WorldGenStrongholdPortalRoom.a(var1, var3, var4, var5, var6, var7);
/*      */     } 
/*      */     
/*  144 */     return var8;
/*      */   }
/*      */   
/*      */   private static WorldGenStrongholdPiece b(WorldGenStrongholdStart var0, List<StructurePiece> var1, Random var2, int var3, int var4, int var5, EnumDirection var6, int var7) {
/*  148 */     if (!c()) {
/*  149 */       return null;
/*      */     }
/*      */     
/*  152 */     if (c != null) {
/*  153 */       WorldGenStrongholdPiece worldGenStrongholdPiece = a(c, var1, var2, var3, var4, var5, var6, var7);
/*  154 */       c = null;
/*      */       
/*  156 */       if (worldGenStrongholdPiece != null) {
/*  157 */         return worldGenStrongholdPiece;
/*      */       }
/*      */     } 
/*      */     
/*  161 */     int var8 = 0;
/*  162 */     while (var8 < 5) {
/*  163 */       var8++;
/*      */       
/*  165 */       int i = var2.nextInt(d);
/*  166 */       for (WorldGenStrongholdPieceWeight var11 : b) {
/*  167 */         i -= var11.b;
/*  168 */         if (i < 0) {
/*  169 */           if (!var11.a(var7) || var11 == var0.a) {
/*      */             break;
/*      */           }
/*      */           
/*  173 */           WorldGenStrongholdPiece var12 = a(var11.a, var1, var2, var3, var4, var5, var6, var7);
/*  174 */           if (var12 != null) {
/*  175 */             var11.c++;
/*  176 */             var0.a = var11;
/*      */             
/*  178 */             if (!var11.a()) {
/*  179 */               b.remove(var11);
/*      */             }
/*  181 */             return var12;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  186 */     StructureBoundingBox var9 = WorldGenStrongholdCorridor.a(var1, var2, var3, var4, var5, var6);
/*  187 */     if (var9 != null && var9.b > 1) {
/*  188 */       return new WorldGenStrongholdCorridor(var7, var9, var6);
/*      */     }
/*      */     
/*  191 */     return null;
/*      */   }
/*      */   
/*      */   private static StructurePiece c(WorldGenStrongholdStart var0, List<StructurePiece> var1, Random var2, int var3, int var4, int var5, @Nullable EnumDirection var6, int var7) {
/*  195 */     if (var7 > 50) {
/*  196 */       return null;
/*      */     }
/*  198 */     if (Math.abs(var3 - (var0.g()).a) > 112 || Math.abs(var5 - (var0.g()).c) > 112) {
/*  199 */       return null;
/*      */     }
/*      */     
/*  202 */     StructurePiece var8 = b(var0, var1, var2, var3, var4, var5, var6, var7 + 1);
/*  203 */     if (var8 != null) {
/*  204 */       var1.add(var8);
/*  205 */       var0.c.add(var8);
/*      */     } 
/*  207 */     return var8;
/*      */   }
/*      */   
/*      */   static abstract class WorldGenStrongholdPiece extends StructurePiece {
/*  211 */     protected WorldGenStrongholdDoorType d = WorldGenStrongholdDoorType.OPENING;
/*      */     
/*      */     protected WorldGenStrongholdPiece(WorldGenFeatureStructurePieceType var0, int var1) {
/*  214 */       super(var0, var1);
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdPiece(WorldGenFeatureStructurePieceType var0, NBTTagCompound var1) {
/*  218 */       super(var0, var1);
/*  219 */       this.d = WorldGenStrongholdDoorType.valueOf(var1.getString("EntryDoor"));
/*      */     }
/*      */     
/*      */     public enum WorldGenStrongholdDoorType {
/*  223 */       OPENING, WOOD_DOOR, GRATES, IRON_DOOR;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/*  228 */       var0.setString("EntryDoor", this.d.name());
/*      */     }
/*      */     
/*      */     protected void a(GeneratorAccessSeed var0, Random var1, StructureBoundingBox var2, WorldGenStrongholdDoorType var3, int var4, int var5, int var6) {
/*  232 */       switch (WorldGenStrongholdPieces.null.a[var3.ordinal()]) {
/*      */         case 1:
/*  234 */           a(var0, var2, var4, var5, var6, var4 + 3 - 1, var5 + 3 - 1, var6, m, m, false);
/*      */           break;
/*      */         case 2:
/*  237 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4, var5, var6, var2);
/*  238 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4, var5 + 1, var6, var2);
/*  239 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4, var5 + 2, var6, var2);
/*  240 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4 + 1, var5 + 2, var6, var2);
/*  241 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4 + 2, var5 + 2, var6, var2);
/*  242 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4 + 2, var5 + 1, var6, var2);
/*  243 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4 + 2, var5, var6, var2);
/*  244 */           a(var0, Blocks.OAK_DOOR.getBlockData(), var4 + 1, var5, var6, var2);
/*  245 */           a(var0, Blocks.OAK_DOOR.getBlockData().set(BlockDoor.HALF, BlockPropertyDoubleBlockHalf.UPPER), var4 + 1, var5 + 1, var6, var2);
/*      */           break;
/*      */         case 3:
/*  248 */           a(var0, Blocks.CAVE_AIR.getBlockData(), var4 + 1, var5, var6, var2);
/*  249 */           a(var0, Blocks.CAVE_AIR.getBlockData(), var4 + 1, var5 + 1, var6, var2);
/*  250 */           a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, Boolean.valueOf(true)), var4, var5, var6, var2);
/*  251 */           a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, Boolean.valueOf(true)), var4, var5 + 1, var6, var2);
/*  252 */           a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true)), var4, var5 + 2, var6, var2);
/*  253 */           a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true)), var4 + 1, var5 + 2, var6, var2);
/*  254 */           a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)).set(BlockIronBars.WEST, Boolean.valueOf(true)), var4 + 2, var5 + 2, var6, var2);
/*  255 */           a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)), var4 + 2, var5 + 1, var6, var2);
/*  256 */           a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.EAST, Boolean.valueOf(true)), var4 + 2, var5, var6, var2);
/*      */           break;
/*      */         case 4:
/*  259 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4, var5, var6, var2);
/*  260 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4, var5 + 1, var6, var2);
/*  261 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4, var5 + 2, var6, var2);
/*  262 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4 + 1, var5 + 2, var6, var2);
/*  263 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4 + 2, var5 + 2, var6, var2);
/*  264 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4 + 2, var5 + 1, var6, var2);
/*  265 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), var4 + 2, var5, var6, var2);
/*  266 */           a(var0, Blocks.IRON_DOOR.getBlockData(), var4 + 1, var5, var6, var2);
/*  267 */           a(var0, Blocks.IRON_DOOR.getBlockData().set(BlockDoor.HALF, BlockPropertyDoubleBlockHalf.UPPER), var4 + 1, var5 + 1, var6, var2);
/*  268 */           a(var0, Blocks.STONE_BUTTON.getBlockData().set(BlockButtonAbstract.FACING, EnumDirection.NORTH), var4 + 2, var5 + 1, var6 + 1, var2);
/*  269 */           a(var0, Blocks.STONE_BUTTON.getBlockData().set(BlockButtonAbstract.FACING, EnumDirection.SOUTH), var4 + 2, var5 + 1, var6 - 1, var2);
/*      */           break;
/*      */       } 
/*      */     }
/*      */     
/*      */     protected WorldGenStrongholdDoorType a(Random var0) {
/*  275 */       int var1 = var0.nextInt(5);
/*  276 */       switch (var1)
/*      */       
/*      */       { 
/*      */         default:
/*  280 */           return WorldGenStrongholdDoorType.OPENING;
/*      */         case 2:
/*  282 */           return WorldGenStrongholdDoorType.WOOD_DOOR;
/*      */         case 3:
/*  284 */           return WorldGenStrongholdDoorType.GRATES;
/*      */         case 4:
/*  286 */           break; }  return WorldGenStrongholdDoorType.IRON_DOOR;
/*      */     }
/*      */ 
/*      */     
/*      */     @Nullable
/*      */     protected StructurePiece a(WorldGenStrongholdPieces.WorldGenStrongholdStart var0, List<StructurePiece> var1, Random var2, int var3, int var4) {
/*  292 */       EnumDirection var5 = i();
/*  293 */       if (var5 != null) {
/*  294 */         switch (WorldGenStrongholdPieces.null.b[var5.ordinal()]) {
/*      */           case 1:
/*  296 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.a + var3, this.n.b + var4, this.n.c - 1, var5, h());
/*      */           case 2:
/*  298 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.a + var3, this.n.b + var4, this.n.f + 1, var5, h());
/*      */           case 3:
/*  300 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.a - 1, this.n.b + var4, this.n.c + var3, var5, h());
/*      */           case 4:
/*  302 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.d + 1, this.n.b + var4, this.n.c + var3, var5, h());
/*      */         } 
/*      */       }
/*  305 */       return null;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     protected StructurePiece b(WorldGenStrongholdPieces.WorldGenStrongholdStart var0, List<StructurePiece> var1, Random var2, int var3, int var4) {
/*  310 */       EnumDirection var5 = i();
/*  311 */       if (var5 != null) {
/*  312 */         switch (WorldGenStrongholdPieces.null.b[var5.ordinal()]) {
/*      */           case 1:
/*  314 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.a - 1, this.n.b + var3, this.n.c + var4, EnumDirection.WEST, h());
/*      */           case 2:
/*  316 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.a - 1, this.n.b + var3, this.n.c + var4, EnumDirection.WEST, h());
/*      */           case 3:
/*  318 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.a + var4, this.n.b + var3, this.n.c - 1, EnumDirection.NORTH, h());
/*      */           case 4:
/*  320 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.a + var4, this.n.b + var3, this.n.c - 1, EnumDirection.NORTH, h());
/*      */         } 
/*      */       }
/*  323 */       return null;
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     protected StructurePiece c(WorldGenStrongholdPieces.WorldGenStrongholdStart var0, List<StructurePiece> var1, Random var2, int var3, int var4) {
/*  328 */       EnumDirection var5 = i();
/*  329 */       if (var5 != null) {
/*  330 */         switch (WorldGenStrongholdPieces.null.b[var5.ordinal()]) {
/*      */           case 1:
/*  332 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.d + 1, this.n.b + var3, this.n.c + var4, EnumDirection.EAST, h());
/*      */           case 2:
/*  334 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.d + 1, this.n.b + var3, this.n.c + var4, EnumDirection.EAST, h());
/*      */           case 3:
/*  336 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.a + var4, this.n.b + var3, this.n.f + 1, EnumDirection.SOUTH, h());
/*      */           case 4:
/*  338 */             return WorldGenStrongholdPieces.a(var0, var1, var2, this.n.a + var4, this.n.b + var3, this.n.f + 1, EnumDirection.SOUTH, h());
/*      */         } 
/*      */       }
/*  341 */       return null;
/*      */     }
/*      */     
/*      */     protected static boolean a(StructureBoundingBox var0) {
/*  345 */       return (var0 != null && var0.b > 10);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WorldGenStrongholdCorridor
/*      */     extends WorldGenStrongholdPiece
/*      */   {
/*      */     private final int a;
/*      */     
/*      */     public WorldGenStrongholdCorridor(int var0, StructureBoundingBox var1, EnumDirection var2) {
/*  356 */       super(WorldGenFeatureStructurePieceType.u, var0);
/*      */       
/*  358 */       a(var2);
/*  359 */       this.n = var1;
/*  360 */       this.a = (var2 == EnumDirection.NORTH || var2 == EnumDirection.SOUTH) ? var1.f() : var1.d();
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdCorridor(DefinedStructureManager var0, NBTTagCompound var1) {
/*  364 */       super(WorldGenFeatureStructurePieceType.u, var1);
/*  365 */       this.a = var1.getInt("Steps");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/*  370 */       super.a(var0);
/*  371 */       var0.setInt("Steps", this.a);
/*      */     }
/*      */     
/*      */     public static StructureBoundingBox a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5) {
/*  375 */       int var6 = 3;
/*      */       
/*  377 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, 4, var5);
/*      */       
/*  379 */       StructurePiece var8 = StructurePiece.a(var0, var7);
/*  380 */       if (var8 == null)
/*      */       {
/*  382 */         return null;
/*      */       }
/*      */       
/*  385 */       if ((var8.g()).b == var7.b)
/*      */       {
/*  387 */         for (int var9 = 3; var9 >= 1; var9--) {
/*  388 */           var7 = StructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, var9 - 1, var5);
/*  389 */           if (!var8.g().b(var7))
/*      */           {
/*      */             
/*  392 */             return StructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, var9, var5);
/*      */           }
/*      */         } 
/*      */       }
/*      */       
/*  397 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  403 */       for (int var7 = 0; var7 < this.a; var7++) {
/*      */         
/*  405 */         a(var0, Blocks.STONE_BRICKS.getBlockData(), 0, 0, var7, var4);
/*  406 */         a(var0, Blocks.STONE_BRICKS.getBlockData(), 1, 0, var7, var4);
/*  407 */         a(var0, Blocks.STONE_BRICKS.getBlockData(), 2, 0, var7, var4);
/*  408 */         a(var0, Blocks.STONE_BRICKS.getBlockData(), 3, 0, var7, var4);
/*  409 */         a(var0, Blocks.STONE_BRICKS.getBlockData(), 4, 0, var7, var4);
/*      */         
/*  411 */         for (int var8 = 1; var8 <= 3; var8++) {
/*  412 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), 0, var8, var7, var4);
/*  413 */           a(var0, Blocks.CAVE_AIR.getBlockData(), 1, var8, var7, var4);
/*  414 */           a(var0, Blocks.CAVE_AIR.getBlockData(), 2, var8, var7, var4);
/*  415 */           a(var0, Blocks.CAVE_AIR.getBlockData(), 3, var8, var7, var4);
/*  416 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), 4, var8, var7, var4);
/*      */         } 
/*      */         
/*  419 */         a(var0, Blocks.STONE_BRICKS.getBlockData(), 0, 4, var7, var4);
/*  420 */         a(var0, Blocks.STONE_BRICKS.getBlockData(), 1, 4, var7, var4);
/*  421 */         a(var0, Blocks.STONE_BRICKS.getBlockData(), 2, 4, var7, var4);
/*  422 */         a(var0, Blocks.STONE_BRICKS.getBlockData(), 3, 4, var7, var4);
/*  423 */         a(var0, Blocks.STONE_BRICKS.getBlockData(), 4, 4, var7, var4);
/*      */       } 
/*      */       
/*  426 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WorldGenStrongholdStairs2
/*      */     extends WorldGenStrongholdPiece
/*      */   {
/*      */     private final boolean a;
/*      */ 
/*      */     
/*      */     public WorldGenStrongholdStairs2(WorldGenFeatureStructurePieceType var0, int var1, Random var2, int var3, int var4) {
/*  438 */       super(var0, var1);
/*      */       
/*  440 */       this.a = true;
/*  441 */       a(EnumDirection.EnumDirectionLimit.HORIZONTAL.a(var2));
/*  442 */       this.d = WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING;
/*      */       
/*  444 */       if (i().n() == EnumDirection.EnumAxis.Z) {
/*  445 */         this.n = new StructureBoundingBox(var3, 64, var4, var3 + 5 - 1, 74, var4 + 5 - 1);
/*      */       } else {
/*  447 */         this.n = new StructureBoundingBox(var3, 64, var4, var3 + 5 - 1, 74, var4 + 5 - 1);
/*      */       } 
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdStairs2(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/*  452 */       super(WorldGenFeatureStructurePieceType.C, var0);
/*      */       
/*  454 */       this.a = false;
/*  455 */       a(var3);
/*  456 */       this.d = a(var1);
/*  457 */       this.n = var2;
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdStairs2(WorldGenFeatureStructurePieceType var0, NBTTagCompound var1) {
/*  461 */       super(var0, var1);
/*  462 */       this.a = var1.getBoolean("Source");
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdStairs2(DefinedStructureManager var0, NBTTagCompound var1) {
/*  466 */       this(WorldGenFeatureStructurePieceType.C, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/*  471 */       super.a(var0);
/*  472 */       var0.setBoolean("Source", this.a);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  477 */       if (this.a)
/*      */       {
/*  479 */         WorldGenStrongholdPieces.a(WorldGenStrongholdPieces.WorldGenStrongholdCrossing.class);
/*      */       }
/*  481 */       a((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 1);
/*      */     }
/*      */     
/*      */     public static WorldGenStrongholdStairs2 a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/*  485 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, -7, 0, 5, 11, 5, var5);
/*      */       
/*  487 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*  488 */         return null;
/*      */       }
/*      */       
/*  491 */       return new WorldGenStrongholdStairs2(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  497 */       a(var0, var4, 0, 0, 0, 4, 10, 4, true, var3, WorldGenStrongholdPieces.b());
/*      */       
/*  499 */       a(var0, var3, var4, this.d, 1, 7, 0);
/*      */       
/*  501 */       a(var0, var3, var4, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING, 1, 1, 4);
/*      */ 
/*      */       
/*  504 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 2, 6, 1, var4);
/*  505 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 1, 5, 1, var4);
/*  506 */       a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 1, 6, 1, var4);
/*  507 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 1, 5, 2, var4);
/*  508 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 1, 4, 3, var4);
/*  509 */       a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 1, 5, 3, var4);
/*  510 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 2, 4, 3, var4);
/*  511 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 3, 3, 3, var4);
/*  512 */       a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 3, 4, 3, var4);
/*  513 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 3, 3, 2, var4);
/*  514 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 3, 2, 1, var4);
/*  515 */       a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 3, 3, 1, var4);
/*  516 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 2, 2, 1, var4);
/*  517 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 1, 1, 1, var4);
/*  518 */       a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 1, 2, 1, var4);
/*  519 */       a(var0, Blocks.STONE_BRICKS.getBlockData(), 1, 1, 2, var4);
/*  520 */       a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 1, 1, 3, var4);
/*      */       
/*  522 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenStrongholdStart
/*      */     extends WorldGenStrongholdStairs2
/*      */   {
/*      */     public WorldGenStrongholdPieces.WorldGenStrongholdPieceWeight a;
/*      */     @Nullable
/*      */     public WorldGenStrongholdPieces.WorldGenStrongholdPortalRoom b;
/*  532 */     public final List<StructurePiece> c = Lists.newArrayList();
/*      */     
/*      */     public WorldGenStrongholdStart(Random var0, int var1, int var2) {
/*  535 */       super(WorldGenFeatureStructurePieceType.D, 0, var0, var1, var2);
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdStart(DefinedStructureManager var0, NBTTagCompound var1) {
/*  539 */       super(WorldGenFeatureStructurePieceType.D, var1);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenStrongholdStairs
/*      */     extends WorldGenStrongholdPiece
/*      */   {
/*      */     private final boolean a;
/*      */ 
/*      */ 
/*      */     
/*      */     private final boolean b;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WorldGenStrongholdStairs(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/*  560 */       super(WorldGenFeatureStructurePieceType.E, var0);
/*      */       
/*  562 */       a(var3);
/*  563 */       this.d = a(var1);
/*  564 */       this.n = var2;
/*      */       
/*  566 */       this.a = (var1.nextInt(2) == 0);
/*  567 */       this.b = (var1.nextInt(2) == 0);
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdStairs(DefinedStructureManager var0, NBTTagCompound var1) {
/*  571 */       super(WorldGenFeatureStructurePieceType.E, var1);
/*  572 */       this.a = var1.getBoolean("Left");
/*  573 */       this.b = var1.getBoolean("Right");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/*  578 */       super.a(var0);
/*  579 */       var0.setBoolean("Left", this.a);
/*  580 */       var0.setBoolean("Right", this.b);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  585 */       a((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 1);
/*  586 */       if (this.a) {
/*  587 */         b((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 2);
/*      */       }
/*  589 */       if (this.b) {
/*  590 */         c((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 2);
/*      */       }
/*      */     }
/*      */     
/*      */     public static WorldGenStrongholdStairs a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/*  595 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, 7, var5);
/*      */       
/*  597 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*  598 */         return null;
/*      */       }
/*      */       
/*  601 */       return new WorldGenStrongholdStairs(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  607 */       a(var0, var4, 0, 0, 0, 4, 4, 6, true, var3, WorldGenStrongholdPieces.b());
/*      */       
/*  609 */       a(var0, var3, var4, this.d, 1, 1, 0);
/*      */       
/*  611 */       a(var0, var3, var4, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING, 1, 1, 6);
/*      */       
/*  613 */       IBlockData var7 = Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.EAST);
/*  614 */       IBlockData var8 = Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.WEST);
/*      */       
/*  616 */       a(var0, var4, var3, 0.1F, 1, 2, 1, var7);
/*  617 */       a(var0, var4, var3, 0.1F, 3, 2, 1, var8);
/*  618 */       a(var0, var4, var3, 0.1F, 1, 2, 5, var7);
/*  619 */       a(var0, var4, var3, 0.1F, 3, 2, 5, var8);
/*      */       
/*  621 */       if (this.a) {
/*  622 */         a(var0, var4, 0, 1, 2, 0, 3, 4, m, m, false);
/*      */       }
/*  624 */       if (this.b) {
/*  625 */         a(var0, var4, 4, 1, 2, 4, 3, 4, m, m, false);
/*      */       }
/*      */       
/*  628 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WorldGenStrongholdChestCorridor
/*      */     extends WorldGenStrongholdPiece
/*      */   {
/*      */     private boolean a;
/*      */ 
/*      */     
/*      */     public WorldGenStrongholdChestCorridor(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/*  640 */       super(WorldGenFeatureStructurePieceType.t, var0);
/*      */       
/*  642 */       a(var3);
/*  643 */       this.d = a(var1);
/*  644 */       this.n = var2;
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdChestCorridor(DefinedStructureManager var0, NBTTagCompound var1) {
/*  648 */       super(WorldGenFeatureStructurePieceType.t, var1);
/*  649 */       this.a = var1.getBoolean("Chest");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/*  654 */       super.a(var0);
/*  655 */       var0.setBoolean("Chest", this.a);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  660 */       a((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 1);
/*      */     }
/*      */     
/*      */     public static WorldGenStrongholdChestCorridor a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/*  664 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, 7, var5);
/*      */       
/*  666 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*  667 */         return null;
/*      */       }
/*      */       
/*  670 */       return new WorldGenStrongholdChestCorridor(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  676 */       a(var0, var4, 0, 0, 0, 4, 4, 6, true, var3, WorldGenStrongholdPieces.b());
/*      */       
/*  678 */       a(var0, var3, var4, this.d, 1, 1, 0);
/*      */       
/*  680 */       a(var0, var3, var4, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING, 1, 1, 6);
/*      */ 
/*      */       
/*  683 */       a(var0, var4, 3, 1, 2, 3, 1, 4, Blocks.STONE_BRICKS.getBlockData(), Blocks.STONE_BRICKS.getBlockData(), false);
/*  684 */       a(var0, Blocks.STONE_BRICK_SLAB.getBlockData(), 3, 1, 1, var4);
/*  685 */       a(var0, Blocks.STONE_BRICK_SLAB.getBlockData(), 3, 1, 5, var4);
/*  686 */       a(var0, Blocks.STONE_BRICK_SLAB.getBlockData(), 3, 2, 2, var4);
/*  687 */       a(var0, Blocks.STONE_BRICK_SLAB.getBlockData(), 3, 2, 4, var4);
/*  688 */       for (int var7 = 2; var7 <= 4; var7++) {
/*  689 */         a(var0, Blocks.STONE_BRICK_SLAB.getBlockData(), 2, 1, var7, var4);
/*      */       }
/*      */       
/*  692 */       if (!this.a && 
/*  693 */         var4.b(new BlockPosition(a(3, 3), d(2), b(3, 3)))) {
/*  694 */         this.a = true;
/*  695 */         a(var0, var4, var3, 3, 2, 3, LootTables.y);
/*      */       } 
/*      */ 
/*      */       
/*  699 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenStrongholdStairsStraight
/*      */     extends WorldGenStrongholdPiece
/*      */   {
/*      */     public WorldGenStrongholdStairsStraight(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/*  709 */       super(WorldGenFeatureStructurePieceType.F, var0);
/*      */       
/*  711 */       a(var3);
/*  712 */       this.d = a(var1);
/*  713 */       this.n = var2;
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdStairsStraight(DefinedStructureManager var0, NBTTagCompound var1) {
/*  717 */       super(WorldGenFeatureStructurePieceType.F, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  722 */       a((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 1);
/*      */     }
/*      */     
/*      */     public static WorldGenStrongholdStairsStraight a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/*  726 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, -7, 0, 5, 11, 8, var5);
/*      */       
/*  728 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*  729 */         return null;
/*      */       }
/*      */       
/*  732 */       return new WorldGenStrongholdStairsStraight(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  738 */       a(var0, var4, 0, 0, 0, 4, 10, 7, true, var3, WorldGenStrongholdPieces.b());
/*      */       
/*  740 */       a(var0, var3, var4, this.d, 1, 7, 0);
/*      */       
/*  742 */       a(var0, var3, var4, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.OPENING, 1, 1, 7);
/*      */ 
/*      */       
/*  745 */       IBlockData var7 = Blocks.COBBLESTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.SOUTH);
/*  746 */       for (int var8 = 0; var8 < 6; var8++) {
/*  747 */         a(var0, var7, 1, 6 - var8, 1 + var8, var4);
/*  748 */         a(var0, var7, 2, 6 - var8, 1 + var8, var4);
/*  749 */         a(var0, var7, 3, 6 - var8, 1 + var8, var4);
/*  750 */         if (var8 < 5) {
/*  751 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), 1, 5 - var8, 1 + var8, var4);
/*  752 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), 2, 5 - var8, 1 + var8, var4);
/*  753 */           a(var0, Blocks.STONE_BRICKS.getBlockData(), 3, 5 - var8, 1 + var8, var4);
/*      */         } 
/*      */       } 
/*      */       
/*  757 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static abstract class q
/*      */     extends WorldGenStrongholdPiece
/*      */   {
/*      */     protected q(WorldGenFeatureStructurePieceType var0, int var1) {
/*  767 */       super(var0, var1);
/*      */     }
/*      */     
/*      */     public q(WorldGenFeatureStructurePieceType var0, NBTTagCompound var1) {
/*  771 */       super(var0, var1);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenStrongholdLeftTurn extends q {
/*      */     public WorldGenStrongholdLeftTurn(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/*  777 */       super(WorldGenFeatureStructurePieceType.w, var0);
/*      */       
/*  779 */       a(var3);
/*  780 */       this.d = a(var1);
/*  781 */       this.n = var2;
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdLeftTurn(DefinedStructureManager var0, NBTTagCompound var1) {
/*  785 */       super(WorldGenFeatureStructurePieceType.w, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  790 */       EnumDirection var3 = i();
/*  791 */       if (var3 == EnumDirection.NORTH || var3 == EnumDirection.EAST) {
/*  792 */         b((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 1);
/*      */       } else {
/*  794 */         c((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 1);
/*      */       } 
/*      */     }
/*      */     
/*      */     public static WorldGenStrongholdLeftTurn a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/*  799 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, 5, var5);
/*      */       
/*  801 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*  802 */         return null;
/*      */       }
/*      */       
/*  805 */       return new WorldGenStrongholdLeftTurn(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  811 */       a(var0, var4, 0, 0, 0, 4, 4, 4, true, var3, WorldGenStrongholdPieces.b());
/*      */       
/*  813 */       a(var0, var3, var4, this.d, 1, 1, 0);
/*      */       
/*  815 */       EnumDirection var7 = i();
/*  816 */       if (var7 == EnumDirection.NORTH || var7 == EnumDirection.EAST) {
/*  817 */         a(var0, var4, 0, 1, 1, 0, 3, 3, m, m, false);
/*      */       } else {
/*  819 */         a(var0, var4, 4, 1, 1, 4, 3, 3, m, m, false);
/*      */       } 
/*      */       
/*  822 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WorldGenStrongholdRightTurn extends q {
/*      */     public WorldGenStrongholdRightTurn(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/*  828 */       super(WorldGenFeatureStructurePieceType.A, var0);
/*      */       
/*  830 */       a(var3);
/*  831 */       this.d = a(var1);
/*  832 */       this.n = var2;
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdRightTurn(DefinedStructureManager var0, NBTTagCompound var1) {
/*  836 */       super(WorldGenFeatureStructurePieceType.A, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  841 */       EnumDirection var3 = i();
/*  842 */       if (var3 == EnumDirection.NORTH || var3 == EnumDirection.EAST) {
/*  843 */         c((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 1);
/*      */       } else {
/*  845 */         b((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 1);
/*      */       } 
/*      */     }
/*      */     
/*      */     public static WorldGenStrongholdRightTurn a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/*  850 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, 5, var5);
/*      */       
/*  852 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*  853 */         return null;
/*      */       }
/*      */       
/*  856 */       return new WorldGenStrongholdRightTurn(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  862 */       a(var0, var4, 0, 0, 0, 4, 4, 4, true, var3, WorldGenStrongholdPieces.b());
/*      */       
/*  864 */       a(var0, var3, var4, this.d, 1, 1, 0);
/*      */       
/*  866 */       EnumDirection var7 = i();
/*  867 */       if (var7 == EnumDirection.NORTH || var7 == EnumDirection.EAST) {
/*  868 */         a(var0, var4, 4, 1, 1, 4, 3, 3, m, m, false);
/*      */       } else {
/*  870 */         a(var0, var4, 0, 1, 1, 0, 3, 3, m, m, false);
/*      */       } 
/*      */       
/*  873 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WorldGenStrongholdRoomCrossing
/*      */     extends WorldGenStrongholdPiece
/*      */   {
/*      */     protected final int a;
/*      */ 
/*      */     
/*      */     public WorldGenStrongholdRoomCrossing(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/*  885 */       super(WorldGenFeatureStructurePieceType.B, var0);
/*      */       
/*  887 */       a(var3);
/*  888 */       this.d = a(var1);
/*  889 */       this.n = var2;
/*  890 */       this.a = var1.nextInt(5);
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdRoomCrossing(DefinedStructureManager var0, NBTTagCompound var1) {
/*  894 */       super(WorldGenFeatureStructurePieceType.B, var1);
/*  895 */       this.a = var1.getInt("Type");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/*  900 */       super.a(var0);
/*  901 */       var0.setInt("Type", this.a);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/*  906 */       a((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 4, 1);
/*  907 */       b((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 4);
/*  908 */       c((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 4);
/*      */     }
/*      */     
/*      */     public static WorldGenStrongholdRoomCrossing a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/*  912 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -4, -1, 0, 11, 7, 11, var5);
/*      */       
/*  914 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*  915 */         return null;
/*      */       }
/*      */       
/*  918 */       return new WorldGenStrongholdRoomCrossing(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  924 */       a(var0, var4, 0, 0, 0, 10, 6, 10, true, var3, WorldGenStrongholdPieces.b());
/*      */       
/*  926 */       a(var0, var3, var4, this.d, 4, 1, 0);
/*      */       
/*  928 */       a(var0, var4, 4, 1, 10, 6, 3, 10, m, m, false);
/*  929 */       a(var0, var4, 0, 1, 4, 0, 3, 6, m, m, false);
/*  930 */       a(var0, var4, 10, 1, 4, 10, 3, 6, m, m, false);
/*      */       
/*  932 */       switch (this.a)
/*      */       
/*      */       { 
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
/*      */         
/*      */         default:
/* 1009 */           return true;
/*      */         case 0:
/*      */           a(var0, Blocks.STONE_BRICKS.getBlockData(), 5, 1, 5, var4); a(var0, Blocks.STONE_BRICKS.getBlockData(), 5, 2, 5, var4); a(var0, Blocks.STONE_BRICKS.getBlockData(), 5, 3, 5, var4); a(var0, Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.WEST), 4, 3, 5, var4); a(var0, Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.EAST), 6, 3, 5, var4); a(var0, Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.SOUTH), 5, 3, 4, var4); a(var0, Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.NORTH), 5, 3, 6, var4); a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 4, 1, 4, var4); a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 4, 1, 5, var4); a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 4, 1, 6, var4); a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 6, 1, 4, var4); a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 6, 1, 5, var4); a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 6, 1, 6, var4); a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 5, 1, 4, var4); a(var0, Blocks.SMOOTH_STONE_SLAB.getBlockData(), 5, 1, 6, var4);
/*      */         case 1:
/*      */           for (i = 0; i < 5; i++) { a(var0, Blocks.STONE_BRICKS.getBlockData(), 3, 1, 3 + i, var4); a(var0, Blocks.STONE_BRICKS.getBlockData(), 7, 1, 3 + i, var4); a(var0, Blocks.STONE_BRICKS.getBlockData(), 3 + i, 1, 3, var4); a(var0, Blocks.STONE_BRICKS.getBlockData(), 3 + i, 1, 7, var4); }  a(var0, Blocks.STONE_BRICKS.getBlockData(), 5, 1, 5, var4); a(var0, Blocks.STONE_BRICKS.getBlockData(), 5, 2, 5, var4); a(var0, Blocks.STONE_BRICKS.getBlockData(), 5, 3, 5, var4); a(var0, Blocks.WATER.getBlockData(), 5, 4, 5, var4);
/*      */         case 2:
/*      */           break; }  int i; for (i = 1; i <= 9; i++) { a(var0, Blocks.COBBLESTONE.getBlockData(), 1, 3, i, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 9, 3, i, var4); }  for (i = 1; i <= 9; i++) { a(var0, Blocks.COBBLESTONE.getBlockData(), i, 3, 1, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), i, 3, 9, var4); }
/*      */        a(var0, Blocks.COBBLESTONE.getBlockData(), 5, 1, 4, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 5, 1, 6, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 5, 3, 4, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 5, 3, 6, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 4, 1, 5, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 6, 1, 5, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 4, 3, 5, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 6, 3, 5, var4); for (i = 1; i <= 3; i++) { a(var0, Blocks.COBBLESTONE.getBlockData(), 4, i, 4, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 6, i, 4, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 4, i, 6, var4); a(var0, Blocks.COBBLESTONE.getBlockData(), 6, i, 6, var4); }
/*      */        a(var0, Blocks.TORCH.getBlockData(), 5, 3, 5, var4); for (i = 2; i <= 8; i++) { a(var0, Blocks.OAK_PLANKS.getBlockData(), 2, 3, i, var4); a(var0, Blocks.OAK_PLANKS.getBlockData(), 3, 3, i, var4); if (i <= 3 || i >= 7) { a(var0, Blocks.OAK_PLANKS.getBlockData(), 4, 3, i, var4); a(var0, Blocks.OAK_PLANKS.getBlockData(), 5, 3, i, var4); a(var0, Blocks.OAK_PLANKS.getBlockData(), 6, 3, i, var4); }
/*      */          a(var0, Blocks.OAK_PLANKS.getBlockData(), 7, 3, i, var4); a(var0, Blocks.OAK_PLANKS.getBlockData(), 8, 3, i, var4); }
/* 1019 */        IBlockData var7 = Blocks.LADDER.getBlockData().set(BlockLadder.FACING, EnumDirection.WEST); a(var0, var7, 9, 1, 3, var4); a(var0, var7, 9, 2, 3, var4); a(var0, var7, 9, 3, 3, var4); a(var0, var4, var3, 3, 4, 8, LootTables.x); } } public static class WorldGenStrongholdPrison extends WorldGenStrongholdPiece { public WorldGenStrongholdPrison(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) { super(WorldGenFeatureStructurePieceType.z, var0);
/*      */       
/* 1021 */       a(var3);
/* 1022 */       this.d = a(var1);
/* 1023 */       this.n = var2; }
/*      */ 
/*      */     
/*      */     public WorldGenStrongholdPrison(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1027 */       super(WorldGenFeatureStructurePieceType.z, var1);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 1032 */       a((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 1, 1);
/*      */     }
/*      */     
/*      */     public static WorldGenStrongholdPrison a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/* 1036 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 9, 5, 11, var5);
/*      */       
/* 1038 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/* 1039 */         return null;
/*      */       }
/*      */       
/* 1042 */       return new WorldGenStrongholdPrison(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1048 */       a(var0, var4, 0, 0, 0, 8, 4, 10, true, var3, WorldGenStrongholdPieces.b());
/*      */       
/* 1050 */       a(var0, var3, var4, this.d, 1, 1, 0);
/*      */       
/* 1052 */       a(var0, var4, 1, 1, 10, 3, 3, 10, m, m, false);
/*      */ 
/*      */       
/* 1055 */       a(var0, var4, 4, 1, 1, 4, 3, 1, false, var3, WorldGenStrongholdPieces.b());
/* 1056 */       a(var0, var4, 4, 1, 3, 4, 3, 3, false, var3, WorldGenStrongholdPieces.b());
/* 1057 */       a(var0, var4, 4, 1, 7, 4, 3, 7, false, var3, WorldGenStrongholdPieces.b());
/* 1058 */       a(var0, var4, 4, 1, 9, 4, 3, 9, false, var3, WorldGenStrongholdPieces.b());
/*      */ 
/*      */       
/* 1061 */       for (int i = 1; i <= 3; i++) {
/* 1062 */         a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true)), 4, i, 4, var4);
/* 1063 */         a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true)).set(BlockIronBars.EAST, Boolean.valueOf(true)), 4, i, 5, var4);
/* 1064 */         a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true)), 4, i, 6, var4);
/*      */         
/* 1066 */         a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, Boolean.valueOf(true)).set(BlockIronBars.EAST, Boolean.valueOf(true)), 5, i, 5, var4);
/* 1067 */         a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, Boolean.valueOf(true)).set(BlockIronBars.EAST, Boolean.valueOf(true)), 6, i, 5, var4);
/* 1068 */         a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, Boolean.valueOf(true)).set(BlockIronBars.EAST, Boolean.valueOf(true)), 7, i, 5, var4);
/*      */       } 
/*      */ 
/*      */       
/* 1072 */       a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true)), 4, 3, 2, var4);
/* 1073 */       a(var0, Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true)), 4, 3, 8, var4);
/* 1074 */       IBlockData var7 = Blocks.IRON_DOOR.getBlockData().set(BlockDoor.FACING, EnumDirection.WEST);
/* 1075 */       IBlockData var8 = Blocks.IRON_DOOR.getBlockData().set(BlockDoor.FACING, EnumDirection.WEST).set(BlockDoor.HALF, BlockPropertyDoubleBlockHalf.UPPER);
/* 1076 */       a(var0, var7, 4, 1, 2, var4);
/* 1077 */       a(var0, var8, 4, 2, 2, var4);
/* 1078 */       a(var0, var7, 4, 1, 8, var4);
/* 1079 */       a(var0, var8, 4, 2, 8, var4);
/*      */       
/* 1081 */       return true;
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WorldGenStrongholdLibrary
/*      */     extends WorldGenStrongholdPiece
/*      */   {
/*      */     private final boolean a;
/*      */ 
/*      */     
/*      */     public WorldGenStrongholdLibrary(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/* 1094 */       super(WorldGenFeatureStructurePieceType.x, var0);
/*      */       
/* 1096 */       a(var3);
/* 1097 */       this.d = a(var1);
/* 1098 */       this.n = var2;
/* 1099 */       this.a = (var2.e() > 6);
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdLibrary(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1103 */       super(WorldGenFeatureStructurePieceType.x, var1);
/* 1104 */       this.a = var1.getBoolean("Tall");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/* 1109 */       super.a(var0);
/* 1110 */       var0.setBoolean("Tall", this.a);
/*      */     }
/*      */ 
/*      */     
/*      */     public static WorldGenStrongholdLibrary a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/* 1115 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -4, -1, 0, 14, 11, 15, var5);
/*      */       
/* 1117 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/*      */         
/* 1119 */         var7 = StructureBoundingBox.a(var2, var3, var4, -4, -1, 0, 14, 6, 15, var5);
/*      */         
/* 1121 */         if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/* 1122 */           return null;
/*      */         }
/*      */       } 
/*      */       
/* 1126 */       return new WorldGenStrongholdLibrary(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1131 */       int var7 = 11;
/* 1132 */       if (!this.a) {
/* 1133 */         var7 = 6;
/*      */       }
/*      */ 
/*      */       
/* 1137 */       a(var0, var4, 0, 0, 0, 13, var7 - 1, 14, true, var3, WorldGenStrongholdPieces.b());
/*      */       
/* 1139 */       a(var0, var3, var4, this.d, 4, 1, 0);
/*      */ 
/*      */       
/* 1142 */       a(var0, var4, var3, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.COBWEB.getBlockData(), Blocks.COBWEB.getBlockData(), false, false);
/*      */       
/* 1144 */       int var8 = 1;
/* 1145 */       int var9 = 12;
/*      */       
/*      */       int var10;
/* 1148 */       for (var10 = 1; var10 <= 13; var10++) {
/* 1149 */         if ((var10 - 1) % 4 == 0) {
/* 1150 */           a(var0, var4, 1, 1, var10, 1, 4, var10, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
/* 1151 */           a(var0, var4, 12, 1, var10, 12, 4, var10, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
/*      */           
/* 1153 */           a(var0, Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.EAST), 2, 3, var10, var4);
/* 1154 */           a(var0, Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.WEST), 11, 3, var10, var4);
/*      */           
/* 1156 */           if (this.a) {
/* 1157 */             a(var0, var4, 1, 6, var10, 1, 9, var10, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
/* 1158 */             a(var0, var4, 12, 6, var10, 12, 9, var10, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
/*      */           } 
/*      */         } else {
/* 1161 */           a(var0, var4, 1, 1, var10, 1, 4, var10, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
/* 1162 */           a(var0, var4, 12, 1, var10, 12, 4, var10, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
/*      */           
/* 1164 */           if (this.a) {
/* 1165 */             a(var0, var4, 1, 6, var10, 1, 9, var10, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
/* 1166 */             a(var0, var4, 12, 6, var10, 12, 9, var10, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1172 */       for (var10 = 3; var10 < 12; var10 += 2) {
/* 1173 */         a(var0, var4, 3, 1, var10, 4, 3, var10, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
/* 1174 */         a(var0, var4, 6, 1, var10, 7, 3, var10, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
/* 1175 */         a(var0, var4, 9, 1, var10, 10, 3, var10, Blocks.BOOKSHELF.getBlockData(), Blocks.BOOKSHELF.getBlockData(), false);
/*      */       } 
/*      */       
/* 1178 */       if (this.a) {
/*      */         
/* 1180 */         a(var0, var4, 1, 5, 1, 3, 5, 13, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
/* 1181 */         a(var0, var4, 10, 5, 1, 12, 5, 13, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
/* 1182 */         a(var0, var4, 4, 5, 1, 9, 5, 2, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
/* 1183 */         a(var0, var4, 4, 5, 12, 9, 5, 13, Blocks.OAK_PLANKS.getBlockData(), Blocks.OAK_PLANKS.getBlockData(), false);
/*      */         
/* 1185 */         a(var0, Blocks.OAK_PLANKS.getBlockData(), 9, 5, 11, var4);
/* 1186 */         a(var0, Blocks.OAK_PLANKS.getBlockData(), 8, 5, 11, var4);
/* 1187 */         a(var0, Blocks.OAK_PLANKS.getBlockData(), 9, 5, 10, var4);
/*      */         
/* 1189 */         IBlockData iBlockData1 = Blocks.OAK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true));
/* 1190 */         IBlockData var11 = Blocks.OAK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.SOUTH, Boolean.valueOf(true));
/*      */ 
/*      */         
/* 1193 */         a(var0, var4, 3, 6, 3, 3, 6, 11, var11, var11, false);
/* 1194 */         a(var0, var4, 10, 6, 3, 10, 6, 9, var11, var11, false);
/* 1195 */         a(var0, var4, 4, 6, 2, 9, 6, 2, iBlockData1, iBlockData1, false);
/* 1196 */         a(var0, var4, 4, 6, 12, 7, 6, 12, iBlockData1, iBlockData1, false);
/*      */         
/* 1198 */         a(var0, Blocks.OAK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true)), 3, 6, 2, var4);
/* 1199 */         a(var0, Blocks.OAK_FENCE.getBlockData().set(BlockFence.SOUTH, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true)), 3, 6, 12, var4);
/* 1200 */         a(var0, Blocks.OAK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.WEST, Boolean.valueOf(true)), 10, 6, 2, var4);
/*      */         
/* 1202 */         for (int i = 0; i <= 2; i++) {
/* 1203 */           a(var0, Blocks.OAK_FENCE.getBlockData().set(BlockFence.SOUTH, Boolean.valueOf(true)).set(BlockFence.WEST, Boolean.valueOf(true)), 8 + i, 6, 12 - i, var4);
/* 1204 */           if (i != 2) {
/* 1205 */             a(var0, Blocks.OAK_FENCE.getBlockData().set(BlockFence.NORTH, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true)), 8 + i, 6, 11 - i, var4);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1210 */         IBlockData var12 = Blocks.LADDER.getBlockData().set(BlockLadder.FACING, EnumDirection.SOUTH);
/* 1211 */         a(var0, var12, 10, 1, 13, var4);
/* 1212 */         a(var0, var12, 10, 2, 13, var4);
/* 1213 */         a(var0, var12, 10, 3, 13, var4);
/* 1214 */         a(var0, var12, 10, 4, 13, var4);
/* 1215 */         a(var0, var12, 10, 5, 13, var4);
/* 1216 */         a(var0, var12, 10, 6, 13, var4);
/* 1217 */         a(var0, var12, 10, 7, 13, var4);
/*      */ 
/*      */         
/* 1220 */         int var13 = 7;
/* 1221 */         int var14 = 7;
/* 1222 */         IBlockData var15 = Blocks.OAK_FENCE.getBlockData().set(BlockFence.EAST, Boolean.valueOf(true));
/* 1223 */         a(var0, var15, 6, 9, 7, var4);
/* 1224 */         IBlockData var16 = Blocks.OAK_FENCE.getBlockData().set(BlockFence.WEST, Boolean.valueOf(true));
/* 1225 */         a(var0, var16, 7, 9, 7, var4);
/*      */         
/* 1227 */         a(var0, var15, 6, 8, 7, var4);
/* 1228 */         a(var0, var16, 7, 8, 7, var4);
/*      */         
/* 1230 */         IBlockData var17 = var11.set(BlockFence.WEST, Boolean.valueOf(true)).set(BlockFence.EAST, Boolean.valueOf(true));
/*      */         
/* 1232 */         a(var0, var17, 6, 7, 7, var4);
/* 1233 */         a(var0, var17, 7, 7, 7, var4);
/*      */         
/* 1235 */         a(var0, var15, 5, 7, 7, var4);
/*      */         
/* 1237 */         a(var0, var16, 8, 7, 7, var4);
/*      */         
/* 1239 */         a(var0, var15.set(BlockFence.NORTH, Boolean.valueOf(true)), 6, 7, 6, var4);
/* 1240 */         a(var0, var15.set(BlockFence.SOUTH, Boolean.valueOf(true)), 6, 7, 8, var4);
/*      */         
/* 1242 */         a(var0, var16.set(BlockFence.NORTH, Boolean.valueOf(true)), 7, 7, 6, var4);
/* 1243 */         a(var0, var16.set(BlockFence.SOUTH, Boolean.valueOf(true)), 7, 7, 8, var4);
/*      */         
/* 1245 */         IBlockData var18 = Blocks.TORCH.getBlockData();
/* 1246 */         a(var0, var18, 5, 8, 7, var4);
/* 1247 */         a(var0, var18, 8, 8, 7, var4);
/* 1248 */         a(var0, var18, 6, 8, 6, var4);
/* 1249 */         a(var0, var18, 6, 8, 8, var4);
/* 1250 */         a(var0, var18, 7, 8, 6, var4);
/* 1251 */         a(var0, var18, 7, 8, 8, var4);
/*      */       } 
/*      */ 
/*      */       
/* 1255 */       a(var0, var4, var3, 3, 3, 5, LootTables.w);
/* 1256 */       if (this.a) {
/* 1257 */         a(var0, m, 12, 9, 1, var4);
/* 1258 */         a(var0, var4, var3, 12, 8, 1, LootTables.w);
/*      */       } 
/*      */       
/* 1261 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WorldGenStrongholdCrossing
/*      */     extends WorldGenStrongholdPiece
/*      */   {
/*      */     private final boolean a;
/*      */     
/*      */     private final boolean b;
/*      */     private final boolean c;
/*      */     private final boolean e;
/*      */     
/*      */     public WorldGenStrongholdCrossing(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3) {
/* 1276 */       super(WorldGenFeatureStructurePieceType.v, var0);
/*      */       
/* 1278 */       a(var3);
/* 1279 */       this.d = a(var1);
/* 1280 */       this.n = var2;
/*      */       
/* 1282 */       this.a = var1.nextBoolean();
/* 1283 */       this.b = var1.nextBoolean();
/* 1284 */       this.c = var1.nextBoolean();
/* 1285 */       this.e = (var1.nextInt(3) > 0);
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdCrossing(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1289 */       super(WorldGenFeatureStructurePieceType.v, var1);
/* 1290 */       this.a = var1.getBoolean("leftLow");
/* 1291 */       this.b = var1.getBoolean("leftHigh");
/* 1292 */       this.c = var1.getBoolean("rightLow");
/* 1293 */       this.e = var1.getBoolean("rightHigh");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/* 1298 */       super.a(var0);
/* 1299 */       var0.setBoolean("leftLow", this.a);
/* 1300 */       var0.setBoolean("leftHigh", this.b);
/* 1301 */       var0.setBoolean("rightLow", this.c);
/* 1302 */       var0.setBoolean("rightHigh", this.e);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 1307 */       int var3 = 3;
/* 1308 */       int var4 = 5;
/*      */       
/* 1310 */       EnumDirection var5 = i();
/* 1311 */       if (var5 == EnumDirection.WEST || var5 == EnumDirection.NORTH) {
/* 1312 */         var3 = 8 - var3;
/* 1313 */         var4 = 8 - var4;
/*      */       } 
/*      */       
/* 1316 */       a((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, 5, 1);
/* 1317 */       if (this.a) {
/* 1318 */         b((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, var3, 1);
/*      */       }
/* 1320 */       if (this.b) {
/* 1321 */         b((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, var4, 7);
/*      */       }
/* 1323 */       if (this.c) {
/* 1324 */         c((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, var3, 1);
/*      */       }
/* 1326 */       if (this.e) {
/* 1327 */         c((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0, var1, var2, var4, 7);
/*      */       }
/*      */     }
/*      */     
/*      */     public static WorldGenStrongholdCrossing a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5, int var6) {
/* 1332 */       StructureBoundingBox var7 = StructureBoundingBox.a(var2, var3, var4, -4, -3, 0, 10, 9, 11, var5);
/*      */       
/* 1334 */       if (!a(var7) || StructurePiece.a(var0, var7) != null) {
/* 1335 */         return null;
/*      */       }
/*      */       
/* 1338 */       return new WorldGenStrongholdCrossing(var6, var1, var7, var5);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1344 */       a(var0, var4, 0, 0, 0, 9, 8, 10, true, var3, WorldGenStrongholdPieces.b());
/*      */       
/* 1346 */       a(var0, var3, var4, this.d, 4, 3, 0);
/*      */ 
/*      */       
/* 1349 */       if (this.a) {
/* 1350 */         a(var0, var4, 0, 3, 1, 0, 5, 3, m, m, false);
/*      */       }
/* 1352 */       if (this.c) {
/* 1353 */         a(var0, var4, 9, 3, 1, 9, 5, 3, m, m, false);
/*      */       }
/* 1355 */       if (this.b) {
/* 1356 */         a(var0, var4, 0, 5, 7, 0, 7, 9, m, m, false);
/*      */       }
/* 1358 */       if (this.e) {
/* 1359 */         a(var0, var4, 9, 5, 7, 9, 7, 9, m, m, false);
/*      */       }
/* 1361 */       a(var0, var4, 5, 1, 10, 7, 3, 10, m, m, false);
/*      */ 
/*      */       
/* 1364 */       a(var0, var4, 1, 2, 1, 8, 2, 6, false, var3, WorldGenStrongholdPieces.b());
/*      */       
/* 1366 */       a(var0, var4, 4, 1, 5, 4, 4, 9, false, var3, WorldGenStrongholdPieces.b());
/* 1367 */       a(var0, var4, 8, 1, 5, 8, 4, 9, false, var3, WorldGenStrongholdPieces.b());
/*      */       
/* 1369 */       a(var0, var4, 1, 4, 7, 3, 4, 9, false, var3, WorldGenStrongholdPieces.b());
/*      */ 
/*      */       
/* 1372 */       a(var0, var4, 1, 3, 5, 3, 3, 6, false, var3, WorldGenStrongholdPieces.b());
/* 1373 */       a(var0, var4, 1, 3, 4, 3, 3, 4, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
/* 1374 */       a(var0, var4, 1, 4, 6, 3, 4, 6, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
/*      */ 
/*      */       
/* 1377 */       a(var0, var4, 5, 1, 7, 7, 1, 8, false, var3, WorldGenStrongholdPieces.b());
/* 1378 */       a(var0, var4, 5, 1, 9, 7, 1, 9, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
/* 1379 */       a(var0, var4, 5, 2, 7, 7, 2, 7, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
/*      */ 
/*      */       
/* 1382 */       a(var0, var4, 4, 5, 7, 4, 5, 9, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
/* 1383 */       a(var0, var4, 8, 5, 7, 8, 5, 9, Blocks.SMOOTH_STONE_SLAB.getBlockData(), Blocks.SMOOTH_STONE_SLAB.getBlockData(), false);
/* 1384 */       a(var0, var4, 5, 5, 7, 7, 5, 9, Blocks.SMOOTH_STONE_SLAB.getBlockData().set(BlockStepAbstract.a, BlockPropertySlabType.DOUBLE), Blocks.SMOOTH_STONE_SLAB.getBlockData().set(BlockStepAbstract.a, BlockPropertySlabType.DOUBLE), false);
/* 1385 */       a(var0, Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.SOUTH), 6, 5, 6, var4);
/*      */       
/* 1387 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class WorldGenStrongholdPortalRoom
/*      */     extends WorldGenStrongholdPiece
/*      */   {
/*      */     private boolean a;
/*      */ 
/*      */     
/*      */     public WorldGenStrongholdPortalRoom(int var0, StructureBoundingBox var1, EnumDirection var2) {
/* 1399 */       super(WorldGenFeatureStructurePieceType.y, var0);
/*      */       
/* 1401 */       a(var2);
/* 1402 */       this.n = var1;
/*      */     }
/*      */     
/*      */     public WorldGenStrongholdPortalRoom(DefinedStructureManager var0, NBTTagCompound var1) {
/* 1406 */       super(WorldGenFeatureStructurePieceType.y, var1);
/* 1407 */       this.a = var1.getBoolean("Mob");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/* 1412 */       super.a(var0);
/* 1413 */       var0.setBoolean("Mob", this.a);
/*      */     }
/*      */ 
/*      */     
/*      */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 1418 */       if (var0 != null) {
/* 1419 */         ((WorldGenStrongholdPieces.WorldGenStrongholdStart)var0).b = this;
/*      */       }
/*      */     }
/*      */     
/*      */     public static WorldGenStrongholdPortalRoom a(List<StructurePiece> var0, int var1, int var2, int var3, EnumDirection var4, int var5) {
/* 1424 */       StructureBoundingBox var6 = StructureBoundingBox.a(var1, var2, var3, -4, -1, 0, 11, 8, 16, var4);
/*      */       
/* 1426 */       if (!a(var6) || StructurePiece.a(var0, var6) != null) {
/* 1427 */         return null;
/*      */       }
/*      */       
/* 1430 */       return new WorldGenStrongholdPortalRoom(var5, var6, var4);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 1436 */       a(var0, var4, 0, 0, 0, 10, 7, 15, false, var3, WorldGenStrongholdPieces.b());
/*      */       
/* 1438 */       a(var0, var3, var4, WorldGenStrongholdPieces.WorldGenStrongholdPiece.WorldGenStrongholdDoorType.GRATES, 4, 1, 0);
/*      */ 
/*      */       
/* 1441 */       int var7 = 6;
/* 1442 */       a(var0, var4, 1, var7, 1, 1, var7, 14, false, var3, WorldGenStrongholdPieces.b());
/* 1443 */       a(var0, var4, 9, var7, 1, 9, var7, 14, false, var3, WorldGenStrongholdPieces.b());
/* 1444 */       a(var0, var4, 2, var7, 1, 8, var7, 2, false, var3, WorldGenStrongholdPieces.b());
/* 1445 */       a(var0, var4, 2, var7, 14, 8, var7, 14, false, var3, WorldGenStrongholdPieces.b());
/*      */ 
/*      */       
/* 1448 */       a(var0, var4, 1, 1, 1, 2, 1, 4, false, var3, WorldGenStrongholdPieces.b());
/* 1449 */       a(var0, var4, 8, 1, 1, 9, 1, 4, false, var3, WorldGenStrongholdPieces.b());
/* 1450 */       a(var0, var4, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getBlockData(), Blocks.LAVA.getBlockData(), false);
/* 1451 */       a(var0, var4, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getBlockData(), Blocks.LAVA.getBlockData(), false);
/*      */ 
/*      */       
/* 1454 */       a(var0, var4, 3, 1, 8, 7, 1, 12, false, var3, WorldGenStrongholdPieces.b());
/* 1455 */       a(var0, var4, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getBlockData(), Blocks.LAVA.getBlockData(), false);
/*      */ 
/*      */       
/* 1458 */       IBlockData var8 = Blocks.IRON_BARS.getBlockData().set(BlockIronBars.NORTH, Boolean.valueOf(true)).set(BlockIronBars.SOUTH, Boolean.valueOf(true));
/* 1459 */       IBlockData var9 = Blocks.IRON_BARS.getBlockData().set(BlockIronBars.WEST, Boolean.valueOf(true)).set(BlockIronBars.EAST, Boolean.valueOf(true)); int i;
/* 1460 */       for (i = 3; i < 14; i += 2) {
/* 1461 */         a(var0, var4, 0, 3, i, 0, 4, i, var8, var8, false);
/* 1462 */         a(var0, var4, 10, 3, i, 10, 4, i, var8, var8, false);
/*      */       } 
/* 1464 */       for (i = 2; i < 9; i += 2) {
/* 1465 */         a(var0, var4, i, 3, 15, i, 4, 15, var9, var9, false);
/*      */       }
/*      */ 
/*      */       
/* 1469 */       IBlockData var10 = Blocks.STONE_BRICK_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.NORTH);
/* 1470 */       a(var0, var4, 4, 1, 5, 6, 1, 7, false, var3, WorldGenStrongholdPieces.b());
/* 1471 */       a(var0, var4, 4, 2, 6, 6, 2, 7, false, var3, WorldGenStrongholdPieces.b());
/* 1472 */       a(var0, var4, 4, 3, 7, 6, 3, 7, false, var3, WorldGenStrongholdPieces.b());
/* 1473 */       for (int j = 4; j <= 6; j++) {
/* 1474 */         a(var0, var10, j, 1, 4, var4);
/* 1475 */         a(var0, var10, j, 2, 5, var4);
/* 1476 */         a(var0, var10, j, 3, 6, var4);
/*      */       } 
/*      */       
/* 1479 */       IBlockData var11 = Blocks.END_PORTAL_FRAME.getBlockData().set(BlockEnderPortalFrame.FACING, EnumDirection.NORTH);
/* 1480 */       IBlockData var12 = Blocks.END_PORTAL_FRAME.getBlockData().set(BlockEnderPortalFrame.FACING, EnumDirection.SOUTH);
/* 1481 */       IBlockData var13 = Blocks.END_PORTAL_FRAME.getBlockData().set(BlockEnderPortalFrame.FACING, EnumDirection.EAST);
/* 1482 */       IBlockData var14 = Blocks.END_PORTAL_FRAME.getBlockData().set(BlockEnderPortalFrame.FACING, EnumDirection.WEST);
/*      */       
/* 1484 */       boolean var15 = true;
/* 1485 */       boolean[] var16 = new boolean[12];
/* 1486 */       for (int var17 = 0; var17 < var16.length; var17++) {
/* 1487 */         var16[var17] = (var3.nextFloat() > 0.9F);
/* 1488 */         var15 &= var16[var17];
/*      */       } 
/*      */       
/* 1491 */       a(var0, var11.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[0])), 4, 3, 8, var4);
/* 1492 */       a(var0, var11.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[1])), 5, 3, 8, var4);
/* 1493 */       a(var0, var11.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[2])), 6, 3, 8, var4);
/* 1494 */       a(var0, var12.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[3])), 4, 3, 12, var4);
/* 1495 */       a(var0, var12.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[4])), 5, 3, 12, var4);
/* 1496 */       a(var0, var12.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[5])), 6, 3, 12, var4);
/* 1497 */       a(var0, var13.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[6])), 3, 3, 9, var4);
/* 1498 */       a(var0, var13.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[7])), 3, 3, 10, var4);
/* 1499 */       a(var0, var13.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[8])), 3, 3, 11, var4);
/* 1500 */       a(var0, var14.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[9])), 7, 3, 9, var4);
/* 1501 */       a(var0, var14.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[10])), 7, 3, 10, var4);
/* 1502 */       a(var0, var14.set(BlockEnderPortalFrame.EYE, Boolean.valueOf(var16[11])), 7, 3, 11, var4);
/*      */       
/* 1504 */       if (var15) {
/* 1505 */         IBlockData iBlockData = Blocks.END_PORTAL.getBlockData();
/*      */         
/* 1507 */         a(var0, iBlockData, 4, 3, 9, var4);
/* 1508 */         a(var0, iBlockData, 5, 3, 9, var4);
/* 1509 */         a(var0, iBlockData, 6, 3, 9, var4);
/* 1510 */         a(var0, iBlockData, 4, 3, 10, var4);
/* 1511 */         a(var0, iBlockData, 5, 3, 10, var4);
/* 1512 */         a(var0, iBlockData, 6, 3, 10, var4);
/* 1513 */         a(var0, iBlockData, 4, 3, 11, var4);
/* 1514 */         a(var0, iBlockData, 5, 3, 11, var4);
/* 1515 */         a(var0, iBlockData, 6, 3, 11, var4);
/*      */       } 
/*      */       
/* 1518 */       if (!this.a) {
/* 1519 */         var7 = d(3);
/* 1520 */         BlockPosition blockPosition = new BlockPosition(a(5, 6), var7, b(5, 6));
/* 1521 */         if (var4.b(blockPosition)) {
/* 1522 */           this.a = true;
/* 1523 */           var0.setTypeAndData(blockPosition, Blocks.SPAWNER.getBlockData(), 2);
/*      */           
/* 1525 */           TileEntity var18 = var0.getTileEntity(blockPosition);
/* 1526 */           if (var18 instanceof TileEntityMobSpawner) {
/* 1527 */             ((TileEntityMobSpawner)var18).getSpawner().setMobName(EntityTypes.SILVERFISH);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1532 */       return true;
/*      */     } }
/*      */   
/*      */   static class WorldGenStrongholdStones extends StructurePiece.StructurePieceBlockSelector {
/*      */     private WorldGenStrongholdStones() {}
/*      */     
/*      */     public void a(Random var0, int var1, int var2, int var3, boolean var4) {
/* 1539 */       if (var4) {
/* 1540 */         float var5 = var0.nextFloat();
/* 1541 */         if (var5 < 0.2F) {
/* 1542 */           this.a = Blocks.CRACKED_STONE_BRICKS.getBlockData();
/* 1543 */         } else if (var5 < 0.5F) {
/* 1544 */           this.a = Blocks.MOSSY_STONE_BRICKS.getBlockData();
/* 1545 */         } else if (var5 < 0.55F) {
/* 1546 */           this.a = Blocks.INFESTED_STONE_BRICKS.getBlockData();
/*      */         } else {
/* 1548 */           this.a = Blocks.STONE_BRICKS.getBlockData();
/*      */         } 
/*      */       } else {
/* 1551 */         this.a = Blocks.CAVE_AIR.getBlockData();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/* 1556 */   private static final WorldGenStrongholdStones e = new WorldGenStrongholdStones();
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenStrongholdPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */