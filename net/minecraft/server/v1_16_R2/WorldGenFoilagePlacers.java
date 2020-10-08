/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenFoilagePlacers<P extends WorldGenFoilagePlacer>
/*    */ {
/*  7 */   public static final WorldGenFoilagePlacers<WorldGenFoilagePlacerBlob> a = a("blob_foliage_placer", WorldGenFoilagePlacerBlob.a);
/*  8 */   public static final WorldGenFoilagePlacers<WorldGenFoilagePlacerSpruce> b = a("spruce_foliage_placer", WorldGenFoilagePlacerSpruce.a);
/*  9 */   public static final WorldGenFoilagePlacers<WorldGenFoilagePlacerPine> c = a("pine_foliage_placer", WorldGenFoilagePlacerPine.a);
/* 10 */   public static final WorldGenFoilagePlacers<WorldGenFoilagePlacerAcacia> d = a("acacia_foliage_placer", WorldGenFoilagePlacerAcacia.a);
/* 11 */   public static final WorldGenFoilagePlacers<WorldGenFoilagePlacerBush> e = a("bush_foliage_placer", WorldGenFoilagePlacerBush.c);
/* 12 */   public static final WorldGenFoilagePlacers<WorldGenFoilagePlacerFancy> f = a("fancy_foliage_placer", WorldGenFoilagePlacerFancy.c);
/* 13 */   public static final WorldGenFoilagePlacers<WorldGenFoilagePlacerJungle> g = a("jungle_foliage_placer", WorldGenFoilagePlacerJungle.a);
/* 14 */   public static final WorldGenFoilagePlacers<WorldGenFoilagePlacerMegaPine> h = a("mega_pine_foliage_placer", WorldGenFoilagePlacerMegaPine.a);
/* 15 */   public static final WorldGenFoilagePlacers<WorldGenFoilagePlacerDarkOak> i = a("dark_oak_foliage_placer", WorldGenFoilagePlacerDarkOak.a);
/*    */   
/*    */   private static <P extends WorldGenFoilagePlacer> WorldGenFoilagePlacers<P> a(String var0, Codec<P> var1) {
/* 18 */     return (WorldGenFoilagePlacers<P>)IRegistry.<WorldGenFoilagePlacers<?>>a(IRegistry.FOLIAGE_PLACER_TYPE, var0, new WorldGenFoilagePlacers(var1));
/*    */   }
/*    */   
/*    */   private final Codec<P> j;
/*    */   
/*    */   private WorldGenFoilagePlacers(Codec<P> var0) {
/* 24 */     this.j = var0;
/*    */   }
/*    */   
/*    */   public Codec<P> a() {
/* 28 */     return this.j;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */