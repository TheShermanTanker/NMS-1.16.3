/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.stream.Stream;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ public class DataConverterBook
/*    */   extends DataFix {
/*    */   public DataConverterBook(Schema var0, boolean var1) {
/* 19 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 23 */     return var0.update("pages", var1 -> (Dynamic)DataFixUtils.orElse(var1.asStreamOpt().map(()).map(var0::createList).result(), var0.emptyList()));
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 72 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.ITEM_STACK);
/* 73 */     OpticFinder<?> var1 = var0.findField("tag");
/*    */     
/* 75 */     return fixTypeEverywhereTyped("ItemWrittenBookPagesStrictJsonFix", var0, var1 -> var1.updateTyped(var0, ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */