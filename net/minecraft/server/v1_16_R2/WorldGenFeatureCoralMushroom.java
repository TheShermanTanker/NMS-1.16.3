/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureCoralMushroom
/*    */   extends WorldGenFeatureCoral
/*    */ {
/*    */   public WorldGenFeatureCoralMushroom(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(GeneratorAccess var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 19 */     int var4 = var1.nextInt(3) + 3;
/* 20 */     int var5 = var1.nextInt(3) + 3;
/* 21 */     int var6 = var1.nextInt(3) + 3;
/*    */     
/* 23 */     int var7 = var1.nextInt(3) + 1;
/*    */     
/* 25 */     BlockPosition.MutableBlockPosition var8 = var2.i();
/*    */ 
/*    */ 
/*    */     
/* 29 */     for (int var9 = 0; var9 <= var5; var9++) {
/* 30 */       for (int var10 = 0; var10 <= var4; var10++) {
/* 31 */         for (int var11 = 0; var11 <= var6; var11++) {
/* 32 */           var8.d(var9 + var2.getX(), var10 + var2.getY(), var11 + var2.getZ());
/* 33 */           var8.c(EnumDirection.DOWN, var7);
/*    */ 
/*    */           
/* 36 */           if ((var9 != 0 && var9 != var5) || (var10 != 0 && var10 != var4))
/*    */           {
/*    */ 
/*    */             
/* 40 */             if ((var11 != 0 && var11 != var6) || (var10 != 0 && var10 != var4))
/*    */             {
/*    */ 
/*    */               
/* 44 */               if ((var9 != 0 && var9 != var5) || (var11 != 0 && var11 != var6))
/*    */               {
/*    */ 
/*    */ 
/*    */                 
/* 49 */                 if (var9 == 0 || var9 == var5 || var10 == 0 || var10 == var4 || var11 == 0 || var11 == var6)
/*    */                 {
/*    */ 
/*    */ 
/*    */                   
/* 54 */                   if (var1.nextFloat() >= 0.1F)
/*    */                   {
/*    */ 
/*    */                     
/* 58 */                     if (!b(var0, var1, var8, var3)); }  }  } 
/*    */             }
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 64 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureCoralMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */