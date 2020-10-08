/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ public class DataConverterCustomNameItem
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterCustomNameItem(Schema var0, boolean var1) {
/* 18 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   private Dynamic<?> a(Dynamic<?> var0) {
/* 22 */     Optional<? extends Dynamic<?>> var1 = var0.get("display").result();
/* 23 */     if (var1.isPresent()) {
/* 24 */       Dynamic<?> var2 = var1.get();
/* 25 */       Optional<String> var3 = var2.get("Name").asString().result();
/* 26 */       if (var3.isPresent()) {
/* 27 */         var2 = var2.set("Name", var2.createString(IChatBaseComponent.ChatSerializer.a(new ChatComponentText(var3.get()))));
/*    */       } else {
/* 29 */         Optional<String> var4 = var2.get("LocName").asString().result();
/* 30 */         if (var4.isPresent()) {
/* 31 */           var2 = var2.set("Name", var2.createString(IChatBaseComponent.ChatSerializer.a(new ChatMessage(var4.get()))));
/* 32 */           var2 = var2.remove("LocName");
/*    */         } 
/*    */       } 
/* 35 */       return var0.set("display", var2);
/*    */     } 
/* 37 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 42 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.ITEM_STACK);
/* 43 */     OpticFinder<?> var1 = var0.findField("tag");
/*    */     
/* 45 */     return fixTypeEverywhereTyped("ItemCustomNameToComponentFix", var0, var1 -> var1.updateTyped(var0, ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterCustomNameItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */