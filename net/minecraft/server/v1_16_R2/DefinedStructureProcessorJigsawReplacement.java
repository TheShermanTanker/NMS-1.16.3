/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.serialization.Codec;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefinedStructureProcessorJigsawReplacement
/*    */   extends DefinedStructureProcessor
/*    */ {
/* 17 */   public static final Codec<DefinedStructureProcessorJigsawReplacement> a = Codec.unit(() -> b);
/*    */   
/* 19 */   public static final DefinedStructureProcessorJigsawReplacement b = new DefinedStructureProcessorJigsawReplacement();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DefinedStructure.BlockInfo a(IWorldReader var0, BlockPosition var1, BlockPosition var2, DefinedStructure.BlockInfo var3, DefinedStructure.BlockInfo var4, DefinedStructureInfo var5) {
/* 27 */     IBlockData var6 = var4.b;
/* 28 */     if (var6.a(Blocks.JIGSAW)) {
/*    */ 
/*    */ 
/*    */       
/* 32 */       String var7 = var4.c.getString("final_state");
/* 33 */       ArgumentBlock var8 = new ArgumentBlock(new StringReader(var7), false);
/*    */       try {
/* 35 */         var8.a(true);
/* 36 */       } catch (CommandSyntaxException var9) {
/* 37 */         throw new RuntimeException(var9);
/*    */       } 
/* 39 */       if (var8.getBlockData().a(Blocks.STRUCTURE_VOID)) {
/* 40 */         return null;
/*    */       }
/* 42 */       return new DefinedStructure.BlockInfo(var4.a, var8.getBlockData(), null);
/*    */     } 
/*    */     return var4;
/*    */   }
/*    */   protected DefinedStructureStructureProcessorType<?> a() {
/* 47 */     return DefinedStructureStructureProcessorType.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessorJigsawReplacement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */