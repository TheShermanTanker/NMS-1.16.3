/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class WorldGenIglooPiece
/*     */ {
/*  34 */   private static final MinecraftKey a = new MinecraftKey("igloo/top");
/*  35 */   private static final MinecraftKey b = new MinecraftKey("igloo/middle");
/*  36 */   private static final MinecraftKey c = new MinecraftKey("igloo/bottom");
/*     */   
/*  38 */   private static final Map<MinecraftKey, BlockPosition> d = (Map<MinecraftKey, BlockPosition>)ImmutableMap.of(a, new BlockPosition(3, 5, 5), b, new BlockPosition(1, 3, 1), c, new BlockPosition(3, 6, 7));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private static final Map<MinecraftKey, BlockPosition> e = (Map<MinecraftKey, BlockPosition>)ImmutableMap.of(a, BlockPosition.ZERO, b, new BlockPosition(2, -3, 4), c, new BlockPosition(0, -3, -2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, List<StructurePiece> var3, Random var4) {
/*  51 */     if (var4.nextDouble() < 0.5D) {
/*  52 */       int var5 = var4.nextInt(8) + 4;
/*  53 */       var3.add(new a(var0, c, var1, var2, var5 * 3));
/*  54 */       for (int var6 = 0; var6 < var5 - 1; var6++) {
/*  55 */         var3.add(new a(var0, b, var1, var2, var6 * 3));
/*     */       }
/*     */     } 
/*     */     
/*  59 */     var3.add(new a(var0, a, var1, var2, 0));
/*     */   }
/*     */   
/*     */   public static class a extends DefinedStructurePiece {
/*     */     private final MinecraftKey d;
/*     */     private final EnumBlockRotation e;
/*     */     
/*     */     public a(DefinedStructureManager var0, MinecraftKey var1, BlockPosition var2, EnumBlockRotation var3, int var4) {
/*  67 */       super(WorldGenFeatureStructurePieceType.I, 0);
/*  68 */       this.d = var1;
/*  69 */       BlockPosition var5 = (BlockPosition)WorldGenIglooPiece.a().get(var1);
/*  70 */       this.c = var2.b(var5.getX(), var5.getY() - var4, var5.getZ());
/*  71 */       this.e = var3;
/*  72 */       a(var0);
/*     */     }
/*     */     
/*     */     public a(DefinedStructureManager var0, NBTTagCompound var1) {
/*  76 */       super(WorldGenFeatureStructurePieceType.I, var1);
/*  77 */       this.d = new MinecraftKey(var1.getString("Template"));
/*  78 */       this.e = EnumBlockRotation.valueOf(var1.getString("Rot"));
/*  79 */       a(var0);
/*     */     }
/*     */     
/*     */     private void a(DefinedStructureManager var0) {
/*  83 */       DefinedStructure var1 = var0.a(this.d);
/*  84 */       DefinedStructureInfo var2 = (new DefinedStructureInfo()).a(this.e).a(EnumBlockMirror.NONE).a((BlockPosition)WorldGenIglooPiece.b().get(this.d)).a(DefinedStructureProcessorBlockIgnore.b);
/*  85 */       a(var1, this.c, var2);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(NBTTagCompound var0) {
/*  90 */       super.a(var0);
/*  91 */       var0.setString("Template", this.d.toString());
/*  92 */       var0.setString("Rot", this.e.name());
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(String var0, BlockPosition var1, WorldAccess var2, Random var3, StructureBoundingBox var4) {
/*  97 */       if (!"chest".equals(var0)) {
/*     */         return;
/*     */       }
/*     */       
/* 101 */       var2.setTypeAndData(var1, Blocks.AIR.getBlockData(), 3);
/* 102 */       TileEntity var5 = var2.getTileEntity(var1.down());
/* 103 */       if (var5 instanceof TileEntityChest) {
/* 104 */         ((TileEntityChest)var5).setLootTable(LootTables.C, var3.nextLong());
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 110 */       DefinedStructureInfo var7 = (new DefinedStructureInfo()).a(this.e).a(EnumBlockMirror.NONE).a((BlockPosition)WorldGenIglooPiece.b().get(this.d)).a(DefinedStructureProcessorBlockIgnore.b);
/*     */       
/* 112 */       BlockPosition var8 = (BlockPosition)WorldGenIglooPiece.a().get(this.d);
/* 113 */       BlockPosition var9 = this.c.a(DefinedStructure.a(var7, new BlockPosition(3 - var8.getX(), 0, 0 - var8.getZ())));
/* 114 */       int var10 = var0.a(HeightMap.Type.WORLD_SURFACE_WG, var9.getX(), var9.getZ());
/* 115 */       BlockPosition var11 = this.c;
/* 116 */       this.c = this.c.b(0, var10 - 90 - 1, 0);
/*     */       
/* 118 */       boolean var12 = super.a(var0, var1, var2, var3, var4, var5, var6);
/*     */       
/* 120 */       if (this.d.equals(WorldGenIglooPiece.c())) {
/* 121 */         BlockPosition var13 = this.c.a(DefinedStructure.a(var7, new BlockPosition(3, 0, 5)));
/* 122 */         IBlockData var14 = var0.getType(var13.down());
/* 123 */         if (!var14.isAir() && !var14.a(Blocks.LADDER)) {
/* 124 */           var0.setTypeAndData(var13, Blocks.SNOW_BLOCK.getBlockData(), 3);
/*     */         }
/*     */       } 
/*     */       
/* 128 */       this.c = var11;
/* 129 */       return var12;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenIglooPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */