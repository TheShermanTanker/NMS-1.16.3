/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DefinedStructureTestBlockState extends DefinedStructureRuleTest {
/*    */   public static final Codec<DefinedStructureTestBlockState> a;
/*    */   
/*    */   static {
/*  9 */     a = IBlockData.b.fieldOf("block_state").xmap(DefinedStructureTestBlockState::new, var0 -> var0.b).codec();
/*    */   }
/*    */   private final IBlockData b;
/*    */   
/*    */   public DefinedStructureTestBlockState(IBlockData var0) {
/* 14 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, Random var1) {
/* 19 */     return (var0 == this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureRuleTestType<?> a() {
/* 24 */     return DefinedStructureRuleTestType.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureTestBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */