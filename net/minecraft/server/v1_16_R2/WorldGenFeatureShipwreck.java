/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureShipwreck
/*    */   extends StructureGenerator<WorldGenFeatureShipwreckConfiguration>
/*    */ {
/*    */   public WorldGenFeatureShipwreck(Codec<WorldGenFeatureShipwreckConfiguration> var0) {
/* 17 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureShipwreckConfiguration> a() {
/* 22 */     return a::new;
/*    */   }
/*    */   
/*    */   public static class a extends StructureStart<WorldGenFeatureShipwreckConfiguration> {
/*    */     public a(StructureGenerator<WorldGenFeatureShipwreckConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 27 */       super(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureShipwreckConfiguration var6) {
/* 32 */       EnumBlockRotation var7 = EnumBlockRotation.a(this.d);
/* 33 */       BlockPosition var8 = new BlockPosition(var3 * 16, 90, var4 * 16);
/*    */       
/* 35 */       WorldGenShipwreck.a(var2, var8, var7, this.b, this.d, var6);
/* 36 */       b();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureShipwreck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */