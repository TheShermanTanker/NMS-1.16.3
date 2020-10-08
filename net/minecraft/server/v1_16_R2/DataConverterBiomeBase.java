/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DataConverterBiomeBase
/*    */   extends DataFix {
/*    */   private final String a;
/*    */   private final Map<String, String> b;
/*    */   
/*    */   public DataConverterBiomeBase(Schema var0, boolean var1, String var2, Map<String, String> var3) {
/* 20 */     super(var0, var1);
/* 21 */     this.b = var3;
/* 22 */     this.a = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 27 */     Type<Pair<String, String>> var0 = DSL.named(DataConverterTypes.BIOME.typeName(), DataConverterSchemaNamed.a());
/* 28 */     if (!Objects.equals(var0, getInputSchema().getType(DataConverterTypes.BIOME))) {
/* 29 */       throw new IllegalStateException("Biome type is not what was expected.");
/*    */     }
/* 31 */     return fixTypeEverywhere(this.a, var0, var0 -> ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBiomeBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */