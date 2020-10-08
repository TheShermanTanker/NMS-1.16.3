/*     */ package org.jline.terminal.impl;
/*     */ 
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.util.function.IntConsumer;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.jline.terminal.Cursor;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.utils.Curses;
/*     */ import org.jline.utils.InfoCmp;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CursorSupport
/*     */ {
/*     */   public static Cursor getCursorPosition(Terminal terminal, IntConsumer discarded) {
/*     */     try {
/*  26 */       String u6 = terminal.getStringCapability(InfoCmp.Capability.user6);
/*  27 */       String u7 = terminal.getStringCapability(InfoCmp.Capability.user7);
/*  28 */       if (u6 == null || u7 == null) {
/*  29 */         return null;
/*     */       }
/*     */       
/*  32 */       boolean inc1 = false;
/*  33 */       StringBuilder patb = new StringBuilder();
/*  34 */       int index = 0;
/*  35 */       while (index < u6.length()) {
/*     */         char ch;
/*  37 */         switch (ch = u6.charAt(index++)) {
/*     */           case '\\':
/*  39 */             switch (u6.charAt(index++)) {
/*     */               case 'E':
/*     */               case 'e':
/*  42 */                 patb.append("\\x1b");
/*     */                 continue;
/*     */             } 
/*  45 */             throw new IllegalArgumentException();
/*     */ 
/*     */           
/*     */           case '%':
/*  49 */             ch = u6.charAt(index++);
/*  50 */             switch (ch) {
/*     */               case '%':
/*  52 */                 patb.append('%');
/*     */                 continue;
/*     */               case 'i':
/*  55 */                 inc1 = true;
/*     */                 continue;
/*     */               case 'd':
/*  58 */                 patb.append("([0-9]+)");
/*     */                 continue;
/*     */             } 
/*  61 */             throw new IllegalArgumentException();
/*     */         } 
/*     */ 
/*     */         
/*  65 */         switch (ch) {
/*     */           case '[':
/*  67 */             patb.append('\\');
/*     */             break;
/*     */         } 
/*  70 */         patb.append(ch);
/*     */       } 
/*     */ 
/*     */       
/*  74 */       Pattern pattern = Pattern.compile(patb.toString());
/*     */       
/*  76 */       Curses.tputs(terminal.writer(), u7, new Object[0]);
/*  77 */       terminal.flush();
/*  78 */       StringBuilder sb = new StringBuilder();
/*  79 */       int start = 0;
/*     */       while (true) {
/*  81 */         int c = terminal.reader().read();
/*  82 */         if (c < 0) {
/*  83 */           return null;
/*     */         }
/*  85 */         sb.append((char)c);
/*  86 */         Matcher matcher = pattern.matcher(sb.substring(start));
/*  87 */         if (matcher.matches()) {
/*  88 */           int y = Integer.parseInt(matcher.group(1));
/*  89 */           int x = Integer.parseInt(matcher.group(2));
/*  90 */           if (inc1) {
/*  91 */             x--;
/*  92 */             y--;
/*     */           } 
/*  94 */           if (discarded != null) {
/*  95 */             for (int i = 0; i < start; i++) {
/*  96 */               discarded.accept(sb.charAt(i));
/*     */             }
/*     */           }
/*  99 */           return new Cursor(x, y);
/* 100 */         }  if (!matcher.hitEnd()) {
/* 101 */           start++;
/*     */         }
/*     */       } 
/* 104 */     } catch (IOException e) {
/* 105 */       throw new IOError(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\CursorSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */