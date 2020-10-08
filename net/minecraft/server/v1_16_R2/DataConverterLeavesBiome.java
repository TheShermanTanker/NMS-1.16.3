/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Arrays;
/*    */ import java.util.Optional;
/*    */ import java.util.stream.IntStream;
/*    */ 
/*    */ public class DataConverterLeavesBiome extends DataFix {
/*    */   public DataConverterLeavesBiome(Schema var0, boolean var1) {
/* 16 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 21 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.CHUNK);
/* 22 */     OpticFinder<?> var1 = var0.findField("Level");
/*    */     
/* 24 */     return fixTypeEverywhereTyped("Leaves fix", var0, var1 -> var1.updateTyped(var0, ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterLeavesBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */