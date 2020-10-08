/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class WorldGenFeatureEndSpikeConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/* 13 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.BOOL.fieldOf("crystal_invulnerable").orElse(Boolean.valueOf(false)).forGetter(()), (App)WorldGenEnder.Spike.a.listOf().fieldOf("spikes").forGetter(()), (App)BlockPosition.a.optionalFieldOf("crystal_beam_target").forGetter(())).apply((Applicative)var0, WorldGenFeatureEndSpikeConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureEndSpikeConfiguration> a;
/*    */   
/*    */   private final boolean b;
/*    */   private final List<WorldGenEnder.Spike> c;
/*    */   @Nullable
/*    */   private final BlockPosition d;
/*    */   
/*    */   public WorldGenFeatureEndSpikeConfiguration(boolean var0, List<WorldGenEnder.Spike> var1, @Nullable BlockPosition var2) {
/* 25 */     this(var0, var1, Optional.ofNullable(var2));
/*    */   }
/*    */   
/*    */   private WorldGenFeatureEndSpikeConfiguration(boolean var0, List<WorldGenEnder.Spike> var1, Optional<BlockPosition> var2) {
/* 29 */     this.b = var0;
/* 30 */     this.c = var1;
/* 31 */     this.d = var2.orElse(null);
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 35 */     return this.b;
/*    */   }
/*    */   
/*    */   public List<WorldGenEnder.Spike> c() {
/* 39 */     return this.c;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public BlockPosition d() {
/* 44 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureEndSpikeConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */