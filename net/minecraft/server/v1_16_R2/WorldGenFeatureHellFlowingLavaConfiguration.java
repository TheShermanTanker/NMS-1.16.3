/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function5;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class WorldGenFeatureHellFlowingLavaConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/* 14 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Fluid.a.fieldOf("state").forGetter(()), (App)Codec.BOOL.fieldOf("requires_block_below").orElse(Boolean.valueOf(true)).forGetter(()), (App)Codec.INT.fieldOf("rock_count").orElse(Integer.valueOf(4)).forGetter(()), (App)Codec.INT.fieldOf("hole_count").orElse(Integer.valueOf(1)).forGetter(()), (App)IRegistry.BLOCK.listOf().fieldOf("valid_blocks").xmap(ImmutableSet::copyOf, ImmutableList::copyOf).forGetter(())).apply((Applicative)var0, WorldGenFeatureHellFlowingLavaConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureHellFlowingLavaConfiguration> a;
/*    */   
/*    */   public final Fluid b;
/*    */   
/*    */   public final boolean c;
/*    */   
/*    */   public final int d;
/*    */   
/*    */   public final int e;
/*    */   
/*    */   public final Set<Block> f;
/*    */   
/*    */   public WorldGenFeatureHellFlowingLavaConfiguration(Fluid var0, boolean var1, int var2, int var3, Set<Block> var4) {
/* 31 */     this.b = var0;
/* 32 */     this.c = var1;
/* 33 */     this.d = var2;
/* 34 */     this.e = var3;
/* 35 */     this.f = var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureHellFlowingLavaConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */