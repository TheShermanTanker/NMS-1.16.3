/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenFeatureRadiusConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/*  9 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IBlockData.b.fieldOf("target").forGetter(()), (App)IBlockData.b.fieldOf("state").forGetter(()), (App)IntSpread.a.fieldOf("radius").forGetter(())).apply((Applicative)var0, WorldGenFeatureRadiusConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureRadiusConfiguration> a;
/*    */   
/*    */   public final IBlockData b;
/*    */   
/*    */   public final IBlockData c;
/*    */   private final IntSpread d;
/*    */   
/*    */   public WorldGenFeatureRadiusConfiguration(IBlockData var0, IBlockData var1, IntSpread var2) {
/* 21 */     this.b = var0;
/* 22 */     this.c = var1;
/* 23 */     this.d = var2;
/*    */   }
/*    */   
/*    */   public IntSpread b() {
/* 27 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRadiusConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */