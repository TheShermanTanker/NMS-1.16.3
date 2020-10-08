/*     */ package org.jline.keymap;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyMap<T>
/*     */ {
/*     */   public static final int KEYMAP_LENGTH = 128;
/*     */   public static final long DEFAULT_AMBIGUOUS_TIMEOUT = 1000L;
/*  34 */   private Object[] mapping = new Object[128];
/*  35 */   private T anotherKey = null;
/*     */   private T unicode;
/*     */   private T nomatch;
/*  38 */   private long ambiguousTimeout = 1000L;
/*     */   
/*     */   public static String display(String key) {
/*  41 */     StringBuilder sb = new StringBuilder();
/*  42 */     sb.append("\"");
/*  43 */     for (int i = 0; i < key.length(); i++) {
/*  44 */       char c = key.charAt(i);
/*  45 */       if (c < ' ') {
/*  46 */         sb.append('^');
/*  47 */         sb.append((char)(c + 65 - 1));
/*  48 */       } else if (c == '') {
/*  49 */         sb.append("^?");
/*  50 */       } else if (c == '^' || c == '\\') {
/*  51 */         sb.append('\\').append(c);
/*  52 */       } else if (c >= '') {
/*  53 */         sb.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c) }));
/*     */       } else {
/*  55 */         sb.append(c);
/*     */       } 
/*     */     } 
/*  58 */     sb.append("\"");
/*  59 */     return sb.toString();
/*     */   }
/*     */   public static final Comparator<String> KEYSEQ_COMPARATOR;
/*     */   
/*     */   public static String translate(String str) {
/*  64 */     if (!str.isEmpty()) {
/*  65 */       char c = str.charAt(0);
/*  66 */       if ((c == '\'' || c == '"') && str.charAt(str.length() - 1) == c) {
/*  67 */         str = str.substring(1, str.length() - 1);
/*     */       }
/*     */     } 
/*  70 */     StringBuilder keySeq = new StringBuilder();
/*  71 */     for (int i = 0; i < str.length(); i++) {
/*  72 */       char c = str.charAt(i);
/*  73 */       if (c == '\\') {
/*  74 */         int j; if (++i >= str.length()) {
/*     */           break;
/*     */         }
/*  77 */         c = str.charAt(i);
/*  78 */         switch (c) {
/*     */           case 'a':
/*  80 */             c = '\007';
/*     */             break;
/*     */           case 'b':
/*  83 */             c = '\b';
/*     */             break;
/*     */           case 'd':
/*  86 */             c = '';
/*     */             break;
/*     */           case 'E':
/*     */           case 'e':
/*  90 */             c = '\033';
/*     */             break;
/*     */           case 'f':
/*  93 */             c = '\f';
/*     */             break;
/*     */           case 'n':
/*  96 */             c = '\n';
/*     */             break;
/*     */           case 'r':
/*  99 */             c = '\r';
/*     */             break;
/*     */           case 't':
/* 102 */             c = '\t';
/*     */             break;
/*     */           case 'v':
/* 105 */             c = '\013';
/*     */             break;
/*     */           case '\\':
/* 108 */             c = '\\';
/*     */             break;
/*     */           case '0':
/*     */           case '1':
/*     */           case '2':
/*     */           case '3':
/*     */           case '4':
/*     */           case '5':
/*     */           case '6':
/*     */           case '7':
/* 118 */             c = Character.MIN_VALUE;
/* 119 */             for (j = 0; j < 3 && 
/* 120 */               i < str.length(); j++, i++) {
/*     */ 
/*     */               
/* 123 */               int k = Character.digit(str.charAt(i), 8);
/* 124 */               if (k < 0) {
/*     */                 break;
/*     */               }
/* 127 */               c = (char)(c * 8 + k);
/*     */             } 
/* 129 */             i--;
/* 130 */             c = (char)(c & 0xFF);
/*     */             break;
/*     */           case 'x':
/* 133 */             i++;
/* 134 */             c = Character.MIN_VALUE;
/* 135 */             for (j = 0; j < 2 && 
/* 136 */               i < str.length(); j++, i++) {
/*     */ 
/*     */               
/* 139 */               int k = Character.digit(str.charAt(i), 16);
/* 140 */               if (k < 0) {
/*     */                 break;
/*     */               }
/* 143 */               c = (char)(c * 16 + k);
/*     */             } 
/* 145 */             i--;
/* 146 */             c = (char)(c & 0xFF);
/*     */             break;
/*     */           case 'u':
/* 149 */             i++;
/* 150 */             c = Character.MIN_VALUE;
/* 151 */             for (j = 0; j < 4 && 
/* 152 */               i < str.length(); j++, i++) {
/*     */ 
/*     */               
/* 155 */               int k = Character.digit(str.charAt(i), 16);
/* 156 */               if (k < 0) {
/*     */                 break;
/*     */               }
/* 159 */               c = (char)(c * 16 + k);
/*     */             } 
/*     */             break;
/*     */           case 'C':
/* 163 */             if (++i >= str.length()) {
/*     */               break;
/*     */             }
/* 166 */             c = str.charAt(i);
/* 167 */             if (c == '-') {
/* 168 */               if (++i >= str.length()) {
/*     */                 break;
/*     */               }
/* 171 */               c = str.charAt(i);
/*     */             } 
/* 173 */             c = (c == '?') ? '' : (char)(Character.toUpperCase(c) & 0x1F);
/*     */             break;
/*     */         } 
/* 176 */       } else if (c == '^') {
/* 177 */         if (++i >= str.length()) {
/*     */           break;
/*     */         }
/* 180 */         c = str.charAt(i);
/* 181 */         if (c != '^') {
/* 182 */           c = (c == '?') ? '' : (char)(Character.toUpperCase(c) & 0x1F);
/*     */         }
/*     */       } 
/* 185 */       keySeq.append(c);
/*     */     } 
/* 187 */     return keySeq.toString();
/*     */   }
/*     */   
/*     */   public static Collection<String> range(String range) {
/* 191 */     String pfx, keys[] = range.split("-");
/* 192 */     if (keys.length != 2) {
/* 193 */       return null;
/*     */     }
/* 195 */     keys[0] = translate(keys[0]);
/* 196 */     keys[1] = translate(keys[1]);
/* 197 */     if (keys[0].length() != keys[1].length()) {
/* 198 */       return null;
/*     */     }
/*     */     
/* 201 */     if (keys[0].length() > 1) {
/* 202 */       pfx = keys[0].substring(0, keys[0].length() - 1);
/* 203 */       if (!keys[1].startsWith(pfx)) {
/* 204 */         return null;
/*     */       }
/*     */     } else {
/* 207 */       pfx = "";
/*     */     } 
/* 209 */     char c0 = keys[0].charAt(keys[0].length() - 1);
/* 210 */     char c1 = keys[1].charAt(keys[1].length() - 1);
/* 211 */     if (c0 > c1) {
/* 212 */       return null;
/*     */     }
/* 214 */     Collection<String> seqs = new ArrayList<>(); char c;
/* 215 */     for (c = c0; c <= c1; c = (char)(c + 1)) {
/* 216 */       seqs.add(pfx + c);
/*     */     }
/* 218 */     return seqs;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String esc() {
/* 223 */     return "\033";
/*     */   }
/*     */   
/*     */   public static String alt(char c) {
/* 227 */     return "\033" + c;
/*     */   }
/*     */   
/*     */   public static String alt(String c) {
/* 231 */     return "\033" + c;
/*     */   }
/*     */   
/*     */   public static String del() {
/* 235 */     return "";
/*     */   }
/*     */   
/*     */   public static String ctrl(char key) {
/* 239 */     return (key == '?') ? del() : Character.toString((char)(Character.toUpperCase(key) & 0x1F));
/*     */   }
/*     */   
/*     */   public static String key(Terminal terminal, InfoCmp.Capability capability) {
/* 243 */     return Curses.tputs(terminal.getStringCapability(capability), new Object[0]);
/*     */   }
/*     */   static {
/* 246 */     KEYSEQ_COMPARATOR = ((s1, s2) -> {
/*     */         int len1 = s1.length();
/*     */         int len2 = s2.length();
/*     */         int lim = Math.min(len1, len2);
/*     */         for (int k = 0; k < lim; k++) {
/*     */           char c1 = s1.charAt(k);
/*     */           char c2 = s2.charAt(k);
/*     */           if (c1 != c2) {
/*     */             int l = len1 - len2;
/*     */             return (l != 0) ? l : (c1 - c2);
/*     */           } 
/*     */         } 
/*     */         return len1 - len2;
/*     */       });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T getUnicode() {
/* 269 */     return this.unicode;
/*     */   }
/*     */   
/*     */   public void setUnicode(T unicode) {
/* 273 */     this.unicode = unicode;
/*     */   }
/*     */   
/*     */   public T getNomatch() {
/* 277 */     return this.nomatch;
/*     */   }
/*     */   
/*     */   public void setNomatch(T nomatch) {
/* 281 */     this.nomatch = nomatch;
/*     */   }
/*     */   
/*     */   public long getAmbiguousTimeout() {
/* 285 */     return this.ambiguousTimeout;
/*     */   }
/*     */   
/*     */   public void setAmbiguousTimeout(long ambiguousTimeout) {
/* 289 */     this.ambiguousTimeout = ambiguousTimeout;
/*     */   }
/*     */   
/*     */   public T getAnotherKey() {
/* 293 */     return this.anotherKey;
/*     */   }
/*     */   
/*     */   public Map<String, T> getBoundKeys() {
/* 297 */     Map<String, T> bound = new TreeMap<>(KEYSEQ_COMPARATOR);
/* 298 */     doGetBoundKeys(this, "", bound);
/* 299 */     return bound;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> void doGetBoundKeys(KeyMap<T> keyMap, String prefix, Map<String, T> bound) {
/* 304 */     if (keyMap.anotherKey != null) {
/* 305 */       bound.put(prefix, keyMap.anotherKey);
/*     */     }
/* 307 */     for (int c = 0; c < keyMap.mapping.length; c++) {
/* 308 */       if (keyMap.mapping[c] instanceof KeyMap) {
/* 309 */         doGetBoundKeys((KeyMap<T>)keyMap.mapping[c], prefix + (char)c, bound);
/*     */       
/*     */       }
/* 312 */       else if (keyMap.mapping[c] != null) {
/* 313 */         bound.put(prefix + (char)c, (T)keyMap.mapping[c]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public T getBound(CharSequence keySeq, int[] remaining) {
/* 320 */     remaining[0] = -1;
/* 321 */     if (keySeq != null && keySeq.length() > 0) {
/* 322 */       char c = keySeq.charAt(0);
/* 323 */       if (c >= this.mapping.length) {
/* 324 */         remaining[0] = Character.codePointCount(keySeq, 0, keySeq.length());
/* 325 */         return null;
/*     */       } 
/* 327 */       if (this.mapping[c] instanceof KeyMap) {
/* 328 */         CharSequence sub = keySeq.subSequence(1, keySeq.length());
/* 329 */         return ((KeyMap<T>)this.mapping[c]).getBound(sub, remaining);
/* 330 */       }  if (this.mapping[c] != null) {
/* 331 */         remaining[0] = keySeq.length() - 1;
/* 332 */         return (T)this.mapping[c];
/*     */       } 
/* 334 */       remaining[0] = keySeq.length();
/* 335 */       return this.anotherKey;
/*     */     } 
/*     */ 
/*     */     
/* 339 */     return this.anotherKey;
/*     */   }
/*     */ 
/*     */   
/*     */   public T getBound(CharSequence keySeq) {
/* 344 */     int[] remaining = new int[1];
/* 345 */     T res = getBound(keySeq, remaining);
/* 346 */     return (remaining[0] <= 0) ? res : null;
/*     */   }
/*     */   
/*     */   public void bindIfNotBound(T function, CharSequence keySeq) {
/* 350 */     if (function != null && keySeq != null) {
/* 351 */       bind(this, keySeq, function, true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void bind(T function, CharSequence... keySeqs) {
/* 356 */     for (CharSequence keySeq : keySeqs) {
/* 357 */       bind(function, keySeq);
/*     */     }
/*     */   }
/*     */   
/*     */   public void bind(T function, Iterable<? extends CharSequence> keySeqs) {
/* 362 */     for (CharSequence keySeq : keySeqs) {
/* 363 */       bind(function, keySeq);
/*     */     }
/*     */   }
/*     */   
/*     */   public void bind(T function, CharSequence keySeq) {
/* 368 */     if (keySeq != null) {
/* 369 */       if (function == null) {
/* 370 */         unbind(keySeq);
/*     */       } else {
/* 372 */         bind(this, keySeq, function, false);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void unbind(CharSequence... keySeqs) {
/* 378 */     for (CharSequence keySeq : keySeqs) {
/* 379 */       unbind(keySeq);
/*     */     }
/*     */   }
/*     */   
/*     */   public void unbind(CharSequence keySeq) {
/* 384 */     if (keySeq != null) {
/* 385 */       unbind(this, keySeq);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> T unbind(KeyMap<T> map, CharSequence keySeq) {
/* 391 */     KeyMap<T> prev = null;
/* 392 */     if (keySeq != null && keySeq.length() > 0) {
/* 393 */       for (int i = 0; i < keySeq.length() - 1; i++) {
/* 394 */         char c1 = keySeq.charAt(i);
/* 395 */         if (c1 > map.mapping.length) {
/* 396 */           return null;
/*     */         }
/* 398 */         if (!(map.mapping[c1] instanceof KeyMap)) {
/* 399 */           return null;
/*     */         }
/* 401 */         prev = map;
/* 402 */         map = (KeyMap<T>)map.mapping[c1];
/*     */       } 
/* 404 */       char c = keySeq.charAt(keySeq.length() - 1);
/* 405 */       if (c > map.mapping.length) {
/* 406 */         return null;
/*     */       }
/* 408 */       if (map.mapping[c] instanceof KeyMap) {
/* 409 */         KeyMap<?> sub = (KeyMap)map.mapping[c];
/* 410 */         Object object = sub.anotherKey;
/* 411 */         sub.anotherKey = null;
/* 412 */         return (T)object;
/*     */       } 
/* 414 */       Object res = map.mapping[c];
/* 415 */       map.mapping[c] = null;
/* 416 */       int nb = 0;
/* 417 */       for (int j = 0; j < map.mapping.length; j++) {
/* 418 */         if (map.mapping[j] != null) {
/* 419 */           nb++;
/*     */         }
/*     */       } 
/* 422 */       if (nb == 0 && prev != null) {
/* 423 */         prev.mapping[keySeq.charAt(keySeq.length() - 2)] = map.anotherKey;
/*     */       }
/* 425 */       return (T)res;
/*     */     } 
/*     */     
/* 428 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> void bind(KeyMap<T> map, CharSequence keySeq, T function, boolean onlyIfNotBound) {
/* 433 */     if (keySeq != null && keySeq.length() > 0)
/* 434 */       for (int i = 0; i < keySeq.length(); i++) {
/* 435 */         char c = keySeq.charAt(i);
/* 436 */         if (c >= map.mapping.length) {
/*     */           return;
/*     */         }
/* 439 */         if (i < keySeq.length() - 1) {
/* 440 */           if (!(map.mapping[c] instanceof KeyMap)) {
/* 441 */             KeyMap<T> m = new KeyMap<>();
/* 442 */             m.anotherKey = (T)map.mapping[c];
/* 443 */             map.mapping[c] = m;
/*     */           } 
/* 445 */           map = (KeyMap<T>)map.mapping[c];
/*     */         }
/* 447 */         else if (map.mapping[c] instanceof KeyMap) {
/* 448 */           ((KeyMap)map.mapping[c]).anotherKey = function;
/*     */         } else {
/* 450 */           Object op = map.mapping[c];
/* 451 */           if (!onlyIfNotBound || op == null)
/* 452 */             map.mapping[c] = function; 
/*     */         } 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\keymap\KeyMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */