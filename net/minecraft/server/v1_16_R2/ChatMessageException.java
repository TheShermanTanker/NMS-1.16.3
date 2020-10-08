/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class ChatMessageException extends IllegalArgumentException {
/*    */   public ChatMessageException(ChatMessage var0, String var1) {
/*  5 */     super(String.format("Error parsing: %s: %s", new Object[] { var0, var1 }));
/*    */   }
/*    */   
/*    */   public ChatMessageException(ChatMessage var0, int var1) {
/*  9 */     super(String.format("Invalid index %d requested for %s", new Object[] { Integer.valueOf(var1), var0 }));
/*    */   }
/*    */   
/*    */   public ChatMessageException(ChatMessage var0, Throwable var1) {
/* 13 */     super(String.format("Error while parsing: %s", new Object[] { var0 }), var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatMessageException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */