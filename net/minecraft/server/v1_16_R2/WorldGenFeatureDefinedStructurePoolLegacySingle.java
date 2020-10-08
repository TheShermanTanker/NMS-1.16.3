/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Either;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureDefinedStructurePoolLegacySingle
/*    */   extends WorldGenFeatureDefinedStructurePoolSingle
/*    */ {
/*    */   public static final Codec<WorldGenFeatureDefinedStructurePoolLegacySingle> a;
/*    */   
/*    */   static {
/* 21 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)c(), (App)b(), (App)d()).apply((Applicative)var0, WorldGenFeatureDefinedStructurePoolLegacySingle::new));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected WorldGenFeatureDefinedStructurePoolLegacySingle(Either<MinecraftKey, DefinedStructure> var0, Supplier<ProcessorList> var1, WorldGenFeatureDefinedStructurePoolTemplate.Matching var2) {
/* 28 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureInfo a(EnumBlockRotation var0, StructureBoundingBox var1, boolean var2) {
/* 33 */     DefinedStructureInfo var3 = super.a(var0, var1, var2);
/* 34 */     var3.b(DefinedStructureProcessorBlockIgnore.b);
/* 35 */     var3.a(DefinedStructureProcessorBlockIgnore.d);
/* 36 */     return var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenFeatureDefinedStructurePools<?> a() {
/* 41 */     return WorldGenFeatureDefinedStructurePools.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "LegacySingle[" + this.c + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDefinedStructurePoolLegacySingle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */