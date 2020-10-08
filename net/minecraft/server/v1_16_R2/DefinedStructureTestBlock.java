/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DefinedStructureTestBlock extends DefinedStructureRuleTest {
/*    */   public static final Codec<DefinedStructureTestBlock> a;
/*    */   
/*    */   static {
/* 11 */     a = IRegistry.BLOCK.fieldOf("block").xmap(DefinedStructureTestBlock::new, var0 -> var0.b).codec();
/*    */   }
/*    */   private final Block b;
/*    */   
/*    */   public DefinedStructureTestBlock(Block var0) {
/* 16 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, Random var1) {
/* 21 */     return var0.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureRuleTestType<?> a() {
/* 26 */     return DefinedStructureRuleTestType.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureTestBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */