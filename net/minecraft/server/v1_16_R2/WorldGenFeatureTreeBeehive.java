/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureTreeBeehive
/*    */   extends WorldGenFeatureTree
/*    */ {
/*    */   public static final Codec<WorldGenFeatureTreeBeehive> a;
/*    */   private final float b;
/*    */   
/*    */   static {
/* 23 */     a = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(WorldGenFeatureTreeBeehive::new, var0 -> Float.valueOf(var0.b)).codec();
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenFeatureTreeBeehive(float var0) {
/* 28 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFeatureTrees<?> a() {
/* 33 */     return WorldGenFeatureTrees.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(GeneratorAccessSeed var0, Random var1, List<BlockPosition> var2, List<BlockPosition> var3, Set<BlockPosition> var4, StructureBoundingBox var5) {
/* 38 */     if (var1.nextFloat() >= this.b) {
/*    */       return;
/*    */     }
/*    */     
/* 42 */     EnumDirection var6 = BlockBeehive.a(var1);
/*    */ 
/*    */ 
/*    */     
/* 46 */     int var7 = !var3.isEmpty() ? Math.max(((BlockPosition)var3.get(0)).getY() - 1, ((BlockPosition)var2.get(0)).getY()) : Math.min(((BlockPosition)var2.get(0)).getY() + 1 + var1.nextInt(3), ((BlockPosition)var2.get(var2.size() - 1)).getY());
/*    */     
/* 48 */     List<BlockPosition> var8 = (List<BlockPosition>)var2.stream().filter(var1 -> (var1.getY() == var0)).collect(Collectors.toList());
/* 49 */     if (var8.isEmpty()) {
/*    */       return;
/*    */     }
/* 52 */     BlockPosition var9 = var8.get(var1.nextInt(var8.size()));
/* 53 */     BlockPosition var10 = var9.shift(var6);
/* 54 */     if (!WorldGenerator.b(var0, var10) || !WorldGenerator.b(var0, var10.shift(EnumDirection.SOUTH))) {
/*    */       return;
/*    */     }
/*    */     
/* 58 */     IBlockData var11 = Blocks.BEE_NEST.getBlockData().set(BlockBeehive.a, EnumDirection.SOUTH);
/* 59 */     a(var0, var10, var11, var4, var5);
/* 60 */     TileEntity var12 = var0.getTileEntity(var10);
/* 61 */     if (var12 instanceof TileEntityBeehive) {
/* 62 */       TileEntityBeehive var13 = (TileEntityBeehive)var12;
/* 63 */       int var14 = 2 + var1.nextInt(2);
/* 64 */       for (int var15 = 0; var15 < var14; var15++) {
/* 65 */         EntityBee var16 = new EntityBee(EntityTypes.BEE, var0.getMinecraftWorld());
/* 66 */         var13.a(var16, false, var1.nextInt(599));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureTreeBeehive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */