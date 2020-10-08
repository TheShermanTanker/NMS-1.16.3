/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.nio.charset.Charset;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
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
/*     */ public class Util
/*     */ {
/*     */   class RandStructcture
/*     */   {
/*     */     long maxValue;
/*     */     double maxValueDbl;
/*     */     long seed1;
/*     */     long seed2;
/*     */   }
/*  60 */   private static Util enclosingInstance = new Util();
/*     */   
/*     */   private static boolean isJdbc4;
/*     */   
/*     */   private static boolean isJdbc42;
/*     */   
/*  66 */   private static int jvmVersion = -1;
/*     */   
/*  68 */   private static int jvmUpdateNumber = -1;
/*     */   
/*     */   private static boolean isColdFusion = false;
/*     */   
/*     */   static {
/*     */     try {
/*  74 */       Class.forName("java.sql.NClob");
/*  75 */       isJdbc4 = true;
/*  76 */     } catch (ClassNotFoundException e) {
/*  77 */       isJdbc4 = false;
/*     */     } 
/*     */     
/*     */     try {
/*  81 */       Class.forName("java.sql.JDBCType");
/*  82 */       isJdbc42 = true;
/*  83 */     } catch (Throwable t) {
/*  84 */       isJdbc42 = false;
/*     */     } 
/*     */     
/*  87 */     String jvmVersionString = System.getProperty("java.version");
/*  88 */     int startPos = jvmVersionString.indexOf('.');
/*  89 */     int endPos = startPos + 1;
/*  90 */     if (startPos != -1) {
/*  91 */       while (Character.isDigit(jvmVersionString.charAt(endPos)) && ++endPos < jvmVersionString.length());
/*     */     }
/*     */ 
/*     */     
/*  95 */     startPos++;
/*  96 */     if (endPos > startPos) {
/*  97 */       jvmVersion = Integer.parseInt(jvmVersionString.substring(startPos, endPos));
/*     */     } else {
/*     */       
/* 100 */       jvmVersion = isJdbc42 ? 8 : (isJdbc4 ? 6 : 5);
/*     */     } 
/* 102 */     startPos = jvmVersionString.indexOf("_");
/* 103 */     endPos = startPos + 1;
/* 104 */     if (startPos != -1) {
/* 105 */       while (Character.isDigit(jvmVersionString.charAt(endPos)) && ++endPos < jvmVersionString.length());
/*     */     }
/*     */ 
/*     */     
/* 109 */     startPos++;
/* 110 */     if (endPos > startPos) {
/* 111 */       jvmUpdateNumber = Integer.parseInt(jvmVersionString.substring(startPos, endPos));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     String loadedFrom = stackTraceToString(new Throwable());
/*     */     
/* 122 */     if (loadedFrom != null) {
/* 123 */       isColdFusion = (loadedFrom.indexOf("coldfusion") != -1);
/*     */     } else {
/* 125 */       isColdFusion = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isJdbc4() {
/* 130 */     return isJdbc4;
/*     */   }
/*     */   
/*     */   public static boolean isJdbc42() {
/* 134 */     return isJdbc42;
/*     */   }
/*     */   
/*     */   public static int getJVMVersion() {
/* 138 */     return jvmVersion;
/*     */   }
/*     */   
/*     */   public static boolean jvmMeetsMinimum(int version, int updateNumber) {
/* 142 */     return (getJVMVersion() > version || (getJVMVersion() == version && getJVMUpdateNumber() >= updateNumber));
/*     */   }
/*     */   
/*     */   public static int getJVMUpdateNumber() {
/* 146 */     return jvmUpdateNumber;
/*     */   }
/*     */   
/*     */   public static boolean isColdFusion() {
/* 150 */     return isColdFusion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCommunityEdition(String serverVersion) {
/* 157 */     return !isEnterpriseEdition(serverVersion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEnterpriseEdition(String serverVersion) {
/* 164 */     return (serverVersion.contains("enterprise") || serverVersion.contains("commercial") || serverVersion.contains("advanced"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String newCrypt(String password, String seed, String encoding) {
/* 172 */     if (password == null || password.length() == 0) {
/* 173 */       return password;
/*     */     }
/*     */     
/* 176 */     long[] pw = newHash(seed.getBytes());
/* 177 */     long[] msg = hashPre41Password(password, encoding);
/* 178 */     long max = 1073741823L;
/* 179 */     long seed1 = (pw[0] ^ msg[0]) % max;
/* 180 */     long seed2 = (pw[1] ^ msg[1]) % max;
/* 181 */     char[] chars = new char[seed.length()];
/*     */     int i;
/* 183 */     for (i = 0; i < seed.length(); i++) {
/* 184 */       seed1 = (seed1 * 3L + seed2) % max;
/* 185 */       seed2 = (seed1 + seed2 + 33L) % max;
/* 186 */       double d1 = seed1 / max;
/* 187 */       byte b1 = (byte)(int)Math.floor(d1 * 31.0D + 64.0D);
/* 188 */       chars[i] = (char)b1;
/*     */     } 
/*     */     
/* 191 */     seed1 = (seed1 * 3L + seed2) % max;
/* 192 */     seed2 = (seed1 + seed2 + 33L) % max;
/* 193 */     double d = seed1 / max;
/* 194 */     byte b = (byte)(int)Math.floor(d * 31.0D);
/*     */     
/* 196 */     for (i = 0; i < seed.length(); i++) {
/* 197 */       chars[i] = (char)(chars[i] ^ (char)b);
/*     */     }
/*     */     
/* 200 */     return new String(chars);
/*     */   }
/*     */ 
/*     */   
/*     */   public static long[] hashPre41Password(String password, String encoding) {
/*     */     try {
/* 206 */       return newHash(password.replaceAll("\\s", "").getBytes(encoding));
/* 207 */     } catch (UnsupportedEncodingException e) {
/* 208 */       return new long[0];
/*     */     } 
/*     */   }
/*     */   
/*     */   public static long[] hashPre41Password(String password) {
/* 213 */     return hashPre41Password(password, Charset.defaultCharset().name());
/*     */   }
/*     */   
/*     */   static long[] newHash(byte[] password) {
/* 217 */     long nr = 1345345333L;
/* 218 */     long add = 7L;
/* 219 */     long nr2 = 305419889L;
/*     */ 
/*     */     
/* 222 */     for (byte b : password) {
/* 223 */       long tmp = (0xFF & b);
/* 224 */       nr ^= ((nr & 0x3FL) + add) * tmp + (nr << 8L);
/* 225 */       nr2 += nr2 << 8L ^ nr;
/* 226 */       add += tmp;
/*     */     } 
/*     */     
/* 229 */     long[] result = new long[2];
/* 230 */     result[0] = nr & 0x7FFFFFFFL;
/* 231 */     result[1] = nr2 & 0x7FFFFFFFL;
/*     */     
/* 233 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String oldCrypt(String password, String seed) {
/* 241 */     long max = 33554431L;
/*     */ 
/*     */ 
/*     */     
/* 245 */     if (password == null || password.length() == 0) {
/* 246 */       return password;
/*     */     }
/*     */     
/* 249 */     long hp = oldHash(seed);
/* 250 */     long hm = oldHash(password);
/*     */     
/* 252 */     long nr = hp ^ hm;
/* 253 */     nr %= max;
/* 254 */     long s1 = nr;
/* 255 */     long s2 = nr / 2L;
/*     */     
/* 257 */     char[] chars = new char[seed.length()];
/*     */     
/* 259 */     for (int i = 0; i < seed.length(); i++) {
/* 260 */       s1 = (s1 * 3L + s2) % max;
/* 261 */       s2 = (s1 + s2 + 33L) % max;
/* 262 */       double d = s1 / max;
/* 263 */       byte b = (byte)(int)Math.floor(d * 31.0D + 64.0D);
/* 264 */       chars[i] = (char)b;
/*     */     } 
/*     */     
/* 267 */     return new String(chars);
/*     */   }
/*     */   
/*     */   static long oldHash(String password) {
/* 271 */     long nr = 1345345333L;
/* 272 */     long nr2 = 7L;
/*     */ 
/*     */     
/* 275 */     for (int i = 0; i < password.length(); i++) {
/* 276 */       if (password.charAt(i) != ' ' && password.charAt(i) != '\t') {
/*     */ 
/*     */ 
/*     */         
/* 280 */         long tmp = password.charAt(i);
/* 281 */         nr ^= ((nr & 0x3FL) + nr2) * tmp + (nr << 8L);
/* 282 */         nr2 += tmp;
/*     */       } 
/*     */     } 
/* 285 */     return nr & 0x7FFFFFFFL;
/*     */   }
/*     */   
/*     */   private static RandStructcture randomInit(long seed1, long seed2) {
/* 289 */     enclosingInstance.getClass(); RandStructcture randStruct = new RandStructcture();
/*     */     
/* 291 */     randStruct.maxValue = 1073741823L;
/* 292 */     randStruct.maxValueDbl = randStruct.maxValue;
/* 293 */     randStruct.seed1 = seed1 % randStruct.maxValue;
/* 294 */     randStruct.seed2 = seed2 % randStruct.maxValue;
/*     */     
/* 296 */     return randStruct;
/*     */   }
/*     */   
/*     */   private static double rnd(RandStructcture randStruct) {
/* 300 */     randStruct.seed1 = (randStruct.seed1 * 3L + randStruct.seed2) % randStruct.maxValue;
/* 301 */     randStruct.seed2 = (randStruct.seed1 + randStruct.seed2 + 33L) % randStruct.maxValue;
/*     */     
/* 303 */     return randStruct.seed1 / randStruct.maxValueDbl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String scramble(String message, String password) {
/* 313 */     byte[] to = new byte[8];
/* 314 */     String val = "";
/*     */     
/* 316 */     message = message.substring(0, 8);
/*     */     
/* 318 */     if (password != null && password.length() > 0) {
/* 319 */       long[] hashPass = hashPre41Password(password);
/* 320 */       long[] hashMessage = newHash(message.getBytes());
/*     */       
/* 322 */       RandStructcture randStruct = randomInit(hashPass[0] ^ hashMessage[0], hashPass[1] ^ hashMessage[1]);
/*     */       
/* 324 */       int msgPos = 0;
/* 325 */       int msgLength = message.length();
/* 326 */       int toPos = 0;
/*     */       
/* 328 */       while (msgPos++ < msgLength) {
/* 329 */         to[toPos++] = (byte)(int)(Math.floor(rnd(randStruct) * 31.0D) + 64.0D);
/*     */       }
/*     */ 
/*     */       
/* 333 */       byte extra = (byte)(int)Math.floor(rnd(randStruct) * 31.0D);
/*     */       
/* 335 */       for (int i = 0; i < to.length; i++) {
/* 336 */         to[i] = (byte)(to[i] ^ extra);
/*     */       }
/*     */       
/* 339 */       val = StringUtils.toString(to);
/*     */     } 
/*     */     
/* 342 */     return val;
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
/*     */   public static String stackTraceToString(Throwable ex) {
/* 355 */     StringBuilder traceBuf = new StringBuilder();
/* 356 */     traceBuf.append(Messages.getString("Util.1"));
/*     */     
/* 358 */     if (ex != null) {
/* 359 */       traceBuf.append(ex.getClass().getName());
/*     */       
/* 361 */       String message = ex.getMessage();
/*     */       
/* 363 */       if (message != null) {
/* 364 */         traceBuf.append(Messages.getString("Util.2"));
/* 365 */         traceBuf.append(message);
/*     */       } 
/*     */       
/* 368 */       StringWriter out = new StringWriter();
/*     */       
/* 370 */       PrintWriter printOut = new PrintWriter(out);
/*     */       
/* 372 */       ex.printStackTrace(printOut);
/*     */       
/* 374 */       traceBuf.append(Messages.getString("Util.3"));
/* 375 */       traceBuf.append(out.toString());
/*     */     } 
/*     */     
/* 378 */     traceBuf.append(Messages.getString("Util.4"));
/*     */     
/* 380 */     return traceBuf.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object getInstance(String className, Class<?>[] argTypes, Object[] args, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*     */     try {
/* 386 */       return handleNewInstance(Class.forName(className).getConstructor(argTypes), args, exceptionInterceptor);
/* 387 */     } catch (SecurityException e) {
/* 388 */       throw SQLError.createSQLException("Can't instantiate required class", "S1000", e, exceptionInterceptor);
/* 389 */     } catch (NoSuchMethodException e) {
/* 390 */       throw SQLError.createSQLException("Can't instantiate required class", "S1000", e, exceptionInterceptor);
/* 391 */     } catch (ClassNotFoundException e) {
/* 392 */       throw SQLError.createSQLException("Can't instantiate required class", "S1000", e, exceptionInterceptor);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final Object handleNewInstance(Constructor<?> ctor, Object[] args, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*     */     try {
/* 403 */       return ctor.newInstance(args);
/* 404 */     } catch (IllegalArgumentException e) {
/* 405 */       throw SQLError.createSQLException("Can't instantiate required class", "S1000", e, exceptionInterceptor);
/* 406 */     } catch (InstantiationException e) {
/* 407 */       throw SQLError.createSQLException("Can't instantiate required class", "S1000", e, exceptionInterceptor);
/* 408 */     } catch (IllegalAccessException e) {
/* 409 */       throw SQLError.createSQLException("Can't instantiate required class", "S1000", e, exceptionInterceptor);
/* 410 */     } catch (InvocationTargetException e) {
/* 411 */       Throwable target = e.getTargetException();
/*     */       
/* 413 */       if (target instanceof SQLException) {
/* 414 */         throw (SQLException)target;
/*     */       }
/*     */       
/* 417 */       if (target instanceof ExceptionInInitializerError) {
/* 418 */         target = ((ExceptionInInitializerError)target).getException();
/*     */       }
/*     */       
/* 421 */       throw SQLError.createSQLException(target.toString(), "S1000", target, exceptionInterceptor);
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
/*     */   public static boolean interfaceExists(String hostname) {
/*     */     try {
/* 435 */       Class<?> networkInterfaceClass = Class.forName("java.net.NetworkInterface"); return 
/* 436 */         (networkInterfaceClass.getMethod("getByName", (Class[])null).invoke(networkInterfaceClass, new Object[] { hostname }) != null);
/* 437 */     } catch (Throwable t) {
/* 438 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Map<Object, Object> calculateDifferences(Map<?, ?> map1, Map<?, ?> map2) {
/* 443 */     Map<Object, Object> diffMap = new HashMap<Object, Object>();
/*     */     
/* 445 */     for (Map.Entry<?, ?> entry : map1.entrySet()) {
/* 446 */       Object key = entry.getKey();
/*     */       
/* 448 */       Number value1 = null;
/* 449 */       Number value2 = null;
/*     */       
/* 451 */       if (entry.getValue() instanceof Number) {
/*     */         
/* 453 */         value1 = (Number)entry.getValue();
/* 454 */         value2 = (Number)map2.get(key);
/*     */       } else {
/*     */         try {
/* 457 */           value1 = new Double(entry.getValue().toString());
/* 458 */           value2 = new Double(map2.get(key).toString());
/* 459 */         } catch (NumberFormatException nfe) {
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */       
/* 464 */       if (value1.equals(value2)) {
/*     */         continue;
/*     */       }
/*     */       
/* 468 */       if (value1 instanceof Byte) {
/* 469 */         diffMap.put(key, Byte.valueOf((byte)(((Byte)value2).byteValue() - ((Byte)value1).byteValue()))); continue;
/* 470 */       }  if (value1 instanceof Short) {
/* 471 */         diffMap.put(key, Short.valueOf((short)(((Short)value2).shortValue() - ((Short)value1).shortValue()))); continue;
/* 472 */       }  if (value1 instanceof Integer) {
/* 473 */         diffMap.put(key, Integer.valueOf(((Integer)value2).intValue() - ((Integer)value1).intValue())); continue;
/* 474 */       }  if (value1 instanceof Long) {
/* 475 */         diffMap.put(key, Long.valueOf(((Long)value2).longValue() - ((Long)value1).longValue())); continue;
/* 476 */       }  if (value1 instanceof Float) {
/* 477 */         diffMap.put(key, Float.valueOf(((Float)value2).floatValue() - ((Float)value1).floatValue())); continue;
/* 478 */       }  if (value1 instanceof Double) {
/* 479 */         diffMap.put(key, Double.valueOf((((Double)value2).shortValue() - ((Double)value1).shortValue()))); continue;
/* 480 */       }  if (value1 instanceof BigDecimal) {
/* 481 */         diffMap.put(key, ((BigDecimal)value2).subtract((BigDecimal)value1)); continue;
/* 482 */       }  if (value1 instanceof BigInteger) {
/* 483 */         diffMap.put(key, ((BigInteger)value2).subtract((BigInteger)value1));
/*     */       }
/*     */     } 
/*     */     
/* 487 */     return diffMap;
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
/*     */   public static List<Extension> loadExtensions(Connection conn, Properties props, String extensionClassNames, String errorMessageKey, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/* 503 */     List<Extension> extensionList = new LinkedList<Extension>();
/*     */     
/* 505 */     List<String> interceptorsToCreate = StringUtils.split(extensionClassNames, ",", true);
/*     */     
/* 507 */     String className = null;
/*     */     
/*     */     try {
/* 510 */       for (int i = 0, s = interceptorsToCreate.size(); i < s; i++) {
/* 511 */         className = interceptorsToCreate.get(i);
/* 512 */         Extension extensionInstance = (Extension)Class.forName(className).newInstance();
/* 513 */         extensionInstance.init(conn, props);
/*     */         
/* 515 */         extensionList.add(extensionInstance);
/*     */       } 
/* 517 */     } catch (Throwable t) {
/* 518 */       SQLException sqlEx = SQLError.createSQLException(Messages.getString(errorMessageKey, new Object[] { className }), exceptionInterceptor);
/* 519 */       sqlEx.initCause(t);
/*     */       
/* 521 */       throw sqlEx;
/*     */     } 
/*     */     
/* 524 */     return extensionList;
/*     */   }
/*     */ 
/*     */   
/* 528 */   private static final ConcurrentMap<Class<?>, Boolean> isJdbcInterfaceCache = new ConcurrentHashMap<Class<?>, Boolean>();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String MYSQL_JDBC_PACKAGE_ROOT;
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isJdbcInterface(Class<?> clazz) {
/* 537 */     if (isJdbcInterfaceCache.containsKey(clazz)) {
/* 538 */       return ((Boolean)isJdbcInterfaceCache.get(clazz)).booleanValue();
/*     */     }
/*     */     
/* 541 */     if (clazz.isInterface()) {
/*     */       try {
/* 543 */         if (isJdbcPackage(getPackageName(clazz))) {
/* 544 */           isJdbcInterfaceCache.putIfAbsent(clazz, Boolean.valueOf(true));
/* 545 */           return true;
/*     */         } 
/* 547 */       } catch (Exception ex) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 555 */     for (Class<?> iface : clazz.getInterfaces()) {
/* 556 */       if (isJdbcInterface(iface)) {
/* 557 */         isJdbcInterfaceCache.putIfAbsent(clazz, Boolean.valueOf(true));
/* 558 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 562 */     if (clazz.getSuperclass() != null && isJdbcInterface(clazz.getSuperclass())) {
/* 563 */       isJdbcInterfaceCache.putIfAbsent(clazz, Boolean.valueOf(true));
/* 564 */       return true;
/*     */     } 
/*     */     
/* 567 */     isJdbcInterfaceCache.putIfAbsent(clazz, Boolean.valueOf(false));
/* 568 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 575 */     String packageName = getPackageName(MultiHostConnectionProxy.class);
/*     */     
/* 577 */     MYSQL_JDBC_PACKAGE_ROOT = packageName.substring(0, packageName.indexOf("jdbc") + 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isJdbcPackage(String packageName) {
/* 587 */     return (packageName != null && (packageName.startsWith("java.sql") || packageName.startsWith("javax.sql") || packageName.startsWith(MYSQL_JDBC_PACKAGE_ROOT)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 592 */   private static final ConcurrentMap<Class<?>, Class<?>[]> implementedInterfacesCache = (ConcurrentMap)new ConcurrentHashMap<Class<?>, Class<?>>();
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
/*     */   public static Class<?>[] getImplementedInterfaces(Class<?> clazz) {
/* 604 */     Class<?>[] implementedInterfaces = implementedInterfacesCache.get(clazz);
/* 605 */     if (implementedInterfaces != null) {
/* 606 */       return implementedInterfaces;
/*     */     }
/*     */     
/* 609 */     Set<Class<?>> interfaces = new LinkedHashSet<Class<?>>();
/* 610 */     Class<?> superClass = clazz;
/*     */     do {
/* 612 */       Collections.addAll(interfaces, superClass.getInterfaces());
/* 613 */     } while ((superClass = superClass.getSuperclass()) != null);
/*     */     
/* 615 */     implementedInterfaces = (Class[])interfaces.<Class<?>[]>toArray((Class<?>[][])new Class[interfaces.size()]);
/* 616 */     Class<?>[] oldValue = implementedInterfacesCache.putIfAbsent(clazz, implementedInterfaces);
/* 617 */     if (oldValue != null) {
/* 618 */       implementedInterfaces = oldValue;
/*     */     }
/* 620 */     return implementedInterfaces;
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
/*     */   public static long secondsSinceMillis(long timeInMillis) {
/* 632 */     return (System.currentTimeMillis() - timeInMillis) / 1000L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int truncateAndConvertToInt(long longValue) {
/* 642 */     return (longValue > 2147483647L) ? Integer.MAX_VALUE : ((longValue < -2147483648L) ? Integer.MIN_VALUE : (int)longValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] truncateAndConvertToInt(long[] longArray) {
/* 652 */     int[] intArray = new int[longArray.length];
/*     */     
/* 654 */     for (int i = 0; i < longArray.length; i++) {
/* 655 */       intArray[i] = (longArray[i] > 2147483647L) ? Integer.MAX_VALUE : ((longArray[i] < -2147483648L) ? Integer.MIN_VALUE : (int)longArray[i]);
/*     */     }
/* 657 */     return intArray;
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
/*     */   public static String getPackageName(Class<?> clazz) {
/* 669 */     String fqcn = clazz.getName();
/* 670 */     int classNameStartsAt = fqcn.lastIndexOf('.');
/* 671 */     if (classNameStartsAt > 0) {
/* 672 */       return fqcn.substring(0, classNameStartsAt);
/*     */     }
/* 674 */     return "";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\Util.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */