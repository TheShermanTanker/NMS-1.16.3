/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class StructurePiece
/*     */ {
/*  66 */   protected static final IBlockData m = Blocks.CAVE_AIR.getBlockData();
/*     */   protected StructureBoundingBox n;
/*     */   @Nullable
/*     */   private EnumDirection a;
/*     */   private EnumBlockMirror b;
/*     */   private EnumBlockRotation c;
/*     */   protected int o;
/*     */   private final WorldGenFeatureStructurePieceType d;
/*     */   
/*     */   protected StructurePiece(WorldGenFeatureStructurePieceType var0, int var1) {
/*  76 */     this.d = var0;
/*  77 */     this.o = var1;
/*     */   }
/*     */   
/*     */   public StructurePiece(WorldGenFeatureStructurePieceType var0, NBTTagCompound var1) {
/*  81 */     this(var0, var1.getInt("GD"));
/*     */     
/*  83 */     if (var1.hasKey("BB")) {
/*  84 */       this.n = new StructureBoundingBox(var1.getIntArray("BB"));
/*     */     }
/*  86 */     int var2 = var1.getInt("O");
/*  87 */     a((var2 == -1) ? null : EnumDirection.fromType2(var2));
/*     */   }
/*     */   
/*     */   public final NBTTagCompound f() {
/*  91 */     NBTTagCompound var0 = new NBTTagCompound();
/*     */     
/*  93 */     var0.setString("id", IRegistry.STRUCTURE_PIECE.getKey(k()).toString());
/*  94 */     var0.set("BB", this.n.h());
/*  95 */     EnumDirection var1 = i();
/*  96 */     var0.setInt("O", (var1 == null) ? -1 : var1.get2DRotationValue());
/*  97 */     var0.setInt("GD", this.o);
/*     */     
/*  99 */     a(var0);
/*     */     
/* 101 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void a(NBTTagCompound paramNBTTagCompound);
/*     */   
/*     */   public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {}
/*     */   
/*     */   public abstract boolean a(GeneratorAccessSeed paramGeneratorAccessSeed, StructureManager paramStructureManager, ChunkGenerator paramChunkGenerator, Random paramRandom, StructureBoundingBox paramStructureBoundingBox, ChunkCoordIntPair paramChunkCoordIntPair, BlockPosition paramBlockPosition);
/*     */   
/*     */   public StructureBoundingBox g() {
/* 112 */     return this.n;
/*     */   }
/*     */   
/*     */   public int h() {
/* 116 */     return this.o;
/*     */   }
/*     */   
/*     */   public boolean a(ChunkCoordIntPair var0, int var1) {
/* 120 */     int var2 = var0.x << 4;
/* 121 */     int var3 = var0.z << 4;
/*     */     
/* 123 */     return this.n.a(var2 - var1, var3 - var1, var2 + 15 + var1, var3 + 15 + var1);
/*     */   }
/*     */   
/*     */   public static StructurePiece a(List<StructurePiece> var0, StructureBoundingBox var1) {
/* 127 */     for (StructurePiece var3 : var0) {
/* 128 */       if (var3.g() != null && var3.g().b(var1)) {
/* 129 */         return var3;
/*     */       }
/*     */     } 
/* 132 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(IBlockAccess var0, StructureBoundingBox var1) {
/* 140 */     int var2 = Math.max(this.n.a - 1, var1.a);
/* 141 */     int var3 = Math.max(this.n.b - 1, var1.b);
/* 142 */     int var4 = Math.max(this.n.c - 1, var1.c);
/* 143 */     int var5 = Math.min(this.n.d + 1, var1.d);
/* 144 */     int var6 = Math.min(this.n.e + 1, var1.e);
/* 145 */     int var7 = Math.min(this.n.f + 1, var1.f);
/*     */     
/* 147 */     BlockPosition.MutableBlockPosition var8 = new BlockPosition.MutableBlockPosition();
/*     */     
/*     */     int var9;
/* 150 */     for (var9 = var2; var9 <= var5; var9++) {
/* 151 */       for (int var10 = var4; var10 <= var7; var10++) {
/* 152 */         if (var0.getType(var8.d(var9, var3, var10)).getMaterial().isLiquid()) {
/* 153 */           return true;
/*     */         }
/* 155 */         if (var0.getType(var8.d(var9, var6, var10)).getMaterial().isLiquid()) {
/* 156 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 161 */     for (var9 = var2; var9 <= var5; var9++) {
/* 162 */       for (int var10 = var3; var10 <= var6; var10++) {
/* 163 */         if (var0.getType(var8.d(var9, var10, var4)).getMaterial().isLiquid()) {
/* 164 */           return true;
/*     */         }
/* 166 */         if (var0.getType(var8.d(var9, var10, var7)).getMaterial().isLiquid()) {
/* 167 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 172 */     for (var9 = var4; var9 <= var7; var9++) {
/* 173 */       for (int var10 = var3; var10 <= var6; var10++) {
/* 174 */         if (var0.getType(var8.d(var2, var10, var9)).getMaterial().isLiquid()) {
/* 175 */           return true;
/*     */         }
/* 177 */         if (var0.getType(var8.d(var5, var10, var9)).getMaterial().isLiquid()) {
/* 178 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int a(int var0, int var1) {
/* 190 */     EnumDirection var2 = i();
/* 191 */     if (var2 == null) {
/* 192 */       return var0;
/*     */     }
/*     */     
/* 195 */     switch (null.a[var2.ordinal()]) {
/*     */       case 1:
/*     */       case 2:
/* 198 */         return this.n.a + var0;
/*     */       case 3:
/* 200 */         return this.n.d - var1;
/*     */       case 4:
/* 202 */         return this.n.a + var1;
/*     */     } 
/* 204 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int d(int var0) {
/* 209 */     if (i() == null) {
/* 210 */       return var0;
/*     */     }
/* 212 */     return var0 + this.n.b;
/*     */   }
/*     */   
/*     */   protected int b(int var0, int var1) {
/* 216 */     EnumDirection var2 = i();
/* 217 */     if (var2 == null) {
/* 218 */       return var1;
/*     */     }
/*     */     
/* 221 */     switch (null.a[var2.ordinal()]) {
/*     */       case 1:
/* 223 */         return this.n.f - var1;
/*     */       case 2:
/* 225 */         return this.n.c + var1;
/*     */       case 3:
/*     */       case 4:
/* 228 */         return this.n.c + var0;
/*     */     } 
/* 230 */     return var1;
/*     */   }
/*     */ 
/*     */   
/* 234 */   private static final Set<Block> e = (Set<Block>)ImmutableSet.builder()
/* 235 */     .add(Blocks.NETHER_BRICK_FENCE)
/* 236 */     .add(Blocks.TORCH)
/* 237 */     .add(Blocks.WALL_TORCH)
/* 238 */     .add(Blocks.OAK_FENCE)
/* 239 */     .add(Blocks.SPRUCE_FENCE)
/* 240 */     .add(Blocks.DARK_OAK_FENCE)
/* 241 */     .add(Blocks.ACACIA_FENCE)
/* 242 */     .add(Blocks.BIRCH_FENCE)
/* 243 */     .add(Blocks.JUNGLE_FENCE)
/* 244 */     .add(Blocks.LADDER)
/* 245 */     .add(Blocks.IRON_BARS)
/* 246 */     .build();
/*     */   
/*     */   protected void a(GeneratorAccessSeed var0, IBlockData var1, int var2, int var3, int var4, StructureBoundingBox var5) {
/* 249 */     BlockPosition var6 = new BlockPosition(a(var2, var4), d(var3), b(var2, var4));
/*     */     
/* 251 */     if (!var5.b(var6)) {
/*     */       return;
/*     */     }
/*     */     
/* 255 */     if (this.b != EnumBlockMirror.NONE) {
/* 256 */       var1 = var1.a(this.b);
/*     */     }
/* 258 */     if (this.c != EnumBlockRotation.NONE) {
/* 259 */       var1 = var1.a(this.c);
/*     */     }
/*     */     
/* 262 */     var0.setTypeAndData(var6, var1, 2);
/* 263 */     Fluid var7 = var0.getFluid(var6);
/* 264 */     if (!var7.isEmpty()) {
/* 265 */       var0.getFluidTickList().a(var6, var7.getType(), 0);
/*     */     }
/* 267 */     if (e.contains(var1.getBlock())) {
/* 268 */       var0.z(var6).e(var6);
/*     */     }
/*     */   }
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
/*     */   protected IBlockData a(IBlockAccess var0, int var1, int var2, int var3, StructureBoundingBox var4) {
/* 285 */     int var5 = a(var1, var3);
/* 286 */     int var6 = d(var2);
/* 287 */     int var7 = b(var1, var3);
/*     */     
/* 289 */     BlockPosition var8 = new BlockPosition(var5, var6, var7);
/* 290 */     if (!var4.b(var8)) {
/* 291 */       return Blocks.AIR.getBlockData();
/*     */     }
/*     */     
/* 294 */     return var0.getType(var8);
/*     */   }
/*     */   
/*     */   protected boolean a(IWorldReader var0, int var1, int var2, int var3, StructureBoundingBox var4) {
/* 298 */     int var5 = a(var1, var3);
/* 299 */     int var6 = d(var2 + 1);
/* 300 */     int var7 = b(var1, var3);
/*     */     
/* 302 */     BlockPosition var8 = new BlockPosition(var5, var6, var7);
/*     */     
/* 304 */     if (!var4.b(var8)) {
/* 305 */       return false;
/*     */     }
/*     */     
/* 308 */     return (var6 < var0.a(HeightMap.Type.OCEAN_FLOOR_WG, var5, var7));
/*     */   }
/*     */   
/*     */   protected void b(GeneratorAccessSeed var0, StructureBoundingBox var1, int var2, int var3, int var4, int var5, int var6, int var7) {
/* 312 */     for (int var8 = var3; var8 <= var6; var8++) {
/* 313 */       for (int var9 = var2; var9 <= var5; var9++) {
/* 314 */         for (int var10 = var4; var10 <= var7; var10++) {
/* 315 */           a(var0, Blocks.AIR.getBlockData(), var9, var8, var10, var1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(GeneratorAccessSeed var0, StructureBoundingBox var1, int var2, int var3, int var4, int var5, int var6, int var7, IBlockData var8, IBlockData var9, boolean var10) {
/* 322 */     for (int var11 = var3; var11 <= var6; var11++) {
/* 323 */       for (int var12 = var2; var12 <= var5; var12++) {
/* 324 */         for (int var13 = var4; var13 <= var7; var13++) {
/* 325 */           if (!var10 || !a(var0, var12, var11, var13, var1).isAir())
/*     */           {
/*     */             
/* 328 */             if (var11 == var3 || var11 == var6 || var12 == var2 || var12 == var5 || var13 == var4 || var13 == var7) {
/* 329 */               a(var0, var8, var12, var11, var13, var1);
/*     */             } else {
/* 331 */               a(var0, var9, var12, var11, var13, var1);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(GeneratorAccessSeed var0, StructureBoundingBox var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, Random var9, StructurePieceBlockSelector var10) {
/* 343 */     for (int var11 = var3; var11 <= var6; var11++) {
/* 344 */       for (int var12 = var2; var12 <= var5; var12++) {
/* 345 */         for (int var13 = var4; var13 <= var7; var13++) {
/* 346 */           if (!var8 || !a(var0, var12, var11, var13, var1).isAir()) {
/*     */ 
/*     */             
/* 349 */             var10.a(var9, var12, var11, var13, (var11 == var3 || var11 == var6 || var12 == var2 || var12 == var5 || var13 == var4 || var13 == var7));
/* 350 */             a(var0, var10.a(), var12, var11, var13, var1);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(GeneratorAccessSeed var0, StructureBoundingBox var1, Random var2, float var3, int var4, int var5, int var6, int var7, int var8, int var9, IBlockData var10, IBlockData var11, boolean var12, boolean var13) {
/* 361 */     for (int var14 = var5; var14 <= var8; var14++) {
/* 362 */       for (int var15 = var4; var15 <= var7; var15++) {
/* 363 */         for (int var16 = var6; var16 <= var9; var16++) {
/* 364 */           if (var2.nextFloat() <= var3)
/*     */           {
/*     */             
/* 367 */             if (!var12 || !a(var0, var15, var14, var16, var1).isAir())
/*     */             {
/*     */               
/* 370 */               if (!var13 || a(var0, var15, var14, var16, var1))
/*     */               {
/*     */                 
/* 373 */                 if (var14 == var5 || var14 == var8 || var15 == var4 || var15 == var7 || var16 == var6 || var16 == var9) {
/* 374 */                   a(var0, var10, var15, var14, var16, var1);
/*     */                 } else {
/* 376 */                   a(var0, var11, var15, var14, var16, var1);
/*     */                 }  }  }  } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(GeneratorAccessSeed var0, StructureBoundingBox var1, Random var2, float var3, int var4, int var5, int var6, IBlockData var7) {
/* 384 */     if (var2.nextFloat() < var3) {
/* 385 */       a(var0, var7, var4, var5, var6, var1);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void a(GeneratorAccessSeed var0, StructureBoundingBox var1, int var2, int var3, int var4, int var5, int var6, int var7, IBlockData var8, boolean var9) {
/* 390 */     float var10 = (var5 - var2 + 1);
/* 391 */     float var11 = (var6 - var3 + 1);
/* 392 */     float var12 = (var7 - var4 + 1);
/*     */     
/* 394 */     float var13 = var2 + var10 / 2.0F;
/* 395 */     float var14 = var4 + var12 / 2.0F;
/*     */     
/* 397 */     for (int var15 = var3; var15 <= var6; var15++) {
/* 398 */       float var16 = (var15 - var3) / var11;
/*     */       
/* 400 */       for (int var17 = var2; var17 <= var5; var17++) {
/* 401 */         float var18 = (var17 - var13) / var10 * 0.5F;
/*     */         
/* 403 */         for (int var19 = var4; var19 <= var7; var19++) {
/* 404 */           float var20 = (var19 - var14) / var12 * 0.5F;
/*     */           
/* 406 */           if (!var9 || !a(var0, var17, var15, var19, var1).isAir()) {
/*     */ 
/*     */ 
/*     */             
/* 410 */             float var21 = var18 * var18 + var16 * var16 + var20 * var20;
/*     */             
/* 412 */             if (var21 <= 1.05F) {
/* 413 */               a(var0, var8, var17, var15, var19, var1);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
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
/*     */   protected void b(GeneratorAccessSeed var0, IBlockData var1, int var2, int var3, int var4, StructureBoundingBox var5) {
/* 434 */     int var6 = a(var2, var4);
/* 435 */     int var7 = d(var3);
/* 436 */     int var8 = b(var2, var4);
/*     */     
/* 438 */     if (!var5.b(new BlockPosition(var6, var7, var8))) {
/*     */       return;
/*     */     }
/*     */     
/* 442 */     while ((var0.isEmpty(new BlockPosition(var6, var7, var8)) || var0.getType(new BlockPosition(var6, var7, var8)).getMaterial().isLiquid()) && var7 > 1) {
/* 443 */       var0.setTypeAndData(new BlockPosition(var6, var7, var8), var1, 2);
/* 444 */       var7--;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean a(GeneratorAccessSeed var0, StructureBoundingBox var1, Random var2, int var3, int var4, int var5, MinecraftKey var6) {
/* 449 */     BlockPosition var7 = new BlockPosition(a(var3, var5), d(var4), b(var3, var5));
/* 450 */     return a(var0, var1, var2, var7, var6, (IBlockData)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static IBlockData a(IBlockAccess var0, BlockPosition var1, IBlockData var2) {
/* 455 */     EnumDirection var3 = null;
/* 456 */     for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 457 */       BlockPosition var6 = var1.shift(enumDirection);
/* 458 */       IBlockData var7 = var0.getType(var6);
/* 459 */       if (var7.a(Blocks.CHEST)) {
/* 460 */         return var2;
/*     */       }
/* 462 */       if (var7.i(var0, var6)) {
/* 463 */         if (var3 == null) {
/* 464 */           var3 = enumDirection; continue;
/*     */         } 
/* 466 */         var3 = null;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 471 */     if (var3 != null) {
/* 472 */       return var2.set(BlockFacingHorizontal.FACING, var3.opposite());
/*     */     }
/*     */ 
/*     */     
/* 476 */     EnumDirection var4 = (EnumDirection)var2.get(BlockFacingHorizontal.FACING);
/* 477 */     BlockPosition var5 = var1.shift(var4);
/* 478 */     if (var0.getType(var5).i(var0, var5)) {
/* 479 */       var4 = var4.opposite();
/* 480 */       var5 = var1.shift(var4);
/*     */     } 
/* 482 */     if (var0.getType(var5).i(var0, var5)) {
/* 483 */       var4 = var4.g();
/* 484 */       var5 = var1.shift(var4);
/*     */     } 
/* 486 */     if (var0.getType(var5).i(var0, var5)) {
/* 487 */       var4 = var4.opposite();
/* 488 */       var5 = var1.shift(var4);
/*     */     } 
/*     */     
/* 491 */     return var2.set(BlockFacingHorizontal.FACING, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(WorldAccess var0, StructureBoundingBox var1, Random var2, BlockPosition var3, MinecraftKey var4, @Nullable IBlockData var5) {
/* 498 */     if (!var1.b(var3) || var0.getType(var3).a(Blocks.CHEST)) {
/* 499 */       return false;
/*     */     }
/*     */     
/* 502 */     if (var5 == null) {
/* 503 */       var5 = a(var0, var3, Blocks.CHEST.getBlockData());
/*     */     }
/* 505 */     var0.setTypeAndData(var3, var5, 2);
/*     */     
/* 507 */     TileEntity var6 = var0.getTileEntity(var3);
/* 508 */     if (var6 instanceof TileEntityChest) {
/* 509 */       ((TileEntityChest)var6).setLootTable(var4, var2.nextLong());
/*     */     }
/* 511 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean a(GeneratorAccessSeed var0, StructureBoundingBox var1, Random var2, int var3, int var4, int var5, EnumDirection var6, MinecraftKey var7) {
/* 515 */     BlockPosition var8 = new BlockPosition(a(var3, var5), d(var4), b(var3, var5));
/*     */     
/* 517 */     if (var1.b(var8) && 
/* 518 */       !var0.getType(var8).a(Blocks.DISPENSER)) {
/* 519 */       a(var0, Blocks.DISPENSER.getBlockData().set(BlockDispenser.FACING, var6), var3, var4, var5, var1);
/*     */       
/* 521 */       TileEntity var9 = var0.getTileEntity(var8);
/* 522 */       if (var9 instanceof TileEntityDispenser) {
/* 523 */         ((TileEntityDispenser)var9).setLootTable(var7, var2.nextLong());
/*     */       }
/* 525 */       return true;
/*     */     } 
/*     */     
/* 528 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int var0, int var1, int var2) {
/* 537 */     this.n.a(var0, var1, var2);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EnumDirection i() {
/* 542 */     return this.a;
/*     */   }
/*     */   
/*     */   public void a(@Nullable EnumDirection var0) {
/* 546 */     this.a = var0;
/* 547 */     if (var0 == null) {
/* 548 */       this.c = EnumBlockRotation.NONE;
/* 549 */       this.b = EnumBlockMirror.NONE;
/*     */     } else {
/* 551 */       switch (null.a[var0.ordinal()]) {
/*     */         case 2:
/* 553 */           this.b = EnumBlockMirror.LEFT_RIGHT;
/* 554 */           this.c = EnumBlockRotation.NONE;
/*     */           return;
/*     */         case 3:
/* 557 */           this.b = EnumBlockMirror.LEFT_RIGHT;
/* 558 */           this.c = EnumBlockRotation.CLOCKWISE_90;
/*     */           return;
/*     */         case 4:
/* 561 */           this.b = EnumBlockMirror.NONE;
/* 562 */           this.c = EnumBlockRotation.CLOCKWISE_90;
/*     */           return;
/*     */       } 
/* 565 */       this.b = EnumBlockMirror.NONE;
/* 566 */       this.c = EnumBlockRotation.NONE;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumBlockRotation ap_() {
/* 573 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenFeatureStructurePieceType k() {
/* 581 */     return this.d;
/*     */   }
/*     */   
/*     */   public static abstract class StructurePieceBlockSelector {
/* 585 */     protected IBlockData a = Blocks.AIR.getBlockData();
/*     */     
/*     */     public abstract void a(Random param1Random, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean);
/*     */     
/*     */     public IBlockData a() {
/* 590 */       return this.a;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructurePiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */