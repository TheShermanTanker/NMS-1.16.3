/*    */ package org.yaml.snakeyaml.tokens;
/*    */ 
/*    */ import org.yaml.snakeyaml.error.Mark;
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
/*    */ public final class FlowMappingStartToken
/*    */   extends Token
/*    */ {
/*    */   public FlowMappingStartToken(Mark startMark, Mark endMark) {
/* 23 */     super(startMark, endMark);
/*    */   }
/*    */ 
/*    */   
/*    */   public Token.ID getTokenId() {
/* 28 */     return Token.ID.FlowMappingStart;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\yaml\snakeyaml\tokens\FlowMappingStartToken.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */