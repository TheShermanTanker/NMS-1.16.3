/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenFeatureFillConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/*  9 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.intRange(0, 255).fieldOf("height").forGetter(()), (App)IBlockData.b.fieldOf("state").forGetter(())).apply((Applicative)var0, WorldGenFeatureFillConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureFillConfiguration> a;
/*    */   public final int b;
/*    */   public final IBlockData c;
/*    */   
/*    */   public WorldGenFeatureFillConfiguration(int var0, IBlockData var1) {
/* 18 */     this.b = var0;
/* 19 */     this.c = var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureFillConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */