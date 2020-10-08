/*     */ package org.apache.logging.slf4j;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.message.Message;
/*     */ import org.apache.logging.log4j.message.ParameterizedMessage;
/*     */ import org.apache.logging.log4j.message.SimpleMessage;
/*     */ import org.apache.logging.log4j.spi.ExtendedLogger;
/*     */ import org.apache.logging.log4j.util.LoaderUtil;
/*     */ import org.slf4j.Marker;
/*     */ import org.slf4j.MarkerFactory;
/*     */ import org.slf4j.impl.StaticMarkerBinder;
/*     */ import org.slf4j.spi.LocationAwareLogger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Log4jLogger
/*     */   implements LocationAwareLogger, Serializable
/*     */ {
/*  41 */   public static final String FQCN = Log4jLogger.class.getName();
/*     */   
/*     */   private static final long serialVersionUID = 7869000638091304316L;
/*  44 */   private static final Marker EVENT_MARKER = MarkerFactory.getMarker("EVENT");
/*     */   private final boolean eventLogger;
/*     */   private transient ExtendedLogger logger;
/*     */   private final String name;
/*     */   private transient EventDataConverter converter;
/*     */   
/*     */   public Log4jLogger(ExtendedLogger logger, String name) {
/*  51 */     this.logger = logger;
/*  52 */     this.eventLogger = "EventLogger".equals(name);
/*  53 */     this.name = name;
/*  54 */     this.converter = createConverter();
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(String format) {
/*  59 */     this.logger.logIfEnabled(FQCN, Level.TRACE, null, format);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(String format, Object o) {
/*  64 */     this.logger.logIfEnabled(FQCN, Level.TRACE, null, format, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(String format, Object arg1, Object arg2) {
/*  69 */     this.logger.logIfEnabled(FQCN, Level.TRACE, null, format, arg1, arg2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(String format, Object... args) {
/*  74 */     this.logger.logIfEnabled(FQCN, Level.TRACE, null, format, args);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(String format, Throwable t) {
/*  79 */     this.logger.logIfEnabled(FQCN, Level.TRACE, null, format, t);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTraceEnabled() {
/*  84 */     return this.logger.isEnabled(Level.TRACE, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTraceEnabled(Marker marker) {
/*  89 */     return this.logger.isEnabled(Level.TRACE, getMarker(marker), null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Marker marker, String s) {
/*  94 */     this.logger.logIfEnabled(FQCN, Level.TRACE, getMarker(marker), s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Marker marker, String s, Object o) {
/*  99 */     this.logger.logIfEnabled(FQCN, Level.TRACE, getMarker(marker), s, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Marker marker, String s, Object o, Object o1) {
/* 104 */     this.logger.logIfEnabled(FQCN, Level.TRACE, getMarker(marker), s, o, o1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Marker marker, String s, Object... objects) {
/* 109 */     this.logger.logIfEnabled(FQCN, Level.TRACE, getMarker(marker), s, objects);
/*     */   }
/*     */ 
/*     */   
/*     */   public void trace(Marker marker, String s, Throwable throwable) {
/* 114 */     this.logger.logIfEnabled(FQCN, Level.TRACE, getMarker(marker), s, throwable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(String format) {
/* 119 */     this.logger.logIfEnabled(FQCN, Level.DEBUG, null, format);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(String format, Object o) {
/* 124 */     this.logger.logIfEnabled(FQCN, Level.DEBUG, null, format, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(String format, Object arg1, Object arg2) {
/* 129 */     this.logger.logIfEnabled(FQCN, Level.DEBUG, null, format, arg1, arg2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(String format, Object... args) {
/* 134 */     this.logger.logIfEnabled(FQCN, Level.DEBUG, null, format, args);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(String format, Throwable t) {
/* 139 */     this.logger.logIfEnabled(FQCN, Level.DEBUG, null, format, t);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDebugEnabled() {
/* 144 */     return this.logger.isEnabled(Level.DEBUG, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDebugEnabled(Marker marker) {
/* 149 */     return this.logger.isEnabled(Level.DEBUG, getMarker(marker), null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(Marker marker, String s) {
/* 154 */     this.logger.logIfEnabled(FQCN, Level.DEBUG, getMarker(marker), s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(Marker marker, String s, Object o) {
/* 159 */     this.logger.logIfEnabled(FQCN, Level.DEBUG, getMarker(marker), s, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(Marker marker, String s, Object o, Object o1) {
/* 164 */     this.logger.logIfEnabled(FQCN, Level.DEBUG, getMarker(marker), s, o, o1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(Marker marker, String s, Object... objects) {
/* 169 */     this.logger.logIfEnabled(FQCN, Level.DEBUG, getMarker(marker), s, objects);
/*     */   }
/*     */ 
/*     */   
/*     */   public void debug(Marker marker, String s, Throwable throwable) {
/* 174 */     this.logger.logIfEnabled(FQCN, Level.DEBUG, getMarker(marker), s, throwable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(String format) {
/* 179 */     this.logger.logIfEnabled(FQCN, Level.INFO, null, format);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(String format, Object o) {
/* 184 */     this.logger.logIfEnabled(FQCN, Level.INFO, null, format, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(String format, Object arg1, Object arg2) {
/* 189 */     this.logger.logIfEnabled(FQCN, Level.INFO, null, format, arg1, arg2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(String format, Object... args) {
/* 194 */     this.logger.logIfEnabled(FQCN, Level.INFO, null, format, args);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(String format, Throwable t) {
/* 199 */     this.logger.logIfEnabled(FQCN, Level.INFO, null, format, t);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInfoEnabled() {
/* 204 */     return this.logger.isEnabled(Level.INFO, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInfoEnabled(Marker marker) {
/* 209 */     return this.logger.isEnabled(Level.INFO, getMarker(marker), null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(Marker marker, String s) {
/* 214 */     this.logger.logIfEnabled(FQCN, Level.INFO, getMarker(marker), s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(Marker marker, String s, Object o) {
/* 219 */     this.logger.logIfEnabled(FQCN, Level.INFO, getMarker(marker), s, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(Marker marker, String s, Object o, Object o1) {
/* 224 */     this.logger.logIfEnabled(FQCN, Level.INFO, getMarker(marker), s, o, o1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(Marker marker, String s, Object... objects) {
/* 229 */     this.logger.logIfEnabled(FQCN, Level.INFO, getMarker(marker), s, objects);
/*     */   }
/*     */ 
/*     */   
/*     */   public void info(Marker marker, String s, Throwable throwable) {
/* 234 */     this.logger.logIfEnabled(FQCN, Level.INFO, getMarker(marker), s, throwable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(String format) {
/* 239 */     this.logger.logIfEnabled(FQCN, Level.WARN, null, format);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(String format, Object o) {
/* 244 */     this.logger.logIfEnabled(FQCN, Level.WARN, null, format, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(String format, Object arg1, Object arg2) {
/* 249 */     this.logger.logIfEnabled(FQCN, Level.WARN, null, format, arg1, arg2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(String format, Object... args) {
/* 254 */     this.logger.logIfEnabled(FQCN, Level.WARN, null, format, args);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(String format, Throwable t) {
/* 259 */     this.logger.logIfEnabled(FQCN, Level.WARN, null, format, t);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWarnEnabled() {
/* 264 */     return this.logger.isEnabled(Level.WARN, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWarnEnabled(Marker marker) {
/* 269 */     return this.logger.isEnabled(Level.WARN, getMarker(marker), null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(Marker marker, String s) {
/* 274 */     this.logger.logIfEnabled(FQCN, Level.WARN, getMarker(marker), s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(Marker marker, String s, Object o) {
/* 279 */     this.logger.logIfEnabled(FQCN, Level.WARN, getMarker(marker), s, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(Marker marker, String s, Object o, Object o1) {
/* 284 */     this.logger.logIfEnabled(FQCN, Level.WARN, getMarker(marker), s, o, o1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(Marker marker, String s, Object... objects) {
/* 289 */     this.logger.logIfEnabled(FQCN, Level.WARN, getMarker(marker), s, objects);
/*     */   }
/*     */ 
/*     */   
/*     */   public void warn(Marker marker, String s, Throwable throwable) {
/* 294 */     this.logger.logIfEnabled(FQCN, Level.WARN, getMarker(marker), s, throwable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(String format) {
/* 299 */     this.logger.logIfEnabled(FQCN, Level.ERROR, null, format);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(String format, Object o) {
/* 304 */     this.logger.logIfEnabled(FQCN, Level.ERROR, null, format, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(String format, Object arg1, Object arg2) {
/* 309 */     this.logger.logIfEnabled(FQCN, Level.ERROR, null, format, arg1, arg2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(String format, Object... args) {
/* 314 */     this.logger.logIfEnabled(FQCN, Level.ERROR, null, format, args);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(String format, Throwable t) {
/* 319 */     this.logger.logIfEnabled(FQCN, Level.ERROR, null, format, t);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isErrorEnabled() {
/* 324 */     return this.logger.isEnabled(Level.ERROR, null, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isErrorEnabled(Marker marker) {
/* 329 */     return this.logger.isEnabled(Level.ERROR, getMarker(marker), null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(Marker marker, String s) {
/* 334 */     this.logger.logIfEnabled(FQCN, Level.ERROR, getMarker(marker), s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(Marker marker, String s, Object o) {
/* 339 */     this.logger.logIfEnabled(FQCN, Level.ERROR, getMarker(marker), s, o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(Marker marker, String s, Object o, Object o1) {
/* 344 */     this.logger.logIfEnabled(FQCN, Level.ERROR, getMarker(marker), s, o, o1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(Marker marker, String s, Object... objects) {
/* 349 */     this.logger.logIfEnabled(FQCN, Level.ERROR, getMarker(marker), s, objects);
/*     */   }
/*     */ 
/*     */   
/*     */   public void error(Marker marker, String s, Throwable throwable) {
/* 354 */     this.logger.logIfEnabled(FQCN, Level.ERROR, getMarker(marker), s, throwable);
/*     */   }
/*     */   
/*     */   public void log(Marker marker, String fqcn, int level, String message, Object[] params, Throwable throwable) {
/*     */     ParameterizedMessage parameterizedMessage;
/* 359 */     Level log4jLevel = getLevel(level);
/* 360 */     Marker log4jMarker = getMarker(marker);
/*     */     
/* 362 */     if (!this.logger.isEnabled(log4jLevel, log4jMarker, message, params)) {
/*     */       return;
/*     */     }
/*     */     
/* 366 */     if (this.eventLogger && marker != null && marker.contains(EVENT_MARKER) && this.converter != null) {
/* 367 */       Message msg = this.converter.convertEvent(message, params, throwable);
/* 368 */     } else if (params == null) {
/* 369 */       SimpleMessage simpleMessage = new SimpleMessage(message);
/*     */     } else {
/* 371 */       parameterizedMessage = new ParameterizedMessage(message, params, throwable);
/* 372 */       if (throwable != null) {
/* 373 */         throwable = parameterizedMessage.getThrowable();
/*     */       }
/*     */     } 
/* 376 */     this.logger.logMessage(fqcn, log4jLevel, log4jMarker, (Message)parameterizedMessage, throwable);
/*     */   }
/*     */   
/*     */   private static Marker getMarker(Marker marker) {
/* 380 */     if (marker == null)
/* 381 */       return null; 
/* 382 */     if (marker instanceof Log4jMarker) {
/* 383 */       return ((Log4jMarker)marker).getLog4jMarker();
/*     */     }
/* 385 */     Log4jMarkerFactory factory = (Log4jMarkerFactory)StaticMarkerBinder.SINGLETON.getMarkerFactory();
/* 386 */     return ((Log4jMarker)factory.getMarker(marker)).getLog4jMarker();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 392 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
/* 401 */     aInputStream.defaultReadObject();
/* 402 */     this.logger = LogManager.getContext().getLogger(this.name);
/* 403 */     this.converter = createConverter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
/* 411 */     aOutputStream.defaultWriteObject();
/*     */   }
/*     */   
/*     */   private static EventDataConverter createConverter() {
/*     */     try {
/* 416 */       LoaderUtil.loadClass("org.slf4j.ext.EventData");
/* 417 */       return new EventDataConverter();
/* 418 */     } catch (ClassNotFoundException cnfe) {
/* 419 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Level getLevel(int i) {
/* 424 */     switch (i) {
/*     */       case 0:
/* 426 */         return Level.TRACE;
/*     */       case 10:
/* 428 */         return Level.DEBUG;
/*     */       case 20:
/* 430 */         return Level.INFO;
/*     */       case 30:
/* 432 */         return Level.WARN;
/*     */       case 40:
/* 434 */         return Level.ERROR;
/*     */     } 
/* 436 */     return Level.ERROR;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\slf4j\Log4jLogger.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */