/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefinedStructureProcessorBlockAge
/*     */   extends DefinedStructureProcessor
/*     */ {
/*     */   public static final Codec<DefinedStructureProcessorBlockAge> a;
/*     */   private final float b;
/*     */   
/*     */   static {
/*  18 */     a = Codec.FLOAT.fieldOf("mossiness").xmap(DefinedStructureProcessorBlockAge::new, var0 -> Float.valueOf(var0.b)).codec();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefinedStructureProcessorBlockAge(float var0) {
/*  26 */     this.b = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public DefinedStructure.BlockInfo a(IWorldReader var0, BlockPosition var1, BlockPosition var2, DefinedStructure.BlockInfo var3, DefinedStructure.BlockInfo var4, DefinedStructureInfo var5) {
/*  32 */     Random var6 = var5.b(var4.a);
/*     */     
/*  34 */     IBlockData var7 = var4.b;
/*  35 */     BlockPosition var8 = var4.a;
/*  36 */     IBlockData var9 = null;
/*  37 */     if (var7.a(Blocks.STONE_BRICKS) || var7.a(Blocks.STONE) || var7.a(Blocks.CHISELED_STONE_BRICKS)) {
/*  38 */       var9 = a(var6);
/*  39 */     } else if (var7.a(TagsBlock.STAIRS)) {
/*  40 */       var9 = a(var6, var4.b);
/*  41 */     } else if (var7.a(TagsBlock.SLABS)) {
/*  42 */       var9 = b(var6);
/*  43 */     } else if (var7.a(TagsBlock.WALLS)) {
/*  44 */       var9 = c(var6);
/*  45 */     } else if (var7.a(Blocks.OBSIDIAN)) {
/*  46 */       var9 = d(var6);
/*     */     } 
/*  48 */     if (var9 != null) {
/*  49 */       return new DefinedStructure.BlockInfo(var8, var9, var4.c);
/*     */     }
/*  51 */     return var4;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IBlockData a(Random var0) {
/*  56 */     if (var0.nextFloat() >= 0.5F) {
/*  57 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  61 */     IBlockData[] var1 = { Blocks.CRACKED_STONE_BRICKS.getBlockData(), a(var0, Blocks.STONE_BRICK_STAIRS) };
/*     */ 
/*     */ 
/*     */     
/*  65 */     IBlockData[] var2 = { Blocks.MOSSY_STONE_BRICKS.getBlockData(), a(var0, Blocks.MOSSY_STONE_BRICK_STAIRS) };
/*     */ 
/*     */     
/*  68 */     return a(var0, var1, var2);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IBlockData a(Random var0, IBlockData var1) {
/*  73 */     EnumDirection var2 = (EnumDirection)var1.get(BlockStairs.FACING);
/*  74 */     BlockPropertyHalf var3 = (BlockPropertyHalf)var1.get(BlockStairs.HALF);
/*     */     
/*  76 */     if (var0.nextFloat() >= 0.5F) {
/*  77 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  82 */     IBlockData[] var4 = { Blocks.STONE_SLAB.getBlockData(), Blocks.STONE_BRICK_SLAB.getBlockData() };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     IBlockData[] var5 = { Blocks.MOSSY_STONE_BRICK_STAIRS.getBlockData().set(BlockStairs.FACING, var2).set(BlockStairs.HALF, var3), Blocks.MOSSY_STONE_BRICK_SLAB.getBlockData() };
/*     */ 
/*     */     
/*  90 */     return a(var0, var4, var5);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IBlockData b(Random var0) {
/*  95 */     if (var0.nextFloat() < this.b) {
/*  96 */       return Blocks.MOSSY_STONE_BRICK_SLAB.getBlockData();
/*     */     }
/*  98 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IBlockData c(Random var0) {
/* 103 */     if (var0.nextFloat() < this.b) {
/* 104 */       return Blocks.MOSSY_STONE_BRICK_WALL.getBlockData();
/*     */     }
/* 106 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IBlockData d(Random var0) {
/* 111 */     if (var0.nextFloat() < 0.15F) {
/* 112 */       return Blocks.CRYING_OBSIDIAN.getBlockData();
/*     */     }
/* 114 */     return null;
/*     */   }
/*     */   
/*     */   private static IBlockData a(Random var0, Block var1) {
/* 118 */     return var1.getBlockData()
/* 119 */       .set(BlockStairs.FACING, EnumDirection.EnumDirectionLimit.HORIZONTAL.a(var0))
/* 120 */       .set(BlockStairs.HALF, BlockPropertyHalf.values()[var0.nextInt((BlockPropertyHalf.values()).length)]);
/*     */   }
/*     */   
/*     */   private IBlockData a(Random var0, IBlockData[] var1, IBlockData[] var2) {
/* 124 */     if (var0.nextFloat() < this.b) {
/* 125 */       return a(var0, var2);
/*     */     }
/* 127 */     return a(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static IBlockData a(Random var0, IBlockData[] var1) {
/* 132 */     return var1[var0.nextInt(var1.length)];
/*     */   }
/*     */ 
/*     */   
/*     */   protected DefinedStructureStructureProcessorType<?> a() {
/* 137 */     return DefinedStructureStructureProcessorType.g;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessorBlockAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */