/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenFeatureTrees<P extends WorldGenFeatureTree>
/*    */ {
/*  7 */   public static final WorldGenFeatureTrees<WorldGenFeatureTreeVineTrunk> a = a("trunk_vine", WorldGenFeatureTreeVineTrunk.a);
/*  8 */   public static final WorldGenFeatureTrees<WorldGenFeatureTreeVineLeaves> b = a("leave_vine", WorldGenFeatureTreeVineLeaves.a);
/*  9 */   public static final WorldGenFeatureTrees<WorldGenFeatureTreeCocoa> c = a("cocoa", WorldGenFeatureTreeCocoa.a);
/* 10 */   public static final WorldGenFeatureTrees<WorldGenFeatureTreeBeehive> d = a("beehive", WorldGenFeatureTreeBeehive.a);
/* 11 */   public static final WorldGenFeatureTrees<WorldGenFeatureTreeAlterGround> e = a("alter_ground", WorldGenFeatureTreeAlterGround.a);
/*    */   
/*    */   private static <P extends WorldGenFeatureTree> WorldGenFeatureTrees<P> a(String var0, Codec<P> var1) {
/* 14 */     return (WorldGenFeatureTrees<P>)IRegistry.<WorldGenFeatureTrees<?>>a(IRegistry.TREE_DECORATOR_TYPE, var0, new WorldGenFeatureTrees(var1));
/*    */   }
/*    */   
/*    */   private final Codec<P> f;
/*    */   
/*    */   private WorldGenFeatureTrees(Codec<P> var0) {
/* 20 */     this.f = var0;
/*    */   }
/*    */   
/*    */   public Codec<P> a() {
/* 24 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */