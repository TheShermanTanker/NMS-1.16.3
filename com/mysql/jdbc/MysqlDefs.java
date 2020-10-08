/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
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
/*     */ public final class MysqlDefs
/*     */ {
/*     */   static final int COM_BINLOG_DUMP = 18;
/*     */   static final int COM_CHANGE_USER = 17;
/*     */   static final int COM_CLOSE_STATEMENT = 25;
/*     */   static final int COM_CONNECT_OUT = 20;
/*     */   static final int COM_END = 29;
/*     */   static final int COM_EXECUTE = 23;
/*     */   static final int COM_FETCH = 28;
/*     */   static final int COM_LONG_DATA = 24;
/*     */   static final int COM_PREPARE = 22;
/*     */   static final int COM_REGISTER_SLAVE = 21;
/*     */   static final int COM_RESET_STMT = 26;
/*     */   static final int COM_SET_OPTION = 27;
/*     */   static final int COM_TABLE_DUMP = 19;
/*     */   static final int CONNECT = 11;
/*     */   static final int CREATE_DB = 5;
/*     */   static final int DEBUG = 13;
/*     */   static final int DELAYED_INSERT = 16;
/*     */   static final int DROP_DB = 6;
/*     */   static final int FIELD_LIST = 4;
/*     */   static final int FIELD_TYPE_BIT = 16;
/*     */   public static final int FIELD_TYPE_BLOB = 252;
/*     */   static final int FIELD_TYPE_DATE = 10;
/*     */   static final int FIELD_TYPE_DATETIME = 12;
/*     */   static final int FIELD_TYPE_DECIMAL = 0;
/*     */   static final int FIELD_TYPE_DOUBLE = 5;
/*     */   static final int FIELD_TYPE_ENUM = 247;
/*     */   static final int FIELD_TYPE_FLOAT = 4;
/*     */   static final int FIELD_TYPE_GEOMETRY = 255;
/*     */   static final int FIELD_TYPE_INT24 = 9;
/*     */   static final int FIELD_TYPE_LONG = 3;
/*     */   static final int FIELD_TYPE_LONG_BLOB = 251;
/*     */   static final int FIELD_TYPE_LONGLONG = 8;
/*     */   static final int FIELD_TYPE_MEDIUM_BLOB = 250;
/*     */   static final int FIELD_TYPE_NEW_DECIMAL = 246;
/*     */   static final int FIELD_TYPE_NEWDATE = 14;
/*     */   static final int FIELD_TYPE_NULL = 6;
/*     */   static final int FIELD_TYPE_SET = 248;
/*     */   static final int FIELD_TYPE_SHORT = 2;
/*     */   static final int FIELD_TYPE_STRING = 254;
/*     */   static final int FIELD_TYPE_TIME = 11;
/*     */   static final int FIELD_TYPE_TIMESTAMP = 7;
/*     */   static final int FIELD_TYPE_TINY = 1;
/*     */   static final int FIELD_TYPE_TINY_BLOB = 249;
/*     */   static final int FIELD_TYPE_VAR_STRING = 253;
/*     */   static final int FIELD_TYPE_VARCHAR = 15;
/*     */   static final int FIELD_TYPE_YEAR = 13;
/*     */   static final int FIELD_TYPE_JSON = 245;
/*     */   static final int INIT_DB = 2;
/*     */   static final long LENGTH_BLOB = 65535L;
/*     */   static final long LENGTH_LONGBLOB = 4294967295L;
/*     */   static final long LENGTH_MEDIUMBLOB = 16777215L;
/*     */   static final long LENGTH_TINYBLOB = 255L;
/*     */   static final int MAX_ROWS = 50000000;
/*     */   public static final int NO_CHARSET_INFO = -1;
/*     */   static final byte OPEN_CURSOR_FLAG = 1;
/*     */   static final int PING = 14;
/*     */   static final int PROCESS_INFO = 10;
/*     */   static final int PROCESS_KILL = 12;
/*     */   static final int QUERY = 3;
/*     */   static final int QUIT = 1;
/*     */   static final int RELOAD = 7;
/*     */   static final int SHUTDOWN = 8;
/*     */   static final int SLEEP = 0;
/*     */   static final int STATISTICS = 9;
/*     */   static final int TIME = 15;
/*     */   
/*     */   static int mysqlToJavaType(int mysqlType) {
/* 182 */     switch (mysqlType)
/*     */     { case 0:
/*     */       case 246:
/* 185 */         jdbcType = 3;
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
/* 312 */         return jdbcType;case 1: jdbcType = -6; return jdbcType;case 2: jdbcType = 5; return jdbcType;case 3: jdbcType = 4; return jdbcType;case 4: jdbcType = 7; return jdbcType;case 5: jdbcType = 8; return jdbcType;case 6: jdbcType = 0; return jdbcType;case 7: jdbcType = 93; return jdbcType;case 8: jdbcType = -5; return jdbcType;case 9: jdbcType = 4; return jdbcType;case 10: jdbcType = 91; return jdbcType;case 11: jdbcType = 92; return jdbcType;case 12: jdbcType = 93; return jdbcType;case 13: jdbcType = 91; return jdbcType;case 14: jdbcType = 91; return jdbcType;case 247: jdbcType = 1; return jdbcType;case 248: jdbcType = 1; return jdbcType;case 249: jdbcType = -3; return jdbcType;case 250: jdbcType = -4; return jdbcType;case 251: jdbcType = -4; return jdbcType;case 252: jdbcType = -4; return jdbcType;case 15: case 253: jdbcType = 12; return jdbcType;case 245: case 254: jdbcType = 1; return jdbcType;case 255: jdbcType = -2; return jdbcType;case 16: jdbcType = -7; return jdbcType; }  int jdbcType = 12; return jdbcType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int mysqlToJavaType(String mysqlType) {
/* 319 */     if (mysqlType.equalsIgnoreCase("BIT"))
/* 320 */       return mysqlToJavaType(16); 
/* 321 */     if (mysqlType.equalsIgnoreCase("TINYINT"))
/* 322 */       return mysqlToJavaType(1); 
/* 323 */     if (mysqlType.equalsIgnoreCase("SMALLINT"))
/* 324 */       return mysqlToJavaType(2); 
/* 325 */     if (mysqlType.equalsIgnoreCase("MEDIUMINT"))
/* 326 */       return mysqlToJavaType(9); 
/* 327 */     if (mysqlType.equalsIgnoreCase("INT") || mysqlType.equalsIgnoreCase("INTEGER"))
/* 328 */       return mysqlToJavaType(3); 
/* 329 */     if (mysqlType.equalsIgnoreCase("BIGINT"))
/* 330 */       return mysqlToJavaType(8); 
/* 331 */     if (mysqlType.equalsIgnoreCase("INT24"))
/* 332 */       return mysqlToJavaType(9); 
/* 333 */     if (mysqlType.equalsIgnoreCase("REAL"))
/* 334 */       return mysqlToJavaType(5); 
/* 335 */     if (mysqlType.equalsIgnoreCase("FLOAT"))
/* 336 */       return mysqlToJavaType(4); 
/* 337 */     if (mysqlType.equalsIgnoreCase("DECIMAL"))
/* 338 */       return mysqlToJavaType(0); 
/* 339 */     if (mysqlType.equalsIgnoreCase("NUMERIC"))
/* 340 */       return mysqlToJavaType(0); 
/* 341 */     if (mysqlType.equalsIgnoreCase("DOUBLE"))
/* 342 */       return mysqlToJavaType(5); 
/* 343 */     if (mysqlType.equalsIgnoreCase("CHAR"))
/* 344 */       return mysqlToJavaType(254); 
/* 345 */     if (mysqlType.equalsIgnoreCase("VARCHAR"))
/* 346 */       return mysqlToJavaType(253); 
/* 347 */     if (mysqlType.equalsIgnoreCase("DATE"))
/* 348 */       return mysqlToJavaType(10); 
/* 349 */     if (mysqlType.equalsIgnoreCase("TIME"))
/* 350 */       return mysqlToJavaType(11); 
/* 351 */     if (mysqlType.equalsIgnoreCase("YEAR"))
/* 352 */       return mysqlToJavaType(13); 
/* 353 */     if (mysqlType.equalsIgnoreCase("TIMESTAMP"))
/* 354 */       return mysqlToJavaType(7); 
/* 355 */     if (mysqlType.equalsIgnoreCase("DATETIME"))
/* 356 */       return mysqlToJavaType(12); 
/* 357 */     if (mysqlType.equalsIgnoreCase("TINYBLOB"))
/* 358 */       return -2; 
/* 359 */     if (mysqlType.equalsIgnoreCase("BLOB"))
/* 360 */       return -4; 
/* 361 */     if (mysqlType.equalsIgnoreCase("MEDIUMBLOB"))
/* 362 */       return -4; 
/* 363 */     if (mysqlType.equalsIgnoreCase("LONGBLOB"))
/* 364 */       return -4; 
/* 365 */     if (mysqlType.equalsIgnoreCase("TINYTEXT"))
/* 366 */       return 12; 
/* 367 */     if (mysqlType.equalsIgnoreCase("TEXT"))
/* 368 */       return -1; 
/* 369 */     if (mysqlType.equalsIgnoreCase("MEDIUMTEXT"))
/* 370 */       return -1; 
/* 371 */     if (mysqlType.equalsIgnoreCase("LONGTEXT"))
/* 372 */       return -1; 
/* 373 */     if (mysqlType.equalsIgnoreCase("ENUM"))
/* 374 */       return mysqlToJavaType(247); 
/* 375 */     if (mysqlType.equalsIgnoreCase("SET"))
/* 376 */       return mysqlToJavaType(248); 
/* 377 */     if (mysqlType.equalsIgnoreCase("GEOMETRY"))
/* 378 */       return mysqlToJavaType(255); 
/* 379 */     if (mysqlType.equalsIgnoreCase("BINARY"))
/* 380 */       return -2; 
/* 381 */     if (mysqlType.equalsIgnoreCase("VARBINARY"))
/* 382 */       return -3; 
/* 383 */     if (mysqlType.equalsIgnoreCase("BIT"))
/* 384 */       return mysqlToJavaType(16); 
/* 385 */     if (mysqlType.equalsIgnoreCase("JSON")) {
/* 386 */       return mysqlToJavaType(245);
/*     */     }
/*     */ 
/*     */     
/* 390 */     return 1111;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String typeToName(int mysqlType) {
/* 397 */     switch (mysqlType) {
/*     */       case 0:
/* 399 */         return "FIELD_TYPE_DECIMAL";
/*     */       
/*     */       case 1:
/* 402 */         return "FIELD_TYPE_TINY";
/*     */       
/*     */       case 2:
/* 405 */         return "FIELD_TYPE_SHORT";
/*     */       
/*     */       case 3:
/* 408 */         return "FIELD_TYPE_LONG";
/*     */       
/*     */       case 4:
/* 411 */         return "FIELD_TYPE_FLOAT";
/*     */       
/*     */       case 5:
/* 414 */         return "FIELD_TYPE_DOUBLE";
/*     */       
/*     */       case 6:
/* 417 */         return "FIELD_TYPE_NULL";
/*     */       
/*     */       case 7:
/* 420 */         return "FIELD_TYPE_TIMESTAMP";
/*     */       
/*     */       case 8:
/* 423 */         return "FIELD_TYPE_LONGLONG";
/*     */       
/*     */       case 9:
/* 426 */         return "FIELD_TYPE_INT24";
/*     */       
/*     */       case 16:
/* 429 */         return "FIELD_TYPE_BIT";
/*     */       
/*     */       case 10:
/* 432 */         return "FIELD_TYPE_DATE";
/*     */       
/*     */       case 11:
/* 435 */         return "FIELD_TYPE_TIME";
/*     */       
/*     */       case 12:
/* 438 */         return "FIELD_TYPE_DATETIME";
/*     */       
/*     */       case 13:
/* 441 */         return "FIELD_TYPE_YEAR";
/*     */       
/*     */       case 14:
/* 444 */         return "FIELD_TYPE_NEWDATE";
/*     */       
/*     */       case 247:
/* 447 */         return "FIELD_TYPE_ENUM";
/*     */       
/*     */       case 248:
/* 450 */         return "FIELD_TYPE_SET";
/*     */       
/*     */       case 249:
/* 453 */         return "FIELD_TYPE_TINY_BLOB";
/*     */       
/*     */       case 250:
/* 456 */         return "FIELD_TYPE_MEDIUM_BLOB";
/*     */       
/*     */       case 251:
/* 459 */         return "FIELD_TYPE_LONG_BLOB";
/*     */       
/*     */       case 252:
/* 462 */         return "FIELD_TYPE_BLOB";
/*     */       
/*     */       case 253:
/* 465 */         return "FIELD_TYPE_VAR_STRING";
/*     */       
/*     */       case 254:
/* 468 */         return "FIELD_TYPE_STRING";
/*     */       
/*     */       case 15:
/* 471 */         return "FIELD_TYPE_VARCHAR";
/*     */       
/*     */       case 255:
/* 474 */         return "FIELD_TYPE_GEOMETRY";
/*     */       
/*     */       case 245:
/* 477 */         return "FIELD_TYPE_JSON";
/*     */     } 
/*     */     
/* 480 */     return " Unknown MySQL Type # " + mysqlType;
/*     */   }
/*     */ 
/*     */   
/* 484 */   private static Map<String, Integer> mysqlToJdbcTypesMap = new HashMap<String, Integer>();
/*     */   
/*     */   static {
/* 487 */     mysqlToJdbcTypesMap.put("BIT", Integer.valueOf(mysqlToJavaType(16)));
/*     */     
/* 489 */     mysqlToJdbcTypesMap.put("TINYINT", Integer.valueOf(mysqlToJavaType(1)));
/* 490 */     mysqlToJdbcTypesMap.put("SMALLINT", Integer.valueOf(mysqlToJavaType(2)));
/* 491 */     mysqlToJdbcTypesMap.put("MEDIUMINT", Integer.valueOf(mysqlToJavaType(9)));
/* 492 */     mysqlToJdbcTypesMap.put("INT", Integer.valueOf(mysqlToJavaType(3)));
/* 493 */     mysqlToJdbcTypesMap.put("INTEGER", Integer.valueOf(mysqlToJavaType(3)));
/* 494 */     mysqlToJdbcTypesMap.put("BIGINT", Integer.valueOf(mysqlToJavaType(8)));
/* 495 */     mysqlToJdbcTypesMap.put("INT24", Integer.valueOf(mysqlToJavaType(9)));
/* 496 */     mysqlToJdbcTypesMap.put("REAL", Integer.valueOf(mysqlToJavaType(5)));
/* 497 */     mysqlToJdbcTypesMap.put("FLOAT", Integer.valueOf(mysqlToJavaType(4)));
/* 498 */     mysqlToJdbcTypesMap.put("DECIMAL", Integer.valueOf(mysqlToJavaType(0)));
/* 499 */     mysqlToJdbcTypesMap.put("NUMERIC", Integer.valueOf(mysqlToJavaType(0)));
/* 500 */     mysqlToJdbcTypesMap.put("DOUBLE", Integer.valueOf(mysqlToJavaType(5)));
/* 501 */     mysqlToJdbcTypesMap.put("CHAR", Integer.valueOf(mysqlToJavaType(254)));
/* 502 */     mysqlToJdbcTypesMap.put("VARCHAR", Integer.valueOf(mysqlToJavaType(253)));
/* 503 */     mysqlToJdbcTypesMap.put("DATE", Integer.valueOf(mysqlToJavaType(10)));
/* 504 */     mysqlToJdbcTypesMap.put("TIME", Integer.valueOf(mysqlToJavaType(11)));
/* 505 */     mysqlToJdbcTypesMap.put("YEAR", Integer.valueOf(mysqlToJavaType(13)));
/* 506 */     mysqlToJdbcTypesMap.put("TIMESTAMP", Integer.valueOf(mysqlToJavaType(7)));
/* 507 */     mysqlToJdbcTypesMap.put("DATETIME", Integer.valueOf(mysqlToJavaType(12)));
/* 508 */     mysqlToJdbcTypesMap.put("TINYBLOB", Integer.valueOf(-2));
/* 509 */     mysqlToJdbcTypesMap.put("BLOB", Integer.valueOf(-4));
/* 510 */     mysqlToJdbcTypesMap.put("MEDIUMBLOB", Integer.valueOf(-4));
/* 511 */     mysqlToJdbcTypesMap.put("LONGBLOB", Integer.valueOf(-4));
/* 512 */     mysqlToJdbcTypesMap.put("TINYTEXT", Integer.valueOf(12));
/* 513 */     mysqlToJdbcTypesMap.put("TEXT", Integer.valueOf(-1));
/* 514 */     mysqlToJdbcTypesMap.put("MEDIUMTEXT", Integer.valueOf(-1));
/* 515 */     mysqlToJdbcTypesMap.put("LONGTEXT", Integer.valueOf(-1));
/* 516 */     mysqlToJdbcTypesMap.put("ENUM", Integer.valueOf(mysqlToJavaType(247)));
/* 517 */     mysqlToJdbcTypesMap.put("SET", Integer.valueOf(mysqlToJavaType(248)));
/* 518 */     mysqlToJdbcTypesMap.put("GEOMETRY", Integer.valueOf(mysqlToJavaType(255)));
/* 519 */     mysqlToJdbcTypesMap.put("JSON", Integer.valueOf(mysqlToJavaType(245)));
/*     */   }
/*     */ 
/*     */   
/*     */   static final void appendJdbcTypeMappingQuery(StringBuilder buf, String mysqlTypeColumnName) {
/* 524 */     buf.append("CASE ");
/* 525 */     Map<String, Integer> typesMap = new HashMap<String, Integer>();
/* 526 */     typesMap.putAll(mysqlToJdbcTypesMap);
/* 527 */     typesMap.put("BINARY", Integer.valueOf(-2));
/* 528 */     typesMap.put("VARBINARY", Integer.valueOf(-3));
/*     */     
/* 530 */     Iterator<String> mysqlTypes = typesMap.keySet().iterator();
/*     */     
/* 532 */     while (mysqlTypes.hasNext()) {
/* 533 */       String mysqlTypeName = mysqlTypes.next();
/* 534 */       buf.append(" WHEN UPPER(");
/* 535 */       buf.append(mysqlTypeColumnName);
/* 536 */       buf.append(")='");
/* 537 */       buf.append(mysqlTypeName);
/* 538 */       buf.append("' THEN ");
/* 539 */       buf.append(typesMap.get(mysqlTypeName));
/*     */       
/* 541 */       if (mysqlTypeName.equalsIgnoreCase("DOUBLE") || mysqlTypeName.equalsIgnoreCase("FLOAT") || mysqlTypeName.equalsIgnoreCase("DECIMAL") || mysqlTypeName.equalsIgnoreCase("NUMERIC")) {
/*     */         
/* 543 */         buf.append(" WHEN ");
/* 544 */         buf.append(mysqlTypeColumnName);
/* 545 */         buf.append("='");
/* 546 */         buf.append(mysqlTypeName);
/* 547 */         buf.append(" UNSIGNED' THEN ");
/* 548 */         buf.append(typesMap.get(mysqlTypeName));
/*     */       } 
/*     */     } 
/*     */     
/* 552 */     buf.append(" ELSE ");
/* 553 */     buf.append(1111);
/* 554 */     buf.append(" END ");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\MysqlDefs.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */