/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureDefinedStructurePoolFeature
/*    */   extends WorldGenFeatureDefinedStructurePoolStructure
/*    */ {
/*    */   public static final Codec<WorldGenFeatureDefinedStructurePoolFeature> a;
/*    */   private final Supplier<WorldGenFeatureConfigured<?, ?>> b;
/*    */   private final NBTTagCompound c;
/*    */   
/*    */   static {
/* 28 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenFeatureConfigured.b.fieldOf("feature").forGetter(()), (App)d()).apply((Applicative)var0, WorldGenFeatureDefinedStructurePoolFeature::new));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected WorldGenFeatureDefinedStructurePoolFeature(Supplier<WorldGenFeatureConfigured<?, ?>> var0, WorldGenFeatureDefinedStructurePoolTemplate.Matching var1) {
/* 37 */     super(var1);
/* 38 */     this.b = var0;
/* 39 */     this.c = b();
/*    */   }
/*    */   
/*    */   private NBTTagCompound b() {
/* 43 */     NBTTagCompound var0 = new NBTTagCompound();
/* 44 */     var0.setString("name", "minecraft:bottom");
/* 45 */     var0.setString("final_state", "minecraft:air");
/*    */ 
/*    */     
/* 48 */     var0.setString("pool", "minecraft:empty");
/* 49 */     var0.setString("target", "minecraft:empty");
/* 50 */     var0.setString("joint", TileEntityJigsaw.JointType.ROLLABLE.getName());
/*    */     
/* 52 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPosition a(DefinedStructureManager var0, EnumBlockRotation var1) {
/* 57 */     return BlockPosition.ZERO;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<DefinedStructure.BlockInfo> a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, Random var3) {
/* 62 */     List<DefinedStructure.BlockInfo> var4 = Lists.newArrayList();
/* 63 */     var4.add(new DefinedStructure.BlockInfo(var1, Blocks.JIGSAW.getBlockData().set(BlockJigsaw.a, BlockPropertyJigsawOrientation.a(EnumDirection.DOWN, EnumDirection.SOUTH)), this.c));
/* 64 */     return var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureBoundingBox a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2) {
/* 69 */     BlockPosition var3 = a(var0, var2);
/* 70 */     return new StructureBoundingBox(var1.getX(), var1.getY(), var1.getZ(), var1.getX() + var3.getX(), var1.getY() + var3.getY(), var1.getZ() + var3.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(DefinedStructureManager var0, GeneratorAccessSeed var1, StructureManager var2, ChunkGenerator var3, BlockPosition var4, BlockPosition var5, EnumBlockRotation var6, StructureBoundingBox var7, Random var8, boolean var9) {
/* 75 */     return ((WorldGenFeatureConfigured)this.b.get()).a(var1, var3, var8, var4);
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenFeatureDefinedStructurePools<?> a() {
/* 80 */     return WorldGenFeatureDefinedStructurePools.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 85 */     return "Feature[" + IRegistry.FEATURE.getKey((WorldGenerator<?>)((WorldGenFeatureConfigured)this.b.get()).b()) + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDefinedStructurePoolFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */