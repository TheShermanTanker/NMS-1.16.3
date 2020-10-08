/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import com.mysql.jdbc.log.Log;
/*     */ import com.mysql.jdbc.log.NullLogger;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.sql.SQLException;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Inflater;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class CompressedInputStream
/*     */   extends InputStream
/*     */ {
/*     */   private byte[] buffer;
/*     */   private InputStream in;
/*     */   private Inflater inflater;
/*     */   private ConnectionPropertiesImpl.BooleanConnectionProperty traceProtocol;
/*     */   private Log log;
/*  58 */   private byte[] packetHeaderBuffer = new byte[7];
/*     */ 
/*     */   
/*  61 */   private int pos = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompressedInputStream(Connection conn, InputStream streamFromServer) {
/*  71 */     this.traceProtocol = ((ConnectionPropertiesImpl)conn).traceProtocol;
/*     */     try {
/*  73 */       this.log = conn.getLog();
/*  74 */     } catch (SQLException e) {
/*  75 */       this.log = (Log)new NullLogger(null);
/*     */     } 
/*     */     
/*  78 */     this.in = streamFromServer;
/*  79 */     this.inflater = new Inflater();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/*  87 */     if (this.buffer == null) {
/*  88 */       return this.in.available();
/*     */     }
/*     */     
/*  91 */     return this.buffer.length - this.pos + this.in.available();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  99 */     this.in.close();
/* 100 */     this.buffer = null;
/* 101 */     this.inflater.end();
/* 102 */     this.inflater = null;
/* 103 */     this.traceProtocol = null;
/* 104 */     this.log = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void getNextPacketFromServer() throws IOException {
/* 115 */     byte[] uncompressedData = null;
/*     */     
/* 117 */     int lengthRead = readFully(this.packetHeaderBuffer, 0, 7);
/*     */     
/* 119 */     if (lengthRead < 7) {
/* 120 */       throw new IOException("Unexpected end of input stream");
/*     */     }
/*     */     
/* 123 */     int compressedPacketLength = (this.packetHeaderBuffer[0] & 0xFF) + ((this.packetHeaderBuffer[1] & 0xFF) << 8) + ((this.packetHeaderBuffer[2] & 0xFF) << 16);
/*     */ 
/*     */     
/* 126 */     int uncompressedLength = (this.packetHeaderBuffer[4] & 0xFF) + ((this.packetHeaderBuffer[5] & 0xFF) << 8) + ((this.packetHeaderBuffer[6] & 0xFF) << 16);
/*     */ 
/*     */     
/* 129 */     boolean doTrace = this.traceProtocol.getValueAsBoolean();
/*     */     
/* 131 */     if (doTrace) {
/* 132 */       this.log.logTrace("Reading compressed packet of length " + compressedPacketLength + " uncompressed to " + uncompressedLength);
/*     */     }
/*     */     
/* 135 */     if (uncompressedLength > 0) {
/* 136 */       uncompressedData = new byte[uncompressedLength];
/*     */       
/* 138 */       byte[] compressedBuffer = new byte[compressedPacketLength];
/*     */       
/* 140 */       readFully(compressedBuffer, 0, compressedPacketLength);
/*     */       
/* 142 */       this.inflater.reset();
/*     */       
/* 144 */       this.inflater.setInput(compressedBuffer);
/*     */       
/*     */       try {
/* 147 */         this.inflater.inflate(uncompressedData);
/* 148 */       } catch (DataFormatException dfe) {
/* 149 */         throw new IOException("Error while uncompressing packet from server.");
/*     */       } 
/*     */     } else {
/*     */       
/* 153 */       if (doTrace) {
/* 154 */         this.log.logTrace("Packet didn't meet compression threshold, not uncompressing...");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 160 */       uncompressedLength = compressedPacketLength;
/* 161 */       uncompressedData = new byte[uncompressedLength];
/* 162 */       readFully(uncompressedData, 0, uncompressedLength);
/*     */     } 
/*     */     
/* 165 */     if (doTrace) {
/* 166 */       if (uncompressedLength > 1024) {
/* 167 */         this.log.logTrace("Uncompressed packet: \n" + StringUtils.dumpAsHex(uncompressedData, 256));
/* 168 */         byte[] tempData = new byte[256];
/* 169 */         System.arraycopy(uncompressedData, uncompressedLength - 256, tempData, 0, 256);
/* 170 */         this.log.logTrace("Uncompressed packet: \n" + StringUtils.dumpAsHex(tempData, 256));
/* 171 */         this.log.logTrace("Large packet dump truncated. Showing first and last 256 bytes.");
/*     */       } else {
/* 173 */         this.log.logTrace("Uncompressed packet: \n" + StringUtils.dumpAsHex(uncompressedData, uncompressedLength));
/*     */       } 
/*     */     }
/*     */     
/* 177 */     if (this.buffer != null && this.pos < this.buffer.length) {
/* 178 */       if (doTrace) {
/* 179 */         this.log.logTrace("Combining remaining packet with new: ");
/*     */       }
/*     */       
/* 182 */       int remaining = this.buffer.length - this.pos;
/* 183 */       byte[] newBuffer = new byte[remaining + uncompressedData.length];
/*     */       
/* 185 */       System.arraycopy(this.buffer, this.pos, newBuffer, 0, remaining);
/* 186 */       System.arraycopy(uncompressedData, 0, newBuffer, remaining, uncompressedData.length);
/*     */       
/* 188 */       uncompressedData = newBuffer;
/*     */     } 
/*     */     
/* 191 */     this.pos = 0;
/* 192 */     this.buffer = uncompressedData;
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
/*     */   private void getNextPacketIfRequired(int numBytes) throws IOException {
/* 208 */     if (this.buffer == null || this.pos + numBytes > this.buffer.length) {
/* 209 */       getNextPacketFromServer();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*     */     try {
/* 219 */       getNextPacketIfRequired(1);
/* 220 */     } catch (IOException ioEx) {
/* 221 */       return -1;
/*     */     } 
/*     */     
/* 224 */     return this.buffer[this.pos++] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 232 */     return read(b, 0, b.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 240 */     if (b == null)
/* 241 */       throw new NullPointerException(); 
/* 242 */     if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0) {
/* 243 */       throw new IndexOutOfBoundsException();
/*     */     }
/*     */     
/* 246 */     if (len <= 0) {
/* 247 */       return 0;
/*     */     }
/*     */     
/*     */     try {
/* 251 */       getNextPacketIfRequired(len);
/* 252 */     } catch (IOException ioEx) {
/* 253 */       return -1;
/*     */     } 
/*     */     
/* 256 */     int remainingBufferLength = this.buffer.length - this.pos;
/* 257 */     int consummedBytesLength = Math.min(remainingBufferLength, len);
/*     */     
/* 259 */     System.arraycopy(this.buffer, this.pos, b, off, consummedBytesLength);
/* 260 */     this.pos += consummedBytesLength;
/*     */     
/* 262 */     return consummedBytesLength;
/*     */   }
/*     */   
/*     */   private final int readFully(byte[] b, int off, int len) throws IOException {
/* 266 */     if (len < 0) {
/* 267 */       throw new IndexOutOfBoundsException();
/*     */     }
/*     */     
/* 270 */     int n = 0;
/*     */     
/* 272 */     while (n < len) {
/* 273 */       int count = this.in.read(b, off + n, len - n);
/*     */       
/* 275 */       if (count < 0) {
/* 276 */         throw new EOFException();
/*     */       }
/*     */       
/* 279 */       n += count;
/*     */     } 
/*     */     
/* 282 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 290 */     long count = 0L;
/*     */     long i;
/* 292 */     for (i = 0L; i < n; i++) {
/* 293 */       int bytesRead = read();
/*     */       
/* 295 */       if (bytesRead == -1) {
/*     */         break;
/*     */       }
/*     */       
/* 299 */       count++;
/*     */     } 
/*     */     
/* 302 */     return count;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\CompressedInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */