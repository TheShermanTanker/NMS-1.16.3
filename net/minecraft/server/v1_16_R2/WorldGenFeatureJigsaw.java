/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureJigsaw
/*    */   extends StructureGenerator<WorldGenFeatureVillageConfiguration>
/*    */ {
/*    */   private final int u;
/*    */   private final boolean v;
/*    */   private final boolean w;
/*    */   
/*    */   public WorldGenFeatureJigsaw(Codec<WorldGenFeatureVillageConfiguration> var0, int var1, boolean var2, boolean var3) {
/* 22 */     super(var0);
/* 23 */     this.u = var1;
/* 24 */     this.v = var2;
/* 25 */     this.w = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureVillageConfiguration> a() {
/* 30 */     return (var0, var1, var2, var3, var4, var5) -> new a(this, var1, var2, var3, var4, var5);
/*    */   }
/*    */   
/*    */   public static class a extends StructureAbstract<WorldGenFeatureVillageConfiguration> {
/*    */     private final WorldGenFeatureJigsaw e;
/*    */     
/*    */     public a(WorldGenFeatureJigsaw var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 37 */       super(var0, var1, var2, var3, var4, var5);
/* 38 */       this.e = var0;
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureVillageConfiguration var6) {
/* 43 */       BlockPosition var7 = new BlockPosition(var3 * 16, WorldGenFeatureJigsaw.a(this.e), var4 * 16);
/*    */       
/* 45 */       WorldGenFeaturePieces.a();
/*    */       
/* 47 */       WorldGenFeatureDefinedStructureJigsawPlacement.a(var0, var6, WorldGenFeaturePillagerOutpostPoolPiece::new, var1, var2, var7, (List)this.b, this.d, WorldGenFeatureJigsaw.b(this.e), WorldGenFeatureJigsaw.c(this.e));
/*    */       
/* 49 */       b();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureJigsaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */