/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
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
/*     */ public class WorldGenFeatureOceanRuinPieces
/*     */ {
/*  43 */   private static final MinecraftKey[] a = new MinecraftKey[] { new MinecraftKey("underwater_ruin/warm_1"), new MinecraftKey("underwater_ruin/warm_2"), new MinecraftKey("underwater_ruin/warm_3"), new MinecraftKey("underwater_ruin/warm_4"), new MinecraftKey("underwater_ruin/warm_5"), new MinecraftKey("underwater_ruin/warm_6"), new MinecraftKey("underwater_ruin/warm_7"), new MinecraftKey("underwater_ruin/warm_8") };
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
/*  54 */   private static final MinecraftKey[] b = new MinecraftKey[] { new MinecraftKey("underwater_ruin/brick_1"), new MinecraftKey("underwater_ruin/brick_2"), new MinecraftKey("underwater_ruin/brick_3"), new MinecraftKey("underwater_ruin/brick_4"), new MinecraftKey("underwater_ruin/brick_5"), new MinecraftKey("underwater_ruin/brick_6"), new MinecraftKey("underwater_ruin/brick_7"), new MinecraftKey("underwater_ruin/brick_8") };
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
/*  65 */   private static final MinecraftKey[] c = new MinecraftKey[] { new MinecraftKey("underwater_ruin/cracked_1"), new MinecraftKey("underwater_ruin/cracked_2"), new MinecraftKey("underwater_ruin/cracked_3"), new MinecraftKey("underwater_ruin/cracked_4"), new MinecraftKey("underwater_ruin/cracked_5"), new MinecraftKey("underwater_ruin/cracked_6"), new MinecraftKey("underwater_ruin/cracked_7"), new MinecraftKey("underwater_ruin/cracked_8") };
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
/*  76 */   private static final MinecraftKey[] d = new MinecraftKey[] { new MinecraftKey("underwater_ruin/mossy_1"), new MinecraftKey("underwater_ruin/mossy_2"), new MinecraftKey("underwater_ruin/mossy_3"), new MinecraftKey("underwater_ruin/mossy_4"), new MinecraftKey("underwater_ruin/mossy_5"), new MinecraftKey("underwater_ruin/mossy_6"), new MinecraftKey("underwater_ruin/mossy_7"), new MinecraftKey("underwater_ruin/mossy_8") };
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
/*  87 */   private static final MinecraftKey[] e = new MinecraftKey[] { new MinecraftKey("underwater_ruin/big_brick_1"), new MinecraftKey("underwater_ruin/big_brick_2"), new MinecraftKey("underwater_ruin/big_brick_3"), new MinecraftKey("underwater_ruin/big_brick_8") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   private static final MinecraftKey[] f = new MinecraftKey[] { new MinecraftKey("underwater_ruin/big_mossy_1"), new MinecraftKey("underwater_ruin/big_mossy_2"), new MinecraftKey("underwater_ruin/big_mossy_3"), new MinecraftKey("underwater_ruin/big_mossy_8") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   private static final MinecraftKey[] g = new MinecraftKey[] { new MinecraftKey("underwater_ruin/big_cracked_1"), new MinecraftKey("underwater_ruin/big_cracked_2"), new MinecraftKey("underwater_ruin/big_cracked_3"), new MinecraftKey("underwater_ruin/big_cracked_8") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   private static final MinecraftKey[] h = new MinecraftKey[] { new MinecraftKey("underwater_ruin/big_warm_4"), new MinecraftKey("underwater_ruin/big_warm_5"), new MinecraftKey("underwater_ruin/big_warm_6"), new MinecraftKey("underwater_ruin/big_warm_7") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static MinecraftKey a(Random var0) {
/* 116 */     return SystemUtils.<MinecraftKey>a(a, var0);
/*     */   }
/*     */   
/*     */   private static MinecraftKey b(Random var0) {
/* 120 */     return SystemUtils.<MinecraftKey>a(h, var0);
/*     */   }
/*     */   
/*     */   public static void a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, List<StructurePiece> var3, Random var4, WorldGenFeatureOceanRuinConfiguration var5) {
/* 124 */     boolean var6 = (var4.nextFloat() <= var5.c);
/* 125 */     float var7 = var6 ? 0.9F : 0.8F;
/*     */     
/* 127 */     a(var0, var1, var2, var3, var4, var5, var6, var7);
/*     */     
/* 129 */     if (var6 && var4.nextFloat() <= var5.d) {
/* 130 */       a(var0, var4, var2, var1, var5, var3);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void a(DefinedStructureManager var0, Random var1, EnumBlockRotation var2, BlockPosition var3, WorldGenFeatureOceanRuinConfiguration var4, List<StructurePiece> var5) {
/* 135 */     int var6 = var3.getX();
/* 136 */     int var7 = var3.getZ();
/* 137 */     BlockPosition var8 = DefinedStructure.a(new BlockPosition(15, 0, 15), EnumBlockMirror.NONE, var2, BlockPosition.ZERO).b(var6, 0, var7);
/* 138 */     StructureBoundingBox var9 = StructureBoundingBox.a(var6, 0, var7, var8.getX(), 0, var8.getZ());
/* 139 */     BlockPosition var10 = new BlockPosition(Math.min(var6, var8.getX()), 0, Math.min(var7, var8.getZ()));
/* 140 */     List<BlockPosition> var11 = a(var1, var10.getX(), var10.getZ());
/* 141 */     int var12 = MathHelper.nextInt(var1, 4, 8);
/*     */     
/* 143 */     for (int var13 = 0; var13 < var12; var13++) {
/* 144 */       if (!var11.isEmpty()) {
/* 145 */         int var14 = var1.nextInt(var11.size());
/* 146 */         BlockPosition var15 = var11.remove(var14);
/* 147 */         int var16 = var15.getX();
/* 148 */         int var17 = var15.getZ();
/* 149 */         EnumBlockRotation var18 = EnumBlockRotation.a(var1);
/* 150 */         BlockPosition var19 = DefinedStructure.a(new BlockPosition(5, 0, 6), EnumBlockMirror.NONE, var18, BlockPosition.ZERO).b(var16, 0, var17);
/* 151 */         StructureBoundingBox var20 = StructureBoundingBox.a(var16, 0, var17, var19.getX(), 0, var19.getZ());
/* 152 */         if (!var20.b(var9))
/*     */         {
/*     */ 
/*     */           
/* 156 */           a(var0, var15, var18, var5, var1, var4, false, 0.8F); } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static List<BlockPosition> a(Random var0, int var1, int var2) {
/* 162 */     List<BlockPosition> var3 = Lists.newArrayList();
/* 163 */     var3.add(new BlockPosition(var1 - 16 + MathHelper.nextInt(var0, 1, 8), 90, var2 + 16 + MathHelper.nextInt(var0, 1, 7)));
/* 164 */     var3.add(new BlockPosition(var1 - 16 + MathHelper.nextInt(var0, 1, 8), 90, var2 + MathHelper.nextInt(var0, 1, 7)));
/* 165 */     var3.add(new BlockPosition(var1 - 16 + MathHelper.nextInt(var0, 1, 8), 90, var2 - 16 + MathHelper.nextInt(var0, 4, 8)));
/* 166 */     var3.add(new BlockPosition(var1 + MathHelper.nextInt(var0, 1, 7), 90, var2 + 16 + MathHelper.nextInt(var0, 1, 7)));
/* 167 */     var3.add(new BlockPosition(var1 + MathHelper.nextInt(var0, 1, 7), 90, var2 - 16 + MathHelper.nextInt(var0, 4, 6)));
/* 168 */     var3.add(new BlockPosition(var1 + 16 + MathHelper.nextInt(var0, 1, 7), 90, var2 + 16 + MathHelper.nextInt(var0, 3, 8)));
/* 169 */     var3.add(new BlockPosition(var1 + 16 + MathHelper.nextInt(var0, 1, 7), 90, var2 + MathHelper.nextInt(var0, 1, 7)));
/* 170 */     var3.add(new BlockPosition(var1 + 16 + MathHelper.nextInt(var0, 1, 7), 90, var2 - 16 + MathHelper.nextInt(var0, 4, 8)));
/*     */     
/* 172 */     return var3;
/*     */   }
/*     */   
/*     */   private static void a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, List<StructurePiece> var3, Random var4, WorldGenFeatureOceanRuinConfiguration var5, boolean var6, float var7) {
/* 176 */     if (var5.b == WorldGenFeatureOceanRuin.Temperature.WARM) {
/* 177 */       MinecraftKey var8 = var6 ? b(var4) : a(var4);
/* 178 */       var3.add(new a(var0, var8, var1, var2, var7, var5.b, var6));
/* 179 */     } else if (var5.b == WorldGenFeatureOceanRuin.Temperature.COLD) {
/* 180 */       MinecraftKey[] var8 = var6 ? e : b;
/* 181 */       MinecraftKey[] var9 = var6 ? g : c;
/* 182 */       MinecraftKey[] var10 = var6 ? f : d;
/*     */       
/* 184 */       int var11 = var4.nextInt(var8.length);
/* 185 */       var3.add(new a(var0, var8[var11], var1, var2, var7, var5.b, var6));
/* 186 */       var3.add(new a(var0, var9[var11], var1, var2, 0.7F, var5.b, var6));
/* 187 */       var3.add(new a(var0, var10[var11], var1, var2, 0.5F, var5.b, var6));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class a extends DefinedStructurePiece {
/*     */     private final WorldGenFeatureOceanRuin.Temperature d;
/*     */     private final float e;
/*     */     private final MinecraftKey f;
/*     */     private final EnumBlockRotation g;
/*     */     private final boolean h;
/*     */     
/*     */     public a(DefinedStructureManager var0, MinecraftKey var1, BlockPosition var2, EnumBlockRotation var3, float var4, WorldGenFeatureOceanRuin.Temperature var5, boolean var6) {
/* 199 */       super(WorldGenFeatureStructurePieceType.H, 0);
/*     */       
/* 201 */       this.f = var1;
/* 202 */       this.c = var2;
/* 203 */       this.g = var3;
/* 204 */       this.e = var4;
/* 205 */       this.d = var5;
/* 206 */       this.h = var6;
/*     */       
/* 208 */       a(var0);
/*     */     }
/*     */     
/*     */     public a(DefinedStructureManager var0, NBTTagCompound var1) {
/* 212 */       super(WorldGenFeatureStructurePieceType.H, var1);
/* 213 */       this.f = new MinecraftKey(var1.getString("Template"));
/* 214 */       this.g = EnumBlockRotation.valueOf(var1.getString("Rot"));
/* 215 */       this.e = var1.getFloat("Integrity");
/* 216 */       this.d = WorldGenFeatureOceanRuin.Temperature.valueOf(var1.getString("BiomeType"));
/* 217 */       this.h = var1.getBoolean("IsLarge");
/* 218 */       a(var0);
/*     */     }
/*     */     
/*     */     private void a(DefinedStructureManager var0) {
/* 222 */       DefinedStructure var1 = var0.a(this.f);
/* 223 */       DefinedStructureInfo var2 = (new DefinedStructureInfo()).a(this.g).a(EnumBlockMirror.NONE).a(DefinedStructureProcessorBlockIgnore.d);
/* 224 */       a(var1, this.c, var2);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(NBTTagCompound var0) {
/* 229 */       super.a(var0);
/* 230 */       var0.setString("Template", this.f.toString());
/* 231 */       var0.setString("Rot", this.g.name());
/* 232 */       var0.setFloat("Integrity", this.e);
/* 233 */       var0.setString("BiomeType", this.d.toString());
/* 234 */       var0.setBoolean("IsLarge", this.h);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(String var0, BlockPosition var1, WorldAccess var2, Random var3, StructureBoundingBox var4) {
/* 239 */       if ("chest".equals(var0)) {
/* 240 */         var2.setTypeAndData(var1, Blocks.CHEST.getBlockData().set(BlockChest.d, Boolean.valueOf(var2.getFluid(var1).a(TagsFluid.WATER))), 2);
/*     */         
/* 242 */         TileEntity var5 = var2.getTileEntity(var1);
/* 243 */         if (var5 instanceof TileEntityChest) {
/* 244 */           ((TileEntityChest)var5).setLootTable(this.h ? LootTables.F : LootTables.E, var3.nextLong());
/*     */         }
/*     */       }
/* 247 */       else if ("drowned".equals(var0)) {
/* 248 */         EntityDrowned var5 = EntityTypes.DROWNED.a(var2.getMinecraftWorld());
/* 249 */         var5.setPersistent();
/* 250 */         var5.setPositionRotation(var1, 0.0F, 0.0F);
/* 251 */         var5.prepare(var2, var2.getDamageScaler(var1), EnumMobSpawn.STRUCTURE, (GroupDataEntity)null, (NBTTagCompound)null);
/* 252 */         var2.addAllEntities(var5);
/* 253 */         if (var1.getY() > var2.getSeaLevel()) {
/* 254 */           var2.setTypeAndData(var1, Blocks.AIR.getBlockData(), 2);
/*     */         } else {
/* 256 */           var2.setTypeAndData(var1, Blocks.WATER.getBlockData(), 2);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 263 */       this.b.b().a(new DefinedStructureProcessorRotation(this.e)).a(DefinedStructureProcessorBlockIgnore.d);
/* 264 */       int var7 = var0.a(HeightMap.Type.OCEAN_FLOOR_WG, this.c.getX(), this.c.getZ());
/* 265 */       this.c = new BlockPosition(this.c.getX(), var7, this.c.getZ());
/* 266 */       BlockPosition var8 = DefinedStructure.a(new BlockPosition(this.a.a().getX() - 1, 0, this.a.a().getZ() - 1), EnumBlockMirror.NONE, this.g, BlockPosition.ZERO).a(this.c);
/* 267 */       this.c = new BlockPosition(this.c.getX(), a(this.c, var0, var8), this.c.getZ());
/*     */       
/* 269 */       return super.a(var0, var1, var2, var3, var4, var5, var6);
/*     */     }
/*     */     
/*     */     private int a(BlockPosition var0, IBlockAccess var1, BlockPosition var2) {
/* 273 */       int var3 = var0.getY();
/* 274 */       int var4 = 512;
/* 275 */       int var5 = var3 - 1;
/* 276 */       int var6 = 0;
/* 277 */       for (BlockPosition var8 : BlockPosition.a(var0, var2)) {
/* 278 */         int var9 = var8.getX();
/* 279 */         int var10 = var8.getZ();
/* 280 */         int var11 = var0.getY() - 1;
/* 281 */         BlockPosition.MutableBlockPosition var12 = new BlockPosition.MutableBlockPosition(var9, var11, var10);
/* 282 */         IBlockData var13 = var1.getType(var12);
/* 283 */         Fluid var14 = var1.getFluid(var12);
/* 284 */         while ((var13.isAir() || var14.a(TagsFluid.WATER) || var13.getBlock().a(TagsBlock.ICE)) && var11 > 1) {
/* 285 */           var11--;
/* 286 */           var12.d(var9, var11, var10);
/* 287 */           var13 = var1.getType(var12);
/* 288 */           var14 = var1.getFluid(var12);
/*     */         } 
/*     */         
/* 291 */         var4 = Math.min(var4, var11);
/* 292 */         if (var11 < var5 - 2) {
/* 293 */           var6++;
/*     */         }
/*     */       } 
/*     */       
/* 297 */       int var7 = Math.abs(var0.getX() - var2.getX());
/* 298 */       if (var5 - var4 > 2 && var6 > var7 - 2) {
/* 299 */         var3 = var4 + 1;
/*     */       }
/*     */       
/* 302 */       return var3;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureOceanRuinPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */