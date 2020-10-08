/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import com.mysql.jdbc.exceptions.MySQLStatementCancelledException;
/*      */ import com.mysql.jdbc.exceptions.MySQLTimeoutException;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.StringReader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.net.URL;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.CharBuffer;
/*      */ import java.nio.charset.Charset;
/*      */ import java.nio.charset.CharsetEncoder;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.Date;
/*      */ import java.sql.ParameterMetaData;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.text.DateFormat;
/*      */ import java.text.ParsePosition;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.TimeZone;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PreparedStatement
/*      */   extends StatementImpl
/*      */   implements PreparedStatement
/*      */ {
/*      */   private static final Constructor<?> JDBC_4_PSTMT_2_ARG_CTOR;
/*      */   private static final Constructor<?> JDBC_4_PSTMT_3_ARG_CTOR;
/*      */   private static final Constructor<?> JDBC_4_PSTMT_4_ARG_CTOR;
/*      */   
/*      */   static {
/*   82 */     if (Util.isJdbc4()) {
/*      */       try {
/*   84 */         String jdbc4ClassName = Util.isJdbc42() ? "com.mysql.jdbc.JDBC42PreparedStatement" : "com.mysql.jdbc.JDBC4PreparedStatement";
/*   85 */         JDBC_4_PSTMT_2_ARG_CTOR = Class.forName(jdbc4ClassName).getConstructor(new Class[] { MySQLConnection.class, String.class });
/*   86 */         JDBC_4_PSTMT_3_ARG_CTOR = Class.forName(jdbc4ClassName).getConstructor(new Class[] { MySQLConnection.class, String.class, String.class });
/*   87 */         JDBC_4_PSTMT_4_ARG_CTOR = Class.forName(jdbc4ClassName).getConstructor(new Class[] { MySQLConnection.class, String.class, String.class, ParseInfo.class });
/*      */       }
/*   89 */       catch (SecurityException e) {
/*   90 */         throw new RuntimeException(e);
/*   91 */       } catch (NoSuchMethodException e) {
/*   92 */         throw new RuntimeException(e);
/*   93 */       } catch (ClassNotFoundException e) {
/*   94 */         throw new RuntimeException(e);
/*      */       } 
/*      */     } else {
/*   97 */       JDBC_4_PSTMT_2_ARG_CTOR = null;
/*   98 */       JDBC_4_PSTMT_3_ARG_CTOR = null;
/*   99 */       JDBC_4_PSTMT_4_ARG_CTOR = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public class BatchParams {
/*  104 */     public boolean[] isNull = null;
/*      */     
/*  106 */     public boolean[] isStream = null;
/*      */     
/*  108 */     public InputStream[] parameterStreams = null;
/*      */     
/*  110 */     public byte[][] parameterStrings = (byte[][])null;
/*      */     
/*  112 */     public int[] streamLengths = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     BatchParams(byte[][] strings, InputStream[] streams, boolean[] isStreamFlags, int[] lengths, boolean[] isNullFlags) {
/*  118 */       this.parameterStrings = new byte[strings.length][];
/*  119 */       this.parameterStreams = new InputStream[streams.length];
/*  120 */       this.isStream = new boolean[isStreamFlags.length];
/*  121 */       this.streamLengths = new int[lengths.length];
/*  122 */       this.isNull = new boolean[isNullFlags.length];
/*  123 */       System.arraycopy(strings, 0, this.parameterStrings, 0, strings.length);
/*  124 */       System.arraycopy(streams, 0, this.parameterStreams, 0, streams.length);
/*  125 */       System.arraycopy(isStreamFlags, 0, this.isStream, 0, isStreamFlags.length);
/*  126 */       System.arraycopy(lengths, 0, this.streamLengths, 0, lengths.length);
/*  127 */       System.arraycopy(isNullFlags, 0, this.isNull, 0, isNullFlags.length);
/*      */     }
/*      */   }
/*      */   
/*      */   class EndPoint
/*      */   {
/*      */     int begin;
/*      */     int end;
/*      */     
/*      */     EndPoint(int b, int e) {
/*  137 */       this.begin = b;
/*  138 */       this.end = e;
/*      */     }
/*      */   }
/*      */   
/*      */   public static final class ParseInfo {
/*  143 */     char firstStmtChar = Character.MIN_VALUE;
/*      */     
/*      */     boolean foundLoadData = false;
/*      */     
/*  147 */     long lastUsed = 0L;
/*      */     
/*  149 */     int statementLength = 0;
/*      */     
/*  151 */     int statementStartPos = 0;
/*      */     
/*      */     boolean canRewriteAsMultiValueInsert = false;
/*      */     
/*  155 */     byte[][] staticSql = (byte[][])null;
/*      */     
/*      */     boolean hasPlaceholders = false;
/*      */     
/*  159 */     int numberOfQueries = 1;
/*      */     
/*      */     boolean isOnDuplicateKeyUpdate = false;
/*      */     
/*  163 */     int locationOfOnDuplicateKeyUpdate = -1;
/*      */     
/*      */     String valuesClause;
/*      */     
/*      */     boolean parametersInDuplicateKeyClause = false;
/*      */     
/*      */     String charEncoding;
/*      */     
/*      */     private ParseInfo batchHead;
/*      */     private ParseInfo batchValues;
/*      */     private ParseInfo batchODKUClause;
/*      */     
/*      */     ParseInfo(String sql, MySQLConnection conn, DatabaseMetaData dbmd, String encoding, SingleByteCharsetConverter converter) throws SQLException {
/*  176 */       this(sql, conn, dbmd, encoding, converter, true);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ParseInfo(String sql, MySQLConnection conn, DatabaseMetaData dbmd, String encoding, SingleByteCharsetConverter converter, boolean buildRewriteInfo) throws SQLException {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: invokespecial <init> : ()V
/*      */       //   4: aload_0
/*      */       //   5: iconst_0
/*      */       //   6: putfield firstStmtChar : C
/*      */       //   9: aload_0
/*      */       //   10: iconst_0
/*      */       //   11: putfield foundLoadData : Z
/*      */       //   14: aload_0
/*      */       //   15: lconst_0
/*      */       //   16: putfield lastUsed : J
/*      */       //   19: aload_0
/*      */       //   20: iconst_0
/*      */       //   21: putfield statementLength : I
/*      */       //   24: aload_0
/*      */       //   25: iconst_0
/*      */       //   26: putfield statementStartPos : I
/*      */       //   29: aload_0
/*      */       //   30: iconst_0
/*      */       //   31: putfield canRewriteAsMultiValueInsert : Z
/*      */       //   34: aload_0
/*      */       //   35: aconst_null
/*      */       //   36: checkcast [[B
/*      */       //   39: putfield staticSql : [[B
/*      */       //   42: aload_0
/*      */       //   43: iconst_0
/*      */       //   44: putfield hasPlaceholders : Z
/*      */       //   47: aload_0
/*      */       //   48: iconst_1
/*      */       //   49: putfield numberOfQueries : I
/*      */       //   52: aload_0
/*      */       //   53: iconst_0
/*      */       //   54: putfield isOnDuplicateKeyUpdate : Z
/*      */       //   57: aload_0
/*      */       //   58: iconst_m1
/*      */       //   59: putfield locationOfOnDuplicateKeyUpdate : I
/*      */       //   62: aload_0
/*      */       //   63: iconst_0
/*      */       //   64: putfield parametersInDuplicateKeyClause : Z
/*      */       //   67: aload_1
/*      */       //   68: ifnonnull -> 88
/*      */       //   71: ldc 'PreparedStatement.61'
/*      */       //   73: invokestatic getString : (Ljava/lang/String;)Ljava/lang/String;
/*      */       //   76: ldc 'S1009'
/*      */       //   78: aload_2
/*      */       //   79: invokeinterface getExceptionInterceptor : ()Lcom/mysql/jdbc/ExceptionInterceptor;
/*      */       //   84: invokestatic createSQLException : (Ljava/lang/String;Ljava/lang/String;Lcom/mysql/jdbc/ExceptionInterceptor;)Ljava/sql/SQLException;
/*      */       //   87: athrow
/*      */       //   88: aload_0
/*      */       //   89: aload #4
/*      */       //   91: putfield charEncoding : Ljava/lang/String;
/*      */       //   94: aload_0
/*      */       //   95: invokestatic currentTimeMillis : ()J
/*      */       //   98: putfield lastUsed : J
/*      */       //   101: aload_3
/*      */       //   102: invokeinterface getIdentifierQuoteString : ()Ljava/lang/String;
/*      */       //   107: astore #7
/*      */       //   109: iconst_0
/*      */       //   110: istore #8
/*      */       //   112: aload #7
/*      */       //   114: ifnull -> 143
/*      */       //   117: aload #7
/*      */       //   119: ldc ' '
/*      */       //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */       //   124: ifne -> 143
/*      */       //   127: aload #7
/*      */       //   129: invokevirtual length : ()I
/*      */       //   132: ifle -> 143
/*      */       //   135: aload #7
/*      */       //   137: iconst_0
/*      */       //   138: invokevirtual charAt : (I)C
/*      */       //   141: istore #8
/*      */       //   143: aload_0
/*      */       //   144: aload_1
/*      */       //   145: invokevirtual length : ()I
/*      */       //   148: putfield statementLength : I
/*      */       //   151: new java/util/ArrayList
/*      */       //   154: dup
/*      */       //   155: invokespecial <init> : ()V
/*      */       //   158: astore #9
/*      */       //   160: iconst_0
/*      */       //   161: istore #10
/*      */       //   163: iconst_0
/*      */       //   164: istore #11
/*      */       //   166: iconst_0
/*      */       //   167: istore #12
/*      */       //   169: iconst_0
/*      */       //   170: istore #13
/*      */       //   172: aload_2
/*      */       //   173: invokeinterface isNoBackslashEscapesSet : ()Z
/*      */       //   178: istore #15
/*      */       //   180: aload_0
/*      */       //   181: aload_1
/*      */       //   182: invokestatic findStartOfStatement : (Ljava/lang/String;)I
/*      */       //   185: putfield statementStartPos : I
/*      */       //   188: aload_0
/*      */       //   189: getfield statementStartPos : I
/*      */       //   192: istore #14
/*      */       //   194: iload #14
/*      */       //   196: aload_0
/*      */       //   197: getfield statementLength : I
/*      */       //   200: if_icmpge -> 845
/*      */       //   203: aload_1
/*      */       //   204: iload #14
/*      */       //   206: invokevirtual charAt : (I)C
/*      */       //   209: istore #16
/*      */       //   211: aload_0
/*      */       //   212: getfield firstStmtChar : C
/*      */       //   215: ifne -> 287
/*      */       //   218: iload #16
/*      */       //   220: invokestatic isLetter : (C)Z
/*      */       //   223: ifeq -> 287
/*      */       //   226: aload_0
/*      */       //   227: iload #16
/*      */       //   229: invokestatic toUpperCase : (C)C
/*      */       //   232: putfield firstStmtChar : C
/*      */       //   235: aload_0
/*      */       //   236: getfield firstStmtChar : C
/*      */       //   239: bipush #73
/*      */       //   241: if_icmpne -> 287
/*      */       //   244: aload_0
/*      */       //   245: aload_1
/*      */       //   246: aload_2
/*      */       //   247: invokeinterface getDontCheckOnDuplicateKeyUpdateInSQL : ()Z
/*      */       //   252: aload_2
/*      */       //   253: invokeinterface getRewriteBatchedStatements : ()Z
/*      */       //   258: aload_2
/*      */       //   259: invokeinterface isNoBackslashEscapesSet : ()Z
/*      */       //   264: invokestatic getOnDuplicateKeyLocation : (Ljava/lang/String;ZZZ)I
/*      */       //   267: putfield locationOfOnDuplicateKeyUpdate : I
/*      */       //   270: aload_0
/*      */       //   271: aload_0
/*      */       //   272: getfield locationOfOnDuplicateKeyUpdate : I
/*      */       //   275: iconst_m1
/*      */       //   276: if_icmpeq -> 283
/*      */       //   279: iconst_1
/*      */       //   280: goto -> 284
/*      */       //   283: iconst_0
/*      */       //   284: putfield isOnDuplicateKeyUpdate : Z
/*      */       //   287: iload #15
/*      */       //   289: ifne -> 316
/*      */       //   292: iload #16
/*      */       //   294: bipush #92
/*      */       //   296: if_icmpne -> 316
/*      */       //   299: iload #14
/*      */       //   301: aload_0
/*      */       //   302: getfield statementLength : I
/*      */       //   305: iconst_1
/*      */       //   306: isub
/*      */       //   307: if_icmpge -> 316
/*      */       //   310: iinc #14, 1
/*      */       //   313: goto -> 839
/*      */       //   316: iload #10
/*      */       //   318: ifne -> 348
/*      */       //   321: iload #8
/*      */       //   323: ifeq -> 348
/*      */       //   326: iload #16
/*      */       //   328: iload #8
/*      */       //   330: if_icmpne -> 348
/*      */       //   333: iload #12
/*      */       //   335: ifne -> 342
/*      */       //   338: iconst_1
/*      */       //   339: goto -> 343
/*      */       //   342: iconst_0
/*      */       //   343: istore #12
/*      */       //   345: goto -> 696
/*      */       //   348: iload #12
/*      */       //   350: ifne -> 696
/*      */       //   353: iload #10
/*      */       //   355: ifeq -> 466
/*      */       //   358: iload #16
/*      */       //   360: bipush #39
/*      */       //   362: if_icmpeq -> 372
/*      */       //   365: iload #16
/*      */       //   367: bipush #34
/*      */       //   369: if_icmpne -> 427
/*      */       //   372: iload #16
/*      */       //   374: iload #11
/*      */       //   376: if_icmpne -> 427
/*      */       //   379: iload #14
/*      */       //   381: aload_0
/*      */       //   382: getfield statementLength : I
/*      */       //   385: iconst_1
/*      */       //   386: isub
/*      */       //   387: if_icmpge -> 409
/*      */       //   390: aload_1
/*      */       //   391: iload #14
/*      */       //   393: iconst_1
/*      */       //   394: iadd
/*      */       //   395: invokevirtual charAt : (I)C
/*      */       //   398: iload #11
/*      */       //   400: if_icmpne -> 409
/*      */       //   403: iinc #14, 1
/*      */       //   406: goto -> 839
/*      */       //   409: iload #10
/*      */       //   411: ifne -> 418
/*      */       //   414: iconst_1
/*      */       //   415: goto -> 419
/*      */       //   418: iconst_0
/*      */       //   419: istore #10
/*      */       //   421: iconst_0
/*      */       //   422: istore #11
/*      */       //   424: goto -> 696
/*      */       //   427: iload #16
/*      */       //   429: bipush #39
/*      */       //   431: if_icmpeq -> 441
/*      */       //   434: iload #16
/*      */       //   436: bipush #34
/*      */       //   438: if_icmpne -> 696
/*      */       //   441: iload #16
/*      */       //   443: iload #11
/*      */       //   445: if_icmpne -> 696
/*      */       //   448: iload #10
/*      */       //   450: ifne -> 457
/*      */       //   453: iconst_1
/*      */       //   454: goto -> 458
/*      */       //   457: iconst_0
/*      */       //   458: istore #10
/*      */       //   460: iconst_0
/*      */       //   461: istore #11
/*      */       //   463: goto -> 696
/*      */       //   466: iload #16
/*      */       //   468: bipush #35
/*      */       //   470: if_icmpeq -> 504
/*      */       //   473: iload #16
/*      */       //   475: bipush #45
/*      */       //   477: if_icmpne -> 550
/*      */       //   480: iload #14
/*      */       //   482: iconst_1
/*      */       //   483: iadd
/*      */       //   484: aload_0
/*      */       //   485: getfield statementLength : I
/*      */       //   488: if_icmpge -> 550
/*      */       //   491: aload_1
/*      */       //   492: iload #14
/*      */       //   494: iconst_1
/*      */       //   495: iadd
/*      */       //   496: invokevirtual charAt : (I)C
/*      */       //   499: bipush #45
/*      */       //   501: if_icmpne -> 550
/*      */       //   504: aload_0
/*      */       //   505: getfield statementLength : I
/*      */       //   508: iconst_1
/*      */       //   509: isub
/*      */       //   510: istore #17
/*      */       //   512: iload #14
/*      */       //   514: iload #17
/*      */       //   516: if_icmpge -> 839
/*      */       //   519: aload_1
/*      */       //   520: iload #14
/*      */       //   522: invokevirtual charAt : (I)C
/*      */       //   525: istore #16
/*      */       //   527: iload #16
/*      */       //   529: bipush #13
/*      */       //   531: if_icmpeq -> 839
/*      */       //   534: iload #16
/*      */       //   536: bipush #10
/*      */       //   538: if_icmpne -> 544
/*      */       //   541: goto -> 839
/*      */       //   544: iinc #14, 1
/*      */       //   547: goto -> 512
/*      */       //   550: iload #16
/*      */       //   552: bipush #47
/*      */       //   554: if_icmpne -> 675
/*      */       //   557: iload #14
/*      */       //   559: iconst_1
/*      */       //   560: iadd
/*      */       //   561: aload_0
/*      */       //   562: getfield statementLength : I
/*      */       //   565: if_icmpge -> 675
/*      */       //   568: aload_1
/*      */       //   569: iload #14
/*      */       //   571: iconst_1
/*      */       //   572: iadd
/*      */       //   573: invokevirtual charAt : (I)C
/*      */       //   576: istore #17
/*      */       //   578: iload #17
/*      */       //   580: bipush #42
/*      */       //   582: if_icmpne -> 672
/*      */       //   585: iinc #14, 2
/*      */       //   588: iload #14
/*      */       //   590: istore #18
/*      */       //   592: iload #18
/*      */       //   594: aload_0
/*      */       //   595: getfield statementLength : I
/*      */       //   598: if_icmpge -> 672
/*      */       //   601: iinc #14, 1
/*      */       //   604: aload_1
/*      */       //   605: iload #18
/*      */       //   607: invokevirtual charAt : (I)C
/*      */       //   610: istore #17
/*      */       //   612: iload #17
/*      */       //   614: bipush #42
/*      */       //   616: if_icmpne -> 666
/*      */       //   619: iload #18
/*      */       //   621: iconst_1
/*      */       //   622: iadd
/*      */       //   623: aload_0
/*      */       //   624: getfield statementLength : I
/*      */       //   627: if_icmpge -> 666
/*      */       //   630: aload_1
/*      */       //   631: iload #18
/*      */       //   633: iconst_1
/*      */       //   634: iadd
/*      */       //   635: invokevirtual charAt : (I)C
/*      */       //   638: bipush #47
/*      */       //   640: if_icmpne -> 666
/*      */       //   643: iinc #14, 1
/*      */       //   646: iload #14
/*      */       //   648: aload_0
/*      */       //   649: getfield statementLength : I
/*      */       //   652: if_icmpge -> 672
/*      */       //   655: aload_1
/*      */       //   656: iload #14
/*      */       //   658: invokevirtual charAt : (I)C
/*      */       //   661: istore #16
/*      */       //   663: goto -> 672
/*      */       //   666: iinc #18, 1
/*      */       //   669: goto -> 592
/*      */       //   672: goto -> 696
/*      */       //   675: iload #16
/*      */       //   677: bipush #39
/*      */       //   679: if_icmpeq -> 689
/*      */       //   682: iload #16
/*      */       //   684: bipush #34
/*      */       //   686: if_icmpne -> 696
/*      */       //   689: iconst_1
/*      */       //   690: istore #10
/*      */       //   692: iload #16
/*      */       //   694: istore #11
/*      */       //   696: iload #10
/*      */       //   698: ifne -> 839
/*      */       //   701: iload #12
/*      */       //   703: ifne -> 839
/*      */       //   706: iload #16
/*      */       //   708: bipush #63
/*      */       //   710: if_icmpne -> 762
/*      */       //   713: aload #9
/*      */       //   715: iconst_2
/*      */       //   716: newarray int
/*      */       //   718: dup
/*      */       //   719: iconst_0
/*      */       //   720: iload #13
/*      */       //   722: iastore
/*      */       //   723: dup
/*      */       //   724: iconst_1
/*      */       //   725: iload #14
/*      */       //   727: iastore
/*      */       //   728: invokevirtual add : (Ljava/lang/Object;)Z
/*      */       //   731: pop
/*      */       //   732: iload #14
/*      */       //   734: iconst_1
/*      */       //   735: iadd
/*      */       //   736: istore #13
/*      */       //   738: aload_0
/*      */       //   739: getfield isOnDuplicateKeyUpdate : Z
/*      */       //   742: ifeq -> 839
/*      */       //   745: iload #14
/*      */       //   747: aload_0
/*      */       //   748: getfield locationOfOnDuplicateKeyUpdate : I
/*      */       //   751: if_icmple -> 839
/*      */       //   754: aload_0
/*      */       //   755: iconst_1
/*      */       //   756: putfield parametersInDuplicateKeyClause : Z
/*      */       //   759: goto -> 839
/*      */       //   762: iload #16
/*      */       //   764: bipush #59
/*      */       //   766: if_icmpne -> 839
/*      */       //   769: iload #14
/*      */       //   771: iconst_1
/*      */       //   772: iadd
/*      */       //   773: istore #17
/*      */       //   775: iload #17
/*      */       //   777: aload_0
/*      */       //   778: getfield statementLength : I
/*      */       //   781: if_icmpge -> 839
/*      */       //   784: iload #17
/*      */       //   786: aload_0
/*      */       //   787: getfield statementLength : I
/*      */       //   790: if_icmpge -> 814
/*      */       //   793: aload_1
/*      */       //   794: iload #17
/*      */       //   796: invokevirtual charAt : (I)C
/*      */       //   799: invokestatic isWhitespace : (C)Z
/*      */       //   802: ifne -> 808
/*      */       //   805: goto -> 814
/*      */       //   808: iinc #17, 1
/*      */       //   811: goto -> 784
/*      */       //   814: iload #17
/*      */       //   816: aload_0
/*      */       //   817: getfield statementLength : I
/*      */       //   820: if_icmpge -> 833
/*      */       //   823: aload_0
/*      */       //   824: dup
/*      */       //   825: getfield numberOfQueries : I
/*      */       //   828: iconst_1
/*      */       //   829: iadd
/*      */       //   830: putfield numberOfQueries : I
/*      */       //   833: iload #17
/*      */       //   835: iconst_1
/*      */       //   836: isub
/*      */       //   837: istore #14
/*      */       //   839: iinc #14, 1
/*      */       //   842: goto -> 194
/*      */       //   845: aload_0
/*      */       //   846: getfield firstStmtChar : C
/*      */       //   849: bipush #76
/*      */       //   851: if_icmpne -> 879
/*      */       //   854: aload_1
/*      */       //   855: ldc 'LOAD DATA'
/*      */       //   857: invokestatic startsWithIgnoreCaseAndWs : (Ljava/lang/String;Ljava/lang/String;)Z
/*      */       //   860: ifeq -> 871
/*      */       //   863: aload_0
/*      */       //   864: iconst_1
/*      */       //   865: putfield foundLoadData : Z
/*      */       //   868: goto -> 884
/*      */       //   871: aload_0
/*      */       //   872: iconst_0
/*      */       //   873: putfield foundLoadData : Z
/*      */       //   876: goto -> 884
/*      */       //   879: aload_0
/*      */       //   880: iconst_0
/*      */       //   881: putfield foundLoadData : Z
/*      */       //   884: aload #9
/*      */       //   886: iconst_2
/*      */       //   887: newarray int
/*      */       //   889: dup
/*      */       //   890: iconst_0
/*      */       //   891: iload #13
/*      */       //   893: iastore
/*      */       //   894: dup
/*      */       //   895: iconst_1
/*      */       //   896: aload_0
/*      */       //   897: getfield statementLength : I
/*      */       //   900: iastore
/*      */       //   901: invokevirtual add : (Ljava/lang/Object;)Z
/*      */       //   904: pop
/*      */       //   905: aload_0
/*      */       //   906: aload #9
/*      */       //   908: invokevirtual size : ()I
/*      */       //   911: anewarray [B
/*      */       //   914: putfield staticSql : [[B
/*      */       //   917: aload_0
/*      */       //   918: aload_0
/*      */       //   919: getfield staticSql : [[B
/*      */       //   922: arraylength
/*      */       //   923: iconst_1
/*      */       //   924: if_icmple -> 931
/*      */       //   927: iconst_1
/*      */       //   928: goto -> 932
/*      */       //   931: iconst_0
/*      */       //   932: putfield hasPlaceholders : Z
/*      */       //   935: iconst_0
/*      */       //   936: istore #14
/*      */       //   938: iload #14
/*      */       //   940: aload_0
/*      */       //   941: getfield staticSql : [[B
/*      */       //   944: arraylength
/*      */       //   945: if_icmpge -> 1145
/*      */       //   948: aload #9
/*      */       //   950: iload #14
/*      */       //   952: invokevirtual get : (I)Ljava/lang/Object;
/*      */       //   955: checkcast [I
/*      */       //   958: astore #16
/*      */       //   960: aload #16
/*      */       //   962: iconst_1
/*      */       //   963: iaload
/*      */       //   964: istore #17
/*      */       //   966: aload #16
/*      */       //   968: iconst_0
/*      */       //   969: iaload
/*      */       //   970: istore #18
/*      */       //   972: iload #17
/*      */       //   974: iload #18
/*      */       //   976: isub
/*      */       //   977: istore #19
/*      */       //   979: aload_0
/*      */       //   980: getfield foundLoadData : Z
/*      */       //   983: ifeq -> 1004
/*      */       //   986: aload_0
/*      */       //   987: getfield staticSql : [[B
/*      */       //   990: iload #14
/*      */       //   992: aload_1
/*      */       //   993: iload #18
/*      */       //   995: iload #19
/*      */       //   997: invokestatic getBytes : (Ljava/lang/String;II)[B
/*      */       //   1000: aastore
/*      */       //   1001: goto -> 1139
/*      */       //   1004: aload #4
/*      */       //   1006: ifnonnull -> 1058
/*      */       //   1009: iload #19
/*      */       //   1011: newarray byte
/*      */       //   1013: astore #20
/*      */       //   1015: iconst_0
/*      */       //   1016: istore #21
/*      */       //   1018: iload #21
/*      */       //   1020: iload #19
/*      */       //   1022: if_icmpge -> 1046
/*      */       //   1025: aload #20
/*      */       //   1027: iload #21
/*      */       //   1029: aload_1
/*      */       //   1030: iload #18
/*      */       //   1032: iload #21
/*      */       //   1034: iadd
/*      */       //   1035: invokevirtual charAt : (I)C
/*      */       //   1038: i2b
/*      */       //   1039: bastore
/*      */       //   1040: iinc #21, 1
/*      */       //   1043: goto -> 1018
/*      */       //   1046: aload_0
/*      */       //   1047: getfield staticSql : [[B
/*      */       //   1050: iload #14
/*      */       //   1052: aload #20
/*      */       //   1054: aastore
/*      */       //   1055: goto -> 1139
/*      */       //   1058: aload #5
/*      */       //   1060: ifnull -> 1103
/*      */       //   1063: aload_0
/*      */       //   1064: getfield staticSql : [[B
/*      */       //   1067: iload #14
/*      */       //   1069: aload_1
/*      */       //   1070: aload #5
/*      */       //   1072: aload #4
/*      */       //   1074: aload_2
/*      */       //   1075: invokeinterface getServerCharset : ()Ljava/lang/String;
/*      */       //   1080: iload #18
/*      */       //   1082: iload #19
/*      */       //   1084: aload_2
/*      */       //   1085: invokeinterface parserKnowsUnicode : ()Z
/*      */       //   1090: aload_2
/*      */       //   1091: invokeinterface getExceptionInterceptor : ()Lcom/mysql/jdbc/ExceptionInterceptor;
/*      */       //   1096: invokestatic getBytes : (Ljava/lang/String;Lcom/mysql/jdbc/SingleByteCharsetConverter;Ljava/lang/String;Ljava/lang/String;IIZLcom/mysql/jdbc/ExceptionInterceptor;)[B
/*      */       //   1099: aastore
/*      */       //   1100: goto -> 1139
/*      */       //   1103: aload_0
/*      */       //   1104: getfield staticSql : [[B
/*      */       //   1107: iload #14
/*      */       //   1109: aload_1
/*      */       //   1110: aload #4
/*      */       //   1112: aload_2
/*      */       //   1113: invokeinterface getServerCharset : ()Ljava/lang/String;
/*      */       //   1118: iload #18
/*      */       //   1120: iload #19
/*      */       //   1122: aload_2
/*      */       //   1123: invokeinterface parserKnowsUnicode : ()Z
/*      */       //   1128: aload_2
/*      */       //   1129: aload_2
/*      */       //   1130: invokeinterface getExceptionInterceptor : ()Lcom/mysql/jdbc/ExceptionInterceptor;
/*      */       //   1135: invokestatic getBytes : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZLcom/mysql/jdbc/MySQLConnection;Lcom/mysql/jdbc/ExceptionInterceptor;)[B
/*      */       //   1138: aastore
/*      */       //   1139: iinc #14, 1
/*      */       //   1142: goto -> 938
/*      */       //   1145: goto -> 1189
/*      */       //   1148: astore #7
/*      */       //   1150: new java/sql/SQLException
/*      */       //   1153: dup
/*      */       //   1154: new java/lang/StringBuilder
/*      */       //   1157: dup
/*      */       //   1158: invokespecial <init> : ()V
/*      */       //   1161: ldc 'Parse error for '
/*      */       //   1163: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   1166: aload_1
/*      */       //   1167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*      */       //   1170: invokevirtual toString : ()Ljava/lang/String;
/*      */       //   1173: invokespecial <init> : (Ljava/lang/String;)V
/*      */       //   1176: astore #8
/*      */       //   1178: aload #8
/*      */       //   1180: aload #7
/*      */       //   1182: invokevirtual initCause : (Ljava/lang/Throwable;)Ljava/lang/Throwable;
/*      */       //   1185: pop
/*      */       //   1186: aload #8
/*      */       //   1188: athrow
/*      */       //   1189: iload #6
/*      */       //   1191: ifeq -> 1264
/*      */       //   1194: aload_0
/*      */       //   1195: aload_0
/*      */       //   1196: getfield numberOfQueries : I
/*      */       //   1199: iconst_1
/*      */       //   1200: if_icmpne -> 1233
/*      */       //   1203: aload_0
/*      */       //   1204: getfield parametersInDuplicateKeyClause : Z
/*      */       //   1207: ifne -> 1233
/*      */       //   1210: aload_1
/*      */       //   1211: aload_0
/*      */       //   1212: getfield isOnDuplicateKeyUpdate : Z
/*      */       //   1215: aload_0
/*      */       //   1216: getfield locationOfOnDuplicateKeyUpdate : I
/*      */       //   1219: aload_0
/*      */       //   1220: getfield statementStartPos : I
/*      */       //   1223: invokestatic canRewrite : (Ljava/lang/String;ZII)Z
/*      */       //   1226: ifeq -> 1233
/*      */       //   1229: iconst_1
/*      */       //   1230: goto -> 1234
/*      */       //   1233: iconst_0
/*      */       //   1234: putfield canRewriteAsMultiValueInsert : Z
/*      */       //   1237: aload_0
/*      */       //   1238: getfield canRewriteAsMultiValueInsert : Z
/*      */       //   1241: ifeq -> 1264
/*      */       //   1244: aload_2
/*      */       //   1245: invokeinterface getRewriteBatchedStatements : ()Z
/*      */       //   1250: ifeq -> 1264
/*      */       //   1253: aload_0
/*      */       //   1254: aload_1
/*      */       //   1255: aload_2
/*      */       //   1256: aload_3
/*      */       //   1257: aload #4
/*      */       //   1259: aload #5
/*      */       //   1261: invokespecial buildRewriteBatchedParams : (Ljava/lang/String;Lcom/mysql/jdbc/MySQLConnection;Ljava/sql/DatabaseMetaData;Ljava/lang/String;Lcom/mysql/jdbc/SingleByteCharsetConverter;)V
/*      */       //   1264: return
/*      */       // Line number table:
/*      */       //   Java source line number -> byte code offset
/*      */       //   #180	-> 0
/*      */       //   #143	-> 4
/*      */       //   #145	-> 9
/*      */       //   #147	-> 14
/*      */       //   #149	-> 19
/*      */       //   #151	-> 24
/*      */       //   #153	-> 29
/*      */       //   #155	-> 34
/*      */       //   #157	-> 42
/*      */       //   #159	-> 47
/*      */       //   #161	-> 52
/*      */       //   #163	-> 57
/*      */       //   #167	-> 62
/*      */       //   #182	-> 67
/*      */       //   #183	-> 71
/*      */       //   #187	-> 88
/*      */       //   #188	-> 94
/*      */       //   #190	-> 101
/*      */       //   #192	-> 109
/*      */       //   #194	-> 112
/*      */       //   #195	-> 135
/*      */       //   #198	-> 143
/*      */       //   #200	-> 151
/*      */       //   #201	-> 160
/*      */       //   #202	-> 163
/*      */       //   #203	-> 166
/*      */       //   #204	-> 169
/*      */       //   #207	-> 172
/*      */       //   #212	-> 180
/*      */       //   #214	-> 188
/*      */       //   #215	-> 203
/*      */       //   #217	-> 211
/*      */       //   #219	-> 226
/*      */       //   #222	-> 235
/*      */       //   #223	-> 244
/*      */       //   #225	-> 270
/*      */       //   #229	-> 287
/*      */       //   #230	-> 310
/*      */       //   #231	-> 313
/*      */       //   #235	-> 316
/*      */       //   #236	-> 333
/*      */       //   #237	-> 348
/*      */       //   #240	-> 353
/*      */       //   #241	-> 358
/*      */       //   #242	-> 379
/*      */       //   #243	-> 403
/*      */       //   #244	-> 406
/*      */       //   #247	-> 409
/*      */       //   #248	-> 421
/*      */       //   #249	-> 427
/*      */       //   #250	-> 448
/*      */       //   #251	-> 460
/*      */       //   #254	-> 466
/*      */       //   #256	-> 504
/*      */       //   #258	-> 512
/*      */       //   #259	-> 519
/*      */       //   #261	-> 527
/*      */       //   #262	-> 541
/*      */       //   #258	-> 544
/*      */       //   #267	-> 550
/*      */       //   #269	-> 568
/*      */       //   #271	-> 578
/*      */       //   #272	-> 585
/*      */       //   #274	-> 588
/*      */       //   #275	-> 601
/*      */       //   #276	-> 604
/*      */       //   #278	-> 612
/*      */       //   #279	-> 630
/*      */       //   #280	-> 643
/*      */       //   #282	-> 646
/*      */       //   #283	-> 655
/*      */       //   #274	-> 666
/*      */       //   #291	-> 672
/*      */       //   #292	-> 689
/*      */       //   #293	-> 692
/*      */       //   #298	-> 696
/*      */       //   #299	-> 706
/*      */       //   #300	-> 713
/*      */       //   #301	-> 732
/*      */       //   #303	-> 738
/*      */       //   #304	-> 754
/*      */       //   #306	-> 762
/*      */       //   #307	-> 769
/*      */       //   #308	-> 775
/*      */       //   #309	-> 784
/*      */       //   #310	-> 793
/*      */       //   #311	-> 805
/*      */       //   #309	-> 808
/*      */       //   #314	-> 814
/*      */       //   #315	-> 823
/*      */       //   #317	-> 833
/*      */       //   #214	-> 839
/*      */       //   #323	-> 845
/*      */       //   #324	-> 854
/*      */       //   #325	-> 863
/*      */       //   #327	-> 871
/*      */       //   #330	-> 879
/*      */       //   #333	-> 884
/*      */       //   #334	-> 905
/*      */       //   #335	-> 917
/*      */       //   #337	-> 935
/*      */       //   #338	-> 948
/*      */       //   #339	-> 960
/*      */       //   #340	-> 966
/*      */       //   #341	-> 972
/*      */       //   #343	-> 979
/*      */       //   #344	-> 986
/*      */       //   #345	-> 1004
/*      */       //   #346	-> 1009
/*      */       //   #348	-> 1015
/*      */       //   #349	-> 1025
/*      */       //   #348	-> 1040
/*      */       //   #352	-> 1046
/*      */       //   #353	-> 1055
/*      */       //   #354	-> 1058
/*      */       //   #355	-> 1063
/*      */       //   #358	-> 1103
/*      */       //   #337	-> 1139
/*      */       //   #368	-> 1145
/*      */       //   #363	-> 1148
/*      */       //   #364	-> 1150
/*      */       //   #365	-> 1178
/*      */       //   #367	-> 1186
/*      */       //   #370	-> 1189
/*      */       //   #371	-> 1194
/*      */       //   #374	-> 1237
/*      */       //   #375	-> 1253
/*      */       //   #378	-> 1264
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	descriptor
/*      */       //   512	38	17	endOfStmt	I
/*      */       //   592	80	18	j	I
/*      */       //   578	94	17	cNext	C
/*      */       //   775	64	17	j	I
/*      */       //   211	628	16	c	C
/*      */       //   1018	28	21	j	I
/*      */       //   1015	40	20	buf	[B
/*      */       //   960	179	16	ep	[I
/*      */       //   966	173	17	end	I
/*      */       //   972	167	18	begin	I
/*      */       //   979	160	19	len	I
/*      */       //   109	1036	7	quotedIdentifierString	Ljava/lang/String;
/*      */       //   112	1033	8	quotedIdentifierChar	C
/*      */       //   160	985	9	endpointList	Ljava/util/ArrayList;
/*      */       //   163	982	10	inQuotes	Z
/*      */       //   166	979	11	quoteChar	C
/*      */       //   169	976	12	inQuotedId	Z
/*      */       //   172	973	13	lastParmEnd	I
/*      */       //   194	951	14	i	I
/*      */       //   180	965	15	noBackslashEscapes	Z
/*      */       //   1178	11	8	sqlEx	Ljava/sql/SQLException;
/*      */       //   1150	39	7	oobEx	Ljava/lang/StringIndexOutOfBoundsException;
/*      */       //   0	1265	0	this	Lcom/mysql/jdbc/PreparedStatement$ParseInfo;
/*      */       //   0	1265	1	sql	Ljava/lang/String;
/*      */       //   0	1265	2	conn	Lcom/mysql/jdbc/MySQLConnection;
/*      */       //   0	1265	3	dbmd	Ljava/sql/DatabaseMetaData;
/*      */       //   0	1265	4	encoding	Ljava/lang/String;
/*      */       //   0	1265	5	converter	Lcom/mysql/jdbc/SingleByteCharsetConverter;
/*      */       //   0	1265	6	buildRewriteInfo	Z
/*      */       // Local variable type table:
/*      */       //   start	length	slot	name	signature
/*      */       //   160	985	9	endpointList	Ljava/util/ArrayList<[I>;
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   67	1145	1148	java/lang/StringIndexOutOfBoundsException
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void buildRewriteBatchedParams(String sql, MySQLConnection conn, DatabaseMetaData metadata, String encoding, SingleByteCharsetConverter converter) throws SQLException {
/*  388 */       this.valuesClause = extractValuesClause(sql, conn.getMetaData().getIdentifierQuoteString());
/*  389 */       String odkuClause = this.isOnDuplicateKeyUpdate ? sql.substring(this.locationOfOnDuplicateKeyUpdate) : null;
/*      */       
/*  391 */       String headSql = null;
/*      */       
/*  393 */       if (this.isOnDuplicateKeyUpdate) {
/*  394 */         headSql = sql.substring(0, this.locationOfOnDuplicateKeyUpdate);
/*      */       } else {
/*  396 */         headSql = sql;
/*      */       } 
/*      */       
/*  399 */       this.batchHead = new ParseInfo(headSql, conn, metadata, encoding, converter, false);
/*  400 */       this.batchValues = new ParseInfo("," + this.valuesClause, conn, metadata, encoding, converter, false);
/*  401 */       this.batchODKUClause = null;
/*      */       
/*  403 */       if (odkuClause != null && odkuClause.length() > 0) {
/*  404 */         this.batchODKUClause = new ParseInfo("," + this.valuesClause + " " + odkuClause, conn, metadata, encoding, converter, false);
/*      */       }
/*      */     }
/*      */     
/*      */     private String extractValuesClause(String sql, String quoteCharStr) throws SQLException {
/*  409 */       int indexOfValues = -1;
/*  410 */       int valuesSearchStart = this.statementStartPos;
/*      */ 
/*      */       
/*  413 */       while (indexOfValues == -1) {
/*  414 */         if (quoteCharStr.length() > 0) {
/*  415 */           indexOfValues = StringUtils.indexOfIgnoreCase(valuesSearchStart, sql, "VALUE", quoteCharStr, quoteCharStr, StringUtils.SEARCH_MODE__MRK_COM_WS);
/*      */         } else {
/*      */           
/*  418 */           indexOfValues = StringUtils.indexOfIgnoreCase(valuesSearchStart, sql, "VALUE");
/*      */         } 
/*      */         
/*  421 */         if (indexOfValues > 0) {
/*      */           
/*  423 */           char c = sql.charAt(indexOfValues - 1);
/*  424 */           if (!Character.isWhitespace(c) && c != ')' && c != '`') {
/*  425 */             valuesSearchStart = indexOfValues + 6;
/*  426 */             indexOfValues = -1;
/*      */             continue;
/*      */           } 
/*  429 */           c = sql.charAt(indexOfValues + 6);
/*  430 */           if (!Character.isWhitespace(c) && c != '(') {
/*  431 */             valuesSearchStart = indexOfValues + 6;
/*  432 */             indexOfValues = -1;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  440 */       if (indexOfValues == -1) {
/*  441 */         return null;
/*      */       }
/*      */ 
/*      */       
/*  445 */       int valLength = (sql.length() > indexOfValues + 5 && Character.toUpperCase(sql.charAt(indexOfValues + 5)) == 'S') ? 6 : 5;
/*  446 */       int indexOfFirstParen = sql.indexOf('(', indexOfValues + valLength);
/*      */       
/*  448 */       if (indexOfFirstParen == -1) {
/*  449 */         return null;
/*      */       }
/*      */       
/*  452 */       int endOfValuesClause = this.isOnDuplicateKeyUpdate ? this.locationOfOnDuplicateKeyUpdate : sql.length();
/*      */       
/*  454 */       return sql.substring(indexOfFirstParen, endOfValuesClause);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     synchronized ParseInfo getParseInfoForBatch(int numBatch) {
/*  461 */       PreparedStatement.AppendingBatchVisitor apv = new PreparedStatement.AppendingBatchVisitor();
/*  462 */       buildInfoForBatch(numBatch, apv);
/*      */       
/*  464 */       ParseInfo batchParseInfo = new ParseInfo(apv.getStaticSqlStrings(), this.firstStmtChar, this.foundLoadData, this.isOnDuplicateKeyUpdate, this.locationOfOnDuplicateKeyUpdate, this.statementLength, this.statementStartPos);
/*      */ 
/*      */       
/*  467 */       return batchParseInfo;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String getSqlForBatch(int numBatch) throws UnsupportedEncodingException {
/*  476 */       ParseInfo batchInfo = getParseInfoForBatch(numBatch);
/*      */       
/*  478 */       return getSqlForBatch(batchInfo);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String getSqlForBatch(ParseInfo batchInfo) throws UnsupportedEncodingException {
/*  485 */       int size = 0;
/*  486 */       byte[][] sqlStrings = batchInfo.staticSql;
/*  487 */       int sqlStringsLength = sqlStrings.length;
/*      */       
/*  489 */       for (int i = 0; i < sqlStringsLength; i++) {
/*  490 */         size += (sqlStrings[i]).length;
/*  491 */         size++;
/*      */       } 
/*      */       
/*  494 */       StringBuilder buf = new StringBuilder(size);
/*      */       
/*  496 */       for (int j = 0; j < sqlStringsLength - 1; j++) {
/*  497 */         buf.append(StringUtils.toString(sqlStrings[j], this.charEncoding));
/*  498 */         buf.append("?");
/*      */       } 
/*      */       
/*  501 */       buf.append(StringUtils.toString(sqlStrings[sqlStringsLength - 1]));
/*      */       
/*  503 */       return buf.toString();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void buildInfoForBatch(int numBatch, PreparedStatement.BatchVisitor visitor) {
/*  515 */       if (!this.hasPlaceholders) {
/*  516 */         if (numBatch == 1) {
/*      */ 
/*      */           
/*  519 */           visitor.append(this.staticSql[0]);
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/*  526 */         byte[] arrayOfByte1 = this.batchHead.staticSql[0];
/*  527 */         visitor.append(arrayOfByte1).increment();
/*      */         
/*  529 */         int k = numBatch - 1;
/*  530 */         if (this.batchODKUClause != null) {
/*  531 */           k--;
/*      */         }
/*      */         
/*  534 */         byte[] arrayOfByte2 = this.batchValues.staticSql[0];
/*  535 */         for (int m = 0; m < k; m++) {
/*  536 */           visitor.mergeWithLast(arrayOfByte2).increment();
/*      */         }
/*      */         
/*  539 */         if (this.batchODKUClause != null) {
/*  540 */           byte[] batchOdkuStaticSql = this.batchODKUClause.staticSql[0];
/*  541 */           visitor.mergeWithLast(batchOdkuStaticSql).increment();
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*      */         return;
/*      */       } 
/*      */ 
/*      */       
/*  550 */       byte[][] headStaticSql = this.batchHead.staticSql;
/*  551 */       int headStaticSqlLength = headStaticSql.length;
/*  552 */       byte[] endOfHead = headStaticSql[headStaticSqlLength - 1];
/*      */       
/*  554 */       for (int i = 0; i < headStaticSqlLength - 1; i++) {
/*  555 */         visitor.append(headStaticSql[i]).increment();
/*      */       }
/*      */ 
/*      */       
/*  559 */       int numValueRepeats = numBatch - 1;
/*  560 */       if (this.batchODKUClause != null) {
/*  561 */         numValueRepeats--;
/*      */       }
/*      */       
/*  564 */       byte[][] valuesStaticSql = this.batchValues.staticSql;
/*  565 */       int valuesStaticSqlLength = valuesStaticSql.length;
/*  566 */       byte[] beginOfValues = valuesStaticSql[0];
/*  567 */       byte[] endOfValues = valuesStaticSql[valuesStaticSqlLength - 1];
/*      */       
/*  569 */       for (int j = 0; j < numValueRepeats; j++) {
/*  570 */         visitor.merge(endOfValues, beginOfValues).increment();
/*  571 */         for (int k = 1; k < valuesStaticSqlLength - 1; k++) {
/*  572 */           visitor.append(valuesStaticSql[k]).increment();
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  577 */       if (this.batchODKUClause != null) {
/*  578 */         byte[][] batchOdkuStaticSql = this.batchODKUClause.staticSql;
/*  579 */         int batchOdkuStaticSqlLength = batchOdkuStaticSql.length;
/*  580 */         byte[] beginOfOdku = batchOdkuStaticSql[0];
/*  581 */         byte[] endOfOdku = batchOdkuStaticSql[batchOdkuStaticSqlLength - 1];
/*      */         
/*  583 */         if (numBatch > 1) {
/*  584 */           visitor.merge((numValueRepeats > 0) ? endOfValues : endOfHead, beginOfOdku).increment();
/*  585 */           for (int k = 1; k < batchOdkuStaticSqlLength; k++) {
/*  586 */             visitor.append(batchOdkuStaticSql[k]).increment();
/*      */           }
/*      */         } else {
/*  589 */           visitor.append(endOfOdku).increment();
/*      */         } 
/*      */       } else {
/*  592 */         visitor.append(endOfHead);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private ParseInfo(byte[][] staticSql, char firstStmtChar, boolean foundLoadData, boolean isOnDuplicateKeyUpdate, int locationOfOnDuplicateKeyUpdate, int statementLength, int statementStartPos) {
/*  598 */       this.firstStmtChar = firstStmtChar;
/*  599 */       this.foundLoadData = foundLoadData;
/*  600 */       this.isOnDuplicateKeyUpdate = isOnDuplicateKeyUpdate;
/*  601 */       this.locationOfOnDuplicateKeyUpdate = locationOfOnDuplicateKeyUpdate;
/*  602 */       this.statementLength = statementLength;
/*  603 */       this.statementStartPos = statementStartPos;
/*  604 */       this.staticSql = staticSql;
/*      */     }
/*      */   }
/*      */   
/*      */   static interface BatchVisitor {
/*      */     BatchVisitor increment();
/*      */     
/*      */     BatchVisitor decrement();
/*      */     
/*      */     BatchVisitor append(byte[] param1ArrayOfbyte);
/*      */     
/*      */     BatchVisitor merge(byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2);
/*      */     
/*      */     BatchVisitor mergeWithLast(byte[] param1ArrayOfbyte);
/*      */   }
/*      */   
/*      */   static class AppendingBatchVisitor implements BatchVisitor {
/*  621 */     LinkedList<byte[]> statementComponents = (LinkedList)new LinkedList<byte>();
/*      */     
/*      */     public PreparedStatement.BatchVisitor append(byte[] values) {
/*  624 */       this.statementComponents.addLast(values);
/*      */       
/*  626 */       return this;
/*      */     }
/*      */ 
/*      */     
/*      */     public PreparedStatement.BatchVisitor increment() {
/*  631 */       return this;
/*      */     }
/*      */     
/*      */     public PreparedStatement.BatchVisitor decrement() {
/*  635 */       this.statementComponents.removeLast();
/*      */       
/*  637 */       return this;
/*      */     }
/*      */     
/*      */     public PreparedStatement.BatchVisitor merge(byte[] front, byte[] back) {
/*  641 */       int mergedLength = front.length + back.length;
/*  642 */       byte[] merged = new byte[mergedLength];
/*  643 */       System.arraycopy(front, 0, merged, 0, front.length);
/*  644 */       System.arraycopy(back, 0, merged, front.length, back.length);
/*  645 */       this.statementComponents.addLast(merged);
/*  646 */       return this;
/*      */     }
/*      */     
/*      */     public PreparedStatement.BatchVisitor mergeWithLast(byte[] values) {
/*  650 */       if (this.statementComponents.isEmpty()) {
/*  651 */         return append(values);
/*      */       }
/*  653 */       return merge(this.statementComponents.removeLast(), values);
/*      */     }
/*      */     
/*      */     public byte[][] getStaticSqlStrings() {
/*  657 */       byte[][] asBytes = new byte[this.statementComponents.size()][];
/*  658 */       this.statementComponents.toArray(asBytes);
/*      */       
/*  660 */       return asBytes;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/*  665 */       StringBuilder sb = new StringBuilder();
/*  666 */       for (byte[] comp : this.statementComponents) {
/*  667 */         sb.append(StringUtils.toString(comp));
/*      */       }
/*  669 */       return sb.toString();
/*      */     }
/*      */   }
/*      */   
/*  673 */   private static final byte[] HEX_DIGITS = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static int readFully(Reader reader, char[] buf, int length) throws IOException {
/*  687 */     int numCharsRead = 0;
/*      */     
/*  689 */     while (numCharsRead < length) {
/*  690 */       int count = reader.read(buf, numCharsRead, length - numCharsRead);
/*      */       
/*  692 */       if (count < 0) {
/*      */         break;
/*      */       }
/*      */       
/*  696 */       numCharsRead += count;
/*      */     } 
/*      */     
/*  699 */     return numCharsRead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean batchHasPlainStatements = false;
/*      */ 
/*      */ 
/*      */   
/*  710 */   private DatabaseMetaData dbmd = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  716 */   protected char firstCharOfStmt = Character.MIN_VALUE;
/*      */ 
/*      */   
/*      */   protected boolean isLoadDataQuery = false;
/*      */   
/*  721 */   protected boolean[] isNull = null;
/*      */   
/*  723 */   private boolean[] isStream = null;
/*      */   
/*  725 */   protected int numberOfExecutions = 0;
/*      */ 
/*      */   
/*  728 */   protected String originalSql = null;
/*      */ 
/*      */   
/*      */   protected int parameterCount;
/*      */   
/*      */   protected MysqlParameterMetadata parameterMetaData;
/*      */   
/*  735 */   private InputStream[] parameterStreams = null;
/*      */   
/*  737 */   private byte[][] parameterValues = (byte[][])null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  743 */   protected int[] parameterTypes = null;
/*      */   
/*      */   protected ParseInfo parseInfo;
/*      */   
/*      */   private ResultSetMetaData pstmtResultMetaData;
/*      */   
/*  749 */   private byte[][] staticSqlStrings = (byte[][])null;
/*      */   
/*  751 */   private byte[] streamConvertBuf = null;
/*      */   
/*  753 */   private int[] streamLengths = null;
/*      */   
/*  755 */   private SimpleDateFormat tsdf = null;
/*      */ 
/*      */   
/*      */   private SimpleDateFormat ddf;
/*      */ 
/*      */   
/*      */   private SimpleDateFormat tdf;
/*      */ 
/*      */   
/*      */   protected boolean useTrueBoolean = false;
/*      */ 
/*      */   
/*      */   protected boolean usingAnsiMode;
/*      */ 
/*      */   
/*      */   protected String batchedValuesClause;
/*      */   
/*      */   private boolean doPingInstead;
/*      */   
/*      */   private boolean compensateForOnDuplicateKeyUpdate = false;
/*      */   
/*      */   private CharsetEncoder charsetEncoder;
/*      */   
/*  778 */   protected int batchCommandIndex = -1;
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean serverSupportsFracSecs;
/*      */ 
/*      */   
/*      */   protected int rewrittenBatchSize;
/*      */ 
/*      */ 
/*      */   
/*      */   protected static PreparedStatement getInstance(MySQLConnection conn, String catalog) throws SQLException {
/*  790 */     if (!Util.isJdbc4()) {
/*  791 */       return new PreparedStatement(conn, catalog);
/*      */     }
/*      */     
/*  794 */     return (PreparedStatement)Util.handleNewInstance(JDBC_4_PSTMT_2_ARG_CTOR, new Object[] { conn, catalog }, conn.getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static PreparedStatement getInstance(MySQLConnection conn, String sql, String catalog) throws SQLException {
/*  805 */     if (!Util.isJdbc4()) {
/*  806 */       return new PreparedStatement(conn, sql, catalog);
/*      */     }
/*      */     
/*  809 */     return (PreparedStatement)Util.handleNewInstance(JDBC_4_PSTMT_3_ARG_CTOR, new Object[] { conn, sql, catalog }, conn.getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static PreparedStatement getInstance(MySQLConnection conn, String sql, String catalog, ParseInfo cachedParseInfo) throws SQLException {
/*  820 */     if (!Util.isJdbc4()) {
/*  821 */       return new PreparedStatement(conn, sql, catalog, cachedParseInfo);
/*      */     }
/*      */     
/*  824 */     return (PreparedStatement)Util.handleNewInstance(JDBC_4_PSTMT_4_ARG_CTOR, new Object[] { conn, sql, catalog, cachedParseInfo }, conn.getExceptionInterceptor());
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
/*      */   public PreparedStatement(MySQLConnection conn, String catalog) throws SQLException
/*      */   {
/*  840 */     super(conn, catalog);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2293 */     this.rewrittenBatchSize = 0; detectFractionalSecondsSupport(); this.compensateForOnDuplicateKeyUpdate = this.connection.getCompensateOnDuplicateKeyUpdateCounts(); } protected void detectFractionalSecondsSupport() throws SQLException { this.serverSupportsFracSecs = (this.connection != null && this.connection.versionMeetsMinimum(5, 6, 4)); } public PreparedStatement(MySQLConnection conn, String sql, String catalog) throws SQLException { super(conn, catalog); this.rewrittenBatchSize = 0; if (sql == null) throw SQLError.createSQLException(Messages.getString("PreparedStatement.0"), "S1009", getExceptionInterceptor());  detectFractionalSecondsSupport(); this.originalSql = sql; this.doPingInstead = this.originalSql.startsWith("/* ping */"); this.dbmd = this.connection.getMetaData(); this.useTrueBoolean = this.connection.versionMeetsMinimum(3, 21, 23); this.parseInfo = new ParseInfo(sql, this.connection, this.dbmd, this.charEncoding, this.charConverter); initializeFromParseInfo(); this.compensateForOnDuplicateKeyUpdate = this.connection.getCompensateOnDuplicateKeyUpdateCounts(); if (conn.getRequiresEscapingEncoder()) this.charsetEncoder = Charset.forName(conn.getEncoding()).newEncoder();  } public PreparedStatement(MySQLConnection conn, String sql, String catalog, ParseInfo cachedParseInfo) throws SQLException { super(conn, catalog); this.rewrittenBatchSize = 0; if (sql == null) throw SQLError.createSQLException(Messages.getString("PreparedStatement.1"), "S1009", getExceptionInterceptor());  detectFractionalSecondsSupport(); this.originalSql = sql; this.dbmd = this.connection.getMetaData(); this.useTrueBoolean = this.connection.versionMeetsMinimum(3, 21, 23); this.parseInfo = cachedParseInfo; this.usingAnsiMode = !this.connection.useAnsiQuotedIdentifiers(); initializeFromParseInfo(); this.compensateForOnDuplicateKeyUpdate = this.connection.getCompensateOnDuplicateKeyUpdateCounts(); if (conn.getRequiresEscapingEncoder()) this.charsetEncoder = Charset.forName(conn.getEncoding()).newEncoder();  }
/*      */   public void addBatch() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.batchedArgs == null) this.batchedArgs = new ArrayList();  for (int i = 0; i < this.parameterValues.length; i++) checkAllParametersSet(this.parameterValues[i], this.parameterStreams[i], i);  this.batchedArgs.add(new BatchParams(this.parameterValues, this.parameterStreams, this.isStream, this.streamLengths, this.isNull)); }  }
/*      */   public void addBatch(String sql) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { this.batchHasPlainStatements = true; super.addBatch(sql); }  }
/* 2296 */   public String asSql() throws SQLException { return asSql(false); } public String asSql(boolean quoteStreamsAndUnknowns) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { StringBuilder buf = new StringBuilder(); try { int realParameterCount = this.parameterCount + getParameterIndexOffset(); Object batchArg = null; if (this.batchCommandIndex != -1) batchArg = this.batchedArgs.get(this.batchCommandIndex);  for (int i = 0; i < realParameterCount; i++) { if (this.charEncoding != null) { buf.append(StringUtils.toString(this.staticSqlStrings[i], this.charEncoding)); } else { buf.append(StringUtils.toString(this.staticSqlStrings[i])); }  byte[] val = null; if (batchArg != null && batchArg instanceof String) { buf.append((String)batchArg); } else { if (this.batchCommandIndex == -1) { val = this.parameterValues[i]; } else { val = ((BatchParams)batchArg).parameterStrings[i]; }  boolean isStreamParam = false; if (this.batchCommandIndex == -1) { isStreamParam = this.isStream[i]; } else { isStreamParam = ((BatchParams)batchArg).isStream[i]; }  if (val == null && !isStreamParam) { if (quoteStreamsAndUnknowns) buf.append("'");  buf.append("** NOT SPECIFIED **"); if (quoteStreamsAndUnknowns) buf.append("'");  } else if (isStreamParam) { if (quoteStreamsAndUnknowns) buf.append("'");  buf.append("** STREAM DATA **"); if (quoteStreamsAndUnknowns) buf.append("'");  } else if (this.charConverter != null) { buf.append(this.charConverter.toString(val)); } else if (this.charEncoding != null) { buf.append(new String(val, this.charEncoding)); } else { buf.append(StringUtils.toAsciiString(val)); }  }  }  if (this.charEncoding != null) { buf.append(StringUtils.toString(this.staticSqlStrings[this.parameterCount + getParameterIndexOffset()], this.charEncoding)); } else { buf.append(StringUtils.toAsciiString(this.staticSqlStrings[this.parameterCount + getParameterIndexOffset()])); }  } catch (UnsupportedEncodingException uue) { throw new RuntimeException(Messages.getString("PreparedStatement.32") + this.charEncoding + Messages.getString("PreparedStatement.33")); }  return buf.toString(); }  } public void clearBatch() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { this.batchHasPlainStatements = false; super.clearBatch(); }  } public void clearParameters() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { for (int i = 0; i < this.parameterValues.length; i++) { this.parameterValues[i] = null; this.parameterStreams[i] = null; this.isStream[i] = false; this.isNull[i] = false; this.parameterTypes[i] = 0; }  }  } private final void escapeblockFast(byte[] buf, Buffer packet, int size) throws SQLException { int lastwritten = 0; for (int i = 0; i < size; i++) { byte b = buf[i]; if (b == 0) { if (i > lastwritten) packet.writeBytesNoNull(buf, lastwritten, i - lastwritten);  packet.writeByte((byte)92); packet.writeByte((byte)48); lastwritten = i + 1; } else if (b == 92 || b == 39 || (!this.usingAnsiMode && b == 34)) { if (i > lastwritten) packet.writeBytesNoNull(buf, lastwritten, i - lastwritten);  packet.writeByte((byte)92); lastwritten = i; }  }  if (lastwritten < size) packet.writeBytesNoNull(buf, lastwritten, size - lastwritten);  } private final void escapeblockFast(byte[] buf, ByteArrayOutputStream bytesOut, int size) { int lastwritten = 0; for (int i = 0; i < size; i++) { byte b = buf[i]; if (b == 0) { if (i > lastwritten) bytesOut.write(buf, lastwritten, i - lastwritten);  bytesOut.write(92); bytesOut.write(48); lastwritten = i + 1; } else if (b == 39) { if (i > lastwritten) bytesOut.write(buf, lastwritten, i - lastwritten);  bytesOut.write(this.connection.isNoBackslashEscapesSet() ? 39 : 92); lastwritten = i; } else if (b == 92 || (!this.usingAnsiMode && b == 34)) { if (i > lastwritten) bytesOut.write(buf, lastwritten, i - lastwritten);  bytesOut.write(92); lastwritten = i; }  }  if (lastwritten < size) bytesOut.write(buf, lastwritten, size - lastwritten);  } protected boolean checkReadOnlySafeStatement() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { return (this.firstCharOfStmt == 'S' || !this.connection.isReadOnly()); }  } public boolean execute() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MySQLConnection locallyScopedConn = this.connection; if (!this.doPingInstead && !checkReadOnlySafeStatement()) throw SQLError.createSQLException(Messages.getString("PreparedStatement.20") + Messages.getString("PreparedStatement.21"), "S1009", getExceptionInterceptor());  ResultSetInternalMethods rs = null; this.lastQueryIsOnDupKeyUpdate = false; if (this.retrieveGeneratedKeys) this.lastQueryIsOnDupKeyUpdate = containsOnDuplicateKeyUpdateInSQL();  this.batchedGeneratedKeys = null; resetCancelledState(); implicitlyCloseAllOpenResults(); clearWarnings(); if (this.doPingInstead) { doPingInstead(); return true; }  setupStreamingTimeout(locallyScopedConn); Buffer sendPacket = fillSendPacket(); String oldCatalog = null; if (!locallyScopedConn.getCatalog().equals(this.currentCatalog)) { oldCatalog = locallyScopedConn.getCatalog(); locallyScopedConn.setCatalog(this.currentCatalog); }  CachedResultSetMetaData cachedMetadata = null; if (locallyScopedConn.getCacheResultSetMetadata()) cachedMetadata = locallyScopedConn.getCachedMetaData(this.originalSql);  Field[] metadataFromCache = null; if (cachedMetadata != null) metadataFromCache = cachedMetadata.fields;  boolean oldInfoMsgState = false; if (this.retrieveGeneratedKeys) { oldInfoMsgState = locallyScopedConn.isReadInfoMsgEnabled(); locallyScopedConn.setReadInfoMsgEnabled(true); }  locallyScopedConn.setSessionMaxRows((this.firstCharOfStmt == 'S') ? this.maxRows : -1); rs = executeInternal(this.maxRows, sendPacket, createStreamingResultSet(), (this.firstCharOfStmt == 'S'), metadataFromCache, false); if (cachedMetadata != null) { locallyScopedConn.initializeResultsMetadataFromCache(this.originalSql, cachedMetadata, rs); } else if (rs.reallyResult() && locallyScopedConn.getCacheResultSetMetadata()) { locallyScopedConn.initializeResultsMetadataFromCache(this.originalSql, (CachedResultSetMetaData)null, rs); }  if (this.retrieveGeneratedKeys) { locallyScopedConn.setReadInfoMsgEnabled(oldInfoMsgState); rs.setFirstCharOfQuery(this.firstCharOfStmt); }  if (oldCatalog != null) locallyScopedConn.setCatalog(oldCatalog);  if (rs != null) { this.lastInsertId = rs.getUpdateID(); this.results = rs; }  return (rs != null && rs.reallyResult()); }  } protected long[] executeBatchInternal() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.connection.isReadOnly()) throw new SQLException(Messages.getString("PreparedStatement.25") + Messages.getString("PreparedStatement.26"), "S1009");  if (this.batchedArgs == null || this.batchedArgs.size() == 0) return new long[0];  int batchTimeout = this.timeoutInMillis; this.timeoutInMillis = 0; resetCancelledState(); try { statementBegins(); clearWarnings(); if (!this.batchHasPlainStatements && this.connection.getRewriteBatchedStatements()) { if (canRewriteAsMultiValueInsertAtSqlLevel()) return executeBatchedInserts(batchTimeout);  if (this.connection.versionMeetsMinimum(4, 1, 0) && !this.batchHasPlainStatements && this.batchedArgs != null && this.batchedArgs.size() > 3) return executePreparedBatchAsMultiStatement(batchTimeout);  }  return executeBatchSerially(batchTimeout); } finally { this.statementExecuting.set(false); clearBatch(); }  }  } public boolean canRewriteAsMultiValueInsertAtSqlLevel() throws SQLException { return this.parseInfo.canRewriteAsMultiValueInsert; } protected int getLocationOfOnDuplicateKeyUpdate() throws SQLException { return this.parseInfo.locationOfOnDuplicateKeyUpdate; } protected long[] executePreparedBatchAsMultiStatement(int batchTimeout) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (this.batchedValuesClause == null) this.batchedValuesClause = this.originalSql + ";";  MySQLConnection locallyScopedConn = this.connection; boolean multiQueriesEnabled = locallyScopedConn.getAllowMultiQueries(); StatementImpl.CancelTask timeoutTask = null; try { clearWarnings(); int numBatchedArgs = this.batchedArgs.size(); if (this.retrieveGeneratedKeys) this.batchedGeneratedKeys = new ArrayList<ResultSetRow>(numBatchedArgs);  int numValuesPerBatch = computeBatchSize(numBatchedArgs); if (numBatchedArgs < numValuesPerBatch) numValuesPerBatch = numBatchedArgs;  PreparedStatement batchedStatement = null; int batchedParamIndex = 1; int numberToExecuteAsMultiValue = 0; int batchCounter = 0; int updateCountCounter = 0; long[] updateCounts = new long[numBatchedArgs * this.parseInfo.numberOfQueries]; SQLException sqlEx = null; try { if (!multiQueriesEnabled) locallyScopedConn.getIO().enableMultiQueries();  if (this.retrieveGeneratedKeys) { batchedStatement = ((Wrapper)locallyScopedConn.prepareStatement(generateMultiStatementForBatch(numValuesPerBatch), 1)).<PreparedStatement>unwrap(PreparedStatement.class); } else { batchedStatement = ((Wrapper)locallyScopedConn.prepareStatement(generateMultiStatementForBatch(numValuesPerBatch))).<PreparedStatement>unwrap(PreparedStatement.class); }  if (locallyScopedConn.getEnableQueryTimeouts() && batchTimeout != 0 && locallyScopedConn.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new StatementImpl.CancelTask(this, (StatementImpl)batchedStatement); locallyScopedConn.getCancelTimer().schedule(timeoutTask, batchTimeout); }  if (numBatchedArgs < numValuesPerBatch) { numberToExecuteAsMultiValue = numBatchedArgs; } else { numberToExecuteAsMultiValue = numBatchedArgs / numValuesPerBatch; }  int numberArgsToExecute = numberToExecuteAsMultiValue * numValuesPerBatch; for (int i = 0; i < numberArgsToExecute; i++) { if (i != 0 && i % numValuesPerBatch == 0) { try { batchedStatement.execute(); } catch (SQLException ex) { sqlEx = handleExceptionForBatch(batchCounter, numValuesPerBatch, updateCounts, ex); }  updateCountCounter = processMultiCountsAndKeys((StatementImpl)batchedStatement, updateCountCounter, updateCounts); batchedStatement.clearParameters(); batchedParamIndex = 1; }  batchedParamIndex = setOneBatchedParameterSet(batchedStatement, batchedParamIndex, this.batchedArgs.get(batchCounter++)); }  try { batchedStatement.execute(); } catch (SQLException ex) { sqlEx = handleExceptionForBatch(batchCounter - 1, numValuesPerBatch, updateCounts, ex); }  updateCountCounter = processMultiCountsAndKeys((StatementImpl)batchedStatement, updateCountCounter, updateCounts); batchedStatement.clearParameters(); numValuesPerBatch = numBatchedArgs - batchCounter; } finally { if (batchedStatement != null) { batchedStatement.close(); batchedStatement = null; }  }  try { if (numValuesPerBatch > 0) { if (this.retrieveGeneratedKeys) { batchedStatement = locallyScopedConn.prepareStatement(generateMultiStatementForBatch(numValuesPerBatch), 1); } else { batchedStatement = locallyScopedConn.prepareStatement(generateMultiStatementForBatch(numValuesPerBatch)); }  if (timeoutTask != null) timeoutTask.toCancel = (StatementImpl)batchedStatement;  batchedParamIndex = 1; while (batchCounter < numBatchedArgs) batchedParamIndex = setOneBatchedParameterSet(batchedStatement, batchedParamIndex, this.batchedArgs.get(batchCounter++));  try { batchedStatement.execute(); } catch (SQLException ex) { sqlEx = handleExceptionForBatch(batchCounter - 1, numValuesPerBatch, updateCounts, ex); }  updateCountCounter = processMultiCountsAndKeys((StatementImpl)batchedStatement, updateCountCounter, updateCounts); batchedStatement.clearParameters(); }  if (timeoutTask != null) { if (timeoutTask.caughtWhileCancelling != null) throw timeoutTask.caughtWhileCancelling;  timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); timeoutTask = null; }  if (sqlEx != null) throw SQLError.createBatchUpdateException(sqlEx, updateCounts, getExceptionInterceptor());  return updateCounts; } finally { if (batchedStatement != null) batchedStatement.close();  }  } finally { if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); }  resetCancelledState(); if (!multiQueriesEnabled) locallyScopedConn.getIO().disableMultiQueries();  clearBatch(); }  }  } private String generateMultiStatementForBatch(int numBatches) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { StringBuilder newStatementSql = new StringBuilder((this.originalSql.length() + 1) * numBatches); newStatementSql.append(this.originalSql); for (int i = 0; i < numBatches - 1; i++) { newStatementSql.append(';'); newStatementSql.append(this.originalSql); }  return newStatementSql.toString(); }  } protected long[] executeBatchedInserts(int batchTimeout) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { String valuesClause = getValuesClause(); MySQLConnection locallyScopedConn = this.connection; if (valuesClause == null) return executeBatchSerially(batchTimeout);  int numBatchedArgs = this.batchedArgs.size(); if (this.retrieveGeneratedKeys) this.batchedGeneratedKeys = new ArrayList<ResultSetRow>(numBatchedArgs);  int numValuesPerBatch = computeBatchSize(numBatchedArgs); if (numBatchedArgs < numValuesPerBatch) numValuesPerBatch = numBatchedArgs;  PreparedStatement batchedStatement = null; int batchedParamIndex = 1; long updateCountRunningTotal = 0L; int numberToExecuteAsMultiValue = 0; int batchCounter = 0; StatementImpl.CancelTask timeoutTask = null; SQLException sqlEx = null; long[] updateCounts = new long[numBatchedArgs]; try { try { batchedStatement = prepareBatchedInsertSQL(locallyScopedConn, numValuesPerBatch); if (locallyScopedConn.getEnableQueryTimeouts() && batchTimeout != 0 && locallyScopedConn.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new StatementImpl.CancelTask(this, batchedStatement); locallyScopedConn.getCancelTimer().schedule(timeoutTask, batchTimeout); }  if (numBatchedArgs < numValuesPerBatch) { numberToExecuteAsMultiValue = numBatchedArgs; } else { numberToExecuteAsMultiValue = numBatchedArgs / numValuesPerBatch; }  int numberArgsToExecute = numberToExecuteAsMultiValue * numValuesPerBatch; for (int i = 0; i < numberArgsToExecute; i++) { if (i != 0 && i % numValuesPerBatch == 0) { try { updateCountRunningTotal += batchedStatement.executeLargeUpdate(); } catch (SQLException ex) { sqlEx = handleExceptionForBatch(batchCounter - 1, numValuesPerBatch, updateCounts, ex); }  getBatchedGeneratedKeys(batchedStatement); batchedStatement.clearParameters(); batchedParamIndex = 1; }  batchedParamIndex = setOneBatchedParameterSet(batchedStatement, batchedParamIndex, this.batchedArgs.get(batchCounter++)); }  try { updateCountRunningTotal += batchedStatement.executeLargeUpdate(); } catch (SQLException ex) { sqlEx = handleExceptionForBatch(batchCounter - 1, numValuesPerBatch, updateCounts, ex); }  getBatchedGeneratedKeys(batchedStatement); numValuesPerBatch = numBatchedArgs - batchCounter; } finally { if (batchedStatement != null) { batchedStatement.close(); batchedStatement = null; }  }  try { if (numValuesPerBatch > 0) { batchedStatement = prepareBatchedInsertSQL(locallyScopedConn, numValuesPerBatch); if (timeoutTask != null) timeoutTask.toCancel = batchedStatement;  batchedParamIndex = 1; while (batchCounter < numBatchedArgs) batchedParamIndex = setOneBatchedParameterSet(batchedStatement, batchedParamIndex, this.batchedArgs.get(batchCounter++));  try { updateCountRunningTotal += batchedStatement.executeLargeUpdate(); } catch (SQLException ex) { sqlEx = handleExceptionForBatch(batchCounter - 1, numValuesPerBatch, updateCounts, ex); }  getBatchedGeneratedKeys(batchedStatement); }  if (sqlEx != null) throw SQLError.createBatchUpdateException(sqlEx, updateCounts, getExceptionInterceptor());  if (numBatchedArgs > 1) { long updCount = (updateCountRunningTotal > 0L) ? -2L : 0L; for (int j = 0; j < numBatchedArgs; j++) updateCounts[j] = updCount;  } else { updateCounts[0] = updateCountRunningTotal; }  return updateCounts; } finally { if (batchedStatement != null) batchedStatement.close();  }  } finally { if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); }  resetCancelledState(); }  }  } public int getRewrittenBatchSize() { return this.rewrittenBatchSize; }
/*      */   protected String getValuesClause() throws SQLException { return this.parseInfo.valuesClause; }
/*      */   protected int computeBatchSize(int numBatchedArgs) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { long[] combinedValues = computeMaxParameterSetSizeAndBatchSize(numBatchedArgs); long maxSizeOfParameterSet = combinedValues[0]; long sizeOfEntireBatch = combinedValues[1]; int maxAllowedPacket = this.connection.getMaxAllowedPacket(); if (sizeOfEntireBatch < (maxAllowedPacket - this.originalSql.length())) return numBatchedArgs;  return (int)Math.max(1L, (maxAllowedPacket - this.originalSql.length()) / maxSizeOfParameterSet); }  }
/*      */   protected long[] computeMaxParameterSetSizeAndBatchSize(int numBatchedArgs) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { long sizeOfEntireBatch = 0L; long maxSizeOfParameterSet = 0L; for (int i = 0; i < numBatchedArgs; i++) { BatchParams paramArg = (BatchParams)this.batchedArgs.get(i); boolean[] isNullBatch = paramArg.isNull; boolean[] isStreamBatch = paramArg.isStream; long sizeOfParameterSet = 0L; for (int j = 0; j < isNullBatch.length; j++) { if (!isNullBatch[j]) { if (isStreamBatch[j]) { int streamLength = paramArg.streamLengths[j]; if (streamLength != -1) { sizeOfParameterSet += (streamLength * 2); } else { int paramLength = (paramArg.parameterStrings[j]).length; sizeOfParameterSet += paramLength; }  } else { sizeOfParameterSet += (paramArg.parameterStrings[j]).length; }  } else { sizeOfParameterSet += 4L; }  }  if (getValuesClause() != null) { sizeOfParameterSet += (getValuesClause().length() + 1); } else { sizeOfParameterSet += (this.originalSql.length() + 1); }  sizeOfEntireBatch += sizeOfParameterSet; if (sizeOfParameterSet > maxSizeOfParameterSet) maxSizeOfParameterSet = sizeOfParameterSet;  }  (new long[2])[0] = maxSizeOfParameterSet; (new long[2])[1] = sizeOfEntireBatch; return new long[2]; }  }
/* 2300 */   protected long[] executeBatchSerially(int batchTimeout) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MySQLConnection locallyScopedConn = this.connection; if (locallyScopedConn == null) checkClosed();  long[] updateCounts = null; if (this.batchedArgs != null) { int nbrCommands = this.batchedArgs.size(); updateCounts = new long[nbrCommands]; for (int i = 0; i < nbrCommands; i++) updateCounts[i] = -3L;  SQLException sqlEx = null; StatementImpl.CancelTask timeoutTask = null; try { if (locallyScopedConn.getEnableQueryTimeouts() && batchTimeout != 0 && locallyScopedConn.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new StatementImpl.CancelTask(this, this); locallyScopedConn.getCancelTimer().schedule(timeoutTask, batchTimeout); }  if (this.retrieveGeneratedKeys) this.batchedGeneratedKeys = new ArrayList<ResultSetRow>(nbrCommands);  for (this.batchCommandIndex = 0; this.batchCommandIndex < nbrCommands; this.batchCommandIndex++) { Object arg = this.batchedArgs.get(this.batchCommandIndex); try { if (arg instanceof String) { updateCounts[this.batchCommandIndex] = executeUpdateInternal((String)arg, true, this.retrieveGeneratedKeys); getBatchedGeneratedKeys((this.results.getFirstCharOfQuery() == 'I' && containsOnDuplicateKeyInString((String)arg)) ? 1 : 0); } else { BatchParams paramArg = (BatchParams)arg; updateCounts[this.batchCommandIndex] = executeUpdateInternal(paramArg.parameterStrings, paramArg.parameterStreams, paramArg.isStream, paramArg.streamLengths, paramArg.isNull, true); getBatchedGeneratedKeys(containsOnDuplicateKeyUpdateInSQL() ? 1 : 0); }  } catch (SQLException ex) { updateCounts[this.batchCommandIndex] = -3L; if (this.continueBatchOnError && !(ex instanceof MySQLTimeoutException) && !(ex instanceof MySQLStatementCancelledException) && !hasDeadlockOrTimeoutRolledBackTx(ex)) { sqlEx = ex; } else { long[] newUpdateCounts = new long[this.batchCommandIndex]; System.arraycopy(updateCounts, 0, newUpdateCounts, 0, this.batchCommandIndex); throw SQLError.createBatchUpdateException(ex, newUpdateCounts, getExceptionInterceptor()); }  }  }  if (sqlEx != null) throw SQLError.createBatchUpdateException(sqlEx, updateCounts, getExceptionInterceptor());  } catch (NullPointerException npe) { try { checkClosed(); } catch (SQLException connectionClosedEx) { updateCounts[this.batchCommandIndex] = -3L; long[] newUpdateCounts = new long[this.batchCommandIndex]; System.arraycopy(updateCounts, 0, newUpdateCounts, 0, this.batchCommandIndex); throw SQLError.createBatchUpdateException(connectionClosedEx, newUpdateCounts, getExceptionInterceptor()); }  throw npe; } finally { this.batchCommandIndex = -1; if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConn.getCancelTimer().purge(); }  resetCancelledState(); }  }  return (updateCounts != null) ? updateCounts : new long[0]; }  } public String getDateTime(String pattern) { SimpleDateFormat sdf = TimeUtil.getSimpleDateFormat(null, pattern, null, null); return sdf.format(new Date()); } protected ResultSetInternalMethods executeInternal(int maxRowsToRetrieve, Buffer sendPacket, boolean createStreamingResultSet, boolean queryIsSelectOnly, Field[] metadataFromCache, boolean isBatch) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { ResultSetInternalMethods rs; MySQLConnection locallyScopedConnection = this.connection; this.numberOfExecutions++; StatementImpl.CancelTask timeoutTask = null; try { if (locallyScopedConnection.getEnableQueryTimeouts() && this.timeoutInMillis != 0 && locallyScopedConnection.versionMeetsMinimum(5, 0, 0)) { timeoutTask = new StatementImpl.CancelTask(this, this); locallyScopedConnection.getCancelTimer().schedule(timeoutTask, this.timeoutInMillis); }  if (!isBatch) statementBegins();  rs = locallyScopedConnection.execSQL(this, (String)null, maxRowsToRetrieve, sendPacket, this.resultSetType, this.resultSetConcurrency, createStreamingResultSet, this.currentCatalog, metadataFromCache, isBatch); if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConnection.getCancelTimer().purge(); if (timeoutTask.caughtWhileCancelling != null) throw timeoutTask.caughtWhileCancelling;  timeoutTask = null; }  synchronized (this.cancelTimeoutMutex) { if (this.wasCancelled) { MySQLStatementCancelledException mySQLStatementCancelledException; SQLException cause = null; if (this.wasCancelledByTimeout) { MySQLTimeoutException mySQLTimeoutException = new MySQLTimeoutException(); } else { mySQLStatementCancelledException = new MySQLStatementCancelledException(); }  resetCancelledState(); throw mySQLStatementCancelledException; }  }  } finally { if (!isBatch) this.statementExecuting.set(false);  if (timeoutTask != null) { timeoutTask.cancel(); locallyScopedConnection.getCancelTimer().purge(); }  }  return rs; }  } public ResultSet executeQuery() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MySQLConnection locallyScopedConn = this.connection; checkForDml(this.originalSql, this.firstCharOfStmt); this.batchedGeneratedKeys = null; resetCancelledState(); implicitlyCloseAllOpenResults(); clearWarnings(); if (this.doPingInstead) { doPingInstead(); return this.results; }  setupStreamingTimeout(locallyScopedConn); Buffer sendPacket = fillSendPacket(); String oldCatalog = null; if (!locallyScopedConn.getCatalog().equals(this.currentCatalog)) { oldCatalog = locallyScopedConn.getCatalog(); locallyScopedConn.setCatalog(this.currentCatalog); }  CachedResultSetMetaData cachedMetadata = null; if (locallyScopedConn.getCacheResultSetMetadata()) cachedMetadata = locallyScopedConn.getCachedMetaData(this.originalSql);  Field[] metadataFromCache = null; if (cachedMetadata != null) metadataFromCache = cachedMetadata.fields;  locallyScopedConn.setSessionMaxRows(this.maxRows); this.results = executeInternal(this.maxRows, sendPacket, createStreamingResultSet(), true, metadataFromCache, false); if (oldCatalog != null) locallyScopedConn.setCatalog(oldCatalog);  if (cachedMetadata != null) { locallyScopedConn.initializeResultsMetadataFromCache(this.originalSql, cachedMetadata, this.results); } else if (locallyScopedConn.getCacheResultSetMetadata()) { locallyScopedConn.initializeResultsMetadataFromCache(this.originalSql, (CachedResultSetMetaData)null, this.results); }  this.lastInsertId = this.results.getUpdateID(); return this.results; }  } public int executeUpdate() throws SQLException { return Util.truncateAndConvertToInt(executeLargeUpdate()); } protected long executeUpdateInternal(boolean clearBatchedGeneratedKeysAndWarnings, boolean isBatch) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { if (clearBatchedGeneratedKeysAndWarnings) { clearWarnings(); this.batchedGeneratedKeys = null; }  return executeUpdateInternal(this.parameterValues, this.parameterStreams, this.isStream, this.streamLengths, this.isNull, isBatch); }  } protected long executeUpdateInternal(byte[][] batchedParameterStrings, InputStream[] batchedParameterStreams, boolean[] batchedIsStream, int[] batchedStreamLengths, boolean[] batchedIsNull, boolean isReallyBatch) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { MySQLConnection locallyScopedConn = this.connection; if (locallyScopedConn.isReadOnly(false)) throw SQLError.createSQLException(Messages.getString("PreparedStatement.34") + Messages.getString("PreparedStatement.35"), "S1009", getExceptionInterceptor());  if (this.firstCharOfStmt == 'S' && isSelectQuery()) throw SQLError.createSQLException(Messages.getString("PreparedStatement.37"), "01S03", getExceptionInterceptor());  resetCancelledState(); implicitlyCloseAllOpenResults(); ResultSetInternalMethods rs = null; Buffer sendPacket = fillSendPacket(batchedParameterStrings, batchedParameterStreams, batchedIsStream, batchedStreamLengths); String oldCatalog = null; if (!locallyScopedConn.getCatalog().equals(this.currentCatalog)) { oldCatalog = locallyScopedConn.getCatalog(); locallyScopedConn.setCatalog(this.currentCatalog); }  locallyScopedConn.setSessionMaxRows(-1); boolean oldInfoMsgState = false; if (this.retrieveGeneratedKeys) { oldInfoMsgState = locallyScopedConn.isReadInfoMsgEnabled(); locallyScopedConn.setReadInfoMsgEnabled(true); }  rs = executeInternal(-1, sendPacket, false, false, (Field[])null, isReallyBatch); if (this.retrieveGeneratedKeys) { locallyScopedConn.setReadInfoMsgEnabled(oldInfoMsgState); rs.setFirstCharOfQuery(this.firstCharOfStmt); }  if (oldCatalog != null) locallyScopedConn.setCatalog(oldCatalog);  this.results = rs; this.updateCount = rs.getUpdateCount(); if (containsOnDuplicateKeyUpdateInSQL() && this.compensateForOnDuplicateKeyUpdate && (this.updateCount == 2L || this.updateCount == 0L)) this.updateCount = 1L;  this.lastInsertId = rs.getUpdateID(); return this.updateCount; }  } protected boolean containsOnDuplicateKeyUpdateInSQL() { return this.parseInfo.isOnDuplicateKeyUpdate; } protected Buffer fillSendPacket() throws SQLException { synchronized (checkClosed().getConnectionMutex()) { return fillSendPacket(this.parameterValues, this.parameterStreams, this.isStream, this.streamLengths); }  } protected Buffer fillSendPacket(byte[][] batchedParameterStrings, InputStream[] batchedParameterStreams, boolean[] batchedIsStream, int[] batchedStreamLengths) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { Buffer sendPacket = this.connection.getIO().getSharedSendPacket(); sendPacket.clear(); sendPacket.writeByte((byte)3); boolean useStreamLengths = this.connection.getUseStreamLengthsInPrepStmts(); int ensurePacketSize = 0; String statementComment = this.connection.getStatementComment(); byte[] commentAsBytes = null; if (statementComment != null) { if (this.charConverter != null) { commentAsBytes = this.charConverter.toBytes(statementComment); } else { commentAsBytes = StringUtils.getBytes(statementComment, this.charConverter, this.charEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor()); }  ensurePacketSize += commentAsBytes.length; ensurePacketSize += 6; }  int i; for (i = 0; i < batchedParameterStrings.length; i++) { if (batchedIsStream[i] && useStreamLengths) ensurePacketSize += batchedStreamLengths[i];  }  if (ensurePacketSize != 0) sendPacket.ensureCapacity(ensurePacketSize);  if (commentAsBytes != null) { sendPacket.writeBytesNoNull(Constants.SLASH_STAR_SPACE_AS_BYTES); sendPacket.writeBytesNoNull(commentAsBytes); sendPacket.writeBytesNoNull(Constants.SPACE_STAR_SLASH_SPACE_AS_BYTES); }  for (i = 0; i < batchedParameterStrings.length; i++) { checkAllParametersSet(batchedParameterStrings[i], batchedParameterStreams[i], i); sendPacket.writeBytesNoNull(this.staticSqlStrings[i]); if (batchedIsStream[i]) { streamToBytes(sendPacket, batchedParameterStreams[i], true, batchedStreamLengths[i], useStreamLengths); } else { sendPacket.writeBytesNoNull(batchedParameterStrings[i]); }  }  sendPacket.writeBytesNoNull(this.staticSqlStrings[batchedParameterStrings.length]); return sendPacket; }  } private void checkAllParametersSet(byte[] parameterString, InputStream parameterStream, int columnIndex) throws SQLException { if (parameterString == null && parameterStream == null) throw SQLError.createSQLException(Messages.getString("PreparedStatement.40") + (columnIndex + 1), "07001", getExceptionInterceptor());  } protected PreparedStatement prepareBatchedInsertSQL(MySQLConnection localConn, int numBatches) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { PreparedStatement pstmt = new PreparedStatement(localConn, "Rewritten batch of: " + this.originalSql, this.currentCatalog, this.parseInfo.getParseInfoForBatch(numBatches)); pstmt.setRetrieveGeneratedKeys(this.retrieveGeneratedKeys); pstmt.rewrittenBatchSize = numBatches; return pstmt; }  } protected void setRetrieveGeneratedKeys(boolean flag) throws SQLException { synchronized (checkClosed().getConnectionMutex()) { this.retrieveGeneratedKeys = flag; }  } public String getNonRewrittenSql() throws SQLException { synchronized (checkClosed().getConnectionMutex()) {
/* 2301 */       int indexOfBatch = this.originalSql.indexOf(" of: ");
/*      */       
/* 2303 */       if (indexOfBatch != -1) {
/* 2304 */         return this.originalSql.substring(indexOfBatch + 5);
/*      */       }
/*      */       
/* 2307 */       return this.originalSql;
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] getBytesRepresentation(int parameterIndex) throws SQLException {
/* 2317 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2318 */       if (this.isStream[parameterIndex]) {
/* 2319 */         return streamToBytes(this.parameterStreams[parameterIndex], false, this.streamLengths[parameterIndex], this.connection.getUseStreamLengthsInPrepStmts());
/*      */       }
/*      */ 
/*      */       
/* 2323 */       byte[] parameterVal = this.parameterValues[parameterIndex];
/*      */       
/* 2325 */       if (parameterVal == null) {
/* 2326 */         return null;
/*      */       }
/*      */       
/* 2329 */       if (parameterVal[0] == 39 && parameterVal[parameterVal.length - 1] == 39) {
/* 2330 */         byte[] valNoQuotes = new byte[parameterVal.length - 2];
/* 2331 */         System.arraycopy(parameterVal, 1, valNoQuotes, 0, parameterVal.length - 2);
/*      */         
/* 2333 */         return valNoQuotes;
/*      */       } 
/*      */       
/* 2336 */       return parameterVal;
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
/*      */   protected byte[] getBytesRepresentationForBatch(int parameterIndex, int commandIndex) throws SQLException {
/* 2348 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2349 */       Object batchedArg = this.batchedArgs.get(commandIndex);
/* 2350 */       if (batchedArg instanceof String) {
/*      */         try {
/* 2352 */           return StringUtils.getBytes((String)batchedArg, this.charEncoding);
/*      */         }
/* 2354 */         catch (UnsupportedEncodingException uue) {
/* 2355 */           throw new RuntimeException(Messages.getString("PreparedStatement.32") + this.charEncoding + Messages.getString("PreparedStatement.33"));
/*      */         } 
/*      */       }
/*      */       
/* 2359 */       BatchParams params = (BatchParams)batchedArg;
/* 2360 */       if (params.isStream[parameterIndex]) {
/* 2361 */         return streamToBytes(params.parameterStreams[parameterIndex], false, params.streamLengths[parameterIndex], this.connection.getUseStreamLengthsInPrepStmts());
/*      */       }
/*      */       
/* 2364 */       byte[] parameterVal = params.parameterStrings[parameterIndex];
/* 2365 */       if (parameterVal == null) {
/* 2366 */         return null;
/*      */       }
/*      */       
/* 2369 */       if (parameterVal[0] == 39 && parameterVal[parameterVal.length - 1] == 39) {
/* 2370 */         byte[] valNoQuotes = new byte[parameterVal.length - 2];
/* 2371 */         System.arraycopy(parameterVal, 1, valNoQuotes, 0, parameterVal.length - 2);
/*      */         
/* 2373 */         return valNoQuotes;
/*      */       } 
/*      */       
/* 2376 */       return parameterVal;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final String getDateTimePattern(String dt, boolean toTime) throws Exception {
/* 2386 */     int dtLength = (dt != null) ? dt.length() : 0;
/*      */     
/* 2388 */     if (dtLength >= 8 && dtLength <= 10) {
/* 2389 */       int dashCount = 0;
/* 2390 */       boolean isDateOnly = true;
/*      */       
/* 2392 */       for (int k = 0; k < dtLength; k++) {
/* 2393 */         char c = dt.charAt(k);
/*      */         
/* 2395 */         if (!Character.isDigit(c) && c != '-') {
/* 2396 */           isDateOnly = false;
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/* 2401 */         if (c == '-') {
/* 2402 */           dashCount++;
/*      */         }
/*      */       } 
/*      */       
/* 2406 */       if (isDateOnly && dashCount == 2) {
/* 2407 */         return "yyyy-MM-dd";
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2414 */     boolean colonsOnly = true;
/*      */     
/* 2416 */     for (int i = 0; i < dtLength; i++) {
/* 2417 */       char c = dt.charAt(i);
/*      */       
/* 2419 */       if (!Character.isDigit(c) && c != ':') {
/* 2420 */         colonsOnly = false;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/* 2426 */     if (colonsOnly) {
/* 2427 */       return "HH:mm:ss";
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2436 */     StringReader reader = new StringReader(dt + " ");
/* 2437 */     ArrayList<Object[]> vec = new ArrayList();
/* 2438 */     ArrayList<Object[]> vecRemovelist = new ArrayList();
/* 2439 */     Object[] nv = new Object[3];
/*      */     
/* 2441 */     nv[0] = Character.valueOf('y');
/* 2442 */     nv[1] = new StringBuilder();
/* 2443 */     nv[2] = Integer.valueOf(0);
/* 2444 */     vec.add(nv);
/*      */     
/* 2446 */     if (toTime) {
/* 2447 */       nv = new Object[3];
/* 2448 */       nv[0] = Character.valueOf('h');
/* 2449 */       nv[1] = new StringBuilder();
/* 2450 */       nv[2] = Integer.valueOf(0);
/* 2451 */       vec.add(nv);
/*      */     } 
/*      */     int z;
/* 2454 */     while ((z = reader.read()) != -1) {
/* 2455 */       char separator = (char)z;
/* 2456 */       int maxvecs = vec.size();
/*      */       
/* 2458 */       for (int count = 0; count < maxvecs; count++) {
/* 2459 */         Object[] arrayOfObject = vec.get(count);
/* 2460 */         int n = ((Integer)arrayOfObject[2]).intValue();
/* 2461 */         char c = getSuccessor(((Character)arrayOfObject[0]).charValue(), n);
/*      */         
/* 2463 */         if (!Character.isLetterOrDigit(separator)) {
/* 2464 */           if (c == ((Character)arrayOfObject[0]).charValue() && c != 'S') {
/* 2465 */             vecRemovelist.add(arrayOfObject);
/*      */           } else {
/* 2467 */             ((StringBuilder)arrayOfObject[1]).append(separator);
/*      */             
/* 2469 */             if (c == 'X' || c == 'Y') {
/* 2470 */               arrayOfObject[2] = Integer.valueOf(4);
/*      */             }
/*      */           } 
/*      */         } else {
/* 2474 */           if (c == 'X') {
/* 2475 */             c = 'y';
/* 2476 */             nv = new Object[3];
/* 2477 */             nv[1] = (new StringBuilder(((StringBuilder)arrayOfObject[1]).toString())).append('M');
/* 2478 */             nv[0] = Character.valueOf('M');
/* 2479 */             nv[2] = Integer.valueOf(1);
/* 2480 */             vec.add(nv);
/* 2481 */           } else if (c == 'Y') {
/* 2482 */             c = 'M';
/* 2483 */             nv = new Object[3];
/* 2484 */             nv[1] = (new StringBuilder(((StringBuilder)arrayOfObject[1]).toString())).append('d');
/* 2485 */             nv[0] = Character.valueOf('d');
/* 2486 */             nv[2] = Integer.valueOf(1);
/* 2487 */             vec.add(nv);
/*      */           } 
/*      */           
/* 2490 */           ((StringBuilder)arrayOfObject[1]).append(c);
/*      */           
/* 2492 */           if (c == ((Character)arrayOfObject[0]).charValue()) {
/* 2493 */             arrayOfObject[2] = Integer.valueOf(n + 1);
/*      */           } else {
/* 2495 */             arrayOfObject[0] = Character.valueOf(c);
/* 2496 */             arrayOfObject[2] = Integer.valueOf(1);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2501 */       int k = vecRemovelist.size();
/*      */       
/* 2503 */       for (int m = 0; m < k; m++) {
/* 2504 */         Object[] arrayOfObject = vecRemovelist.get(m);
/* 2505 */         vec.remove(arrayOfObject);
/*      */       } 
/*      */       
/* 2508 */       vecRemovelist.clear();
/*      */     } 
/*      */     
/* 2511 */     int size = vec.size();
/*      */     int j;
/* 2513 */     for (j = 0; j < size; j++) {
/* 2514 */       Object[] arrayOfObject = vec.get(j);
/* 2515 */       char c = ((Character)arrayOfObject[0]).charValue();
/* 2516 */       int n = ((Integer)arrayOfObject[2]).intValue();
/*      */       
/* 2518 */       boolean bk = (getSuccessor(c, n) != c);
/* 2519 */       boolean atEnd = ((c == 's' || c == 'm' || (c == 'h' && toTime)) && bk);
/* 2520 */       boolean finishesAtDate = (bk && c == 'd' && !toTime);
/* 2521 */       boolean containsEnd = (((StringBuilder)arrayOfObject[1]).toString().indexOf('W') != -1);
/*      */       
/* 2523 */       if ((!atEnd && !finishesAtDate) || containsEnd) {
/* 2524 */         vecRemovelist.add(arrayOfObject);
/*      */       }
/*      */     } 
/*      */     
/* 2528 */     size = vecRemovelist.size();
/*      */     
/* 2530 */     for (j = 0; j < size; j++) {
/* 2531 */       vec.remove(vecRemovelist.get(j));
/*      */     }
/*      */     
/* 2534 */     vecRemovelist.clear();
/* 2535 */     Object[] v = vec.get(0);
/*      */     
/* 2537 */     StringBuilder format = (StringBuilder)v[1];
/* 2538 */     format.setLength(format.length() - 1);
/*      */     
/* 2540 */     return format.toString();
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
/*      */   public ResultSetMetaData getMetaData() throws SQLException {
/* 2554 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2562 */       if (!isSelectQuery()) {
/* 2563 */         return null;
/*      */       }
/*      */       
/* 2566 */       PreparedStatement mdStmt = null;
/* 2567 */       ResultSet mdRs = null;
/*      */       
/* 2569 */       if (this.pstmtResultMetaData == null) {
/*      */         try {
/* 2571 */           mdStmt = new PreparedStatement(this.connection, this.originalSql, this.currentCatalog, this.parseInfo);
/*      */           
/* 2573 */           mdStmt.setMaxRows(1);
/*      */           
/* 2575 */           int paramCount = this.parameterValues.length;
/*      */           
/* 2577 */           for (int i = 1; i <= paramCount; i++) {
/* 2578 */             mdStmt.setString(i, (String)null);
/*      */           }
/*      */           
/* 2581 */           boolean hadResults = mdStmt.execute();
/*      */           
/* 2583 */           if (hadResults) {
/* 2584 */             mdRs = mdStmt.getResultSet();
/*      */             
/* 2586 */             this.pstmtResultMetaData = mdRs.getMetaData();
/*      */           } else {
/* 2588 */             this.pstmtResultMetaData = new ResultSetMetaData(new Field[0], this.connection.getUseOldAliasMetadataBehavior(), this.connection.getYearIsDateType(), getExceptionInterceptor());
/*      */           } 
/*      */         } finally {
/*      */           
/* 2592 */           SQLException sqlExRethrow = null;
/*      */           
/* 2594 */           if (mdRs != null) {
/*      */             try {
/* 2596 */               mdRs.close();
/* 2597 */             } catch (SQLException sqlEx) {
/* 2598 */               sqlExRethrow = sqlEx;
/*      */             } 
/*      */             
/* 2601 */             mdRs = null;
/*      */           } 
/*      */           
/* 2604 */           if (mdStmt != null) {
/*      */             try {
/* 2606 */               mdStmt.close();
/* 2607 */             } catch (SQLException sqlEx) {
/* 2608 */               sqlExRethrow = sqlEx;
/*      */             } 
/*      */             
/* 2611 */             mdStmt = null;
/*      */           } 
/*      */           
/* 2614 */           if (sqlExRethrow != null) {
/* 2615 */             throw sqlExRethrow;
/*      */           }
/*      */         } 
/*      */       }
/*      */       
/* 2620 */       return this.pstmtResultMetaData;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean isSelectQuery() throws SQLException {
/* 2625 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2626 */       return StringUtils.startsWithIgnoreCaseAndWs(StringUtils.stripComments(this.originalSql, "'\"", "'\"", true, false, true, true), "SELECT");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ParameterMetaData getParameterMetaData() throws SQLException {
/* 2634 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2635 */       if (this.parameterMetaData == null) {
/* 2636 */         if (this.connection.getGenerateSimpleParameterMetadata()) {
/* 2637 */           this.parameterMetaData = new MysqlParameterMetadata(this.parameterCount);
/*      */         } else {
/* 2639 */           this.parameterMetaData = new MysqlParameterMetadata(null, this.parameterCount, getExceptionInterceptor());
/*      */         } 
/*      */       }
/*      */       
/* 2643 */       return this.parameterMetaData;
/*      */     } 
/*      */   }
/*      */   
/*      */   ParseInfo getParseInfo() {
/* 2648 */     return this.parseInfo;
/*      */   }
/*      */   
/*      */   private final char getSuccessor(char c, int n) {
/* 2652 */     return (c == 'y' && n == 2) ? 'X' : ((c == 'y' && n < 4) ? 'y' : ((c == 'y') ? 'M' : ((c == 'M' && n == 2) ? 'Y' : ((c == 'M' && n < 3) ? 'M' : ((c == 'M') ? 'd' : ((c == 'd' && n < 2) ? 'd' : ((c == 'd') ? 'H' : ((c == 'H' && n < 2) ? 'H' : ((c == 'H') ? 'm' : ((c == 'm' && n < 2) ? 'm' : ((c == 'm') ? 's' : ((c == 's' && n < 2) ? 's' : 'W'))))))))))));
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
/*      */   private final void hexEscapeBlock(byte[] buf, Buffer packet, int size) throws SQLException {
/* 2676 */     for (int i = 0; i < size; i++) {
/* 2677 */       byte b = buf[i];
/* 2678 */       int lowBits = (b & 0xFF) / 16;
/* 2679 */       int highBits = (b & 0xFF) % 16;
/*      */       
/* 2681 */       packet.writeByte(HEX_DIGITS[lowBits]);
/* 2682 */       packet.writeByte(HEX_DIGITS[highBits]);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void initializeFromParseInfo() throws SQLException {
/* 2687 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2688 */       this.staticSqlStrings = this.parseInfo.staticSql;
/* 2689 */       this.isLoadDataQuery = this.parseInfo.foundLoadData;
/* 2690 */       this.firstCharOfStmt = this.parseInfo.firstStmtChar;
/*      */       
/* 2692 */       this.parameterCount = this.staticSqlStrings.length - 1;
/*      */       
/* 2694 */       this.parameterValues = new byte[this.parameterCount][];
/* 2695 */       this.parameterStreams = new InputStream[this.parameterCount];
/* 2696 */       this.isStream = new boolean[this.parameterCount];
/* 2697 */       this.streamLengths = new int[this.parameterCount];
/* 2698 */       this.isNull = new boolean[this.parameterCount];
/* 2699 */       this.parameterTypes = new int[this.parameterCount];
/*      */       
/* 2701 */       clearParameters();
/*      */       
/* 2703 */       for (int j = 0; j < this.parameterCount; j++) {
/* 2704 */         this.isStream[j] = false;
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   boolean isNull(int paramIndex) throws SQLException {
/* 2710 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2711 */       return this.isNull[paramIndex];
/*      */     } 
/*      */   }
/*      */   
/*      */   private final int readblock(InputStream i, byte[] b) throws SQLException {
/*      */     try {
/* 2717 */       return i.read(b);
/* 2718 */     } catch (Throwable ex) {
/* 2719 */       SQLException sqlEx = SQLError.createSQLException(Messages.getString("PreparedStatement.56") + ex.getClass().getName(), "S1000", getExceptionInterceptor());
/*      */       
/* 2721 */       sqlEx.initCause(ex);
/*      */       
/* 2723 */       throw sqlEx;
/*      */     } 
/*      */   }
/*      */   
/*      */   private final int readblock(InputStream i, byte[] b, int length) throws SQLException {
/*      */     try {
/* 2729 */       int lengthToRead = length;
/*      */       
/* 2731 */       if (lengthToRead > b.length) {
/* 2732 */         lengthToRead = b.length;
/*      */       }
/*      */       
/* 2735 */       return i.read(b, 0, lengthToRead);
/* 2736 */     } catch (Throwable ex) {
/* 2737 */       SQLException sqlEx = SQLError.createSQLException(Messages.getString("PreparedStatement.56") + ex.getClass().getName(), "S1000", getExceptionInterceptor());
/*      */       
/* 2739 */       sqlEx.initCause(ex);
/*      */       
/* 2741 */       throw sqlEx;
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
/*      */ 
/*      */ 
/*      */   
/*      */   protected void realClose(boolean calledExplicitly, boolean closeOpenResults) throws SQLException {
/* 2756 */     MySQLConnection locallyScopedConn = this.connection;
/*      */     
/* 2758 */     if (locallyScopedConn == null) {
/*      */       return;
/*      */     }
/*      */     
/* 2762 */     synchronized (locallyScopedConn.getConnectionMutex()) {
/*      */ 
/*      */ 
/*      */       
/* 2766 */       if (this.isClosed) {
/*      */         return;
/*      */       }
/*      */       
/* 2770 */       if (this.useUsageAdvisor && 
/* 2771 */         this.numberOfExecutions <= 1) {
/* 2772 */         this.connection.getProfilerEventHandlerInstance().processEvent((byte)0, this.connection, this, null, 0L, new Throwable(), Messages.getString("PreparedStatement.43"));
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 2777 */       super.realClose(calledExplicitly, closeOpenResults);
/*      */       
/* 2779 */       this.dbmd = null;
/* 2780 */       this.originalSql = null;
/* 2781 */       this.staticSqlStrings = (byte[][])null;
/* 2782 */       this.parameterValues = (byte[][])null;
/* 2783 */       this.parameterStreams = null;
/* 2784 */       this.isStream = null;
/* 2785 */       this.streamLengths = null;
/* 2786 */       this.isNull = null;
/* 2787 */       this.streamConvertBuf = null;
/* 2788 */       this.parameterTypes = null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setArray(int i, Array x) throws SQLException {
/* 2805 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
/* 2830 */     if (x == null) {
/* 2831 */       setNull(parameterIndex, 12);
/*      */     } else {
/* 2833 */       setBinaryStream(parameterIndex, x, length);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
/* 2850 */     if (x == null) {
/* 2851 */       setNull(parameterIndex, 3);
/*      */     } else {
/* 2853 */       setInternal(parameterIndex, StringUtils.fixDecimalExponent(StringUtils.consistentToString(x)));
/*      */       
/* 2855 */       this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 3;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
/* 2879 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2880 */       if (x == null) {
/* 2881 */         setNull(parameterIndex, -2);
/*      */       } else {
/* 2883 */         int parameterIndexOffset = getParameterIndexOffset();
/*      */         
/* 2885 */         if (parameterIndex < 1 || parameterIndex > this.staticSqlStrings.length) {
/* 2886 */           throw SQLError.createSQLException(Messages.getString("PreparedStatement.2") + parameterIndex + Messages.getString("PreparedStatement.3") + this.staticSqlStrings.length + Messages.getString("PreparedStatement.4"), "S1009", getExceptionInterceptor());
/*      */         }
/*      */ 
/*      */         
/* 2890 */         if (parameterIndexOffset == -1 && parameterIndex == 1) {
/* 2891 */           throw SQLError.createSQLException("Can't set IN parameter for return value of stored function call.", "S1009", getExceptionInterceptor());
/*      */         }
/*      */ 
/*      */         
/* 2895 */         this.parameterStreams[parameterIndex - 1 + parameterIndexOffset] = x;
/* 2896 */         this.isStream[parameterIndex - 1 + parameterIndexOffset] = true;
/* 2897 */         this.streamLengths[parameterIndex - 1 + parameterIndexOffset] = length;
/* 2898 */         this.isNull[parameterIndex - 1 + parameterIndexOffset] = false;
/* 2899 */         this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 2004;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
/* 2905 */     setBinaryStream(parameterIndex, inputStream, (int)length);
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
/*      */   public void setBlob(int i, Blob x) throws SQLException {
/* 2920 */     if (x == null) {
/* 2921 */       setNull(i, 2004);
/*      */     } else {
/* 2923 */       ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
/*      */       
/* 2925 */       bytesOut.write(39);
/* 2926 */       escapeblockFast(x.getBytes(1L, (int)x.length()), bytesOut, (int)x.length());
/* 2927 */       bytesOut.write(39);
/*      */       
/* 2929 */       setInternal(i, bytesOut.toByteArray());
/*      */       
/* 2931 */       this.parameterTypes[i - 1 + getParameterIndexOffset()] = 2004;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBoolean(int parameterIndex, boolean x) throws SQLException {
/* 2948 */     if (this.useTrueBoolean) {
/* 2949 */       setInternal(parameterIndex, x ? "1" : "0");
/*      */     } else {
/* 2951 */       setInternal(parameterIndex, x ? "'t'" : "'f'");
/*      */       
/* 2953 */       this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 16;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setByte(int parameterIndex, byte x) throws SQLException {
/* 2970 */     setInternal(parameterIndex, String.valueOf(x));
/*      */     
/* 2972 */     this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = -6;
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
/*      */   public void setBytes(int parameterIndex, byte[] x) throws SQLException {
/* 2989 */     setBytes(parameterIndex, x, true, true);
/*      */     
/* 2991 */     if (x != null) {
/* 2992 */       this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = -2;
/*      */     }
/*      */   }
/*      */   
/*      */   protected void setBytes(int parameterIndex, byte[] x, boolean checkForIntroducer, boolean escapeForMBChars) throws SQLException {
/* 2997 */     synchronized (checkClosed().getConnectionMutex()) {
/* 2998 */       if (x == null) {
/* 2999 */         setNull(parameterIndex, -2);
/*      */       } else {
/* 3001 */         String connectionEncoding = this.connection.getEncoding();
/*      */         
/*      */         try {
/* 3004 */           if (this.connection.isNoBackslashEscapesSet() || (escapeForMBChars && this.connection.getUseUnicode() && connectionEncoding != null && CharsetMapping.isMultibyteCharset(connectionEncoding))) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 3009 */             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(x.length * 2 + 3);
/* 3010 */             byteArrayOutputStream.write(120);
/* 3011 */             byteArrayOutputStream.write(39);
/*      */             
/* 3013 */             for (int j = 0; j < x.length; j++) {
/* 3014 */               int lowBits = (x[j] & 0xFF) / 16;
/* 3015 */               int highBits = (x[j] & 0xFF) % 16;
/*      */               
/* 3017 */               byteArrayOutputStream.write(HEX_DIGITS[lowBits]);
/* 3018 */               byteArrayOutputStream.write(HEX_DIGITS[highBits]);
/*      */             } 
/*      */             
/* 3021 */             byteArrayOutputStream.write(39);
/*      */             
/* 3023 */             setInternal(parameterIndex, byteArrayOutputStream.toByteArray());
/*      */             
/*      */             return;
/*      */           } 
/* 3027 */         } catch (SQLException ex) {
/* 3028 */           throw ex;
/* 3029 */         } catch (RuntimeException ex) {
/* 3030 */           SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 3031 */           sqlEx.initCause(ex);
/* 3032 */           throw sqlEx;
/*      */         } 
/*      */ 
/*      */         
/* 3036 */         int numBytes = x.length;
/*      */         
/* 3038 */         int pad = 2;
/*      */         
/* 3040 */         boolean needsIntroducer = (checkForIntroducer && this.connection.versionMeetsMinimum(4, 1, 0));
/*      */         
/* 3042 */         if (needsIntroducer) {
/* 3043 */           pad += 7;
/*      */         }
/*      */         
/* 3046 */         ByteArrayOutputStream bOut = new ByteArrayOutputStream(numBytes + pad);
/*      */         
/* 3048 */         if (needsIntroducer) {
/* 3049 */           bOut.write(95);
/* 3050 */           bOut.write(98);
/* 3051 */           bOut.write(105);
/* 3052 */           bOut.write(110);
/* 3053 */           bOut.write(97);
/* 3054 */           bOut.write(114);
/* 3055 */           bOut.write(121);
/*      */         } 
/* 3057 */         bOut.write(39);
/*      */         
/* 3059 */         for (int i = 0; i < numBytes; i++) {
/* 3060 */           byte b = x[i];
/*      */           
/* 3062 */           switch (b) {
/*      */             case 0:
/* 3064 */               bOut.write(92);
/* 3065 */               bOut.write(48);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 10:
/* 3070 */               bOut.write(92);
/* 3071 */               bOut.write(110);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 13:
/* 3076 */               bOut.write(92);
/* 3077 */               bOut.write(114);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 92:
/* 3082 */               bOut.write(92);
/* 3083 */               bOut.write(92);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 39:
/* 3088 */               bOut.write(92);
/* 3089 */               bOut.write(39);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 34:
/* 3094 */               bOut.write(92);
/* 3095 */               bOut.write(34);
/*      */               break;
/*      */ 
/*      */             
/*      */             case 26:
/* 3100 */               bOut.write(92);
/* 3101 */               bOut.write(90);
/*      */               break;
/*      */ 
/*      */             
/*      */             default:
/* 3106 */               bOut.write(b);
/*      */               break;
/*      */           } 
/*      */         } 
/* 3110 */         bOut.write(39);
/*      */         
/* 3112 */         setInternal(parameterIndex, bOut.toByteArray());
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBytesNoEscape(int parameterIndex, byte[] parameterAsBytes) throws SQLException {
/* 3130 */     byte[] parameterWithQuotes = new byte[parameterAsBytes.length + 2];
/* 3131 */     parameterWithQuotes[0] = 39;
/* 3132 */     System.arraycopy(parameterAsBytes, 0, parameterWithQuotes, 1, parameterAsBytes.length);
/* 3133 */     parameterWithQuotes[parameterAsBytes.length + 1] = 39;
/*      */     
/* 3135 */     setInternal(parameterIndex, parameterWithQuotes);
/*      */   }
/*      */   
/*      */   protected void setBytesNoEscapeNoQuotes(int parameterIndex, byte[] parameterAsBytes) throws SQLException {
/* 3139 */     setInternal(parameterIndex, parameterAsBytes);
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
/*      */   public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
/* 3164 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       try {
/* 3166 */         if (reader == null) {
/* 3167 */           setNull(parameterIndex, -1);
/*      */         } else {
/* 3169 */           char[] c = null;
/* 3170 */           int len = 0;
/*      */           
/* 3172 */           boolean useLength = this.connection.getUseStreamLengthsInPrepStmts();
/*      */           
/* 3174 */           String forcedEncoding = this.connection.getClobCharacterEncoding();
/*      */           
/* 3176 */           if (useLength && length != -1) {
/* 3177 */             c = new char[length];
/*      */             
/* 3179 */             int numCharsRead = readFully(reader, c, length);
/*      */             
/* 3181 */             if (forcedEncoding == null) {
/* 3182 */               setString(parameterIndex, new String(c, 0, numCharsRead));
/*      */             } else {
/*      */               try {
/* 3185 */                 setBytes(parameterIndex, StringUtils.getBytes(new String(c, 0, numCharsRead), forcedEncoding));
/* 3186 */               } catch (UnsupportedEncodingException uee) {
/* 3187 */                 throw SQLError.createSQLException("Unsupported character encoding " + forcedEncoding, "S1009", getExceptionInterceptor());
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             
/* 3192 */             c = new char[4096];
/*      */             
/* 3194 */             StringBuilder buf = new StringBuilder();
/*      */             
/* 3196 */             while ((len = reader.read(c)) != -1) {
/* 3197 */               buf.append(c, 0, len);
/*      */             }
/*      */             
/* 3200 */             if (forcedEncoding == null) {
/* 3201 */               setString(parameterIndex, buf.toString());
/*      */             } else {
/*      */               try {
/* 3204 */                 setBytes(parameterIndex, StringUtils.getBytes(buf.toString(), forcedEncoding));
/* 3205 */               } catch (UnsupportedEncodingException uee) {
/* 3206 */                 throw SQLError.createSQLException("Unsupported character encoding " + forcedEncoding, "S1009", getExceptionInterceptor());
/*      */               } 
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 3212 */           this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 2005;
/*      */         } 
/* 3214 */       } catch (IOException ioEx) {
/* 3215 */         throw SQLError.createSQLException(ioEx.toString(), "S1000", getExceptionInterceptor());
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClob(int i, Clob x) throws SQLException {
/* 3232 */     synchronized (checkClosed().getConnectionMutex()) {
/* 3233 */       if (x == null) {
/* 3234 */         setNull(i, 2005);
/*      */       } else {
/*      */         
/* 3237 */         String forcedEncoding = this.connection.getClobCharacterEncoding();
/*      */         
/* 3239 */         if (forcedEncoding == null) {
/* 3240 */           setString(i, x.getSubString(1L, (int)x.length()));
/*      */         } else {
/*      */           try {
/* 3243 */             setBytes(i, StringUtils.getBytes(x.getSubString(1L, (int)x.length()), forcedEncoding));
/* 3244 */           } catch (UnsupportedEncodingException uee) {
/* 3245 */             throw SQLError.createSQLException("Unsupported character encoding " + forcedEncoding, "S1009", getExceptionInterceptor());
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 3250 */         this.parameterTypes[i - 1 + getParameterIndexOffset()] = 2005;
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDate(int parameterIndex, Date x) throws SQLException {
/* 3268 */     setDate(parameterIndex, x, (Calendar)null);
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
/*      */   public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
/* 3286 */     if (x == null) {
/* 3287 */       setNull(parameterIndex, 91);
/*      */     }
/* 3289 */     else if (!this.useLegacyDatetimeCode) {
/* 3290 */       newSetDateInternal(parameterIndex, x, cal);
/*      */     } else {
/* 3292 */       synchronized (checkClosed().getConnectionMutex()) {
/* 3293 */         this.ddf = TimeUtil.getSimpleDateFormat(this.ddf, "''yyyy-MM-dd''", cal, null);
/*      */         
/* 3295 */         setInternal(parameterIndex, this.ddf.format(x));
/*      */         
/* 3297 */         this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 91;
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDouble(int parameterIndex, double x) throws SQLException {
/* 3316 */     synchronized (checkClosed().getConnectionMutex()) {
/* 3317 */       if (!this.connection.getAllowNanAndInf() && (x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY || Double.isNaN(x))) {
/* 3318 */         throw SQLError.createSQLException("'" + x + "' is not a valid numeric or approximate numeric value", "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3323 */       setInternal(parameterIndex, StringUtils.fixDecimalExponent(String.valueOf(x)));
/*      */       
/* 3325 */       this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 8;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloat(int parameterIndex, float x) throws SQLException {
/* 3342 */     setInternal(parameterIndex, StringUtils.fixDecimalExponent(String.valueOf(x)));
/*      */     
/* 3344 */     this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 6;
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
/*      */   public void setInt(int parameterIndex, int x) throws SQLException {
/* 3360 */     setInternal(parameterIndex, String.valueOf(x));
/*      */     
/* 3362 */     this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 4;
/*      */   }
/*      */   
/*      */   protected final void setInternal(int paramIndex, byte[] val) throws SQLException {
/* 3366 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/* 3368 */       int parameterIndexOffset = getParameterIndexOffset();
/*      */       
/* 3370 */       checkBounds(paramIndex, parameterIndexOffset);
/*      */       
/* 3372 */       this.isStream[paramIndex - 1 + parameterIndexOffset] = false;
/* 3373 */       this.isNull[paramIndex - 1 + parameterIndexOffset] = false;
/* 3374 */       this.parameterStreams[paramIndex - 1 + parameterIndexOffset] = null;
/* 3375 */       this.parameterValues[paramIndex - 1 + parameterIndexOffset] = val;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void checkBounds(int paramIndex, int parameterIndexOffset) throws SQLException {
/* 3380 */     synchronized (checkClosed().getConnectionMutex()) {
/* 3381 */       if (paramIndex < 1) {
/* 3382 */         throw SQLError.createSQLException(Messages.getString("PreparedStatement.49") + paramIndex + Messages.getString("PreparedStatement.50"), "S1009", getExceptionInterceptor());
/*      */       }
/* 3384 */       if (paramIndex > this.parameterCount) {
/* 3385 */         throw SQLError.createSQLException(Messages.getString("PreparedStatement.51") + paramIndex + Messages.getString("PreparedStatement.52") + this.parameterValues.length + Messages.getString("PreparedStatement.53"), "S1009", getExceptionInterceptor());
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 3390 */       if (parameterIndexOffset == -1 && paramIndex == 1) {
/* 3391 */         throw SQLError.createSQLException("Can't set IN parameter for return value of stored function call.", "S1009", getExceptionInterceptor());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected final void setInternal(int paramIndex, String val) throws SQLException {
/* 3398 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/* 3400 */       byte[] parameterAsBytes = null;
/*      */       
/* 3402 */       if (this.charConverter != null) {
/* 3403 */         parameterAsBytes = this.charConverter.toBytes(val);
/*      */       } else {
/* 3405 */         parameterAsBytes = StringUtils.getBytes(val, this.charConverter, this.charEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor());
/*      */       } 
/*      */ 
/*      */       
/* 3409 */       setInternal(paramIndex, parameterAsBytes);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLong(int parameterIndex, long x) throws SQLException {
/* 3426 */     setInternal(parameterIndex, String.valueOf(x));
/*      */     
/* 3428 */     this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = -5;
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
/*      */   public void setNull(int parameterIndex, int sqlType) throws SQLException {
/* 3447 */     synchronized (checkClosed().getConnectionMutex()) {
/* 3448 */       setInternal(parameterIndex, "null");
/* 3449 */       this.isNull[parameterIndex - 1 + getParameterIndexOffset()] = true;
/*      */       
/* 3451 */       this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 0;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNull(int parameterIndex, int sqlType, String arg) throws SQLException {
/* 3473 */     setNull(parameterIndex, sqlType);
/*      */     
/* 3475 */     this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   private void setNumericObject(int parameterIndex, Object parameterObj, int targetSqlType, int scale) throws SQLException {
/*      */     Number parameterAsNum;
/* 3481 */     if (parameterObj instanceof Boolean) {
/* 3482 */       parameterAsNum = ((Boolean)parameterObj).booleanValue() ? Integer.valueOf(1) : Integer.valueOf(0);
/* 3483 */     } else if (parameterObj instanceof String) {
/* 3484 */       boolean parameterAsBoolean; switch (targetSqlType) {
/*      */         case -7:
/* 3486 */           if ("1".equals(parameterObj) || "0".equals(parameterObj)) {
/* 3487 */             Number number = Integer.valueOf((String)parameterObj); break;
/*      */           } 
/* 3489 */           parameterAsBoolean = "true".equalsIgnoreCase((String)parameterObj);
/*      */           
/* 3491 */           parameterAsNum = parameterAsBoolean ? Integer.valueOf(1) : Integer.valueOf(0);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case -6:
/*      */         case 4:
/*      */         case 5:
/* 3499 */           parameterAsNum = Integer.valueOf((String)parameterObj);
/*      */           break;
/*      */ 
/*      */         
/*      */         case -5:
/* 3504 */           parameterAsNum = Long.valueOf((String)parameterObj);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 7:
/* 3509 */           parameterAsNum = Float.valueOf((String)parameterObj);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 6:
/*      */         case 8:
/* 3515 */           parameterAsNum = Double.valueOf((String)parameterObj);
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 3522 */           parameterAsNum = new BigDecimal((String)parameterObj); break;
/*      */       } 
/*      */     } else {
/* 3525 */       parameterAsNum = (Number)parameterObj;
/*      */     } 
/*      */     
/* 3528 */     switch (targetSqlType) {
/*      */       case -7:
/*      */       case -6:
/*      */       case 4:
/*      */       case 5:
/* 3533 */         setInt(parameterIndex, parameterAsNum.intValue());
/*      */         break;
/*      */ 
/*      */       
/*      */       case -5:
/* 3538 */         setLong(parameterIndex, parameterAsNum.longValue());
/*      */         break;
/*      */ 
/*      */       
/*      */       case 7:
/* 3543 */         setFloat(parameterIndex, parameterAsNum.floatValue());
/*      */         break;
/*      */ 
/*      */       
/*      */       case 6:
/*      */       case 8:
/* 3549 */         setDouble(parameterIndex, parameterAsNum.doubleValue());
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 3:
/* 3556 */         if (parameterAsNum instanceof BigDecimal) {
/* 3557 */           BigDecimal scaledBigDecimal = null;
/*      */           
/*      */           try {
/* 3560 */             scaledBigDecimal = ((BigDecimal)parameterAsNum).setScale(scale);
/* 3561 */           } catch (ArithmeticException ex) {
/*      */             try {
/* 3563 */               scaledBigDecimal = ((BigDecimal)parameterAsNum).setScale(scale, 4);
/* 3564 */             } catch (ArithmeticException arEx) {
/* 3565 */               throw SQLError.createSQLException("Can't set scale of '" + scale + "' for DECIMAL argument '" + parameterAsNum + "'", "S1009", getExceptionInterceptor());
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 3570 */           setBigDecimal(parameterIndex, scaledBigDecimal); break;
/* 3571 */         }  if (parameterAsNum instanceof BigInteger) {
/* 3572 */           setBigDecimal(parameterIndex, new BigDecimal((BigInteger)parameterAsNum, scale)); break;
/*      */         } 
/* 3574 */         setBigDecimal(parameterIndex, new BigDecimal(parameterAsNum.doubleValue()));
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObject(int parameterIndex, Object parameterObj) throws SQLException {
/* 3582 */     synchronized (checkClosed().getConnectionMutex()) {
/* 3583 */       if (parameterObj == null) {
/* 3584 */         setNull(parameterIndex, 1111);
/*      */       }
/* 3586 */       else if (parameterObj instanceof Byte) {
/* 3587 */         setInt(parameterIndex, ((Byte)parameterObj).intValue());
/* 3588 */       } else if (parameterObj instanceof String) {
/* 3589 */         setString(parameterIndex, (String)parameterObj);
/* 3590 */       } else if (parameterObj instanceof BigDecimal) {
/* 3591 */         setBigDecimal(parameterIndex, (BigDecimal)parameterObj);
/* 3592 */       } else if (parameterObj instanceof Short) {
/* 3593 */         setShort(parameterIndex, ((Short)parameterObj).shortValue());
/* 3594 */       } else if (parameterObj instanceof Integer) {
/* 3595 */         setInt(parameterIndex, ((Integer)parameterObj).intValue());
/* 3596 */       } else if (parameterObj instanceof Long) {
/* 3597 */         setLong(parameterIndex, ((Long)parameterObj).longValue());
/* 3598 */       } else if (parameterObj instanceof Float) {
/* 3599 */         setFloat(parameterIndex, ((Float)parameterObj).floatValue());
/* 3600 */       } else if (parameterObj instanceof Double) {
/* 3601 */         setDouble(parameterIndex, ((Double)parameterObj).doubleValue());
/* 3602 */       } else if (parameterObj instanceof byte[]) {
/* 3603 */         setBytes(parameterIndex, (byte[])parameterObj);
/* 3604 */       } else if (parameterObj instanceof Date) {
/* 3605 */         setDate(parameterIndex, (Date)parameterObj);
/* 3606 */       } else if (parameterObj instanceof Time) {
/* 3607 */         setTime(parameterIndex, (Time)parameterObj);
/* 3608 */       } else if (parameterObj instanceof Timestamp) {
/* 3609 */         setTimestamp(parameterIndex, (Timestamp)parameterObj);
/* 3610 */       } else if (parameterObj instanceof Boolean) {
/* 3611 */         setBoolean(parameterIndex, ((Boolean)parameterObj).booleanValue());
/* 3612 */       } else if (parameterObj instanceof InputStream) {
/* 3613 */         setBinaryStream(parameterIndex, (InputStream)parameterObj, -1);
/* 3614 */       } else if (parameterObj instanceof Blob) {
/* 3615 */         setBlob(parameterIndex, (Blob)parameterObj);
/* 3616 */       } else if (parameterObj instanceof Clob) {
/* 3617 */         setClob(parameterIndex, (Clob)parameterObj);
/* 3618 */       } else if (this.connection.getTreatUtilDateAsTimestamp() && parameterObj instanceof Date) {
/* 3619 */         setTimestamp(parameterIndex, new Timestamp(((Date)parameterObj).getTime()));
/* 3620 */       } else if (parameterObj instanceof BigInteger) {
/* 3621 */         setString(parameterIndex, parameterObj.toString());
/*      */       } else {
/* 3623 */         setSerializableObject(parameterIndex, parameterObj);
/*      */       } 
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
/*      */   
/*      */   public void setObject(int parameterIndex, Object parameterObj, int targetSqlType) throws SQLException {
/* 3637 */     if (!(parameterObj instanceof BigDecimal)) {
/* 3638 */       setObject(parameterIndex, parameterObj, targetSqlType, 0);
/*      */     } else {
/* 3640 */       setObject(parameterIndex, parameterObj, targetSqlType, ((BigDecimal)parameterObj).scale());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObject(int parameterIndex, Object parameterObj, int targetSqlType, int scale) throws SQLException {
/* 3672 */     synchronized (checkClosed().getConnectionMutex()) {
/* 3673 */       if (parameterObj == null) {
/* 3674 */         setNull(parameterIndex, 1111);
/*      */       } else {
/*      */         try {
/*      */           Date parameterAsDate;
/*      */ 
/*      */           
/* 3680 */           switch (targetSqlType) {
/*      */             
/*      */             case 16:
/* 3683 */               if (parameterObj instanceof Boolean) {
/* 3684 */                 setBoolean(parameterIndex, ((Boolean)parameterObj).booleanValue());
/*      */                 break;
/*      */               } 
/* 3687 */               if (parameterObj instanceof String) {
/* 3688 */                 if ("true".equalsIgnoreCase((String)parameterObj) || "Y".equalsIgnoreCase((String)parameterObj)) {
/* 3689 */                   setBoolean(parameterIndex, true); break;
/* 3690 */                 }  if ("false".equalsIgnoreCase((String)parameterObj) || "N".equalsIgnoreCase((String)parameterObj)) {
/* 3691 */                   setBoolean(parameterIndex, false); break;
/* 3692 */                 }  if (((String)parameterObj).matches("-?\\d+\\.?\\d*")) {
/* 3693 */                   setBoolean(parameterIndex, !((String)parameterObj).matches("-?[0]+[.]*[0]*")); break;
/*      */                 } 
/* 3695 */                 throw SQLError.createSQLException("No conversion from " + parameterObj + " to Types.BOOLEAN possible.", "S1009", getExceptionInterceptor());
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/* 3700 */               if (parameterObj instanceof Number) {
/* 3701 */                 int intValue = ((Number)parameterObj).intValue();
/*      */                 
/* 3703 */                 setBoolean(parameterIndex, (intValue != 0));
/*      */                 
/*      */                 break;
/*      */               } 
/* 3707 */               throw SQLError.createSQLException("No conversion from " + parameterObj.getClass().getName() + " to Types.BOOLEAN possible.", "S1009", getExceptionInterceptor());
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case -7:
/*      */             case -6:
/*      */             case -5:
/*      */             case 2:
/*      */             case 3:
/*      */             case 4:
/*      */             case 5:
/*      */             case 6:
/*      */             case 7:
/*      */             case 8:
/* 3722 */               setNumericObject(parameterIndex, parameterObj, targetSqlType, scale);
/*      */               break;
/*      */ 
/*      */             
/*      */             case -1:
/*      */             case 1:
/*      */             case 12:
/* 3729 */               if (parameterObj instanceof BigDecimal) {
/* 3730 */                 setString(parameterIndex, StringUtils.fixDecimalExponent(StringUtils.consistentToString((BigDecimal)parameterObj))); break;
/*      */               } 
/* 3732 */               setString(parameterIndex, parameterObj.toString());
/*      */               break;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case 2005:
/* 3739 */               if (parameterObj instanceof Clob) {
/* 3740 */                 setClob(parameterIndex, (Clob)parameterObj); break;
/*      */               } 
/* 3742 */               setString(parameterIndex, parameterObj.toString());
/*      */               break;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case -4:
/*      */             case -3:
/*      */             case -2:
/*      */             case 2004:
/* 3752 */               if (parameterObj instanceof byte[]) {
/* 3753 */                 setBytes(parameterIndex, (byte[])parameterObj); break;
/* 3754 */               }  if (parameterObj instanceof Blob) {
/* 3755 */                 setBlob(parameterIndex, (Blob)parameterObj); break;
/*      */               } 
/* 3757 */               setBytes(parameterIndex, StringUtils.getBytes(parameterObj.toString(), this.charConverter, this.charEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor()));
/*      */               break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             case 91:
/*      */             case 93:
/* 3768 */               if (parameterObj instanceof String) {
/* 3769 */                 ParsePosition pp = new ParsePosition(0);
/*      */                 
/* 3771 */                 DateFormat sdf = TimeUtil.getSimpleDateFormat(null, getDateTimePattern((String)parameterObj, false), null, null);
/* 3772 */                 parameterAsDate = sdf.parse((String)parameterObj, pp);
/*      */               } else {
/* 3774 */                 parameterAsDate = (Date)parameterObj;
/*      */               } 
/*      */               
/* 3777 */               switch (targetSqlType) {
/*      */                 
/*      */                 case 91:
/* 3780 */                   if (parameterAsDate instanceof Date) {
/* 3781 */                     setDate(parameterIndex, (Date)parameterAsDate); break;
/*      */                   } 
/* 3783 */                   setDate(parameterIndex, new Date(parameterAsDate.getTime()));
/*      */                   break;
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 93:
/* 3790 */                   if (parameterAsDate instanceof Timestamp) {
/* 3791 */                     setTimestamp(parameterIndex, (Timestamp)parameterAsDate); break;
/*      */                   } 
/* 3793 */                   setTimestamp(parameterIndex, new Timestamp(parameterAsDate.getTime()));
/*      */                   break;
/*      */               } 
/*      */ 
/*      */ 
/*      */               
/*      */               break;
/*      */ 
/*      */             
/*      */             case 92:
/* 3803 */               if (parameterObj instanceof String) {
/* 3804 */                 DateFormat sdf = TimeUtil.getSimpleDateFormat(null, getDateTimePattern((String)parameterObj, true), null, null);
/* 3805 */                 setTime(parameterIndex, new Time(sdf.parse((String)parameterObj).getTime())); break;
/* 3806 */               }  if (parameterObj instanceof Timestamp) {
/* 3807 */                 Timestamp xT = (Timestamp)parameterObj;
/* 3808 */                 setTime(parameterIndex, new Time(xT.getTime())); break;
/*      */               } 
/* 3810 */               setTime(parameterIndex, (Time)parameterObj);
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             case 1111:
/* 3816 */               setSerializableObject(parameterIndex, parameterObj);
/*      */               break;
/*      */ 
/*      */             
/*      */             default:
/* 3821 */               throw SQLError.createSQLException(Messages.getString("PreparedStatement.16"), "S1000", getExceptionInterceptor());
/*      */           } 
/*      */         
/* 3824 */         } catch (Exception ex) {
/* 3825 */           if (ex instanceof SQLException) {
/* 3826 */             throw (SQLException)ex;
/*      */           }
/*      */           
/* 3829 */           SQLException sqlEx = SQLError.createSQLException(Messages.getString("PreparedStatement.17") + parameterObj.getClass().toString() + Messages.getString("PreparedStatement.18") + ex.getClass().getName() + Messages.getString("PreparedStatement.19") + ex.getMessage(), "S1000", getExceptionInterceptor());
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3834 */           sqlEx.initCause(ex);
/*      */           
/* 3836 */           throw sqlEx;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected int setOneBatchedParameterSet(PreparedStatement batchedStatement, int batchedParamIndex, Object paramSet) throws SQLException {
/* 3843 */     BatchParams paramArg = (BatchParams)paramSet;
/*      */     
/* 3845 */     boolean[] isNullBatch = paramArg.isNull;
/* 3846 */     boolean[] isStreamBatch = paramArg.isStream;
/*      */     
/* 3848 */     for (int j = 0; j < isNullBatch.length; j++) {
/* 3849 */       if (isNullBatch[j]) {
/* 3850 */         batchedStatement.setNull(batchedParamIndex++, 0);
/*      */       }
/* 3852 */       else if (isStreamBatch[j]) {
/* 3853 */         batchedStatement.setBinaryStream(batchedParamIndex++, paramArg.parameterStreams[j], paramArg.streamLengths[j]);
/*      */       } else {
/* 3855 */         ((PreparedStatement)batchedStatement).setBytesNoEscapeNoQuotes(batchedParamIndex++, paramArg.parameterStrings[j]);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 3860 */     return batchedParamIndex;
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
/*      */   public void setRef(int i, Ref x) throws SQLException {
/* 3876 */     throw SQLError.createSQLFeatureNotSupportedException();
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
/*      */   private final void setSerializableObject(int parameterIndex, Object parameterObj) throws SQLException {
/*      */     try {
/* 3890 */       ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
/* 3891 */       ObjectOutputStream objectOut = new ObjectOutputStream(bytesOut);
/* 3892 */       objectOut.writeObject(parameterObj);
/* 3893 */       objectOut.flush();
/* 3894 */       objectOut.close();
/* 3895 */       bytesOut.flush();
/* 3896 */       bytesOut.close();
/*      */       
/* 3898 */       byte[] buf = bytesOut.toByteArray();
/* 3899 */       ByteArrayInputStream bytesIn = new ByteArrayInputStream(buf);
/* 3900 */       setBinaryStream(parameterIndex, bytesIn, buf.length);
/* 3901 */       this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = -2;
/* 3902 */     } catch (Exception ex) {
/* 3903 */       SQLException sqlEx = SQLError.createSQLException(Messages.getString("PreparedStatement.54") + ex.getClass().getName(), "S1009", getExceptionInterceptor());
/*      */       
/* 3905 */       sqlEx.initCause(ex);
/*      */       
/* 3907 */       throw sqlEx;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setShort(int parameterIndex, short x) throws SQLException {
/* 3924 */     setInternal(parameterIndex, String.valueOf(x));
/*      */     
/* 3926 */     this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 5;
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
/*      */   public void setString(int parameterIndex, String x) throws SQLException {
/* 3943 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       
/* 3945 */       if (x == null) {
/* 3946 */         setNull(parameterIndex, 1);
/*      */       } else {
/* 3948 */         checkClosed();
/*      */         
/* 3950 */         int stringLength = x.length();
/*      */         
/* 3952 */         if (this.connection.isNoBackslashEscapesSet()) {
/*      */ 
/*      */           
/* 3955 */           boolean needsHexEscape = isEscapeNeededForString(x, stringLength);
/*      */           
/* 3957 */           if (!needsHexEscape) {
/* 3958 */             byte[] arrayOfByte = null;
/*      */             
/* 3960 */             StringBuilder quotedString = new StringBuilder(x.length() + 2);
/* 3961 */             quotedString.append('\'');
/* 3962 */             quotedString.append(x);
/* 3963 */             quotedString.append('\'');
/*      */             
/* 3965 */             if (!this.isLoadDataQuery) {
/* 3966 */               arrayOfByte = StringUtils.getBytes(quotedString.toString(), this.charConverter, this.charEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor());
/*      */             }
/*      */             else {
/*      */               
/* 3970 */               arrayOfByte = StringUtils.getBytes(quotedString.toString());
/*      */             } 
/*      */             
/* 3973 */             setInternal(parameterIndex, arrayOfByte);
/*      */           } else {
/* 3975 */             byte[] arrayOfByte = null;
/*      */             
/* 3977 */             if (!this.isLoadDataQuery) {
/* 3978 */               arrayOfByte = StringUtils.getBytes(x, this.charConverter, this.charEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor());
/*      */             }
/*      */             else {
/*      */               
/* 3982 */               arrayOfByte = StringUtils.getBytes(x);
/*      */             } 
/*      */             
/* 3985 */             setBytes(parameterIndex, arrayOfByte);
/*      */           } 
/*      */           
/*      */           return;
/*      */         } 
/*      */         
/* 3991 */         String parameterAsString = x;
/* 3992 */         boolean needsQuoted = true;
/*      */         
/* 3994 */         if (this.isLoadDataQuery || isEscapeNeededForString(x, stringLength)) {
/* 3995 */           needsQuoted = false;
/*      */           
/* 3997 */           StringBuilder buf = new StringBuilder((int)(x.length() * 1.1D));
/*      */           
/* 3999 */           buf.append('\'');
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 4005 */           for (int i = 0; i < stringLength; i++) {
/* 4006 */             char c = x.charAt(i);
/*      */             
/* 4008 */             switch (c) {
/*      */               case '\000':
/* 4010 */                 buf.append('\\');
/* 4011 */                 buf.append('0');
/*      */                 break;
/*      */ 
/*      */               
/*      */               case '\n':
/* 4016 */                 buf.append('\\');
/* 4017 */                 buf.append('n');
/*      */                 break;
/*      */ 
/*      */               
/*      */               case '\r':
/* 4022 */                 buf.append('\\');
/* 4023 */                 buf.append('r');
/*      */                 break;
/*      */ 
/*      */               
/*      */               case '\\':
/* 4028 */                 buf.append('\\');
/* 4029 */                 buf.append('\\');
/*      */                 break;
/*      */ 
/*      */               
/*      */               case '\'':
/* 4034 */                 buf.append('\\');
/* 4035 */                 buf.append('\'');
/*      */                 break;
/*      */ 
/*      */               
/*      */               case '"':
/* 4040 */                 if (this.usingAnsiMode) {
/* 4041 */                   buf.append('\\');
/*      */                 }
/*      */                 
/* 4044 */                 buf.append('"');
/*      */                 break;
/*      */ 
/*      */               
/*      */               case '\032':
/* 4049 */                 buf.append('\\');
/* 4050 */                 buf.append('Z');
/*      */                 break;
/*      */ 
/*      */ 
/*      */               
/*      */               case '¥':
/*      */               case '₩':
/* 4057 */                 if (this.charsetEncoder != null) {
/* 4058 */                   CharBuffer cbuf = CharBuffer.allocate(1);
/* 4059 */                   ByteBuffer bbuf = ByteBuffer.allocate(1);
/* 4060 */                   cbuf.put(c);
/* 4061 */                   cbuf.position(0);
/* 4062 */                   this.charsetEncoder.encode(cbuf, bbuf, true);
/* 4063 */                   if (bbuf.get(0) == 92) {
/* 4064 */                     buf.append('\\');
/*      */                   }
/*      */                 } 
/* 4067 */                 buf.append(c);
/*      */                 break;
/*      */               
/*      */               default:
/* 4071 */                 buf.append(c);
/*      */                 break;
/*      */             } 
/*      */           } 
/* 4075 */           buf.append('\'');
/*      */           
/* 4077 */           parameterAsString = buf.toString();
/*      */         } 
/*      */         
/* 4080 */         byte[] parameterAsBytes = null;
/*      */         
/* 4082 */         if (!this.isLoadDataQuery) {
/* 4083 */           if (needsQuoted) {
/* 4084 */             parameterAsBytes = StringUtils.getBytesWrapped(parameterAsString, '\'', '\'', this.charConverter, this.charEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor());
/*      */           } else {
/*      */             
/* 4087 */             parameterAsBytes = StringUtils.getBytes(parameterAsString, this.charConverter, this.charEncoding, this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor());
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 4092 */           parameterAsBytes = StringUtils.getBytes(parameterAsString);
/*      */         } 
/*      */         
/* 4095 */         setInternal(parameterIndex, parameterAsBytes);
/*      */         
/* 4097 */         this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 12;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean isEscapeNeededForString(String x, int stringLength) {
/* 4103 */     boolean needsHexEscape = false;
/*      */     
/* 4105 */     for (int i = 0; i < stringLength; i++) {
/* 4106 */       char c = x.charAt(i);
/*      */       
/* 4108 */       switch (c) {
/*      */         
/*      */         case '\000':
/* 4111 */           needsHexEscape = true;
/*      */           break;
/*      */         
/*      */         case '\n':
/* 4115 */           needsHexEscape = true;
/*      */           break;
/*      */ 
/*      */         
/*      */         case '\r':
/* 4120 */           needsHexEscape = true;
/*      */           break;
/*      */         
/*      */         case '\\':
/* 4124 */           needsHexEscape = true;
/*      */           break;
/*      */ 
/*      */         
/*      */         case '\'':
/* 4129 */           needsHexEscape = true;
/*      */           break;
/*      */ 
/*      */         
/*      */         case '"':
/* 4134 */           needsHexEscape = true;
/*      */           break;
/*      */ 
/*      */         
/*      */         case '\032':
/* 4139 */           needsHexEscape = true;
/*      */           break;
/*      */       } 
/*      */       
/* 4143 */       if (needsHexEscape) {
/*      */         break;
/*      */       }
/*      */     } 
/* 4147 */     return needsHexEscape;
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
/*      */   public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
/* 4165 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4166 */       setTimeInternal(parameterIndex, x, cal, cal.getTimeZone(), true);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTime(int parameterIndex, Time x) throws SQLException {
/* 4183 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4184 */       setTimeInternal(parameterIndex, x, (Calendar)null, this.connection.getDefaultTimeZone(), false);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setTimeInternal(int parameterIndex, Time x, Calendar targetCalendar, TimeZone tz, boolean rollForward) throws SQLException {
/* 4204 */     if (x == null) {
/* 4205 */       setNull(parameterIndex, 92);
/*      */     } else {
/* 4207 */       checkClosed();
/*      */       
/* 4209 */       if (!this.useLegacyDatetimeCode) {
/* 4210 */         newSetTimeInternal(parameterIndex, x, targetCalendar);
/*      */       } else {
/* 4212 */         Calendar sessionCalendar = getCalendarInstanceForSessionOrNew();
/*      */         
/* 4214 */         x = TimeUtil.changeTimezone(this.connection, sessionCalendar, targetCalendar, x, tz, this.connection.getServerTimezoneTZ(), rollForward);
/*      */         
/* 4216 */         setInternal(parameterIndex, "'" + x.toString() + "'");
/*      */       } 
/*      */       
/* 4219 */       this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 92;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
/* 4238 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4239 */       int fractLen = -1;
/* 4240 */       if (!this.sendFractionalSeconds || !this.serverSupportsFracSecs) {
/* 4241 */         fractLen = 0;
/* 4242 */       } else if (this.parameterMetaData != null && this.parameterMetaData.metadata != null && this.parameterMetaData.metadata.fields != null && parameterIndex <= this.parameterMetaData.metadata.fields.length && parameterIndex >= 0 && this.parameterMetaData.metadata.getField(parameterIndex).getDecimals() > 0) {
/*      */ 
/*      */         
/* 4245 */         fractLen = this.parameterMetaData.metadata.getField(parameterIndex).getDecimals();
/*      */       } 
/*      */       
/* 4248 */       setTimestampInternal(parameterIndex, x, cal, cal.getTimeZone(), true, fractLen, this.connection.getUseSSPSCompatibleTimezoneShift());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
/* 4265 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4266 */       int fractLen = -1;
/* 4267 */       if (!this.sendFractionalSeconds || !this.serverSupportsFracSecs) {
/* 4268 */         fractLen = 0;
/* 4269 */       } else if (this.parameterMetaData != null && this.parameterMetaData.metadata != null && this.parameterMetaData.metadata.fields != null && parameterIndex <= this.parameterMetaData.metadata.fields.length && parameterIndex >= 0) {
/*      */         
/* 4271 */         fractLen = this.parameterMetaData.metadata.getField(parameterIndex).getDecimals();
/*      */       } 
/*      */       
/* 4274 */       setTimestampInternal(parameterIndex, x, (Calendar)null, this.connection.getDefaultTimeZone(), false, fractLen, this.connection.getUseSSPSCompatibleTimezoneShift());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setTimestampInternal(int parameterIndex, Timestamp x, Calendar targetCalendar, TimeZone tz, boolean rollForward, int fractionalLength, boolean useSSPSCompatibleTimezoneShift) throws SQLException {
/* 4295 */     if (x == null) {
/* 4296 */       setNull(parameterIndex, 93);
/*      */     } else {
/* 4298 */       checkClosed();
/*      */       
/* 4300 */       x = (Timestamp)x.clone();
/*      */       
/* 4302 */       if (!this.serverSupportsFracSecs || (!this.sendFractionalSeconds && fractionalLength == 0)) {
/* 4303 */         x = TimeUtil.truncateFractionalSeconds(x);
/*      */       }
/*      */       
/* 4306 */       if (fractionalLength < 0)
/*      */       {
/* 4308 */         fractionalLength = 6;
/*      */       }
/*      */       
/* 4311 */       x = TimeUtil.adjustTimestampNanosPrecision(x, fractionalLength, !this.connection.isServerTruncatesFracSecs());
/*      */       
/* 4313 */       if (!this.useLegacyDatetimeCode) {
/* 4314 */         newSetTimestampInternal(parameterIndex, x, targetCalendar);
/*      */       } else {
/* 4316 */         Calendar sessionCalendar = this.connection.getUseJDBCCompliantTimezoneShift() ? this.connection.getUtcCalendar() : getCalendarInstanceForSessionOrNew();
/*      */ 
/*      */ 
/*      */         
/* 4320 */         sessionCalendar = TimeUtil.setProlepticIfNeeded(sessionCalendar, targetCalendar);
/*      */         
/* 4322 */         x = TimeUtil.changeTimezone(this.connection, sessionCalendar, targetCalendar, x, tz, this.connection.getServerTimezoneTZ(), rollForward);
/*      */         
/* 4324 */         if (useSSPSCompatibleTimezoneShift) {
/* 4325 */           doSSPSCompatibleTimezoneShift(parameterIndex, x, fractionalLength, targetCalendar);
/*      */         } else {
/* 4327 */           synchronized (this) {
/*      */             
/* 4329 */             this.tsdf = TimeUtil.getSimpleDateFormat(this.tsdf, "''yyyy-MM-dd HH:mm:ss", null, null);
/*      */             
/* 4331 */             Calendar adjCal = TimeUtil.setProlepticIfNeeded(this.tsdf.getCalendar(), targetCalendar);
/* 4332 */             if (this.tsdf.getCalendar() != adjCal) {
/* 4333 */               this.tsdf.setCalendar(adjCal);
/*      */             }
/*      */             
/* 4336 */             StringBuffer buf = new StringBuffer();
/* 4337 */             buf.append(this.tsdf.format(x));
/*      */             
/* 4339 */             if (fractionalLength > 0) {
/* 4340 */               int nanos = x.getNanos();
/*      */               
/* 4342 */               if (nanos != 0) {
/* 4343 */                 buf.append('.');
/* 4344 */                 buf.append(TimeUtil.formatNanos(nanos, this.serverSupportsFracSecs, fractionalLength));
/*      */               } 
/*      */             } 
/*      */             
/* 4348 */             buf.append('\'');
/*      */             
/* 4350 */             setInternal(parameterIndex, buf.toString());
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 4356 */       this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 93;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void newSetTimestampInternal(int parameterIndex, Timestamp x, Calendar targetCalendar) throws SQLException {
/* 4361 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4362 */       this.tsdf = TimeUtil.getSimpleDateFormat(this.tsdf, "''yyyy-MM-dd HH:mm:ss", targetCalendar, (targetCalendar != null) ? null : this.connection.getServerTimezoneTZ());
/*      */ 
/*      */       
/* 4365 */       StringBuffer buf = new StringBuffer();
/* 4366 */       buf.append(this.tsdf.format(x));
/* 4367 */       buf.append('.');
/* 4368 */       buf.append(TimeUtil.formatNanos(x.getNanos(), this.serverSupportsFracSecs, 6));
/* 4369 */       buf.append('\'');
/*      */       
/* 4371 */       setInternal(parameterIndex, buf.toString());
/*      */     } 
/*      */   }
/*      */   
/*      */   private void newSetTimeInternal(int parameterIndex, Time x, Calendar targetCalendar) throws SQLException {
/* 4376 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4377 */       this.tdf = TimeUtil.getSimpleDateFormat(this.tdf, "''HH:mm:ss''", targetCalendar, (targetCalendar != null) ? null : this.connection.getServerTimezoneTZ());
/*      */ 
/*      */       
/* 4380 */       setInternal(parameterIndex, this.tdf.format(x));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void newSetDateInternal(int parameterIndex, Date x, Calendar targetCalendar) throws SQLException {
/* 4385 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4386 */       this.ddf = TimeUtil.getSimpleDateFormat(this.ddf, "''yyyy-MM-dd''", targetCalendar, (targetCalendar != null) ? null : (this.connection.getNoTimezoneConversionForDateType() ? this.connection.getDefaultTimeZone() : this.connection.getServerTimezoneTZ()));
/*      */ 
/*      */       
/* 4389 */       setInternal(parameterIndex, this.ddf.format(x));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doSSPSCompatibleTimezoneShift(int parameterIndex, Timestamp x, int fractionalLength, Calendar targetCalendar) throws SQLException {
/* 4394 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4395 */       Calendar sessionCalendar2 = this.connection.getUseJDBCCompliantTimezoneShift() ? this.connection.getUtcCalendar() : getCalendarInstanceForSessionOrNew();
/*      */ 
/*      */ 
/*      */       
/* 4399 */       sessionCalendar2 = TimeUtil.setProlepticIfNeeded(sessionCalendar2, targetCalendar);
/*      */       
/* 4401 */       synchronized (sessionCalendar2) {
/* 4402 */         Date oldTime = sessionCalendar2.getTime();
/*      */         
/*      */         try {
/* 4405 */           sessionCalendar2.setTime(x);
/*      */           
/* 4407 */           int year = sessionCalendar2.get(1);
/* 4408 */           int month = sessionCalendar2.get(2) + 1;
/* 4409 */           int date = sessionCalendar2.get(5);
/*      */           
/* 4411 */           int hour = sessionCalendar2.get(11);
/* 4412 */           int minute = sessionCalendar2.get(12);
/* 4413 */           int seconds = sessionCalendar2.get(13);
/*      */           
/* 4415 */           StringBuilder tsBuf = new StringBuilder();
/*      */           
/* 4417 */           tsBuf.append('\'');
/* 4418 */           tsBuf.append(year);
/*      */           
/* 4420 */           tsBuf.append("-");
/*      */           
/* 4422 */           if (month < 10) {
/* 4423 */             tsBuf.append('0');
/*      */           }
/*      */           
/* 4426 */           tsBuf.append(month);
/*      */           
/* 4428 */           tsBuf.append('-');
/*      */           
/* 4430 */           if (date < 10) {
/* 4431 */             tsBuf.append('0');
/*      */           }
/*      */           
/* 4434 */           tsBuf.append(date);
/*      */           
/* 4436 */           tsBuf.append(' ');
/*      */           
/* 4438 */           if (hour < 10) {
/* 4439 */             tsBuf.append('0');
/*      */           }
/*      */           
/* 4442 */           tsBuf.append(hour);
/*      */           
/* 4444 */           tsBuf.append(':');
/*      */           
/* 4446 */           if (minute < 10) {
/* 4447 */             tsBuf.append('0');
/*      */           }
/*      */           
/* 4450 */           tsBuf.append(minute);
/*      */           
/* 4452 */           tsBuf.append(':');
/*      */           
/* 4454 */           if (seconds < 10) {
/* 4455 */             tsBuf.append('0');
/*      */           }
/*      */           
/* 4458 */           tsBuf.append(seconds);
/*      */           
/* 4460 */           tsBuf.append('.');
/* 4461 */           tsBuf.append(TimeUtil.formatNanos(x.getNanos(), this.serverSupportsFracSecs, fractionalLength));
/* 4462 */           tsBuf.append('\'');
/*      */           
/* 4464 */           setInternal(parameterIndex, tsBuf.toString());
/*      */         } finally {
/*      */           
/* 4467 */           sessionCalendar2.setTime(oldTime);
/*      */         } 
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
/* 4498 */     if (x == null) {
/* 4499 */       setNull(parameterIndex, 12);
/*      */     } else {
/* 4501 */       setBinaryStream(parameterIndex, x, length);
/*      */       
/* 4503 */       this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 2005;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setURL(int parameterIndex, URL arg) throws SQLException {
/* 4511 */     if (arg != null) {
/* 4512 */       setString(parameterIndex, arg.toString());
/*      */       
/* 4514 */       this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 70;
/*      */     } else {
/* 4516 */       setNull(parameterIndex, 1);
/*      */     } 
/*      */   }
/*      */   
/*      */   private final void streamToBytes(Buffer packet, InputStream in, boolean escape, int streamLength, boolean useLength) throws SQLException {
/* 4521 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       try {
/* 4523 */         if (this.streamConvertBuf == null) {
/* 4524 */           this.streamConvertBuf = new byte[4096];
/*      */         }
/*      */         
/* 4527 */         String connectionEncoding = this.connection.getEncoding();
/*      */         
/* 4529 */         boolean hexEscape = false;
/*      */         
/*      */         try {
/* 4532 */           if (this.connection.isNoBackslashEscapesSet() || (this.connection.getUseUnicode() && connectionEncoding != null && CharsetMapping.isMultibyteCharset(connectionEncoding) && !this.connection.parserKnowsUnicode()))
/*      */           {
/* 4534 */             hexEscape = true;
/*      */           }
/* 4536 */         } catch (RuntimeException ex) {
/* 4537 */           SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 4538 */           sqlEx.initCause(ex);
/* 4539 */           throw sqlEx;
/*      */         } 
/*      */         
/* 4542 */         if (streamLength == -1) {
/* 4543 */           useLength = false;
/*      */         }
/*      */         
/* 4546 */         int bc = -1;
/*      */         
/* 4548 */         if (useLength) {
/* 4549 */           bc = readblock(in, this.streamConvertBuf, streamLength);
/*      */         } else {
/* 4551 */           bc = readblock(in, this.streamConvertBuf);
/*      */         } 
/*      */         
/* 4554 */         int lengthLeftToRead = streamLength - bc;
/*      */         
/* 4556 */         if (hexEscape) {
/* 4557 */           packet.writeStringNoNull("x");
/* 4558 */         } else if (this.connection.getIO().versionMeetsMinimum(4, 1, 0)) {
/* 4559 */           packet.writeStringNoNull("_binary");
/*      */         } 
/*      */         
/* 4562 */         if (escape) {
/* 4563 */           packet.writeByte((byte)39);
/*      */         }
/*      */         
/* 4566 */         while (bc > 0) {
/* 4567 */           if (hexEscape) {
/* 4568 */             hexEscapeBlock(this.streamConvertBuf, packet, bc);
/* 4569 */           } else if (escape) {
/* 4570 */             escapeblockFast(this.streamConvertBuf, packet, bc);
/*      */           } else {
/* 4572 */             packet.writeBytesNoNull(this.streamConvertBuf, 0, bc);
/*      */           } 
/*      */           
/* 4575 */           if (useLength) {
/* 4576 */             bc = readblock(in, this.streamConvertBuf, lengthLeftToRead);
/*      */             
/* 4578 */             if (bc > 0)
/* 4579 */               lengthLeftToRead -= bc; 
/*      */             continue;
/*      */           } 
/* 4582 */           bc = readblock(in, this.streamConvertBuf);
/*      */         } 
/*      */ 
/*      */         
/* 4586 */         if (escape) {
/* 4587 */           packet.writeByte((byte)39);
/*      */         }
/*      */       } finally {
/* 4590 */         if (this.connection.getAutoClosePStmtStreams()) {
/*      */           try {
/* 4592 */             in.close();
/* 4593 */           } catch (IOException ioEx) {}
/*      */ 
/*      */           
/* 4596 */           in = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private final byte[] streamToBytes(InputStream in, boolean escape, int streamLength, boolean useLength) throws SQLException {
/* 4603 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4604 */       in.mark(2147483647);
/*      */       try {
/* 4606 */         if (this.streamConvertBuf == null) {
/* 4607 */           this.streamConvertBuf = new byte[4096];
/*      */         }
/* 4609 */         if (streamLength == -1) {
/* 4610 */           useLength = false;
/*      */         }
/*      */         
/* 4613 */         ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
/*      */         
/* 4615 */         int bc = -1;
/*      */         
/* 4617 */         if (useLength) {
/* 4618 */           bc = readblock(in, this.streamConvertBuf, streamLength);
/*      */         } else {
/* 4620 */           bc = readblock(in, this.streamConvertBuf);
/*      */         } 
/*      */         
/* 4623 */         int lengthLeftToRead = streamLength - bc;
/*      */         
/* 4625 */         if (escape) {
/* 4626 */           if (this.connection.versionMeetsMinimum(4, 1, 0)) {
/* 4627 */             bytesOut.write(95);
/* 4628 */             bytesOut.write(98);
/* 4629 */             bytesOut.write(105);
/* 4630 */             bytesOut.write(110);
/* 4631 */             bytesOut.write(97);
/* 4632 */             bytesOut.write(114);
/* 4633 */             bytesOut.write(121);
/*      */           } 
/*      */           
/* 4636 */           bytesOut.write(39);
/*      */         } 
/*      */         
/* 4639 */         while (bc > 0) {
/* 4640 */           if (escape) {
/* 4641 */             escapeblockFast(this.streamConvertBuf, bytesOut, bc);
/*      */           } else {
/* 4643 */             bytesOut.write(this.streamConvertBuf, 0, bc);
/*      */           } 
/*      */           
/* 4646 */           if (useLength) {
/* 4647 */             bc = readblock(in, this.streamConvertBuf, lengthLeftToRead);
/*      */             
/* 4649 */             if (bc > 0)
/* 4650 */               lengthLeftToRead -= bc; 
/*      */             continue;
/*      */           } 
/* 4653 */           bc = readblock(in, this.streamConvertBuf);
/*      */         } 
/*      */ 
/*      */         
/* 4657 */         if (escape) {
/* 4658 */           bytesOut.write(39);
/*      */         }
/*      */         
/* 4661 */         return bytesOut.toByteArray();
/*      */       } finally {
/*      */         try {
/* 4664 */           in.reset();
/* 4665 */         } catch (IOException e) {}
/*      */         
/* 4667 */         if (this.connection.getAutoClosePStmtStreams()) {
/*      */           try {
/* 4669 */             in.close();
/* 4670 */           } catch (IOException ioEx) {}
/*      */ 
/*      */           
/* 4673 */           in = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/* 4686 */     StringBuilder buf = new StringBuilder();
/* 4687 */     buf.append(super.toString());
/* 4688 */     buf.append(": ");
/*      */     
/*      */     try {
/* 4691 */       buf.append(asSql());
/* 4692 */     } catch (SQLException sqlEx) {
/* 4693 */       buf.append("EXCEPTION: " + sqlEx.toString());
/*      */     } 
/*      */     
/* 4696 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getParameterIndexOffset() {
/* 4706 */     return 0;
/*      */   }
/*      */   
/*      */   public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
/* 4710 */     setAsciiStream(parameterIndex, x, -1);
/*      */   }
/*      */   
/*      */   public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
/* 4714 */     setAsciiStream(parameterIndex, x, (int)length);
/* 4715 */     this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 2005;
/*      */   }
/*      */   
/*      */   public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
/* 4719 */     setBinaryStream(parameterIndex, x, -1);
/*      */   }
/*      */   
/*      */   public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
/* 4723 */     setBinaryStream(parameterIndex, x, (int)length);
/*      */   }
/*      */   
/*      */   public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
/* 4727 */     setBinaryStream(parameterIndex, inputStream);
/*      */   }
/*      */   
/*      */   public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
/* 4731 */     setCharacterStream(parameterIndex, reader, -1);
/*      */   }
/*      */   
/*      */   public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
/* 4735 */     setCharacterStream(parameterIndex, reader, (int)length);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClob(int parameterIndex, Reader reader) throws SQLException {
/* 4740 */     setCharacterStream(parameterIndex, reader);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
/* 4745 */     setCharacterStream(parameterIndex, reader, length);
/*      */   }
/*      */   
/*      */   public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
/* 4749 */     setNCharacterStream(parameterIndex, value, -1L);
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
/*      */   public void setNString(int parameterIndex, String x) throws SQLException {
/* 4767 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4768 */       if (this.charEncoding.equalsIgnoreCase("UTF-8") || this.charEncoding.equalsIgnoreCase("utf8")) {
/* 4769 */         setString(parameterIndex, x);
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/* 4774 */       if (x == null) {
/* 4775 */         setNull(parameterIndex, 1);
/*      */       } else {
/* 4777 */         int stringLength = x.length();
/*      */ 
/*      */ 
/*      */         
/* 4781 */         StringBuilder buf = new StringBuilder((int)(x.length() * 1.1D + 4.0D));
/* 4782 */         buf.append("_utf8");
/* 4783 */         buf.append('\'');
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4789 */         for (int i = 0; i < stringLength; i++) {
/* 4790 */           char c = x.charAt(i);
/*      */           
/* 4792 */           switch (c) {
/*      */             case '\000':
/* 4794 */               buf.append('\\');
/* 4795 */               buf.append('0');
/*      */               break;
/*      */ 
/*      */             
/*      */             case '\n':
/* 4800 */               buf.append('\\');
/* 4801 */               buf.append('n');
/*      */               break;
/*      */ 
/*      */             
/*      */             case '\r':
/* 4806 */               buf.append('\\');
/* 4807 */               buf.append('r');
/*      */               break;
/*      */ 
/*      */             
/*      */             case '\\':
/* 4812 */               buf.append('\\');
/* 4813 */               buf.append('\\');
/*      */               break;
/*      */ 
/*      */             
/*      */             case '\'':
/* 4818 */               buf.append('\\');
/* 4819 */               buf.append('\'');
/*      */               break;
/*      */ 
/*      */             
/*      */             case '"':
/* 4824 */               if (this.usingAnsiMode) {
/* 4825 */                 buf.append('\\');
/*      */               }
/*      */               
/* 4828 */               buf.append('"');
/*      */               break;
/*      */ 
/*      */             
/*      */             case '\032':
/* 4833 */               buf.append('\\');
/* 4834 */               buf.append('Z');
/*      */               break;
/*      */ 
/*      */             
/*      */             default:
/* 4839 */               buf.append(c);
/*      */               break;
/*      */           } 
/*      */         } 
/* 4843 */         buf.append('\'');
/*      */         
/* 4845 */         String parameterAsString = buf.toString();
/*      */         
/* 4847 */         byte[] parameterAsBytes = null;
/*      */         
/* 4849 */         if (!this.isLoadDataQuery) {
/* 4850 */           parameterAsBytes = StringUtils.getBytes(parameterAsString, this.connection.getCharsetConverter("UTF-8"), "UTF-8", this.connection.getServerCharset(), this.connection.parserKnowsUnicode(), getExceptionInterceptor());
/*      */         }
/*      */         else {
/*      */           
/* 4854 */           parameterAsBytes = StringUtils.getBytes(parameterAsString);
/*      */         } 
/*      */         
/* 4857 */         setInternal(parameterIndex, parameterAsBytes);
/*      */         
/* 4859 */         this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = -9;
/*      */       } 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
/* 4886 */     synchronized (checkClosed().getConnectionMutex()) {
/*      */       try {
/* 4888 */         if (reader == null) {
/* 4889 */           setNull(parameterIndex, -1);
/*      */         } else {
/*      */           
/* 4892 */           char[] c = null;
/* 4893 */           int len = 0;
/*      */           
/* 4895 */           boolean useLength = this.connection.getUseStreamLengthsInPrepStmts();
/*      */ 
/*      */ 
/*      */           
/* 4899 */           if (useLength && length != -1L) {
/* 4900 */             c = new char[(int)length];
/*      */             
/* 4902 */             int numCharsRead = readFully(reader, c, (int)length);
/* 4903 */             setNString(parameterIndex, new String(c, 0, numCharsRead));
/*      */           } else {
/*      */             
/* 4906 */             c = new char[4096];
/*      */             
/* 4908 */             StringBuilder buf = new StringBuilder();
/*      */             
/* 4910 */             while ((len = reader.read(c)) != -1) {
/* 4911 */               buf.append(c, 0, len);
/*      */             }
/*      */             
/* 4914 */             setNString(parameterIndex, buf.toString());
/*      */           } 
/*      */           
/* 4917 */           this.parameterTypes[parameterIndex - 1 + getParameterIndexOffset()] = 2011;
/*      */         } 
/* 4919 */       } catch (IOException ioEx) {
/* 4920 */         throw SQLError.createSQLException(ioEx.toString(), "S1000", getExceptionInterceptor());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setNClob(int parameterIndex, Reader reader) throws SQLException {
/* 4926 */     setNCharacterStream(parameterIndex, reader);
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
/*      */   public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
/* 4943 */     if (reader == null) {
/* 4944 */       setNull(parameterIndex, -1);
/*      */     } else {
/* 4946 */       setNCharacterStream(parameterIndex, reader, length);
/*      */     } 
/*      */   }
/*      */   
/*      */   public ParameterBindings getParameterBindings() throws SQLException {
/* 4951 */     synchronized (checkClosed().getConnectionMutex()) {
/* 4952 */       return new EmulatedPreparedStatementBindings();
/*      */     } 
/*      */   }
/*      */   
/*      */   class EmulatedPreparedStatementBindings
/*      */     implements ParameterBindings {
/*      */     private ResultSetImpl bindingsAsRs;
/*      */     private boolean[] parameterIsNull;
/*      */     
/*      */     EmulatedPreparedStatementBindings() throws SQLException {
/* 4962 */       List<ResultSetRow> rows = new ArrayList<ResultSetRow>();
/* 4963 */       this.parameterIsNull = new boolean[PreparedStatement.this.parameterCount];
/* 4964 */       System.arraycopy(PreparedStatement.this.isNull, 0, this.parameterIsNull, 0, PreparedStatement.this.parameterCount);
/* 4965 */       byte[][] rowData = new byte[PreparedStatement.this.parameterCount][];
/* 4966 */       Field[] typeMetadata = new Field[PreparedStatement.this.parameterCount];
/*      */       
/* 4968 */       for (int i = 0; i < PreparedStatement.this.parameterCount; i++) {
/* 4969 */         if (PreparedStatement.this.batchCommandIndex == -1) {
/* 4970 */           rowData[i] = PreparedStatement.this.getBytesRepresentation(i);
/*      */         } else {
/* 4972 */           rowData[i] = PreparedStatement.this.getBytesRepresentationForBatch(i, PreparedStatement.this.batchCommandIndex);
/*      */         } 
/*      */         
/* 4975 */         int charsetIndex = 0;
/*      */         
/* 4977 */         if (PreparedStatement.this.parameterTypes[i] == -2 || PreparedStatement.this.parameterTypes[i] == 2004) {
/* 4978 */           charsetIndex = 63;
/*      */         } else {
/*      */           try {
/* 4981 */             charsetIndex = CharsetMapping.getCollationIndexForJavaEncoding(PreparedStatement.this.connection.getEncoding(), PreparedStatement.this.connection);
/*      */           }
/* 4983 */           catch (SQLException ex) {
/* 4984 */             throw ex;
/* 4985 */           } catch (RuntimeException ex) {
/* 4986 */             SQLException sqlEx = SQLError.createSQLException(ex.toString(), "S1009", (ExceptionInterceptor)null);
/* 4987 */             sqlEx.initCause(ex);
/* 4988 */             throw sqlEx;
/*      */           } 
/*      */         } 
/*      */         
/* 4992 */         Field parameterMetadata = new Field(null, "parameter_" + (i + 1), charsetIndex, PreparedStatement.this.parameterTypes[i], (rowData[i]).length);
/* 4993 */         parameterMetadata.setConnection(PreparedStatement.this.connection);
/* 4994 */         typeMetadata[i] = parameterMetadata;
/*      */       } 
/*      */       
/* 4997 */       rows.add(new ByteArrayRow(rowData, PreparedStatement.this.getExceptionInterceptor()));
/*      */       
/* 4999 */       this.bindingsAsRs = new ResultSetImpl(PreparedStatement.this.connection.getCatalog(), typeMetadata, new RowDataStatic(rows), PreparedStatement.this.connection, null);
/*      */       
/* 5001 */       this.bindingsAsRs.next();
/*      */     }
/*      */     
/*      */     public Array getArray(int parameterIndex) throws SQLException {
/* 5005 */       return this.bindingsAsRs.getArray(parameterIndex);
/*      */     }
/*      */     
/*      */     public InputStream getAsciiStream(int parameterIndex) throws SQLException {
/* 5009 */       return this.bindingsAsRs.getAsciiStream(parameterIndex);
/*      */     }
/*      */     
/*      */     public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
/* 5013 */       return this.bindingsAsRs.getBigDecimal(parameterIndex);
/*      */     }
/*      */     
/*      */     public InputStream getBinaryStream(int parameterIndex) throws SQLException {
/* 5017 */       return this.bindingsAsRs.getBinaryStream(parameterIndex);
/*      */     }
/*      */     
/*      */     public Blob getBlob(int parameterIndex) throws SQLException {
/* 5021 */       return this.bindingsAsRs.getBlob(parameterIndex);
/*      */     }
/*      */     
/*      */     public boolean getBoolean(int parameterIndex) throws SQLException {
/* 5025 */       return this.bindingsAsRs.getBoolean(parameterIndex);
/*      */     }
/*      */     
/*      */     public byte getByte(int parameterIndex) throws SQLException {
/* 5029 */       return this.bindingsAsRs.getByte(parameterIndex);
/*      */     }
/*      */     
/*      */     public byte[] getBytes(int parameterIndex) throws SQLException {
/* 5033 */       return this.bindingsAsRs.getBytes(parameterIndex);
/*      */     }
/*      */     
/*      */     public Reader getCharacterStream(int parameterIndex) throws SQLException {
/* 5037 */       return this.bindingsAsRs.getCharacterStream(parameterIndex);
/*      */     }
/*      */     
/*      */     public Clob getClob(int parameterIndex) throws SQLException {
/* 5041 */       return this.bindingsAsRs.getClob(parameterIndex);
/*      */     }
/*      */     
/*      */     public Date getDate(int parameterIndex) throws SQLException {
/* 5045 */       return this.bindingsAsRs.getDate(parameterIndex);
/*      */     }
/*      */     
/*      */     public double getDouble(int parameterIndex) throws SQLException {
/* 5049 */       return this.bindingsAsRs.getDouble(parameterIndex);
/*      */     }
/*      */     
/*      */     public float getFloat(int parameterIndex) throws SQLException {
/* 5053 */       return this.bindingsAsRs.getFloat(parameterIndex);
/*      */     }
/*      */     
/*      */     public int getInt(int parameterIndex) throws SQLException {
/* 5057 */       return this.bindingsAsRs.getInt(parameterIndex);
/*      */     }
/*      */     
/*      */     public long getLong(int parameterIndex) throws SQLException {
/* 5061 */       return this.bindingsAsRs.getLong(parameterIndex);
/*      */     }
/*      */     
/*      */     public Reader getNCharacterStream(int parameterIndex) throws SQLException {
/* 5065 */       return this.bindingsAsRs.getCharacterStream(parameterIndex);
/*      */     }
/*      */     
/*      */     public Reader getNClob(int parameterIndex) throws SQLException {
/* 5069 */       return this.bindingsAsRs.getCharacterStream(parameterIndex);
/*      */     }
/*      */     
/*      */     public Object getObject(int parameterIndex) throws SQLException {
/* 5073 */       PreparedStatement.this.checkBounds(parameterIndex, 0);
/*      */       
/* 5075 */       if (this.parameterIsNull[parameterIndex - 1]) {
/* 5076 */         return null;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 5081 */       switch (PreparedStatement.this.parameterTypes[parameterIndex - 1]) {
/*      */         case -6:
/* 5083 */           return Byte.valueOf(getByte(parameterIndex));
/*      */         case 5:
/* 5085 */           return Short.valueOf(getShort(parameterIndex));
/*      */         case 4:
/* 5087 */           return Integer.valueOf(getInt(parameterIndex));
/*      */         case -5:
/* 5089 */           return Long.valueOf(getLong(parameterIndex));
/*      */         case 6:
/* 5091 */           return Float.valueOf(getFloat(parameterIndex));
/*      */         case 8:
/* 5093 */           return Double.valueOf(getDouble(parameterIndex));
/*      */       } 
/* 5095 */       return this.bindingsAsRs.getObject(parameterIndex);
/*      */     }
/*      */ 
/*      */     
/*      */     public Ref getRef(int parameterIndex) throws SQLException {
/* 5100 */       return this.bindingsAsRs.getRef(parameterIndex);
/*      */     }
/*      */     
/*      */     public short getShort(int parameterIndex) throws SQLException {
/* 5104 */       return this.bindingsAsRs.getShort(parameterIndex);
/*      */     }
/*      */     
/*      */     public String getString(int parameterIndex) throws SQLException {
/* 5108 */       return this.bindingsAsRs.getString(parameterIndex);
/*      */     }
/*      */     
/*      */     public Time getTime(int parameterIndex) throws SQLException {
/* 5112 */       return this.bindingsAsRs.getTime(parameterIndex);
/*      */     }
/*      */     
/*      */     public Timestamp getTimestamp(int parameterIndex) throws SQLException {
/* 5116 */       return this.bindingsAsRs.getTimestamp(parameterIndex);
/*      */     }
/*      */     
/*      */     public URL getURL(int parameterIndex) throws SQLException {
/* 5120 */       return this.bindingsAsRs.getURL(parameterIndex);
/*      */     }
/*      */     
/*      */     public boolean isNull(int parameterIndex) throws SQLException {
/* 5124 */       PreparedStatement.this.checkBounds(parameterIndex, 0);
/*      */       
/* 5126 */       return this.parameterIsNull[parameterIndex - 1];
/*      */     }
/*      */   }
/*      */   
/*      */   public String getPreparedSql() {
/*      */     try {
/* 5132 */       synchronized (checkClosed().getConnectionMutex()) {
/* 5133 */         if (this.rewrittenBatchSize == 0) {
/* 5134 */           return this.originalSql;
/*      */         }
/*      */         
/*      */         try {
/* 5138 */           return this.parseInfo.getSqlForBatch(this.parseInfo);
/* 5139 */         } catch (UnsupportedEncodingException e) {
/* 5140 */           throw new RuntimeException(e);
/*      */         } 
/*      */       } 
/* 5143 */     } catch (SQLException e) {
/* 5144 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getUpdateCount() throws SQLException {
/* 5150 */     int count = super.getUpdateCount();
/*      */     
/* 5152 */     if (containsOnDuplicateKeyUpdateInSQL() && this.compensateForOnDuplicateKeyUpdate && (
/* 5153 */       count == 2 || count == 0)) {
/* 5154 */       count = 1;
/*      */     }
/*      */ 
/*      */     
/* 5158 */     return count;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static boolean canRewrite(String sql, boolean isOnDuplicateKeyUpdate, int locationOfOnDuplicateKeyUpdate, int statementStartPos) {
/* 5165 */     if (StringUtils.startsWithIgnoreCaseAndWs(sql, "INSERT", statementStartPos)) {
/* 5166 */       if (StringUtils.indexOfIgnoreCase(statementStartPos, sql, "SELECT", "\"'`", "\"'`", StringUtils.SEARCH_MODE__MRK_COM_WS) != -1) {
/* 5167 */         return false;
/*      */       }
/* 5169 */       if (isOnDuplicateKeyUpdate) {
/* 5170 */         int updateClausePos = StringUtils.indexOfIgnoreCase(locationOfOnDuplicateKeyUpdate, sql, " UPDATE ");
/* 5171 */         if (updateClausePos != -1) {
/* 5172 */           return (StringUtils.indexOfIgnoreCase(updateClausePos, sql, "LAST_INSERT_ID", "\"'`", "\"'`", StringUtils.SEARCH_MODE__MRK_COM_WS) == -1);
/*      */         }
/*      */       } 
/* 5175 */       return true;
/*      */     } 
/*      */     
/* 5178 */     return (StringUtils.startsWithIgnoreCaseAndWs(sql, "REPLACE", statementStartPos) && StringUtils.indexOfIgnoreCase(statementStartPos, sql, "SELECT", "\"'`", "\"'`", StringUtils.SEARCH_MODE__MRK_COM_WS) == -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long executeLargeUpdate() throws SQLException {
/* 5187 */     return executeUpdateInternal(true, false);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\PreparedStatement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */