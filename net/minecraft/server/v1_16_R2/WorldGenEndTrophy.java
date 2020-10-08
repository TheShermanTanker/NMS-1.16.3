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
/*    */ public class WorldGenEndTrophy
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/* 19 */   public static final BlockPosition a = BlockPosition.ZERO;
/*    */   
/*    */   private final boolean ab;
/*    */   
/*    */   public WorldGenEndTrophy(boolean var0) {
/* 24 */     super(WorldGenFeatureEmptyConfiguration.a);
/* 25 */     this.ab = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 30 */     for (BlockPosition var6 : BlockPosition.a(new BlockPosition(var3.getX() - 4, var3.getY() - 1, var3.getZ() - 4), new BlockPosition(var3.getX() + 4, var3.getY() + 32, var3.getZ() + 4))) {
/* 31 */       boolean var7 = var6.a(var3, 2.5D);
/*    */       
/* 33 */       if (var7 || var6.a(var3, 3.5D)) {
/* 34 */         if (var6.getY() < var3.getY()) {
/* 35 */           if (var7) {
/*    */             
/* 37 */             a(var0, var6, Blocks.BEDROCK.getBlockData()); continue;
/* 38 */           }  if (var6.getY() < var3.getY())
/*    */           {
/* 40 */             a(var0, var6, Blocks.END_STONE.getBlockData()); }  continue;
/*    */         } 
/* 42 */         if (var6.getY() > var3.getY()) {
/*    */           
/* 44 */           a(var0, var6, Blocks.AIR.getBlockData()); continue;
/* 45 */         }  if (!var7) {
/*    */           
/* 47 */           a(var0, var6, Blocks.BEDROCK.getBlockData()); continue;
/* 48 */         }  if (this.ab) {
/*    */           
/* 50 */           a(var0, new BlockPosition(var6), Blocks.END_PORTAL.getBlockData()); continue;
/*    */         } 
/* 52 */         a(var0, new BlockPosition(var6), Blocks.AIR.getBlockData());
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 58 */     for (int i = 0; i < 4; i++) {
/* 59 */       a(var0, var3.up(i), Blocks.BEDROCK.getBlockData());
/*    */     }
/*    */     
/* 62 */     BlockPosition var5 = var3.up(2);
/* 63 */     for (EnumDirection var7 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 64 */       a(var0, var5.shift(var7), Blocks.WALL_TORCH.getBlockData().set(BlockTorchWall.a, var7));
/*    */     }
/*    */     
/* 67 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenEndTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */