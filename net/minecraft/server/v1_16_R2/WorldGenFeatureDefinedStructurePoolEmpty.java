/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureDefinedStructurePoolEmpty
/*    */   extends WorldGenFeatureDefinedStructurePoolStructure
/*    */ {
/* 18 */   public static final Codec<WorldGenFeatureDefinedStructurePoolEmpty> a = Codec.unit(() -> b);
/*    */   
/* 20 */   public static final WorldGenFeatureDefinedStructurePoolEmpty b = new WorldGenFeatureDefinedStructurePoolEmpty();
/*    */   
/*    */   private WorldGenFeatureDefinedStructurePoolEmpty() {
/* 23 */     super(WorldGenFeatureDefinedStructurePoolTemplate.Matching.TERRAIN_MATCHING);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<DefinedStructure.BlockInfo> a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, Random var3) {
/* 33 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureBoundingBox a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2) {
/* 38 */     return StructureBoundingBox.a();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(DefinedStructureManager var0, GeneratorAccessSeed var1, StructureManager var2, ChunkGenerator var3, BlockPosition var4, BlockPosition var5, EnumBlockRotation var6, StructureBoundingBox var7, Random var8, boolean var9) {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenFeatureDefinedStructurePools<?> a() {
/* 48 */     return WorldGenFeatureDefinedStructurePools.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return "Empty";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDefinedStructurePoolEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */