/*     */ package org.fusesource.jansi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HtmlAnsiOutputStream
/*     */   extends AnsiOutputStream
/*     */ {
/*     */   private boolean concealOn = false;
/*     */   
/*     */   public void close() throws IOException {
/*  32 */     closeAttributes();
/*  33 */     super.close();
/*     */   }
/*     */   
/*  36 */   private static final String[] ANSI_COLOR_MAP = new String[] { "black", "red", "green", "yellow", "blue", "magenta", "cyan", "white" };
/*     */ 
/*     */   
/*  39 */   private static final byte[] BYTES_QUOT = "&quot;".getBytes();
/*  40 */   private static final byte[] BYTES_AMP = "&amp;".getBytes();
/*  41 */   private static final byte[] BYTES_LT = "&lt;".getBytes(); private final List<String> closingAttributes;
/*  42 */   private static final byte[] BYTES_GT = "&gt;".getBytes();
/*     */   
/*     */   public HtmlAnsiOutputStream(OutputStream os) {
/*  45 */     super(os);
/*     */ 
/*     */     
/*  48 */     this.closingAttributes = new ArrayList<String>();
/*     */   }
/*     */   private void write(String s) throws IOException {
/*  51 */     this.out.write(s.getBytes());
/*     */   }
/*     */   
/*     */   private void writeAttribute(String s) throws IOException {
/*  55 */     write("<" + s + ">");
/*  56 */     this.closingAttributes.add(0, s.split(" ", 2)[0]);
/*     */   }
/*     */   
/*     */   private void closeAttributes() throws IOException {
/*  60 */     for (String attr : this.closingAttributes) {
/*  61 */       write("</" + attr + ">");
/*     */     }
/*  63 */     this.closingAttributes.clear();
/*     */   }
/*     */   
/*     */   public void write(int data) throws IOException {
/*  67 */     switch (data) {
/*     */       case 34:
/*  69 */         this.out.write(BYTES_QUOT);
/*     */         return;
/*     */       case 38:
/*  72 */         this.out.write(BYTES_AMP);
/*     */         return;
/*     */       case 60:
/*  75 */         this.out.write(BYTES_LT);
/*     */         return;
/*     */       case 62:
/*  78 */         this.out.write(BYTES_GT);
/*     */         return;
/*     */     } 
/*  81 */     super.write(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeLine(byte[] buf, int offset, int len) throws IOException {
/*  86 */     write(buf, offset, len);
/*  87 */     closeAttributes();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetAttribute(int attribute) throws IOException {
/*  92 */     switch (attribute) {
/*     */       case 8:
/*  94 */         write("\033[8m");
/*  95 */         this.concealOn = true;
/*     */         break;
/*     */       case 1:
/*  98 */         writeAttribute("b");
/*     */         break;
/*     */       case 22:
/* 101 */         closeAttributes();
/*     */         break;
/*     */       case 4:
/* 104 */         writeAttribute("u");
/*     */         break;
/*     */       case 24:
/* 107 */         closeAttributes();
/*     */         break;
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
/*     */   protected void processAttributeRest() throws IOException {
/* 120 */     if (this.concealOn) {
/* 121 */       write("\033[0m");
/* 122 */       this.concealOn = false;
/*     */     } 
/* 124 */     closeAttributes();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetForegroundColor(int color, boolean bright) throws IOException {
/* 129 */     writeAttribute("span style=\"color: " + ANSI_COLOR_MAP[color] + ";\"");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void processSetBackgroundColor(int color, boolean bright) throws IOException {
/* 134 */     writeAttribute("span style=\"background-color: " + ANSI_COLOR_MAP[color] + ";\"");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\HtmlAnsiOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */