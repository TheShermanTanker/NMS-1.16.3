/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Random;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ public class WorldGenBlockPlacerColumn extends WorldGenBlockPlacer {
/*    */   public static final Codec<WorldGenBlockPlacerColumn> b;
/*    */   
/*    */   static {
/* 14 */     b = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("min_size").forGetter(()), (App)Codec.INT.fieldOf("extra_size").forGetter(())).apply((Applicative)var0, WorldGenBlockPlacerColumn::new));
/*    */   }
/*    */ 
/*    */   
/*    */   private final int c;
/*    */   
/*    */   private final int d;
/*    */   
/*    */   public WorldGenBlockPlacerColumn(int var0, int var1) {
/* 23 */     this.c = var0;
/* 24 */     this.d = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenBlockPlacers<?> a() {
/* 29 */     return WorldGenBlockPlacers.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(GeneratorAccess var0, BlockPosition var1, IBlockData var2, Random var3) {
/* 34 */     BlockPosition.MutableBlockPosition var4 = var1.i();
/* 35 */     int var5 = this.c + var3.nextInt(var3.nextInt(this.d + 1) + 1);
/* 36 */     for (int var6 = 0; var6 < var5; var6++) {
/* 37 */       var0.setTypeAndData(var4, var2, 2);
/* 38 */       var4.c(EnumDirection.UP);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenBlockPlacerColumn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */