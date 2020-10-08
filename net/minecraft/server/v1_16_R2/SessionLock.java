/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Charsets;
/*    */ import java.io.IOException;
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.channels.FileChannel;
/*    */ import java.nio.channels.FileLock;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.OpenOption;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.StandardOpenOption;
/*    */ import java.nio.file.attribute.FileAttribute;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SessionLock
/*    */   implements AutoCloseable
/*    */ {
/*    */   private final FileChannel a;
/*    */   private final FileLock b;
/*    */   private static final ByteBuffer c;
/*    */   
/*    */   static {
/* 24 */     byte[] var0 = "☃".getBytes(Charsets.UTF_8);
/* 25 */     c = ByteBuffer.allocateDirect(var0.length);
/* 26 */     c.put(var0);
/* 27 */     c.flip();
/*    */   }
/*    */   
/*    */   public static SessionLock a(Path var0) throws IOException {
/* 31 */     Path var1 = var0.resolve("session.lock");
/*    */ 
/*    */     
/* 34 */     if (!Files.isDirectory(var0, new java.nio.file.LinkOption[0])) {
/* 35 */       Files.createDirectories(var0, (FileAttribute<?>[])new FileAttribute[0]);
/*    */     }
/* 37 */     FileChannel var2 = FileChannel.open(var1, new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.WRITE });
/*    */     
/*    */     try {
/* 40 */       var2.write(c.duplicate());
/* 41 */       var2.force(true);
/* 42 */       FileLock var3 = var2.tryLock();
/* 43 */       if (var3 == null) {
/* 44 */         throw ExceptionWorldConflict.a(var1);
/*    */       }
/* 46 */       return new SessionLock(var2, var3);
/* 47 */     } catch (IOException var3) {
/*    */       try {
/* 49 */         var2.close();
/* 50 */       } catch (IOException var4) {
/* 51 */         var3.addSuppressed(var4);
/*    */       } 
/* 53 */       throw var3;
/*    */     } 
/*    */   }
/*    */   
/*    */   private SessionLock(FileChannel var0, FileLock var1) {
/* 58 */     this.a = var0;
/* 59 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/*    */     try {
/* 65 */       if (this.b.isValid()) {
/* 66 */         this.b.release();
/*    */       }
/*    */     } finally {
/* 69 */       if (this.a.isOpen()) {
/* 70 */         this.a.close();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 76 */     return this.b.isValid();
/*    */   }
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
/*    */   public static class ExceptionWorldConflict
/*    */     extends IOException
/*    */   {
/*    */     private ExceptionWorldConflict(Path var0, String var1) {
/* 94 */       super(var0.toAbsolutePath() + ": " + var1);
/*    */     }
/*    */     
/*    */     public static ExceptionWorldConflict a(Path var0) {
/* 98 */       return new ExceptionWorldConflict(var0, "already locked (possibly by other Minecraft instance?)");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SessionLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */