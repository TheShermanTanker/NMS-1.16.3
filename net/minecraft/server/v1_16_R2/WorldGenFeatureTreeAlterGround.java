/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureTreeAlterGround
/*    */   extends WorldGenFeatureTree
/*    */ {
/*    */   public static final Codec<WorldGenFeatureTreeAlterGround> a;
/*    */   private final WorldGenFeatureStateProvider b;
/*    */   
/*    */   static {
/* 17 */     a = WorldGenFeatureStateProvider.a.fieldOf("provider").xmap(WorldGenFeatureTreeAlterGround::new, var0 -> var0.b).codec();
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenFeatureTreeAlterGround(WorldGenFeatureStateProvider var0) {
/* 22 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFeatureTrees<?> a() {
/* 27 */     return WorldGenFeatureTrees.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(GeneratorAccessSeed var0, Random var1, List<BlockPosition> var2, List<BlockPosition> var3, Set<BlockPosition> var4, StructureBoundingBox var5) {
/* 32 */     int var6 = ((BlockPosition)var2.get(0)).getY();
/* 33 */     var2.stream().filter(var1 -> (var1.getY() == var0)).forEach(var2 -> {
/*    */           a(var0, var1, var2.west().north());
/*    */           a(var0, var1, var2.east(2).north());
/*    */           a(var0, var1, var2.west().south(2));
/*    */           a(var0, var1, var2.east(2).south(2));
/*    */           for (int var3 = 0; var3 < 5; var3++) {
/*    */             int var4 = var1.nextInt(64);
/*    */             int var5 = var4 % 8;
/*    */             int var6 = var4 / 8;
/*    */             if (var5 == 0 || var5 == 7 || var6 == 0 || var6 == 7) {
/*    */               a(var0, var1, var2.b(-3 + var5, 0, -3 + var6));
/*    */             }
/*    */           } 
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   private void a(VirtualLevelWritable var0, Random var1, BlockPosition var2) {
/* 51 */     for (int var3 = -2; var3 <= 2; var3++) {
/* 52 */       for (int var4 = -2; var4 <= 2; var4++) {
/* 53 */         if (Math.abs(var3) != 2 || Math.abs(var4) != 2) {
/* 54 */           b(var0, var1, var2.b(var3, 0, var4));
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void b(VirtualLevelWritable var0, Random var1, BlockPosition var2) {
/* 61 */     for (int var3 = 2; var3 >= -3; var3--) {
/* 62 */       BlockPosition var4 = var2.up(var3);
/* 63 */       if (WorldGenerator.a(var0, var4)) {
/* 64 */         var0.setTypeAndData(var4, this.b.a(var1, var2), 19); break;
/*    */       } 
/* 66 */       if (!WorldGenerator.b(var0, var4) && var3 < 0)
/*    */         break; 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureTreeAlterGround.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */