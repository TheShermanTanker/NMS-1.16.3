/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class DefinedStructureProcessorRule extends DefinedStructureProcessor {
/*    */   public static final Codec<DefinedStructureProcessorRule> a;
/*    */   
/*    */   static {
/* 15 */     a = DefinedStructureProcessorPredicates.a.listOf().fieldOf("rules").xmap(DefinedStructureProcessorRule::new, var0 -> var0.b).codec();
/*    */   }
/*    */   private final ImmutableList<DefinedStructureProcessorPredicates> b;
/*    */   
/*    */   public DefinedStructureProcessorRule(List<? extends DefinedStructureProcessorPredicates> var0) {
/* 20 */     this.b = ImmutableList.copyOf(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public DefinedStructure.BlockInfo a(IWorldReader var0, BlockPosition var1, BlockPosition var2, DefinedStructure.BlockInfo var3, DefinedStructure.BlockInfo var4, DefinedStructureInfo var5) {
/* 26 */     Random var6 = new Random(MathHelper.a(var4.a));
/* 27 */     IBlockData var7 = var0.getType(var4.a);
/* 28 */     for (UnmodifiableIterator<DefinedStructureProcessorPredicates> unmodifiableIterator = this.b.iterator(); unmodifiableIterator.hasNext(); ) { DefinedStructureProcessorPredicates var9 = unmodifiableIterator.next();
/* 29 */       if (var9.a(var4.b, var7, var3.a, var4.a, var2, var6)) {
/* 30 */         return new DefinedStructure.BlockInfo(var4.a, var9.a(), var9.b());
/*    */       } }
/*    */     
/* 33 */     return var4;
/*    */   }
/*    */ 
/*    */   
/*    */   protected DefinedStructureStructureProcessorType<?> a() {
/* 38 */     return DefinedStructureStructureProcessorType.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessorRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */