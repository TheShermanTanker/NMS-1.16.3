/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function4;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenFeatureDeltaConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/*  9 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IBlockData.b.fieldOf("contents").forGetter(()), (App)IBlockData.b.fieldOf("rim").forGetter(()), (App)IntSpread.a(0, 8, 8).fieldOf("size").forGetter(()), (App)IntSpread.a(0, 8, 8).fieldOf("rim_size").forGetter(())).apply((Applicative)var0, WorldGenFeatureDeltaConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureDeltaConfiguration> a;
/*    */   
/*    */   private final IBlockData b;
/*    */   
/*    */   private final IBlockData c;
/*    */   private final IntSpread d;
/*    */   private final IntSpread e;
/*    */   
/*    */   public WorldGenFeatureDeltaConfiguration(IBlockData var0, IBlockData var1, IntSpread var2, IntSpread var3) {
/* 22 */     this.b = var0;
/* 23 */     this.c = var1;
/* 24 */     this.d = var2;
/* 25 */     this.e = var3;
/*    */   }
/*    */   
/*    */   public IBlockData b() {
/* 29 */     return this.b;
/*    */   }
/*    */   
/*    */   public IBlockData c() {
/* 33 */     return this.c;
/*    */   }
/*    */   
/*    */   public IntSpread d() {
/* 37 */     return this.d;
/*    */   }
/*    */   
/*    */   public IntSpread e() {
/* 41 */     return this.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDeltaConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */