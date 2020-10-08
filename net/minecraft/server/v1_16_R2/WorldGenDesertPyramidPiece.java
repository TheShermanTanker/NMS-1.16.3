/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
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
/*     */ public class WorldGenDesertPyramidPiece
/*     */   extends WorldGenScatteredPiece
/*     */ {
/*  20 */   private final boolean[] e = new boolean[4];
/*     */   
/*     */   public WorldGenDesertPyramidPiece(Random var0, int var1, int var2) {
/*  23 */     super(WorldGenFeatureStructurePieceType.L, var0, var1, 64, var2, 21, 15, 21);
/*     */   }
/*     */   
/*     */   public WorldGenDesertPyramidPiece(DefinedStructureManager var0, NBTTagCompound var1) {
/*  27 */     super(WorldGenFeatureStructurePieceType.L, var1);
/*  28 */     this.e[0] = var1.getBoolean("hasPlacedChest0");
/*  29 */     this.e[1] = var1.getBoolean("hasPlacedChest1");
/*  30 */     this.e[2] = var1.getBoolean("hasPlacedChest2");
/*  31 */     this.e[3] = var1.getBoolean("hasPlacedChest3");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound var0) {
/*  36 */     super.a(var0);
/*  37 */     var0.setBoolean("hasPlacedChest0", this.e[0]);
/*  38 */     var0.setBoolean("hasPlacedChest1", this.e[1]);
/*  39 */     var0.setBoolean("hasPlacedChest2", this.e[2]);
/*  40 */     var0.setBoolean("hasPlacedChest3", this.e[3]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  46 */     a(var0, var4, 0, -4, 0, this.a - 1, 0, this.c - 1, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false); int i;
/*  47 */     for (i = 1; i <= 9; i++) {
/*  48 */       a(var0, var4, i, i, i, this.a - 1 - i, i, this.c - 1 - i, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/*  49 */       a(var0, var4, i + 1, i, i + 1, this.a - 2 - i, i, this.c - 2 - i, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*     */     } 
/*  51 */     for (i = 0; i < this.a; i++) {
/*  52 */       for (int j = 0; j < this.c; j++) {
/*  53 */         int k = -5;
/*  54 */         b(var0, Blocks.SANDSTONE.getBlockData(), i, -5, j, var4);
/*     */       } 
/*     */     } 
/*     */     
/*  58 */     IBlockData var7 = Blocks.SANDSTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.NORTH);
/*  59 */     IBlockData var8 = Blocks.SANDSTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.SOUTH);
/*  60 */     IBlockData var9 = Blocks.SANDSTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.EAST);
/*  61 */     IBlockData var10 = Blocks.SANDSTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.WEST);
/*     */ 
/*     */     
/*  64 */     a(var0, var4, 0, 0, 0, 4, 9, 4, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
/*  65 */     a(var0, var4, 1, 10, 1, 3, 10, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/*  66 */     a(var0, var7, 2, 10, 0, var4);
/*  67 */     a(var0, var8, 2, 10, 4, var4);
/*  68 */     a(var0, var9, 0, 10, 2, var4);
/*  69 */     a(var0, var10, 4, 10, 2, var4);
/*  70 */     a(var0, var4, this.a - 5, 0, 0, this.a - 1, 9, 4, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
/*  71 */     a(var0, var4, this.a - 4, 10, 1, this.a - 2, 10, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/*  72 */     a(var0, var7, this.a - 3, 10, 0, var4);
/*  73 */     a(var0, var8, this.a - 3, 10, 4, var4);
/*  74 */     a(var0, var9, this.a - 5, 10, 2, var4);
/*  75 */     a(var0, var10, this.a - 1, 10, 2, var4);
/*     */ 
/*     */     
/*  78 */     a(var0, var4, 8, 0, 0, 12, 4, 4, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
/*  79 */     a(var0, var4, 9, 1, 0, 11, 3, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*  80 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 9, 1, 1, var4);
/*  81 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 9, 2, 1, var4);
/*  82 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 9, 3, 1, var4);
/*  83 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 10, 3, 1, var4);
/*  84 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 11, 3, 1, var4);
/*  85 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 11, 2, 1, var4);
/*  86 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 11, 1, 1, var4);
/*     */ 
/*     */     
/*  89 */     a(var0, var4, 4, 1, 1, 8, 3, 3, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
/*  90 */     a(var0, var4, 4, 1, 2, 8, 2, 2, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*  91 */     a(var0, var4, 12, 1, 1, 16, 3, 3, Blocks.SANDSTONE.getBlockData(), Blocks.AIR.getBlockData(), false);
/*  92 */     a(var0, var4, 12, 1, 2, 16, 2, 2, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*     */ 
/*     */     
/*  95 */     a(var0, var4, 5, 4, 5, this.a - 6, 4, this.c - 6, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/*  96 */     a(var0, var4, 9, 4, 9, 11, 4, 11, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/*  97 */     a(var0, var4, 8, 1, 8, 8, 3, 8, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
/*  98 */     a(var0, var4, 12, 1, 8, 12, 3, 8, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
/*  99 */     a(var0, var4, 8, 1, 12, 8, 3, 12, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
/* 100 */     a(var0, var4, 12, 1, 12, 12, 3, 12, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
/*     */ 
/*     */     
/* 103 */     a(var0, var4, 1, 1, 5, 4, 4, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/* 104 */     a(var0, var4, this.a - 5, 1, 5, this.a - 2, 4, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/* 105 */     a(var0, var4, 6, 7, 9, 6, 7, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/* 106 */     a(var0, var4, this.a - 7, 7, 9, this.a - 7, 7, 11, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/* 107 */     a(var0, var4, 5, 5, 9, 5, 7, 11, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
/* 108 */     a(var0, var4, this.a - 6, 5, 9, this.a - 6, 7, 11, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
/* 109 */     a(var0, Blocks.AIR.getBlockData(), 5, 5, 10, var4);
/* 110 */     a(var0, Blocks.AIR.getBlockData(), 5, 6, 10, var4);
/* 111 */     a(var0, Blocks.AIR.getBlockData(), 6, 6, 10, var4);
/* 112 */     a(var0, Blocks.AIR.getBlockData(), this.a - 6, 5, 10, var4);
/* 113 */     a(var0, Blocks.AIR.getBlockData(), this.a - 6, 6, 10, var4);
/* 114 */     a(var0, Blocks.AIR.getBlockData(), this.a - 7, 6, 10, var4);
/*     */ 
/*     */     
/* 117 */     a(var0, var4, 2, 4, 4, 2, 6, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/* 118 */     a(var0, var4, this.a - 3, 4, 4, this.a - 3, 6, 4, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/* 119 */     a(var0, var7, 2, 4, 5, var4);
/* 120 */     a(var0, var7, 2, 3, 4, var4);
/* 121 */     a(var0, var7, this.a - 3, 4, 5, var4);
/* 122 */     a(var0, var7, this.a - 3, 3, 4, var4);
/* 123 */     a(var0, var4, 1, 1, 3, 2, 2, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/* 124 */     a(var0, var4, this.a - 3, 1, 3, this.a - 2, 2, 3, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/* 125 */     a(var0, Blocks.SANDSTONE.getBlockData(), 1, 1, 2, var4);
/* 126 */     a(var0, Blocks.SANDSTONE.getBlockData(), this.a - 2, 1, 2, var4);
/* 127 */     a(var0, Blocks.SANDSTONE_SLAB.getBlockData(), 1, 2, 2, var4);
/* 128 */     a(var0, Blocks.SANDSTONE_SLAB.getBlockData(), this.a - 2, 2, 2, var4);
/* 129 */     a(var0, var10, 2, 1, 2, var4);
/* 130 */     a(var0, var9, this.a - 3, 1, 2, var4);
/*     */ 
/*     */     
/* 133 */     a(var0, var4, 4, 3, 5, 4, 3, 17, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/* 134 */     a(var0, var4, this.a - 5, 3, 5, this.a - 5, 3, 17, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/* 135 */     a(var0, var4, 3, 1, 5, 4, 2, 16, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/* 136 */     a(var0, var4, this.a - 6, 1, 5, this.a - 5, 2, 16, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false); int var11;
/* 137 */     for (var11 = 5; var11 <= 17; var11 += 2) {
/* 138 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 4, 1, var11, var4);
/* 139 */       a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), 4, 2, var11, var4);
/* 140 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), this.a - 5, 1, var11, var4);
/* 141 */       a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), this.a - 5, 2, var11, var4);
/*     */     } 
/* 143 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 10, 0, 7, var4);
/* 144 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 10, 0, 8, var4);
/* 145 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 9, 0, 9, var4);
/* 146 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 11, 0, 9, var4);
/* 147 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 8, 0, 10, var4);
/* 148 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 12, 0, 10, var4);
/* 149 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 7, 0, 10, var4);
/* 150 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 13, 0, 10, var4);
/* 151 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 9, 0, 11, var4);
/* 152 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 11, 0, 11, var4);
/* 153 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 10, 0, 12, var4);
/* 154 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 10, 0, 13, var4);
/* 155 */     a(var0, Blocks.BLUE_TERRACOTTA.getBlockData(), 10, 0, 10, var4);
/*     */ 
/*     */     
/* 158 */     for (var11 = 0; var11 <= this.a - 1; var11 += this.a - 1) {
/* 159 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11, 2, 1, var4);
/* 160 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 2, 2, var4);
/* 161 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11, 2, 3, var4);
/* 162 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11, 3, 1, var4);
/* 163 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 3, 2, var4);
/* 164 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11, 3, 3, var4);
/* 165 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 4, 1, var4);
/* 166 */       a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), var11, 4, 2, var4);
/* 167 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 4, 3, var4);
/* 168 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11, 5, 1, var4);
/* 169 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 5, 2, var4);
/* 170 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11, 5, 3, var4);
/* 171 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 6, 1, var4);
/* 172 */       a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), var11, 6, 2, var4);
/* 173 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 6, 3, var4);
/* 174 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 7, 1, var4);
/* 175 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 7, 2, var4);
/* 176 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 7, 3, var4);
/* 177 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11, 8, 1, var4);
/* 178 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11, 8, 2, var4);
/* 179 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11, 8, 3, var4);
/*     */     } 
/* 181 */     for (var11 = 2; var11 <= this.a - 3; var11 += this.a - 3 - 2) {
/* 182 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11 - 1, 2, 0, var4);
/* 183 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 2, 0, var4);
/* 184 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11 + 1, 2, 0, var4);
/* 185 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11 - 1, 3, 0, var4);
/* 186 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 3, 0, var4);
/* 187 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11 + 1, 3, 0, var4);
/* 188 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11 - 1, 4, 0, var4);
/* 189 */       a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), var11, 4, 0, var4);
/* 190 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11 + 1, 4, 0, var4);
/* 191 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11 - 1, 5, 0, var4);
/* 192 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 5, 0, var4);
/* 193 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11 + 1, 5, 0, var4);
/* 194 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11 - 1, 6, 0, var4);
/* 195 */       a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), var11, 6, 0, var4);
/* 196 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11 + 1, 6, 0, var4);
/* 197 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11 - 1, 7, 0, var4);
/* 198 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11, 7, 0, var4);
/* 199 */       a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), var11 + 1, 7, 0, var4);
/* 200 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11 - 1, 8, 0, var4);
/* 201 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11, 8, 0, var4);
/* 202 */       a(var0, Blocks.CUT_SANDSTONE.getBlockData(), var11 + 1, 8, 0, var4);
/*     */     } 
/* 204 */     a(var0, var4, 8, 4, 0, 12, 6, 0, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
/* 205 */     a(var0, Blocks.AIR.getBlockData(), 8, 6, 0, var4);
/* 206 */     a(var0, Blocks.AIR.getBlockData(), 12, 6, 0, var4);
/* 207 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 9, 5, 0, var4);
/* 208 */     a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), 10, 5, 0, var4);
/* 209 */     a(var0, Blocks.ORANGE_TERRACOTTA.getBlockData(), 11, 5, 0, var4);
/*     */ 
/*     */     
/* 212 */     a(var0, var4, 8, -14, 8, 12, -11, 12, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
/* 213 */     a(var0, var4, 8, -10, 8, 12, -10, 12, Blocks.CHISELED_SANDSTONE.getBlockData(), Blocks.CHISELED_SANDSTONE.getBlockData(), false);
/* 214 */     a(var0, var4, 8, -9, 8, 12, -9, 12, Blocks.CUT_SANDSTONE.getBlockData(), Blocks.CUT_SANDSTONE.getBlockData(), false);
/* 215 */     a(var0, var4, 8, -8, 8, 12, -1, 12, Blocks.SANDSTONE.getBlockData(), Blocks.SANDSTONE.getBlockData(), false);
/* 216 */     a(var0, var4, 9, -11, 9, 11, -1, 11, Blocks.AIR.getBlockData(), Blocks.AIR.getBlockData(), false);
/* 217 */     a(var0, Blocks.STONE_PRESSURE_PLATE.getBlockData(), 10, -11, 10, var4);
/* 218 */     a(var0, var4, 9, -13, 9, 11, -13, 11, Blocks.TNT.getBlockData(), Blocks.AIR.getBlockData(), false);
/* 219 */     a(var0, Blocks.AIR.getBlockData(), 8, -11, 10, var4);
/* 220 */     a(var0, Blocks.AIR.getBlockData(), 8, -10, 10, var4);
/* 221 */     a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), 7, -10, 10, var4);
/* 222 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 7, -11, 10, var4);
/* 223 */     a(var0, Blocks.AIR.getBlockData(), 12, -11, 10, var4);
/* 224 */     a(var0, Blocks.AIR.getBlockData(), 12, -10, 10, var4);
/* 225 */     a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), 13, -10, 10, var4);
/* 226 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 13, -11, 10, var4);
/* 227 */     a(var0, Blocks.AIR.getBlockData(), 10, -11, 8, var4);
/* 228 */     a(var0, Blocks.AIR.getBlockData(), 10, -10, 8, var4);
/* 229 */     a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), 10, -10, 7, var4);
/* 230 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 10, -11, 7, var4);
/* 231 */     a(var0, Blocks.AIR.getBlockData(), 10, -11, 12, var4);
/* 232 */     a(var0, Blocks.AIR.getBlockData(), 10, -10, 12, var4);
/* 233 */     a(var0, Blocks.CHISELED_SANDSTONE.getBlockData(), 10, -10, 13, var4);
/* 234 */     a(var0, Blocks.CUT_SANDSTONE.getBlockData(), 10, -11, 13, var4);
/*     */ 
/*     */     
/* 237 */     for (EnumDirection var12 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 238 */       if (!this.e[var12.get2DRotationValue()]) {
/* 239 */         int var13 = var12.getAdjacentX() * 2;
/* 240 */         int var14 = var12.getAdjacentZ() * 2;
/* 241 */         this.e[var12.get2DRotationValue()] = a(var0, var4, var3, 10 + var13, -11, 10 + var14, LootTables.z);
/*     */       } 
/*     */     } 
/*     */     
/* 245 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDesertPyramidPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */