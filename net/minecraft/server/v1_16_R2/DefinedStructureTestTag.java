/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DefinedStructureTestTag extends DefinedStructureRuleTest {
/*    */   public static final Codec<DefinedStructureTestTag> a;
/*    */   private final Tag<Block> b;
/*    */   
/*    */   static {
/* 12 */     a = Tag.<T>a(() -> TagsInstance.a().getBlockTags()).fieldOf("tag").xmap(DefinedStructureTestTag::new, var0 -> var0.b).codec();
/*    */   }
/*    */ 
/*    */   
/*    */   public DefinedStructureTestTag(Tag<Block> var0) {
/* 17 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, Random var1) {
/* 22 */     return var0.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureRuleTestType<?> a() {
/* 27 */     return DefinedStructureRuleTestType.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureTestTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */