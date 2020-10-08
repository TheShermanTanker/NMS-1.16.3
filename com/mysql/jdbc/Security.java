/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.DigestException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
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
/*     */ public class Security
/*     */ {
/*     */   private static final char PVERSION41_CHAR = '*';
/*     */   private static final int SHA1_HASH_SIZE = 20;
/*  39 */   private static int CACHING_SHA2_DIGEST_LENGTH = 32;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int charVal(char c) {
/*  45 */     return (c >= '0' && c <= '9') ? (c - 48) : ((c >= 'A' && c <= 'Z') ? (c - 65 + 10) : (c - 97 + 10));
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
/*     */   static byte[] createKeyFromOldPassword(String passwd) throws NoSuchAlgorithmException {
/*  74 */     passwd = makeScrambledPassword(passwd);
/*     */ 
/*     */     
/*  77 */     int[] salt = getSaltFromPassword(passwd);
/*     */ 
/*     */     
/*  80 */     return getBinaryPassword(salt, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static byte[] getBinaryPassword(int[] salt, boolean usingNewPasswords) throws NoSuchAlgorithmException {
/*  91 */     int val = 0;
/*     */     
/*  93 */     byte[] binaryPassword = new byte[20];
/*     */     
/*  95 */     if (usingNewPasswords) {
/*  96 */       int pos = 0;
/*     */       
/*  98 */       for (int j = 0; j < 4; j++) {
/*  99 */         val = salt[j];
/*     */         
/* 101 */         for (int t = 3; t >= 0; t--) {
/* 102 */           binaryPassword[pos++] = (byte)(val & 0xFF);
/* 103 */           val >>= 8;
/*     */         } 
/*     */       } 
/*     */       
/* 107 */       return binaryPassword;
/*     */     } 
/*     */     
/* 110 */     int offset = 0;
/*     */     
/* 112 */     for (int i = 0; i < 2; i++) {
/* 113 */       val = salt[i];
/*     */       
/* 115 */       for (int t = 3; t >= 0; t--) {
/* 116 */         binaryPassword[t + offset] = (byte)(val % 256);
/* 117 */         val >>= 8;
/*     */       } 
/*     */       
/* 120 */       offset += 4;
/*     */     } 
/*     */     
/* 123 */     MessageDigest md = MessageDigest.getInstance("SHA-1");
/*     */     
/* 125 */     md.update(binaryPassword, 0, 8);
/*     */     
/* 127 */     return md.digest();
/*     */   }
/*     */   
/*     */   private static int[] getSaltFromPassword(String password) {
/* 131 */     int[] result = new int[6];
/*     */     
/* 133 */     if (password == null || password.length() == 0) {
/* 134 */       return result;
/*     */     }
/*     */     
/* 137 */     if (password.charAt(0) == '*') {
/*     */       
/* 139 */       String saltInHex = password.substring(1, 5);
/*     */       
/* 141 */       int val = 0;
/*     */       
/* 143 */       for (int i = 0; i < 4; i++) {
/* 144 */         val = (val << 4) + charVal(saltInHex.charAt(i));
/*     */       }
/*     */       
/* 147 */       return result;
/*     */     } 
/*     */     
/* 150 */     int resultPos = 0;
/* 151 */     int pos = 0;
/* 152 */     int length = password.length();
/*     */     
/* 154 */     while (pos < length) {
/* 155 */       int val = 0;
/*     */       
/* 157 */       for (int i = 0; i < 8; i++) {
/* 158 */         val = (val << 4) + charVal(password.charAt(pos++));
/*     */       }
/*     */       
/* 161 */       result[resultPos++] = val;
/*     */     } 
/*     */     
/* 164 */     return result;
/*     */   }
/*     */   
/*     */   private static String longToHex(long val) {
/* 168 */     String longHex = Long.toHexString(val);
/*     */     
/* 170 */     int length = longHex.length();
/*     */     
/* 172 */     if (length < 8) {
/* 173 */       int padding = 8 - length;
/* 174 */       StringBuilder buf = new StringBuilder();
/*     */       
/* 176 */       for (int i = 0; i < padding; i++) {
/* 177 */         buf.append("0");
/*     */       }
/*     */       
/* 180 */       buf.append(longHex);
/*     */       
/* 182 */       return buf.toString();
/*     */     } 
/*     */     
/* 185 */     return longHex.substring(0, 8);
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
/*     */   static String makeScrambledPassword(String password) throws NoSuchAlgorithmException {
/* 202 */     long[] passwordHash = Util.hashPre41Password(password);
/* 203 */     StringBuilder scramble = new StringBuilder();
/*     */     
/* 205 */     scramble.append(longToHex(passwordHash[0]));
/* 206 */     scramble.append(longToHex(passwordHash[1]));
/*     */     
/* 208 */     return scramble.toString();
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
/*     */   public static void xorString(byte[] from, byte[] to, byte[] scramble, int length) {
/* 226 */     int pos = 0;
/* 227 */     int scrambleLength = scramble.length;
/*     */     
/* 229 */     while (pos < length) {
/* 230 */       to[pos] = (byte)(from[pos] ^ scramble[pos % scrambleLength]);
/* 231 */       pos++;
/*     */     } 
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
/*     */   static byte[] passwordHashStage1(String password) throws NoSuchAlgorithmException {
/* 247 */     MessageDigest md = MessageDigest.getInstance("SHA-1");
/* 248 */     StringBuilder cleansedPassword = new StringBuilder();
/*     */     
/* 250 */     int passwordLength = password.length();
/*     */     
/* 252 */     for (int i = 0; i < passwordLength; i++) {
/* 253 */       char c = password.charAt(i);
/*     */       
/* 255 */       if (c != ' ' && c != '\t')
/*     */       {
/*     */ 
/*     */         
/* 259 */         cleansedPassword.append(c);
/*     */       }
/*     */     } 
/* 262 */     return md.digest(StringUtils.getBytes(cleansedPassword.toString()));
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
/*     */   static byte[] passwordHashStage2(byte[] hashedPassword, byte[] salt) throws NoSuchAlgorithmException {
/* 279 */     MessageDigest md = MessageDigest.getInstance("SHA-1");
/*     */ 
/*     */     
/* 282 */     md.update(salt, 0, 4);
/*     */     
/* 284 */     md.update(hashedPassword, 0, 20);
/*     */     
/* 286 */     return md.digest();
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
/*     */   public static byte[] scramble411(String password, String seed, String passwordEncoding) throws NoSuchAlgorithmException, UnsupportedEncodingException {
/* 307 */     MessageDigest md = MessageDigest.getInstance("SHA-1");
/*     */     
/* 309 */     byte[] passwordHashStage1 = md.digest((passwordEncoding == null || passwordEncoding.length() == 0) ? StringUtils.getBytes(password) : StringUtils.getBytes(password, passwordEncoding));
/*     */     
/* 311 */     md.reset();
/*     */     
/* 313 */     byte[] passwordHashStage2 = md.digest(passwordHashStage1);
/* 314 */     md.reset();
/*     */     
/* 316 */     byte[] seedAsBytes = StringUtils.getBytes(seed, "ASCII");
/* 317 */     md.update(seedAsBytes);
/* 318 */     md.update(passwordHashStage2);
/*     */     
/* 320 */     byte[] toBeXord = md.digest();
/*     */     
/* 322 */     int numToXor = toBeXord.length;
/*     */     
/* 324 */     for (int i = 0; i < numToXor; i++) {
/* 325 */       toBeXord[i] = (byte)(toBeXord[i] ^ passwordHashStage1[i]);
/*     */     }
/*     */     
/* 328 */     return toBeXord;
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
/*     */   public static byte[] scrambleCachingSha2(byte[] password, byte[] seed) throws DigestException {
/*     */     MessageDigest md;
/*     */     try {
/* 351 */       md = MessageDigest.getInstance("SHA-256");
/* 352 */     } catch (NoSuchAlgorithmException ex) {
/* 353 */       throw new AssertionFailedException(ex);
/*     */     } 
/*     */     
/* 356 */     byte[] dig1 = new byte[CACHING_SHA2_DIGEST_LENGTH];
/* 357 */     byte[] dig2 = new byte[CACHING_SHA2_DIGEST_LENGTH];
/* 358 */     byte[] scramble1 = new byte[CACHING_SHA2_DIGEST_LENGTH];
/*     */ 
/*     */     
/* 361 */     md.update(password, 0, password.length);
/* 362 */     md.digest(dig1, 0, CACHING_SHA2_DIGEST_LENGTH);
/* 363 */     md.reset();
/*     */ 
/*     */     
/* 366 */     md.update(dig1, 0, dig1.length);
/* 367 */     md.digest(dig2, 0, CACHING_SHA2_DIGEST_LENGTH);
/* 368 */     md.reset();
/*     */ 
/*     */     
/* 371 */     md.update(dig2, 0, dig1.length);
/* 372 */     md.update(seed, 0, seed.length);
/* 373 */     md.digest(scramble1, 0, CACHING_SHA2_DIGEST_LENGTH);
/*     */ 
/*     */     
/* 376 */     byte[] mysqlScrambleBuff = new byte[CACHING_SHA2_DIGEST_LENGTH];
/* 377 */     xorString(dig1, mysqlScrambleBuff, scramble1, CACHING_SHA2_DIGEST_LENGTH);
/*     */     
/* 379 */     return mysqlScrambleBuff;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\Security.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */