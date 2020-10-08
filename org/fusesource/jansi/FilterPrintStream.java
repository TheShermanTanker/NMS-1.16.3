/*     */ package org.fusesource.jansi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterPrintStream
/*     */   extends PrintStream
/*     */ {
/*  31 */   private static final String NEWLINE = System.getProperty("line.separator");
/*     */   
/*     */   protected final PrintStream ps;
/*     */   
/*     */   public FilterPrintStream(PrintStream ps) {
/*  36 */     super(new OutputStream()
/*     */         {
/*     */           public void write(int b) throws IOException
/*     */           {
/*  40 */             throw new RuntimeException("Direct OutputStream use forbidden: must go through delegate PrintStream");
/*     */           }
/*     */         });
/*     */     
/*  44 */     this.ps = ps;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean filter(int data) {
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(int data) {
/*  60 */     if (filter(data))
/*     */     {
/*  62 */       this.ps.write(data);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(byte[] buf, int off, int len) {
/*  69 */     for (int i = 0; i < len; i++)
/*     */     {
/*  71 */       write(buf[off + i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkError() {
/*  78 */     return (super.checkError() || this.ps.checkError());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*  84 */     super.close();
/*  85 */     this.ps.close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {
/*  91 */     super.flush();
/*  92 */     this.ps.flush();
/*     */   }
/*     */   
/*     */   private void write(char[] buf) {
/*  96 */     for (char c : buf) {
/*     */       
/*  98 */       if (filter(c))
/*     */       {
/* 100 */         this.ps.print(c);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void write(String s) {
/* 106 */     char[] buf = new char[s.length()];
/* 107 */     s.getChars(0, s.length(), buf, 0);
/* 108 */     write(buf);
/*     */   }
/*     */   
/*     */   private void newLine() {
/* 112 */     write(NEWLINE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void print(boolean b) {
/* 119 */     write(b ? "true" : "false");
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(char c) {
/* 124 */     write(String.valueOf(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(int i) {
/* 129 */     write(String.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(long l) {
/* 134 */     write(String.valueOf(l));
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(float f) {
/* 139 */     write(String.valueOf(f));
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(double d) {
/* 144 */     write(String.valueOf(d));
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(char[] s) {
/* 149 */     write(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(String s) {
/* 154 */     if (s == null) {
/* 155 */       s = "null";
/*     */     }
/* 157 */     write(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public void print(Object obj) {
/* 162 */     write(String.valueOf(obj));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void println() {
/* 170 */     newLine();
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(boolean x) {
/* 175 */     synchronized (this) {
/* 176 */       print(x);
/* 177 */       newLine();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(char x) {
/* 183 */     synchronized (this) {
/* 184 */       print(x);
/* 185 */       newLine();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(int x) {
/* 191 */     synchronized (this) {
/* 192 */       print(x);
/* 193 */       newLine();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(long x) {
/* 199 */     synchronized (this) {
/* 200 */       print(x);
/* 201 */       newLine();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(float x) {
/* 207 */     synchronized (this) {
/* 208 */       print(x);
/* 209 */       newLine();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(double x) {
/* 215 */     synchronized (this) {
/* 216 */       print(x);
/* 217 */       newLine();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(char[] x) {
/* 223 */     synchronized (this) {
/* 224 */       print(x);
/* 225 */       newLine();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(String x) {
/* 231 */     synchronized (this) {
/* 232 */       print(x);
/* 233 */       newLine();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void println(Object x) {
/* 239 */     String s = String.valueOf(x);
/* 240 */     synchronized (this) {
/* 241 */       print(s);
/* 242 */       newLine();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\FilterPrintStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */