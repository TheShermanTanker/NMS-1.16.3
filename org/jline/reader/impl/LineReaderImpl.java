/*      */ package org.jline.reader.impl;
/*      */ 
/*      */ import java.io.Flushable;
/*      */ import java.io.IOError;
/*      */ import java.io.IOException;
/*      */ import java.time.Instant;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Set;
/*      */ import java.util.Spliterators;
/*      */ import java.util.TreeMap;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import java.util.function.BiFunction;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.IntBinaryOperator;
/*      */ import java.util.function.Predicate;
/*      */ import java.util.function.Supplier;
/*      */ import java.util.function.ToIntFunction;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import java.util.stream.Collectors;
/*      */ import java.util.stream.Stream;
/*      */ import java.util.stream.StreamSupport;
/*      */ import org.jline.keymap.BindingReader;
/*      */ import org.jline.keymap.KeyMap;
/*      */ import org.jline.reader.Binding;
/*      */ import org.jline.reader.Buffer;
/*      */ import org.jline.reader.Candidate;
/*      */ import org.jline.reader.Completer;
/*      */ import org.jline.reader.CompletingParsedLine;
/*      */ import org.jline.reader.EOFError;
/*      */ import org.jline.reader.EndOfFileException;
/*      */ import org.jline.reader.Expander;
/*      */ import org.jline.reader.Highlighter;
/*      */ import org.jline.reader.History;
/*      */ import org.jline.reader.LineReader;
/*      */ import org.jline.reader.Macro;
/*      */ import org.jline.reader.MaskingCallback;
/*      */ import org.jline.reader.ParsedLine;
/*      */ import org.jline.reader.Parser;
/*      */ import org.jline.reader.Reference;
/*      */ import org.jline.reader.SyntaxError;
/*      */ import org.jline.reader.UserInterruptException;
/*      */ import org.jline.reader.Widget;
/*      */ import org.jline.reader.impl.history.DefaultHistory;
/*      */ import org.jline.terminal.Attributes;
/*      */ import org.jline.terminal.Cursor;
/*      */ import org.jline.terminal.MouseEvent;
/*      */ import org.jline.terminal.Size;
/*      */ import org.jline.terminal.Terminal;
/*      */ import org.jline.utils.AttributedString;
/*      */ import org.jline.utils.AttributedStringBuilder;
/*      */ import org.jline.utils.AttributedStyle;
/*      */ import org.jline.utils.Curses;
/*      */ import org.jline.utils.Display;
/*      */ import org.jline.utils.InfoCmp;
/*      */ import org.jline.utils.Levenshtein;
/*      */ import org.jline.utils.Log;
/*      */ import org.jline.utils.Status;
/*      */ import org.jline.utils.WCWidth;
/*      */ 
/*      */ public class LineReaderImpl
/*      */   implements LineReader, Flushable
/*      */ {
/*      */   public static final char NULL_MASK = '\000';
/*      */   public static final int TAB_WIDTH = 4;
/*      */   public static final String DEFAULT_WORDCHARS = "*?_-.[]~=/&;!#$%^(){}<>";
/*      */   public static final String DEFAULT_REMOVE_SUFFIX_CHARS = " \t\n;&|";
/*      */   public static final String DEFAULT_COMMENT_BEGIN = "#";
/*      */   public static final String DEFAULT_SEARCH_TERMINATORS = "\033\n";
/*      */   public static final String DEFAULT_BELL_STYLE = "";
/*      */   public static final int DEFAULT_LIST_MAX = 100;
/*      */   public static final int DEFAULT_ERRORS = 2;
/*      */   public static final long DEFAULT_BLINK_MATCHING_PAREN = 500L;
/*      */   public static final long DEFAULT_AMBIGUOUS_BINDING = 1000L;
/*      */   public static final String DEFAULT_SECONDARY_PROMPT_PATTERN = "%M> ";
/*      */   public static final String DEFAULT_OTHERS_GROUP_NAME = "others";
/*      */   public static final String DEFAULT_ORIGINAL_GROUP_NAME = "original";
/*      */   public static final String DEFAULT_COMPLETION_STYLE_STARTING = "36";
/*      */   public static final String DEFAULT_COMPLETION_STYLE_DESCRIPTION = "90";
/*      */   public static final String DEFAULT_COMPLETION_STYLE_GROUP = "35;1";
/*      */   public static final String DEFAULT_COMPLETION_STYLE_SELECTION = "7";
/*      */   private static final int MIN_ROWS = 3;
/*      */   public static final String BRACKETED_PASTE_ON = "\033[?2004h";
/*      */   public static final String BRACKETED_PASTE_OFF = "\033[?2004l";
/*      */   public static final String BRACKETED_PASTE_BEGIN = "\033[200~";
/*      */   public static final String BRACKETED_PASTE_END = "\033[201~";
/*      */   public static final String FOCUS_IN_SEQ = "\033[I";
/*      */   public static final String FOCUS_OUT_SEQ = "\033[O";
/*      */   protected final Terminal terminal;
/*      */   protected final String appName;
/*      */   protected final Map<String, KeyMap<Binding>> keyMaps;
/*      */   protected final Map<String, Object> variables;
/*      */   
/*      */   protected enum State {
/*  107 */     NORMAL,
/*      */ 
/*      */ 
/*      */     
/*  111 */     DONE,
/*      */ 
/*      */ 
/*      */     
/*  115 */     EOF,
/*      */ 
/*      */ 
/*      */     
/*  119 */     INTERRUPT;
/*      */   }
/*      */   
/*      */   protected enum ViMoveMode {
/*  123 */     NORMAL,
/*  124 */     YANK,
/*  125 */     DELETE,
/*  126 */     CHANGE;
/*      */   }
/*      */   
/*      */   protected enum BellType {
/*  130 */     NONE,
/*  131 */     AUDIBLE,
/*  132 */     VISIBLE;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  150 */   protected History history = (History)new DefaultHistory();
/*  151 */   protected Completer completer = null;
/*  152 */   protected Highlighter highlighter = new DefaultHighlighter();
/*  153 */   protected Parser parser = new DefaultParser();
/*  154 */   protected Expander expander = new DefaultExpander();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  160 */   protected final Map<LineReader.Option, Boolean> options = new HashMap<>();
/*      */   
/*  162 */   protected final Buffer buf = new BufferImpl();
/*      */   
/*  164 */   protected final Size size = new Size();
/*      */   
/*  166 */   protected AttributedString prompt = AttributedString.EMPTY;
/*  167 */   protected AttributedString rightPrompt = AttributedString.EMPTY;
/*      */   
/*      */   protected MaskingCallback maskingCallback;
/*      */   
/*  171 */   protected Map<Integer, String> modifiedHistory = new HashMap<>();
/*  172 */   protected Buffer historyBuffer = null;
/*      */   protected CharSequence searchBuffer;
/*  174 */   protected StringBuffer searchTerm = null;
/*      */   protected boolean searchFailing;
/*      */   protected boolean searchBackward;
/*  177 */   protected int searchIndex = -1;
/*      */ 
/*      */   
/*      */   protected final BindingReader bindingReader;
/*      */ 
/*      */   
/*      */   protected int findChar;
/*      */ 
/*      */   
/*      */   protected int findDir;
/*      */ 
/*      */   
/*      */   protected int findTailAdd;
/*      */ 
/*      */   
/*      */   private int searchDir;
/*      */ 
/*      */   
/*      */   private String searchString;
/*      */ 
/*      */   
/*      */   protected int regionMark;
/*      */ 
/*      */   
/*      */   protected LineReader.RegionType regionActive;
/*      */ 
/*      */   
/*      */   private boolean forceChar;
/*      */   
/*      */   private boolean forceLine;
/*      */   
/*  208 */   protected String yankBuffer = "";
/*      */   
/*  210 */   protected ViMoveMode viMoveMode = ViMoveMode.NORMAL;
/*      */   
/*  212 */   protected KillRing killRing = new KillRing();
/*      */   
/*  214 */   protected UndoTree<Buffer> undo = new UndoTree<>(this::setBuffer);
/*      */ 
/*      */   
/*      */   protected boolean isUndo;
/*      */ 
/*      */   
/*  220 */   protected final ReentrantLock lock = new ReentrantLock();
/*      */ 
/*      */ 
/*      */   
/*  224 */   protected State state = State.DONE;
/*  225 */   protected final AtomicBoolean startedReading = new AtomicBoolean();
/*      */   
/*      */   protected boolean reading;
/*      */   
/*      */   protected Supplier<AttributedString> post;
/*      */   
/*      */   protected Map<String, Widget> builtinWidgets;
/*      */   protected Map<String, Widget> widgets;
/*      */   protected int count;
/*      */   protected int mult;
/*  235 */   protected int universal = 4;
/*      */   
/*      */   protected int repeatCount;
/*      */   
/*      */   protected boolean isArgDigit;
/*      */   
/*      */   protected ParsedLine parsedLine;
/*      */   
/*      */   protected boolean skipRedisplay;
/*      */   
/*      */   protected Display display;
/*      */   protected boolean overTyping = false;
/*      */   protected String keyMap;
/*  248 */   protected int smallTerminalOffset = 0;
/*      */ 
/*      */   
/*      */   protected boolean nextCommandFromHistory = false;
/*      */ 
/*      */   
/*  254 */   protected int nextHistoryId = -1; private static final String DESC_PREFIX = "(";
/*      */   private static final String DESC_SUFFIX = ")";
/*      */   
/*      */   public LineReaderImpl(Terminal terminal) throws IOException {
/*  258 */     this(terminal, null, null);
/*      */   }
/*      */   private static final int MARGIN_BETWEEN_DISPLAY_AND_DESC = 1; private static final int MARGIN_BETWEEN_COLUMNS = 3;
/*      */   public LineReaderImpl(Terminal terminal, String appName) throws IOException {
/*  262 */     this(terminal, appName, null);
/*      */   }
/*      */   
/*      */   public LineReaderImpl(Terminal terminal, String appName, Map<String, Object> variables) {
/*  266 */     Objects.requireNonNull(terminal, "terminal can not be null");
/*  267 */     this.terminal = terminal;
/*  268 */     if (appName == null) {
/*  269 */       appName = "JLine";
/*      */     }
/*  271 */     this.appName = appName;
/*  272 */     if (variables != null) {
/*  273 */       this.variables = variables;
/*      */     } else {
/*  275 */       this.variables = new HashMap<>();
/*      */     } 
/*  277 */     this.keyMaps = defaultKeyMaps();
/*      */     
/*  279 */     this.builtinWidgets = builtinWidgets();
/*  280 */     this.widgets = new HashMap<>(this.builtinWidgets);
/*  281 */     this.bindingReader = new BindingReader(terminal.reader());
/*  282 */     doDisplay();
/*      */   }
/*      */   
/*      */   public Terminal getTerminal() {
/*  286 */     return this.terminal;
/*      */   }
/*      */   
/*      */   public String getAppName() {
/*  290 */     return this.appName;
/*      */   }
/*      */   
/*      */   public Map<String, KeyMap<Binding>> getKeyMaps() {
/*  294 */     return this.keyMaps;
/*      */   }
/*      */   
/*      */   public KeyMap<Binding> getKeys() {
/*  298 */     return this.keyMaps.get(this.keyMap);
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<String, Widget> getWidgets() {
/*  303 */     return this.widgets;
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<String, Widget> getBuiltinWidgets() {
/*  308 */     return Collections.unmodifiableMap(this.builtinWidgets);
/*      */   }
/*      */ 
/*      */   
/*      */   public Buffer getBuffer() {
/*  313 */     return this.buf;
/*      */   }
/*      */ 
/*      */   
/*      */   public void runMacro(String macro) {
/*  318 */     this.bindingReader.runMacro(macro);
/*      */   }
/*      */ 
/*      */   
/*      */   public MouseEvent readMouseEvent() {
/*  323 */     return this.terminal.readMouseEvent(this.bindingReader::readCharacter);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCompleter(Completer completer) {
/*  332 */     this.completer = completer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Completer getCompleter() {
/*  341 */     return this.completer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHistory(History history) {
/*  349 */     Objects.requireNonNull(history);
/*  350 */     this.history = history;
/*      */   }
/*      */   
/*      */   public History getHistory() {
/*  354 */     return this.history;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHighlighter(Highlighter highlighter) {
/*  362 */     this.highlighter = highlighter;
/*      */   }
/*      */   
/*      */   public Highlighter getHighlighter() {
/*  366 */     return this.highlighter;
/*      */   }
/*      */   
/*      */   public Parser getParser() {
/*  370 */     return this.parser;
/*      */   }
/*      */   
/*      */   public void setParser(Parser parser) {
/*  374 */     this.parser = parser;
/*      */   }
/*      */ 
/*      */   
/*      */   public Expander getExpander() {
/*  379 */     return this.expander;
/*      */   }
/*      */   
/*      */   public void setExpander(Expander expander) {
/*  383 */     this.expander = expander;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readLine() throws UserInterruptException, EndOfFileException {
/*  396 */     return readLine((String)null, (String)null, (MaskingCallback)null, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readLine(Character mask) throws UserInterruptException, EndOfFileException {
/*  407 */     return readLine((String)null, (String)null, mask, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readLine(String prompt) throws UserInterruptException, EndOfFileException {
/*  418 */     return readLine(prompt, (String)null, (MaskingCallback)null, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readLine(String prompt, Character mask) throws UserInterruptException, EndOfFileException {
/*  430 */     return readLine(prompt, (String)null, mask, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readLine(String prompt, Character mask, String buffer) throws UserInterruptException, EndOfFileException {
/*  443 */     return readLine(prompt, (String)null, mask, buffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readLine(String prompt, String rightPrompt, Character mask, String buffer) throws UserInterruptException, EndOfFileException {
/*  457 */     return readLine(prompt, rightPrompt, (mask != null) ? new SimpleMaskingCallback(mask) : null, buffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readLine(String prompt, String rightPrompt, MaskingCallback maskingCallback, String buffer) throws UserInterruptException, EndOfFileException {
/*  475 */     if (!this.startedReading.compareAndSet(false, true)) {
/*  476 */       throw new IllegalStateException();
/*      */     }
/*      */     
/*  479 */     Thread readLineThread = Thread.currentThread();
/*  480 */     Terminal.SignalHandler previousIntrHandler = null;
/*  481 */     Terminal.SignalHandler previousWinchHandler = null;
/*  482 */     Terminal.SignalHandler previousContHandler = null;
/*  483 */     Attributes originalAttributes = null;
/*  484 */     boolean dumb = isTerminalDumb();
/*      */     
/*      */     try {
/*  487 */       this.maskingCallback = maskingCallback;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  494 */       this.repeatCount = 0;
/*  495 */       this.mult = 1;
/*  496 */       this.regionActive = LineReader.RegionType.NONE;
/*  497 */       this.regionMark = -1;
/*      */       
/*  499 */       this.smallTerminalOffset = 0;
/*      */       
/*  501 */       this.state = State.NORMAL;
/*      */       
/*  503 */       this.modifiedHistory.clear();
/*      */       
/*  505 */       setPrompt(prompt);
/*  506 */       setRightPrompt(rightPrompt);
/*  507 */       this.buf.clear();
/*  508 */       if (buffer != null) {
/*  509 */         this.buf.write(buffer);
/*      */       }
/*  511 */       if (this.nextCommandFromHistory && this.nextHistoryId > 0) {
/*  512 */         if (this.history.size() > this.nextHistoryId) {
/*  513 */           this.history.moveTo(this.nextHistoryId);
/*      */         } else {
/*  515 */           this.history.moveTo(this.history.last());
/*      */         } 
/*  517 */         this.buf.write(this.history.current());
/*      */       } else {
/*  519 */         this.nextHistoryId = -1;
/*      */       } 
/*  521 */       this.nextCommandFromHistory = false;
/*  522 */       this.undo.clear();
/*  523 */       this.parsedLine = null;
/*  524 */       this.keyMap = "main";
/*      */       
/*  526 */       if (this.history != null) {
/*  527 */         this.history.attach(this);
/*      */       }
/*      */       
/*      */       try {
/*  531 */         this.lock.lock();
/*      */         
/*  533 */         this.reading = true;
/*      */         
/*  535 */         previousIntrHandler = this.terminal.handle(Terminal.Signal.INT, signal -> readLineThread.interrupt());
/*  536 */         previousWinchHandler = this.terminal.handle(Terminal.Signal.WINCH, this::handleSignal);
/*  537 */         previousContHandler = this.terminal.handle(Terminal.Signal.CONT, this::handleSignal);
/*  538 */         originalAttributes = this.terminal.enterRawMode();
/*      */         
/*  540 */         doDisplay();
/*      */ 
/*      */         
/*  543 */         if (!dumb) {
/*  544 */           this.terminal.puts(InfoCmp.Capability.keypad_xmit, new Object[0]);
/*  545 */           if (isSet(LineReader.Option.AUTO_FRESH_LINE))
/*  546 */             callWidget("fresh-line"); 
/*  547 */           if (isSet(LineReader.Option.MOUSE))
/*  548 */             this.terminal.trackMouse(Terminal.MouseTracking.Normal); 
/*  549 */           if (isSet(LineReader.Option.BRACKETED_PASTE)) {
/*  550 */             this.terminal.writer().write("\033[?2004h");
/*      */           }
/*      */         } else {
/*  553 */           Attributes attr = new Attributes(originalAttributes);
/*  554 */           attr.setInputFlag(Attributes.InputFlag.IGNCR, true);
/*  555 */           this.terminal.setAttributes(attr);
/*      */         } 
/*      */         
/*  558 */         callWidget("callback-init");
/*      */         
/*  560 */         this.undo.newState(this.buf.copy());
/*      */ 
/*      */         
/*  563 */         redrawLine();
/*  564 */         redisplay();
/*      */       } finally {
/*  566 */         this.lock.unlock();
/*      */       } 
/*      */ 
/*      */       
/*      */       while (true) {
/*  571 */         KeyMap<Binding> local = null;
/*  572 */         if (isInViCmdMode() && this.regionActive != LineReader.RegionType.NONE) {
/*  573 */           local = this.keyMaps.get("visual");
/*      */         }
/*  575 */         Binding o = readBinding(getKeys(), local);
/*  576 */         if (o == null) {
/*  577 */           throw new EndOfFileException();
/*      */         }
/*  579 */         Log.trace(new Object[] { "Binding: ", o });
/*  580 */         if (this.buf.length() == 0 && getLastBinding().charAt(0) == originalAttributes.getControlChar(Attributes.ControlChar.VEOF)) {
/*  581 */           throw new EndOfFileException();
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  586 */         this.isArgDigit = false;
/*      */ 
/*      */ 
/*      */         
/*  590 */         this.count = ((this.repeatCount == 0) ? 1 : this.repeatCount) * this.mult;
/*      */         
/*  592 */         this.isUndo = false;
/*      */         
/*  594 */         if (this.regionActive == LineReader.RegionType.PASTE) {
/*  595 */           this.regionActive = LineReader.RegionType.NONE;
/*      */         }
/*      */         
/*      */         try {
/*  599 */           this.lock.lock();
/*      */           
/*  601 */           Buffer copy = this.buf.copy();
/*  602 */           Widget w = getWidget(o);
/*  603 */           if (!w.apply()) {
/*  604 */             beep();
/*      */           }
/*  606 */           if (!this.isUndo && !copy.toString().equals(this.buf.toString())) {
/*  607 */             this.undo.newState(this.buf.copy());
/*      */           }
/*      */           
/*  610 */           switch (this.state) {
/*      */             case DONE:
/*  612 */               return finishBuffer();
/*      */             case EOF:
/*  614 */               throw new EndOfFileException();
/*      */             case INTERRUPT:
/*  616 */               throw new UserInterruptException(this.buf.toString());
/*      */           } 
/*      */           
/*  619 */           if (!this.isArgDigit) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  624 */             this.repeatCount = 0;
/*  625 */             this.mult = 1;
/*      */           } 
/*      */           
/*  628 */           if (!dumb) {
/*  629 */             redisplay();
/*      */           }
/*      */         } finally {
/*  632 */           this.lock.unlock();
/*      */         } 
/*      */       } 
/*  635 */     } catch (IOError e) {
/*  636 */       if (e.getCause() instanceof java.io.InterruptedIOException) {
/*  637 */         throw new UserInterruptException(this.buf.toString());
/*      */       }
/*  639 */       throw e;
/*      */     } finally {
/*      */ 
/*      */       
/*      */       try {
/*  644 */         this.lock.lock();
/*      */         
/*  646 */         this.reading = false;
/*      */         
/*  648 */         cleanup();
/*  649 */         if (originalAttributes != null) {
/*  650 */           this.terminal.setAttributes(originalAttributes);
/*      */         }
/*  652 */         if (previousIntrHandler != null) {
/*  653 */           this.terminal.handle(Terminal.Signal.INT, previousIntrHandler);
/*      */         }
/*  655 */         if (previousWinchHandler != null) {
/*  656 */           this.terminal.handle(Terminal.Signal.WINCH, previousWinchHandler);
/*      */         }
/*  658 */         if (previousContHandler != null) {
/*  659 */           this.terminal.handle(Terminal.Signal.CONT, previousContHandler);
/*      */         }
/*      */       } finally {
/*  662 */         this.lock.unlock();
/*      */       } 
/*  664 */       this.startedReading.set(false);
/*      */     } 
/*      */   }
/*      */   
/*      */   private boolean isTerminalDumb() {
/*  669 */     return ("dumb".equals(this.terminal.getType()) || "dumb-color"
/*  670 */       .equals(this.terminal.getType()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void doDisplay() {
/*  676 */     this.size.copy(this.terminal.getBufferSize());
/*      */     
/*  678 */     this.display = new Display(this.terminal, false);
/*  679 */     if (this.size.getRows() == 0 || this.size.getColumns() == 0) {
/*  680 */       this.display.resize(1, 2147483647);
/*      */     } else {
/*  682 */       this.display.resize(this.size.getRows(), this.size.getColumns());
/*      */     } 
/*  684 */     if (isSet(LineReader.Option.DELAY_LINE_WRAP)) {
/*  685 */       this.display.setDelayLineWrap(true);
/*      */     }
/*      */   }
/*      */   
/*      */   public void printAbove(String str) {
/*      */     try {
/*  691 */       this.lock.lock();
/*      */       
/*  693 */       boolean reading = this.reading;
/*  694 */       if (reading) {
/*  695 */         this.display.update(Collections.emptyList(), 0);
/*      */       }
/*  697 */       if (str.endsWith("\n") || str.endsWith("\n\033[m") || str.endsWith("\n\033[0m")) {
/*  698 */         this.terminal.writer().print(str);
/*      */       } else {
/*  700 */         this.terminal.writer().println(str);
/*      */       } 
/*  702 */       if (reading) {
/*  703 */         redisplay(false);
/*      */       }
/*  705 */       this.terminal.flush();
/*      */     } finally {
/*  707 */       this.lock.unlock();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void printAbove(AttributedString str) {
/*  713 */     printAbove(str.toAnsi(this.terminal));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isReading() {
/*      */     try {
/*  719 */       this.lock.lock();
/*  720 */       return this.reading;
/*      */     } finally {
/*  722 */       this.lock.unlock();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean freshLine() {
/*  728 */     boolean wrapAtEol = this.terminal.getBooleanCapability(InfoCmp.Capability.auto_right_margin);
/*  729 */     boolean delayedWrapAtEol = (wrapAtEol && this.terminal.getBooleanCapability(InfoCmp.Capability.eat_newline_glitch));
/*  730 */     AttributedStringBuilder sb = new AttributedStringBuilder();
/*  731 */     sb.style(AttributedStyle.DEFAULT.foreground(8));
/*  732 */     sb.append("~");
/*  733 */     sb.style(AttributedStyle.DEFAULT);
/*  734 */     if (!wrapAtEol || delayedWrapAtEol) {
/*  735 */       for (int i = 0; i < this.size.getColumns() - 1; i++) {
/*  736 */         sb.append(" ");
/*      */       }
/*  738 */       sb.append(KeyMap.key(this.terminal, InfoCmp.Capability.carriage_return));
/*  739 */       sb.append(" ");
/*  740 */       sb.append(KeyMap.key(this.terminal, InfoCmp.Capability.carriage_return));
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  747 */       String el = this.terminal.getStringCapability(InfoCmp.Capability.clr_eol);
/*  748 */       if (el != null) {
/*  749 */         Curses.tputs((Appendable)sb, el, new Object[0]);
/*      */       }
/*  751 */       for (int i = 0; i < this.size.getColumns() - 2; i++) {
/*  752 */         sb.append(" ");
/*      */       }
/*  754 */       sb.append(KeyMap.key(this.terminal, InfoCmp.Capability.carriage_return));
/*  755 */       sb.append(" ");
/*  756 */       sb.append(KeyMap.key(this.terminal, InfoCmp.Capability.carriage_return));
/*      */     } 
/*  758 */     sb.print(this.terminal);
/*  759 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void callWidget(String name) {
/*      */     try {
/*  765 */       this.lock.lock();
/*  766 */       if (!this.reading) {
/*  767 */         throw new IllegalStateException("Widgets can only be called during a `readLine` call");
/*      */       }
/*      */       try {
/*      */         Widget w;
/*  771 */         if (name.startsWith(".")) {
/*  772 */           w = this.builtinWidgets.get(name.substring(1));
/*      */         } else {
/*  774 */           w = this.widgets.get(name);
/*      */         } 
/*  776 */         if (w != null) {
/*  777 */           w.apply();
/*      */         }
/*  779 */       } catch (Throwable t) {
/*  780 */         Log.debug(new Object[] { "Error executing widget '", name, "'", t });
/*      */       } 
/*      */     } finally {
/*  783 */       this.lock.unlock();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean redrawLine() {
/*  792 */     this.display.reset();
/*  793 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void putString(CharSequence str) {
/*  801 */     this.buf.write(str, this.overTyping);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void flush() {
/*  810 */     this.terminal.flush();
/*      */   }
/*      */   
/*      */   public boolean isKeyMap(String name) {
/*  814 */     return this.keyMap.equals(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readCharacter() {
/*  823 */     if (this.lock.isHeldByCurrentThread()) {
/*      */       try {
/*  825 */         this.lock.unlock();
/*  826 */         return this.bindingReader.readCharacter();
/*      */       } finally {
/*  828 */         this.lock.lock();
/*      */       } 
/*      */     }
/*  831 */     return this.bindingReader.readCharacter();
/*      */   }
/*      */ 
/*      */   
/*      */   public int peekCharacter(long timeout) {
/*  836 */     return this.bindingReader.peekCharacter(timeout);
/*      */   }
/*      */   
/*      */   protected <T> T doReadBinding(KeyMap<T> keys, KeyMap<T> local) {
/*  840 */     if (this.lock.isHeldByCurrentThread()) {
/*      */       try {
/*  842 */         this.lock.unlock();
/*  843 */         return (T)this.bindingReader.readBinding(keys, local);
/*      */       } finally {
/*  845 */         this.lock.lock();
/*      */       } 
/*      */     }
/*  848 */     return (T)this.bindingReader.readBinding(keys, local);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Binding readBinding(KeyMap<Binding> keys) {
/*  864 */     return readBinding(keys, null);
/*      */   }
/*      */   
/*      */   public Binding readBinding(KeyMap<Binding> keys, KeyMap<Binding> local) {
/*  868 */     Binding o = doReadBinding(keys, local);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  874 */     if (o instanceof Reference) {
/*  875 */       String ref = ((Reference)o).name();
/*  876 */       if (!"yank-pop".equals(ref) && !"yank".equals(ref)) {
/*  877 */         this.killRing.resetLastYank();
/*      */       }
/*  879 */       if (!"kill-line".equals(ref) && !"kill-whole-line".equals(ref) && 
/*  880 */         !"backward-kill-word".equals(ref) && !"kill-word".equals(ref)) {
/*  881 */         this.killRing.resetLastKill();
/*      */       }
/*      */     } 
/*  884 */     return o;
/*      */   }
/*      */ 
/*      */   
/*      */   public ParsedLine getParsedLine() {
/*  889 */     return this.parsedLine;
/*      */   }
/*      */   
/*      */   public String getLastBinding() {
/*  893 */     return this.bindingReader.getLastBinding();
/*      */   }
/*      */   
/*      */   public String getSearchTerm() {
/*  897 */     return (this.searchTerm != null) ? this.searchTerm.toString() : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public LineReader.RegionType getRegionActive() {
/*  902 */     return this.regionActive;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRegionMark() {
/*  907 */     return this.regionMark;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setKeyMap(String name) {
/*  922 */     KeyMap<Binding> map = this.keyMaps.get(name);
/*  923 */     if (map == null) {
/*  924 */       return false;
/*      */     }
/*  926 */     this.keyMap = name;
/*  927 */     if (this.reading) {
/*  928 */       callWidget("callback-keymap");
/*      */     }
/*  930 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getKeyMap() {
/*  940 */     return this.keyMap;
/*      */   }
/*      */ 
/*      */   
/*      */   public LineReader variable(String name, Object value) {
/*  945 */     this.variables.put(name, value);
/*  946 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<String, Object> getVariables() {
/*  951 */     return this.variables;
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getVariable(String name) {
/*  956 */     return this.variables.get(name);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setVariable(String name, Object value) {
/*  961 */     this.variables.put(name, value);
/*      */   }
/*      */ 
/*      */   
/*      */   public LineReader option(LineReader.Option option, boolean value) {
/*  966 */     this.options.put(option, Boolean.valueOf(value));
/*  967 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSet(LineReader.Option option) {
/*  972 */     Boolean b = this.options.get(option);
/*  973 */     return (b != null) ? b.booleanValue() : option.isDef();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOpt(LineReader.Option option) {
/*  978 */     this.options.put(option, Boolean.TRUE);
/*      */   }
/*      */ 
/*      */   
/*      */   public void unsetOpt(LineReader.Option option) {
/*  983 */     this.options.put(option, Boolean.FALSE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String finishBuffer() {
/*  998 */     String str = this.buf.toString();
/*  999 */     String historyLine = str;
/*      */     
/* 1001 */     if (!isSet(LineReader.Option.DISABLE_EVENT_EXPANSION)) {
/* 1002 */       StringBuilder sb = new StringBuilder();
/* 1003 */       boolean escaped = false;
/* 1004 */       for (int i = 0; i < str.length(); i++) {
/* 1005 */         char ch = str.charAt(i);
/* 1006 */         if (escaped) {
/* 1007 */           escaped = false;
/* 1008 */           if (ch != '\n') {
/* 1009 */             sb.append(ch);
/*      */           }
/* 1011 */         } else if (this.parser.isEscapeChar(ch)) {
/* 1012 */           escaped = true;
/*      */         } else {
/* 1014 */           sb.append(ch);
/*      */         } 
/*      */       } 
/* 1017 */       str = sb.toString();
/*      */     } 
/*      */     
/* 1020 */     if (this.maskingCallback != null) {
/* 1021 */       historyLine = this.maskingCallback.history(historyLine);
/*      */     }
/*      */ 
/*      */     
/* 1025 */     if (historyLine != null && historyLine.length() > 0) {
/* 1026 */       this.history.add(Instant.now(), historyLine);
/*      */     }
/* 1028 */     return str;
/*      */   }
/*      */   
/*      */   protected void handleSignal(Terminal.Signal signal) {
/* 1032 */     if (signal == Terminal.Signal.WINCH) {
/* 1033 */       Status status = Status.getStatus(this.terminal, false);
/* 1034 */       if (status != null) {
/* 1035 */         status.hardReset();
/*      */       }
/* 1037 */       this.size.copy(this.terminal.getBufferSize());
/* 1038 */       this.display.resize(this.size.getRows(), this.size.getColumns());
/* 1039 */       redrawLine();
/* 1040 */       redisplay();
/*      */     }
/* 1042 */     else if (signal == Terminal.Signal.CONT) {
/* 1043 */       this.terminal.enterRawMode();
/* 1044 */       this.size.copy(this.terminal.getBufferSize());
/* 1045 */       this.display.resize(this.size.getRows(), this.size.getColumns());
/* 1046 */       this.terminal.puts(InfoCmp.Capability.keypad_xmit, new Object[0]);
/* 1047 */       redrawLine();
/* 1048 */       redisplay();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected Widget getWidget(Object binding) {
/*      */     Widget w;
/* 1055 */     if (binding instanceof Widget) {
/* 1056 */       w = (Widget)binding;
/* 1057 */     } else if (binding instanceof Macro) {
/* 1058 */       String macro = ((Macro)binding).getSequence();
/* 1059 */       w = (() -> {
/*      */           this.bindingReader.runMacro(macro);
/*      */           return true;
/*      */         });
/* 1063 */     } else if (binding instanceof Reference) {
/* 1064 */       String name = ((Reference)binding).name();
/* 1065 */       w = this.widgets.get(name);
/* 1066 */       if (w == null) {
/* 1067 */         w = (() -> {
/*      */             this.post = (());
/*      */             return false;
/*      */           });
/*      */       }
/*      */     } else {
/* 1073 */       w = (() -> {
/*      */           this.post = (());
/*      */           return false;
/*      */         });
/*      */     } 
/* 1078 */     return w;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPrompt(String prompt) {
/* 1086 */     this
/* 1087 */       .prompt = (prompt == null) ? AttributedString.EMPTY : expandPromptPattern(prompt, 0, "", 0);
/*      */   }
/*      */   
/*      */   public void setRightPrompt(String rightPrompt) {
/* 1091 */     this
/* 1092 */       .rightPrompt = (rightPrompt == null) ? AttributedString.EMPTY : expandPromptPattern(rightPrompt, 0, "", 0);
/*      */   }
/*      */   
/*      */   protected void setBuffer(Buffer buffer) {
/* 1096 */     this.buf.copyFrom(buffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBuffer(String buffer) {
/* 1106 */     this.buf.clear();
/* 1107 */     this.buf.write(buffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String viDeleteChangeYankToRemap(String op) {
/* 1120 */     switch (op) {
/*      */       case "abort":
/*      */       case "backward-char":
/*      */       case "forward-char":
/*      */       case "end-of-line":
/*      */       case "vi-match-bracket":
/*      */       case "vi-digit-or-beginning-of-line":
/*      */       case "neg-argument":
/*      */       case "digit-argument":
/*      */       case "vi-backward-char":
/*      */       case "vi-backward-word":
/*      */       case "vi-forward-char":
/*      */       case "vi-forward-word":
/*      */       case "vi-forward-word-end":
/*      */       case "vi-first-non-blank":
/*      */       case "vi-goto-column":
/*      */       case "vi-delete":
/*      */       case "vi-yank":
/*      */       case "vi-change-to":
/*      */       case "vi-find-next-char":
/*      */       case "vi-find-next-char-skip":
/*      */       case "vi-find-prev-char":
/*      */       case "vi-find-prev-char-skip":
/*      */       case "vi-repeat-find":
/*      */       case "vi-rev-repeat-find":
/* 1145 */         return op;
/*      */     } 
/*      */     
/* 1148 */     return "vi-cmd-mode";
/*      */   }
/*      */ 
/*      */   
/*      */   protected int switchCase(int ch) {
/* 1153 */     if (Character.isUpperCase(ch))
/* 1154 */       return Character.toLowerCase(ch); 
/* 1155 */     if (Character.isLowerCase(ch)) {
/* 1156 */       return Character.toUpperCase(ch);
/*      */     }
/* 1158 */     return ch;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isInViMoveOperation() {
/* 1167 */     return (this.viMoveMode != ViMoveMode.NORMAL);
/*      */   }
/*      */   
/*      */   protected boolean isInViChangeOperation() {
/* 1171 */     return (this.viMoveMode == ViMoveMode.CHANGE);
/*      */   }
/*      */   
/*      */   protected boolean isInViCmdMode() {
/* 1175 */     return "vicmd".equals(this.keyMap);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean viForwardChar() {
/* 1184 */     if (this.count < 0) {
/* 1185 */       return callNeg(this::viBackwardChar);
/*      */     }
/* 1187 */     int lim = findeol();
/* 1188 */     if (isInViCmdMode() && !isInViMoveOperation()) {
/* 1189 */       lim--;
/*      */     }
/* 1191 */     if (this.buf.cursor() >= lim) {
/* 1192 */       return false;
/*      */     }
/* 1194 */     while (this.count-- > 0 && this.buf.cursor() < lim) {
/* 1195 */       this.buf.move(1);
/*      */     }
/* 1197 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viBackwardChar() {
/* 1201 */     if (this.count < 0) {
/* 1202 */       return callNeg(this::viForwardChar);
/*      */     }
/* 1204 */     int lim = findbol();
/* 1205 */     if (this.buf.cursor() == lim) {
/* 1206 */       return false;
/*      */     }
/* 1208 */     while (this.count-- > 0 && this.buf.cursor() > 0) {
/* 1209 */       this.buf.move(-1);
/* 1210 */       if (this.buf.currChar() == 10) {
/* 1211 */         this.buf.move(1);
/*      */         break;
/*      */       } 
/*      */     } 
/* 1215 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean forwardWord() {
/* 1224 */     if (this.count < 0) {
/* 1225 */       return callNeg(this::backwardWord);
/*      */     }
/* 1227 */     while (this.count-- > 0) {
/* 1228 */       while (this.buf.cursor() < this.buf.length() && isWord(this.buf.currChar())) {
/* 1229 */         this.buf.move(1);
/*      */       }
/* 1231 */       if (isInViChangeOperation() && this.count == 0) {
/*      */         break;
/*      */       }
/* 1234 */       while (this.buf.cursor() < this.buf.length() && !isWord(this.buf.currChar())) {
/* 1235 */         this.buf.move(1);
/*      */       }
/*      */     } 
/* 1238 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viForwardWord() {
/* 1242 */     if (this.count < 0) {
/* 1243 */       return callNeg(this::backwardWord);
/*      */     }
/* 1245 */     while (this.count-- > 0) {
/* 1246 */       if (isViAlphaNum(this.buf.currChar())) {
/* 1247 */         while (this.buf.cursor() < this.buf.length() && isViAlphaNum(this.buf.currChar())) {
/* 1248 */           this.buf.move(1);
/*      */         }
/*      */       } else {
/* 1251 */         while (this.buf.cursor() < this.buf.length() && 
/* 1252 */           !isViAlphaNum(this.buf.currChar()) && 
/* 1253 */           !isWhitespace(this.buf.currChar())) {
/* 1254 */           this.buf.move(1);
/*      */         }
/*      */       } 
/* 1257 */       if (isInViChangeOperation() && this.count == 0) {
/* 1258 */         return true;
/*      */       }
/* 1260 */       int nl = (this.buf.currChar() == 10) ? 1 : 0;
/* 1261 */       while (this.buf.cursor() < this.buf.length() && nl < 2 && 
/*      */         
/* 1263 */         isWhitespace(this.buf.currChar())) {
/* 1264 */         this.buf.move(1);
/* 1265 */         nl += (this.buf.currChar() == 10) ? 1 : 0;
/*      */       } 
/*      */     } 
/* 1268 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viForwardBlankWord() {
/* 1272 */     if (this.count < 0) {
/* 1273 */       return callNeg(this::viBackwardBlankWord);
/*      */     }
/* 1275 */     while (this.count-- > 0) {
/* 1276 */       while (this.buf.cursor() < this.buf.length() && !isWhitespace(this.buf.currChar())) {
/* 1277 */         this.buf.move(1);
/*      */       }
/* 1279 */       if (isInViChangeOperation() && this.count == 0) {
/* 1280 */         return true;
/*      */       }
/* 1282 */       int nl = (this.buf.currChar() == 10) ? 1 : 0;
/* 1283 */       while (this.buf.cursor() < this.buf.length() && nl < 2 && 
/*      */         
/* 1285 */         isWhitespace(this.buf.currChar())) {
/* 1286 */         this.buf.move(1);
/* 1287 */         nl += (this.buf.currChar() == 10) ? 1 : 0;
/*      */       } 
/*      */     } 
/* 1290 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean emacsForwardWord() {
/* 1294 */     if (this.count < 0) {
/* 1295 */       return callNeg(this::emacsBackwardWord);
/*      */     }
/* 1297 */     while (this.count-- > 0) {
/* 1298 */       while (this.buf.cursor() < this.buf.length() && !isWord(this.buf.currChar())) {
/* 1299 */         this.buf.move(1);
/*      */       }
/* 1301 */       if (isInViChangeOperation() && this.count == 0) {
/* 1302 */         return true;
/*      */       }
/* 1304 */       while (this.buf.cursor() < this.buf.length() && isWord(this.buf.currChar())) {
/* 1305 */         this.buf.move(1);
/*      */       }
/*      */     } 
/* 1308 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viForwardBlankWordEnd() {
/* 1312 */     if (this.count < 0) {
/* 1313 */       return false;
/*      */     }
/* 1315 */     while (this.count-- > 0) {
/* 1316 */       while (this.buf.cursor() < this.buf.length()) {
/* 1317 */         this.buf.move(1);
/* 1318 */         if (!isWhitespace(this.buf.currChar())) {
/*      */           break;
/*      */         }
/*      */       } 
/* 1322 */       while (this.buf.cursor() < this.buf.length()) {
/* 1323 */         this.buf.move(1);
/* 1324 */         if (isWhitespace(this.buf.currChar())) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     } 
/* 1329 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viForwardWordEnd() {
/* 1333 */     if (this.count < 0) {
/* 1334 */       return callNeg(this::backwardWord);
/*      */     }
/* 1336 */     while (this.count-- > 0) {
/* 1337 */       while (this.buf.cursor() < this.buf.length() && 
/* 1338 */         isWhitespace(this.buf.nextChar()))
/*      */       {
/*      */         
/* 1341 */         this.buf.move(1);
/*      */       }
/* 1343 */       if (this.buf.cursor() < this.buf.length()) {
/* 1344 */         if (isViAlphaNum(this.buf.nextChar())) {
/* 1345 */           this.buf.move(1);
/* 1346 */           while (this.buf.cursor() < this.buf.length() && isViAlphaNum(this.buf.nextChar()))
/* 1347 */             this.buf.move(1); 
/*      */           continue;
/*      */         } 
/* 1350 */         this.buf.move(1);
/* 1351 */         while (this.buf.cursor() < this.buf.length() && !isViAlphaNum(this.buf.nextChar()) && !isWhitespace(this.buf.nextChar())) {
/* 1352 */           this.buf.move(1);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1357 */     if (this.buf.cursor() < this.buf.length() && isInViMoveOperation()) {
/* 1358 */       this.buf.move(1);
/*      */     }
/* 1360 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean backwardWord() {
/* 1364 */     if (this.count < 0) {
/* 1365 */       return callNeg(this::forwardWord);
/*      */     }
/* 1367 */     while (this.count-- > 0) {
/* 1368 */       while (this.buf.cursor() > 0 && !isWord(this.buf.atChar(this.buf.cursor() - 1))) {
/* 1369 */         this.buf.move(-1);
/*      */       }
/* 1371 */       while (this.buf.cursor() > 0 && isWord(this.buf.atChar(this.buf.cursor() - 1))) {
/* 1372 */         this.buf.move(-1);
/*      */       }
/*      */     } 
/* 1375 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viBackwardWord() {
/* 1379 */     if (this.count < 0) {
/* 1380 */       return callNeg(this::backwardWord);
/*      */     }
/* 1382 */     while (this.count-- > 0) {
/* 1383 */       int nl = 0;
/* 1384 */       while (this.buf.cursor() > 0) {
/* 1385 */         this.buf.move(-1);
/* 1386 */         if (!isWhitespace(this.buf.currChar())) {
/*      */           break;
/*      */         }
/* 1389 */         nl += (this.buf.currChar() == 10) ? 1 : 0;
/* 1390 */         if (nl == 2) {
/* 1391 */           this.buf.move(1);
/*      */           break;
/*      */         } 
/*      */       } 
/* 1395 */       if (this.buf.cursor() > 0) {
/* 1396 */         if (isViAlphaNum(this.buf.currChar())) {
/* 1397 */           while (this.buf.cursor() > 0 && 
/* 1398 */             isViAlphaNum(this.buf.prevChar()))
/*      */           {
/*      */             
/* 1401 */             this.buf.move(-1); } 
/*      */           continue;
/*      */         } 
/* 1404 */         while (this.buf.cursor() > 0 && 
/* 1405 */           !isViAlphaNum(this.buf.prevChar()) && !isWhitespace(this.buf.prevChar()))
/*      */         {
/*      */           
/* 1408 */           this.buf.move(-1);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1413 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viBackwardBlankWord() {
/* 1417 */     if (this.count < 0) {
/* 1418 */       return callNeg(this::viForwardBlankWord);
/*      */     }
/* 1420 */     while (this.count-- > 0) {
/* 1421 */       while (this.buf.cursor() > 0) {
/* 1422 */         this.buf.move(-1);
/* 1423 */         if (!isWhitespace(this.buf.currChar())) {
/*      */           break;
/*      */         }
/*      */       } 
/* 1427 */       while (this.buf.cursor() > 0) {
/* 1428 */         this.buf.move(-1);
/* 1429 */         if (isWhitespace(this.buf.currChar())) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     } 
/* 1434 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viBackwardWordEnd() {
/* 1438 */     if (this.count < 0) {
/* 1439 */       return callNeg(this::viForwardWordEnd);
/*      */     }
/* 1441 */     while (this.count-- > 0 && this.buf.cursor() > 1) {
/*      */       int start;
/* 1443 */       if (isViAlphaNum(this.buf.currChar())) {
/* 1444 */         start = 1;
/* 1445 */       } else if (!isWhitespace(this.buf.currChar())) {
/* 1446 */         start = 2;
/*      */       } else {
/* 1448 */         start = 0;
/*      */       } 
/* 1450 */       while (this.buf.cursor() > 0) {
/* 1451 */         boolean same = (start != 1 && isWhitespace(this.buf.currChar()));
/* 1452 */         if (start != 0) {
/* 1453 */           same |= isViAlphaNum(this.buf.currChar());
/*      */         }
/* 1455 */         if (same == ((start == 2))) {
/*      */           break;
/*      */         }
/* 1458 */         this.buf.move(-1);
/*      */       } 
/* 1460 */       while (this.buf.cursor() > 0 && isWhitespace(this.buf.currChar())) {
/* 1461 */         this.buf.move(-1);
/*      */       }
/*      */     } 
/* 1464 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viBackwardBlankWordEnd() {
/* 1468 */     if (this.count < 0) {
/* 1469 */       return callNeg(this::viForwardBlankWordEnd);
/*      */     }
/* 1471 */     while (this.count-- > 0) {
/* 1472 */       while (this.buf.cursor() > 0 && !isWhitespace(this.buf.currChar())) {
/* 1473 */         this.buf.move(-1);
/*      */       }
/* 1475 */       while (this.buf.cursor() > 0 && isWhitespace(this.buf.currChar())) {
/* 1476 */         this.buf.move(-1);
/*      */       }
/*      */     } 
/* 1479 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean emacsBackwardWord() {
/* 1483 */     if (this.count < 0) {
/* 1484 */       return callNeg(this::emacsForwardWord);
/*      */     }
/* 1486 */     while (this.count-- > 0) {
/* 1487 */       while (this.buf.cursor() > 0) {
/* 1488 */         this.buf.move(-1);
/* 1489 */         if (isWord(this.buf.currChar())) {
/*      */           break;
/*      */         }
/*      */       } 
/* 1493 */       while (this.buf.cursor() > 0) {
/* 1494 */         this.buf.move(-1);
/* 1495 */         if (!isWord(this.buf.currChar())) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     } 
/* 1500 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean backwardDeleteWord() {
/* 1504 */     if (this.count < 0) {
/* 1505 */       return callNeg(this::deleteWord);
/*      */     }
/* 1507 */     int cursor = this.buf.cursor();
/* 1508 */     while (this.count-- > 0) {
/* 1509 */       while (cursor > 0 && !isWord(this.buf.atChar(cursor - 1))) {
/* 1510 */         cursor--;
/*      */       }
/* 1512 */       while (cursor > 0 && isWord(this.buf.atChar(cursor - 1))) {
/* 1513 */         cursor--;
/*      */       }
/*      */     } 
/* 1516 */     this.buf.backspace(this.buf.cursor() - cursor);
/* 1517 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viBackwardKillWord() {
/* 1521 */     if (this.count < 0) {
/* 1522 */       return false;
/*      */     }
/* 1524 */     int lim = findbol();
/* 1525 */     int x = this.buf.cursor();
/* 1526 */     while (this.count-- > 0) {
/* 1527 */       while (x > lim && isWhitespace(this.buf.atChar(x - 1))) {
/* 1528 */         x--;
/*      */       }
/* 1530 */       if (x > lim) {
/* 1531 */         if (isViAlphaNum(this.buf.atChar(x - 1))) {
/* 1532 */           while (x > lim && isViAlphaNum(this.buf.atChar(x - 1)))
/* 1533 */             x--; 
/*      */           continue;
/*      */         } 
/* 1536 */         while (x > lim && !isViAlphaNum(this.buf.atChar(x - 1)) && !isWhitespace(this.buf.atChar(x - 1))) {
/* 1537 */           x--;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1542 */     this.killRing.addBackwards(this.buf.substring(x, this.buf.cursor()));
/* 1543 */     this.buf.backspace(this.buf.cursor() - x);
/* 1544 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean backwardKillWord() {
/* 1548 */     if (this.count < 0) {
/* 1549 */       return callNeg(this::killWord);
/*      */     }
/* 1551 */     int x = this.buf.cursor();
/* 1552 */     while (this.count-- > 0) {
/* 1553 */       while (x > 0 && !isWord(this.buf.atChar(x - 1))) {
/* 1554 */         x--;
/*      */       }
/* 1556 */       while (x > 0 && isWord(this.buf.atChar(x - 1))) {
/* 1557 */         x--;
/*      */       }
/*      */     } 
/* 1560 */     this.killRing.addBackwards(this.buf.substring(x, this.buf.cursor()));
/* 1561 */     this.buf.backspace(this.buf.cursor() - x);
/* 1562 */     return true;
/*      */   }
/*      */   protected boolean copyPrevWord() {
/*      */     int t1;
/* 1566 */     if (this.count <= 0) {
/* 1567 */       return false;
/*      */     }
/* 1569 */     int t0 = this.buf.cursor();
/*      */     while (true) {
/* 1571 */       t1 = t0;
/* 1572 */       while (t0 > 0 && !isWord(this.buf.atChar(t0 - 1))) {
/* 1573 */         t0--;
/*      */       }
/* 1575 */       while (t0 > 0 && isWord(this.buf.atChar(t0 - 1))) {
/* 1576 */         t0--;
/*      */       }
/* 1578 */       if (--this.count == 0) {
/*      */         break;
/*      */       }
/* 1581 */       if (t0 == 0) {
/* 1582 */         return false;
/*      */       }
/*      */     } 
/* 1585 */     this.buf.write(this.buf.substring(t0, t1));
/* 1586 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean upCaseWord() {
/* 1590 */     int count = Math.abs(this.count);
/* 1591 */     int cursor = this.buf.cursor();
/* 1592 */     while (count-- > 0) {
/* 1593 */       while (this.buf.cursor() < this.buf.length() && !isWord(this.buf.currChar())) {
/* 1594 */         this.buf.move(1);
/*      */       }
/* 1596 */       while (this.buf.cursor() < this.buf.length() && isWord(this.buf.currChar())) {
/* 1597 */         this.buf.currChar(Character.toUpperCase(this.buf.currChar()));
/* 1598 */         this.buf.move(1);
/*      */       } 
/*      */     } 
/* 1601 */     if (this.count < 0) {
/* 1602 */       this.buf.cursor(cursor);
/*      */     }
/* 1604 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean downCaseWord() {
/* 1608 */     int count = Math.abs(this.count);
/* 1609 */     int cursor = this.buf.cursor();
/* 1610 */     while (count-- > 0) {
/* 1611 */       while (this.buf.cursor() < this.buf.length() && !isWord(this.buf.currChar())) {
/* 1612 */         this.buf.move(1);
/*      */       }
/* 1614 */       while (this.buf.cursor() < this.buf.length() && isWord(this.buf.currChar())) {
/* 1615 */         this.buf.currChar(Character.toLowerCase(this.buf.currChar()));
/* 1616 */         this.buf.move(1);
/*      */       } 
/*      */     } 
/* 1619 */     if (this.count < 0) {
/* 1620 */       this.buf.cursor(cursor);
/*      */     }
/* 1622 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean capitalizeWord() {
/* 1626 */     int count = Math.abs(this.count);
/* 1627 */     int cursor = this.buf.cursor();
/* 1628 */     while (count-- > 0) {
/* 1629 */       boolean first = true;
/* 1630 */       while (this.buf.cursor() < this.buf.length() && !isWord(this.buf.currChar())) {
/* 1631 */         this.buf.move(1);
/*      */       }
/* 1633 */       while (this.buf.cursor() < this.buf.length() && isWord(this.buf.currChar()) && !isAlpha(this.buf.currChar())) {
/* 1634 */         this.buf.move(1);
/*      */       }
/* 1636 */       while (this.buf.cursor() < this.buf.length() && isWord(this.buf.currChar())) {
/* 1637 */         this.buf.currChar(first ? 
/* 1638 */             Character.toUpperCase(this.buf.currChar()) : 
/* 1639 */             Character.toLowerCase(this.buf.currChar()));
/* 1640 */         this.buf.move(1);
/* 1641 */         first = false;
/*      */       } 
/*      */     } 
/* 1644 */     if (this.count < 0) {
/* 1645 */       this.buf.cursor(cursor);
/*      */     }
/* 1647 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean deleteWord() {
/* 1651 */     if (this.count < 0) {
/* 1652 */       return callNeg(this::backwardDeleteWord);
/*      */     }
/* 1654 */     int x = this.buf.cursor();
/* 1655 */     while (this.count-- > 0) {
/* 1656 */       while (x < this.buf.length() && !isWord(this.buf.atChar(x))) {
/* 1657 */         x++;
/*      */       }
/* 1659 */       while (x < this.buf.length() && isWord(this.buf.atChar(x))) {
/* 1660 */         x++;
/*      */       }
/*      */     } 
/* 1663 */     this.buf.delete(x - this.buf.cursor());
/* 1664 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean killWord() {
/* 1668 */     if (this.count < 0) {
/* 1669 */       return callNeg(this::backwardKillWord);
/*      */     }
/* 1671 */     int x = this.buf.cursor();
/* 1672 */     while (this.count-- > 0) {
/* 1673 */       while (x < this.buf.length() && !isWord(this.buf.atChar(x))) {
/* 1674 */         x++;
/*      */       }
/* 1676 */       while (x < this.buf.length() && isWord(this.buf.atChar(x))) {
/* 1677 */         x++;
/*      */       }
/*      */     } 
/* 1680 */     this.killRing.add(this.buf.substring(this.buf.cursor(), x));
/* 1681 */     this.buf.delete(x - this.buf.cursor());
/* 1682 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean transposeWords() {
/* 1686 */     int lstart = this.buf.cursor() - 1;
/* 1687 */     int lend = this.buf.cursor();
/* 1688 */     while (this.buf.atChar(lstart) != 0 && this.buf.atChar(lstart) != 10) {
/* 1689 */       lstart--;
/*      */     }
/* 1691 */     lstart++;
/* 1692 */     while (this.buf.atChar(lend) != 0 && this.buf.atChar(lend) != 10) {
/* 1693 */       lend++;
/*      */     }
/* 1695 */     if (lend - lstart < 2) {
/* 1696 */       return false;
/*      */     }
/* 1698 */     int words = 0;
/* 1699 */     boolean inWord = false;
/* 1700 */     if (!isDelimiter(this.buf.atChar(lstart))) {
/* 1701 */       words++;
/* 1702 */       inWord = true;
/*      */     } 
/* 1704 */     for (int i = lstart; i < lend; i++) {
/* 1705 */       if (isDelimiter(this.buf.atChar(i))) {
/* 1706 */         inWord = false;
/*      */       } else {
/* 1708 */         if (!inWord) {
/* 1709 */           words++;
/*      */         }
/* 1711 */         inWord = true;
/*      */       } 
/*      */     } 
/* 1714 */     if (words < 2) {
/* 1715 */       return false;
/*      */     }
/*      */     
/* 1718 */     boolean neg = (this.count < 0);
/* 1719 */     for (int count = Math.max(this.count, -this.count); count > 0; count--) {
/*      */ 
/*      */       
/* 1722 */       int sta2, end2, sta1 = this.buf.cursor();
/* 1723 */       while (sta1 > lstart && !isDelimiter(this.buf.atChar(sta1 - 1))) {
/* 1724 */         sta1--;
/*      */       }
/* 1726 */       int end1 = sta1;
/* 1727 */       while (end1 < lend && !isDelimiter(this.buf.atChar(++end1)));
/* 1728 */       if (neg) {
/* 1729 */         end2 = sta1 - 1;
/* 1730 */         while (end2 > lstart && isDelimiter(this.buf.atChar(end2 - 1))) {
/* 1731 */           end2--;
/*      */         }
/* 1733 */         if (end2 < lstart) {
/*      */           
/* 1735 */           sta2 = end1;
/* 1736 */           while (isDelimiter(this.buf.atChar(++sta2)));
/* 1737 */           end2 = sta2;
/* 1738 */           while (end2 < lend && !isDelimiter(this.buf.atChar(++end2)));
/*      */         } else {
/* 1740 */           sta2 = end2;
/* 1741 */           while (sta2 > lstart && !isDelimiter(this.buf.atChar(sta2 - 1))) {
/* 1742 */             sta2--;
/*      */           }
/*      */         } 
/*      */       } else {
/* 1746 */         sta2 = end1;
/* 1747 */         while (sta2 < lend && isDelimiter(this.buf.atChar(++sta2)));
/* 1748 */         if (sta2 == lend) {
/*      */           
/* 1750 */           end2 = sta1;
/* 1751 */           while (isDelimiter(this.buf.atChar(end2 - 1))) {
/* 1752 */             end2--;
/*      */           }
/* 1754 */           sta2 = end2;
/* 1755 */           while (sta2 > lstart && !isDelimiter(this.buf.atChar(sta2 - 1))) {
/* 1756 */             sta2--;
/*      */           }
/*      */         } else {
/* 1759 */           end2 = sta2;
/* 1760 */           while (end2 < lend && !isDelimiter(this.buf.atChar(++end2)));
/*      */         } 
/*      */       } 
/* 1763 */       if (sta1 < sta2) {
/*      */ 
/*      */         
/* 1766 */         String res = this.buf.substring(0, sta1) + this.buf.substring(sta2, end2) + this.buf.substring(end1, sta2) + this.buf.substring(sta1, end1) + this.buf.substring(end2);
/* 1767 */         this.buf.clear();
/* 1768 */         this.buf.write(res);
/* 1769 */         this.buf.cursor(neg ? end1 : end2);
/*      */       }
/*      */       else {
/*      */         
/* 1773 */         String res = this.buf.substring(0, sta2) + this.buf.substring(sta1, end1) + this.buf.substring(end2, sta1) + this.buf.substring(sta2, end2) + this.buf.substring(end1);
/* 1774 */         this.buf.clear();
/* 1775 */         this.buf.write(res);
/* 1776 */         this.buf.cursor(neg ? end2 : end1);
/*      */       } 
/*      */     } 
/* 1779 */     return true;
/*      */   }
/*      */   
/*      */   private int findbol() {
/* 1783 */     int x = this.buf.cursor();
/* 1784 */     while (x > 0 && this.buf.atChar(x - 1) != 10) {
/* 1785 */       x--;
/*      */     }
/* 1787 */     return x;
/*      */   }
/*      */   
/*      */   private int findeol() {
/* 1791 */     int x = this.buf.cursor();
/* 1792 */     while (x < this.buf.length() && this.buf.atChar(x) != 10) {
/* 1793 */       x++;
/*      */     }
/* 1795 */     return x;
/*      */   }
/*      */   
/*      */   protected boolean insertComment() {
/* 1799 */     return doInsertComment(false);
/*      */   }
/*      */   
/*      */   protected boolean viInsertComment() {
/* 1803 */     return doInsertComment(true);
/*      */   }
/*      */   
/*      */   protected boolean doInsertComment(boolean isViMode) {
/* 1807 */     String comment = getString("comment-begin", "#");
/* 1808 */     beginningOfLine();
/* 1809 */     putString(comment);
/* 1810 */     if (isViMode) {
/* 1811 */       setKeyMap("viins");
/*      */     }
/* 1813 */     return acceptLine();
/*      */   }
/*      */   
/*      */   protected boolean viFindNextChar() {
/* 1817 */     if ((this.findChar = vigetkey()) > 0) {
/* 1818 */       this.findDir = 1;
/* 1819 */       this.findTailAdd = 0;
/* 1820 */       return vifindchar(false);
/*      */     } 
/* 1822 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean viFindPrevChar() {
/* 1826 */     if ((this.findChar = vigetkey()) > 0) {
/* 1827 */       this.findDir = -1;
/* 1828 */       this.findTailAdd = 0;
/* 1829 */       return vifindchar(false);
/*      */     } 
/* 1831 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean viFindNextCharSkip() {
/* 1835 */     if ((this.findChar = vigetkey()) > 0) {
/* 1836 */       this.findDir = 1;
/* 1837 */       this.findTailAdd = -1;
/* 1838 */       return vifindchar(false);
/*      */     } 
/* 1840 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean viFindPrevCharSkip() {
/* 1844 */     if ((this.findChar = vigetkey()) > 0) {
/* 1845 */       this.findDir = -1;
/* 1846 */       this.findTailAdd = 1;
/* 1847 */       return vifindchar(false);
/*      */     } 
/* 1849 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean viRepeatFind() {
/* 1853 */     return vifindchar(true);
/*      */   }
/*      */   
/*      */   protected boolean viRevRepeatFind() {
/* 1857 */     if (this.count < 0) {
/* 1858 */       return callNeg(() -> vifindchar(true));
/*      */     }
/* 1860 */     this.findTailAdd = -this.findTailAdd;
/* 1861 */     this.findDir = -this.findDir;
/* 1862 */     boolean ret = vifindchar(true);
/* 1863 */     this.findTailAdd = -this.findTailAdd;
/* 1864 */     this.findDir = -this.findDir;
/* 1865 */     return ret;
/*      */   }
/*      */   
/*      */   private int vigetkey() {
/* 1869 */     int ch = readCharacter();
/* 1870 */     KeyMap<Binding> km = this.keyMaps.get("main");
/* 1871 */     if (km != null) {
/* 1872 */       Binding b = (Binding)km.getBound(new String(Character.toChars(ch)));
/* 1873 */       if (b instanceof Reference) {
/* 1874 */         String func = ((Reference)b).name();
/* 1875 */         if ("abort".equals(func)) {
/* 1876 */           return -1;
/*      */         }
/*      */       } 
/*      */     } 
/* 1880 */     return ch;
/*      */   }
/*      */   
/*      */   private boolean vifindchar(boolean repeat) {
/* 1884 */     if (this.findDir == 0) {
/* 1885 */       return false;
/*      */     }
/* 1887 */     if (this.count < 0) {
/* 1888 */       return callNeg(this::viRevRepeatFind);
/*      */     }
/* 1890 */     if (repeat && this.findTailAdd != 0) {
/* 1891 */       if (this.findDir > 0) {
/* 1892 */         if (this.buf.cursor() < this.buf.length() && this.buf.nextChar() == this.findChar) {
/* 1893 */           this.buf.move(1);
/*      */         }
/*      */       }
/* 1896 */       else if (this.buf.cursor() > 0 && this.buf.prevChar() == this.findChar) {
/* 1897 */         this.buf.move(-1);
/*      */       } 
/*      */     }
/*      */     
/* 1901 */     int cursor = this.buf.cursor();
/* 1902 */     while (this.count-- > 0) {
/*      */       do {
/* 1904 */         this.buf.move(this.findDir);
/* 1905 */       } while (this.buf.cursor() > 0 && this.buf.cursor() < this.buf.length() && this.buf
/* 1906 */         .currChar() != this.findChar && this.buf
/* 1907 */         .currChar() != 10);
/* 1908 */       if (this.buf.cursor() <= 0 || this.buf.cursor() >= this.buf.length() || this.buf
/* 1909 */         .currChar() == 10) {
/* 1910 */         this.buf.cursor(cursor);
/* 1911 */         return false;
/*      */       } 
/*      */     } 
/* 1914 */     if (this.findTailAdd != 0) {
/* 1915 */       this.buf.move(this.findTailAdd);
/*      */     }
/* 1917 */     if (this.findDir == 1 && isInViMoveOperation()) {
/* 1918 */       this.buf.move(1);
/*      */     }
/* 1920 */     return true;
/*      */   }
/*      */   
/*      */   private boolean callNeg(Widget widget) {
/* 1924 */     this.count = -this.count;
/* 1925 */     boolean ret = widget.apply();
/* 1926 */     this.count = -this.count;
/* 1927 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean viHistorySearchForward() {
/* 1936 */     this.searchDir = 1;
/* 1937 */     this.searchIndex = 0;
/* 1938 */     return (getViSearchString() && viRepeatSearch());
/*      */   }
/*      */   
/*      */   protected boolean viHistorySearchBackward() {
/* 1942 */     this.searchDir = -1;
/* 1943 */     this.searchIndex = this.history.size() - 1;
/* 1944 */     return (getViSearchString() && viRepeatSearch());
/*      */   }
/*      */   
/*      */   protected boolean viRepeatSearch() {
/* 1948 */     if (this.searchDir == 0) {
/* 1949 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1953 */     int si = (this.searchDir < 0) ? searchBackwards(this.searchString, this.searchIndex, false) : searchForwards(this.searchString, this.searchIndex, false);
/* 1954 */     if (si == -1 || si == this.history.index()) {
/* 1955 */       return false;
/*      */     }
/* 1957 */     this.searchIndex = si;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1962 */     this.buf.clear();
/* 1963 */     this.history.moveTo(this.searchIndex);
/* 1964 */     this.buf.write(this.history.get(this.searchIndex));
/* 1965 */     if ("vicmd".equals(this.keyMap)) {
/* 1966 */       this.buf.move(-1);
/*      */     }
/* 1968 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean viRevRepeatSearch() {
/* 1973 */     this.searchDir = -this.searchDir;
/* 1974 */     boolean ret = viRepeatSearch();
/* 1975 */     this.searchDir = -this.searchDir;
/* 1976 */     return ret;
/*      */   }
/*      */   
/*      */   private boolean getViSearchString() {
/* 1980 */     if (this.searchDir == 0) {
/* 1981 */       return false;
/*      */     }
/* 1983 */     String searchPrompt = (this.searchDir < 0) ? "?" : "/";
/* 1984 */     Buffer searchBuffer = new BufferImpl();
/*      */     
/* 1986 */     KeyMap<Binding> keyMap = this.keyMaps.get("main");
/* 1987 */     if (keyMap == null) {
/* 1988 */       keyMap = this.keyMaps.get(".safe");
/*      */     }
/*      */     while (true) {
/* 1991 */       this.post = (() -> new AttributedString(searchPrompt + searchBuffer.toString() + "_"));
/* 1992 */       redisplay();
/* 1993 */       Binding b = doReadBinding(keyMap, null);
/* 1994 */       if (b instanceof Reference) {
/* 1995 */         int c; String func = ((Reference)b).name();
/* 1996 */         switch (func) {
/*      */           case "abort":
/* 1998 */             this.post = null;
/* 1999 */             return false;
/*      */           case "accept-line":
/*      */           case "vi-cmd-mode":
/* 2002 */             this.searchString = searchBuffer.toString();
/* 2003 */             this.post = null;
/* 2004 */             return true;
/*      */           case "magic-space":
/* 2006 */             searchBuffer.write(32);
/*      */             continue;
/*      */           case "redisplay":
/* 2009 */             redisplay();
/*      */             continue;
/*      */           case "clear-screen":
/* 2012 */             clearScreen();
/*      */             continue;
/*      */           case "self-insert":
/* 2015 */             searchBuffer.write(getLastBinding());
/*      */             continue;
/*      */           case "self-insert-unmeta":
/* 2018 */             if (getLastBinding().charAt(0) == '\033') {
/* 2019 */               String s = getLastBinding().substring(1);
/* 2020 */               if ("\r".equals(s)) {
/* 2021 */                 s = "\n";
/*      */               }
/* 2023 */               searchBuffer.write(s);
/*      */             } 
/*      */             continue;
/*      */           case "backward-delete-char":
/*      */           case "vi-backward-delete-char":
/* 2028 */             if (searchBuffer.length() > 0) {
/* 2029 */               searchBuffer.backspace();
/*      */             }
/*      */             continue;
/*      */           case "backward-kill-word":
/*      */           case "vi-backward-kill-word":
/* 2034 */             if (searchBuffer.length() > 0 && !isWhitespace(searchBuffer.prevChar())) {
/* 2035 */               searchBuffer.backspace();
/*      */             }
/* 2037 */             if (searchBuffer.length() > 0 && isWhitespace(searchBuffer.prevChar())) {
/* 2038 */               searchBuffer.backspace();
/*      */             }
/*      */             continue;
/*      */           case "quoted-insert":
/*      */           case "vi-quoted-insert":
/* 2043 */             c = readCharacter();
/* 2044 */             if (c >= 0) {
/* 2045 */               searchBuffer.write(c); continue;
/*      */             } 
/* 2047 */             beep();
/*      */             continue;
/*      */         } 
/*      */         
/* 2051 */         beep();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean insertCloseCurly() {
/* 2059 */     return insertClose("}");
/*      */   }
/*      */   
/*      */   protected boolean insertCloseParen() {
/* 2063 */     return insertClose(")");
/*      */   }
/*      */   
/*      */   protected boolean insertCloseSquare() {
/* 2067 */     return insertClose("]");
/*      */   }
/*      */   
/*      */   protected boolean insertClose(String s) {
/* 2071 */     putString(s);
/*      */     
/* 2073 */     long blink = getLong("blink-matching-paren", 500L);
/* 2074 */     if (blink <= 0L) {
/* 2075 */       return true;
/*      */     }
/*      */     
/* 2078 */     int closePosition = this.buf.cursor();
/*      */     
/* 2080 */     this.buf.move(-1);
/* 2081 */     doViMatchBracket();
/* 2082 */     redisplay();
/*      */     
/* 2084 */     peekCharacter(blink);
/*      */     
/* 2086 */     this.buf.cursor(closePosition);
/* 2087 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viMatchBracket() {
/* 2091 */     return doViMatchBracket();
/*      */   }
/*      */   
/*      */   protected boolean undefinedKey() {
/* 2095 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean doViMatchBracket() {
/* 2106 */     int pos = this.buf.cursor();
/*      */     
/* 2108 */     if (pos == this.buf.length()) {
/* 2109 */       return false;
/*      */     }
/*      */     
/* 2112 */     int type = getBracketType(this.buf.atChar(pos));
/* 2113 */     int move = (type < 0) ? -1 : 1;
/* 2114 */     int count = 1;
/*      */     
/* 2116 */     if (type == 0) {
/* 2117 */       return false;
/*      */     }
/* 2119 */     while (count > 0) {
/* 2120 */       pos += move;
/*      */ 
/*      */       
/* 2123 */       if (pos < 0 || pos >= this.buf.length()) {
/* 2124 */         return false;
/*      */       }
/*      */       
/* 2127 */       int curType = getBracketType(this.buf.atChar(pos));
/* 2128 */       if (curType == type) {
/* 2129 */         count++; continue;
/*      */       } 
/* 2131 */       if (curType == -type) {
/* 2132 */         count--;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2140 */     if (move > 0 && isInViMoveOperation()) {
/* 2141 */       pos++;
/*      */     }
/* 2143 */     this.buf.cursor(pos);
/* 2144 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getBracketType(int ch) {
/* 2155 */     switch (ch) { case 91:
/* 2156 */         return 1;
/* 2157 */       case 93: return -1;
/* 2158 */       case 123: return 2;
/* 2159 */       case 125: return -2;
/* 2160 */       case 40: return 3;
/* 2161 */       case 41: return -3; }
/*      */     
/* 2163 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean transposeChars() {
/* 2174 */     int lstart = this.buf.cursor() - 1;
/* 2175 */     int lend = this.buf.cursor();
/* 2176 */     while (this.buf.atChar(lstart) != 0 && this.buf.atChar(lstart) != 10) {
/* 2177 */       lstart--;
/*      */     }
/* 2179 */     lstart++;
/* 2180 */     while (this.buf.atChar(lend) != 0 && this.buf.atChar(lend) != 10) {
/* 2181 */       lend++;
/*      */     }
/* 2183 */     if (lend - lstart < 2) {
/* 2184 */       return false;
/*      */     }
/* 2186 */     boolean neg = (this.count < 0);
/* 2187 */     for (int count = Math.max(this.count, -this.count); count > 0; count--) {
/* 2188 */       while (this.buf.cursor() <= lstart) {
/* 2189 */         this.buf.move(1);
/*      */       }
/* 2191 */       while (this.buf.cursor() >= lend) {
/* 2192 */         this.buf.move(-1);
/*      */       }
/* 2194 */       int c = this.buf.currChar();
/* 2195 */       this.buf.currChar(this.buf.prevChar());
/* 2196 */       this.buf.move(-1);
/* 2197 */       this.buf.currChar(c);
/* 2198 */       this.buf.move(neg ? 0 : 2);
/*      */     } 
/* 2200 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean undo() {
/* 2204 */     this.isUndo = true;
/* 2205 */     if (this.undo.canUndo()) {
/* 2206 */       this.undo.undo();
/* 2207 */       return true;
/*      */     } 
/* 2209 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean redo() {
/* 2213 */     this.isUndo = true;
/* 2214 */     if (this.undo.canRedo()) {
/* 2215 */       this.undo.redo();
/* 2216 */       return true;
/*      */     } 
/* 2218 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean sendBreak() {
/* 2222 */     if (this.searchTerm == null) {
/* 2223 */       this.buf.clear();
/* 2224 */       println();
/* 2225 */       redrawLine();
/*      */       
/* 2227 */       return false;
/*      */     } 
/* 2229 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean backwardChar() {
/* 2233 */     return (this.buf.move(-this.count) != 0);
/*      */   }
/*      */   
/*      */   protected boolean forwardChar() {
/* 2237 */     return (this.buf.move(this.count) != 0);
/*      */   }
/*      */   
/*      */   protected boolean viDigitOrBeginningOfLine() {
/* 2241 */     if (this.repeatCount > 0) {
/* 2242 */       return digitArgument();
/*      */     }
/* 2244 */     return beginningOfLine();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean universalArgument() {
/* 2249 */     this.mult *= this.universal;
/* 2250 */     this.isArgDigit = true;
/* 2251 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean argumentBase() {
/* 2255 */     if (this.repeatCount > 0 && this.repeatCount < 32) {
/* 2256 */       this.universal = this.repeatCount;
/* 2257 */       this.isArgDigit = true;
/* 2258 */       return true;
/*      */     } 
/* 2260 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean negArgument() {
/* 2265 */     this.mult *= -1;
/* 2266 */     this.isArgDigit = true;
/* 2267 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean digitArgument() {
/* 2271 */     String s = getLastBinding();
/* 2272 */     this.repeatCount = this.repeatCount * 10 + s.charAt(s.length() - 1) - 48;
/* 2273 */     this.isArgDigit = true;
/* 2274 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viDelete() {
/* 2278 */     int cursorStart = this.buf.cursor();
/* 2279 */     Binding o = readBinding(getKeys());
/* 2280 */     if (o instanceof Reference) {
/*      */       
/* 2282 */       String op = viDeleteChangeYankToRemap(((Reference)o).name());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2287 */       if ("vi-delete".equals(op)) {
/* 2288 */         killWholeLine();
/*      */       } else {
/* 2290 */         this.viMoveMode = ViMoveMode.DELETE;
/* 2291 */         Widget widget = this.widgets.get(op);
/* 2292 */         if (widget != null && !widget.apply()) {
/* 2293 */           this.viMoveMode = ViMoveMode.NORMAL;
/* 2294 */           return false;
/*      */         } 
/* 2296 */         this.viMoveMode = ViMoveMode.NORMAL;
/*      */       } 
/* 2298 */       return viDeleteTo(cursorStart, this.buf.cursor());
/*      */     } 
/* 2300 */     pushBackBinding();
/* 2301 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean viYankTo() {
/* 2306 */     int cursorStart = this.buf.cursor();
/* 2307 */     Binding o = readBinding(getKeys());
/* 2308 */     if (o instanceof Reference) {
/*      */       
/* 2310 */       String op = viDeleteChangeYankToRemap(((Reference)o).name());
/*      */       
/* 2312 */       if ("vi-yank".equals(op)) {
/* 2313 */         this.yankBuffer = this.buf.toString();
/* 2314 */         return true;
/*      */       } 
/* 2316 */       this.viMoveMode = ViMoveMode.YANK;
/* 2317 */       Widget widget = this.widgets.get(op);
/* 2318 */       if (widget != null && !widget.apply()) {
/* 2319 */         return false;
/*      */       }
/* 2321 */       this.viMoveMode = ViMoveMode.NORMAL;
/*      */       
/* 2323 */       return viYankTo(cursorStart, this.buf.cursor());
/*      */     } 
/* 2325 */     pushBackBinding();
/* 2326 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean viYankWholeLine() {
/* 2332 */     int p = this.buf.cursor();
/* 2333 */     while (this.buf.move(-1) == -1 && this.buf.prevChar() != 10);
/* 2334 */     int s = this.buf.cursor();
/* 2335 */     for (int i = 0; i < this.repeatCount; i++) {
/* 2336 */       while (this.buf.move(1) == 1 && this.buf.prevChar() != 10);
/*      */     }
/* 2338 */     int e = this.buf.cursor();
/* 2339 */     this.yankBuffer = this.buf.substring(s, e);
/* 2340 */     if (!this.yankBuffer.endsWith("\n")) {
/* 2341 */       this.yankBuffer += "\n";
/*      */     }
/* 2343 */     this.buf.cursor(p);
/* 2344 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viChange() {
/* 2348 */     int cursorStart = this.buf.cursor();
/* 2349 */     Binding o = readBinding(getKeys());
/* 2350 */     if (o instanceof Reference) {
/*      */       
/* 2352 */       String op = viDeleteChangeYankToRemap(((Reference)o).name());
/*      */       
/* 2354 */       if ("vi-change-to".equals(op)) {
/* 2355 */         killWholeLine();
/*      */       } else {
/* 2357 */         this.viMoveMode = ViMoveMode.CHANGE;
/* 2358 */         Widget widget = this.widgets.get(op);
/* 2359 */         if (widget != null && !widget.apply()) {
/* 2360 */           this.viMoveMode = ViMoveMode.NORMAL;
/* 2361 */           return false;
/*      */         } 
/* 2363 */         this.viMoveMode = ViMoveMode.NORMAL;
/*      */       } 
/* 2365 */       boolean res = viChange(cursorStart, this.buf.cursor());
/* 2366 */       setKeyMap("viins");
/* 2367 */       return res;
/*      */     } 
/* 2369 */     pushBackBinding();
/* 2370 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void cleanup() {
/* 2410 */     if (isSet(LineReader.Option.ERASE_LINE_ON_FINISH)) {
/* 2411 */       Buffer oldBuffer = this.buf.copy();
/* 2412 */       AttributedString oldPrompt = this.prompt;
/* 2413 */       this.buf.clear();
/* 2414 */       this.prompt = new AttributedString("");
/* 2415 */       doCleanup(false);
/* 2416 */       this.prompt = oldPrompt;
/* 2417 */       this.buf.copyFrom(oldBuffer);
/*      */     } else {
/* 2419 */       doCleanup(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void doCleanup(boolean nl) {
/* 2424 */     this.buf.cursor(this.buf.length());
/* 2425 */     this.post = null;
/* 2426 */     if (this.size.getColumns() > 0 || this.size.getRows() > 0) {
/* 2427 */       redisplay(false);
/* 2428 */       if (nl) {
/* 2429 */         println();
/*      */       }
/* 2431 */       this.terminal.puts(InfoCmp.Capability.keypad_local, new Object[0]);
/* 2432 */       this.terminal.trackMouse(Terminal.MouseTracking.Off);
/* 2433 */       if (isSet(LineReader.Option.BRACKETED_PASTE))
/* 2434 */         this.terminal.writer().write("\033[?2004l"); 
/* 2435 */       flush();
/*      */     } 
/* 2437 */     this.history.moveToEnd();
/*      */   }
/*      */   
/*      */   protected boolean historyIncrementalSearchForward() {
/* 2441 */     return doSearchHistory(false);
/*      */   }
/*      */   
/*      */   protected boolean historyIncrementalSearchBackward() {
/* 2445 */     return doSearchHistory(true);
/*      */   }
/*      */   static class Pair<U, V> { final U u;
/*      */     final V v;
/*      */     
/*      */     public Pair(U u, V v) {
/* 2451 */       this.u = u;
/* 2452 */       this.v = v;
/*      */     }
/*      */     public U getU() {
/* 2455 */       return this.u;
/*      */     }
/*      */     public V getV() {
/* 2458 */       return this.v;
/*      */     } }
/*      */ 
/*      */   
/*      */   protected boolean doSearchHistory(boolean backward) {
/* 2463 */     if (this.history.isEmpty()) {
/* 2464 */       return false;
/*      */     }
/*      */     
/* 2467 */     KeyMap<Binding> terminators = new KeyMap();
/* 2468 */     getString("search-terminators", "\033\n")
/* 2469 */       .codePoints().forEach(c -> bind(terminators, "accept-line", new CharSequence[] { new String(Character.toChars(c)) }));
/*      */     
/* 2471 */     Buffer originalBuffer = this.buf.copy();
/* 2472 */     this.searchIndex = -1;
/* 2473 */     this.searchTerm = new StringBuffer();
/* 2474 */     this.searchBackward = backward;
/* 2475 */     this.searchFailing = false;
/* 2476 */     this.post = (() -> new AttributedString((this.searchFailing ? "failing " : "") + (this.searchBackward ? "bck-i-search" : "fwd-i-search") + ": " + this.searchTerm + "_"));
/*      */ 
/*      */ 
/*      */     
/* 2480 */     redisplay(); try {
/*      */       while (true) {
/*      */         boolean bool;
/* 2483 */         int prevSearchIndex = this.searchIndex;
/* 2484 */         Binding operation = readBinding(getKeys(), terminators);
/* 2485 */         String ref = (operation instanceof Reference) ? ((Reference)operation).name() : "";
/* 2486 */         boolean next = false;
/* 2487 */         switch (ref) {
/*      */           case "abort":
/* 2489 */             beep();
/* 2490 */             this.buf.copyFrom(originalBuffer);
/* 2491 */             bool = true; return bool;
/*      */           case "history-incremental-search-backward":
/* 2493 */             this.searchBackward = true;
/* 2494 */             next = true;
/*      */             break;
/*      */           case "history-incremental-search-forward":
/* 2497 */             this.searchBackward = false;
/* 2498 */             next = true;
/*      */             break;
/*      */           case "backward-delete-char":
/* 2501 */             if (this.searchTerm.length() > 0) {
/* 2502 */               this.searchTerm.deleteCharAt(this.searchTerm.length() - 1);
/*      */             }
/*      */             break;
/*      */           case "self-insert":
/* 2506 */             this.searchTerm.append(getLastBinding());
/*      */             break;
/*      */           
/*      */           default:
/* 2510 */             if (this.searchIndex != -1) {
/* 2511 */               this.history.moveTo(this.searchIndex);
/*      */             }
/* 2513 */             pushBackBinding();
/* 2514 */             bool = true; return bool;
/*      */         } 
/*      */ 
/*      */         
/* 2518 */         String pattern = doGetSearchPattern();
/* 2519 */         if (pattern.length() == 0) {
/* 2520 */           this.buf.copyFrom(originalBuffer);
/* 2521 */           this.searchFailing = false;
/*      */         } else {
/* 2523 */           boolean caseInsensitive = isSet(LineReader.Option.CASE_INSENSITIVE_SEARCH);
/* 2524 */           Pattern pat = Pattern.compile(pattern, caseInsensitive ? 66 : 64);
/*      */           
/* 2526 */           Pair<Integer, Integer> pair = null;
/* 2527 */           if (this.searchBackward) {
/* 2528 */             boolean nextOnly = next;
/*      */ 
/*      */ 
/*      */             
/* 2532 */             pair = matches(pat, this.buf.toString(), this.searchIndex).stream().filter(p -> nextOnly ? ((((Integer)p.v).intValue() < this.buf.cursor())) : ((((Integer)p.v).intValue() <= this.buf.cursor()))).max(Comparator.comparing(Pair::getV)).orElse(null);
/* 2533 */             if (pair == null)
/*      */             {
/*      */ 
/*      */ 
/*      */               
/* 2538 */               pair = StreamSupport.stream(Spliterators.spliteratorUnknownSize(this.history.reverseIterator((this.searchIndex < 0) ? this.history.last() : (this.searchIndex - 1)), 16), false).flatMap(e -> matches(pat, e.line(), e.index()).stream()).findFirst().orElse(null);
/*      */             }
/*      */           } else {
/* 2541 */             boolean nextOnly = next;
/*      */ 
/*      */ 
/*      */             
/* 2545 */             pair = matches(pat, this.buf.toString(), this.searchIndex).stream().filter(p -> nextOnly ? ((((Integer)p.v).intValue() > this.buf.cursor())) : ((((Integer)p.v).intValue() >= this.buf.cursor()))).min(Comparator.comparing(Pair::getV)).orElse(null);
/* 2546 */             if (pair == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 2551 */               pair = StreamSupport.stream(Spliterators.spliteratorUnknownSize(this.history.iterator(((this.searchIndex < 0) ? this.history.last() : this.searchIndex) + 1), 16), false).flatMap(e -> matches(pat, e.line(), e.index()).stream()).findFirst().orElse(null);
/* 2552 */               if (pair == null && this.searchIndex >= 0)
/*      */               {
/*      */                 
/* 2555 */                 pair = matches(pat, originalBuffer.toString(), -1).stream().min(Comparator.comparing(Pair::getV)).orElse(null);
/*      */               }
/*      */             } 
/*      */           } 
/* 2559 */           if (pair != null) {
/* 2560 */             this.searchIndex = ((Integer)pair.u).intValue();
/* 2561 */             this.buf.clear();
/* 2562 */             if (this.searchIndex >= 0) {
/* 2563 */               this.buf.write(this.history.get(this.searchIndex));
/*      */             } else {
/* 2565 */               this.buf.write(originalBuffer.toString());
/*      */             } 
/* 2567 */             this.buf.cursor(((Integer)pair.v).intValue());
/* 2568 */             this.searchFailing = false;
/*      */           } else {
/* 2570 */             this.searchFailing = true;
/* 2571 */             beep();
/*      */           } 
/*      */         } 
/* 2574 */         redisplay();
/*      */       } 
/* 2576 */     } catch (IOError e) {
/*      */       
/* 2578 */       if (!(e.getCause() instanceof InterruptedException)) {
/* 2579 */         throw e;
/*      */       }
/* 2581 */       return true;
/*      */     } finally {
/* 2583 */       this.searchTerm = null;
/* 2584 */       this.searchIndex = -1;
/* 2585 */       this.post = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private List<Pair<Integer, Integer>> matches(Pattern p, String line, int index) {
/* 2590 */     List<Pair<Integer, Integer>> starts = new ArrayList<>();
/* 2591 */     Matcher m = p.matcher(line);
/* 2592 */     while (m.find()) {
/* 2593 */       starts.add(new Pair<>(Integer.valueOf(index), Integer.valueOf(m.start())));
/*      */     }
/* 2595 */     return starts;
/*      */   }
/*      */   
/*      */   private String doGetSearchPattern() {
/* 2599 */     StringBuilder sb = new StringBuilder();
/* 2600 */     boolean inQuote = false;
/* 2601 */     for (int i = 0; i < this.searchTerm.length(); i++) {
/* 2602 */       char c = this.searchTerm.charAt(i);
/* 2603 */       if (Character.isLowerCase(c)) {
/* 2604 */         if (inQuote) {
/* 2605 */           sb.append("\\E");
/* 2606 */           inQuote = false;
/*      */         } 
/* 2608 */         sb.append("[").append(Character.toLowerCase(c)).append(Character.toUpperCase(c)).append("]");
/*      */       } else {
/* 2610 */         if (!inQuote) {
/* 2611 */           sb.append("\\Q");
/* 2612 */           inQuote = true;
/*      */         } 
/* 2614 */         sb.append(c);
/*      */       } 
/*      */     } 
/* 2617 */     if (inQuote) {
/* 2618 */       sb.append("\\E");
/*      */     }
/* 2620 */     return sb.toString();
/*      */   }
/*      */   
/*      */   private void pushBackBinding() {
/* 2624 */     pushBackBinding(false);
/*      */   }
/*      */   
/*      */   private void pushBackBinding(boolean skip) {
/* 2628 */     String s = getLastBinding();
/* 2629 */     if (s != null) {
/* 2630 */       this.bindingReader.runMacro(s);
/* 2631 */       this.skipRedisplay = skip;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean historySearchForward() {
/* 2636 */     if (this.historyBuffer == null || this.buf.length() == 0 || 
/* 2637 */       !this.buf.toString().equals(this.history.current())) {
/* 2638 */       this.historyBuffer = this.buf.copy();
/* 2639 */       this.searchBuffer = getFirstWord();
/*      */     } 
/* 2641 */     int index = this.history.index() + 1;
/*      */     
/* 2643 */     if (index < this.history.last() + 1) {
/* 2644 */       int searchIndex = searchForwards(this.searchBuffer.toString(), index, true);
/* 2645 */       if (searchIndex == -1) {
/* 2646 */         this.history.moveToEnd();
/* 2647 */         if (!this.buf.toString().equals(this.historyBuffer.toString())) {
/* 2648 */           setBuffer(this.historyBuffer.toString());
/* 2649 */           this.historyBuffer = null;
/*      */         } else {
/* 2651 */           return false;
/*      */         }
/*      */       
/*      */       }
/* 2655 */       else if (this.history.moveTo(searchIndex)) {
/* 2656 */         setBuffer(this.history.current());
/*      */       } else {
/* 2658 */         this.history.moveToEnd();
/* 2659 */         setBuffer(this.historyBuffer.toString());
/* 2660 */         return false;
/*      */       } 
/*      */     } else {
/*      */       
/* 2664 */       this.history.moveToEnd();
/* 2665 */       if (!this.buf.toString().equals(this.historyBuffer.toString())) {
/* 2666 */         setBuffer(this.historyBuffer.toString());
/* 2667 */         this.historyBuffer = null;
/*      */       } else {
/* 2669 */         return false;
/*      */       } 
/*      */     } 
/* 2672 */     return true;
/*      */   }
/*      */   
/*      */   private CharSequence getFirstWord() {
/* 2676 */     String s = this.buf.toString();
/* 2677 */     int i = 0;
/* 2678 */     while (i < s.length() && !Character.isWhitespace(s.charAt(i))) {
/* 2679 */       i++;
/*      */     }
/* 2681 */     return s.substring(0, i);
/*      */   }
/*      */   
/*      */   protected boolean historySearchBackward() {
/* 2685 */     if (this.historyBuffer == null || this.buf.length() == 0 || 
/* 2686 */       !this.buf.toString().equals(this.history.current())) {
/* 2687 */       this.historyBuffer = this.buf.copy();
/* 2688 */       this.searchBuffer = getFirstWord();
/*      */     } 
/* 2690 */     int searchIndex = searchBackwards(this.searchBuffer.toString(), this.history.index(), true);
/*      */     
/* 2692 */     if (searchIndex == -1) {
/* 2693 */       return false;
/*      */     }
/*      */     
/* 2696 */     if (this.history.moveTo(searchIndex)) {
/* 2697 */       setBuffer(this.history.current());
/*      */     } else {
/* 2699 */       return false;
/*      */     } 
/*      */     
/* 2702 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int searchBackwards(String searchTerm, int startIndex) {
/* 2716 */     return searchBackwards(searchTerm, startIndex, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int searchBackwards(String searchTerm) {
/* 2726 */     return searchBackwards(searchTerm, this.history.index(), false);
/*      */   }
/*      */   
/*      */   public int searchBackwards(String searchTerm, int startIndex, boolean startsWith) {
/* 2730 */     boolean caseInsensitive = isSet(LineReader.Option.CASE_INSENSITIVE_SEARCH);
/* 2731 */     if (caseInsensitive) {
/* 2732 */       searchTerm = searchTerm.toLowerCase();
/*      */     }
/* 2734 */     ListIterator<History.Entry> it = this.history.iterator(startIndex);
/* 2735 */     while (it.hasPrevious()) {
/* 2736 */       History.Entry e = it.previous();
/* 2737 */       String line = e.line();
/* 2738 */       if (caseInsensitive) {
/* 2739 */         line = line.toLowerCase();
/*      */       }
/* 2741 */       int idx = line.indexOf(searchTerm);
/* 2742 */       if ((startsWith && idx == 0) || (!startsWith && idx >= 0)) {
/* 2743 */         return e.index();
/*      */       }
/*      */     } 
/* 2746 */     return -1;
/*      */   }
/*      */   
/*      */   public int searchForwards(String searchTerm, int startIndex, boolean startsWith) {
/* 2750 */     boolean caseInsensitive = isSet(LineReader.Option.CASE_INSENSITIVE_SEARCH);
/* 2751 */     if (caseInsensitive) {
/* 2752 */       searchTerm = searchTerm.toLowerCase();
/*      */     }
/* 2754 */     if (startIndex > this.history.last()) {
/* 2755 */       startIndex = this.history.last();
/*      */     }
/* 2757 */     ListIterator<History.Entry> it = this.history.iterator(startIndex);
/* 2758 */     if (this.searchIndex != -1 && it.hasNext()) {
/* 2759 */       it.next();
/*      */     }
/* 2761 */     while (it.hasNext()) {
/* 2762 */       History.Entry e = it.next();
/* 2763 */       String line = e.line();
/* 2764 */       if (caseInsensitive) {
/* 2765 */         line = line.toLowerCase();
/*      */       }
/* 2767 */       int idx = line.indexOf(searchTerm);
/* 2768 */       if ((startsWith && idx == 0) || (!startsWith && idx >= 0)) {
/* 2769 */         return e.index();
/*      */       }
/*      */     } 
/* 2772 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int searchForwards(String searchTerm, int startIndex) {
/* 2783 */     return searchForwards(searchTerm, startIndex, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int searchForwards(String searchTerm) {
/* 2792 */     return searchForwards(searchTerm, this.history.index());
/*      */   }
/*      */   
/*      */   protected boolean quit() {
/* 2796 */     getBuffer().clear();
/* 2797 */     return acceptLine();
/*      */   }
/*      */   
/*      */   protected boolean acceptAndHold() {
/* 2801 */     this.nextCommandFromHistory = false;
/* 2802 */     acceptLine();
/* 2803 */     if (!this.buf.toString().isEmpty()) {
/* 2804 */       this.nextHistoryId = Integer.MAX_VALUE;
/* 2805 */       this.nextCommandFromHistory = true;
/*      */     } 
/* 2807 */     return this.nextCommandFromHistory;
/*      */   }
/*      */   
/*      */   protected boolean acceptLineAndDownHistory() {
/* 2811 */     this.nextCommandFromHistory = false;
/* 2812 */     acceptLine();
/* 2813 */     if (this.nextHistoryId < 0) {
/* 2814 */       this.nextHistoryId = this.history.index();
/*      */     }
/* 2816 */     if (this.history.size() > this.nextHistoryId + 1) {
/* 2817 */       this.nextHistoryId++;
/* 2818 */       this.nextCommandFromHistory = true;
/*      */     } 
/* 2820 */     return this.nextCommandFromHistory;
/*      */   }
/*      */   
/*      */   protected boolean acceptAndInferNextHistory() {
/* 2824 */     this.nextCommandFromHistory = false;
/* 2825 */     acceptLine();
/* 2826 */     if (!this.buf.toString().isEmpty()) {
/* 2827 */       this.nextHistoryId = searchBackwards(this.buf.toString(), this.history.last());
/* 2828 */       if (this.nextHistoryId >= 0 && this.history.size() > this.nextHistoryId + 1) {
/* 2829 */         this.nextHistoryId++;
/* 2830 */         this.nextCommandFromHistory = true;
/*      */       } 
/*      */     } 
/* 2833 */     return this.nextCommandFromHistory;
/*      */   }
/*      */   
/*      */   protected boolean acceptLine() {
/* 2837 */     this.parsedLine = null;
/* 2838 */     if (!isSet(LineReader.Option.DISABLE_EVENT_EXPANSION)) {
/*      */       try {
/* 2840 */         String str = this.buf.toString();
/* 2841 */         String exp = this.expander.expandHistory(this.history, str);
/* 2842 */         if (!exp.equals(str)) {
/* 2843 */           this.buf.clear();
/* 2844 */           this.buf.write(exp);
/* 2845 */           if (isSet(LineReader.Option.HISTORY_VERIFY)) {
/* 2846 */             return true;
/*      */           }
/*      */         } 
/* 2849 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2854 */       this.parsedLine = this.parser.parse(this.buf.toString(), this.buf.cursor(), Parser.ParseContext.ACCEPT_LINE);
/* 2855 */     } catch (EOFError e) {
/* 2856 */       this.buf.write("\n");
/* 2857 */       return true;
/* 2858 */     } catch (SyntaxError syntaxError) {}
/*      */ 
/*      */     
/* 2861 */     callWidget("callback-finish");
/* 2862 */     this.state = State.DONE;
/* 2863 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean selfInsert() {
/* 2867 */     for (int count = this.count; count > 0; count--) {
/* 2868 */       putString(getLastBinding());
/*      */     }
/* 2870 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean selfInsertUnmeta() {
/* 2874 */     if (getLastBinding().charAt(0) == '\033') {
/* 2875 */       String s = getLastBinding().substring(1);
/* 2876 */       if ("\r".equals(s)) {
/* 2877 */         s = "\n";
/*      */       }
/* 2879 */       for (int count = this.count; count > 0; count--) {
/* 2880 */         putString(s);
/*      */       }
/* 2882 */       return true;
/*      */     } 
/* 2884 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean overwriteMode() {
/* 2889 */     this.overTyping = !this.overTyping;
/* 2890 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean beginningOfBufferOrHistory() {
/* 2899 */     if (findbol() != 0) {
/* 2900 */       this.buf.cursor(0);
/* 2901 */       return true;
/*      */     } 
/* 2903 */     return beginningOfHistory();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean beginningOfHistory() {
/* 2908 */     if (this.history.moveToFirst()) {
/* 2909 */       setBuffer(this.history.current());
/* 2910 */       return true;
/*      */     } 
/* 2912 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean endOfBufferOrHistory() {
/* 2917 */     if (findeol() != this.buf.length()) {
/* 2918 */       this.buf.cursor(this.buf.length());
/* 2919 */       return true;
/*      */     } 
/* 2921 */     return endOfHistory();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean endOfHistory() {
/* 2926 */     if (this.history.moveToLast()) {
/* 2927 */       setBuffer(this.history.current());
/* 2928 */       return true;
/*      */     } 
/* 2930 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean beginningOfLineHist() {
/* 2935 */     if (this.count < 0) {
/* 2936 */       return callNeg(this::endOfLineHist);
/*      */     }
/* 2938 */     while (this.count-- > 0) {
/* 2939 */       int bol = findbol();
/* 2940 */       if (bol != this.buf.cursor()) {
/* 2941 */         this.buf.cursor(bol); continue;
/*      */       } 
/* 2943 */       moveHistory(false);
/* 2944 */       this.buf.cursor(0);
/*      */     } 
/*      */     
/* 2947 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean endOfLineHist() {
/* 2951 */     if (this.count < 0) {
/* 2952 */       return callNeg(this::beginningOfLineHist);
/*      */     }
/* 2954 */     while (this.count-- > 0) {
/* 2955 */       int eol = findeol();
/* 2956 */       if (eol != this.buf.cursor()) {
/* 2957 */         this.buf.cursor(eol); continue;
/*      */       } 
/* 2959 */       moveHistory(true);
/*      */     } 
/*      */     
/* 2962 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean upHistory() {
/* 2966 */     while (this.count-- > 0) {
/* 2967 */       if (!moveHistory(false)) {
/* 2968 */         return !isSet(LineReader.Option.HISTORY_BEEP);
/*      */       }
/*      */     } 
/* 2971 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean downHistory() {
/* 2975 */     while (this.count-- > 0) {
/* 2976 */       if (!moveHistory(true)) {
/* 2977 */         return !isSet(LineReader.Option.HISTORY_BEEP);
/*      */       }
/*      */     } 
/* 2980 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viUpLineOrHistory() {
/* 2984 */     return (upLine() || (
/* 2985 */       upHistory() && viFirstNonBlank()));
/*      */   }
/*      */   
/*      */   protected boolean viDownLineOrHistory() {
/* 2989 */     return (downLine() || (
/* 2990 */       downHistory() && viFirstNonBlank()));
/*      */   }
/*      */   
/*      */   protected boolean upLine() {
/* 2994 */     return this.buf.up();
/*      */   }
/*      */   
/*      */   protected boolean downLine() {
/* 2998 */     return this.buf.down();
/*      */   }
/*      */   
/*      */   protected boolean upLineOrHistory() {
/* 3002 */     return (upLine() || upHistory());
/*      */   }
/*      */   
/*      */   protected boolean upLineOrSearch() {
/* 3006 */     return (upLine() || historySearchBackward());
/*      */   }
/*      */   
/*      */   protected boolean downLineOrHistory() {
/* 3010 */     return (downLine() || downHistory());
/*      */   }
/*      */   
/*      */   protected boolean downLineOrSearch() {
/* 3014 */     return (downLine() || historySearchForward());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean viCmdMode() {
/* 3023 */     if (this.state == State.NORMAL) {
/* 3024 */       this.buf.move(-1);
/*      */     }
/* 3026 */     return setKeyMap("vicmd");
/*      */   }
/*      */   
/*      */   protected boolean viInsert() {
/* 3030 */     return setKeyMap("viins");
/*      */   }
/*      */   
/*      */   protected boolean viAddNext() {
/* 3034 */     this.buf.move(1);
/* 3035 */     return setKeyMap("viins");
/*      */   }
/*      */   
/*      */   protected boolean viAddEol() {
/* 3039 */     return (endOfLine() && setKeyMap("viins"));
/*      */   }
/*      */   
/*      */   protected boolean emacsEditingMode() {
/* 3043 */     return setKeyMap("emacs");
/*      */   }
/*      */   
/*      */   protected boolean viChangeWholeLine() {
/* 3047 */     return (viFirstNonBlank() && viChangeEol());
/*      */   }
/*      */   
/*      */   protected boolean viChangeEol() {
/* 3051 */     return (viChange(this.buf.cursor(), this.buf.length()) && 
/* 3052 */       setKeyMap("viins"));
/*      */   }
/*      */   
/*      */   protected boolean viKillEol() {
/* 3056 */     int eol = findeol();
/* 3057 */     if (this.buf.cursor() == eol) {
/* 3058 */       return false;
/*      */     }
/* 3060 */     this.killRing.add(this.buf.substring(this.buf.cursor(), eol));
/* 3061 */     this.buf.delete(eol - this.buf.cursor());
/* 3062 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean quotedInsert() {
/* 3066 */     int c = readCharacter();
/* 3067 */     while (this.count-- > 0) {
/* 3068 */       putString(new String(Character.toChars(c)));
/*      */     }
/* 3070 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viJoin() {
/* 3074 */     if (this.buf.down()) {
/* 3075 */       while (this.buf.move(-1) == -1 && this.buf.prevChar() != 10);
/* 3076 */       this.buf.backspace();
/* 3077 */       this.buf.write(32);
/* 3078 */       this.buf.move(-1);
/* 3079 */       return true;
/*      */     } 
/* 3081 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean viKillWholeLine() {
/* 3085 */     return (killWholeLine() && setKeyMap("viins"));
/*      */   }
/*      */   
/*      */   protected boolean viInsertBol() {
/* 3089 */     return (beginningOfLine() && setKeyMap("viins"));
/*      */   }
/*      */   
/*      */   protected boolean backwardDeleteChar() {
/* 3093 */     if (this.count < 0) {
/* 3094 */       return callNeg(this::deleteChar);
/*      */     }
/* 3096 */     if (this.buf.cursor() == 0) {
/* 3097 */       return false;
/*      */     }
/* 3099 */     this.buf.backspace(this.count);
/* 3100 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viFirstNonBlank() {
/* 3104 */     beginningOfLine();
/* 3105 */     while (this.buf.cursor() < this.buf.length() && isWhitespace(this.buf.currChar())) {
/* 3106 */       this.buf.move(1);
/*      */     }
/* 3108 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viBeginningOfLine() {
/* 3112 */     this.buf.cursor(findbol());
/* 3113 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viEndOfLine() {
/* 3117 */     if (this.count < 0) {
/* 3118 */       return false;
/*      */     }
/* 3120 */     while (this.count-- > 0) {
/* 3121 */       this.buf.cursor(findeol() + 1);
/*      */     }
/* 3123 */     this.buf.move(-1);
/* 3124 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean beginningOfLine() {
/* 3128 */     while (this.count-- > 0) {
/* 3129 */       while (this.buf.move(-1) == -1 && this.buf.prevChar() != 10);
/*      */     }
/* 3131 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean endOfLine() {
/* 3135 */     while (this.count-- > 0) {
/* 3136 */       while (this.buf.move(1) == 1 && this.buf.currChar() != 10);
/*      */     }
/* 3138 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean deleteChar() {
/* 3142 */     if (this.count < 0) {
/* 3143 */       return callNeg(this::backwardDeleteChar);
/*      */     }
/* 3145 */     if (this.buf.cursor() == this.buf.length()) {
/* 3146 */       return false;
/*      */     }
/* 3148 */     this.buf.delete(this.count);
/* 3149 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean viBackwardDeleteChar() {
/* 3157 */     for (int i = 0; i < this.count; i++) {
/* 3158 */       if (!this.buf.backspace()) {
/* 3159 */         return false;
/*      */       }
/*      */     } 
/* 3162 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean viDeleteChar() {
/* 3171 */     for (int i = 0; i < this.count; i++) {
/* 3172 */       if (!this.buf.delete()) {
/* 3173 */         return false;
/*      */       }
/*      */     } 
/* 3176 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean viSwapCase() {
/* 3186 */     for (int i = 0; i < this.count; i++) {
/* 3187 */       if (this.buf.cursor() < this.buf.length()) {
/* 3188 */         int ch = this.buf.atChar(this.buf.cursor());
/* 3189 */         ch = switchCase(ch);
/* 3190 */         this.buf.currChar(ch);
/* 3191 */         this.buf.move(1);
/*      */       } else {
/* 3193 */         return false;
/*      */       } 
/*      */     } 
/* 3196 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean viReplaceChars() {
/* 3205 */     int c = readCharacter();
/*      */     
/* 3207 */     if (c < 0 || c == 27 || c == 3) {
/* 3208 */       return true;
/*      */     }
/*      */     
/* 3211 */     for (int i = 0; i < this.count; i++) {
/* 3212 */       if (this.buf.currChar((char)c)) {
/* 3213 */         if (i < this.count - 1) {
/* 3214 */           this.buf.move(1);
/*      */         }
/*      */       } else {
/* 3217 */         return false;
/*      */       } 
/*      */     } 
/* 3220 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viChange(int startPos, int endPos) {
/* 3224 */     return doViDeleteOrChange(startPos, endPos, true);
/*      */   }
/*      */   
/*      */   protected boolean viDeleteTo(int startPos, int endPos) {
/* 3228 */     return doViDeleteOrChange(startPos, endPos, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean doViDeleteOrChange(int startPos, int endPos, boolean isChange) {
/* 3242 */     if (startPos == endPos) {
/* 3243 */       return true;
/*      */     }
/*      */     
/* 3246 */     if (endPos < startPos) {
/* 3247 */       int tmp = endPos;
/* 3248 */       endPos = startPos;
/* 3249 */       startPos = tmp;
/*      */     } 
/*      */     
/* 3252 */     this.buf.cursor(startPos);
/* 3253 */     this.buf.delete(endPos - startPos);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3260 */     if (!isChange && startPos > 0 && startPos == this.buf.length()) {
/* 3261 */       this.buf.move(-1);
/*      */     }
/* 3263 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean viYankTo(int startPos, int endPos) {
/* 3276 */     int cursorPos = startPos;
/*      */     
/* 3278 */     if (endPos < startPos) {
/* 3279 */       int tmp = endPos;
/* 3280 */       endPos = startPos;
/* 3281 */       startPos = tmp;
/*      */     } 
/*      */     
/* 3284 */     if (startPos == endPos) {
/* 3285 */       this.yankBuffer = "";
/* 3286 */       return true;
/*      */     } 
/*      */     
/* 3289 */     this.yankBuffer = this.buf.substring(startPos, endPos);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3295 */     this.buf.cursor(cursorPos);
/* 3296 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viOpenLineAbove() {
/* 3300 */     while (this.buf.move(-1) == -1 && this.buf.prevChar() != 10);
/* 3301 */     this.buf.write(10);
/* 3302 */     this.buf.move(-1);
/* 3303 */     return setKeyMap("viins");
/*      */   }
/*      */   
/*      */   protected boolean viOpenLineBelow() {
/* 3307 */     while (this.buf.move(1) == 1 && this.buf.currChar() != 10);
/* 3308 */     this.buf.write(10);
/* 3309 */     return setKeyMap("viins");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean viPutAfter() {
/* 3318 */     if (this.yankBuffer.indexOf('\n') >= 0) {
/* 3319 */       while (this.buf.move(1) == 1 && this.buf.currChar() != 10);
/* 3320 */       this.buf.move(1);
/* 3321 */       putString(this.yankBuffer);
/* 3322 */       this.buf.move(-this.yankBuffer.length());
/* 3323 */     } else if (this.yankBuffer.length() != 0) {
/* 3324 */       if (this.buf.cursor() < this.buf.length()) {
/* 3325 */         this.buf.move(1);
/*      */       }
/* 3327 */       for (int i = 0; i < this.count; i++) {
/* 3328 */         putString(this.yankBuffer);
/*      */       }
/* 3330 */       this.buf.move(-1);
/*      */     } 
/* 3332 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean viPutBefore() {
/* 3336 */     if (this.yankBuffer.indexOf('\n') >= 0) {
/* 3337 */       while (this.buf.move(-1) == -1 && this.buf.prevChar() != 10);
/* 3338 */       putString(this.yankBuffer);
/* 3339 */       this.buf.move(-this.yankBuffer.length());
/* 3340 */     } else if (this.yankBuffer.length() != 0) {
/* 3341 */       if (this.buf.cursor() > 0) {
/* 3342 */         this.buf.move(-1);
/*      */       }
/* 3344 */       for (int i = 0; i < this.count; i++) {
/* 3345 */         putString(this.yankBuffer);
/*      */       }
/* 3347 */       this.buf.move(-1);
/*      */     } 
/* 3349 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean doLowercaseVersion() {
/* 3353 */     this.bindingReader.runMacro(getLastBinding().toLowerCase());
/* 3354 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean setMarkCommand() {
/* 3358 */     if (this.count < 0) {
/* 3359 */       this.regionActive = LineReader.RegionType.NONE;
/* 3360 */       return true;
/*      */     } 
/* 3362 */     this.regionMark = this.buf.cursor();
/* 3363 */     this.regionActive = LineReader.RegionType.CHAR;
/* 3364 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean exchangePointAndMark() {
/* 3368 */     if (this.count == 0) {
/* 3369 */       this.regionActive = LineReader.RegionType.CHAR;
/* 3370 */       return true;
/*      */     } 
/* 3372 */     int x = this.regionMark;
/* 3373 */     this.regionMark = this.buf.cursor();
/* 3374 */     this.buf.cursor(x);
/* 3375 */     if (this.buf.cursor() > this.buf.length()) {
/* 3376 */       this.buf.cursor(this.buf.length());
/*      */     }
/* 3378 */     if (this.count > 0) {
/* 3379 */       this.regionActive = LineReader.RegionType.CHAR;
/*      */     }
/* 3381 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean visualMode() {
/* 3385 */     if (isInViMoveOperation()) {
/* 3386 */       this.isArgDigit = true;
/* 3387 */       this.forceLine = false;
/* 3388 */       this.forceChar = true;
/* 3389 */       return true;
/*      */     } 
/* 3391 */     if (this.regionActive == LineReader.RegionType.NONE) {
/* 3392 */       this.regionMark = this.buf.cursor();
/* 3393 */       this.regionActive = LineReader.RegionType.CHAR;
/* 3394 */     } else if (this.regionActive == LineReader.RegionType.CHAR) {
/* 3395 */       this.regionActive = LineReader.RegionType.NONE;
/* 3396 */     } else if (this.regionActive == LineReader.RegionType.LINE) {
/* 3397 */       this.regionActive = LineReader.RegionType.CHAR;
/*      */     } 
/* 3399 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean visualLineMode() {
/* 3403 */     if (isInViMoveOperation()) {
/* 3404 */       this.isArgDigit = true;
/* 3405 */       this.forceLine = true;
/* 3406 */       this.forceChar = false;
/* 3407 */       return true;
/*      */     } 
/* 3409 */     if (this.regionActive == LineReader.RegionType.NONE) {
/* 3410 */       this.regionMark = this.buf.cursor();
/* 3411 */       this.regionActive = LineReader.RegionType.LINE;
/* 3412 */     } else if (this.regionActive == LineReader.RegionType.CHAR) {
/* 3413 */       this.regionActive = LineReader.RegionType.LINE;
/* 3414 */     } else if (this.regionActive == LineReader.RegionType.LINE) {
/* 3415 */       this.regionActive = LineReader.RegionType.NONE;
/*      */     } 
/* 3417 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean deactivateRegion() {
/* 3421 */     this.regionActive = LineReader.RegionType.NONE;
/* 3422 */     return true;
/*      */   }
/*      */   
/*      */   protected boolean whatCursorPosition() {
/* 3426 */     this.post = (() -> {
/*      */         AttributedStringBuilder sb = new AttributedStringBuilder();
/*      */         if (this.buf.cursor() < this.buf.length()) {
/*      */           int c = this.buf.currChar();
/*      */           sb.append("Char: ");
/*      */           if (c == 32) {
/*      */             sb.append("SPC");
/*      */           } else if (c == 10) {
/*      */             sb.append("LFD");
/*      */           } else if (c < 32) {
/*      */             sb.append('^');
/*      */             sb.append((char)(c + 65 - 1));
/*      */           } else if (c == 127) {
/*      */             sb.append("^?");
/*      */           } else {
/*      */             sb.append((char)c);
/*      */           } 
/*      */           sb.append(" (");
/*      */           sb.append("0").append(Integer.toOctalString(c)).append(" ");
/*      */           sb.append(Integer.toString(c)).append(" ");
/*      */           sb.append("0x").append(Integer.toHexString(c)).append(" ");
/*      */           sb.append(")");
/*      */         } else {
/*      */           sb.append("EOF");
/*      */         } 
/*      */         sb.append("   ");
/*      */         sb.append("point ");
/*      */         sb.append(Integer.toString(this.buf.cursor() + 1));
/*      */         sb.append(" of ");
/*      */         sb.append(Integer.toString(this.buf.length() + 1));
/*      */         sb.append(" (");
/*      */         sb.append(Integer.toString((this.buf.length() == 0) ? 100 : (100 * this.buf.cursor() / this.buf.length())));
/*      */         sb.append("%)");
/*      */         sb.append("   ");
/*      */         sb.append("column ");
/*      */         sb.append(Integer.toString(this.buf.cursor() - findbol()));
/*      */         return sb.toAttributedString();
/*      */       });
/* 3464 */     return true;
/*      */   }
/*      */   
/*      */   protected Map<String, Widget> builtinWidgets() {
/* 3468 */     Map<String, Widget> widgets = new HashMap<>();
/* 3469 */     addBuiltinWidget(widgets, "accept-and-infer-next-history", this::acceptAndInferNextHistory);
/* 3470 */     addBuiltinWidget(widgets, "accept-and-hold", this::acceptAndHold);
/* 3471 */     addBuiltinWidget(widgets, "accept-line", this::acceptLine);
/* 3472 */     addBuiltinWidget(widgets, "accept-line-and-down-history", this::acceptLineAndDownHistory);
/* 3473 */     addBuiltinWidget(widgets, "argument-base", this::argumentBase);
/* 3474 */     addBuiltinWidget(widgets, "backward-char", this::backwardChar);
/* 3475 */     addBuiltinWidget(widgets, "backward-delete-char", this::backwardDeleteChar);
/* 3476 */     addBuiltinWidget(widgets, "backward-delete-word", this::backwardDeleteWord);
/* 3477 */     addBuiltinWidget(widgets, "backward-kill-line", this::backwardKillLine);
/* 3478 */     addBuiltinWidget(widgets, "backward-kill-word", this::backwardKillWord);
/* 3479 */     addBuiltinWidget(widgets, "backward-word", this::backwardWord);
/* 3480 */     addBuiltinWidget(widgets, "beep", this::beep);
/* 3481 */     addBuiltinWidget(widgets, "beginning-of-buffer-or-history", this::beginningOfBufferOrHistory);
/* 3482 */     addBuiltinWidget(widgets, "beginning-of-history", this::beginningOfHistory);
/* 3483 */     addBuiltinWidget(widgets, "beginning-of-line", this::beginningOfLine);
/* 3484 */     addBuiltinWidget(widgets, "beginning-of-line-hist", this::beginningOfLineHist);
/* 3485 */     addBuiltinWidget(widgets, "capitalize-word", this::capitalizeWord);
/* 3486 */     addBuiltinWidget(widgets, "clear", this::clear);
/* 3487 */     addBuiltinWidget(widgets, "clear-screen", this::clearScreen);
/* 3488 */     addBuiltinWidget(widgets, "complete-prefix", this::completePrefix);
/* 3489 */     addBuiltinWidget(widgets, "complete-word", this::completeWord);
/* 3490 */     addBuiltinWidget(widgets, "copy-prev-word", this::copyPrevWord);
/* 3491 */     addBuiltinWidget(widgets, "copy-region-as-kill", this::copyRegionAsKill);
/* 3492 */     addBuiltinWidget(widgets, "delete-char", this::deleteChar);
/* 3493 */     addBuiltinWidget(widgets, "delete-char-or-list", this::deleteCharOrList);
/* 3494 */     addBuiltinWidget(widgets, "delete-word", this::deleteWord);
/* 3495 */     addBuiltinWidget(widgets, "digit-argument", this::digitArgument);
/* 3496 */     addBuiltinWidget(widgets, "do-lowercase-version", this::doLowercaseVersion);
/* 3497 */     addBuiltinWidget(widgets, "down-case-word", this::downCaseWord);
/* 3498 */     addBuiltinWidget(widgets, "down-line", this::downLine);
/* 3499 */     addBuiltinWidget(widgets, "down-line-or-history", this::downLineOrHistory);
/* 3500 */     addBuiltinWidget(widgets, "down-line-or-search", this::downLineOrSearch);
/* 3501 */     addBuiltinWidget(widgets, "down-history", this::downHistory);
/* 3502 */     addBuiltinWidget(widgets, "emacs-editing-mode", this::emacsEditingMode);
/* 3503 */     addBuiltinWidget(widgets, "emacs-backward-word", this::emacsBackwardWord);
/* 3504 */     addBuiltinWidget(widgets, "emacs-forward-word", this::emacsForwardWord);
/* 3505 */     addBuiltinWidget(widgets, "end-of-buffer-or-history", this::endOfBufferOrHistory);
/* 3506 */     addBuiltinWidget(widgets, "end-of-history", this::endOfHistory);
/* 3507 */     addBuiltinWidget(widgets, "end-of-line", this::endOfLine);
/* 3508 */     addBuiltinWidget(widgets, "end-of-line-hist", this::endOfLineHist);
/* 3509 */     addBuiltinWidget(widgets, "exchange-point-and-mark", this::exchangePointAndMark);
/* 3510 */     addBuiltinWidget(widgets, "expand-history", this::expandHistory);
/* 3511 */     addBuiltinWidget(widgets, "expand-or-complete", this::expandOrComplete);
/* 3512 */     addBuiltinWidget(widgets, "expand-or-complete-prefix", this::expandOrCompletePrefix);
/* 3513 */     addBuiltinWidget(widgets, "expand-word", this::expandWord);
/* 3514 */     addBuiltinWidget(widgets, "fresh-line", this::freshLine);
/* 3515 */     addBuiltinWidget(widgets, "forward-char", this::forwardChar);
/* 3516 */     addBuiltinWidget(widgets, "forward-word", this::forwardWord);
/* 3517 */     addBuiltinWidget(widgets, "history-incremental-search-backward", this::historyIncrementalSearchBackward);
/* 3518 */     addBuiltinWidget(widgets, "history-incremental-search-forward", this::historyIncrementalSearchForward);
/* 3519 */     addBuiltinWidget(widgets, "history-search-backward", this::historySearchBackward);
/* 3520 */     addBuiltinWidget(widgets, "history-search-forward", this::historySearchForward);
/* 3521 */     addBuiltinWidget(widgets, "insert-close-curly", this::insertCloseCurly);
/* 3522 */     addBuiltinWidget(widgets, "insert-close-paren", this::insertCloseParen);
/* 3523 */     addBuiltinWidget(widgets, "insert-close-square", this::insertCloseSquare);
/* 3524 */     addBuiltinWidget(widgets, "insert-comment", this::insertComment);
/* 3525 */     addBuiltinWidget(widgets, "kill-buffer", this::killBuffer);
/* 3526 */     addBuiltinWidget(widgets, "kill-line", this::killLine);
/* 3527 */     addBuiltinWidget(widgets, "kill-region", this::killRegion);
/* 3528 */     addBuiltinWidget(widgets, "kill-whole-line", this::killWholeLine);
/* 3529 */     addBuiltinWidget(widgets, "kill-word", this::killWord);
/* 3530 */     addBuiltinWidget(widgets, "list-choices", this::listChoices);
/* 3531 */     addBuiltinWidget(widgets, "menu-complete", this::menuComplete);
/* 3532 */     addBuiltinWidget(widgets, "menu-expand-or-complete", this::menuExpandOrComplete);
/* 3533 */     addBuiltinWidget(widgets, "neg-argument", this::negArgument);
/* 3534 */     addBuiltinWidget(widgets, "overwrite-mode", this::overwriteMode);
/*      */     
/* 3536 */     addBuiltinWidget(widgets, "quoted-insert", this::quotedInsert);
/* 3537 */     addBuiltinWidget(widgets, "redisplay", this::redisplay);
/* 3538 */     addBuiltinWidget(widgets, "redraw-line", this::redrawLine);
/* 3539 */     addBuiltinWidget(widgets, "redo", this::redo);
/* 3540 */     addBuiltinWidget(widgets, "self-insert", this::selfInsert);
/* 3541 */     addBuiltinWidget(widgets, "self-insert-unmeta", this::selfInsertUnmeta);
/* 3542 */     addBuiltinWidget(widgets, "abort", this::sendBreak);
/* 3543 */     addBuiltinWidget(widgets, "set-mark-command", this::setMarkCommand);
/* 3544 */     addBuiltinWidget(widgets, "transpose-chars", this::transposeChars);
/* 3545 */     addBuiltinWidget(widgets, "transpose-words", this::transposeWords);
/* 3546 */     addBuiltinWidget(widgets, "undefined-key", this::undefinedKey);
/* 3547 */     addBuiltinWidget(widgets, "universal-argument", this::universalArgument);
/* 3548 */     addBuiltinWidget(widgets, "undo", this::undo);
/* 3549 */     addBuiltinWidget(widgets, "up-case-word", this::upCaseWord);
/* 3550 */     addBuiltinWidget(widgets, "up-history", this::upHistory);
/* 3551 */     addBuiltinWidget(widgets, "up-line", this::upLine);
/* 3552 */     addBuiltinWidget(widgets, "up-line-or-history", this::upLineOrHistory);
/* 3553 */     addBuiltinWidget(widgets, "up-line-or-search", this::upLineOrSearch);
/* 3554 */     addBuiltinWidget(widgets, "vi-add-eol", this::viAddEol);
/* 3555 */     addBuiltinWidget(widgets, "vi-add-next", this::viAddNext);
/* 3556 */     addBuiltinWidget(widgets, "vi-backward-char", this::viBackwardChar);
/* 3557 */     addBuiltinWidget(widgets, "vi-backward-delete-char", this::viBackwardDeleteChar);
/* 3558 */     addBuiltinWidget(widgets, "vi-backward-blank-word", this::viBackwardBlankWord);
/* 3559 */     addBuiltinWidget(widgets, "vi-backward-blank-word-end", this::viBackwardBlankWordEnd);
/* 3560 */     addBuiltinWidget(widgets, "vi-backward-kill-word", this::viBackwardKillWord);
/* 3561 */     addBuiltinWidget(widgets, "vi-backward-word", this::viBackwardWord);
/* 3562 */     addBuiltinWidget(widgets, "vi-backward-word-end", this::viBackwardWordEnd);
/* 3563 */     addBuiltinWidget(widgets, "vi-beginning-of-line", this::viBeginningOfLine);
/* 3564 */     addBuiltinWidget(widgets, "vi-cmd-mode", this::viCmdMode);
/* 3565 */     addBuiltinWidget(widgets, "vi-digit-or-beginning-of-line", this::viDigitOrBeginningOfLine);
/* 3566 */     addBuiltinWidget(widgets, "vi-down-line-or-history", this::viDownLineOrHistory);
/* 3567 */     addBuiltinWidget(widgets, "vi-change-to", this::viChange);
/* 3568 */     addBuiltinWidget(widgets, "vi-change-eol", this::viChangeEol);
/* 3569 */     addBuiltinWidget(widgets, "vi-change-whole-line", this::viChangeWholeLine);
/* 3570 */     addBuiltinWidget(widgets, "vi-delete-char", this::viDeleteChar);
/* 3571 */     addBuiltinWidget(widgets, "vi-delete", this::viDelete);
/* 3572 */     addBuiltinWidget(widgets, "vi-end-of-line", this::viEndOfLine);
/* 3573 */     addBuiltinWidget(widgets, "vi-kill-eol", this::viKillEol);
/* 3574 */     addBuiltinWidget(widgets, "vi-first-non-blank", this::viFirstNonBlank);
/* 3575 */     addBuiltinWidget(widgets, "vi-find-next-char", this::viFindNextChar);
/* 3576 */     addBuiltinWidget(widgets, "vi-find-next-char-skip", this::viFindNextCharSkip);
/* 3577 */     addBuiltinWidget(widgets, "vi-find-prev-char", this::viFindPrevChar);
/* 3578 */     addBuiltinWidget(widgets, "vi-find-prev-char-skip", this::viFindPrevCharSkip);
/* 3579 */     addBuiltinWidget(widgets, "vi-forward-blank-word", this::viForwardBlankWord);
/* 3580 */     addBuiltinWidget(widgets, "vi-forward-blank-word-end", this::viForwardBlankWordEnd);
/* 3581 */     addBuiltinWidget(widgets, "vi-forward-char", this::viForwardChar);
/* 3582 */     addBuiltinWidget(widgets, "vi-forward-word", this::viForwardWord);
/* 3583 */     addBuiltinWidget(widgets, "vi-forward-word", this::viForwardWord);
/* 3584 */     addBuiltinWidget(widgets, "vi-forward-word-end", this::viForwardWordEnd);
/* 3585 */     addBuiltinWidget(widgets, "vi-history-search-backward", this::viHistorySearchBackward);
/* 3586 */     addBuiltinWidget(widgets, "vi-history-search-forward", this::viHistorySearchForward);
/* 3587 */     addBuiltinWidget(widgets, "vi-insert", this::viInsert);
/* 3588 */     addBuiltinWidget(widgets, "vi-insert-bol", this::viInsertBol);
/* 3589 */     addBuiltinWidget(widgets, "vi-insert-comment", this::viInsertComment);
/* 3590 */     addBuiltinWidget(widgets, "vi-join", this::viJoin);
/* 3591 */     addBuiltinWidget(widgets, "vi-kill-line", this::viKillWholeLine);
/* 3592 */     addBuiltinWidget(widgets, "vi-match-bracket", this::viMatchBracket);
/* 3593 */     addBuiltinWidget(widgets, "vi-open-line-above", this::viOpenLineAbove);
/* 3594 */     addBuiltinWidget(widgets, "vi-open-line-below", this::viOpenLineBelow);
/* 3595 */     addBuiltinWidget(widgets, "vi-put-after", this::viPutAfter);
/* 3596 */     addBuiltinWidget(widgets, "vi-put-before", this::viPutBefore);
/* 3597 */     addBuiltinWidget(widgets, "vi-repeat-find", this::viRepeatFind);
/* 3598 */     addBuiltinWidget(widgets, "vi-repeat-search", this::viRepeatSearch);
/* 3599 */     addBuiltinWidget(widgets, "vi-replace-chars", this::viReplaceChars);
/* 3600 */     addBuiltinWidget(widgets, "vi-rev-repeat-find", this::viRevRepeatFind);
/* 3601 */     addBuiltinWidget(widgets, "vi-rev-repeat-search", this::viRevRepeatSearch);
/* 3602 */     addBuiltinWidget(widgets, "vi-swap-case", this::viSwapCase);
/* 3603 */     addBuiltinWidget(widgets, "vi-up-line-or-history", this::viUpLineOrHistory);
/* 3604 */     addBuiltinWidget(widgets, "vi-yank", this::viYankTo);
/* 3605 */     addBuiltinWidget(widgets, "vi-yank-whole-line", this::viYankWholeLine);
/* 3606 */     addBuiltinWidget(widgets, "visual-line-mode", this::visualLineMode);
/* 3607 */     addBuiltinWidget(widgets, "visual-mode", this::visualMode);
/* 3608 */     addBuiltinWidget(widgets, "what-cursor-position", this::whatCursorPosition);
/* 3609 */     addBuiltinWidget(widgets, "yank", this::yank);
/* 3610 */     addBuiltinWidget(widgets, "yank-pop", this::yankPop);
/* 3611 */     addBuiltinWidget(widgets, "mouse", this::mouse);
/* 3612 */     addBuiltinWidget(widgets, "begin-paste", this::beginPaste);
/* 3613 */     addBuiltinWidget(widgets, "terminal-focus-in", this::focusIn);
/* 3614 */     addBuiltinWidget(widgets, "terminal-focus-out", this::focusOut);
/* 3615 */     return widgets;
/*      */   }
/*      */   
/*      */   private void addBuiltinWidget(Map<String, Widget> widgets, String name, Widget widget) {
/* 3619 */     widgets.put(name, namedWidget(name, widget));
/*      */   }
/*      */   
/*      */   private Widget namedWidget(final String name, final Widget widget) {
/* 3623 */     return new Widget()
/*      */       {
/*      */         public String toString() {
/* 3626 */           return name;
/*      */         }
/*      */         
/*      */         public boolean apply() {
/* 3630 */           return widget.apply();
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public boolean redisplay() {
/* 3636 */     redisplay(true);
/* 3637 */     return true;
/*      */   }
/*      */   protected void redisplay(boolean flush) {
/*      */     try {
/*      */       List<AttributedString> newLines, rightPromptLines;
/* 3642 */       this.lock.lock();
/*      */       
/* 3644 */       if (this.skipRedisplay) {
/* 3645 */         this.skipRedisplay = false;
/*      */         
/*      */         return;
/*      */       } 
/* 3649 */       Status status = Status.getStatus(this.terminal, false);
/* 3650 */       if (status != null) {
/* 3651 */         status.redraw();
/*      */       }
/*      */       
/* 3654 */       if (this.size.getRows() > 0 && this.size.getRows() < 3) {
/* 3655 */         AttributedStringBuilder sb = (new AttributedStringBuilder()).tabs(4);
/*      */         
/* 3657 */         sb.append(this.prompt);
/* 3658 */         concat(getHighlightedBuffer(this.buf.toString()).columnSplitLength(2147483647), sb);
/* 3659 */         AttributedString attributedString1 = sb.toAttributedString();
/*      */         
/* 3661 */         sb.setLength(0);
/* 3662 */         sb.append(this.prompt);
/* 3663 */         String line = this.buf.upToCursor();
/* 3664 */         if (this.maskingCallback != null) {
/* 3665 */           line = this.maskingCallback.display(line);
/*      */         }
/*      */         
/* 3668 */         concat((new AttributedString(line)).columnSplitLength(2147483647), sb);
/* 3669 */         AttributedString toCursor = sb.toAttributedString();
/*      */         
/* 3671 */         int w = WCWidth.wcwidth(8230);
/* 3672 */         int width = this.size.getColumns();
/* 3673 */         int cursor = toCursor.columnLength();
/* 3674 */         int inc = width / 2 + 1;
/* 3675 */         while (cursor <= this.smallTerminalOffset + w) {
/* 3676 */           this.smallTerminalOffset -= inc;
/*      */         }
/* 3678 */         while (cursor >= this.smallTerminalOffset + width - w) {
/* 3679 */           this.smallTerminalOffset += inc;
/*      */         }
/* 3681 */         if (this.smallTerminalOffset > 0) {
/* 3682 */           sb.setLength(0);
/* 3683 */           sb.append("…");
/* 3684 */           sb.append(attributedString1.columnSubSequence(this.smallTerminalOffset + w, 2147483647));
/* 3685 */           attributedString1 = sb.toAttributedString();
/*      */         } 
/* 3687 */         int length = attributedString1.columnLength();
/* 3688 */         if (length >= this.smallTerminalOffset + width) {
/* 3689 */           sb.setLength(0);
/* 3690 */           sb.append(attributedString1.columnSubSequence(0, width - w));
/* 3691 */           sb.append("…");
/* 3692 */           attributedString1 = sb.toAttributedString();
/*      */         } 
/*      */         
/* 3695 */         this.display.update(Collections.singletonList(attributedString1), cursor - this.smallTerminalOffset, flush);
/*      */         
/*      */         return;
/*      */       } 
/* 3699 */       List<AttributedString> secondaryPrompts = new ArrayList<>();
/* 3700 */       AttributedString full = getDisplayedBufferWithPrompts(secondaryPrompts);
/*      */ 
/*      */       
/* 3703 */       if (this.size.getColumns() <= 0) {
/* 3704 */         newLines = new ArrayList<>();
/* 3705 */         newLines.add(full);
/*      */       } else {
/* 3707 */         newLines = full.columnSplitLength(this.size.getColumns(), true, this.display.delayLineWrap());
/*      */       } 
/*      */ 
/*      */       
/* 3711 */       if (this.rightPrompt.length() == 0 || this.size.getColumns() <= 0) {
/* 3712 */         rightPromptLines = new ArrayList<>();
/*      */       } else {
/* 3714 */         rightPromptLines = this.rightPrompt.columnSplitLength(this.size.getColumns());
/*      */       } 
/* 3716 */       while (newLines.size() < rightPromptLines.size()) {
/* 3717 */         newLines.add(new AttributedString(""));
/*      */       }
/* 3719 */       for (int i = 0; i < rightPromptLines.size(); i++) {
/* 3720 */         AttributedString line = rightPromptLines.get(i);
/* 3721 */         newLines.set(i, addRightPrompt(line, newLines.get(i)));
/*      */       } 
/*      */       
/* 3724 */       int cursorPos = -1;
/* 3725 */       int cursorNewLinesId = -1;
/* 3726 */       int cursorColPos = -1;
/* 3727 */       if (this.size.getColumns() > 0) {
/* 3728 */         AttributedStringBuilder sb = (new AttributedStringBuilder()).tabs(4);
/* 3729 */         sb.append(this.prompt);
/* 3730 */         String buffer = this.buf.upToCursor();
/* 3731 */         if (this.maskingCallback != null) {
/* 3732 */           buffer = this.maskingCallback.display(buffer);
/*      */         }
/* 3734 */         sb.append(insertSecondaryPrompts(new AttributedString(buffer), secondaryPrompts, false));
/* 3735 */         List<AttributedString> promptLines = sb.columnSplitLength(this.size.getColumns(), false, this.display.delayLineWrap());
/* 3736 */         if (!promptLines.isEmpty()) {
/* 3737 */           cursorNewLinesId = promptLines.size() - 1;
/* 3738 */           cursorColPos = ((AttributedString)promptLines.get(promptLines.size() - 1)).columnLength();
/* 3739 */           cursorPos = this.size.cursorPos(cursorNewLinesId, cursorColPos);
/*      */         } 
/*      */       } 
/*      */       
/* 3743 */       List<AttributedString> newLinesToDisplay = new ArrayList<>();
/* 3744 */       int displaySize = this.size.getRows() - ((status != null) ? status.size() : 0);
/* 3745 */       if (newLines.size() > displaySize && !isTerminalDumb()) {
/* 3746 */         StringBuilder sb = new StringBuilder(">....");
/*      */         
/* 3748 */         for (int j = sb.toString().length(); j < this.size.getColumns(); j++) {
/* 3749 */           sb.append(" ");
/*      */         }
/* 3751 */         AttributedString partialCommandInfo = new AttributedString(sb.toString());
/* 3752 */         int lineId = newLines.size() - displaySize + 1;
/* 3753 */         int endId = displaySize;
/* 3754 */         int startId = 1;
/* 3755 */         if (lineId > cursorNewLinesId) {
/* 3756 */           lineId = cursorNewLinesId;
/* 3757 */           endId = displaySize - 1;
/* 3758 */           startId = 0;
/*      */         } else {
/* 3760 */           newLinesToDisplay.add(partialCommandInfo);
/*      */         } 
/* 3762 */         int cursorRowPos = 0;
/* 3763 */         for (int k = startId; k < endId; k++) {
/* 3764 */           if (cursorNewLinesId == lineId) {
/* 3765 */             cursorRowPos = k;
/*      */           }
/* 3767 */           newLinesToDisplay.add(newLines.get(lineId++));
/*      */         } 
/* 3769 */         if (startId == 0) {
/* 3770 */           newLinesToDisplay.add(partialCommandInfo);
/*      */         }
/* 3772 */         cursorPos = this.size.cursorPos(cursorRowPos, cursorColPos);
/*      */       } else {
/* 3774 */         newLinesToDisplay = newLines;
/*      */       } 
/* 3776 */       this.display.update(newLinesToDisplay, cursorPos, flush);
/*      */     } finally {
/* 3778 */       this.lock.unlock();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void concat(List<AttributedString> lines, AttributedStringBuilder sb) {
/* 3783 */     if (lines.size() > 1) {
/* 3784 */       for (int i = 0; i < lines.size() - 1; i++) {
/* 3785 */         sb.append(lines.get(i));
/* 3786 */         sb.style(sb.style().inverse());
/* 3787 */         sb.append("\\n");
/* 3788 */         sb.style(sb.style().inverseOff());
/*      */       } 
/*      */     }
/* 3791 */     sb.append(lines.get(lines.size() - 1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AttributedString getDisplayedBufferWithPrompts(List<AttributedString> secondaryPrompts) {
/* 3800 */     AttributedString attBuf = getHighlightedBuffer(this.buf.toString());
/*      */     
/* 3802 */     AttributedString tNewBuf = insertSecondaryPrompts(attBuf, secondaryPrompts);
/* 3803 */     AttributedStringBuilder full = (new AttributedStringBuilder()).tabs(4);
/* 3804 */     full.append(this.prompt);
/* 3805 */     full.append(tNewBuf);
/* 3806 */     if (this.post != null) {
/* 3807 */       full.append("\n");
/* 3808 */       full.append(this.post.get());
/*      */     } 
/* 3810 */     return full.toAttributedString();
/*      */   }
/*      */   
/*      */   private AttributedString getHighlightedBuffer(String buffer) {
/* 3814 */     if (this.maskingCallback != null) {
/* 3815 */       buffer = this.maskingCallback.display(buffer);
/*      */     }
/* 3817 */     if (this.highlighter != null && !isSet(LineReader.Option.DISABLE_HIGHLIGHTER)) {
/* 3818 */       return this.highlighter.highlight(this, buffer);
/*      */     }
/* 3820 */     return new AttributedString(buffer);
/*      */   }
/*      */ 
/*      */   
/*      */   private AttributedString expandPromptPattern(String pattern, int padToWidth, String message, int line) {
/* 3825 */     ArrayList<AttributedString> parts = new ArrayList<>();
/* 3826 */     boolean isHidden = false;
/* 3827 */     int padPartIndex = -1;
/* 3828 */     StringBuilder padPartString = null;
/* 3829 */     StringBuilder sb = new StringBuilder();
/*      */     
/* 3831 */     pattern = pattern + "%{";
/* 3832 */     int plen = pattern.length();
/* 3833 */     int padChar = -1;
/* 3834 */     int padPos = -1;
/* 3835 */     int cols = 0; int i;
/* 3836 */     label75: for (i = 0; i < plen; ) {
/* 3837 */       char ch = pattern.charAt(i++);
/* 3838 */       if (ch == '%' && i < plen) {
/* 3839 */         int count = 0;
/* 3840 */         boolean countSeen = false; while (true) {
/*      */           String str; AttributedString astr; boolean neg;
/* 3842 */           ch = pattern.charAt(i++);
/* 3843 */           switch (ch) {
/*      */             case '{':
/*      */             case '}':
/* 3846 */               str = sb.toString();
/*      */               
/* 3848 */               if (!isHidden) {
/* 3849 */                 astr = AttributedString.fromAnsi(str);
/* 3850 */                 cols += astr.columnLength();
/*      */               } else {
/* 3852 */                 astr = new AttributedString(str, AttributedStyle.HIDDEN);
/*      */               } 
/* 3854 */               if (padPartIndex == parts.size()) {
/* 3855 */                 padPartString = sb;
/* 3856 */                 if (i < plen) {
/* 3857 */                   sb = new StringBuilder();
/*      */                 }
/*      */               } else {
/* 3860 */                 sb.setLength(0);
/*      */               } 
/* 3862 */               parts.add(astr);
/* 3863 */               isHidden = (ch == '{');
/*      */               continue label75;
/*      */             case '%':
/* 3866 */               sb.append(ch);
/*      */               continue label75;
/*      */             case 'N':
/* 3869 */               sb.append(getInt("line-offset", 0) + line);
/*      */               continue label75;
/*      */             case 'M':
/* 3872 */               if (message != null) {
/* 3873 */                 sb.append(message); continue label75;
/*      */               }  continue label75;
/*      */             case 'P':
/* 3876 */               if (countSeen && count >= 0)
/* 3877 */                 padToWidth = count; 
/* 3878 */               if (i < plen) {
/* 3879 */                 padChar = pattern.charAt(i++);
/*      */               }
/*      */               
/* 3882 */               padPos = sb.length();
/* 3883 */               padPartIndex = parts.size();
/*      */               continue label75;
/*      */             case '-':
/*      */             case '0':
/*      */             case '1':
/*      */             case '2':
/*      */             case '3':
/*      */             case '4':
/*      */             case '5':
/*      */             case '6':
/*      */             case '7':
/*      */             case '8':
/*      */             case '9':
/* 3896 */               neg = false;
/* 3897 */               if (ch == '-') {
/* 3898 */                 neg = true;
/* 3899 */                 ch = pattern.charAt(i++);
/*      */               } 
/* 3901 */               countSeen = true;
/* 3902 */               count = 0;
/* 3903 */               while (ch >= '0' && ch <= '9') {
/* 3904 */                 count = ((count < 0) ? 0 : (10 * count)) + ch - 48;
/* 3905 */                 ch = pattern.charAt(i++);
/*      */               } 
/* 3907 */               if (neg) {
/* 3908 */                 count = -count;
/*      */               }
/* 3910 */               i--;
/*      */               continue;
/*      */           } 
/*      */           
/*      */           continue label75;
/*      */         } 
/*      */       } 
/* 3917 */       sb.append(ch);
/*      */     } 
/* 3919 */     if (padToWidth > cols) {
/* 3920 */       int padCharCols = WCWidth.wcwidth(padChar);
/* 3921 */       int padCount = (padToWidth - cols) / padCharCols;
/* 3922 */       sb = padPartString;
/* 3923 */       while (--padCount >= 0)
/* 3924 */         sb.insert(padPos, (char)padChar); 
/* 3925 */       parts.set(padPartIndex, AttributedString.fromAnsi(sb.toString()));
/*      */     } 
/* 3927 */     return AttributedString.join(null, parts);
/*      */   }
/*      */   
/*      */   private AttributedString insertSecondaryPrompts(AttributedString str, List<AttributedString> prompts) {
/* 3931 */     return insertSecondaryPrompts(str, prompts, true);
/*      */   }
/*      */   
/*      */   private AttributedString insertSecondaryPrompts(AttributedString strAtt, List<AttributedString> prompts, boolean computePrompts) {
/* 3935 */     Objects.requireNonNull(prompts);
/* 3936 */     List<AttributedString> lines = strAtt.columnSplitLength(2147483647);
/* 3937 */     AttributedStringBuilder sb = new AttributedStringBuilder();
/* 3938 */     String secondaryPromptPattern = getString("secondary-prompt-pattern", "%M> ");
/* 3939 */     boolean needsMessage = secondaryPromptPattern.contains("%M");
/* 3940 */     AttributedStringBuilder buf = new AttributedStringBuilder();
/* 3941 */     int width = 0;
/* 3942 */     List<String> missings = new ArrayList<>();
/* 3943 */     if (computePrompts && secondaryPromptPattern.contains("%P")) {
/* 3944 */       width = this.prompt.columnLength();
/* 3945 */       for (int i = 0; i < lines.size() - 1; i++) {
/*      */         
/* 3947 */         buf.append(lines.get(i)).append("\n");
/* 3948 */         String missing = "";
/* 3949 */         if (needsMessage) {
/*      */           try {
/* 3951 */             this.parser.parse(buf.toString(), buf.length(), Parser.ParseContext.SECONDARY_PROMPT);
/* 3952 */           } catch (EOFError e) {
/* 3953 */             missing = e.getMissing();
/* 3954 */           } catch (SyntaxError syntaxError) {}
/*      */         }
/*      */ 
/*      */         
/* 3958 */         missings.add(missing);
/* 3959 */         AttributedString prompt = expandPromptPattern(secondaryPromptPattern, 0, missing, i + 1);
/* 3960 */         width = Math.max(width, prompt.columnLength());
/*      */       } 
/* 3962 */       buf.setLength(0);
/*      */     } 
/* 3964 */     int line = 0;
/* 3965 */     while (line < lines.size() - 1) {
/* 3966 */       AttributedString prompt; sb.append(lines.get(line)).append("\n");
/* 3967 */       buf.append(lines.get(line)).append("\n");
/*      */       
/* 3969 */       if (computePrompts) {
/* 3970 */         String missing = "";
/* 3971 */         if (needsMessage) {
/* 3972 */           if (missings.isEmpty()) {
/*      */             try {
/* 3974 */               this.parser.parse(buf.toString(), buf.length(), Parser.ParseContext.SECONDARY_PROMPT);
/* 3975 */             } catch (EOFError e) {
/* 3976 */               missing = e.getMissing();
/* 3977 */             } catch (SyntaxError syntaxError) {}
/*      */           }
/*      */           else {
/*      */             
/* 3981 */             missing = missings.get(line);
/*      */           } 
/*      */         }
/* 3984 */         prompt = expandPromptPattern(secondaryPromptPattern, width, missing, line + 1);
/*      */       } else {
/* 3986 */         prompt = prompts.get(line);
/*      */       } 
/* 3988 */       prompts.add(prompt);
/* 3989 */       sb.append(prompt);
/* 3990 */       line++;
/*      */     } 
/* 3992 */     sb.append(lines.get(line));
/* 3993 */     buf.append(lines.get(line));
/* 3994 */     return sb.toAttributedString();
/*      */   }
/*      */   
/*      */   private AttributedString addRightPrompt(AttributedString prompt, AttributedString line) {
/* 3998 */     int width = prompt.columnLength();
/*      */     
/* 4000 */     boolean endsWithNl = (line.length() > 0 && line.charAt(line.length() - 1) == '\n');
/*      */ 
/*      */     
/* 4003 */     int nb = this.size.getColumns() - width - line.columnLength() + (endsWithNl ? 1 : 0);
/* 4004 */     if (nb >= 3) {
/* 4005 */       AttributedStringBuilder sb = new AttributedStringBuilder(this.size.getColumns());
/* 4006 */       sb.append(line, 0, endsWithNl ? (line.length() - 1) : line.length());
/* 4007 */       for (int j = 0; j < nb; j++) {
/* 4008 */         sb.append(' ');
/*      */       }
/* 4010 */       sb.append(prompt);
/* 4011 */       if (endsWithNl) {
/* 4012 */         sb.append('\n');
/*      */       }
/* 4014 */       line = sb.toAttributedString();
/*      */     } 
/* 4016 */     return line;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean insertTab() {
/* 4024 */     return (isSet(LineReader.Option.INSERT_TAB) && 
/* 4025 */       getLastBinding().equals("\t") && this.buf
/* 4026 */       .toString().matches("(^|[\\s\\S]*\n)[\r\n\t ]*"));
/*      */   }
/*      */   
/*      */   protected boolean expandHistory() {
/* 4030 */     String str = this.buf.toString();
/* 4031 */     String exp = this.expander.expandHistory(this.history, str);
/* 4032 */     if (!exp.equals(str)) {
/* 4033 */       this.buf.clear();
/* 4034 */       this.buf.write(exp);
/* 4035 */       return true;
/*      */     } 
/* 4037 */     return false;
/*      */   }
/*      */   
/*      */   protected enum CompletionType
/*      */   {
/* 4042 */     Expand,
/* 4043 */     ExpandComplete,
/* 4044 */     Complete,
/* 4045 */     List;
/*      */   }
/*      */   
/*      */   protected boolean expandWord() {
/* 4049 */     if (insertTab()) {
/* 4050 */       return selfInsert();
/*      */     }
/* 4052 */     return doComplete(CompletionType.Expand, isSet(LineReader.Option.MENU_COMPLETE), false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean expandOrComplete() {
/* 4057 */     if (insertTab()) {
/* 4058 */       return selfInsert();
/*      */     }
/* 4060 */     return doComplete(CompletionType.ExpandComplete, isSet(LineReader.Option.MENU_COMPLETE), false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean expandOrCompletePrefix() {
/* 4065 */     if (insertTab()) {
/* 4066 */       return selfInsert();
/*      */     }
/* 4068 */     return doComplete(CompletionType.ExpandComplete, isSet(LineReader.Option.MENU_COMPLETE), true);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean completeWord() {
/* 4073 */     if (insertTab()) {
/* 4074 */       return selfInsert();
/*      */     }
/* 4076 */     return doComplete(CompletionType.Complete, isSet(LineReader.Option.MENU_COMPLETE), false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean menuComplete() {
/* 4081 */     if (insertTab()) {
/* 4082 */       return selfInsert();
/*      */     }
/* 4084 */     return doComplete(CompletionType.Complete, true, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean menuExpandOrComplete() {
/* 4089 */     if (insertTab()) {
/* 4090 */       return selfInsert();
/*      */     }
/* 4092 */     return doComplete(CompletionType.ExpandComplete, true, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean completePrefix() {
/* 4097 */     if (insertTab()) {
/* 4098 */       return selfInsert();
/*      */     }
/* 4100 */     return doComplete(CompletionType.Complete, isSet(LineReader.Option.MENU_COMPLETE), true);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean listChoices() {
/* 4105 */     return doComplete(CompletionType.List, isSet(LineReader.Option.MENU_COMPLETE), false);
/*      */   }
/*      */   
/*      */   protected boolean deleteCharOrList() {
/* 4109 */     if (this.buf.cursor() != this.buf.length() || this.buf.length() == 0) {
/* 4110 */       return deleteChar();
/*      */     }
/* 4112 */     return doComplete(CompletionType.List, isSet(LineReader.Option.MENU_COMPLETE), false);
/*      */   }
/*      */   protected boolean doComplete(CompletionType lst, boolean useMenu, boolean prefix) {
/*      */     CompletingParsedLine line;
/*      */     List<Function<Map<String, List<Candidate>>, Map<String, List<Candidate>>>> matchers;
/*      */     Predicate<String> exact;
/* 4118 */     if (getBoolean("disable-completion", false)) {
/* 4119 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 4123 */     if (!isSet(LineReader.Option.DISABLE_EVENT_EXPANSION)) {
/*      */       try {
/* 4125 */         if (expandHistory()) {
/* 4126 */           return true;
/*      */         }
/* 4128 */       } catch (Exception e) {
/* 4129 */         Log.info(new Object[] { "Error while expanding history", e });
/* 4130 */         return false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 4137 */       line = wrap(this.parser.parse(this.buf.toString(), this.buf.cursor(), Parser.ParseContext.COMPLETE));
/* 4138 */     } catch (Exception e) {
/* 4139 */       Log.info(new Object[] { "Error while parsing line", e });
/* 4140 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 4144 */     List<Candidate> candidates = new ArrayList<>();
/*      */     try {
/* 4146 */       if (this.completer != null) {
/* 4147 */         this.completer.complete(this, (ParsedLine)line, candidates);
/*      */       }
/* 4149 */     } catch (Exception e) {
/* 4150 */       Log.info(new Object[] { "Error while finding completion candidates", e });
/* 4151 */       return false;
/*      */     } 
/*      */     
/* 4154 */     if (lst == CompletionType.ExpandComplete || lst == CompletionType.Expand) {
/* 4155 */       String w = this.expander.expandVar(line.word());
/* 4156 */       if (!line.word().equals(w)) {
/* 4157 */         if (prefix) {
/* 4158 */           this.buf.backspace(line.wordCursor());
/*      */         } else {
/* 4160 */           this.buf.move(line.word().length() - line.wordCursor());
/* 4161 */           this.buf.backspace(line.word().length());
/*      */         } 
/* 4163 */         this.buf.write(w);
/* 4164 */         return true;
/*      */       } 
/* 4166 */       if (lst == CompletionType.Expand) {
/* 4167 */         return false;
/*      */       }
/* 4169 */       lst = CompletionType.Complete;
/*      */     } 
/*      */ 
/*      */     
/* 4173 */     boolean caseInsensitive = isSet(LineReader.Option.CASE_INSENSITIVE);
/* 4174 */     int errors = getInt("errors", 2);
/*      */ 
/*      */     
/* 4177 */     Map<String, List<Candidate>> sortedCandidates = new HashMap<>();
/* 4178 */     for (Candidate cand : candidates) {
/* 4179 */       ((List<Candidate>)sortedCandidates
/* 4180 */         .computeIfAbsent(AttributedString.fromAnsi(cand.value()).toString(), s -> new ArrayList()))
/* 4181 */         .add(cand);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 4189 */     if (prefix) {
/* 4190 */       String wd = line.word();
/* 4191 */       String wdi = caseInsensitive ? wd.toLowerCase() : wd;
/* 4192 */       String wp = wdi.substring(0, line.wordCursor());
/* 4193 */       matchers = Arrays.asList((Function<Map<String, List<Candidate>>, Map<String, List<Candidate>>>[])new Function[] {
/* 4194 */             simpleMatcher(s -> (caseInsensitive ? s.toLowerCase() : s).startsWith(wp)), 
/* 4195 */             simpleMatcher(s -> (caseInsensitive ? s.toLowerCase() : s).contains(wp)), 
/* 4196 */             typoMatcher(wp, errors, caseInsensitive)
/*      */           });
/* 4198 */       exact = (s -> caseInsensitive ? s.equalsIgnoreCase(wp) : s.equals(wp));
/* 4199 */     } else if (isSet(LineReader.Option.COMPLETE_IN_WORD)) {
/* 4200 */       String wd = line.word();
/* 4201 */       String wdi = caseInsensitive ? wd.toLowerCase() : wd;
/* 4202 */       String wp = wdi.substring(0, line.wordCursor());
/* 4203 */       String ws = wdi.substring(line.wordCursor());
/* 4204 */       Pattern p1 = Pattern.compile(Pattern.quote(wp) + ".*" + Pattern.quote(ws) + ".*");
/* 4205 */       Pattern p2 = Pattern.compile(".*" + Pattern.quote(wp) + ".*" + Pattern.quote(ws) + ".*");
/* 4206 */       matchers = Arrays.asList((Function<Map<String, List<Candidate>>, Map<String, List<Candidate>>>[])new Function[] {
/* 4207 */             simpleMatcher(s -> p1.matcher(caseInsensitive ? s.toLowerCase() : s).matches()), 
/* 4208 */             simpleMatcher(s -> p2.matcher(caseInsensitive ? s.toLowerCase() : s).matches()), 
/* 4209 */             typoMatcher(wdi, errors, caseInsensitive)
/*      */           });
/* 4211 */       exact = (s -> caseInsensitive ? s.equalsIgnoreCase(wd) : s.equals(wd));
/*      */     } else {
/* 4213 */       String wd = line.word();
/* 4214 */       String wdi = caseInsensitive ? wd.toLowerCase() : wd;
/* 4215 */       matchers = Arrays.asList((Function<Map<String, List<Candidate>>, Map<String, List<Candidate>>>[])new Function[] {
/* 4216 */             simpleMatcher(s -> (caseInsensitive ? s.toLowerCase() : s).startsWith(wdi)), 
/* 4217 */             simpleMatcher(s -> (caseInsensitive ? s.toLowerCase() : s).contains(wdi)), 
/* 4218 */             typoMatcher(wdi, errors, caseInsensitive)
/*      */           });
/* 4220 */       exact = (s -> caseInsensitive ? s.equalsIgnoreCase(wd) : s.equals(wd));
/*      */     } 
/*      */     
/* 4223 */     Map<String, List<Candidate>> matching = Collections.emptyMap();
/*      */     
/* 4225 */     for (Function<Map<String, List<Candidate>>, Map<String, List<Candidate>>> matcher : matchers) {
/* 4226 */       matching = matcher.apply(sortedCandidates);
/* 4227 */       if (!matching.isEmpty()) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 4233 */     if (matching.isEmpty()) {
/* 4234 */       return false;
/*      */     }
/* 4236 */     this.size.copy(this.terminal.getSize());
/*      */     try {
/*      */       String current;
/* 4239 */       if (lst == CompletionType.List) {
/*      */ 
/*      */         
/* 4242 */         List<Candidate> list = (List<Candidate>)matching.entrySet().stream().flatMap(e -> ((List)e.getValue()).stream()).collect(Collectors.toList());
/* 4243 */         doList(list, line.word(), false, line::escape);
/* 4244 */         return !list.isEmpty();
/*      */       } 
/*      */ 
/*      */       
/* 4248 */       Candidate completion = null;
/*      */       
/* 4250 */       if (matching.size() == 1) {
/*      */         
/* 4252 */         completion = matching.values().stream().flatMap(Collection::stream).findFirst().orElse(null);
/*      */       
/*      */       }
/* 4255 */       else if (isSet(LineReader.Option.RECOGNIZE_EXACT)) {
/*      */ 
/*      */ 
/*      */         
/* 4259 */         completion = matching.values().stream().flatMap(Collection::stream).filter(Candidate::complete).filter(c -> exact.test(c.value())).findFirst().orElse(null);
/*      */       } 
/*      */       
/* 4262 */       if (completion != null && !completion.value().isEmpty()) {
/* 4263 */         if (prefix) {
/* 4264 */           this.buf.backspace(line.rawWordCursor());
/*      */         } else {
/* 4266 */           this.buf.move(line.rawWordLength() - line.rawWordCursor());
/* 4267 */           this.buf.backspace(line.rawWordLength());
/*      */         } 
/* 4269 */         this.buf.write(line.escape(completion.value(), completion.complete()));
/* 4270 */         if (completion.complete()) {
/* 4271 */           if (this.buf.currChar() != 32) {
/* 4272 */             this.buf.write(" ");
/*      */           } else {
/* 4274 */             this.buf.move(1);
/*      */           } 
/*      */         }
/* 4277 */         if (completion.suffix() != null) {
/* 4278 */           redisplay();
/* 4279 */           Binding op = readBinding(getKeys());
/* 4280 */           if (op != null) {
/* 4281 */             String chars = getString("REMOVE_SUFFIX_CHARS", " \t\n;&|");
/* 4282 */             String ref = (op instanceof Reference) ? ((Reference)op).name() : null;
/* 4283 */             if (("self-insert".equals(ref) && chars.indexOf(getLastBinding().charAt(0)) >= 0) || "accept-line"
/* 4284 */               .equals(ref)) {
/* 4285 */               this.buf.backspace(completion.suffix().length());
/* 4286 */               if (getLastBinding().charAt(0) != ' ') {
/* 4287 */                 this.buf.write(32);
/*      */               }
/*      */             } 
/* 4290 */             pushBackBinding(true);
/*      */           } 
/*      */         } 
/* 4293 */         return true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 4298 */       List<Candidate> possible = (List<Candidate>)matching.entrySet().stream().flatMap(e -> ((List)e.getValue()).stream()).collect(Collectors.toList());
/*      */       
/* 4300 */       if (useMenu) {
/* 4301 */         this.buf.move(line.word().length() - line.wordCursor());
/* 4302 */         this.buf.backspace(line.word().length());
/* 4303 */         doMenu(possible, line.word(), line::escape);
/* 4304 */         return true;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 4309 */       if (prefix) {
/* 4310 */         current = line.word().substring(0, line.wordCursor());
/*      */       } else {
/* 4312 */         current = line.word();
/* 4313 */         this.buf.move(line.rawWordLength() - line.rawWordCursor());
/*      */       } 
/*      */ 
/*      */       
/* 4317 */       String commonPrefix = null;
/* 4318 */       for (String key : matching.keySet()) {
/* 4319 */         commonPrefix = (commonPrefix == null) ? key : getCommonStart(commonPrefix, key, caseInsensitive);
/*      */       }
/* 4321 */       boolean hasUnambiguous = (commonPrefix.startsWith(current) && !commonPrefix.equals(current));
/*      */       
/* 4323 */       if (hasUnambiguous) {
/* 4324 */         this.buf.backspace(line.rawWordLength());
/* 4325 */         this.buf.write(line.escape(commonPrefix, false));
/* 4326 */         current = commonPrefix;
/* 4327 */         if (((!isSet(LineReader.Option.AUTO_LIST) && isSet(LineReader.Option.AUTO_MENU)) || (
/* 4328 */           isSet(LineReader.Option.AUTO_LIST) && isSet(LineReader.Option.LIST_AMBIGUOUS))) && 
/* 4329 */           !nextBindingIsComplete()) {
/* 4330 */           return true;
/*      */         }
/*      */       } 
/*      */       
/* 4334 */       if (isSet(LineReader.Option.AUTO_LIST) && 
/* 4335 */         !doList(possible, current, true, line::escape)) {
/* 4336 */         return true;
/*      */       }
/*      */       
/* 4339 */       if (isSet(LineReader.Option.AUTO_MENU)) {
/* 4340 */         this.buf.backspace(current.length());
/* 4341 */         doMenu(possible, line.word(), line::escape);
/*      */       } 
/* 4343 */       return true;
/*      */     } finally {
/* 4345 */       this.size.copy(this.terminal.getBufferSize());
/*      */     } 
/*      */   }
/*      */   
/*      */   private CompletingParsedLine wrap(final ParsedLine line) {
/* 4350 */     if (line instanceof CompletingParsedLine) {
/* 4351 */       return (CompletingParsedLine)line;
/*      */     }
/* 4353 */     return new CompletingParsedLine() {
/*      */         public String word() {
/* 4355 */           return line.word();
/*      */         }
/*      */         public int wordCursor() {
/* 4358 */           return line.wordCursor();
/*      */         }
/*      */         public int wordIndex() {
/* 4361 */           return line.wordIndex();
/*      */         }
/*      */         public List<String> words() {
/* 4364 */           return line.words();
/*      */         }
/*      */         public String line() {
/* 4367 */           return line.line();
/*      */         }
/*      */         public int cursor() {
/* 4370 */           return line.cursor();
/*      */         }
/*      */         public CharSequence escape(CharSequence candidate, boolean complete) {
/* 4373 */           return candidate;
/*      */         }
/*      */         public int rawWordCursor() {
/* 4376 */           return wordCursor();
/*      */         }
/*      */         public int rawWordLength() {
/* 4379 */           return word().length();
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */   
/*      */   protected Comparator<Candidate> getCandidateComparator(boolean caseInsensitive, String word) {
/* 4386 */     String wdi = caseInsensitive ? word.toLowerCase() : word;
/* 4387 */     ToIntFunction<String> wordDistance = w -> distance(wdi, caseInsensitive ? w.toLowerCase() : w);
/* 4388 */     return 
/* 4389 */       Comparator.comparing(Candidate::value, Comparator.comparingInt(wordDistance))
/* 4390 */       .thenComparing(Candidate::value, Comparator.comparingInt(String::length))
/* 4391 */       .thenComparing(Comparator.naturalOrder());
/*      */   }
/*      */   
/*      */   protected String getOthersGroupName() {
/* 4395 */     return getString("OTHERS_GROUP_NAME", "others");
/*      */   }
/*      */   
/*      */   protected String getOriginalGroupName() {
/* 4399 */     return getString("ORIGINAL_GROUP_NAME", "original");
/*      */   }
/*      */ 
/*      */   
/*      */   protected Comparator<String> getGroupComparator() {
/* 4404 */     return Comparator.comparingInt(s -> getOthersGroupName().equals(s) ? 1 : (getOriginalGroupName().equals(s) ? -1 : 0))
/* 4405 */       .thenComparing(String::toLowerCase, Comparator.naturalOrder());
/*      */   }
/*      */ 
/*      */   
/*      */   private void mergeCandidates(List<Candidate> possible) {
/* 4410 */     Map<String, List<Candidate>> keyedCandidates = new HashMap<>();
/* 4411 */     for (Candidate candidate : possible) {
/* 4412 */       if (candidate.key() != null) {
/* 4413 */         List<Candidate> cands = keyedCandidates.computeIfAbsent(candidate.key(), s -> new ArrayList());
/* 4414 */         cands.add(candidate);
/*      */       } 
/*      */     } 
/* 4417 */     if (!keyedCandidates.isEmpty()) {
/* 4418 */       for (List<Candidate> candidates : keyedCandidates.values()) {
/* 4419 */         if (candidates.size() >= 1) {
/* 4420 */           possible.removeAll(candidates);
/*      */ 
/*      */           
/* 4423 */           candidates.sort(Comparator.comparing(Candidate::value));
/* 4424 */           Candidate first = candidates.get(0);
/*      */ 
/*      */           
/* 4427 */           String disp = candidates.stream().map(Candidate::displ).collect(Collectors.joining(" "));
/* 4428 */           possible.add(new Candidate(first.value(), disp, first.group(), first
/* 4429 */                 .descr(), first.suffix(), null, first.complete()));
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private Function<Map<String, List<Candidate>>, Map<String, List<Candidate>>> simpleMatcher(Predicate<String> pred) {
/* 4437 */     return m -> (Map)m.entrySet().stream().filter(()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Function<Map<String, List<Candidate>>, Map<String, List<Candidate>>> typoMatcher(String word, int errors, boolean caseInsensitive) {
/* 4444 */     return m -> {
/*      */         Map<String, List<Candidate>> map = (Map<String, List<Candidate>>)m.entrySet().stream().filter(()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
/*      */         if (map.size() > 1) {
/*      */           ((List<Candidate>)map.computeIfAbsent(word, ())).add(new Candidate(word, word, getOriginalGroupName(), null, null, null, false));
/*      */         }
/*      */         return map;
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int distance(String word, String cand) {
/* 4457 */     if (word.length() < cand.length()) {
/* 4458 */       int d1 = Levenshtein.distance(word, cand.substring(0, Math.min(cand.length(), word.length())));
/* 4459 */       int d2 = Levenshtein.distance(word, cand);
/* 4460 */       return Math.min(d1, d2);
/*      */     } 
/* 4462 */     return Levenshtein.distance(word, cand);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean nextBindingIsComplete() {
/* 4467 */     redisplay();
/* 4468 */     KeyMap<Binding> keyMap = this.keyMaps.get("menu");
/* 4469 */     Binding operation = readBinding(getKeys(), keyMap);
/* 4470 */     if (operation instanceof Reference && "menu-complete".equals(((Reference)operation).name())) {
/* 4471 */       return true;
/*      */     }
/* 4473 */     pushBackBinding();
/* 4474 */     return false;
/*      */   }
/*      */   
/*      */   private class MenuSupport
/*      */     implements Supplier<AttributedString> {
/*      */     final List<Candidate> possible;
/*      */     final BiFunction<CharSequence, Boolean, CharSequence> escaper;
/*      */     int selection;
/*      */     int topLine;
/*      */     String word;
/*      */     AttributedString computed;
/*      */     int lines;
/*      */     int columns;
/*      */     String completed;
/*      */     
/*      */     public MenuSupport(List<Candidate> original, String completed, BiFunction<CharSequence, Boolean, CharSequence> escaper) {
/* 4490 */       this.possible = new ArrayList<>();
/* 4491 */       this.escaper = escaper;
/* 4492 */       this.selection = -1;
/* 4493 */       this.topLine = 0;
/* 4494 */       this.word = "";
/* 4495 */       this.completed = completed;
/* 4496 */       LineReaderImpl.this.computePost(original, null, this.possible, completed);
/* 4497 */       next();
/*      */     }
/*      */     
/*      */     public Candidate completion() {
/* 4501 */       return this.possible.get(this.selection);
/*      */     }
/*      */     
/*      */     public void next() {
/* 4505 */       this.selection = (this.selection + 1) % this.possible.size();
/* 4506 */       update();
/*      */     }
/*      */     
/*      */     public void previous() {
/* 4510 */       this.selection = (this.selection + this.possible.size() - 1) % this.possible.size();
/* 4511 */       update();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void major(int step) {
/* 4522 */       int axis = LineReaderImpl.this.isSet(LineReader.Option.LIST_ROWS_FIRST) ? this.columns : this.lines;
/* 4523 */       int sel = this.selection + step * axis;
/* 4524 */       if (sel < 0) {
/* 4525 */         int pos = (sel + axis) % axis;
/* 4526 */         int remainders = this.possible.size() % axis;
/* 4527 */         sel = this.possible.size() - remainders + pos;
/* 4528 */         if (sel >= this.possible.size()) {
/* 4529 */           sel -= axis;
/*      */         }
/* 4531 */       } else if (sel >= this.possible.size()) {
/* 4532 */         sel %= axis;
/*      */       } 
/* 4534 */       this.selection = sel;
/* 4535 */       update();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void minor(int step) {
/* 4546 */       int axis = LineReaderImpl.this.isSet(LineReader.Option.LIST_ROWS_FIRST) ? this.columns : this.lines;
/* 4547 */       int row = this.selection % axis;
/* 4548 */       int options = this.possible.size();
/* 4549 */       if (this.selection - row + axis > options)
/*      */       {
/*      */         
/* 4552 */         axis = options % axis;
/*      */       }
/* 4554 */       this.selection = this.selection - row + (axis + row + step) % axis;
/* 4555 */       update();
/*      */     }
/*      */     
/*      */     public void up() {
/* 4559 */       if (LineReaderImpl.this.isSet(LineReader.Option.LIST_ROWS_FIRST)) {
/* 4560 */         major(-1);
/*      */       } else {
/* 4562 */         minor(-1);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void down() {
/* 4567 */       if (LineReaderImpl.this.isSet(LineReader.Option.LIST_ROWS_FIRST)) {
/* 4568 */         major(1);
/*      */       } else {
/* 4570 */         minor(1);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void left() {
/* 4575 */       if (LineReaderImpl.this.isSet(LineReader.Option.LIST_ROWS_FIRST)) {
/* 4576 */         minor(-1);
/*      */       } else {
/* 4578 */         major(-1);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void right() {
/* 4583 */       if (LineReaderImpl.this.isSet(LineReader.Option.LIST_ROWS_FIRST)) {
/* 4584 */         minor(1);
/*      */       } else {
/* 4586 */         major(1);
/*      */       } 
/*      */     }
/*      */     
/*      */     private void update() {
/* 4591 */       LineReaderImpl.this.buf.backspace(this.word.length());
/* 4592 */       this.word = ((CharSequence)this.escaper.apply(completion().value(), Boolean.valueOf(true))).toString();
/* 4593 */       LineReaderImpl.this.buf.write(this.word);
/*      */ 
/*      */       
/* 4596 */       LineReaderImpl.PostResult pr = LineReaderImpl.this.computePost(this.possible, completion(), null, this.completed);
/* 4597 */       AttributedString text = LineReaderImpl.this.insertSecondaryPrompts(AttributedStringBuilder.append(new CharSequence[] { (CharSequence)this.this$0.prompt, this.this$0.buf.toString() }, ), new ArrayList());
/* 4598 */       int promptLines = text.columnSplitLength(LineReaderImpl.this.size.getColumns(), false, LineReaderImpl.this.display.delayLineWrap()).size();
/* 4599 */       if (pr.lines > LineReaderImpl.this.size.getRows() - promptLines) {
/* 4600 */         int displayed = LineReaderImpl.this.size.getRows() - promptLines - 1;
/* 4601 */         if (pr.selectedLine >= 0) {
/* 4602 */           if (pr.selectedLine < this.topLine) {
/* 4603 */             this.topLine = pr.selectedLine;
/* 4604 */           } else if (pr.selectedLine >= this.topLine + displayed) {
/* 4605 */             this.topLine = pr.selectedLine - displayed + 1;
/*      */           } 
/*      */         }
/* 4608 */         AttributedString post = pr.post;
/* 4609 */         if (post.length() > 0 && post.charAt(post.length() - 1) != '\n')
/*      */         {
/* 4611 */           post = (new AttributedStringBuilder(post.length() + 1)).append(post).append("\n").toAttributedString();
/*      */         }
/* 4613 */         List<AttributedString> lines = post.columnSplitLength(LineReaderImpl.this.size.getColumns(), true, LineReaderImpl.this.display.delayLineWrap());
/* 4614 */         List<AttributedString> sub = new ArrayList<>(lines.subList(this.topLine, this.topLine + displayed));
/* 4615 */         sub.add((new AttributedStringBuilder())
/* 4616 */             .style(AttributedStyle.DEFAULT.foreground(6))
/* 4617 */             .append("rows ")
/* 4618 */             .append(Integer.toString(this.topLine + 1))
/* 4619 */             .append(" to ")
/* 4620 */             .append(Integer.toString(this.topLine + displayed))
/* 4621 */             .append(" of ")
/* 4622 */             .append(Integer.toString(lines.size()))
/* 4623 */             .append("\n")
/* 4624 */             .style(AttributedStyle.DEFAULT).toAttributedString());
/* 4625 */         this.computed = AttributedString.join(AttributedString.EMPTY, sub);
/*      */       } else {
/* 4627 */         this.computed = pr.post;
/*      */       } 
/* 4629 */       this.lines = pr.lines;
/* 4630 */       this.columns = (this.possible.size() + this.lines - 1) / this.lines;
/*      */     }
/*      */ 
/*      */     
/*      */     public AttributedString get() {
/* 4635 */       return this.computed;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean doMenu(List<Candidate> original, String completed, BiFunction<CharSequence, Boolean, CharSequence> escaper) {
/* 4642 */     List<Candidate> possible = new ArrayList<>();
/* 4643 */     boolean caseInsensitive = isSet(LineReader.Option.CASE_INSENSITIVE);
/* 4644 */     original.sort(getCandidateComparator(caseInsensitive, completed));
/* 4645 */     mergeCandidates(original);
/* 4646 */     computePost(original, null, possible, completed);
/*      */ 
/*      */     
/* 4649 */     MenuSupport menuSupport = new MenuSupport(original, completed, escaper);
/* 4650 */     this.post = menuSupport;
/* 4651 */     redisplay();
/*      */ 
/*      */     
/* 4654 */     KeyMap<Binding> keyMap = this.keyMaps.get("menu");
/*      */     Binding operation;
/* 4656 */     while ((operation = readBinding(getKeys(), keyMap)) != null) {
/* 4657 */       Candidate completion; String ref = (operation instanceof Reference) ? ((Reference)operation).name() : "";
/* 4658 */       switch (ref) {
/*      */         case "menu-complete":
/* 4660 */           menuSupport.next();
/*      */           break;
/*      */         case "reverse-menu-complete":
/* 4663 */           menuSupport.previous();
/*      */           break;
/*      */         case "up-line-or-history":
/*      */         case "up-line-or-search":
/* 4667 */           menuSupport.up();
/*      */           break;
/*      */         case "down-line-or-history":
/*      */         case "down-line-or-search":
/* 4671 */           menuSupport.down();
/*      */           break;
/*      */         case "forward-char":
/* 4674 */           menuSupport.right();
/*      */           break;
/*      */         case "backward-char":
/* 4677 */           menuSupport.left();
/*      */           break;
/*      */         case "clear-screen":
/* 4680 */           clearScreen();
/*      */           break;
/*      */         default:
/* 4683 */           completion = menuSupport.completion();
/* 4684 */           if (completion.suffix() != null) {
/* 4685 */             String chars = getString("REMOVE_SUFFIX_CHARS", " \t\n;&|");
/* 4686 */             if (("self-insert".equals(ref) && chars
/* 4687 */               .indexOf(getLastBinding().charAt(0)) >= 0) || "backward-delete-char"
/* 4688 */               .equals(ref)) {
/* 4689 */               this.buf.backspace(completion.suffix().length());
/*      */             }
/*      */           } 
/* 4692 */           if (completion.complete() && 
/* 4693 */             getLastBinding().charAt(0) != ' ' && ("self-insert"
/* 4694 */             .equals(ref) || getLastBinding().charAt(0) != ' ')) {
/* 4695 */             this.buf.write(32);
/*      */           }
/* 4697 */           if (!"accept-line".equals(ref) && (
/* 4698 */             !"self-insert".equals(ref) || completion
/* 4699 */             .suffix() == null || 
/* 4700 */             !completion.suffix().startsWith(getLastBinding()))) {
/* 4701 */             pushBackBinding(true);
/*      */           }
/* 4703 */           this.post = null;
/* 4704 */           return true;
/*      */       } 
/*      */       
/* 4707 */       redisplay();
/*      */     } 
/* 4709 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean doList(List<Candidate> possible, String completed, boolean runLoop, BiFunction<CharSequence, Boolean, CharSequence> escaper) {
/* 4717 */     mergeCandidates(possible);
/* 4718 */     AttributedString text = insertSecondaryPrompts(AttributedStringBuilder.append(new CharSequence[] { (CharSequence)this.prompt, this.buf.toString() }, ), new ArrayList<>());
/* 4719 */     int promptLines = text.columnSplitLength(this.size.getColumns(), false, this.display.delayLineWrap()).size();
/* 4720 */     PostResult postResult = computePost(possible, null, null, completed);
/* 4721 */     int lines = postResult.lines;
/* 4722 */     int listMax = getInt("list-max", 100);
/* 4723 */     if ((listMax > 0 && possible.size() >= listMax) || lines >= this.size
/* 4724 */       .getRows() - promptLines) {
/*      */       
/* 4726 */       this.post = (() -> new AttributedString(getAppName() + ": do you wish to see all " + possible.size() + " possibilities (" + lines + " lines)?"));
/*      */       
/* 4728 */       redisplay(true);
/* 4729 */       int c = readCharacter();
/* 4730 */       if (c != 121 && c != 89 && c != 9) {
/* 4731 */         this.post = null;
/* 4732 */         return false;
/*      */       } 
/*      */     } 
/*      */     
/* 4736 */     boolean caseInsensitive = isSet(LineReader.Option.CASE_INSENSITIVE);
/* 4737 */     StringBuilder sb = new StringBuilder(); while (true) {
/*      */       List<Candidate> cands;
/* 4739 */       String current = completed + sb.toString();
/*      */       
/* 4741 */       if (sb.length() > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 4747 */         cands = (List<Candidate>)possible.stream().filter(c -> caseInsensitive ? c.value().toLowerCase().startsWith(current.toLowerCase()) : c.value().startsWith(current)).sorted(getCandidateComparator(caseInsensitive, current)).collect(Collectors.toList());
/*      */       }
/*      */       else {
/*      */         
/* 4751 */         cands = (List<Candidate>)possible.stream().sorted(getCandidateComparator(caseInsensitive, current)).collect(Collectors.toList());
/*      */       } 
/* 4753 */       this.post = (() -> {
/*      */           AttributedString t = insertSecondaryPrompts(AttributedStringBuilder.append(new CharSequence[] { (CharSequence)this.prompt, this.buf.toString() }, ), new ArrayList<>());
/*      */           int pl = t.columnSplitLength(this.size.getColumns(), false, this.display.delayLineWrap()).size();
/*      */           PostResult pr = computePost(cands, null, null, current);
/*      */           if (pr.lines >= this.size.getRows() - pl) {
/*      */             this.post = null;
/*      */             int oldCursor = this.buf.cursor();
/*      */             this.buf.cursor(this.buf.length());
/*      */             redisplay(false);
/*      */             this.buf.cursor(oldCursor);
/*      */             println();
/*      */             List<AttributedString> ls = postResult.post.columnSplitLength(this.size.getColumns(), false, this.display.delayLineWrap());
/*      */             Display d = new Display(this.terminal, false);
/*      */             d.resize(this.size.getRows(), this.size.getColumns());
/*      */             d.update(ls, -1);
/*      */             redrawLine();
/*      */             return new AttributedString("");
/*      */           } 
/*      */           return pr.post;
/*      */         });
/* 4773 */       if (!runLoop) {
/* 4774 */         return false;
/*      */       }
/* 4776 */       redisplay();
/*      */       
/* 4778 */       Binding b = doReadBinding(getKeys(), null);
/* 4779 */       if (b instanceof Reference) {
/* 4780 */         String name = ((Reference)b).name();
/* 4781 */         if ("backward-delete-char".equals(name) || "vi-backward-delete-char".equals(name)) {
/* 4782 */           if (sb.length() == 0) {
/* 4783 */             pushBackBinding();
/* 4784 */             this.post = null;
/* 4785 */             return false;
/*      */           } 
/* 4787 */           sb.setLength(sb.length() - 1);
/* 4788 */           this.buf.backspace(); continue;
/*      */         } 
/* 4790 */         if ("self-insert".equals(name)) {
/* 4791 */           sb.append(getLastBinding());
/* 4792 */           this.buf.write(getLastBinding());
/* 4793 */           if (cands.isEmpty()) {
/* 4794 */             this.post = null;
/* 4795 */             return false;
/*      */           }  continue;
/* 4797 */         }  if ("\t".equals(getLastBinding())) {
/* 4798 */           if (cands.size() == 1 || sb.length() > 0) {
/* 4799 */             this.post = null;
/* 4800 */             pushBackBinding();
/* 4801 */           } else if (isSet(LineReader.Option.AUTO_MENU)) {
/* 4802 */             this.buf.backspace(((CharSequence)escaper.apply(current, Boolean.valueOf(false))).length());
/* 4803 */             doMenu(cands, current, escaper);
/*      */           } 
/* 4805 */           return false;
/*      */         } 
/* 4807 */         pushBackBinding();
/* 4808 */         this.post = null;
/* 4809 */         return false;
/*      */       } 
/* 4811 */       if (b == null) {
/* 4812 */         this.post = null;
/* 4813 */         return false;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected static class PostResult {
/*      */     final AttributedString post;
/*      */     final int lines;
/*      */     final int selectedLine;
/*      */     
/*      */     public PostResult(AttributedString post, int lines, int selectedLine) {
/* 4824 */       this.post = post;
/* 4825 */       this.lines = lines;
/* 4826 */       this.selectedLine = selectedLine;
/*      */     }
/*      */   }
/*      */   
/*      */   protected PostResult computePost(List<Candidate> possible, Candidate selection, List<Candidate> ordered, String completed) {
/* 4831 */     return computePost(possible, selection, ordered, completed, this.display::wcwidth, this.size.getColumns(), isSet(LineReader.Option.AUTO_GROUP), isSet(LineReader.Option.GROUP), isSet(LineReader.Option.LIST_ROWS_FIRST));
/*      */   }
/*      */   
/*      */   protected PostResult computePost(List<Candidate> possible, Candidate selection, List<Candidate> ordered, String completed, Function<String, Integer> wcwidth, int width, boolean autoGroup, boolean groupName, boolean rowsFirst) {
/* 4835 */     List<Object> strings = new ArrayList();
/* 4836 */     if (groupName) {
/* 4837 */       Comparator<String> groupComparator = getGroupComparator();
/*      */       
/* 4839 */       Map<String, Map<String, Candidate>> sorted = (groupComparator != null) ? new TreeMap<>(groupComparator) : new LinkedHashMap<>();
/*      */ 
/*      */       
/* 4842 */       for (Candidate cand : possible) {
/* 4843 */         String group = cand.group();
/* 4844 */         ((Map<String, Candidate>)sorted.computeIfAbsent((group != null) ? group : "", s -> new LinkedHashMap<>()))
/* 4845 */           .put(cand.value(), cand);
/*      */       } 
/* 4847 */       for (Map.Entry<String, Map<String, Candidate>> entry : sorted.entrySet()) {
/* 4848 */         String group = entry.getKey();
/* 4849 */         if (group.isEmpty() && sorted.size() > 1) {
/* 4850 */           group = getOthersGroupName();
/*      */         }
/* 4852 */         if (!group.isEmpty() && autoGroup) {
/* 4853 */           strings.add(group);
/*      */         }
/* 4855 */         strings.add(new ArrayList(((Map)entry.getValue()).values()));
/* 4856 */         if (ordered != null) {
/* 4857 */           ordered.addAll(((Map)entry.getValue()).values());
/*      */         }
/*      */       } 
/*      */     } else {
/* 4861 */       Set<String> groups = new LinkedHashSet<>();
/* 4862 */       TreeMap<String, Candidate> sorted = new TreeMap<>();
/* 4863 */       for (Candidate cand : possible) {
/* 4864 */         String group = cand.group();
/* 4865 */         if (group != null) {
/* 4866 */           groups.add(group);
/*      */         }
/* 4868 */         sorted.put(cand.value(), cand);
/*      */       } 
/* 4870 */       if (autoGroup) {
/* 4871 */         strings.addAll(groups);
/*      */       }
/* 4873 */       strings.add(new ArrayList(sorted.values()));
/* 4874 */       if (ordered != null) {
/* 4875 */         ordered.addAll(sorted.values());
/*      */       }
/*      */     } 
/* 4878 */     return toColumns(strings, selection, completed, wcwidth, width, rowsFirst);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected PostResult toColumns(List<Object> items, Candidate selection, String completed, Function<String, Integer> wcwidth, int width, boolean rowsFirst) {
/* 4888 */     int[] out = new int[2];
/*      */ 
/*      */     
/* 4891 */     int maxWidth = 0;
/* 4892 */     for (Object item : items) {
/* 4893 */       if (item instanceof String) {
/* 4894 */         int len = ((Integer)wcwidth.apply((String)item)).intValue();
/* 4895 */         maxWidth = Math.max(maxWidth, len); continue;
/*      */       } 
/* 4897 */       if (item instanceof List) {
/* 4898 */         for (Candidate cand : item) {
/* 4899 */           int len = ((Integer)wcwidth.apply(cand.displ())).intValue();
/* 4900 */           if (cand.descr() != null) {
/* 4901 */             len++;
/* 4902 */             len += "(".length();
/* 4903 */             len += ((Integer)wcwidth.apply(cand.descr())).intValue();
/* 4904 */             len += ")".length();
/*      */           } 
/* 4906 */           maxWidth = Math.max(maxWidth, len);
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/* 4911 */     AttributedStringBuilder sb = new AttributedStringBuilder();
/* 4912 */     for (Object list : items) {
/* 4913 */       toColumns(list, width, maxWidth, sb, selection, completed, rowsFirst, out);
/*      */     }
/* 4915 */     if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
/* 4916 */       sb.setLength(sb.length() - 1);
/*      */     }
/* 4918 */     return new PostResult(sb.toAttributedString(), out[0], out[1]);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void toColumns(Object items, int width, int maxWidth, AttributedStringBuilder sb, Candidate selection, String completed, boolean rowsFirst, int[] out) {
/* 4923 */     if (maxWidth <= 0 || width <= 0) {
/*      */       return;
/*      */     }
/*      */     
/* 4927 */     if (items instanceof String) {
/* 4928 */       sb.style(getCompletionStyleGroup())
/* 4929 */         .append((String)items)
/* 4930 */         .style(AttributedStyle.DEFAULT)
/* 4931 */         .append("\n");
/* 4932 */       out[0] = out[0] + 1;
/*      */     
/*      */     }
/* 4935 */     else if (items instanceof List) {
/* 4936 */       IntBinaryOperator index; List<Candidate> candidates = (List<Candidate>)items;
/* 4937 */       maxWidth = Math.min(width, maxWidth);
/* 4938 */       int c = width / maxWidth;
/* 4939 */       while (c > 1 && c * maxWidth + (c - 1) * 3 >= width) {
/* 4940 */         c--;
/*      */       }
/* 4942 */       int lines = (candidates.size() + c - 1) / c;
/*      */ 
/*      */       
/* 4945 */       int columns = (candidates.size() + lines - 1) / lines;
/*      */       
/* 4947 */       if (rowsFirst) {
/* 4948 */         index = ((i, j) -> i * columns + j);
/*      */       } else {
/* 4950 */         index = ((i, j) -> j * lines + i);
/*      */       } 
/* 4952 */       for (int i = 0; i < lines; i++) {
/* 4953 */         for (int j = 0; j < columns; j++) {
/* 4954 */           int idx = index.applyAsInt(i, j);
/* 4955 */           if (idx < candidates.size()) {
/* 4956 */             Candidate cand = candidates.get(idx);
/* 4957 */             boolean hasRightItem = (j < columns - 1 && index.applyAsInt(i, j + 1) < candidates.size());
/* 4958 */             AttributedString left = AttributedString.fromAnsi(cand.displ());
/* 4959 */             AttributedString right = AttributedString.fromAnsi(cand.descr());
/* 4960 */             int lw = left.columnLength();
/* 4961 */             int rw = 0;
/* 4962 */             if (right != null) {
/*      */               
/* 4964 */               int rem = maxWidth - lw + 1 + "(".length() + ")".length();
/* 4965 */               rw = right.columnLength();
/* 4966 */               if (rw > rem) {
/* 4967 */                 right = AttributedStringBuilder.append(new CharSequence[] { (CharSequence)right
/* 4968 */                       .columnSubSequence(0, rem - WCWidth.wcwidth(8230)), "…" });
/*      */                 
/* 4970 */                 rw = right.columnLength();
/*      */               } 
/* 4972 */               right = AttributedStringBuilder.append(new CharSequence[] { "(", (CharSequence)right, ")" });
/* 4973 */               rw += "(".length() + ")".length();
/*      */             } 
/* 4975 */             if (cand == selection) {
/* 4976 */               out[1] = i;
/* 4977 */               sb.style(getCompletionStyleSelection());
/* 4978 */               if (left.toString().regionMatches(
/* 4979 */                   isSet(LineReader.Option.CASE_INSENSITIVE), 0, completed, 0, completed.length())) {
/* 4980 */                 sb.append(left.toString(), 0, completed.length());
/* 4981 */                 sb.append(left.toString(), completed.length(), left.length());
/*      */               } else {
/* 4983 */                 sb.append(left.toString());
/*      */               } 
/* 4985 */               for (int k = 0; k < maxWidth - lw - rw; k++) {
/* 4986 */                 sb.append(' ');
/*      */               }
/* 4988 */               if (right != null) {
/* 4989 */                 sb.append(right);
/*      */               }
/* 4991 */               sb.style(AttributedStyle.DEFAULT);
/*      */             } else {
/* 4993 */               if (left.toString().regionMatches(
/* 4994 */                   isSet(LineReader.Option.CASE_INSENSITIVE), 0, completed, 0, completed.length())) {
/* 4995 */                 sb.style(getCompletionStyleStarting());
/* 4996 */                 sb.append(left, 0, completed.length());
/* 4997 */                 sb.style(AttributedStyle.DEFAULT);
/* 4998 */                 sb.append(left, completed.length(), left.length());
/*      */               } else {
/* 5000 */                 sb.append(left);
/*      */               } 
/* 5002 */               if (right != null || hasRightItem) {
/* 5003 */                 for (int k = 0; k < maxWidth - lw - rw; k++) {
/* 5004 */                   sb.append(' ');
/*      */                 }
/*      */               }
/* 5007 */               if (right != null) {
/* 5008 */                 sb.style(getCompletionStyleDescription());
/* 5009 */                 sb.append(right);
/* 5010 */                 sb.style(AttributedStyle.DEFAULT);
/*      */               } 
/*      */             } 
/* 5013 */             if (hasRightItem) {
/* 5014 */               for (int k = 0; k < 3; k++) {
/* 5015 */                 sb.append(' ');
/*      */               }
/*      */             }
/*      */           } 
/*      */         } 
/* 5020 */         sb.append('\n');
/*      */       } 
/* 5022 */       out[0] = out[0] + lines;
/*      */     } 
/*      */   }
/*      */   
/*      */   private AttributedStyle getCompletionStyleStarting() {
/* 5027 */     return getCompletionStyle("COMPLETION_STYLE_STARTING", "36");
/*      */   }
/*      */   
/*      */   protected AttributedStyle getCompletionStyleDescription() {
/* 5031 */     return getCompletionStyle("COMPLETION_STYLE_DESCRIPTION", "90");
/*      */   }
/*      */   
/*      */   protected AttributedStyle getCompletionStyleGroup() {
/* 5035 */     return getCompletionStyle("COMPLETION_STYLE_GROUP", "35;1");
/*      */   }
/*      */   
/*      */   protected AttributedStyle getCompletionStyleSelection() {
/* 5039 */     return getCompletionStyle("COMPLETION_STYLE_SELECTION", "7");
/*      */   }
/*      */   
/*      */   protected AttributedStyle getCompletionStyle(String name, String value) {
/* 5043 */     return buildStyle(getString(name, value));
/*      */   }
/*      */   
/*      */   protected AttributedStyle buildStyle(String str) {
/* 5047 */     return AttributedString.fromAnsi("\033[" + str + "m ").styleAt(0);
/*      */   }
/*      */   
/*      */   private String getCommonStart(String str1, String str2, boolean caseInsensitive) {
/* 5051 */     int[] s1 = str1.codePoints().toArray();
/* 5052 */     int[] s2 = str2.codePoints().toArray();
/* 5053 */     int len = 0;
/* 5054 */     while (len < Math.min(s1.length, s2.length)) {
/* 5055 */       int ch1 = s1[len];
/* 5056 */       int ch2 = s2[len];
/* 5057 */       if (ch1 != ch2 && caseInsensitive) {
/* 5058 */         ch1 = Character.toUpperCase(ch1);
/* 5059 */         ch2 = Character.toUpperCase(ch2);
/* 5060 */         if (ch1 != ch2) {
/* 5061 */           ch1 = Character.toLowerCase(ch1);
/* 5062 */           ch2 = Character.toLowerCase(ch2);
/*      */         } 
/*      */       } 
/* 5065 */       if (ch1 != ch2) {
/*      */         break;
/*      */       }
/* 5068 */       len++;
/*      */     } 
/* 5070 */     return new String(s1, 0, len);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean moveHistory(boolean next, int count) {
/* 5082 */     boolean ok = true;
/* 5083 */     for (int i = 0; i < count && (ok = moveHistory(next)); i++);
/*      */ 
/*      */     
/* 5086 */     return ok;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean moveHistory(boolean next) {
/* 5095 */     if (!this.buf.toString().equals(this.history.current())) {
/* 5096 */       this.modifiedHistory.put(Integer.valueOf(this.history.index()), this.buf.toString());
/*      */     }
/* 5098 */     if (next && !this.history.next()) {
/* 5099 */       return false;
/*      */     }
/* 5101 */     if (!next && !this.history.previous()) {
/* 5102 */       return false;
/*      */     }
/*      */     
/* 5105 */     setBuffer(this.modifiedHistory.containsKey(Integer.valueOf(this.history.index())) ? this.modifiedHistory
/* 5106 */         .get(Integer.valueOf(this.history.index())) : this.history
/* 5107 */         .current());
/*      */     
/* 5109 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void print(String str) {
/* 5121 */     this.terminal.writer().write(str);
/*      */   }
/*      */   
/*      */   void println(String s) {
/* 5125 */     print(s);
/* 5126 */     println();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void println() {
/* 5133 */     this.terminal.puts(InfoCmp.Capability.carriage_return, new Object[0]);
/* 5134 */     print("\n");
/* 5135 */     redrawLine();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean killBuffer() {
/* 5144 */     this.killRing.add(this.buf.toString());
/* 5145 */     this.buf.clear();
/* 5146 */     return true;
/*      */   }
/*      */   protected boolean killWholeLine() {
/*      */     int start, end;
/* 5150 */     if (this.buf.length() == 0) {
/* 5151 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 5155 */     if (this.count < 0) {
/* 5156 */       end = this.buf.cursor();
/* 5157 */       while (this.buf.atChar(end) != 0 && this.buf.atChar(end) != 10) {
/* 5158 */         end++;
/*      */       }
/* 5160 */       start = end;
/* 5161 */       for (int count = -this.count; count > 0; count--) {
/* 5162 */         while (start > 0 && this.buf.atChar(start - 1) != 10) {
/* 5163 */           start--;
/*      */         }
/* 5165 */         start--;
/*      */       } 
/*      */     } else {
/* 5168 */       start = this.buf.cursor();
/* 5169 */       while (start > 0 && this.buf.atChar(start - 1) != 10) {
/* 5170 */         start--;
/*      */       }
/* 5172 */       end = start;
/* 5173 */       while (this.count-- > 0) {
/* 5174 */         while (end < this.buf.length() && this.buf.atChar(end) != 10) {
/* 5175 */           end++;
/*      */         }
/* 5177 */         if (end < this.buf.length()) {
/* 5178 */           end++;
/*      */         }
/*      */       } 
/*      */     } 
/* 5182 */     String killed = this.buf.substring(start, end);
/* 5183 */     this.buf.cursor(start);
/* 5184 */     this.buf.delete(end - start);
/* 5185 */     this.killRing.add(killed);
/* 5186 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean killLine() {
/* 5195 */     if (this.count < 0) {
/* 5196 */       return callNeg(this::backwardKillLine);
/*      */     }
/* 5198 */     if (this.buf.cursor() == this.buf.length()) {
/* 5199 */       return false;
/*      */     }
/* 5201 */     int cp = this.buf.cursor();
/* 5202 */     int len = cp;
/* 5203 */     while (this.count-- > 0) {
/* 5204 */       if (this.buf.atChar(len) == 10) {
/* 5205 */         len++; continue;
/*      */       } 
/* 5207 */       while (this.buf.atChar(len) != 0 && this.buf.atChar(len) != 10) {
/* 5208 */         len++;
/*      */       }
/*      */     } 
/*      */     
/* 5212 */     int num = len - cp;
/* 5213 */     String killed = this.buf.substring(cp, cp + num);
/* 5214 */     this.buf.delete(num);
/* 5215 */     this.killRing.add(killed);
/* 5216 */     return true;
/*      */   }
/*      */   
/*      */   public boolean backwardKillLine() {
/* 5220 */     if (this.count < 0) {
/* 5221 */       return callNeg(this::killLine);
/*      */     }
/* 5223 */     if (this.buf.cursor() == 0) {
/* 5224 */       return false;
/*      */     }
/* 5226 */     int cp = this.buf.cursor();
/* 5227 */     int beg = cp;
/* 5228 */     while (this.count-- > 0 && 
/* 5229 */       beg != 0) {
/*      */ 
/*      */       
/* 5232 */       if (this.buf.atChar(beg - 1) == 10) {
/* 5233 */         beg--; continue;
/*      */       } 
/* 5235 */       while (beg > 0 && this.buf.atChar(beg - 1) != 0 && this.buf.atChar(beg - 1) != 10) {
/* 5236 */         beg--;
/*      */       }
/*      */     } 
/*      */     
/* 5240 */     int num = cp - beg;
/* 5241 */     String killed = this.buf.substring(cp - beg, cp);
/* 5242 */     this.buf.cursor(beg);
/* 5243 */     this.buf.delete(num);
/* 5244 */     this.killRing.add(killed);
/* 5245 */     return true;
/*      */   }
/*      */   
/*      */   public boolean killRegion() {
/* 5249 */     return doCopyKillRegion(true);
/*      */   }
/*      */   
/*      */   public boolean copyRegionAsKill() {
/* 5253 */     return doCopyKillRegion(false);
/*      */   }
/*      */   
/*      */   private boolean doCopyKillRegion(boolean kill) {
/* 5257 */     if (this.regionMark > this.buf.length()) {
/* 5258 */       this.regionMark = this.buf.length();
/*      */     }
/* 5260 */     if (this.regionActive == LineReader.RegionType.LINE) {
/* 5261 */       int start = this.regionMark;
/* 5262 */       int end = this.buf.cursor();
/* 5263 */       if (start < end) {
/* 5264 */         while (start > 0 && this.buf.atChar(start - 1) != 10) {
/* 5265 */           start--;
/*      */         }
/* 5267 */         while (end < this.buf.length() - 1 && this.buf.atChar(end + 1) != 10) {
/* 5268 */           end++;
/*      */         }
/* 5270 */         if (isInViCmdMode()) {
/* 5271 */           end++;
/*      */         }
/* 5273 */         this.killRing.add(this.buf.substring(start, end));
/* 5274 */         if (kill) {
/* 5275 */           this.buf.backspace(end - start);
/*      */         }
/*      */       } else {
/* 5278 */         while (end > 0 && this.buf.atChar(end - 1) != 10) {
/* 5279 */           end--;
/*      */         }
/* 5281 */         while (start < this.buf.length() && this.buf.atChar(start) != 10) {
/* 5282 */           start++;
/*      */         }
/* 5284 */         if (isInViCmdMode()) {
/* 5285 */           start++;
/*      */         }
/* 5287 */         this.killRing.addBackwards(this.buf.substring(end, start));
/* 5288 */         if (kill) {
/* 5289 */           this.buf.cursor(end);
/* 5290 */           this.buf.delete(start - end);
/*      */         } 
/*      */       } 
/* 5293 */     } else if (this.regionMark > this.buf.cursor()) {
/* 5294 */       if (isInViCmdMode()) {
/* 5295 */         this.regionMark++;
/*      */       }
/* 5297 */       this.killRing.add(this.buf.substring(this.buf.cursor(), this.regionMark));
/* 5298 */       if (kill) {
/* 5299 */         this.buf.delete(this.regionMark - this.buf.cursor());
/*      */       }
/*      */     } else {
/* 5302 */       if (isInViCmdMode()) {
/* 5303 */         this.buf.move(1);
/*      */       }
/* 5305 */       this.killRing.add(this.buf.substring(this.regionMark, this.buf.cursor()));
/* 5306 */       if (kill) {
/* 5307 */         this.buf.backspace(this.buf.cursor() - this.regionMark);
/*      */       }
/*      */     } 
/* 5310 */     if (kill) {
/* 5311 */       this.regionActive = LineReader.RegionType.NONE;
/*      */     }
/* 5313 */     return true;
/*      */   }
/*      */   
/*      */   public boolean yank() {
/* 5317 */     String yanked = this.killRing.yank();
/* 5318 */     if (yanked == null) {
/* 5319 */       return false;
/*      */     }
/* 5321 */     putString(yanked);
/* 5322 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean yankPop() {
/* 5327 */     if (!this.killRing.lastYank()) {
/* 5328 */       return false;
/*      */     }
/* 5330 */     String current = this.killRing.yank();
/* 5331 */     if (current == null)
/*      */     {
/* 5333 */       return false;
/*      */     }
/* 5335 */     this.buf.backspace(current.length());
/* 5336 */     String yanked = this.killRing.yankPop();
/* 5337 */     if (yanked == null)
/*      */     {
/* 5339 */       return false;
/*      */     }
/*      */     
/* 5342 */     putString(yanked);
/* 5343 */     return true;
/*      */   }
/*      */   
/*      */   public boolean mouse() {
/* 5347 */     MouseEvent event = readMouseEvent();
/* 5348 */     if (event.getType() == MouseEvent.Type.Released && event
/* 5349 */       .getButton() == MouseEvent.Button.Button1) {
/* 5350 */       StringBuilder tsb = new StringBuilder();
/* 5351 */       Cursor cursor = this.terminal.getCursorPosition(c -> tsb.append((char)c));
/* 5352 */       this.bindingReader.runMacro(tsb.toString());
/*      */       
/* 5354 */       List<AttributedString> secondaryPrompts = new ArrayList<>();
/* 5355 */       getDisplayedBufferWithPrompts(secondaryPrompts);
/*      */       
/* 5357 */       AttributedStringBuilder sb = (new AttributedStringBuilder()).tabs(4);
/* 5358 */       sb.append(this.prompt);
/* 5359 */       sb.append(insertSecondaryPrompts(new AttributedString(this.buf.upToCursor()), secondaryPrompts, false));
/* 5360 */       List<AttributedString> promptLines = sb.columnSplitLength(this.size.getColumns(), false, this.display.delayLineWrap());
/*      */       
/* 5362 */       int currentLine = promptLines.size() - 1;
/* 5363 */       int wantedLine = Math.max(0, Math.min(currentLine + event.getY() - cursor.getY(), secondaryPrompts.size()));
/* 5364 */       int pl0 = (currentLine == 0) ? this.prompt.columnLength() : ((AttributedString)secondaryPrompts.get(currentLine - 1)).columnLength();
/* 5365 */       int pl1 = (wantedLine == 0) ? this.prompt.columnLength() : ((AttributedString)secondaryPrompts.get(wantedLine - 1)).columnLength();
/* 5366 */       int adjust = pl1 - pl0;
/* 5367 */       this.buf.moveXY(event.getX() - cursor.getX() - adjust, event.getY() - cursor.getY());
/*      */     } 
/* 5369 */     return true;
/*      */   }
/*      */   
/*      */   public boolean beginPaste() {
/* 5373 */     Object SELF_INSERT = new Object();
/* 5374 */     Object END_PASTE = new Object();
/* 5375 */     KeyMap<Object> keyMap = new KeyMap();
/* 5376 */     keyMap.setUnicode(SELF_INSERT);
/* 5377 */     keyMap.setNomatch(SELF_INSERT);
/* 5378 */     keyMap.setAmbiguousTimeout(0L);
/* 5379 */     keyMap.bind(END_PASTE, "\033[201~");
/* 5380 */     StringBuilder sb = new StringBuilder();
/*      */     while (true) {
/* 5382 */       Object b = doReadBinding(keyMap, null);
/* 5383 */       if (b == END_PASTE) {
/*      */         break;
/*      */       }
/* 5386 */       String s = getLastBinding();
/* 5387 */       if ("\r".equals(s)) {
/* 5388 */         s = "\n";
/*      */       }
/* 5390 */       sb.append(s);
/*      */     } 
/* 5392 */     this.regionActive = LineReader.RegionType.PASTE;
/* 5393 */     this.regionMark = getBuffer().cursor();
/* 5394 */     getBuffer().write(sb);
/* 5395 */     return true;
/*      */   }
/*      */   
/*      */   public boolean focusIn() {
/* 5399 */     return false;
/*      */   }
/*      */   
/*      */   public boolean focusOut() {
/* 5403 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean clear() {
/* 5411 */     this.display.update(Collections.emptyList(), 0);
/* 5412 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean clearScreen() {
/* 5420 */     if (this.terminal.puts(InfoCmp.Capability.clear_screen, new Object[0])) {
/*      */       
/* 5422 */       if ("windows-conemu".equals(this.terminal.getType()) && 
/* 5423 */         !Boolean.getBoolean("org.jline.terminal.conemu.disable-activate")) {
/* 5424 */         this.terminal.writer().write("\033[9999E");
/*      */       }
/* 5426 */       Status status = Status.getStatus(this.terminal, false);
/* 5427 */       if (status != null) {
/* 5428 */         status.reset();
/*      */       }
/* 5430 */       redrawLine();
/*      */     } else {
/* 5432 */       println();
/*      */     } 
/* 5434 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean beep() {
/* 5442 */     BellType bell_preference = BellType.AUDIBLE;
/* 5443 */     switch (getString("bell-style", "").toLowerCase()) {
/*      */       case "none":
/*      */       case "off":
/* 5446 */         bell_preference = BellType.NONE;
/*      */         break;
/*      */       case "audible":
/* 5449 */         bell_preference = BellType.AUDIBLE;
/*      */         break;
/*      */       case "visible":
/* 5452 */         bell_preference = BellType.VISIBLE;
/*      */         break;
/*      */       case "on":
/* 5455 */         bell_preference = getBoolean("prefer-visible-bell", false) ? BellType.VISIBLE : BellType.AUDIBLE;
/*      */         break;
/*      */     } 
/*      */     
/* 5459 */     if (bell_preference == BellType.VISIBLE) {
/* 5460 */       if (this.terminal.puts(InfoCmp.Capability.flash_screen, new Object[0]) || this.terminal
/* 5461 */         .puts(InfoCmp.Capability.bell, new Object[0])) {
/* 5462 */         flush();
/*      */       }
/* 5464 */     } else if (bell_preference == BellType.AUDIBLE && 
/* 5465 */       this.terminal.puts(InfoCmp.Capability.bell, new Object[0])) {
/* 5466 */       flush();
/*      */     } 
/*      */     
/* 5469 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isDelimiter(int c) {
/* 5484 */     return !Character.isLetterOrDigit(c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isWhitespace(int c) {
/* 5497 */     return Character.isWhitespace(c);
/*      */   }
/*      */   
/*      */   protected boolean isViAlphaNum(int c) {
/* 5501 */     return (c == 95 || Character.isLetterOrDigit(c));
/*      */   }
/*      */   
/*      */   protected boolean isAlpha(int c) {
/* 5505 */     return Character.isLetter(c);
/*      */   }
/*      */   
/*      */   protected boolean isWord(int c) {
/* 5509 */     String wordchars = getString("WORDCHARS", "*?_-.[]~=/&;!#$%^(){}<>");
/* 5510 */     return (Character.isLetterOrDigit(c) || (c < 128 && wordchars
/* 5511 */       .indexOf((char)c) >= 0));
/*      */   }
/*      */   
/*      */   String getString(String name, String def) {
/* 5515 */     return ReaderUtils.getString(this, name, def);
/*      */   }
/*      */   
/*      */   boolean getBoolean(String name, boolean def) {
/* 5519 */     return ReaderUtils.getBoolean(this, name, def);
/*      */   }
/*      */   
/*      */   int getInt(String name, int def) {
/* 5523 */     return ReaderUtils.getInt(this, name, def);
/*      */   }
/*      */   
/*      */   long getLong(String name, long def) {
/* 5527 */     return ReaderUtils.getLong(this, name, def);
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<String, KeyMap<Binding>> defaultKeyMaps() {
/* 5532 */     Map<String, KeyMap<Binding>> keyMaps = new HashMap<>();
/* 5533 */     keyMaps.put("emacs", emacs());
/* 5534 */     keyMaps.put("vicmd", viCmd());
/* 5535 */     keyMaps.put("viins", viInsertion());
/* 5536 */     keyMaps.put("menu", menu());
/* 5537 */     keyMaps.put("viopp", viOpp());
/* 5538 */     keyMaps.put("visual", visual());
/* 5539 */     keyMaps.put(".safe", safe());
/* 5540 */     if (getBoolean("bind-tty-special-chars", true)) {
/* 5541 */       Attributes attr = this.terminal.getAttributes();
/* 5542 */       bindConsoleChars(keyMaps.get("emacs"), attr);
/* 5543 */       bindConsoleChars(keyMaps.get("viins"), attr);
/*      */     } 
/*      */     
/* 5546 */     for (KeyMap<Binding> keyMap : keyMaps.values()) {
/* 5547 */       keyMap.setUnicode(new Reference("self-insert"));
/* 5548 */       keyMap.setAmbiguousTimeout(getLong("ambiguous-binding", 1000L));
/*      */     } 
/*      */     
/* 5551 */     keyMaps.put("main", keyMaps.get("emacs"));
/* 5552 */     return keyMaps;
/*      */   }
/*      */   
/*      */   public KeyMap<Binding> emacs() {
/* 5556 */     KeyMap<Binding> emacs = new KeyMap();
/* 5557 */     bindKeys(emacs);
/* 5558 */     bind(emacs, "set-mark-command", new CharSequence[] { KeyMap.ctrl('@') });
/* 5559 */     bind(emacs, "beginning-of-line", new CharSequence[] { KeyMap.ctrl('A') });
/* 5560 */     bind(emacs, "backward-char", new CharSequence[] { KeyMap.ctrl('B') });
/* 5561 */     bind(emacs, "delete-char-or-list", new CharSequence[] { KeyMap.ctrl('D') });
/* 5562 */     bind(emacs, "end-of-line", new CharSequence[] { KeyMap.ctrl('E') });
/* 5563 */     bind(emacs, "forward-char", new CharSequence[] { KeyMap.ctrl('F') });
/* 5564 */     bind(emacs, "abort", new CharSequence[] { KeyMap.ctrl('G') });
/* 5565 */     bind(emacs, "backward-delete-char", new CharSequence[] { KeyMap.ctrl('H') });
/* 5566 */     bind(emacs, "expand-or-complete", new CharSequence[] { KeyMap.ctrl('I') });
/* 5567 */     bind(emacs, "accept-line", new CharSequence[] { KeyMap.ctrl('J') });
/* 5568 */     bind(emacs, "kill-line", new CharSequence[] { KeyMap.ctrl('K') });
/* 5569 */     bind(emacs, "clear-screen", new CharSequence[] { KeyMap.ctrl('L') });
/* 5570 */     bind(emacs, "accept-line", new CharSequence[] { KeyMap.ctrl('M') });
/* 5571 */     bind(emacs, "down-line-or-history", new CharSequence[] { KeyMap.ctrl('N') });
/* 5572 */     bind(emacs, "accept-line-and-down-history", new CharSequence[] { KeyMap.ctrl('O') });
/* 5573 */     bind(emacs, "up-line-or-history", new CharSequence[] { KeyMap.ctrl('P') });
/* 5574 */     bind(emacs, "history-incremental-search-backward", new CharSequence[] { KeyMap.ctrl('R') });
/* 5575 */     bind(emacs, "history-incremental-search-forward", new CharSequence[] { KeyMap.ctrl('S') });
/* 5576 */     bind(emacs, "transpose-chars", new CharSequence[] { KeyMap.ctrl('T') });
/* 5577 */     bind(emacs, "kill-whole-line", new CharSequence[] { KeyMap.ctrl('U') });
/* 5578 */     bind(emacs, "quoted-insert", new CharSequence[] { KeyMap.ctrl('V') });
/* 5579 */     bind(emacs, "backward-kill-word", new CharSequence[] { KeyMap.ctrl('W') });
/* 5580 */     bind(emacs, "yank", new CharSequence[] { KeyMap.ctrl('Y') });
/* 5581 */     bind(emacs, "character-search", new CharSequence[] { KeyMap.ctrl(']') });
/* 5582 */     bind(emacs, "undo", new CharSequence[] { KeyMap.ctrl('_') });
/* 5583 */     bind(emacs, "self-insert", KeyMap.range(" -~"));
/* 5584 */     bind(emacs, "insert-close-paren", new CharSequence[] { ")" });
/* 5585 */     bind(emacs, "insert-close-square", new CharSequence[] { "]" });
/* 5586 */     bind(emacs, "insert-close-curly", new CharSequence[] { "}" });
/* 5587 */     bind(emacs, "backward-delete-char", new CharSequence[] { KeyMap.del() });
/* 5588 */     bind(emacs, "vi-match-bracket", new CharSequence[] { KeyMap.translate("^X^B") });
/* 5589 */     bind(emacs, "abort", new CharSequence[] { KeyMap.translate("^X^G") });
/* 5590 */     bind(emacs, "vi-find-next-char", new CharSequence[] { KeyMap.translate("^X^F") });
/* 5591 */     bind(emacs, "vi-join", new CharSequence[] { KeyMap.translate("^X^J") });
/* 5592 */     bind(emacs, "kill-buffer", new CharSequence[] { KeyMap.translate("^X^K") });
/* 5593 */     bind(emacs, "infer-next-history", new CharSequence[] { KeyMap.translate("^X^N") });
/* 5594 */     bind(emacs, "overwrite-mode", new CharSequence[] { KeyMap.translate("^X^O") });
/* 5595 */     bind(emacs, "redo", new CharSequence[] { KeyMap.translate("^X^R") });
/* 5596 */     bind(emacs, "undo", new CharSequence[] { KeyMap.translate("^X^U") });
/* 5597 */     bind(emacs, "vi-cmd-mode", new CharSequence[] { KeyMap.translate("^X^V") });
/* 5598 */     bind(emacs, "exchange-point-and-mark", new CharSequence[] { KeyMap.translate("^X^X") });
/* 5599 */     bind(emacs, "do-lowercase-version", new CharSequence[] { KeyMap.translate("^XA-^XZ") });
/* 5600 */     bind(emacs, "what-cursor-position", new CharSequence[] { KeyMap.translate("^X=") });
/* 5601 */     bind(emacs, "kill-line", new CharSequence[] { KeyMap.translate("^X^?") });
/* 5602 */     bind(emacs, "abort", new CharSequence[] { KeyMap.alt(KeyMap.ctrl('G')) });
/* 5603 */     bind(emacs, "backward-kill-word", new CharSequence[] { KeyMap.alt(KeyMap.ctrl('H')) });
/* 5604 */     bind(emacs, "self-insert-unmeta", new CharSequence[] { KeyMap.alt(KeyMap.ctrl('M')) });
/* 5605 */     bind(emacs, "complete-word", new CharSequence[] { KeyMap.alt(KeyMap.esc()) });
/* 5606 */     bind(emacs, "character-search-backward", new CharSequence[] { KeyMap.alt(KeyMap.ctrl(']')) });
/* 5607 */     bind(emacs, "copy-prev-word", new CharSequence[] { KeyMap.alt(KeyMap.ctrl('_')) });
/* 5608 */     bind(emacs, "set-mark-command", new CharSequence[] { KeyMap.alt(' ') });
/* 5609 */     bind(emacs, "neg-argument", new CharSequence[] { KeyMap.alt('-') });
/* 5610 */     bind(emacs, "digit-argument", KeyMap.range("\\E0-\\E9"));
/* 5611 */     bind(emacs, "beginning-of-history", new CharSequence[] { KeyMap.alt('<') });
/* 5612 */     bind(emacs, "list-choices", new CharSequence[] { KeyMap.alt('=') });
/* 5613 */     bind(emacs, "end-of-history", new CharSequence[] { KeyMap.alt('>') });
/* 5614 */     bind(emacs, "list-choices", new CharSequence[] { KeyMap.alt('?') });
/* 5615 */     bind(emacs, "do-lowercase-version", KeyMap.range("^[A-^[Z"));
/* 5616 */     bind(emacs, "accept-and-hold", new CharSequence[] { KeyMap.alt('a') });
/* 5617 */     bind(emacs, "backward-word", new CharSequence[] { KeyMap.alt('b') });
/* 5618 */     bind(emacs, "capitalize-word", new CharSequence[] { KeyMap.alt('c') });
/* 5619 */     bind(emacs, "kill-word", new CharSequence[] { KeyMap.alt('d') });
/* 5620 */     bind(emacs, "kill-word", new CharSequence[] { KeyMap.translate("^[[3;5~") });
/* 5621 */     bind(emacs, "forward-word", new CharSequence[] { KeyMap.alt('f') });
/* 5622 */     bind(emacs, "down-case-word", new CharSequence[] { KeyMap.alt('l') });
/* 5623 */     bind(emacs, "history-search-forward", new CharSequence[] { KeyMap.alt('n') });
/* 5624 */     bind(emacs, "history-search-backward", new CharSequence[] { KeyMap.alt('p') });
/* 5625 */     bind(emacs, "transpose-words", new CharSequence[] { KeyMap.alt('t') });
/* 5626 */     bind(emacs, "up-case-word", new CharSequence[] { KeyMap.alt('u') });
/* 5627 */     bind(emacs, "yank-pop", new CharSequence[] { KeyMap.alt('y') });
/* 5628 */     bind(emacs, "backward-kill-word", new CharSequence[] { KeyMap.alt(KeyMap.del()) });
/* 5629 */     bindArrowKeys(emacs);
/* 5630 */     bind(emacs, "forward-word", new CharSequence[] { KeyMap.translate("^[[1;5C") });
/* 5631 */     bind(emacs, "backward-word", new CharSequence[] { KeyMap.translate("^[[1;5D") });
/* 5632 */     bind(emacs, "forward-word", new CharSequence[] { KeyMap.alt(key(InfoCmp.Capability.key_right)) });
/* 5633 */     bind(emacs, "backward-word", new CharSequence[] { KeyMap.alt(key(InfoCmp.Capability.key_left)) });
/* 5634 */     bind(emacs, "forward-word", new CharSequence[] { KeyMap.alt(KeyMap.translate("^[[C")) });
/* 5635 */     bind(emacs, "backward-word", new CharSequence[] { KeyMap.alt(KeyMap.translate("^[[D")) });
/* 5636 */     return emacs;
/*      */   }
/*      */   
/*      */   public KeyMap<Binding> viInsertion() {
/* 5640 */     KeyMap<Binding> viins = new KeyMap();
/* 5641 */     bindKeys(viins);
/* 5642 */     bind(viins, "self-insert", KeyMap.range("^@-^_"));
/* 5643 */     bind(viins, "list-choices", new CharSequence[] { KeyMap.ctrl('D') });
/* 5644 */     bind(viins, "abort", new CharSequence[] { KeyMap.ctrl('G') });
/* 5645 */     bind(viins, "backward-delete-char", new CharSequence[] { KeyMap.ctrl('H') });
/* 5646 */     bind(viins, "expand-or-complete", new CharSequence[] { KeyMap.ctrl('I') });
/* 5647 */     bind(viins, "accept-line", new CharSequence[] { KeyMap.ctrl('J') });
/* 5648 */     bind(viins, "clear-screen", new CharSequence[] { KeyMap.ctrl('L') });
/* 5649 */     bind(viins, "accept-line", new CharSequence[] { KeyMap.ctrl('M') });
/* 5650 */     bind(viins, "menu-complete", new CharSequence[] { KeyMap.ctrl('N') });
/* 5651 */     bind(viins, "reverse-menu-complete", new CharSequence[] { KeyMap.ctrl('P') });
/* 5652 */     bind(viins, "history-incremental-search-backward", new CharSequence[] { KeyMap.ctrl('R') });
/* 5653 */     bind(viins, "history-incremental-search-forward", new CharSequence[] { KeyMap.ctrl('S') });
/* 5654 */     bind(viins, "transpose-chars", new CharSequence[] { KeyMap.ctrl('T') });
/* 5655 */     bind(viins, "kill-whole-line", new CharSequence[] { KeyMap.ctrl('U') });
/* 5656 */     bind(viins, "quoted-insert", new CharSequence[] { KeyMap.ctrl('V') });
/* 5657 */     bind(viins, "backward-kill-word", new CharSequence[] { KeyMap.ctrl('W') });
/* 5658 */     bind(viins, "yank", new CharSequence[] { KeyMap.ctrl('Y') });
/* 5659 */     bind(viins, "vi-cmd-mode", new CharSequence[] { KeyMap.ctrl('[') });
/* 5660 */     bind(viins, "undo", new CharSequence[] { KeyMap.ctrl('_') });
/* 5661 */     bind(viins, "history-incremental-search-backward", new CharSequence[] { KeyMap.ctrl('X') + "r" });
/* 5662 */     bind(viins, "history-incremental-search-forward", new CharSequence[] { KeyMap.ctrl('X') + "s" });
/* 5663 */     bind(viins, "self-insert", KeyMap.range(" -~"));
/* 5664 */     bind(viins, "insert-close-paren", new CharSequence[] { ")" });
/* 5665 */     bind(viins, "insert-close-square", new CharSequence[] { "]" });
/* 5666 */     bind(viins, "insert-close-curly", new CharSequence[] { "}" });
/* 5667 */     bind(viins, "backward-delete-char", new CharSequence[] { KeyMap.del() });
/* 5668 */     bindArrowKeys(viins);
/* 5669 */     return viins;
/*      */   }
/*      */   
/*      */   public KeyMap<Binding> viCmd() {
/* 5673 */     KeyMap<Binding> vicmd = new KeyMap();
/* 5674 */     bind(vicmd, "list-choices", new CharSequence[] { KeyMap.ctrl('D') });
/* 5675 */     bind(vicmd, "emacs-editing-mode", new CharSequence[] { KeyMap.ctrl('E') });
/* 5676 */     bind(vicmd, "abort", new CharSequence[] { KeyMap.ctrl('G') });
/* 5677 */     bind(vicmd, "vi-backward-char", new CharSequence[] { KeyMap.ctrl('H') });
/* 5678 */     bind(vicmd, "accept-line", new CharSequence[] { KeyMap.ctrl('J') });
/* 5679 */     bind(vicmd, "kill-line", new CharSequence[] { KeyMap.ctrl('K') });
/* 5680 */     bind(vicmd, "clear-screen", new CharSequence[] { KeyMap.ctrl('L') });
/* 5681 */     bind(vicmd, "accept-line", new CharSequence[] { KeyMap.ctrl('M') });
/* 5682 */     bind(vicmd, "vi-down-line-or-history", new CharSequence[] { KeyMap.ctrl('N') });
/* 5683 */     bind(vicmd, "vi-up-line-or-history", new CharSequence[] { KeyMap.ctrl('P') });
/* 5684 */     bind(vicmd, "quoted-insert", new CharSequence[] { KeyMap.ctrl('Q') });
/* 5685 */     bind(vicmd, "history-incremental-search-backward", new CharSequence[] { KeyMap.ctrl('R') });
/* 5686 */     bind(vicmd, "history-incremental-search-forward", new CharSequence[] { KeyMap.ctrl('S') });
/* 5687 */     bind(vicmd, "transpose-chars", new CharSequence[] { KeyMap.ctrl('T') });
/* 5688 */     bind(vicmd, "kill-whole-line", new CharSequence[] { KeyMap.ctrl('U') });
/* 5689 */     bind(vicmd, "quoted-insert", new CharSequence[] { KeyMap.ctrl('V') });
/* 5690 */     bind(vicmd, "backward-kill-word", new CharSequence[] { KeyMap.ctrl('W') });
/* 5691 */     bind(vicmd, "yank", new CharSequence[] { KeyMap.ctrl('Y') });
/* 5692 */     bind(vicmd, "history-incremental-search-backward", new CharSequence[] { KeyMap.ctrl('X') + "r" });
/* 5693 */     bind(vicmd, "history-incremental-search-forward", new CharSequence[] { KeyMap.ctrl('X') + "s" });
/* 5694 */     bind(vicmd, "abort", new CharSequence[] { KeyMap.alt(KeyMap.ctrl('G')) });
/* 5695 */     bind(vicmd, "backward-kill-word", new CharSequence[] { KeyMap.alt(KeyMap.ctrl('H')) });
/* 5696 */     bind(vicmd, "self-insert-unmeta", new CharSequence[] { KeyMap.alt(KeyMap.ctrl('M')) });
/* 5697 */     bind(vicmd, "complete-word", new CharSequence[] { KeyMap.alt(KeyMap.esc()) });
/* 5698 */     bind(vicmd, "character-search-backward", new CharSequence[] { KeyMap.alt(KeyMap.ctrl(']')) });
/* 5699 */     bind(vicmd, "set-mark-command", new CharSequence[] { KeyMap.alt(' ') });
/*      */ 
/*      */     
/* 5702 */     bind(vicmd, "digit-argument", new CharSequence[] { KeyMap.alt('-') });
/* 5703 */     bind(vicmd, "beginning-of-history", new CharSequence[] { KeyMap.alt('<') });
/* 5704 */     bind(vicmd, "list-choices", new CharSequence[] { KeyMap.alt('=') });
/* 5705 */     bind(vicmd, "end-of-history", new CharSequence[] { KeyMap.alt('>') });
/* 5706 */     bind(vicmd, "list-choices", new CharSequence[] { KeyMap.alt('?') });
/* 5707 */     bind(vicmd, "do-lowercase-version", KeyMap.range("^[A-^[Z"));
/* 5708 */     bind(vicmd, "backward-word", new CharSequence[] { KeyMap.alt('b') });
/* 5709 */     bind(vicmd, "capitalize-word", new CharSequence[] { KeyMap.alt('c') });
/* 5710 */     bind(vicmd, "kill-word", new CharSequence[] { KeyMap.alt('d') });
/* 5711 */     bind(vicmd, "forward-word", new CharSequence[] { KeyMap.alt('f') });
/* 5712 */     bind(vicmd, "down-case-word", new CharSequence[] { KeyMap.alt('l') });
/* 5713 */     bind(vicmd, "history-search-forward", new CharSequence[] { KeyMap.alt('n') });
/* 5714 */     bind(vicmd, "history-search-backward", new CharSequence[] { KeyMap.alt('p') });
/* 5715 */     bind(vicmd, "transpose-words", new CharSequence[] { KeyMap.alt('t') });
/* 5716 */     bind(vicmd, "up-case-word", new CharSequence[] { KeyMap.alt('u') });
/* 5717 */     bind(vicmd, "yank-pop", new CharSequence[] { KeyMap.alt('y') });
/* 5718 */     bind(vicmd, "backward-kill-word", new CharSequence[] { KeyMap.alt(KeyMap.del()) });
/*      */     
/* 5720 */     bind(vicmd, "forward-char", new CharSequence[] { " " });
/* 5721 */     bind(vicmd, "vi-insert-comment", new CharSequence[] { "#" });
/* 5722 */     bind(vicmd, "end-of-line", new CharSequence[] { "$" });
/* 5723 */     bind(vicmd, "vi-match-bracket", new CharSequence[] { "%" });
/* 5724 */     bind(vicmd, "vi-down-line-or-history", new CharSequence[] { "+" });
/* 5725 */     bind(vicmd, "vi-rev-repeat-find", new CharSequence[] { "," });
/* 5726 */     bind(vicmd, "vi-up-line-or-history", new CharSequence[] { "-" });
/* 5727 */     bind(vicmd, "vi-repeat-change", new CharSequence[] { "." });
/* 5728 */     bind(vicmd, "vi-history-search-backward", new CharSequence[] { "/" });
/* 5729 */     bind(vicmd, "vi-digit-or-beginning-of-line", new CharSequence[] { "0" });
/* 5730 */     bind(vicmd, "digit-argument", KeyMap.range("1-9"));
/* 5731 */     bind(vicmd, "vi-repeat-find", new CharSequence[] { ";" });
/* 5732 */     bind(vicmd, "list-choices", new CharSequence[] { "=" });
/* 5733 */     bind(vicmd, "vi-history-search-forward", new CharSequence[] { "?" });
/* 5734 */     bind(vicmd, "vi-add-eol", new CharSequence[] { "A" });
/* 5735 */     bind(vicmd, "vi-backward-blank-word", new CharSequence[] { "B" });
/* 5736 */     bind(vicmd, "vi-change-eol", new CharSequence[] { "C" });
/* 5737 */     bind(vicmd, "vi-kill-eol", new CharSequence[] { "D" });
/* 5738 */     bind(vicmd, "vi-forward-blank-word-end", new CharSequence[] { "E" });
/* 5739 */     bind(vicmd, "vi-find-prev-char", new CharSequence[] { "F" });
/* 5740 */     bind(vicmd, "vi-fetch-history", new CharSequence[] { "G" });
/* 5741 */     bind(vicmd, "vi-insert-bol", new CharSequence[] { "I" });
/* 5742 */     bind(vicmd, "vi-join", new CharSequence[] { "J" });
/* 5743 */     bind(vicmd, "vi-rev-repeat-search", new CharSequence[] { "N" });
/* 5744 */     bind(vicmd, "vi-open-line-above", new CharSequence[] { "O" });
/* 5745 */     bind(vicmd, "vi-put-before", new CharSequence[] { "P" });
/* 5746 */     bind(vicmd, "vi-replace", new CharSequence[] { "R" });
/* 5747 */     bind(vicmd, "vi-kill-line", new CharSequence[] { "S" });
/* 5748 */     bind(vicmd, "vi-find-prev-char-skip", new CharSequence[] { "T" });
/* 5749 */     bind(vicmd, "redo", new CharSequence[] { "U" });
/* 5750 */     bind(vicmd, "visual-line-mode", new CharSequence[] { "V" });
/* 5751 */     bind(vicmd, "vi-forward-blank-word", new CharSequence[] { "W" });
/* 5752 */     bind(vicmd, "vi-backward-delete-char", new CharSequence[] { "X" });
/* 5753 */     bind(vicmd, "vi-yank-whole-line", new CharSequence[] { "Y" });
/* 5754 */     bind(vicmd, "vi-first-non-blank", new CharSequence[] { "^" });
/* 5755 */     bind(vicmd, "vi-add-next", new CharSequence[] { "a" });
/* 5756 */     bind(vicmd, "vi-backward-word", new CharSequence[] { "b" });
/* 5757 */     bind(vicmd, "vi-change-to", new CharSequence[] { "c" });
/* 5758 */     bind(vicmd, "vi-delete", new CharSequence[] { "d" });
/* 5759 */     bind(vicmd, "vi-forward-word-end", new CharSequence[] { "e" });
/* 5760 */     bind(vicmd, "vi-find-next-char", new CharSequence[] { "f" });
/* 5761 */     bind(vicmd, "what-cursor-position", new CharSequence[] { "ga" });
/* 5762 */     bind(vicmd, "vi-backward-blank-word-end", new CharSequence[] { "gE" });
/* 5763 */     bind(vicmd, "vi-backward-word-end", new CharSequence[] { "ge" });
/* 5764 */     bind(vicmd, "vi-backward-char", new CharSequence[] { "h" });
/* 5765 */     bind(vicmd, "vi-insert", new CharSequence[] { "i" });
/* 5766 */     bind(vicmd, "down-line-or-history", new CharSequence[] { "j" });
/* 5767 */     bind(vicmd, "up-line-or-history", new CharSequence[] { "k" });
/* 5768 */     bind(vicmd, "vi-forward-char", new CharSequence[] { "l" });
/* 5769 */     bind(vicmd, "vi-repeat-search", new CharSequence[] { "n" });
/* 5770 */     bind(vicmd, "vi-open-line-below", new CharSequence[] { "o" });
/* 5771 */     bind(vicmd, "vi-put-after", new CharSequence[] { "p" });
/* 5772 */     bind(vicmd, "vi-replace-chars", new CharSequence[] { "r" });
/* 5773 */     bind(vicmd, "vi-substitute", new CharSequence[] { "s" });
/* 5774 */     bind(vicmd, "vi-find-next-char-skip", new CharSequence[] { "t" });
/* 5775 */     bind(vicmd, "undo", new CharSequence[] { "u" });
/* 5776 */     bind(vicmd, "visual-mode", new CharSequence[] { "v" });
/* 5777 */     bind(vicmd, "vi-forward-word", new CharSequence[] { "w" });
/* 5778 */     bind(vicmd, "vi-delete-char", new CharSequence[] { "x" });
/* 5779 */     bind(vicmd, "vi-yank", new CharSequence[] { "y" });
/* 5780 */     bind(vicmd, "vi-goto-column", new CharSequence[] { "|" });
/* 5781 */     bind(vicmd, "vi-swap-case", new CharSequence[] { "~" });
/* 5782 */     bind(vicmd, "vi-backward-char", new CharSequence[] { KeyMap.del() });
/*      */     
/* 5784 */     bindArrowKeys(vicmd);
/* 5785 */     return vicmd;
/*      */   }
/*      */   
/*      */   public KeyMap<Binding> menu() {
/* 5789 */     KeyMap<Binding> menu = new KeyMap();
/* 5790 */     bind(menu, "menu-complete", new CharSequence[] { "\t" });
/* 5791 */     bind(menu, "reverse-menu-complete", new CharSequence[] { key(InfoCmp.Capability.back_tab) });
/* 5792 */     bind(menu, "accept-line", new CharSequence[] { "\r", "\n" });
/* 5793 */     bindArrowKeys(menu);
/* 5794 */     return menu;
/*      */   }
/*      */   
/*      */   public KeyMap<Binding> safe() {
/* 5798 */     KeyMap<Binding> safe = new KeyMap();
/* 5799 */     bind(safe, "self-insert", KeyMap.range("^@-^?"));
/* 5800 */     bind(safe, "accept-line", new CharSequence[] { "\r", "\n" });
/* 5801 */     bind(safe, "abort", new CharSequence[] { KeyMap.ctrl('G') });
/* 5802 */     return safe;
/*      */   }
/*      */   
/*      */   public KeyMap<Binding> visual() {
/* 5806 */     KeyMap<Binding> visual = new KeyMap();
/* 5807 */     bind(visual, "up-line", new CharSequence[] { key(InfoCmp.Capability.key_up), "k" });
/* 5808 */     bind(visual, "down-line", new CharSequence[] { key(InfoCmp.Capability.key_down), "j" });
/* 5809 */     bind(visual, this::deactivateRegion, new CharSequence[] { KeyMap.esc() });
/* 5810 */     bind(visual, "exchange-point-and-mark", new CharSequence[] { "o" });
/* 5811 */     bind(visual, "put-replace-selection", new CharSequence[] { "p" });
/* 5812 */     bind(visual, "vi-delete", new CharSequence[] { "x" });
/* 5813 */     bind(visual, "vi-oper-swap-case", new CharSequence[] { "~" });
/* 5814 */     return visual;
/*      */   }
/*      */   
/*      */   public KeyMap<Binding> viOpp() {
/* 5818 */     KeyMap<Binding> viOpp = new KeyMap();
/* 5819 */     bind(viOpp, "up-line", new CharSequence[] { key(InfoCmp.Capability.key_up), "k" });
/* 5820 */     bind(viOpp, "down-line", new CharSequence[] { key(InfoCmp.Capability.key_down), "j" });
/* 5821 */     bind(viOpp, "vi-cmd-mode", new CharSequence[] { KeyMap.esc() });
/* 5822 */     return viOpp;
/*      */   }
/*      */   
/*      */   private void bind(KeyMap<Binding> map, String widget, Iterable<? extends CharSequence> keySeqs) {
/* 5826 */     map.bind(new Reference(widget), keySeqs);
/*      */   }
/*      */   
/*      */   private void bind(KeyMap<Binding> map, String widget, CharSequence... keySeqs) {
/* 5830 */     map.bind(new Reference(widget), keySeqs);
/*      */   }
/*      */   
/*      */   private void bind(KeyMap<Binding> map, Widget widget, CharSequence... keySeqs) {
/* 5834 */     map.bind(widget, keySeqs);
/*      */   }
/*      */   
/*      */   private String key(InfoCmp.Capability capability) {
/* 5838 */     return KeyMap.key(this.terminal, capability);
/*      */   }
/*      */   
/*      */   private void bindKeys(KeyMap<Binding> emacs) {
/* 5842 */     Widget beep = namedWidget("beep", this::beep);
/* 5843 */     Stream.<InfoCmp.Capability>of(InfoCmp.Capability.values())
/* 5844 */       .filter(c -> c.name().startsWith("key_"))
/* 5845 */       .map(this::key)
/* 5846 */       .forEach(k -> bind(emacs, beep, new CharSequence[] { k }));
/*      */   }
/*      */   
/*      */   private void bindArrowKeys(KeyMap<Binding> map) {
/* 5850 */     bind(map, "up-line-or-search", new CharSequence[] { key(InfoCmp.Capability.key_up) });
/* 5851 */     bind(map, "down-line-or-search", new CharSequence[] { key(InfoCmp.Capability.key_down) });
/* 5852 */     bind(map, "backward-char", new CharSequence[] { key(InfoCmp.Capability.key_left) });
/* 5853 */     bind(map, "forward-char", new CharSequence[] { key(InfoCmp.Capability.key_right) });
/* 5854 */     bind(map, "beginning-of-line", new CharSequence[] { key(InfoCmp.Capability.key_home) });
/* 5855 */     bind(map, "end-of-line", new CharSequence[] { key(InfoCmp.Capability.key_end) });
/* 5856 */     bind(map, "delete-char", new CharSequence[] { key(InfoCmp.Capability.key_dc) });
/* 5857 */     bind(map, "kill-whole-line", new CharSequence[] { key(InfoCmp.Capability.key_dl) });
/* 5858 */     bind(map, "overwrite-mode", new CharSequence[] { key(InfoCmp.Capability.key_ic) });
/* 5859 */     bind(map, "mouse", new CharSequence[] { key(InfoCmp.Capability.key_mouse) });
/* 5860 */     bind(map, "begin-paste", new CharSequence[] { "\033[200~" });
/* 5861 */     bind(map, "terminal-focus-in", new CharSequence[] { "\033[I" });
/* 5862 */     bind(map, "terminal-focus-out", new CharSequence[] { "\033[O" });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void bindConsoleChars(KeyMap<Binding> keyMap, Attributes attr) {
/* 5870 */     if (attr != null) {
/* 5871 */       rebind(keyMap, "backward-delete-char", 
/* 5872 */           KeyMap.del(), (char)attr.getControlChar(Attributes.ControlChar.VERASE));
/* 5873 */       rebind(keyMap, "backward-kill-word", 
/* 5874 */           KeyMap.ctrl('W'), (char)attr.getControlChar(Attributes.ControlChar.VWERASE));
/* 5875 */       rebind(keyMap, "kill-whole-line", 
/* 5876 */           KeyMap.ctrl('U'), (char)attr.getControlChar(Attributes.ControlChar.VKILL));
/* 5877 */       rebind(keyMap, "quoted-insert", 
/* 5878 */           KeyMap.ctrl('V'), (char)attr.getControlChar(Attributes.ControlChar.VLNEXT));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void rebind(KeyMap<Binding> keyMap, String operation, String prevBinding, char newBinding) {
/* 5883 */     if (newBinding > '\000' && newBinding < '') {
/* 5884 */       Reference ref = new Reference(operation);
/* 5885 */       bind(keyMap, "self-insert", new CharSequence[] { prevBinding });
/* 5886 */       keyMap.bind(ref, Character.toString(newBinding));
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\LineReaderImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */