/*     */ package com.mysql.jdbc.profiler;
/*     */ 
/*     */ import com.mysql.jdbc.StringUtils;
/*     */ import com.mysql.jdbc.log.LogUtils;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProfilerEvent
/*     */ {
/*     */   public static final byte TYPE_USAGE = 0;
/*     */   public static final byte TYPE_WARN = 0;
/*     */   public static final byte TYPE_OBJECT_CREATION = 1;
/*     */   public static final byte TYPE_PREPARE = 2;
/*     */   public static final byte TYPE_QUERY = 3;
/*     */   public static final byte TYPE_EXECUTE = 4;
/*     */   public static final byte TYPE_FETCH = 5;
/*     */   public static final byte TYPE_SLOW_QUERY = 6;
/*     */   public static final byte NA = -1;
/*     */   protected byte eventType;
/*     */   protected String hostName;
/*     */   protected String catalog;
/*     */   protected long connectionId;
/*     */   protected int statementId;
/*     */   protected int resultSetId;
/*     */   protected long eventCreationTime;
/*     */   protected long eventDuration;
/*     */   protected String durationUnits;
/*     */   protected String eventCreationPointDesc;
/*     */   protected String message;
/*     */   public int hostNameIndex;
/*     */   public int catalogIndex;
/*     */   public int eventCreationPointIndex;
/*     */   
/*     */   public ProfilerEvent(byte eventType, String hostName, String catalog, long connectionId, int statementId, int resultSetId, long eventDuration, String durationUnits, Throwable eventCreationPoint, String message) {
/* 178 */     this(eventType, hostName, catalog, connectionId, statementId, resultSetId, System.currentTimeMillis(), eventDuration, durationUnits, LogUtils.findCallingClassAndMethod(eventCreationPoint), message, -1, -1, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ProfilerEvent(byte eventType, String hostName, String catalog, long connectionId, int statementId, int resultSetId, long eventCreationTime, long eventDuration, String durationUnits, String eventCreationPointDesc, String message, int hostNameIndex, int catalogIndex, int eventCreationPointIndex) {
/* 186 */     this.eventType = eventType;
/* 187 */     this.hostName = (hostName == null) ? "" : hostName;
/* 188 */     this.catalog = (catalog == null) ? "" : catalog;
/* 189 */     this.connectionId = connectionId;
/* 190 */     this.statementId = statementId;
/* 191 */     this.resultSetId = resultSetId;
/* 192 */     this.eventCreationTime = eventCreationTime;
/* 193 */     this.eventDuration = eventDuration;
/* 194 */     this.durationUnits = (durationUnits == null) ? "" : durationUnits;
/* 195 */     this.eventCreationPointDesc = (eventCreationPointDesc == null) ? "" : eventCreationPointDesc;
/* 196 */     this.message = (message == null) ? "" : message;
/* 197 */     this.hostNameIndex = hostNameIndex;
/* 198 */     this.catalogIndex = catalogIndex;
/* 199 */     this.eventCreationPointIndex = eventCreationPointIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getEventType() {
/* 208 */     return this.eventType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHostName() {
/* 217 */     return this.hostName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCatalog() {
/* 226 */     return this.catalog;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getConnectionId() {
/* 235 */     return this.connectionId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStatementId() {
/* 244 */     return this.statementId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getResultSetId() {
/* 253 */     return this.resultSetId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getEventCreationTime() {
/* 262 */     return this.eventCreationTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getEventDuration() {
/* 271 */     return this.eventDuration;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDurationUnits() {
/* 280 */     return this.durationUnits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEventCreationPointAsString() {
/* 289 */     return this.eventCreationPointDesc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/* 298 */     return this.message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 308 */     StringBuilder buf = new StringBuilder();
/* 309 */     buf.append("[");
/*     */     
/* 311 */     switch (getEventType())
/*     */     { case 4:
/* 313 */         buf.append("EXECUTE");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 336 */         buf.append("] ");
/*     */         
/* 338 */         buf.append(this.message);
/*     */         
/* 340 */         buf.append(" [Created on: ");
/* 341 */         buf.append(new Date(this.eventCreationTime));
/* 342 */         buf.append(", duration: ");
/* 343 */         buf.append(this.eventDuration);
/* 344 */         buf.append(", connection-id: ");
/* 345 */         buf.append(this.connectionId);
/* 346 */         buf.append(", statement-id: ");
/* 347 */         buf.append(this.statementId);
/* 348 */         buf.append(", resultset-id: ");
/* 349 */         buf.append(this.resultSetId);
/* 350 */         buf.append(",");
/* 351 */         buf.append(this.eventCreationPointDesc);
/* 352 */         buf.append(", hostNameIndex: ");
/* 353 */         buf.append(this.hostNameIndex);
/* 354 */         buf.append(", catalogIndex: ");
/* 355 */         buf.append(this.catalogIndex);
/* 356 */         buf.append(", eventCreationPointIndex: ");
/* 357 */         buf.append(this.eventCreationPointIndex);
/* 358 */         buf.append("]");
/*     */         
/* 360 */         return buf.toString();case 5: buf.append("FETCH"); buf.append("] "); buf.append(this.message); buf.append(" [Created on: "); buf.append(new Date(this.eventCreationTime)); buf.append(", duration: "); buf.append(this.eventDuration); buf.append(", connection-id: "); buf.append(this.connectionId); buf.append(", statement-id: "); buf.append(this.statementId); buf.append(", resultset-id: "); buf.append(this.resultSetId); buf.append(","); buf.append(this.eventCreationPointDesc); buf.append(", hostNameIndex: "); buf.append(this.hostNameIndex); buf.append(", catalogIndex: "); buf.append(this.catalogIndex); buf.append(", eventCreationPointIndex: "); buf.append(this.eventCreationPointIndex); buf.append("]"); return buf.toString();case 1: buf.append("CONSTRUCT"); buf.append("] "); buf.append(this.message); buf.append(" [Created on: "); buf.append(new Date(this.eventCreationTime)); buf.append(", duration: "); buf.append(this.eventDuration); buf.append(", connection-id: "); buf.append(this.connectionId); buf.append(", statement-id: "); buf.append(this.statementId); buf.append(", resultset-id: "); buf.append(this.resultSetId); buf.append(","); buf.append(this.eventCreationPointDesc); buf.append(", hostNameIndex: "); buf.append(this.hostNameIndex); buf.append(", catalogIndex: "); buf.append(this.catalogIndex); buf.append(", eventCreationPointIndex: "); buf.append(this.eventCreationPointIndex); buf.append("]"); return buf.toString();case 2: buf.append("PREPARE"); buf.append("] "); buf.append(this.message); buf.append(" [Created on: "); buf.append(new Date(this.eventCreationTime)); buf.append(", duration: "); buf.append(this.eventDuration); buf.append(", connection-id: "); buf.append(this.connectionId); buf.append(", statement-id: "); buf.append(this.statementId); buf.append(", resultset-id: "); buf.append(this.resultSetId); buf.append(","); buf.append(this.eventCreationPointDesc); buf.append(", hostNameIndex: "); buf.append(this.hostNameIndex); buf.append(", catalogIndex: "); buf.append(this.catalogIndex); buf.append(", eventCreationPointIndex: "); buf.append(this.eventCreationPointIndex); buf.append("]"); return buf.toString();case 3: buf.append("QUERY"); buf.append("] "); buf.append(this.message); buf.append(" [Created on: "); buf.append(new Date(this.eventCreationTime)); buf.append(", duration: "); buf.append(this.eventDuration); buf.append(", connection-id: "); buf.append(this.connectionId); buf.append(", statement-id: "); buf.append(this.statementId); buf.append(", resultset-id: "); buf.append(this.resultSetId); buf.append(","); buf.append(this.eventCreationPointDesc); buf.append(", hostNameIndex: "); buf.append(this.hostNameIndex); buf.append(", catalogIndex: "); buf.append(this.catalogIndex); buf.append(", eventCreationPointIndex: "); buf.append(this.eventCreationPointIndex); buf.append("]"); return buf.toString();case 0: buf.append("USAGE ADVISOR"); buf.append("] "); buf.append(this.message); buf.append(" [Created on: "); buf.append(new Date(this.eventCreationTime)); buf.append(", duration: "); buf.append(this.eventDuration); buf.append(", connection-id: "); buf.append(this.connectionId); buf.append(", statement-id: "); buf.append(this.statementId); buf.append(", resultset-id: "); buf.append(this.resultSetId); buf.append(","); buf.append(this.eventCreationPointDesc); buf.append(", hostNameIndex: "); buf.append(this.hostNameIndex); buf.append(", catalogIndex: "); buf.append(this.catalogIndex); buf.append(", eventCreationPointIndex: "); buf.append(this.eventCreationPointIndex); buf.append("]"); return buf.toString();case 6: buf.append("SLOW QUERY"); buf.append("] "); buf.append(this.message); buf.append(" [Created on: "); buf.append(new Date(this.eventCreationTime)); buf.append(", duration: "); buf.append(this.eventDuration); buf.append(", connection-id: "); buf.append(this.connectionId); buf.append(", statement-id: "); buf.append(this.statementId); buf.append(", resultset-id: "); buf.append(this.resultSetId); buf.append(","); buf.append(this.eventCreationPointDesc); buf.append(", hostNameIndex: "); buf.append(this.hostNameIndex); buf.append(", catalogIndex: "); buf.append(this.catalogIndex); buf.append(", eventCreationPointIndex: "); buf.append(this.eventCreationPointIndex); buf.append("]"); return buf.toString(); }  buf.append("UNKNOWN"); buf.append("] "); buf.append(this.message); buf.append(" [Created on: "); buf.append(new Date(this.eventCreationTime)); buf.append(", duration: "); buf.append(this.eventDuration); buf.append(", connection-id: "); buf.append(this.connectionId); buf.append(", statement-id: "); buf.append(this.statementId); buf.append(", resultset-id: "); buf.append(this.resultSetId); buf.append(","); buf.append(this.eventCreationPointDesc); buf.append(", hostNameIndex: "); buf.append(this.hostNameIndex); buf.append(", catalogIndex: "); buf.append(this.catalogIndex); buf.append(", eventCreationPointIndex: "); buf.append(this.eventCreationPointIndex); buf.append("]"); return buf.toString();
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
/*     */   public static ProfilerEvent unpack(byte[] buf) throws Exception {
/* 373 */     int pos = 0;
/*     */     
/* 375 */     byte eventType = buf[pos++];
/*     */     
/* 377 */     byte[] host = readBytes(buf, pos);
/* 378 */     pos += 4 + host.length;
/*     */     
/* 380 */     byte[] db = readBytes(buf, pos);
/* 381 */     pos += 4 + db.length;
/*     */     
/* 383 */     long connectionId = readLong(buf, pos);
/* 384 */     pos += 8;
/* 385 */     int statementId = readInt(buf, pos);
/* 386 */     pos += 4;
/* 387 */     int resultSetId = readInt(buf, pos);
/* 388 */     pos += 4;
/* 389 */     long eventCreationTime = readLong(buf, pos);
/* 390 */     pos += 8;
/* 391 */     long eventDuration = readLong(buf, pos);
/* 392 */     pos += 8;
/*     */     
/* 394 */     byte[] eventDurationUnits = readBytes(buf, pos);
/* 395 */     pos += 4 + eventDurationUnits.length;
/*     */     
/* 397 */     byte[] eventCreationAsBytes = readBytes(buf, pos);
/* 398 */     pos += 4 + eventCreationAsBytes.length;
/*     */     
/* 400 */     byte[] message = readBytes(buf, pos);
/* 401 */     pos += 4 + message.length;
/*     */     
/* 403 */     int hostNameIndex = readInt(buf, pos);
/* 404 */     pos += 4;
/* 405 */     int catalogIndex = readInt(buf, pos);
/* 406 */     pos += 4;
/* 407 */     int eventCreationPointIndex = readInt(buf, pos);
/* 408 */     pos += 4;
/*     */ 
/*     */     
/* 411 */     return new ProfilerEvent(eventType, StringUtils.toString(host, "ISO8859_1"), StringUtils.toString(db, "ISO8859_1"), connectionId, statementId, resultSetId, eventCreationTime, eventDuration, StringUtils.toString(eventDurationUnits, "ISO8859_1"), StringUtils.toString(eventCreationAsBytes, "ISO8859_1"), StringUtils.toString(message, "ISO8859_1"), hostNameIndex, catalogIndex, eventCreationPointIndex);
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
/*     */   public byte[] pack() throws Exception {
/* 427 */     byte[] hostNameAsBytes = StringUtils.getBytes(this.hostName, "ISO8859_1");
/* 428 */     byte[] dbAsBytes = StringUtils.getBytes(this.catalog, "ISO8859_1");
/* 429 */     byte[] durationUnitsAsBytes = StringUtils.getBytes(this.durationUnits, "ISO8859_1");
/* 430 */     byte[] eventCreationAsBytes = StringUtils.getBytes(this.eventCreationPointDesc, "ISO8859_1");
/* 431 */     byte[] messageAsBytes = StringUtils.getBytes(this.message, "ISO8859_1");
/*     */     
/* 433 */     int len = 1 + 4 + hostNameAsBytes.length + 4 + dbAsBytes.length + 8 + 4 + 4 + 8 + 8 + 4 + durationUnitsAsBytes.length + 4 + eventCreationAsBytes.length + 4 + messageAsBytes.length + 4 + 4 + 4;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 438 */     byte[] buf = new byte[len];
/* 439 */     int pos = 0;
/* 440 */     buf[pos++] = this.eventType;
/* 441 */     pos = writeBytes(hostNameAsBytes, buf, pos);
/* 442 */     pos = writeBytes(dbAsBytes, buf, pos);
/* 443 */     pos = writeLong(this.connectionId, buf, pos);
/* 444 */     pos = writeInt(this.statementId, buf, pos);
/* 445 */     pos = writeInt(this.resultSetId, buf, pos);
/* 446 */     pos = writeLong(this.eventCreationTime, buf, pos);
/* 447 */     pos = writeLong(this.eventDuration, buf, pos);
/* 448 */     pos = writeBytes(durationUnitsAsBytes, buf, pos);
/* 449 */     pos = writeBytes(eventCreationAsBytes, buf, pos);
/* 450 */     pos = writeBytes(messageAsBytes, buf, pos);
/*     */     
/* 452 */     pos = writeInt(this.hostNameIndex, buf, pos);
/* 453 */     pos = writeInt(this.catalogIndex, buf, pos);
/* 454 */     pos = writeInt(this.eventCreationPointIndex, buf, pos);
/*     */     
/* 456 */     return buf;
/*     */   }
/*     */   
/*     */   private static int writeInt(int i, byte[] buf, int pos) {
/* 460 */     buf[pos++] = (byte)(i & 0xFF);
/* 461 */     buf[pos++] = (byte)(i >>> 8);
/* 462 */     buf[pos++] = (byte)(i >>> 16);
/* 463 */     buf[pos++] = (byte)(i >>> 24);
/* 464 */     return pos;
/*     */   }
/*     */   
/*     */   private static int writeLong(long l, byte[] buf, int pos) {
/* 468 */     buf[pos++] = (byte)(int)(l & 0xFFL);
/* 469 */     buf[pos++] = (byte)(int)(l >>> 8L);
/* 470 */     buf[pos++] = (byte)(int)(l >>> 16L);
/* 471 */     buf[pos++] = (byte)(int)(l >>> 24L);
/* 472 */     buf[pos++] = (byte)(int)(l >>> 32L);
/* 473 */     buf[pos++] = (byte)(int)(l >>> 40L);
/* 474 */     buf[pos++] = (byte)(int)(l >>> 48L);
/* 475 */     buf[pos++] = (byte)(int)(l >>> 56L);
/* 476 */     return pos;
/*     */   }
/*     */   
/*     */   private static int writeBytes(byte[] msg, byte[] buf, int pos) {
/* 480 */     pos = writeInt(msg.length, buf, pos);
/* 481 */     System.arraycopy(msg, 0, buf, pos, msg.length);
/* 482 */     return pos + msg.length;
/*     */   }
/*     */   
/*     */   private static int readInt(byte[] buf, int pos) {
/* 486 */     return buf[pos++] & 0xFF | (buf[pos++] & 0xFF) << 8 | (buf[pos++] & 0xFF) << 16 | (buf[pos++] & 0xFF) << 24;
/*     */   }
/*     */   
/*     */   private static long readLong(byte[] buf, int pos) {
/* 490 */     return (buf[pos++] & 0xFF) | (buf[pos++] & 0xFF) << 8L | (buf[pos++] & 0xFF) << 16L | (buf[pos++] & 0xFF) << 24L | (buf[pos++] & 0xFF) << 32L | (buf[pos++] & 0xFF) << 40L | (buf[pos++] & 0xFF) << 48L | (buf[pos++] & 0xFF) << 56L;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] readBytes(byte[] buf, int pos) {
/* 496 */     int length = readInt(buf, pos);
/* 497 */     byte[] msg = new byte[length];
/* 498 */     System.arraycopy(buf, pos + 4, msg, 0, length);
/* 499 */     return msg;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\profiler\ProfilerEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */