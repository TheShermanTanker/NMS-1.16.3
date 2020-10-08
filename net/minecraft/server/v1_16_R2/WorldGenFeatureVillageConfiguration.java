/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class WorldGenFeatureVillageConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/* 10 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenFeatureDefinedStructurePoolTemplate.b.fieldOf("start_pool").forGetter(WorldGenFeatureVillageConfiguration::c), (App)Codec.intRange(0, 7).fieldOf("size").forGetter(WorldGenFeatureVillageConfiguration::b)).apply((Applicative)var0, WorldGenFeatureVillageConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureVillageConfiguration> a;
/*    */   private final Supplier<WorldGenFeatureDefinedStructurePoolTemplate> b;
/*    */   private final int c;
/*    */   
/*    */   public WorldGenFeatureVillageConfiguration(Supplier<WorldGenFeatureDefinedStructurePoolTemplate> var0, int var1) {
/* 19 */     this.b = var0;
/* 20 */     this.c = var1;
/*    */   }
/*    */   
/*    */   public int b() {
/* 24 */     return this.c;
/*    */   }
/*    */   
/*    */   public Supplier<WorldGenFeatureDefinedStructurePoolTemplate> c() {
/* 28 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureVillageConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */