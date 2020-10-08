/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class RedirectStream
/*    */   extends PrintStream {
/* 11 */   protected static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   protected final String b;
/*    */   
/*    */   public RedirectStream(String var0, OutputStream var1) {
/* 16 */     super(var1);
/* 17 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void println(@Nullable String var0) {
/* 22 */     a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void println(Object var0) {
/* 27 */     a(String.valueOf(var0));
/*    */   }
/*    */   
/*    */   protected void a(@Nullable String var0) {
/* 31 */     LOGGER.info("[{}]: {}", this.b, var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RedirectStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */