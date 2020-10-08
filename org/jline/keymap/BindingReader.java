/*     */ package org.jline.keymap;
/*     */ 
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Deque;
/*     */ import org.jline.reader.EndOfFileException;
/*     */ import org.jline.utils.ClosedException;
/*     */ import org.jline.utils.NonBlockingReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BindingReader
/*     */ {
/*     */   protected final NonBlockingReader reader;
/*  29 */   protected final StringBuilder opBuffer = new StringBuilder();
/*  30 */   protected final Deque<Integer> pushBackChar = new ArrayDeque<>();
/*     */   protected String lastBinding;
/*     */   
/*     */   public BindingReader(NonBlockingReader reader) {
/*  34 */     this.reader = reader;
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
/*     */   public <T> T readBinding(KeyMap<T> keys) {
/*  57 */     return readBinding(keys, null, true);
/*     */   }
/*     */   
/*     */   public <T> T readBinding(KeyMap<T> keys, KeyMap<T> local) {
/*  61 */     return readBinding(keys, local, true);
/*     */   }
/*     */   
/*     */   public <T> T readBinding(KeyMap<T> keys, KeyMap<T> local, boolean block) {
/*  65 */     this.lastBinding = null;
/*  66 */     T o = null;
/*  67 */     int[] remaining = new int[1];
/*  68 */     boolean hasRead = false;
/*     */     while (true) {
/*  70 */       if (local != null) {
/*  71 */         o = local.getBound(this.opBuffer, remaining);
/*     */       }
/*  73 */       if (o == null && (local == null || remaining[0] >= 0)) {
/*  74 */         o = keys.getBound(this.opBuffer, remaining);
/*     */       }
/*     */       
/*  77 */       if (o != null) {
/*  78 */         if (remaining[0] >= 0) {
/*  79 */           runMacro(this.opBuffer.substring(this.opBuffer.length() - remaining[0]));
/*  80 */           this.opBuffer.setLength(this.opBuffer.length() - remaining[0]);
/*     */         } else {
/*     */           
/*  83 */           long ambiguousTimeout = keys.getAmbiguousTimeout();
/*  84 */           if (ambiguousTimeout > 0L && peekCharacter(ambiguousTimeout) != -2) {
/*  85 */             o = null;
/*     */           }
/*     */         } 
/*  88 */         if (o != null) {
/*  89 */           this.lastBinding = this.opBuffer.toString();
/*  90 */           this.opBuffer.setLength(0);
/*  91 */           return o;
/*     */         }
/*     */       
/*  94 */       } else if (remaining[0] > 0) {
/*  95 */         int cp = this.opBuffer.codePointAt(0);
/*  96 */         String rem = this.opBuffer.substring(Character.charCount(cp));
/*  97 */         this.lastBinding = this.opBuffer.substring(0, Character.charCount(cp));
/*     */         
/*  99 */         o = (cp >= 128) ? keys.getUnicode() : keys.getNomatch();
/* 100 */         this.opBuffer.setLength(0);
/* 101 */         this.opBuffer.append(rem);
/* 102 */         if (o != null) {
/* 103 */           return o;
/*     */         }
/*     */       } 
/*     */       
/* 107 */       if (!block && hasRead) {
/*     */         break;
/*     */       }
/* 110 */       int c = readCharacter();
/* 111 */       if (c == -1) {
/* 112 */         return null;
/*     */       }
/* 114 */       this.opBuffer.appendCodePoint(c);
/* 115 */       hasRead = true;
/*     */     } 
/* 117 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int readCharacter() {
/* 126 */     if (!this.pushBackChar.isEmpty()) {
/* 127 */       return ((Integer)this.pushBackChar.pop()).intValue();
/*     */     }
/*     */     try {
/* 130 */       int c = -2;
/* 131 */       int s = 0;
/* 132 */       while (c == -2) {
/* 133 */         c = this.reader.read(100L);
/* 134 */         if (c >= 0 && Character.isHighSurrogate((char)c)) {
/* 135 */           s = c;
/* 136 */           c = -2;
/*     */         } 
/*     */       } 
/* 139 */       return (s != 0) ? Character.toCodePoint((char)s, (char)c) : c;
/* 140 */     } catch (ClosedException e) {
/* 141 */       throw new EndOfFileException(e);
/* 142 */     } catch (IOException e) {
/* 143 */       throw new IOError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int peekCharacter(long timeout) {
/* 148 */     if (!this.pushBackChar.isEmpty()) {
/* 149 */       return ((Integer)this.pushBackChar.peek()).intValue();
/*     */     }
/*     */     try {
/* 152 */       return this.reader.peek(timeout);
/* 153 */     } catch (IOException e) {
/* 154 */       throw new IOError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void runMacro(String macro) {
/* 159 */     macro.codePoints().forEachOrdered(this.pushBackChar::addLast);
/*     */   }
/*     */   
/*     */   public String getCurrentBuffer() {
/* 163 */     return this.opBuffer.toString();
/*     */   }
/*     */   
/*     */   public String getLastBinding() {
/* 167 */     return this.lastBinding;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\keymap\BindingReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */