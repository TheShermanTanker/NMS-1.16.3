/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.CharBuffer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.CharsetDecoder;
/*     */ import java.nio.charset.CoderResult;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WriterOutputStream
/*     */   extends OutputStream
/*     */ {
/*     */   private final Writer out;
/*     */   private final CharsetDecoder decoder;
/*  34 */   private final ByteBuffer decoderIn = ByteBuffer.allocate(256);
/*  35 */   private final CharBuffer decoderOut = CharBuffer.allocate(128);
/*     */   
/*     */   public WriterOutputStream(Writer out, Charset charset) {
/*  38 */     this(out, charset.newDecoder()
/*  39 */         .onMalformedInput(CodingErrorAction.REPLACE)
/*  40 */         .onUnmappableCharacter(CodingErrorAction.REPLACE));
/*     */   }
/*     */   
/*     */   public WriterOutputStream(Writer out, CharsetDecoder decoder) {
/*  44 */     this.out = out;
/*  45 */     this.decoder = decoder;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(int b) throws IOException {
/*  50 */     write(new byte[] { (byte)b }, 0, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/*  55 */     write(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(byte[] b, int off, int len) throws IOException {
/*  60 */     while (len > 0) {
/*  61 */       int c = Math.min(len, this.decoderIn.remaining());
/*  62 */       this.decoderIn.put(b, off, c);
/*  63 */       processInput(false);
/*  64 */       len -= c;
/*  65 */       off += c;
/*     */     } 
/*  67 */     flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public void flush() throws IOException {
/*  72 */     flushOutput();
/*  73 */     this.out.flush();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  78 */     processInput(true);
/*  79 */     flush();
/*  80 */     this.out.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processInput(boolean endOfInput) throws IOException {
/*     */     CoderResult coderResult;
/*  91 */     this.decoderIn.flip();
/*     */     
/*     */     while (true) {
/*  94 */       coderResult = this.decoder.decode(this.decoderIn, this.decoderOut, endOfInput);
/*  95 */       if (coderResult.isOverflow())
/*  96 */       { flushOutput(); continue; }  break;
/*  97 */     }  if (coderResult.isUnderflow()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 106 */       this.decoderIn.compact();
/*     */       return;
/*     */     } 
/*     */     throw new IOException("Unexpected coder result");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void flushOutput() throws IOException {
/* 115 */     if (this.decoderOut.position() > 0) {
/* 116 */       this.out.write(this.decoderOut.array(), 0, this.decoderOut.position());
/* 117 */       this.decoderOut.rewind();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\WriterOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */