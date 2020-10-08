/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.GeneralSecurityException;
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.Key;
/*     */ import java.security.KeyFactory;
/*     */ import java.security.KeyPair;
/*     */ import java.security.KeyPairGenerator;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.PrivateKey;
/*     */ import java.security.PublicKey;
/*     */ import java.security.spec.EncodedKeySpec;
/*     */ import java.security.spec.InvalidKeySpecException;
/*     */ import java.security.spec.X509EncodedKeySpec;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.SecretKey;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class MinecraftEncryption
/*     */ {
/*  30 */   private static final Logger LOGGER = LogManager.getLogger();
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
/*     */   public static KeyPair b() {
/*     */     try {
/*  51 */       KeyPairGenerator var0 = KeyPairGenerator.getInstance("RSA");
/*  52 */       var0.initialize(1024);
/*     */       
/*  54 */       return var0.generateKeyPair();
/*  55 */     } catch (NoSuchAlgorithmException var0) {
/*  56 */       var0.printStackTrace();
/*     */       
/*  58 */       LOGGER.error("Key pair generation failed!");
/*  59 */       return null;
/*     */     } 
/*     */   }
/*     */   public static byte[] a(String var0, PublicKey var1, SecretKey var2) {
/*     */     try {
/*  64 */       return a("SHA-1", new byte[][] { var0
/*     */             
/*  66 */             .getBytes("ISO_8859_1"), var2
/*  67 */             .getEncoded(), var1
/*  68 */             .getEncoded() });
/*     */     }
/*  70 */     catch (UnsupportedEncodingException var3) {
/*  71 */       var3.printStackTrace();
/*     */ 
/*     */       
/*  74 */       return null;
/*     */     } 
/*     */   }
/*     */   private static byte[] a(String var0, byte[]... var1) {
/*     */     try {
/*  79 */       MessageDigest var2 = MessageDigest.getInstance(var0);
/*  80 */       for (byte[] var6 : var1) {
/*  81 */         var2.update(var6);
/*     */       }
/*  83 */       return var2.digest();
/*  84 */     } catch (NoSuchAlgorithmException var2) {
/*  85 */       var2.printStackTrace();
/*     */ 
/*     */       
/*  88 */       return null;
/*     */     } 
/*     */   }
/*     */   public static PublicKey a(byte[] var0) {
/*     */     
/*  93 */     try { EncodedKeySpec var1 = new X509EncodedKeySpec(var0);
/*  94 */       KeyFactory var2 = KeyFactory.getInstance("RSA");
/*  95 */       return var2.generatePublic(var1); }
/*  96 */     catch (NoSuchAlgorithmException noSuchAlgorithmException) {  }
/*  97 */     catch (InvalidKeySpecException invalidKeySpecException) {}
/*     */     
/*  99 */     LOGGER.error("Public key reconstitute failed!");
/* 100 */     return null;
/*     */   }
/*     */   
/*     */   public static SecretKey a(PrivateKey var0, byte[] var1) {
/* 104 */     return new SecretKeySpec(b(var0, var1), "AES");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] b(Key var0, byte[] var1) {
/* 112 */     return a(2, var0, var1);
/*     */   }
/*     */   
/*     */   private static byte[] a(int var0, Key var1, byte[] var2) {
/*     */     try {
/* 117 */       return a(var0, var1.getAlgorithm(), var1).doFinal(var2);
/* 118 */     } catch (IllegalBlockSizeException var3) {
/* 119 */       var3.printStackTrace();
/* 120 */     } catch (BadPaddingException var3) {
/* 121 */       var3.printStackTrace();
/*     */     } 
/* 123 */     LOGGER.error("Cipher data failed!");
/* 124 */     return null;
/*     */   }
/*     */   
/*     */   private static Cipher a(int var0, String var1, Key var2) {
/*     */     try {
/* 129 */       Cipher var3 = Cipher.getInstance(var1);
/* 130 */       var3.init(var0, var2);
/* 131 */       return var3;
/* 132 */     } catch (InvalidKeyException var3) {
/* 133 */       var3.printStackTrace();
/* 134 */     } catch (NoSuchAlgorithmException var3) {
/* 135 */       var3.printStackTrace();
/* 136 */     } catch (NoSuchPaddingException var3) {
/* 137 */       var3.printStackTrace();
/*     */     } 
/* 139 */     LOGGER.error("Cipher creation failed!");
/* 140 */     return null;
/*     */   }
/*     */   
/*     */   public static Cipher a(int var0, Key var1) {
/*     */     try {
/* 145 */       Cipher var2 = Cipher.getInstance("AES/CFB8/NoPadding");
/* 146 */       var2.init(var0, var1, new IvParameterSpec(var1.getEncoded()));
/* 147 */       return var2;
/* 148 */     } catch (GeneralSecurityException var2) {
/* 149 */       throw new RuntimeException(var2);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MinecraftEncryption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */