/*     */ package org.jline.reader.impl;
/*     */ 
/*     */ import java.util.ListIterator;
/*     */ import org.jline.reader.Expander;
/*     */ import org.jline.reader.History;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultExpander
/*     */   implements Expander
/*     */ {
/*     */   public String expandHistory(History history, String line) {
/*  26 */     boolean inQuote = false;
/*  27 */     StringBuilder sb = new StringBuilder();
/*  28 */     boolean escaped = false;
/*  29 */     int unicode = 0;
/*  30 */     for (int i = 0; i < line.length(); i++) {
/*  31 */       char c = line.charAt(i);
/*  32 */       if (unicode > 0) {
/*  33 */         escaped = (--unicode >= 0);
/*  34 */         sb.append(c);
/*     */       }
/*  36 */       else if (escaped) {
/*  37 */         if (c == 'u') {
/*  38 */           unicode = 4;
/*     */         } else {
/*  40 */           escaped = false;
/*     */         } 
/*  42 */         sb.append(c);
/*     */       }
/*  44 */       else if (c == '\'') {
/*  45 */         inQuote = !inQuote;
/*  46 */         sb.append(c);
/*     */       }
/*  48 */       else if (inQuote) {
/*  49 */         sb.append(c);
/*     */       } else {
/*     */         
/*  52 */         switch (c) {
/*     */ 
/*     */ 
/*     */           
/*     */           case '\\':
/*  57 */             escaped = true;
/*  58 */             sb.append(c);
/*     */             break;
/*     */           case '!':
/*  61 */             if (i + 1 < line.length()) {
/*  62 */               int i1, idx; String sc, previous; int lastSpace; String ss; c = line.charAt(++i);
/*  63 */               boolean neg = false;
/*  64 */               String rep = null;
/*     */               
/*  66 */               switch (c) {
/*     */                 case '!':
/*  68 */                   if (history.size() == 0) {
/*  69 */                     throw new IllegalArgumentException("!!: event not found");
/*     */                   }
/*  71 */                   rep = history.get(history.index() - 1);
/*     */                   break;
/*     */                 case '#':
/*  74 */                   sb.append(sb.toString());
/*     */                   break;
/*     */                 case '?':
/*  77 */                   i1 = line.indexOf('?', i + 1);
/*  78 */                   if (i1 < 0) {
/*  79 */                     i1 = line.length();
/*     */                   }
/*  81 */                   sc = line.substring(i + 1, i1);
/*  82 */                   i = i1;
/*  83 */                   idx = searchBackwards(history, sc, history.index(), false);
/*  84 */                   if (idx < 0) {
/*  85 */                     throw new IllegalArgumentException("!?" + sc + ": event not found");
/*     */                   }
/*  87 */                   rep = history.get(idx);
/*     */                   break;
/*     */                 
/*     */                 case '$':
/*  91 */                   if (history.size() == 0) {
/*  92 */                     throw new IllegalArgumentException("!$: event not found");
/*     */                   }
/*  94 */                   previous = history.get(history.index() - 1).trim();
/*  95 */                   lastSpace = previous.lastIndexOf(' ');
/*  96 */                   if (lastSpace != -1) {
/*  97 */                     rep = previous.substring(lastSpace + 1); break;
/*     */                   } 
/*  99 */                   rep = previous;
/*     */                   break;
/*     */                 
/*     */                 case '\t':
/*     */                 case ' ':
/* 104 */                   sb.append('!');
/* 105 */                   sb.append(c);
/*     */                   break;
/*     */                 case '-':
/* 108 */                   neg = true;
/* 109 */                   i++;
/*     */                 
/*     */                 case '0':
/*     */                 case '1':
/*     */                 case '2':
/*     */                 case '3':
/*     */                 case '4':
/*     */                 case '5':
/*     */                 case '6':
/*     */                 case '7':
/*     */                 case '8':
/*     */                 case '9':
/* 121 */                   i1 = i;
/* 122 */                   for (; i < line.length(); i++) {
/* 123 */                     c = line.charAt(i);
/* 124 */                     if (c < '0' || c > '9') {
/*     */                       break;
/*     */                     }
/*     */                   } 
/*     */                   try {
/* 129 */                     idx = Integer.parseInt(line.substring(i1, i));
/* 130 */                   } catch (NumberFormatException e) {
/* 131 */                     throw new IllegalArgumentException((neg ? "!-" : "!") + line.substring(i1, i) + ": event not found");
/*     */                   } 
/* 133 */                   if (neg && idx > 0 && idx <= history.size()) {
/* 134 */                     rep = history.get(history.index() - idx); break;
/* 135 */                   }  if (!neg && idx > history.index() - history.size() && idx <= history.index()) {
/* 136 */                     rep = history.get(idx - 1); break;
/*     */                   } 
/* 138 */                   throw new IllegalArgumentException((neg ? "!-" : "!") + line.substring(i1, i) + ": event not found");
/*     */ 
/*     */                 
/*     */                 default:
/* 142 */                   ss = line.substring(i);
/* 143 */                   i = line.length();
/* 144 */                   idx = searchBackwards(history, ss, history.index(), true);
/* 145 */                   if (idx < 0) {
/* 146 */                     throw new IllegalArgumentException("!" + ss + ": event not found");
/*     */                   }
/* 148 */                   rep = history.get(idx);
/*     */                   break;
/*     */               } 
/*     */               
/* 152 */               if (rep != null)
/* 153 */                 sb.append(rep); 
/*     */               break;
/*     */             } 
/* 156 */             sb.append(c);
/*     */             break;
/*     */           
/*     */           case '^':
/* 160 */             if (i == 0) {
/* 161 */               int i1 = line.indexOf('^', i + 1);
/* 162 */               int i2 = line.indexOf('^', i1 + 1);
/* 163 */               if (i2 < 0) {
/* 164 */                 i2 = line.length();
/*     */               }
/* 166 */               if (i1 > 0 && i2 > 0) {
/* 167 */                 String s1 = line.substring(i + 1, i1);
/* 168 */                 String s2 = line.substring(i1 + 1, i2);
/* 169 */                 String s = history.get(history.index() - 1).replace(s1, s2);
/* 170 */                 sb.append(s);
/* 171 */                 i = i2 + 1;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 175 */             sb.append(c);
/*     */             break;
/*     */           default:
/* 178 */             sb.append(c);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 183 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String expandVar(String word) {
/* 188 */     return word;
/*     */   }
/*     */   
/*     */   protected int searchBackwards(History history, String searchTerm, int startIndex, boolean startsWith) {
/* 192 */     ListIterator<History.Entry> it = history.iterator(startIndex);
/* 193 */     while (it.hasPrevious()) {
/* 194 */       History.Entry e = it.previous();
/* 195 */       if (startsWith) {
/* 196 */         if (e.line().startsWith(searchTerm))
/* 197 */           return e.index(); 
/*     */         continue;
/*     */       } 
/* 200 */       if (e.line().contains(searchTerm)) {
/* 201 */         return e.index();
/*     */       }
/*     */     } 
/*     */     
/* 205 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\DefaultExpander.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */