/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.Products;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class WorldGenFoilagePlacer
/*     */ {
/*  19 */   public static final Codec<WorldGenFoilagePlacer> d = IRegistry.FOLIAGE_PLACER_TYPE.dispatch(WorldGenFoilagePlacer::a, WorldGenFoilagePlacers::a);
/*     */   
/*     */   protected final IntSpread e;
/*     */   protected final IntSpread f;
/*     */   
/*     */   protected static <P extends WorldGenFoilagePlacer> Products.P2<RecordCodecBuilder.Mu<P>, IntSpread, IntSpread> b(RecordCodecBuilder.Instance<P> var0) {
/*  25 */     return var0.group(
/*  26 */         (App)IntSpread.a(0, 8, 8).fieldOf("radius").forGetter(var0 -> var0.e), 
/*  27 */         (App)IntSpread.a(0, 8, 8).fieldOf("offset").forGetter(var0 -> var0.f));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenFoilagePlacer(IntSpread var0, IntSpread var1) {
/*  36 */     this.e = var0;
/*  37 */     this.f = var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, int var3, b var4, int var5, int var6, Set<BlockPosition> var7, StructureBoundingBox var8) {
/*  43 */     a(var0, var1, var2, var3, var4, var5, var6, var7, a(var1), var8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(Random var0, int var1) {
/*  51 */     return this.e.a(var0);
/*     */   }
/*     */   
/*     */   private int a(Random var0) {
/*  55 */     return this.f.a(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean b(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/*     */     int var6;
/*     */     int var7;
/*  63 */     if (var5) {
/*  64 */       var6 = Math.min(Math.abs(var1), Math.abs(var1 - 1));
/*  65 */       var7 = Math.min(Math.abs(var3), Math.abs(var3 - 1));
/*     */     } else {
/*  67 */       var6 = Math.abs(var1);
/*  68 */       var7 = Math.abs(var3);
/*     */     } 
/*  70 */     return a(var0, var6, var2, var7, var4, var5);
/*     */   }
/*     */   
/*     */   protected void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, BlockPosition var3, int var4, Set<BlockPosition> var5, int var6, boolean var7, StructureBoundingBox var8) {
/*  74 */     int var9 = var7 ? 1 : 0;
/*  75 */     BlockPosition.MutableBlockPosition var10 = new BlockPosition.MutableBlockPosition();
/*  76 */     for (int var11 = -var4; var11 <= var4 + var9; var11++) {
/*  77 */       for (int var12 = -var4; var12 <= var4 + var9; var12++) {
/*  78 */         if (!b(var1, var11, var6, var12, var4, var7)) {
/*     */ 
/*     */           
/*  81 */           var10.a(var3, var11, var6, var12);
/*  82 */           if (WorldGenTrees.e(var0, var10)) {
/*  83 */             var0.setTypeAndData(var10, var2.c.a(var1, var10), 19);
/*  84 */             var8.c(new StructureBoundingBox(var10, var10));
/*  85 */             var5.add(var10.immutableCopy());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   protected abstract WorldGenFoilagePlacers<?> a();
/*     */   protected abstract void a(VirtualLevelWritable paramVirtualLevelWritable, Random paramRandom, WorldGenFeatureTreeConfiguration paramWorldGenFeatureTreeConfiguration, int paramInt1, b paramb, int paramInt2, int paramInt3, Set<BlockPosition> paramSet, int paramInt4, StructureBoundingBox paramStructureBoundingBox);
/*     */   public abstract int a(Random paramRandom, int paramInt, WorldGenFeatureTreeConfiguration paramWorldGenFeatureTreeConfiguration);
/*     */   protected abstract boolean a(Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean);
/*     */   public static final class b { private final BlockPosition a;
/*     */     public b(BlockPosition var0, int var1, boolean var2) {
/*  97 */       this.a = var0;
/*  98 */       this.b = var1;
/*  99 */       this.c = var2;
/*     */     }
/*     */     private final int b; private final boolean c;
/*     */     public BlockPosition a() {
/* 103 */       return this.a;
/*     */     }
/*     */     
/*     */     public int b() {
/* 107 */       return this.b;
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 111 */       return this.c;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */