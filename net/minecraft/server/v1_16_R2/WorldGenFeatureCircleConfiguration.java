/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function4;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ 
/*    */ public class WorldGenFeatureCircleConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/* 11 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IBlockData.b.fieldOf("state").forGetter(()), (App)IntSpread.a(0, 4, 4).fieldOf("radius").forGetter(()), (App)Codec.intRange(0, 4).fieldOf("half_height").forGetter(()), (App)IBlockData.b.listOf().fieldOf("targets").forGetter(())).apply((Applicative)var0, WorldGenFeatureCircleConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureCircleConfiguration> a;
/*    */   
/*    */   public final IBlockData b;
/*    */   
/*    */   public final IntSpread c;
/*    */   public final int d;
/*    */   public final List<IBlockData> e;
/*    */   
/*    */   public WorldGenFeatureCircleConfiguration(IBlockData var0, IntSpread var1, int var2, List<IBlockData> var3) {
/* 24 */     this.b = var0;
/* 25 */     this.c = var1;
/* 26 */     this.d = var2;
/* 27 */     this.e = var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureCircleConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */