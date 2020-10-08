/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenBlockPlacers<P extends WorldGenBlockPlacer>
/*    */ {
/*  7 */   public static final WorldGenBlockPlacers<WorldGenBlockPlacerSimple> a = a("simple_block_placer", WorldGenBlockPlacerSimple.b);
/*  8 */   public static final WorldGenBlockPlacers<WorldGenBlockPlacerDoublePlant> b = a("double_plant_placer", WorldGenBlockPlacerDoublePlant.b);
/*  9 */   public static final WorldGenBlockPlacers<WorldGenBlockPlacerColumn> c = a("column_placer", WorldGenBlockPlacerColumn.b);
/*    */   
/*    */   private static <P extends WorldGenBlockPlacer> WorldGenBlockPlacers<P> a(String var0, Codec<P> var1) {
/* 12 */     return (WorldGenBlockPlacers<P>)IRegistry.<WorldGenBlockPlacers<?>>a(IRegistry.BLOCK_PLACER_TYPE, var0, new WorldGenBlockPlacers(var1));
/*    */   }
/*    */   
/*    */   private final Codec<P> d;
/*    */   
/*    */   private WorldGenBlockPlacers(Codec<P> var0) {
/* 18 */     this.d = var0;
/*    */   }
/*    */   
/*    */   public Codec<P> a() {
/* 22 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenBlockPlacers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */