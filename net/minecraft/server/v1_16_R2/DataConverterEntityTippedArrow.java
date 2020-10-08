/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class DataConverterEntityTippedArrow
/*    */   extends DataConverterEntityRenameAbstract {
/*    */   public DataConverterEntityTippedArrow(Schema var0, boolean var1) {
/*  9 */     super("EntityTippedArrowFix", var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String a(String var0) {
/* 14 */     return Objects.equals(var0, "TippedArrow") ? "Arrow" : var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityTippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */