/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenFeatureOreConfiguration implements WorldGenFeatureConfiguration {
/*    */   public static final Codec<WorldGenFeatureOreConfiguration> a;
/*    */   
/*    */   static {
/* 13 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)DefinedStructureRuleTest.c.fieldOf("target").forGetter(()), (App)IBlockData.b.fieldOf("state").forGetter(()), (App)Codec.intRange(0, 64).fieldOf("size").forGetter(())).apply((Applicative)var0, WorldGenFeatureOreConfiguration::new));
/*    */   }
/*    */   
/*    */   public final DefinedStructureRuleTest b;
/*    */   public final int c;
/*    */   public final IBlockData d;
/*    */   
/* 20 */   public static final class Target { public static final DefinedStructureRuleTest NATURAL_STONE = new DefinedStructureTestTag(TagsBlock.aH);
/* 21 */     public static final DefinedStructureRuleTest NETHERRACK = new DefinedStructureTestBlock(Blocks.NETHERRACK);
/* 22 */     public static final DefinedStructureRuleTest NETHER_ORE_REPLACEABLES = new DefinedStructureTestTag(TagsBlock.aI); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenFeatureOreConfiguration(DefinedStructureRuleTest var0, IBlockData var1, int var2) {
/* 30 */     this.c = var2;
/* 31 */     this.d = var1;
/* 32 */     this.b = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureOreConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */