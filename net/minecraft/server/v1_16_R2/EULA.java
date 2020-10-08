/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.util.Properties;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class EULA {
/* 12 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   private final Path b;
/*    */   private final boolean c;
/*    */   
/*    */   public EULA(Path java_nio_file_path) {
/* 17 */     this.b = java_nio_file_path;
/* 18 */     this.c = (SharedConstants.d || b());
/*    */   }
/*    */   private boolean b() {
/*    */     try {
/*    */       boolean flag;
/* 23 */       InputStream inputstream = Files.newInputStream(this.b, new java.nio.file.OpenOption[0]);
/* 24 */       Throwable throwable = null;
/*    */ 
/*    */ 
/*    */       
/*    */       try {
/* 29 */         Properties properties = new Properties();
/*    */         
/* 31 */         properties.load(inputstream);
/* 32 */         flag = Boolean.parseBoolean(properties.getProperty("eula", "false"));
/* 33 */       } catch (Throwable throwable1) {
/* 34 */         throwable = throwable1;
/* 35 */         throw throwable1;
/*    */       } finally {
/* 37 */         if (inputstream != null) {
/* 38 */           if (throwable != null) {
/*    */             try {
/* 40 */               inputstream.close();
/* 41 */             } catch (Throwable throwable2) {
/* 42 */               throwable.addSuppressed(throwable2);
/*    */             } 
/*    */           } else {
/* 45 */             inputstream.close();
/*    */           } 
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 51 */       return flag;
/* 52 */     } catch (Exception exception) {
/* 53 */       LOGGER.warn("Failed to load {}", this.b);
/* 54 */       c();
/* 55 */       return false;
/*    */     } 
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 60 */     return this.c;
/*    */   }
/*    */   
/*    */   private void c() {
/* 64 */     if (!SharedConstants.d)
/*    */       try {
/* 66 */         OutputStream outputstream = Files.newOutputStream(this.b, new java.nio.file.OpenOption[0]);
/* 67 */         Throwable throwable = null;
/*    */         
/*    */         try {
/* 70 */           Properties properties = new Properties();
/*    */           
/* 72 */           properties.setProperty("eula", "false");
/* 73 */           properties.store(outputstream, "By changing the setting below to TRUE you are indicating your agreement to our EULA (https://account.mojang.com/documents/minecraft_eula).");
/* 74 */         } catch (Throwable throwable1) {
/* 75 */           throwable = throwable1;
/* 76 */           throw throwable1;
/*    */         } finally {
/* 78 */           if (outputstream != null) {
/* 79 */             if (throwable != null) {
/*    */               try {
/* 81 */                 outputstream.close();
/* 82 */               } catch (Throwable throwable2) {
/* 83 */                 throwable.addSuppressed(throwable2);
/*    */               } 
/*    */             } else {
/* 86 */               outputstream.close();
/*    */             }
/*    */           
/*    */           }
/*    */         } 
/* 91 */       } catch (Exception exception) {
/* 92 */         LOGGER.warn("Failed to save {}", this.b, exception);
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EULA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */