/*    */ package org.spigotmc;
/*    */ 
/*    */ import java.io.FilterInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import net.minecraft.server.v1_16_R2.NBTReadLimiter;
/*    */ 
/*    */ 
/*    */ public class LimitStream
/*    */   extends FilterInputStream
/*    */ {
/*    */   private final NBTReadLimiter limit;
/*    */   
/*    */   public LimitStream(InputStream is, NBTReadLimiter limit) {
/* 15 */     super(is);
/* 16 */     this.limit = limit;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 22 */     this.limit.a(8L);
/* 23 */     return super.read();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int read(byte[] b) throws IOException {
/* 29 */     this.limit.a((b.length * 8));
/* 30 */     return super.read(b);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 36 */     this.limit.a((len * 8));
/* 37 */     return super.read(b, off, len);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\LimitStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */