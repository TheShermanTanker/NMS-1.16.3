/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ 
/*    */ public class DataConverterShoulderEntity extends DataFix {
/*    */   private final String a;
/*    */   private final DSL.TypeReference b;
/*    */   
/*    */   public DataConverterShoulderEntity(Schema var0, String var1, DSL.TypeReference var2) {
/* 13 */     super(var0, true);
/* 14 */     this.a = var1;
/* 15 */     this.b = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 20 */     return writeAndRead(this.a, getInputSchema().getType(this.b), getOutputSchema().getType(this.b));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterShoulderEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */