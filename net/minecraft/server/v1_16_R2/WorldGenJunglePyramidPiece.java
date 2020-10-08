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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenJunglePyramidPiece
/*     */   extends WorldGenScatteredPiece
/*     */ {
/*     */   private boolean e;
/*     */   private boolean f;
/*     */   private boolean g;
/*     */   private boolean h;
/*     */   
/*     */   public WorldGenJunglePyramidPiece(Random var0, int var1, int var2) {
/*  35 */     super(WorldGenFeatureStructurePieceType.G, var0, var1, 64, var2, 12, 10, 15);
/*     */   }
/*     */   
/*     */   public WorldGenJunglePyramidPiece(DefinedStructureManager var0, NBTTagCompound var1) {
/*  39 */     super(WorldGenFeatureStructurePieceType.G, var1);
/*  40 */     this.e = var1.getBoolean("placedMainChest");
/*  41 */     this.f = var1.getBoolean("placedHiddenChest");
/*  42 */     this.g = var1.getBoolean("placedTrap1");
/*  43 */     this.h = var1.getBoolean("placedTrap2");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound var0) {
/*  48 */     super.a(var0);
/*  49 */     var0.setBoolean("placedMainChest", this.e);
/*  50 */     var0.setBoolean("placedHiddenChest", this.f);
/*  51 */     var0.setBoolean("placedTrap1", this.g);
/*  52 */     var0.setBoolean("placedTrap2", this.h);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  57 */     if (!a(var0, var4, 0)) {
/*  58 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  62 */     a(var0, var4, 0, -4, 0, this.a - 1, 0, this.c - 1, false, var3, i);
/*     */ 
/*     */     
/*  65 */     a(var0, var4, 2, 1, 2, 9, 2, 2, false, var3, i);
/*  66 */     a(var0, var4, 2, 1, 12, 9, 2, 12, false, var3, i);
/*  67 */     a(var0, var4, 2, 1, 3, 2, 2, 11, false, var3, i);
/*  68 */     a(var0, var4, 9, 1, 3, 9, 2, 11, false, var3, i);
/*     */ 
/*     */     
/*  71 */     a(var0, var4, 1, 3, 1, 10, 6, 1, false, var3, i);
/*  72 */     a(var0, var4, 1, 3, 13, 10, 6, 13, false, var3, i);
/*  73 */     a(var0, var4, 1, 3, 2, 1, 6, 12, false, var3, i);
/*  74 */     a(var0, var4, 10, 3, 2, 10, 6, 12, false, var3, i);
/*     */ 
/*     */     
/*  77 */     a(var0, var4, 2, 3, 2, 9, 3, 12, false, var3, i);
/*  78 */     a(var0, var4, 2, 6, 2, 9, 6, 12, false, var3, i);
/*  79 */     a(var0, var4, 3, 7, 3, 8, 7, 11, false, var3, i);
/*  80 */     a(var0, var4, 4, 8, 4, 7, 8, 10, false, var3, i);
/*     */ 
/*     */     
/*  83 */     b(var0, var4, 3, 1, 3, 8, 2, 11);
/*  84 */     b(var0, var4, 4, 3, 6, 7, 3, 9);
/*  85 */     b(var0, var4, 2, 4, 2, 9, 5, 12);
/*  86 */     b(var0, var4, 4, 6, 5, 7, 6, 9);
/*  87 */     b(var0, var4, 5, 7, 6, 6, 7, 8);
/*     */ 
/*     */     
/*  90 */     b(var0, var4, 5, 1, 2, 6, 2, 2);
/*  91 */     b(var0, var4, 5, 2, 12, 6, 2, 12);
/*  92 */     b(var0, var4, 5, 5, 1, 6, 5, 1);
/*  93 */     b(var0, var4, 5, 5, 13, 6, 5, 13);
/*  94 */     a(var0, Blocks.AIR.getBlockData(), 1, 5, 5, var4);
/*  95 */     a(var0, Blocks.AIR.getBlockData(), 10, 5, 5, var4);
/*  96 */     a(var0, Blocks.AIR.getBlockData(), 1, 5, 9, var4);
/*  97 */     a(var0, Blocks.AIR.getBlockData(), 10, 5, 9, var4);
/*     */     
/*     */     int i;
/* 100 */     for (i = 0; i <= 14; i += 14) {
/* 101 */       a(var0, var4, 2, 4, i, 2, 5, i, false, var3, i);
/* 102 */       a(var0, var4, 4, 4, i, 4, 5, i, false, var3, i);
/* 103 */       a(var0, var4, 7, 4, i, 7, 5, i, false, var3, i);
/* 104 */       a(var0, var4, 9, 4, i, 9, 5, i, false, var3, i);
/*     */     } 
/* 106 */     a(var0, var4, 5, 6, 0, 6, 6, 0, false, var3, i);
/* 107 */     for (i = 0; i <= 11; i += 11) {
/* 108 */       for (int k = 2; k <= 12; k += 2) {
/* 109 */         a(var0, var4, i, 4, k, i, 5, k, false, var3, i);
/*     */       }
/* 111 */       a(var0, var4, i, 6, 5, i, 6, 5, false, var3, i);
/* 112 */       a(var0, var4, i, 6, 9, i, 6, 9, false, var3, i);
/*     */     } 
/* 114 */     a(var0, var4, 2, 7, 2, 2, 9, 2, false, var3, i);
/* 115 */     a(var0, var4, 9, 7, 2, 9, 9, 2, false, var3, i);
/* 116 */     a(var0, var4, 2, 7, 12, 2, 9, 12, false, var3, i);
/* 117 */     a(var0, var4, 9, 7, 12, 9, 9, 12, false, var3, i);
/* 118 */     a(var0, var4, 4, 9, 4, 4, 9, 4, false, var3, i);
/* 119 */     a(var0, var4, 7, 9, 4, 7, 9, 4, false, var3, i);
/* 120 */     a(var0, var4, 4, 9, 10, 4, 9, 10, false, var3, i);
/* 121 */     a(var0, var4, 7, 9, 10, 7, 9, 10, false, var3, i);
/* 122 */     a(var0, var4, 5, 9, 7, 6, 9, 7, false, var3, i);
/*     */     
/* 124 */     IBlockData var7 = Blocks.COBBLESTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.EAST);
/* 125 */     IBlockData var8 = Blocks.COBBLESTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.WEST);
/* 126 */     IBlockData var9 = Blocks.COBBLESTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.SOUTH);
/* 127 */     IBlockData var10 = Blocks.COBBLESTONE_STAIRS.getBlockData().set(BlockStairs.FACING, EnumDirection.NORTH);
/*     */     
/* 129 */     a(var0, var10, 5, 9, 6, var4);
/* 130 */     a(var0, var10, 6, 9, 6, var4);
/* 131 */     a(var0, var9, 5, 9, 8, var4);
/* 132 */     a(var0, var9, 6, 9, 8, var4);
/*     */ 
/*     */     
/* 135 */     a(var0, var10, 4, 0, 0, var4);
/* 136 */     a(var0, var10, 5, 0, 0, var4);
/* 137 */     a(var0, var10, 6, 0, 0, var4);
/* 138 */     a(var0, var10, 7, 0, 0, var4);
/*     */ 
/*     */     
/* 141 */     a(var0, var10, 4, 1, 8, var4);
/* 142 */     a(var0, var10, 4, 2, 9, var4);
/* 143 */     a(var0, var10, 4, 3, 10, var4);
/* 144 */     a(var0, var10, 7, 1, 8, var4);
/* 145 */     a(var0, var10, 7, 2, 9, var4);
/* 146 */     a(var0, var10, 7, 3, 10, var4);
/* 147 */     a(var0, var4, 4, 1, 9, 4, 1, 9, false, var3, i);
/* 148 */     a(var0, var4, 7, 1, 9, 7, 1, 9, false, var3, i);
/* 149 */     a(var0, var4, 4, 1, 10, 7, 2, 10, false, var3, i);
/*     */ 
/*     */     
/* 152 */     a(var0, var4, 5, 4, 5, 6, 4, 5, false, var3, i);
/* 153 */     a(var0, var7, 4, 4, 5, var4);
/* 154 */     a(var0, var8, 7, 4, 5, var4);
/*     */     
/*     */     int j;
/* 157 */     for (j = 0; j < 4; j++) {
/* 158 */       a(var0, var9, 5, 0 - j, 6 + j, var4);
/* 159 */       a(var0, var9, 6, 0 - j, 6 + j, var4);
/* 160 */       b(var0, var4, 5, 0 - j, 7 + j, 6, 0 - j, 9 + j);
/*     */     } 
/*     */ 
/*     */     
/* 164 */     b(var0, var4, 1, -3, 12, 10, -1, 13);
/* 165 */     b(var0, var4, 1, -3, 1, 3, -1, 13);
/* 166 */     b(var0, var4, 1, -3, 1, 9, -1, 5);
/* 167 */     for (j = 1; j <= 13; j += 2) {
/* 168 */       a(var0, var4, 1, -3, j, 1, -2, j, false, var3, i);
/*     */     }
/* 170 */     for (j = 2; j <= 12; j += 2) {
/* 171 */       a(var0, var4, 1, -1, j, 3, -1, j, false, var3, i);
/*     */     }
/* 173 */     a(var0, var4, 2, -2, 1, 5, -2, 1, false, var3, i);
/* 174 */     a(var0, var4, 7, -2, 1, 9, -2, 1, false, var3, i);
/* 175 */     a(var0, var4, 6, -3, 1, 6, -3, 1, false, var3, i);
/* 176 */     a(var0, var4, 6, -1, 1, 6, -1, 1, false, var3, i);
/*     */ 
/*     */     
/* 179 */     a(var0, Blocks.TRIPWIRE_HOOK.getBlockData().set(BlockTripwireHook.FACING, EnumDirection.EAST).set(BlockTripwireHook.ATTACHED, Boolean.valueOf(true)), 1, -3, 8, var4);
/* 180 */     a(var0, Blocks.TRIPWIRE_HOOK.getBlockData().set(BlockTripwireHook.FACING, EnumDirection.WEST).set(BlockTripwireHook.ATTACHED, Boolean.valueOf(true)), 4, -3, 8, var4);
/* 181 */     a(var0, Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.EAST, Boolean.valueOf(true)).set(BlockTripwire.WEST, Boolean.valueOf(true)).set(BlockTripwire.ATTACHED, Boolean.valueOf(true)), 2, -3, 8, var4);
/* 182 */     a(var0, Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.EAST, Boolean.valueOf(true)).set(BlockTripwire.WEST, Boolean.valueOf(true)).set(BlockTripwire.ATTACHED, Boolean.valueOf(true)), 3, -3, 8, var4);
/* 183 */     IBlockData var11 = Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.NORTH, BlockPropertyRedstoneSide.SIDE).set(BlockRedstoneWire.SOUTH, BlockPropertyRedstoneSide.SIDE);
/* 184 */     a(var0, var11, 5, -3, 7, var4);
/* 185 */     a(var0, var11, 5, -3, 6, var4);
/* 186 */     a(var0, var11, 5, -3, 5, var4);
/* 187 */     a(var0, var11, 5, -3, 4, var4);
/* 188 */     a(var0, var11, 5, -3, 3, var4);
/* 189 */     a(var0, var11, 5, -3, 2, var4);
/* 190 */     a(var0, Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.NORTH, BlockPropertyRedstoneSide.SIDE).set(BlockRedstoneWire.WEST, BlockPropertyRedstoneSide.SIDE), 5, -3, 1, var4);
/* 191 */     a(var0, Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.EAST, BlockPropertyRedstoneSide.SIDE).set(BlockRedstoneWire.WEST, BlockPropertyRedstoneSide.SIDE), 4, -3, 1, var4);
/* 192 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 3, -3, 1, var4);
/* 193 */     if (!this.g) {
/* 194 */       this.g = a(var0, var4, var3, 3, -2, 1, EnumDirection.NORTH, LootTables.B);
/*     */     }
/* 196 */     a(var0, Blocks.VINE.getBlockData().set(BlockVine.SOUTH, Boolean.valueOf(true)), 3, -2, 2, var4);
/*     */ 
/*     */     
/* 199 */     a(var0, Blocks.TRIPWIRE_HOOK.getBlockData().set(BlockTripwireHook.FACING, EnumDirection.NORTH).set(BlockTripwireHook.ATTACHED, Boolean.valueOf(true)), 7, -3, 1, var4);
/* 200 */     a(var0, Blocks.TRIPWIRE_HOOK.getBlockData().set(BlockTripwireHook.FACING, EnumDirection.SOUTH).set(BlockTripwireHook.ATTACHED, Boolean.valueOf(true)), 7, -3, 5, var4);
/* 201 */     a(var0, Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.NORTH, Boolean.valueOf(true)).set(BlockTripwire.SOUTH, Boolean.valueOf(true)).set(BlockTripwire.ATTACHED, Boolean.valueOf(true)), 7, -3, 2, var4);
/* 202 */     a(var0, Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.NORTH, Boolean.valueOf(true)).set(BlockTripwire.SOUTH, Boolean.valueOf(true)).set(BlockTripwire.ATTACHED, Boolean.valueOf(true)), 7, -3, 3, var4);
/* 203 */     a(var0, Blocks.TRIPWIRE.getBlockData().set(BlockTripwire.NORTH, Boolean.valueOf(true)).set(BlockTripwire.SOUTH, Boolean.valueOf(true)).set(BlockTripwire.ATTACHED, Boolean.valueOf(true)), 7, -3, 4, var4);
/* 204 */     a(var0, Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.EAST, BlockPropertyRedstoneSide.SIDE).set(BlockRedstoneWire.WEST, BlockPropertyRedstoneSide.SIDE), 8, -3, 6, var4);
/* 205 */     a(var0, Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.WEST, BlockPropertyRedstoneSide.SIDE).set(BlockRedstoneWire.SOUTH, BlockPropertyRedstoneSide.SIDE), 9, -3, 6, var4);
/* 206 */     a(var0, Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.NORTH, BlockPropertyRedstoneSide.SIDE).set(BlockRedstoneWire.SOUTH, BlockPropertyRedstoneSide.UP), 9, -3, 5, var4);
/* 207 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 9, -3, 4, var4);
/* 208 */     a(var0, var11, 9, -2, 4, var4);
/* 209 */     if (!this.h) {
/* 210 */       this.h = a(var0, var4, var3, 9, -2, 3, EnumDirection.WEST, LootTables.B);
/*     */     }
/* 212 */     a(var0, Blocks.VINE.getBlockData().set(BlockVine.EAST, Boolean.valueOf(true)), 8, -1, 3, var4);
/* 213 */     a(var0, Blocks.VINE.getBlockData().set(BlockVine.EAST, Boolean.valueOf(true)), 8, -2, 3, var4);
/* 214 */     if (!this.e) {
/* 215 */       this.e = a(var0, var4, var3, 8, -3, 3, LootTables.A);
/*     */     }
/* 217 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 9, -3, 2, var4);
/* 218 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 8, -3, 1, var4);
/* 219 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 4, -3, 5, var4);
/* 220 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 5, -2, 5, var4);
/* 221 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 5, -1, 5, var4);
/* 222 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 6, -3, 5, var4);
/* 223 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 7, -2, 5, var4);
/* 224 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 7, -1, 5, var4);
/* 225 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 8, -3, 5, var4);
/* 226 */     a(var0, var4, 9, -1, 1, 9, -1, 5, false, var3, i);
/*     */ 
/*     */     
/* 229 */     b(var0, var4, 8, -3, 8, 10, -1, 10);
/* 230 */     a(var0, Blocks.CHISELED_STONE_BRICKS.getBlockData(), 8, -2, 11, var4);
/* 231 */     a(var0, Blocks.CHISELED_STONE_BRICKS.getBlockData(), 9, -2, 11, var4);
/* 232 */     a(var0, Blocks.CHISELED_STONE_BRICKS.getBlockData(), 10, -2, 11, var4);
/* 233 */     IBlockData var12 = Blocks.LEVER.getBlockData().set(BlockLever.FACING, EnumDirection.NORTH).set(BlockLever.FACE, BlockPropertyAttachPosition.WALL);
/* 234 */     a(var0, var12, 8, -2, 12, var4);
/* 235 */     a(var0, var12, 9, -2, 12, var4);
/* 236 */     a(var0, var12, 10, -2, 12, var4);
/* 237 */     a(var0, var4, 8, -3, 8, 8, -3, 10, false, var3, i);
/* 238 */     a(var0, var4, 10, -3, 8, 10, -3, 10, false, var3, i);
/* 239 */     a(var0, Blocks.MOSSY_COBBLESTONE.getBlockData(), 10, -2, 9, var4);
/* 240 */     a(var0, var11, 8, -2, 9, var4);
/* 241 */     a(var0, var11, 8, -2, 10, var4);
/* 242 */     a(var0, Blocks.REDSTONE_WIRE.getBlockData().set(BlockRedstoneWire.NORTH, BlockPropertyRedstoneSide.SIDE).set(BlockRedstoneWire.SOUTH, BlockPropertyRedstoneSide.SIDE).set(BlockRedstoneWire.EAST, BlockPropertyRedstoneSide.SIDE).set(BlockRedstoneWire.WEST, BlockPropertyRedstoneSide.SIDE), 10, -1, 9, var4);
/* 243 */     a(var0, Blocks.STICKY_PISTON.getBlockData().set(BlockPiston.FACING, EnumDirection.UP), 9, -2, 8, var4);
/* 244 */     a(var0, Blocks.STICKY_PISTON.getBlockData().set(BlockPiston.FACING, EnumDirection.WEST), 10, -2, 8, var4);
/* 245 */     a(var0, Blocks.STICKY_PISTON.getBlockData().set(BlockPiston.FACING, EnumDirection.WEST), 10, -1, 8, var4);
/* 246 */     a(var0, Blocks.REPEATER.getBlockData().set(BlockRepeater.FACING, EnumDirection.NORTH), 10, -2, 10, var4);
/* 247 */     if (!this.f) {
/* 248 */       this.f = a(var0, var4, var3, 9, -3, 10, LootTables.A);
/*     */     }
/*     */     
/* 251 */     return true;
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends StructurePiece.StructurePieceBlockSelector {
/*     */     public void a(Random var0, int var1, int var2, int var3, boolean var4) {
/* 257 */       if (var0.nextFloat() < 0.4F) {
/* 258 */         this.a = Blocks.COBBLESTONE.getBlockData();
/*     */       } else {
/* 260 */         this.a = Blocks.MOSSY_COBBLESTONE.getBlockData();
/*     */       } 
/*     */     }
/*     */     private a() {} }
/*     */   
/* 265 */   private static final a i = new a();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenJunglePyramidPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */