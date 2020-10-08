/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenTaigaStructure
/*    */   extends WorldGenerator<WorldGenFeatureLakeConfiguration>
/*    */ {
/*    */   public WorldGenTaigaStructure(Codec<WorldGenFeatureLakeConfiguration> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenFeatureLakeConfiguration var4) {
/* 19 */     while (var3.getY() > 3) {
/* 20 */       if (!var0.isEmpty(var3.down())) {
/* 21 */         Block block = var0.getType(var3.down()).getBlock();
/* 22 */         if (b(block) || a(block)) {
/*    */           break;
/*    */         }
/*    */       } 
/* 26 */       var3 = var3.down();
/*    */     } 
/* 28 */     if (var3.getY() <= 3) {
/* 29 */       return false;
/*    */     }
/*    */     
/* 32 */     int var5 = 0;
/* 33 */     while (var5 < 3) {
/* 34 */       int var6 = var2.nextInt(2);
/* 35 */       int var7 = var2.nextInt(2);
/* 36 */       int var8 = var2.nextInt(2);
/* 37 */       float var9 = (var6 + var7 + var8) * 0.333F + 0.5F;
/*    */       
/* 39 */       for (BlockPosition var11 : BlockPosition.a(var3.b(-var6, -var7, -var8), var3.b(var6, var7, var8))) {
/* 40 */         if (var11.j(var3) <= (var9 * var9)) {
/* 41 */           var0.setTypeAndData(var11, var4.b, 4);
/*    */         }
/*    */       } 
/*    */       
/* 45 */       var3 = var3.b(-1 + var2.nextInt(2), -var2.nextInt(2), -1 + var2.nextInt(2));
/* 46 */       var5++;
/*    */     } 
/*    */     
/* 49 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenTaigaStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */