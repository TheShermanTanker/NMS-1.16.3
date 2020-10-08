/*     */ package org.jline.reader.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import org.jline.reader.CompletingParsedLine;
/*     */ import org.jline.reader.EOFError;
/*     */ import org.jline.reader.ParsedLine;
/*     */ import org.jline.reader.Parser;
/*     */ 
/*     */ public class DefaultParser
/*     */   implements Parser
/*     */ {
/*     */   public enum Bracket
/*     */   {
/*  22 */     ROUND,
/*  23 */     CURLY,
/*  24 */     SQUARE,
/*  25 */     ANGLE;
/*     */   }
/*     */   
/*  28 */   private char[] quoteChars = new char[] { '\'', '"' };
/*     */   
/*  30 */   private char[] escapeChars = new char[] { '\\' };
/*     */   
/*     */   private boolean eofOnUnclosedQuote;
/*     */   
/*     */   private boolean eofOnEscapedNewLine;
/*     */   
/*  36 */   private char[] openingBrackets = null;
/*     */   
/*  38 */   private char[] closingBrackets = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultParser quoteChars(char[] chars) {
/*  45 */     this.quoteChars = chars;
/*  46 */     return this;
/*     */   }
/*     */   
/*     */   public DefaultParser escapeChars(char[] chars) {
/*  50 */     this.escapeChars = chars;
/*  51 */     return this;
/*     */   }
/*     */   
/*     */   public DefaultParser eofOnUnclosedQuote(boolean eofOnUnclosedQuote) {
/*  55 */     this.eofOnUnclosedQuote = eofOnUnclosedQuote;
/*  56 */     return this;
/*     */   }
/*     */   
/*     */   public DefaultParser eofOnUnclosedBracket(Bracket... brackets) {
/*  60 */     setEofOnUnclosedBracket(brackets);
/*  61 */     return this;
/*     */   }
/*     */   
/*     */   public DefaultParser eofOnEscapedNewLine(boolean eofOnEscapedNewLine) {
/*  65 */     this.eofOnEscapedNewLine = eofOnEscapedNewLine;
/*  66 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQuoteChars(char[] chars) {
/*  74 */     this.quoteChars = chars;
/*     */   }
/*     */   
/*     */   public char[] getQuoteChars() {
/*  78 */     return this.quoteChars;
/*     */   }
/*     */   
/*     */   public void setEscapeChars(char[] chars) {
/*  82 */     this.escapeChars = chars;
/*     */   }
/*     */   
/*     */   public char[] getEscapeChars() {
/*  86 */     return this.escapeChars;
/*     */   }
/*     */   
/*     */   public void setEofOnUnclosedQuote(boolean eofOnUnclosedQuote) {
/*  90 */     this.eofOnUnclosedQuote = eofOnUnclosedQuote;
/*     */   }
/*     */   
/*     */   public boolean isEofOnUnclosedQuote() {
/*  94 */     return this.eofOnUnclosedQuote;
/*     */   }
/*     */   
/*     */   public void setEofOnEscapedNewLine(boolean eofOnEscapedNewLine) {
/*  98 */     this.eofOnEscapedNewLine = eofOnEscapedNewLine;
/*     */   }
/*     */   
/*     */   public boolean isEofOnEscapedNewLine() {
/* 102 */     return this.eofOnEscapedNewLine;
/*     */   }
/*     */   
/*     */   public void setEofOnUnclosedBracket(Bracket... brackets) {
/* 106 */     if (brackets == null) {
/* 107 */       this.openingBrackets = null;
/* 108 */       this.closingBrackets = null;
/*     */     } else {
/* 110 */       Set<Bracket> bs = new HashSet<>(Arrays.asList(brackets));
/* 111 */       this.openingBrackets = new char[bs.size()];
/* 112 */       this.closingBrackets = new char[bs.size()];
/* 113 */       int i = 0;
/* 114 */       for (Bracket b : bs) {
/* 115 */         switch (b) {
/*     */           case ROUND:
/* 117 */             this.openingBrackets[i] = '(';
/* 118 */             this.closingBrackets[i] = ')';
/*     */             break;
/*     */           case CURLY:
/* 121 */             this.openingBrackets[i] = '{';
/* 122 */             this.closingBrackets[i] = '}';
/*     */             break;
/*     */           case SQUARE:
/* 125 */             this.openingBrackets[i] = '[';
/* 126 */             this.closingBrackets[i] = ']';
/*     */             break;
/*     */           case ANGLE:
/* 129 */             this.openingBrackets[i] = '<';
/* 130 */             this.closingBrackets[i] = '>';
/*     */             break;
/*     */         } 
/* 133 */         i++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParsedLine parse(String line, int cursor, Parser.ParseContext context) {
/* 139 */     List<String> words = new LinkedList<>();
/* 140 */     StringBuilder current = new StringBuilder();
/* 141 */     int wordCursor = -1;
/* 142 */     int wordIndex = -1;
/* 143 */     int quoteStart = -1;
/* 144 */     int rawWordCursor = -1;
/* 145 */     int rawWordLength = -1;
/* 146 */     int rawWordStart = 0;
/* 147 */     BracketChecker bracketChecker = new BracketChecker();
/* 148 */     boolean quotedWord = false;
/*     */     
/* 150 */     for (int i = 0; line != null && i < line.length(); i++) {
/*     */ 
/*     */       
/* 153 */       if (i == cursor) {
/* 154 */         wordIndex = words.size();
/*     */ 
/*     */         
/* 157 */         wordCursor = current.length();
/* 158 */         rawWordCursor = i - rawWordStart;
/*     */       } 
/*     */       
/* 161 */       if (quoteStart < 0 && isQuoteChar(line, i)) {
/*     */         
/* 163 */         quoteStart = i;
/* 164 */         if (current.length() == 0) {
/* 165 */           quotedWord = true;
/*     */         } else {
/* 167 */           current.append(line.charAt(i));
/*     */         } 
/* 169 */       } else if (quoteStart >= 0 && line.charAt(quoteStart) == line.charAt(i) && !isEscaped(line, i)) {
/*     */         
/* 171 */         if (!quotedWord) {
/* 172 */           current.append(line.charAt(i));
/* 173 */         } else if (rawWordCursor >= 0 && rawWordLength < 0) {
/* 174 */           rawWordLength = i - rawWordStart + 1;
/*     */         } 
/* 176 */         quoteStart = -1;
/* 177 */         quotedWord = false;
/* 178 */       } else if (quoteStart < 0 && isDelimiter(line, i)) {
/*     */         
/* 180 */         if (current.length() > 0) {
/* 181 */           words.add(current.toString());
/* 182 */           current.setLength(0);
/* 183 */           if (rawWordCursor >= 0 && rawWordLength < 0) {
/* 184 */             rawWordLength = i - rawWordStart;
/*     */           }
/*     */         } 
/* 187 */         rawWordStart = i + 1;
/*     */       }
/* 189 */       else if (!isEscapeChar(line, i)) {
/* 190 */         current.append(line.charAt(i));
/* 191 */         if (quoteStart < 0) {
/* 192 */           bracketChecker.check(line, i);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 198 */     if (current.length() > 0 || cursor == line.length()) {
/* 199 */       words.add(current.toString());
/* 200 */       if (rawWordCursor >= 0 && rawWordLength < 0) {
/* 201 */         rawWordLength = line.length() - rawWordStart;
/*     */       }
/*     */     } 
/*     */     
/* 205 */     if (cursor == line.length()) {
/* 206 */       wordIndex = words.size() - 1;
/* 207 */       wordCursor = ((String)words.get(words.size() - 1)).length();
/* 208 */       rawWordCursor = cursor - rawWordStart;
/* 209 */       rawWordLength = rawWordCursor;
/*     */     } 
/*     */     
/* 212 */     if (context != Parser.ParseContext.COMPLETE) {
/* 213 */       if (this.eofOnEscapedNewLine && isEscapeChar(line, line.length() - 1)) {
/* 214 */         throw new EOFError(-1, -1, "Escaped new line", "newline");
/*     */       }
/* 216 */       if (this.eofOnUnclosedQuote && quoteStart >= 0) {
/* 217 */         throw new EOFError(-1, -1, "Missing closing quote", (line.charAt(quoteStart) == '\'') ? "quote" : "dquote");
/*     */       }
/*     */       
/* 220 */       if (bracketChecker.isOpeningBracketMissing()) {
/* 221 */         throw new EOFError(-1, -1, "Missing opening bracket", "missing: " + bracketChecker.getMissingOpeningBracket());
/*     */       }
/* 223 */       if (bracketChecker.isClosingBracketMissing()) {
/* 224 */         throw new EOFError(-1, -1, "Missing closing brackets", "add: " + bracketChecker.getMissingClosingBrackets());
/*     */       }
/*     */     } 
/*     */     
/* 228 */     String openingQuote = quotedWord ? line.substring(quoteStart, quoteStart + 1) : null;
/* 229 */     return new ArgumentList(line, words, wordIndex, wordCursor, cursor, openingQuote, rawWordCursor, rawWordLength);
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
/*     */   public boolean isDelimiter(CharSequence buffer, int pos) {
/* 242 */     return (!isQuoted(buffer, pos) && !isEscaped(buffer, pos) && isDelimiterChar(buffer, pos));
/*     */   }
/*     */   
/*     */   public boolean isQuoted(CharSequence buffer, int pos) {
/* 246 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isQuoteChar(CharSequence buffer, int pos) {
/* 250 */     if (pos < 0) {
/* 251 */       return false;
/*     */     }
/* 253 */     if (this.quoteChars != null) {
/* 254 */       for (char e : this.quoteChars) {
/* 255 */         if (e == buffer.charAt(pos)) {
/* 256 */           return !isEscaped(buffer, pos);
/*     */         }
/*     */       } 
/*     */     }
/* 260 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEscapeChar(char ch) {
/* 265 */     if (this.escapeChars != null) {
/* 266 */       for (char e : this.escapeChars) {
/* 267 */         if (e == ch) {
/* 268 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 272 */     return false;
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
/*     */   public boolean isEscapeChar(CharSequence buffer, int pos) {
/* 286 */     if (pos < 0) {
/* 287 */       return false;
/*     */     }
/* 289 */     char ch = buffer.charAt(pos);
/* 290 */     return (isEscapeChar(ch) && !isEscaped(buffer, pos));
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
/*     */   public boolean isEscaped(CharSequence buffer, int pos) {
/* 304 */     if (pos <= 0) {
/* 305 */       return false;
/*     */     }
/* 307 */     return isEscapeChar(buffer, pos - 1);
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
/*     */   public boolean isDelimiterChar(CharSequence buffer, int pos) {
/* 322 */     return Character.isWhitespace(buffer.charAt(pos));
/*     */   }
/*     */   
/*     */   private boolean isRawEscapeChar(char key) {
/* 326 */     if (this.escapeChars != null) {
/* 327 */       for (char e : this.escapeChars) {
/* 328 */         if (e == key) {
/* 329 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 333 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isRawQuoteChar(char key) {
/* 337 */     if (this.quoteChars != null) {
/* 338 */       for (char e : this.quoteChars) {
/* 339 */         if (e == key) {
/* 340 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 344 */     return false;
/*     */   }
/*     */   
/*     */   private class BracketChecker {
/* 348 */     private int missingOpeningBracket = -1;
/* 349 */     private List<Integer> nested = new ArrayList<>();
/*     */ 
/*     */ 
/*     */     
/*     */     public void check(CharSequence buffer, int pos) {
/* 354 */       if (DefaultParser.this.openingBrackets == null || pos < 0) {
/*     */         return;
/*     */       }
/* 357 */       int bid = bracketId(DefaultParser.this.openingBrackets, buffer, pos);
/* 358 */       if (bid >= 0) {
/* 359 */         this.nested.add(Integer.valueOf(bid));
/*     */       } else {
/* 361 */         bid = bracketId(DefaultParser.this.closingBrackets, buffer, pos);
/* 362 */         if (bid >= 0) {
/* 363 */           if (!this.nested.isEmpty() && bid == ((Integer)this.nested.get(this.nested.size() - 1)).intValue()) {
/* 364 */             this.nested.remove(this.nested.size() - 1);
/*     */           } else {
/* 366 */             this.missingOpeningBracket = bid;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isOpeningBracketMissing() {
/* 373 */       return (this.missingOpeningBracket != -1);
/*     */     }
/*     */     
/*     */     public String getMissingOpeningBracket() {
/* 377 */       if (!isOpeningBracketMissing()) {
/* 378 */         return null;
/*     */       }
/* 380 */       return Character.toString(DefaultParser.this.openingBrackets[this.missingOpeningBracket]);
/*     */     }
/*     */     
/*     */     public boolean isClosingBracketMissing() {
/* 384 */       return !this.nested.isEmpty();
/*     */     }
/*     */     
/*     */     public String getMissingClosingBrackets() {
/* 388 */       if (!isClosingBracketMissing()) {
/* 389 */         return null;
/*     */       }
/* 391 */       StringBuilder out = new StringBuilder();
/* 392 */       for (int i = this.nested.size() - 1; i > -1; i--) {
/* 393 */         out.append(DefaultParser.this.closingBrackets[((Integer)this.nested.get(i)).intValue()]);
/*     */       }
/* 395 */       return out.toString();
/*     */     }
/*     */     
/*     */     private int bracketId(char[] brackets, CharSequence buffer, int pos) {
/* 399 */       for (int i = 0; i < brackets.length; i++) {
/* 400 */         if (buffer.charAt(pos) == brackets[i]) {
/* 401 */           return i;
/*     */         }
/*     */       } 
/* 404 */       return -1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class ArgumentList
/*     */     implements ParsedLine, CompletingParsedLine
/*     */   {
/*     */     private final String line;
/*     */ 
/*     */     
/*     */     private final List<String> words;
/*     */ 
/*     */     
/*     */     private final int wordIndex;
/*     */ 
/*     */     
/*     */     private final int wordCursor;
/*     */     
/*     */     private final int cursor;
/*     */     
/*     */     private final String openingQuote;
/*     */     
/*     */     private final int rawWordCursor;
/*     */     
/*     */     private final int rawWordLength;
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public ArgumentList(String line, List<String> words, int wordIndex, int wordCursor, int cursor) {
/* 435 */       this(line, words, wordIndex, wordCursor, cursor, null, wordCursor, ((String)words
/* 436 */           .get(wordIndex)).length());
/*     */     }
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
/*     */     public ArgumentList(String line, List<String> words, int wordIndex, int wordCursor, int cursor, String openingQuote, int rawWordCursor, int rawWordLength) {
/* 454 */       this.line = line;
/* 455 */       this.words = Collections.unmodifiableList(Objects.<List<? extends String>>requireNonNull(words));
/* 456 */       this.wordIndex = wordIndex;
/* 457 */       this.wordCursor = wordCursor;
/* 458 */       this.cursor = cursor;
/* 459 */       this.openingQuote = openingQuote;
/* 460 */       this.rawWordCursor = rawWordCursor;
/* 461 */       this.rawWordLength = rawWordLength;
/*     */     }
/*     */     
/*     */     public int wordIndex() {
/* 465 */       return this.wordIndex;
/*     */     }
/*     */ 
/*     */     
/*     */     public String word() {
/* 470 */       if (this.wordIndex < 0 || this.wordIndex >= this.words.size()) {
/* 471 */         return "";
/*     */       }
/* 473 */       return this.words.get(this.wordIndex);
/*     */     }
/*     */     
/*     */     public int wordCursor() {
/* 477 */       return this.wordCursor;
/*     */     }
/*     */     
/*     */     public List<String> words() {
/* 481 */       return this.words;
/*     */     }
/*     */     
/*     */     public int cursor() {
/* 485 */       return this.cursor;
/*     */     }
/*     */     
/*     */     public String line() {
/* 489 */       return this.line;
/*     */     }
/*     */     
/*     */     public CharSequence escape(CharSequence candidate, boolean complete) {
/* 493 */       StringBuilder sb = new StringBuilder(candidate);
/*     */       
/* 495 */       String quote = this.openingQuote;
/* 496 */       boolean middleQuotes = false;
/* 497 */       if (this.openingQuote == null) {
/* 498 */         for (int i = 0; i < sb.length(); i++) {
/* 499 */           if (DefaultParser.this.isQuoteChar(sb, i)) {
/* 500 */             middleQuotes = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/* 505 */       if (DefaultParser.this.escapeChars != null) {
/*     */         Predicate<Integer> needToBeEscaped;
/*     */ 
/*     */         
/* 509 */         if (this.openingQuote != null) {
/* 510 */           needToBeEscaped = (i -> (DefaultParser.this.isRawEscapeChar(sb.charAt(i.intValue())) || String.valueOf(sb.charAt(i.intValue())).equals(this.openingQuote)));
/*     */ 
/*     */         
/*     */         }
/* 514 */         else if (middleQuotes) {
/* 515 */           needToBeEscaped = (i -> DefaultParser.this.isRawEscapeChar(sb.charAt(i.intValue())));
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 520 */           needToBeEscaped = (i -> (DefaultParser.this.isDelimiterChar(sb, i.intValue()) || DefaultParser.this.isRawEscapeChar(sb.charAt(i.intValue())) || DefaultParser.this.isRawQuoteChar(sb.charAt(i.intValue()))));
/*     */         } 
/* 522 */         for (int i = 0; i < sb.length(); i++) {
/* 523 */           if (needToBeEscaped.test(Integer.valueOf(i))) {
/* 524 */             sb.insert(i++, DefaultParser.this.escapeChars[0]);
/*     */           }
/*     */         } 
/* 527 */       } else if (this.openingQuote == null && !middleQuotes) {
/* 528 */         for (int i = 0; i < sb.length(); i++) {
/* 529 */           if (DefaultParser.this.isDelimiterChar(sb, i)) {
/* 530 */             quote = "'";
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 535 */       if (quote != null) {
/* 536 */         sb.insert(0, quote);
/* 537 */         if (complete) {
/* 538 */           sb.append(quote);
/*     */         }
/*     */       } 
/* 541 */       return sb;
/*     */     }
/*     */ 
/*     */     
/*     */     public int rawWordCursor() {
/* 546 */       return this.rawWordCursor;
/*     */     }
/*     */ 
/*     */     
/*     */     public int rawWordLength() {
/* 551 */       return this.rawWordLength;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\DefaultParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */