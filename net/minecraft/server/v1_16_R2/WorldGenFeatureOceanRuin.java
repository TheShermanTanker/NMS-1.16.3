/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureOceanRuin
/*    */   extends StructureGenerator<WorldGenFeatureOceanRuinConfiguration>
/*    */ {
/*    */   public WorldGenFeatureOceanRuin(Codec<WorldGenFeatureOceanRuinConfiguration> var0) {
/* 21 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureOceanRuinConfiguration> a() {
/* 26 */     return a::new;
/*    */   }
/*    */   
/*    */   public static class a extends StructureStart<WorldGenFeatureOceanRuinConfiguration> {
/*    */     public a(StructureGenerator<WorldGenFeatureOceanRuinConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 31 */       super(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureOceanRuinConfiguration var6) {
/* 36 */       int var7 = var3 * 16;
/* 37 */       int var8 = var4 * 16;
/*    */       
/* 39 */       BlockPosition var9 = new BlockPosition(var7, 90, var8);
/* 40 */       EnumBlockRotation var10 = EnumBlockRotation.a(this.d);
/* 41 */       WorldGenFeatureOceanRuinPieces.a(var2, var9, var10, this.b, this.d, var6);
/* 42 */       b();
/*    */     }
/*    */   }
/*    */   
/*    */   public enum Temperature implements INamable {
/* 47 */     WARM("warm"),
/* 48 */     COLD("cold");
/*    */ 
/*    */     
/* 51 */     public static final Codec<Temperature> c = INamable.a(Temperature::values, Temperature::a); private static final Map<String, Temperature> d;
/*    */     static {
/* 53 */       d = (Map<String, Temperature>)Arrays.<Temperature>stream(values()).collect(Collectors.toMap(Temperature::b, var0 -> var0));
/*    */     }
/*    */     private final String e;
/*    */     Temperature(String var2) {
/* 57 */       this.e = var2;
/*    */     }
/*    */     
/*    */     public String b() {
/* 61 */       return this.e;
/*    */     }
/*    */     
/*    */     @Nullable
/*    */     public static Temperature a(String var0) {
/* 66 */       return d.get(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public String getName() {
/* 71 */       return this.e;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureOceanRuin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */