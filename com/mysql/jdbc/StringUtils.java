/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.StringReader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.math.BigDecimal;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.CharBuffer;
/*      */ import java.nio.charset.Charset;
/*      */ import java.nio.charset.IllegalCharsetNameException;
/*      */ import java.nio.charset.UnsupportedCharsetException;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.EnumSet;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class StringUtils
/*      */ {
/*      */   public enum SearchMode
/*      */   {
/*   53 */     ALLOW_BACKSLASH_ESCAPE, SKIP_BETWEEN_MARKERS, SKIP_BLOCK_COMMENTS, SKIP_LINE_COMMENTS, SKIP_WHITE_SPACE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   63 */   public static final Set<SearchMode> SEARCH_MODE__ALL = Collections.unmodifiableSet(EnumSet.allOf(SearchMode.class));
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   68 */   public static final Set<SearchMode> SEARCH_MODE__MRK_COM_WS = Collections.unmodifiableSet(EnumSet.of(SearchMode.SKIP_BETWEEN_MARKERS, SearchMode.SKIP_BLOCK_COMMENTS, SearchMode.SKIP_LINE_COMMENTS, SearchMode.SKIP_WHITE_SPACE));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   74 */   public static final Set<SearchMode> SEARCH_MODE__BSESC_COM_WS = Collections.unmodifiableSet(EnumSet.of(SearchMode.ALLOW_BACKSLASH_ESCAPE, SearchMode.SKIP_BLOCK_COMMENTS, SearchMode.SKIP_LINE_COMMENTS, SearchMode.SKIP_WHITE_SPACE));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   80 */   public static final Set<SearchMode> SEARCH_MODE__BSESC_MRK_WS = Collections.unmodifiableSet(EnumSet.of(SearchMode.ALLOW_BACKSLASH_ESCAPE, SearchMode.SKIP_BETWEEN_MARKERS, SearchMode.SKIP_WHITE_SPACE));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   86 */   public static final Set<SearchMode> SEARCH_MODE__COM_WS = Collections.unmodifiableSet(EnumSet.of(SearchMode.SKIP_BLOCK_COMMENTS, SearchMode.SKIP_LINE_COMMENTS, SearchMode.SKIP_WHITE_SPACE));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   92 */   public static final Set<SearchMode> SEARCH_MODE__MRK_WS = Collections.unmodifiableSet(EnumSet.of(SearchMode.SKIP_BETWEEN_MARKERS, SearchMode.SKIP_WHITE_SPACE));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   98 */   public static final Set<SearchMode> SEARCH_MODE__NONE = Collections.unmodifiableSet(EnumSet.noneOf(SearchMode.class));
/*      */ 
/*      */   
/*      */   private static final int NON_COMMENTS_MYSQL_VERSION_REF_LENGTH = 5;
/*      */   
/*      */   private static final int BYTE_RANGE = 256;
/*      */   
/*  105 */   private static byte[] allBytes = new byte[256];
/*      */   
/*  107 */   private static char[] byteToChars = new char[256];
/*      */   
/*      */   private static Method toPlainStringMethod;
/*      */   
/*      */   private static final int WILD_COMPARE_MATCH = 0;
/*      */   
/*      */   private static final int WILD_COMPARE_CONTINUE_WITH_WILD = 1;
/*      */   
/*      */   private static final int WILD_COMPARE_NO_MATCH = -1;
/*      */   static final char WILDCARD_MANY = '%';
/*      */   static final char WILDCARD_ONE = '_';
/*      */   static final char WILDCARD_ESCAPE = '\\';
/*  119 */   private static final ConcurrentHashMap<String, Charset> charsetsByAlias = new ConcurrentHashMap<String, Charset>();
/*      */   
/*  121 */   private static final String platformEncoding = System.getProperty("file.encoding");
/*      */   
/*      */   private static final String VALID_ID_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789$_#@";
/*      */   
/*      */   static Charset findCharset(String alias) throws UnsupportedEncodingException {
/*      */     try {
/*  127 */       Charset cs = charsetsByAlias.get(alias);
/*      */       
/*  129 */       if (cs == null) {
/*  130 */         cs = Charset.forName(alias);
/*  131 */         Charset oldCs = charsetsByAlias.putIfAbsent(alias, cs);
/*  132 */         if (oldCs != null)
/*      */         {
/*  134 */           cs = oldCs;
/*      */         }
/*      */       } 
/*      */       
/*  138 */       return cs;
/*      */     
/*      */     }
/*  141 */     catch (UnsupportedCharsetException uce) {
/*  142 */       throw new UnsupportedEncodingException(alias);
/*  143 */     } catch (IllegalCharsetNameException icne) {
/*  144 */       throw new UnsupportedEncodingException(alias);
/*  145 */     } catch (IllegalArgumentException iae) {
/*  146 */       throw new UnsupportedEncodingException(alias);
/*      */     } 
/*      */   }
/*      */   
/*      */   static {
/*  151 */     for (int i = -128; i <= 127; i++) {
/*  152 */       allBytes[i - -128] = (byte)i;
/*      */     }
/*      */     
/*  155 */     String allBytesString = new String(allBytes, 0, 255);
/*      */     
/*  157 */     int allBytesStringLen = allBytesString.length();
/*      */     
/*  159 */     for (int j = 0; j < 255 && j < allBytesStringLen; j++) {
/*  160 */       byteToChars[j] = allBytesString.charAt(j);
/*      */     }
/*      */     
/*      */     try {
/*  164 */       toPlainStringMethod = BigDecimal.class.getMethod("toPlainString", new Class[0]);
/*  165 */     } catch (NoSuchMethodException nsme) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String consistentToString(BigDecimal decimal) {
/*  180 */     if (decimal == null) {
/*  181 */       return null;
/*      */     }
/*      */     
/*  184 */     if (toPlainStringMethod != null) {
/*      */       try {
/*  186 */         return (String)toPlainStringMethod.invoke(decimal, (Object[])null);
/*  187 */       } catch (InvocationTargetException invokeEx) {
/*      */       
/*  189 */       } catch (IllegalAccessException accessEx) {}
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  194 */     return decimal.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String dumpAsHex(byte[] byteBuffer, int length) {
/*  208 */     StringBuilder outputBuilder = new StringBuilder(length * 4);
/*      */     
/*  210 */     int p = 0;
/*  211 */     int rows = length / 8;
/*      */     
/*  213 */     for (int i = 0; i < rows && p < length; i++) {
/*  214 */       int ptemp = p;
/*      */       int k;
/*  216 */       for (k = 0; k < 8; k++) {
/*  217 */         String hexVal = Integer.toHexString(byteBuffer[ptemp] & 0xFF);
/*      */         
/*  219 */         if (hexVal.length() == 1) {
/*  220 */           hexVal = "0" + hexVal;
/*      */         }
/*      */         
/*  223 */         outputBuilder.append(hexVal + " ");
/*  224 */         ptemp++;
/*      */       } 
/*      */       
/*  227 */       outputBuilder.append("    ");
/*      */       
/*  229 */       for (k = 0; k < 8; k++) {
/*  230 */         int b = 0xFF & byteBuffer[p];
/*      */         
/*  232 */         if (b > 32 && b < 127) {
/*  233 */           outputBuilder.append((char)b + " ");
/*      */         } else {
/*  235 */           outputBuilder.append(". ");
/*      */         } 
/*      */         
/*  238 */         p++;
/*      */       } 
/*      */       
/*  241 */       outputBuilder.append("\n");
/*      */     } 
/*      */     
/*  244 */     int n = 0;
/*      */     int j;
/*  246 */     for (j = p; j < length; j++) {
/*  247 */       String hexVal = Integer.toHexString(byteBuffer[j] & 0xFF);
/*      */       
/*  249 */       if (hexVal.length() == 1) {
/*  250 */         hexVal = "0" + hexVal;
/*      */       }
/*      */       
/*  253 */       outputBuilder.append(hexVal + " ");
/*  254 */       n++;
/*      */     } 
/*      */     
/*  257 */     for (j = n; j < 8; j++) {
/*  258 */       outputBuilder.append("   ");
/*      */     }
/*      */     
/*  261 */     outputBuilder.append("    ");
/*      */     
/*  263 */     for (j = p; j < length; j++) {
/*  264 */       int b = 0xFF & byteBuffer[j];
/*      */       
/*  266 */       if (b > 32 && b < 127) {
/*  267 */         outputBuilder.append((char)b + " ");
/*      */       } else {
/*  269 */         outputBuilder.append(". ");
/*      */       } 
/*      */     } 
/*      */     
/*  273 */     outputBuilder.append("\n");
/*      */     
/*  275 */     return outputBuilder.toString();
/*      */   }
/*      */   
/*      */   private static boolean endsWith(byte[] dataFrom, String suffix) {
/*  279 */     for (int i = 1; i <= suffix.length(); i++) {
/*  280 */       int dfOffset = dataFrom.length - i;
/*  281 */       int suffixOffset = suffix.length() - i;
/*  282 */       if (dataFrom[dfOffset] != suffix.charAt(suffixOffset)) {
/*  283 */         return false;
/*      */       }
/*      */     } 
/*  286 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] escapeEasternUnicodeByteStream(byte[] origBytes, String origString) {
/*  301 */     if (origBytes == null) {
/*  302 */       return null;
/*      */     }
/*  304 */     if (origBytes.length == 0) {
/*  305 */       return new byte[0];
/*      */     }
/*      */     
/*  308 */     int bytesLen = origBytes.length;
/*  309 */     int bufIndex = 0;
/*  310 */     int strIndex = 0;
/*      */     
/*  312 */     ByteArrayOutputStream bytesOut = new ByteArrayOutputStream(bytesLen);
/*      */     
/*      */     while (true) {
/*  315 */       if (origString.charAt(strIndex) == '\\') {
/*      */         
/*  317 */         bytesOut.write(origBytes[bufIndex++]);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  322 */         int loByte = origBytes[bufIndex];
/*      */         
/*  324 */         if (loByte < 0) {
/*  325 */           loByte += 256;
/*      */         }
/*      */ 
/*      */         
/*  329 */         bytesOut.write(loByte);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  347 */         if (loByte >= 128) {
/*  348 */           if (bufIndex < bytesLen - 1) {
/*  349 */             int hiByte = origBytes[bufIndex + 1];
/*      */             
/*  351 */             if (hiByte < 0) {
/*  352 */               hiByte += 256;
/*      */             }
/*      */ 
/*      */             
/*  356 */             bytesOut.write(hiByte);
/*  357 */             bufIndex++;
/*      */ 
/*      */             
/*  360 */             if (hiByte == 92) {
/*  361 */               bytesOut.write(hiByte);
/*      */             }
/*      */           } 
/*  364 */         } else if (loByte == 92 && 
/*  365 */           bufIndex < bytesLen - 1) {
/*  366 */           int hiByte = origBytes[bufIndex + 1];
/*      */           
/*  368 */           if (hiByte < 0) {
/*  369 */             hiByte += 256;
/*      */           }
/*      */           
/*  372 */           if (hiByte == 98) {
/*      */             
/*  374 */             bytesOut.write(92);
/*  375 */             bytesOut.write(98);
/*  376 */             bufIndex++;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  381 */         bufIndex++;
/*      */       } 
/*      */       
/*  384 */       if (bufIndex >= bytesLen) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  389 */       strIndex++;
/*      */     } 
/*      */     
/*  392 */     return bytesOut.toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static char firstNonWsCharUc(String searchIn) {
/*  404 */     return firstNonWsCharUc(searchIn, 0);
/*      */   }
/*      */   
/*      */   public static char firstNonWsCharUc(String searchIn, int startAt) {
/*  408 */     if (searchIn == null) {
/*  409 */       return Character.MIN_VALUE;
/*      */     }
/*      */     
/*  412 */     int length = searchIn.length();
/*      */     
/*  414 */     for (int i = startAt; i < length; i++) {
/*  415 */       char c = searchIn.charAt(i);
/*      */       
/*  417 */       if (!Character.isWhitespace(c)) {
/*  418 */         return Character.toUpperCase(c);
/*      */       }
/*      */     } 
/*      */     
/*  422 */     return Character.MIN_VALUE;
/*      */   }
/*      */   
/*      */   public static char firstAlphaCharUc(String searchIn, int startAt) {
/*  426 */     if (searchIn == null) {
/*  427 */       return Character.MIN_VALUE;
/*      */     }
/*      */     
/*  430 */     int length = searchIn.length();
/*      */     
/*  432 */     for (int i = startAt; i < length; i++) {
/*  433 */       char c = searchIn.charAt(i);
/*      */       
/*  435 */       if (Character.isLetter(c)) {
/*  436 */         return Character.toUpperCase(c);
/*      */       }
/*      */     } 
/*      */     
/*  440 */     return Character.MIN_VALUE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String fixDecimalExponent(String dString) {
/*  453 */     int ePos = dString.indexOf('E');
/*      */     
/*  455 */     if (ePos == -1) {
/*  456 */       ePos = dString.indexOf('e');
/*      */     }
/*      */     
/*  459 */     if (ePos != -1 && 
/*  460 */       dString.length() > ePos + 1) {
/*  461 */       char maybeMinusChar = dString.charAt(ePos + 1);
/*      */       
/*  463 */       if (maybeMinusChar != '-' && maybeMinusChar != '+') {
/*  464 */         StringBuilder strBuilder = new StringBuilder(dString.length() + 1);
/*  465 */         strBuilder.append(dString.substring(0, ePos + 1));
/*  466 */         strBuilder.append('+');
/*  467 */         strBuilder.append(dString.substring(ePos + 1, dString.length()));
/*  468 */         dString = strBuilder.toString();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  473 */     return dString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] getBytes(char[] c, SingleByteCharsetConverter converter, String encoding, String serverEncoding, boolean parserKnowsUnicode, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*      */     try {
/*      */       byte[] b;
/*  485 */       if (converter != null) {
/*  486 */         b = converter.toBytes(c);
/*  487 */       } else if (encoding == null) {
/*  488 */         b = getBytes(c);
/*      */       } else {
/*  490 */         b = getBytes(c, encoding);
/*      */         
/*  492 */         if (!parserKnowsUnicode && CharsetMapping.requiresEscapeEasternUnicode(encoding))
/*      */         {
/*  494 */           if (!encoding.equalsIgnoreCase(serverEncoding)) {
/*  495 */             b = escapeEasternUnicodeByteStream(b, new String(c));
/*      */           }
/*      */         }
/*      */       } 
/*      */       
/*  500 */       return b;
/*  501 */     } catch (UnsupportedEncodingException uee) {
/*  502 */       throw SQLError.createSQLException(Messages.getString("StringUtils.0") + encoding + Messages.getString("StringUtils.1"), "S1009", exceptionInterceptor);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] getBytes(char[] c, SingleByteCharsetConverter converter, String encoding, String serverEncoding, int offset, int length, boolean parserKnowsUnicode, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*      */     try {
/*      */       byte[] b;
/*  516 */       if (converter != null) {
/*  517 */         b = converter.toBytes(c, offset, length);
/*  518 */       } else if (encoding == null) {
/*  519 */         b = getBytes(c, offset, length);
/*      */       } else {
/*  521 */         b = getBytes(c, offset, length, encoding);
/*      */         
/*  523 */         if (!parserKnowsUnicode && CharsetMapping.requiresEscapeEasternUnicode(encoding))
/*      */         {
/*  525 */           if (!encoding.equalsIgnoreCase(serverEncoding)) {
/*  526 */             b = escapeEasternUnicodeByteStream(b, new String(c, offset, length));
/*      */           }
/*      */         }
/*      */       } 
/*      */       
/*  531 */       return b;
/*  532 */     } catch (UnsupportedEncodingException uee) {
/*  533 */       throw SQLError.createSQLException(Messages.getString("StringUtils.0") + encoding + Messages.getString("StringUtils.1"), "S1009", exceptionInterceptor);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] getBytes(char[] c, String encoding, String serverEncoding, boolean parserKnowsUnicode, MySQLConnection conn, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*      */     try {
/*  545 */       SingleByteCharsetConverter converter = (conn != null) ? conn.getCharsetConverter(encoding) : SingleByteCharsetConverter.getInstance(encoding, null);
/*      */       
/*  547 */       return getBytes(c, converter, encoding, serverEncoding, parserKnowsUnicode, exceptionInterceptor);
/*  548 */     } catch (UnsupportedEncodingException uee) {
/*  549 */       throw SQLError.createSQLException(Messages.getString("StringUtils.0") + encoding + Messages.getString("StringUtils.1"), "S1009", exceptionInterceptor);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] getBytes(String s, SingleByteCharsetConverter converter, String encoding, String serverEncoding, boolean parserKnowsUnicode, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*      */     try {
/*      */       byte[] b;
/*  563 */       if (converter != null) {
/*  564 */         b = converter.toBytes(s);
/*  565 */       } else if (encoding == null) {
/*  566 */         b = getBytes(s);
/*      */       } else {
/*  568 */         b = getBytes(s, encoding);
/*      */         
/*  570 */         if (!parserKnowsUnicode && CharsetMapping.requiresEscapeEasternUnicode(encoding))
/*      */         {
/*  572 */           if (!encoding.equalsIgnoreCase(serverEncoding)) {
/*  573 */             b = escapeEasternUnicodeByteStream(b, s);
/*      */           }
/*      */         }
/*      */       } 
/*      */       
/*  578 */       return b;
/*  579 */     } catch (UnsupportedEncodingException uee) {
/*  580 */       throw SQLError.createSQLException(Messages.getString("StringUtils.5") + encoding + Messages.getString("StringUtils.6"), "S1009", exceptionInterceptor);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] getBytes(String s, SingleByteCharsetConverter converter, String encoding, String serverEncoding, int offset, int length, boolean parserKnowsUnicode, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*      */     try {
/*      */       byte[] b;
/*  594 */       if (converter != null) {
/*  595 */         b = converter.toBytes(s, offset, length);
/*  596 */       } else if (encoding == null) {
/*  597 */         b = getBytes(s, offset, length);
/*      */       } else {
/*  599 */         s = s.substring(offset, offset + length);
/*  600 */         b = getBytes(s, encoding);
/*      */         
/*  602 */         if (!parserKnowsUnicode && CharsetMapping.requiresEscapeEasternUnicode(encoding))
/*      */         {
/*  604 */           if (!encoding.equalsIgnoreCase(serverEncoding)) {
/*  605 */             b = escapeEasternUnicodeByteStream(b, s);
/*      */           }
/*      */         }
/*      */       } 
/*      */       
/*  610 */       return b;
/*  611 */     } catch (UnsupportedEncodingException uee) {
/*  612 */       throw SQLError.createSQLException(Messages.getString("StringUtils.5") + encoding + Messages.getString("StringUtils.6"), "S1009", exceptionInterceptor);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] getBytes(String s, String encoding, String serverEncoding, boolean parserKnowsUnicode, MySQLConnection conn, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*      */     try {
/*  624 */       SingleByteCharsetConverter converter = (conn != null) ? conn.getCharsetConverter(encoding) : SingleByteCharsetConverter.getInstance(encoding, null);
/*      */       
/*  626 */       return getBytes(s, converter, encoding, serverEncoding, parserKnowsUnicode, exceptionInterceptor);
/*  627 */     } catch (UnsupportedEncodingException uee) {
/*  628 */       throw SQLError.createSQLException(Messages.getString("StringUtils.5") + encoding + Messages.getString("StringUtils.6"), "S1009", exceptionInterceptor);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final byte[] getBytes(String s, String encoding, String serverEncoding, int offset, int length, boolean parserKnowsUnicode, MySQLConnection conn, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*      */     try {
/*  640 */       SingleByteCharsetConverter converter = (conn != null) ? conn.getCharsetConverter(encoding) : SingleByteCharsetConverter.getInstance(encoding, null);
/*      */       
/*  642 */       return getBytes(s, converter, encoding, serverEncoding, offset, length, parserKnowsUnicode, exceptionInterceptor);
/*  643 */     } catch (UnsupportedEncodingException uee) {
/*  644 */       throw SQLError.createSQLException(Messages.getString("StringUtils.5") + encoding + Messages.getString("StringUtils.6"), "S1009", exceptionInterceptor);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] getBytesWrapped(String s, char beginWrap, char endWrap, SingleByteCharsetConverter converter, String encoding, String serverEncoding, boolean parserKnowsUnicode, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*      */     try {
/*      */       byte[] b;
/*  658 */       if (converter != null) {
/*  659 */         b = converter.toBytesWrapped(s, beginWrap, endWrap);
/*  660 */       } else if (encoding == null) {
/*  661 */         StringBuilder strBuilder = new StringBuilder(s.length() + 2);
/*  662 */         strBuilder.append(beginWrap);
/*  663 */         strBuilder.append(s);
/*  664 */         strBuilder.append(endWrap);
/*      */         
/*  666 */         b = getBytes(strBuilder.toString());
/*      */       } else {
/*  668 */         StringBuilder strBuilder = new StringBuilder(s.length() + 2);
/*  669 */         strBuilder.append(beginWrap);
/*  670 */         strBuilder.append(s);
/*  671 */         strBuilder.append(endWrap);
/*      */         
/*  673 */         s = strBuilder.toString();
/*  674 */         b = getBytes(s, encoding);
/*      */         
/*  676 */         if (!parserKnowsUnicode && CharsetMapping.requiresEscapeEasternUnicode(encoding))
/*      */         {
/*  678 */           if (!encoding.equalsIgnoreCase(serverEncoding)) {
/*  679 */             b = escapeEasternUnicodeByteStream(b, s);
/*      */           }
/*      */         }
/*      */       } 
/*      */       
/*  684 */       return b;
/*  685 */     } catch (UnsupportedEncodingException uee) {
/*  686 */       throw SQLError.createSQLException(Messages.getString("StringUtils.10") + encoding + Messages.getString("StringUtils.11"), "S1009", exceptionInterceptor);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getInt(byte[] buf) throws NumberFormatException {
/*  692 */     return getInt(buf, 0, buf.length);
/*      */   }
/*      */   
/*      */   public static int getInt(byte[] buf, int offset, int endPos) throws NumberFormatException {
/*  696 */     int base = 10;
/*      */     
/*  698 */     int s = offset;
/*      */ 
/*      */     
/*  701 */     while (s < endPos && Character.isWhitespace((char)buf[s])) {
/*  702 */       s++;
/*      */     }
/*      */     
/*  705 */     if (s == endPos) {
/*  706 */       throw new NumberFormatException(toString(buf));
/*      */     }
/*      */ 
/*      */     
/*  710 */     boolean negative = false;
/*      */     
/*  712 */     if ((char)buf[s] == '-') {
/*  713 */       negative = true;
/*  714 */       s++;
/*  715 */     } else if ((char)buf[s] == '+') {
/*  716 */       s++;
/*      */     } 
/*      */ 
/*      */     
/*  720 */     int save = s;
/*      */     
/*  722 */     int cutoff = Integer.MAX_VALUE / base;
/*  723 */     int cutlim = Integer.MAX_VALUE % base;
/*      */     
/*  725 */     if (negative) {
/*  726 */       cutlim++;
/*      */     }
/*      */     
/*  729 */     boolean overflow = false;
/*      */     
/*  731 */     int i = 0;
/*      */     
/*  733 */     for (; s < endPos; s++) {
/*  734 */       char c = (char)buf[s];
/*      */       
/*  736 */       if (Character.isDigit(c)) {
/*  737 */         c = (char)(c - 48);
/*  738 */       } else if (Character.isLetter(c)) {
/*  739 */         c = (char)(Character.toUpperCase(c) - 65 + 10);
/*      */       } else {
/*      */         break;
/*      */       } 
/*      */       
/*  744 */       if (c >= base) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  749 */       if (i > cutoff || (i == cutoff && c > cutlim)) {
/*  750 */         overflow = true;
/*      */       } else {
/*  752 */         i *= base;
/*  753 */         i += c;
/*      */       } 
/*      */     } 
/*      */     
/*  757 */     if (s == save) {
/*  758 */       throw new NumberFormatException(toString(buf));
/*      */     }
/*      */     
/*  761 */     if (overflow) {
/*  762 */       throw new NumberFormatException(toString(buf));
/*      */     }
/*      */ 
/*      */     
/*  766 */     return negative ? -i : i;
/*      */   }
/*      */   
/*      */   public static long getLong(byte[] buf) throws NumberFormatException {
/*  770 */     return getLong(buf, 0, buf.length);
/*      */   }
/*      */   
/*      */   public static long getLong(byte[] buf, int offset, int endpos) throws NumberFormatException {
/*  774 */     int base = 10;
/*      */     
/*  776 */     int s = offset;
/*      */ 
/*      */     
/*  779 */     while (s < endpos && Character.isWhitespace((char)buf[s])) {
/*  780 */       s++;
/*      */     }
/*      */     
/*  783 */     if (s == endpos) {
/*  784 */       throw new NumberFormatException(toString(buf));
/*      */     }
/*      */ 
/*      */     
/*  788 */     boolean negative = false;
/*      */     
/*  790 */     if ((char)buf[s] == '-') {
/*  791 */       negative = true;
/*  792 */       s++;
/*  793 */     } else if ((char)buf[s] == '+') {
/*  794 */       s++;
/*      */     } 
/*      */ 
/*      */     
/*  798 */     int save = s;
/*      */     
/*  800 */     long cutoff = Long.MAX_VALUE / base;
/*  801 */     long cutlim = (int)(Long.MAX_VALUE % base);
/*      */     
/*  803 */     if (negative) {
/*  804 */       cutlim++;
/*      */     }
/*      */     
/*  807 */     boolean overflow = false;
/*  808 */     long i = 0L;
/*      */     
/*  810 */     for (; s < endpos; s++) {
/*  811 */       char c = (char)buf[s];
/*      */       
/*  813 */       if (Character.isDigit(c)) {
/*  814 */         c = (char)(c - 48);
/*  815 */       } else if (Character.isLetter(c)) {
/*  816 */         c = (char)(Character.toUpperCase(c) - 65 + 10);
/*      */       } else {
/*      */         break;
/*      */       } 
/*      */       
/*  821 */       if (c >= base) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  826 */       if (i > cutoff || (i == cutoff && c > cutlim)) {
/*  827 */         overflow = true;
/*      */       } else {
/*  829 */         i *= base;
/*  830 */         i += c;
/*      */       } 
/*      */     } 
/*      */     
/*  834 */     if (s == save) {
/*  835 */       throw new NumberFormatException(toString(buf));
/*      */     }
/*      */     
/*  838 */     if (overflow) {
/*  839 */       throw new NumberFormatException(toString(buf));
/*      */     }
/*      */ 
/*      */     
/*  843 */     return negative ? -i : i;
/*      */   }
/*      */   
/*      */   public static short getShort(byte[] buf) throws NumberFormatException {
/*  847 */     return getShort(buf, 0, buf.length);
/*      */   }
/*      */   
/*      */   public static short getShort(byte[] buf, int offset, int endpos) throws NumberFormatException {
/*  851 */     short base = 10;
/*      */     
/*  853 */     int s = offset;
/*      */ 
/*      */     
/*  856 */     while (s < endpos && Character.isWhitespace((char)buf[s])) {
/*  857 */       s++;
/*      */     }
/*      */     
/*  860 */     if (s == endpos) {
/*  861 */       throw new NumberFormatException(toString(buf));
/*      */     }
/*      */ 
/*      */     
/*  865 */     boolean negative = false;
/*      */     
/*  867 */     if ((char)buf[s] == '-') {
/*  868 */       negative = true;
/*  869 */       s++;
/*  870 */     } else if ((char)buf[s] == '+') {
/*  871 */       s++;
/*      */     } 
/*      */ 
/*      */     
/*  875 */     int save = s;
/*      */     
/*  877 */     short cutoff = (short)(32767 / base);
/*  878 */     short cutlim = (short)(32767 % base);
/*      */     
/*  880 */     if (negative) {
/*  881 */       cutlim = (short)(cutlim + 1);
/*      */     }
/*      */     
/*  884 */     boolean overflow = false;
/*  885 */     short i = 0;
/*      */     
/*  887 */     for (; s < endpos; s++) {
/*  888 */       char c = (char)buf[s];
/*      */       
/*  890 */       if (Character.isDigit(c)) {
/*  891 */         c = (char)(c - 48);
/*  892 */       } else if (Character.isLetter(c)) {
/*  893 */         c = (char)(Character.toUpperCase(c) - 65 + 10);
/*      */       } else {
/*      */         break;
/*      */       } 
/*      */       
/*  898 */       if (c >= base) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  903 */       if (i > cutoff || (i == cutoff && c > cutlim)) {
/*  904 */         overflow = true;
/*      */       } else {
/*  906 */         i = (short)(i * base);
/*  907 */         i = (short)(i + c);
/*      */       } 
/*      */     } 
/*      */     
/*  911 */     if (s == save) {
/*  912 */       throw new NumberFormatException(toString(buf));
/*      */     }
/*      */     
/*  915 */     if (overflow) {
/*  916 */       throw new NumberFormatException(toString(buf));
/*      */     }
/*      */ 
/*      */     
/*  920 */     return negative ? (short)-i : i;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfIgnoreCase(String searchIn, String searchFor) {
/*  933 */     return indexOfIgnoreCase(0, searchIn, searchFor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfIgnoreCase(int startingPosition, String searchIn, String searchFor) {
/*  948 */     if (searchIn == null || searchFor == null) {
/*  949 */       return -1;
/*      */     }
/*      */     
/*  952 */     int searchInLength = searchIn.length();
/*  953 */     int searchForLength = searchFor.length();
/*  954 */     int stopSearchingAt = searchInLength - searchForLength;
/*      */     
/*  956 */     if (startingPosition > stopSearchingAt || searchForLength == 0) {
/*  957 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*  961 */     char firstCharOfSearchForUc = Character.toUpperCase(searchFor.charAt(0));
/*  962 */     char firstCharOfSearchForLc = Character.toLowerCase(searchFor.charAt(0));
/*      */     
/*  964 */     for (int i = startingPosition; i <= stopSearchingAt; i++) {
/*  965 */       if (isCharAtPosNotEqualIgnoreCase(searchIn, i, firstCharOfSearchForUc, firstCharOfSearchForLc))
/*      */       {
/*  967 */         while (++i <= stopSearchingAt && isCharAtPosNotEqualIgnoreCase(searchIn, i, firstCharOfSearchForUc, firstCharOfSearchForLc));
/*      */       }
/*      */ 
/*      */       
/*  971 */       if (i <= stopSearchingAt && startsWithIgnoreCase(searchIn, i, searchFor)) {
/*  972 */         return i;
/*      */       }
/*      */     } 
/*      */     
/*  976 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfIgnoreCase(int startingPosition, String searchIn, String[] searchForSequence, String openingMarkers, String closingMarkers, Set<SearchMode> searchMode) {
/* 1004 */     if (searchIn == null || searchForSequence == null) {
/* 1005 */       return -1;
/*      */     }
/*      */     
/* 1008 */     int searchInLength = searchIn.length();
/* 1009 */     int searchForLength = 0;
/* 1010 */     for (String searchForPart : searchForSequence) {
/* 1011 */       searchForLength += searchForPart.length();
/*      */     }
/*      */     
/* 1014 */     if (searchForLength == 0) {
/* 1015 */       return -1;
/*      */     }
/*      */     
/* 1018 */     int searchForWordsCount = searchForSequence.length;
/* 1019 */     searchForLength += (searchForWordsCount > 0) ? (searchForWordsCount - 1) : 0;
/* 1020 */     int stopSearchingAt = searchInLength - searchForLength;
/*      */     
/* 1022 */     if (startingPosition > stopSearchingAt) {
/* 1023 */       return -1;
/*      */     }
/*      */     
/* 1026 */     if (searchMode.contains(SearchMode.SKIP_BETWEEN_MARKERS) && (openingMarkers == null || closingMarkers == null || openingMarkers.length() != closingMarkers.length()))
/*      */     {
/* 1028 */       throw new IllegalArgumentException(Messages.getString("StringUtils.15", new String[] { openingMarkers, closingMarkers }));
/*      */     }
/*      */     
/* 1031 */     if (Character.isWhitespace(searchForSequence[0].charAt(0)) && searchMode.contains(SearchMode.SKIP_WHITE_SPACE)) {
/*      */       
/* 1033 */       searchMode = EnumSet.copyOf(searchMode);
/* 1034 */       searchMode.remove(SearchMode.SKIP_WHITE_SPACE);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1039 */     Set<SearchMode> searchMode2 = EnumSet.of(SearchMode.SKIP_WHITE_SPACE);
/* 1040 */     searchMode2.addAll(searchMode);
/* 1041 */     searchMode2.remove(SearchMode.SKIP_BETWEEN_MARKERS);
/*      */     
/* 1043 */     for (int positionOfFirstWord = startingPosition; positionOfFirstWord <= stopSearchingAt; positionOfFirstWord++) {
/* 1044 */       positionOfFirstWord = indexOfIgnoreCase(positionOfFirstWord, searchIn, searchForSequence[0], openingMarkers, closingMarkers, searchMode);
/*      */       
/* 1046 */       if (positionOfFirstWord == -1 || positionOfFirstWord > stopSearchingAt) {
/* 1047 */         return -1;
/*      */       }
/*      */       
/* 1050 */       int startingPositionForNextWord = positionOfFirstWord + searchForSequence[0].length();
/* 1051 */       int wc = 0;
/* 1052 */       boolean match = true;
/* 1053 */       while (++wc < searchForWordsCount && match) {
/* 1054 */         int positionOfNextWord = indexOfNextChar(startingPositionForNextWord, searchInLength - 1, searchIn, null, null, null, searchMode2);
/* 1055 */         if (startingPositionForNextWord == positionOfNextWord || !startsWithIgnoreCase(searchIn, positionOfNextWord, searchForSequence[wc])) {
/*      */           
/* 1057 */           match = false; continue;
/*      */         } 
/* 1059 */         startingPositionForNextWord = positionOfNextWord + searchForSequence[wc].length();
/*      */       } 
/*      */ 
/*      */       
/* 1063 */       if (match) {
/* 1064 */         return positionOfFirstWord;
/*      */       }
/*      */     } 
/*      */     
/* 1068 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfIgnoreCase(int startingPosition, String searchIn, String searchFor, String openingMarkers, String closingMarkers, Set<SearchMode> searchMode) {
/* 1091 */     return indexOfIgnoreCase(startingPosition, searchIn, searchFor, openingMarkers, closingMarkers, "", searchMode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int indexOfIgnoreCase(int startingPosition, String searchIn, String searchFor, String openingMarkers, String closingMarkers, String overridingMarkers, Set<SearchMode> searchMode) {
/* 1118 */     if (searchIn == null || searchFor == null) {
/* 1119 */       return -1;
/*      */     }
/*      */     
/* 1122 */     int searchInLength = searchIn.length();
/* 1123 */     int searchForLength = searchFor.length();
/* 1124 */     int stopSearchingAt = searchInLength - searchForLength;
/*      */     
/* 1126 */     if (startingPosition > stopSearchingAt || searchForLength == 0) {
/* 1127 */       return -1;
/*      */     }
/*      */     
/* 1130 */     if (searchMode.contains(SearchMode.SKIP_BETWEEN_MARKERS)) {
/* 1131 */       if (openingMarkers == null || closingMarkers == null || openingMarkers.length() != closingMarkers.length()) {
/* 1132 */         throw new IllegalArgumentException(Messages.getString("StringUtils.15", new String[] { openingMarkers, closingMarkers }));
/*      */       }
/* 1134 */       if (overridingMarkers == null) {
/* 1135 */         throw new IllegalArgumentException(Messages.getString("StringUtils.16", new String[] { overridingMarkers, openingMarkers }));
/*      */       }
/* 1137 */       for (char c : overridingMarkers.toCharArray()) {
/* 1138 */         if (openingMarkers.indexOf(c) == -1) {
/* 1139 */           throw new IllegalArgumentException(Messages.getString("StringUtils.16", new String[] { overridingMarkers, openingMarkers }));
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1145 */     char firstCharOfSearchForUc = Character.toUpperCase(searchFor.charAt(0));
/* 1146 */     char firstCharOfSearchForLc = Character.toLowerCase(searchFor.charAt(0));
/*      */     
/* 1148 */     if (Character.isWhitespace(firstCharOfSearchForLc) && searchMode.contains(SearchMode.SKIP_WHITE_SPACE)) {
/*      */       
/* 1150 */       searchMode = EnumSet.copyOf(searchMode);
/* 1151 */       searchMode.remove(SearchMode.SKIP_WHITE_SPACE);
/*      */     } 
/*      */     
/* 1154 */     for (int i = startingPosition; i <= stopSearchingAt; i++) {
/* 1155 */       i = indexOfNextChar(i, stopSearchingAt, searchIn, openingMarkers, closingMarkers, overridingMarkers, searchMode);
/*      */       
/* 1157 */       if (i == -1) {
/* 1158 */         return -1;
/*      */       }
/*      */       
/* 1161 */       char c = searchIn.charAt(i);
/*      */       
/* 1163 */       if (isCharEqualIgnoreCase(c, firstCharOfSearchForUc, firstCharOfSearchForLc) && startsWithIgnoreCase(searchIn, i, searchFor)) {
/* 1164 */         return i;
/*      */       }
/*      */     } 
/*      */     
/* 1168 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int indexOfNextChar(int startingPosition, int stopPosition, String searchIn, String openingMarkers, String closingMarkers, String overridingMarkers, Set<SearchMode> searchMode) {
/*      */     // Byte code:
/*      */     //   0: aload_2
/*      */     //   1: ifnonnull -> 6
/*      */     //   4: iconst_m1
/*      */     //   5: ireturn
/*      */     //   6: aload_2
/*      */     //   7: invokevirtual length : ()I
/*      */     //   10: istore #7
/*      */     //   12: iload_0
/*      */     //   13: iload #7
/*      */     //   15: if_icmplt -> 20
/*      */     //   18: iconst_m1
/*      */     //   19: ireturn
/*      */     //   20: iconst_0
/*      */     //   21: istore #8
/*      */     //   23: aload_2
/*      */     //   24: iload_0
/*      */     //   25: invokevirtual charAt : (I)C
/*      */     //   28: istore #9
/*      */     //   30: iload_0
/*      */     //   31: iconst_1
/*      */     //   32: iadd
/*      */     //   33: iload #7
/*      */     //   35: if_icmpge -> 48
/*      */     //   38: aload_2
/*      */     //   39: iload_0
/*      */     //   40: iconst_1
/*      */     //   41: iadd
/*      */     //   42: invokevirtual charAt : (I)C
/*      */     //   45: goto -> 49
/*      */     //   48: iconst_0
/*      */     //   49: istore #10
/*      */     //   51: iload_0
/*      */     //   52: istore #11
/*      */     //   54: iload #11
/*      */     //   56: iload_1
/*      */     //   57: if_icmpgt -> 998
/*      */     //   60: iload #9
/*      */     //   62: istore #8
/*      */     //   64: iload #10
/*      */     //   66: istore #9
/*      */     //   68: iload #11
/*      */     //   70: iconst_2
/*      */     //   71: iadd
/*      */     //   72: iload #7
/*      */     //   74: if_icmpge -> 88
/*      */     //   77: aload_2
/*      */     //   78: iload #11
/*      */     //   80: iconst_2
/*      */     //   81: iadd
/*      */     //   82: invokevirtual charAt : (I)C
/*      */     //   85: goto -> 89
/*      */     //   88: iconst_0
/*      */     //   89: istore #10
/*      */     //   91: iconst_0
/*      */     //   92: istore #12
/*      */     //   94: iconst_m1
/*      */     //   95: istore #13
/*      */     //   97: aload #6
/*      */     //   99: getstatic com/mysql/jdbc/StringUtils$SearchMode.ALLOW_BACKSLASH_ESCAPE : Lcom/mysql/jdbc/StringUtils$SearchMode;
/*      */     //   102: invokeinterface contains : (Ljava/lang/Object;)Z
/*      */     //   107: ifeq -> 150
/*      */     //   110: iload #8
/*      */     //   112: bipush #92
/*      */     //   114: if_icmpne -> 150
/*      */     //   117: iinc #11, 1
/*      */     //   120: iload #10
/*      */     //   122: istore #9
/*      */     //   124: iload #11
/*      */     //   126: iconst_2
/*      */     //   127: iadd
/*      */     //   128: iload #7
/*      */     //   130: if_icmpge -> 144
/*      */     //   133: aload_2
/*      */     //   134: iload #11
/*      */     //   136: iconst_2
/*      */     //   137: iadd
/*      */     //   138: invokevirtual charAt : (I)C
/*      */     //   141: goto -> 145
/*      */     //   144: iconst_0
/*      */     //   145: istore #10
/*      */     //   147: goto -> 992
/*      */     //   150: aload #6
/*      */     //   152: getstatic com/mysql/jdbc/StringUtils$SearchMode.SKIP_BETWEEN_MARKERS : Lcom/mysql/jdbc/StringUtils$SearchMode;
/*      */     //   155: invokeinterface contains : (Ljava/lang/Object;)Z
/*      */     //   160: ifeq -> 462
/*      */     //   163: aload_3
/*      */     //   164: iload #8
/*      */     //   166: invokevirtual indexOf : (I)I
/*      */     //   169: dup
/*      */     //   170: istore #13
/*      */     //   172: iconst_m1
/*      */     //   173: if_icmpeq -> 462
/*      */     //   176: iconst_0
/*      */     //   177: istore #14
/*      */     //   179: iload #8
/*      */     //   181: istore #15
/*      */     //   183: aload #4
/*      */     //   185: iload #13
/*      */     //   187: invokevirtual charAt : (I)C
/*      */     //   190: istore #16
/*      */     //   192: aload #5
/*      */     //   194: iload #15
/*      */     //   196: invokevirtual indexOf : (I)I
/*      */     //   199: iconst_m1
/*      */     //   200: if_icmpeq -> 207
/*      */     //   203: iconst_1
/*      */     //   204: goto -> 208
/*      */     //   207: iconst_0
/*      */     //   208: istore #17
/*      */     //   210: iinc #11, 1
/*      */     //   213: iload #11
/*      */     //   215: iload_1
/*      */     //   216: if_icmpgt -> 413
/*      */     //   219: aload_2
/*      */     //   220: iload #11
/*      */     //   222: invokevirtual charAt : (I)C
/*      */     //   225: dup
/*      */     //   226: istore #8
/*      */     //   228: iload #16
/*      */     //   230: if_icmpne -> 238
/*      */     //   233: iload #14
/*      */     //   235: ifeq -> 413
/*      */     //   238: iload #17
/*      */     //   240: ifne -> 361
/*      */     //   243: aload #5
/*      */     //   245: iload #8
/*      */     //   247: invokevirtual indexOf : (I)I
/*      */     //   250: iconst_m1
/*      */     //   251: if_icmpeq -> 361
/*      */     //   254: aload_3
/*      */     //   255: iload #8
/*      */     //   257: invokevirtual indexOf : (I)I
/*      */     //   260: istore #18
/*      */     //   262: iconst_0
/*      */     //   263: istore #19
/*      */     //   265: iload #8
/*      */     //   267: istore #20
/*      */     //   269: aload #4
/*      */     //   271: iload #18
/*      */     //   273: invokevirtual charAt : (I)C
/*      */     //   276: istore #21
/*      */     //   278: iinc #11, 1
/*      */     //   281: iload #11
/*      */     //   283: iload_1
/*      */     //   284: if_icmpgt -> 358
/*      */     //   287: aload_2
/*      */     //   288: iload #11
/*      */     //   290: invokevirtual charAt : (I)C
/*      */     //   293: dup
/*      */     //   294: istore #8
/*      */     //   296: iload #21
/*      */     //   298: if_icmpne -> 306
/*      */     //   301: iload #19
/*      */     //   303: ifeq -> 358
/*      */     //   306: iload #8
/*      */     //   308: iload #20
/*      */     //   310: if_icmpne -> 319
/*      */     //   313: iinc #19, 1
/*      */     //   316: goto -> 278
/*      */     //   319: iload #8
/*      */     //   321: iload #21
/*      */     //   323: if_icmpne -> 332
/*      */     //   326: iinc #19, -1
/*      */     //   329: goto -> 278
/*      */     //   332: aload #6
/*      */     //   334: getstatic com/mysql/jdbc/StringUtils$SearchMode.ALLOW_BACKSLASH_ESCAPE : Lcom/mysql/jdbc/StringUtils$SearchMode;
/*      */     //   337: invokeinterface contains : (Ljava/lang/Object;)Z
/*      */     //   342: ifeq -> 278
/*      */     //   345: iload #8
/*      */     //   347: bipush #92
/*      */     //   349: if_icmpne -> 278
/*      */     //   352: iinc #11, 1
/*      */     //   355: goto -> 278
/*      */     //   358: goto -> 210
/*      */     //   361: iload #8
/*      */     //   363: iload #15
/*      */     //   365: if_icmpne -> 374
/*      */     //   368: iinc #14, 1
/*      */     //   371: goto -> 210
/*      */     //   374: iload #8
/*      */     //   376: iload #16
/*      */     //   378: if_icmpne -> 387
/*      */     //   381: iinc #14, -1
/*      */     //   384: goto -> 210
/*      */     //   387: aload #6
/*      */     //   389: getstatic com/mysql/jdbc/StringUtils$SearchMode.ALLOW_BACKSLASH_ESCAPE : Lcom/mysql/jdbc/StringUtils$SearchMode;
/*      */     //   392: invokeinterface contains : (Ljava/lang/Object;)Z
/*      */     //   397: ifeq -> 210
/*      */     //   400: iload #8
/*      */     //   402: bipush #92
/*      */     //   404: if_icmpne -> 210
/*      */     //   407: iinc #11, 1
/*      */     //   410: goto -> 210
/*      */     //   413: iload #11
/*      */     //   415: iconst_1
/*      */     //   416: iadd
/*      */     //   417: iload #7
/*      */     //   419: if_icmpge -> 433
/*      */     //   422: aload_2
/*      */     //   423: iload #11
/*      */     //   425: iconst_1
/*      */     //   426: iadd
/*      */     //   427: invokevirtual charAt : (I)C
/*      */     //   430: goto -> 434
/*      */     //   433: iconst_0
/*      */     //   434: istore #9
/*      */     //   436: iload #11
/*      */     //   438: iconst_2
/*      */     //   439: iadd
/*      */     //   440: iload #7
/*      */     //   442: if_icmpge -> 456
/*      */     //   445: aload_2
/*      */     //   446: iload #11
/*      */     //   448: iconst_2
/*      */     //   449: iadd
/*      */     //   450: invokevirtual charAt : (I)C
/*      */     //   453: goto -> 457
/*      */     //   456: iconst_0
/*      */     //   457: istore #10
/*      */     //   459: goto -> 992
/*      */     //   462: aload #6
/*      */     //   464: getstatic com/mysql/jdbc/StringUtils$SearchMode.SKIP_BLOCK_COMMENTS : Lcom/mysql/jdbc/StringUtils$SearchMode;
/*      */     //   467: invokeinterface contains : (Ljava/lang/Object;)Z
/*      */     //   472: ifeq -> 661
/*      */     //   475: iload #8
/*      */     //   477: bipush #47
/*      */     //   479: if_icmpne -> 661
/*      */     //   482: iload #9
/*      */     //   484: bipush #42
/*      */     //   486: if_icmpne -> 661
/*      */     //   489: iload #10
/*      */     //   491: bipush #33
/*      */     //   493: if_icmpeq -> 554
/*      */     //   496: iinc #11, 1
/*      */     //   499: iinc #11, 1
/*      */     //   502: iload #11
/*      */     //   504: iload_1
/*      */     //   505: if_icmpgt -> 548
/*      */     //   508: aload_2
/*      */     //   509: iload #11
/*      */     //   511: invokevirtual charAt : (I)C
/*      */     //   514: bipush #42
/*      */     //   516: if_icmpne -> 499
/*      */     //   519: iload #11
/*      */     //   521: iconst_1
/*      */     //   522: iadd
/*      */     //   523: iload #7
/*      */     //   525: if_icmpge -> 539
/*      */     //   528: aload_2
/*      */     //   529: iload #11
/*      */     //   531: iconst_1
/*      */     //   532: iadd
/*      */     //   533: invokevirtual charAt : (I)C
/*      */     //   536: goto -> 540
/*      */     //   539: iconst_0
/*      */     //   540: bipush #47
/*      */     //   542: if_icmpeq -> 548
/*      */     //   545: goto -> 499
/*      */     //   548: iinc #11, 1
/*      */     //   551: goto -> 612
/*      */     //   554: iinc #11, 1
/*      */     //   557: iinc #11, 1
/*      */     //   560: iconst_1
/*      */     //   561: istore #14
/*      */     //   563: iload #14
/*      */     //   565: iconst_5
/*      */     //   566: if_icmpgt -> 603
/*      */     //   569: iload #11
/*      */     //   571: iload #14
/*      */     //   573: iadd
/*      */     //   574: iload #7
/*      */     //   576: if_icmpge -> 603
/*      */     //   579: aload_2
/*      */     //   580: iload #11
/*      */     //   582: iload #14
/*      */     //   584: iadd
/*      */     //   585: invokevirtual charAt : (I)C
/*      */     //   588: invokestatic isDigit : (C)Z
/*      */     //   591: ifne -> 597
/*      */     //   594: goto -> 603
/*      */     //   597: iinc #14, 1
/*      */     //   600: goto -> 563
/*      */     //   603: iload #14
/*      */     //   605: iconst_5
/*      */     //   606: if_icmpne -> 612
/*      */     //   609: iinc #11, 5
/*      */     //   612: iload #11
/*      */     //   614: iconst_1
/*      */     //   615: iadd
/*      */     //   616: iload #7
/*      */     //   618: if_icmpge -> 632
/*      */     //   621: aload_2
/*      */     //   622: iload #11
/*      */     //   624: iconst_1
/*      */     //   625: iadd
/*      */     //   626: invokevirtual charAt : (I)C
/*      */     //   629: goto -> 633
/*      */     //   632: iconst_0
/*      */     //   633: istore #9
/*      */     //   635: iload #11
/*      */     //   637: iconst_2
/*      */     //   638: iadd
/*      */     //   639: iload #7
/*      */     //   641: if_icmpge -> 655
/*      */     //   644: aload_2
/*      */     //   645: iload #11
/*      */     //   647: iconst_2
/*      */     //   648: iadd
/*      */     //   649: invokevirtual charAt : (I)C
/*      */     //   652: goto -> 656
/*      */     //   655: iconst_0
/*      */     //   656: istore #10
/*      */     //   658: goto -> 992
/*      */     //   661: aload #6
/*      */     //   663: getstatic com/mysql/jdbc/StringUtils$SearchMode.SKIP_BLOCK_COMMENTS : Lcom/mysql/jdbc/StringUtils$SearchMode;
/*      */     //   666: invokeinterface contains : (Ljava/lang/Object;)Z
/*      */     //   671: ifeq -> 721
/*      */     //   674: iload #8
/*      */     //   676: bipush #42
/*      */     //   678: if_icmpne -> 721
/*      */     //   681: iload #9
/*      */     //   683: bipush #47
/*      */     //   685: if_icmpne -> 721
/*      */     //   688: iinc #11, 1
/*      */     //   691: iload #10
/*      */     //   693: istore #9
/*      */     //   695: iload #11
/*      */     //   697: iconst_2
/*      */     //   698: iadd
/*      */     //   699: iload #7
/*      */     //   701: if_icmpge -> 715
/*      */     //   704: aload_2
/*      */     //   705: iload #11
/*      */     //   707: iconst_2
/*      */     //   708: iadd
/*      */     //   709: invokevirtual charAt : (I)C
/*      */     //   712: goto -> 716
/*      */     //   715: iconst_0
/*      */     //   716: istore #10
/*      */     //   718: goto -> 992
/*      */     //   721: aload #6
/*      */     //   723: getstatic com/mysql/jdbc/StringUtils$SearchMode.SKIP_LINE_COMMENTS : Lcom/mysql/jdbc/StringUtils$SearchMode;
/*      */     //   726: invokeinterface contains : (Ljava/lang/Object;)Z
/*      */     //   731: ifeq -> 968
/*      */     //   734: iload #8
/*      */     //   736: bipush #45
/*      */     //   738: if_icmpne -> 779
/*      */     //   741: iload #9
/*      */     //   743: bipush #45
/*      */     //   745: if_icmpne -> 779
/*      */     //   748: iload #10
/*      */     //   750: invokestatic isWhitespace : (C)Z
/*      */     //   753: ifne -> 786
/*      */     //   756: iload #10
/*      */     //   758: bipush #59
/*      */     //   760: if_icmpne -> 767
/*      */     //   763: iconst_1
/*      */     //   764: goto -> 768
/*      */     //   767: iconst_0
/*      */     //   768: dup
/*      */     //   769: istore #12
/*      */     //   771: ifne -> 786
/*      */     //   774: iload #10
/*      */     //   776: ifeq -> 786
/*      */     //   779: iload #8
/*      */     //   781: bipush #35
/*      */     //   783: if_icmpne -> 968
/*      */     //   786: iload #12
/*      */     //   788: ifeq -> 846
/*      */     //   791: iinc #11, 1
/*      */     //   794: iinc #11, 1
/*      */     //   797: iload #11
/*      */     //   799: iconst_1
/*      */     //   800: iadd
/*      */     //   801: iload #7
/*      */     //   803: if_icmpge -> 817
/*      */     //   806: aload_2
/*      */     //   807: iload #11
/*      */     //   809: iconst_1
/*      */     //   810: iadd
/*      */     //   811: invokevirtual charAt : (I)C
/*      */     //   814: goto -> 818
/*      */     //   817: iconst_0
/*      */     //   818: istore #9
/*      */     //   820: iload #11
/*      */     //   822: iconst_2
/*      */     //   823: iadd
/*      */     //   824: iload #7
/*      */     //   826: if_icmpge -> 840
/*      */     //   829: aload_2
/*      */     //   830: iload #11
/*      */     //   832: iconst_2
/*      */     //   833: iadd
/*      */     //   834: invokevirtual charAt : (I)C
/*      */     //   837: goto -> 841
/*      */     //   840: iconst_0
/*      */     //   841: istore #10
/*      */     //   843: goto -> 992
/*      */     //   846: iinc #11, 1
/*      */     //   849: iload #11
/*      */     //   851: iload_1
/*      */     //   852: if_icmpgt -> 879
/*      */     //   855: aload_2
/*      */     //   856: iload #11
/*      */     //   858: invokevirtual charAt : (I)C
/*      */     //   861: dup
/*      */     //   862: istore #8
/*      */     //   864: bipush #10
/*      */     //   866: if_icmpeq -> 879
/*      */     //   869: iload #8
/*      */     //   871: bipush #13
/*      */     //   873: if_icmpeq -> 879
/*      */     //   876: goto -> 846
/*      */     //   879: iload #11
/*      */     //   881: iconst_1
/*      */     //   882: iadd
/*      */     //   883: iload #7
/*      */     //   885: if_icmpge -> 899
/*      */     //   888: aload_2
/*      */     //   889: iload #11
/*      */     //   891: iconst_1
/*      */     //   892: iadd
/*      */     //   893: invokevirtual charAt : (I)C
/*      */     //   896: goto -> 900
/*      */     //   899: iconst_0
/*      */     //   900: istore #9
/*      */     //   902: iload #8
/*      */     //   904: bipush #13
/*      */     //   906: if_icmpne -> 942
/*      */     //   909: iload #9
/*      */     //   911: bipush #10
/*      */     //   913: if_icmpne -> 942
/*      */     //   916: iinc #11, 1
/*      */     //   919: iload #11
/*      */     //   921: iconst_1
/*      */     //   922: iadd
/*      */     //   923: iload #7
/*      */     //   925: if_icmpge -> 939
/*      */     //   928: aload_2
/*      */     //   929: iload #11
/*      */     //   931: iconst_1
/*      */     //   932: iadd
/*      */     //   933: invokevirtual charAt : (I)C
/*      */     //   936: goto -> 940
/*      */     //   939: iconst_0
/*      */     //   940: istore #9
/*      */     //   942: iload #11
/*      */     //   944: iconst_2
/*      */     //   945: iadd
/*      */     //   946: iload #7
/*      */     //   948: if_icmpge -> 962
/*      */     //   951: aload_2
/*      */     //   952: iload #11
/*      */     //   954: iconst_2
/*      */     //   955: iadd
/*      */     //   956: invokevirtual charAt : (I)C
/*      */     //   959: goto -> 963
/*      */     //   962: iconst_0
/*      */     //   963: istore #10
/*      */     //   965: goto -> 992
/*      */     //   968: aload #6
/*      */     //   970: getstatic com/mysql/jdbc/StringUtils$SearchMode.SKIP_WHITE_SPACE : Lcom/mysql/jdbc/StringUtils$SearchMode;
/*      */     //   973: invokeinterface contains : (Ljava/lang/Object;)Z
/*      */     //   978: ifeq -> 989
/*      */     //   981: iload #8
/*      */     //   983: invokestatic isWhitespace : (C)Z
/*      */     //   986: ifne -> 992
/*      */     //   989: iload #11
/*      */     //   991: ireturn
/*      */     //   992: iinc #11, 1
/*      */     //   995: goto -> 54
/*      */     //   998: iconst_m1
/*      */     //   999: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1191	-> 0
/*      */     //   #1192	-> 4
/*      */     //   #1195	-> 6
/*      */     //   #1197	-> 12
/*      */     //   #1198	-> 18
/*      */     //   #1201	-> 20
/*      */     //   #1202	-> 23
/*      */     //   #1203	-> 30
/*      */     //   #1205	-> 51
/*      */     //   #1206	-> 60
/*      */     //   #1207	-> 64
/*      */     //   #1208	-> 68
/*      */     //   #1210	-> 91
/*      */     //   #1211	-> 94
/*      */     //   #1213	-> 97
/*      */     //   #1214	-> 117
/*      */     //   #1216	-> 120
/*      */     //   #1217	-> 124
/*      */     //   #1219	-> 150
/*      */     //   #1221	-> 176
/*      */     //   #1222	-> 179
/*      */     //   #1223	-> 183
/*      */     //   #1224	-> 192
/*      */     //   #1225	-> 210
/*      */     //   #1226	-> 238
/*      */     //   #1228	-> 254
/*      */     //   #1229	-> 262
/*      */     //   #1230	-> 265
/*      */     //   #1231	-> 269
/*      */     //   #1232	-> 278
/*      */     //   #1234	-> 306
/*      */     //   #1235	-> 313
/*      */     //   #1236	-> 319
/*      */     //   #1237	-> 326
/*      */     //   #1238	-> 332
/*      */     //   #1239	-> 352
/*      */     //   #1242	-> 358
/*      */     //   #1243	-> 368
/*      */     //   #1244	-> 374
/*      */     //   #1245	-> 381
/*      */     //   #1246	-> 387
/*      */     //   #1247	-> 407
/*      */     //   #1251	-> 413
/*      */     //   #1252	-> 436
/*      */     //   #1254	-> 459
/*      */     //   #1255	-> 489
/*      */     //   #1257	-> 496
/*      */     //   #1259	-> 499
/*      */     //   #1262	-> 548
/*      */     //   #1266	-> 554
/*      */     //   #1267	-> 557
/*      */     //   #1269	-> 560
/*      */     //   #1270	-> 563
/*      */     //   #1271	-> 569
/*      */     //   #1272	-> 594
/*      */     //   #1270	-> 597
/*      */     //   #1275	-> 603
/*      */     //   #1276	-> 609
/*      */     //   #1280	-> 612
/*      */     //   #1281	-> 635
/*      */     //   #1283	-> 661
/*      */     //   #1286	-> 688
/*      */     //   #1288	-> 691
/*      */     //   #1289	-> 695
/*      */     //   #1291	-> 721
/*      */     //   #1294	-> 786
/*      */     //   #1296	-> 791
/*      */     //   #1297	-> 794
/*      */     //   #1299	-> 797
/*      */     //   #1300	-> 820
/*      */     //   #1303	-> 846
/*      */     //   #1307	-> 879
/*      */     //   #1308	-> 902
/*      */     //   #1310	-> 916
/*      */     //   #1311	-> 919
/*      */     //   #1313	-> 942
/*      */     //   #1316	-> 968
/*      */     //   #1317	-> 989
/*      */     //   #1205	-> 992
/*      */     //   #1321	-> 998
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   262	96	18	overridingMarkerIndex	I
/*      */     //   265	93	19	overridingNestedMarkersCount	I
/*      */     //   269	89	20	overridingOpeningMarker	C
/*      */     //   278	80	21	overridingClosingMarker	C
/*      */     //   179	280	14	nestedMarkersCount	I
/*      */     //   183	276	15	openingMarker	C
/*      */     //   192	267	16	closingMarker	C
/*      */     //   210	249	17	outerIsAnOverridingMarker	Z
/*      */     //   563	49	14	j	I
/*      */     //   94	898	12	dashDashCommentImmediateEnd	Z
/*      */     //   97	895	13	markerIndex	I
/*      */     //   54	944	11	i	I
/*      */     //   0	1000	0	startingPosition	I
/*      */     //   0	1000	1	stopPosition	I
/*      */     //   0	1000	2	searchIn	Ljava/lang/String;
/*      */     //   0	1000	3	openingMarkers	Ljava/lang/String;
/*      */     //   0	1000	4	closingMarkers	Ljava/lang/String;
/*      */     //   0	1000	5	overridingMarkers	Ljava/lang/String;
/*      */     //   0	1000	6	searchMode	Ljava/util/Set;
/*      */     //   12	988	7	searchInLength	I
/*      */     //   23	977	8	c0	C
/*      */     //   30	970	9	c1	C
/*      */     //   51	949	10	c2	C
/*      */     // Local variable type table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	1000	6	searchMode	Ljava/util/Set<Lcom/mysql/jdbc/StringUtils$SearchMode;>;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isCharAtPosNotEqualIgnoreCase(String searchIn, int pos, char firstCharOfSearchForUc, char firstCharOfSearchForLc) {
/* 1325 */     return (Character.toLowerCase(searchIn.charAt(pos)) != firstCharOfSearchForLc && Character.toUpperCase(searchIn.charAt(pos)) != firstCharOfSearchForUc);
/*      */   }
/*      */   
/*      */   private static boolean isCharEqualIgnoreCase(char charToCompare, char compareToCharUC, char compareToCharLC) {
/* 1329 */     return (Character.toLowerCase(charToCompare) == compareToCharLC || Character.toUpperCase(charToCompare) == compareToCharUC);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> split(String stringToSplit, String delimiter, boolean trim) {
/* 1347 */     if (stringToSplit == null) {
/* 1348 */       return new ArrayList<String>();
/*      */     }
/*      */     
/* 1351 */     if (delimiter == null) {
/* 1352 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1355 */     StringTokenizer tokenizer = new StringTokenizer(stringToSplit, delimiter, false);
/*      */     
/* 1357 */     List<String> splitTokens = new ArrayList<String>(tokenizer.countTokens());
/*      */     
/* 1359 */     while (tokenizer.hasMoreTokens()) {
/* 1360 */       String token = tokenizer.nextToken();
/*      */       
/* 1362 */       if (trim) {
/* 1363 */         token = token.trim();
/*      */       }
/*      */       
/* 1366 */       splitTokens.add(token);
/*      */     } 
/*      */     
/* 1369 */     return splitTokens;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> split(String stringToSplit, String delimiter, String openingMarkers, String closingMarkers, boolean trim) {
/* 1391 */     return split(stringToSplit, delimiter, openingMarkers, closingMarkers, "", trim);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> split(String stringToSplit, String delimiter, String openingMarkers, String closingMarkers, String overridingMarkers, boolean trim) {
/* 1418 */     if (stringToSplit == null) {
/* 1419 */       return new ArrayList<String>();
/*      */     }
/*      */     
/* 1422 */     if (delimiter == null) {
/* 1423 */       throw new IllegalArgumentException();
/*      */     }
/*      */     
/* 1426 */     int delimPos = 0;
/* 1427 */     int currentPos = 0;
/*      */     
/* 1429 */     List<String> splitTokens = new ArrayList<String>();
/*      */ 
/*      */     
/* 1432 */     while ((delimPos = indexOfIgnoreCase(currentPos, stringToSplit, delimiter, openingMarkers, closingMarkers, overridingMarkers, SEARCH_MODE__MRK_COM_WS)) != -1) {
/* 1433 */       String token = stringToSplit.substring(currentPos, delimPos);
/*      */       
/* 1435 */       if (trim) {
/* 1436 */         token = token.trim();
/*      */       }
/*      */       
/* 1439 */       splitTokens.add(token);
/* 1440 */       currentPos = delimPos + 1;
/*      */     } 
/*      */     
/* 1443 */     if (currentPos < stringToSplit.length()) {
/* 1444 */       String token = stringToSplit.substring(currentPos);
/*      */       
/* 1446 */       if (trim) {
/* 1447 */         token = token.trim();
/*      */       }
/*      */       
/* 1450 */       splitTokens.add(token);
/*      */     } 
/*      */     
/* 1453 */     return splitTokens;
/*      */   }
/*      */   
/*      */   private static boolean startsWith(byte[] dataFrom, String chars) {
/* 1457 */     int charsLength = chars.length();
/*      */     
/* 1459 */     if (dataFrom.length < charsLength) {
/* 1460 */       return false;
/*      */     }
/* 1462 */     for (int i = 0; i < charsLength; i++) {
/* 1463 */       if (dataFrom[i] != chars.charAt(i)) {
/* 1464 */         return false;
/*      */       }
/*      */     } 
/* 1467 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean startsWithIgnoreCase(String searchIn, int startAt, String searchFor) {
/* 1485 */     return searchIn.regionMatches(true, startAt, searchFor, 0, searchFor.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean startsWithIgnoreCase(String searchIn, String searchFor) {
/* 1500 */     return startsWithIgnoreCase(searchIn, 0, searchFor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean startsWithIgnoreCaseAndNonAlphaNumeric(String searchIn, String searchFor) {
/* 1516 */     if (searchIn == null) {
/* 1517 */       return (searchFor == null);
/*      */     }
/*      */     
/* 1520 */     int beginPos = 0;
/* 1521 */     int inLength = searchIn.length();
/*      */     
/* 1523 */     for (; beginPos < inLength; beginPos++) {
/* 1524 */       char c = searchIn.charAt(beginPos);
/* 1525 */       if (Character.isLetterOrDigit(c)) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/* 1530 */     return startsWithIgnoreCase(searchIn, beginPos, searchFor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean startsWithIgnoreCaseAndWs(String searchIn, String searchFor) {
/* 1545 */     return startsWithIgnoreCaseAndWs(searchIn, searchFor, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean startsWithIgnoreCaseAndWs(String searchIn, String searchFor, int beginPos) {
/* 1563 */     if (searchIn == null) {
/* 1564 */       return (searchFor == null);
/*      */     }
/*      */     
/* 1567 */     int inLength = searchIn.length();
/*      */     
/* 1569 */     for (; beginPos < inLength && 
/* 1570 */       Character.isWhitespace(searchIn.charAt(beginPos)); beginPos++);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1575 */     return startsWithIgnoreCase(searchIn, beginPos, searchFor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int startsWithIgnoreCaseAndWs(String searchIn, String[] searchFor) {
/* 1590 */     for (int i = 0; i < searchFor.length; i++) {
/* 1591 */       if (startsWithIgnoreCaseAndWs(searchIn, searchFor[i], 0)) {
/* 1592 */         return i;
/*      */       }
/*      */     } 
/* 1595 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] stripEnclosure(byte[] source, String prefix, String suffix) {
/* 1604 */     if (source.length >= prefix.length() + suffix.length() && startsWith(source, prefix) && endsWith(source, suffix)) {
/*      */       
/* 1606 */       int totalToStrip = prefix.length() + suffix.length();
/* 1607 */       int enclosedLength = source.length - totalToStrip;
/* 1608 */       byte[] enclosed = new byte[enclosedLength];
/*      */       
/* 1610 */       int startPos = prefix.length();
/* 1611 */       int numToCopy = enclosed.length;
/* 1612 */       System.arraycopy(source, startPos, enclosed, 0, numToCopy);
/*      */       
/* 1614 */       return enclosed;
/*      */     } 
/* 1616 */     return source;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toAsciiString(byte[] buffer) {
/* 1628 */     return toAsciiString(buffer, 0, buffer.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toAsciiString(byte[] buffer, int startPos, int length) {
/* 1644 */     char[] charArray = new char[length];
/* 1645 */     int readpoint = startPos;
/*      */     
/* 1647 */     for (int i = 0; i < length; i++) {
/* 1648 */       charArray[i] = (char)buffer[readpoint];
/* 1649 */       readpoint++;
/*      */     } 
/*      */     
/* 1652 */     return new String(charArray);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean wildCompareIgnoreCase(String searchIn, String searchFor) {
/* 1664 */     return (wildCompareInternal(searchIn, searchFor) == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int wildCompareInternal(String searchIn, String searchFor) {
/* 1682 */     if (searchIn == null || searchFor == null) {
/* 1683 */       return -1;
/*      */     }
/*      */     
/* 1686 */     if (searchFor.equals("%")) {
/* 1687 */       return 0;
/*      */     }
/*      */     
/* 1690 */     int searchForPos = 0;
/* 1691 */     int searchForEnd = searchFor.length();
/*      */     
/* 1693 */     int searchInPos = 0;
/* 1694 */     int searchInEnd = searchIn.length();
/*      */     
/* 1696 */     int result = -1;
/*      */     
/* 1698 */     while (searchForPos != searchForEnd) {
/* 1699 */       while (searchFor.charAt(searchForPos) != '%' && searchFor.charAt(searchForPos) != '_') {
/* 1700 */         if (searchFor.charAt(searchForPos) == '\\' && searchForPos + 1 != searchForEnd) {
/* 1701 */           searchForPos++;
/*      */         }
/*      */         
/* 1704 */         if (searchInPos == searchInEnd || Character.toUpperCase(searchFor.charAt(searchForPos++)) != Character.toUpperCase(searchIn.charAt(searchInPos++)))
/*      */         {
/* 1706 */           return 1;
/*      */         }
/*      */         
/* 1709 */         if (searchForPos == searchForEnd) {
/* 1710 */           return (searchInPos != searchInEnd) ? 1 : 0;
/*      */         }
/*      */         
/* 1713 */         result = 1;
/*      */       } 
/*      */       
/* 1716 */       if (searchFor.charAt(searchForPos) == '_') {
/*      */         do {
/* 1718 */           if (searchInPos == searchInEnd) {
/* 1719 */             return result;
/*      */           }
/* 1721 */           searchInPos++;
/* 1722 */         } while (++searchForPos < searchForEnd && searchFor.charAt(searchForPos) == '_');
/*      */         
/* 1724 */         if (searchForPos == searchForEnd) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */       
/* 1729 */       if (searchFor.charAt(searchForPos) == '%') {
/* 1730 */         searchForPos++;
/*      */ 
/*      */         
/* 1733 */         for (; searchForPos != searchForEnd; searchForPos++) {
/* 1734 */           if (searchFor.charAt(searchForPos) != '%')
/*      */           {
/*      */ 
/*      */             
/* 1738 */             if (searchFor.charAt(searchForPos) == '_') {
/* 1739 */               if (searchInPos == searchInEnd) {
/* 1740 */                 return -1;
/*      */               }
/* 1742 */               searchInPos++;
/*      */             } else {
/*      */               break;
/*      */             } 
/*      */           }
/*      */         } 
/*      */         
/* 1749 */         if (searchForPos == searchForEnd) {
/* 1750 */           return 0;
/*      */         }
/*      */         
/* 1753 */         if (searchInPos == searchInEnd) {
/* 1754 */           return -1;
/*      */         }
/*      */         
/*      */         char cmp;
/* 1758 */         if ((cmp = searchFor.charAt(searchForPos)) == '\\' && searchForPos + 1 != searchForEnd) {
/* 1759 */           cmp = searchFor.charAt(++searchForPos);
/*      */         }
/*      */         
/* 1762 */         searchForPos++;
/*      */         
/*      */         while (true) {
/* 1765 */           if (searchInPos != searchInEnd && Character.toUpperCase(searchIn.charAt(searchInPos)) != Character.toUpperCase(cmp)) {
/* 1766 */             searchInPos++;
/*      */             continue;
/*      */           } 
/* 1769 */           if (searchInPos++ == searchInEnd) {
/* 1770 */             return -1;
/*      */           }
/*      */           
/* 1773 */           int tmp = wildCompareInternal(searchIn.substring(searchInPos), searchFor.substring(searchForPos));
/* 1774 */           if (tmp <= 0) {
/* 1775 */             return tmp;
/*      */           }
/*      */           
/* 1778 */           if (searchInPos == searchInEnd)
/*      */             break; 
/* 1780 */         }  return -1;
/*      */       } 
/*      */     } 
/*      */     
/* 1784 */     return (searchInPos != searchInEnd) ? 1 : 0;
/*      */   }
/*      */   
/*      */   static byte[] s2b(String s, MySQLConnection conn) throws SQLException {
/* 1788 */     if (s == null) {
/* 1789 */       return null;
/*      */     }
/*      */     
/* 1792 */     if (conn != null && conn.getUseUnicode()) {
/*      */       try {
/* 1794 */         String encoding = conn.getEncoding();
/*      */         
/* 1796 */         if (encoding == null) {
/* 1797 */           return s.getBytes();
/*      */         }
/*      */         
/* 1800 */         SingleByteCharsetConverter converter = conn.getCharsetConverter(encoding);
/*      */         
/* 1802 */         if (converter != null) {
/* 1803 */           return converter.toBytes(s);
/*      */         }
/*      */         
/* 1806 */         return s.getBytes(encoding);
/* 1807 */       } catch (UnsupportedEncodingException E) {
/* 1808 */         return s.getBytes();
/*      */       } 
/*      */     }
/*      */     
/* 1812 */     return s.getBytes();
/*      */   }
/*      */   
/*      */   public static int lastIndexOf(byte[] s, char c) {
/* 1816 */     if (s == null) {
/* 1817 */       return -1;
/*      */     }
/*      */     
/* 1820 */     for (int i = s.length - 1; i >= 0; i--) {
/* 1821 */       if (s[i] == c) {
/* 1822 */         return i;
/*      */       }
/*      */     } 
/*      */     
/* 1826 */     return -1;
/*      */   }
/*      */   
/*      */   public static int indexOf(byte[] s, char c) {
/* 1830 */     if (s == null) {
/* 1831 */       return -1;
/*      */     }
/*      */     
/* 1834 */     int length = s.length;
/*      */     
/* 1836 */     for (int i = 0; i < length; i++) {
/* 1837 */       if (s[i] == c) {
/* 1838 */         return i;
/*      */       }
/*      */     } 
/*      */     
/* 1842 */     return -1;
/*      */   }
/*      */   
/*      */   public static boolean isNullOrEmpty(String toTest) {
/* 1846 */     return (toTest == null || toTest.length() == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String stripComments(String src, String stringOpens, String stringCloses, boolean slashStarComments, boolean slashSlashComments, boolean hashComments, boolean dashDashComments) {
/* 1871 */     if (src == null) {
/* 1872 */       return null;
/*      */     }
/*      */     
/* 1875 */     StringBuilder strBuilder = new StringBuilder(src.length());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1880 */     StringReader sourceReader = new StringReader(src);
/*      */     
/* 1882 */     int contextMarker = 0;
/* 1883 */     boolean escaped = false;
/* 1884 */     int markerTypeFound = -1;
/*      */     
/* 1886 */     int ind = 0;
/*      */     
/* 1888 */     int currentChar = 0;
/*      */     
/*      */     try {
/* 1891 */       label81: while ((currentChar = sourceReader.read()) != -1) {
/*      */         
/* 1893 */         if (markerTypeFound != -1 && currentChar == stringCloses.charAt(markerTypeFound) && !escaped) {
/* 1894 */           contextMarker = 0;
/* 1895 */           markerTypeFound = -1;
/* 1896 */         } else if ((ind = stringOpens.indexOf(currentChar)) != -1 && !escaped && contextMarker == 0) {
/* 1897 */           markerTypeFound = ind;
/* 1898 */           contextMarker = currentChar;
/*      */         } 
/*      */         
/* 1901 */         if (contextMarker == 0 && currentChar == 47 && (slashSlashComments || slashStarComments)) {
/* 1902 */           currentChar = sourceReader.read();
/* 1903 */           if (currentChar == 42 && slashStarComments) {
/* 1904 */             int prevChar = 0; while (true) {
/* 1905 */               if ((currentChar = sourceReader.read()) != 47 || prevChar != 42) {
/* 1906 */                 if (currentChar == 13) {
/*      */                   
/* 1908 */                   currentChar = sourceReader.read();
/* 1909 */                   if (currentChar == 10) {
/* 1910 */                     currentChar = sourceReader.read();
/*      */                   }
/*      */                 }
/* 1913 */                 else if (currentChar == 10) {
/*      */                   
/* 1915 */                   currentChar = sourceReader.read();
/*      */                 } 
/*      */                 
/* 1918 */                 if (currentChar < 0) {
/*      */                   continue label81;
/*      */                 }
/* 1921 */                 prevChar = currentChar; continue;
/*      */               }  continue label81;
/*      */             } 
/* 1924 */           }  if (currentChar == 47 && slashSlashComments) {
/* 1925 */             while ((currentChar = sourceReader.read()) != 10 && currentChar != 13 && currentChar >= 0);
/*      */           }
/*      */         }
/* 1928 */         else if (contextMarker == 0 && currentChar == 35 && hashComments) {
/*      */           
/* 1930 */           while ((currentChar = sourceReader.read()) != 10 && currentChar != 13 && currentChar >= 0);
/*      */         }
/* 1932 */         else if (contextMarker == 0 && currentChar == 45 && dashDashComments) {
/* 1933 */           currentChar = sourceReader.read();
/*      */           
/* 1935 */           if (currentChar == -1 || currentChar != 45) {
/* 1936 */             strBuilder.append('-');
/*      */             
/* 1938 */             if (currentChar != -1) {
/* 1939 */               strBuilder.append((char)currentChar);
/*      */             }
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/*      */ 
/*      */           
/* 1947 */           while ((currentChar = sourceReader.read()) != 10 && currentChar != 13 && currentChar >= 0);
/*      */         } 
/*      */ 
/*      */         
/* 1951 */         if (currentChar != -1) {
/* 1952 */           strBuilder.append((char)currentChar);
/*      */         }
/*      */       } 
/* 1955 */     } catch (IOException ioEx) {}
/*      */ 
/*      */ 
/*      */     
/* 1959 */     return strBuilder.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String sanitizeProcOrFuncName(String src) {
/* 1975 */     if (src == null || src.equals("%")) {
/* 1976 */       return null;
/*      */     }
/*      */     
/* 1979 */     return src;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> splitDBdotName(String source, String catalog, String quoteId, boolean isNoBslashEscSet) {
/*      */     String entityName;
/* 1998 */     if (source == null || source.equals("%")) {
/* 1999 */       return Collections.emptyList();
/*      */     }
/*      */     
/* 2002 */     int dotIndex = -1;
/* 2003 */     if (" ".equals(quoteId)) {
/* 2004 */       dotIndex = source.indexOf(".");
/*      */     } else {
/* 2006 */       dotIndex = indexOfIgnoreCase(0, source, ".", quoteId, quoteId, isNoBslashEscSet ? SEARCH_MODE__MRK_WS : SEARCH_MODE__BSESC_MRK_WS);
/*      */     } 
/*      */     
/* 2009 */     String database = catalog;
/*      */     
/* 2011 */     if (dotIndex != -1) {
/* 2012 */       database = unQuoteIdentifier(source.substring(0, dotIndex), quoteId);
/* 2013 */       entityName = unQuoteIdentifier(source.substring(dotIndex + 1), quoteId);
/*      */     } else {
/* 2015 */       entityName = unQuoteIdentifier(source, quoteId);
/*      */     } 
/*      */     
/* 2018 */     return Arrays.asList(new String[] { database, entityName });
/*      */   }
/*      */   
/*      */   public static boolean isEmptyOrWhitespaceOnly(String str) {
/* 2022 */     if (str == null || str.length() == 0) {
/* 2023 */       return true;
/*      */     }
/*      */     
/* 2026 */     int length = str.length();
/*      */     
/* 2028 */     for (int i = 0; i < length; i++) {
/* 2029 */       if (!Character.isWhitespace(str.charAt(i))) {
/* 2030 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 2034 */     return true;
/*      */   }
/*      */   
/*      */   public static String escapeQuote(String src, String quotChar) {
/* 2038 */     if (src == null) {
/* 2039 */       return null;
/*      */     }
/*      */     
/* 2042 */     src = toString(stripEnclosure(src.getBytes(), quotChar, quotChar));
/*      */     
/* 2044 */     int lastNdx = src.indexOf(quotChar);
/*      */ 
/*      */ 
/*      */     
/* 2048 */     String tmpSrc = src.substring(0, lastNdx);
/* 2049 */     tmpSrc = tmpSrc + quotChar + quotChar;
/*      */     
/* 2051 */     String tmpRest = src.substring(lastNdx + 1, src.length());
/*      */     
/* 2053 */     lastNdx = tmpRest.indexOf(quotChar);
/* 2054 */     while (lastNdx > -1) {
/*      */       
/* 2056 */       tmpSrc = tmpSrc + tmpRest.substring(0, lastNdx);
/* 2057 */       tmpSrc = tmpSrc + quotChar + quotChar;
/* 2058 */       tmpRest = tmpRest.substring(lastNdx + 1, tmpRest.length());
/*      */       
/* 2060 */       lastNdx = tmpRest.indexOf(quotChar);
/*      */     } 
/*      */     
/* 2063 */     tmpSrc = tmpSrc + tmpRest;
/* 2064 */     src = tmpSrc;
/*      */     
/* 2066 */     return src;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String quoteIdentifier(String identifier, String quoteChar, boolean isPedantic) {
/* 2100 */     if (identifier == null) {
/* 2101 */       return null;
/*      */     }
/*      */     
/* 2104 */     identifier = identifier.trim();
/*      */     
/* 2106 */     int quoteCharLength = quoteChar.length();
/* 2107 */     if (quoteCharLength == 0 || " ".equals(quoteChar)) {
/* 2108 */       return identifier;
/*      */     }
/*      */ 
/*      */     
/* 2112 */     if (!isPedantic && identifier.startsWith(quoteChar) && identifier.endsWith(quoteChar)) {
/*      */       
/* 2114 */       String identifierQuoteTrimmed = identifier.substring(quoteCharLength, identifier.length() - quoteCharLength);
/*      */ 
/*      */       
/* 2117 */       int quoteCharPos = identifierQuoteTrimmed.indexOf(quoteChar);
/* 2118 */       while (quoteCharPos >= 0) {
/* 2119 */         int quoteCharNextExpectedPos = quoteCharPos + quoteCharLength;
/* 2120 */         int quoteCharNextPosition = identifierQuoteTrimmed.indexOf(quoteChar, quoteCharNextExpectedPos);
/*      */         
/* 2122 */         if (quoteCharNextPosition == quoteCharNextExpectedPos) {
/* 2123 */           quoteCharPos = identifierQuoteTrimmed.indexOf(quoteChar, quoteCharNextPosition + quoteCharLength);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2130 */       if (quoteCharPos < 0) {
/* 2131 */         return identifier;
/*      */       }
/*      */     } 
/*      */     
/* 2135 */     return quoteChar + identifier.replaceAll(quoteChar, quoteChar + quoteChar) + quoteChar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String quoteIdentifier(String identifier, boolean isPedantic) {
/* 2158 */     return quoteIdentifier(identifier, "`", isPedantic);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String unQuoteIdentifier(String identifier, String quoteChar) {
/* 2183 */     if (identifier == null) {
/* 2184 */       return null;
/*      */     }
/*      */     
/* 2187 */     identifier = identifier.trim();
/*      */     
/* 2189 */     int quoteCharLength = quoteChar.length();
/* 2190 */     if (quoteCharLength == 0 || " ".equals(quoteChar)) {
/* 2191 */       return identifier;
/*      */     }
/*      */ 
/*      */     
/* 2195 */     if (identifier.startsWith(quoteChar) && identifier.endsWith(quoteChar)) {
/*      */       
/* 2197 */       String identifierQuoteTrimmed = identifier.substring(quoteCharLength, identifier.length() - quoteCharLength);
/*      */ 
/*      */       
/* 2200 */       int quoteCharPos = identifierQuoteTrimmed.indexOf(quoteChar);
/* 2201 */       while (quoteCharPos >= 0) {
/* 2202 */         int quoteCharNextExpectedPos = quoteCharPos + quoteCharLength;
/* 2203 */         int quoteCharNextPosition = identifierQuoteTrimmed.indexOf(quoteChar, quoteCharNextExpectedPos);
/*      */         
/* 2205 */         if (quoteCharNextPosition == quoteCharNextExpectedPos) {
/* 2206 */           quoteCharPos = identifierQuoteTrimmed.indexOf(quoteChar, quoteCharNextPosition + quoteCharLength);
/*      */           continue;
/*      */         } 
/* 2209 */         return identifier;
/*      */       } 
/*      */ 
/*      */       
/* 2213 */       return identifier.substring(quoteCharLength, identifier.length() - quoteCharLength).replaceAll(quoteChar + quoteChar, quoteChar);
/*      */     } 
/*      */     
/* 2216 */     return identifier;
/*      */   }
/*      */   
/*      */   public static int indexOfQuoteDoubleAware(String searchIn, String quoteChar, int startFrom) {
/* 2220 */     if (searchIn == null || quoteChar == null || quoteChar.length() == 0 || startFrom > searchIn.length()) {
/* 2221 */       return -1;
/*      */     }
/*      */     
/* 2224 */     int lastIndex = searchIn.length() - 1;
/*      */     
/* 2226 */     int beginPos = startFrom;
/* 2227 */     int pos = -1;
/*      */     
/* 2229 */     boolean next = true;
/* 2230 */     while (next) {
/* 2231 */       pos = searchIn.indexOf(quoteChar, beginPos);
/* 2232 */       if (pos == -1 || pos == lastIndex || !searchIn.startsWith(quoteChar, pos + 1)) {
/* 2233 */         next = false; continue;
/*      */       } 
/* 2235 */       beginPos = pos + 2;
/*      */     } 
/*      */ 
/*      */     
/* 2239 */     return pos;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String toString(byte[] value, int offset, int length, String encoding) throws UnsupportedEncodingException {
/* 2251 */     Charset cs = findCharset(encoding);
/*      */     
/* 2253 */     return cs.decode(ByteBuffer.wrap(value, offset, length)).toString();
/*      */   }
/*      */   
/*      */   public static String toString(byte[] value, String encoding) throws UnsupportedEncodingException {
/* 2257 */     Charset cs = findCharset(encoding);
/*      */     
/* 2259 */     return cs.decode(ByteBuffer.wrap(value)).toString();
/*      */   }
/*      */   
/*      */   public static String toString(byte[] value, int offset, int length) {
/*      */     try {
/* 2264 */       Charset cs = findCharset(platformEncoding);
/*      */       
/* 2266 */       return cs.decode(ByteBuffer.wrap(value, offset, length)).toString();
/* 2267 */     } catch (UnsupportedEncodingException e) {
/*      */ 
/*      */ 
/*      */       
/* 2271 */       return null;
/*      */     } 
/*      */   }
/*      */   public static String toString(byte[] value) {
/*      */     try {
/* 2276 */       Charset cs = findCharset(platformEncoding);
/*      */       
/* 2278 */       return cs.decode(ByteBuffer.wrap(value)).toString();
/* 2279 */     } catch (UnsupportedEncodingException e) {
/*      */ 
/*      */ 
/*      */       
/* 2283 */       return null;
/*      */     } 
/*      */   }
/*      */   public static byte[] getBytes(char[] value) {
/*      */     try {
/* 2288 */       return getBytes(value, 0, value.length, platformEncoding);
/* 2289 */     } catch (UnsupportedEncodingException e) {
/*      */ 
/*      */ 
/*      */       
/* 2293 */       return null;
/*      */     } 
/*      */   }
/*      */   public static byte[] getBytes(char[] value, int offset, int length) {
/*      */     try {
/* 2298 */       return getBytes(value, offset, length, platformEncoding);
/* 2299 */     } catch (UnsupportedEncodingException e) {
/*      */ 
/*      */ 
/*      */       
/* 2303 */       return null;
/*      */     } 
/*      */   }
/*      */   public static byte[] getBytes(char[] value, String encoding) throws UnsupportedEncodingException {
/* 2307 */     return getBytes(value, 0, value.length, encoding);
/*      */   }
/*      */   
/*      */   public static byte[] getBytes(char[] value, int offset, int length, String encoding) throws UnsupportedEncodingException {
/* 2311 */     Charset cs = findCharset(encoding);
/*      */     
/* 2313 */     ByteBuffer buf = cs.encode(CharBuffer.wrap(value, offset, length));
/*      */ 
/*      */     
/* 2316 */     int encodedLen = buf.limit();
/* 2317 */     byte[] asBytes = new byte[encodedLen];
/* 2318 */     buf.get(asBytes, 0, encodedLen);
/*      */     
/* 2320 */     return asBytes;
/*      */   }
/*      */   
/*      */   public static byte[] getBytes(String value) {
/*      */     try {
/* 2325 */       return getBytes(value, 0, value.length(), platformEncoding);
/* 2326 */     } catch (UnsupportedEncodingException e) {
/*      */ 
/*      */ 
/*      */       
/* 2330 */       return null;
/*      */     } 
/*      */   }
/*      */   public static byte[] getBytes(String value, int offset, int length) {
/*      */     try {
/* 2335 */       return getBytes(value, offset, length, platformEncoding);
/* 2336 */     } catch (UnsupportedEncodingException e) {
/*      */ 
/*      */ 
/*      */       
/* 2340 */       return null;
/*      */     } 
/*      */   }
/*      */   public static byte[] getBytes(String value, String encoding) throws UnsupportedEncodingException {
/* 2344 */     return getBytes(value, 0, value.length(), encoding);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] getBytes(String value, int offset, int length, String encoding) throws UnsupportedEncodingException {
/* 2353 */     if (!Util.isJdbc4()) {
/* 2354 */       if (offset != 0 || length != value.length()) {
/* 2355 */         return value.substring(offset, offset + length).getBytes(encoding);
/*      */       }
/* 2357 */       return value.getBytes(encoding);
/*      */     } 
/*      */     
/* 2360 */     Charset cs = findCharset(encoding);
/*      */     
/* 2362 */     ByteBuffer buf = cs.encode(CharBuffer.wrap(value.toCharArray(), offset, length));
/*      */ 
/*      */     
/* 2365 */     int encodedLen = buf.limit();
/* 2366 */     byte[] asBytes = new byte[encodedLen];
/* 2367 */     buf.get(asBytes, 0, encodedLen);
/*      */     
/* 2369 */     return asBytes;
/*      */   }
/*      */   
/*      */   public static final boolean isValidIdChar(char c) {
/* 2373 */     return ("abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789$_#@".indexOf(c) != -1);
/*      */   }
/*      */   
/* 2376 */   private static final char[] HEX_DIGITS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*      */   
/*      */   public static void appendAsHex(StringBuilder builder, byte[] bytes) {
/* 2379 */     builder.append("0x");
/* 2380 */     for (byte b : bytes) {
/* 2381 */       builder.append(HEX_DIGITS[b >>> 4 & 0xF]).append(HEX_DIGITS[b & 0xF]);
/*      */     }
/*      */   }
/*      */   
/*      */   public static void appendAsHex(StringBuilder builder, int value) {
/* 2386 */     if (value == 0) {
/* 2387 */       builder.append("0x0");
/*      */       
/*      */       return;
/*      */     } 
/* 2391 */     int shift = 32;
/*      */     
/* 2393 */     boolean nonZeroFound = false;
/*      */     
/* 2395 */     builder.append("0x");
/*      */     do {
/* 2397 */       shift -= 4;
/* 2398 */       byte nibble = (byte)(value >>> shift & 0xF);
/* 2399 */       if (nonZeroFound) {
/* 2400 */         builder.append(HEX_DIGITS[nibble]);
/* 2401 */       } else if (nibble != 0) {
/* 2402 */         builder.append(HEX_DIGITS[nibble]);
/* 2403 */         nonZeroFound = true;
/*      */       } 
/* 2405 */     } while (shift != 0);
/*      */   }
/*      */   
/*      */   public static byte[] getBytesNullTerminated(String value, String encoding) throws UnsupportedEncodingException {
/* 2409 */     Charset cs = findCharset(encoding);
/*      */     
/* 2411 */     ByteBuffer buf = cs.encode(value);
/*      */     
/* 2413 */     int encodedLen = buf.limit();
/* 2414 */     byte[] asBytes = new byte[encodedLen + 1];
/* 2415 */     buf.get(asBytes, 0, encodedLen);
/* 2416 */     asBytes[encodedLen] = 0;
/*      */     
/* 2418 */     return asBytes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isStrictlyNumeric(CharSequence cs) {
/* 2430 */     if (cs == null || cs.length() == 0) {
/* 2431 */       return false;
/*      */     }
/* 2433 */     for (int i = 0; i < cs.length(); i++) {
/* 2434 */       if (!Character.isDigit(cs.charAt(i))) {
/* 2435 */         return false;
/*      */       }
/*      */     } 
/* 2438 */     return true;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\StringUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */