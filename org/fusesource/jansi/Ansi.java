/*     */ package org.fusesource.jansi;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.concurrent.Callable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Ansi
/*     */ {
/*     */   private static final char FIRST_ESC_CHAR = '\033';
/*     */   private static final char SECOND_ESC_CHAR = '[';
/*     */   
/*     */   public enum Color
/*     */   {
/*  37 */     BLACK(0, "BLACK"),
/*  38 */     RED(1, "RED"),
/*  39 */     GREEN(2, "GREEN"),
/*  40 */     YELLOW(3, "YELLOW"),
/*  41 */     BLUE(4, "BLUE"),
/*  42 */     MAGENTA(5, "MAGENTA"),
/*  43 */     CYAN(6, "CYAN"),
/*  44 */     WHITE(7, "WHITE"),
/*  45 */     DEFAULT(9, "DEFAULT");
/*     */     
/*     */     private final int value;
/*     */     private final String name;
/*     */     
/*     */     Color(int index, String name) {
/*  51 */       this.value = index;
/*  52 */       this.name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  57 */       return this.name;
/*     */     }
/*     */     
/*     */     public int value() {
/*  61 */       return this.value;
/*     */     }
/*     */     
/*     */     public int fg() {
/*  65 */       return this.value + 30;
/*     */     }
/*     */     
/*     */     public int bg() {
/*  69 */       return this.value + 40;
/*     */     }
/*     */     
/*     */     public int fgBright() {
/*  73 */       return this.value + 90;
/*     */     }
/*     */     
/*     */     public int bgBright() {
/*  77 */       return this.value + 100;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Attribute
/*     */   {
/*  87 */     RESET(0, "RESET"),
/*  88 */     INTENSITY_BOLD(1, "INTENSITY_BOLD"),
/*  89 */     INTENSITY_FAINT(2, "INTENSITY_FAINT"),
/*  90 */     ITALIC(3, "ITALIC_ON"),
/*  91 */     UNDERLINE(4, "UNDERLINE_ON"),
/*  92 */     BLINK_SLOW(5, "BLINK_SLOW"),
/*  93 */     BLINK_FAST(6, "BLINK_FAST"),
/*  94 */     NEGATIVE_ON(7, "NEGATIVE_ON"),
/*  95 */     CONCEAL_ON(8, "CONCEAL_ON"),
/*  96 */     STRIKETHROUGH_ON(9, "STRIKETHROUGH_ON"),
/*  97 */     UNDERLINE_DOUBLE(21, "UNDERLINE_DOUBLE"),
/*  98 */     INTENSITY_BOLD_OFF(22, "INTENSITY_BOLD_OFF"),
/*  99 */     ITALIC_OFF(23, "ITALIC_OFF"),
/* 100 */     UNDERLINE_OFF(24, "UNDERLINE_OFF"),
/* 101 */     BLINK_OFF(25, "BLINK_OFF"),
/* 102 */     NEGATIVE_OFF(27, "NEGATIVE_OFF"),
/* 103 */     CONCEAL_OFF(28, "CONCEAL_OFF"),
/* 104 */     STRIKETHROUGH_OFF(29, "STRIKETHROUGH_OFF");
/*     */     
/*     */     private final int value;
/*     */     private final String name;
/*     */     
/*     */     Attribute(int index, String name) {
/* 110 */       this.value = index;
/* 111 */       this.name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 116 */       return this.name;
/*     */     }
/*     */     
/*     */     public int value() {
/* 120 */       return this.value;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Erase
/*     */   {
/* 132 */     FORWARD(0, "FORWARD"),
/* 133 */     BACKWARD(1, "BACKWARD"),
/* 134 */     ALL(2, "ALL");
/*     */     
/*     */     private final int value;
/*     */     private final String name;
/*     */     
/*     */     Erase(int index, String name) {
/* 140 */       this.value = index;
/* 141 */       this.name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 146 */       return this.name;
/*     */     }
/*     */     
/*     */     public int value() {
/* 150 */       return this.value;
/*     */     }
/*     */   }
/*     */   
/* 154 */   public static final String DISABLE = Ansi.class.getName() + ".disable";
/*     */   
/* 156 */   private static Callable<Boolean> detector = new Callable<Boolean>() {
/*     */       public Boolean call() throws Exception {
/* 158 */         return Boolean.valueOf(!Boolean.getBoolean(Ansi.DISABLE));
/*     */       }
/*     */     };
/*     */   
/*     */   public static void setDetector(Callable<Boolean> detector) {
/* 163 */     if (detector == null) throw new IllegalArgumentException(); 
/* 164 */     Ansi.detector = detector;
/*     */   }
/*     */   
/*     */   public static boolean isDetected() {
/*     */     try {
/* 169 */       return ((Boolean)detector.call()).booleanValue();
/* 170 */     } catch (Exception e) {
/* 171 */       return true;
/*     */     } 
/*     */   }
/*     */   
/* 175 */   private static final InheritableThreadLocal<Boolean> holder = new InheritableThreadLocal<Boolean>()
/*     */     {
/*     */       protected Boolean initialValue() {
/* 178 */         return Boolean.valueOf(Ansi.isDetected());
/*     */       }
/*     */     };
/*     */   
/*     */   public static void setEnabled(boolean flag) {
/* 183 */     holder.set(Boolean.valueOf(flag));
/*     */   }
/*     */   private final StringBuilder builder;
/*     */   public static boolean isEnabled() {
/* 187 */     return ((Boolean)holder.get()).booleanValue();
/*     */   }
/*     */   
/*     */   public static Ansi ansi() {
/* 191 */     if (isEnabled()) {
/* 192 */       return new Ansi();
/*     */     }
/* 194 */     return new NoAnsi();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Ansi ansi(StringBuilder builder) {
/* 199 */     if (isEnabled()) {
/* 200 */       return new Ansi(builder);
/*     */     }
/* 202 */     return new NoAnsi(builder);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Ansi ansi(int size) {
/* 207 */     if (isEnabled()) {
/* 208 */       return new Ansi(size);
/*     */     }
/* 210 */     return new NoAnsi(size);
/*     */   }
/*     */ 
/*     */   
/*     */   private static class NoAnsi
/*     */     extends Ansi
/*     */   {
/*     */     public NoAnsi() {}
/*     */ 
/*     */     
/*     */     public NoAnsi(int size) {
/* 221 */       super(size);
/*     */     }
/*     */     
/*     */     public NoAnsi(StringBuilder builder) {
/* 225 */       super(builder);
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi fg(Ansi.Color color) {
/* 230 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi bg(Ansi.Color color) {
/* 235 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi fgBright(Ansi.Color color) {
/* 240 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi bgBright(Ansi.Color color) {
/* 245 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi a(Ansi.Attribute attribute) {
/* 250 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi cursor(int row, int column) {
/* 255 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi cursorToColumn(int x) {
/* 260 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi cursorUp(int y) {
/* 265 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi cursorRight(int x) {
/* 270 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi cursorDown(int y) {
/* 275 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi cursorLeft(int x) {
/* 280 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi cursorDownLine() {
/* 285 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi cursorDownLine(int n) {
/* 290 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi cursorUpLine() {
/* 295 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi cursorUpLine(int n) {
/* 300 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi eraseScreen() {
/* 305 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi eraseScreen(Ansi.Erase kind) {
/* 310 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi eraseLine() {
/* 315 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi eraseLine(Ansi.Erase kind) {
/* 320 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi scrollUp(int rows) {
/* 325 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi scrollDown(int rows) {
/* 330 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi saveCursorPosition() {
/* 335 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public Ansi restorCursorPosition() {
/* 341 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi restoreCursorPosition() {
/* 346 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Ansi reset() {
/* 351 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 356 */   private final ArrayList<Integer> attributeOptions = new ArrayList<Integer>(5);
/*     */   
/*     */   public Ansi() {
/* 359 */     this(new StringBuilder());
/*     */   }
/*     */   
/*     */   public Ansi(Ansi parent) {
/* 363 */     this(new StringBuilder(parent.builder));
/* 364 */     this.attributeOptions.addAll(parent.attributeOptions);
/*     */   }
/*     */   
/*     */   public Ansi(int size) {
/* 368 */     this(new StringBuilder(size));
/*     */   }
/*     */   
/*     */   public Ansi(StringBuilder builder) {
/* 372 */     this.builder = builder;
/*     */   }
/*     */   
/*     */   public Ansi fg(Color color) {
/* 376 */     this.attributeOptions.add(Integer.valueOf(color.fg()));
/* 377 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi fgBlack() {
/* 381 */     return fg(Color.BLACK);
/*     */   }
/*     */   
/*     */   public Ansi fgBlue() {
/* 385 */     return fg(Color.BLUE);
/*     */   }
/*     */   
/*     */   public Ansi fgCyan() {
/* 389 */     return fg(Color.CYAN);
/*     */   }
/*     */   
/*     */   public Ansi fgDefault() {
/* 393 */     return fg(Color.DEFAULT);
/*     */   }
/*     */   
/*     */   public Ansi fgGreen() {
/* 397 */     return fg(Color.GREEN);
/*     */   }
/*     */   
/*     */   public Ansi fgMagenta() {
/* 401 */     return fg(Color.MAGENTA);
/*     */   }
/*     */   
/*     */   public Ansi fgRed() {
/* 405 */     return fg(Color.RED);
/*     */   }
/*     */   
/*     */   public Ansi fgYellow() {
/* 409 */     return fg(Color.YELLOW);
/*     */   }
/*     */   
/*     */   public Ansi bg(Color color) {
/* 413 */     this.attributeOptions.add(Integer.valueOf(color.bg()));
/* 414 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi bgCyan() {
/* 418 */     return fg(Color.CYAN);
/*     */   }
/*     */   
/*     */   public Ansi bgDefault() {
/* 422 */     return bg(Color.DEFAULT);
/*     */   }
/*     */   
/*     */   public Ansi bgGreen() {
/* 426 */     return bg(Color.GREEN);
/*     */   }
/*     */   
/*     */   public Ansi bgMagenta() {
/* 430 */     return bg(Color.MAGENTA);
/*     */   }
/*     */   
/*     */   public Ansi bgRed() {
/* 434 */     return bg(Color.RED);
/*     */   }
/*     */   
/*     */   public Ansi bgYellow() {
/* 438 */     return bg(Color.YELLOW);
/*     */   }
/*     */   
/*     */   public Ansi fgBright(Color color) {
/* 442 */     this.attributeOptions.add(Integer.valueOf(color.fgBright()));
/* 443 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi fgBrightBlack() {
/* 447 */     return fgBright(Color.BLACK);
/*     */   }
/*     */   
/*     */   public Ansi fgBrightBlue() {
/* 451 */     return fgBright(Color.BLUE);
/*     */   }
/*     */   
/*     */   public Ansi fgBrightCyan() {
/* 455 */     return fgBright(Color.CYAN);
/*     */   }
/*     */   
/*     */   public Ansi fgBrightDefault() {
/* 459 */     return fgBright(Color.DEFAULT);
/*     */   }
/*     */   
/*     */   public Ansi fgBrightGreen() {
/* 463 */     return fgBright(Color.GREEN);
/*     */   }
/*     */   
/*     */   public Ansi fgBrightMagenta() {
/* 467 */     return fgBright(Color.MAGENTA);
/*     */   }
/*     */   
/*     */   public Ansi fgBrightRed() {
/* 471 */     return fgBright(Color.RED);
/*     */   }
/*     */   
/*     */   public Ansi fgBrightYellow() {
/* 475 */     return fgBright(Color.YELLOW);
/*     */   }
/*     */   
/*     */   public Ansi bgBright(Color color) {
/* 479 */     this.attributeOptions.add(Integer.valueOf(color.bgBright()));
/* 480 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi bgBrightCyan() {
/* 484 */     return fgBright(Color.CYAN);
/*     */   }
/*     */   
/*     */   public Ansi bgBrightDefault() {
/* 488 */     return bgBright(Color.DEFAULT);
/*     */   }
/*     */   
/*     */   public Ansi bgBrightGreen() {
/* 492 */     return bgBright(Color.GREEN);
/*     */   }
/*     */   
/*     */   public Ansi bgBrightMagenta() {
/* 496 */     return bg(Color.MAGENTA);
/*     */   }
/*     */   
/*     */   public Ansi bgBrightRed() {
/* 500 */     return bgBright(Color.RED);
/*     */   }
/*     */   
/*     */   public Ansi bgBrightYellow() {
/* 504 */     return bgBright(Color.YELLOW);
/*     */   }
/*     */   
/*     */   public Ansi a(Attribute attribute) {
/* 508 */     this.attributeOptions.add(Integer.valueOf(attribute.value()));
/* 509 */     return this;
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
/*     */   public Ansi cursor(int row, int column) {
/* 522 */     return appendEscapeSequence('H', new Object[] { Integer.valueOf(row), Integer.valueOf(column) });
/*     */   }
/*     */   
/*     */   public Ansi cursorToColumn(int x) {
/* 526 */     return appendEscapeSequence('G', x);
/*     */   }
/*     */   
/*     */   public Ansi cursorUp(int y) {
/* 530 */     return appendEscapeSequence('A', y);
/*     */   }
/*     */   
/*     */   public Ansi cursorDown(int y) {
/* 534 */     return appendEscapeSequence('B', y);
/*     */   }
/*     */   
/*     */   public Ansi cursorRight(int x) {
/* 538 */     return appendEscapeSequence('C', x);
/*     */   }
/*     */   
/*     */   public Ansi cursorLeft(int x) {
/* 542 */     return appendEscapeSequence('D', x);
/*     */   }
/*     */   
/*     */   public Ansi cursorDownLine() {
/* 546 */     return appendEscapeSequence('E');
/*     */   }
/*     */   
/*     */   public Ansi cursorDownLine(int n) {
/* 550 */     return appendEscapeSequence('E', n);
/*     */   }
/*     */   
/*     */   public Ansi cursorUpLine() {
/* 554 */     return appendEscapeSequence('F');
/*     */   }
/*     */   
/*     */   public Ansi cursorUpLine(int n) {
/* 558 */     return appendEscapeSequence('F', n);
/*     */   }
/*     */   
/*     */   public Ansi eraseScreen() {
/* 562 */     return appendEscapeSequence('J', Erase.ALL.value());
/*     */   }
/*     */   
/*     */   public Ansi eraseScreen(Erase kind) {
/* 566 */     return appendEscapeSequence('J', kind.value());
/*     */   }
/*     */   
/*     */   public Ansi eraseLine() {
/* 570 */     return appendEscapeSequence('K');
/*     */   }
/*     */   
/*     */   public Ansi eraseLine(Erase kind) {
/* 574 */     return appendEscapeSequence('K', kind.value());
/*     */   }
/*     */   
/*     */   public Ansi scrollUp(int rows) {
/* 578 */     return appendEscapeSequence('S', rows);
/*     */   }
/*     */   
/*     */   public Ansi scrollDown(int rows) {
/* 582 */     return appendEscapeSequence('T', rows);
/*     */   }
/*     */   
/*     */   public Ansi saveCursorPosition() {
/* 586 */     return appendEscapeSequence('s');
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Ansi restorCursorPosition() {
/* 591 */     return appendEscapeSequence('u');
/*     */   }
/*     */   
/*     */   public Ansi restoreCursorPosition() {
/* 595 */     return appendEscapeSequence('u');
/*     */   }
/*     */   
/*     */   public Ansi reset() {
/* 599 */     return a(Attribute.RESET);
/*     */   }
/*     */   
/*     */   public Ansi bold() {
/* 603 */     return a(Attribute.INTENSITY_BOLD);
/*     */   }
/*     */   
/*     */   public Ansi boldOff() {
/* 607 */     return a(Attribute.INTENSITY_BOLD_OFF);
/*     */   }
/*     */   
/*     */   public Ansi a(String value) {
/* 611 */     flushAttributes();
/* 612 */     this.builder.append(value);
/* 613 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(boolean value) {
/* 617 */     flushAttributes();
/* 618 */     this.builder.append(value);
/* 619 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(char value) {
/* 623 */     flushAttributes();
/* 624 */     this.builder.append(value);
/* 625 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(char[] value, int offset, int len) {
/* 629 */     flushAttributes();
/* 630 */     this.builder.append(value, offset, len);
/* 631 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(char[] value) {
/* 635 */     flushAttributes();
/* 636 */     this.builder.append(value);
/* 637 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(CharSequence value, int start, int end) {
/* 641 */     flushAttributes();
/* 642 */     this.builder.append(value, start, end);
/* 643 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(CharSequence value) {
/* 647 */     flushAttributes();
/* 648 */     this.builder.append(value);
/* 649 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(double value) {
/* 653 */     flushAttributes();
/* 654 */     this.builder.append(value);
/* 655 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(float value) {
/* 659 */     flushAttributes();
/* 660 */     this.builder.append(value);
/* 661 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(int value) {
/* 665 */     flushAttributes();
/* 666 */     this.builder.append(value);
/* 667 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(long value) {
/* 671 */     flushAttributes();
/* 672 */     this.builder.append(value);
/* 673 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(Object value) {
/* 677 */     flushAttributes();
/* 678 */     this.builder.append(value);
/* 679 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi a(StringBuffer value) {
/* 683 */     flushAttributes();
/* 684 */     this.builder.append(value);
/* 685 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi newline() {
/* 689 */     flushAttributes();
/* 690 */     this.builder.append(System.getProperty("line.separator"));
/* 691 */     return this;
/*     */   }
/*     */   
/*     */   public Ansi format(String pattern, Object... args) {
/* 695 */     flushAttributes();
/* 696 */     this.builder.append(String.format(pattern, args));
/* 697 */     return this;
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
/*     */   public Ansi render(String text) {
/* 710 */     a(AnsiRenderer.render(text));
/* 711 */     return this;
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
/*     */   public Ansi render(String text, Object... args) {
/* 725 */     a(String.format(AnsiRenderer.render(text), args));
/* 726 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 731 */     flushAttributes();
/* 732 */     return this.builder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Ansi appendEscapeSequence(char command) {
/* 740 */     flushAttributes();
/* 741 */     this.builder.append('\033');
/* 742 */     this.builder.append('[');
/* 743 */     this.builder.append(command);
/* 744 */     return this;
/*     */   }
/*     */   
/*     */   private Ansi appendEscapeSequence(char command, int option) {
/* 748 */     flushAttributes();
/* 749 */     this.builder.append('\033');
/* 750 */     this.builder.append('[');
/* 751 */     this.builder.append(option);
/* 752 */     this.builder.append(command);
/* 753 */     return this;
/*     */   }
/*     */   
/*     */   private Ansi appendEscapeSequence(char command, Object... options) {
/* 757 */     flushAttributes();
/* 758 */     return _appendEscapeSequence(command, options);
/*     */   }
/*     */   
/*     */   private void flushAttributes() {
/* 762 */     if (this.attributeOptions.isEmpty())
/*     */       return; 
/* 764 */     if (this.attributeOptions.size() == 1 && ((Integer)this.attributeOptions.get(0)).intValue() == 0) {
/* 765 */       this.builder.append('\033');
/* 766 */       this.builder.append('[');
/* 767 */       this.builder.append('m');
/*     */     } else {
/* 769 */       _appendEscapeSequence('m', this.attributeOptions.toArray());
/*     */     } 
/* 771 */     this.attributeOptions.clear();
/*     */   }
/*     */   
/*     */   private Ansi _appendEscapeSequence(char command, Object... options) {
/* 775 */     this.builder.append('\033');
/* 776 */     this.builder.append('[');
/* 777 */     int size = options.length;
/* 778 */     for (int i = 0; i < size; i++) {
/* 779 */       if (i != 0) {
/* 780 */         this.builder.append(';');
/*     */       }
/* 782 */       if (options[i] != null) {
/* 783 */         this.builder.append(options[i]);
/*     */       }
/*     */     } 
/* 786 */     this.builder.append(command);
/* 787 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\Ansi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */