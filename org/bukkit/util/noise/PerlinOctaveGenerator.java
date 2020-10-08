/*    */ package org.bukkit.util.noise;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.World;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PerlinOctaveGenerator
/*    */   extends OctaveGenerator
/*    */ {
/*    */   public PerlinOctaveGenerator(@NotNull World world, int octaves) {
/* 19 */     this(new Random(world.getSeed()), octaves);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PerlinOctaveGenerator(long seed, int octaves) {
/* 29 */     this(new Random(seed), octaves);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PerlinOctaveGenerator(@NotNull Random rand, int octaves) {
/* 39 */     super(createOctaves(rand, octaves));
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   private static NoiseGenerator[] createOctaves(@NotNull Random rand, int octaves) {
/* 44 */     NoiseGenerator[] result = new NoiseGenerator[octaves];
/*    */     
/* 46 */     for (int i = 0; i < octaves; i++) {
/* 47 */       result[i] = new PerlinNoiseGenerator(rand);
/*    */     }
/*    */     
/* 50 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\noise\PerlinOctaveGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */