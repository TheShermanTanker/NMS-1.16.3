/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
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
/*    */ public class StructureFeature<FC extends WorldGenFeatureConfiguration, F extends StructureGenerator<FC>>
/*    */ {
/*    */   public static final Codec<StructureFeature<?, ?>> a;
/*    */   
/*    */   static {
/* 21 */     a = IRegistry.STRUCTURE_FEATURE.dispatch(var0 -> var0.d, StructureGenerator::h);
/*    */   }
/* 23 */   public static final Codec<Supplier<StructureFeature<?, ?>>> b = RegistryFileCodec.a(IRegistry.av, a);
/* 24 */   public static final Codec<List<Supplier<StructureFeature<?, ?>>>> c = RegistryFileCodec.b(IRegistry.av, a);
/*    */   
/*    */   public final F d;
/*    */   public final FC e;
/*    */   
/*    */   public StructureFeature(F var0, FC var1) {
/* 30 */     this.d = var0;
/* 31 */     this.e = var1;
/*    */   }
/*    */   
/*    */   public StructureStart<?> a(IRegistryCustom var0, ChunkGenerator var1, WorldChunkManager var2, DefinedStructureManager var3, long var4, ChunkCoordIntPair var6, BiomeBase var7, int var8, StructureSettingsFeature var9) {
/* 35 */     return this.d.a(var0, var1, var2, var3, var4, var6, var7, var8, new SeededRandom(), var9, this.e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructureFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */