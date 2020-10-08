/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ 
/*    */ public abstract class DataConverterNamedEntity extends DataFix {
/*    */   private final String a;
/*    */   private final String b;
/*    */   private final DSL.TypeReference c;
/*    */   
/*    */   public DataConverterNamedEntity(Schema var0, boolean var1, String var2, DSL.TypeReference var3, String var4) {
/* 16 */     super(var0, var1);
/* 17 */     this.a = var2;
/* 18 */     this.c = var3;
/* 19 */     this.b = var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 24 */     OpticFinder<?> var0 = DSL.namedChoice(this.b, getInputSchema().getChoiceType(this.c, this.b));
/*    */     
/* 26 */     return fixTypeEverywhereTyped(this.a, getInputSchema().getType(this.c), getOutputSchema().getType(this.c), var1 -> var1.updateTyped(var0, getOutputSchema().getChoiceType(this.c, this.b), this::a));
/*    */   }
/*    */   
/*    */   protected abstract Typed<?> a(Typed<?> paramTyped);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterNamedEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */