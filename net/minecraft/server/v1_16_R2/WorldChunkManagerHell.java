/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.function.Supplier;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class WorldChunkManagerHell extends WorldChunkManager {
/*    */   public static final Codec<WorldChunkManagerHell> e;
/*    */   
/*    */   static {
/* 15 */     e = BiomeBase.d.fieldOf("biome").xmap(WorldChunkManagerHell::new, var0 -> var0.f).stable().codec();
/*    */   }
/*    */   private final Supplier<BiomeBase> f;
/*    */   
/*    */   public WorldChunkManagerHell(BiomeBase var0) {
/* 20 */     this(() -> var0);
/*    */   }
/*    */   
/*    */   public WorldChunkManagerHell(Supplier<BiomeBase> var0) {
/* 24 */     super((List<BiomeBase>)ImmutableList.of(var0.get()));
/* 25 */     this.f = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Codec<? extends WorldChunkManager> a() {
/* 30 */     return (Codec)e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BiomeBase getBiome(int var0, int var1, int var2) {
/* 40 */     return this.f.get();
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BlockPosition a(int var0, int var1, int var2, int var3, int var4, Predicate<BiomeBase> var5, Random var6, boolean var7) {
/* 46 */     if (var5.test(this.f.get())) {
/* 47 */       if (var7) {
/* 48 */         return new BlockPosition(var0, var1, var2);
/*    */       }
/* 50 */       return new BlockPosition(var0 - var3 + var6.nextInt(var3 * 2 + 1), var1, var2 - var3 + var6.nextInt(var3 * 2 + 1));
/*    */     } 
/*    */     
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BiomeBase> a(int var0, int var1, int var2, int var3) {
/* 58 */     return Sets.newHashSet((Object[])new BiomeBase[] { this.f.get() });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldChunkManagerHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */