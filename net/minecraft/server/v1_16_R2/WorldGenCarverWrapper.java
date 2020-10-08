/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.BitSet;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenCarverWrapper<WC extends WorldGenCarverConfiguration>
/*    */ {
/*    */   public static final Codec<WorldGenCarverWrapper<?>> a;
/*    */   
/*    */   static {
/* 17 */     a = IRegistry.CARVER.dispatch(var0 -> var0.d, WorldGenCarverAbstract::c);
/*    */   }
/* 19 */   public static final Codec<Supplier<WorldGenCarverWrapper<?>>> b = RegistryFileCodec.a(IRegistry.at, a);
/* 20 */   public static final Codec<List<Supplier<WorldGenCarverWrapper<?>>>> c = RegistryFileCodec.b(IRegistry.at, a);
/*    */   
/*    */   private final WorldGenCarverAbstract<WC> d;
/*    */   private final WC e;
/*    */   
/*    */   public WorldGenCarverWrapper(WorldGenCarverAbstract<WC> var0, WC var1) {
/* 26 */     this.d = var0;
/* 27 */     this.e = var1;
/*    */   }
/*    */   
/*    */   public WC a() {
/* 31 */     return this.e;
/*    */   }
/*    */   
/*    */   public boolean a(Random var0, int var1, int var2) {
/* 35 */     return this.d.a(var0, var1, var2, this.e);
/*    */   }
/*    */   
/*    */   public boolean a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, Random var2, int var3, int var4, int var5, int var6, int var7, BitSet var8) {
/* 39 */     return this.d.a(var0, var1, var2, var3, var4, var5, var6, var7, var8, this.e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenCarverWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */