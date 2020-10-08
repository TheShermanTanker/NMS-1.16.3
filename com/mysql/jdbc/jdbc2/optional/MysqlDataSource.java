/*     */ package com.mysql.jdbc.jdbc2.optional;
/*     */ 
/*     */ import com.mysql.jdbc.ConnectionPropertiesImpl;
/*     */ import com.mysql.jdbc.Messages;
/*     */ import com.mysql.jdbc.NonRegisteringDriver;
/*     */ import com.mysql.jdbc.SQLError;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Properties;
/*     */ import javax.naming.NamingException;
/*     */ import javax.naming.Reference;
/*     */ import javax.naming.Referenceable;
/*     */ import javax.naming.StringRefAddr;
/*     */ import javax.sql.DataSource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MysqlDataSource
/*     */   extends ConnectionPropertiesImpl
/*     */   implements DataSource, Referenceable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -5515846944416881264L;
/*     */   protected static final NonRegisteringDriver mysqlDriver;
/*     */   
/*     */   static {
/*     */     try {
/*  55 */       mysqlDriver = new NonRegisteringDriver();
/*  56 */     } catch (Exception E) {
/*  57 */       throw new RuntimeException("Can not load Driver class com.mysql.jdbc.Driver");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  62 */   protected transient PrintWriter logWriter = null;
/*     */ 
/*     */   
/*  65 */   protected String databaseName = null;
/*     */ 
/*     */   
/*  68 */   protected String encoding = null;
/*     */ 
/*     */   
/*  71 */   protected String hostName = null;
/*     */ 
/*     */   
/*  74 */   protected String password = null;
/*     */ 
/*     */   
/*  77 */   protected String profileSql = "false";
/*     */ 
/*     */   
/*  80 */   protected String url = null;
/*     */ 
/*     */   
/*  83 */   protected String user = null;
/*     */ 
/*     */   
/*     */   protected boolean explicitUrl = false;
/*     */ 
/*     */   
/*  89 */   protected int port = 3306;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Connection getConnection() throws SQLException {
/* 107 */     return getConnection(this.user, this.password);
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
/*     */   public Connection getConnection(String userID, String pass) throws SQLException {
/* 124 */     Properties props = new Properties();
/*     */     
/* 126 */     if (userID != null) {
/* 127 */       props.setProperty("user", userID);
/*     */     }
/*     */     
/* 130 */     if (pass != null) {
/* 131 */       props.setProperty("password", pass);
/*     */     }
/*     */     
/* 134 */     exposeAsProperties(props);
/*     */     
/* 136 */     return getConnection(props);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDatabaseName(String dbName) {
/* 146 */     this.databaseName = dbName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDatabaseName() {
/* 155 */     return (this.databaseName != null) ? this.databaseName : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLogWriter(PrintWriter output) throws SQLException {
/* 164 */     this.logWriter = output;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrintWriter getLogWriter() {
/* 173 */     return this.logWriter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLoginTimeout(int seconds) throws SQLException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLoginTimeout() {
/* 190 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPassword(String pass) {
/* 200 */     this.password = pass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPort(int p) {
/* 210 */     this.port = p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPort() {
/* 219 */     return this.port;
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
/*     */   public void setPortNumber(int p) {
/* 231 */     setPort(p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPortNumber() {
/* 240 */     return getPort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPropertiesViaRef(Reference ref) throws SQLException {
/* 249 */     initializeFromRef(ref);
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
/*     */   public Reference getReference() throws NamingException {
/* 261 */     String factoryName = "com.mysql.jdbc.jdbc2.optional.MysqlDataSourceFactory";
/* 262 */     Reference ref = new Reference(getClass().getName(), factoryName, null);
/* 263 */     ref.add(new StringRefAddr("user", getUser()));
/* 264 */     ref.add(new StringRefAddr("password", this.password));
/* 265 */     ref.add(new StringRefAddr("serverName", getServerName()));
/* 266 */     ref.add(new StringRefAddr("port", "" + getPort()));
/* 267 */     ref.add(new StringRefAddr("databaseName", getDatabaseName()));
/* 268 */     ref.add(new StringRefAddr("url", getUrl()));
/* 269 */     ref.add(new StringRefAddr("explicitUrl", String.valueOf(this.explicitUrl)));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 275 */       storeToRef(ref);
/* 276 */     } catch (SQLException sqlEx) {
/* 277 */       throw new NamingException(sqlEx.getMessage());
/*     */     } 
/*     */     
/* 280 */     return ref;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerName(String serverName) {
/* 290 */     this.hostName = serverName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getServerName() {
/* 299 */     return (this.hostName != null) ? this.hostName : "";
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
/*     */   public void setURL(String url) {
/* 313 */     setUrl(url);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURL() {
/* 322 */     return getUrl();
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
/*     */   public void setUrl(String url) {
/* 334 */     this.url = url;
/* 335 */     this.explicitUrl = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUrl() {
/* 344 */     if (!this.explicitUrl) {
/* 345 */       String builtUrl = "jdbc:mysql://";
/* 346 */       builtUrl = builtUrl + getServerName() + ":" + getPort() + "/" + getDatabaseName();
/*     */       
/* 348 */       return builtUrl;
/*     */     } 
/*     */     
/* 351 */     return this.url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUser(String userID) {
/* 361 */     this.user = userID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUser() {
/* 370 */     return this.user;
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
/*     */   protected Connection getConnection(Properties props) throws SQLException {
/* 385 */     String jdbcUrlToUse = null;
/*     */     
/* 387 */     if (!this.explicitUrl) {
/* 388 */       StringBuilder jdbcUrl = new StringBuilder("jdbc:mysql://");
/*     */       
/* 390 */       if (this.hostName != null) {
/* 391 */         jdbcUrl.append(this.hostName);
/*     */       }
/*     */       
/* 394 */       jdbcUrl.append(":");
/* 395 */       jdbcUrl.append(this.port);
/* 396 */       jdbcUrl.append("/");
/*     */       
/* 398 */       if (this.databaseName != null) {
/* 399 */         jdbcUrl.append(this.databaseName);
/*     */       }
/*     */       
/* 402 */       jdbcUrlToUse = jdbcUrl.toString();
/*     */     } else {
/* 404 */       jdbcUrlToUse = this.url;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 411 */     Properties urlProps = mysqlDriver.parseURL(jdbcUrlToUse, null);
/* 412 */     if (urlProps == null) {
/* 413 */       throw SQLError.createSQLException(Messages.getString("MysqlDataSource.BadUrl", new Object[] { jdbcUrlToUse }), "08006", null);
/*     */     }
/*     */     
/* 416 */     urlProps.remove("DBNAME");
/* 417 */     urlProps.remove("HOST");
/* 418 */     urlProps.remove("PORT");
/*     */     
/* 420 */     Iterator<Object> keys = urlProps.keySet().iterator();
/*     */     
/* 422 */     while (keys.hasNext()) {
/* 423 */       String key = (String)keys.next();
/*     */       
/* 425 */       props.setProperty(key, urlProps.getProperty(key));
/*     */     } 
/*     */     
/* 428 */     return mysqlDriver.connect(jdbcUrlToUse, props);
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
/*     */   public Properties exposeAsProperties(Properties props) throws SQLException {
/* 441 */     return exposeAsProperties(props, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\MysqlDataSource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */