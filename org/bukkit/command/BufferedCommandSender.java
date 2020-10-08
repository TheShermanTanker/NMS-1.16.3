/*    */ package org.bukkit.command;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class BufferedCommandSender implements MessageCommandSender {
/*  6 */   private final StringBuffer buffer = new StringBuffer();
/*    */   
/*    */   public void sendMessage(@NotNull String message) {
/*  9 */     this.buffer.append(message);
/* 10 */     this.buffer.append("\n");
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String getBuffer() {
/* 15 */     return this.buffer.toString();
/*    */   }
/*    */   
/*    */   public void reset() {
/* 19 */     this.buffer.setLength(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\BufferedCommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */