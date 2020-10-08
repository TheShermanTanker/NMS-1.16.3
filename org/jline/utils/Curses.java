/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.io.Flushable;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.util.Stack;
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
/*     */ public final class Curses
/*     */ {
/*  24 */   private static Object[] sv = new Object[26];
/*  25 */   private static Object[] dv = new Object[26];
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int IFTE_NONE = 0;
/*     */ 
/*     */   
/*     */   private static final int IFTE_IF = 1;
/*     */ 
/*     */   
/*     */   private static final int IFTE_THEN = 2;
/*     */ 
/*     */   
/*     */   private static final int IFTE_ELSE = 3;
/*     */ 
/*     */ 
/*     */   
/*     */   public static String tputs(String cap, Object... params) {
/*  43 */     if (cap != null) {
/*  44 */       StringWriter sw = new StringWriter();
/*  45 */       tputs(sw, cap, params);
/*  46 */       return sw.toString();
/*     */     } 
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void tputs(Appendable out, String str, Object... params) {
/*     */     try {
/*  60 */       doTputs(out, str, params);
/*  61 */     } catch (Exception e) {
/*  62 */       throw new IOError(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void doTputs(Appendable out, String str, Object... params) throws IOException {
/*  67 */     int index = 0;
/*  68 */     int length = str.length();
/*  69 */     int ifte = 0;
/*  70 */     boolean exec = true;
/*  71 */     Stack<Object> stack = new Stack();
/*  72 */     while (index < length) {
/*  73 */       int start; char ch = str.charAt(index++);
/*  74 */       switch (ch) {
/*     */         case '\\':
/*  76 */           ch = str.charAt(index++);
/*  77 */           if (ch >= '0' && ch <= '7') {
/*  78 */             int val = ch - 48;
/*  79 */             for (int i = 0; i < 2; i++) {
/*  80 */               ch = str.charAt(index++);
/*  81 */               if (ch < '0' || ch > '7') {
/*  82 */                 throw new IllegalStateException();
/*     */               }
/*  84 */               val = val * 8 + ch - 48;
/*     */             } 
/*  86 */             out.append((char)val); continue;
/*     */           } 
/*  88 */           switch (ch) {
/*     */             case 'E':
/*     */             case 'e':
/*  91 */               if (exec) {
/*  92 */                 out.append('\033');
/*     */               }
/*     */               continue;
/*     */             case 'n':
/*  96 */               out.append('\n');
/*     */               continue;
/*     */ 
/*     */ 
/*     */             
/*     */             case 'r':
/* 102 */               if (exec) {
/* 103 */                 out.append('\r');
/*     */               }
/*     */               continue;
/*     */             case 't':
/* 107 */               if (exec) {
/* 108 */                 out.append('\t');
/*     */               }
/*     */               continue;
/*     */             case 'b':
/* 112 */               if (exec) {
/* 113 */                 out.append('\b');
/*     */               }
/*     */               continue;
/*     */             case 'f':
/* 117 */               if (exec) {
/* 118 */                 out.append('\f');
/*     */               }
/*     */               continue;
/*     */             case 's':
/* 122 */               if (exec) {
/* 123 */                 out.append(' ');
/*     */               }
/*     */               continue;
/*     */             case ':':
/*     */             case '\\':
/*     */             case '^':
/* 129 */               if (exec) {
/* 130 */                 out.append(ch);
/*     */               }
/*     */               continue;
/*     */           } 
/* 134 */           throw new IllegalArgumentException();
/*     */ 
/*     */ 
/*     */         
/*     */         case '^':
/* 139 */           ch = str.charAt(index++);
/* 140 */           if (exec) {
/* 141 */             out.append((char)(ch - 64));
/*     */           }
/*     */           continue;
/*     */         case '%':
/* 145 */           ch = str.charAt(index++);
/* 146 */           switch (ch) {
/*     */             case '%':
/* 148 */               if (exec) {
/* 149 */                 out.append('%');
/*     */               }
/*     */               continue;
/*     */             case 'p':
/* 153 */               ch = str.charAt(index++);
/* 154 */               if (exec) {
/* 155 */                 stack.push(params[ch - 49]);
/*     */               }
/*     */               continue;
/*     */             case 'P':
/* 159 */               ch = str.charAt(index++);
/* 160 */               if (ch >= 'a' && ch <= 'z') {
/* 161 */                 if (exec)
/* 162 */                   dv[ch - 97] = stack.pop();  continue;
/*     */               } 
/* 164 */               if (ch >= 'A' && ch <= 'Z') {
/* 165 */                 if (exec)
/* 166 */                   sv[ch - 65] = stack.pop(); 
/*     */                 continue;
/*     */               } 
/* 169 */               throw new IllegalArgumentException();
/*     */ 
/*     */             
/*     */             case 'g':
/* 173 */               ch = str.charAt(index++);
/* 174 */               if (ch >= 'a' && ch <= 'z') {
/* 175 */                 if (exec)
/* 176 */                   stack.push(dv[ch - 97]);  continue;
/*     */               } 
/* 178 */               if (ch >= 'A' && ch <= 'Z') {
/* 179 */                 if (exec)
/* 180 */                   stack.push(sv[ch - 65]); 
/*     */                 continue;
/*     */               } 
/* 183 */               throw new IllegalArgumentException();
/*     */ 
/*     */             
/*     */             case '\'':
/* 187 */               ch = str.charAt(index++);
/* 188 */               if (exec) {
/* 189 */                 stack.push(Integer.valueOf(ch));
/*     */               }
/* 191 */               ch = str.charAt(index++);
/* 192 */               if (ch != '\'') {
/* 193 */                 throw new IllegalArgumentException();
/*     */               }
/*     */               continue;
/*     */             case '{':
/* 197 */               start = index;
/* 198 */               while (str.charAt(index++) != '}');
/* 199 */               if (exec) {
/* 200 */                 int v = Integer.valueOf(str.substring(start, index - 1)).intValue();
/* 201 */                 stack.push(Integer.valueOf(v));
/*     */               } 
/*     */               continue;
/*     */             case 'l':
/* 205 */               if (exec) {
/* 206 */                 stack.push(Integer.valueOf(stack.pop().toString().length()));
/*     */               }
/*     */               continue;
/*     */             case '+':
/* 210 */               if (exec) {
/* 211 */                 int v2 = toInteger(stack.pop());
/* 212 */                 int v1 = toInteger(stack.pop());
/* 213 */                 stack.push(Integer.valueOf(v1 + v2));
/*     */               } 
/*     */               continue;
/*     */             case '-':
/* 217 */               if (exec) {
/* 218 */                 int v2 = toInteger(stack.pop());
/* 219 */                 int v1 = toInteger(stack.pop());
/* 220 */                 stack.push(Integer.valueOf(v1 - v2));
/*     */               } 
/*     */               continue;
/*     */             case '*':
/* 224 */               if (exec) {
/* 225 */                 int v2 = toInteger(stack.pop());
/* 226 */                 int v1 = toInteger(stack.pop());
/* 227 */                 stack.push(Integer.valueOf(v1 * v2));
/*     */               } 
/*     */               continue;
/*     */             case '/':
/* 231 */               if (exec) {
/* 232 */                 int v2 = toInteger(stack.pop());
/* 233 */                 int v1 = toInteger(stack.pop());
/* 234 */                 stack.push(Integer.valueOf(v1 / v2));
/*     */               } 
/*     */               continue;
/*     */             case 'm':
/* 238 */               if (exec) {
/* 239 */                 int v2 = toInteger(stack.pop());
/* 240 */                 int v1 = toInteger(stack.pop());
/* 241 */                 stack.push(Integer.valueOf(v1 % v2));
/*     */               } 
/*     */               continue;
/*     */             case '&':
/* 245 */               if (exec) {
/* 246 */                 int v2 = toInteger(stack.pop());
/* 247 */                 int v1 = toInteger(stack.pop());
/* 248 */                 stack.push(Integer.valueOf(v1 & v2));
/*     */               } 
/*     */               continue;
/*     */             case '|':
/* 252 */               if (exec) {
/* 253 */                 int v2 = toInteger(stack.pop());
/* 254 */                 int v1 = toInteger(stack.pop());
/* 255 */                 stack.push(Integer.valueOf(v1 | v2));
/*     */               } 
/*     */               continue;
/*     */             case '^':
/* 259 */               if (exec) {
/* 260 */                 int v2 = toInteger(stack.pop());
/* 261 */                 int v1 = toInteger(stack.pop());
/* 262 */                 stack.push(Integer.valueOf(v1 ^ v2));
/*     */               } 
/*     */               continue;
/*     */             case '=':
/* 266 */               if (exec) {
/* 267 */                 int v2 = toInteger(stack.pop());
/* 268 */                 int v1 = toInteger(stack.pop());
/* 269 */                 stack.push(Boolean.valueOf((v1 == v2)));
/*     */               } 
/*     */               continue;
/*     */             case '>':
/* 273 */               if (exec) {
/* 274 */                 int v2 = toInteger(stack.pop());
/* 275 */                 int v1 = toInteger(stack.pop());
/* 276 */                 stack.push(Boolean.valueOf((v1 > v2)));
/*     */               } 
/*     */               continue;
/*     */             case '<':
/* 280 */               if (exec) {
/* 281 */                 int v2 = toInteger(stack.pop());
/* 282 */                 int v1 = toInteger(stack.pop());
/* 283 */                 stack.push(Boolean.valueOf((v1 < v2)));
/*     */               } 
/*     */               continue;
/*     */             case 'A':
/* 287 */               if (exec) {
/* 288 */                 int v2 = toInteger(stack.pop());
/* 289 */                 int v1 = toInteger(stack.pop());
/* 290 */                 stack.push(Boolean.valueOf((v1 != 0 && v2 != 0)));
/*     */               } 
/*     */               continue;
/*     */             case '!':
/* 294 */               if (exec) {
/* 295 */                 int v1 = toInteger(stack.pop());
/* 296 */                 stack.push(Boolean.valueOf((v1 == 0)));
/*     */               } 
/*     */               continue;
/*     */             case '~':
/* 300 */               if (exec) {
/* 301 */                 int v1 = toInteger(stack.pop());
/* 302 */                 stack.push(Integer.valueOf(v1 ^ 0xFFFFFFFF));
/*     */               } 
/*     */               continue;
/*     */             case 'O':
/* 306 */               if (exec) {
/* 307 */                 int v2 = toInteger(stack.pop());
/* 308 */                 int v1 = toInteger(stack.pop());
/* 309 */                 stack.push(Boolean.valueOf((v1 != 0 || v2 != 0)));
/*     */               } 
/*     */               continue;
/*     */             case '?':
/* 313 */               if (ifte != 0) {
/* 314 */                 throw new IllegalArgumentException();
/*     */               }
/* 316 */               ifte = 1;
/*     */               continue;
/*     */             
/*     */             case 't':
/* 320 */               if (ifte != 1 && ifte != 3) {
/* 321 */                 throw new IllegalArgumentException();
/*     */               }
/* 323 */               ifte = 2;
/*     */               
/* 325 */               exec = (toInteger(stack.pop()) != 0);
/*     */               continue;
/*     */             case 'e':
/* 328 */               if (ifte != 2) {
/* 329 */                 throw new IllegalArgumentException();
/*     */               }
/* 331 */               ifte = 3;
/*     */               
/* 333 */               exec = !exec;
/*     */               continue;
/*     */             case ';':
/* 336 */               if (ifte == 0 || ifte == 1) {
/* 337 */                 throw new IllegalArgumentException();
/*     */               }
/* 339 */               ifte = 0;
/*     */               
/* 341 */               exec = true;
/*     */               continue;
/*     */             case 'i':
/* 344 */               if (params.length >= 1) {
/* 345 */                 params[0] = Integer.valueOf(toInteger(params[0]) + 1);
/*     */               }
/* 347 */               if (params.length >= 2) {
/* 348 */                 params[1] = Integer.valueOf(toInteger(params[1]) + 1);
/*     */               }
/*     */               continue;
/*     */             case 'd':
/* 352 */               out.append(Integer.toString(toInteger(stack.pop())));
/*     */               continue;
/*     */           } 
/* 355 */           throw new UnsupportedOperationException();
/*     */ 
/*     */         
/*     */         case '$':
/* 359 */           if (str.charAt(index) == '<') {
/*     */             
/* 361 */             int nb = 0;
/* 362 */             while ((ch = str.charAt(++index)) != '>') {
/* 363 */               if (ch >= '0' && ch <= '9') {
/* 364 */                 nb = nb * 10 + ch - 48; continue;
/* 365 */               }  if (ch == '*')
/*     */                 continue; 
/* 367 */               if (ch == '/');
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 373 */             index++;
/*     */             try {
/* 375 */               if (out instanceof Flushable) {
/* 376 */                 ((Flushable)out).flush();
/*     */               }
/* 378 */               Thread.sleep(nb);
/* 379 */             } catch (InterruptedException interruptedException) {}
/*     */             continue;
/*     */           } 
/* 382 */           if (exec) {
/* 383 */             out.append(ch);
/*     */           }
/*     */           continue;
/*     */       } 
/*     */       
/* 388 */       if (exec) {
/* 389 */         out.append(ch);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int toInteger(Object pop) {
/* 397 */     if (pop instanceof Number)
/* 398 */       return ((Number)pop).intValue(); 
/* 399 */     if (pop instanceof Boolean) {
/* 400 */       return ((Boolean)pop).booleanValue() ? 1 : 0;
/*     */     }
/* 402 */     return Integer.valueOf(pop.toString()).intValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\Curses.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */