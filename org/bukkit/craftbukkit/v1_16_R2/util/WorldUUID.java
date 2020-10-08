/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.UUID;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public final class WorldUUID
/*    */ {
/* 15 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static UUID getUUID(File baseDir) {
/* 21 */     File file1 = new File(baseDir, "uid.dat");
/* 22 */     if (file1.exists()) {
/* 23 */       DataInputStream dis = null;
/*    */       try {
/* 25 */         dis = new DataInputStream(new FileInputStream(file1));
/* 26 */         return new UUID(dis.readLong(), dis.readLong());
/* 27 */       } catch (IOException ex) {
/* 28 */         LOGGER.warn("Failed to read " + file1 + ", generating new random UUID", ex);
/*    */       } finally {
/* 30 */         if (dis != null) {
/*    */           try {
/* 32 */             dis.close();
/* 33 */           } catch (IOException iOException) {}
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 39 */     UUID uuid = UUID.randomUUID();
/* 40 */     DataOutputStream dos = null;
/*    */     try {
/* 42 */       dos = new DataOutputStream(new FileOutputStream(file1));
/* 43 */       dos.writeLong(uuid.getMostSignificantBits());
/* 44 */       dos.writeLong(uuid.getLeastSignificantBits());
/* 45 */     } catch (IOException ex) {
/* 46 */       LOGGER.warn("Failed to write " + file1, ex);
/*    */     } finally {
/* 48 */       if (dos != null) {
/*    */         try {
/* 50 */           dos.close();
/* 51 */         } catch (IOException iOException) {}
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 56 */     return uuid;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\WorldUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */