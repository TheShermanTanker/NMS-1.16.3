/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Random;
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
/*     */ public class WorldGenMineshaftPieces
/*     */ {
/*     */   static abstract class c
/*     */     extends StructurePiece
/*     */   {
/*     */     protected WorldGenMineshaft.Type a;
/*     */     
/*     */     public c(WorldGenFeatureStructurePieceType var0, int var1, WorldGenMineshaft.Type var2) {
/*  47 */       super(var0, var1);
/*  48 */       this.a = var2;
/*     */     }
/*     */     
/*     */     public c(WorldGenFeatureStructurePieceType var0, NBTTagCompound var1) {
/*  52 */       super(var0, var1);
/*  53 */       this.a = WorldGenMineshaft.Type.a(var1.getInt("MST"));
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(NBTTagCompound var0) {
/*  58 */       var0.setInt("MST", this.a.ordinal());
/*     */     }
/*     */     
/*     */     protected IBlockData a() {
/*  62 */       switch (WorldGenMineshaftPieces.null.a[this.a.ordinal()])
/*     */       
/*     */       { default:
/*  65 */           return Blocks.OAK_PLANKS.getBlockData();
/*     */         case 2:
/*  67 */           break; }  return Blocks.DARK_OAK_PLANKS.getBlockData();
/*     */     }
/*     */ 
/*     */     
/*     */     protected IBlockData b() {
/*  72 */       switch (WorldGenMineshaftPieces.null.a[this.a.ordinal()])
/*     */       
/*     */       { default:
/*  75 */           return Blocks.OAK_FENCE.getBlockData();
/*     */         case 2:
/*  77 */           break; }  return Blocks.DARK_OAK_FENCE.getBlockData();
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a(IBlockAccess var0, StructureBoundingBox var1, int var2, int var3, int var4, int var5) {
/*  82 */       for (int var6 = var2; var6 <= var3; var6++) {
/*  83 */         if (a(var0, var6, var4 + 1, var5, var1).isAir()) {
/*  84 */           return false;
/*     */         }
/*     */       } 
/*  87 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   private static c a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, @Nullable EnumDirection var5, int var6, WorldGenMineshaft.Type var7) {
/*  92 */     int var8 = var1.nextInt(100);
/*  93 */     if (var8 >= 80) {
/*  94 */       StructureBoundingBox var9 = WorldGenMineshaftCross.a(var0, var1, var2, var3, var4, var5);
/*  95 */       if (var9 != null) {
/*  96 */         return new WorldGenMineshaftCross(var6, var9, var5, var7);
/*     */       }
/*  98 */     } else if (var8 >= 70) {
/*  99 */       StructureBoundingBox var9 = WorldGenMineshaftStairs.a(var0, var1, var2, var3, var4, var5);
/* 100 */       if (var9 != null) {
/* 101 */         return new WorldGenMineshaftStairs(var6, var9, var5, var7);
/*     */       }
/*     */     } else {
/* 104 */       StructureBoundingBox var9 = WorldGenMineshaftCorridor.a(var0, var1, var2, var3, var4, var5);
/* 105 */       if (var9 != null) {
/* 106 */         return new WorldGenMineshaftCorridor(var6, var1, var9, var5, var7);
/*     */       }
/*     */     } 
/*     */     
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   private static c b(StructurePiece var0, List<StructurePiece> var1, Random var2, int var3, int var4, int var5, EnumDirection var6, int var7) {
/* 114 */     if (var7 > 8) {
/* 115 */       return null;
/*     */     }
/* 117 */     if (Math.abs(var3 - (var0.g()).a) > 80 || Math.abs(var5 - (var0.g()).c) > 80) {
/* 118 */       return null;
/*     */     }
/*     */     
/* 121 */     WorldGenMineshaft.Type var8 = ((c)var0).a;
/* 122 */     c var9 = a(var1, var2, var3, var4, var5, var6, var7 + 1, var8);
/* 123 */     if (var9 != null) {
/* 124 */       var1.add(var9);
/* 125 */       var9.a(var0, var1, var2);
/*     */     } 
/* 127 */     return var9;
/*     */   }
/*     */   
/*     */   public static class WorldGenMineshaftRoom extends c {
/* 131 */     private final List<StructureBoundingBox> b = Lists.newLinkedList();
/*     */     
/*     */     public WorldGenMineshaftRoom(int var0, Random var1, int var2, int var3, WorldGenMineshaft.Type var4) {
/* 134 */       super(WorldGenFeatureStructurePieceType.c, var0, var4);
/* 135 */       this.a = var4;
/*     */       
/* 137 */       this.n = new StructureBoundingBox(var2, 50, var3, var2 + 7 + var1.nextInt(6), 54 + var1.nextInt(6), var3 + 7 + var1.nextInt(6));
/*     */     }
/*     */     
/*     */     public WorldGenMineshaftRoom(DefinedStructureManager var0, NBTTagCompound var1) {
/* 141 */       super(WorldGenFeatureStructurePieceType.c, var1);
/* 142 */       NBTTagList var2 = var1.getList("Entrances", 11);
/* 143 */       for (int var3 = 0; var3 < var2.size(); var3++) {
/* 144 */         this.b.add(new StructureBoundingBox(var2.f(var3)));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 150 */       int var3 = h();
/*     */ 
/*     */ 
/*     */       
/* 154 */       int var5 = this.n.e() - 3 - 1;
/* 155 */       if (var5 <= 0) {
/* 156 */         var5 = 1;
/*     */       }
/*     */ 
/*     */       
/* 160 */       int var4 = 0;
/* 161 */       while (var4 < this.n.d()) {
/* 162 */         var4 += var2.nextInt(this.n.d());
/* 163 */         if (var4 + 3 > this.n.d()) {
/*     */           break;
/*     */         }
/* 166 */         WorldGenMineshaftPieces.c var6 = WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a + var4, this.n.b + var2.nextInt(var5) + 1, this.n.c - 1, EnumDirection.NORTH, var3);
/* 167 */         if (var6 != null) {
/* 168 */           StructureBoundingBox var7 = var6.g();
/* 169 */           this.b.add(new StructureBoundingBox(var7.a, var7.b, this.n.c, var7.d, var7.e, this.n.c + 1));
/*     */         } 
/* 171 */         var4 += 4;
/*     */       } 
/*     */       
/* 174 */       var4 = 0;
/* 175 */       while (var4 < this.n.d()) {
/* 176 */         var4 += var2.nextInt(this.n.d());
/* 177 */         if (var4 + 3 > this.n.d()) {
/*     */           break;
/*     */         }
/* 180 */         WorldGenMineshaftPieces.c var6 = WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a + var4, this.n.b + var2.nextInt(var5) + 1, this.n.f + 1, EnumDirection.SOUTH, var3);
/* 181 */         if (var6 != null) {
/* 182 */           StructureBoundingBox var7 = var6.g();
/* 183 */           this.b.add(new StructureBoundingBox(var7.a, var7.b, this.n.f - 1, var7.d, var7.e, this.n.f));
/*     */         } 
/* 185 */         var4 += 4;
/*     */       } 
/*     */       
/* 188 */       var4 = 0;
/* 189 */       while (var4 < this.n.f()) {
/* 190 */         var4 += var2.nextInt(this.n.f());
/* 191 */         if (var4 + 3 > this.n.f()) {
/*     */           break;
/*     */         }
/* 194 */         WorldGenMineshaftPieces.c var6 = WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a - 1, this.n.b + var2.nextInt(var5) + 1, this.n.c + var4, EnumDirection.WEST, var3);
/* 195 */         if (var6 != null) {
/* 196 */           StructureBoundingBox var7 = var6.g();
/* 197 */           this.b.add(new StructureBoundingBox(this.n.a, var7.b, var7.c, this.n.a + 1, var7.e, var7.f));
/*     */         } 
/* 199 */         var4 += 4;
/*     */       } 
/*     */       
/* 202 */       var4 = 0;
/* 203 */       while (var4 < this.n.f()) {
/* 204 */         var4 += var2.nextInt(this.n.f());
/* 205 */         if (var4 + 3 > this.n.f()) {
/*     */           break;
/*     */         }
/* 208 */         StructurePiece var6 = WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d + 1, this.n.b + var2.nextInt(var5) + 1, this.n.c + var4, EnumDirection.EAST, var3);
/* 209 */         if (var6 != null) {
/* 210 */           StructureBoundingBox var7 = var6.g();
/* 211 */           this.b.add(new StructureBoundingBox(this.n.d - 1, var7.b, var7.c, this.n.d, var7.e, var7.f));
/*     */         } 
/* 213 */         var4 += 4;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 219 */       if (a(var0, var4)) {
/* 220 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 224 */       a(var0, var4, this.n.a, this.n.b, this.n.c, this.n.d, this.n.b, this.n.f, Blocks.DIRT.getBlockData(), m, true);
/*     */ 
/*     */       
/* 227 */       a(var0, var4, this.n.a, this.n.b + 1, this.n.c, this.n.d, Math.min(this.n.b + 3, this.n.e), this.n.f, m, m, false);
/* 228 */       for (StructureBoundingBox var8 : this.b) {
/* 229 */         a(var0, var4, var8.a, var8.e - 2, var8.c, var8.d, var8.e, var8.f, m, m, false);
/*     */       }
/* 231 */       a(var0, var4, this.n.a, this.n.b + 4, this.n.c, this.n.d, this.n.e, this.n.f, m, false);
/*     */       
/* 233 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(int var0, int var1, int var2) {
/* 238 */       super.a(var0, var1, var2);
/* 239 */       for (StructureBoundingBox var4 : this.b) {
/* 240 */         var4.a(var0, var1, var2);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(NBTTagCompound var0) {
/* 246 */       super.a(var0);
/* 247 */       NBTTagList var1 = new NBTTagList();
/* 248 */       for (StructureBoundingBox var3 : this.b) {
/* 249 */         var1.add(var3.h());
/*     */       }
/* 251 */       var0.set("Entrances", var1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WorldGenMineshaftCorridor extends c {
/*     */     private final boolean b;
/*     */     private final boolean c;
/*     */     private boolean d;
/*     */     private final int e;
/*     */     
/*     */     public WorldGenMineshaftCorridor(DefinedStructureManager var0, NBTTagCompound var1) {
/* 262 */       super(WorldGenFeatureStructurePieceType.a, var1);
/*     */       
/* 264 */       this.b = var1.getBoolean("hr");
/* 265 */       this.c = var1.getBoolean("sc");
/* 266 */       this.d = var1.getBoolean("hps");
/* 267 */       this.e = var1.getInt("Num");
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(NBTTagCompound var0) {
/* 272 */       super.a(var0);
/* 273 */       var0.setBoolean("hr", this.b);
/* 274 */       var0.setBoolean("sc", this.c);
/* 275 */       var0.setBoolean("hps", this.d);
/* 276 */       var0.setInt("Num", this.e);
/*     */     }
/*     */     
/*     */     public WorldGenMineshaftCorridor(int var0, Random var1, StructureBoundingBox var2, EnumDirection var3, WorldGenMineshaft.Type var4) {
/* 280 */       super(WorldGenFeatureStructurePieceType.a, var0, var4);
/* 281 */       a(var3);
/* 282 */       this.n = var2;
/* 283 */       this.b = (var1.nextInt(3) == 0);
/* 284 */       this.c = (!this.b && var1.nextInt(23) == 0);
/*     */       
/* 286 */       if (i().n() == EnumDirection.EnumAxis.Z) {
/* 287 */         this.e = var2.f() / 5;
/*     */       } else {
/* 289 */         this.e = var2.d() / 5;
/*     */       } 
/*     */     }
/*     */     
/*     */     public static StructureBoundingBox a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5) {
/* 294 */       StructureBoundingBox var6 = new StructureBoundingBox(var2, var3, var4, var2, var3 + 3 - 1, var4);
/*     */       
/* 296 */       int var7 = var1.nextInt(3) + 2;
/* 297 */       while (var7 > 0) {
/* 298 */         int var8 = var7 * 5;
/*     */         
/* 300 */         switch (WorldGenMineshaftPieces.null.b[var5.ordinal()]) {
/*     */           
/*     */           default:
/* 303 */             var6.d = var2 + 3 - 1;
/* 304 */             var6.c = var4 - var8 - 1;
/*     */             break;
/*     */           case 2:
/* 307 */             var6.d = var2 + 3 - 1;
/* 308 */             var6.f = var4 + var8 - 1;
/*     */             break;
/*     */           case 3:
/* 311 */             var6.a = var2 - var8 - 1;
/* 312 */             var6.f = var4 + 3 - 1;
/*     */             break;
/*     */           case 4:
/* 315 */             var6.d = var2 + var8 - 1;
/* 316 */             var6.f = var4 + 3 - 1;
/*     */             break;
/*     */         } 
/*     */         
/* 320 */         if (StructurePiece.a(var0, var6) != null) {
/* 321 */           var7--;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 327 */       if (var7 > 0) {
/* 328 */         return var6;
/*     */       }
/*     */       
/* 331 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 336 */       int var3 = h();
/* 337 */       int var4 = var2.nextInt(4);
/* 338 */       EnumDirection var5 = i();
/* 339 */       if (var5 != null) {
/* 340 */         switch (WorldGenMineshaftPieces.null.b[var5.ordinal()]) {
/*     */           
/*     */           default:
/* 343 */             if (var4 <= 1) {
/* 344 */               WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a, this.n.b - 1 + var2.nextInt(3), this.n.c - 1, var5, var3); break;
/* 345 */             }  if (var4 == 2) {
/* 346 */               WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a - 1, this.n.b - 1 + var2.nextInt(3), this.n.c, EnumDirection.WEST, var3); break;
/*     */             } 
/* 348 */             WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d + 1, this.n.b - 1 + var2.nextInt(3), this.n.c, EnumDirection.EAST, var3);
/*     */             break;
/*     */           
/*     */           case 2:
/* 352 */             if (var4 <= 1) {
/* 353 */               WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a, this.n.b - 1 + var2.nextInt(3), this.n.f + 1, var5, var3); break;
/* 354 */             }  if (var4 == 2) {
/* 355 */               WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a - 1, this.n.b - 1 + var2.nextInt(3), this.n.f - 3, EnumDirection.WEST, var3); break;
/*     */             } 
/* 357 */             WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d + 1, this.n.b - 1 + var2.nextInt(3), this.n.f - 3, EnumDirection.EAST, var3);
/*     */             break;
/*     */           
/*     */           case 3:
/* 361 */             if (var4 <= 1) {
/* 362 */               WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a - 1, this.n.b - 1 + var2.nextInt(3), this.n.c, var5, var3); break;
/* 363 */             }  if (var4 == 2) {
/* 364 */               WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a, this.n.b - 1 + var2.nextInt(3), this.n.c - 1, EnumDirection.NORTH, var3); break;
/*     */             } 
/* 366 */             WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a, this.n.b - 1 + var2.nextInt(3), this.n.f + 1, EnumDirection.SOUTH, var3);
/*     */             break;
/*     */           
/*     */           case 4:
/* 370 */             if (var4 <= 1) {
/* 371 */               WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d + 1, this.n.b - 1 + var2.nextInt(3), this.n.c, var5, var3); break;
/* 372 */             }  if (var4 == 2) {
/* 373 */               WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d - 3, this.n.b - 1 + var2.nextInt(3), this.n.c - 1, EnumDirection.NORTH, var3); break;
/*     */             } 
/* 375 */             WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d - 3, this.n.b - 1 + var2.nextInt(3), this.n.f + 1, EnumDirection.SOUTH, var3);
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 382 */       if (var3 < 8) {
/* 383 */         if (var5 == EnumDirection.NORTH || var5 == EnumDirection.SOUTH) {
/* 384 */           for (int var6 = this.n.c + 3; var6 + 3 <= this.n.f; var6 += 5) {
/* 385 */             int var7 = var2.nextInt(5);
/* 386 */             if (var7 == 0) {
/* 387 */               WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a - 1, this.n.b, var6, EnumDirection.WEST, var3 + 1);
/* 388 */             } else if (var7 == 1) {
/* 389 */               WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d + 1, this.n.b, var6, EnumDirection.EAST, var3 + 1);
/*     */             } 
/*     */           } 
/*     */         } else {
/* 393 */           for (int var6 = this.n.a + 3; var6 + 3 <= this.n.d; var6 += 5) {
/* 394 */             int var7 = var2.nextInt(5);
/* 395 */             if (var7 == 0) {
/* 396 */               WorldGenMineshaftPieces.a(var0, var1, var2, var6, this.n.b, this.n.c - 1, EnumDirection.NORTH, var3 + 1);
/* 397 */             } else if (var7 == 1) {
/* 398 */               WorldGenMineshaftPieces.a(var0, var1, var2, var6, this.n.b, this.n.f + 1, EnumDirection.SOUTH, var3 + 1);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a(GeneratorAccessSeed var0, StructureBoundingBox var1, Random var2, int var3, int var4, int var5, MinecraftKey var6) {
/* 407 */       BlockPosition var7 = new BlockPosition(a(var3, var5), d(var4), b(var3, var5));
/* 408 */       if (var1.b(var7) && 
/* 409 */         var0.getType(var7).isAir() && !var0.getType(var7.down()).isAir()) {
/* 410 */         IBlockData var8 = Blocks.RAIL.getBlockData().set(BlockMinecartTrack.SHAPE, var2.nextBoolean() ? BlockPropertyTrackPosition.NORTH_SOUTH : BlockPropertyTrackPosition.EAST_WEST);
/* 411 */         a(var0, var8, var3, var4, var5, var1);
/* 412 */         EntityMinecartChest var9 = new EntityMinecartChest(var0.getMinecraftWorld(), var7.getX() + 0.5D, var7.getY() + 0.5D, var7.getZ() + 0.5D);
/* 413 */         var9.setLootTable(var6, var2.nextLong());
/* 414 */         var0.addEntity(var9);
/* 415 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 419 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 424 */       if (a(var0, var4)) {
/* 425 */         return false;
/*     */       }
/*     */       
/* 428 */       int var7 = 0;
/* 429 */       int var8 = 2;
/* 430 */       int var9 = 0;
/* 431 */       int var10 = 2;
/* 432 */       int var11 = this.e * 5 - 1;
/*     */       
/* 434 */       IBlockData var12 = a();
/*     */ 
/*     */       
/* 437 */       a(var0, var4, 0, 0, 0, 2, 1, var11, m, m, false);
/* 438 */       a(var0, var4, var3, 0.8F, 0, 2, 0, 2, 2, var11, m, m, false, false);
/*     */       
/* 440 */       if (this.c) {
/* 441 */         a(var0, var4, var3, 0.6F, 0, 0, 0, 2, 1, var11, Blocks.COBWEB.getBlockData(), m, false, true);
/*     */       }
/*     */       
/*     */       int var13;
/* 445 */       for (var13 = 0; var13 < this.e; var13++) {
/* 446 */         int var14 = 2 + var13 * 5;
/*     */         
/* 448 */         a(var0, var4, 0, 0, var14, 2, 2, var3);
/*     */         
/* 450 */         a(var0, var4, var3, 0.1F, 0, 2, var14 - 1);
/* 451 */         a(var0, var4, var3, 0.1F, 2, 2, var14 - 1);
/* 452 */         a(var0, var4, var3, 0.1F, 0, 2, var14 + 1);
/* 453 */         a(var0, var4, var3, 0.1F, 2, 2, var14 + 1);
/* 454 */         a(var0, var4, var3, 0.05F, 0, 2, var14 - 2);
/* 455 */         a(var0, var4, var3, 0.05F, 2, 2, var14 - 2);
/* 456 */         a(var0, var4, var3, 0.05F, 0, 2, var14 + 2);
/* 457 */         a(var0, var4, var3, 0.05F, 2, 2, var14 + 2);
/*     */         
/* 459 */         if (var3.nextInt(100) == 0) {
/* 460 */           a(var0, var4, var3, 2, 0, var14 - 1, LootTables.u);
/*     */         }
/* 462 */         if (var3.nextInt(100) == 0) {
/* 463 */           a(var0, var4, var3, 0, 0, var14 + 1, LootTables.u);
/*     */         }
/* 465 */         if (this.c && !this.d) {
/* 466 */           int var15 = d(0);
/* 467 */           int var16 = var14 - 1 + var3.nextInt(3);
/* 468 */           int var17 = a(1, var16);
/* 469 */           int var18 = b(1, var16);
/* 470 */           BlockPosition var19 = new BlockPosition(var17, var15, var18);
/*     */           
/* 472 */           if (var4.b(var19) && a(var0, 1, 0, var16, var4)) {
/* 473 */             this.d = true;
/* 474 */             var0.setTypeAndData(var19, Blocks.SPAWNER.getBlockData(), 2);
/*     */             
/* 476 */             TileEntity var20 = var0.getTileEntity(var19);
/* 477 */             if (var20 instanceof TileEntityMobSpawner) {
/* 478 */               ((TileEntityMobSpawner)var20).getSpawner().setMobName(EntityTypes.CAVE_SPIDER);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 485 */       for (var13 = 0; var13 <= 2; var13++) {
/* 486 */         for (int var14 = 0; var14 <= var11; var14++) {
/* 487 */           int var15 = -1;
/* 488 */           IBlockData var16 = a(var0, var13, -1, var14, var4);
/* 489 */           if (var16.isAir() && a(var0, var13, -1, var14, var4)) {
/* 490 */             int var17 = -1;
/* 491 */             a(var0, var12, var13, -1, var14, var4);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 496 */       if (this.b) {
/* 497 */         IBlockData iBlockData = Blocks.RAIL.getBlockData().set(BlockMinecartTrack.SHAPE, BlockPropertyTrackPosition.NORTH_SOUTH);
/* 498 */         for (int var14 = 0; var14 <= var11; var14++) {
/* 499 */           IBlockData var15 = a(var0, 1, -1, var14, var4);
/* 500 */           if (!var15.isAir() && var15.i(var0, new BlockPosition(a(1, var14), d(-1), b(1, var14)))) {
/* 501 */             float var16 = a(var0, 1, 0, var14, var4) ? 0.7F : 0.9F;
/* 502 */             a(var0, var4, var3, var16, 1, 0, var14, iBlockData);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 507 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     private void a(GeneratorAccessSeed var0, StructureBoundingBox var1, int var2, int var3, int var4, int var5, int var6, Random var7) {
/* 512 */       if (!a(var0, var1, var2, var6, var5, var4)) {
/*     */         return;
/*     */       }
/*     */       
/* 516 */       IBlockData var8 = a();
/* 517 */       IBlockData var9 = b();
/*     */       
/* 519 */       a(var0, var1, var2, var3, var4, var2, var5 - 1, var4, var9.set(BlockFence.WEST, Boolean.valueOf(true)), m, false);
/* 520 */       a(var0, var1, var6, var3, var4, var6, var5 - 1, var4, var9.set(BlockFence.EAST, Boolean.valueOf(true)), m, false);
/* 521 */       if (var7.nextInt(4) == 0) {
/* 522 */         a(var0, var1, var2, var5, var4, var2, var5, var4, var8, m, false);
/* 523 */         a(var0, var1, var6, var5, var4, var6, var5, var4, var8, m, false);
/*     */       } else {
/* 525 */         a(var0, var1, var2, var5, var4, var6, var5, var4, var8, m, false);
/* 526 */         a(var0, var1, var7, 0.05F, var2 + 1, var5, var4 - 1, Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.NORTH));
/* 527 */         a(var0, var1, var7, 0.05F, var2 + 1, var5, var4 + 1, Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, EnumDirection.SOUTH));
/*     */       } 
/*     */     }
/*     */     
/*     */     private void a(GeneratorAccessSeed var0, StructureBoundingBox var1, Random var2, float var3, int var4, int var5, int var6) {
/* 532 */       if (a(var0, var4, var5, var6, var1))
/* 533 */         a(var0, var1, var2, var3, var4, var5, var6, Blocks.COBWEB.getBlockData()); 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WorldGenMineshaftCross
/*     */     extends c {
/*     */     private final EnumDirection b;
/*     */     private final boolean c;
/*     */     
/*     */     public WorldGenMineshaftCross(DefinedStructureManager var0, NBTTagCompound var1) {
/* 543 */       super(WorldGenFeatureStructurePieceType.b, var1);
/* 544 */       this.c = var1.getBoolean("tf");
/* 545 */       this.b = EnumDirection.fromType2(var1.getInt("D"));
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(NBTTagCompound var0) {
/* 550 */       super.a(var0);
/* 551 */       var0.setBoolean("tf", this.c);
/* 552 */       var0.setInt("D", this.b.get2DRotationValue());
/*     */     }
/*     */     
/*     */     public WorldGenMineshaftCross(int var0, StructureBoundingBox var1, @Nullable EnumDirection var2, WorldGenMineshaft.Type var3) {
/* 556 */       super(WorldGenFeatureStructurePieceType.b, var0, var3);
/*     */       
/* 558 */       this.b = var2;
/* 559 */       this.n = var1;
/* 560 */       this.c = (var1.e() > 3);
/*     */     }
/*     */     
/*     */     public static StructureBoundingBox a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5) {
/* 564 */       StructureBoundingBox var6 = new StructureBoundingBox(var2, var3, var4, var2, var3 + 3 - 1, var4);
/*     */       
/* 566 */       if (var1.nextInt(4) == 0) {
/* 567 */         var6.e += 4;
/*     */       }
/*     */       
/* 570 */       switch (WorldGenMineshaftPieces.null.b[var5.ordinal()]) {
/*     */         
/*     */         default:
/* 573 */           var6.a = var2 - 1;
/* 574 */           var6.d = var2 + 3;
/* 575 */           var6.c = var4 - 4;
/*     */           break;
/*     */         case 2:
/* 578 */           var6.a = var2 - 1;
/* 579 */           var6.d = var2 + 3;
/* 580 */           var6.f = var4 + 3 + 1;
/*     */           break;
/*     */         case 3:
/* 583 */           var6.a = var2 - 4;
/* 584 */           var6.c = var4 - 1;
/* 585 */           var6.f = var4 + 3;
/*     */           break;
/*     */         case 4:
/* 588 */           var6.d = var2 + 3 + 1;
/* 589 */           var6.c = var4 - 1;
/* 590 */           var6.f = var4 + 3;
/*     */           break;
/*     */       } 
/*     */       
/* 594 */       if (StructurePiece.a(var0, var6) != null) {
/* 595 */         return null;
/*     */       }
/*     */       
/* 598 */       return var6;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 603 */       int var3 = h();
/*     */ 
/*     */       
/* 606 */       switch (WorldGenMineshaftPieces.null.b[this.b.ordinal()]) {
/*     */         
/*     */         default:
/* 609 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a + 1, this.n.b, this.n.c - 1, EnumDirection.NORTH, var3);
/* 610 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a - 1, this.n.b, this.n.c + 1, EnumDirection.WEST, var3);
/* 611 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d + 1, this.n.b, this.n.c + 1, EnumDirection.EAST, var3);
/*     */           break;
/*     */         case 2:
/* 614 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a + 1, this.n.b, this.n.f + 1, EnumDirection.SOUTH, var3);
/* 615 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a - 1, this.n.b, this.n.c + 1, EnumDirection.WEST, var3);
/* 616 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d + 1, this.n.b, this.n.c + 1, EnumDirection.EAST, var3);
/*     */           break;
/*     */         case 3:
/* 619 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a + 1, this.n.b, this.n.c - 1, EnumDirection.NORTH, var3);
/* 620 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a + 1, this.n.b, this.n.f + 1, EnumDirection.SOUTH, var3);
/* 621 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a - 1, this.n.b, this.n.c + 1, EnumDirection.WEST, var3);
/*     */           break;
/*     */         case 4:
/* 624 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a + 1, this.n.b, this.n.c - 1, EnumDirection.NORTH, var3);
/* 625 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a + 1, this.n.b, this.n.f + 1, EnumDirection.SOUTH, var3);
/* 626 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d + 1, this.n.b, this.n.c + 1, EnumDirection.EAST, var3);
/*     */           break;
/*     */       } 
/*     */       
/* 630 */       if (this.c) {
/* 631 */         if (var2.nextBoolean()) {
/* 632 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a + 1, this.n.b + 3 + 1, this.n.c - 1, EnumDirection.NORTH, var3);
/*     */         }
/* 634 */         if (var2.nextBoolean()) {
/* 635 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a - 1, this.n.b + 3 + 1, this.n.c + 1, EnumDirection.WEST, var3);
/*     */         }
/* 637 */         if (var2.nextBoolean()) {
/* 638 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d + 1, this.n.b + 3 + 1, this.n.c + 1, EnumDirection.EAST, var3);
/*     */         }
/* 640 */         if (var2.nextBoolean()) {
/* 641 */           WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a + 1, this.n.b + 3 + 1, this.n.f + 1, EnumDirection.SOUTH, var3);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 648 */       if (a(var0, var4)) {
/* 649 */         return false;
/*     */       }
/*     */       
/* 652 */       IBlockData var7 = a();
/*     */ 
/*     */       
/* 655 */       if (this.c) {
/* 656 */         a(var0, var4, this.n.a + 1, this.n.b, this.n.c, this.n.d - 1, this.n.b + 3 - 1, this.n.f, m, m, false);
/* 657 */         a(var0, var4, this.n.a, this.n.b, this.n.c + 1, this.n.d, this.n.b + 3 - 1, this.n.f - 1, m, m, false);
/* 658 */         a(var0, var4, this.n.a + 1, this.n.e - 2, this.n.c, this.n.d - 1, this.n.e, this.n.f, m, m, false);
/* 659 */         a(var0, var4, this.n.a, this.n.e - 2, this.n.c + 1, this.n.d, this.n.e, this.n.f - 1, m, m, false);
/* 660 */         a(var0, var4, this.n.a + 1, this.n.b + 3, this.n.c + 1, this.n.d - 1, this.n.b + 3, this.n.f - 1, m, m, false);
/*     */       } else {
/* 662 */         a(var0, var4, this.n.a + 1, this.n.b, this.n.c, this.n.d - 1, this.n.e, this.n.f, m, m, false);
/* 663 */         a(var0, var4, this.n.a, this.n.b, this.n.c + 1, this.n.d, this.n.e, this.n.f - 1, m, m, false);
/*     */       } 
/*     */ 
/*     */       
/* 667 */       a(var0, var4, this.n.a + 1, this.n.b, this.n.c + 1, this.n.e);
/* 668 */       a(var0, var4, this.n.a + 1, this.n.b, this.n.f - 1, this.n.e);
/* 669 */       a(var0, var4, this.n.d - 1, this.n.b, this.n.c + 1, this.n.e);
/* 670 */       a(var0, var4, this.n.d - 1, this.n.b, this.n.f - 1, this.n.e);
/*     */ 
/*     */ 
/*     */       
/* 674 */       for (int var8 = this.n.a; var8 <= this.n.d; var8++) {
/* 675 */         for (int var9 = this.n.c; var9 <= this.n.f; var9++) {
/* 676 */           if (a(var0, var8, this.n.b - 1, var9, var4).isAir() && a(var0, var8, this.n.b - 1, var9, var4)) {
/* 677 */             a(var0, var7, var8, this.n.b - 1, var9, var4);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 682 */       return true;
/*     */     }
/*     */     
/*     */     private void a(GeneratorAccessSeed var0, StructureBoundingBox var1, int var2, int var3, int var4, int var5) {
/* 686 */       if (!a(var0, var2, var5 + 1, var4, var1).isAir())
/* 687 */         a(var0, var1, var2, var3, var4, var2, var5, var4, a(), m, false); 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WorldGenMineshaftStairs
/*     */     extends c {
/*     */     public WorldGenMineshaftStairs(int var0, StructureBoundingBox var1, EnumDirection var2, WorldGenMineshaft.Type var3) {
/* 694 */       super(WorldGenFeatureStructurePieceType.d, var0, var3);
/* 695 */       a(var2);
/* 696 */       this.n = var1;
/*     */     }
/*     */     
/*     */     public WorldGenMineshaftStairs(DefinedStructureManager var0, NBTTagCompound var1) {
/* 700 */       super(WorldGenFeatureStructurePieceType.d, var1);
/*     */     }
/*     */ 
/*     */     
/*     */     public static StructureBoundingBox a(List<StructurePiece> var0, Random var1, int var2, int var3, int var4, EnumDirection var5) {
/* 705 */       StructureBoundingBox var6 = new StructureBoundingBox(var2, var3 - 5, var4, var2, var3 + 3 - 1, var4);
/*     */       
/* 707 */       switch (WorldGenMineshaftPieces.null.b[var5.ordinal()]) {
/*     */         
/*     */         default:
/* 710 */           var6.d = var2 + 3 - 1;
/* 711 */           var6.c = var4 - 8;
/*     */           break;
/*     */         case 2:
/* 714 */           var6.d = var2 + 3 - 1;
/* 715 */           var6.f = var4 + 8;
/*     */           break;
/*     */         case 3:
/* 718 */           var6.a = var2 - 8;
/* 719 */           var6.f = var4 + 3 - 1;
/*     */           break;
/*     */         case 4:
/* 722 */           var6.d = var2 + 8;
/* 723 */           var6.f = var4 + 3 - 1;
/*     */           break;
/*     */       } 
/*     */       
/* 727 */       if (StructurePiece.a(var0, var6) != null) {
/* 728 */         return null;
/*     */       }
/*     */       
/* 731 */       return var6;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(StructurePiece var0, List<StructurePiece> var1, Random var2) {
/* 736 */       int var3 = h();
/*     */ 
/*     */       
/* 739 */       EnumDirection var4 = i();
/* 740 */       if (var4 != null) {
/* 741 */         switch (WorldGenMineshaftPieces.null.b[var4.ordinal()]) {
/*     */           
/*     */           default:
/* 744 */             WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a, this.n.b, this.n.c - 1, EnumDirection.NORTH, var3);
/*     */             return;
/*     */           case 2:
/* 747 */             WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a, this.n.b, this.n.f + 1, EnumDirection.SOUTH, var3);
/*     */             return;
/*     */           case 3:
/* 750 */             WorldGenMineshaftPieces.a(var0, var1, var2, this.n.a - 1, this.n.b, this.n.c, EnumDirection.WEST, var3); return;
/*     */           case 4:
/*     */             break;
/* 753 */         }  WorldGenMineshaftPieces.a(var0, var1, var2, this.n.d + 1, this.n.b, this.n.c, EnumDirection.EAST, var3);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 761 */       if (a(var0, var4)) {
/* 762 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 766 */       a(var0, var4, 0, 5, 0, 2, 7, 1, m, m, false);
/*     */       
/* 768 */       a(var0, var4, 0, 0, 7, 2, 2, 8, m, m, false);
/*     */       
/* 770 */       for (int var7 = 0; var7 < 5; var7++) {
/* 771 */         a(var0, var4, 0, 5 - var7 - ((var7 < 4) ? 1 : 0), 2 + var7, 2, 7 - var7, 2 + var7, m, m, false);
/*     */       }
/*     */       
/* 774 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenMineshaftPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */