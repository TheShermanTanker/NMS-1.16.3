/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.sql.Date;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Time;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BufferRow
/*     */   extends ResultSetRow
/*     */ {
/*     */   private Buffer rowFromServer;
/*  55 */   private int homePosition = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private int preNullBitmaskHomePosition = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private int lastRequestedIndex = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   private int lastRequestedPos;
/*     */ 
/*     */ 
/*     */   
/*     */   private Field[] metadata;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isBinaryEncoded;
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean[] isNull;
/*     */ 
/*     */ 
/*     */   
/*     */   private List<InputStream> openStreams;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferRow(Buffer buf, Field[] fields, boolean isBinaryEncoded, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  92 */     super(exceptionInterceptor);
/*     */     
/*  94 */     this.rowFromServer = buf;
/*  95 */     this.metadata = fields;
/*  96 */     this.isBinaryEncoded = isBinaryEncoded;
/*  97 */     this.homePosition = this.rowFromServer.getPosition();
/*  98 */     this.preNullBitmaskHomePosition = this.homePosition;
/*     */     
/* 100 */     if (fields != null) {
/* 101 */       setMetadata(fields);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void closeOpenStreams() {
/* 107 */     if (this.openStreams != null) {
/*     */ 
/*     */ 
/*     */       
/* 111 */       Iterator<InputStream> iter = this.openStreams.iterator();
/*     */       
/* 113 */       while (iter.hasNext()) {
/*     */         
/*     */         try {
/* 116 */           ((InputStream)iter.next()).close();
/* 117 */         } catch (IOException e) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 122 */       this.openStreams.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   private int findAndSeekToOffset(int index) throws SQLException {
/* 127 */     if (!this.isBinaryEncoded) {
/*     */       
/* 129 */       if (index == 0) {
/* 130 */         this.lastRequestedIndex = 0;
/* 131 */         this.lastRequestedPos = this.homePosition;
/* 132 */         this.rowFromServer.setPosition(this.homePosition);
/*     */         
/* 134 */         return 0;
/*     */       } 
/*     */       
/* 137 */       if (index == this.lastRequestedIndex) {
/* 138 */         this.rowFromServer.setPosition(this.lastRequestedPos);
/*     */         
/* 140 */         return this.lastRequestedPos;
/*     */       } 
/*     */       
/* 143 */       int startingIndex = 0;
/*     */       
/* 145 */       if (index > this.lastRequestedIndex) {
/* 146 */         if (this.lastRequestedIndex >= 0) {
/* 147 */           startingIndex = this.lastRequestedIndex;
/*     */         } else {
/* 149 */           startingIndex = 0;
/*     */         } 
/*     */         
/* 152 */         this.rowFromServer.setPosition(this.lastRequestedPos);
/*     */       } else {
/* 154 */         this.rowFromServer.setPosition(this.homePosition);
/*     */       } 
/*     */       
/* 157 */       for (int i = startingIndex; i < index; i++) {
/* 158 */         this.rowFromServer.fastSkipLenByteArray();
/*     */       }
/*     */       
/* 161 */       this.lastRequestedIndex = index;
/* 162 */       this.lastRequestedPos = this.rowFromServer.getPosition();
/*     */       
/* 164 */       return this.lastRequestedPos;
/*     */     } 
/*     */     
/* 167 */     return findAndSeekToOffsetForBinaryEncoding(index);
/*     */   }
/*     */   
/*     */   private int findAndSeekToOffsetForBinaryEncoding(int index) throws SQLException {
/* 171 */     if (index == 0) {
/* 172 */       this.lastRequestedIndex = 0;
/* 173 */       this.lastRequestedPos = this.homePosition;
/* 174 */       this.rowFromServer.setPosition(this.homePosition);
/*     */       
/* 176 */       return 0;
/*     */     } 
/*     */     
/* 179 */     if (index == this.lastRequestedIndex) {
/* 180 */       this.rowFromServer.setPosition(this.lastRequestedPos);
/*     */       
/* 182 */       return this.lastRequestedPos;
/*     */     } 
/*     */     
/* 185 */     int startingIndex = 0;
/*     */     
/* 187 */     if (index > this.lastRequestedIndex) {
/* 188 */       if (this.lastRequestedIndex >= 0) {
/* 189 */         startingIndex = this.lastRequestedIndex;
/*     */       } else {
/*     */         
/* 192 */         startingIndex = 0;
/* 193 */         this.lastRequestedPos = this.homePosition;
/*     */       } 
/*     */       
/* 196 */       this.rowFromServer.setPosition(this.lastRequestedPos);
/*     */     } else {
/* 198 */       this.rowFromServer.setPosition(this.homePosition);
/*     */     } 
/*     */     
/* 201 */     for (int i = startingIndex; i < index; i++) {
/* 202 */       if (!this.isNull[i]) {
/*     */ 
/*     */ 
/*     */         
/* 206 */         int curPosition = this.rowFromServer.getPosition();
/*     */         
/* 208 */         switch (this.metadata[i].getMysqlType()) {
/*     */           case 6:
/*     */             break;
/*     */ 
/*     */           
/*     */           case 1:
/* 214 */             this.rowFromServer.setPosition(curPosition + 1);
/*     */             break;
/*     */           
/*     */           case 2:
/*     */           case 13:
/* 219 */             this.rowFromServer.setPosition(curPosition + 2);
/*     */             break;
/*     */           
/*     */           case 3:
/*     */           case 9:
/* 224 */             this.rowFromServer.setPosition(curPosition + 4);
/*     */             break;
/*     */           
/*     */           case 8:
/* 228 */             this.rowFromServer.setPosition(curPosition + 8);
/*     */             break;
/*     */           
/*     */           case 4:
/* 232 */             this.rowFromServer.setPosition(curPosition + 4);
/*     */             break;
/*     */           
/*     */           case 5:
/* 236 */             this.rowFromServer.setPosition(curPosition + 8);
/*     */             break;
/*     */           
/*     */           case 11:
/* 240 */             this.rowFromServer.fastSkipLenByteArray();
/*     */             break;
/*     */ 
/*     */           
/*     */           case 10:
/* 245 */             this.rowFromServer.fastSkipLenByteArray();
/*     */             break;
/*     */           
/*     */           case 7:
/*     */           case 12:
/* 250 */             this.rowFromServer.fastSkipLenByteArray();
/*     */             break;
/*     */           
/*     */           case 0:
/*     */           case 15:
/*     */           case 16:
/*     */           case 245:
/*     */           case 246:
/*     */           case 249:
/*     */           case 250:
/*     */           case 251:
/*     */           case 252:
/*     */           case 253:
/*     */           case 254:
/*     */           case 255:
/* 265 */             this.rowFromServer.fastSkipLenByteArray();
/*     */             break;
/*     */ 
/*     */           
/*     */           default:
/* 270 */             throw SQLError.createSQLException(Messages.getString("MysqlIO.97") + this.metadata[i].getMysqlType() + Messages.getString("MysqlIO.98") + (i + 1) + Messages.getString("MysqlIO.99") + this.metadata.length + Messages.getString("MysqlIO.100"), "S1000", this.exceptionInterceptor);
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 277 */     this.lastRequestedIndex = index;
/* 278 */     this.lastRequestedPos = this.rowFromServer.getPosition();
/*     */     
/* 280 */     return this.lastRequestedPos;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized InputStream getBinaryInputStream(int columnIndex) throws SQLException {
/* 285 */     if (this.isBinaryEncoded && 
/* 286 */       isNull(columnIndex)) {
/* 287 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 291 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 293 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 295 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 297 */     if (length == -1L) {
/* 298 */       return null;
/*     */     }
/*     */     
/* 301 */     InputStream stream = new ByteArrayInputStream(this.rowFromServer.getByteBuffer(), offset, (int)length);
/*     */     
/* 303 */     if (this.openStreams == null) {
/* 304 */       this.openStreams = new LinkedList<InputStream>();
/*     */     }
/*     */     
/* 307 */     return stream;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getColumnValue(int index) throws SQLException {
/* 312 */     findAndSeekToOffset(index);
/*     */     
/* 314 */     if (!this.isBinaryEncoded) {
/* 315 */       return this.rowFromServer.readLenByteArray(0);
/*     */     }
/*     */     
/* 318 */     if (this.isNull[index]) {
/* 319 */       return null;
/*     */     }
/*     */     
/* 322 */     switch (this.metadata[index].getMysqlType()) {
/*     */       case 6:
/* 324 */         return null;
/*     */       
/*     */       case 1:
/* 327 */         return new byte[] { this.rowFromServer.readByte() };
/*     */       
/*     */       case 2:
/*     */       case 13:
/* 331 */         return this.rowFromServer.getBytes(2);
/*     */       
/*     */       case 3:
/*     */       case 9:
/* 335 */         return this.rowFromServer.getBytes(4);
/*     */       
/*     */       case 8:
/* 338 */         return this.rowFromServer.getBytes(8);
/*     */       
/*     */       case 4:
/* 341 */         return this.rowFromServer.getBytes(4);
/*     */       
/*     */       case 5:
/* 344 */         return this.rowFromServer.getBytes(8);
/*     */       
/*     */       case 0:
/*     */       case 7:
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/*     */       case 15:
/*     */       case 16:
/*     */       case 245:
/*     */       case 246:
/*     */       case 249:
/*     */       case 250:
/*     */       case 251:
/*     */       case 252:
/*     */       case 253:
/*     */       case 254:
/*     */       case 255:
/* 362 */         return this.rowFromServer.readLenByteArray(0);
/*     */     } 
/*     */     
/* 365 */     throw SQLError.createSQLException(Messages.getString("MysqlIO.97") + this.metadata[index].getMysqlType() + Messages.getString("MysqlIO.98") + (index + 1) + Messages.getString("MysqlIO.99") + this.metadata.length + Messages.getString("MysqlIO.100"), "S1000", this.exceptionInterceptor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt(int columnIndex) throws SQLException {
/* 375 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 377 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 379 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 381 */     if (length == -1L) {
/* 382 */       return 0;
/*     */     }
/*     */     
/* 385 */     return StringUtils.getInt(this.rowFromServer.getByteBuffer(), offset, offset + (int)length);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong(int columnIndex) throws SQLException {
/* 390 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 392 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 394 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 396 */     if (length == -1L) {
/* 397 */       return 0L;
/*     */     }
/*     */     
/* 400 */     return StringUtils.getLong(this.rowFromServer.getByteBuffer(), offset, offset + (int)length);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getNativeDouble(int columnIndex) throws SQLException {
/* 405 */     if (isNull(columnIndex)) {
/* 406 */       return 0.0D;
/*     */     }
/*     */     
/* 409 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 411 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 413 */     return getNativeDouble(this.rowFromServer.getByteBuffer(), offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getNativeFloat(int columnIndex) throws SQLException {
/* 418 */     if (isNull(columnIndex)) {
/* 419 */       return 0.0F;
/*     */     }
/*     */     
/* 422 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 424 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 426 */     return getNativeFloat(this.rowFromServer.getByteBuffer(), offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNativeInt(int columnIndex) throws SQLException {
/* 431 */     if (isNull(columnIndex)) {
/* 432 */       return 0;
/*     */     }
/*     */     
/* 435 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 437 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 439 */     return getNativeInt(this.rowFromServer.getByteBuffer(), offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getNativeLong(int columnIndex) throws SQLException {
/* 444 */     if (isNull(columnIndex)) {
/* 445 */       return 0L;
/*     */     }
/*     */     
/* 448 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 450 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 452 */     return getNativeLong(this.rowFromServer.getByteBuffer(), offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public short getNativeShort(int columnIndex) throws SQLException {
/* 457 */     if (isNull(columnIndex)) {
/* 458 */       return 0;
/*     */     }
/*     */     
/* 461 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 463 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 465 */     return getNativeShort(this.rowFromServer.getByteBuffer(), offset);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Timestamp getNativeTimestamp(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/* 471 */     if (isNull(columnIndex)) {
/* 472 */       return null;
/*     */     }
/*     */     
/* 475 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 477 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 479 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 481 */     return getNativeTimestamp(this.rowFromServer.getByteBuffer(), offset, (int)length, targetCalendar, tz, rollForward, conn, rs);
/*     */   }
/*     */ 
/*     */   
/*     */   public Reader getReader(int columnIndex) throws SQLException {
/* 486 */     InputStream stream = getBinaryInputStream(columnIndex);
/*     */     
/* 488 */     if (stream == null) {
/* 489 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 493 */       return new InputStreamReader(stream, this.metadata[columnIndex].getEncoding());
/* 494 */     } catch (UnsupportedEncodingException e) {
/* 495 */       SQLException sqlEx = SQLError.createSQLException("", this.exceptionInterceptor);
/*     */       
/* 497 */       sqlEx.initCause(e);
/*     */       
/* 499 */       throw sqlEx;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getString(int columnIndex, String encoding, MySQLConnection conn) throws SQLException {
/* 505 */     if (this.isBinaryEncoded && 
/* 506 */       isNull(columnIndex)) {
/* 507 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 511 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 513 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 515 */     if (length == -1L) {
/* 516 */       return null;
/*     */     }
/*     */     
/* 519 */     if (length == 0L) {
/* 520 */       return "";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 525 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 527 */     return getString(encoding, conn, this.rowFromServer.getByteBuffer(), offset, (int)length);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Time getTimeFast(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/* 533 */     if (isNull(columnIndex)) {
/* 534 */       return null;
/*     */     }
/*     */     
/* 537 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 539 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 541 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 543 */     return getTimeFast(columnIndex, this.rowFromServer.getByteBuffer(), offset, (int)length, targetCalendar, tz, rollForward, conn, rs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Timestamp getTimestampFast(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs, boolean useGmtMillis, boolean useJDBCCompliantTimezoneShift) throws SQLException {
/* 549 */     if (isNull(columnIndex)) {
/* 550 */       return null;
/*     */     }
/*     */     
/* 553 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 555 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 557 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 559 */     return getTimestampFast(columnIndex, this.rowFromServer.getByteBuffer(), offset, (int)length, targetCalendar, tz, rollForward, conn, rs, useGmtMillis, useJDBCCompliantTimezoneShift);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFloatingPointNumber(int index) throws SQLException {
/* 565 */     if (this.isBinaryEncoded) {
/* 566 */       switch (this.metadata[index].getSQLType()) {
/*     */         case 2:
/*     */         case 3:
/*     */         case 6:
/*     */         case 8:
/* 571 */           return true;
/*     */       } 
/* 573 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 577 */     findAndSeekToOffset(index);
/*     */     
/* 579 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 581 */     if (length == -1L) {
/* 582 */       return false;
/*     */     }
/*     */     
/* 585 */     if (length == 0L) {
/* 586 */       return false;
/*     */     }
/*     */     
/* 589 */     int offset = this.rowFromServer.getPosition();
/* 590 */     byte[] buffer = this.rowFromServer.getByteBuffer();
/*     */     
/* 592 */     for (int i = 0; i < (int)length; i++) {
/* 593 */       char c = (char)buffer[offset + i];
/*     */       
/* 595 */       if (c == 'e' || c == 'E') {
/* 596 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 600 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNull(int index) throws SQLException {
/* 605 */     if (!this.isBinaryEncoded) {
/* 606 */       findAndSeekToOffset(index);
/*     */       
/* 608 */       return (this.rowFromServer.readFieldLength() == -1L);
/*     */     } 
/*     */     
/* 611 */     return this.isNull[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public long length(int index) throws SQLException {
/* 616 */     findAndSeekToOffset(index);
/*     */     
/* 618 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 620 */     if (length == -1L) {
/* 621 */       return 0L;
/*     */     }
/*     */     
/* 624 */     return length;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColumnValue(int index, byte[] value) throws SQLException {
/* 629 */     throw new OperationNotSupportedException();
/*     */   }
/*     */ 
/*     */   
/*     */   public ResultSetRow setMetadata(Field[] f) throws SQLException {
/* 634 */     super.setMetadata(f);
/*     */     
/* 636 */     if (this.isBinaryEncoded) {
/* 637 */       setupIsNullBitmask();
/*     */     }
/*     */     
/* 640 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setupIsNullBitmask() throws SQLException {
/* 649 */     if (this.isNull != null) {
/*     */       return;
/*     */     }
/*     */     
/* 653 */     this.rowFromServer.setPosition(this.preNullBitmaskHomePosition);
/*     */     
/* 655 */     int nullCount = (this.metadata.length + 9) / 8;
/*     */     
/* 657 */     byte[] nullBitMask = new byte[nullCount];
/*     */     
/* 659 */     for (int i = 0; i < nullCount; i++) {
/* 660 */       nullBitMask[i] = this.rowFromServer.readByte();
/*     */     }
/*     */     
/* 663 */     this.homePosition = this.rowFromServer.getPosition();
/*     */     
/* 665 */     this.isNull = new boolean[this.metadata.length];
/*     */     
/* 667 */     int nullMaskPos = 0;
/* 668 */     int bit = 4;
/*     */     
/* 670 */     for (int j = 0; j < this.metadata.length; j++) {
/*     */       
/* 672 */       this.isNull[j] = ((nullBitMask[nullMaskPos] & bit) != 0);
/*     */       
/* 674 */       if (((bit <<= 1) & 0xFF) == 0) {
/* 675 */         bit = 1;
/*     */         
/* 677 */         nullMaskPos++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getDateFast(int columnIndex, MySQLConnection conn, ResultSetImpl rs, Calendar targetCalendar) throws SQLException {
/* 684 */     if (isNull(columnIndex)) {
/* 685 */       return null;
/*     */     }
/*     */     
/* 688 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 690 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 692 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 694 */     return getDateFast(columnIndex, this.rowFromServer.getByteBuffer(), offset, (int)length, conn, rs, targetCalendar);
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getNativeDate(int columnIndex, MySQLConnection conn, ResultSetImpl rs, Calendar cal) throws SQLException {
/* 699 */     if (isNull(columnIndex)) {
/* 700 */       return null;
/*     */     }
/*     */     
/* 703 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 705 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 707 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 709 */     return getNativeDate(columnIndex, this.rowFromServer.getByteBuffer(), offset, (int)length, conn, rs, cal);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getNativeDateTimeValue(int columnIndex, Calendar targetCalendar, int jdbcType, int mysqlType, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/* 715 */     if (isNull(columnIndex)) {
/* 716 */       return null;
/*     */     }
/*     */     
/* 719 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 721 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 723 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 725 */     return getNativeDateTimeValue(columnIndex, this.rowFromServer.getByteBuffer(), offset, (int)length, targetCalendar, jdbcType, mysqlType, tz, rollForward, conn, rs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Time getNativeTime(int columnIndex, Calendar targetCalendar, TimeZone tz, boolean rollForward, MySQLConnection conn, ResultSetImpl rs) throws SQLException {
/* 732 */     if (isNull(columnIndex)) {
/* 733 */       return null;
/*     */     }
/*     */     
/* 736 */     findAndSeekToOffset(columnIndex);
/*     */     
/* 738 */     long length = this.rowFromServer.readFieldLength();
/*     */     
/* 740 */     int offset = this.rowFromServer.getPosition();
/*     */     
/* 742 */     return getNativeTime(columnIndex, this.rowFromServer.getByteBuffer(), offset, (int)length, targetCalendar, tz, rollForward, conn, rs);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBytesSize() {
/* 747 */     return this.rowFromServer.getBufLength();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\BufferRow.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */