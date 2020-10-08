/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class WorldChunkManagerCheckerBoard extends WorldChunkManager {
/*    */   static {
/* 10 */     e = RecordCodecBuilder.create(var0 -> var0.group((App)BiomeBase.e.fieldOf("biomes").forGetter(()), (App)Codec.intRange(0, 62).fieldOf("scale").orElse(Integer.valueOf(2)).forGetter(())).apply((Applicative)var0, WorldChunkManagerCheckerBoard::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldChunkManagerCheckerBoard> e;
/*    */   private final List<Supplier<BiomeBase>> f;
/*    */   private final int g;
/*    */   private final int h;
/*    */   
/*    */   public WorldChunkManagerCheckerBoard(List<Supplier<BiomeBase>> var0, int var1) {
/* 20 */     super(var0.stream());
/* 21 */     this.f = var0;
/* 22 */     this.g = var1 + 2;
/* 23 */     this.h = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Codec<? extends WorldChunkManager> a() {
/* 28 */     return (Codec)e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BiomeBase getBiome(int var0, int var1, int var2) {
/* 38 */     return ((Supplier<BiomeBase>)this.f.get(Math.floorMod((var0 >> this.g) + (var2 >> this.g), this.f.size()))).get();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldChunkManagerCheckerBoard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */