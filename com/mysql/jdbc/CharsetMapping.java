/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
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
/*     */ public class CharsetMapping
/*     */ {
/*     */   public static final int MAP_SIZE = 2048;
/*     */   public static final String[] COLLATION_INDEX_TO_COLLATION_NAME;
/*     */   public static final MysqlCharset[] COLLATION_INDEX_TO_CHARSET;
/*     */   public static final Map<String, MysqlCharset> CHARSET_NAME_TO_CHARSET;
/*     */   public static final Map<String, Integer> CHARSET_NAME_TO_COLLATION_INDEX;
/*     */   private static final Map<String, List<MysqlCharset>> JAVA_ENCODING_UC_TO_MYSQL_CHARSET;
/*     */   private static final Set<String> MULTIBYTE_ENCODINGS;
/*     */   private static final Map<String, String> ERROR_MESSAGE_FILE_TO_MYSQL_CHARSET;
/*     */   private static final Set<String> ESCAPE_ENCODINGS;
/*     */   public static final Set<Integer> UTF8MB4_INDEXES;
/*     */   private static final String MYSQL_CHARSET_NAME_armscii8 = "armscii8";
/*     */   private static final String MYSQL_CHARSET_NAME_ascii = "ascii";
/*     */   private static final String MYSQL_CHARSET_NAME_big5 = "big5";
/*     */   private static final String MYSQL_CHARSET_NAME_binary = "binary";
/*     */   private static final String MYSQL_CHARSET_NAME_cp1250 = "cp1250";
/*     */   private static final String MYSQL_CHARSET_NAME_cp1251 = "cp1251";
/*     */   private static final String MYSQL_CHARSET_NAME_cp1256 = "cp1256";
/*     */   private static final String MYSQL_CHARSET_NAME_cp1257 = "cp1257";
/*     */   private static final String MYSQL_CHARSET_NAME_cp850 = "cp850";
/*     */   private static final String MYSQL_CHARSET_NAME_cp852 = "cp852";
/*     */   private static final String MYSQL_CHARSET_NAME_cp866 = "cp866";
/*     */   private static final String MYSQL_CHARSET_NAME_cp932 = "cp932";
/*     */   private static final String MYSQL_CHARSET_NAME_dec8 = "dec8";
/*     */   private static final String MYSQL_CHARSET_NAME_eucjpms = "eucjpms";
/*     */   private static final String MYSQL_CHARSET_NAME_euckr = "euckr";
/*     */   private static final String MYSQL_CHARSET_NAME_gb18030 = "gb18030";
/*     */   private static final String MYSQL_CHARSET_NAME_gb2312 = "gb2312";
/*     */   private static final String MYSQL_CHARSET_NAME_gbk = "gbk";
/*     */   private static final String MYSQL_CHARSET_NAME_geostd8 = "geostd8";
/*     */   private static final String MYSQL_CHARSET_NAME_greek = "greek";
/*     */   private static final String MYSQL_CHARSET_NAME_hebrew = "hebrew";
/*     */   private static final String MYSQL_CHARSET_NAME_hp8 = "hp8";
/*     */   private static final String MYSQL_CHARSET_NAME_keybcs2 = "keybcs2";
/*     */   private static final String MYSQL_CHARSET_NAME_koi8r = "koi8r";
/*     */   private static final String MYSQL_CHARSET_NAME_koi8u = "koi8u";
/*     */   private static final String MYSQL_CHARSET_NAME_latin1 = "latin1";
/*     */   private static final String MYSQL_CHARSET_NAME_latin2 = "latin2";
/*     */   private static final String MYSQL_CHARSET_NAME_latin5 = "latin5";
/*     */   private static final String MYSQL_CHARSET_NAME_latin7 = "latin7";
/*     */   private static final String MYSQL_CHARSET_NAME_macce = "macce";
/*     */   private static final String MYSQL_CHARSET_NAME_macroman = "macroman";
/*     */   private static final String MYSQL_CHARSET_NAME_sjis = "sjis";
/*     */   private static final String MYSQL_CHARSET_NAME_swe7 = "swe7";
/*     */   private static final String MYSQL_CHARSET_NAME_tis620 = "tis620";
/*     */   private static final String MYSQL_CHARSET_NAME_ucs2 = "ucs2";
/*     */   private static final String MYSQL_CHARSET_NAME_ujis = "ujis";
/*     */   private static final String MYSQL_CHARSET_NAME_utf16 = "utf16";
/*     */   private static final String MYSQL_CHARSET_NAME_utf16le = "utf16le";
/*     */   private static final String MYSQL_CHARSET_NAME_utf32 = "utf32";
/*     */   private static final String MYSQL_CHARSET_NAME_utf8 = "utf8";
/*     */   private static final String MYSQL_CHARSET_NAME_utf8mb4 = "utf8mb4";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_cp1251cias = "cp1251cias";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_cp1251csas = "cp1251csas";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_croat = "croat";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_czech = "czech";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_danish = "danish";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_dos = "dos";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_estonia = "estonia";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_euc_kr = "euc_kr";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_german1 = "german1";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_hungarian = "hungarian";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_koi8_ru = "koi8_ru";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_koi8_ukr = "koi8_ukr";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_latin1_de = "latin1_de";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_latvian = "latvian";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_latvian1 = "latvian1";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_usa7 = "usa7";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_win1250 = "win1250";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_win1251 = "win1251";
/*     */   private static final String MYSQL_4_0_CHARSET_NAME_win1251ukr = "win1251ukr";
/*     */   public static final String NOT_USED = "latin1";
/*     */   public static final String COLLATION_NOT_DEFINED = "none";
/*     */   public static final int MYSQL_COLLATION_INDEX_utf8 = 33;
/*     */   public static final int MYSQL_COLLATION_INDEX_binary = 63;
/* 129 */   private static int numberOfEncodingsConfigured = 0;
/*     */ 
/*     */   
/*     */   static {
/* 133 */     MysqlCharset[] charset = { new MysqlCharset("usa7", 1, 0, new String[] { "US-ASCII" }, 4, 0), new MysqlCharset("ascii", 1, 0, new String[] { "US-ASCII", "ASCII" }), new MysqlCharset("big5", 2, 0, new String[] { "Big5" }), new MysqlCharset("gbk", 2, 0, new String[] { "GBK" }), new MysqlCharset("sjis", 2, 0, new String[] { "SHIFT_JIS", "Cp943", "WINDOWS-31J" }), new MysqlCharset("cp932", 2, 1, new String[] { "WINDOWS-31J" }), new MysqlCharset("gb2312", 2, 0, new String[] { "GB2312" }), new MysqlCharset("ujis", 3, 0, new String[] { "EUC_JP" }), new MysqlCharset("eucjpms", 3, 0, new String[] { "EUC_JP_Solaris" }, 5, 0, 3), new MysqlCharset("gb18030", 4, 0, new String[] { "GB18030" }, 5, 7, 4), new MysqlCharset("euc_kr", 2, 0, new String[] { "EUC_KR" }, 4, 0), new MysqlCharset("euckr", 2, 0, new String[] { "EUC-KR" }), new MysqlCharset("latin1", 1, 1, new String[] { "Cp1252", "ISO8859_1" }), new MysqlCharset("swe7", 1, 0, new String[] { "Cp1252" }), new MysqlCharset("hp8", 1, 0, new String[] { "Cp1252" }), new MysqlCharset("dec8", 1, 0, new String[] { "Cp1252" }), new MysqlCharset("armscii8", 1, 0, new String[] { "Cp1252" }), new MysqlCharset("geostd8", 1, 0, new String[] { "Cp1252" }), new MysqlCharset("latin2", 1, 0, new String[] { "ISO8859_2" }), new MysqlCharset("czech", 1, 0, new String[] { "ISO8859_2" }, 4, 0), new MysqlCharset("hungarian", 1, 0, new String[] { "ISO8859_2" }, 4, 0), new MysqlCharset("croat", 1, 0, new String[] { "ISO8859_2" }, 4, 0), new MysqlCharset("greek", 1, 0, new String[] { "ISO8859_7", "greek" }), new MysqlCharset("latin7", 1, 0, new String[] { "ISO-8859-13" }), new MysqlCharset("hebrew", 1, 0, new String[] { "ISO8859_8" }), new MysqlCharset("latin5", 1, 0, new String[] { "ISO8859_9" }), new MysqlCharset("latvian", 1, 0, new String[] { "ISO8859_13" }, 4, 0), new MysqlCharset("latvian1", 1, 0, new String[] { "ISO8859_13" }, 4, 0), new MysqlCharset("estonia", 1, 1, new String[] { "ISO8859_13" }, 4, 0), new MysqlCharset("cp850", 1, 0, new String[] { "Cp850", "Cp437" }), new MysqlCharset("dos", 1, 0, new String[] { "Cp850", "Cp437" }, 4, 0), new MysqlCharset("cp852", 1, 0, new String[] { "Cp852" }), new MysqlCharset("keybcs2", 1, 0, new String[] { "Cp852" }), new MysqlCharset("cp866", 1, 0, new String[] { "Cp866" }), new MysqlCharset("koi8_ru", 1, 0, new String[] { "KOI8_R" }, 4, 0), new MysqlCharset("koi8r", 1, 1, new String[] { "KOI8_R" }), new MysqlCharset("koi8u", 1, 0, new String[] { "KOI8_R" }), new MysqlCharset("koi8_ukr", 1, 0, new String[] { "KOI8_R" }, 4, 0), new MysqlCharset("tis620", 1, 0, new String[] { "TIS620" }), new MysqlCharset("cp1250", 1, 0, new String[] { "Cp1250" }), new MysqlCharset("win1250", 1, 0, new String[] { "Cp1250" }, 4, 0), new MysqlCharset("cp1251", 1, 1, new String[] { "Cp1251" }), new MysqlCharset("win1251", 1, 0, new String[] { "Cp1251" }, 4, 0), new MysqlCharset("cp1251cias", 1, 0, new String[] { "Cp1251" }, 4, 0), new MysqlCharset("cp1251csas", 1, 0, new String[] { "Cp1251" }, 4, 0), new MysqlCharset("win1251ukr", 1, 0, new String[] { "Cp1251" }, 4, 0), new MysqlCharset("cp1256", 1, 0, new String[] { "Cp1256" }), new MysqlCharset("cp1257", 1, 0, new String[] { "Cp1257" }), new MysqlCharset("macroman", 1, 0, new String[] { "MacRoman" }), new MysqlCharset("macce", 1, 0, new String[] { "MacCentralEurope" }), new MysqlCharset("utf8", 3, 1, new String[] { "UTF-8" }), new MysqlCharset("utf8mb4", 4, 0, new String[] { "UTF-8" }), new MysqlCharset("ucs2", 2, 0, new String[] { "UnicodeBig" }), new MysqlCharset("binary", 1, 1, new String[] { "ISO8859_1" }), new MysqlCharset("latin1_de", 1, 0, new String[] { "ISO8859_1" }, 4, 0), new MysqlCharset("german1", 1, 0, new String[] { "ISO8859_1" }, 4, 0), new MysqlCharset("danish", 1, 0, new String[] { "ISO8859_1" }, 4, 0), new MysqlCharset("utf16", 4, 0, new String[] { "UTF-16" }), new MysqlCharset("utf16le", 4, 0, new String[] { "UTF-16LE" }), new MysqlCharset("utf32", 4, 0, new String[] { "UTF-32" }) };
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
/* 217 */     HashMap<String, MysqlCharset> charsetNameToMysqlCharsetMap = new HashMap<String, MysqlCharset>();
/* 218 */     HashMap<String, List<MysqlCharset>> javaUcToMysqlCharsetMap = new HashMap<String, List<MysqlCharset>>();
/* 219 */     Set<String> tempMultibyteEncodings = new HashSet<String>();
/* 220 */     Set<String> tempEscapeEncodings = new HashSet<String>();
/* 221 */     for (int i = 0; i < charset.length; i++) {
/* 222 */       String charsetName = (charset[i]).charsetName;
/*     */       
/* 224 */       charsetNameToMysqlCharsetMap.put(charsetName, charset[i]);
/*     */       
/* 226 */       numberOfEncodingsConfigured += (charset[i]).javaEncodingsUc.size();
/*     */       
/* 228 */       for (String encUC : (charset[i]).javaEncodingsUc) {
/*     */ 
/*     */         
/* 231 */         List<MysqlCharset> charsets = javaUcToMysqlCharsetMap.get(encUC);
/* 232 */         if (charsets == null) {
/* 233 */           charsets = new ArrayList<MysqlCharset>();
/* 234 */           javaUcToMysqlCharsetMap.put(encUC, charsets);
/*     */         } 
/* 236 */         charsets.add(charset[i]);
/*     */ 
/*     */         
/* 239 */         if ((charset[i]).mblen > 1) {
/* 240 */           tempMultibyteEncodings.add(encUC);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 247 */       if (charsetName.equals("big5") || charsetName.equals("gbk") || charsetName.equals("sjis")) {
/* 248 */         tempEscapeEncodings.addAll((charset[i]).javaEncodingsUc);
/*     */       }
/*     */     } 
/*     */     
/* 252 */     CHARSET_NAME_TO_CHARSET = Collections.unmodifiableMap(charsetNameToMysqlCharsetMap);
/* 253 */     JAVA_ENCODING_UC_TO_MYSQL_CHARSET = Collections.unmodifiableMap(javaUcToMysqlCharsetMap);
/* 254 */     MULTIBYTE_ENCODINGS = Collections.unmodifiableSet(tempMultibyteEncodings);
/* 255 */     ESCAPE_ENCODINGS = Collections.unmodifiableSet(tempEscapeEncodings);
/*     */ 
/*     */     
/* 258 */     Collation[] collation = new Collation[2048];
/* 259 */     collation[1] = new Collation(1, "big5_chinese_ci", 1, "big5");
/* 260 */     collation[2] = new Collation(2, "latin2_czech_cs", 0, "latin2");
/* 261 */     collation[3] = new Collation(3, "dec8_swedish_ci", 0, "dec8");
/* 262 */     collation[4] = new Collation(4, "cp850_general_ci", 1, "cp850");
/* 263 */     collation[5] = new Collation(5, "latin1_german1_ci", 0, "latin1");
/* 264 */     collation[6] = new Collation(6, "hp8_english_ci", 0, "hp8");
/* 265 */     collation[7] = new Collation(7, "koi8r_general_ci", 0, "koi8r");
/* 266 */     collation[8] = new Collation(8, "latin1_swedish_ci", 1, "latin1");
/* 267 */     collation[9] = new Collation(9, "latin2_general_ci", 1, "latin2");
/* 268 */     collation[10] = new Collation(10, "swe7_swedish_ci", 0, "swe7");
/* 269 */     collation[11] = new Collation(11, "ascii_general_ci", 0, "ascii");
/* 270 */     collation[12] = new Collation(12, "ujis_japanese_ci", 0, "ujis");
/* 271 */     collation[13] = new Collation(13, "sjis_japanese_ci", 0, "sjis");
/* 272 */     collation[14] = new Collation(14, "cp1251_bulgarian_ci", 0, "cp1251");
/* 273 */     collation[15] = new Collation(15, "latin1_danish_ci", 0, "latin1");
/* 274 */     collation[16] = new Collation(16, "hebrew_general_ci", 0, "hebrew");
/*     */     
/* 276 */     collation[18] = new Collation(18, "tis620_thai_ci", 0, "tis620");
/* 277 */     collation[19] = new Collation(19, "euckr_korean_ci", 0, "euckr");
/* 278 */     collation[20] = new Collation(20, "latin7_estonian_cs", 0, "latin7");
/* 279 */     collation[21] = new Collation(21, "latin2_hungarian_ci", 0, "latin2");
/* 280 */     collation[22] = new Collation(22, "koi8u_general_ci", 0, "koi8u");
/* 281 */     collation[23] = new Collation(23, "cp1251_ukrainian_ci", 0, "cp1251");
/* 282 */     collation[24] = new Collation(24, "gb2312_chinese_ci", 0, "gb2312");
/* 283 */     collation[25] = new Collation(25, "greek_general_ci", 0, "greek");
/* 284 */     collation[26] = new Collation(26, "cp1250_general_ci", 1, "cp1250");
/* 285 */     collation[27] = new Collation(27, "latin2_croatian_ci", 0, "latin2");
/* 286 */     collation[28] = new Collation(28, "gbk_chinese_ci", 1, "gbk");
/* 287 */     collation[29] = new Collation(29, "cp1257_lithuanian_ci", 0, "cp1257");
/* 288 */     collation[30] = new Collation(30, "latin5_turkish_ci", 1, "latin5");
/* 289 */     collation[31] = new Collation(31, "latin1_german2_ci", 0, "latin1");
/* 290 */     collation[32] = new Collation(32, "armscii8_general_ci", 0, "armscii8");
/* 291 */     collation[33] = new Collation(33, "utf8_general_ci", 1, "utf8");
/* 292 */     collation[34] = new Collation(34, "cp1250_czech_cs", 0, "cp1250");
/* 293 */     collation[35] = new Collation(35, "ucs2_general_ci", 1, "ucs2");
/* 294 */     collation[36] = new Collation(36, "cp866_general_ci", 1, "cp866");
/* 295 */     collation[37] = new Collation(37, "keybcs2_general_ci", 1, "keybcs2");
/* 296 */     collation[38] = new Collation(38, "macce_general_ci", 1, "macce");
/* 297 */     collation[39] = new Collation(39, "macroman_general_ci", 1, "macroman");
/* 298 */     collation[40] = new Collation(40, "cp852_general_ci", 1, "cp852");
/* 299 */     collation[41] = new Collation(41, "latin7_general_ci", 1, "latin7");
/* 300 */     collation[42] = new Collation(42, "latin7_general_cs", 0, "latin7");
/* 301 */     collation[43] = new Collation(43, "macce_bin", 0, "macce");
/* 302 */     collation[44] = new Collation(44, "cp1250_croatian_ci", 0, "cp1250");
/* 303 */     collation[45] = new Collation(45, "utf8mb4_general_ci", 0, "utf8mb4");
/* 304 */     collation[46] = new Collation(46, "utf8mb4_bin", 0, "utf8mb4");
/* 305 */     collation[47] = new Collation(47, "latin1_bin", 0, "latin1");
/* 306 */     collation[48] = new Collation(48, "latin1_general_ci", 0, "latin1");
/* 307 */     collation[49] = new Collation(49, "latin1_general_cs", 0, "latin1");
/* 308 */     collation[50] = new Collation(50, "cp1251_bin", 0, "cp1251");
/* 309 */     collation[51] = new Collation(51, "cp1251_general_ci", 1, "cp1251");
/* 310 */     collation[52] = new Collation(52, "cp1251_general_cs", 0, "cp1251");
/* 311 */     collation[53] = new Collation(53, "macroman_bin", 0, "macroman");
/* 312 */     collation[54] = new Collation(54, "utf16_general_ci", 1, "utf16");
/* 313 */     collation[55] = new Collation(55, "utf16_bin", 0, "utf16");
/* 314 */     collation[56] = new Collation(56, "utf16le_general_ci", 1, "utf16le");
/* 315 */     collation[57] = new Collation(57, "cp1256_general_ci", 1, "cp1256");
/* 316 */     collation[58] = new Collation(58, "cp1257_bin", 0, "cp1257");
/* 317 */     collation[59] = new Collation(59, "cp1257_general_ci", 1, "cp1257");
/* 318 */     collation[60] = new Collation(60, "utf32_general_ci", 1, "utf32");
/* 319 */     collation[61] = new Collation(61, "utf32_bin", 0, "utf32");
/* 320 */     collation[62] = new Collation(62, "utf16le_bin", 0, "utf16le");
/* 321 */     collation[63] = new Collation(63, "binary", 1, "binary");
/* 322 */     collation[64] = new Collation(64, "armscii8_bin", 0, "armscii8");
/* 323 */     collation[65] = new Collation(65, "ascii_bin", 0, "ascii");
/* 324 */     collation[66] = new Collation(66, "cp1250_bin", 0, "cp1250");
/* 325 */     collation[67] = new Collation(67, "cp1256_bin", 0, "cp1256");
/* 326 */     collation[68] = new Collation(68, "cp866_bin", 0, "cp866");
/* 327 */     collation[69] = new Collation(69, "dec8_bin", 0, "dec8");
/* 328 */     collation[70] = new Collation(70, "greek_bin", 0, "greek");
/* 329 */     collation[71] = new Collation(71, "hebrew_bin", 0, "hebrew");
/* 330 */     collation[72] = new Collation(72, "hp8_bin", 0, "hp8");
/* 331 */     collation[73] = new Collation(73, "keybcs2_bin", 0, "keybcs2");
/* 332 */     collation[74] = new Collation(74, "koi8r_bin", 0, "koi8r");
/* 333 */     collation[75] = new Collation(75, "koi8u_bin", 0, "koi8u");
/* 334 */     collation[76] = new Collation(76, "utf8_tolower_ci", 0, "utf8");
/* 335 */     collation[77] = new Collation(77, "latin2_bin", 0, "latin2");
/* 336 */     collation[78] = new Collation(78, "latin5_bin", 0, "latin5");
/* 337 */     collation[79] = new Collation(79, "latin7_bin", 0, "latin7");
/* 338 */     collation[80] = new Collation(80, "cp850_bin", 0, "cp850");
/* 339 */     collation[81] = new Collation(81, "cp852_bin", 0, "cp852");
/* 340 */     collation[82] = new Collation(82, "swe7_bin", 0, "swe7");
/* 341 */     collation[83] = new Collation(83, "utf8_bin", 0, "utf8");
/* 342 */     collation[84] = new Collation(84, "big5_bin", 0, "big5");
/* 343 */     collation[85] = new Collation(85, "euckr_bin", 0, "euckr");
/* 344 */     collation[86] = new Collation(86, "gb2312_bin", 0, "gb2312");
/* 345 */     collation[87] = new Collation(87, "gbk_bin", 0, "gbk");
/* 346 */     collation[88] = new Collation(88, "sjis_bin", 0, "sjis");
/* 347 */     collation[89] = new Collation(89, "tis620_bin", 0, "tis620");
/* 348 */     collation[90] = new Collation(90, "ucs2_bin", 0, "ucs2");
/* 349 */     collation[91] = new Collation(91, "ujis_bin", 0, "ujis");
/* 350 */     collation[92] = new Collation(92, "geostd8_general_ci", 0, "geostd8");
/* 351 */     collation[93] = new Collation(93, "geostd8_bin", 0, "geostd8");
/* 352 */     collation[94] = new Collation(94, "latin1_spanish_ci", 0, "latin1");
/* 353 */     collation[95] = new Collation(95, "cp932_japanese_ci", 1, "cp932");
/* 354 */     collation[96] = new Collation(96, "cp932_bin", 0, "cp932");
/* 355 */     collation[97] = new Collation(97, "eucjpms_japanese_ci", 1, "eucjpms");
/* 356 */     collation[98] = new Collation(98, "eucjpms_bin", 0, "eucjpms");
/* 357 */     collation[99] = new Collation(99, "cp1250_polish_ci", 0, "cp1250");
/*     */     
/* 359 */     collation[101] = new Collation(101, "utf16_unicode_ci", 0, "utf16");
/* 360 */     collation[102] = new Collation(102, "utf16_icelandic_ci", 0, "utf16");
/* 361 */     collation[103] = new Collation(103, "utf16_latvian_ci", 0, "utf16");
/* 362 */     collation[104] = new Collation(104, "utf16_romanian_ci", 0, "utf16");
/* 363 */     collation[105] = new Collation(105, "utf16_slovenian_ci", 0, "utf16");
/* 364 */     collation[106] = new Collation(106, "utf16_polish_ci", 0, "utf16");
/* 365 */     collation[107] = new Collation(107, "utf16_estonian_ci", 0, "utf16");
/* 366 */     collation[108] = new Collation(108, "utf16_spanish_ci", 0, "utf16");
/* 367 */     collation[109] = new Collation(109, "utf16_swedish_ci", 0, "utf16");
/* 368 */     collation[110] = new Collation(110, "utf16_turkish_ci", 0, "utf16");
/* 369 */     collation[111] = new Collation(111, "utf16_czech_ci", 0, "utf16");
/* 370 */     collation[112] = new Collation(112, "utf16_danish_ci", 0, "utf16");
/* 371 */     collation[113] = new Collation(113, "utf16_lithuanian_ci", 0, "utf16");
/* 372 */     collation[114] = new Collation(114, "utf16_slovak_ci", 0, "utf16");
/* 373 */     collation[115] = new Collation(115, "utf16_spanish2_ci", 0, "utf16");
/* 374 */     collation[116] = new Collation(116, "utf16_roman_ci", 0, "utf16");
/* 375 */     collation[117] = new Collation(117, "utf16_persian_ci", 0, "utf16");
/* 376 */     collation[118] = new Collation(118, "utf16_esperanto_ci", 0, "utf16");
/* 377 */     collation[119] = new Collation(119, "utf16_hungarian_ci", 0, "utf16");
/* 378 */     collation[120] = new Collation(120, "utf16_sinhala_ci", 0, "utf16");
/* 379 */     collation[121] = new Collation(121, "utf16_german2_ci", 0, "utf16");
/* 380 */     collation[122] = new Collation(122, "utf16_croatian_ci", 0, "utf16");
/* 381 */     collation[123] = new Collation(123, "utf16_unicode_520_ci", 0, "utf16");
/* 382 */     collation[124] = new Collation(124, "utf16_vietnamese_ci", 0, "utf16");
/*     */     
/* 384 */     collation[128] = new Collation(128, "ucs2_unicode_ci", 0, "ucs2");
/* 385 */     collation[129] = new Collation(129, "ucs2_icelandic_ci", 0, "ucs2");
/* 386 */     collation[130] = new Collation(130, "ucs2_latvian_ci", 0, "ucs2");
/* 387 */     collation[131] = new Collation(131, "ucs2_romanian_ci", 0, "ucs2");
/* 388 */     collation[132] = new Collation(132, "ucs2_slovenian_ci", 0, "ucs2");
/* 389 */     collation[133] = new Collation(133, "ucs2_polish_ci", 0, "ucs2");
/* 390 */     collation[134] = new Collation(134, "ucs2_estonian_ci", 0, "ucs2");
/* 391 */     collation[135] = new Collation(135, "ucs2_spanish_ci", 0, "ucs2");
/* 392 */     collation[136] = new Collation(136, "ucs2_swedish_ci", 0, "ucs2");
/* 393 */     collation[137] = new Collation(137, "ucs2_turkish_ci", 0, "ucs2");
/* 394 */     collation[138] = new Collation(138, "ucs2_czech_ci", 0, "ucs2");
/* 395 */     collation[139] = new Collation(139, "ucs2_danish_ci", 0, "ucs2");
/* 396 */     collation[140] = new Collation(140, "ucs2_lithuanian_ci", 0, "ucs2");
/* 397 */     collation[141] = new Collation(141, "ucs2_slovak_ci", 0, "ucs2");
/* 398 */     collation[142] = new Collation(142, "ucs2_spanish2_ci", 0, "ucs2");
/* 399 */     collation[143] = new Collation(143, "ucs2_roman_ci", 0, "ucs2");
/* 400 */     collation[144] = new Collation(144, "ucs2_persian_ci", 0, "ucs2");
/* 401 */     collation[145] = new Collation(145, "ucs2_esperanto_ci", 0, "ucs2");
/* 402 */     collation[146] = new Collation(146, "ucs2_hungarian_ci", 0, "ucs2");
/* 403 */     collation[147] = new Collation(147, "ucs2_sinhala_ci", 0, "ucs2");
/* 404 */     collation[148] = new Collation(148, "ucs2_german2_ci", 0, "ucs2");
/* 405 */     collation[149] = new Collation(149, "ucs2_croatian_ci", 0, "ucs2");
/* 406 */     collation[150] = new Collation(150, "ucs2_unicode_520_ci", 0, "ucs2");
/* 407 */     collation[151] = new Collation(151, "ucs2_vietnamese_ci", 0, "ucs2");
/*     */     
/* 409 */     collation[159] = new Collation(159, "ucs2_general_mysql500_ci", 0, "ucs2");
/* 410 */     collation[160] = new Collation(160, "utf32_unicode_ci", 0, "utf32");
/* 411 */     collation[161] = new Collation(161, "utf32_icelandic_ci", 0, "utf32");
/* 412 */     collation[162] = new Collation(162, "utf32_latvian_ci", 0, "utf32");
/* 413 */     collation[163] = new Collation(163, "utf32_romanian_ci", 0, "utf32");
/* 414 */     collation[164] = new Collation(164, "utf32_slovenian_ci", 0, "utf32");
/* 415 */     collation[165] = new Collation(165, "utf32_polish_ci", 0, "utf32");
/* 416 */     collation[166] = new Collation(166, "utf32_estonian_ci", 0, "utf32");
/* 417 */     collation[167] = new Collation(167, "utf32_spanish_ci", 0, "utf32");
/* 418 */     collation[168] = new Collation(168, "utf32_swedish_ci", 0, "utf32");
/* 419 */     collation[169] = new Collation(169, "utf32_turkish_ci", 0, "utf32");
/* 420 */     collation[170] = new Collation(170, "utf32_czech_ci", 0, "utf32");
/* 421 */     collation[171] = new Collation(171, "utf32_danish_ci", 0, "utf32");
/* 422 */     collation[172] = new Collation(172, "utf32_lithuanian_ci", 0, "utf32");
/* 423 */     collation[173] = new Collation(173, "utf32_slovak_ci", 0, "utf32");
/* 424 */     collation[174] = new Collation(174, "utf32_spanish2_ci", 0, "utf32");
/* 425 */     collation[175] = new Collation(175, "utf32_roman_ci", 0, "utf32");
/* 426 */     collation[176] = new Collation(176, "utf32_persian_ci", 0, "utf32");
/* 427 */     collation[177] = new Collation(177, "utf32_esperanto_ci", 0, "utf32");
/* 428 */     collation[178] = new Collation(178, "utf32_hungarian_ci", 0, "utf32");
/* 429 */     collation[179] = new Collation(179, "utf32_sinhala_ci", 0, "utf32");
/* 430 */     collation[180] = new Collation(180, "utf32_german2_ci", 0, "utf32");
/* 431 */     collation[181] = new Collation(181, "utf32_croatian_ci", 0, "utf32");
/* 432 */     collation[182] = new Collation(182, "utf32_unicode_520_ci", 0, "utf32");
/* 433 */     collation[183] = new Collation(183, "utf32_vietnamese_ci", 0, "utf32");
/*     */     
/* 435 */     collation[192] = new Collation(192, "utf8_unicode_ci", 0, "utf8");
/* 436 */     collation[193] = new Collation(193, "utf8_icelandic_ci", 0, "utf8");
/* 437 */     collation[194] = new Collation(194, "utf8_latvian_ci", 0, "utf8");
/* 438 */     collation[195] = new Collation(195, "utf8_romanian_ci", 0, "utf8");
/* 439 */     collation[196] = new Collation(196, "utf8_slovenian_ci", 0, "utf8");
/* 440 */     collation[197] = new Collation(197, "utf8_polish_ci", 0, "utf8");
/* 441 */     collation[198] = new Collation(198, "utf8_estonian_ci", 0, "utf8");
/* 442 */     collation[199] = new Collation(199, "utf8_spanish_ci", 0, "utf8");
/* 443 */     collation[200] = new Collation(200, "utf8_swedish_ci", 0, "utf8");
/* 444 */     collation[201] = new Collation(201, "utf8_turkish_ci", 0, "utf8");
/* 445 */     collation[202] = new Collation(202, "utf8_czech_ci", 0, "utf8");
/* 446 */     collation[203] = new Collation(203, "utf8_danish_ci", 0, "utf8");
/* 447 */     collation[204] = new Collation(204, "utf8_lithuanian_ci", 0, "utf8");
/* 448 */     collation[205] = new Collation(205, "utf8_slovak_ci", 0, "utf8");
/* 449 */     collation[206] = new Collation(206, "utf8_spanish2_ci", 0, "utf8");
/* 450 */     collation[207] = new Collation(207, "utf8_roman_ci", 0, "utf8");
/* 451 */     collation[208] = new Collation(208, "utf8_persian_ci", 0, "utf8");
/* 452 */     collation[209] = new Collation(209, "utf8_esperanto_ci", 0, "utf8");
/* 453 */     collation[210] = new Collation(210, "utf8_hungarian_ci", 0, "utf8");
/* 454 */     collation[211] = new Collation(211, "utf8_sinhala_ci", 0, "utf8");
/* 455 */     collation[212] = new Collation(212, "utf8_german2_ci", 0, "utf8");
/* 456 */     collation[213] = new Collation(213, "utf8_croatian_ci", 0, "utf8");
/* 457 */     collation[214] = new Collation(214, "utf8_unicode_520_ci", 0, "utf8");
/* 458 */     collation[215] = new Collation(215, "utf8_vietnamese_ci", 0, "utf8");
/*     */     
/* 460 */     collation[223] = new Collation(223, "utf8_general_mysql500_ci", 0, "utf8");
/* 461 */     collation[224] = new Collation(224, "utf8mb4_unicode_ci", 0, "utf8mb4");
/* 462 */     collation[225] = new Collation(225, "utf8mb4_icelandic_ci", 0, "utf8mb4");
/* 463 */     collation[226] = new Collation(226, "utf8mb4_latvian_ci", 0, "utf8mb4");
/* 464 */     collation[227] = new Collation(227, "utf8mb4_romanian_ci", 0, "utf8mb4");
/* 465 */     collation[228] = new Collation(228, "utf8mb4_slovenian_ci", 0, "utf8mb4");
/* 466 */     collation[229] = new Collation(229, "utf8mb4_polish_ci", 0, "utf8mb4");
/* 467 */     collation[230] = new Collation(230, "utf8mb4_estonian_ci", 0, "utf8mb4");
/* 468 */     collation[231] = new Collation(231, "utf8mb4_spanish_ci", 0, "utf8mb4");
/* 469 */     collation[232] = new Collation(232, "utf8mb4_swedish_ci", 0, "utf8mb4");
/* 470 */     collation[233] = new Collation(233, "utf8mb4_turkish_ci", 0, "utf8mb4");
/* 471 */     collation[234] = new Collation(234, "utf8mb4_czech_ci", 0, "utf8mb4");
/* 472 */     collation[235] = new Collation(235, "utf8mb4_danish_ci", 0, "utf8mb4");
/* 473 */     collation[236] = new Collation(236, "utf8mb4_lithuanian_ci", 0, "utf8mb4");
/* 474 */     collation[237] = new Collation(237, "utf8mb4_slovak_ci", 0, "utf8mb4");
/* 475 */     collation[238] = new Collation(238, "utf8mb4_spanish2_ci", 0, "utf8mb4");
/* 476 */     collation[239] = new Collation(239, "utf8mb4_roman_ci", 0, "utf8mb4");
/* 477 */     collation[240] = new Collation(240, "utf8mb4_persian_ci", 0, "utf8mb4");
/* 478 */     collation[241] = new Collation(241, "utf8mb4_esperanto_ci", 0, "utf8mb4");
/* 479 */     collation[242] = new Collation(242, "utf8mb4_hungarian_ci", 0, "utf8mb4");
/* 480 */     collation[243] = new Collation(243, "utf8mb4_sinhala_ci", 0, "utf8mb4");
/* 481 */     collation[244] = new Collation(244, "utf8mb4_german2_ci", 0, "utf8mb4");
/* 482 */     collation[245] = new Collation(245, "utf8mb4_croatian_ci", 0, "utf8mb4");
/* 483 */     collation[246] = new Collation(246, "utf8mb4_unicode_520_ci", 0, "utf8mb4");
/* 484 */     collation[247] = new Collation(247, "utf8mb4_vietnamese_ci", 0, "utf8mb4");
/* 485 */     collation[248] = new Collation(248, "gb18030_chinese_ci", 1, "gb18030");
/* 486 */     collation[249] = new Collation(249, "gb18030_bin", 0, "gb18030");
/* 487 */     collation[250] = new Collation(250, "gb18030_unicode_520_ci", 0, "gb18030");
/*     */     
/* 489 */     collation[255] = new Collation(255, "utf8mb4_0900_ai_ci", 1, "utf8mb4");
/* 490 */     collation[256] = new Collation(256, "utf8mb4_de_pb_0900_ai_ci", 0, "utf8mb4");
/* 491 */     collation[257] = new Collation(257, "utf8mb4_is_0900_ai_ci", 0, "utf8mb4");
/* 492 */     collation[258] = new Collation(258, "utf8mb4_lv_0900_ai_ci", 0, "utf8mb4");
/* 493 */     collation[259] = new Collation(259, "utf8mb4_ro_0900_ai_ci", 0, "utf8mb4");
/* 494 */     collation[260] = new Collation(260, "utf8mb4_sl_0900_ai_ci", 0, "utf8mb4");
/* 495 */     collation[261] = new Collation(261, "utf8mb4_pl_0900_ai_ci", 0, "utf8mb4");
/* 496 */     collation[262] = new Collation(262, "utf8mb4_et_0900_ai_ci", 0, "utf8mb4");
/* 497 */     collation[263] = new Collation(263, "utf8mb4_es_0900_ai_ci", 0, "utf8mb4");
/* 498 */     collation[264] = new Collation(264, "utf8mb4_sv_0900_ai_ci", 0, "utf8mb4");
/* 499 */     collation[265] = new Collation(265, "utf8mb4_tr_0900_ai_ci", 0, "utf8mb4");
/* 500 */     collation[266] = new Collation(266, "utf8mb4_cs_0900_ai_ci", 0, "utf8mb4");
/* 501 */     collation[267] = new Collation(267, "utf8mb4_da_0900_ai_ci", 0, "utf8mb4");
/* 502 */     collation[268] = new Collation(268, "utf8mb4_lt_0900_ai_ci", 0, "utf8mb4");
/* 503 */     collation[269] = new Collation(269, "utf8mb4_sk_0900_ai_ci", 0, "utf8mb4");
/* 504 */     collation[270] = new Collation(270, "utf8mb4_es_trad_0900_ai_ci", 0, "utf8mb4");
/* 505 */     collation[271] = new Collation(271, "utf8mb4_la_0900_ai_ci", 0, "utf8mb4");
/*     */     
/* 507 */     collation[273] = new Collation(273, "utf8mb4_eo_0900_ai_ci", 0, "utf8mb4");
/* 508 */     collation[274] = new Collation(274, "utf8mb4_hu_0900_ai_ci", 0, "utf8mb4");
/* 509 */     collation[275] = new Collation(275, "utf8mb4_hr_0900_ai_ci", 0, "utf8mb4");
/*     */     
/* 511 */     collation[277] = new Collation(277, "utf8mb4_vi_0900_ai_ci", 0, "utf8mb4");
/* 512 */     collation[278] = new Collation(278, "utf8mb4_0900_as_cs", 0, "utf8mb4");
/* 513 */     collation[279] = new Collation(279, "utf8mb4_de_pb_0900_as_cs", 0, "utf8mb4");
/* 514 */     collation[280] = new Collation(280, "utf8mb4_is_0900_as_cs", 0, "utf8mb4");
/* 515 */     collation[281] = new Collation(281, "utf8mb4_lv_0900_as_cs", 0, "utf8mb4");
/* 516 */     collation[282] = new Collation(282, "utf8mb4_ro_0900_as_cs", 0, "utf8mb4");
/* 517 */     collation[283] = new Collation(283, "utf8mb4_sl_0900_as_cs", 0, "utf8mb4");
/* 518 */     collation[284] = new Collation(284, "utf8mb4_pl_0900_as_cs", 0, "utf8mb4");
/* 519 */     collation[285] = new Collation(285, "utf8mb4_et_0900_as_cs", 0, "utf8mb4");
/* 520 */     collation[286] = new Collation(286, "utf8mb4_es_0900_as_cs", 0, "utf8mb4");
/* 521 */     collation[287] = new Collation(287, "utf8mb4_sv_0900_as_cs", 0, "utf8mb4");
/* 522 */     collation[288] = new Collation(288, "utf8mb4_tr_0900_as_cs", 0, "utf8mb4");
/* 523 */     collation[289] = new Collation(289, "utf8mb4_cs_0900_as_cs", 0, "utf8mb4");
/* 524 */     collation[290] = new Collation(290, "utf8mb4_da_0900_as_cs", 0, "utf8mb4");
/* 525 */     collation[291] = new Collation(291, "utf8mb4_lt_0900_as_cs", 0, "utf8mb4");
/* 526 */     collation[292] = new Collation(292, "utf8mb4_sk_0900_as_cs", 0, "utf8mb4");
/* 527 */     collation[293] = new Collation(293, "utf8mb4_es_trad_0900_as_cs", 0, "utf8mb4");
/* 528 */     collation[294] = new Collation(294, "utf8mb4_la_0900_as_cs", 0, "utf8mb4");
/*     */     
/* 530 */     collation[296] = new Collation(296, "utf8mb4_eo_0900_as_cs", 0, "utf8mb4");
/* 531 */     collation[297] = new Collation(297, "utf8mb4_hu_0900_as_cs", 0, "utf8mb4");
/* 532 */     collation[298] = new Collation(298, "utf8mb4_hr_0900_as_cs", 0, "utf8mb4");
/*     */     
/* 534 */     collation[300] = new Collation(300, "utf8mb4_vi_0900_as_cs", 0, "utf8mb4");
/*     */     
/* 536 */     collation[303] = new Collation(303, "utf8mb4_ja_0900_as_cs", 0, "utf8mb4");
/* 537 */     collation[304] = new Collation(304, "utf8mb4_ja_0900_as_cs_ks", 0, "utf8mb4");
/* 538 */     collation[305] = new Collation(305, "utf8mb4_0900_as_ci", 0, "utf8mb4");
/* 539 */     collation[306] = new Collation(306, "utf8mb4_ru_0900_ai_ci", 0, "utf8mb4");
/* 540 */     collation[307] = new Collation(307, "utf8mb4_ru_0900_as_cs", 0, "utf8mb4");
/* 541 */     collation[308] = new Collation(308, "utf8mb4_zh_0900_as_cs", 0, "utf8mb4");
/* 542 */     collation[309] = new Collation(309, "utf8mb4_0900_bin", 0, "utf8mb4");
/*     */     
/* 544 */     collation[326] = new Collation(326, "utf8mb4_test_ci", 0, "utf8mb4");
/* 545 */     collation[327] = new Collation(327, "utf16_test_ci", 0, "utf16");
/* 546 */     collation[328] = new Collation(328, "utf8mb4_test_400_ci", 0, "utf8mb4");
/*     */     
/* 548 */     collation[336] = new Collation(336, "utf8_bengali_standard_ci", 0, "utf8");
/* 549 */     collation[337] = new Collation(337, "utf8_bengali_traditional_ci", 0, "utf8");
/*     */     
/* 551 */     collation[352] = new Collation(352, "utf8_phone_ci", 0, "utf8");
/* 552 */     collation[353] = new Collation(353, "utf8_test_ci", 0, "utf8");
/* 553 */     collation[354] = new Collation(354, "utf8_5624_1", 0, "utf8");
/* 554 */     collation[355] = new Collation(355, "utf8_5624_2", 0, "utf8");
/* 555 */     collation[356] = new Collation(356, "utf8_5624_3", 0, "utf8");
/* 556 */     collation[357] = new Collation(357, "utf8_5624_4", 0, "utf8");
/* 557 */     collation[358] = new Collation(358, "ucs2_test_ci", 0, "ucs2");
/* 558 */     collation[359] = new Collation(359, "ucs2_vn_ci", 0, "ucs2");
/* 559 */     collation[360] = new Collation(360, "ucs2_5624_1", 0, "ucs2");
/*     */     
/* 561 */     collation[368] = new Collation(368, "utf8_5624_5", 0, "utf8");
/* 562 */     collation[391] = new Collation(391, "utf32_test_ci", 0, "utf32");
/* 563 */     collation[2047] = new Collation(2047, "utf8_maxuserid_ci", 0, "utf8");
/*     */     
/* 565 */     COLLATION_INDEX_TO_COLLATION_NAME = new String[2048];
/* 566 */     COLLATION_INDEX_TO_CHARSET = new MysqlCharset[2048];
/* 567 */     Map<String, Integer> charsetNameToCollationIndexMap = new TreeMap<String, Integer>();
/* 568 */     Map<String, Integer> charsetNameToCollationPriorityMap = new TreeMap<String, Integer>();
/* 569 */     Set<Integer> tempUTF8MB4Indexes = new HashSet<Integer>();
/*     */     
/* 571 */     Collation notUsedCollation = new Collation(0, "none", 0, "latin1");
/* 572 */     for (int j = 1; j < 2048; j++) {
/* 573 */       Collation coll = (collation[j] != null) ? collation[j] : notUsedCollation;
/* 574 */       COLLATION_INDEX_TO_COLLATION_NAME[j] = coll.collationName;
/* 575 */       COLLATION_INDEX_TO_CHARSET[j] = coll.mysqlCharset;
/* 576 */       String charsetName = coll.mysqlCharset.charsetName;
/*     */       
/* 578 */       if (!charsetNameToCollationIndexMap.containsKey(charsetName) || ((Integer)charsetNameToCollationPriorityMap.get(charsetName)).intValue() < coll.priority) {
/* 579 */         charsetNameToCollationIndexMap.put(charsetName, Integer.valueOf(j));
/* 580 */         charsetNameToCollationPriorityMap.put(charsetName, Integer.valueOf(coll.priority));
/*     */       } 
/*     */ 
/*     */       
/* 584 */       if (charsetName.equals("utf8mb4")) {
/* 585 */         tempUTF8MB4Indexes.add(Integer.valueOf(j));
/*     */       }
/*     */     } 
/*     */     
/* 589 */     CHARSET_NAME_TO_COLLATION_INDEX = Collections.unmodifiableMap(charsetNameToCollationIndexMap);
/* 590 */     UTF8MB4_INDEXES = Collections.unmodifiableSet(tempUTF8MB4Indexes);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 595 */     Map<String, String> tempMap = new HashMap<String, String>();
/* 596 */     tempMap.put("czech", "latin2");
/* 597 */     tempMap.put("danish", "latin1");
/* 598 */     tempMap.put("dutch", "latin1");
/* 599 */     tempMap.put("english", "latin1");
/* 600 */     tempMap.put("estonian", "latin7");
/* 601 */     tempMap.put("french", "latin1");
/* 602 */     tempMap.put("german", "latin1");
/* 603 */     tempMap.put("greek", "greek");
/* 604 */     tempMap.put("hungarian", "latin2");
/* 605 */     tempMap.put("italian", "latin1");
/* 606 */     tempMap.put("japanese", "ujis");
/* 607 */     tempMap.put("japanese-sjis", "sjis");
/* 608 */     tempMap.put("korean", "euckr");
/* 609 */     tempMap.put("norwegian", "latin1");
/* 610 */     tempMap.put("norwegian-ny", "latin1");
/* 611 */     tempMap.put("polish", "latin2");
/* 612 */     tempMap.put("portuguese", "latin1");
/* 613 */     tempMap.put("romanian", "latin2");
/* 614 */     tempMap.put("russian", "koi8r");
/* 615 */     tempMap.put("serbian", "cp1250");
/* 616 */     tempMap.put("slovak", "latin2");
/* 617 */     tempMap.put("spanish", "latin1");
/* 618 */     tempMap.put("swedish", "latin1");
/* 619 */     tempMap.put("ukrainian", "koi8u");
/* 620 */     ERROR_MESSAGE_FILE_TO_MYSQL_CHARSET = Collections.unmodifiableMap(tempMap);
/*     */     
/* 622 */     collation = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final String getMysqlCharsetForJavaEncoding(String javaEncoding, Connection conn) throws SQLException {
/*     */     try {
/* 628 */       List<MysqlCharset> mysqlCharsets = JAVA_ENCODING_UC_TO_MYSQL_CHARSET.get(javaEncoding.toUpperCase(Locale.ENGLISH));
/*     */       
/* 630 */       if (mysqlCharsets != null) {
/* 631 */         Iterator<MysqlCharset> iter = mysqlCharsets.iterator();
/*     */         
/* 633 */         MysqlCharset versionedProp = null;
/*     */         
/* 635 */         while (iter.hasNext()) {
/* 636 */           MysqlCharset charset = iter.next();
/*     */           
/* 638 */           if (conn == null)
/*     */           {
/*     */             
/* 641 */             return charset.charsetName;
/*     */           }
/*     */           
/* 644 */           if (versionedProp == null || versionedProp.major < charset.major || versionedProp.minor < charset.minor || versionedProp.subminor < charset.subminor || (versionedProp.priority < charset.priority && versionedProp.major == charset.major && versionedProp.minor == charset.minor && versionedProp.subminor == charset.subminor))
/*     */           {
/*     */             
/* 647 */             if (charset.isOkayForVersion(conn)) {
/* 648 */               versionedProp = charset;
/*     */             }
/*     */           }
/*     */         } 
/*     */         
/* 653 */         if (versionedProp != null) {
/* 654 */           return versionedProp.charsetName;
/*     */         }
/*     */       } 
/*     */       
/* 658 */       return null;
/* 659 */     } catch (SQLException ex) {
/* 660 */       throw ex;
/* 661 */     } catch (RuntimeException ex) {
/* 662 */       SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 663 */       sqlEx.initCause(ex);
/* 664 */       throw sqlEx;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCollationIndexForJavaEncoding(String javaEncoding, Connection conn) throws SQLException {
/* 670 */     String charsetName = getMysqlCharsetForJavaEncoding(javaEncoding, (Connection)conn);
/* 671 */     if (charsetName != null) {
/* 672 */       Integer ci = CHARSET_NAME_TO_COLLATION_INDEX.get(charsetName);
/* 673 */       if (ci != null) {
/* 674 */         return ci.intValue();
/*     */       }
/*     */     } 
/* 677 */     return 0;
/*     */   }
/*     */   
/*     */   public static String getMysqlCharsetNameForCollationIndex(Integer collationIndex) {
/* 681 */     if (collationIndex != null && collationIndex.intValue() > 0 && collationIndex.intValue() < 2048) {
/* 682 */       return (COLLATION_INDEX_TO_CHARSET[collationIndex.intValue()]).charsetName;
/*     */     }
/* 684 */     return null;
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
/*     */   public static String getJavaEncodingForMysqlCharset(String mysqlCharsetName, String javaEncoding) {
/* 702 */     String res = javaEncoding;
/* 703 */     MysqlCharset cs = CHARSET_NAME_TO_CHARSET.get(mysqlCharsetName);
/* 704 */     if (cs != null) {
/* 705 */       res = cs.getMatchingJavaEncoding(javaEncoding);
/*     */     }
/* 707 */     return res;
/*     */   }
/*     */   
/*     */   public static String getJavaEncodingForMysqlCharset(String mysqlCharsetName) {
/* 711 */     return getJavaEncodingForMysqlCharset(mysqlCharsetName, null);
/*     */   }
/*     */   
/*     */   public static String getJavaEncodingForCollationIndex(Integer collationIndex, String javaEncoding) {
/* 715 */     if (collationIndex != null && collationIndex.intValue() > 0 && collationIndex.intValue() < 2048) {
/* 716 */       MysqlCharset cs = COLLATION_INDEX_TO_CHARSET[collationIndex.intValue()];
/* 717 */       return cs.getMatchingJavaEncoding(javaEncoding);
/*     */     } 
/* 719 */     return null;
/*     */   }
/*     */   
/*     */   public static String getJavaEncodingForCollationIndex(Integer collationIndex) {
/* 723 */     return getJavaEncodingForCollationIndex(collationIndex, null);
/*     */   }
/*     */   
/*     */   static final int getNumberOfCharsetsConfigured() {
/* 727 */     return numberOfEncodingsConfigured;
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
/*     */   static final String getCharacterEncodingForErrorMessages(ConnectionImpl conn) throws SQLException {
/* 747 */     if (conn.versionMeetsMinimum(5, 5, 0)) {
/* 748 */       String errorMessageCharsetName = conn.getServerVariable("jdbc.local.character_set_results");
/* 749 */       if (errorMessageCharsetName != null) {
/* 750 */         String str = getJavaEncodingForMysqlCharset(errorMessageCharsetName);
/* 751 */         if (str != null) {
/* 752 */           return str;
/*     */         }
/*     */       } 
/*     */       
/* 756 */       return "UTF-8";
/*     */     } 
/*     */     
/* 759 */     String errorMessageFile = conn.getServerVariable("language");
/*     */     
/* 761 */     if (errorMessageFile == null || errorMessageFile.length() == 0)
/*     */     {
/* 763 */       return "Cp1252";
/*     */     }
/*     */     
/* 766 */     int endWithoutSlash = errorMessageFile.length();
/*     */     
/* 768 */     if (errorMessageFile.endsWith("/") || errorMessageFile.endsWith("\\")) {
/* 769 */       endWithoutSlash--;
/*     */     }
/*     */     
/* 772 */     int lastSlashIndex = errorMessageFile.lastIndexOf('/', endWithoutSlash - 1);
/*     */     
/* 774 */     if (lastSlashIndex == -1) {
/* 775 */       lastSlashIndex = errorMessageFile.lastIndexOf('\\', endWithoutSlash - 1);
/*     */     }
/*     */     
/* 778 */     if (lastSlashIndex == -1) {
/* 779 */       lastSlashIndex = 0;
/*     */     }
/*     */     
/* 782 */     if (lastSlashIndex == endWithoutSlash || endWithoutSlash < lastSlashIndex)
/*     */     {
/* 784 */       return "Cp1252";
/*     */     }
/*     */     
/* 787 */     errorMessageFile = errorMessageFile.substring(lastSlashIndex + 1, endWithoutSlash);
/*     */     
/* 789 */     String errorMessageEncodingMysql = ERROR_MESSAGE_FILE_TO_MYSQL_CHARSET.get(errorMessageFile);
/*     */     
/* 791 */     if (errorMessageEncodingMysql == null)
/*     */     {
/* 793 */       return "Cp1252";
/*     */     }
/*     */     
/* 796 */     String javaEncoding = getJavaEncodingForMysqlCharset(errorMessageEncodingMysql);
/*     */     
/* 798 */     if (javaEncoding == null)
/*     */     {
/* 800 */       return "Cp1252";
/*     */     }
/*     */     
/* 803 */     return javaEncoding;
/*     */   }
/*     */   
/*     */   static final boolean requiresEscapeEasternUnicode(String javaEncodingName) {
/* 807 */     return ESCAPE_ENCODINGS.contains(javaEncodingName.toUpperCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean isMultibyteCharset(String javaEncodingName) {
/* 816 */     return MULTIBYTE_ENCODINGS.contains(javaEncodingName.toUpperCase(Locale.ENGLISH));
/*     */   }
/*     */   
/*     */   public static int getMblen(String charsetName) {
/* 820 */     if (charsetName != null) {
/* 821 */       MysqlCharset cs = CHARSET_NAME_TO_CHARSET.get(charsetName);
/* 822 */       if (cs != null) {
/* 823 */         return cs.mblen;
/*     */       }
/*     */     } 
/* 826 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\CharsetMapping.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */