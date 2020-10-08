/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DataConverterMapId extends DataFix {
/*    */   public DataConverterMapId(Schema var0, boolean var1) {
/* 16 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 21 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.SAVED_DATA);
/* 22 */     OpticFinder<?> var1 = var0.findField("data");
/* 23 */     return fixTypeEverywhereTyped("Map id fix", var0, var1 -> {
/*    */           Optional<? extends Typed<?>> var2 = var1.getOptionalTyped(var0);
/*    */           return var2.isPresent() ? var1 : var1.update(DSL.remainderFinder(), ());
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterMapId.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */