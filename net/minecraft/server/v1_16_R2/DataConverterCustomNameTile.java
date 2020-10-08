/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DataConverterCustomNameTile
/*    */   extends DataFix {
/*    */   public DataConverterCustomNameTile(Schema var0, boolean var1) {
/* 16 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 21 */     OpticFinder<String> var0 = DSL.fieldFinder("id", DataConverterSchemaNamed.a());
/* 22 */     return fixTypeEverywhereTyped("BlockEntityCustomNameToComponentFix", getInputSchema().getType(DataConverterTypes.BLOCK_ENTITY), var1 -> var1.update(DSL.remainderFinder(), ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterCustomNameTile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */