/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class DoubleBlockFinder
/*     */ {
/*     */   public static <S extends TileEntity> Result<S> a(TileEntityTypes<S> tileentitytypes, Function<IBlockData, BlockType> function, Function<IBlockData, EnumDirection> function1, BlockStateDirection blockstatedirection, IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, BiPredicate<GeneratorAccess, BlockPosition> bipredicate) {
/*   9 */     S s0 = tileentitytypes.a(generatoraccess, blockposition);
/*     */     
/*  11 */     if (s0 == null)
/*  12 */       return Combiner::b; 
/*  13 */     if (bipredicate.test(generatoraccess, blockposition)) {
/*  14 */       return Combiner::b;
/*     */     }
/*  16 */     BlockType doubleblockfinder_blocktype = function.apply(iblockdata);
/*  17 */     boolean flag = (doubleblockfinder_blocktype == BlockType.SINGLE);
/*  18 */     boolean flag1 = (doubleblockfinder_blocktype == BlockType.FIRST);
/*     */     
/*  20 */     if (flag) {
/*  21 */       return new Result.Single<>(s0);
/*     */     }
/*  23 */     BlockPosition blockposition1 = blockposition.shift(function1.apply(iblockdata));
/*     */     
/*  25 */     IBlockData iblockdata1 = generatoraccess.getTypeIfLoaded(blockposition1);
/*  26 */     if (iblockdata1 == null) {
/*  27 */       return new Result.Single<>(s0);
/*     */     }
/*     */ 
/*     */     
/*  31 */     if (iblockdata1.a(iblockdata.getBlock())) {
/*  32 */       BlockType doubleblockfinder_blocktype1 = function.apply(iblockdata1);
/*     */       
/*  34 */       if (doubleblockfinder_blocktype1 != BlockType.SINGLE && doubleblockfinder_blocktype != doubleblockfinder_blocktype1 && iblockdata1.get(blockstatedirection) == iblockdata.get(blockstatedirection)) {
/*  35 */         if (bipredicate.test(generatoraccess, blockposition1)) {
/*  36 */           return Combiner::b;
/*     */         }
/*     */         
/*  39 */         S s1 = tileentitytypes.a(generatoraccess, blockposition1);
/*     */         
/*  41 */         if (s1 != null) {
/*  42 */           S s2 = flag1 ? s0 : s1;
/*  43 */           S s3 = flag1 ? s1 : s0;
/*     */           
/*  45 */           return new Result.Double<>(s2, s3);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  50 */     return new Result.Single<>(s0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Single<S>
/*     */     implements Result<S>
/*     */   {
/*     */     private final S a;
/*     */ 
/*     */ 
/*     */     
/*     */     public Single(S s0) {
/*  64 */       this.a = s0;
/*     */     }
/*     */     
/*     */     public <T> T apply(DoubleBlockFinder.Combiner<? super S, T> doubleblockfinder_combiner)
/*     */     {
/*  69 */       return doubleblockfinder_combiner.a(this.a); } } public static interface Result<S> { <T> T apply(DoubleBlockFinder.Combiner<? super S, T> param1Combiner); public static final class Single<S> implements Result<S> { public <T> T apply(DoubleBlockFinder.Combiner<? super S, T> doubleblockfinder_combiner) { return doubleblockfinder_combiner.a(this.a); }
/*     */       
/*     */       private final S a;
/*     */       public Single(S s0) {
/*     */         this.a = s0;
/*     */       } }
/*     */     public static final class Double<S> implements Result<S> { private final S a;
/*     */       private final S b;
/*     */       
/*     */       public Double(S s0, S s1) {
/*  79 */         this.a = s0;
/*  80 */         this.b = s1;
/*     */       }
/*     */       
/*     */       public <T> T apply(DoubleBlockFinder.Combiner<? super S, T> doubleblockfinder_combiner)
/*     */       {
/*  85 */         return doubleblockfinder_combiner.a(this.a, this.b); } } } public static final class Double<S> implements Result<S> { private final S a; public <T> T apply(DoubleBlockFinder.Combiner<? super S, T> doubleblockfinder_combiner) { return doubleblockfinder_combiner.a(this.a, this.b); }
/*     */ 
/*     */ 
/*     */     
/*     */     private final S b;
/*     */ 
/*     */     
/*     */     public Double(S s0, S s1) {
/*     */       this.a = s0;
/*     */       this.b = s1;
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum BlockType
/*     */   {
/* 101 */     SINGLE, FIRST, SECOND;
/*     */   }
/*     */   
/*     */   public static interface Combiner<S, T> {
/*     */     T a(S param1S1, S param1S2);
/*     */     
/*     */     T a(S param1S);
/*     */     
/*     */     T b();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DoubleBlockFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */