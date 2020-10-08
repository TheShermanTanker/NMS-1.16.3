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
/*    */ 
/*    */ 
/*    */ public class DataConverterCustomNameEntity
/*    */   extends DataFix
/*    */ {
/*    */   public DataConverterCustomNameEntity(Schema var0, boolean var1) {
/* 19 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 24 */     OpticFinder<String> var0 = DSL.fieldFinder("id", DataConverterSchemaNamed.a());
/* 25 */     return fixTypeEverywhereTyped("EntityCustomNameToComponentFix", getInputSchema().getType(DataConverterTypes.ENTITY), var1 -> var1.update(DSL.remainderFinder(), ()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Dynamic<?> a(Dynamic<?> var0) {
/* 35 */     String var1 = var0.get("CustomName").asString("");
/* 36 */     if (var1.isEmpty()) {
/* 37 */       return var0.remove("CustomName");
/*    */     }
/* 39 */     return var0.set("CustomName", var0.createString(IChatBaseComponent.ChatSerializer.a(new ChatComponentText(var1))));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterCustomNameEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */