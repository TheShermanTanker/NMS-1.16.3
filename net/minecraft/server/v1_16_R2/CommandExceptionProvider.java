/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public class CommandExceptionProvider implements BuiltInExceptionProvider {
/*     */   private static final Dynamic2CommandExceptionType a;
/*     */   private static final Dynamic2CommandExceptionType b;
/*     */   private static final Dynamic2CommandExceptionType c;
/*     */   private static final Dynamic2CommandExceptionType d;
/*     */   
/*     */   static {
/*  10 */     a = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("argument.double.low", new Object[] { var1, var0 }));
/*  11 */     b = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("argument.double.big", new Object[] { var1, var0 }));
/*     */     
/*  13 */     c = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("argument.float.low", new Object[] { var1, var0 }));
/*  14 */     d = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("argument.float.big", new Object[] { var1, var0 }));
/*     */     
/*  16 */     e = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("argument.integer.low", new Object[] { var1, var0 }));
/*  17 */     f = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("argument.integer.big", new Object[] { var1, var0 }));
/*     */     
/*  19 */     g = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("argument.long.low", new Object[] { var1, var0 }));
/*  20 */     h = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("argument.long.big", new Object[] { var1, var0 }));
/*     */     
/*  22 */     i = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.literal.incorrect", new Object[] { var0 }));
/*     */   }
/*  24 */   private static final Dynamic2CommandExceptionType e; private static final Dynamic2CommandExceptionType f; private static final Dynamic2CommandExceptionType g; private static final Dynamic2CommandExceptionType h; private static final DynamicCommandExceptionType i; private static final SimpleCommandExceptionType j = new SimpleCommandExceptionType(new ChatMessage("parsing.quote.expected.start"));
/*  25 */   private static final SimpleCommandExceptionType k = new SimpleCommandExceptionType(new ChatMessage("parsing.quote.expected.end")); private static final DynamicCommandExceptionType l; private static final DynamicCommandExceptionType m; private static final DynamicCommandExceptionType n; static {
/*  26 */     l = new DynamicCommandExceptionType(var0 -> new ChatMessage("parsing.quote.escape", new Object[] { var0 }));
/*  27 */     m = new DynamicCommandExceptionType(var0 -> new ChatMessage("parsing.bool.invalid", new Object[] { var0 }));
/*  28 */     n = new DynamicCommandExceptionType(var0 -> new ChatMessage("parsing.int.invalid", new Object[] { var0 }));
/*  29 */   } private static final SimpleCommandExceptionType o = new SimpleCommandExceptionType(new ChatMessage("parsing.int.expected")); private static final DynamicCommandExceptionType p; static {
/*  30 */     p = new DynamicCommandExceptionType(var0 -> new ChatMessage("parsing.long.invalid", new Object[] { var0 }));
/*  31 */   } private static final SimpleCommandExceptionType q = new SimpleCommandExceptionType(new ChatMessage("parsing.long.expected")); private static final DynamicCommandExceptionType r; static {
/*  32 */     r = new DynamicCommandExceptionType(var0 -> new ChatMessage("parsing.double.invalid", new Object[] { var0 }));
/*  33 */   } private static final SimpleCommandExceptionType s = new SimpleCommandExceptionType(new ChatMessage("parsing.double.expected")); private static final DynamicCommandExceptionType t; static {
/*  34 */     t = new DynamicCommandExceptionType(var0 -> new ChatMessage("parsing.float.invalid", new Object[] { var0 }));
/*  35 */   } private static final SimpleCommandExceptionType u = new SimpleCommandExceptionType(new ChatMessage("parsing.float.expected"));
/*  36 */   private static final SimpleCommandExceptionType v = new SimpleCommandExceptionType(new ChatMessage("parsing.bool.expected")); private static final DynamicCommandExceptionType w; static {
/*  37 */     w = new DynamicCommandExceptionType(var0 -> new ChatMessage("parsing.expected", new Object[] { var0 }));
/*     */   }
/*  39 */   private static final SimpleCommandExceptionType x = new SimpleCommandExceptionType(new ChatMessage("command.unknown.command"));
/*  40 */   private static final SimpleCommandExceptionType y = new SimpleCommandExceptionType(new ChatMessage("command.unknown.argument"));
/*  41 */   private static final SimpleCommandExceptionType z = new SimpleCommandExceptionType(new ChatMessage("command.expected.separator")); private static final DynamicCommandExceptionType A; static {
/*  42 */     A = new DynamicCommandExceptionType(var0 -> new ChatMessage("command.exception", new Object[] { var0 }));
/*     */   }
/*     */   
/*     */   public Dynamic2CommandExceptionType doubleTooLow() {
/*  46 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dynamic2CommandExceptionType doubleTooHigh() {
/*  51 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dynamic2CommandExceptionType floatTooLow() {
/*  56 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dynamic2CommandExceptionType floatTooHigh() {
/*  61 */     return d;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dynamic2CommandExceptionType integerTooLow() {
/*  66 */     return e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dynamic2CommandExceptionType integerTooHigh() {
/*  71 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dynamic2CommandExceptionType longTooLow() {
/*  76 */     return g;
/*     */   }
/*     */ 
/*     */   
/*     */   public Dynamic2CommandExceptionType longTooHigh() {
/*  81 */     return h;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicCommandExceptionType literalIncorrect() {
/*  86 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommandExceptionType readerExpectedStartOfQuote() {
/*  91 */     return j;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommandExceptionType readerExpectedEndOfQuote() {
/*  96 */     return k;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicCommandExceptionType readerInvalidEscape() {
/* 101 */     return l;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicCommandExceptionType readerInvalidBool() {
/* 106 */     return m;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicCommandExceptionType readerInvalidInt() {
/* 111 */     return n;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommandExceptionType readerExpectedInt() {
/* 116 */     return o;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicCommandExceptionType readerInvalidLong() {
/* 121 */     return p;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommandExceptionType readerExpectedLong() {
/* 126 */     return q;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicCommandExceptionType readerInvalidDouble() {
/* 131 */     return r;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommandExceptionType readerExpectedDouble() {
/* 136 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicCommandExceptionType readerInvalidFloat() {
/* 141 */     return t;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommandExceptionType readerExpectedFloat() {
/* 146 */     return u;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommandExceptionType readerExpectedBool() {
/* 151 */     return v;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicCommandExceptionType readerExpectedSymbol() {
/* 156 */     return w;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommandExceptionType dispatcherUnknownCommand() {
/* 161 */     return x;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommandExceptionType dispatcherUnknownArgument() {
/* 166 */     return y;
/*     */   }
/*     */ 
/*     */   
/*     */   public SimpleCommandExceptionType dispatcherExpectedArgumentSeparator() {
/* 171 */     return z;
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicCommandExceptionType dispatcherParseException() {
/* 176 */     return A;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandExceptionProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */