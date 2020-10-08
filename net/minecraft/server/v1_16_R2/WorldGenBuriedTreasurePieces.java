/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenBuriedTreasurePieces
/*    */ {
/*    */   public static class a
/*    */     extends StructurePiece
/*    */   {
/*    */     public a(BlockPosition var0) {
/* 23 */       super(WorldGenFeatureStructurePieceType.aa, 0);
/* 24 */       this.n = new StructureBoundingBox(var0.getX(), var0.getY(), var0.getZ(), var0.getX(), var0.getY(), var0.getZ());
/*    */     }
/*    */     
/*    */     public a(DefinedStructureManager var0, NBTTagCompound var1) {
/* 28 */       super(WorldGenFeatureStructurePieceType.aa, var1);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     protected void a(NBTTagCompound var0) {}
/*    */ 
/*    */     
/*    */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 37 */       int var7 = var0.a(HeightMap.Type.OCEAN_FLOOR_WG, this.n.a, this.n.c);
/* 38 */       BlockPosition.MutableBlockPosition var8 = new BlockPosition.MutableBlockPosition(this.n.a, var7, this.n.c);
/*    */       
/* 40 */       while (var8.getY() > 0) {
/* 41 */         IBlockData var9 = var0.getType(var8);
/* 42 */         IBlockData var10 = var0.getType(var8.down());
/*    */         
/* 44 */         if (var10 == Blocks.SANDSTONE.getBlockData() || var10 == Blocks.STONE
/* 45 */           .getBlockData() || var10 == Blocks.ANDESITE
/* 46 */           .getBlockData() || var10 == Blocks.GRANITE
/* 47 */           .getBlockData() || var10 == Blocks.DIORITE
/* 48 */           .getBlockData()) {
/*    */           
/* 50 */           IBlockData var11 = (var9.isAir() || a(var9)) ? Blocks.SAND.getBlockData() : var9;
/*    */           
/* 52 */           for (EnumDirection var15 : EnumDirection.values()) {
/* 53 */             BlockPosition var16 = var8.shift(var15);
/* 54 */             IBlockData var17 = var0.getType(var16);
/*    */             
/* 56 */             if (var17.isAir() || a(var17)) {
/* 57 */               BlockPosition var18 = var16.down();
/* 58 */               IBlockData var19 = var0.getType(var18);
/*    */               
/* 60 */               if ((var19.isAir() || a(var19)) && var15 != EnumDirection.UP) {
/* 61 */                 var0.setTypeAndData(var16, var10, 3);
/*    */               } else {
/* 63 */                 var0.setTypeAndData(var16, var11, 3);
/*    */               } 
/*    */             } 
/*    */           } 
/* 67 */           this.n = new StructureBoundingBox(var8.getX(), var8.getY(), var8.getZ(), var8.getX(), var8.getY(), var8.getZ());
/* 68 */           return a(var0, var4, var3, var8, LootTables.G, (IBlockData)null);
/*    */         } 
/*    */         
/* 71 */         var8.e(0, -1, 0);
/*    */       } 
/* 73 */       return false;
/*    */     }
/*    */     
/*    */     private boolean a(IBlockData var0) {
/* 77 */       return (var0 == Blocks.WATER.getBlockData() || var0 == Blocks.LAVA
/* 78 */         .getBlockData());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenBuriedTreasurePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */