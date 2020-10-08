/*      */ package org.yaml.snakeyaml.emitter;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.Writer;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Queue;
/*      */ import java.util.Set;
/*      */ import java.util.TreeSet;
/*      */ import java.util.concurrent.ArrayBlockingQueue;
/*      */ import java.util.regex.Pattern;
/*      */ import org.yaml.snakeyaml.DumperOptions;
/*      */ import org.yaml.snakeyaml.error.YAMLException;
/*      */ import org.yaml.snakeyaml.events.CollectionStartEvent;
/*      */ import org.yaml.snakeyaml.events.DocumentEndEvent;
/*      */ import org.yaml.snakeyaml.events.DocumentStartEvent;
/*      */ import org.yaml.snakeyaml.events.Event;
/*      */ import org.yaml.snakeyaml.events.MappingStartEvent;
/*      */ import org.yaml.snakeyaml.events.NodeEvent;
/*      */ import org.yaml.snakeyaml.events.ScalarEvent;
/*      */ import org.yaml.snakeyaml.events.SequenceStartEvent;
/*      */ import org.yaml.snakeyaml.reader.StreamReader;
/*      */ import org.yaml.snakeyaml.scanner.Constant;
/*      */ import org.yaml.snakeyaml.util.ArrayStack;
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
/*      */ public final class Emitter
/*      */   implements Emitable
/*      */ {
/*   63 */   private static final Map<Character, String> ESCAPE_REPLACEMENTS = new HashMap<>();
/*      */   
/*      */   public static final int MIN_INDENT = 1;
/*      */   public static final int MAX_INDENT = 10;
/*   67 */   private static final char[] SPACE = new char[] { ' ' };
/*      */   
/*      */   static {
/*   70 */     ESCAPE_REPLACEMENTS.put(Character.valueOf(false), "0");
/*   71 */     ESCAPE_REPLACEMENTS.put(Character.valueOf('\007'), "a");
/*   72 */     ESCAPE_REPLACEMENTS.put(Character.valueOf('\b'), "b");
/*   73 */     ESCAPE_REPLACEMENTS.put(Character.valueOf('\t'), "t");
/*   74 */     ESCAPE_REPLACEMENTS.put(Character.valueOf('\n'), "n");
/*   75 */     ESCAPE_REPLACEMENTS.put(Character.valueOf('\013'), "v");
/*   76 */     ESCAPE_REPLACEMENTS.put(Character.valueOf('\f'), "f");
/*   77 */     ESCAPE_REPLACEMENTS.put(Character.valueOf('\r'), "r");
/*   78 */     ESCAPE_REPLACEMENTS.put(Character.valueOf('\033'), "e");
/*   79 */     ESCAPE_REPLACEMENTS.put(Character.valueOf('"'), "\"");
/*   80 */     ESCAPE_REPLACEMENTS.put(Character.valueOf('\\'), "\\");
/*   81 */     ESCAPE_REPLACEMENTS.put(Character.valueOf(''), "N");
/*   82 */     ESCAPE_REPLACEMENTS.put(Character.valueOf(' '), "_");
/*   83 */     ESCAPE_REPLACEMENTS.put(Character.valueOf(' '), "L");
/*   84 */     ESCAPE_REPLACEMENTS.put(Character.valueOf(' '), "P");
/*      */   }
/*      */   
/*   87 */   private static final Map<String, String> DEFAULT_TAG_PREFIXES = new LinkedHashMap<>();
/*      */   static {
/*   89 */     DEFAULT_TAG_PREFIXES.put("!", "!");
/*   90 */     DEFAULT_TAG_PREFIXES.put("tag:yaml.org,2002:", "!!");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final Writer stream;
/*      */ 
/*      */   
/*      */   private final ArrayStack<EmitterState> states;
/*      */ 
/*      */   
/*      */   private EmitterState state;
/*      */   
/*      */   private final Queue<Event> events;
/*      */   
/*      */   private Event event;
/*      */   
/*      */   private final ArrayStack<Integer> indents;
/*      */   
/*      */   private Integer indent;
/*      */   
/*      */   private int flowLevel;
/*      */   
/*      */   private boolean rootContext;
/*      */   
/*      */   private boolean mappingContext;
/*      */   
/*      */   private boolean simpleKeyContext;
/*      */   
/*      */   private int column;
/*      */   
/*      */   private boolean whitespace;
/*      */   
/*      */   private boolean indention;
/*      */   
/*      */   private boolean openEnded;
/*      */   
/*      */   private Boolean canonical;
/*      */   
/*      */   private Boolean prettyFlow;
/*      */   
/*      */   private boolean allowUnicode;
/*      */   
/*      */   private int bestIndent;
/*      */   
/*      */   private int indicatorIndent;
/*      */   
/*      */   private int bestWidth;
/*      */   
/*      */   private char[] bestLineBreak;
/*      */   
/*      */   private boolean splitLines;
/*      */   
/*      */   private int maxSimpleKeyLength;
/*      */   
/*      */   private Map<String, String> tagPrefixes;
/*      */   
/*      */   private String preparedAnchor;
/*      */   
/*      */   private String preparedTag;
/*      */   
/*      */   private ScalarAnalysis analysis;
/*      */   
/*      */   private DumperOptions.ScalarStyle style;
/*      */ 
/*      */   
/*      */   public Emitter(Writer stream, DumperOptions opts) {
/*  157 */     this.stream = stream;
/*      */ 
/*      */     
/*  160 */     this.states = new ArrayStack(100);
/*  161 */     this.state = new ExpectStreamStart();
/*      */     
/*  163 */     this.events = new ArrayBlockingQueue<>(100);
/*  164 */     this.event = null;
/*      */     
/*  166 */     this.indents = new ArrayStack(10);
/*  167 */     this.indent = null;
/*      */     
/*  169 */     this.flowLevel = 0;
/*      */     
/*  171 */     this.mappingContext = false;
/*  172 */     this.simpleKeyContext = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  180 */     this.column = 0;
/*  181 */     this.whitespace = true;
/*  182 */     this.indention = true;
/*      */ 
/*      */     
/*  185 */     this.openEnded = false;
/*      */ 
/*      */     
/*  188 */     this.canonical = Boolean.valueOf(opts.isCanonical());
/*  189 */     this.prettyFlow = Boolean.valueOf(opts.isPrettyFlow());
/*  190 */     this.allowUnicode = opts.isAllowUnicode();
/*  191 */     this.bestIndent = 2;
/*  192 */     if (opts.getIndent() > 1 && opts.getIndent() < 10) {
/*  193 */       this.bestIndent = opts.getIndent();
/*      */     }
/*  195 */     this.indicatorIndent = opts.getIndicatorIndent();
/*  196 */     this.bestWidth = 80;
/*  197 */     if (opts.getWidth() > this.bestIndent * 2) {
/*  198 */       this.bestWidth = opts.getWidth();
/*      */     }
/*  200 */     this.bestLineBreak = opts.getLineBreak().getString().toCharArray();
/*  201 */     this.splitLines = opts.getSplitLines();
/*  202 */     this.maxSimpleKeyLength = opts.getMaxSimpleKeyLength();
/*      */ 
/*      */     
/*  205 */     this.tagPrefixes = new LinkedHashMap<>();
/*      */ 
/*      */     
/*  208 */     this.preparedAnchor = null;
/*  209 */     this.preparedTag = null;
/*      */ 
/*      */     
/*  212 */     this.analysis = null;
/*  213 */     this.style = null;
/*      */   }
/*      */   
/*      */   public void emit(Event event) throws IOException {
/*  217 */     this.events.add(event);
/*  218 */     while (!needMoreEvents()) {
/*  219 */       this.event = this.events.poll();
/*  220 */       this.state.expect();
/*  221 */       this.event = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean needMoreEvents() {
/*  228 */     if (this.events.isEmpty()) {
/*  229 */       return true;
/*      */     }
/*  231 */     Event event = this.events.peek();
/*  232 */     if (event instanceof DocumentStartEvent)
/*  233 */       return needEvents(1); 
/*  234 */     if (event instanceof SequenceStartEvent)
/*  235 */       return needEvents(2); 
/*  236 */     if (event instanceof MappingStartEvent) {
/*  237 */       return needEvents(3);
/*      */     }
/*  239 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean needEvents(int count) {
/*  244 */     int level = 0;
/*  245 */     Iterator<Event> iter = this.events.iterator();
/*  246 */     iter.next();
/*  247 */     while (iter.hasNext()) {
/*  248 */       Event event = iter.next();
/*  249 */       if (event instanceof DocumentStartEvent || event instanceof CollectionStartEvent) {
/*  250 */         level++;
/*  251 */       } else if (event instanceof DocumentEndEvent || event instanceof org.yaml.snakeyaml.events.CollectionEndEvent) {
/*  252 */         level--;
/*  253 */       } else if (event instanceof org.yaml.snakeyaml.events.StreamEndEvent) {
/*  254 */         level = -1;
/*      */       } 
/*  256 */       if (level < 0) {
/*  257 */         return false;
/*      */       }
/*      */     } 
/*  260 */     return (this.events.size() < count + 1);
/*      */   }
/*      */   
/*      */   private void increaseIndent(boolean flow, boolean indentless) {
/*  264 */     this.indents.push(this.indent);
/*  265 */     if (this.indent == null) {
/*  266 */       if (flow) {
/*  267 */         this.indent = Integer.valueOf(this.bestIndent);
/*      */       } else {
/*  269 */         this.indent = Integer.valueOf(0);
/*      */       } 
/*  271 */     } else if (!indentless) {
/*  272 */       Emitter emitter = this; emitter.indent = Integer.valueOf(emitter.indent.intValue() + this.bestIndent);
/*      */     } 
/*      */   }
/*      */   
/*      */   private class ExpectStreamStart
/*      */     implements EmitterState
/*      */   {
/*      */     private ExpectStreamStart() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  282 */       if (Emitter.this.event instanceof org.yaml.snakeyaml.events.StreamStartEvent) {
/*  283 */         Emitter.this.writeStreamStart();
/*  284 */         Emitter.this.state = new Emitter.ExpectFirstDocumentStart();
/*      */       } else {
/*  286 */         throw new EmitterException("expected StreamStartEvent, but got " + Emitter.this.event);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private class ExpectNothing implements EmitterState {
/*      */     public void expect() throws IOException {
/*  293 */       throw new EmitterException("expecting nothing, but got " + Emitter.this.event);
/*      */     }
/*      */     
/*      */     private ExpectNothing() {} }
/*      */   
/*      */   private class ExpectFirstDocumentStart implements EmitterState { private ExpectFirstDocumentStart() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  301 */       (new Emitter.ExpectDocumentStart(true)).expect();
/*      */     } }
/*      */ 
/*      */   
/*      */   private class ExpectDocumentStart implements EmitterState {
/*      */     private boolean first;
/*      */     
/*      */     public ExpectDocumentStart(boolean first) {
/*  309 */       this.first = first;
/*      */     }
/*      */     
/*      */     public void expect() throws IOException {
/*  313 */       if (Emitter.this.event instanceof DocumentStartEvent) {
/*  314 */         DocumentStartEvent ev = (DocumentStartEvent)Emitter.this.event;
/*  315 */         if ((ev.getVersion() != null || ev.getTags() != null) && Emitter.this.openEnded) {
/*  316 */           Emitter.this.writeIndicator("...", true, false, false);
/*  317 */           Emitter.this.writeIndent();
/*      */         } 
/*  319 */         if (ev.getVersion() != null) {
/*  320 */           String versionText = Emitter.this.prepareVersion(ev.getVersion());
/*  321 */           Emitter.this.writeVersionDirective(versionText);
/*      */         } 
/*  323 */         Emitter.this.tagPrefixes = (Map)new LinkedHashMap<>(Emitter.DEFAULT_TAG_PREFIXES);
/*  324 */         if (ev.getTags() != null) {
/*  325 */           Set<String> handles = new TreeSet<>(ev.getTags().keySet());
/*  326 */           for (String handle : handles) {
/*  327 */             String prefix = (String)ev.getTags().get(handle);
/*  328 */             Emitter.this.tagPrefixes.put(prefix, handle);
/*  329 */             String handleText = Emitter.this.prepareTagHandle(handle);
/*  330 */             String prefixText = Emitter.this.prepareTagPrefix(prefix);
/*  331 */             Emitter.this.writeTagDirective(handleText, prefixText);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  337 */         boolean implicit = (this.first && !ev.getExplicit() && !Emitter.this.canonical.booleanValue() && ev.getVersion() == null && (ev.getTags() == null || ev.getTags().isEmpty()) && !Emitter.this.checkEmptyDocument());
/*  338 */         if (!implicit) {
/*  339 */           Emitter.this.writeIndent();
/*  340 */           Emitter.this.writeIndicator("---", true, false, false);
/*  341 */           if (Emitter.this.canonical.booleanValue()) {
/*  342 */             Emitter.this.writeIndent();
/*      */           }
/*      */         } 
/*  345 */         Emitter.this.state = new Emitter.ExpectDocumentRoot();
/*  346 */       } else if (Emitter.this.event instanceof org.yaml.snakeyaml.events.StreamEndEvent) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  352 */         Emitter.this.writeStreamEnd();
/*  353 */         Emitter.this.state = new Emitter.ExpectNothing();
/*      */       } else {
/*  355 */         throw new EmitterException("expected DocumentStartEvent, but got " + Emitter.this.event);
/*      */       } 
/*      */     } }
/*      */   
/*      */   private class ExpectDocumentEnd implements EmitterState { private ExpectDocumentEnd() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  362 */       if (Emitter.this.event instanceof DocumentEndEvent) {
/*  363 */         Emitter.this.writeIndent();
/*  364 */         if (((DocumentEndEvent)Emitter.this.event).getExplicit()) {
/*  365 */           Emitter.this.writeIndicator("...", true, false, false);
/*  366 */           Emitter.this.writeIndent();
/*      */         } 
/*  368 */         Emitter.this.flushStream();
/*  369 */         Emitter.this.state = new Emitter.ExpectDocumentStart(false);
/*      */       } else {
/*  371 */         throw new EmitterException("expected DocumentEndEvent, but got " + Emitter.this.event);
/*      */       } 
/*      */     } }
/*      */   
/*      */   private class ExpectDocumentRoot implements EmitterState { private ExpectDocumentRoot() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  378 */       Emitter.this.states.push(new Emitter.ExpectDocumentEnd());
/*  379 */       Emitter.this.expectNode(true, false, false);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void expectNode(boolean root, boolean mapping, boolean simpleKey) throws IOException {
/*  386 */     this.rootContext = root;
/*  387 */     this.mappingContext = mapping;
/*  388 */     this.simpleKeyContext = simpleKey;
/*  389 */     if (this.event instanceof org.yaml.snakeyaml.events.AliasEvent) {
/*  390 */       expectAlias();
/*  391 */     } else if (this.event instanceof ScalarEvent || this.event instanceof CollectionStartEvent) {
/*  392 */       processAnchor("&");
/*  393 */       processTag();
/*  394 */       if (this.event instanceof ScalarEvent) {
/*  395 */         expectScalar();
/*  396 */       } else if (this.event instanceof SequenceStartEvent) {
/*  397 */         if (this.flowLevel != 0 || this.canonical.booleanValue() || ((SequenceStartEvent)this.event).isFlow() || 
/*  398 */           checkEmptySequence()) {
/*  399 */           expectFlowSequence();
/*      */         } else {
/*  401 */           expectBlockSequence();
/*      */         }
/*      */       
/*  404 */       } else if (this.flowLevel != 0 || this.canonical.booleanValue() || ((MappingStartEvent)this.event).isFlow() || 
/*  405 */         checkEmptyMapping()) {
/*  406 */         expectFlowMapping();
/*      */       } else {
/*  408 */         expectBlockMapping();
/*      */       } 
/*      */     } else {
/*      */       
/*  412 */       throw new EmitterException("expected NodeEvent, but got " + this.event);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void expectAlias() throws IOException {
/*  417 */     if (!(this.event instanceof org.yaml.snakeyaml.events.AliasEvent)) {
/*  418 */       throw new EmitterException("Alias must be provided");
/*      */     }
/*  420 */     processAnchor("*");
/*  421 */     this.state = (EmitterState)this.states.pop();
/*      */   }
/*      */   
/*      */   private void expectScalar() throws IOException {
/*  425 */     increaseIndent(true, false);
/*  426 */     processScalar();
/*  427 */     this.indent = (Integer)this.indents.pop();
/*  428 */     this.state = (EmitterState)this.states.pop();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void expectFlowSequence() throws IOException {
/*  434 */     writeIndicator("[", true, true, false);
/*  435 */     this.flowLevel++;
/*  436 */     increaseIndent(true, false);
/*  437 */     if (this.prettyFlow.booleanValue()) {
/*  438 */       writeIndent();
/*      */     }
/*  440 */     this.state = new ExpectFirstFlowSequenceItem();
/*      */   }
/*      */   private class ExpectFirstFlowSequenceItem implements EmitterState { private ExpectFirstFlowSequenceItem() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  445 */       if (Emitter.this.event instanceof org.yaml.snakeyaml.events.SequenceEndEvent) {
/*  446 */         Emitter.this.indent = (Integer)Emitter.this.indents.pop();
/*  447 */         Emitter.this.flowLevel--;
/*  448 */         Emitter.this.writeIndicator("]", false, false, false);
/*  449 */         Emitter.this.state = (EmitterState)Emitter.this.states.pop();
/*      */       } else {
/*  451 */         if (Emitter.this.canonical.booleanValue() || (Emitter.this.column > Emitter.this.bestWidth && Emitter.this.splitLines) || Emitter.this.prettyFlow.booleanValue()) {
/*  452 */           Emitter.this.writeIndent();
/*      */         }
/*  454 */         Emitter.this.states.push(new Emitter.ExpectFlowSequenceItem());
/*  455 */         Emitter.this.expectNode(false, false, false);
/*      */       } 
/*      */     } }
/*      */   
/*      */   private class ExpectFlowSequenceItem implements EmitterState { private ExpectFlowSequenceItem() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  462 */       if (Emitter.this.event instanceof org.yaml.snakeyaml.events.SequenceEndEvent) {
/*  463 */         Emitter.this.indent = (Integer)Emitter.this.indents.pop();
/*  464 */         Emitter.this.flowLevel--;
/*  465 */         if (Emitter.this.canonical.booleanValue()) {
/*  466 */           Emitter.this.writeIndicator(",", false, false, false);
/*  467 */           Emitter.this.writeIndent();
/*      */         } 
/*  469 */         Emitter.this.writeIndicator("]", false, false, false);
/*  470 */         if (Emitter.this.prettyFlow.booleanValue()) {
/*  471 */           Emitter.this.writeIndent();
/*      */         }
/*  473 */         Emitter.this.state = (EmitterState)Emitter.this.states.pop();
/*      */       } else {
/*  475 */         Emitter.this.writeIndicator(",", false, false, false);
/*  476 */         if (Emitter.this.canonical.booleanValue() || (Emitter.this.column > Emitter.this.bestWidth && Emitter.this.splitLines) || Emitter.this.prettyFlow.booleanValue()) {
/*  477 */           Emitter.this.writeIndent();
/*      */         }
/*  479 */         Emitter.this.states.push(new ExpectFlowSequenceItem());
/*  480 */         Emitter.this.expectNode(false, false, false);
/*      */       } 
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void expectFlowMapping() throws IOException {
/*  488 */     writeIndicator("{", true, true, false);
/*  489 */     this.flowLevel++;
/*  490 */     increaseIndent(true, false);
/*  491 */     if (this.prettyFlow.booleanValue()) {
/*  492 */       writeIndent();
/*      */     }
/*  494 */     this.state = new ExpectFirstFlowMappingKey();
/*      */   }
/*      */   private class ExpectFirstFlowMappingKey implements EmitterState { private ExpectFirstFlowMappingKey() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  499 */       if (Emitter.this.event instanceof org.yaml.snakeyaml.events.MappingEndEvent) {
/*  500 */         Emitter.this.indent = (Integer)Emitter.this.indents.pop();
/*  501 */         Emitter.this.flowLevel--;
/*  502 */         Emitter.this.writeIndicator("}", false, false, false);
/*  503 */         Emitter.this.state = (EmitterState)Emitter.this.states.pop();
/*      */       } else {
/*  505 */         if (Emitter.this.canonical.booleanValue() || (Emitter.this.column > Emitter.this.bestWidth && Emitter.this.splitLines) || Emitter.this.prettyFlow.booleanValue()) {
/*  506 */           Emitter.this.writeIndent();
/*      */         }
/*  508 */         if (!Emitter.this.canonical.booleanValue() && Emitter.this.checkSimpleKey()) {
/*  509 */           Emitter.this.states.push(new Emitter.ExpectFlowMappingSimpleValue());
/*  510 */           Emitter.this.expectNode(false, true, true);
/*      */         } else {
/*  512 */           Emitter.this.writeIndicator("?", true, false, false);
/*  513 */           Emitter.this.states.push(new Emitter.ExpectFlowMappingValue());
/*  514 */           Emitter.this.expectNode(false, true, false);
/*      */         } 
/*      */       } 
/*      */     } }
/*      */   
/*      */   private class ExpectFlowMappingKey implements EmitterState { private ExpectFlowMappingKey() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  522 */       if (Emitter.this.event instanceof org.yaml.snakeyaml.events.MappingEndEvent) {
/*  523 */         Emitter.this.indent = (Integer)Emitter.this.indents.pop();
/*  524 */         Emitter.this.flowLevel--;
/*  525 */         if (Emitter.this.canonical.booleanValue()) {
/*  526 */           Emitter.this.writeIndicator(",", false, false, false);
/*  527 */           Emitter.this.writeIndent();
/*      */         } 
/*  529 */         if (Emitter.this.prettyFlow.booleanValue()) {
/*  530 */           Emitter.this.writeIndent();
/*      */         }
/*  532 */         Emitter.this.writeIndicator("}", false, false, false);
/*  533 */         Emitter.this.state = (EmitterState)Emitter.this.states.pop();
/*      */       } else {
/*  535 */         Emitter.this.writeIndicator(",", false, false, false);
/*  536 */         if (Emitter.this.canonical.booleanValue() || (Emitter.this.column > Emitter.this.bestWidth && Emitter.this.splitLines) || Emitter.this.prettyFlow.booleanValue()) {
/*  537 */           Emitter.this.writeIndent();
/*      */         }
/*  539 */         if (!Emitter.this.canonical.booleanValue() && Emitter.this.checkSimpleKey()) {
/*  540 */           Emitter.this.states.push(new Emitter.ExpectFlowMappingSimpleValue());
/*  541 */           Emitter.this.expectNode(false, true, true);
/*      */         } else {
/*  543 */           Emitter.this.writeIndicator("?", true, false, false);
/*  544 */           Emitter.this.states.push(new Emitter.ExpectFlowMappingValue());
/*  545 */           Emitter.this.expectNode(false, true, false);
/*      */         } 
/*      */       } 
/*      */     } }
/*      */   
/*      */   private class ExpectFlowMappingSimpleValue implements EmitterState { private ExpectFlowMappingSimpleValue() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  553 */       Emitter.this.writeIndicator(":", false, false, false);
/*  554 */       Emitter.this.states.push(new Emitter.ExpectFlowMappingKey());
/*  555 */       Emitter.this.expectNode(false, true, false);
/*      */     } }
/*      */   
/*      */   private class ExpectFlowMappingValue implements EmitterState { private ExpectFlowMappingValue() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  561 */       if (Emitter.this.canonical.booleanValue() || Emitter.this.column > Emitter.this.bestWidth || Emitter.this.prettyFlow.booleanValue()) {
/*  562 */         Emitter.this.writeIndent();
/*      */       }
/*  564 */       Emitter.this.writeIndicator(":", true, false, false);
/*  565 */       Emitter.this.states.push(new Emitter.ExpectFlowMappingKey());
/*  566 */       Emitter.this.expectNode(false, true, false);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void expectBlockSequence() throws IOException {
/*  573 */     boolean indentless = (this.mappingContext && !this.indention);
/*  574 */     increaseIndent(false, indentless);
/*  575 */     this.state = new ExpectFirstBlockSequenceItem();
/*      */   }
/*      */   private class ExpectFirstBlockSequenceItem implements EmitterState { private ExpectFirstBlockSequenceItem() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  580 */       (new Emitter.ExpectBlockSequenceItem(true)).expect();
/*      */     } }
/*      */ 
/*      */   
/*      */   private class ExpectBlockSequenceItem implements EmitterState {
/*      */     private boolean first;
/*      */     
/*      */     public ExpectBlockSequenceItem(boolean first) {
/*  588 */       this.first = first;
/*      */     }
/*      */     
/*      */     public void expect() throws IOException {
/*  592 */       if (!this.first && Emitter.this.event instanceof org.yaml.snakeyaml.events.SequenceEndEvent) {
/*  593 */         Emitter.this.indent = (Integer)Emitter.this.indents.pop();
/*  594 */         Emitter.this.state = (EmitterState)Emitter.this.states.pop();
/*      */       } else {
/*  596 */         Emitter.this.writeIndent();
/*  597 */         Emitter.this.writeWhitespace(Emitter.this.indicatorIndent);
/*  598 */         Emitter.this.writeIndicator("-", true, false, true);
/*  599 */         Emitter.this.states.push(new ExpectBlockSequenceItem(false));
/*  600 */         Emitter.this.expectNode(false, false, false);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void expectBlockMapping() throws IOException {
/*  607 */     increaseIndent(false, false);
/*  608 */     this.state = new ExpectFirstBlockMappingKey();
/*      */   }
/*      */   private class ExpectFirstBlockMappingKey implements EmitterState { private ExpectFirstBlockMappingKey() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  613 */       (new Emitter.ExpectBlockMappingKey(true)).expect();
/*      */     } }
/*      */ 
/*      */   
/*      */   private class ExpectBlockMappingKey implements EmitterState {
/*      */     private boolean first;
/*      */     
/*      */     public ExpectBlockMappingKey(boolean first) {
/*  621 */       this.first = first;
/*      */     }
/*      */     
/*      */     public void expect() throws IOException {
/*  625 */       if (!this.first && Emitter.this.event instanceof org.yaml.snakeyaml.events.MappingEndEvent) {
/*  626 */         Emitter.this.indent = (Integer)Emitter.this.indents.pop();
/*  627 */         Emitter.this.state = (EmitterState)Emitter.this.states.pop();
/*      */       } else {
/*  629 */         Emitter.this.writeIndent();
/*  630 */         if (Emitter.this.checkSimpleKey()) {
/*  631 */           Emitter.this.states.push(new Emitter.ExpectBlockMappingSimpleValue());
/*  632 */           Emitter.this.expectNode(false, true, true);
/*      */         } else {
/*  634 */           Emitter.this.writeIndicator("?", true, false, true);
/*  635 */           Emitter.this.states.push(new Emitter.ExpectBlockMappingValue());
/*  636 */           Emitter.this.expectNode(false, true, false);
/*      */         } 
/*      */       } 
/*      */     } }
/*      */   
/*      */   private class ExpectBlockMappingSimpleValue implements EmitterState { private ExpectBlockMappingSimpleValue() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  644 */       Emitter.this.writeIndicator(":", false, false, false);
/*  645 */       Emitter.this.states.push(new Emitter.ExpectBlockMappingKey(false));
/*  646 */       Emitter.this.expectNode(false, true, false);
/*      */     } }
/*      */   
/*      */   private class ExpectBlockMappingValue implements EmitterState { private ExpectBlockMappingValue() {}
/*      */     
/*      */     public void expect() throws IOException {
/*  652 */       Emitter.this.writeIndent();
/*  653 */       Emitter.this.writeIndicator(":", true, false, true);
/*  654 */       Emitter.this.states.push(new Emitter.ExpectBlockMappingKey(false));
/*  655 */       Emitter.this.expectNode(false, true, false);
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean checkEmptySequence() {
/*  662 */     return (this.event instanceof SequenceStartEvent && !this.events.isEmpty() && this.events.peek() instanceof org.yaml.snakeyaml.events.SequenceEndEvent);
/*      */   }
/*      */   
/*      */   private boolean checkEmptyMapping() {
/*  666 */     return (this.event instanceof MappingStartEvent && !this.events.isEmpty() && this.events.peek() instanceof org.yaml.snakeyaml.events.MappingEndEvent);
/*      */   }
/*      */   
/*      */   private boolean checkEmptyDocument() {
/*  670 */     if (!(this.event instanceof DocumentStartEvent) || this.events.isEmpty()) {
/*  671 */       return false;
/*      */     }
/*  673 */     Event event = this.events.peek();
/*  674 */     if (event instanceof ScalarEvent) {
/*  675 */       ScalarEvent e = (ScalarEvent)event;
/*  676 */       return (e.getAnchor() == null && e.getTag() == null && e.getImplicit() != null && e
/*  677 */         .getValue().length() == 0);
/*      */     } 
/*  679 */     return false;
/*      */   }
/*      */   
/*      */   private boolean checkSimpleKey() {
/*  683 */     int length = 0;
/*  684 */     if (this.event instanceof NodeEvent && ((NodeEvent)this.event).getAnchor() != null) {
/*  685 */       if (this.preparedAnchor == null) {
/*  686 */         this.preparedAnchor = prepareAnchor(((NodeEvent)this.event).getAnchor());
/*      */       }
/*  688 */       length += this.preparedAnchor.length();
/*      */     } 
/*  690 */     String tag = null;
/*  691 */     if (this.event instanceof ScalarEvent) {
/*  692 */       tag = ((ScalarEvent)this.event).getTag();
/*  693 */     } else if (this.event instanceof CollectionStartEvent) {
/*  694 */       tag = ((CollectionStartEvent)this.event).getTag();
/*      */     } 
/*  696 */     if (tag != null) {
/*  697 */       if (this.preparedTag == null) {
/*  698 */         this.preparedTag = prepareTag(tag);
/*      */       }
/*  700 */       length += this.preparedTag.length();
/*      */     } 
/*  702 */     if (this.event instanceof ScalarEvent) {
/*  703 */       if (this.analysis == null) {
/*  704 */         this.analysis = analyzeScalar(((ScalarEvent)this.event).getValue());
/*      */       }
/*  706 */       length += this.analysis.getScalar().length();
/*      */     } 
/*  708 */     return (length < this.maxSimpleKeyLength && (this.event instanceof org.yaml.snakeyaml.events.AliasEvent || (this.event instanceof ScalarEvent && 
/*  709 */       !this.analysis.isEmpty() && !this.analysis.isMultiline()) || 
/*  710 */       checkEmptySequence() || checkEmptyMapping()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void processAnchor(String indicator) throws IOException {
/*  716 */     NodeEvent ev = (NodeEvent)this.event;
/*  717 */     if (ev.getAnchor() == null) {
/*  718 */       this.preparedAnchor = null;
/*      */       return;
/*      */     } 
/*  721 */     if (this.preparedAnchor == null) {
/*  722 */       this.preparedAnchor = prepareAnchor(ev.getAnchor());
/*      */     }
/*  724 */     writeIndicator(indicator + this.preparedAnchor, true, false, false);
/*  725 */     this.preparedAnchor = null;
/*      */   }
/*      */   
/*      */   private void processTag() throws IOException {
/*  729 */     String tag = null;
/*  730 */     if (this.event instanceof ScalarEvent) {
/*  731 */       ScalarEvent ev = (ScalarEvent)this.event;
/*  732 */       tag = ev.getTag();
/*  733 */       if (this.style == null) {
/*  734 */         this.style = chooseScalarStyle();
/*      */       }
/*  736 */       if ((!this.canonical.booleanValue() || tag == null) && ((this.style == null && ev.getImplicit()
/*  737 */         .canOmitTagInPlainScalar()) || (this.style != null && ev.getImplicit()
/*  738 */         .canOmitTagInNonPlainScalar()))) {
/*  739 */         this.preparedTag = null;
/*      */         return;
/*      */       } 
/*  742 */       if (ev.getImplicit().canOmitTagInPlainScalar() && tag == null) {
/*  743 */         tag = "!";
/*  744 */         this.preparedTag = null;
/*      */       } 
/*      */     } else {
/*  747 */       CollectionStartEvent ev = (CollectionStartEvent)this.event;
/*  748 */       tag = ev.getTag();
/*  749 */       if ((!this.canonical.booleanValue() || tag == null) && ev.getImplicit()) {
/*  750 */         this.preparedTag = null;
/*      */         return;
/*      */       } 
/*      */     } 
/*  754 */     if (tag == null) {
/*  755 */       throw new EmitterException("tag is not specified");
/*      */     }
/*  757 */     if (this.preparedTag == null) {
/*  758 */       this.preparedTag = prepareTag(tag);
/*      */     }
/*  760 */     writeIndicator(this.preparedTag, true, false, false);
/*  761 */     this.preparedTag = null;
/*      */   }
/*      */   
/*      */   private DumperOptions.ScalarStyle chooseScalarStyle() {
/*  765 */     ScalarEvent ev = (ScalarEvent)this.event;
/*  766 */     if (this.analysis == null) {
/*  767 */       this.analysis = analyzeScalar(ev.getValue());
/*      */     }
/*  769 */     if ((!ev.isPlain() && ev.getScalarStyle() == DumperOptions.ScalarStyle.DOUBLE_QUOTED) || this.canonical.booleanValue()) {
/*  770 */       return DumperOptions.ScalarStyle.DOUBLE_QUOTED;
/*      */     }
/*  772 */     if (ev.isPlain() && ev.getImplicit().canOmitTagInPlainScalar() && (
/*  773 */       !this.simpleKeyContext || (!this.analysis.isEmpty() && !this.analysis.isMultiline())) && ((this.flowLevel != 0 && this.analysis
/*  774 */       .isAllowFlowPlain()) || (this.flowLevel == 0 && this.analysis.isAllowBlockPlain()))) {
/*  775 */       return null;
/*      */     }
/*      */     
/*  778 */     if (!ev.isPlain() && (ev.getScalarStyle() == DumperOptions.ScalarStyle.LITERAL || ev.getScalarStyle() == DumperOptions.ScalarStyle.FOLDED) && 
/*  779 */       this.flowLevel == 0 && !this.simpleKeyContext && this.analysis.isAllowBlock()) {
/*  780 */       return ev.getScalarStyle();
/*      */     }
/*      */     
/*  783 */     if ((ev.isPlain() || ev.getScalarStyle() == DumperOptions.ScalarStyle.SINGLE_QUOTED) && 
/*  784 */       this.analysis.isAllowSingleQuoted() && (!this.simpleKeyContext || !this.analysis.isMultiline())) {
/*  785 */       return DumperOptions.ScalarStyle.SINGLE_QUOTED;
/*      */     }
/*      */     
/*  788 */     return DumperOptions.ScalarStyle.DOUBLE_QUOTED;
/*      */   }
/*      */   
/*      */   private void processScalar() throws IOException {
/*  792 */     ScalarEvent ev = (ScalarEvent)this.event;
/*  793 */     if (this.analysis == null) {
/*  794 */       this.analysis = analyzeScalar(ev.getValue());
/*      */     }
/*  796 */     if (this.style == null) {
/*  797 */       this.style = chooseScalarStyle();
/*      */     }
/*  799 */     boolean split = (!this.simpleKeyContext && this.splitLines);
/*  800 */     if (this.style == null) {
/*  801 */       writePlain(this.analysis.getScalar(), split);
/*      */     } else {
/*  803 */       switch (this.style) {
/*      */         case DOUBLE_QUOTED:
/*  805 */           writeDoubleQuoted(this.analysis.getScalar(), split);
/*      */           break;
/*      */         case SINGLE_QUOTED:
/*  808 */           writeSingleQuoted(this.analysis.getScalar(), split);
/*      */           break;
/*      */         case FOLDED:
/*  811 */           writeFolded(this.analysis.getScalar(), split);
/*      */           break;
/*      */         case LITERAL:
/*  814 */           writeLiteral(this.analysis.getScalar());
/*      */           break;
/*      */         default:
/*  817 */           throw new YAMLException("Unexpected style: " + this.style);
/*      */       } 
/*      */     } 
/*  820 */     this.analysis = null;
/*  821 */     this.style = null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String prepareVersion(DumperOptions.Version version) {
/*  827 */     if (version.major() != 1) {
/*  828 */       throw new EmitterException("unsupported YAML version: " + version);
/*      */     }
/*  830 */     return version.getRepresentation();
/*      */   }
/*      */   
/*  833 */   private static final Pattern HANDLE_FORMAT = Pattern.compile("^![-_\\w]*!$");
/*      */   
/*      */   private String prepareTagHandle(String handle) {
/*  836 */     if (handle.length() == 0)
/*  837 */       throw new EmitterException("tag handle must not be empty"); 
/*  838 */     if (handle.charAt(0) != '!' || handle.charAt(handle.length() - 1) != '!')
/*  839 */       throw new EmitterException("tag handle must start and end with '!': " + handle); 
/*  840 */     if (!"!".equals(handle) && !HANDLE_FORMAT.matcher(handle).matches()) {
/*  841 */       throw new EmitterException("invalid character in the tag handle: " + handle);
/*      */     }
/*  843 */     return handle;
/*      */   }
/*      */   
/*      */   private String prepareTagPrefix(String prefix) {
/*  847 */     if (prefix.length() == 0) {
/*  848 */       throw new EmitterException("tag prefix must not be empty");
/*      */     }
/*  850 */     StringBuilder chunks = new StringBuilder();
/*  851 */     int start = 0;
/*  852 */     int end = 0;
/*  853 */     if (prefix.charAt(0) == '!') {
/*  854 */       end = 1;
/*      */     }
/*  856 */     while (end < prefix.length()) {
/*  857 */       end++;
/*      */     }
/*  859 */     if (start < end) {
/*  860 */       chunks.append(prefix.substring(start, end));
/*      */     }
/*  862 */     return chunks.toString();
/*      */   }
/*      */   
/*      */   private String prepareTag(String tag) {
/*  866 */     if (tag.length() == 0) {
/*  867 */       throw new EmitterException("tag must not be empty");
/*      */     }
/*  869 */     if ("!".equals(tag)) {
/*  870 */       return tag;
/*      */     }
/*  872 */     String handle = null;
/*  873 */     String suffix = tag;
/*      */     
/*  875 */     for (String prefix : this.tagPrefixes.keySet()) {
/*  876 */       if (tag.startsWith(prefix) && ("!".equals(prefix) || prefix.length() < tag.length())) {
/*  877 */         handle = prefix;
/*      */       }
/*      */     } 
/*  880 */     if (handle != null) {
/*  881 */       suffix = tag.substring(handle.length());
/*  882 */       handle = this.tagPrefixes.get(handle);
/*      */     } 
/*      */     
/*  885 */     int end = suffix.length();
/*  886 */     String suffixText = (end > 0) ? suffix.substring(0, end) : "";
/*      */     
/*  888 */     if (handle != null) {
/*  889 */       return handle + suffixText;
/*      */     }
/*  891 */     return "!<" + suffixText + ">";
/*      */   }
/*      */   
/*  894 */   private static final Pattern ANCHOR_FORMAT = Pattern.compile("^[-_\\w]*$");
/*      */   
/*      */   static String prepareAnchor(String anchor) {
/*  897 */     if (anchor.length() == 0) {
/*  898 */       throw new EmitterException("anchor must not be empty");
/*      */     }
/*  900 */     if (!ANCHOR_FORMAT.matcher(anchor).matches()) {
/*  901 */       throw new EmitterException("invalid character in the anchor: " + anchor);
/*      */     }
/*  903 */     return anchor;
/*      */   }
/*      */ 
/*      */   
/*      */   private ScalarAnalysis analyzeScalar(String scalar) {
/*  908 */     if (scalar.length() == 0) {
/*  909 */       return new ScalarAnalysis(scalar, true, false, false, true, true, false);
/*      */     }
/*      */     
/*  912 */     boolean blockIndicators = false;
/*  913 */     boolean flowIndicators = false;
/*  914 */     boolean lineBreaks = false;
/*  915 */     boolean specialCharacters = false;
/*      */ 
/*      */     
/*  918 */     boolean leadingSpace = false;
/*  919 */     boolean leadingBreak = false;
/*  920 */     boolean trailingSpace = false;
/*  921 */     boolean trailingBreak = false;
/*  922 */     boolean breakSpace = false;
/*  923 */     boolean spaceBreak = false;
/*      */ 
/*      */     
/*  926 */     if (scalar.startsWith("---") || scalar.startsWith("...")) {
/*  927 */       blockIndicators = true;
/*  928 */       flowIndicators = true;
/*      */     } 
/*      */     
/*  931 */     boolean preceededByWhitespace = true;
/*  932 */     boolean followedByWhitespace = (scalar.length() == 1 || Constant.NULL_BL_T_LINEBR.has(scalar.codePointAt(1)));
/*      */     
/*  934 */     boolean previousSpace = false;
/*      */ 
/*      */     
/*  937 */     boolean previousBreak = false;
/*      */     
/*  939 */     int index = 0;
/*      */     
/*  941 */     while (index < scalar.length()) {
/*  942 */       int c = scalar.codePointAt(index);
/*      */       
/*  944 */       if (index == 0) {
/*      */         
/*  946 */         if ("#,[]{}&*!|>'\"%@`".indexOf(c) != -1) {
/*  947 */           flowIndicators = true;
/*  948 */           blockIndicators = true;
/*      */         } 
/*  950 */         if (c == 63 || c == 58) {
/*  951 */           flowIndicators = true;
/*  952 */           if (followedByWhitespace) {
/*  953 */             blockIndicators = true;
/*      */           }
/*      */         } 
/*  956 */         if (c == 45 && followedByWhitespace) {
/*  957 */           flowIndicators = true;
/*  958 */           blockIndicators = true;
/*      */         } 
/*      */       } else {
/*      */         
/*  962 */         if (",?[]{}".indexOf(c) != -1) {
/*  963 */           flowIndicators = true;
/*      */         }
/*  965 */         if (c == 58) {
/*  966 */           flowIndicators = true;
/*  967 */           if (followedByWhitespace) {
/*  968 */             blockIndicators = true;
/*      */           }
/*      */         } 
/*  971 */         if (c == 35 && preceededByWhitespace) {
/*  972 */           flowIndicators = true;
/*  973 */           blockIndicators = true;
/*      */         } 
/*      */       } 
/*      */       
/*  977 */       boolean isLineBreak = Constant.LINEBR.has(c);
/*  978 */       if (isLineBreak) {
/*  979 */         lineBreaks = true;
/*      */       }
/*  981 */       if (c != 10 && (32 > c || c > 126)) {
/*  982 */         if (c == 133 || (c >= 160 && c <= 55295) || (c >= 57344 && c <= 65533) || (c >= 65536 && c <= 1114111)) {
/*      */ 
/*      */ 
/*      */           
/*  986 */           if (!this.allowUnicode) {
/*  987 */             specialCharacters = true;
/*      */           }
/*      */         } else {
/*  990 */           specialCharacters = true;
/*      */         } 
/*      */       }
/*      */       
/*  994 */       if (c == 32) {
/*  995 */         if (index == 0) {
/*  996 */           leadingSpace = true;
/*      */         }
/*  998 */         if (index == scalar.length() - 1) {
/*  999 */           trailingSpace = true;
/*      */         }
/* 1001 */         if (previousBreak) {
/* 1002 */           breakSpace = true;
/*      */         }
/* 1004 */         previousSpace = true;
/* 1005 */         previousBreak = false;
/* 1006 */       } else if (isLineBreak) {
/* 1007 */         if (index == 0) {
/* 1008 */           leadingBreak = true;
/*      */         }
/* 1010 */         if (index == scalar.length() - 1) {
/* 1011 */           trailingBreak = true;
/*      */         }
/* 1013 */         if (previousSpace) {
/* 1014 */           spaceBreak = true;
/*      */         }
/* 1016 */         previousSpace = false;
/* 1017 */         previousBreak = true;
/*      */       } else {
/* 1019 */         previousSpace = false;
/* 1020 */         previousBreak = false;
/*      */       } 
/*      */ 
/*      */       
/* 1024 */       index += Character.charCount(c);
/* 1025 */       preceededByWhitespace = (Constant.NULL_BL_T.has(c) || isLineBreak);
/* 1026 */       followedByWhitespace = true;
/* 1027 */       if (index + 1 < scalar.length()) {
/* 1028 */         int nextIndex = index + Character.charCount(scalar.codePointAt(index));
/* 1029 */         if (nextIndex < scalar.length()) {
/* 1030 */           followedByWhitespace = (Constant.NULL_BL_T.has(scalar.codePointAt(nextIndex)) || isLineBreak);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1035 */     boolean allowFlowPlain = true;
/* 1036 */     boolean allowBlockPlain = true;
/* 1037 */     boolean allowSingleQuoted = true;
/* 1038 */     boolean allowBlock = true;
/*      */     
/* 1040 */     if (leadingSpace || leadingBreak || trailingSpace || trailingBreak) {
/* 1041 */       allowFlowPlain = allowBlockPlain = false;
/*      */     }
/*      */     
/* 1044 */     if (trailingSpace) {
/* 1045 */       allowBlock = false;
/*      */     }
/*      */ 
/*      */     
/* 1049 */     if (breakSpace) {
/* 1050 */       allowFlowPlain = allowBlockPlain = allowSingleQuoted = false;
/*      */     }
/*      */ 
/*      */     
/* 1054 */     if (spaceBreak || specialCharacters) {
/* 1055 */       allowFlowPlain = allowBlockPlain = allowSingleQuoted = allowBlock = false;
/*      */     }
/*      */ 
/*      */     
/* 1059 */     if (lineBreaks) {
/* 1060 */       allowFlowPlain = false;
/*      */     }
/*      */     
/* 1063 */     if (flowIndicators) {
/* 1064 */       allowFlowPlain = false;
/*      */     }
/*      */     
/* 1067 */     if (blockIndicators) {
/* 1068 */       allowBlockPlain = false;
/*      */     }
/*      */     
/* 1071 */     return new ScalarAnalysis(scalar, false, lineBreaks, allowFlowPlain, allowBlockPlain, allowSingleQuoted, allowBlock);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void flushStream() throws IOException {
/* 1078 */     this.stream.flush();
/*      */   }
/*      */ 
/*      */   
/*      */   void writeStreamStart() {}
/*      */ 
/*      */   
/*      */   void writeStreamEnd() throws IOException {
/* 1086 */     flushStream();
/*      */   }
/*      */ 
/*      */   
/*      */   void writeIndicator(String indicator, boolean needWhitespace, boolean whitespace, boolean indentation) throws IOException {
/* 1091 */     if (!this.whitespace && needWhitespace) {
/* 1092 */       this.column++;
/* 1093 */       this.stream.write(SPACE);
/*      */     } 
/* 1095 */     this.whitespace = whitespace;
/* 1096 */     this.indention = (this.indention && indentation);
/* 1097 */     this.column += indicator.length();
/* 1098 */     this.openEnded = false;
/* 1099 */     this.stream.write(indicator);
/*      */   }
/*      */   
/*      */   void writeIndent() throws IOException {
/*      */     int indent;
/* 1104 */     if (this.indent != null) {
/* 1105 */       indent = this.indent.intValue();
/*      */     } else {
/* 1107 */       indent = 0;
/*      */     } 
/*      */     
/* 1110 */     if (!this.indention || this.column > indent || (this.column == indent && !this.whitespace)) {
/* 1111 */       writeLineBreak(null);
/*      */     }
/*      */     
/* 1114 */     writeWhitespace(indent - this.column);
/*      */   }
/*      */   
/*      */   private void writeWhitespace(int length) throws IOException {
/* 1118 */     if (length <= 0) {
/*      */       return;
/*      */     }
/* 1121 */     this.whitespace = true;
/* 1122 */     char[] data = new char[length];
/* 1123 */     for (int i = 0; i < data.length; i++) {
/* 1124 */       data[i] = ' ';
/*      */     }
/* 1126 */     this.column += length;
/* 1127 */     this.stream.write(data);
/*      */   }
/*      */   
/*      */   private void writeLineBreak(String data) throws IOException {
/* 1131 */     this.whitespace = true;
/* 1132 */     this.indention = true;
/* 1133 */     this.column = 0;
/* 1134 */     if (data == null) {
/* 1135 */       this.stream.write(this.bestLineBreak);
/*      */     } else {
/* 1137 */       this.stream.write(data);
/*      */     } 
/*      */   }
/*      */   
/*      */   void writeVersionDirective(String versionText) throws IOException {
/* 1142 */     this.stream.write("%YAML ");
/* 1143 */     this.stream.write(versionText);
/* 1144 */     writeLineBreak(null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   void writeTagDirective(String handleText, String prefixText) throws IOException {
/* 1150 */     this.stream.write("%TAG ");
/* 1151 */     this.stream.write(handleText);
/* 1152 */     this.stream.write(SPACE);
/* 1153 */     this.stream.write(prefixText);
/* 1154 */     writeLineBreak(null);
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeSingleQuoted(String text, boolean split) throws IOException {
/* 1159 */     writeIndicator("'", true, false, false);
/* 1160 */     boolean spaces = false;
/* 1161 */     boolean breaks = false;
/* 1162 */     int start = 0, end = 0;
/*      */     
/* 1164 */     while (end <= text.length()) {
/* 1165 */       char ch = Character.MIN_VALUE;
/* 1166 */       if (end < text.length()) {
/* 1167 */         ch = text.charAt(end);
/*      */       }
/* 1169 */       if (spaces) {
/* 1170 */         if (ch == '\000' || ch != ' ') {
/* 1171 */           if (start + 1 == end && this.column > this.bestWidth && split && start != 0 && end != text
/* 1172 */             .length()) {
/* 1173 */             writeIndent();
/*      */           } else {
/* 1175 */             int len = end - start;
/* 1176 */             this.column += len;
/* 1177 */             this.stream.write(text, start, len);
/*      */           } 
/* 1179 */           start = end;
/*      */         } 
/* 1181 */       } else if (breaks) {
/* 1182 */         if (ch == '\000' || Constant.LINEBR.hasNo(ch)) {
/* 1183 */           if (text.charAt(start) == '\n') {
/* 1184 */             writeLineBreak(null);
/*      */           }
/* 1186 */           String data = text.substring(start, end);
/* 1187 */           for (char br : data.toCharArray()) {
/* 1188 */             if (br == '\n') {
/* 1189 */               writeLineBreak(null);
/*      */             } else {
/* 1191 */               writeLineBreak(String.valueOf(br));
/*      */             } 
/*      */           } 
/* 1194 */           writeIndent();
/* 1195 */           start = end;
/*      */         }
/*      */       
/* 1198 */       } else if (Constant.LINEBR.has(ch, "\000 '") && 
/* 1199 */         start < end) {
/* 1200 */         int len = end - start;
/* 1201 */         this.column += len;
/* 1202 */         this.stream.write(text, start, len);
/* 1203 */         start = end;
/*      */       } 
/*      */ 
/*      */       
/* 1207 */       if (ch == '\'') {
/* 1208 */         this.column += 2;
/* 1209 */         this.stream.write("''");
/* 1210 */         start = end + 1;
/*      */       } 
/* 1212 */       if (ch != '\000') {
/* 1213 */         spaces = (ch == ' ');
/* 1214 */         breaks = Constant.LINEBR.has(ch);
/*      */       } 
/* 1216 */       end++;
/*      */     } 
/* 1218 */     writeIndicator("'", false, false, false);
/*      */   }
/*      */   
/*      */   private void writeDoubleQuoted(String text, boolean split) throws IOException {
/* 1222 */     writeIndicator("\"", true, false, false);
/* 1223 */     int start = 0;
/* 1224 */     int end = 0;
/* 1225 */     while (end <= text.length()) {
/* 1226 */       Character ch = null;
/* 1227 */       if (end < text.length()) {
/* 1228 */         ch = Character.valueOf(text.charAt(end));
/*      */       }
/* 1230 */       if (ch == null || "\"\\  ﻿".indexOf(ch.charValue()) != -1 || ' ' > ch
/* 1231 */         .charValue() || ch.charValue() > '~') {
/* 1232 */         if (start < end) {
/* 1233 */           int len = end - start;
/* 1234 */           this.column += len;
/* 1235 */           this.stream.write(text, start, len);
/* 1236 */           start = end;
/*      */         } 
/* 1238 */         if (ch != null) {
/*      */           String data;
/* 1240 */           if (ESCAPE_REPLACEMENTS.containsKey(ch)) {
/* 1241 */             data = "\\" + (String)ESCAPE_REPLACEMENTS.get(ch);
/* 1242 */           } else if (!this.allowUnicode || !StreamReader.isPrintable(ch.charValue())) {
/*      */ 
/*      */             
/* 1245 */             if (ch.charValue() <= 'ÿ') {
/* 1246 */               String s = "0" + Integer.toString(ch.charValue(), 16);
/* 1247 */               data = "\\x" + s.substring(s.length() - 2);
/* 1248 */             } else if (ch.charValue() >= '?' && ch.charValue() <= '?') {
/* 1249 */               if (end + 1 < text.length()) {
/* 1250 */                 Character ch2 = Character.valueOf(text.charAt(++end));
/* 1251 */                 String s = "000" + Long.toHexString(Character.toCodePoint(ch.charValue(), ch2.charValue()));
/* 1252 */                 data = "\\U" + s.substring(s.length() - 8);
/*      */               } else {
/* 1254 */                 String s = "000" + Integer.toString(ch.charValue(), 16);
/* 1255 */                 data = "\\u" + s.substring(s.length() - 4);
/*      */               } 
/*      */             } else {
/* 1258 */               String s = "000" + Integer.toString(ch.charValue(), 16);
/* 1259 */               data = "\\u" + s.substring(s.length() - 4);
/*      */             } 
/*      */           } else {
/* 1262 */             data = String.valueOf(ch);
/*      */           } 
/* 1264 */           this.column += data.length();
/* 1265 */           this.stream.write(data);
/* 1266 */           start = end + 1;
/*      */         } 
/*      */       } 
/* 1269 */       if (0 < end && end < text.length() - 1 && (ch.charValue() == ' ' || start >= end) && this.column + end - start > this.bestWidth && split) {
/*      */         String data;
/*      */         
/* 1272 */         if (start >= end) {
/* 1273 */           data = "\\";
/*      */         } else {
/* 1275 */           data = text.substring(start, end) + "\\";
/*      */         } 
/* 1277 */         if (start < end) {
/* 1278 */           start = end;
/*      */         }
/* 1280 */         this.column += data.length();
/* 1281 */         this.stream.write(data);
/* 1282 */         writeIndent();
/* 1283 */         this.whitespace = false;
/* 1284 */         this.indention = false;
/* 1285 */         if (text.charAt(start) == ' ') {
/* 1286 */           data = "\\";
/* 1287 */           this.column += data.length();
/* 1288 */           this.stream.write(data);
/*      */         } 
/*      */       } 
/* 1291 */       end++;
/*      */     } 
/* 1293 */     writeIndicator("\"", false, false, false);
/*      */   }
/*      */   
/*      */   private String determineBlockHints(String text) {
/* 1297 */     StringBuilder hints = new StringBuilder();
/* 1298 */     if (Constant.LINEBR.has(text.charAt(0), " ")) {
/* 1299 */       hints.append(this.bestIndent);
/*      */     }
/* 1301 */     char ch1 = text.charAt(text.length() - 1);
/* 1302 */     if (Constant.LINEBR.hasNo(ch1)) {
/* 1303 */       hints.append("-");
/* 1304 */     } else if (text.length() == 1 || Constant.LINEBR.has(text.charAt(text.length() - 2))) {
/* 1305 */       hints.append("+");
/*      */     } 
/* 1307 */     return hints.toString();
/*      */   }
/*      */   
/*      */   void writeFolded(String text, boolean split) throws IOException {
/* 1311 */     String hints = determineBlockHints(text);
/* 1312 */     writeIndicator(">" + hints, true, false, false);
/* 1313 */     if (hints.length() > 0 && hints.charAt(hints.length() - 1) == '+') {
/* 1314 */       this.openEnded = true;
/*      */     }
/* 1316 */     writeLineBreak(null);
/* 1317 */     boolean leadingSpace = true;
/* 1318 */     boolean spaces = false;
/* 1319 */     boolean breaks = true;
/* 1320 */     int start = 0, end = 0;
/* 1321 */     while (end <= text.length()) {
/* 1322 */       char ch = Character.MIN_VALUE;
/* 1323 */       if (end < text.length()) {
/* 1324 */         ch = text.charAt(end);
/*      */       }
/* 1326 */       if (breaks) {
/* 1327 */         if (ch == '\000' || Constant.LINEBR.hasNo(ch)) {
/* 1328 */           if (!leadingSpace && ch != '\000' && ch != ' ' && text.charAt(start) == '\n') {
/* 1329 */             writeLineBreak(null);
/*      */           }
/* 1331 */           leadingSpace = (ch == ' ');
/* 1332 */           String data = text.substring(start, end);
/* 1333 */           for (char br : data.toCharArray()) {
/* 1334 */             if (br == '\n') {
/* 1335 */               writeLineBreak(null);
/*      */             } else {
/* 1337 */               writeLineBreak(String.valueOf(br));
/*      */             } 
/*      */           } 
/* 1340 */           if (ch != '\000') {
/* 1341 */             writeIndent();
/*      */           }
/* 1343 */           start = end;
/*      */         } 
/* 1345 */       } else if (spaces) {
/* 1346 */         if (ch != ' ') {
/* 1347 */           if (start + 1 == end && this.column > this.bestWidth && split) {
/* 1348 */             writeIndent();
/*      */           } else {
/* 1350 */             int len = end - start;
/* 1351 */             this.column += len;
/* 1352 */             this.stream.write(text, start, len);
/*      */           } 
/* 1354 */           start = end;
/*      */         }
/*      */       
/* 1357 */       } else if (Constant.LINEBR.has(ch, "\000 ")) {
/* 1358 */         int len = end - start;
/* 1359 */         this.column += len;
/* 1360 */         this.stream.write(text, start, len);
/* 1361 */         if (ch == '\000') {
/* 1362 */           writeLineBreak(null);
/*      */         }
/* 1364 */         start = end;
/*      */       } 
/*      */       
/* 1367 */       if (ch != '\000') {
/* 1368 */         breaks = Constant.LINEBR.has(ch);
/* 1369 */         spaces = (ch == ' ');
/*      */       } 
/* 1371 */       end++;
/*      */     } 
/*      */   }
/*      */   
/*      */   void writeLiteral(String text) throws IOException {
/* 1376 */     String hints = determineBlockHints(text);
/* 1377 */     writeIndicator("|" + hints, true, false, false);
/* 1378 */     if (hints.length() > 0 && hints.charAt(hints.length() - 1) == '+') {
/* 1379 */       this.openEnded = true;
/*      */     }
/* 1381 */     writeLineBreak(null);
/* 1382 */     boolean breaks = true;
/* 1383 */     int start = 0, end = 0;
/* 1384 */     while (end <= text.length()) {
/* 1385 */       char ch = Character.MIN_VALUE;
/* 1386 */       if (end < text.length()) {
/* 1387 */         ch = text.charAt(end);
/*      */       }
/* 1389 */       if (breaks) {
/* 1390 */         if (ch == '\000' || Constant.LINEBR.hasNo(ch)) {
/* 1391 */           String data = text.substring(start, end);
/* 1392 */           for (char br : data.toCharArray()) {
/* 1393 */             if (br == '\n') {
/* 1394 */               writeLineBreak(null);
/*      */             } else {
/* 1396 */               writeLineBreak(String.valueOf(br));
/*      */             } 
/*      */           } 
/* 1399 */           if (ch != '\000') {
/* 1400 */             writeIndent();
/*      */           }
/* 1402 */           start = end;
/*      */         }
/*      */       
/* 1405 */       } else if (ch == '\000' || Constant.LINEBR.has(ch)) {
/* 1406 */         this.stream.write(text, start, end - start);
/* 1407 */         if (ch == '\000') {
/* 1408 */           writeLineBreak(null);
/*      */         }
/* 1410 */         start = end;
/*      */       } 
/*      */       
/* 1413 */       if (ch != '\000') {
/* 1414 */         breaks = Constant.LINEBR.has(ch);
/*      */       }
/* 1416 */       end++;
/*      */     } 
/*      */   }
/*      */   
/*      */   void writePlain(String text, boolean split) throws IOException {
/* 1421 */     if (this.rootContext) {
/* 1422 */       this.openEnded = true;
/*      */     }
/* 1424 */     if (text.length() == 0) {
/*      */       return;
/*      */     }
/* 1427 */     if (!this.whitespace) {
/* 1428 */       this.column++;
/* 1429 */       this.stream.write(SPACE);
/*      */     } 
/* 1431 */     this.whitespace = false;
/* 1432 */     this.indention = false;
/* 1433 */     boolean spaces = false;
/* 1434 */     boolean breaks = false;
/* 1435 */     int start = 0, end = 0;
/* 1436 */     while (end <= text.length()) {
/* 1437 */       char ch = Character.MIN_VALUE;
/* 1438 */       if (end < text.length()) {
/* 1439 */         ch = text.charAt(end);
/*      */       }
/* 1441 */       if (spaces) {
/* 1442 */         if (ch != ' ') {
/* 1443 */           if (start + 1 == end && this.column > this.bestWidth && split) {
/* 1444 */             writeIndent();
/* 1445 */             this.whitespace = false;
/* 1446 */             this.indention = false;
/*      */           } else {
/* 1448 */             int len = end - start;
/* 1449 */             this.column += len;
/* 1450 */             this.stream.write(text, start, len);
/*      */           } 
/* 1452 */           start = end;
/*      */         } 
/* 1454 */       } else if (breaks) {
/* 1455 */         if (Constant.LINEBR.hasNo(ch)) {
/* 1456 */           if (text.charAt(start) == '\n') {
/* 1457 */             writeLineBreak(null);
/*      */           }
/* 1459 */           String data = text.substring(start, end);
/* 1460 */           for (char br : data.toCharArray()) {
/* 1461 */             if (br == '\n') {
/* 1462 */               writeLineBreak(null);
/*      */             } else {
/* 1464 */               writeLineBreak(String.valueOf(br));
/*      */             } 
/*      */           } 
/* 1467 */           writeIndent();
/* 1468 */           this.whitespace = false;
/* 1469 */           this.indention = false;
/* 1470 */           start = end;
/*      */         }
/*      */       
/* 1473 */       } else if (Constant.LINEBR.has(ch, "\000 ")) {
/* 1474 */         int len = end - start;
/* 1475 */         this.column += len;
/* 1476 */         this.stream.write(text, start, len);
/* 1477 */         start = end;
/*      */       } 
/*      */       
/* 1480 */       if (ch != '\000') {
/* 1481 */         spaces = (ch == ' ');
/* 1482 */         breaks = Constant.LINEBR.has(ch);
/*      */       } 
/* 1484 */       end++;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\yaml\snakeyaml\emitter\Emitter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */