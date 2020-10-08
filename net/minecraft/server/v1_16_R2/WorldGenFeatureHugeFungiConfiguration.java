/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function5;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenFeatureHugeFungiConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/* 10 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IBlockData.b.fieldOf("valid_base_block").forGetter(()), (App)IBlockData.b.fieldOf("stem_state").forGetter(()), (App)IBlockData.b.fieldOf("hat_state").forGetter(()), (App)IBlockData.b.fieldOf("decor_state").forGetter(()), (App)Codec.BOOL.fieldOf("planted").orElse(Boolean.valueOf(false)).forGetter(())).apply((Applicative)var0, WorldGenFeatureHugeFungiConfiguration::new));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureHugeFungiConfiguration> a;
/*    */ 
/*    */   
/* 18 */   public static final WorldGenFeatureHugeFungiConfiguration CRIMSON_FUNGUS = new WorldGenFeatureHugeFungiConfiguration(Blocks.CRIMSON_NYLIUM.getBlockData(), Blocks.CRIMSON_STEM.getBlockData(), Blocks.NETHER_WART_BLOCK.getBlockData(), Blocks.SHROOMLIGHT.getBlockData(), true);
/* 19 */   public static final WorldGenFeatureHugeFungiConfiguration c = new WorldGenFeatureHugeFungiConfiguration(CRIMSON_FUNGUS.f, CRIMSON_FUNGUS.g, CRIMSON_FUNGUS.h, CRIMSON_FUNGUS.i, false);
/* 20 */   public static final WorldGenFeatureHugeFungiConfiguration WARPED_FUNGUS = new WorldGenFeatureHugeFungiConfiguration(Blocks.WARPED_NYLIUM.getBlockData(), Blocks.WARPED_STEM.getBlockData(), Blocks.WARPED_WART_BLOCK.getBlockData(), Blocks.SHROOMLIGHT.getBlockData(), true);
/* 21 */   public static final WorldGenFeatureHugeFungiConfiguration e = new WorldGenFeatureHugeFungiConfiguration(WARPED_FUNGUS.f, WARPED_FUNGUS.g, WARPED_FUNGUS.h, WARPED_FUNGUS.i, false);
/*    */   
/*    */   public final IBlockData f;
/*    */   public final IBlockData g;
/*    */   public final IBlockData h;
/*    */   public final IBlockData i;
/*    */   public final boolean j;
/*    */   
/*    */   public WorldGenFeatureHugeFungiConfiguration(IBlockData var0, IBlockData var1, IBlockData var2, IBlockData var3, boolean var4) {
/* 30 */     this.f = var0;
/* 31 */     this.g = var1;
/* 32 */     this.h = var2;
/* 33 */     this.i = var3;
/* 34 */     this.j = var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureHugeFungiConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */