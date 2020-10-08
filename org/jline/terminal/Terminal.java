/*     */ package org.jline.terminal;public interface Terminal extends Closeable, Flushable { public static final String TYPE_DUMB = "dumb";
/*     */   public static final String TYPE_DUMB_COLOR = "dumb-color";
/*     */   
/*     */   String getName();
/*     */   
/*     */   SignalHandler handle(Signal paramSignal, SignalHandler paramSignalHandler);
/*     */   
/*     */   void raise(Signal paramSignal);
/*     */   
/*     */   NonBlockingReader reader();
/*     */   
/*     */   PrintWriter writer();
/*     */   
/*     */   Charset encoding();
/*     */   
/*     */   InputStream input();
/*     */   
/*     */   OutputStream output();
/*     */   
/*     */   boolean canPauseResume();
/*     */   
/*     */   void pause();
/*     */   
/*     */   void pause(boolean paramBoolean) throws InterruptedException;
/*     */   
/*     */   void resume();
/*     */   
/*     */   boolean paused();
/*     */   
/*     */   Attributes enterRawMode();
/*     */   
/*     */   boolean echo();
/*     */   
/*     */   boolean echo(boolean paramBoolean);
/*     */   
/*     */   Attributes getAttributes();
/*     */   
/*     */   void setAttributes(Attributes paramAttributes);
/*     */   
/*     */   Size getSize();
/*     */   
/*     */   void setSize(Size paramSize);
/*     */   
/*     */   public enum Signal {
/*  45 */     INT,
/*  46 */     QUIT,
/*  47 */     TSTP,
/*  48 */     CONT,
/*  49 */     INFO,
/*  50 */     WINCH;
/*     */   }
/*     */   
/*     */   public static interface SignalHandler
/*     */   {
/*  55 */     public static final SignalHandler SIG_DFL = (SignalHandler)NativeSignalHandler.SIG_DFL;
/*  56 */     public static final SignalHandler SIG_IGN = (SignalHandler)NativeSignalHandler.SIG_IGN;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void handle(Terminal.Signal param1Signal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default int getWidth() {
/* 196 */     return getSize().getColumns();
/*     */   }
/*     */   
/*     */   default int getHeight() {
/* 200 */     return getSize().getRows();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default Size getBufferSize() {
/* 216 */     return getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   void flush();
/*     */ 
/*     */   
/*     */   String getType();
/*     */ 
/*     */   
/*     */   boolean puts(InfoCmp.Capability paramCapability, Object... paramVarArgs);
/*     */ 
/*     */   
/*     */   boolean getBooleanCapability(InfoCmp.Capability paramCapability);
/*     */ 
/*     */   
/*     */   Integer getNumericCapability(InfoCmp.Capability paramCapability);
/*     */ 
/*     */   
/*     */   String getStringCapability(InfoCmp.Capability paramCapability);
/*     */ 
/*     */   
/*     */   Cursor getCursorPosition(IntConsumer paramIntConsumer);
/*     */ 
/*     */   
/*     */   boolean hasMouseSupport();
/*     */ 
/*     */   
/*     */   boolean trackMouse(MouseTracking paramMouseTracking);
/*     */ 
/*     */   
/*     */   MouseEvent readMouseEvent();
/*     */ 
/*     */   
/*     */   MouseEvent readMouseEvent(IntSupplier paramIntSupplier);
/*     */ 
/*     */   
/*     */   boolean hasFocusSupport();
/*     */ 
/*     */   
/*     */   boolean trackFocus(boolean paramBoolean);
/*     */ 
/*     */   
/*     */   public enum MouseTracking
/*     */   {
/* 261 */     Off,
/*     */ 
/*     */ 
/*     */     
/* 265 */     Normal,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 270 */     Button,
/*     */ 
/*     */ 
/*     */     
/* 274 */     Any;
/*     */   } }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\Terminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */