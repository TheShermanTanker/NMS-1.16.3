/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenSurfaceBasaltDeltas
/*    */   extends WorldGenSurfaceNetherAbstract
/*    */ {
/*  9 */   private static final IBlockData a = Blocks.BASALT.getBlockData();
/* 10 */   private static final IBlockData b = Blocks.BLACKSTONE.getBlockData();
/* 11 */   private static final IBlockData c = Blocks.GRAVEL.getBlockData();
/*    */   
/* 13 */   private static final ImmutableList<IBlockData> d = ImmutableList.of(a, b);
/* 14 */   private static final ImmutableList<IBlockData> e = ImmutableList.of(a);
/*    */   
/*    */   public WorldGenSurfaceBasaltDeltas(Codec<WorldGenSurfaceConfigurationBase> var0) {
/* 17 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ImmutableList<IBlockData> a() {
/* 22 */     return d;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ImmutableList<IBlockData> b() {
/* 27 */     return e;
/*    */   }
/*    */ 
/*    */   
/*    */   protected IBlockData c() {
/* 32 */     return c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceBasaltDeltas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */