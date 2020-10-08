/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenSurfaceComposite<SC extends WorldGenSurfaceConfiguration>
/*    */ {
/*    */   public static final Codec<WorldGenSurfaceComposite<?>> a;
/*    */   
/*    */   static {
/* 14 */     a = IRegistry.SURFACE_BUILDER.dispatch(var0 -> var0.c, WorldGenSurface::d);
/*    */   }
/* 16 */   public static final Codec<Supplier<WorldGenSurfaceComposite<?>>> b = RegistryFileCodec.a(IRegistry.as, a);
/*    */   
/*    */   public final WorldGenSurface<SC> c;
/*    */   public final SC d;
/*    */   
/*    */   public WorldGenSurfaceComposite(WorldGenSurface<SC> var0, SC var1) {
/* 22 */     this.c = var0;
/* 23 */     this.d = var1;
/*    */   }
/*    */   
/*    */   public void a(Random var0, IChunkAccess var1, BiomeBase var2, int var3, int var4, int var5, double var6, IBlockData var8, IBlockData var9, int var10, long var11) {
/* 27 */     this.c.a(var0, var1, var2, var3, var4, var5, var6, var8, var9, var10, var11, this.d);
/*    */   }
/*    */   
/*    */   public void a(long var0) {
/* 31 */     this.c.a(var0);
/*    */   }
/*    */   
/*    */   public SC a() {
/* 35 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceComposite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */