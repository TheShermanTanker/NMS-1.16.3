/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function4;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ 
/*    */ public class WorldGenFeatureBlockConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/* 10 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IBlockData.b.fieldOf("to_place").forGetter(()), (App)IBlockData.b.listOf().fieldOf("place_on").forGetter(()), (App)IBlockData.b.listOf().fieldOf("place_in").forGetter(()), (App)IBlockData.b.listOf().fieldOf("place_under").forGetter(())).apply((Applicative)var0, WorldGenFeatureBlockConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureBlockConfiguration> a;
/*    */   
/*    */   public final IBlockData b;
/*    */   
/*    */   public final List<IBlockData> c;
/*    */   public final List<IBlockData> d;
/*    */   public final List<IBlockData> e;
/*    */   
/*    */   public WorldGenFeatureBlockConfiguration(IBlockData var0, List<IBlockData> var1, List<IBlockData> var2, List<IBlockData> var3) {
/* 23 */     this.b = var0;
/* 24 */     this.c = var1;
/* 25 */     this.d = var2;
/* 26 */     this.e = var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureBlockConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */