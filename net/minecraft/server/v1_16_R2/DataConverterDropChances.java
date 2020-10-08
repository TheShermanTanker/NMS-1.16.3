/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.OptionalDynamic;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DataConverterDropChances extends DataFix {
/* 13 */   private static final Codec<List<Float>> a = Codec.FLOAT.listOf();
/*    */   
/*    */   public DataConverterDropChances(Schema var0, boolean var1) {
/* 16 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 22 */     return fixTypeEverywhereTyped("EntityRedundantChanceTagsFix", getInputSchema().getType(DataConverterTypes.ENTITY), var0 -> var0.update(DSL.remainderFinder(), ()));
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
/*    */   private static boolean a(OptionalDynamic<?> var0, int var1) {
/* 35 */     return ((Boolean)var0.flatMap(a::parse).map(var1 -> Boolean.valueOf((var1.size() == var0 && var1.stream().allMatch(())))).result().orElse(Boolean.valueOf(false))).booleanValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterDropChances.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */