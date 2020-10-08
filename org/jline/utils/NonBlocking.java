/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CharsetEncoder;
/*     */ import java.nio.charset.CodingErrorAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NonBlocking
/*     */ {
/*     */   public static NonBlockingPumpReader nonBlockingPumpReader() {
/*  24 */     return new NonBlockingPumpReader();
/*     */   }
/*     */   
/*     */   public static NonBlockingPumpReader nonBlockingPumpReader(int size) {
/*  28 */     return new NonBlockingPumpReader(size);
/*     */   }
/*     */   
/*     */   public static NonBlockingPumpInputStream nonBlockingPumpInputStream() {
/*  32 */     return new NonBlockingPumpInputStream();
/*     */   }
/*     */   
/*     */   public static NonBlockingPumpInputStream nonBlockingPumpInputStream(int size) {
/*  36 */     return new NonBlockingPumpInputStream(size);
/*     */   }
/*     */   
/*     */   public static NonBlockingInputStream nonBlockingStream(NonBlockingReader reader, Charset encoding) {
/*  40 */     return new NonBlockingReaderInputStream(reader, encoding);
/*     */   }
/*     */   
/*     */   public static NonBlockingInputStream nonBlocking(String name, InputStream inputStream) {
/*  44 */     if (inputStream instanceof NonBlockingInputStream) {
/*  45 */       return (NonBlockingInputStream)inputStream;
/*     */     }
/*  47 */     return new NonBlockingInputStreamImpl(name, inputStream);
/*     */   }
/*     */   
/*     */   public static NonBlockingReader nonBlocking(String name, Reader reader) {
/*  51 */     if (reader instanceof NonBlockingReader) {
/*  52 */       return (NonBlockingReader)reader;
/*     */     }
/*  54 */     return new NonBlockingReaderImpl(name, reader);
/*     */   }
/*     */   
/*     */   public static NonBlockingReader nonBlocking(String name, InputStream inputStream, Charset encoding) {
/*  58 */     return new NonBlockingInputStreamReader(nonBlocking(name, inputStream), encoding);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class NonBlockingReaderInputStream
/*     */     extends NonBlockingInputStream
/*     */   {
/*     */     private final NonBlockingReader reader;
/*     */     
/*     */     private final CharsetEncoder encoder;
/*     */     
/*     */     private final ByteBuffer bytes;
/*     */     
/*     */     private final CharBuffer chars;
/*     */     
/*     */     private NonBlockingReaderInputStream(NonBlockingReader reader, Charset charset) {
/*  74 */       this.reader = reader;
/*  75 */       this
/*     */         
/*  77 */         .encoder = charset.newEncoder().onUnmappableCharacter(CodingErrorAction.REPLACE).onMalformedInput(CodingErrorAction.REPLACE);
/*  78 */       this.bytes = ByteBuffer.allocate(4);
/*  79 */       this.chars = CharBuffer.allocate(2);
/*     */       
/*  81 */       this.bytes.limit(0);
/*  82 */       this.chars.limit(0);
/*     */     }
/*     */ 
/*     */     
/*     */     public int available() {
/*  87 */       return (int)(this.reader.available() * this.encoder.averageBytesPerChar()) + this.bytes
/*  88 */         .remaining();
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/*  93 */       this.reader.close();
/*     */     }
/*     */ 
/*     */     
/*     */     public int read(long timeout, boolean isPeek) throws IOException {
/*  98 */       boolean isInfinite = (timeout <= 0L);
/*  99 */       while (!this.bytes.hasRemaining() && (isInfinite || timeout > 0L)) {
/* 100 */         long start = 0L;
/* 101 */         if (!isInfinite) {
/* 102 */           start = System.currentTimeMillis();
/*     */         }
/* 104 */         int c = this.reader.read(timeout);
/* 105 */         if (c == -1) {
/* 106 */           return -1;
/*     */         }
/* 108 */         if (c >= 0) {
/* 109 */           if (!this.chars.hasRemaining()) {
/* 110 */             this.chars.position(0);
/* 111 */             this.chars.limit(0);
/*     */           } 
/* 113 */           int l = this.chars.limit();
/* 114 */           this.chars.array()[this.chars.arrayOffset() + l] = (char)c;
/* 115 */           this.chars.limit(l + 1);
/* 116 */           this.bytes.clear();
/* 117 */           this.encoder.encode(this.chars, this.bytes, false);
/* 118 */           this.bytes.flip();
/*     */         } 
/* 120 */         if (!isInfinite) {
/* 121 */           timeout -= System.currentTimeMillis() - start;
/*     */         }
/*     */       } 
/* 124 */       if (this.bytes.hasRemaining()) {
/* 125 */         if (isPeek) {
/* 126 */           return this.bytes.get(this.bytes.position());
/*     */         }
/* 128 */         return this.bytes.get();
/*     */       } 
/*     */       
/* 131 */       return -2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class NonBlockingInputStreamReader
/*     */     extends NonBlockingReader
/*     */   {
/*     */     private final NonBlockingInputStream input;
/*     */     private final CharsetDecoder decoder;
/*     */     private final ByteBuffer bytes;
/*     */     private final CharBuffer chars;
/*     */     
/*     */     public NonBlockingInputStreamReader(NonBlockingInputStream inputStream, Charset encoding) {
/* 145 */       this(inputStream, ((encoding != null) ? encoding : 
/* 146 */           Charset.defaultCharset()).newDecoder()
/* 147 */           .onMalformedInput(CodingErrorAction.REPLACE)
/* 148 */           .onUnmappableCharacter(CodingErrorAction.REPLACE));
/*     */     }
/*     */     
/*     */     public NonBlockingInputStreamReader(NonBlockingInputStream input, CharsetDecoder decoder) {
/* 152 */       this.input = input;
/* 153 */       this.decoder = decoder;
/* 154 */       this.bytes = ByteBuffer.allocate(4);
/* 155 */       this.chars = CharBuffer.allocate(2);
/* 156 */       this.bytes.limit(0);
/* 157 */       this.chars.limit(0);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int read(long timeout, boolean isPeek) throws IOException {
/* 162 */       boolean isInfinite = (timeout <= 0L);
/* 163 */       while (!this.chars.hasRemaining() && (isInfinite || timeout > 0L)) {
/* 164 */         long start = 0L;
/* 165 */         if (!isInfinite) {
/* 166 */           start = System.currentTimeMillis();
/*     */         }
/* 168 */         int b = this.input.read(timeout);
/* 169 */         if (b == -1) {
/* 170 */           return -1;
/*     */         }
/* 172 */         if (b >= 0) {
/* 173 */           if (!this.bytes.hasRemaining()) {
/* 174 */             this.bytes.position(0);
/* 175 */             this.bytes.limit(0);
/*     */           } 
/* 177 */           int l = this.bytes.limit();
/* 178 */           this.bytes.array()[this.bytes.arrayOffset() + l] = (byte)b;
/* 179 */           this.bytes.limit(l + 1);
/* 180 */           this.chars.clear();
/* 181 */           this.decoder.decode(this.bytes, this.chars, false);
/* 182 */           this.chars.flip();
/*     */         } 
/*     */         
/* 185 */         if (!isInfinite) {
/* 186 */           timeout -= System.currentTimeMillis() - start;
/*     */         }
/*     */       } 
/* 189 */       if (this.chars.hasRemaining()) {
/* 190 */         if (isPeek) {
/* 191 */           return this.chars.get(this.chars.position());
/*     */         }
/* 193 */         return this.chars.get();
/*     */       } 
/*     */       
/* 196 */       return -2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void shutdown() {
/* 202 */       this.input.shutdown();
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 207 */       this.input.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\NonBlocking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */