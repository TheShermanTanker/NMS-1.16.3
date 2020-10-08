/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function5;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Optional;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class DefinedStructureProcessorPredicates
/*    */ {
/*    */   public static final Codec<DefinedStructureProcessorPredicates> a;
/*    */   
/*    */   static {
/* 17 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)DefinedStructureRuleTest.c.fieldOf("input_predicate").forGetter(()), (App)DefinedStructureRuleTest.c.fieldOf("location_predicate").forGetter(()), (App)PosRuleTest.c.optionalFieldOf("position_predicate", PosRuleTestTrue.b).forGetter(()), (App)IBlockData.b.fieldOf("output_state").forGetter(()), (App)NBTTagCompound.a.optionalFieldOf("output_nbt").forGetter(())).apply((Applicative)var0, DefinedStructureProcessorPredicates::new));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private final DefinedStructureRuleTest b;
/*    */ 
/*    */   
/*    */   private final DefinedStructureRuleTest c;
/*    */ 
/*    */   
/*    */   private final PosRuleTest d;
/*    */ 
/*    */   
/*    */   private final IBlockData e;
/*    */   
/*    */   @Nullable
/*    */   private final NBTTagCompound f;
/*    */ 
/*    */   
/*    */   public DefinedStructureProcessorPredicates(DefinedStructureRuleTest var0, DefinedStructureRuleTest var1, IBlockData var2) {
/* 38 */     this(var0, var1, PosRuleTestTrue.b, var2, Optional.empty());
/*    */   }
/*    */   
/*    */   public DefinedStructureProcessorPredicates(DefinedStructureRuleTest var0, DefinedStructureRuleTest var1, PosRuleTest var2, IBlockData var3) {
/* 42 */     this(var0, var1, var2, var3, Optional.empty());
/*    */   }
/*    */   
/*    */   public DefinedStructureProcessorPredicates(DefinedStructureRuleTest var0, DefinedStructureRuleTest var1, PosRuleTest var2, IBlockData var3, Optional<NBTTagCompound> var4) {
/* 46 */     this.b = var0;
/* 47 */     this.c = var1;
/* 48 */     this.d = var2;
/* 49 */     this.e = var3;
/* 50 */     this.f = var4.orElse(null);
/*    */   }
/*    */   
/*    */   public boolean a(IBlockData var0, IBlockData var1, BlockPosition var2, BlockPosition var3, BlockPosition var4, Random var5) {
/* 54 */     return (this.b.a(var0, var5) && this.c.a(var1, var5) && this.d.a(var2, var3, var4, var5));
/*    */   }
/*    */   
/*    */   public IBlockData a() {
/* 58 */     return this.e;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public NBTTagCompound b() {
/* 63 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureProcessorPredicates.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */