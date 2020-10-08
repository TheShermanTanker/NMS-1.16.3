/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenFeatureReplaceBlockConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IBlockData.b.fieldOf("target").forGetter(()), (App)IBlockData.b.fieldOf("state").forGetter(())).apply((Applicative)var0, WorldGenFeatureReplaceBlockConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureReplaceBlockConfiguration> a;
/*    */   public final IBlockData b;
/*    */   public final IBlockData c;
/*    */   
/*    */   public WorldGenFeatureReplaceBlockConfiguration(IBlockData var0, IBlockData var1) {
/* 17 */     this.b = var0;
/* 18 */     this.c = var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureReplaceBlockConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */