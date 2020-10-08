/*     */ package org.jline.reader.impl.history;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.CopyOption;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.OpenOption;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.nio.file.StandardOpenOption;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.time.DateTimeException;
/*     */ import java.time.Instant;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Spliterator;
/*     */ import org.jline.reader.History;
/*     */ import org.jline.reader.LineReader;
/*     */ import org.jline.reader.impl.ReaderUtils;
/*     */ import org.jline.utils.Log;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultHistory
/*     */   implements History
/*     */ {
/*     */   public static final int DEFAULT_HISTORY_SIZE = 500;
/*     */   public static final int DEFAULT_HISTORY_FILE_SIZE = 10000;
/*  36 */   private final LinkedList<History.Entry> items = new LinkedList<>();
/*     */   
/*     */   private LineReader reader;
/*     */   
/*  40 */   private Map<String, HistoryFileData> historyFiles = new HashMap<>();
/*  41 */   private int offset = 0;
/*  42 */   private int index = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultHistory(LineReader reader) {
/*  48 */     attach(reader);
/*     */   }
/*     */   
/*     */   private Path getPath() {
/*  52 */     Object obj = (this.reader != null) ? this.reader.getVariables().get("history-file") : null;
/*  53 */     if (obj instanceof Path)
/*  54 */       return (Path)obj; 
/*  55 */     if (obj instanceof File)
/*  56 */       return ((File)obj).toPath(); 
/*  57 */     if (obj != null) {
/*  58 */       return Paths.get(obj.toString(), new String[0]);
/*     */     }
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void attach(LineReader reader) {
/*  66 */     if (this.reader != reader) {
/*  67 */       this.reader = reader;
/*     */       try {
/*  69 */         load();
/*     */       }
/*  71 */       catch (IllegalArgumentException|IOException e) {
/*  72 */         Log.warn(new Object[] { "Failed to load history", e });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void load() throws IOException {
/*  79 */     Path path = getPath();
/*  80 */     if (path != null) {
/*     */       try {
/*  82 */         if (Files.exists(path, new java.nio.file.LinkOption[0])) {
/*  83 */           Log.trace(new Object[] { "Loading history from: ", path });
/*  84 */           try (BufferedReader reader = Files.newBufferedReader(path)) {
/*  85 */             internalClear();
/*  86 */             reader.lines().forEach(line -> addHistoryLine(path, line));
/*  87 */             setHistoryFileData(path, new HistoryFileData(this.items.size(), this.items.size()));
/*  88 */             maybeResize();
/*     */           } 
/*     */         } 
/*  91 */       } catch (IllegalArgumentException|IOException e) {
/*  92 */         Log.debug(new Object[] { "Failed to load history; clearing", e });
/*  93 */         internalClear();
/*  94 */         throw e;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(Path file, boolean incremental) throws IOException {
/* 101 */     Path path = (file != null) ? file : getPath();
/* 102 */     if (path != null) {
/*     */       try {
/* 104 */         if (Files.exists(path, new java.nio.file.LinkOption[0])) {
/* 105 */           Log.trace(new Object[] { "Reading history from: ", path });
/* 106 */           try (BufferedReader reader = Files.newBufferedReader(path)) {
/* 107 */             reader.lines().forEach(line -> addHistoryLine(path, line, incremental));
/* 108 */             setHistoryFileData(path, new HistoryFileData(this.items.size(), this.items.size()));
/* 109 */             maybeResize();
/*     */           } 
/*     */         } 
/* 112 */       } catch (IllegalArgumentException|IOException e) {
/* 113 */         Log.debug(new Object[] { "Failed to read history; clearing", e });
/* 114 */         internalClear();
/* 115 */         throw e;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private String doHistoryFileDataKey(Path path) {
/* 121 */     return (path != null) ? path.toAbsolutePath().toString() : null;
/*     */   }
/*     */   
/*     */   private HistoryFileData getHistoryFileData(Path path) {
/* 125 */     String key = doHistoryFileDataKey(path);
/* 126 */     if (!this.historyFiles.containsKey(key)) {
/* 127 */       this.historyFiles.put(key, new HistoryFileData());
/*     */     }
/* 129 */     return this.historyFiles.get(key);
/*     */   }
/*     */   
/*     */   private void setHistoryFileData(Path path, HistoryFileData historyFileData) {
/* 133 */     this.historyFiles.put(doHistoryFileDataKey(path), historyFileData);
/*     */   }
/*     */   
/*     */   private boolean isLineReaderHistory(Path path) throws IOException {
/* 137 */     Path lrp = getPath();
/* 138 */     if (lrp == null) {
/* 139 */       if (path != null) {
/* 140 */         return false;
/*     */       }
/* 142 */       return true;
/*     */     } 
/*     */     
/* 145 */     return Files.isSameFile(lrp, path);
/*     */   }
/*     */   
/*     */   private void setLastLoaded(Path path, int lastloaded) {
/* 149 */     getHistoryFileData(path).setLastLoaded(lastloaded);
/*     */   }
/*     */   
/*     */   private void setEntriesInFile(Path path, int entriesInFile) {
/* 153 */     getHistoryFileData(path).setEntriesInFile(entriesInFile);
/*     */   }
/*     */   
/*     */   private void incEntriesInFile(Path path, int amount) {
/* 157 */     getHistoryFileData(path).incEntriesInFile(amount);
/*     */   }
/*     */   
/*     */   private int getLastLoaded(Path path) {
/* 161 */     return getHistoryFileData(path).getLastLoaded();
/*     */   }
/*     */   
/*     */   private int getEntriesInFile(Path path) {
/* 165 */     return getHistoryFileData(path).getEntriesInFile();
/*     */   }
/*     */   
/*     */   protected void addHistoryLine(Path path, String line) {
/* 169 */     addHistoryLine(path, line, false);
/*     */   }
/*     */   
/*     */   protected void addHistoryLine(Path path, String line, boolean checkDuplicates) {
/* 173 */     if (this.reader.isSet(LineReader.Option.HISTORY_TIMESTAMPED)) {
/* 174 */       Instant time; int idx = line.indexOf(':');
/* 175 */       String badHistoryFileSyntax = "Bad history file syntax! The history file `" + path + "` may be an older history: please remove it or use a different history file.";
/*     */ 
/*     */       
/* 178 */       if (idx < 0) {
/* 179 */         throw new IllegalArgumentException(badHistoryFileSyntax);
/*     */       }
/*     */       
/*     */       try {
/* 183 */         time = Instant.ofEpochMilli(Long.parseLong(line.substring(0, idx)));
/* 184 */       } catch (DateTimeException|NumberFormatException e) {
/* 185 */         throw new IllegalArgumentException(badHistoryFileSyntax);
/*     */       } 
/*     */       
/* 188 */       String unescaped = unescape(line.substring(idx + 1));
/* 189 */       internalAdd(time, unescaped, checkDuplicates);
/*     */     } else {
/*     */       
/* 192 */       internalAdd(Instant.now(), unescape(line), checkDuplicates);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void purge() throws IOException {
/* 198 */     internalClear();
/* 199 */     Path path = getPath();
/* 200 */     if (path != null) {
/* 201 */       Log.trace(new Object[] { "Purging history from: ", path });
/* 202 */       Files.deleteIfExists(path);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(Path file, boolean incremental) throws IOException {
/* 208 */     Path path = (file != null) ? file : getPath();
/* 209 */     if (path != null && Files.exists(path, new java.nio.file.LinkOption[0])) {
/* 210 */       path.toFile().delete();
/*     */     }
/* 212 */     internalWrite(path, incremental ? getLastLoaded(path) : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void append(Path file, boolean incremental) throws IOException {
/* 217 */     internalWrite((file != null) ? file : getPath(), incremental ? 
/* 218 */         getLastLoaded(file) : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void save() throws IOException {
/* 223 */     internalWrite(getPath(), getLastLoaded(getPath()));
/*     */   }
/*     */   
/*     */   private void internalWrite(Path path, int from) throws IOException {
/* 227 */     if (path != null) {
/* 228 */       Log.trace(new Object[] { "Saving history to: ", path });
/* 229 */       Files.createDirectories(path.toAbsolutePath().getParent(), (FileAttribute<?>[])new FileAttribute[0]);
/*     */       
/* 231 */       try (BufferedWriter writer = Files.newBufferedWriter(path.toAbsolutePath(), new OpenOption[] { StandardOpenOption.WRITE, StandardOpenOption.APPEND, StandardOpenOption.CREATE })) {
/*     */         
/* 233 */         for (History.Entry entry : this.items.subList(from, this.items.size())) {
/* 234 */           if (isPersistable(entry)) {
/* 235 */             writer.append(format(entry));
/*     */           }
/*     */         } 
/*     */       } 
/* 239 */       incEntriesInFile(path, this.items.size() - from);
/* 240 */       int max = ReaderUtils.getInt(this.reader, "history-file-size", 10000);
/* 241 */       if (getEntriesInFile(path) > max + max / 4) {
/* 242 */         trimHistory(path, max);
/*     */       }
/*     */     } 
/* 245 */     setLastLoaded(path, this.items.size());
/*     */   }
/*     */   
/*     */   protected void trimHistory(Path path, int max) throws IOException {
/* 249 */     Log.trace(new Object[] { "Trimming history path: ", path });
/*     */     
/* 251 */     LinkedList<History.Entry> allItems = new LinkedList<>();
/* 252 */     try (BufferedReader reader = Files.newBufferedReader(path)) {
/* 253 */       reader.lines().forEach(l -> {
/*     */             int idx = l.indexOf(':');
/*     */             
/*     */             Instant time = Instant.ofEpochMilli(Long.parseLong(l.substring(0, idx)));
/*     */             String line = unescape(l.substring(idx + 1));
/*     */             allItems.add(createEntry(allItems.size(), time, line));
/*     */           });
/*     */     } 
/* 261 */     doTrimHistory(allItems, max);
/*     */     
/* 263 */     Path temp = Files.createTempFile(path.toAbsolutePath().getParent(), path.getFileName().toString(), ".tmp", (FileAttribute<?>[])new FileAttribute[0]);
/* 264 */     try (BufferedWriter writer = Files.newBufferedWriter(temp, new OpenOption[] { StandardOpenOption.WRITE })) {
/* 265 */       for (History.Entry entry : allItems) {
/* 266 */         writer.append(format(entry));
/*     */       }
/*     */     } 
/* 269 */     Files.move(temp, path, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*     */     
/* 271 */     if (isLineReaderHistory(path)) {
/* 272 */       internalClear();
/* 273 */       this.offset = ((History.Entry)allItems.get(0)).index();
/* 274 */       this.items.addAll(allItems);
/* 275 */       setHistoryFileData(path, new HistoryFileData(this.items.size(), this.items.size()));
/*     */     } else {
/* 277 */       setEntriesInFile(path, allItems.size());
/*     */     } 
/* 279 */     maybeResize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EntryImpl createEntry(int index, Instant time, String line) {
/* 290 */     return new EntryImpl(index, time, line);
/*     */   }
/*     */   
/*     */   private void internalClear() {
/* 294 */     this.offset = 0;
/* 295 */     this.index = 0;
/* 296 */     this.historyFiles = new HashMap<>();
/* 297 */     this.items.clear();
/*     */   }
/*     */   
/*     */   static void doTrimHistory(List<History.Entry> allItems, int max) {
/* 301 */     int idx = 0;
/* 302 */     while (idx < allItems.size()) {
/* 303 */       int ridx = allItems.size() - idx - 1;
/* 304 */       String line = ((History.Entry)allItems.get(ridx)).line().trim();
/* 305 */       ListIterator<History.Entry> iterator = allItems.listIterator(ridx);
/* 306 */       while (iterator.hasPrevious()) {
/* 307 */         String l = ((History.Entry)iterator.previous()).line();
/* 308 */         if (line.equals(l.trim())) {
/* 309 */           iterator.remove();
/*     */         }
/*     */       } 
/* 312 */       idx++;
/*     */     } 
/* 314 */     while (allItems.size() > max) {
/* 315 */       allItems.remove(0);
/*     */     }
/*     */   }
/*     */   
/*     */   public int size() {
/* 320 */     return this.items.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 324 */     return this.items.isEmpty();
/*     */   }
/*     */   
/*     */   public int index() {
/* 328 */     return this.offset + this.index;
/*     */   }
/*     */   
/*     */   public int first() {
/* 332 */     return this.offset;
/*     */   }
/*     */   
/*     */   public int last() {
/* 336 */     return this.offset + this.items.size() - 1;
/*     */   }
/*     */   
/*     */   private String format(History.Entry entry) {
/* 340 */     if (this.reader.isSet(LineReader.Option.HISTORY_TIMESTAMPED)) {
/* 341 */       return Long.toString(entry.time().toEpochMilli()) + ":" + escape(entry.line()) + "\n";
/*     */     }
/* 343 */     return escape(entry.line()) + "\n";
/*     */   }
/*     */   
/*     */   public String get(int index) {
/* 347 */     return ((History.Entry)this.items.get(index - this.offset)).line();
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(Instant time, String line) {
/* 352 */     Objects.requireNonNull(time);
/* 353 */     Objects.requireNonNull(line);
/*     */     
/* 355 */     if (ReaderUtils.getBoolean(this.reader, "disable-history", false)) {
/*     */       return;
/*     */     }
/* 358 */     if (ReaderUtils.isSet(this.reader, LineReader.Option.HISTORY_IGNORE_SPACE) && line.startsWith(" ")) {
/*     */       return;
/*     */     }
/* 361 */     if (ReaderUtils.isSet(this.reader, LineReader.Option.HISTORY_REDUCE_BLANKS)) {
/* 362 */       line = line.trim();
/*     */     }
/* 364 */     if (ReaderUtils.isSet(this.reader, LineReader.Option.HISTORY_IGNORE_DUPS) && 
/* 365 */       !this.items.isEmpty() && line.equals(((History.Entry)this.items.getLast()).line())) {
/*     */       return;
/*     */     }
/*     */     
/* 369 */     if (matchPatterns(ReaderUtils.getString(this.reader, "history-ignore", ""), line)) {
/*     */       return;
/*     */     }
/* 372 */     internalAdd(time, line);
/* 373 */     if (ReaderUtils.isSet(this.reader, LineReader.Option.HISTORY_INCREMENTAL)) {
/*     */       try {
/* 375 */         save();
/*     */       }
/* 377 */       catch (IOException e) {
/* 378 */         Log.warn(new Object[] { "Failed to save history", e });
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean matchPatterns(String patterns, String line) {
/* 384 */     if (patterns == null || patterns.isEmpty()) {
/* 385 */       return false;
/*     */     }
/* 387 */     StringBuilder sb = new StringBuilder();
/* 388 */     for (int i = 0; i < patterns.length(); i++) {
/* 389 */       char ch = patterns.charAt(i);
/* 390 */       if (ch == '\\') {
/* 391 */         ch = patterns.charAt(++i);
/* 392 */         sb.append(ch);
/* 393 */       } else if (ch == ':') {
/* 394 */         sb.append('|');
/* 395 */       } else if (ch == '*') {
/* 396 */         sb.append('.').append('*');
/*     */       } 
/*     */     } 
/* 399 */     return line.matches(sb.toString());
/*     */   }
/*     */   
/*     */   protected void internalAdd(Instant time, String line) {
/* 403 */     internalAdd(time, line, false);
/*     */   }
/*     */   
/*     */   protected void internalAdd(Instant time, String line, boolean checkDuplicates) {
/* 407 */     History.Entry entry = new EntryImpl(this.offset + this.items.size(), time, line);
/* 408 */     if (checkDuplicates) {
/* 409 */       for (History.Entry e : this.items) {
/* 410 */         if (e.line().trim().equals(line.trim())) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     }
/* 415 */     this.items.add(entry);
/* 416 */     maybeResize();
/*     */   }
/*     */   
/*     */   private void maybeResize() {
/* 420 */     while (size() > ReaderUtils.getInt(this.reader, "history-size", 500)) {
/* 421 */       this.items.removeFirst();
/* 422 */       for (HistoryFileData hfd : this.historyFiles.values()) {
/* 423 */         hfd.decLastLoaded();
/*     */       }
/* 425 */       this.offset++;
/*     */     } 
/* 427 */     this.index = size();
/*     */   }
/*     */   
/*     */   public ListIterator<History.Entry> iterator(int index) {
/* 431 */     return this.items.listIterator(index - this.offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public Spliterator<History.Entry> spliterator() {
/* 436 */     return this.items.spliterator();
/*     */   }
/*     */   
/*     */   protected static class EntryImpl
/*     */     implements History.Entry {
/*     */     private final int index;
/*     */     private final Instant time;
/*     */     private final String line;
/*     */     
/*     */     public EntryImpl(int index, Instant time, String line) {
/* 446 */       this.index = index;
/* 447 */       this.time = time;
/* 448 */       this.line = line;
/*     */     }
/*     */     
/*     */     public int index() {
/* 452 */       return this.index;
/*     */     }
/*     */     
/*     */     public Instant time() {
/* 456 */       return this.time;
/*     */     }
/*     */     
/*     */     public String line() {
/* 460 */       return this.line;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 465 */       return String.format("%d: %s", new Object[] { Integer.valueOf(this.index), this.line });
/*     */     }
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
/*     */   public boolean moveToLast() {
/* 481 */     int lastEntry = size() - 1;
/* 482 */     if (lastEntry >= 0 && lastEntry != this.index) {
/* 483 */       this.index = size() - 1;
/* 484 */       return true;
/*     */     } 
/*     */     
/* 487 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean moveTo(int index) {
/* 494 */     index -= this.offset;
/* 495 */     if (index >= 0 && index < size()) {
/* 496 */       this.index = index;
/* 497 */       return true;
/*     */     } 
/* 499 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean moveToFirst() {
/* 509 */     if (size() > 0 && this.index != 0) {
/* 510 */       this.index = 0;
/* 511 */       return true;
/*     */     } 
/* 513 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveToEnd() {
/* 521 */     this.index = size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String current() {
/* 528 */     if (this.index >= size()) {
/* 529 */       return "";
/*     */     }
/* 531 */     return ((History.Entry)this.items.get(this.index)).line();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean previous() {
/* 540 */     if (this.index <= 0) {
/* 541 */       return false;
/*     */     }
/* 543 */     this.index--;
/* 544 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean next() {
/* 553 */     if (this.index >= size()) {
/* 554 */       return false;
/*     */     }
/* 556 */     this.index++;
/* 557 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 562 */     StringBuilder sb = new StringBuilder();
/* 563 */     for (ListIterator<History.Entry> listIterator = iterator(); listIterator.hasNext(); ) { History.Entry e = listIterator.next();
/* 564 */       sb.append(e.toString()).append("\n"); }
/*     */     
/* 566 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private static String escape(String s) {
/* 570 */     StringBuilder sb = new StringBuilder();
/* 571 */     for (int i = 0; i < s.length(); i++) {
/* 572 */       char ch = s.charAt(i);
/* 573 */       switch (ch) {
/*     */         case '\n':
/* 575 */           sb.append('\\');
/* 576 */           sb.append('n');
/*     */           break;
/*     */         case '\r':
/* 579 */           sb.append('\\');
/* 580 */           sb.append('r');
/*     */           break;
/*     */         case '\\':
/* 583 */           sb.append('\\');
/* 584 */           sb.append('\\');
/*     */           break;
/*     */         default:
/* 587 */           sb.append(ch);
/*     */           break;
/*     */       } 
/*     */     } 
/* 591 */     return sb.toString();
/*     */   }
/*     */   
/*     */   static String unescape(String s) {
/* 595 */     StringBuilder sb = new StringBuilder();
/* 596 */     for (int i = 0; i < s.length(); i++) {
/* 597 */       char ch = s.charAt(i);
/* 598 */       switch (ch) {
/*     */         case '\\':
/* 600 */           ch = s.charAt(++i);
/* 601 */           if (ch == 'n') {
/* 602 */             sb.append('\n'); break;
/* 603 */           }  if (ch == 'r') {
/* 604 */             sb.append('\r'); break;
/*     */           } 
/* 606 */           sb.append(ch);
/*     */           break;
/*     */         
/*     */         default:
/* 610 */           sb.append(ch);
/*     */           break;
/*     */       } 
/*     */     } 
/* 614 */     return sb.toString();
/*     */   }
/*     */   public DefaultHistory() {}
/*     */   
/* 618 */   private class HistoryFileData { private int lastLoaded = 0;
/* 619 */     private int entriesInFile = 0;
/*     */ 
/*     */     
/*     */     public HistoryFileData() {}
/*     */     
/*     */     public HistoryFileData(int lastLoaded, int entriesInFile) {
/* 625 */       this.lastLoaded = lastLoaded;
/* 626 */       this.entriesInFile = entriesInFile;
/*     */     }
/*     */     
/*     */     public int getLastLoaded() {
/* 630 */       return this.lastLoaded;
/*     */     }
/*     */     
/*     */     public void setLastLoaded(int lastLoaded) {
/* 634 */       this.lastLoaded = lastLoaded;
/*     */     }
/*     */     
/*     */     public void decLastLoaded() {
/* 638 */       this.lastLoaded--;
/* 639 */       if (this.lastLoaded < 0) {
/* 640 */         this.lastLoaded = 0;
/*     */       }
/*     */     }
/*     */     
/*     */     public int getEntriesInFile() {
/* 645 */       return this.entriesInFile;
/*     */     }
/*     */     
/*     */     public void setEntriesInFile(int entriesInFile) {
/* 649 */       this.entriesInFile = entriesInFile;
/*     */     }
/*     */     
/*     */     public void incEntriesInFile(int amount) {
/* 653 */       this.entriesInFile += amount;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\history\DefaultHistory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */