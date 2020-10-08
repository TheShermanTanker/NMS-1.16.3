/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Random;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ public class DefinedStructureTestRandomBlock extends DefinedStructureRuleTest {
/*    */   static {
/* 12 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IRegistry.BLOCK.fieldOf("block").forGetter(()), (App)Codec.FLOAT.fieldOf("probability").forGetter(())).apply((Applicative)var0, DefinedStructureTestRandomBlock::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<DefinedStructureTestRandomBlock> a;
/*    */   private final Block b;
/*    */   private final float d;
/*    */   
/*    */   public DefinedStructureTestRandomBlock(Block var0, float var1) {
/* 21 */     this.b = var0;
/* 22 */     this.d = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, Random var1) {
/* 27 */     return (var0.a(this.b) && var1.nextFloat() < this.d);
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureRuleTestType<?> a() {
/* 32 */     return DefinedStructureRuleTestType.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureTestRandomBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */