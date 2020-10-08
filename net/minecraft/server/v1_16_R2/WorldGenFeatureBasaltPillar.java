/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
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
/*    */ public class WorldGenFeatureBasaltPillar
/*    */   extends WorldGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenFeatureBasaltPillar(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 19 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureEmptyConfiguration var4) {
/* 24 */     if (!var0.isEmpty(var3) || var0.isEmpty(var3.up())) {
/* 25 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 29 */     BlockPosition.MutableBlockPosition var5 = var3.i();
/* 30 */     BlockPosition.MutableBlockPosition var6 = var3.i();
/* 31 */     boolean var7 = true;
/* 32 */     boolean var8 = true;
/* 33 */     boolean var9 = true;
/* 34 */     boolean var10 = true;
/*    */     
/* 36 */     while (var0.isEmpty(var5)) {
/* 37 */       if (World.isOutsideWorld(var5)) {
/* 38 */         return true;
/*    */       }
/*    */       
/* 41 */       var0.setTypeAndData(var5, Blocks.BASALT.getBlockData(), 2);
/*    */       
/* 43 */       var7 = (var7 && b(var0, var2, var6.a(var5, EnumDirection.NORTH)));
/* 44 */       var8 = (var8 && b(var0, var2, var6.a(var5, EnumDirection.SOUTH)));
/* 45 */       var9 = (var9 && b(var0, var2, var6.a(var5, EnumDirection.WEST)));
/* 46 */       var10 = (var10 && b(var0, var2, var6.a(var5, EnumDirection.EAST)));
/*    */       
/* 48 */       var5.c(EnumDirection.DOWN);
/*    */     } 
/*    */ 
/*    */     
/* 52 */     var5.c(EnumDirection.UP);
/* 53 */     a(var0, var2, var6.a(var5, EnumDirection.NORTH));
/* 54 */     a(var0, var2, var6.a(var5, EnumDirection.SOUTH));
/* 55 */     a(var0, var2, var6.a(var5, EnumDirection.WEST));
/* 56 */     a(var0, var2, var6.a(var5, EnumDirection.EAST));
/* 57 */     var5.c(EnumDirection.DOWN);
/*    */     
/* 59 */     BlockPosition.MutableBlockPosition var11 = new BlockPosition.MutableBlockPosition();
/* 60 */     for (int var12 = -3; var12 < 4; var12++) {
/* 61 */       for (int var13 = -3; var13 < 4; var13++) {
/* 62 */         int var14 = MathHelper.a(var12) * MathHelper.a(var13);
/* 63 */         if (var2.nextInt(10) < 10 - var14) {
/*    */ 
/*    */ 
/*    */           
/* 67 */           var11.g(var5.b(var12, 0, var13));
/* 68 */           int var15 = 3;
/* 69 */           while (var0.isEmpty(var6.a(var11, EnumDirection.DOWN))) {
/* 70 */             var11.c(EnumDirection.DOWN);
/* 71 */             var15--;
/* 72 */             if (var15 <= 0) {
/*    */               break;
/*    */             }
/*    */           } 
/*    */           
/* 77 */           if (!var0.isEmpty(var6.a(var11, EnumDirection.DOWN))) {
/* 78 */             var0.setTypeAndData(var11, Blocks.BASALT.getBlockData(), 2);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 83 */     return true;
/*    */   }
/*    */   
/*    */   private void a(GeneratorAccess var0, Random var1, BlockPosition var2) {
/* 87 */     if (var1.nextBoolean()) {
/* 88 */       var0.setTypeAndData(var2, Blocks.BASALT.getBlockData(), 2);
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean b(GeneratorAccess var0, Random var1, BlockPosition var2) {
/* 93 */     if (var1.nextInt(10) != 0) {
/* 94 */       var0.setTypeAndData(var2, Blocks.BASALT.getBlockData(), 2);
/* 95 */       return true;
/*    */     } 
/*    */     
/* 98 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBasaltPillar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */