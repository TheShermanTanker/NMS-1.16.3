/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class DefinedStructureTestRandomBlockState extends DefinedStructureRuleTest {
/*    */   static {
/* 10 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IBlockData.b.fieldOf("block_state").forGetter(()), (App)Codec.FLOAT.fieldOf("probability").forGetter(())).apply((Applicative)var0, DefinedStructureTestRandomBlockState::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<DefinedStructureTestRandomBlockState> a;
/*    */   private final IBlockData b;
/*    */   private final float d;
/*    */   
/*    */   public DefinedStructureTestRandomBlockState(IBlockData var0, float var1) {
/* 19 */     this.b = var0;
/* 20 */     this.d = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, Random var1) {
/* 25 */     return (var0 == this.b && var1.nextFloat() < this.d);
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureRuleTestType<?> a() {
/* 30 */     return DefinedStructureRuleTestType.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureTestRandomBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */