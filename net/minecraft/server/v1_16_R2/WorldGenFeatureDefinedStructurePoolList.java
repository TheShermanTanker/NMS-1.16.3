/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public class WorldGenFeatureDefinedStructurePoolList
/*    */   extends WorldGenFeatureDefinedStructurePoolStructure
/*    */ {
/*    */   public static final Codec<WorldGenFeatureDefinedStructurePoolList> a;
/*    */   private final List<WorldGenFeatureDefinedStructurePoolStructure> b;
/*    */   
/*    */   static {
/* 19 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenFeatureDefinedStructurePoolStructure.e.listOf().fieldOf("elements").forGetter(()), (App)d()).apply((Applicative)var0, WorldGenFeatureDefinedStructurePoolList::new));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenFeatureDefinedStructurePoolList(List<WorldGenFeatureDefinedStructurePoolStructure> var0, WorldGenFeatureDefinedStructurePoolTemplate.Matching var1) {
/* 27 */     super(var1);
/* 28 */     if (var0.isEmpty()) {
/* 29 */       throw new IllegalArgumentException("Elements are empty");
/*    */     }
/* 31 */     this.b = var0;
/* 32 */     b(var1);
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<DefinedStructure.BlockInfo> a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, Random var3) {
/* 52 */     return ((WorldGenFeatureDefinedStructurePoolStructure)this.b.get(0)).a(var0, var1, var2, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureBoundingBox a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2) {
/* 57 */     StructureBoundingBox var3 = StructureBoundingBox.a();
/* 58 */     for (WorldGenFeatureDefinedStructurePoolStructure var5 : this.b) {
/* 59 */       StructureBoundingBox var6 = var5.a(var0, var1, var2);
/* 60 */       var3.c(var6);
/*    */     } 
/*    */     
/* 63 */     return var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(DefinedStructureManager var0, GeneratorAccessSeed var1, StructureManager var2, ChunkGenerator var3, BlockPosition var4, BlockPosition var5, EnumBlockRotation var6, StructureBoundingBox var7, Random var8, boolean var9) {
/* 68 */     for (WorldGenFeatureDefinedStructurePoolStructure var11 : this.b) {
/* 69 */       if (!var11.a(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9)) {
/* 70 */         return false;
/*    */       }
/*    */     } 
/* 73 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenFeatureDefinedStructurePools<?> a() {
/* 78 */     return WorldGenFeatureDefinedStructurePools.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenFeatureDefinedStructurePoolStructure a(WorldGenFeatureDefinedStructurePoolTemplate.Matching var0) {
/* 83 */     super.a(var0);
/* 84 */     b(var0);
/* 85 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 90 */     return "List[" + (String)this.b.stream().map(Object::toString).collect(Collectors.joining(", ")) + "]";
/*    */   }
/*    */   
/*    */   private void b(WorldGenFeatureDefinedStructurePoolTemplate.Matching var0) {
/* 94 */     this.b.forEach(var1 -> var1.a(var0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDefinedStructurePoolList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */