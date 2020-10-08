/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.sql.Connection;
/*     */ import java.sql.Driver;
/*     */ import java.sql.DriverPropertyInfo;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NonRegisteringDriver
/*     */   implements Driver
/*     */ {
/*     */   private static final String ALLOWED_QUOTES = "\"'";
/*     */   private static final String REPLICATION_URL_PREFIX = "jdbc:mysql:replication://";
/*     */   private static final String URL_PREFIX = "jdbc:mysql://";
/*     */   private static final String MXJ_URL_PREFIX = "jdbc:mysql:mxj://";
/*     */   public static final String LOADBALANCE_URL_PREFIX = "jdbc:mysql:loadbalance://";
/*  68 */   public static final String PLATFORM = getPlatform();
/*  69 */   public static final String OS = getOSName();
/*     */   public static final String LICENSE = "GPL";
/*  71 */   public static final String RUNTIME_VENDOR = System.getProperty("java.vendor");
/*  72 */   public static final String RUNTIME_VERSION = System.getProperty("java.version");
/*     */   public static final String VERSION = "5.1.49";
/*     */   public static final String NAME = "MySQL Connector Java";
/*     */   public static final String DBNAME_PROPERTY_KEY = "DBNAME";
/*     */   public static final boolean DEBUG = false;
/*     */   public static final int HOST_NAME_INDEX = 0;
/*     */   public static final String HOST_PROPERTY_KEY = "HOST";
/*     */   public static final String NUM_HOSTS_PROPERTY_KEY = "NUM_HOSTS";
/*     */   public static final String PASSWORD_PROPERTY_KEY = "password";
/*     */   
/*     */   public static String getOSName() {
/*  83 */     return System.getProperty("os.name");
/*     */   }
/*     */ 
/*     */   
/*     */   public static final int PORT_NUMBER_INDEX = 1;
/*     */   public static final String PORT_PROPERTY_KEY = "PORT";
/*     */   public static final String PROPERTIES_TRANSFORM_KEY = "propertiesTransform";
/*     */   public static final boolean TRACE = false;
/*     */   
/*     */   public static String getPlatform() {
/*  93 */     return System.getProperty("os.arch");
/*     */   }
/*     */   public static final String USE_CONFIG_PROPERTY_KEY = "useConfigs"; public static final String USER_PROPERTY_KEY = "user"; public static final String PROTOCOL_PROPERTY_KEY = "PROTOCOL"; public static final String PATH_PROPERTY_KEY = "PATH";
/*     */   static {
/*     */     try {
/*  98 */       Class.forName(AbandonedConnectionCleanupThread.class.getName());
/*  99 */     } catch (ClassNotFoundException e) {}
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getMajorVersionInternal() {
/* 162 */     return safeIntParse("5");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int getMinorVersionInternal() {
/* 171 */     return safeIntParse("1");
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
/*     */   protected static String[] parseHostPortPair(String hostPortPair) throws SQLException {
/* 190 */     String[] splitValues = new String[2];
/*     */     
/* 192 */     if (StringUtils.startsWithIgnoreCaseAndWs(hostPortPair, "address=")) {
/* 193 */       splitValues[0] = hostPortPair.trim();
/* 194 */       splitValues[1] = null;
/*     */       
/* 196 */       return splitValues;
/*     */     } 
/*     */     
/* 199 */     int portIndex = hostPortPair.indexOf(":");
/*     */     
/* 201 */     String hostname = null;
/*     */     
/* 203 */     if (portIndex != -1) {
/* 204 */       if (portIndex + 1 < hostPortPair.length()) {
/* 205 */         String portAsString = hostPortPair.substring(portIndex + 1);
/* 206 */         hostname = hostPortPair.substring(0, portIndex);
/*     */         
/* 208 */         splitValues[0] = hostname;
/*     */         
/* 210 */         splitValues[1] = portAsString;
/*     */       } else {
/* 212 */         throw SQLError.createSQLException(Messages.getString("NonRegisteringDriver.37"), "01S00", null);
/*     */       } 
/*     */     } else {
/* 215 */       splitValues[0] = hostPortPair;
/* 216 */       splitValues[1] = null;
/*     */     } 
/*     */     
/* 219 */     return splitValues;
/*     */   }
/*     */   
/*     */   private static int safeIntParse(String intAsString) {
/*     */     try {
/* 224 */       return Integer.parseInt(intAsString);
/* 225 */     } catch (NumberFormatException nfe) {
/* 226 */       return 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean acceptsURL(String url) throws SQLException {
/* 256 */     if (url == null) {
/* 257 */       throw SQLError.createSQLException(Messages.getString("NonRegisteringDriver.1"), "08001", null);
/*     */     }
/* 259 */     return (parseURL(url, null) != null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Connection connect(String url, Properties info) throws SQLException {
/* 302 */     if (url == null) {
/* 303 */       throw SQLError.createSQLException(Messages.getString("NonRegisteringDriver.1"), "08001", null);
/*     */     }
/*     */     
/* 306 */     if (StringUtils.startsWithIgnoreCase(url, "jdbc:mysql:loadbalance://"))
/* 307 */       return connectLoadBalanced(url, info); 
/* 308 */     if (StringUtils.startsWithIgnoreCase(url, "jdbc:mysql:replication://")) {
/* 309 */       return connectReplicationConnection(url, info);
/*     */     }
/*     */     
/* 312 */     Properties props = null;
/*     */     
/* 314 */     if ((props = parseURL(url, info)) == null) {
/* 315 */       return null;
/*     */     }
/*     */     
/* 318 */     if (!"1".equals(props.getProperty("NUM_HOSTS"))) {
/* 319 */       return connectFailover(url, info);
/*     */     }
/*     */     
/*     */     try {
/* 323 */       Connection newConn = ConnectionImpl.getInstance(host(props), port(props), props, database(props), url);
/*     */       
/* 325 */       return newConn;
/* 326 */     } catch (SQLException sqlEx) {
/*     */ 
/*     */       
/* 329 */       throw sqlEx;
/* 330 */     } catch (Exception ex) {
/* 331 */       SQLException sqlEx = SQLError.createSQLException(Messages.getString("NonRegisteringDriver.17") + ex.toString() + Messages.getString("NonRegisteringDriver.18"), "08001", (ExceptionInterceptor)null);
/*     */ 
/*     */ 
/*     */       
/* 335 */       sqlEx.initCause(ex);
/*     */       
/* 337 */       throw sqlEx;
/*     */     } 
/*     */   }
/*     */   
/*     */   private Connection connectLoadBalanced(String url, Properties info) throws SQLException {
/* 342 */     Properties parsedProps = parseURL(url, info);
/*     */     
/* 344 */     if (parsedProps == null) {
/* 345 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 349 */     parsedProps.remove("roundRobinLoadBalance");
/*     */     
/* 351 */     int numHosts = Integer.parseInt(parsedProps.getProperty("NUM_HOSTS"));
/*     */     
/* 353 */     List<String> hostList = new ArrayList<String>();
/*     */     
/* 355 */     for (int i = 0; i < numHosts; i++) {
/* 356 */       int index = i + 1;
/*     */       
/* 358 */       hostList.add(parsedProps.getProperty("HOST." + index) + ":" + parsedProps.getProperty("PORT." + index));
/*     */     } 
/*     */     
/* 361 */     return LoadBalancedConnectionProxy.createProxyInstance(hostList, parsedProps);
/*     */   }
/*     */   
/*     */   private Connection connectFailover(String url, Properties info) throws SQLException {
/* 365 */     Properties parsedProps = parseURL(url, info);
/*     */     
/* 367 */     if (parsedProps == null) {
/* 368 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 372 */     parsedProps.remove("roundRobinLoadBalance");
/*     */     
/* 374 */     int numHosts = Integer.parseInt(parsedProps.getProperty("NUM_HOSTS"));
/*     */     
/* 376 */     List<String> hostList = new ArrayList<String>();
/*     */     
/* 378 */     for (int i = 0; i < numHosts; i++) {
/* 379 */       int index = i + 1;
/*     */       
/* 381 */       hostList.add(parsedProps.getProperty("HOST." + index) + ":" + parsedProps.getProperty("PORT." + index));
/*     */     } 
/*     */     
/* 384 */     return FailoverConnectionProxy.createProxyInstance(hostList, parsedProps);
/*     */   }
/*     */   
/*     */   protected Connection connectReplicationConnection(String url, Properties info) throws SQLException {
/* 388 */     Properties parsedProps = parseURL(url, info);
/*     */     
/* 390 */     if (parsedProps == null) {
/* 391 */       return null;
/*     */     }
/*     */     
/* 394 */     Properties masterProps = (Properties)parsedProps.clone();
/* 395 */     Properties slavesProps = (Properties)parsedProps.clone();
/*     */ 
/*     */ 
/*     */     
/* 399 */     slavesProps.setProperty("com.mysql.jdbc.ReplicationConnection.isSlave", "true");
/*     */     
/* 401 */     int numHosts = Integer.parseInt(parsedProps.getProperty("NUM_HOSTS"));
/*     */     
/* 403 */     if (numHosts < 2) {
/* 404 */       throw SQLError.createSQLException("Must specify at least one slave host to connect to for master/slave replication load-balancing functionality", "01S00", null);
/*     */     }
/*     */     
/* 407 */     List<String> slaveHostList = new ArrayList<String>();
/* 408 */     List<String> masterHostList = new ArrayList<String>();
/*     */     
/* 410 */     String firstHost = masterProps.getProperty("HOST.1") + ":" + masterProps.getProperty("PORT.1");
/*     */     
/* 412 */     boolean usesExplicitServerType = isHostPropertiesList(firstHost);
/*     */     
/* 414 */     for (int i = 0; i < numHosts; i++) {
/* 415 */       int index = i + 1;
/*     */       
/* 417 */       masterProps.remove("HOST." + index);
/* 418 */       masterProps.remove("PORT." + index);
/* 419 */       slavesProps.remove("HOST." + index);
/* 420 */       slavesProps.remove("PORT." + index);
/*     */       
/* 422 */       String host = parsedProps.getProperty("HOST." + index);
/* 423 */       String port = parsedProps.getProperty("PORT." + index);
/* 424 */       if (usesExplicitServerType) {
/* 425 */         if (isHostMaster(host)) {
/* 426 */           masterHostList.add(host);
/*     */         } else {
/* 428 */           slaveHostList.add(host);
/*     */         }
/*     */       
/* 431 */       } else if (i == 0) {
/* 432 */         masterHostList.add(host + ":" + port);
/*     */       } else {
/* 434 */         slaveHostList.add(host + ":" + port);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 439 */     slavesProps.remove("NUM_HOSTS");
/* 440 */     masterProps.remove("NUM_HOSTS");
/* 441 */     masterProps.remove("HOST");
/* 442 */     masterProps.remove("PORT");
/* 443 */     slavesProps.remove("HOST");
/* 444 */     slavesProps.remove("PORT");
/*     */     
/* 446 */     return ReplicationConnectionProxy.createProxyInstance(masterHostList, masterProps, slaveHostList, slavesProps);
/*     */   }
/*     */   
/*     */   private boolean isHostMaster(String host) {
/* 450 */     if (isHostPropertiesList(host)) {
/* 451 */       Properties hostSpecificProps = expandHostKeyValues(host);
/* 452 */       if (hostSpecificProps.containsKey("type") && "master".equalsIgnoreCase(hostSpecificProps.get("type").toString())) {
/* 453 */         return true;
/*     */       }
/*     */     } 
/* 456 */     return false;
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
/*     */   public String database(Properties props) {
/* 468 */     return props.getProperty("DBNAME");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMajorVersion() {
/* 477 */     return getMajorVersionInternal();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinorVersion() {
/* 486 */     return getMinorVersionInternal();
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
/*     */   public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
/* 515 */     if (info == null) {
/* 516 */       info = new Properties();
/*     */     }
/*     */     
/* 519 */     if (url != null && url.startsWith("jdbc:mysql://")) {
/* 520 */       info = parseURL(url, info);
/*     */     }
/*     */     
/* 523 */     DriverPropertyInfo hostProp = new DriverPropertyInfo("HOST", info.getProperty("HOST"));
/* 524 */     hostProp.required = true;
/* 525 */     hostProp.description = Messages.getString("NonRegisteringDriver.3");
/*     */     
/* 527 */     DriverPropertyInfo portProp = new DriverPropertyInfo("PORT", info.getProperty("PORT", "3306"));
/* 528 */     portProp.required = false;
/* 529 */     portProp.description = Messages.getString("NonRegisteringDriver.7");
/*     */     
/* 531 */     DriverPropertyInfo dbProp = new DriverPropertyInfo("DBNAME", info.getProperty("DBNAME"));
/* 532 */     dbProp.required = false;
/* 533 */     dbProp.description = "Database name";
/*     */     
/* 535 */     DriverPropertyInfo userProp = new DriverPropertyInfo("user", info.getProperty("user"));
/* 536 */     userProp.required = true;
/* 537 */     userProp.description = Messages.getString("NonRegisteringDriver.13");
/*     */     
/* 539 */     DriverPropertyInfo passwordProp = new DriverPropertyInfo("password", info.getProperty("password"));
/* 540 */     passwordProp.required = true;
/* 541 */     passwordProp.description = Messages.getString("NonRegisteringDriver.16");
/*     */     
/* 543 */     DriverPropertyInfo[] dpi = ConnectionPropertiesImpl.exposeAsDriverPropertyInfo(info, 5);
/*     */     
/* 545 */     dpi[0] = hostProp;
/* 546 */     dpi[1] = portProp;
/* 547 */     dpi[2] = dbProp;
/* 548 */     dpi[3] = userProp;
/* 549 */     dpi[4] = passwordProp;
/*     */     
/* 551 */     return dpi;
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
/*     */   public String host(Properties props) {
/* 568 */     return props.getProperty("HOST", "localhost");
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
/*     */   public boolean jdbcCompliant() {
/* 584 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Properties parseURL(String url, Properties defaults) throws SQLException {
/* 589 */     Properties urlProps = (defaults != null) ? new Properties(defaults) : new Properties();
/*     */     
/* 591 */     if (url == null) {
/* 592 */       return null;
/*     */     }
/*     */     
/* 595 */     if (!StringUtils.startsWithIgnoreCase(url, "jdbc:mysql://") && !StringUtils.startsWithIgnoreCase(url, "jdbc:mysql:mxj://") && !StringUtils.startsWithIgnoreCase(url, "jdbc:mysql:loadbalance://") && !StringUtils.startsWithIgnoreCase(url, "jdbc:mysql:replication://"))
/*     */     {
/*     */       
/* 598 */       return null;
/*     */     }
/*     */     
/* 601 */     int beginningOfSlashes = url.indexOf("//");
/*     */     
/* 603 */     if (StringUtils.startsWithIgnoreCase(url, "jdbc:mysql:mxj://"))
/*     */     {
/* 605 */       urlProps.setProperty("socketFactory", "com.mysql.management.driverlaunched.ServerLauncherSocketFactory");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 612 */     int index = url.indexOf("?");
/*     */     
/* 614 */     if (index != -1) {
/* 615 */       String paramString = url.substring(index + 1, url.length());
/* 616 */       url = url.substring(0, index);
/*     */       
/* 618 */       StringTokenizer queryParams = new StringTokenizer(paramString, "&");
/*     */       
/* 620 */       while (queryParams.hasMoreTokens()) {
/* 621 */         String parameterValuePair = queryParams.nextToken();
/*     */         
/* 623 */         int indexOfEquals = StringUtils.indexOfIgnoreCase(0, parameterValuePair, "=");
/*     */         
/* 625 */         String parameter = null;
/* 626 */         String value = null;
/*     */         
/* 628 */         if (indexOfEquals != -1) {
/* 629 */           parameter = parameterValuePair.substring(0, indexOfEquals);
/*     */           
/* 631 */           if (indexOfEquals + 1 < parameterValuePair.length()) {
/* 632 */             value = parameterValuePair.substring(indexOfEquals + 1);
/*     */           }
/*     */         } 
/*     */         
/* 636 */         if (value != null && value.length() > 0 && parameter != null && parameter.length() > 0) {
/*     */           try {
/* 638 */             urlProps.setProperty(parameter, URLDecoder.decode(value, "UTF-8"));
/* 639 */           } catch (UnsupportedEncodingException badEncoding) {
/*     */             
/* 641 */             urlProps.setProperty(parameter, URLDecoder.decode(value));
/* 642 */           } catch (NoSuchMethodError nsme) {
/*     */             
/* 644 */             urlProps.setProperty(parameter, URLDecoder.decode(value));
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 650 */     url = url.substring(beginningOfSlashes + 2);
/*     */     
/* 652 */     String hostStuff = null;
/*     */     
/* 654 */     int slashIndex = StringUtils.indexOfIgnoreCase(0, url, "/", "\"'", "\"'", StringUtils.SEARCH_MODE__ALL);
/*     */     
/* 656 */     if (slashIndex != -1) {
/* 657 */       hostStuff = url.substring(0, slashIndex);
/*     */       
/* 659 */       if (slashIndex + 1 < url.length()) {
/* 660 */         urlProps.put("DBNAME", url.substring(slashIndex + 1, url.length()));
/*     */       }
/*     */     } else {
/* 663 */       hostStuff = url;
/*     */     } 
/*     */     
/* 666 */     int numHosts = 0;
/*     */     
/* 668 */     if (hostStuff != null && hostStuff.trim().length() > 0) {
/* 669 */       List<String> hosts = StringUtils.split(hostStuff, ",", "\"'", "\"'", false);
/*     */       
/* 671 */       for (String hostAndPort : hosts) {
/* 672 */         numHosts++;
/*     */         
/* 674 */         String[] hostPortPair = parseHostPortPair(hostAndPort);
/*     */         
/* 676 */         if (hostPortPair[0] != null && hostPortPair[0].trim().length() > 0) {
/* 677 */           urlProps.setProperty("HOST." + numHosts, hostPortPair[0]);
/*     */         } else {
/* 679 */           urlProps.setProperty("HOST." + numHosts, "localhost");
/*     */         } 
/*     */         
/* 682 */         if (hostPortPair[1] != null) {
/* 683 */           urlProps.setProperty("PORT." + numHosts, hostPortPair[1]); continue;
/*     */         } 
/* 685 */         urlProps.setProperty("PORT." + numHosts, "3306");
/*     */       } 
/*     */     } else {
/*     */       
/* 689 */       numHosts = 1;
/* 690 */       urlProps.setProperty("HOST.1", "localhost");
/* 691 */       urlProps.setProperty("PORT.1", "3306");
/*     */     } 
/*     */     
/* 694 */     urlProps.setProperty("NUM_HOSTS", String.valueOf(numHosts));
/* 695 */     urlProps.setProperty("HOST", urlProps.getProperty("HOST.1"));
/* 696 */     urlProps.setProperty("PORT", urlProps.getProperty("PORT.1"));
/*     */     
/* 698 */     String propertiesTransformClassName = urlProps.getProperty("propertiesTransform");
/*     */     
/* 700 */     if (propertiesTransformClassName != null) {
/*     */       try {
/* 702 */         ConnectionPropertiesTransform propTransformer = (ConnectionPropertiesTransform)Class.forName(propertiesTransformClassName).newInstance();
/*     */         
/* 704 */         urlProps = propTransformer.transformProperties(urlProps);
/* 705 */       } catch (InstantiationException e) {
/* 706 */         throw SQLError.createSQLException("Unable to create properties transform instance '" + propertiesTransformClassName + "' due to underlying exception: " + e.toString(), "01S00", null);
/*     */       
/*     */       }
/* 709 */       catch (IllegalAccessException e) {
/* 710 */         throw SQLError.createSQLException("Unable to create properties transform instance '" + propertiesTransformClassName + "' due to underlying exception: " + e.toString(), "01S00", null);
/*     */       
/*     */       }
/* 713 */       catch (ClassNotFoundException e) {
/* 714 */         throw SQLError.createSQLException("Unable to create properties transform instance '" + propertiesTransformClassName + "' due to underlying exception: " + e.toString(), "01S00", null);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 720 */     if (Util.isColdFusion() && urlProps.getProperty("autoConfigureForColdFusion", "true").equalsIgnoreCase("true")) {
/* 721 */       String configs = urlProps.getProperty("useConfigs");
/*     */       
/* 723 */       StringBuilder newConfigs = new StringBuilder();
/*     */       
/* 725 */       if (configs != null) {
/* 726 */         newConfigs.append(configs);
/* 727 */         newConfigs.append(",");
/*     */       } 
/*     */       
/* 730 */       newConfigs.append("coldFusion");
/*     */       
/* 732 */       urlProps.setProperty("useConfigs", newConfigs.toString());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 737 */     String configNames = null;
/*     */     
/* 739 */     if (defaults != null) {
/* 740 */       configNames = defaults.getProperty("useConfigs");
/*     */     }
/*     */     
/* 743 */     if (configNames == null) {
/* 744 */       configNames = urlProps.getProperty("useConfigs");
/*     */     }
/*     */     
/* 747 */     if (configNames != null) {
/* 748 */       List<String> splitNames = StringUtils.split(configNames, ",", true);
/*     */       
/* 750 */       Properties configProps = new Properties();
/*     */       
/* 752 */       Iterator<String> namesIter = splitNames.iterator();
/*     */       
/* 754 */       while (namesIter.hasNext()) {
/* 755 */         String configName = namesIter.next();
/*     */         
/*     */         try {
/* 758 */           InputStream configAsStream = getClass().getResourceAsStream("configs/" + configName + ".properties");
/*     */           
/* 760 */           if (configAsStream == null) {
/* 761 */             throw SQLError.createSQLException("Can't find configuration template named '" + configName + "'", "01S00", null);
/*     */           }
/*     */           
/* 764 */           configProps.load(configAsStream);
/* 765 */         } catch (IOException ioEx) {
/* 766 */           SQLException sqlEx = SQLError.createSQLException("Unable to load configuration template '" + configName + "' due to underlying IOException: " + ioEx, "01S00", (ExceptionInterceptor)null);
/*     */ 
/*     */           
/* 769 */           sqlEx.initCause(ioEx);
/*     */           
/* 771 */           throw sqlEx;
/*     */         } 
/*     */       } 
/*     */       
/* 775 */       Iterator<Object> propsIter = urlProps.keySet().iterator();
/*     */       
/* 777 */       while (propsIter.hasNext()) {
/* 778 */         String key = propsIter.next().toString();
/* 779 */         String property = urlProps.getProperty(key);
/* 780 */         configProps.setProperty(key, property);
/*     */       } 
/*     */       
/* 783 */       urlProps = configProps;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 788 */     if (defaults != null) {
/* 789 */       Iterator<Object> propsIter = defaults.keySet().iterator();
/*     */       
/* 791 */       while (propsIter.hasNext()) {
/* 792 */         String key = propsIter.next().toString();
/* 793 */         if (!key.equals("NUM_HOSTS")) {
/* 794 */           String property = defaults.getProperty(key);
/* 795 */           urlProps.setProperty(key, property);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 800 */     return urlProps;
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
/*     */   public int port(Properties props) {
/* 812 */     return Integer.parseInt(props.getProperty("PORT", "3306"));
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
/*     */   public String property(String name, Properties props) {
/* 826 */     return props.getProperty(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Properties expandHostKeyValues(String host) {
/* 835 */     Properties hostProps = new Properties();
/*     */     
/* 837 */     if (isHostPropertiesList(host)) {
/* 838 */       host = host.substring("address=".length() + 1);
/* 839 */       List<String> hostPropsList = StringUtils.split(host, ")", "'\"", "'\"", true);
/*     */       
/* 841 */       for (String propDef : hostPropsList) {
/* 842 */         if (propDef.startsWith("(")) {
/* 843 */           propDef = propDef.substring(1);
/*     */         }
/*     */         
/* 846 */         List<String> kvp = StringUtils.split(propDef, "=", "'\"", "'\"", true);
/*     */         
/* 848 */         String key = kvp.get(0);
/* 849 */         String value = (kvp.size() > 1) ? kvp.get(1) : null;
/*     */         
/* 851 */         if (value != null && ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("'") && value.endsWith("'")))) {
/* 852 */           value = value.substring(1, value.length() - 1);
/*     */         }
/*     */         
/* 855 */         if (value != null) {
/* 856 */           if ("HOST".equalsIgnoreCase(key) || "DBNAME".equalsIgnoreCase(key) || "PORT".equalsIgnoreCase(key) || "PROTOCOL".equalsIgnoreCase(key) || "PATH".equalsIgnoreCase(key)) {
/*     */             
/* 858 */             key = key.toUpperCase(Locale.ENGLISH);
/* 859 */           } else if ("user".equalsIgnoreCase(key) || "password".equalsIgnoreCase(key)) {
/* 860 */             key = key.toLowerCase(Locale.ENGLISH);
/*     */           } 
/*     */           
/* 863 */           hostProps.setProperty(key, value);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 868 */     return hostProps;
/*     */   }
/*     */   
/*     */   public static boolean isHostPropertiesList(String host) {
/* 872 */     return (host != null && StringUtils.startsWithIgnoreCase(host, "address="));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\NonRegisteringDriver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */