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
/*    */ public class WorldGenEndGateway
/*    */   extends WorldGenerator<WorldGenEndGatewayConfiguration>
/*    */ {
/*    */   public WorldGenEndGateway(Codec<WorldGenEndGatewayConfiguration> var0) {
/* 16 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3, WorldGenEndGatewayConfiguration var4) {
/* 21 */     for (BlockPosition var6 : BlockPosition.a(var3.b(-1, -2, -1), var3.b(1, 2, 1))) {
/* 22 */       boolean var7 = (var6.getX() == var3.getX());
/* 23 */       boolean var8 = (var6.getY() == var3.getY());
/* 24 */       boolean var9 = (var6.getZ() == var3.getZ());
/* 25 */       boolean var10 = (Math.abs(var6.getY() - var3.getY()) == 2);
/*    */       
/* 27 */       if (var7 && var8 && var9) {
/* 28 */         BlockPosition var11 = var6.immutableCopy();
/* 29 */         a(var0, var11, Blocks.END_GATEWAY.getBlockData());
/* 30 */         var4.c().ifPresent(var3 -> {
/*    */               TileEntity var4 = var0.getTileEntity(var1); if (var4 instanceof TileEntityEndGateway) {
/*    */                 TileEntityEndGateway var5 = (TileEntityEndGateway)var4; var5.a(var3, var2.d());
/*    */                 var4.update();
/*    */               } 
/*    */             });
/*    */         continue;
/*    */       } 
/* 38 */       if (var8) {
/* 39 */         a(var0, var6, Blocks.AIR.getBlockData()); continue;
/* 40 */       }  if (var10 && var7 && var9) {
/* 41 */         a(var0, var6, Blocks.BEDROCK.getBlockData()); continue;
/* 42 */       }  if ((!var7 && !var9) || var10) {
/* 43 */         a(var0, var6, Blocks.AIR.getBlockData()); continue;
/*    */       } 
/* 45 */       a(var0, var6, Blocks.BEDROCK.getBlockData());
/*    */     } 
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenEndGateway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */