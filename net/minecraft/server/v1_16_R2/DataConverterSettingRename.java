/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterSettingRename extends DataFix {
/*    */   private final String a;
/*    */   
/*    */   public DataConverterSettingRename(Schema var0, boolean var1, String var2, String var3, String var4) {
/* 15 */     super(var0, var1);
/* 16 */     this.a = var2;
/* 17 */     this.b = var3;
/* 18 */     this.c = var4;
/*    */   }
/*    */   private final String b; private final String c;
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 23 */     return fixTypeEverywhereTyped(this.a, getInputSchema().getType(DataConverterTypes.OPTIONS), var0 -> var0.update(DSL.remainderFinder(), ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSettingRename.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */