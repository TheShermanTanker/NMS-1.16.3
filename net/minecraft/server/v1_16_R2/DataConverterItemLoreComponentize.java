/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
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
/*    */ 
/*    */ public class DataConverterItemLoreComponentize
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterItemLoreComponentize(Schema var0, boolean var1) {
/* 18 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 23 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.ITEM_STACK);
/* 24 */     OpticFinder<?> var1 = var0.findField("tag");
/*    */     
/* 26 */     return fixTypeEverywhereTyped("Item Lore componentize", var0, var1 -> var1.updateTyped(var0, ()));
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
/*    */   private static <T> Stream<Dynamic<T>> a(Stream<Dynamic<T>> var0) {
/* 40 */     return var0.map(var0 -> (Dynamic)DataFixUtils.orElse(var0.asString().map(DataConverterItemLoreComponentize::a).map(var0::createString).result(), var0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static String a(String var0) {
/* 46 */     return IChatBaseComponent.ChatSerializer.a(new ChatComponentText(var0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterItemLoreComponentize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */