/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CoderResult;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ import java.nio.charset.MalformedInputException;
/*     */ import java.nio.charset.UnmappableCharacterException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InputStreamReader
/*     */   extends Reader
/*     */ {
/*     */   private InputStream in;
/*     */   private static final int BUFFER_SIZE = 4;
/*     */   private boolean endOfInput = false;
/*     */   CharsetDecoder decoder;
/*  53 */   ByteBuffer bytes = ByteBuffer.allocate(4);
/*     */   
/*  55 */   char pending = Character.MAX_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStreamReader(InputStream in) {
/*  67 */     super(in);
/*  68 */     this.in = in;
/*  69 */     this
/*  70 */       .decoder = Charset.defaultCharset().newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
/*     */     
/*  72 */     this.bytes.limit(0);
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
/*     */   public InputStreamReader(InputStream in, String enc) throws UnsupportedEncodingException {
/*  92 */     super(in);
/*  93 */     if (enc == null) {
/*  94 */       throw new NullPointerException();
/*     */     }
/*  96 */     this.in = in;
/*     */     try {
/*  98 */       this
/*  99 */         .decoder = Charset.forName(enc).newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
/*     */     }
/* 101 */     catch (IllegalArgumentException e) {
/* 102 */       throw (UnsupportedEncodingException)(new UnsupportedEncodingException(enc))
/* 103 */         .initCause(e);
/*     */     } 
/* 105 */     this.bytes.limit(0);
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
/*     */   public InputStreamReader(InputStream in, CharsetDecoder dec) {
/* 118 */     super(in);
/* 119 */     dec.averageCharsPerByte();
/* 120 */     this.in = in;
/* 121 */     this.decoder = dec;
/* 122 */     this.bytes.limit(0);
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
/*     */   public InputStreamReader(InputStream in, Charset charset) {
/* 135 */     super(in);
/* 136 */     this.in = in;
/* 137 */     this
/* 138 */       .decoder = charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
/*     */     
/* 140 */     this.bytes.limit(0);
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
/*     */   public void close() throws IOException {
/* 152 */     synchronized (this.lock) {
/* 153 */       this.decoder = null;
/* 154 */       if (this.in != null) {
/* 155 */         this.in.close();
/* 156 */         this.in = null;
/*     */       } 
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
/*     */   public String getEncoding() {
/* 169 */     if (!isOpen()) {
/* 170 */       return null;
/*     */     }
/* 172 */     return this.decoder.charset().name();
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
/*     */   public int read() throws IOException {
/* 189 */     synchronized (this.lock) {
/* 190 */       if (!isOpen()) {
/* 191 */         throw new ClosedException("InputStreamReader is closed.");
/*     */       }
/*     */       
/* 194 */       if (this.pending != Character.MAX_VALUE) {
/* 195 */         char c = this.pending;
/* 196 */         this.pending = Character.MAX_VALUE;
/* 197 */         return c;
/*     */       } 
/* 199 */       char[] buf = new char[2];
/* 200 */       int nb = read(buf, 0, 2);
/* 201 */       if (nb == 2) {
/* 202 */         this.pending = buf[1];
/*     */       }
/* 204 */       if (nb > 0) {
/* 205 */         return buf[0];
/*     */       }
/* 207 */       return -1;
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
/*     */   
/*     */   public int read(char[] buf, int offset, int length) throws IOException {
/* 238 */     synchronized (this.lock) {
/* 239 */       if (!isOpen()) {
/* 240 */         throw new IOException("InputStreamReader is closed.");
/*     */       }
/* 242 */       if (offset < 0 || offset > buf.length - length || length < 0) {
/* 243 */         throw new IndexOutOfBoundsException();
/*     */       }
/* 245 */       if (length == 0) {
/* 246 */         return 0;
/*     */       }
/*     */       
/* 249 */       CharBuffer out = CharBuffer.wrap(buf, offset, length);
/* 250 */       CoderResult result = CoderResult.UNDERFLOW;
/*     */ 
/*     */ 
/*     */       
/* 254 */       boolean needInput = !this.bytes.hasRemaining();
/*     */       
/* 256 */       while (out.position() == offset) {
/*     */         
/* 258 */         if (needInput) {
/*     */           try {
/* 260 */             if (this.in.available() == 0 && out
/* 261 */               .position() > offset) {
/*     */               break;
/*     */             }
/*     */           }
/* 265 */           catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */           
/* 269 */           int off = this.bytes.arrayOffset() + this.bytes.limit();
/* 270 */           int was_red = this.in.read(this.bytes.array(), off, 1);
/*     */           
/* 272 */           if (was_red == -1) {
/* 273 */             this.endOfInput = true; break;
/*     */           } 
/* 275 */           if (was_red == 0) {
/*     */             break;
/*     */           }
/* 278 */           this.bytes.limit(this.bytes.limit() + was_red);
/*     */         } 
/*     */ 
/*     */         
/* 282 */         result = this.decoder.decode(this.bytes, out, false);
/*     */         
/* 284 */         if (result.isUnderflow()) {
/*     */           
/* 286 */           if (this.bytes.limit() == this.bytes.capacity()) {
/* 287 */             this.bytes.compact();
/* 288 */             this.bytes.limit(this.bytes.position());
/* 289 */             this.bytes.position(0);
/*     */           } 
/* 291 */           needInput = true;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 297 */       if (result == CoderResult.UNDERFLOW && this.endOfInput) {
/* 298 */         result = this.decoder.decode(this.bytes, out, true);
/* 299 */         this.decoder.flush(out);
/* 300 */         this.decoder.reset();
/*     */       } 
/* 302 */       if (result.isMalformed())
/* 303 */         throw new MalformedInputException(result.length()); 
/* 304 */       if (result.isUnmappable()) {
/* 305 */         throw new UnmappableCharacterException(result.length());
/*     */       }
/*     */       
/* 308 */       return (out.position() - offset == 0) ? -1 : (out.position() - offset);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isOpen() {
/* 317 */     return (this.in != null);
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
/*     */   public boolean ready() throws IOException {
/* 335 */     synchronized (this.lock) {
/* 336 */       if (this.in == null) {
/* 337 */         throw new IOException("InputStreamReader is closed.");
/*     */       }
/*     */       try {
/* 340 */         return (this.bytes.hasRemaining() || this.in.available() > 0);
/* 341 */       } catch (IOException e) {
/* 342 */         return false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\InputStreamReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */