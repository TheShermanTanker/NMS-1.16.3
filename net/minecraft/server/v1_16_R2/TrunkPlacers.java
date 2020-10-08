/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class TrunkPlacers<P extends TrunkPlacer>
/*    */ {
/*  7 */   public static final TrunkPlacers<TrunkPlacerStraight> a = a("straight_trunk_placer", TrunkPlacerStraight.a);
/*  8 */   public static final TrunkPlacers<TrunkPlacerForking> b = a("forking_trunk_placer", TrunkPlacerForking.a);
/*  9 */   public static final TrunkPlacers<TrunkPlacerGiant> c = a("giant_trunk_placer", TrunkPlacerGiant.a);
/* 10 */   public static final TrunkPlacers<TrunkPlacerMegaJungle> d = a("mega_jungle_trunk_placer", TrunkPlacerMegaJungle.b);
/* 11 */   public static final TrunkPlacers<TrunkPlacerDarkOak> e = a("dark_oak_trunk_placer", TrunkPlacerDarkOak.a);
/* 12 */   public static final TrunkPlacers<TrunkPlacerFancy> f = a("fancy_trunk_placer", TrunkPlacerFancy.a);
/*    */   
/*    */   private static <P extends TrunkPlacer> TrunkPlacers<P> a(String var0, Codec<P> var1) {
/* 15 */     return (TrunkPlacers<P>)IRegistry.<TrunkPlacers<?>>a(IRegistry.TRUNK_PLACER_TYPE, var0, new TrunkPlacers(var1));
/*    */   }
/*    */   
/*    */   private final Codec<P> g;
/*    */   
/*    */   private TrunkPlacers(Codec<P> var0) {
/* 21 */     this.g = var0;
/*    */   }
/*    */   
/*    */   public Codec<P> a() {
/* 25 */     return this.g;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TrunkPlacers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */