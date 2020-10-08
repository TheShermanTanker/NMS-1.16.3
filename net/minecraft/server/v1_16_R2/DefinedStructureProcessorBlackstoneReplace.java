/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefinedStructureProcessorBlackstoneReplace
/*    */   extends DefinedStructureProcessor
/*    */ {
/* 20 */   public static final Codec<DefinedStructureProcessorBlackstoneReplace> a = Codec.unit(() -> b);
/*    */   
/* 22 */   public static final DefinedStructureProcessorBlackstoneReplace b = new DefinedStructureProcessorBlackstoneReplace();
/*    */   
/*    */   private final Map<Block, Block> c;
/*    */   
/*    */   private DefinedStructureProcessorBlackstoneReplace() {
/* 27 */     this.c = SystemUtils.<Map<Block, Block>>a(Maps.newHashMap(), var0 -> {
/*    */           var0.put(Blocks.COBBLESTONE, Blocks.BLACKSTONE);
/*    */           var0.put(Blocks.MOSSY_COBBLESTONE, Blocks.BLACKSTONE);
/*    */           var0.put(Blocks.STONE, Blocks.POLISHED_BLACKSTONE);
/*    */           var0.put(Blocks.STONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS);
/*    */           var0.put(Blocks.MOSSY_STONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS);
/*    */           var0.put(Blocks.COBBLESTONE_STAIRS, Blocks.BLACKSTONE_STAIRS);
/*    */           var0.put(Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.BLACKSTONE_STAIRS);
/*    */           var0.put(Blocks.STONE_STAIRS, Blocks.POLISHED_BLACKSTONE_STAIRS);
/*    */           var0.put(Blocks.STONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS);
/*    */           var0.put(Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS);
/*    */           var0.put(Blocks.COBBLESTONE_SLAB, Blocks.BLACKSTONE_SLAB);
/*    */           var0.put(Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.BLACKSTONE_SLAB);
/*    */           var0.put(Blocks.SMOOTH_STONE_SLAB, Blocks.POLISHED_BLACKSTONE_SLAB);
/*    */           var0.put(Blocks.STONE_SLAB, Blocks.POLISHED_BLACKSTONE_SLAB);
/*    */           var0.put(Blocks.STONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB);
/*    */           var0.put(Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB);
/*    */           var0.put(Blocks.STONE_BRICK_WALL, Blocks.POLISHED_BLACKSTONE_BRICK_WALL);
/*    */           var0.put(Blocks.MOSSY_STONE_BRICK_WALL, Blocks.POLISHED_BLACKSTONE_BRICK_WALL);
/*    */           var0.put(Blocks.COBBLESTONE_WALL, Blocks.BLACKSTONE_WALL);
/*    */           var0.put(Blocks.MOSSY_COBBLESTONE_WALL, Blocks.BLACKSTONE_WALL);
/*    */           var0.put(Blocks.CHISELED_STONE_BRICKS, Blocks.CHISELED_POLISHED_BLACKSTONE);
/*    */           var0.put(Blocks.CRACKED_STONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
/*    */           var0.put(Blocks.IRON_BARS, Blocks.CHAIN);
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DefinedStructure.BlockInfo a(IWorldReader var0, BlockPosition var1, BlockPosition var2, DefinedStructure.BlockInfo var3, DefinedStructure.BlockInfo var4, DefinedStructureInfo var5) {
/* 67 */     Block var6 = this.c.get(var4.b.getBlock());
/* 68 */     if (var6 == null) {
/* 69 */       return var4;
/*    */     }
/* 71 */     IBlockData var7 = var4.b;
/* 72 */     IBlockData var8 = var6.getBlockData();
/* 73 */     if (var7.b(BlockStairs.FACING)) {
/* 74 */       var8 = var8.set(BlockStairs.FACING, var7.get(BlockStairs.FACING));
/*    */     }
/* 76 */     if (var7.b(BlockStairs.HALF)) {
/* 77 */       var8 = var8.set(BlockStairs.HALF, var7.get(BlockStairs.HALF));
/*    */     }
/* 79 */     if (var7.b(BlockStepAbstract.a)) {
/* 80 */       var8 = var8.set(BlockStepAbstract.a, var7.get(BlockStepAbstract.a));
/*    */     }
/* 82 */     return new DefinedStructure.BlockInfo(var4.a, var8, var4.c);
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureStructureProcessorType<?> a() {
/* 87 */     return DefinedStructureStructureProcessorType.h;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessorBlackstoneReplace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */