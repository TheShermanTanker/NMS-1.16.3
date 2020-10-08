/*     */ package org.jline.reader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Path;
/*     */ import java.time.Instant;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
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
/*     */ public interface History
/*     */   extends Iterable<History.Entry>
/*     */ {
/*     */   default boolean isEmpty() {
/*  75 */     return (size() == 0);
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
/*     */   default void add(String line) {
/*  87 */     add(Instant.now(), line);
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
/*     */   default boolean isPersistable(Entry entry) {
/*  99 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default ListIterator<Entry> iterator() {
/* 118 */     return iterator(first());
/*     */   }
/*     */   
/*     */   default Iterator<Entry> reverseIterator() {
/* 122 */     return reverseIterator(last());
/*     */   }
/*     */   
/*     */   default Iterator<Entry> reverseIterator(final int index) {
/* 126 */     return new Iterator<Entry>() {
/* 127 */         private final ListIterator<History.Entry> it = History.this.iterator(index + 1);
/*     */         
/*     */         public boolean hasNext() {
/* 130 */           return this.it.hasPrevious();
/*     */         }
/*     */         
/*     */         public History.Entry next() {
/* 134 */           return this.it.previous();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   void attach(LineReader paramLineReader);
/*     */   
/*     */   void load() throws IOException;
/*     */   
/*     */   void save() throws IOException;
/*     */   
/*     */   void write(Path paramPath, boolean paramBoolean) throws IOException;
/*     */   
/*     */   void append(Path paramPath, boolean paramBoolean) throws IOException;
/*     */   
/*     */   void read(Path paramPath, boolean paramBoolean) throws IOException;
/*     */   
/*     */   void purge() throws IOException;
/*     */   
/*     */   int size();
/*     */   
/*     */   int index();
/*     */   
/*     */   int first();
/*     */   
/*     */   int last();
/*     */   
/*     */   String get(int paramInt);
/*     */   
/*     */   void add(Instant paramInstant, String paramString);
/*     */   
/*     */   ListIterator<Entry> iterator(int paramInt);
/*     */   
/*     */   String current();
/*     */   
/*     */   boolean previous();
/*     */   
/*     */   boolean next();
/*     */   
/*     */   boolean moveToFirst();
/*     */   
/*     */   boolean moveToLast();
/*     */   
/*     */   boolean moveTo(int paramInt);
/*     */   
/*     */   void moveToEnd();
/*     */   
/*     */   public static interface Entry {
/*     */     int index();
/*     */     
/*     */     Instant time();
/*     */     
/*     */     String line();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\History.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */