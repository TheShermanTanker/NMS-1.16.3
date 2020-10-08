/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenSurfaceSoulSandValley
/*    */   extends WorldGenSurfaceNetherAbstract
/*    */ {
/*  9 */   private static final IBlockData a = Blocks.SOUL_SAND.getBlockData();
/* 10 */   private static final IBlockData b = Blocks.SOUL_SOIL.getBlockData();
/* 11 */   private static final IBlockData c = Blocks.GRAVEL.getBlockData();
/*    */   
/* 13 */   private static final ImmutableList<IBlockData> d = ImmutableList.of(a, b);
/*    */   
/*    */   public WorldGenSurfaceSoulSandValley(Codec<WorldGenSurfaceConfigurationBase> var0) {
/* 16 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ImmutableList<IBlockData> a() {
/* 21 */     return d;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ImmutableList<IBlockData> b() {
/* 26 */     return d;
/*    */   }
/*    */ 
/*    */   
/*    */   protected IBlockData c() {
/* 31 */     return c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceSoulSandValley.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */