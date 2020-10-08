/*    */ package org.jline.reader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Parser
/*    */ {
/*    */   ParsedLine parse(String paramString, int paramInt, ParseContext paramParseContext) throws SyntaxError;
/*    */   
/*    */   default ParsedLine parse(String line, int cursor) throws SyntaxError {
/* 16 */     return parse(line, cursor, ParseContext.UNSPECIFIED);
/*    */   }
/*    */   
/*    */   default boolean isEscapeChar(char ch) {
/* 20 */     return (ch == '\\');
/*    */   }
/*    */   
/*    */   public enum ParseContext {
/* 24 */     UNSPECIFIED,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     ACCEPT_LINE,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     COMPLETE,
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 40 */     SECONDARY_PROMPT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\Parser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */