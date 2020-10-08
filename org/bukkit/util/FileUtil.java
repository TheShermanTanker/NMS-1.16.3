/*    */ package org.bukkit.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.nio.channels.FileChannel;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public class FileUtil
/*    */ {
/*    */   public static boolean copy(@NotNull File inFile, @NotNull File outFile) {
/* 23 */     if (!inFile.exists()) {
/* 24 */       return false;
/*    */     }
/*    */     
/* 27 */     FileChannel in = null;
/* 28 */     FileChannel out = null;
/*    */     
/*    */     try {
/* 31 */       in = (new FileInputStream(inFile)).getChannel();
/* 32 */       out = (new FileOutputStream(outFile)).getChannel();
/*    */       
/* 34 */       long pos = 0L;
/* 35 */       long size = in.size();
/*    */       
/* 37 */       while (pos < size) {
/* 38 */         pos += in.transferTo(pos, 10485760L, out);
/*    */       }
/* 40 */     } catch (IOException ioe) {
/* 41 */       return false;
/*    */     } finally {
/*    */       try {
/* 44 */         if (in != null) {
/* 45 */           in.close();
/*    */         }
/* 47 */         if (out != null) {
/* 48 */           out.close();
/*    */         }
/* 50 */       } catch (IOException ioe) {
/* 51 */         return false;
/*    */       } 
/*    */     } 
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\FileUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */