/*    */ package org.jline.terminal.impl;
/*    */ 
/*    */ import org.jline.terminal.Terminal;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class NativeSignalHandler
/*    */   implements Terminal.SignalHandler
/*    */ {
/* 16 */   public static final NativeSignalHandler SIG_DFL = new NativeSignalHandler();
/*    */   
/* 18 */   public static final NativeSignalHandler SIG_IGN = new NativeSignalHandler();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handle(Terminal.Signal signal) {
/* 24 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\NativeSignalHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */