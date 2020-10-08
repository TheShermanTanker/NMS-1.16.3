/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
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
/*    */ 
/*    */ public class WorldGenFeatureSwampHut
/*    */   extends StructureGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/* 19 */   private static final List<BiomeSettingsMobs.c> u = (List<BiomeSettingsMobs.c>)ImmutableList.of(new BiomeSettingsMobs.c(EntityTypes.WITCH, 1, 1, 1));
/* 20 */   private static final List<BiomeSettingsMobs.c> v = (List<BiomeSettingsMobs.c>)ImmutableList.of(new BiomeSettingsMobs.c(EntityTypes.CAT, 1, 1, 1));
/*    */   
/*    */   public WorldGenFeatureSwampHut(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 23 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureEmptyConfiguration> a() {
/* 28 */     return a::new;
/*    */   }
/*    */   
/*    */   public static class a extends StructureStart<WorldGenFeatureEmptyConfiguration> {
/*    */     public a(StructureGenerator<WorldGenFeatureEmptyConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 33 */       super(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureEmptyConfiguration var6) {
/* 38 */       WorldGenWitchHut var7 = new WorldGenWitchHut(this.d, var3 * 16, var4 * 16);
/* 39 */       this.b.add(var7);
/* 40 */       b();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public List<BiomeSettingsMobs.c> c() {
/* 46 */     return u;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<BiomeSettingsMobs.c> j() {
/* 51 */     return v;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureSwampHut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */