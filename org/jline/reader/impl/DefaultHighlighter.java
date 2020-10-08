/*    */ package org.jline.reader.impl;
/*    */ 
/*    */ import org.jline.reader.Highlighter;
/*    */ import org.jline.reader.LineReader;
/*    */ import org.jline.utils.AttributedString;
/*    */ import org.jline.utils.AttributedStringBuilder;
/*    */ import org.jline.utils.AttributedStyle;
/*    */ import org.jline.utils.WCWidth;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultHighlighter
/*    */   implements Highlighter
/*    */ {
/*    */   public AttributedString highlight(LineReader reader, String buffer) {
/* 23 */     int underlineStart = -1;
/* 24 */     int underlineEnd = -1;
/* 25 */     int negativeStart = -1;
/* 26 */     int negativeEnd = -1;
/* 27 */     String search = reader.getSearchTerm();
/* 28 */     if (search != null && search.length() > 0) {
/* 29 */       underlineStart = buffer.indexOf(search);
/* 30 */       if (underlineStart >= 0) {
/* 31 */         underlineEnd = underlineStart + search.length() - 1;
/*    */       }
/*    */     } 
/* 34 */     if (reader.getRegionActive() != LineReader.RegionType.NONE) {
/* 35 */       negativeStart = reader.getRegionMark();
/* 36 */       negativeEnd = reader.getBuffer().cursor();
/* 37 */       if (negativeStart > negativeEnd) {
/* 38 */         int x = negativeEnd;
/* 39 */         negativeEnd = negativeStart;
/* 40 */         negativeStart = x;
/*    */       } 
/* 42 */       if (reader.getRegionActive() == LineReader.RegionType.LINE) {
/* 43 */         while (negativeStart > 0 && reader.getBuffer().atChar(negativeStart - 1) != 10) {
/* 44 */           negativeStart--;
/*    */         }
/* 46 */         while (negativeEnd < reader.getBuffer().length() - 1 && reader.getBuffer().atChar(negativeEnd + 1) != 10) {
/* 47 */           negativeEnd++;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 52 */     AttributedStringBuilder sb = new AttributedStringBuilder();
/* 53 */     for (int i = 0; i < buffer.length(); i++) {
/* 54 */       if (i == underlineStart) {
/* 55 */         sb.style(AttributedStyle::underline);
/*    */       }
/* 57 */       if (i == negativeStart) {
/* 58 */         sb.style(AttributedStyle::inverse);
/*    */       }
/* 60 */       char c = buffer.charAt(i);
/* 61 */       if (c == '\t' || c == '\n') {
/* 62 */         sb.append(c);
/* 63 */       } else if (c < ' ') {
/* 64 */         sb.style(AttributedStyle::inverseNeg)
/* 65 */           .append('^')
/* 66 */           .append((char)(c + 64))
/* 67 */           .style(AttributedStyle::inverseNeg);
/*    */       } else {
/* 69 */         int w = WCWidth.wcwidth(c);
/* 70 */         if (w > 0) {
/* 71 */           sb.append(c);
/*    */         }
/*    */       } 
/* 74 */       if (i == underlineEnd) {
/* 75 */         sb.style(AttributedStyle::underlineOff);
/*    */       }
/* 77 */       if (i == negativeEnd) {
/* 78 */         sb.style(AttributedStyle::inverseOff);
/*    */       }
/*    */     } 
/* 81 */     return sb.toAttributedString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\DefaultHighlighter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */